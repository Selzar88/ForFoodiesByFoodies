package com.example.forfoodiesbyfoodies;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity{

    private static int SPLASH_SCREEN_TIMEOUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.splash_activity);

        Animation fadeOut = new AlphaAnimation(1,0);
        fadeOut.setInterpolator(new AccelerateInterpolator());
        fadeOut.setStartOffset(500);
        fadeOut.setDuration(2500);
        ImageView image = findViewById(R.id.imageView3);
        TextView text = findViewById(R.id.textView);
        TextView text2 = findViewById(R.id.textView2);

        image.setAnimation(fadeOut);
        text.setAnimation(fadeOut);
        text2.setAnimation(fadeOut);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_SCREEN_TIMEOUT);

    }
}
