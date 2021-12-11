package com.example.studigo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.studigo.model.Room;

public class RoomActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roon);
        Room room = (Room) getIntent().getSerializableExtra("room");
        TextView textView = findViewById(R.id.textView2);
        textView.setText(room.roomNumber);
    }
}