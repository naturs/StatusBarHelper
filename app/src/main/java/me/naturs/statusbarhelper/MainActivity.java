package me.naturs.statusbarhelper;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.View;

import me.naturs.library.statusbar.StatusBarHelper;

public class MainActivity extends BaseActivity {

    DrawerLayout mDrawerLayout;
    ActionBarDrawerToggle mDrawerToggle;

    ScrimInsetsFrameLayout mScrimInsetsFrameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setSwipeBackEnable(false);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        if (Build.VERSION.SDK_INT >= 21)
            getWindow().setStatusBarColor(Color.TRANSPARENT);

        setupScrimInsetsLayout();
        setupDrawer();
        setupListener();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    protected void onTintStatusBar() {
        if (mStatusBarHelper == null) {
            mStatusBarHelper = new StatusBarHelper(this, StatusBarHelper.LEVEL_19_TRANSLUCENT,
                    StatusBarHelper.LEVEL_NONE);
        }
        mStatusBarHelper.setActivityRootLayoutFitSystemWindows(false);
//        mStatusBarHelper.setColor(getResources().getColor(R.color.drawer_status_bar_color));
        mStatusBarHelper.setColor(Color.TRANSPARENT);


    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    void setupScrimInsetsLayout() {
        mScrimInsetsFrameLayout = (ScrimInsetsFrameLayout) findViewById(R.id.layout);
        mScrimInsetsFrameLayout.setOnInsetsCallback(new ScrimInsetsFrameLayout.OnInsetsCallback() {
            @Override
            public void onInsetsChanged(Rect insets) {
                mScrimInsetsFrameLayout.setPadding(0, insets.top, 0, 0);
            }
        });
    }

    void setupDrawer() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        mDrawerToggle = new ActionBarDrawerToggle(
                this,
                mDrawerLayout,
                mToolbar,
                R.string.drawer_open,
                R.string.drawer_close);

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        mDrawerToggle.setDrawerIndicatorEnabled(true);
    }

    void setupListener() {
        findViewById(R.id.status_bar_color).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, StatusBarColorActivity.class));
            }
        });
        findViewById(R.id.white_status_bar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, WhiteStatusBarActivity.class));
            }
        });
        findViewById(R.id.full_screen).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ListWithHeaderActivity.class));
            }
        });
        findViewById(R.id.input).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, InputActivity.class));
            }
        });
    }
}
