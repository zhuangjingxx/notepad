package com.activity;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.EditText;

public class MyEditText extends AppCompatActivity {

    public static  int RESULT_CODE=2;
    public static  String  DATA_KEY="data";
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_edit_text);
        editText=(EditText) findViewById(R.id.edit);
        editText.setFocusable(true);

        //让actionbar显示返回箭头
        ActionBar mActionBar=getSupportActionBar();
        mActionBar.setHomeButtonEnabled(true);
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setTitle("");
        Resources resources = this.getResources();
        Drawable drawable = resources.getDrawable(R.drawable.timg5);
        mActionBar.setBackgroundDrawable(drawable);

        Intent intent=getIntent();
        if(intent!=null){
            editText.setText(intent.getStringExtra(NotepadListActivity.DATA_KEY));

        }
    }

    //与返回箭头配合使用
    @Override
    public boolean onSupportNavigateUp() {

        finish();
        return super.onSupportNavigateUp();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){

            finish();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void finish() {
        Intent intent=new Intent();
        intent.putExtra(DATA_KEY,editText.getText().toString());
        setResult(RESULT_CODE,intent);
        super.finish();
    }
}
