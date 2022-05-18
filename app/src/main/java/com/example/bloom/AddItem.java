package com.example.bloom;

import static com.google.firebase.auth.FirebaseAuth.getInstance;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.NumberPicker;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.hdodenhof.circleimageview.CircleImageView;

public class AddItem extends AppCompatActivity {
    FirebaseDatabase mDatabase;
    DatabaseReference mRef;
    DatabaseReference mRef2;
    FirebaseStorage mStorage;
    ImageButton imageButton;
    EditText editItemPrice, editItemName, editItemDescription;
    Button btnInsert;
    private static final int Gallery_Code=1;
    Uri imageUrl=null;
    ProgressDialog progressDialog;

    String[] items = {"Plants","Compost & Soils","Pots & Planters","Seeds","Gardening Tools","Pesticides","Gardening Accessories"};
    AutoCompleteTextView autoCompleteTxt;
    ArrayAdapter<String> adapterItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        autoCompleteTxt = findViewById(R.id.auto_complete_txt);
        adapterItems = new ArrayAdapter<String>(this,R.layout.categorydropdown,items);
        autoCompleteTxt.setAdapter(adapterItems);
        autoCompleteTxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String itemdropdown = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(),"Item: "+itemdropdown,Toast.LENGTH_SHORT).show();
            }
        });

        imageButton = findViewById(R.id.imageButton);
        editItemName = findViewById(R.id.editItemName);
        editItemPrice = findViewById(R.id.editItemPrice);
        editItemDescription = findViewById(R.id.editItemDescription);
        btnInsert = findViewById(R.id.btnInsert);

        String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference().child("items").child(userID);
        mRef2 = mDatabase.getReference().child("allitems");
        mStorage= FirebaseStorage.getInstance();
        progressDialog = new ProgressDialog(this);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,Gallery_Code);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==Gallery_Code && resultCode==RESULT_OK)
        {
            imageUrl = data.getData();
            imageButton.setImageURI(imageUrl);
        }

    btnInsert.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String in= editItemName.getText().toString().trim();
            String ip= editItemPrice.getText().toString().trim();
            String dd= editItemDescription.getText().toString().trim();
            String ic= autoCompleteTxt.getText().toString().trim();

            String ItemName = editItemName.getText().toString().trim();
            String ItemPrice = editItemPrice.getText().toString().trim();

            if(dd.isEmpty()){
                autoCompleteTxt.setError("Please, choose category for your item!");
                autoCompleteTxt.requestFocus();
                return;
            }

            if(ic.isEmpty()){
                editItemDescription.setError("Item must have description");
                editItemDescription.requestFocus();
                return;
            }
            if(ic.length()>100){
                editItemDescription.setError("Max description length is 100 characters!");
                editItemDescription.requestFocus();
                return;
            }

            if (ItemName.length()>25){
                editItemName.setError("Max item name length is 25 characters!");
                editItemName.requestFocus();
                return;
            }
            if (ItemName.isEmpty()){
                editItemName.setError("This field must not be empty");
                editItemName.requestFocus();
                return;
            }
            String pattern2 = "^(-?(\\d+)?\\.\\d+)$";
            Pattern r2 = Pattern.compile(pattern2);
            Matcher m2 = r2.matcher(ItemPrice);
            if (!m2.matches()) {
                editItemPrice.setError("Price must have point/dot(.) followed by 2 decimal numbers!");
                editItemPrice.requestFocus();
                return;
            }
            String pattern = "^(\\d+(\\.\\d{0,2})?|\\.?\\d{1,2})$";
            Pattern r = Pattern.compile(pattern);
            Matcher m = r.matcher(ItemPrice);
            if (!m.matches()) {
                editItemPrice.setError("Price must be in 2 decimal places!");
                editItemPrice.requestFocus();
                return;
            }
            if (!(in.isEmpty() && ip.isEmpty() && imageUrl!=null))
            {
                progressDialog.setTitle("Uploading...");
                progressDialog.show();

                StorageReference filepath = mStorage.getReference().child("ItemImage/").child(imageUrl.getLastPathSegment());
                filepath.putFile(imageUrl).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        Task<Uri> downloadUrl=taskSnapshot.getStorage().getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                            @Override
                            public void onComplete(@NonNull Task<Uri> task) {

                                String t = task.getResult().toString();
                                if(task.isSuccessful()){
                                    DatabaseReference newPost = mRef.push();
                                    newPost.child("itemName").setValue(in);
                                    newPost.child("itemPrice").setValue(ip);
                                    newPost.child("image").setValue(task.getResult().toString());
                                    newPost.child("itemDescription").setValue(dd);
                                    newPost.child("itemCategory").setValue(ic);

                                    DatabaseReference newPost2 = mRef2.push();
                                    newPost2.child("itemName").setValue(in);
                                    newPost2.child("itemPrice").setValue(ip);
                                    newPost2.child("image").setValue(task.getResult().toString());
                                    newPost2.child("itemDescription").setValue(dd);
                                    newPost2.child("itemCategory").setValue(ic);
                                    progressDialog.dismiss();

                                    Intent intent = new Intent(AddItem.this, ProfileActivitySeller.class);
                                    startActivity(intent);
                                }else {
                                    Toast.makeText( AddItem.this,"There is something wrong.",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                });
            }
        }
    });
    }
}