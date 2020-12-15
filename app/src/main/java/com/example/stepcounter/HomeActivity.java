package com.example.stepcounter;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {
    private androidx.constraintlayout.widget.ConstraintLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().hide();

        layout   = findViewById(R.id.MainLayout);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,Hub.class);
                Toast.makeText(HomeActivity.this, "Welcome to XP!", Toast.LENGTH_SHORT).show();
                startActivity(intent);

            }
        });

    }

}