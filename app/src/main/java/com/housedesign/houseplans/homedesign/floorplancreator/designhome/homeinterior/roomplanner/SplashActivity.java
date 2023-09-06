package com.housedesign.houseplans.homedesign.floorplancreator.designhome.homeinterior.roomplanner;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;
import static androidx.core.content.PackageManagerCompat.LOG_TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AlertDialogLayout;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.app.Application;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.housedesign.houseplans.homedesign.floorplancreator.designhome.homeinterior.roomplanner.Ad_Util.InAppBilling;
import com.housedesign.houseplans.homedesign.floorplancreator.designhome.homeinterior.roomplanner.Ad_Util.MyApplication;

public class SplashActivity extends AppCompatActivity {
    ImageView getStart_btn;
    Dialog tutorialDialog;
    int clickcount;
    AdView adview;
    AdRequest adRequest;
    ConstraintLayout adLayout;
    private boolean connected, isInAppPurchased;
    private InAppBilling inAppBilling;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this,TutorialActivity.class));
            }
        }, 5000L);
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        connected = (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED);
        initView();
        inAppBilling = new InAppBilling(this
                , this);
        isInAppPurchased = inAppBilling.hasUserBoughtInApp();


    }

    @Override
    protected void onResume() {
        super.onResume();
        inAppBilling = new InAppBilling(this
                , this);
        inAppBilling.savePurchaseValueToPref(false);
        isInAppPurchased = inAppBilling.hasUserBoughtInApp();
        if (isInAppPurchased) {
            adLayout.setVisibility(View.GONE);
        } else {
            adLayout.setVisibility(View.VISIBLE);
            SetBannerAd(getString(R.string.collapsable_test_banner));
        }
    }

    private void SetBannerAd(String unitId) {
//        AdRequest adRequest = new AdRequest.Builder().build();
        AdView adView = new AdView(this);
        adView.setAdUnitId(unitId);
        adView.setAdSize(AdSize.BANNER);
//        adView.loadAd(adRequest);
        Bundle extras = new Bundle();
        extras.putString("collapsible", "bottom");
        adRequest = new AdRequest.Builder()
                .addNetworkExtrasBundle(AdMobAdapter.class, extras)
                .build();
        adView.loadAd(adRequest);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        adLayout.addView(adView, params);
//       this.adView.loadAd(adRequest);
        adView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                Log.d(TAG, "onAdLoaded: Banner  Ad on Splash loaded ");
            }

            @Override
            public void onAdFailedToLoad(LoadAdError loadAdError) {
                if (loadAdError != null && loadAdError.getCode() == AdRequest.ERROR_CODE_NO_FILL) {
                    Log.d(TAG, "onAdFailedToLoad: banner Ad on Splash Error for " + loadAdError);
                }
            }
        });
    }

    private void initView() {
        adLayout = findViewById(R.id.adLayout);
    }


    @Override
    public void onBackPressed() {
//        super.onBackPressed();



//        finish();
//        finishAffinity();
    }
}