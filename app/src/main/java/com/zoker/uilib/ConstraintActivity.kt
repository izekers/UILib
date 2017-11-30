package com.zoker.uilib

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.zoker.lib.list.fragment.SingleListFragment
import com.zoker.uilib.bean.ModelBean
import com.zoker.uilib.viewholder.MainViewHolder

import kotlinx.android.synthetic.main.activity_constraint.*
import java.util.*

class ConstraintActivity : AppCompatActivity() {
    var listfragment : ConstraintFragment = ConstraintFragment();
    private var datas: Array<ModelBean>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_constraint)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        datas = arrayOf(
                ModelBean("多样式的ImageView", this, MaterialImageViewActivity::class.java),
                ModelBean("拍照", this, CameraActivity::class.java),
                ModelBean("底部导航栏", this, BottomNavigationActivity::class.java),
                ModelBean("webview", this, WebViewActivity::class.java),
                ModelBean("多颜色按钮", this, ColorButtonActivity::class.java),
                ModelBean("图片放大控件", this, LargeImageActivity::class.java),
                ModelBean("gif图片控件", this, GifImageViewActivity::class.java),
                ModelBean("动画", this, TranslationActivity::class.java),
                ModelBean("ListFragment", this, ListFragmentActivity::class.java),
                ModelBean("SimpleListFragment", this, SimpleListActivity::class.java))

        getSupportFragmentManager().beginTransaction().add(R.id.constraint_root_content,listfragment).commit();

        listfragment.addData(Arrays.asList(*datas!!));
    }

    class ConstraintFragment : SingleListFragment<ModelBean, MainViewHolder>(){
        override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
            holder.nameView.setText(getItemData(position).title);
            holder.nameView.setOnClickListener { startActivity(getItemData(holder.getAdapterPosition()).getIntent()) }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
            return MainViewHolder(parent)
        }

        override fun installLayoutManager(): RecyclerView.LayoutManager {
            return LinearLayoutManager(context);
        }

    }
}
