package com.housedesign.houseplans.homedesign.floorplancreator.designhome.homeinterior.roomplanner;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.housedesign.houseplans.homedesign.floorplancreator.designhome.homeinterior.roomplanner.Ad_Util.InAppBilling;
import com.housedesign.houseplans.homedesign.floorplancreator.designhome.homeinterior.roomplanner.Adapter.HouseRecylceAdapter;
import com.housedesign.houseplans.homedesign.floorplancreator.designhome.homeinterior.roomplanner.Modal.HouseModal;
import com.housedesign.houseplans.homedesign.floorplancreator.designhome.homeinterior.roomplanner.Modal.ResourceData;

import java.util.ArrayList;

public class itemActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    HouseRecylceAdapter adapter;
    ArrayList<HouseModal> arrayList = new ArrayList<>();
    int casevalue = 0;
    TextView mainTitle;
    ResourceData resourceData;
    ImageView backArrowBtn;
    ConstraintLayout adLayout;
    InAppBilling inAppBilling;
    boolean isInAppPurchased=false;
    private boolean is_High_called = false, is_Medium_Called = false, ishighDisplay = false, isMediumDisaplay = false, isthirdCalled = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        initView();

        resourceData = new ResourceData(this);
        casevalue = getIntent().getIntExtra("case", 0);
        setmainText(casevalue);
        arrayList = resourceData.getResource(casevalue);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        adapter = new HouseRecylceAdapter(this, arrayList);
        recyclerView.setAdapter(adapter);
        backArrowBtn.setOnClickListener(view -> {
            finish();
        });
    }

    private void initView() {
        recyclerView = findViewById(R.id.recylceview);
        mainTitle = findViewById(R.id.title);
        backArrowBtn = findViewById(R.id.sort_btn);
        adLayout = findViewById(R.id.adLayout);
    }

    public ArrayList<HouseModal> setmainText(int value) {
        if (value == 1) {
            mainTitle.setText(getString(R.string.twod));
        } else if (value == 2) {
            mainTitle.setText(getString(R.string.threed));
        } else if (value == 3) {
            mainTitle.setText(getString(R.string.front));
        } else if (value == 4) {
            mainTitle.setText(getString(R.string.bathroom));

        } else if (value == 5) {
            mainTitle.setText(getString(R.string.kitchen));
        } else if (value == 6) {
            mainTitle.setText(getString(R.string.bathroom));
        } else if (value == 7) {
            mainTitle.setText(getString(R.string.lounge));
        }
        return arrayList;

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