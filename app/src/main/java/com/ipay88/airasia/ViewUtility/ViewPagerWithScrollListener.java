package com.ipay88.airasia.ViewUtility;

import android.content.Context;
import android.support.annotation.Px;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

public class ViewPagerWithScrollListener extends ViewPager {
    private int mScrollingX, mScrollingY;
    public CustomOnScrollChangeListener mListener;
    public interface CustomOnScrollChangeListener {
        /**
         * Called when the scroll position of a view changes.
         *
         * @param v The view whose scroll position has changed.
         * @param scrollX Current horizontal scroll origin.
         * @param scrollY Current vertical scroll origin.
         * @param oldScrollX Previous horizontal scroll origin.
         * @param oldScrollY Previous vertical scroll origin.
         */
        void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY);
    }
    public ViewPagerWithScrollListener(Context context) {
        super(context);
    }

    public ViewPagerWithScrollListener(Context context, AttributeSet attrs) {
        super(context, attrs);
    }



    public void setCustomScrollChangeListener(CustomOnScrollChangeListener listener) {
        mListener = listener;
    }

    @Override
    public void scrollTo(@Px int x, @Px int y) {
        super.scrollTo(x, y);
        if (mScrollingX != x || mScrollingY != y) {
            int oldX = mScrollingX;
            int oldY = mScrollingY;
            mScrollingX = x;
            mScrollingY = y;
            invalidate();
            mListener.onScrollChange(this, mScrollingX, mScrollingY, oldX, oldY);
            if (!awakenScrollBars()) {
                postInvalidateOnAnimation();
            }
        }
    }
}
