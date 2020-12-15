package com.example.stepcounter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class personal extends AppCompatActivity {
    private static final String TAG = "TAG";
    private TextView usernameTV,ageTV, weightTV;

    private FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);
        usernameTV = findViewById(R.id.personal_name_text);
        ageTV = findViewById(R.id.textViewAge);

        db = FirebaseFirestore.getInstance();
        db.collection("userInfo").whereEqualTo("user",FirebaseAuth.getInstance().getCurrentUser().getUid()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for(QueryDocumentSnapshot doc : task.getResult()){
                        usernameTV.setText(doc.getString("name"));
                        ageTV.setText(String.valueOf(doc.get("age")));
                       // weightTV.setText(String.valueOf(doc.getString("weight")));

                    }
                }else{
                    Log.d(TAG,"error",task.getException());
                }

            }
        });


    }

    public void profileSetup(View view) {

        Intent intent = new Intent(personal.this, profileSetup.class);
        startActivity(intent);
    }

    public void Goback(View view) {
        Intent intent = new Intent(personal.this, MainActivity.class);
        startActivity(intent);
    }
}