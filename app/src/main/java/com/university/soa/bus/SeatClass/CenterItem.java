package com.university.soa.bus.SeatClass;

/**
 * Represents a center seat in the seat selection grid.
 */
public class CenterItem extends AbstractItem {

    private boolean selectable;

    /**
     * Constructs a new CenterItem with the given label.
     *
     * @param label The label for the seat.
     */
    public CenterItem(String label) {
        super(label);
        selectable = true;
    }

    /**
     * Checks if the seat is selectable.
     *
     * @return True if the seat is selectable, false otherwise.
     */
    boolean isSelectable() {
        return selectable;
    }

    /**
     * Sets the selectability of the seat.
     *
     * @param selectable True to make the seat selectable, false otherwise.
     */
    void setSelectable(boolean selectable) {
        this.selectable = selectable;
    }

    /**
     * Gets the type of the item.
     *
     * @return The item's type, which is always TYPE_CENTER.
     */
    @Override
    public int getType() {
        return AbstractItem.TYPE_CENTER;
    }
}
