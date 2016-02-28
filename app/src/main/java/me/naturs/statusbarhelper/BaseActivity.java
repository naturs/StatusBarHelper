package me.naturs.statusbarhelper;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import me.imid.swipebacklayout.lib.app.SwipeBackActivity;
import me.naturs.library.statusbar.StatusBarHelper;

/**
 * Created by naturs on 2016/2/21.
 */
public abstract class BaseActivity extends SwipeBackActivity {

    protected Toolbar mToolbar;

    protected StatusBarHelper mStatusBarHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getLayoutResource());

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        onTintStatusBar();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    protected void onTintStatusBar() {
        if (mStatusBarHelper == null) {
            mStatusBarHelper = new StatusBarHelper(this, StatusBarHelper.LEVEL_19_TRANSLUCENT,
                    StatusBarHelper.LEVEL_21_VIEW);
        }
        mStatusBarHelper.setColor(getResources().getColor(R.color.colorPrimaryDark));
    }

    protected abstract int getLayoutResource();

}
