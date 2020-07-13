package com.example.housemanagementsystem.ui.dashboard;

import android.database.sqlite.SQLiteDatabase;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.housemanagementsystem.tool.DBHelper;
import com.example.housemanagementsystem.tool.person;

public class DashboardViewModel extends ViewModel {
    public DBHelper dbhelper;
    public person per;
    public SQLiteDatabase sqldb;

}