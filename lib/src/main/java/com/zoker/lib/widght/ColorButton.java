package com.zoker.lib.widght;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.support.annotation.ColorInt;
import android.util.AttributeSet;
import android.widget.Button;

import com.zoker.lib.R;

/**
 *
 * Created by Administrator on 2017/10/23.
 */

public class ColorButton extends android.support.v7.widget.AppCompatButton {
    //背景shape
    GradientDrawable normalShape;
    GradientDrawable pressShape;
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
            bg = new StateListDrawable();
            TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.ColorButton);
            int radius = (int) typedArray.getDimension(R.styleable.ColorButton_radius, 0);
            int border_width = (int) typedArray.getDimension(R.styleable.ColorButton_border_width, 0);
            int borderColor = typedArray.getColor(R.styleable.ColorButton_border_color, 0);
            int normalColor = typedArray.getColor(R.styleable.ColorButton_normal_color, 0);
            int pressColor = typedArray.getColor(R.styleable.ColorButton_press_color, 0);

            getNormalShape().setCornerRadius(radius);
            getNormalShape().setStroke(border_width, borderColor);
            getNormalShape().setColor(normalColor);

            getPressShape().setCornerRadius(radius);
            getPressShape().setStroke(border_width, borderColor);
            getPressShape().setColor(pressColor);

            bg.addState(new int[]{android.R.attr.state_pressed}, getPressShape());
            bg.addState(new int[]{}, getNormalShape());

            this.setBackgroundDrawable(bg);
            typedArray.recycle();
        }
    }

    public void setColor(@ColorInt int normalColor,@ColorInt int pressColor){
        getNormalShape().setColor(normalColor);
        getPressShape().setColor(pressColor);
        bg.addState(new int[]{android.R.attr.state_pressed}, getPressShape());
        bg.addState(new int[]{}, getNormalShape());
        this.setBackgroundDrawable(bg);
    }

    private GradientDrawable getNormalShape() {
        if (normalShape==null)
            normalShape=new GradientDrawable();
        return normalShape;
    }
    private GradientDrawable getPressShape() {
        if (pressShape==null)
            pressShape=new GradientDrawable();
        return pressShape;
    }
}
