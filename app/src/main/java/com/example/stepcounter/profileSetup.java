package com.example.stepcounter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class profileSetup extends AppCompatActivity {
    private EditText NameET,AgeET,WeightET;
    private Button submitButton;
    private FirebaseFirestore firebaseFireStore;
    String UserID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_setup);
        NameET = findViewById(R.id.editTextName);
        AgeET = findViewById(R.id.editTextAge);
        WeightET = findViewById(R.id.editTextWeight);
        submitButton = findViewById(R.id.buttonSubmit);
        UserID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        firebaseFireStore = FirebaseFirestore.getInstance();





        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(NameET.getText().toString()) || !TextUtils.isEmpty(AgeET.getText().toString()) || !TextUtils.isEmpty(WeightET.getText().toString())){
                    CollectionReference dbUserInfo = firebaseFireStore.collection("userInfo");
                    userInfo userInfo1 = new userInfo(
                            UserID,
                            NameET.getText().toString(),
                            Integer.parseInt(AgeET.getText().toString()),
                            Double.parseDouble(WeightET.getText().toString())
                    );
                    dbUserInfo.add(userInfo1).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Toast.makeText(profileSetup.this,"User Added",Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(profileSetup.this, personal.class);
                            startActivity(intent);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(profileSetup.this,e.getMessage(),Toast.LENGTH_LONG).show();
                        }
                    });



                }
            }
        });

    }
}