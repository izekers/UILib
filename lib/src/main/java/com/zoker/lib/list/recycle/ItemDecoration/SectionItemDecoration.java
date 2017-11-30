package com.zoker.lib.list.recycle.ItemDecoration;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * 分割线
 * Created by Administrator on 2017/10/24.
 */

public class SectionItemDecoration extends RecyclerView.ItemDecoration {
    private Paint paint;

    private int devideWidth;
    private int devidePaddingStart, devidePaddingEnd;

    public SectionItemDecoration(int tagHeight, int tagWidth, Bitmap bitmap) {
        
    }


    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if (parent.getLayoutManager() instanceof LinearLayoutManager) {
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) parent.getLayoutManager();
            if (linearLayoutManager.getOrientation() == LinearLayoutManager.VERTICAL) {
                final int left = parent.getPaddingLeft() + devidePaddingStart;
                final int right = parent.getWidth() - parent.getPaddingRight() - devidePaddingEnd;

                for (int i = 0; i < parent.getChildCount(); i++) {
                    final View view = parent.getChildAt(i);
                    final RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
                    final int top = view.getBottom() + layoutParams.topMargin + Math.round(ViewCompat.getTranslationY(view));
                    final int bottom = top + devideWidth;
                    c.drawRect(left, top, right, bottom, paint);
                }
            } else if (linearLayoutManager.getOrientation() == LinearLayoutManager.HORIZONTAL) {
                final int top = parent.getPaddingLeft() + devidePaddingStart;
                final int bottom = parent.getWidth() - parent.getPaddingRight() - devidePaddingEnd;
                for (int i = 0; i < parent.getChildCount(); i++) {
                    final View view = parent.getChildAt(i);
                    final RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
                    final int left = view.getLeft() + layoutParams.leftMargin + Math.round(ViewCompat.getTranslationX(view));
                    final int right = left + devideWidth;
                    c.drawRect(left, top, right, bottom, paint);
                }
            }
        }
    }
}
