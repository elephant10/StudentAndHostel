package com.example.studigo;

import static com.example.studigo.Constatns.*;

import static java.security.AccessController.getContext;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;

import com.example.studigo.model.Room;
import com.example.studigo.model.Student;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

public class BestRoomsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    volatile ArrayList<Room> rooms = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_best_rooms);
        getSupportActionBar().hide();

        Query roomsQuery = roomRef/*.equalTo(deviceStudent.sex, "sex")*/;

        roomsQuery.addChildEventListener(new ChildEventListener() {


            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Room room = snapshot.getValue(Room.class);
                if (room != null && room.roomSex == deviceStudent.sex && room.totalAmountOfBeds - room.getStudentIds().size() > 0) {
                    System.out.println("room = " + room);
                    rooms.add(room);
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        recyclerView = findViewById(R.id.recyclerView);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(getDrawable(R.drawable.separator));
        recyclerView.addItemDecoration(dividerItemDecoration);


        new Thread(new Runnable() {
            @Override
            public void run() {
                Date d = new Date();
                while (rooms.isEmpty()) {
                    System.out.println("nullll");
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                rooms.sort(new Comparator<Room>() {
                    @Override
                    public int compare(Room thisRoom, Room anotherRoom) {
                        final double[] thisScore = {0};
                        final double[] anotherScore = {0};

                        //because data asynk
                        int numberOfStudentsToCheck = thisRoom.getStudentIds().size() + anotherRoom.getStudentIds().size();
                        final int[] checkedStudentAmount = {0};
                        for (int i = 0; i < thisRoom.getStudentIds().size(); i++) {
                            studentRef.child(thisRoom.getStudentIds().get(i) + "").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<DataSnapshot> task) {
                                    Student studentFromRoom = task.getResult().getValue(Student.class);
                                    if (studentFromRoom != null) {
                                        runOnUiThread(() -> {
                                            thisScore[0] += deviceStudent.compatibility(studentFromRoom);
                                            checkedStudentAmount[0]++;
                                        });
                                    }
                                }
                            });
                        }
                        thisScore[0] /= thisRoom.getStudentIds().size();
                        for (int i = 0; i < anotherRoom.getStudentIds().size(); i++) {
                            studentRef.child(anotherRoom.getStudentIds().get(i) + "").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<DataSnapshot> task) {
                                    Student studentFromRoom = task.getResult().getValue(Student.class);
                                    if (studentFromRoom != null) {
                                        runOnUiThread(() -> {
                                            anotherScore[0] += deviceStudent.compatibility(studentFromRoom);
                                            checkedStudentAmount[0]++;
                                        });
                                    }
                                }
                            });
                        }
                        anotherScore[0] /= anotherRoom.getStudentIds().size();
                        while (checkedStudentAmount[0] < numberOfStudentsToCheck) {

                        }

                        System.out.println("thisScore[0] = " + thisScore[0]);
                        System.out.println("anotherScore[0] = " + anotherScore[0]);
                        return thisScore[0] >= anotherScore[0] ? 1 : -1;
                    }
                });
                runOnUiThread(() -> {
                    Adapter adapter = new Adapter(BestRoomsActivity.this, rooms);
                    recyclerView.setAdapter(adapter);
                });
                System.out.println("new Date().getTime() - d.getTime() = " + (new Date().getTime() - d.getTime()));
            }
        }).start();


    }

    public void returnPressed(View view) {
        onBackPressed();
    }
}