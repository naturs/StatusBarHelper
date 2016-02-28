package me.naturs.library.statusbar;

import android.app.Activity;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;

/**
 *
 * Created by naturs on 2016/2/20.
 */
public class StatusBarHelper {

    /**
     * 4.4、5.x都不设置Status Bar颜色
     */
    public static final int LEVEL_NONE = 0;

    /**
     * 4.4设置透明状态栏
     */
    public static final int LEVEL_KK_TRANSLUCENT = 1;

    /**
     * 设置5.x以上设置Status Bar颜色的方式。使用5.x自带的API。
     * 该方式适用于大部分的显示情况。
     */
    public static final int LEVEL_L_WITH_NORMAL = 1;

    /**
     * 设置5.x以上设置Status Bar颜色的方式。使用5.x自带的API，并且设置全屏。
     * 该方式适用于页面内容需要全屏显示的情况，比如一些显示图片的页面。
     */
    public static final int LEVEL_L_WITH_FULL_SCREEN = 2;

    /**
     * 设置5.x以上设置Status Bar颜色的方式。不使用5.x自带API，而是使用Kitkat的方式，
     * 添加一个View，该方式和{@link #LEVEL_L_WITH_FULL_SCREEN}相似，只是除了可以设置
     * color外还可以设置drawable。
     */
    public static final int LEVEL_L_WITH_VIEW = 3;

    private final StatusBarHelperImpl mImpl;

    public StatusBarHelper(Activity activity) {
        this(activity, LEVEL_KK_TRANSLUCENT, LEVEL_L_WITH_NORMAL);
    }

    public StatusBarHelper(Activity activity, int levelInKK, int levelInL) {
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {
            if (levelInKK == LEVEL_KK_TRANSLUCENT) {
                mImpl = new StatusBarHelperImplKK(activity);
            } else {
                mImpl = new StatusBarHelperImplBase(activity);
            }
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (levelInL == LEVEL_L_WITH_NORMAL) {
                mImpl = new StatusBarHelperImplLNormal(activity);
            } else if (levelInL == LEVEL_L_WITH_FULL_SCREEN) {
                mImpl = new StatusBarHelperImplLFullScreen(activity);
            } else if (levelInL == LEVEL_L_WITH_VIEW) {
                mImpl = new StatusBarHelperImplLView(activity);
            } else {
                mImpl = new StatusBarHelperImplBase(activity);
            }
        } else {
            mImpl = new StatusBarHelperImplBase(activity);
        }

    }

    public void setColor(int color) {
        mImpl.setColor(color);
    }

    public void setDrawable(Drawable drawable) {
        mImpl.setDrawable(drawable);
    }

    public void setActivityRootLayoutFitSystemWindows(boolean fitSystemWindows) {
        mImpl.setActivityRootLayoutFitSystemWindows(fitSystemWindows);
    }

    /**
     * 处理全屏模式下adjust resize失效的问题，请在{@link #setColor(int)}或
     * {@link #setDrawable(Drawable)}方法之前调用。
     * @param callback 通过回调可以知道fitSystemWindow insets的值
     */
    public void setConsumeInsets(OnConsumeInsetsCallback callback) {
        mImpl.setConsumeInsets(callback);
    }

    public interface OnConsumeInsetsCallback {
        void onInsetsChanged(Rect insets);
    }
}
