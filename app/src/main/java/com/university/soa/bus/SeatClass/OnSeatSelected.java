package com.university.soa.bus.SeatClass;

import java.util.Set;

/**
 * Interface for handling seat selection events.
 */
public interface OnSeatSelected {

    /**
     * Called when a seat is selected or deselected.
     *
     * @param count    The total number of selected seats.
     * @param selected A set of strings representing the selected seat positions.
     */
    void onSeatSelected(int count, Set<String> selected);
}
