package in.inator.dbmsproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Login extends AppCompatActivity {
EditText et_email, et_pass;
TextView tv_reg;
ImageButton bt_login;
    String resUrl="http://tesla.codes/xapi/auth.php?apicall=login";
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_email = findViewById(R.id.editTextEmail);
        et_pass = findViewById(R.id.editTextPassword);
        tv_reg = findViewById(R.id.ToReg);
        bt_login = findViewById(R.id.buttonSignin);
        bt_login.setEnabled(true);
        tv_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, Register.class));
            }
        });

        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateData();
                bt_login.setEnabled(false);
            }
        });

    }

    public void validateData(){
        String email = et_email.getText().toString().trim();
        String pass = et_pass.getText().toString().trim();
        if(email.equals("") || !validate(email)){
            FancyToast.makeText(this,"Enter a valid email id",FancyToast.LENGTH_LONG,FancyToast.ERROR,false).show();
            bt_login.setEnabled(true);
            return;
        }

        if(pass.equals("")){
            FancyToast.makeText(this,"Enter a valid password",FancyToast.LENGTH_LONG,FancyToast.ERROR,false).show();
            bt_login.setEnabled(true);
            return;
        }

        loginUser(email,pass);

    }

    public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

    public void loginUser(final String email, final String pass){
        new Thread(new Runnable() {
            public void run() {
                Looper.prepare();
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
                RequestQueue queue = Volley.newRequestQueue(Login.this);
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
                                        FancyToast.makeText(Login.this,message,FancyToast.LENGTH_LONG,FancyToast.ERROR,false).show();
                                    }else{
                                        final JSONObject userObj = regObject.getJSONObject("user");
                                        int uid = userObj.getInt("id");
                                        FancyToast.makeText(Login.this,message,FancyToast.LENGTH_LONG,FancyToast.SUCCESS,false).show();
                                        PreferenceManager.getDefaultSharedPreferences(getBaseContext()).edit().putString("authstate", "1").apply();
                                        PreferenceManager.getDefaultSharedPreferences(getBaseContext()).edit().putInt("currid", uid).apply();
                                        startActivity(new Intent(Login.this, Dashboard.class));
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
                        postParam.put("email", email);
                        postParam.put("password", pass);


                        return postParam;
                    }

                };

                queue.add(jsonObjRequest);
                Looper.loop();

                bt_login.setEnabled(true);
            }
        }).start();
    }
}