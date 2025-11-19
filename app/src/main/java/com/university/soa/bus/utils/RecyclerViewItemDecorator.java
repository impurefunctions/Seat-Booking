package com.university.soa.bus.utils;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * An ItemDecoration for adding spacing to items in a RecyclerView.
 */
public class RecyclerViewItemDecorator extends RecyclerView.ItemDecoration {
    private int spaceInPixels;

    /**
     * Constructs a new RecyclerViewItemDecorator.
     *
     * @param spaceInPixels The spacing to add in pixels.
     */
    public RecyclerViewItemDecorator(int spaceInPixels) {
        this.spaceInPixels = spaceInPixels;
    }

    /**
     * Sets the spacing for each item in the RecyclerView.
     *
     * @param outRect The Rect to receive the output.
     * @param view    The child view to decorate.
     * @param parent  The RecyclerView this ItemDecoration is decorating.
     * @param state   The current state of RecyclerView.
     */
    @Override
    public void getItemOffsets(Rect outRect, View view,
                             RecyclerView parent, RecyclerView.State state) {
        outRect.left = spaceInPixels;
        outRect.right = spaceInPixels;
        outRect.bottom = spaceInPixels;

        if (parent.getChildLayoutPosition(view) == 0) {
            outRect.top = spaceInPixels;
        } else {
            outRect.top = 0;
        }
    }
}
