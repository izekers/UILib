package com.zoker.lib.list.recycle.ItemDecoration;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.ColorInt;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * 分割线
 * Created by Administrator on 2017/10/24.
 */

public class LineItemDecoration extends RecyclerView.ItemDecoration {
    private Paint paint;

    private int devideWidth;
    private int devidePaddingStart, devidePaddingEnd;

    public LineItemDecoration(int devideWidth) {
        this(devideWidth, 0, 0, 0);
    }

    public LineItemDecoration(int devideWidth, @ColorInt int devideColor) {
        this(devideWidth, devideColor, 0, 0);
    }

    public LineItemDecoration(int devideWidth, @ColorInt int devideColor, int paddingStart, int paddingEnd) {
        paint = new Paint();
        if (devideColor != 0)
            paint.setColor(devideColor);
        setDevideWidth(devideWidth);
        setDevidePaddingStart(paddingStart);
        setDevidePaddingEnd(paddingEnd);
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

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if (parent.getLayoutManager() instanceof LinearLayoutManager) {
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) parent.getLayoutManager();
            if (linearLayoutManager.getOrientation() == LinearLayoutManager.VERTICAL) {
                outRect.set(0, 0, 0, devideWidth);
            } else {
                outRect.set(0, 0, devideWidth, 0);
            }
        }else if (parent.getLayoutManager() instanceof GridLayoutManager){
//            GridLayoutManager gridLayoutManager = (GridLayoutManager) parent.getLayoutManager();
//            int totalCount = gridLayoutManager.getItemCount();
//            int surplusCount = totalCount % gridLayoutManager.getSpanCount();
//            int childposition = parent.getChildAdapterPosition(view);
//            if (gridLayoutManager.getOrientation() == GridLayoutManager.VERTICAL){
//                if (surplusCount == 0 && childposition > totalCount - gridLayoutManager.getSpanCount() -1){
//                    outRect.bottom = devideWidth;
//                }else if (surplusCount !=0 && childposition>totalCount - gridLayoutManager.getSpanCount()-1){
//                    outRect.bottom = devideWidth;
//                }
//            }else if (gridLayoutManager.getOrientation() == GridLayoutManager.HORIZONTAL){
//
//            }
        }
    }

    public void setDevidePaddingStart(int devidePaddingStart) {
        this.devidePaddingStart = devidePaddingStart;
    }

    public void setDevidePaddingEnd(int devidePaddingEnd) {
        this.devidePaddingEnd = devidePaddingEnd;
    }

    public void setDevideWidth(int devideWidth) {
        this.devideWidth = devideWidth;
    }
}
