package me.naturs.statusbarhelper;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import java.util.Random;

/**
 * Created by naturs on 2016/2/21.
 */
public class StatusBarColorActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeColor();
            }
        });
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.layout_status_bar_color;
    }

    void changeColor() {
        Random random = new Random();
        int R = random.nextInt(256);
        int G = random.nextInt(256);
        int B = random.nextInt(256);
        int color = Color.argb(255, R, G, B);
        mToolbar.setBackgroundColor(color);
        mStatusBarHelper.setColor(color);
    }
}
