package me.naturs.statusbarhelper;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;

import me.naturs.library.statusbar.StatusBarHelper;

/**
 *
 * Created by naturs on 2016/2/21.
 */
public class WhiteStatusBarActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mToolbar.setBackgroundColor(Color.TRANSPARENT);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.layout_white;
    }

    @Override
    protected void onTintStatusBar() {
        if (mStatusBarHelper == null) {
            mStatusBarHelper = new StatusBarHelper(this, StatusBarHelper.LEVEL_19_TRANSLUCENT,
                    StatusBarHelper.LEVEL_21_VIEW);
        }
        mStatusBarHelper.setActivityRootLayoutFitSystemWindows(false);
        mStatusBarHelper.setDrawable(getResources().getDrawable(R.drawable.drawable_status_bar_bg));
    }
    
}
