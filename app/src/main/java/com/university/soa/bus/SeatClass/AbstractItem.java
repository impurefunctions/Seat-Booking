package com.university.soa.bus.SeatClass;

/**
 * Represents an abstract item in the seat selection grid.
 * This class defines the basic properties of an item, such as its label and type.
 */
public abstract class AbstractItem {

    /**
     * Type for a center seat.
     */
    public static final int TYPE_CENTER = 0;
    /**
     * Type for an edge seat.
     */
    public static final int TYPE_EDGE = 1;
    /**
     * Type for an empty space.
     */
    public static final int TYPE_EMPTY = 2;

    private String label;

    /**
     * Constructs a new AbstractItem with the given label.
     *
     * @param label The label for the item.
     */
    public AbstractItem(String label) {
        this.label = label;
    }

    /**
     * Gets the label of the item.
     *
     * @return The item's label.
     */
    public String getLabel() {
        return label;
    }

    /**
     * Gets the type of the item.
     * This method must be implemented by subclasses.
     *
     * @return The item's type.
     */
    abstract public int getType();
}
