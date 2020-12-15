package com.example.stepcounter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;


public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private TextView stepCounter;
    private SensorManager manager;
    private Sensor sensor;
    private boolean sensorPresent;
    int stepCount=0;
    Button friendsButton, personalButton, logoutButton, counterButton;
    private FirebaseFirestore firebaseFireStore;



    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        friendsButton = findViewById(R.id.buttonFriends);
        personalButton = findViewById(R.id.buttonPersonal);
        logoutButton = findViewById(R.id.buttonLogout);
        counterButton = findViewById(R.id.buttonCounter);



        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(MainActivity.this, Hub.class)); //Go back to home page
                finish();
            }
        });

        personalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, personal.class)); //Go back to home page
                finish();
            }
        });






        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACTIVITY_RECOGNITION) == PackageManager.PERMISSION_DENIED){
            //ask for permission
            requestPermissions(new String[]{Manifest.permission.ACTIVITY_RECOGNITION}, 0);
        }

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        stepCounter = (TextView) findViewById(R.id.counter);
        manager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if(manager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER) != null)
        {
            sensor = manager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
            sensorPresent = true;
        }else
        {
            stepCounter.setText("Sensor not present");
            sensorPresent = false;
        }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        firebaseFireStore = FirebaseFirestore.getInstance();
        if(sensorPresent) {
            stepCount = (int) sensorEvent.values[0];
            stepCounter.setText(String.valueOf(stepCount));
            CollectionReference dbCounter = firebaseFireStore.collection("counter");
            Score score = new Score(
                    FirebaseAuth.getInstance().getCurrentUser().getUid(),
                    stepCount
            );
            dbCounter.add(score);


        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorPresent = false;
/*        if(manager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER) != null)
        {
            manager.unregisterListener(this, sensor);
        }*/
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(manager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER) != null)
        {
            manager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_UI);
        }
    }

    public void friendsList(View view) {

    }
}