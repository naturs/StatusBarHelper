package me.naturs.library.statusbar;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

/**
 * set translucent status bar on 4.4
 * Created by naturs on 2016/2/21.
 */
@TargetApi(Build.VERSION_CODES.KITKAT)
class StatusBarHelperImpl19 extends StatusBarHelperImplBase {

    protected View mStatusBarView;
    protected Drawable mStatusBarDrawable;

    public StatusBarHelperImpl19(Activity activity) {
        super(activity);
    }

    @Override
    protected void setColor(int color) {
        setDrawable(new ColorDrawable(color));
    }

    @Override
    protected void setDrawable(Drawable drawable) {
        setStatusBarTranslucent(true);
        setupStatusBarView();
        setActivityRootLayoutFitSystemWindowsInternal();
        mStatusBarDrawable = drawable;
        mStatusBarView.setBackground(drawable);
    }

    @Override
    protected void destroy() {
        destroyStatusBarView();
        setStatusBarTranslucent(false);
    }

    /**
     * 设置状态栏透明
     * @param on true为设置，false为取消
     */
    protected void setStatusBarTranslucent(boolean on) {
        Window window = mActivity.getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            if ((params.flags & bits) == 0) {
                params.flags |= bits;
                window.setAttributes(params);
            }
        } else {
            if ((params.flags & bits) != 0) {
                params.flags &= ~bits;
                window.setAttributes(params);
            }
        }
    }

    /**
     * 添加用于着色的View
     */
    protected void setupStatusBarView() {
        if (mStatusBarView == null) {
            mStatusBarView = new View(mActivity);
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, getStatusBarHeight());

            ViewGroup contentLayout = (ViewGroup) mActivity.findViewById(android.R.id.content);
            contentLayout.addView(mStatusBarView, lp);
        }
    }

    /**
     * 移除用于着色的View
     */
    protected void destroyStatusBarView() {
        if (mStatusBarView != null) {
            ViewGroup contentLayout = (ViewGroup) mActivity.findViewById(android.R.id.content);
            contentLayout.removeView(mStatusBarView);
            mStatusBarView = null;
        }
    }

}
