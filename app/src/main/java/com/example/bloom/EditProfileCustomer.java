package com.example.bloom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditProfileCustomer extends AppCompatActivity {

    EditText profFullname,profUsername,profEmail,profPhonenum;
    public static final String TAG = "TAG";

    String fullname,username,email,phone;

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    String userID = user.getUid();

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_edit_profile_customer);

        reference = FirebaseDatabase.getInstance().getReference("users");


        Intent data = getIntent();
        fullname = data.getStringExtra("fullname");
        username =  data.getStringExtra("username");
        email = data.getStringExtra("email");
        phone = data.getStringExtra("phonenum");

        profFullname = findViewById(R.id.editfullname);
        profUsername = findViewById(R.id.editusername);
        profEmail = findViewById(R.id.editemail);
        profPhonenum = findViewById(R.id.editphonenum);

        profFullname.setText(fullname);
        profUsername.setText(username);
        profEmail.setText(email);
        profPhonenum.setText(phone);

        Log.d(TAG,"onCreate: " + fullname + " " + username + " " + email + " " + phone );
    }

    public void update(View view){
        if(isNameChanged()){
            Toast.makeText(this,"Data has been updated", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Fullname is same.", Toast.LENGTH_LONG).show();
        }

        if(isUsernameChanged()){
            Toast.makeText(this,"Data has been updated", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Username is same.", Toast.LENGTH_LONG).show();
        }

        if(isEmailChanged()){
            Toast.makeText(this,"Data has been updated", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Email is same.", Toast.LENGTH_LONG).show();
        }

        if(isPhoneChanged()){
            Toast.makeText(this,"Data has been updated", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Phone number is same.", Toast.LENGTH_LONG).show();
        }
    }

    private boolean isPhoneChanged() {
        if(!phone.equals(profPhonenum.getText().toString())){
            reference.child(userID).child("phone").setValue(profPhonenum.getText().toString());
            return true;
        }else {
            return false;
        }
    }

    private boolean isEmailChanged() {
        if(!email.equals(profEmail.getText().toString())){
            reference.child(userID).child("email").setValue(profEmail.getText().toString());
            return true;
        }else {
            return false;
        }
    }

    private boolean isUsernameChanged() {
        if(!username.equals(profUsername.getText().toString())){
            reference.child(userID).child("username").setValue(profUsername.getText().toString());
            return true;
        }else {
            return false;
        }
    }

    private boolean isNameChanged() {

        if(!fullname.equals(profFullname.getText().toString())){
            reference.child(userID).child("fullname").setValue(profFullname.getText().toString());
            return true;
        }else {
            return false;
        }
    }
}