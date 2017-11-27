package com.zoker.lib.animation.interpolater;

import android.view.animation.Interpolator;

/**
 * Created by Administrator on 2017/10/27.
 */
public class AnticipateOverShootInterpolater implements Interpolator {
    float tension = 3;  //(拉力)

    @Override
    public float getInterpolation(float input) {
        return getValue(input);
    }

    public float getValue(float x) {
        if (x < 0.5)
            return (float) 0.5 * a(x * 2, tension);
        else
            return (float) 0.5 * (o(x * 2 - 2, tension) + 2);
    }

    public float a(float t, float s) {
        return t * t * ((s + 1) * t - s);
    }

    public float o(float t, float s) {
        return t * t * ((s + 1) * t + s);
    }
}
