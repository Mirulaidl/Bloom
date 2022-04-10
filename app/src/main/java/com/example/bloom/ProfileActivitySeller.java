package com.example.bloom;

import static com.google.firebase.auth.FirebaseAuth.getInstance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivitySeller extends AppCompatActivity  {

    BottomNavigationView bottomNavigationView;

    private RecyclerView recyclerView;
    private CustomAdapter adapter;
    private FloatingActionButton addButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_seller);

        bottomNavigationView = findViewById(R.id.bottom_navigator);
        bottomNavigationView.setSelectedItemId(R.id.listings);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId())
                {
                    case R.id.order:
                        startActivity(new Intent(getApplicationContext(),Order.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.listings:
                        return true;
                    case R.id.sellerProfile:
                        startActivity(new Intent(getApplicationContext(),SellerProfile.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
        //recyclerView

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
                Intent i = new Intent(ProfileActivitySeller.this,AddItem.class);
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
