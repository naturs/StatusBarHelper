package me.naturs.library.statusbar;

import android.app.Activity;
import android.graphics.drawable.Drawable;

/**
 *
 * Created by naturs on 2016/2/21.
 */
class StatusBarHelperImplBase extends StatusBarHelperImpl {

    public StatusBarHelperImplBase(Activity activity) {
        super(activity);
    }

    @Override
    protected void setColor(int color) {
        // do noting
    }

    @Override
    protected void setDrawable(Drawable drawable) {
        // do noting
    }

    @Override
    protected void destroy() {
        // do noting
    }

}
