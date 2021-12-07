package com.example.studigo;

import static com.example.studigo.Constatns.*;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Toast;
import android.os.Vibrator;

import com.example.studigo.model.Student;

import java.util.ArrayList;
import java.util.Arrays;

public class LoginActivity extends AppCompatActivity {

    EditText nameview;
    EditText surnameview;
    Button registrationButton;
    RadioGroup sexGroup;
    RadioButton maleRadio, femaleRadio;
    EditText editTextPhone;
    EditText editTextEmail;

    Spinner religionSpinner;
    SeekBar seekBarReligion;

    EditText countryEditText;
    SeekBar seekBarCountry;

    EditText regionEditText;
    SeekBar seekBarRegion;

    EditText townEditText;
    SeekBar seekBarTown;

    EditText ethnicEditText;
    SeekBar seekBarEthnic;

    EditText languageEditText;
    SeekBar seekBarLanguage;

    CheckBox alcoholBox;
    SeekBar seekBarAlcohol;

    CheckBox burnBox;
    SeekBar seekBarBurn;

    CheckBox loudBox;
    SeekBar seekBarLoud;

    RadioButton wakeEarlyRadio;
    RadioButton wakeLateRadio;

    RadioButton sleepEarlyRadio;
    RadioButton sleepLateRadio;

    SeekBar seekBarSleep;

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

        editTextPhone = findViewById(R.id.editTextPhone);
        editTextEmail = findViewById(R.id.editTextEmail);

        religionSpinner = findViewById(R.id.religionSpinner);
        seekBarReligion = findViewById(R.id.seekBarReligion);

        countryEditText = findViewById(R.id.countryEditText);
        seekBarCountry = findViewById(R.id.seekBarCountry);

        regionEditText = findViewById(R.id.regionEditText);
        seekBarRegion = findViewById(R.id.seekBarRegion);

        townEditText = findViewById(R.id.townEditText);
        seekBarTown = findViewById(R.id.seekBarTown);

        ethnicEditText = findViewById(R.id.ethnicEditText);
        seekBarEthnic = findViewById(R.id.seekBarEthnic);

        languageEditText = findViewById(R.id.languageEditText);
        seekBarLanguage = findViewById(R.id.seekBarLanguage);

        alcoholBox = findViewById(R.id.alcoholBox);
        seekBarAlcohol = findViewById(R.id.seekBarAlcohol);

        burnBox = findViewById(R.id.burnBox);
        seekBarBurn = findViewById(R.id.seekBarBurn);

        loudBox = findViewById(R.id.loudBox);
        seekBarLoud = findViewById(R.id.seekBarLoud);

        wakeEarlyRadio = findViewById(R.id.wakeEarlyRadio);
        wakeLateRadio = findViewById(R.id.wakeLateRadio);

        sleepEarlyRadio = findViewById(R.id.sleepEarlyRadio);
        sleepLateRadio = findViewById(R.id.sleepLateRadio);
        seekBarSleep = findViewById(R.id.seekBarSleep);

        if (hasRegistered) {
            int studentId = sharedPreferences.getInt(SHARED_PREFERENCES_STUDENT_ID, 0);
            String name = sharedPreferences.getString(SHARED_PREFERENCES_STUDENT_NAME, "");
            String surname = sharedPreferences.getString(SHARED_PREFERENCES_STUDENT_SURNAME, "");
            boolean sex = sharedPreferences.getBoolean(SHARED_PREFERENCES_STUDENT_SEX, true);
            String phone = sharedPreferences.getString(SHARED_PREFERENCES_STUDENT_PHONE, "");
            String email = sharedPreferences.getString(SHARED_PREFERENCES_STUDENT_EMAIL, "");
            ;
            Pair<String, Integer> religion = new Pair<>(
                    sharedPreferences.getString(SHARED_PREFERENCES_STUDENT_RELIGION, ""),
                    sharedPreferences.getInt(SHARED_PREFERENCES_STUDENT_RELIGION_IMPORTANCE, 50));
            Pair<String, Integer> country = new Pair<>(
                    sharedPreferences.getString(SHARED_PREFERENCES_STUDENT_COUNTRY, "Ukraine"),
                    sharedPreferences.getInt(SHARED_PREFERENCES_STUDENT_COUNTRY_IMPORTANCE, 50));
            Pair<String, Integer> region = new Pair<>(
                    sharedPreferences.getString(SHARED_PREFERENCES_STUDENT_REGION, ""),
                    sharedPreferences.getInt(SHARED_PREFERENCES_STUDENT_REGION_IMPORTANCE, 50));
            Pair<String, Integer> town = new Pair<>(
                    sharedPreferences.getString(SHARED_PREFERENCES_STUDENT_TOWN, ""),
                    sharedPreferences.getInt(SHARED_PREFERENCES_STUDENT_TOWN_IMPORTANCE, 50));
            Pair<String, Integer> ethnic = new Pair<>(
                    sharedPreferences.getString(SHARED_PREFERENCES_STUDENT_ETHNIC, ""),
                    sharedPreferences.getInt(SHARED_PREFERENCES_STUDENT_ETHNIC_IMPORTANCE, 50));
            Pair<String, Integer> language = new Pair<>(
                    sharedPreferences.getString(SHARED_PREFERENCES_STUDENT_LANGUAGE, ""),
                    sharedPreferences.getInt(SHARED_PREFERENCES_STUDENT_LANGUAGE_IMPORTANCE, 50));
            Pair<Boolean, Integer> alcohol = new Pair<>(
                    sharedPreferences.getBoolean(SHARED_PREFERENCES_STUDENT_ALCOHOL, false),
                    sharedPreferences.getInt(SHARED_PREFERENCES_STUDENT_ALCOHOL_IMPORTANCE, 50));
            Pair<Boolean, Integer> burn = new Pair<>(
                    sharedPreferences.getBoolean(SHARED_PREFERENCES_STUDENT_BURN, false),
                    sharedPreferences.getInt(SHARED_PREFERENCES_STUDENT_BURN_IMPORTANCE, 50));
            Pair<Boolean, Integer> loud = new Pair<>(
                    sharedPreferences.getBoolean(SHARED_PREFERENCES_STUDENT_LOUD, false),
                    sharedPreferences.getInt(SHARED_PREFERENCES_STUDENT_LOUD_IMPORTANCE, 50));
            Pair<Boolean, Integer> wakeEarly = new Pair<>(
                    sharedPreferences.getBoolean(SHARED_PREFERENCES_STUDENT_WAKE_EARLY, false),
                    sharedPreferences.getInt(SHARED_PREFERENCES_STUDENT_SLEEP_IMPORTANCE, 50));
            Pair<Boolean, Integer> sleepEarly = new Pair<>(
                    sharedPreferences.getBoolean(SHARED_PREFERENCES_STUDENT_SLEEP_EARLY, false),
                    sharedPreferences.getInt(SHARED_PREFERENCES_STUDENT_SLEEP_IMPORTANCE, 50));
//            ArrayList<String> habits = new ArrayList<>(sharedPreferences.getStringSet(SHARED_PREFERENCES_STUDENT_HABITS, new ArraySet<>()));
//            ArrayList<String> preferences = new ArrayList<>(sharedPreferences.getStringSet(SHARED_PREFERENCES_STUDENT_PREFERENCES, new ArraySet<>()));
            student = new Student(name, surname, sex, studentId/*, habits, preferences*/);
            student.phone = phone;
            student.email = email;
            student.religion = religion;
            student.country = country;
            student.region = region;
            student.town = town;
            student.ethnic = ethnic;
            student.language = language;
            student.alcohol = alcohol;
            student.burn = burn;
            student.loud = loud;
            student.wakeEarly = wakeEarly;
            student.sleepEarly = sleepEarly;


            surnameview.setText(surname);
            nameview.setText(name);
            if (sex) {
                maleRadio.toggle();
            } else {
                femaleRadio.toggle();
            }
            editTextPhone.setText(phone);
            editTextEmail.setText(email);

            ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.religions)));
            religionSpinner.setSelection(arrayList.indexOf(religion.first));
            seekBarReligion.setProgress(religion.second);
            townEditText.setText(town.first);
            seekBarTown.setProgress(town.second);
            ethnicEditText.setText(ethnic.first);
            seekBarEthnic.setProgress(ethnic.second);
            languageEditText.setText(language.first);
            seekBarLanguage.setProgress(language.second);
            alcoholBox.setChecked(alcohol.first);
            seekBarAlcohol.setProgress(alcohol.second);
            burnBox.setChecked(burn.first);
            seekBarBurn.setProgress(burn.second);
            loudBox.setChecked(loud.first);
            seekBarLoud.setProgress(loud.second);
            if (wakeEarly.first) {
                wakeEarlyRadio.toggle();
            } else {
                wakeLateRadio.toggle();
            }
            if (sleepEarly.first) {
                sleepEarlyRadio.toggle();
            } else {
                sleepLateRadio.toggle();
            }

        }
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

    }

    @Override
    public void onBackPressed() {
        if (nameview.getText().toString().equals("") || surnameview.getText().toString().equals("")
                || (!maleRadio.isChecked() && !femaleRadio.isChecked()) || editTextPhone.getText().toString().equals("")
                || editTextEmail.getText().toString().equals("")) {
            Toast.makeText(this, "Введіть основні поля", Toast.LENGTH_SHORT).show();
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
                /*editor.putStringSet(SHARED_PREFERENCES_STUDENT_HABITS, new HashSet<>(student.habits));
                editor.putStringSet(SHARED_PREFERENCES_STUDENT_PREFERENCES, new HashSet<>(student.preferences));*/
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