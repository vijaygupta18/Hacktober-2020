package com.example.level0;

import androidx.appcompat.app.AppCompatActivity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
ImageView ironman,cap,thor;
TextView scText;
MediaPlayer mPlayer = new MediaPlayer();
ImageButton pause,next,prev;
boolean isPlaying = false;
View line;
int c1 = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ironman = findViewById(R.id.ironman);
        cap = findViewById(R.id.captain);
        thor = findViewById(R.id.thor);
        pause = findViewById(R.id.pause);
        scText = findViewById(R.id.sc_text);
        line = findViewById(R.id.line);
        next = findViewById(R.id.next);
        prev = findViewById(R.id.prev);

              ironman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               im_function();
            }
        });
        cap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cap_function();
            }
        });
        thor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               thor_function();
            }
        });
    }
public void playing(){
        pause.setVisibility(View.VISIBLE);
        scText.setVisibility(View.VISIBLE);
        next.setVisibility(View.VISIBLE);
        prev.setVisibility(View.VISIBLE);
        pause.setImageResource(R.drawable.pause);
        line.setVisibility(View.VISIBLE);
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPlaying){
                    mPlayer.pause();
                    pause.setImageResource(R.drawable.play);
                    isPlaying = false;

                }else if(!isPlaying){
                    mPlayer.start();
                    pause.setImageResource(R.drawable.pause);
                    isPlaying = true;

                }

            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (c1){
                    case 1:
                        cap_function();
                        break;
                    case 2:
                        thor_function();
                        break;
                    case 3:
                        Toast.makeText(MainActivity.this,"You are on the last song, so you will now be redirected to song 1", Toast.LENGTH_LONG).show();
                        im_function();
                        break;
                    default:
                        break;
                }
            }
        });
    prev.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (c1){
                case 1:
                    Toast.makeText(MainActivity.this,"You are on the first song, so you will now be redirected to song 3", Toast.LENGTH_LONG).show();
                    thor_function();
                    break;
                case 2:
                    im_function();
                    break;
                case 3:
                    cap_function();
                    break;
                default:
                    break;
            }
        }
    });
}
public void im_function(){
    mPlayer.stop();
    mPlayer = MediaPlayer.create(MainActivity.this, R.raw.ironmantheme);
    mPlayer.start();
    isPlaying = true;
    scText.setText("Iron Man Theme Song");
    c1 = 1;
    playing();
}

public void cap_function(){
    mPlayer.stop();
    mPlayer = MediaPlayer.create(MainActivity.this, R.raw.captainamericatheme);
    mPlayer.start();
    isPlaying = true;
    scText.setText("Captain America Theme Song");
    c1 = 2;
    playing();
}
public void thor_function(){
    mPlayer.stop();
    mPlayer = MediaPlayer.create(MainActivity.this, R.raw.thorragnarok);
    mPlayer.start();
    isPlaying = true;
    scText.setText("Thor Theme Song");
    c1 = 3;
    playing();
}
}
