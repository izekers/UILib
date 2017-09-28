package com.zoker.lib.image;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

import com.zoker.lib.R;

/**
 * Created by Zoker on 2017/9/28.
 */
public class MaterialImageView extends android.support.v7.widget.AppCompatImageView {
    private final String TAG = MaterialImageView.class.getSimpleName();
    private static final int circle = 0;
    private static final int normal = -1;
    private static final int rounded_corners = 1;
    private static final int RoundDefaultSize = 10;
    private int mode = normal;
    private int round_size = RoundDefaultSize;

    private Bitmap maskBitmap, srcBitmap;
    private Canvas maskCanvans, srcCanvas;

    private Paint srcPaint, maskPaint;
    private RectF roundRectF;

    public MaterialImageView(Context context) {
        super(context);
    }

    public MaterialImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(attrs);
    }

    public MaterialImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(attrs);
    }

    public void initView(AttributeSet attributeSet) {
        TypedArray a = getContext().obtainStyledAttributes(attributeSet, R.styleable.MaterialImageView);
        mode = a.getInt(R.styleable.MaterialImageView_mode, normal);
        round_size = a.getInteger(R.styleable.MaterialImageView_round_size, RoundDefaultSize);
        a.recycle();

        maskPaint = new Paint();
        maskPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        srcPaint = new Paint();
        if (mode == rounded_corners){
            roundRectF =new RectF();
        }
    }

    @Override
    public void draw(Canvas canvas) {
        int height = canvas.getHeight();
        int width = canvas.getWidth();
        if (maskBitmap==null){
            maskBitmap = Bitmap.createBitmap(canvas.getWidth(), canvas.getHeight(), Bitmap.Config.ARGB_8888);
            maskCanvans = new Canvas(maskBitmap);
            srcBitmap = Bitmap.createBitmap(canvas.getWidth(), canvas.getHeight(), Bitmap.Config.ARGB_8888);
            srcCanvas = new Canvas(srcBitmap);
        }

        switch (mode) {
            case normal:
                super.draw(canvas);
                break;
            case circle:
                maskCanvans.drawColor(Color.TRANSPARENT);
                srcCanvas.drawColor(Color.TRANSPARENT);
                srcPaint.setColor(Color.parseColor("#000000"));
                srcPaint.setAlpha(255);
                srcPaint.setAntiAlias(true);
                int radius = 0;
                if (height > width)
                    radius = width / 2;
                else
                    radius = height / 2;
                super.draw(maskCanvans);
                srcCanvas.drawCircle(width / 2, height / 2, radius, srcPaint);
                maskCanvans.drawBitmap(srcBitmap, 0, 0, maskPaint);
                canvas.drawBitmap(maskBitmap, 0, 0, srcPaint);
                break;
            case rounded_corners:
                maskCanvans.drawColor(Color.TRANSPARENT);
                srcCanvas.drawColor(Color.TRANSPARENT);
                srcBitmap = Bitmap.createBitmap(canvas.getWidth(), canvas.getHeight(), Bitmap.Config.ARGB_8888);
                srcCanvas = new Canvas(srcBitmap);
                srcPaint.setColor(Color.parseColor("#000000"));
                srcPaint.setAlpha(255);
                srcPaint.setStyle(Paint.Style.FILL);
                srcPaint.setAntiAlias(true);
                roundRectF.left = 0;
                roundRectF.top = 0;
                roundRectF.bottom = height;
                roundRectF.right = width;
                super.draw(maskCanvans);
                srcCanvas.drawRoundRect(roundRectF, round_size, round_size, srcPaint);
                maskCanvans.drawBitmap(srcBitmap, 0, 0, maskPaint);
                canvas.drawBitmap(maskBitmap, 0, 0, srcPaint);
                break;
            default:
                super.draw(canvas);
                break;
        }
    }
}
