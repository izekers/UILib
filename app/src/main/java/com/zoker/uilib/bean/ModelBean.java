package com.zoker.uilib.bean;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Administrator on 2017/9/29.
 */

public class ModelBean {
    private String title;
    private Intent intent;

    public ModelBean(String title, Context context, Class<? extends Activity> activity) {
        this.intent = new Intent(context, activity);
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Intent getIntent() {
        return intent;
    }

    public void setIntent(Intent intent) {
        this.intent = intent;
    }
}
