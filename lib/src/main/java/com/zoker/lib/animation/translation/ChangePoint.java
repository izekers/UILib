package com.zoker.lib.animation.translation;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.graphics.Point;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.transition.Transition;
import android.support.transition.TransitionValues;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;

import com.zoker.lib.animation.interpolater.AnticipateOverShootInterpolater;
import com.zoker.lib.animation.value.PointEvalutor;

/**
 * 坐标转场
 * Created by Administrator on 2017/10/27.
 */
public class ChangePoint extends Transition {
    private static final String locationX =
            "zoker:changelocation:x";
    private static final String locationY =
            "zoker:changelocation:y";


    private int durationTime = 1000;
    private Interpolator interpolator = null;

    public ChangePoint() {
        this(1000, null);
    }

    public ChangePoint(int durationTime) {
        this(durationTime, null);
    }

    public ChangePoint(Interpolator interpolator) {
        this(1000, interpolator);
    }

    public ChangePoint(int durationTime, Interpolator interpolator) {
        this.durationTime = durationTime;
        this.interpolator = interpolator;
    }

    public void setInterpolator(Interpolator interpolator) {
        this.interpolator = interpolator;
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
        transitionValues.values.put(locationX, (int) view.getX());
        transitionValues.values.put(locationY, (int) view.getY());
    }

    @Nullable
    @Override
    public Animator createAnimator(@NonNull ViewGroup sceneRoot, @Nullable TransitionValues startValues, @Nullable TransitionValues endValues) {
        if (null == startValues || null == endValues) {
            return null;
        }
        final View view = endValues.view;
        int startX = (int) startValues.values.get(locationX);
        int endX = (int) endValues.values.get(locationX);
        int startY = (int) startValues.values.get(locationY);
        int endY = (int) endValues.values.get(locationY);

        if (startX != endX || startY != endY) {
            Point startPoint = new Point(startX, startY);
            Point endPoint = new Point(endX, endY);
            ValueAnimator animator = ValueAnimator.ofObject(new PointEvalutor(),
                    startPoint, endPoint);
            animator.setDuration(durationTime);
            animator.setInterpolator(this.interpolator);
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    Point value = (Point) animation.getAnimatedValue();
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
