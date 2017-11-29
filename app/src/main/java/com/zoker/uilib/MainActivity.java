package com.zoker.uilib;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.zoker.lib.recycle.ItemDecoration.LineItemDecoration;
import com.zoker.uilib.bean.ModelBean;
import com.zoker.uilib.viewholder.MainViewHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ModelBean[] datas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        datas = new ModelBean[]{
                new ModelBean("多样式的ImageView", this, MaterialImageViewActivity.class),
                new ModelBean("拍照", this, CameraActivity.class),
                new ModelBean("底部导航栏", this, BottomNavigationActivity.class),
                new ModelBean("webview", this, WebViewActivity.class),
                new ModelBean("多颜色按钮", this, ColorButtonActivity.class),
                new ModelBean("图片放大控件", this, LargeImageActivity.class),
                new ModelBean("gif图片控件", this, GifImageViewActivity.class),
                new ModelBean("动画",this,TranslationActivity.class),
                new ModelBean("ListFragment",this,ListFragmentActivity.class),
                new ModelBean("SimpleListFragment",this,SimpleListActivity.class),
                new ModelBean("测试使用ConstraintLayout布局",this,ConstraintActivity.class),
        };
        List<ModelBean> modelBeanList = Arrays.asList(datas);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.main_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerView.addItemDecoration(new LineItemDecoration(10));
        recyclerView.setAdapter(new Adapter());
    }

    private class Adapter extends RecyclerView.Adapter<MainViewHolder> {

        @Override
        public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MainViewHolder(parent);
        }

        @Override
        public void onBindViewHolder(final MainViewHolder holder, int position) {
            holder.nameView.setText(getItem(position).getTitle());
            holder.nameView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(getItem(holder.getAdapterPosition()).getIntent());
                }
            });
        }

        private ModelBean getItem(int position) {
            return datas[position];
        }

        @Override
        public int getItemCount() {
            return datas.length;
        }
    }
}
