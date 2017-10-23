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
 *
 * Created by Zoker on 2017/9/28.
 */
public class MaterialImageView extends ImageView{
    private final String TAG = MaterialImageView.class.getSimpleName();
    public static final int MODE_CIRCLE = 0;
    public static final int MODE_NORMAL = -1;
    public static final int MODE_ROUNDED_CORNERS = 1;
    private static final int RoundDefaultSize = 10;
    private int mode = MODE_NORMAL;
    private int round_size = RoundDefaultSize;

    private Bitmap maskBitmap, srcBitmap;
    private Canvas maskCanvans, srcCanvas;

    private Paint srcPaint, maskPaint;
    private RectF roundRectF;
    private Paint clearPaint;

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
        mode = a.getInt(R.styleable.MaterialImageView_mode, MODE_NORMAL);
        round_size = a.getInteger(R.styleable.MaterialImageView_round_size, RoundDefaultSize);
        a.recycle();

        maskPaint = new Paint();
        maskPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        srcPaint = new Paint();
        roundRectF = new RectF();
        clearPaint= new Paint();
        clearPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        Log.d(TAG, "#initView mode=" + mode);
        Log.d(TAG, "#initView round_size=" + round_size);
    }

    @Override
    public void draw(Canvas canvas) {
        int height = canvas.getHeight();
        int width = canvas.getWidth();
        if (maskBitmap == null) {
            maskBitmap = Bitmap.createBitmap(canvas.getWidth(), canvas.getHeight(), Bitmap.Config.ARGB_8888);
            maskCanvans = new Canvas(maskBitmap);
            srcBitmap = Bitmap.createBitmap(canvas.getWidth(), canvas.getHeight(), Bitmap.Config.ARGB_8888);
            srcCanvas = new Canvas(srcBitmap);
        }

        maskCanvans.drawPaint(clearPaint);
        srcCanvas.drawPaint(clearPaint);

        switch (mode) {
            case MODE_NORMAL:
                super.draw(canvas);
                break;
            case MODE_CIRCLE:
                srcPaint.setColor(Color.parseColor("#000000"));
                srcPaint.setAlpha(255);
                srcPaint.setAntiAlias(true);
                int radius = 0;
                if ((height - (getPaddingTop() + getPaddingBottom())) > (width - (getPaddingLeft() + getPaddingRight())))
                    radius = (width - (getPaddingLeft() + getPaddingRight())) / 2;
                else
                    radius = (height - (getPaddingTop() + getPaddingBottom())) / 2;
                super.draw(maskCanvans);
                srcCanvas.drawCircle((getPaddingLeft() + (width - (getPaddingLeft() + getPaddingRight())) / 2), (getPaddingTop() + (height - (getPaddingTop() + getPaddingBottom())) / 2), radius, srcPaint);
                maskCanvans.drawBitmap(srcBitmap, 0, 0, maskPaint);
                canvas.drawBitmap(maskBitmap, 0, 0, srcPaint);
                break;
            case MODE_ROUNDED_CORNERS:
                srcBitmap = Bitmap.createBitmap(canvas.getWidth(), canvas.getHeight(), Bitmap.Config.ARGB_8888);
                srcCanvas = new Canvas(srcBitmap);
                srcPaint.setColor(Color.parseColor("#000000"));
                srcPaint.setAlpha(255);
                srcPaint.setStyle(Paint.Style.FILL);
                srcPaint.setAntiAlias(true);
                roundRectF.left = getPaddingLeft();
                roundRectF.top = getPaddingTop();
                roundRectF.bottom = height - (getPaddingTop() + getPaddingBottom());
                roundRectF.right = width - (getPaddingLeft() + getPaddingRight());
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

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
        invalidate();
    }
}
