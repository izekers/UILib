package com.zoker.lib.animation.translation;

import android.animation.Animator;
import android.animation.PointFEvaluator;
import android.animation.ValueAnimator;
import android.graphics.Point;
import android.graphics.PointF;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.transition.Transition;
import android.support.transition.TransitionValues;
import android.view.View;
import android.view.ViewGroup;

import com.zoker.lib.animation.interpolater.AnticipateOverShootInterpolater;
import com.zoker.lib.animation.value.PointEvalutor;

/**
 * Created by Administrator on 2017/10/27.
 */

public class ChangePointF extends Transition {
    private static final String locationX =
            "zoker:changelocation:x";
    private static final String locationY =
            "zoker:changelocation:y";


    private int durationTime = 1000;

    public ChangePointF() {

    }

    public ChangePointF(int durationTime) {
        this.durationTime = durationTime;
    }

    @Override
    public void captureEndValues(@NonNull TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    @Override
    public void captureStartValues(@NonNull TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    private void captureValues(TransitionValues transitionValues) {
        View view = transitionValues.view;
        transitionValues.values.put(locationX, view.getX());
        transitionValues.values.put(locationY, view.getY());
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Nullable
    @Override
    public Animator createAnimator(@NonNull ViewGroup sceneRoot, @Nullable TransitionValues startValues, @Nullable TransitionValues endValues) {
        if (null == startValues || null == endValues) {
            return null;
        }
        final View view = endValues.view;
        float startX = (float) startValues.values.get(locationX);
        float endX = (float) endValues.values.get(locationX);
        float startY = (float) startValues.values.get(locationY);
        float endY = (float) endValues.values.get(locationY);

        if (startX != endX || startY != endY) {
            PointF startPoint = new PointF(startX, startY);
            PointF endPoint = new PointF(endX, endY);
            ValueAnimator animator = ValueAnimator.ofObject(new PointFEvaluator(),
                    startPoint, endPoint);
            animator.setDuration(durationTime);
            animator.setInterpolator(new AnticipateOverShootInterpolater());
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    PointF value = (PointF) animation.getAnimatedValue();
                    if (null != value) {
                        view.setX(value.x);
                        view.setY(value.y);
                    }
                }
            });
            return animator;
        }
        return null;
    }
}
