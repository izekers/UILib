package com.zoker.lib.widght;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import android.widget.Button;

import com.zoker.lib.R;

/**
 * Created by Administrator on 2017/10/23.
 */

public class ColorButton extends android.support.v7.widget.AppCompatButton {
    //背景shape
    GradientDrawable normalShape;
    GradientDrawable selectShape;
    StateListDrawable bg;

    public ColorButton(Context context) {
        super(context);
        initView(null);
    }

    public ColorButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(attrs);
    }

    public ColorButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(attrs);
    }

    private void initView(AttributeSet attrs) {
        if (attrs != null) {
            normalShape = new GradientDrawable();
            selectShape = new GradientDrawable();
            bg = new StateListDrawable();
            TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.ColorButton);
            int radius = (int) typedArray.getDimension(R.styleable.ColorButton_radius, 0);
            int border_width = (int) typedArray.getDimension(R.styleable.ColorButton_border_width, 0);
            int borderColor = typedArray.getColor(R.styleable.ColorButton_border_color, 0);
            int normalColor = typedArray.getColor(R.styleable.ColorButton_normal_color, 0);
            int selectColor = typedArray.getColor(R.styleable.ColorButton_select_color, 0);

            normalShape.setCornerRadius(radius);
            normalShape.setStroke(border_width, borderColor);
            normalShape.setColor(normalColor);

            selectShape.setCornerRadius(radius);
            selectShape.setStroke(border_width, borderColor);
            selectShape.setColor(selectColor);

            bg.addState(new int[]{android.R.attr.state_pressed}, selectShape);
            bg.addState(new int[]{}, normalShape);

            this.setBackgroundDrawable(bg);
            typedArray.recycle();
        }
    }
}
