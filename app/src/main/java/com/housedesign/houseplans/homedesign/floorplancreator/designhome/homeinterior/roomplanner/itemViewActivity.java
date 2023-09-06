package com.housedesign.houseplans.homedesign.floorplancreator.designhome.homeinterior.roomplanner;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.housedesign.houseplans.homedesign.floorplancreator.designhome.homeinterior.roomplanner.Ad_Util.InAppBilling;
import com.ortiz.touchview.TouchImageView;

public class itemViewActivity extends AppCompatActivity {
    int ImageID = 0;
    private static final String TAG = "Touch";
    static final int NONE = 0;
    int mode = NONE;
    int pxWidth;
    int pxHeight;
    TouchImageView touchImageView;
    ConstraintLayout adLayout;
    ImageView backArrowBtn;
    InAppBilling inAppBilling;
    boolean isInAppPurchased;
    boolean is_High_called = false, is_Medium_Called = false, ishighDisplay = false, isMediumDisaplay = false, isthirdCalled = false;


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
        adLayout=findViewById(R.id.adLayout);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onResume() {
        super.onResume();
        inAppBilling = new InAppBilling(this, this);
        inAppBilling.InappCalling();
//        inAppBilling.savePurchaseValueToPref(false);
        isInAppPurchased = inAppBilling.hasUserBoughtInApp();
        if(isInAppPurchased){
            adLayout.setVisibility(View.GONE);
        }else {
            adLayout.setVisibility(View.VISIBLE);
            loadBannerAd();
        }
    }

    public void loadBannerAd() {
        ishighDisplay = true;
        is_Medium_Called = false;
        isthirdCalled = false;
        showBannerAd(getString(R.string.app_banner_highid), true, false, false, getString(R.string.Banner));
    }

    private void showBannerAd(String unitId, boolean forhigh, boolean formedium, boolean forlow, String Value) {
        AdRequest adRequest = new AdRequest.Builder().build();
        AdView adView = new AdView(this);
        adView.setAdUnitId(unitId);
        adView.setAdSize(AdSize.BANNER);
        adView.loadAd(adRequest);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        adLayout.addView(adView, params);
//       this.adView.loadAd(adRequest);
        adView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                setprority(forhigh, formedium, forlow, getString(R.string.Banner));
            }

            @Override
            public void onAdFailedToLoad(LoadAdError loadAdError) {
                if (loadAdError != null && loadAdError.getCode() == AdRequest.ERROR_CODE_NO_FILL) {
                    Log.d(TAG, "onAdFailedToLoad: banner Ad Error for " + loadAdError);
                    if (formedium) {
                        checkprority(forhigh, formedium, forlow, getString(R.string.Banner));
                    } else if (is_Medium_Called && !isthirdCalled) {
                        isthirdCalled = true;
                        checkprority(forhigh, formedium, forlow, getString(R.string.Banner));
                    }

                }
            }
        });

    }

    private void setprority(boolean forhigh, boolean formedium, boolean forlow, String strvalue) {
        if (forhigh) {
            ishighDisplay = true;
        } else if (formedium) {
            isMediumDisaplay = true;
        } else {
            is_High_called = false;
            ishighDisplay = false;
            is_Medium_Called = false;
            isMediumDisaplay = false;
        }
    }

    private void checkprority(boolean forhigh, boolean formedium, boolean forlow, String strvalue) {
        if (is_High_called && !is_Medium_Called) {
            is_Medium_Called = true;
            if (strvalue.equals(getString(R.string.Banner))) {
                showBannerAd(getString(R.string.app_banner_mediumid), false, true, false, getString(R.string.Banner));
            }
        } else {
            if (strvalue.equals(getString(R.string.Banner))) {
                showBannerAd(getString(R.string.app_banner_lowid), false, false, true, getString(R.string.Banner));
            }
        }

    }
}