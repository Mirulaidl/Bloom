package com.example.bloom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
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

    FirebaseDatabase mDatabase;
    DatabaseReference mRef;

    ImageView img;
    TextView tvitemname,tvitemprice,tvitemdesc,tvitemcat;
    String name,price,desc,cat;
    Button addToCartButton;

    int quantity;
    NumberPicker numberPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_clickitem_home);


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