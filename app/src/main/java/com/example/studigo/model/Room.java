package com.example.studigo.model;

import static com.google.firebase.database.FirebaseDatabase.getInstance;

import android.util.Log;

import com.example.studigo.Constatns;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class Room {

    public int hostel;
    public int stage;
    public boolean roomSex;
    public String roomNumber;
    public int roomId;
    public int totalAmountOfBeds = 4;
    ArrayList<Integer> studentIds;
    double stateOfPerfection;

    public ArrayList<Integer> getStudentIds() {
        return studentIds;
    }

    public void addStudent(int studentId) {
        studentIds.add(studentId);
    }

    public void addStudent(Student student) {
        studentIds.add(student.studentId);
    }

    public Room() {

    }

    public Room(int hostel, int stage, String roomNumber, int totalAmountOfBeds, boolean roomSex) {
        this.hostel = hostel;
        this.stage = stage;
        this.roomNumber = roomNumber;
        roomId = Constatns.maxRoomID + 1;
        Constatns.maxRoomID += 1;
        FirebaseDatabase.getInstance().getReference().child("maxRoomID").setValue(Constatns.maxRoomID);
        this.totalAmountOfBeds = totalAmountOfBeds;
        studentIds = new ArrayList<>();
        stateOfPerfection = 10;
        this.roomSex = roomSex;
    }

    public Room(int hostel, int stage, String roomNumber, int roomId, int totalAmountOfBeds, boolean roomSex) {
        this.hostel = hostel;
        this.stage = stage;
        this.roomNumber = roomNumber;
        this.roomId = roomId;
        studentIds = new ArrayList<>();
        this.totalAmountOfBeds = totalAmountOfBeds;
        this.roomSex = roomSex;
    }

    public String location() {
        return "" + hostel + "-" + stage + "-" + roomNumber;
    }

    @Override
    public String toString() {
        return "Room{" +
                "\troomsex=" + roomSex + "\n" +
                "\thostel=" + hostel + "\n" +
                ",\t stage=" + stage + "\n" +
                ",\t roomNumber='" + roomNumber + "\n" +
                ",\t roomId=" + roomId + "\n" +
                ",\t totalAmountOfBeds=" + totalAmountOfBeds + "\n" +
                ",\t studentIds=" + studentIds + "\n" +
                ",\t stateOfPerfection=" + stateOfPerfection + "\n" +
                "\t" + '}' + "\n";
    }
}
