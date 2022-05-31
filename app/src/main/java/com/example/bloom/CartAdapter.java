package com.example.bloom;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder>{

    Context context;
    List<Item> itemList;


    public CartAdapter(Context context, List<Item> itemList) {
        this.context = context;
        this.itemList = itemList;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_recycler_item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Item item = itemList.get(position);
        holder.quantity.setText(item.getQuantity()+" x RM"+item.getItemPrice());
        double qt = Double.parseDouble(item.getQuantity());
        double ip = Double.parseDouble(item.getItemPrice());
        double tp = qt*ip;
        String totalprice = String.valueOf(tp);
        holder.itemname.setText(item.getItemName());
        holder.itemprice.setText(totalprice);

        String imageUri = null;
        imageUri = item.getImage();
        Picasso.get().load(imageUri).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView itemname, itemprice , quantity;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.itemImageView);
            itemname = itemView.findViewById(R.id.itemName);
            itemprice = itemView.findViewById(R.id.itemPrice);
            quantity = itemView.findViewById(R.id.quantity);


        }
    }

}
