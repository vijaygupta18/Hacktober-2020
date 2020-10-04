package in.inator.dbmsproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.shashank.sony.fancytoastlib.FancyToast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register extends AppCompatActivity {
ImageButton bt_reg;
EditText et_name,et_email,et_pass,et_confirmpass;
TextView alsign;
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
boolean termscheck = false;
    String resUrl="http://tesla.codes/xapi/auth.php?apicall=signup";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        et_name = findViewById(R.id.fullName);
        et_email = findViewById(R.id.editTextEmail);
        et_pass = findViewById(R.id.editTextPassword);
        et_confirmpass = findViewById(R.id.confirmPassword);
        bt_reg = findViewById(R.id.buttonSignup);
        bt_reg.setEnabled(true);
        alsign = findViewById(R.id.textViewSignin);
        bt_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFormData();
                bt_reg.setEnabled(false);
            }
        });
        alsign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register.this, Login.class));
            }
        });
    }

    public void getContent(final String url, final String name, final String email, final String pass){

        new Thread(new Runnable() {
            public void run() {
                Looper.prepare();
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
                RequestQueue queue = Volley.newRequestQueue(Register.this);
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
                                    System.out.println(error +  "idhar");
                                    String message = regObject.getString("message");

                                    if(error){
                                        FancyToast.makeText(Register.this,message,FancyToast.LENGTH_LONG,FancyToast.ERROR,false).show();
                                    }else{
                                        final JSONObject userObj = regObject.getJSONObject("user");
                                        int uid = userObj.getInt("id");
                                        FancyToast.makeText(Register.this,message,FancyToast.LENGTH_LONG,FancyToast.SUCCESS,false).show();
                                        startActivity(new Intent(Register.this, Login.class));
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
                        postParam.put("username", name);
                        postParam.put("email", email);
                        postParam.put("password", pass);


                        return postParam;
                    }

                };

                queue.add(jsonObjRequest);
                Looper.loop();

                bt_reg.setEnabled(true);
        }
    }).start();

}
public void getFormData(){
        System.out.println("Im here");

        String fullname = et_name.getText().toString().trim();
        String email = et_email.getText().toString().trim();
        String password = et_pass.getText().toString().trim();
        String conpassword = et_confirmpass.getText().toString().trim();
        if(fullname.equals("")){
            System.out.println("Im here");
            FancyToast.makeText(Register.this,"Enter a valid name",FancyToast.LENGTH_LONG,FancyToast.ERROR,false).show();
            bt_reg.setEnabled(true);
            return;
        }

        if(email.equals("") || !validate(email)){
            FancyToast.makeText(this,"Enter a valid email id",FancyToast.LENGTH_LONG,FancyToast.ERROR,false).show();
            bt_reg.setEnabled(true);
            return;
        }

        if(password.equals("")){
            FancyToast.makeText(this,"Enter a valid password",FancyToast.LENGTH_LONG,FancyToast.ERROR,false).show();
            bt_reg.setEnabled(true);
            return;
        }
        if(conpassword.equals("")){
            FancyToast.makeText(this,"Please confirm your password",FancyToast.LENGTH_LONG,FancyToast.ERROR,false).show();
            bt_reg.setEnabled(true);
            return;
        }
        if(!password.equals(conpassword)){
            FancyToast.makeText(this,"Passwords do not match",FancyToast.LENGTH_LONG,FancyToast.ERROR,false).show();
            bt_reg.setEnabled(true);
            return;
        }
        getContent(resUrl,fullname,email,password);
}


    public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }
}