package com.example.housemanagementsystem.ui.notifications;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.housemanagementsystem.tool.DBHelper;
import com.example.housemanagementsystem.tool.person;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class NotificationsViewModel extends ViewModel {

    public DBHelper dbhelper;
    public person per;
    public SQLiteDatabase sqldb;
    public List<Map<String,String>> queryall(String data){
        sqldb = dbhelper.getReadableDatabase();
        List<Map<String,String>> a = new LinkedList<>();
        //创建游标
        Cursor mCursor = sqldb.query("info", null, "time<=?", new String[]{data}, null, null,
                null);
        //游标置顶
        mCursor.moveToFirst();
        //遍历
        if (mCursor.getCount() != 0) {
            do {
                HashMap<String,String> map = new HashMap<>();
                map.put("info_id",mCursor.getString(mCursor.getColumnIndex("info_id")));
                map.put("info_house",mCursor.getString(mCursor.getColumnIndex("info_house")));
                map.put("info_name",mCursor.getString(mCursor.getColumnIndex("info_name")));
                map.put("info_person_id",mCursor.getString(mCursor.getColumnIndex("info_person_id")));
                map.put("info_phone",mCursor.getString(mCursor.getColumnIndex("info_phone")));
                map.put("info_money",mCursor.getString(mCursor.getColumnIndex("info_money")));
                map.put("info_start",mCursor.getString(mCursor.getColumnIndex("info_start")));
                map.put("info_end",mCursor.getString(mCursor.getColumnIndex("info_end")));
                map.put("info_time",mCursor.getString(mCursor.getColumnIndex("info_time")));
                map.put("info_content",mCursor.getString(mCursor.getColumnIndex("info_content")));
                a.add(map);
            } while (mCursor.moveToNext());
        }
        return a;
    }//查询所有信息

}