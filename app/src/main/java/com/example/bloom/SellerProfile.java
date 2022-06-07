package com.example.bloom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SellerProfile extends AppCompatActivity {


    FirebaseUser user;
    DatabaseReference reference;
    String userID;
    Button logout;
    Button editProfile;

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_profile);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("users");
        userID = user.getUid();

        final TextView fullnameTV = findViewById(R.id.dispfullname);
        final TextView usernameTV = findViewById(R.id.dispusername);
        final TextView emailTV = findViewById(R.id.dispemail);
        final TextView phonenumTV = findViewById(R.id.dispphoneNum);

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);
                if(userProfile != null){
                    String fullname = userProfile.fullname;
                    String username = userProfile.username;
                    String email = userProfile.email;
                    String phonenum = userProfile.phone;

                    fullnameTV.setText(fullname);
                    usernameTV.setText(username);
                    emailTV.setText(email);
                    phonenumTV.setText(phonenum);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(SellerProfile.this,"Something wrong happened!", Toast.LENGTH_LONG).show();
            }
        });

        editProfile = (Button) findViewById(R.id.editprofile);
        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(),EditProfileCustomer.class);
                i.putExtra("fullname",fullnameTV.getText());
                i.putExtra("username",usernameTV.getText());
                i.putExtra("email",emailTV.getText());
                i.putExtra("phonenum",phonenumTV.getText());
                startActivity(i);
            }
        });

        logout = (Button) findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(SellerProfile.this,MainActivity.class));
            }
        });


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

    @Override
    public void onBackPressed() {
        setContentView(R.layout.activity_profile_seller);
    }
}