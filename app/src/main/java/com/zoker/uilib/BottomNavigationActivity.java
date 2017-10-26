package com.zoker.uilib;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.zoker.lib.widght.SimpleFragmentPagerAdapter;
import com.zoker.lib.widght.bottomNavigation.BottomNavigationBar;
import com.zoker.lib.widght.bottomNavigation.BottomNavigationItem;
import com.zoker.uilib.fragment.SimpleFragment;

/**
 * Created by Administrator on 2017/10/19.
 */

public class BottomNavigationActivity extends AppCompatActivity {
    BottomNavigationBar tabLayout;
    ViewPager viewPager;
    private FragmentManager fm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation);
        tabLayout = (BottomNavigationBar) findViewById(R.id.tablayout);
        tabLayout.setTabLayout(R.layout.bottom_navigation_tab2);
        viewPager = (ViewPager) findViewById(R.id.viewpage);
        initFragment();
    }

    public void initFragment() {
        BottomNavigationBar.Worker worker = tabLayout.setup(viewPager, getSupportFragmentManager());
        worker.addItem(new BottomNavigationItem(getSupportFragmentManager()).fragment(new SimpleFragment()).icon(R.drawable.nor_icon, R.drawable.select).title("首页"));
        worker.addItem(new BottomNavigationItem(getSupportFragmentManager()).fragment(new SimpleFragment()).icon(R.drawable.nor_icon, R.drawable.select).title("第二页"));
        worker.addItem(new BottomNavigationItem(getSupportFragmentManager()).fragment(new SimpleFragment()).icon(R.drawable.nor_icon, R.drawable.select).title("第三页"));
        worker.addItem(new BottomNavigationItem(getSupportFragmentManager()).fragment(new SimpleFragment()).icon(R.drawable.nor_icon, R.drawable.select).title("第四页"));
        worker.addItem(new BottomNavigationItem(getSupportFragmentManager()).fragment(new SimpleFragment()).icon(R.drawable.nor_icon, R.drawable.select).title("第五页"));
        worker.work();
    }
}
