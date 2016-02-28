package me.naturs.statusbarhelper;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import me.naturs.library.statusbar.StatusBarHelper;

/**
 *
 * Created by naturs on 2016/2/21.
 */
public class ListWithHeaderActivity extends BaseActivity {

    ListView mListView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mListView = (ListView) findViewById(android.R.id.list);

        int screenWidth = getResources().getDisplayMetrics().widthPixels;

        View header = LayoutInflater.from(this).inflate(R.layout.layout_list_header, null);
        ImageView headerIv = (ImageView) header.findViewById(R.id.image);
        ViewGroup.LayoutParams lp = headerIv.getLayoutParams();
        lp.width = ViewGroup.LayoutParams.MATCH_PARENT;
        lp.height = (int) (screenWidth * 9F / 16F);
        mListView.addHeaderView(header);

        List<String> data = new ArrayList<>();
        for (int i = 0; i < 100; i ++) {
            data.add(String.valueOf(i));
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
        mListView.setAdapter(adapter);

        mToolbar.setBackgroundColor(Color.TRANSPARENT);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

    }

    @Override
    protected int getLayoutResource() {
        return R.layout.layout_full_screen;
    }

    @Override
    protected void onTintStatusBar() {
        if (mStatusBarHelper == null) {
            mStatusBarHelper = new StatusBarHelper(this, StatusBarHelper.LEVEL_19_TRANSLUCENT,
                    StatusBarHelper.LEVEL_21_NORMAL_FULL);
        }
        mStatusBarHelper.setActivityRootLayoutFitSystemWindows(false);
        mStatusBarHelper.setColor(Color.TRANSPARENT);
    }

}
