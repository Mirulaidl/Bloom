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

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {


    private Context context;
    private List<Item> data;

    public HomeAdapter(Context context, List<Item> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        LayoutInflater mInflater = LayoutInflater.from(context);
        view = mInflater.inflate(R.layout.customer_home_product, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Item item = data.get(position);
        holder.tv_itemPrice.setText(data.get(position).getItemPrice());
        holder.tv_itemName.setText(data.get(position).getItemName());

        String imageUri = null;
        imageUri = item.getImage();
        Picasso.get().load(imageUri).into(holder.item_image);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent (context,customer_clickitem_home.class);
                intent.putExtra("item name",data.get(holder.getAdapterPosition()).getItemName());
                intent.putExtra("item price",data.get(holder.getAdapterPosition()).getItemPrice());
                intent.putExtra("item image",data.get(holder.getAdapterPosition()).getImage());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_itemName, tv_itemPrice;
        ImageView item_image;
        CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_itemName = (TextView) itemView.findViewById(R.id.nameItem);
            tv_itemPrice = (TextView) itemView.findViewById(R.id.priceItem);
            item_image = (ImageView) itemView.findViewById(R.id.imageItem);

            cardView = (CardView) itemView.findViewById(R.id.cardview_id);

        }
    }
}
