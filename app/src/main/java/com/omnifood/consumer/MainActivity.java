package com.omnifood.consumer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.facebook.shimmer.ShimmerFrameLayout;


public class MainActivity extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGTH = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ShimmerFrameLayout shimmerLayout = (ShimmerFrameLayout) findViewById(R.id.shimmer_layout);

        shimmerLayout.startShimmer();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                MainActivity.this.startActivity(intent);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                MainActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
