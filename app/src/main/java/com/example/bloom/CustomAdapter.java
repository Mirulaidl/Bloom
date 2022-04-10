package com.example.bloom;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bloom.databinding.ActivityMainBinding;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CustomAdapter extends FirebaseRecyclerAdapter<Item,CustomAdapter.myviewholder>
{
    private Context context;
    private List<Item> items = new ArrayList<>();

    public CustomAdapter(@NonNull FirebaseRecyclerOptions<Item> options, Context context, List<Item> items) {
        super(options);
        this.context = context;
        this.items = items;
    }

    public CustomAdapter(@NonNull FirebaseRecyclerOptions<Item> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull Item model) {
        holder.ItemName.setText(model.getItemName());
        holder.ItemPrice.setText(model.getItemPrice());

    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listing_recycler_item,parent,false);
        return new myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder
    {
        TextView ItemName,ItemPrice, imageID;


        public myviewholder(@NonNull View itemView) {
            super(itemView);
            ItemName=(TextView) itemView.findViewById(R.id.itemName);
            ItemPrice=(TextView) itemView.findViewById(R.id.itemPrice);
            imageID=(TextView) itemView.findViewById(R.id.imageID);


        }
    }
}
