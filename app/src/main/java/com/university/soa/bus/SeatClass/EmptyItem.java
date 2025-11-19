package com.university.soa.bus.SeatClass;

/**
 * Represents an empty space in the seat selection grid.
 */
public class EmptyItem extends AbstractItem {

    /**
     * Constructs a new EmptyItem with the given label.
     *
     * @param label The label for the empty space.
     */
    public EmptyItem(String label) {
        super(label);
    }

    /**
     * Gets the type of the item.
     *
     * @return The item's type, which is always TYPE_EMPTY.
     */
    @Override
    public int getType() {
        return AbstractItem.TYPE_EMPTY;
    }
}
