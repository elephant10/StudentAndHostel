package com.example.studigo;

import android.content.SharedPreferences;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Spinner;


import com.example.studigo.model.Student;
import com.google.firebase.database.DatabaseReference;

public class Constatns {
    public static final String STUDENTS_REFERENCE_KEY = "students";
    public static final String ROOM_REFERENCE_KEY = "rooms";

    public static Student deviceStudent;
    public static int maxRoomID;
    public static int maxStudentID;
    public static DatabaseReference studentRef;
    public static DatabaseReference roomRef;
    //public static SharedPreferences sharedPreferences;


    public static final String SHARED_PREFERENCES_THIS_STUDENT = "THIS_STUDENT";
    public static final String SHARED_PREFERENCES_HAS_REGISTERED = "HAS_REGISTERED";
    public static final String SHARED_PREFERENCES_STUDENT_ID = "STUDENT_ID";
    public static final String SHARED_PREFERENCES_STUDENT_NAME = "STUDENT_NAME";
    public static final String SHARED_PREFERENCES_STUDENT_SURNAME = "STUDENT_SURNAME";
    public static final String SHARED_PREFERENCES_STUDENT_SEX = "STUDENT_SEX";


    public static final String SHARED_PREFERENCES_STUDENT_PHONE = "STUDENT_PHONE ";
    public static final String SHARED_PREFERENCES_STUDENT_EMAIL = "STUDENT_EMAIL ";
    public static final String SHARED_PREFERENCES_STUDENT_RELIGION = "STUDENT_RELIGION ";
    public static final String SHARED_PREFERENCES_STUDENT_RELIGION_IMPORTANCE = "STUDENT_RELIGION_IMPORTANCE ";
    public static final String SHARED_PREFERENCES_STUDENT_COUNTRY = "STUDENT_COUNTRY ";
    public static final String SHARED_PREFERENCES_STUDENT_COUNTRY_IMPORTANCE = "STUDENT_COUNTRY_IMPORTANCE ";
    public static final String SHARED_PREFERENCES_STUDENT_REGION = "STUDENT_REGION ";
    public static final String SHARED_PREFERENCES_STUDENT_REGION_IMPORTANCE = "STUDENT_REGION_IMPORTANCE ";
    public static final String SHARED_PREFERENCES_STUDENT_TOWN = "STUDENT_TOWN ";
    public static final String SHARED_PREFERENCES_STUDENT_TOWN_IMPORTANCE = "STUDENT_TOWN_IMPORTANCE ";
    public static final String SHARED_PREFERENCES_STUDENT_ETHNIC = "STUDENT_ETHNIC ";
    public static final String SHARED_PREFERENCES_STUDENT_ETHNIC_IMPORTANCE = "STUDENT_ETHNIC_IMPORTANCE ";
    public static final String SHARED_PREFERENCES_STUDENT_LANGUAGE = "STUDENT_LANGUAGE ";
    public static final String SHARED_PREFERENCES_STUDENT_LANGUAGE_IMPORTANCE = "STUDENT_LANGUAGE_IMPORTANCE ";


    public static final String SHARED_PREFERENCES_STUDENT_ALCOHOL = "STUDENT_ALCOHOL ";
    public static final String SHARED_PREFERENCES_STUDENT_ALCOHOL_IMPORTANCE = "STUDENT_ALCOHOL_IMPORTANCE ";

    public static final String SHARED_PREFERENCES_STUDENT_BURN = "STUDENT_BURN ";
    public static final String SHARED_PREFERENCES_STUDENT_BURN_IMPORTANCE = "STUDENT_BURN_IMPORTANCE ";

    public static final String SHARED_PREFERENCES_STUDENT_LOUD = "STUDENT_LOUD ";
    public static final String SHARED_PREFERENCES_STUDENT_LOUD_IMPORTANCE = "STUDENT_LOUD_IMPORTANCE ";

    public static final String SHARED_PREFERENCES_STUDENT_WAKE_EARLY = "STUDENT_WAKE_EARLY ";

    public static final String SHARED_PREFERENCES_STUDENT_WAKE_LATE = "STUDENT_WAKE_LATE ";

    public static final String SHARED_PREFERENCES_STUDENT_SLEEP_EARLY = "STUDENT_SLEEP_EARLY";
    public static final String SHARED_PREFERENCES_STUDENT_SLEEP_LATE = "STUDENT_SLEEP_LATE ";

    public static final String SHARED_PREFERENCES_STUDENT_SLEEP_IMPORTANCE = "STUDENT_SLEEP_IMPORTANCE ";


    public static final String SHARED_PREFERENCES_STUDENT_HABITS = "STUDENT_HABITS";
    public static final String SHARED_PREFERENCES_STUDENT_PREFERENCES = "STUDENT_PREFERENCES";


}
