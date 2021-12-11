package com.example.studigo.model;

import com.example.studigo.Constatns;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class Student {

    // because Pair has no novariables constructor, but google want it


    public String name;
    public String surname;
    public boolean sex;
    public String phone;
    public String email;
    public MyPair<String, Integer> religion;
    public MyPair<String, Integer> country;
    public MyPair<String, Integer> region;
    public MyPair<String, Integer> town;
    public MyPair<String, Integer> ethnic;
    public MyPair<String, Integer> language;
    public MyPair<Boolean, Integer> alcohol;
    public MyPair<Boolean, Integer> burn;
    public MyPair<Boolean, Integer> loud;
    public MyPair<Boolean, Integer> wakeEarly;
    public MyPair<Boolean, Integer> sleepEarly;


    public int studentId;
    /* public ArrayList<String> habits;
     public ArrayList<String> preferences;*/
    public int roomId;

    // register new student
    public Student() {

    }

    public Student(String name, String surname, boolean sex) {
        this.name = name;
        this.surname = surname;
        /*habits = new ArrayList<>();
        preferences = new ArrayList<>();*/
        studentId = Constatns.maxStudentID + 1;
        System.out.println("studentId = " + studentId);
        Constatns.maxStudentID += 1;
        FirebaseDatabase.getInstance().getReference().child("maxStudentID").setValue(Constatns.maxStudentID);
        this.sex = sex;
    }

    //load existing student
    public Student(String name, String surname, boolean sex, int studentId, int roomId/*, ArrayList<String> habits, ArrayList<String> preferences*/) {
        this.name = name;
        this.surname = surname;
        this.studentId = studentId;
       /* this.habits = habits;
        this.preferences = preferences;*/
        this.roomId = roomId;
        this.sex = sex;
    }

    //load existing student with no room
    public Student(String name, String surname, boolean sex, int studentId/*, ArrayList<String> habits, ArrayList<String> preferences*/) {
        this.name = name;
        this.surname = surname;
        this.studentId = studentId;
       /* this.habits = habits;
        this.preferences = preferences;*/
        this.sex = sex;
    }


   /* public Student(String name, String surname, boolean sex, int roomId) {
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
    }*/

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

    /*public ArrayList<String> getHabits() {
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
*/
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

    public double compatibility(Student anotherStudent){
        double compatibility = 0;
        if (religion.first.equals(anotherStudent.religion.first)){
            compatibility += religion.second + anotherStudent.religion.second;
        }
        if (country.first.equals(anotherStudent.country.first)){
            compatibility += country.second + anotherStudent.country.second;
        }
        if (region.first.equals(anotherStudent.region.first)){
            compatibility += region.second + anotherStudent.region.second;
        }
        if (town.first.equals(anotherStudent.town.first)){
            compatibility += town.second + anotherStudent.town.second;
        }
        if (ethnic.first.equals(anotherStudent.ethnic.first)){
            compatibility += ethnic.second + anotherStudent.ethnic.second;
        }
        if (language.first.equals(anotherStudent.language.first)){
            compatibility += language.second + anotherStudent.language.second;
        }
        if (alcohol.first.equals(anotherStudent.alcohol.first)){
            compatibility += alcohol.second + anotherStudent.alcohol.second;
        }
        if (burn.first.equals(anotherStudent.burn.first)){
            compatibility += burn.second + anotherStudent.burn.second;
        }
        if (loud.first.equals(anotherStudent.loud.first)){
            compatibility += loud.second + anotherStudent.loud.second;
        }

        if (wakeEarly.first.equals(anotherStudent.wakeEarly.first)){
            compatibility += wakeEarly.second + anotherStudent.wakeEarly.second;
        }

        if (sleepEarly.first.equals(anotherStudent.sleepEarly.first)){
            compatibility += sleepEarly.second + anotherStudent.sleepEarly.second;
        }

        return compatibility;
    }

    @Override
    public String toString() {
        return "Student{" +
                "\tname=" + name + "\n" +
                "\t surname=" + surname + "\n" +
                "\t sex=" + (getSex() ? "чоловік" : "жінка") + "\n" +
                "\t studentId=" + studentId + "\n" +
                /*"\t habits=" + habits + "\n" +
                "\t preferences=" + preferences + "\n" +*/
                "\t roomId=" + roomId + "\n" +
                "}\n";
    }
}
