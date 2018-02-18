package com.example.android.classicmusic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class Splash_screen extends AppCompatActivity {


    Animation blink;

    ImageView appIcon;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        appIcon=(ImageView)findViewById(R.id.logo);
        blink = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blink);
        appIcon.startAnimation(blink);
        blink.setAnimationListener(new Animation.AnimationListener() {


            @Override
            public void onAnimationEnd(Animation animation) {


            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationStart(Animation animation) {


            }
        });
        blink.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });



        Thread t = new Thread() {
            public void run() {
                try {

                    sleep(3000);
                    Intent i = new Intent(Splash_screen.this, Playlist.class);
                    startActivity(i);

                } catch (Exception ex) {
                    Log.e("My Exception", ex.toString());
                }
            }

        };

        t.start();


    }
}
