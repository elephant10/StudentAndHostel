package com.example.studigo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class BestRoomsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_best_rooms);
        getSupportActionBar().hide();
    }
}