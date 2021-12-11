package com.example.studigo;

import static com.example.studigo.Constatns.ROOM_REFERENCE_KEY;
import static com.example.studigo.Constatns.SHARED_PREFERENCES_HAS_REGISTERED;
//import static com.example.studigo.Constatns.SHARED_PREFERENCES_STUDENT_HABITS;
import static com.example.studigo.Constatns.SHARED_PREFERENCES_STUDENT_ALCOHOL;
import static com.example.studigo.Constatns.SHARED_PREFERENCES_STUDENT_ALCOHOL_IMPORTANCE;
import static com.example.studigo.Constatns.SHARED_PREFERENCES_STUDENT_BURN;
import static com.example.studigo.Constatns.SHARED_PREFERENCES_STUDENT_BURN_IMPORTANCE;
import static com.example.studigo.Constatns.SHARED_PREFERENCES_STUDENT_COUNTRY;
import static com.example.studigo.Constatns.SHARED_PREFERENCES_STUDENT_COUNTRY_IMPORTANCE;
import static com.example.studigo.Constatns.SHARED_PREFERENCES_STUDENT_EMAIL;
import static com.example.studigo.Constatns.SHARED_PREFERENCES_STUDENT_ETHNIC;
import static com.example.studigo.Constatns.SHARED_PREFERENCES_STUDENT_ETHNIC_IMPORTANCE;
import static com.example.studigo.Constatns.SHARED_PREFERENCES_STUDENT_ID;
import static com.example.studigo.Constatns.SHARED_PREFERENCES_STUDENT_LANGUAGE;
import static com.example.studigo.Constatns.SHARED_PREFERENCES_STUDENT_LANGUAGE_IMPORTANCE;
import static com.example.studigo.Constatns.SHARED_PREFERENCES_STUDENT_LOUD;
import static com.example.studigo.Constatns.SHARED_PREFERENCES_STUDENT_LOUD_IMPORTANCE;
import static com.example.studigo.Constatns.SHARED_PREFERENCES_STUDENT_NAME;
//import static com.example.studigo.Constatns.SHARED_PREFERENCES_STUDENT_PREFERENCES;
import static com.example.studigo.Constatns.SHARED_PREFERENCES_STUDENT_PHONE;
import static com.example.studigo.Constatns.SHARED_PREFERENCES_STUDENT_REGION;
import static com.example.studigo.Constatns.SHARED_PREFERENCES_STUDENT_REGION_IMPORTANCE;
import static com.example.studigo.Constatns.SHARED_PREFERENCES_STUDENT_RELIGION;
import static com.example.studigo.Constatns.SHARED_PREFERENCES_STUDENT_RELIGION_IMPORTANCE;
import static com.example.studigo.Constatns.SHARED_PREFERENCES_STUDENT_SEX;
import static com.example.studigo.Constatns.SHARED_PREFERENCES_STUDENT_SLEEP_EARLY;
import static com.example.studigo.Constatns.SHARED_PREFERENCES_STUDENT_SLEEP_IMPORTANCE;
import static com.example.studigo.Constatns.SHARED_PREFERENCES_STUDENT_SURNAME;
import static com.example.studigo.Constatns.SHARED_PREFERENCES_STUDENT_TOWN;
import static com.example.studigo.Constatns.SHARED_PREFERENCES_STUDENT_TOWN_IMPORTANCE;
import static com.example.studigo.Constatns.SHARED_PREFERENCES_STUDENT_WAKE_EARLY;
import static com.example.studigo.Constatns.SHARED_PREFERENCES_THIS_STUDENT;
import static com.example.studigo.Constatns.STUDENTS_REFERENCE_KEY;
import static com.example.studigo.Constatns.deviceStudent;
import static com.example.studigo.Constatns.maxRoomID;
import static com.example.studigo.Constatns.maxStudentID;
import static com.example.studigo.Constatns.studentRef;
import static com.example.studigo.Constatns.roomRef;
import static com.example.studigo.Constatns.updateHappened;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.studigo.model.MyPair;
import com.example.studigo.model.Student;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    TextView pleaseTextView;
    Button changeDataButton;
    Button bestRoomButton;
    FirebaseDatabase database;


    @Override
    protected void onResume() {
        super.onResume();
        if (deviceStudent != null) {
            pleaseTextView.setText(deviceStudent.name + " " + deviceStudent.surname);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        //  deleteSharedPreferences(SHARED_PREFERENCES_THIS_STUDENT);

        database = FirebaseDatabase.getInstance();
        studentRef = database.getReference(STUDENTS_REFERENCE_KEY);

        roomRef = database.getReference(ROOM_REFERENCE_KEY);
        init();
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_THIS_STUDENT, MODE_PRIVATE);
        if (!sharedPreferences.getBoolean(SHARED_PREFERENCES_HAS_REGISTERED, false)) {
            changeDataButton.callOnClick();
        } else {
            int studentId = sharedPreferences.getInt(SHARED_PREFERENCES_STUDENT_ID, 0);
            String name = sharedPreferences.getString(SHARED_PREFERENCES_STUDENT_NAME, "");
            String surname = sharedPreferences.getString(SHARED_PREFERENCES_STUDENT_SURNAME, "");
            boolean sex = sharedPreferences.getBoolean(SHARED_PREFERENCES_STUDENT_SEX, true);
            String phone = sharedPreferences.getString(SHARED_PREFERENCES_STUDENT_PHONE, "");
            String email = sharedPreferences.getString(SHARED_PREFERENCES_STUDENT_EMAIL, "");

            MyPair<String, Integer> religion = new MyPair<>(
                    sharedPreferences.getString(SHARED_PREFERENCES_STUDENT_RELIGION, ""),
                    sharedPreferences.getInt(SHARED_PREFERENCES_STUDENT_RELIGION_IMPORTANCE, 50));
            MyPair<String, Integer> country = new MyPair<>(
                    sharedPreferences.getString(SHARED_PREFERENCES_STUDENT_COUNTRY, "Ukraine"),
                    sharedPreferences.getInt(SHARED_PREFERENCES_STUDENT_COUNTRY_IMPORTANCE, 50));
            MyPair<String, Integer> region = new MyPair<>(
                    sharedPreferences.getString(SHARED_PREFERENCES_STUDENT_REGION, ""),
                    sharedPreferences.getInt(SHARED_PREFERENCES_STUDENT_REGION_IMPORTANCE, 50));
            MyPair<String, Integer> town = new MyPair<>(
                    sharedPreferences.getString(SHARED_PREFERENCES_STUDENT_TOWN, ""),
                    sharedPreferences.getInt(SHARED_PREFERENCES_STUDENT_TOWN_IMPORTANCE, 50));
            MyPair<String, Integer> ethnic = new MyPair<>(
                    sharedPreferences.getString(SHARED_PREFERENCES_STUDENT_ETHNIC, ""),
                    sharedPreferences.getInt(SHARED_PREFERENCES_STUDENT_ETHNIC_IMPORTANCE, 50));
            MyPair<String, Integer> language = new MyPair<>(
                    sharedPreferences.getString(SHARED_PREFERENCES_STUDENT_LANGUAGE, ""),
                    sharedPreferences.getInt(SHARED_PREFERENCES_STUDENT_LANGUAGE_IMPORTANCE, 50));
            MyPair<Boolean, Integer> alcohol = new MyPair<>(
                    sharedPreferences.getBoolean(SHARED_PREFERENCES_STUDENT_ALCOHOL, false),
                    sharedPreferences.getInt(SHARED_PREFERENCES_STUDENT_ALCOHOL_IMPORTANCE, 50));
            MyPair<Boolean, Integer> burn = new MyPair<>(
                    sharedPreferences.getBoolean(SHARED_PREFERENCES_STUDENT_BURN, false),
                    sharedPreferences.getInt(SHARED_PREFERENCES_STUDENT_BURN_IMPORTANCE, 50));
            MyPair<Boolean, Integer> loud = new MyPair<>(
                    sharedPreferences.getBoolean(SHARED_PREFERENCES_STUDENT_LOUD, false),
                    sharedPreferences.getInt(SHARED_PREFERENCES_STUDENT_LOUD_IMPORTANCE, 50));
            MyPair<Boolean, Integer> wakeEarly = new MyPair<>(
                    sharedPreferences.getBoolean(SHARED_PREFERENCES_STUDENT_WAKE_EARLY, false),
                    sharedPreferences.getInt(SHARED_PREFERENCES_STUDENT_SLEEP_IMPORTANCE, 50));
            MyPair<Boolean, Integer> sleepEarly = new MyPair<>(
                    sharedPreferences.getBoolean(SHARED_PREFERENCES_STUDENT_SLEEP_EARLY, false),
                    sharedPreferences.getInt(SHARED_PREFERENCES_STUDENT_SLEEP_IMPORTANCE, 50));
//            ArrayList<String> habits = new ArrayList<>(sharedPreferences.getStringSet(SHARED_PREFERENCES_STUDENT_HABITS, new ArraySet<>()));
//            ArrayList<String> preferences = new ArrayList<>(sharedPreferences.getStringSet(SHARED_PREFERENCES_STUDENT_PREFERENCES, new ArraySet<>()));
            deviceStudent = new Student(name, surname, sex, studentId/*, habits, preferences*/);
            deviceStudent.phone = phone;
            deviceStudent.email = email;
            deviceStudent.religion = religion;
            deviceStudent.country = country;
            deviceStudent.region = region;
            deviceStudent.town = town;
            deviceStudent.ethnic = ethnic;
            deviceStudent.language = language;
            deviceStudent.alcohol = alcohol;
            deviceStudent.burn = burn;
            deviceStudent.loud = loud;
            deviceStudent.wakeEarly = wakeEarly;
            deviceStudent.sleepEarly = sleepEarly;

        }

    }

    void init() {

        //  textFromCloud = findViewById(R.id.fromCloud);
        pleaseTextView = findViewById(R.id.pleaseTextView);
//      textToCloud = findViewById(R.id.toCloud);
//      buttonFromCloud = findViewById(R.id.buttonFromCloud);
        changeDataButton = findViewById(R.id.changeDataButton);
        changeDataButton.setOnClickListener(onClickLogin);
        bestRoomButton = findViewById(R.id.bestRoomButton);
        bestRoomButton.setOnClickListener(onClickBestRoomListener);
       /* changeDataButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Room room = new Room(20, 2, "02l", 1,4);
                Student student = new Student("ba", "eb", false, room.roomId);
                room.addStudent(student);
                roomRef.child("" + room.roomId).setValue(room);
                studentRef.child(student.getStudentId() + "").setValue(student);
                return true;
            }
        });*/
        database.getReference().child("maxRoomID").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Integer t = snapshot.getValue(Integer.class);
                if (t != null) {
                    Constatns.maxRoomID = t;
                    updateHappened = true;
                }
                System.out.println("maxRoomID = " + maxRoomID);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        database.getReference().child("maxStudentID").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Integer t = snapshot.getValue(Integer.class);
                if (t != null)
                    maxStudentID = t;
                System.out.println("maxStudentID = " + maxStudentID);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    /*   ChildEventListener childEventListener = new ChildEventListener() {
           @Override
           public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
               Student std = snapshot.getValue(Student.class);
               students.add(std);
               textFromCloud.append(std != null ? std.toString() : "null");
           }

           @Override
           public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

           }

           @Override
           public void onChildRemoved(@NonNull DataSnapshot snapshot) {
               Student std = snapshot.getValue(Student.class);
               textFromCloud.setText("");
               for (int i = 0; i < students.size(); i++) {
                   Student s = students.get(i);
                   if (s.equals(std)) {
                       students.remove(s);
                       i--;
                       continue;
                   }
                   textFromCloud.append(s.toString());
               }
               //textFromCloud.append(std != null ? std.toString() : "null");
           }

           @Override
           public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

           }

           @Override
           public void onCancelled(@NonNull DatabaseError error) {

           }
       };
   */
    View.OnClickListener onClickBestRoomListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, BestRoomsActivity.class);
            startActivity(intent);
        }
    };
    View.OnClickListener onClickLogin = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        }
    };
}