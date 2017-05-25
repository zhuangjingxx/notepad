package com.service;

import android.content.Context;
import android.provider.Settings;
import android.util.Log;

import com.dao.NotePadDao;
import com.model.Category;
import com.model.NotePad;

import java.util.Date;
import java.util.List;

/**
 * Created by 13410 on 2017/5/24.
 */

public class NotepadSevice {
    private NotePadDao notePadDao;
    public NotepadSevice(Context context){
        notePadDao=new NotePadDao(context);
    }

    public void addNotepad(int categoryId,String content){
        notePadDao.add(getNotepad(categoryId,content),content);
    }


    public void remove(int notepadId){
        Log.i("test","3");
        notePadDao.remove(notepadId);
    }

    public void update(NotePad notePad,String content){
        int len=20<content.length()?20:content.length();
        notePad.setFirstLineOfContent(content.substring(0,len));
        notePadDao.update(notePad,content);
    }

    public List<NotePad> showAllNotepadOfCategory(int categoryId){
        List<NotePad> temp=null;
        temp=notePadDao.getAll(categoryId);
        return temp;
    }


    public String getContent(int notepadId){
        return notePadDao.getNotePadContent(notepadId);
    }
    private NotePad getNotepad(int categoryId,String content){

        NotePad notePad=new NotePad();
        notePad.setCategoryId(categoryId);
        notePad.setBuildTime(new Date(System.currentTimeMillis()));
        int len=20<content.length()?20:content.length();
        notePad.setFirstLineOfContent(content.substring(0,len));
        return notePad;
    }



}
