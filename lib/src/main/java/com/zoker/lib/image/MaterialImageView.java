package com.zoker.lib.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 *
 * Created by Zoker on 2017/9/28.
 */
public class MaterialImageView extends android.support.v7.widget.AppCompatImageView {
    Bitmap canvasBitmap, srcBitmap;
    Canvas maskCanvans, srcCanvas;
    Paint paint, maskPaint;

    public MaterialImageView(Context context) {
        super(context);
    }

    public MaterialImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MaterialImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void draw(Canvas canvas) {
        if (canvasBitmap == null) {
            canvasBitmap = Bitmap.createBitmap(canvas.getWidth(), canvas.getHeight(), Bitmap.Config.ARGB_8888);
            maskCanvans = new Canvas(canvasBitmap);

            srcBitmap = Bitmap.createBitmap(canvas.getWidth(), canvas.getHeight(), Bitmap.Config.ARGB_8888);
            srcCanvas = new Canvas(srcBitmap);
            paint = new Paint();
            paint.setColor(Color.parseColor("#000000"));
            paint.setAlpha(255);
            paint.setAntiAlias(true);
            maskPaint = new Paint();
            maskPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        }
        int height = canvas.getHeight();
        int widght = canvas.getWidth();
        int radius = 0;
        if (height > widght)
            radius = widght / 2;
        else
            radius = height / 2;
        super.draw(maskCanvans);

        srcCanvas.drawCircle(widght / 2, height / 2, radius, paint);
        maskCanvans.drawBitmap(srcBitmap, 0, 0, maskPaint);
        canvas.drawBitmap(canvasBitmap, 0, 0, paint);
    }
}
