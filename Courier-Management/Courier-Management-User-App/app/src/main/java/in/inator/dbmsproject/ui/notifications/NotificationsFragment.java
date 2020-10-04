package in.inator.dbmsproject.ui.notifications;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.shashank.sony.fancytoastlib.FancyToast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import in.inator.dbmsproject.Dashboard;
import in.inator.dbmsproject.Login;
import in.inator.dbmsproject.R;

public class NotificationsFragment extends Fragment {
EditText phn, address,city,state,pincode;
Button submitbt;
String resUrl="http://tesla.codes/xapi/auth.php?apicall=updateuser";
    private NotificationsViewModel notificationsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);

        notificationsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
               // textView.setText(s);
            }
        });

        phn = root.findViewById(R.id.et_phn);
        address = root.findViewById(R.id.et_address);
        city = root.findViewById(R.id.et_ct);
        state = root.findViewById(R.id.et_state);
        pincode = root.findViewById(R.id.et_pin);

        phn.setText(PreferenceManager.getDefaultSharedPreferences(getContext()).getString("uphn", ""));
        address.setText(PreferenceManager.getDefaultSharedPreferences(getContext()).getString("uaddress", ""));
        city.setText(PreferenceManager.getDefaultSharedPreferences(getContext()).getString("ucity", ""));
        state.setText(PreferenceManager.getDefaultSharedPreferences(getContext()).getString("ustate", ""));
        pincode.setText(PreferenceManager.getDefaultSharedPreferences(getContext()).getString("upin", ""));

        submitbt = root.findViewById(R.id.bt_pubdate);

        submitbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitbt.setEnabled(false);
                filterData();
            }
        });

        return root;
    }

    public void filterData(){
        String sphn = phn.getText().toString().trim(), scity = city.getText().toString().trim(), sstate = state.getText().toString().trim(), spincode = pincode.getText().toString().trim(), saddress = address.getText().toString().trim();

        if(sphn.equals("")){
            FancyToast.makeText(getContext(),"Enter a valid phone number",FancyToast.LENGTH_LONG,FancyToast.ERROR,false).show();
            submitbt.setEnabled(true);
            return;
        }

        if(scity.equals("")){
            FancyToast.makeText(getContext(),"Enter a valid city",FancyToast.LENGTH_LONG,FancyToast.ERROR,false).show();
            submitbt.setEnabled(true);
            return;
        }

        if(sstate.equals("")){
            FancyToast.makeText(getContext(),"Enter a valid state",FancyToast.LENGTH_LONG,FancyToast.ERROR,false).show();
            submitbt.setEnabled(true);
            return;
        }

        if(spincode.equals("")){
            FancyToast.makeText(getContext(),"Enter a valid pincode",FancyToast.LENGTH_LONG,FancyToast.ERROR,false).show();
            submitbt.setEnabled(true);
            return;
        }

        if(saddress.equals("")){
            FancyToast.makeText(getContext(),"Enter a valid address",FancyToast.LENGTH_LONG,FancyToast.ERROR,false).show();
            submitbt.setEnabled(true);
            return;
        }

        updateProfile(sphn,scity,sstate,spincode,saddress);

    }

    public void updateProfile(final String phone, final String scity, final String sstate, final String spin, final String sadr){
        final int uid = PreferenceManager.getDefaultSharedPreferences(getContext()).getInt("currid", -1);
        new Thread(new Runnable() {
            public void run() {
                Looper.prepare();
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
                RequestQueue queue = Volley.newRequestQueue(getContext());
                StringRequest jsonObjRequest = new StringRequest(Request.Method.POST,
                        resUrl,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                System.out.println("Here");
                                System.out.println(response);
                                try{
                                    JSONObject regObject = new JSONObject(response);
                                    boolean error = regObject.getBoolean("error");
                                    String message = regObject.getString("message");

                                    if(error){
                                        FancyToast.makeText(getContext(),message,FancyToast.LENGTH_LONG,FancyToast.ERROR,false).show();
                                    }else{

                                        FancyToast.makeText(getContext(),message,FancyToast.LENGTH_LONG,FancyToast.SUCCESS,false).show();
                                        PreferenceManager.getDefaultSharedPreferences(getContext()).edit().putString("uphn", phone).apply();
                                        PreferenceManager.getDefaultSharedPreferences(getContext()).edit().putString("uaddress", sadr).apply();
                                        PreferenceManager.getDefaultSharedPreferences(getContext()).edit().putString("ucity", scity).apply();
                                        PreferenceManager.getDefaultSharedPreferences(getContext()).edit().putString("ustate", sstate).apply();
                                        PreferenceManager.getDefaultSharedPreferences(getContext()).edit().putString("upin", spin).apply();
                                    }

                                }catch(JSONException j){
                                    System.out.println("Ye he error: " + j.toString());
                                }

                            }
                        }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("Error: " + error.toString());
                    }
                }) {

                    @Override
                    public String getBodyContentType() {
                        return "application/x-www-form-urlencoded; charset=UTF-8";
                    }

                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {


                        Map<String, String> postParam = new HashMap<String, String>();
                        postParam.put("id", String.valueOf(uid));
                        postParam.put("cell", phone);
                        postParam.put("address", sadr);
                        postParam.put("city", scity);
                        postParam.put("state", sstate);
                        postParam.put("pincode", spin);
                        return postParam;
                    }

                };

                queue.add(jsonObjRequest);
                Looper.loop();

                submitbt.setEnabled(true);
            }
        }).start();

    }

}