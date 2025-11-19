package com.university.soa.bus.listener;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * A RecyclerView.OnItemTouchListener that detects single taps and long presses.
 */
public class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {
    private GestureDetector gestureDetector;
    private RecyclerClickListener clickListener;
    private boolean disallowIntercept = false;

    /**
     * Constructs a new RecyclerTouchListener.
     *
     * @param context      The context.
     * @param recyclerView The RecyclerView to attach the listener to.
     * @param clickListener The listener for click and long-click events.
     */
    public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final RecyclerClickListener clickListener) {
        this.clickListener = clickListener;
        gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                if (child != null && clickListener != null) {
                    clickListener.onLongClick(child, recyclerView.getChildLayoutPosition(child));
                }
            }
        });
    }

    /**
     * Intercepts touch events to detect gestures.
     *
     * @param rv The RecyclerView.
     * @param e  The MotionEvent.
     * @return True to intercept the event, false otherwise.
     */
    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        View child = rv.findChildViewUnder(e.getX(), e.getY());
        if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
            clickListener.onClick(child, rv.getChildLayoutPosition(child));
        }
        return false;
    }

    /**
     * Handles touch events.
     *
     * @param rv The RecyclerView.
     * @param e  The MotionEvent.
     */
    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {
    }

    /**
     * Disallows intercepting touch events.
     *
     * @param disallowIntercept True to disallow interception, false otherwise.
     */
    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        this.disallowIntercept = disallowIntercept;
    }
}
