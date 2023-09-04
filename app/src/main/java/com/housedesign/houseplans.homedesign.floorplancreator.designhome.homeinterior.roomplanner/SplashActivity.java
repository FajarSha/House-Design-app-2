package com.housedesign.houseplans.homedesign.floorplancreator.designhome.homeinterior.roomplanner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {
    ImageView getStart_btn;
    Dialog tutorialDialog;
    int clickcount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initView();
        getStart_btn.setOnClickListener(view -> {
            AdblockingDialogue();
//            startActivity(new Intent(this,MainActivity.class));
        });
    }

    private void initView() {
        getStart_btn = findViewById(R.id.getStart_btn);
    }

    private void AdblockingDialogue() {

        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.btn2);
        animation.setRepeatCount(Animation.INFINITE);
        animation.setRepeatMode(Animation.REVERSE);
        clickcount = 1;

        tutorialDialog = new Dialog(this, android.R.style.Theme_Translucent_NoTitleBar);
        View view1 = getLayoutInflater().inflate(R.layout.tutorial_layout, null);
        tutorialDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        tutorialDialog.setContentView(view1);
        Window window = tutorialDialog.getWindow();

        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.CENTER;
        wlp.flags &= ~WindowManager.LayoutParams.FLAG_BLUR_BEHIND;
        window.setAttributes(wlp);
        tutorialDialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        tutorialDialog.show();
        ImageView tutorialImg;
        TextView mainText, finishtxt;
        CardView nextbtn, dot1, dot2, dot3;
        tutorialImg = view1.findViewById(R.id.mainimg);
        mainText = view1.findViewById(R.id.para_text);
        nextbtn = view1.findViewById(R.id.nextBtn);
        tutorialImg.setBackgroundResource(R.drawable.twod_tut);
        dot1 = view1.findViewById(R.id.card1);
        dot2 = view1.findViewById(R.id.card2);
        dot3 = view1.findViewById(R.id.card3);
        finishtxt = view1.findViewById(R.id.nextBtntxt);
        nextbtn.startAnimation(animation);
        dot1.setCardBackgroundColor(ContextCompat.getColor(this, R.color.dot_sel));
        dot2.setCardBackgroundColor(ContextCompat.getColor(this, R.color.dot_unsel));
        dot3.setCardBackgroundColor(ContextCompat.getColor(this, R.color.dot_unsel));
        nextbtn.setOnClickListener(view -> {
            if (clickcount == 1) {
                tutorialImg.setBackgroundResource(R.drawable.threed_tut);
                mainText.setText(getString(R.string.threedstr));
                dot1.setCardBackgroundColor(ContextCompat.getColor(this, R.color.dot_unsel));
                dot2.setCardBackgroundColor(ContextCompat.getColor(this, R.color.dot_sel));
                dot3.setCardBackgroundColor(ContextCompat.getColor(this, R.color.dot_unsel));
                clickcount++;
            } else if (clickcount == 2) {
                tutorialImg.setBackgroundResource(R.drawable.make_tut);
                mainText.setText(getString(R.string.makestr));
                dot1.setCardBackgroundColor(ContextCompat.getColor(this, R.color.dot_unsel));
                dot2.setCardBackgroundColor(ContextCompat.getColor(this, R.color.dot_unsel));
                dot3.setCardBackgroundColor(ContextCompat.getColor(this, R.color.dot_sel));
                finishtxt.setText("Finish");
                clickcount++;
            } else if (clickcount == 3) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        tutorialDialog.dismiss();
                    }
                },1500L);

                startActivity(new Intent(this, MainActivity.class));
            }
        });
    }
}