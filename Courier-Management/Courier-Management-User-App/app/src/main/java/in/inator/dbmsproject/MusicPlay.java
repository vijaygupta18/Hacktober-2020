package com.google.firebase.udacity.friendlychat;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.functions.FirebaseFunctions;
import com.google.firebase.functions.HttpsCallableResult;
import com.google.firebase.udacity.friendlychat.R;

import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;


public class MusicPlay extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private Button playButton;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_play);
        playButton = findViewById(R.id.button);
        mediaPlayer = new MediaPlayer();
        final String url = "https://www.cricinshots.com/music/ipl.mp3";
        final MediaPlayer mediaPlayer = new MediaPlayer();
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Player().execute(url);
            }
        });

        Button button1 = findViewById(R.id.button2);
        Button button2 = findViewById(R.id.button3);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //http request function
                //cheerTeam1();

                //http callable function
               cheer("2326-45650","team1").addOnCompleteListener(new OnCompleteListener<String>() {
                   @Override
                   public void onComplete(@NonNull Task<String> task) {
                       if(task.isSuccessful()) {
                           Log.i("Response",task.getResult());
                       }
                       if(!task.isSuccessful()) {
                           Log.i("Error response",task.getException().getMessage());
                       }
                   }
               });
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //http request function
                //cheerTeam2();

                //http callable function
               cheer("2326-45650","team2").addOnCompleteListener(new OnCompleteListener<String>() {
                   @Override
                   public void onComplete(@NonNull Task<String> task) {
                       if(task.isSuccessful()) {
                           Log.i("Response",task.getResult());
                       }
                       if(!task.isSuccessful()) {
                           Log.i("Error response",task.getException().getMessage());
                       }
                   }
               });
            }
        });


    }

    //http callable function
    private Task<String> cheer(String text,String team) {
        // Create the arguments to the callable function.
        FirebaseFunctions mFunctions = FirebaseFunctions.getInstance();
        Map<String, Object> data = new HashMap<>();
        data.put("matchid", text);
        data.put(team, true);

        return mFunctions
                .getHttpsCallable("cheer")
                .call(data)
                .continueWith(new Continuation<HttpsCallableResult, String>() {
                    @Override
                    public String then(@NonNull Task<HttpsCallableResult> task) throws Exception {
                        // This continuation runs on either success or failure, but if the task
                        // has failed then getResult() will throw an Exception which will be
                        // propagated down.
                        String result = (String) task.getResult().getData();
                        return result;
                    }
                });
    }

    private void cheerTeam2() {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, "https://us-central1-cricinshots.cloudfunctions.net/cheer", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>params = new HashMap<>();
                params.put("matchid","2326-45650");
                params.put("team2","true");
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);

    }

    private void cheerTeam1() {

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, "https://us-central1-cricinshots.cloudfunctions.net/cheer", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println("yes");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error.toString());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>params = new HashMap<>();
                params.put("matchid","2326-45650");
                params.put("team1","true");
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    public class Player extends AsyncTask<String,Void,Void> {

        @Override
        protected Void doInBackground(String... strings) {
            mediaPlayer.reset();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                mediaPlayer.setAudioAttributes(
                        new AudioAttributes.Builder()
                                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                                .setUsage(AudioAttributes.USAGE_MEDIA)
                                .build()
                );
            }
            try {
                mediaPlayer.setDataSource(strings[0]);
                mediaPlayer.setLooping(false);
                mediaPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(MusicPlay.this, "Done", Toast.LENGTH_SHORT).show();
            mediaPlayer.start();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        mediaPlayer.stop();
    }
}
