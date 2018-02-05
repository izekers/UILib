package com.zoker.lib.widght;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by zoker on 2018/1/25.
 */

public class DrawerView extends ViewGroup {
    private float leftWidght = 1;
    private float rightWidght = 1;

    private int minWidth = 100;
    private View leftView;
    private View leftDrawerView;
    private View leftTipView;
    private View rightView;
    private View rightTipView;
    private View rightDrawerView;

    public DrawerView(Context context) {
        super(context);
    }

    public DrawerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public DrawerView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setView(View leftView, View rightView, View leftTipView, View rightTipView, View leftDrawerView, View rightDrawerView) {
        this.leftView = leftView;
        this.rightView = rightView;
        this.leftTipView = leftTipView;
        this.leftTipView.setAlpha(0);
        this.rightTipView = rightTipView;
        this.rightTipView.setAlpha(0);
        this.rightDrawerView = rightDrawerView;
        this.leftDrawerView = leftDrawerView;
        this.addView(rightView);
        this.addView(leftView);
        this.addView(leftTipView);
        this.addView(rightTipView);
        this.addView(leftDrawerView);
        this.addView(rightDrawerView);
        this.leftTipView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                openLeft();
            }
        });
        this.rightTipView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                openRight();
            }
        });
        this.leftView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                openLeft();
            }
        });
        this.rightView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                openRight();
            }
        });
        postInvalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec) - getPaddingLeft() - getPaddingRight();
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int warpHeight = height;

        if (rightTipView != null && leftTipView != null) {
            int childWidthMeasureSpec = MeasureSpec.makeMeasureSpec(minWidth, MeasureSpec.EXACTLY);
            measureChild(rightTipView, childWidthMeasureSpec, heightMeasureSpec);
            measureChild(leftTipView, childWidthMeasureSpec, heightMeasureSpec);
        }

        if (leftView != null && rightView != null) {
            int leftWidthMeasureSpec = MeasureSpec.makeMeasureSpec((int) (leftWidght / (leftWidght + rightWidght) * width), MeasureSpec.EXACTLY);
            int rightWidthMeasureSpace = MeasureSpec.makeMeasureSpec((int) (rightWidght / (leftWidght + rightWidght) * width), MeasureSpec.EXACTLY);
            measureChild(leftView, leftWidthMeasureSpec, heightMeasureSpec);
            measureChild(rightView, rightWidthMeasureSpace, heightMeasureSpec);
            warpHeight = leftView.getMeasuredHeight() > rightView.getMeasuredHeight() ? leftView.getMeasuredHeight() : rightView.getMeasuredHeight();

        }

        if (leftDrawerView != null && rightDrawerView != null) {
            int childWidthMeasureSpec = MeasureSpec.makeMeasureSpec(width - minWidth, MeasureSpec.EXACTLY);
            measureChild(leftDrawerView, childWidthMeasureSpec, heightMeasureSpec);
            measureChild(rightDrawerView, childWidthMeasureSpec, heightMeasureSpec);
        }


        setMeasuredDimension(width, heightMode == MeasureSpec.EXACTLY ? height : warpHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (leftView != null && rightView != null) {
            int top, bottom, left, right;
            MarginLayoutParams leftLayoutParams = (MarginLayoutParams) leftView.getLayoutParams();
            top = leftLayoutParams.topMargin + getPaddingTop();
            left = leftLayoutParams.leftMargin + getPaddingLeft();
            bottom = top + leftView.getMeasuredHeight();
            right = left + leftView.getMeasuredWidth();
            leftView.layout(left, top, right, bottom);

            MarginLayoutParams rightLayoutParams = (MarginLayoutParams) rightView.getLayoutParams();
            top = rightLayoutParams.topMargin + getPaddingTop();
            bottom = top + rightView.getMeasuredHeight();
            right = getMeasuredWidth() - getPaddingRight();
            left = right - rightView.getMeasuredWidth();
            rightView.layout(left, top, right, bottom);
        }


        if (leftTipView != null && rightTipView != null) {
            int top, bottom, left, right;
            MarginLayoutParams leftLayoutParams = (MarginLayoutParams) leftTipView.getLayoutParams();
            top = leftLayoutParams.topMargin + getPaddingTop();
            left = leftLayoutParams.leftMargin + getPaddingLeft();
            bottom = top + leftTipView.getMeasuredHeight();
            right = left + leftTipView.getMeasuredWidth();
            leftTipView.layout(left, top, right, bottom);

            MarginLayoutParams rightLayoutParams = (MarginLayoutParams) rightTipView.getLayoutParams();
            right = getMeasuredWidth() - getPaddingRight() - rightLayoutParams.rightMargin;
            top = rightLayoutParams.topMargin + getPaddingTop();
            left = right - rightTipView.getMeasuredWidth();
            bottom = top + rightTipView.getMeasuredHeight();
            rightTipView.layout(left, top, right, bottom);
            leftTipView.setAlpha(0);
            rightTipView.setAlpha(0);
        }

        if (leftDrawerView != null && rightDrawerView != null) {
            int top, bottom, left, right;
            MarginLayoutParams leftLayoutParams = (MarginLayoutParams) leftDrawerView.getLayoutParams();
            top = leftLayoutParams.topMargin + getPaddingTop();
            left = leftLayoutParams.leftMargin + getPaddingLeft();
            bottom = top + leftDrawerView.getMeasuredHeight();
            right = left + leftDrawerView.getMeasuredWidth();
            leftDrawerView.layout(left, top, right, bottom);

            MarginLayoutParams rightLayoutParams = (MarginLayoutParams) rightDrawerView.getLayoutParams();
            right = getMeasuredWidth() - getPaddingRight() - rightLayoutParams.rightMargin;
            top = rightLayoutParams.topMargin + getPaddingTop();
            left = right - rightDrawerView.getMeasuredWidth();
            bottom = top + rightDrawerView.getMeasuredHeight();
            rightDrawerView.layout(left, top, right, bottom);
            leftDrawerView.setX(-leftDrawerView.getMeasuredWidth());
            rightDrawerView.setX(right);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    public void openLeft() {
        if (leftDrawerView != null && rightTipView != null) {
            ObjectAnimator animator = ObjectAnimator.ofFloat(leftDrawerView, "translationX", 0);
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                boolean isStartDrawer = false;
                boolean isStartRightViewDrawer = false;

                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    Float translationX = (Float) animation.getAnimatedValue();

                    if (rightDrawerView != null && leftTipView != null) {
                        if (isStartDrawer) return;
                        if (translationX + leftDrawerView.getMeasuredWidth() >= rightDrawerView.getX()) {
                            ObjectAnimator.ofFloat(rightDrawerView, "translationX", getMeasuredWidth() - getPaddingRight()).start();
                            isStartDrawer = true;
                        }
                    }

                    if (leftView != null && rightView != null) {
                        if (isStartRightViewDrawer) return;
                        if (translationX + leftDrawerView.getMeasuredWidth() >= rightView.getX()) {
                            ObjectAnimator.ofFloat(rightView, "translationX", rightTipView.getX()).start();
                            ObjectAnimator.ofFloat(leftView, "translationX", 0).start();
                            ObjectAnimator.ofFloat(leftView, "alpha", 0).start();
                            ObjectAnimator.ofFloat(rightView, "alpha", 0).start();
                            isStartRightViewDrawer = true;
                        }
                    }
                }
            });
            animator.start();
            ObjectAnimator.ofFloat(rightTipView, "alpha", 1).start();
        }

        if (rightDrawerView != null && leftTipView != null) {
//            ObjectAnimator.ofFloat(rightDrawerView, "translationX", getMeasuredWidth() - getPaddingRight()).start();
            ObjectAnimator.ofFloat(leftTipView, "alpha", 0).start();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    public void openRight() {
        if (leftView != null && rightView != null) {
            ObjectAnimator.ofFloat(leftView, "alpha", 0).start();
            ObjectAnimator.ofFloat(rightView, "alpha", 0).start();
        }
        if (leftDrawerView != null && rightTipView != null) {
//            ObjectAnimator.ofFloat(leftDrawerView, "translationX", -leftDrawerView.getMeasuredWidth()).start();
            ObjectAnimator.ofFloat(rightTipView, "alpha", 0).start();
        }
        if (rightDrawerView != null && leftTipView != null) {
            ObjectAnimator animator = ObjectAnimator.ofFloat(rightDrawerView, "translationX", 0);
            ObjectAnimator.ofFloat(leftTipView, "alpha", 1).start();

            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                boolean isStartDrawer = false;
                boolean isStartRightViewDrawer = false;

                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    Float translationX = (Float) animation.getAnimatedValue();
                    if (leftDrawerView != null) {
                        if (isStartDrawer) return;
                        if (translationX <= leftDrawerView.getX() + leftDrawerView.getMeasuredWidth()) {
                            ObjectAnimator.ofFloat(leftDrawerView, "translationX", -leftDrawerView.getMeasuredWidth()).start();
                            isStartDrawer = true;
                        }
                    }


                    if (leftView != null && rightView != null) {
                        if (isStartRightViewDrawer) return;
                        if (translationX + leftDrawerView.getMeasuredWidth() >= rightView.getX()) {
                            ObjectAnimator.ofFloat(leftView, "translationX", -leftView.getMeasuredWidth() + leftView.getMeasuredWidth()).start();
                            ObjectAnimator.ofFloat(rightTipView, "translationX", 0).start();
                            ObjectAnimator.ofFloat(leftView, "alpha", 0).start();
                            ObjectAnimator.ofFloat(rightView, "alpha", 0).start();
                            isStartRightViewDrawer = true;
                        }
                    }
                }
            });
            animator.start();
        }
    }

    public void allClose() {
        if (leftView != null && rightView != null) {
            ObjectAnimator.ofFloat(leftView, "alpha", 1).start();
            ObjectAnimator.ofFloat(rightView, "alpha", 1).start();
        }
        if (leftDrawerView != null && rightTipView != null) {
            ObjectAnimator.ofFloat(leftDrawerView, "translationX", -leftDrawerView.getMeasuredWidth()).start();
            ObjectAnimator.ofFloat(rightTipView, "alpha", 0).start();
        }
        if (rightDrawerView != null && leftTipView != null) {
            ObjectAnimator.ofFloat(rightDrawerView, "translationX", getMeasuredWidth() - getPaddingRight()).start();
            ObjectAnimator.ofFloat(leftTipView, "alpha", 0).start();
        }
    }

    @Override
    protected LayoutParams generateLayoutParams(
            LayoutParams p) {
        return new MarginLayoutParams(p);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT);
    }
}
