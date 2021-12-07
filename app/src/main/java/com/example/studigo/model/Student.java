package com.example.studigo.model;

import android.util.Log;

import com.example.studigo.Constatns;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class Student {


    public String name;
    public String surname;
    public boolean sex;


    public int studentId;
    public ArrayList<String> habits;
    public ArrayList<String> preferences;
    public int roomId;

    // register new student
    public Student() {

    }

    public Student(String name, String surname, boolean sex) {
        this.name = name;
        this.surname = surname;
        habits = new ArrayList<>();
        preferences = new ArrayList<>();
        studentId = Constatns.maxStudentID + 1;
        System.out.println("studentId = " + studentId);
        Constatns.maxStudentID += 1;
        FirebaseDatabase.getInstance().getReference().child("maxStudentID").setValue(Constatns.maxStudentID);
        this.sex = sex;
    }

    //load existing student
    public Student(String name, String surname, boolean sex, int studentId, int roomId, ArrayList<String> habits, ArrayList<String> preferences) {
        this.name = name;
        this.surname = surname;
        this.studentId = studentId;
        this.habits = habits;
        this.preferences = preferences;
        this.roomId = roomId;
        this.sex = sex;
    }
    //load existing student with no room
    public Student(String name, String surname, boolean sex, int studentId, ArrayList<String> habits, ArrayList<String> preferences) {
        this.name = name;
        this.surname = surname;
        this.studentId = studentId;
        this.habits = habits;
        this.preferences = preferences;
        this.sex = sex;
    }


    public Student(String name, String surname, boolean sex, int roomId) {
        this.name = name;
        this.surname = surname;
        this.roomId = roomId;
        habits = new ArrayList<>();
        preferences = new ArrayList<>();
        studentId = Constatns.maxStudentID + 1;
        System.out.println("studentId = " + studentId);
        Constatns.maxStudentID += 1;
        FirebaseDatabase.getInstance().getReference().child("maxStudentID").setValue(Constatns.maxStudentID);
        this.sex = sex;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public void changeName(String name) {
        this.name = name;
    }

    public void changeSurname(String surname) {
        this.surname = surname;
    }

    public int getStudentId() {
        return studentId;
    }

    public ArrayList<String> getHabits() {
        return habits;
    }

    public void setHabits(ArrayList<String> habits) {
        this.habits = habits;
    }

    public void addHabit(String habit) {
        habits.add(habit);
    }

    public ArrayList<String> getPreferences() {
        return preferences;
    }

    public void setPreferences(ArrayList<String> preferences) {
        this.preferences = preferences;
    }

    public void addPreference(String preference) {
        habits.add(preference);
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public boolean getSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return getStudentId() == student.getStudentId();
    }


    @Override
    public int hashCode() {
        return Objects.hash(getStudentId());
    }

    @Override
    public String toString() {
        return "Student{" +
                "\tname=" + name + "\n" +
                "\t surname=" + surname + "\n" +
                "\t sex=" + (getSex() ? "чоловік" : "жінка") + "\n" +
                "\t studentId=" + studentId + "\n" +
                "\t habits=" + habits + "\n" +
                "\t preferences=" + preferences + "\n" +
                "\t roomId=" + roomId + "\n" +
                "}\n";
    }
}
