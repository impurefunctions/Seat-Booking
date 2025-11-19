package com.university.soa.bus.SeatClass;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.university.soa.bus.R;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Adapter for the seat selection RecyclerView.
 * This adapter handles the display and selection of seats in a grid layout.
 */
public class AirplaneAdapter extends SelectableAdapter<RecyclerView.ViewHolder> {

    private OnSeatSelected mOnSeatSelected;
    private Set<String> selected = new HashSet<>(), positions;

    /**
     * ViewHolder for edge seats.
     */
    private static class EdgeViewHolder extends RecyclerView.ViewHolder {

        ImageView imgSeat;
        private final ImageView imgSeatSelected;

        /**
         * Constructs a new EdgeViewHolder.
         *
         * @param itemView The view for the item.
         */
        EdgeViewHolder(View itemView) {
            super(itemView);
            imgSeat = itemView.findViewById(R.id.img_seat);
            imgSeatSelected = itemView.findViewById(R.id.img_seat_selected);
        }
    }

    /**
     * ViewHolder for center seats.
     */
    private static class CenterViewHolder extends RecyclerView.ViewHolder {

        ImageView imgSeat;
        private final ImageView imgSeatSelected;

        /**
         * Constructs a new CenterViewHolder.
         *
         * @param itemView The view for the item.
         */
        CenterViewHolder(View itemView) {
            super(itemView);
            imgSeat = itemView.findViewById(R.id.img_seat);
            imgSeatSelected = itemView.findViewById(R.id.img_seat_selected);
        }
    }

    /**
     * ViewHolder for empty spaces.
     */
    private static class EmptyViewHolder extends RecyclerView.ViewHolder {

        /**
         * Constructs a new EmptyViewHolder.
         *
         * @param itemView The view for the item.
         */
        EmptyViewHolder(View itemView) {
            super(itemView);
        }
    }

    private Context mContext;
    private LayoutInflater mLayoutInflater;

    private List<AbstractItem> mItems;

    /**
     * Constructs a new AirplaneAdapter.
     *
     * @param context  The context.
     * @param items    The list of items to display.
     * @param selected The set of selected seat positions.
     */
    public AirplaneAdapter(Context context, List<AbstractItem> items, Set<String> selected) {
        mOnSeatSelected = (OnSeatSelected) context;
        mContext = context;
        positions = selected;
        mLayoutInflater = LayoutInflater.from(context);
        mItems = items;
    }

    /**
     * Gets the number of items in the adapter.
     *
     * @return The number of items.
     */
    @Override
    public int getItemCount() {
        return mItems.size();
    }

    /**
     * Gets the view type of the item at the given position.
     *
     * @param position The position of the item.
     * @return The view type.
     */
    @Override
    public int getItemViewType(int position) {
        return mItems.get(position).getType();
    }

    /**
     * Creates a new ViewHolder for the given view type.
     *
     * @param parent   The parent view group.
     * @param viewType The view type.
     * @return The new ViewHolder.
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == AbstractItem.TYPE_CENTER) {
            View itemView = mLayoutInflater.inflate(R.layout.list_item_seat, parent, false);
            return new CenterViewHolder(itemView);
        } else if (viewType == AbstractItem.TYPE_EDGE) {
            View itemView = mLayoutInflater.inflate(R.layout.list_item_seat, parent, false);
            return new EdgeViewHolder(itemView);
        } else {
            View itemView = new View(mContext);
            return new EmptyViewHolder(itemView);
        }
    }

    /**
     * Binds the data to the ViewHolder at the given position.
     *
     * @param viewHolder The ViewHolder to bind.
     * @param position   The position of the item.
     */
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder, final int position) {
        int type = mItems.get(position).getType();
        if (type == AbstractItem.TYPE_CENTER) {
            final CenterItem item = (CenterItem) mItems.get(position);
            CenterViewHolder holder = (CenterViewHolder) viewHolder;
            if (SeatSelection.positions.contains(String.valueOf(position)) && !selected.contains(String.valueOf(position))) {
                item.setSelectable(false);
                holder.imgSeatSelected.setVisibility(View.VISIBLE);
            }

            holder.imgSeat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (item.isSelectable()) {
                        toggleSelection(position);
                        if (isSelected(position)) {
                            SeatSelection.positions.add(String.valueOf(position));
                            selected.add(String.valueOf(position));
                            positions.add(String.valueOf(position));
                        } else if (SeatSelection.positions.contains(String.valueOf(position))) {
                            SeatSelection.positions.remove(String.valueOf(position));
                            selected.remove(String.valueOf(position));
                            positions.remove(String.valueOf(position));
                        }
                    } else {
                        Toast.makeText(mContext, "Seat already booked", Toast.LENGTH_SHORT).show();
                    }
                    mOnSeatSelected.onSeatSelected(getSelectedItemCount(), positions);
                }
            });

            holder.imgSeatSelected.setVisibility(isSelected(position) || !item.isSelectable() ? View.VISIBLE : View.GONE);

        } else if (type == AbstractItem.TYPE_EDGE) {
            final EdgeItem item = (EdgeItem) mItems.get(position);
            EdgeViewHolder holder = (EdgeViewHolder) viewHolder;
            if (SeatSelection.positions.contains(String.valueOf(position)) && !selected.contains(String.valueOf(position))) {
                item.setSelectable(false);
                holder.imgSeatSelected.setVisibility(View.VISIBLE);
            }

            holder.imgSeat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (item.isSelectable()) {
                        toggleSelection(position);
                        if (isSelected(position)) {
                            SeatSelection.positions.add(String.valueOf(position));
                            selected.add(String.valueOf(position));
                            positions.add(String.valueOf(position));
                        } else if (SeatSelection.positions.contains(String.valueOf(position))) {
                            SeatSelection.positions.remove(String.valueOf(position));
                            selected.remove(String.valueOf(position));
                            positions.remove(String.valueOf(position));
                        }
                    } else {
                        Toast.makeText(mContext, "Seat already booked", Toast.LENGTH_SHORT).show();
                    }
                    mOnSeatSelected.onSeatSelected(getSelectedItemCount(), positions);
                }
            });

            holder.imgSeatSelected.setVisibility(isSelected(position) || !item.isSelectable() ? View.VISIBLE : View.GONE);
        }
    }
}
