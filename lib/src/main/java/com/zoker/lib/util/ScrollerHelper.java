package com.zoker.lib.util;

import android.graphics.PointF;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Scroller;

/**
 * Created by zoker on 2018/1/11.
 */

public class ScrollerHelper {
    /**
     * 手机当时所处的屏幕坐标
     */
    private PointF mCurrentPoint = new PointF();

    /**
     * 上次触发ACTION_MOVE事件时的屏幕坐标
     */
    private PointF mLastPoint = new PointF();

    private Callback callback;

    private View view;

    /**
     * 用于完成滚动操作的实例
     */
    public Scroller mScroller;

    /**
     * 判定为拖动的最小移动像素数
     */
    public int mTouchSlop;

    private ScrollerHelper(View view, Callback callback) {
        this.mScroller = new Scroller(view.getContext());
        this.view = view;
        this.mTouchSlop = ViewConfiguration.get(view.getContext()).getScaledTouchSlop();
        this.callback = callback;
    }

    public static ScrollerHelper create(View view, Callback callback) {
        return new ScrollerHelper(view, callback);
    }

    public void computeScroll() {
        // 第三步，重写computeScroll()方法，并在其内部完成平滑滚动的逻辑
        if (callback != null)
            callback.onMove(view.getScrollX(), view.getScrollY());
        if (mScroller.computeScrollOffset()) {
            view.scrollTo(mScroller.getCurrX(), mScroller.getCurrY());

            view.invalidate();
        }
    }

    /**
     * 只允许在Callback.onRelease()回调中使用，View在松开手后会自动滚动到响应位置
     *
     * @param endx 结束点x
     * @param endy 结束点y
     */
    public void autoMove(int endx, int endy) {
        // 当手指抬起时，根据当前的滚动值来判定应该滚动到哪个子控件的界面
        // 第二步，调用startScroll()方法来初始化滚动数据并刷新界面
        mScroller.startScroll(view.getScrollX(), view.getScrollY(), endx - view.getScrollX(), endy - view.getScrollY());

        if (callback != null) {
            callback.onMove(view.getScrollX(), view.getScrollY());
        }

        view.invalidate();
    }


    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastPoint.x = ev.getRawX();
                mLastPoint.y = ev.getRawY();
                return false;
            case MotionEvent.ACTION_MOVE:
                mCurrentPoint.x = ev.getRawX();
                mCurrentPoint.y = ev.getRawY();
                float diffx = Math.abs(mCurrentPoint.x - mLastPoint.x);
                float diffy = Math.abs(mCurrentPoint.y - mLastPoint.y);

                if (diffx > mTouchSlop && (callback != null && callback.isHorizontalMove(view.getScrollX(), diffx))) {
                    mLastPoint.x = mCurrentPoint.x;
                    mLastPoint.y = mCurrentPoint.y;
                    return true;
                } else if (diffy > mTouchSlop && (callback != null && callback.isVerticalMove(view.getScrollY(), diffy))) {
                    mLastPoint.x = mCurrentPoint.x;
                    mLastPoint.y = mCurrentPoint.y;
                    return true;
                }

                mLastPoint.x = mCurrentPoint.x;
                mLastPoint.y = mCurrentPoint.y;
                return false;
        }
        return false;
    }

    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                return true;
            case MotionEvent.ACTION_MOVE:
                mCurrentPoint.x = event.getRawX();
                mCurrentPoint.y = event.getRawY();
                int dx = (int) (mCurrentPoint.x - mLastPoint.x);
                int dy = (int) (mCurrentPoint.y - mLastPoint.y);
                if (callback != null && callback.isVerticalMove(view.getScrollY(), dy)) {
                    int diffy = callback.clampViewPositionVertical(view.getScrollY(), dy);
                    view.scrollBy(0, diffy);
                    callback.onMove(view.getScrollX(), view.getScrollY());
                }
                if (callback != null && callback.isHorizontalMove(view.getScrollX(), dx)) {
                    int diffx = callback.clampViewPositionHorizontal(view.getScrollX(), dx);
                    view.scrollBy(0, diffx);
                    callback.onMove(view.getScrollX(), view.getScrollY());
                }
                mLastPoint.x = mCurrentPoint.x;
                mLastPoint.y = mCurrentPoint.y;
                break;
            case MotionEvent.ACTION_UP:
                // 当手指抬起时，根据当前的滚动值来判定应该滚动到哪个子控件的界面
                if (callback != null)
                    callback.onRelease(view.getScrollX(), view.getScrollY());
                break;
        }
        return false;
    }


    public abstract static class Callback {
        /**
         * 松开手后的操作
         *
         * @param scrollX View滑动到的点 x坐标 = view.getScrollX();
         * @param scrollY View滑动到的点 y坐标 = view.getScrollY();
         */
        public void onRelease(float scrollX, float scrollY) {
        }

        public void onMove(float scrollX, float scrollY) {
        }

        ;


        /**
         * 滑动事件触发
         *
         * @param scrollX View滑动到的点 x坐标 = view.getScrollX();
         * @param dx      水平线上滑动的距离
         */
        public int clampViewPositionHorizontal(float scrollX, float dx) {
            return 0;
        }

        /**
         * 滑动事件触发
         *
         * @param scrollY View滑动到的点 y坐标 = view.getScrollY();
         * @param dy      竖直线上滑动的距离
         */
        public int clampViewPositionVertical(float scrollY, float dy) {
            return 0;
        }

        /**
         * 判断组件是否可以横向移动
         *
         * @param scrollX 当前View滑动到的点 x坐标 = view.getScrollX();
         * @param diff    手指在屏幕上滑动的距离
         * @return true 可以滑动，false 不可以滑动
         */
        public boolean isHorizontalMove(float scrollX, float diff) {
            return false;
        }

        /**
         * Y
         * 判断组件是否可以横向移动
         *
         * @param scrollY 当前View滑动到的点 y坐标 = view.getScrollY();
         * @param diff    手指在屏幕上滑动的距离
         * @return true 可以滑动，false 不可以滑动
         */
        public boolean isVerticalMove(float scrollY, float diff) {
            return false;
        }
    }
}
