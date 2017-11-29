package com.zoker.uilib;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;

import com.zoker.uilib.bean.Demo1;
import com.zoker.uilib.bean.Demo2;
import com.zoker.uilib.fragment.DemoListFramg;

import java.util.ArrayList;
import java.util.List;

public class ListFragmentActivity extends AppCompatActivity {
    FrameLayout frameLayout;
    DemoListFramg listFramg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_fragment);
        frameLayout = (FrameLayout) findViewById(R.id.contant);
        listFramg = new DemoListFramg();
        getSupportFragmentManager().beginTransaction().replace(R.id.contant, listFramg).commit();
        List<Object> mdata = new ArrayList<>();
        mdata.add(new Demo1());
        mdata.add(new Demo1());
        mdata.add(new Demo1());
        mdata.add(new Demo2());
        mdata.add(new Demo2());
        listFramg.setData(mdata);
    }
}
