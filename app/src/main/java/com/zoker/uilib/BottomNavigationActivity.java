package com.zoker.uilib;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

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
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            int lastPosition = 0;

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == ViewPager.SCROLL_STATE_SETTLING) {
                    int currenPosition = viewPager.getCurrentItem();
                    if (currenPosition != lastPosition) {
                        boolean isToLeft = lastPosition < currenPosition;   //左滑
                        boolean isToRight = lastPosition > currenPosition;  //右滑

                        if (isToLeft) {
                            ((SimpleFragment) tabLayout.getTabItem(currenPosition).getFragment()).in(0);
                            if (currenPosition - 1 >= 0)
                                ((SimpleFragment) tabLayout.getTabItem(currenPosition - 1).getFragment()).outToLeft(0);
//                            ((SimpleFragment) tabLayout.getTabItem(lastPosition).getFragment()).outToLeft(0);
                        }

                        if (isToRight) {
                            ((SimpleFragment) tabLayout.getTabItem(currenPosition).getFragment()).in(0);
                            if (currenPosition + 1 < tabLayout.getItemSize())
                                ((SimpleFragment) tabLayout.getTabItem(currenPosition + 1).getFragment()).outToRight(0);
                            if (currenPosition - 1 >= 0)
                                ((SimpleFragment) tabLayout.getTabItem(currenPosition - 1).getFragment()).outToLeft(0);
//                            ((SimpleFragment) tabLayout.getTabItem(lastPosition).getFragment()).outToRight(0);
                        }
                    }

                    lastPosition = currenPosition;
                }
            }
        });
        viewPager.setCurrentItem(0);
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
