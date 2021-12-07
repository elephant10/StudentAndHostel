package com.example.studigo;

import android.content.SharedPreferences;


import com.example.studigo.model.Student;
import com.google.firebase.database.DatabaseReference;

public class Constatns {
    public static final String STUDENTS_REFERENCE_KEY = "students";
    public static final String ROOM_REFERENCE_KEY = "rooms";

    public static Student deviceStudent;
    public static  int maxRoomID;
    public static  int maxStudentID;
    public static DatabaseReference studentRef;
    public static DatabaseReference roomRef;
    //public static SharedPreferences sharedPreferences;




    public static final String SHARED_PREFERENCES_THIS_STUDENT = "THIS_STUDENT";
    public static final String SHARED_PREFERENCES_HAS_REGISTERED = "HAS_REGISTERED";
    public static final String SHARED_PREFERENCES_STUDENT_ID = "STUDENT_ID";
    public static final String SHARED_PREFERENCES_STUDENT_NAME = "STUDENT_NAME";
    public static final String SHARED_PREFERENCES_STUDENT_SURNAME = "STUDENT_SURNAME";
    public static final String SHARED_PREFERENCES_STUDENT_SEX = "STUDENT_SEX";
    public static final String SHARED_PREFERENCES_STUDENT_HABITS = "STUDENT_HABITS";
    public static final String SHARED_PREFERENCES_STUDENT_PREFERENCES = "STUDENT_PREFERENCES";





}
