package com.example.bloom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private EditText add1, add2, addnum;
    private Button orderbutton, cancelbutton;

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
    public void createDeliveryInformation(){
        dialogBuilder = new AlertDialog.Builder(this);
        final View popupView = getLayoutInflater().inflate(R.layout.popup, null);
        add1 = (EditText) popupView.findViewById(R.id.address1);
        add2 = (EditText) popupView.findViewById(R.id.address2);
        addnum = (EditText) popupView.findViewById(R.id.additionalnum);

        orderbutton = (Button) popupView.findViewById(R.id.btnConfirm);
        cancelbutton = (Button) popupView.findViewById(R.id.btnCancel);

        dialogBuilder.setView(popupView);
        dialog = dialogBuilder.create();
        dialog.show();

        orderbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent getImage = getIntent();
                String gettingImageUrl = getImage.getStringExtra("image");
                Picasso.get().load(gettingImageUrl).into(img);

                String tvin = tvitemname.getText().toString().trim();
                String tvip = tvitemprice.getText().toString().trim();
                String tvid = tvitemdesc.getText().toString().trim();
                String tvic = tvitemcat.getText().toString().trim();
                String ivimg = gettingImageUrl.trim();
                String qt = Integer.toString(quantity);
                String tvadd1 = add1.getText().toString().trim();
                String tvadd2 = add2.getText().toString().trim();
                String tvaddnum = addnum.getText().toString().trim();

                mRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        if(task.isSuccessful()){
                            DatabaseReference newPost = mRef.push();
                            newPost.child("itemName").setValue(tvin);
                            newPost.child("itemPrice").setValue(tvip);
                            newPost.child("itemDesc").setValue(tvid);
                            newPost.child("itemCat").setValue(tvic);
                            newPost.child("image").setValue(ivimg);
                            newPost.child("quantity").setValue(qt);
                            newPost.child("address1").setValue(tvadd1);
                            newPost.child("address2").setValue(tvadd2);
                            newPost.child("additionalnum").setValue(tvaddnum);


                            Intent intent = new Intent(customer_clickitem_home.this, ProfileActivity.class);
                            startActivity(intent);
                            Toast.makeText( customer_clickitem_home.this,"Item added to your cart!",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText( customer_clickitem_home.this,"There is something wrong.",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        cancelbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        });

    }
}