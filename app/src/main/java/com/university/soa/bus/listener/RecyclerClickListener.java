package com.university.soa.bus.listener;

import android.view.View;

/**
 * An abstract class for handling click events on a RecyclerView.
 */
public abstract class RecyclerClickListener {

    /**
     * Called when an item in the RecyclerView is clicked.
     *
     * @param view     The view that was clicked.
     * @param position The position of the clicked item.
     */
    public void onClick(View view, int position) {
    }

    /**
     * Called when an item in the RecyclerView is long-clicked.
     *
     * @param view     The view that was long-clicked.
     * @param position The position of the long-clicked item.
     */
    public void onLongClick(View view, int position) {
    }
}
