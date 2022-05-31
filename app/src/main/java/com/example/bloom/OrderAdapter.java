package com.example.bloom;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.MyViewHolder> {

    private Context context;
    private List<Item> data;

    public OrderAdapter(Context context, List<Item> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        LayoutInflater mInflater = LayoutInflater.from(context);
        view = mInflater.inflate(R.layout.cart_recycler_item, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder,int position) {

        final Item item=data.get(position);

        String multiple = " x RM";
        String tvq = item.getQuantity()+multiple+item.getItemPrice();
        holder.tvquantity.setText(tvq);
        double qt = Double.parseDouble(item.getQuantity());
        double ip = Double.parseDouble(item.getItemPrice());
        double tp = qt*ip;
        String totalprice = String.valueOf(tp);
        holder.tvitemname.setText(item.getItemName());
        holder.tvitemprice.setText(item.getItemPrice());
        holder.tvitemprice.setText(totalprice);

        String imageUri = null;
        imageUri = item.getImage();
        Picasso.get().load(imageUri).into(holder.tvimageView);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView tvimageView;
        TextView tvitemname, tvitemprice, tvquantity;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvimageView = itemView.findViewById(R.id.itemImageView);
            tvitemname = itemView.findViewById(R.id.itemName);
            tvitemprice = itemView.findViewById(R.id.itemPrice);
            tvquantity = itemView.findViewById(R.id.quantity);

        }
    }
}
