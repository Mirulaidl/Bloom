package com.example.bloom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class customer_clickitem_home extends AppCompatActivity {

    private TextView tv_itemname, tv_itemprice;
    private ImageView img;

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