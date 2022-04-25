package com.example.bloom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class customer_clickitem_home extends AppCompatActivity {

    ImageView img;
    TextView tvitemname,tvitemprice,tvitemdesc,tvitemcat;
    String name,price,desc,cat;

    DatabaseReference reference;
    FirebaseAuth mAuth;
    FirebaseDatabase mDatabase;
    FirebaseUser user;
    Item item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_clickitem_home);

        tv_itemname = (TextView) findViewById(R.id.itemname);
        tv_itemprice = (TextView) findViewById(R.id.itemprice);
        img = (ImageView) findViewById(R.id.imageviewclick);

        Intent intent = getIntent();
        String itemname = intent.getExtras().getString("item name");
        String itemprice = intent.getExtras().getString("item price");
        int image = intent.getExtras().getInt("item image");

        tv_itemname.setText(itemname);
        tv_itemprice.setText(itemprice);
        img.setImageResource(image);
    }
}