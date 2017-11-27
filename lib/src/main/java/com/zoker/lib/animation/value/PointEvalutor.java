package com.zoker.lib.animation.value;

import android.animation.PointFEvaluator;
import android.animation.TypeEvaluator;
import android.graphics.Point;

/**
 * Created by Administrator on 2017/10/27.
 */

public class PointEvalutor implements TypeEvaluator<Point> {
    /**
     * When null, a new PointF is returned on every evaluate call. When non-null,
     * mPoint will be modified and returned on every evaluate.
     */
    private Point mPoint;

    /**
     * Construct a PointFEvaluator that returns a new PointF on every evaluate call.
     * To avoid creating an object for each evaluate call,
     * {@link PointFEvaluator#PointFEvaluator(android.graphics.PointF)} should be used
     * whenever possible.
     */
    public PointEvalutor() {
    }

    /**
     * Constructs a PointFEvaluator that modifies and returns <code>reuse</code>
     * in {@link #evaluate(float, android.graphics.PointF, android.graphics.PointF)} calls.
     * The value returned from
     * {@link #evaluate(float, android.graphics.PointF, android.graphics.PointF)} should
     * not be cached because it will change over time as the object is reused on each
     * call.
     *
     * @param reuse A PointF to be modified and returned by evaluate.
     */
    public PointEvalutor(Point reuse) {
        mPoint = reuse;
    }

    /**
     * @param fraction   动画变化中的浮点参数,0-1
     * @param startValue 动画开始时的Point对象
     * @param endValue   动画结束时的Point对象
     * @return 动画过程中通过计算获取半径并返回一个新的Point对象
     */
    @Override
    public Point evaluate(float fraction, Point startValue, Point endValue) {

        int x = (int)(startValue.x + (fraction * (endValue.x - startValue.x)));
        int y = (int)(startValue.y + (fraction * (endValue.y - startValue.y)));

        if (mPoint != null) {
            mPoint.set(x, y);
            return mPoint;
        } else {
            return new Point(x, y);
        }
    }
}
