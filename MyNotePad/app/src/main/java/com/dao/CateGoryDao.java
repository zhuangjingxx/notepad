package com.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.text.style.TtsSpan;

import com.model.Category;
import com.model.NotePad;
import com.util.DataBaseUtil;
import com.util.LogUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by 13410 on 2017/4/23.
 */

public class CateGoryDao {

    private SQLiteDatabase database;
    private Context context;

    public CateGoryDao(Context context){
        this.context=context;
        database=DataBaseUtil.getDataBase(context);
    }

    public void add(Category category){
        ContentValues contentValues=new ContentValues();
        contentValues.put(DataBaseUtil.CATEGORIES_CATEGORYNAME,category.getCategoryName());
        database.insert(DataBaseUtil.TABLE_CATEGORIES,null,contentValues);
        LogUtil.out("插入成功");
    }

    public void remove(Integer id){
        NotePadDao notePadDao=new NotePadDao(context);
        notePadDao.removeNotePadOfCategory(id);
        database.delete(DataBaseUtil.TABLE_CATEGORIES, DataBaseUtil.CATEGORIES_ID +"=?", new String[]{id.toString()});
        LogUtil.out("删除成功");
    }

    public void removeBatch(List<Integer> ids){
        Iterator<Integer> it=ids.iterator();
        String [] whereArgs=new String[ids.size()];
        int i=0;
        StringBuffer condition=new StringBuffer();
        condition.append("(");
        NotePadDao notePadDao=new NotePadDao(context);
        while(it.hasNext()){
            Integer id=it.next();
            notePadDao.removeNotePadOfCategory(id);
            condition.append("? ,");
            whereArgs[i++]=id.toString();
        }
        condition.delete(condition.length()-1,condition.length());
        condition.append(")");

        database.delete(DataBaseUtil.TABLE_CATEGORIES,DataBaseUtil.CATEGORIES_ID+"in "+condition,
              whereArgs  );
    }

    public Category get(Integer id){
        Category category=null;
        Cursor cursor=database.query(DataBaseUtil.TABLE_CATEGORIES,null,DataBaseUtil.CATEGORIES_ID+"=?",
                new String[]{id.toString()},null,null,null);

        if(cursor.moveToFirst()){
            category=new Category();

            category.setId(id);
            category.setCategoryName(cursor.getString(cursor.getColumnIndex(DataBaseUtil.CATEGORIES_CATEGORYNAME)));
            NotePadDao notePadDao=new NotePadDao(context);
            List<NotePad> temp=notePadDao.getAll(id);
            category.setNotePads(temp);
        }

        return category;
    }


    public void update(Category category){
        ContentValues contentValues =new ContentValues();
        contentValues.put(DataBaseUtil.CATEGORIES_CATEGORYNAME,category.getCategoryName());
        database.update(DataBaseUtil.TABLE_CATEGORIES,contentValues,
                DataBaseUtil.CATEGORIES_ID+"=?",new String[]{category.getId().toString()});
    }

    public List<Category> getAllCategoryWithoutNotePad(){
        List<Category> categories=null;
        Cursor cursor=database.query(DataBaseUtil.TABLE_CATEGORIES,null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            categories=new ArrayList<>();
            do{
                Category category=new Category();
                category.setCategoryName(cursor.getString(cursor.getColumnIndex(DataBaseUtil.CATEGORIES_CATEGORYNAME)));
                category.setId(cursor.getInt(cursor.getColumnIndex(DataBaseUtil.CATEGORIES_ID)));
                categories.add(category);
            }while(cursor.moveToNext());
        }
        return categories;
    }



}
