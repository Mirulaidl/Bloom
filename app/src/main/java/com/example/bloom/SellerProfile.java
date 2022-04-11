package com.example.bloom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SellerProfile extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_profile);


        bottomNavigationView = findViewById(R.id.bottom_navigator);
        bottomNavigationView.setSelectedItemId(R.id.sellerProfile);

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
                        startActivity(new Intent(getApplicationContext(),ProfileActivitySeller.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.sellerProfile:
                        return true;
                }
                return false;
            }
        });
    }
}