package com.zoker.lib.widght;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.zoker.lib.R;
import com.zoker.lib.list.recycle.widght.SingleTypeListView;
import com.zoker.lib.util.DateUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by zoker on 2018/1/24.
 */

public class DateView extends SingleTypeListView<Date, DateView.DateViewHolder> {


    public DateView(Context context) {
        super(context);
    }

    public DateView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DateView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected LayoutManager installLayoutManager() {
        return new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
    }

    @Override
    public int getItemCount() {
        return getData() != null ? getData().size() / 7 : 0;
    }

    @Override
    protected void initView() {
        super.initView();
        new PagerSnapHelper().attachToRecyclerView(this);
    }

    @Override
    public DateViewHolder onCreateViewHolder(ViewGroup viewGroup) {
        return new DateViewHolder(viewGroup);
    }

    public void setTime(long timeStamp) {
        setData(DateUtils.getWeekDateWithMonth(timeStamp, true));
    }

    @Override
    public void onBindViewHolder(DateViewHolder dateViewHolder, int i) {
        List<Date> dates = new ArrayList<>();
        for (int index = i * 7; index < i * 7 + 7; index++) {
            dates.add(getData().get(index));
        }
        dateViewHolder.setData(dates);
    }

    public static class DateViewHolder extends RecyclerView.ViewHolder {
        private static final int layout_Res = R.layout.date_layout_view;

        TextView mondayView, tuesdayView, wednesdayView, thursdayView, fridayView, saturdayView, sundayView;

        public DateViewHolder(ViewGroup parent) {
            this(LayoutInflater.from(parent.getContext()).inflate(layout_Res, parent, false));
        }

        public DateViewHolder(View itemView) {
            super(itemView);
            mondayView = itemView.findViewById(R.id.mondayView);
            tuesdayView = itemView.findViewById(R.id.tuesdayView);
            wednesdayView = itemView.findViewById(R.id.wednesdayView);
            thursdayView = itemView.findViewById(R.id.thursdayView);
            fridayView = itemView.findViewById(R.id.fridayView);
            saturdayView = itemView.findViewById(R.id.saturdayView);
            sundayView = itemView.findViewById(R.id.sundayView);
        }

        public void setData(List<Date> dates) {
            if (dates.size() != 7) return;
            sundayView.setText(String.valueOf(Integer.valueOf(DateUtils.DAYFORMAT.format(dates.get(0)))));
            mondayView.setText(String.valueOf(Integer.valueOf(DateUtils.DAYFORMAT.format(dates.get(1)))));
            tuesdayView.setText(String.valueOf(Integer.valueOf(DateUtils.DAYFORMAT.format(dates.get(2)))));
            wednesdayView.setText(String.valueOf(Integer.valueOf(DateUtils.DAYFORMAT.format(dates.get(3)))));
            thursdayView.setText(String.valueOf(Integer.valueOf(DateUtils.DAYFORMAT.format(dates.get(4)))));
            fridayView.setText(String.valueOf(Integer.valueOf(DateUtils.DAYFORMAT.format(dates.get(5)))));
            saturdayView.setText(String.valueOf(Integer.valueOf(DateUtils.DAYFORMAT.format(dates.get(6)))));
        }
    }
}
