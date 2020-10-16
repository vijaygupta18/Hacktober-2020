package com.sugata.mycredibleinfo;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.karan.churi.PermissionManager.PermissionManager;
import com.sugata.mycredibleinfo.LoginClasses.LoginSignupData;
import com.sugata.mycredibleinfo.LoginClasses.ServerTest;
import com.sugata.mycredibleinfo.LoginClasses.User;
import com.sugata.mycredibleinfo.UserDetails.PersonalDetailsActivity;
import com.sugata.mycredibleinfo.remote.APIUtils;
import com.sugata.mycredibleinfo.remote.UserService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    PermissionManager permissionManager;

    public static final String MY_PREF = "MyPreference";

    private String userEmail;
    private int userId;

    private String email, password;

    UserService userService;

    Button loginButton, signUpButton;

    EditText emailEditText, passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        permissionManager = new PermissionManager() {};
        permissionManager.checkAndRequestPermissions(this);

        loginButton = findViewById(R.id.login_button);
        signUpButton = findViewById(R.id.signup_button);

        emailEditText = findViewById(R.id.emailText);
        passwordEditText = findViewById(R.id.password);

        userService = APIUtils.getUserService();

        serverTest();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                email = emailEditText.getText().toString().trim();
                password = passwordEditText.getText().toString().trim();

                if(email != null && password != null)
                {
                    User user = new User(email, password);
                    loginUser(user);
                }
                else
                {
                    Toast.makeText(LoginActivity.this, "Login Details Empty!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                email = emailEditText.getText().toString().trim();
                password = passwordEditText.getText().toString().trim();


                if(email != null && password != null)
                {
                    User user = new User(email, password);
                    signUpUser(user, email);
                }
                else
                {
                    Toast.makeText(LoginActivity.this, "SignUp Details Empty!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    @Override
    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
        permissionManager.checkResult(requestCode,permissions, grantResults);
    }

    private void loginUser(User user) {

        Call<LoginSignupData> call = userService.getUser(user);
        call.enqueue(new Callback<LoginSignupData>() {
            @Override
            public void onResponse(Call<LoginSignupData> call, Response<LoginSignupData> response) {
                if(response.body().getData() == null )
                {
                    Toast.makeText(LoginActivity.this, "No such user exists!", Toast.LENGTH_SHORT).show();
                    return;
                }

                userId = Integer.parseInt(response.body().getData().getId());
                userEmail = response.body().getData().getEmail();

                SharedPreferences.Editor editor = getSharedPreferences(MY_PREF, MODE_PRIVATE).edit();
                editor.putString("email", userEmail);
                editor.putInt("id", userId);
                editor.apply();

                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                intent.putExtra("id", userId);

                startActivity(intent);
            }

            @Override
            public void onFailure(Call<LoginSignupData> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Login Failed: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void signUpUser(final User user, final String email)
    {
        Call<LoginSignupData> call = userService.addUser(user);
        call.enqueue(new Callback<LoginSignupData>() {
            @Override
            public void onResponse(Call<LoginSignupData> call, Response<LoginSignupData> response) {
                userId = Integer.parseInt(response.body().getData().getId());
                userEmail = response.body().getData().getEmail();

                Toast.makeText(LoginActivity.this, "Thanks for joining us.\nYour unique ID is: " + userId + ".\nPlease fill the details to continue", Toast.LENGTH_LONG).show();

                SharedPreferences.Editor editor = getSharedPreferences(MY_PREF, MODE_PRIVATE).edit();
                editor.putString("email", userEmail);
                editor.putInt("id", userId);
                editor.apply();

                Intent intent = new Intent(LoginActivity.this, PersonalDetailsActivity.class);
                intent.putExtra("id", userId);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<LoginSignupData> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Signup Failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void serverTest() {
        Call<ServerTest> call = userService.getServerStatus();
        call.enqueue(new Callback<ServerTest>() {
            @Override
            public void onResponse(Call<ServerTest> call, Response<ServerTest> response) {

                Toast.makeText(LoginActivity.this, "ServerStatus : " + response.body().getStatus(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ServerTest> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "ServerStatus : Down : " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
