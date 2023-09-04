package com.housedesign.houseplans.homedesign.floorplancreator.designhome.homeinterior.roomplanner;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.ads.nativetemplates.NativeTemplateStyle;
import com.google.android.ads.nativetemplates.TemplateView;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.ads.nativead.NativeAd;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.navigation.NavigationView;
import com.housedesign.houseplans.homedesign.floorplancreator.designhome.homeinterior.roomplanner.Ad_Util.InAppBilling;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    DrawerLayout drawerLayout;
    ImageView openDrawerBtn;
    NavigationView navigationView;
    LinearLayout mainRoundedBg;
    CardView twoDTemplate, threeDtemplate, makeYourtemplate;
    private boolean is_High_called = false, is_Medium_Called = false, ishighDisplay = false, isMediumDisaplay = false, isthirdCalled = false;
    ConstraintLayout adLayout;
    private ProgressDialog progressDialog;
    int adcount = 1;
    InAppBilling inAppBilling;
    AlertDialog alertDialog;
    boolean isInAppPurchased = false;
    TemplateView templateView;
    Dialog adBlockdialog,exitDialog;
    CardView subBtn;
    boolean isDisplayed = false;
    ImageView adBtn;
    private SharedPreferences sharedPreferences;
    private boolean showDialog = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initview();
        setupDrawerContent(navigationView);
        openDrawerBtn.setOnClickListener(view -> {
            drawerLayout.openDrawer(navigationView);
        });
        AdblockingDialogue();
        navigationView.setItemIconTintList(null);
        twoDTemplate.setOnClickListener(this);
        threeDtemplate.setOnClickListener(this);
        makeYourtemplate.setOnClickListener(this);
        adBtn.setOnClickListener(this);


//


    }

    private void initview() {
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navView_drawer);
        openDrawerBtn = findViewById(R.id.sort_btn);
        mainRoundedBg = findViewById(R.id.rounded_layout);
        twoDTemplate = findViewById(R.id.twodcard);
        threeDtemplate = findViewById(R.id.threedcard);
        makeYourtemplate = findViewById(R.id.makecard);
        adLayout = findViewById(R.id.adLayout);
        adBtn = findViewById(R.id.ad_btn);

    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                selectDrawerItem(menuItem);
                return false;
            }
        });
    }

    public boolean selectDrawerItem(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.shareApp:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Go To Play Store For Download this App " + "\n\n" + "https://play.google.com/store/apps/details?id=" + getPackageName());
                sendIntent.setType("text/plain");
                Intent shareIntent = Intent.createChooser(sendIntent, null);
                startActivity(shareIntent);
                break;
            case R.id.rate_us:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName())));
                break;
            case R.id.more_app:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/developer?id=PeriStudio")));
                break;
            case R.id.privacy_policy:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://sites.google.com/view/peri-studio-privacy-policy/privacy-policy?pli=1")));
                break;
            default:
        }

        drawerLayout.closeDrawers();
        return true;
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        if (drawerLayout.isOpen()) {
            drawerLayout.closeDrawers();
        } else {
            exitDialog.show();
        }
        // finishAffinity();
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, itemActivity.class);
        switch (view.getId()) {
            case R.id.twodcard:
                AdCount(1);
                break;
            case R.id.threedcard:
                AdCount(2);
                break;
            case R.id.makecard:
                AdCount(3);
                break;
            case R.id.ad_btn:
                AdblockingDialogue();
                adBlockdialog.show();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        inAppBilling = new InAppBilling(this, this);
        inAppBilling.InappCalling();
//        inAppBilling.savePurchaseValueToPref(true);
//        inAppBilling.savePurchaseValueToPref(false);
        isInAppPurchased = inAppBilling.hasUserBoughtInApp();
        if (isInAppPurchased) {
            adLayout.setVisibility(View.GONE);
            adBtn.setVisibility(View.GONE);
        } else {
            adLayout.setVisibility(View.VISIBLE);
            adBtn.setVisibility(View.VISIBLE);
            loadBannerAd();
            if (!isDisplayed) {
                adBlockdialog.show();
            }
        }
        is_High_called = true;
        is_Medium_Called = false;
        isthirdCalled = false;
        showNativeAd(getString(R.string.app_native_highid), true, false, false, getString(R.string.native1));

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
                        checkprority(forhigh, formedium, forlow, getString(R.string.Banner), 0);
                    } else if (is_Medium_Called && !isthirdCalled) {
                        isthirdCalled = true;
                        checkprority(forhigh, formedium, forlow, getString(R.string.Banner), 0);
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

    private void checkprority(boolean forhigh, boolean formedium, boolean forlow, String strvalue, int val) {
        if (is_High_called && !is_Medium_Called) {
            is_Medium_Called = true;
            if (strvalue.equals(getString(R.string.Banner))) {
                showBannerAd(getString(R.string.app_banner_mediumid), false, true, false, getString(R.string.Banner));
            } else if (strvalue.equals(getString(R.string.interstial))) {
                showInterstialAd(getString(R.string.app_interstitial_mediumid), false, true, false, getString(R.string.interstial), val);
            }
        } else {
            if (strvalue.equals(getString(R.string.Banner))) {
                showBannerAd(getString(R.string.app_banner_lowid), false, false, true, getString(R.string.Banner));
            } else if (strvalue.equals(getString(R.string.interstial))) {
                showInterstialAd(getString(R.string.app_interstitial_lowid), false, false, true, getString(R.string.interstial), val);
            }
        }

    }

    private void showInterstialAd(String unitId, boolean forhigh, boolean formedium, boolean forlow, String Value, int acti_value) {

        AdRequest ad_request = new AdRequest.Builder().build();
        InterstitialAd.load(MainActivity.this, unitId, ad_request, new InterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                super.onAdLoaded(interstitialAd);
                setprority(forhigh, formedium, forlow, Value);
                // interstitialAd=interstitialAd;
                interstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                    @Override
                    public void onAdDismissedFullScreenContent() {
                        super.onAdDismissedFullScreenContent();
                        changeActivity(acti_value);
                    }

                    @Override
                    public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                        super.onAdFailedToShowFullScreenContent(adError);
                        if (progressDialog != null) {
                            if (progressDialog.isShowing()) {
                                progressDialog.dismiss();
                            }
                        }

//                        changeActivity(acti_value);
                    }
                });
                if (interstitialAd != null) {
                    interstitialAd.show(MainActivity.this);
                }
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
                if (!forlow) {
                    checkprority(forhigh, formedium, forlow, Value, acti_value);

                } else if (forlow && !isthirdCalled) {
                    if (progressDialog != null && progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                    Log.d(TAG, "onAdFailedToLoad: Interstitial Ad" + loadAdError);
                    checkprority(forhigh, formedium, forlow, Value, acti_value);
                    changeActivity(acti_value);
                    isthirdCalled = true;
                }
            }
        });

    }

    public void laodinterstialAd(int value) {
        is_Medium_Called = false;
        is_High_called = true;
        isthirdCalled = false;
        showInterstialAd(getString(R.string.app_interstitial_highid), true, false, false, getString(R.string.interstial), value);
    }

    private void changeActivity(int val) {
        Intent intent = new Intent(this, itemActivity.class);
        switch (val) {
            case 1:
                intent.putExtra("case", 1);
                startActivity(intent);
                break;
            case 2:
                intent.putExtra("case", 2);
                startActivity(intent);
                break;
            case 3:
                startActivity(new Intent(this, DragableActivity.class));
                break;
        }

    }

    private void AdCount(int val) {
        if (adcount % 2 == 1) {
            if (!isInAppPurchased) {
                laodinterstialAd(val);
                loadingAdDialog();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }
                    }
                }, 2500L);
            } else {
                changeActivity(val);
            }
        } else {
            changeActivity(val);
        }
        adcount++;
    }

    private void loadingAdDialog() {
        progressDialog = ProgressDialog.show(this, "", "Loading Ads Please wait...", true);
    }

    private void showNativeAd(String unitId, boolean forhigh, boolean formedium, boolean forlow, String Value) {
//        View view1 = getLayoutInflater().inflate(R.layout.native_dialog, null);
//        bottomSheetDialog = new BottomSheetDialog(this, R.style.BottomSheetDialogTheme);
//        bottomSheetDialog.setContentView(view1);
        exitDialog=new Dialog(this, android.R.style.Theme_Translucent_NoTitleBar);
        View view1 = LayoutInflater.from(this).inflate(R.layout.exit_dialouge, null);
        exitDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        exitDialog.setContentView(view1);

        Window window = exitDialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.CENTER;
        wlp.flags &= ~WindowManager.LayoutParams.FLAG_BLUR_BEHIND;
        window.setAttributes(wlp);
        exitDialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);

//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setView(view1);
//        alertDialog = builder.create();
//        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));

        templateView = view1.findViewById(R.id.nativeAds_template);

        isInAppPurchased = inAppBilling.hasUserBoughtInApp();
        if (isInAppPurchased) {
            templateView.setVisibility(View.GONE);
        } else {
            templateView.setVisibility(View.VISIBLE);
        }
        CardView yesBtn, NoBtn;
        yesBtn = view1.findViewById(R.id.yesBtn);
        NoBtn = view1.findViewById(R.id.Nobtn);

        yesBtn.setOnClickListener(view -> {
            if (exitDialog.isShowing()) {
                exitDialog.dismiss();
            }

            finishAffinity();
        });
        NoBtn.setOnClickListener(view -> {
            exitDialog.dismiss();
        });

//        Native_adLayout = view1.findViewById(R.id.native_adLayout);
        if (!isInAppPurchased) {
            AdLoader adLoader = new AdLoader.Builder(this, unitId).forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
                @Override
                public void onNativeAdLoaded(NativeAd nativeAd) {
                    NativeTemplateStyle style = new NativeTemplateStyle.Builder().build();
                    templateView.setStyles(style);
                    templateView.setNativeAd(nativeAd);
                    templateView.setVisibility(View.VISIBLE); // Show the TemplateView since the ad is loaded

                    setprority(forhigh, formedium, forlow, getString(R.string.native1));
                }
            }).withAdListener(new AdListener() {
                @Override
                public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                    super.onAdFailedToLoad(loadAdError);
                    if (!forlow) {
                        checkprority(forhigh, formedium, forlow, Value, 0);
                    } else if (forlow && !isthirdCalled) {

                        Log.d(TAG, "onAdFailedToLoad: Native Ad" + loadAdError);
                        checkprority(forhigh, formedium, forlow, Value, 0);
                        isthirdCalled = true;
//                          templateView.setVisibility(View.GONE);
                    }
                }

                @Override
                public void onAdLoaded() {
                    super.onAdLoaded();
                }
            }).build();
            adLoader.loadAd(new AdRequest.Builder().build());
        }

    }

    private void AdblockingDialogue() {
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.button_scale);
        animation.setRepeatCount(Animation.INFINITE);
        animation.setRepeatMode(Animation.REVERSE);

        adBlockdialog = new Dialog(this, android.R.style.Theme_Translucent_NoTitleBar);
        View view1 = getLayoutInflater().inflate(R.layout.purchase_layout, null);
        adBlockdialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        adBlockdialog.setContentView(view1);
        Window window = adBlockdialog.getWindow();

        WindowManager.LayoutParams wlp = window.getAttributes();
        TextView closebtn = view1.findViewById(R.id.cross_btn);
        subBtn = view1.findViewById(R.id.subscribeNow);
        subBtn.startAnimation(animation);

        closebtn.setOnClickListener(view -> {
            isDisplayed = true;
            if (adBlockdialog != null) {
                if (adBlockdialog.isShowing()) {
                    adBlockdialog.dismiss();
                }
            }
        });
        subBtn.setOnClickListener(view -> {
            inAppBilling.purchase(view);
        });
        wlp.gravity = Gravity.CENTER;
        wlp.flags &= ~WindowManager.LayoutParams.FLAG_BLUR_BEHIND;
        window.setAttributes(wlp);
        adBlockdialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
    }


    @SuppressLint("ResourceType")
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.d(TAG, "onConfigurationChanged: " + newConfig);
        int nightModeFlags = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        Menu menu = navigationView.getMenu();

        if (nightModeFlags == Configuration.UI_MODE_NIGHT_NO) {
            //for light
            mainRoundedBg.setBackgroundResource(R.drawable.top_rounded_drawable);
            makeYourtemplate.setCardBackgroundColor(ContextCompat.getColor(this, R.color.white));
            twoDTemplate.setCardBackgroundColor(ContextCompat.getColor(this, R.color.white));
            threeDtemplate.setCardBackgroundColor(ContextCompat.getColor(this, R.color.white));
            navigationView.setBackgroundColor(ContextCompat.getColor(this, R.color.white)); // Set the new color
            navigationView.setItemTextColor(ContextCompat.getColorStateList(this, R.color.black));


        } else {
            // for night
            mainRoundedBg.setBackgroundResource(R.drawable.darktop_rounded_drawable);
            makeYourtemplate.setCardBackgroundColor(ContextCompat.getColor(this, R.color.black));
            twoDTemplate.setCardBackgroundColor(ContextCompat.getColor(this, R.color.black));
            threeDtemplate.setCardBackgroundColor(ContextCompat.getColor(this, R.color.black));
            navigationView.setBackgroundColor(ContextCompat.getColor(this, R.color.light_black)); // Set the new color
            navigationView.setItemTextColor(ContextCompat.getColorStateList(this, R.color.white));
        }
        isDisplayed = true;

    }

}