package com.university.soa.bus.SeatClass;

/**
 * Represents an edge seat in the seat selection grid.
 */
public class EdgeItem extends AbstractItem {

    private boolean selectable;

    /**
     * Constructs a new EdgeItem with the given label.
     *
     * @param label The label for the seat.
     */
    public EdgeItem(String label) {
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
     * @return The item's type, which is always TYPE_EDGE.
     */
    @Override
    public int getType() {
        return AbstractItem.TYPE_EDGE;
    }
}
