package demolistview.java.com.heroku.RecyclerViewComponent;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import demolistview.java.com.heroku.PojoCategoryData.Variants;
import demolistview.java.com.heroku.R;

public class VarientRecyclerViewAdapter extends RecyclerView.Adapter<VarientRecyclerViewAdapter.ViewHolder> {

    private Variants [] mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    public VarientRecyclerViewAdapter(Context context, Variants [] data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        Variants item_varients = mData[position];
        holder.tvprice.setText(item_varients.getPrice());
        holder.tvID.setText(item_varients.getId());
        holder.tvcolor.setText(item_varients.getColor());
        holder.tvsize.setText(item_varients.getSize());
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.length;
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvprice;
        TextView tvID;
        TextView tvcolor;
        TextView tvsize;

        ViewHolder(View itemView) {
            super(itemView);
            tvprice = itemView.findViewById(R.id.tvprice);
            tvID = itemView.findViewById(R.id.tvID);
            tvcolor = itemView.findViewById(R.id.tvcolor);
            tvsize = itemView.findViewById(R.id.tvsize);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    Variants getItem(int id)
    {
        return mData[id];
    }

    // allows clicks events to be caught
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}