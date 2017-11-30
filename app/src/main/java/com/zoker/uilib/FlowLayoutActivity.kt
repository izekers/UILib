package com.zoker.uilib

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView

import kotlinx.android.synthetic.main.activity_flow_layout.*
import kotlinx.android.synthetic.main.content_flow_layout.*

class FlowLayoutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flow_layout)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        val lp :LinearLayout.LayoutParams  =LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.rightMargin = 20;
        val text1 : TextView = TextView(this);
        text1.layoutParams = lp
        val text2 : TextView = TextView(this);
        text2.layoutParams = lp
        val text3 : TextView = TextView(this);
        text3.layoutParams = lp
        val text4 : TextView = TextView(this);
        text4.layoutParams = lp
        val text5 : TextView = TextView(this);
        text5.layoutParams = lp
        val text6 : TextView = TextView(this);
        text6.layoutParams = lp

        text1.setText("第一个item")
        text2.setText("第二个item")
        text3.setText("第仨个item")
        text4.setText("第四个item")
        text5.setText("第五个item")
        text6.setText("第六个item")

        flow_layout.addView(text1)
        flow_layout.addView(text2)
        flow_layout.addView(text3)
        flow_layout.addView(text4)
        flow_layout.addView(text5)
        flow_layout.addView(text6)
    }

}
