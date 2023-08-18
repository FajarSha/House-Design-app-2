package com.housedesign.houseplans.homedesign.floorplancreator.designhome.homeinterior.roomplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class SplashActivity extends AppCompatActivity {
ImageView getStart_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initView();
        getStart_btn.setOnClickListener(view -> {
            startActivity(new Intent(this,MainActivity.class));
        });
    }

    private void initView() {
        getStart_btn=findViewById(R.id.getStart_btn);
    }
}