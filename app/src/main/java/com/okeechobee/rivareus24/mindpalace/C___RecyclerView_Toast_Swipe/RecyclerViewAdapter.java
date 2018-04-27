package com.okeechobee.rivareus24.mindpalace.C___RecyclerView_Toast_Swipe;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.okeechobee.rivareus24.mindpalace.R;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private int itemsNumber;
    private final ListItemClickListener mListener;
    private List<RecyclerViewActivity.Product> groceries;

    public RecyclerViewAdapter(int itemsNumber, ListItemClickListener listener, List<RecyclerViewActivity.Product> groceries) {
        this.itemsNumber = itemsNumber;
        mListener = listener;
        this.groceries = groceries;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.recycler_view_item;  // Nome del file contentente l'elemento singolo
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvName.setText(groceries.get(position).getName());
        holder.tvPrice.setText(groceries.get(position).getPrice());
        holder.tvDesc.setText(groceries.get(position).getDesc());
    }

    @Override
    public int getItemCount() {
        return groceries.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView tvName;
        private TextView tvPrice;
        private TextView tvDesc;

        public ViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            tvPrice = (TextView) itemView.findViewById(R.id.tvPrice);
            tvDesc = (TextView) itemView.findViewById(R.id.tvDesc);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            mListener.OnListItemClick(position);
        }
    }

    public interface ListItemClickListener{
        void OnListItemClick(int clickedItemId);
    }
}
