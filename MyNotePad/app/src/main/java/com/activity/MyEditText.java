package com.activity;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

public class MyEditText extends AppCompatActivity {

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

    }

    //与返回箭头配合使用
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}
