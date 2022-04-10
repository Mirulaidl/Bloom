package com.example.bloom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class ListingPageSeller extends AppCompatActivity {

    RecyclerView recyclerView;
    CustomAdapter adapter;
    FloatingActionButton addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing_page_seller);

        recyclerView = (RecyclerView) findViewById(R.id.listing_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        FirebaseRecyclerOptions<Item> options =
                new FirebaseRecyclerOptions.Builder<Item>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("items").child(userID), Item.class)
                        .build();

        adapter = new CustomAdapter(options);
        recyclerView.setAdapter(adapter);

        addButton = findViewById(R.id.addItemButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ListingPageSeller.this,AddItem.class);
                startActivity(i);
            }
        });
    }
    @Override
    public void onStart(){
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop(){
        super.onStop();
        adapter.stopListening();
    }
}