package com.example.housemanagementsystem.tool;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private String create_info = "create table info("
            + "info_id integer primary key autoincrement not null ,"//id
            + "info_house varchar(20) not null ,"//房号
            + "info_name varchar(20) not null ,"//租客姓名
            + "info_person_id varchar(20) not null,"//身份证
            + "info_phone varchar(20) not null,"//电话
            + "info_money varchar(100) not null,"//租金
            + "info_start varchar(100) not null,"//起始时间
            + "info_end varchar(20) not null,"//终止时间
            + "info_time datetime not null,"//交款期限
            + "info_content varchar(100) )";//备注

    /**
     *
     * @param context
     * @param name 数据库名字
     * @param factory 数据库进行查询的时候会返回一个cursor，这个cursor就是在上面的factory中产生的。
    如果有需求，可以自定义factory，这样返回的cursor就会符合自己的需求！
     * @param version 数据库版本号
     */
    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(create_info);
    }

    /**
     * 该方法会在数据库需要升级的时候调用
     *
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("ALTER TABLE student ADD COLUMN other TEXT");
    }
}
