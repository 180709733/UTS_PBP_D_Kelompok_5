package com.uts.salon.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity(tableName = "todos")
public class Todo {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "user_id")
    private int user_id;

    @ColumnInfo(name = "K")
    private String k;

    @ColumnInfo(name = "Date")
    private String date;
//    @ColumnInfo(name = "K")
//    private ArrayList<String> nK;

//    @ColumnInfo(name = "k1")
//    private String k1;
//
//    @ColumnInfo(name = "k1")
//    private String k2;
//
//    @ColumnInfo(name = "k1")
//    private String k3;
//
//    @ColumnInfo(name = "k1")
//    private String k4;
//
//    @ColumnInfo(name = "k1")
//    private String k5;
//
//    @ColumnInfo(name = "k1")
//    private String k6;
//
//    @ColumnInfo(name = "k1")
//    private String k7;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getK() { return k; }

    public void setK(String k) { this.k = k; }

    public String getDate() { return date; }

    public void setDate(String date) { this.date = date; }
//    public ArrayList<String> getnK() {return nK;}
//
//    public void setnK(ArrayList<String> nK) {this.nK = nK;}
}