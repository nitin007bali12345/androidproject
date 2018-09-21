package demolistview.java.com.heroku.RecyclerViewComponent;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import demolistview.java.com.heroku.PojoCategoryData.Products;
import demolistview.java.com.heroku.R;

public class CategoryRecyclerViewAdapter extends RecyclerView.Adapter<CategoryRecyclerViewAdapter.ViewHolder> {

    private LayoutInflater mInflater;
    public ItemClickListener mClickListener;
    Products[] mProducts;
    String mCategoryName;
    String mCategoryDateIssue;
    int mItemPosition = 0;


    public CategoryRecyclerViewAdapter(Context context, Products[] products,String categoryName,String categoryDateIssue) {
        this.mInflater = LayoutInflater.from(context);
        this.mProducts = products;
        this.mCategoryName = categoryName;
        this.mCategoryDateIssue = categoryDateIssue;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.categoryrecycler_row, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        //mCategoryName+"-"+
        String product = mProducts[position].getName()+"-----"+mCategoryDateIssue;
        holder.myTextView.setText(product);
    }

    // total number of rows
    @Override
    public int getItemCount() {
        mItemPosition = mProducts.length;
        Log.e("mItemPosition ",""+mItemPosition);
        return mProducts.length;
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView myTextView;
        ImageView img;


        ViewHolder(View itemView)
        {
            super(itemView);
            myTextView = itemView.findViewById(R.id.tvAnimalName);
            myTextView.setBackgroundResource(R.color.lightblue);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view)
        {
            if (mClickListener != null) mClickListener.onItemClick(view, mProducts[getAdapterPosition()]);
        }
    }

    // allows clicks events to be caught
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, Products products);
    }
}