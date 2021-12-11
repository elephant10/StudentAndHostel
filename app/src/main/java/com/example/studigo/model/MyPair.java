package com.example.studigo.model;

public class MyPair<E, V> {
    public E first;
    public V second;

    E getFirst() {
        return first;
    }

    public MyPair(E key, V value) {
        this.first = key;
        this.second = value;
    }

    MyPair() {

    }

    V getSecond() {
        return second;
    }
}
