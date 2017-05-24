package com.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.model.Category;
import com.model.NotePad;
import com.util.DataBaseUtil;
import com.util.DateUtil;
import com.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 13410 on 2017/4/23.
 */

public class NotePadDao {
    private SQLiteDatabase database;
    public NotePadDao(Context context){
        database= DataBaseUtil.getDataBase(context);
    }


    //传入一个notePad对象和notePad的内容content,向数据库中插入一条notepad记录
    public void add(NotePad notePad,String content){
        ContentValues contentValues=new ContentValues();
        contentValues.put(DataBaseUtil.NOTEPADS_CONTENT,content);
        contentValues.put(DataBaseUtil.NOTEPADS_TIME, DateUtil.DateToString(notePad.getBuildTime()));
        contentValues.put(DataBaseUtil.NOTEPADS_FIRSTLINEOFCONTENT,notePad.getFirstLineOfContent());
        contentValues.put(DataBaseUtil.NOTEPADS_CATEGORYID,notePad.getCategoryId());
        database.insert(DataBaseUtil.TABLE_NOTEPADS,null,contentValues);
        LogUtil.out("加入一条notepad成功");
    }

    //传入一个notePad的id,从数据库中删除一条notePad记录
    public void remove(Integer id){
        database.delete(DataBaseUtil.TABLE_NOTEPADS,DataBaseUtil.NOTEPADS_ID+"=?",new String[]{id.toString()});
        LogUtil.out("删除成功");
    }


    //传入一个category的id ,从数据库中删除这一类的notePad
    public void removeNotePadOfCategory(Integer id){
        database.delete(DataBaseUtil.TABLE_NOTEPADS,DataBaseUtil.CATEGORIES_ID+"=?",
                new String[]{id.toString()});
        LogUtil.out("删除成功");
    }

    //传入一个notePad对象和notePad的内容content,从数据库中更新一条notePad记录
    public void update(NotePad notePad,String content){
        ContentValues contentValues=new ContentValues();
        contentValues.put(DataBaseUtil.NOTEPADS_CONTENT,content);
        contentValues.put(DataBaseUtil.NOTEPADS_TIME, DateUtil.DateToString(notePad.getBuildTime()));
        contentValues.put(DataBaseUtil.NOTEPADS_FIRSTLINEOFCONTENT,notePad.getFirstLineOfContent());
        contentValues.put(DataBaseUtil.NOTEPADS_CATEGORYID,notePad.getCategoryId());
        database.update(DataBaseUtil.TABLE_NOTEPADS,contentValues,DataBaseUtil.NOTEPADS_ID+"=?",new String[]{notePad.getId().toString()});
        LogUtil.out("更新成功");
    }


    //传入一个notePad的id,从数据库中得到一个notePad对象
    public NotePad getNotePad(Integer id){
        NotePad res=null;
        Cursor cursor=database.query(DataBaseUtil.TABLE_NOTEPADS,null,DataBaseUtil.NOTEPADS_ID+"=?",
                new String[]{id.toString()},null,null,null);
        if(cursor.moveToFirst()) {
            res=new NotePad();
            res.setId(id);
            res.setBuildTime(DateUtil.stringToDate(cursor.getString(cursor.getColumnIndex(DataBaseUtil.NOTEPADS_TIME))));
            res.setCategoryId(cursor.getInt(cursor.getColumnIndex(DataBaseUtil.NOTEPADS_CATEGORYID)));
            res.setFirstLineOfContent(cursor.getString(cursor.getColumnIndex(DataBaseUtil.NOTEPADS_FIRSTLINEOFCONTENT)));
        }
        return res;
    }


    //传入一个category 的id，得到一类的notePad并以list来封装
    public List<NotePad> getAll(Integer categoryId){
        List<NotePad> notePads=null;
        Cursor cursor=database.query(DataBaseUtil.TABLE_NOTEPADS,null,
                DataBaseUtil.CATEGORIES_ID+"=?",new String[]{categoryId.toString()},null,null,null);
        if(cursor.moveToFirst()){
            notePads=new ArrayList<>();
            do{
                NotePad temp=new NotePad();
                temp.setId(cursor.getInt(cursor.getColumnIndex(DataBaseUtil.NOTEPADS_ID)));
                temp.setBuildTime(DateUtil.stringToDate(cursor.getString(cursor.getColumnIndex(DataBaseUtil.NOTEPADS_TIME))));
                temp.setCategoryId(cursor.getInt(cursor.getColumnIndex(DataBaseUtil.NOTEPADS_CATEGORYID)));
                temp.setFirstLineOfContent(cursor.getString(cursor.getColumnIndex(DataBaseUtil.NOTEPADS_FIRSTLINEOFCONTENT)));
                notePads.add(temp);
            }while(cursor.moveToNext());
        }
        return notePads;
    }


    //传入一个notepad的id，得到该notepad的内容content
    public String getNotePadContent(Integer id){
        String res=null;
        Cursor cursor=database.query(DataBaseUtil.TABLE_NOTEPADS,null,DataBaseUtil.NOTEPADS_ID+"=?",
                new String[]{id.toString()},null,null,null);
        if(cursor.moveToFirst()){
            res=cursor.getString(cursor.getColumnIndex(DataBaseUtil.NOTEPADS_CONTENT));
        }
        return res;
    }
}
