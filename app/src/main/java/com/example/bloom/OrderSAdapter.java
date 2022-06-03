package com.example.bloom;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bloom.databinding.ActivityMainBinding;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



import de.hdodenhof.circleimageview.CircleImageView;

public class OrderSAdapter extends RecyclerView.Adapter<OrderSAdapter.ViewHolder> {

    Context context;
    List<Item> itemList;


    public OrderSAdapter(Context context, List<Item> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.seller_order_item,parent,false);

        return new ViewHolder(v).linkAdapter(this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        Item item = itemList.get(position);
        holder.itemname.setText(item.getItemName());
        holder.itemprice.setText(item.getItemPrice());
        holder.itemquantity.setText(item.getQuantity());
        holder.itemcategory.setText(item.getItemCategory());
        holder.itemadd1.setText(item.getAddress1());
        holder.itemadd2.setText(item.getAddress2());
        holder.itemphonenum.setText(item.getAdditionalnumber());
        String itemID = item.getOrderID();

        String imageUri = null;
        imageUri = item.getImage();
        Picasso.get().load(imageUri).into(holder.itemimage);

        holder.imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference orderRef = FirebaseDatabase.getInstance().getReference().child("orderseller").child(itemID);
                orderRef.removeValue();
                Intent intent = new Intent(context,Order.class);
                context.startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView itemimage;
        TextView itemname, itemprice, itemquantity, itemcategory, itemadd1,itemadd2, itemphonenum;
        Button imgbtn;
        private OrderSAdapter adapter;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            itemimage = itemView.findViewById(R.id.itemImageView);
            itemname = itemView.findViewById(R.id.itemName);
            itemprice = itemView.findViewById(R.id.itemPrice);
            itemquantity = itemView.findViewById(R.id.quantity);
            itemcategory = itemView.findViewById(R.id.category);
            itemadd1 = itemView.findViewById(R.id.ad1);
            itemadd2 = itemView.findViewById(R.id.ad2);
            itemphonenum = itemView.findViewById(R.id.addnum);
            imgbtn = itemView.findViewById(R.id.delete);

        }
        public ViewHolder linkAdapter(OrderSAdapter adapter){
            this.adapter = adapter;
            return this;
        }

    }
}

