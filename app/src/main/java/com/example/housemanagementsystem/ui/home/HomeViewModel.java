package com.example.housemanagementsystem.ui.home;

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

public class HomeViewModel extends ViewModel {

    public DBHelper dbhelper;
    public person per;
    public SQLiteDatabase sqldb;
    public List<Map<String,String>> queryall(){
        sqldb = dbhelper.getReadableDatabase();
        List<Map<String,String>> a = new LinkedList<>();
                //创建游标
                Cursor mCursor = sqldb.query("info", null, null, null, null, null,
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
    public List<Map<String,String>> query(String name,String house){
        sqldb = dbhelper.getReadableDatabase();
        List<Map<String,String>> b = new LinkedList<>();
        String where="";
        LinkedList<String> arg=new LinkedList<>();
        System.out.println(name+"asd"+house);
        if (!name.isEmpty()){
            where+="info_name=?";
            arg.add(name);
        }
        if (!house.isEmpty()) {
            if (!where.isEmpty())
                where+=" and ";
            where += "info_house=?";
            arg.add(house);
        }
        String[] a=new String[arg.size()];
        for (int i=0;i<arg.size();i++){
            a[i]=arg.get(i);
        }
        //创建游标

        if (where.equals(""))
            where=null;
        System.out.println("Ddf"+where+a.toString());
        Cursor mCursor = sqldb.query("info", null,where, a, null, null,
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
                b.add(map);
            } while (mCursor.moveToNext());
        }
        return b;
    }//查询所有信息
}