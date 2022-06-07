package com.example.bloom;

import static com.google.firebase.auth.FirebaseAuth.getInstance;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends AppCompatActivity{

    BottomNavigationView bottomNavigationView;

    FirebaseDatabase mDatabase;
    DatabaseReference mRef,mRef2;
    FirebaseStorage mStorage;
    RecyclerView recyclerView;
    HomeAdapter itemAdapter;
    List<Item> itemList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        //recyclerview

        Intent intent = getIntent();
        String id = intent.getStringExtra("sellerID");

        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference("allitems");

        mStorage = FirebaseStorage.getInstance();

        recyclerView = findViewById(R.id.home_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));

        itemList = new ArrayList<Item>();
        itemAdapter = new HomeAdapter(ProfileActivity.this,itemList);
        recyclerView.setAdapter(itemAdapter);
        mRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot datasnapshot, @Nullable String previousChildName) {
                Item item = datasnapshot.getValue(Item.class);
                itemList.add(item);
                itemAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }


            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //bottom navigation
        bottomNavigationView = findViewById(R.id.bottom_navigator);
        bottomNavigationView.setSelectedItemId(R.id.home);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId())
                {
                    case R.id.home:
                        return true;
                    case R.id.customerProfile:
                        startActivity(new Intent(getApplicationContext(),CustomerProfile.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.order:
                        startActivity(new Intent(getApplicationContext(),ordercust.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });


    }
    int counter=0;
    @Override
    public void onBackPressed() {
        counter ++;
        if (counter==2)
            super.onBackPressed();
    }
}