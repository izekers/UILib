package com.zoker.lib.widght;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.zoker.lib.R;

/**
 * Created by Administrator on 2017/10/11.
 */

public class PopMenu extends PopupWindow {
    private View rootView;
    private Context context;
    private RecyclerView recyclerView;

    public PopMenu(Context context) {
        super(LayoutInflater.from(context).inflate(R.layout.popmenu, null, false), -2, -2);
        this.context = context;
        setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setFocusable(true);
        setOutsideTouchable(true);
        rootView = getContentView();
        rootView.setBackgroundResource(R.drawable.popmenu_border);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.popmenu_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
    }

    public View getRootView() {
        return rootView;
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        recyclerView.setAdapter(adapter);
    }


    public void addItem(Item item, final View.OnClickListener listener) {
//        View lineView = new View(context);
//        lineView.setBackgroundColor(Color.parseColor("#999999"));
//        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1);
//        layoutParams.leftMargin = 20;
//        layoutParams.rightMargin = 20;
//        lineView.setLayoutParams(layoutParams);
//        rootView.addView(lineView);
//        View itemView = LayoutInflater.from(context).inflate(R.layout.popmenu_item, rootView, false);
//        TextView itemTxt = (TextView) itemView.findViewById(R.id.menu_txt);
//        itemTxt.setText(item.name);
//        itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (listener != null) {
//                    listener.onClick(v);
//                }
//                dismiss();
//            }
//        });
//        rootView.addView(itemView);
    }

    @Override
    public void showAsDropDown(View anchor) {
        if (!isShowing())
            super.showAsDropDown(anchor);
    }

    public static class Item {
        public String name;

        public Item(String name) {
            this.name = name;
        }
    }
}
