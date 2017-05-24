package com.service;

import android.content.ContentValues;
import android.content.Context;
import android.icu.util.ULocale;

import com.dao.CateGoryDao;
import com.model.Category;
import com.model.NotePad;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by 13410 on 2017/4/23.
 * category的业务逻辑类，包含了对category各种操作的方法
 */

public class CateGorySevice {

    private CateGoryDao cateGoryDao;
    private Context context;

    public CateGorySevice(Context context){
        this.context=context;
        cateGoryDao=new CateGoryDao(context);
    }


    public List<Category> showAllCategory(){
        return cateGoryDao.getAllCategoryWithoutNotePad();
    }

    public boolean addACategory(String name){
        List<Category> temp=showAllCategory();
        Iterator<Category> it=temp.iterator();
        while(it.hasNext()){
            if(it.next().getCategoryName().equals(name)){
                return false;
            }
        }
        Category category=new Category();
        category.setCategoryName(name);
        cateGoryDao.add(category);
        return true;
    }

    public void removeACategory(Integer id){
        cateGoryDao.remove(id);
    }


    public void updateACategory(Integer id,String name){
        Category category=new Category();
        category.setCategoryName(name);
        category.setId(id);
        cateGoryDao.update(category);
    }

    public void removeBatchCategory(List<Integer> ids){
            cateGoryDao.removeBatch(ids);
    }
}
