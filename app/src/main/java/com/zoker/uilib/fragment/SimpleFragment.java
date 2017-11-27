package com.zoker.uilib.fragment;

import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.transition.Fade;
import android.support.transition.Scene;
import android.support.transition.TransitionManager;
import android.support.transition.TransitionSet;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.TextView;

import com.zoker.lib.animation.translation.ChangeColor;
import com.zoker.lib.animation.translation.ChangePoint;
import com.zoker.lib.util.DisplayUtil;
import com.zoker.uilib.R;

/**
 * Created by Administrator on 2017/10/19.
 */

public class SimpleFragment extends Fragment {
    boolean isCreate = false;

    Point point;
    Scene scene1, scene2, scene3, scene;
    ViewGroup rootView;

    boolean isInLeft = false;
    boolean isInRight = true;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootViews==null)
            rootViews = inflater.inflate(R.layout.activity_translation, container, false);
        return rootViews;
    }
    View rootViews;
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        rootView = (ViewGroup) getView().findViewById(R.id.rootView);
        scene2 = Scene.getSceneForLayout(this.rootView, R.layout.translation_sense2, getContext());
        scene1 = Scene.getSceneForLayout(this.rootView, R.layout.translation_sense1, getContext());
        scene3 = Scene.getSceneForLayout(this.rootView, R.layout.translation_sense3, getContext());
//        isCreate = true;
//        point =DisplayUtil.getScreenMetrics(getContext());
//        setMoveDelayed();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onPause() {

        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    public void setMoveDelayed() {
        ChangePoint changePoint = new ChangePoint(500, new OvershootInterpolator());
//        ChangePointF changeBounds = new ChangePointF();
        ChangeColor changeColor = new ChangeColor();
        TransitionSet transition = new TransitionSet();
        Fade fade = new Fade();
        transition.addTransition(changePoint);
//        transition.addTransition(changeColor);
//        transition.addTransition(fade);
        //开启延迟动画，在这里会记录当前视图树的状态
        TransitionManager.beginDelayedTransition(((ViewGroup) getView()), transition);
    }

    Interpolator interpolator =new BounceInterpolator();
    public void in(float positionOffset) {
        ChangePoint changePoint = new ChangePoint(800, interpolator);
        ChangePoint changePoint1 = new ChangePoint(700, interpolator);
        ChangePoint changePoint2 = new ChangePoint(1000, interpolator);
        ChangePoint changePoint3 = new ChangePoint(600, interpolator);
        ChangePoint changePoint4 = new ChangePoint(600, interpolator);
        changePoint4.addTarget(R.id.imageView);
        changePoint1.addTarget(R.id.point2);
        changePoint2.addTarget(R.id.point3);
        changePoint3.addTarget(R.id.point4);
        changePoint.addTarget(R.id.point1);

        TransitionSet transition = new TransitionSet();
        transition.addTransition(changePoint);
        transition.addTransition(changePoint1);
        transition.addTransition(changePoint2);
        transition.addTransition(changePoint3);
        transition.addTransition(changePoint4);

        TransitionManager.go(scene1, transition);
    }

    public void outToLeft(float positionOffset) {
        ChangePoint changePoint = new ChangePoint(800, interpolator);
        ChangePoint changePoint1 = new ChangePoint(700, interpolator);
        ChangePoint changePoint2 = new ChangePoint(1000, interpolator);
        ChangePoint changePoint3 = new ChangePoint(600, interpolator);
        ChangePoint changePoint4 = new ChangePoint(600, interpolator);
        changePoint4.addTarget(R.id.imageView);
        changePoint1.addTarget(R.id.point2);
        changePoint2.addTarget(R.id.point3);
        changePoint3.addTarget(R.id.point4);
        changePoint4.addTarget(R.id.point1);
        TransitionSet transition = new TransitionSet();
        transition.addTransition(changePoint);
        transition.addTransition(changePoint1);
        transition.addTransition(changePoint2);
        transition.addTransition(changePoint3);
        transition.addTransition(changePoint4);
        TransitionManager.go(scene3, transition);
        isInLeft = true;
        isInRight = false;
    }

    public void outToRight(float positionOffset) {
        ChangePoint changePoint = new ChangePoint(800, interpolator);
        ChangePoint changePoint1 = new ChangePoint(700, interpolator);
        ChangePoint changePoint2 = new ChangePoint(1000, interpolator);
        ChangePoint changePoint3 = new ChangePoint(600, interpolator);
        ChangePoint changePoint4 = new ChangePoint(600, interpolator);
        changePoint4.addTarget(R.id.imageView);
        changePoint1.addTarget(R.id.point2);
        changePoint2.addTarget(R.id.point3);
        changePoint3.addTarget(R.id.point4);
        changePoint4.addTarget(R.id.point1);
        TransitionSet transition = new TransitionSet();
        transition.addTransition(changePoint);
        transition.addTransition(changePoint1);
        transition.addTransition(changePoint2);
        transition.addTransition(changePoint3);
        transition.addTransition(changePoint4);
        TransitionManager.go(scene2, transition);
        isInLeft = false;
        isInRight = true;
    }
}
