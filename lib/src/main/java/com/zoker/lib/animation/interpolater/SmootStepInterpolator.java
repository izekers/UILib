package com.zoker.lib.animation.interpolater;

import android.view.animation.Interpolator;

/**
 * Created by Administrator on 2017/10/27.
 */
public class SmootStepInterpolator implements Interpolator {
    @Override
    public float getInterpolation(float input) {
        return (input * input * (3 - 2 * input));
    }
}
