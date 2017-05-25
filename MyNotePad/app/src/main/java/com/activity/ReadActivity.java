package com.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class ReadActivity extends AppCompatActivity {

    private TextView txtContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);
        Intent intent=getIntent();

        //让actionbar显示返回箭头
        ActionBar mActionBar=getSupportActionBar();
        mActionBar.setHomeButtonEnabled(true);
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setTitle("");
        Resources resources = this.getResources();
        Drawable drawable = resources.getDrawable(R.drawable.timg5);
        mActionBar.setBackgroundDrawable(drawable);

        txtContent=(TextView) findViewById(R.id.txtContent);
        txtContent.setMovementMethod(new ScrollingMovementMethod());
        txtContent.setText(intent.getStringExtra(NotepadListActivity.DATA_KEY));
    }
}
