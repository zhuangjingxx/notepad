package com.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.dao.CateGoryDao;
import com.dao.NotePadDao;
import com.model.Category;
import com.model.NotePad;
import com.util.DateUtil;
import com.util.LogUtil;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnTest=(Button)findViewById(R.id.btnTest);
        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotePadDao notePadDao=new NotePadDao(MainActivity.this);
                CateGoryDao cateGoryDao=new CateGoryDao(MainActivity.this);
                Category category=cateGoryDao.get(1);
                LogUtil.out(category.getCategoryName());
                List<NotePad> temp=category.getNotePads();
                if(temp!=null){
                    Iterator<NotePad> it=temp.iterator();
                    while(it.hasNext()){
                        LogUtil.out(it.next().getFirstLineOfContent());
                    }
                }



            }
        });
    }
}
