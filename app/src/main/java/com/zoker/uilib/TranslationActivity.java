package com.zoker.uilib;

import android.support.transition.Fade;
import android.support.transition.Scene;
import android.support.transition.TransitionManager;
import android.support.transition.TransitionSet;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.OvershootInterpolator;

import com.zoker.lib.animation.translation.ChangeColor;
import com.zoker.lib.animation.translation.ChangePoint;

public class TranslationActivity extends AppCompatActivity {
    ViewGroup view;
    Scene scene,scene2,scene1,scene3;
    int i = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translation);
        view = (ViewGroup) findViewById(R.id.rootView);
        scene2 = Scene.getSceneForLayout(this.view,R.layout.translation_sense2,this);
        scene1 = Scene.getSceneForLayout(this.view,R.layout.translation_sense1,this);
        scene3 = Scene.getSceneForLayout(this.view,R.layout.translation_sense3,this);
        scene =scene1;
    }

    public void start(View view){
        if (scene == scene2 || scene == scene3){
            if (i == 1)
                i = 2;
            else
                i = 1;
            scene =scene1;
        }else if (scene == scene1){
            if (i==1)
                scene =scene2;
            if (i==2)
                scene = scene3;
        }

        ChangePoint changePoint = new ChangePoint(500,new AccelerateDecelerateInterpolator());
//        ChangePointF changeBounds = new ChangePointF();
        ChangeColor changeColor = new ChangeColor();
        TransitionSet transition = new TransitionSet();
        Fade fade = new Fade();
        transition.addTransition(changePoint);
//        transition.addTransition(changeColor);
//        transition.addTransition(fade);
        TransitionManager.go(scene,transition);
    }
}
