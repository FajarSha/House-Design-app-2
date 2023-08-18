package com.example.housedesign;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.ImageView;

import com.ortiz.touchview.TouchImageView;

public class itemViewActivity extends AppCompatActivity {
    String ItemTypeValue;
    ImageView previous_btn;
    int ImageID = 0;
    private static final String TAG = "Touch";
    static final int NONE = 0;
    int mode = NONE;
    int pxWidth;
    int pxHeight;

    TouchImageView touchImageView;

    ConstraintLayout adLayout;
    ImageView backArrowBtn;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_view);
        Initialization();
//        ItemTypeValue = getIntent().getStringExtra("itemvalue");
        String TAG = "ABCD";
//        Log.d(TAG, "onCreate: " + ItemTypeValue);
        ImageID = getIntent().getIntExtra("ImageValue", 0);
        if (ImageID != 0) {
            touchImageView.setImageResource(ImageID);
        }
//        touchImageView.ro
        backArrowBtn.setOnClickListener(view -> {
            finish();
        });
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        pxHeight = displaymetrics.heightPixels;
        pxWidth = displaymetrics.widthPixels;
    }

    private void Initialization() {
        touchImageView = findViewById(R.id.house_img);
        backArrowBtn = findViewById(R.id.previous_btn);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}