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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
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

    private Button AddItem;
    private EditText addItemName, addItemPrice;
    private CircleImageView addItemImage;
    public Uri imageUri;
    private FirebaseStorage storage;
    private StorageReference storageReference;
    public static String itemNameGlobal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        addItemName = (EditText) findViewById(R.id.addItemName);
        addItemPrice = (EditText) findViewById(R.id.addItemPrice);
        addItemImage = (CircleImageView) findViewById(R.id.addItemImage);

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        addItemImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosePicture();
            }
        });

        Button AddItem = findViewById(R.id.addButton);
        AddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addItem();
            }
        });

    }
    private void addItem() {
        String ItemName = addItemName.getText().toString().trim();
        String ItemPrice = addItemPrice.getText().toString().trim();

        if (ItemName.length()>30){
            addItemName.setError("Max item name length is 30 characters!");
            addItemName.requestFocus();
            return;
        }
        if (ItemName.isEmpty()){
            addItemName.setError("This field must not be empty");
            addItemName.requestFocus();
            return;
        }
        String pattern = "^(\\d+(\\.\\d{0,2})?|\\.?\\d{1,2})$";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(ItemPrice);
        if (!m.matches()) {
            addItemPrice.setError("Price must be in 2 decimal places!");
            addItemPrice.requestFocus();
            return;
        }

        String itemName = addItemName.getText().toString();
        itemNameGlobal = addItemName.getText().toString();

        Item item = new Item(ItemName, ItemPrice, ItemName);
        FirebaseDatabase.getInstance().getReference("items")
                .child(getInstance().getCurrentUser().getUid()).child(itemName)
                .setValue(item).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {



                if(task.isSuccessful()){
                    Toast.makeText(AddItem.this,"Item Added",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(AddItem.this,ListingPageSeller.class);
                    startActivity(i);

                }else{
                    Toast.makeText( AddItem.this,"There is something wrong.",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void choosePicture(){
        Intent intent = new Intent();
        intent.setType("image/");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 1);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 && resultCode==RESULT_OK && data!=null && data.getData()!=null){
            imageUri = data.getData();
            addItemImage.setImageURI(imageUri);
            uploadPicture();
        }
    }

    private void uploadPicture(){

        final ProgressDialog pd = new ProgressDialog(this);
        pd.setTitle("Uploading Image...");
        pd.show();

        String itemName = addItemName.getText().toString();
        final String randomKey = UUID.randomUUID().toString();
        StorageReference riversRef = storageReference.child("ItemImage/").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(itemName);

        riversRef.putFile(imageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        pd.dismiss();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                        double progressPercent = (100.00 * taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                        pd.setMessage("Percentage: "+(int)progressPercent+"%");
                    }
                });

    }
}