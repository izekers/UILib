package com.zoker.lib.widght;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by zoker on 2018/1/19.
 */

public class PointIndicator extends IndicatorLayout {
    public PointIndicator(Context context) {
        super(context);
    }

    public PointIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PointIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public PointIndicator(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public View getIndicatorView() {
        PointView pointView = new PointView(getContext()) {
            @Override
            public void setSelected(boolean selected) {
                super.setSelected(selected);
                if (selected) {
                    this.setPointColor(Color.DKGRAY);
                    this.setPercent(1.0f);
                } else{
                    this.setPointColor(Color.GRAY);
                    this.setPercent(0.8f);
                }

            }
        };
        pointView.setPercent(0.8f);
        pointView.setLayoutParams(new ViewGroup.LayoutParams(50, 50));
//        TextView pointView = new TextView(getContext());
//        pointView.setText("123");
        return pointView;
    }
}
