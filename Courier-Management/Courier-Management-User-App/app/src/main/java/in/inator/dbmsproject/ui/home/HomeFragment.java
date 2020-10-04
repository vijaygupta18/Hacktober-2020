package in.inator.dbmsproject.ui.home;

import android.media.session.PlaybackState;
import android.os.Bundle;
import android.os.Looper;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.shashank.sony.fancytoastlib.FancyToast;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import in.inator.dbmsproject.CustomAdapter;
import in.inator.dbmsproject.R;
import in.inator.dbmsproject.single;
import in.inator.dbmsproject.single_data;


public class HomeFragment extends Fragment {
    CarouselView carouselView;
    RecyclerView rv_eb;
    private HomeViewModel homeViewModel;
    String url = "http://tesla.codes/xapi/couriers.php?apicall=getall";
    int[] sampleImages = {R.drawable.image_1, R.drawable.image_2, R.drawable.image_3, R.drawable.image_4, R.drawable.image_5};
    TextView norder;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });
        rv_eb = root.findViewById(R.id.rv);
        carouselView = root.findViewById(R.id.carouselView);
        carouselView.setPageCount(sampleImages.length);
        carouselView.setImageListener(imageListener);
        norder = root.findViewById(R.id.norder);
        fetchAllOrders();
        return root;
    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(sampleImages[position]);
        }
    };

    public void fetchAllOrders(){
        final int uid = PreferenceManager.getDefaultSharedPreferences(getContext()).getInt("currid", -1);
        new Thread(new Runnable() {
            public void run() {
                Looper.prepare();
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
                RequestQueue queue = Volley.newRequestQueue(getContext());
                StringRequest jsonObjRequest = new StringRequest(Request.Method.POST,
                        url,
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
                                        if(message.equals("You have no live orders")){
                                            norder.setVisibility(View.VISIBLE);
                                        }
                                    }else{

                                        FancyToast.makeText(getContext(),message,FancyToast.LENGTH_LONG,FancyToast.SUCCESS,false).show();
                                        JSONArray ordersArray = regObject.getJSONArray("list");
                                        displayOrders(ordersArray);

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
                        return postParam;
                    }

                };

                queue.add(jsonObjRequest);
                Looper.loop();
            }
        }).start();
    }

    public void displayOrders(JSONArray ja){

        int len = ja.length();
        ArrayList<single_data> arrayList = new ArrayList<single_data>();

        for(int i = 0; i < len; i++){
            try {
                String inner = ja.getString(i);
                //String[] ka = innerObj.getArray(i);
                String[] ia = inner.split(",");
                arrayList.add(0,new single_data(ia[0].replaceAll("\\[", "").replaceAll("\\]","").replaceAll("\"",""), ia[1].replaceAll("\\[", "").replaceAll("\\]","").replaceAll("\"",""), ia[2].replaceAll("\\[", "").replaceAll("\\]","").replaceAll("\"",""), ia[3].replaceAll("\\[", "").replaceAll("\\]","").replaceAll("\"",""), ia[4].replaceAll("\\[", "").replaceAll("\\]","").replaceAll("\"",""), ia[5].replaceAll("\\[", "").replaceAll("\\]","").replaceAll("\"",""), ia[6].replaceAll("\\[", "").replaceAll("\\]","").replaceAll("\"","")));

            }catch (JSONException jz){
                System.out.println("Error is: " + jz.toString());
            }


        }
        System.out.println("I am here");
        CustomAdapter c_adapter = new CustomAdapter(arrayList);
        rv_eb.setHasFixedSize(true);
        rv_eb.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_eb.setAdapter(c_adapter);
    }
}