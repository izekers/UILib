package com.zoker.lib.animation.interpolater;

import android.view.animation.Interpolator;

/**
 * 弹性差值器
 * Created by Administrator on 2017/10/27.
 */
public class SpringInterpolator implements Interpolator {
    float factor = (float) 0.4;

    @Override
    public float getInterpolation(float input) {
        return (float) (Math.pow(2, -10 * input) * Math.sin((input - factor / 4) * (2 * Math.PI) / factor) + 1);
    }
}
