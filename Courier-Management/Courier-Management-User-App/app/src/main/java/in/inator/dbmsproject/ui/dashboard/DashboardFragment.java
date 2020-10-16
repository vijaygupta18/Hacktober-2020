package in.inator.dbmsproject.ui.dashboard;

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

import in.inator.dbmsproject.R;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    EditText et_tit, et_des, et_weight, et_add, et_city, et_state, et_pincode, et_type;
    Button submitbt;
    String resUrl="http://tesla.codes/xapi/couriers.php?apicall=addpackage";
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        dashboardViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
               // textView.setText(s);
            }
        });

        et_tit = root.findViewById(R.id.et_ptitle);
        et_des = root.findViewById(R.id.et_pdesc);
        et_weight = root.findViewById(R.id.et_pweight);
        et_add = root.findViewById(R.id.et_paddress);
        et_city = root.findViewById(R.id.et_pct);
        et_state = root.findViewById(R.id.et_pstate);
        et_pincode = root.findViewById(R.id.et_ppin);
        //et_type = root.findViewById(R.id.);
        submitbt = root.findViewById(R.id.bt_psend);

        submitbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitbt.setEnabled(false);
                validateData();
            }
        });

        return root;
    }

    public void validateData(){
        String title = et_tit.getText().toString().trim(), desc = et_des.getText().toString().trim(),weight = et_weight.getText().toString().trim(), address = et_add.getText().toString().trim(), city = et_city.getText().toString().trim(), state = et_state.getText().toString().trim(), pincode = et_pincode.getText().toString().trim(), type = "0";

        if(title.equals("")){
            FancyToast.makeText(getContext(),"Enter a valid title",FancyToast.LENGTH_LONG,FancyToast.ERROR,false).show();
            submitbt.setEnabled(true);
            return;
        }

        if(weight.equals("")){
            FancyToast.makeText(getContext(),"Enter a valid weight",FancyToast.LENGTH_LONG,FancyToast.ERROR,false).show();
            submitbt.setEnabled(true);
            return;
        }

        if(desc.equals("")){
            FancyToast.makeText(getContext(),"Enter a valid description",FancyToast.LENGTH_LONG,FancyToast.ERROR,false).show();
            submitbt.setEnabled(true);
            return;
        }


        if(city.equals("")){
            FancyToast.makeText(getContext(),"Enter a valid city",FancyToast.LENGTH_LONG,FancyToast.ERROR,false).show();
            submitbt.setEnabled(true);
            return;
        }

        if(state.equals("")){
            FancyToast.makeText(getContext(),"Enter a valid state",FancyToast.LENGTH_LONG,FancyToast.ERROR,false).show();
            submitbt.setEnabled(true);
            return;
        }

        if(pincode.equals("")){
            FancyToast.makeText(getContext(),"Enter a valid pincode",FancyToast.LENGTH_LONG,FancyToast.ERROR,false).show();
            submitbt.setEnabled(true);
            return;
        }

        if(address.equals("")){
            FancyToast.makeText(getContext(),"Enter a valid address",FancyToast.LENGTH_LONG,FancyToast.ERROR,false).show();
            submitbt.setEnabled(true);
            return;
        }

        placeOrder(title, desc,weight, address, city, state, pincode, "0");
    }

    public void placeOrder(final String title, final String desc, final String weight, final String address, final String city, final String state, final String pincode, final String type){
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
                        postParam.put("uid", String.valueOf(uid));
                        postParam.put("title", title);
                        postParam.put("description", desc);
                        postParam.put("weight", weight);
                        postParam.put("address", address);
                        postParam.put("city", city);
                        postParam.put("state", state);
                        postParam.put("pincode", pincode);
                        postParam.put("type", type);
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
