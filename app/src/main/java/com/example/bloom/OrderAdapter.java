package com.example.bloom;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Locale;

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
        String totalprice = String.format(Locale.getDefault(),"%.2f",tp);
        holder.tvitemname.setText(item.getItemName());
        holder.tvitemprice.setText(item.getItemPrice());
        holder.tvitemprice.setText(totalprice);
        String orderID = item.getOrderID();

        String imageUri = null;
        imageUri = item.getImage();
        Picasso.get().load(imageUri).into(holder.tvimageView);

        holder.btnreceive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String userID = user.getUid();
                DatabaseReference orderRef = FirebaseDatabase.getInstance().getReference().child("ordercustomer").child(userID).child(orderID);
                orderRef.removeValue();
                Intent intent = new Intent(context,ordercust.class);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView tvimageView;
        TextView tvitemname, tvitemprice, tvquantity;
        Button btnreceive;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvimageView = itemView.findViewById(R.id.itemImageView);
            tvitemname = itemView.findViewById(R.id.itemName);
            tvitemprice = itemView.findViewById(R.id.itemPrice);
            tvquantity = itemView.findViewById(R.id.quantity);
            btnreceive = itemView.findViewById(R.id.btnReceive);

        }
    }
}
