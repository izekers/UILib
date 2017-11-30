package com.zoker.uilib;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.zoker.lib.list.fragment.SimpleSingleListFragment;
import com.zoker.uilib.bean.Demo3;
import com.zoker.uilib.viewholder.Demo3ViewHolder;

import java.util.ArrayList;
import java.util.List;

public class SimpleListActivity extends AppCompatActivity {
    FrameLayout frameLayout;
    SimpleListFragment listFramg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_fragment);
        frameLayout = (FrameLayout) findViewById(R.id.contant);
        listFramg = new SimpleListFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.contant, listFramg).commit();
        frameLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                List<Demo3> mdata = new ArrayList<>();
                mdata.add(new Demo3());
                mdata.add(new Demo3());
                mdata.add(new Demo3());
                mdata.add(new Demo3());
                mdata.add(new Demo3());
                listFramg.addData(mdata);
            }
        }, 1000);
    }

    public static class SimpleListFragment extends SimpleSingleListFragment<Demo3, Demo3ViewHolder> {
        @Override
        public Demo3ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new Demo3ViewHolder(parent);
        }
    }
}
