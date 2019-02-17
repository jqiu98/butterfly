package com.example.jeff.butterfly;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.jeff.butterfly.Model.Transaction;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<Transaction> mData;
    private Context mContext;

    // private LayoutInflater mInflater;
    // private ItemClickListener mClickListener;

    // data is passed into the constructor
    public RecyclerViewAdapter(Context context, List<Transaction> data) {
        mContext = context;
        this.mData = data;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.lollipop1, viewGroup, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Transaction transaction = mData.get(position);
        holder.title.setText(transaction.getTitle());
        Log.e("what is in there", "this is ti"+transaction.getBody());
        holder.body.setText(transaction.getBody());
        holder.body.setVisibility(View.GONE);

        holder.title.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    if (holder.body.getVisibility() == View.VISIBLE){
                        holder.body.setVisibility(View.GONE);
                    }
                    else{
                        holder.body.setVisibility(View.VISIBLE);
                    }
                }
            });
    }

    // total number of rows
    @Override
    public int getItemCount() {
//        return 3;
         return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView body;

        public ViewHolder(View itemView){
            super(itemView);

            title = itemView.findViewById(R.id.post);
            body = itemView.findViewById(R.id.body);
        }
}
        

    //     @Override
    //     public void onClick(View view) {
    //         if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
    //     }
    // }

    // // convenience method for getting data at click position
    // String getItem(int id) {
    //     return mData.get(id);
    // }

    // // allows clicks events to be caught
    // void setClickListener(ItemClickListener itemClickListener) {
    //     this.mClickListener = itemClickListener;
    // }

    // // parent activity will implement this method to respond to click events
    // public interface ItemClickListener {
    //     void onItemClick(View view, int position);
    // }
}

