package com.example.studigo;

import static com.example.studigo.Constatns.*;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.ArraySet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.os.Vibrator;

import com.example.studigo.model.Student;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class LoginActivity extends AppCompatActivity {

    EditText nameview;
    EditText surnameview;
    Button registrationButton;
    RadioGroup sexGroup;
    RadioButton maleRadio, femaleRadio;
    volatile Vibrator vibrator;
    boolean hasRegistered = false;
    SharedPreferences sharedPreferences;
    Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_THIS_STUDENT, MODE_PRIVATE);
        hasRegistered = sharedPreferences.getBoolean(SHARED_PREFERENCES_HAS_REGISTERED, false);

        init();
    }

    void init() {
        registrationButton = findViewById(R.id.registrationButton);
        surnameview = findViewById(R.id.surnameView);
        nameview = findViewById(R.id.nameView);
        registrationButton.setOnClickListener(registrationListener);
        sexGroup = findViewById(R.id.sexGroup);
        maleRadio = findViewById(R.id.radioMale);
        femaleRadio = findViewById(R.id.radioFemale);
        if (hasRegistered) {
            int studentId = sharedPreferences.getInt(SHARED_PREFERENCES_STUDENT_ID, 0);
            String name = sharedPreferences.getString(SHARED_PREFERENCES_STUDENT_NAME, "");
            String surname = sharedPreferences.getString(SHARED_PREFERENCES_STUDENT_SURNAME, "");
            boolean sex = sharedPreferences.getBoolean(SHARED_PREFERENCES_STUDENT_SEX, true);
            ArrayList<String> habits = new ArrayList<>(sharedPreferences.getStringSet(SHARED_PREFERENCES_STUDENT_HABITS, new ArraySet<>()));
            ArrayList<String> preferences = new ArrayList<>(sharedPreferences.getStringSet(SHARED_PREFERENCES_STUDENT_PREFERENCES, new ArraySet<>()));
            student = new Student(name, surname, sex, studentId, habits, preferences);

            surnameview.setText(surname);
            nameview.setText(name);
            if (sex) {
                maleRadio.toggle();
            } else {
                femaleRadio.toggle();
            }


        }
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

    }

    @Override
    public void onBackPressed() {
        if (nameview.getText().toString().equals("") || surnameview.getText().toString().equals("")
                || (!maleRadio.isChecked() && !femaleRadio.isChecked())) {
            Toast.makeText(this, "Введіть усі поля", Toast.LENGTH_SHORT).show();
        } else {
            super.onBackPressed();
        }
    }

    View.OnClickListener registrationListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            if (!nameview.getText().toString().equals("") && !surnameview.getText().toString().equals("")
                    && (maleRadio.isChecked() || femaleRadio.isChecked())) {

                if (hasRegistered) {
                    System.out.println("student = " + student);
                    student.name = nameview.getText().toString();
                    student.surname = surnameview.getText().toString();
                    student.sex = maleRadio.isChecked();
                    studentRef.child(student.getStudentId() + "").setValue(student);
//                    studentRef.push().setValue(student);
                } else {
                    student = new Student(nameview.getText().toString(), surnameview.getText().toString(), maleRadio.isChecked());
                    System.out.println("student = " + student);
                    while (studentRef == null) {
                        System.out.println("\"expr\" = " + "expr");
                    }
                    studentRef.
                            child(student.getStudentId() + "").
                            setValue(student);
                }
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt(SHARED_PREFERENCES_STUDENT_ID, student.studentId);
                editor.putString(SHARED_PREFERENCES_STUDENT_NAME, student.name);
                editor.putString(SHARED_PREFERENCES_STUDENT_SURNAME, student.surname);
                editor.putBoolean(SHARED_PREFERENCES_STUDENT_SEX, student.sex);
                editor.putStringSet(SHARED_PREFERENCES_STUDENT_HABITS, new HashSet<>(student.habits));
                editor.putStringSet(SHARED_PREFERENCES_STUDENT_PREFERENCES, new HashSet<>(student.preferences));
                editor.putBoolean(SHARED_PREFERENCES_HAS_REGISTERED, true);
                editor.apply();
                deviceStudent = student;
                Toast.makeText(LoginActivity.this, "користувача додано", Toast.LENGTH_SHORT).show();
                onBackPressed();
            } else {
                Toast.makeText(LoginActivity.this, "не залишайте пусті поля", Toast.LENGTH_SHORT).show();
                vibrator.vibrate(new long[]{50, 200, 50, 400}, 0);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        vibrator.cancel();
                    }
                }).start();
            }
        }
    };
}