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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation);
        tabLayout=(BottomNavigationBar)findViewById(R.id.tablayout);
        viewPager=(ViewPager) findViewById(R.id.viewpage);
        initFragment();
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);
    }

    public void initFragment(){
        tabLayout.addItem(new BottomNavigationItem().fragment(new SimpleFragment()).icon(R.drawable.nor_icon,R.drawable.select).title("1"));
        tabLayout.addItem(new BottomNavigationItem().fragment(new SimpleFragment()).icon(R.drawable.nor_icon,R.drawable.select).title("2"));
        tabLayout.addItem(new BottomNavigationItem().fragment(new SimpleFragment()).icon(R.drawable.nor_icon,R.drawable.select).title("3"));
        tabLayout.addItem(new BottomNavigationItem().fragment(new SimpleFragment()).icon(R.drawable.nor_icon,R.drawable.select).title("4"));
        tabLayout.addItem(new BottomNavigationItem().fragment(new SimpleFragment()).icon(R.drawable.nor_icon,R.drawable.select).title("5"));
        tabLayout.addItem(new BottomNavigationItem().fragment(new SimpleFragment()).icon(R.drawable.nor_icon,R.drawable.select).title("5"));
        tabLayout.addItem(new BottomNavigationItem().fragment(new SimpleFragment()).icon(R.drawable.nor_icon,R.drawable.select).title("5"));
        tabLayout.addItem(new BottomNavigationItem().fragment(new SimpleFragment()).icon(R.drawable.nor_icon,R.drawable.select).title("5"));
    }


    public class ViewPagerAdapter extends FragmentPagerAdapter {

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return tabLayout.getTabItem(position).getFragment();
        }

        @Override
        public int getCount() {
            return tabLayout.getItemSize();
        }
    }
}
