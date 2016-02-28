package me.naturs.statusbarhelper;

import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;

import me.naturs.library.statusbar.StatusBarHelper;

/**
 * Created by naturs on 2016/2/21.
 */
public class InputActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mToolbar.setBackgroundColor(Color.TRANSPARENT);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.layout_input;
    }

    @Override
    protected void onTintStatusBar() {
        if (mStatusBarHelper == null) {
            mStatusBarHelper = new StatusBarHelper(this, StatusBarHelper.LEVEL_KK_TRANSLUCENT,
                    StatusBarHelper.LEVEL_L_WITH_FULL_SCREEN);
        }
        mStatusBarHelper.setActivityRootLayoutFitSystemWindows(true);

        mStatusBarHelper.setConsumeInsets(new StatusBarHelper.OnConsumeInsetsCallback() {
            @Override
            public void onInsetsChanged(Rect insets) {
                findViewById(R.id.toolbar_layout).setPadding(0, insets.top, 0, 0);
            }
        });

        mStatusBarHelper.setColor(Color.TRANSPARENT);
    }
}
