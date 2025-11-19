package com.university.soa.bus.SeatClass;

import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;

import java.util.ArrayList;
import java.util.List;

/**
 * An abstract adapter that supports item selection.
 *
 * @param <VH> The type of the ViewHolder.
 */
public abstract class SelectableAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    @SuppressWarnings("unused")
    private static final String TAG = SelectableAdapter.class.getSimpleName();

    private SparseBooleanArray selectedItems;

    /**
     * Constructs a new SelectableAdapter.
     */
    public SelectableAdapter() {
        selectedItems = new SparseBooleanArray();
    }

    /**
     * Indicates if the item at the given position is selected.
     *
     * @param position The position of the item to check.
     * @return True if the item is selected, false otherwise.
     */
    public boolean isSelected(int position) {
        return getSelectedItems().contains(position);
    }

    /**
     * Toggles the selection status of the item at the given position.
     *
     * @param position The position of the item to toggle.
     */
    public void toggleSelection(int position) {
        if (selectedItems.get(position, false)) {
            selectedItems.delete(position);
        } else {
            selectedItems.put(position, true);
        }
        notifyItemChanged(position);
    }

    /**
     * Clears the selection status for all items.
     */
    public void clearSelection() {
        List<Integer> selection = getSelectedItems();
        selectedItems.clear();
        for (Integer i : selection) {
            notifyItemChanged(i);
        }
    }

    /**
     * Gets the number of selected items.
     *
     * @return The count of selected items.
     */
    public int getSelectedItemCount() {
        return selectedItems.size();
    }

    /**
     * Gets a list of the selected item positions.
     *
     * @return A list of integers representing the positions of the selected items.
     */
    public List<Integer> getSelectedItems() {
        List<Integer> items = new ArrayList<>(selectedItems.size());
        for (int i = 0; i < selectedItems.size(); ++i) {
            items.add(selectedItems.keyAt(i));
        }
        return items;
    }
}
