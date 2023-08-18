package com.example.housedesign;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    DrawerLayout drawerLayout;
    ImageView openDrawerBtn;
    NavigationView navigationView;
    LinearLayout mainRoundedBg;
    CardView twoDTemplate, threeDtemplate, makeYourtemplate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initview();
        setupDrawerContent(navigationView);
        openDrawerBtn.setOnClickListener(view -> {
            drawerLayout.openDrawer(navigationView);
        });
        navigationView.setItemIconTintList(null);
        twoDTemplate.setOnClickListener(this);
        threeDtemplate.setOnClickListener(this);
        makeYourtemplate.setOnClickListener(this);

    }

    private void initview() {
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navView_drawer);
        openDrawerBtn = findViewById(R.id.sort_btn);
        mainRoundedBg = findViewById(R.id.rounded_layout);
        twoDTemplate = findViewById(R.id.twodcard);
        threeDtemplate = findViewById(R.id.threedcard);
        makeYourtemplate = findViewById(R.id.makecard);

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
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Go To Play Store For Download this App " + "\n\n" + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID);
                sendIntent.setType("text/plain");
                Intent shareIntent = Intent.createChooser(sendIntent, null);
                startActivity(shareIntent);
//                nav.getMenu().findItem(R.id.shareApp).setChecked(false);
                // fragmentClass = FirstFragment.class;
//                nav.getMenu().findItem(R.id.shareApp).setChecked(false);
                break;
            case R.id.rate_us:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName())));
//                nav.getMenu().findItem(R.id.rate_us).setChecked(false);
                break;
//            case R.id.more_app:
//                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/developer?id=PeriStudio")));
//                nav.getMenu().findItem(R.id.more_app).setChecked(false);
//                break;
            case R.id.privacy_policy:
            case R.id.terms:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://sites.google.com/view/peri-studio-privacy-policy/privacy-policy?pli=1")));
//                nav.getMenu().findItem(R.id.privacy_policy).setChecked(false);
//                nav.getMenu().findItem(R.id.terms).setChecked(false);

                break;
            default:
        }

        drawerLayout.closeDrawers();
        return false;
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        finishAffinity();
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, itemActivity.class);
        switch (view.getId()) {
            case R.id.twodcard:
                intent.putExtra("case", 1);
                startActivity(intent);
                break;
            case R.id.threedcard:
                intent.putExtra("case", 2);
                startActivity(intent);

                break;
            case R.id.makecard:
                startActivity(new Intent(this,DragableActivity.class));
                // Add more cases for other views
                break;
            default:
                break;
        }
    }
//    @Override
//    public void onConfigurationChanged(Configuration newConfig) {
//        super.onConfigurationChanged(newConfig);
//        Log.d(TAG, "onConfigurationChanged: "+newConfig);
//        mainRoundedBg.setBackgroundResource(R.drawable.darktop_rounded_drawable);
//        // Dismiss the ad-blocking dialog if it's showing
//
//    }
}