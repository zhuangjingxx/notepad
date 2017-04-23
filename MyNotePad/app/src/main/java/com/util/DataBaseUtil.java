package com.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 13410 on 2017/4/23.
 */

public class DataBaseUtil  {

    public static  final String DB_NAME="notepads";
    public static  final String NOTEPADS_ID="id",CATEGORIES_ID="id";
    public static final String NOTEPADS_FIRSTLINEOFCONTENT="firstLineOfContent";
    public static final String NOTEPADS_CONTENT="content";
    public static final String NOTEPADS_CATEGORYID="categoryId";
    public static  final String NOTEPADS_TIME="time";
    public static  final String CATEGORIES_CATEGORYNAME="categoryName";
    public static  final String TABLE_NOTEPADS="notepads";
    public static  final String TABLE_CATEGORIES="categories";
    public static SQLiteDatabase getDataBase(Context context){
        return new MySQliteOpenHelper(context,DB_NAME,null,1).getWritableDatabase();
    }

}


class MySQliteOpenHelper extends  SQLiteOpenHelper{
    private static  final String CREATE_TABLE_NOTEPADS="CREATE TABLE notepads(" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "firstLineOfContent  VARCHAR(50)," +
            "time DATE," +
            "content TEXT," +
            "categoryId INT," +
            "FOREIGN KEY (categoryId) REFERENCES categries (id)" +
            ");";

    private static  final  String CREATE_TABLE_CATEGORY="CREATE TABLE categories(" +
            "id  INTEGER PRIMARY KEY AUTOINCREMENT," +
            "categoryName VARCHAR(15)" +
            ");";
    private Context context;
    public MySQliteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_CATEGORY);
        sqLiteDatabase.execSQL(CREATE_TABLE_NOTEPADS);
        LogUtil.out("数据库创建成功");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

}