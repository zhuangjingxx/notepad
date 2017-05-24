package com.activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.view.CategoryAdapter;

import java.util.ArrayList;
import java.util.List;

public class NotepadListActivity extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener{

    private String TAG = "ListViewActivity";
    private AlertDialog dialog=null;
    private int mode=0;//选择模式，0为阅读，1为编辑
    private LayoutInflater inflater;
    private boolean isDeleteMode=false;//是否处于删除模式
    private CategoryAdapter adapter;
    private Dialog alertDialog;
    private List<String> list;
    private RelativeLayout relativelayout1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_user);


        initActionBar();
        initData();

        initUI();

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(dialog!=null){
            dialog.dismiss();
        }
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    /*
         * 初始化数据

         */
    private void initData() {
        list=new ArrayList<>();
        inflater = getLayoutInflater();
        list.add("篮球");
        list.add("排球");
        list.add("网球");
        list.add("乒乓球");
        list.add("足球");
        list.add("橄榄球");
        list.add("羽毛球");
        list.add("桌球");
        list.add("保龄球");
        list.add("十");
    }

    /*
      * 初始化UI
      */
    private void initUI() {
        Button button1 = (Button) findViewById(R.id.button1);
        Button button4 = (Button) findViewById(R.id.button4);
        relativelayout1 = (RelativeLayout) findViewById(R.id.relativelayout1);
        button1.setOnClickListener(this);
        button4.setOnClickListener(this);
        ListView listView1 = (ListView) findViewById(R.id.listView1);
        adapter = new CategoryAdapter(inflater, list);
        listView1.setAdapter(adapter);
        listView1.setOnItemClickListener(this);
        listView1.setOnItemLongClickListener(this);
    }


//按钮点击事件

    @Override
    public void onClick(View arg0) {
        int id = arg0.getId();
        switch (id) {
            case R.id.button1:
                for (int i = 0; i < list.size(); i++) {
                    adapter.getIsSelectedMap().put(i, true);
                }
                adapter.notifyDataSetChanged();
                break;
            case R.id.button4:
                alertDialog = new AlertDialog.Builder(this)
                        .setTitle("确定删除？")
                        .setMessage("您确定删除所选信息？")
                        .setPositiveButton("确定",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface arg0, int arg1) {
                                        int len = list.size();
                                        for (int i = len - 1; i >= 0; i--) {
                                            Boolean value =adapter
                                                    .getIsSelectedMap().get(i);
                                            if (value) {
                                                list.remove(i);
                                                adapter.getIsSelectedMap().put(i, false);
                                            }
                                            adapter.getIsvisibleMap().put(i,View.INVISIBLE);
                                        }
                                        adapter.notifyDataSetChanged();
                                        alertDialog.cancel();
                                        relativelayout1.setVisibility(View.GONE);
                                        isDeleteMode=false;
                                    }
                                })
                        .setNegativeButton("取消",
                                new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface arg0,
                                                        int arg1) {
                                        alertDialog.cancel();
                                    }
                                }).create();
                alertDialog.show();
                break;
            default:
                break;
        }
    }


    //ListView的点击事件
    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

        if(isDeleteMode){
            CategoryAdapter.ViewHolder holder= (CategoryAdapter.ViewHolder) arg1.getTag();
            holder.checkBox.toggle();
            adapter.getIsSelectedMap().put(arg2,true);
        }else{
            showModechooseAlertDialog();
        }

    }


    //ListView的长按事件
    @Override
    public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        relativelayout1.setVisibility(View.VISIBLE);
        for(int i=0;i<list.size();i++){
            adapter.getIsvisibleMap().put(i,View.VISIBLE);
        }
        adapter.notifyDataSetChanged();
        isDeleteMode=true;
        return false;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(isDeleteMode==true){
            for(int i=0;i<list.size();i++){
                adapter.getIsvisibleMap().put(i,View.INVISIBLE);
                adapter.getIsSelectedMap().put(i,false);
            }
            relativelayout1.setVisibility(View.GONE);
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void initActionBar(){
        ActionBar actionBar=getSupportActionBar();
        actionBar.setCustomView(R.layout.useractivity_actionbar_layout);
        Button add=(Button)actionBar.getCustomView().findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(NotepadListActivity.this,MyEditText.class);
                startActivity(intent);
            }
        });
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM|ActionBar.DISPLAY_SHOW_HOME);
    }


    private void showModechooseAlertDialog(){
        dialog= new AlertDialog.Builder(this).create();
        dialog.setView(LayoutInflater.from(this).inflate(R.layout.modechoose_alertdialog_layout, null));
        dialog.show();
        dialog.getWindow().setContentView(R.layout.modechoose_alertdialog_layout);
        RadioGroup modes=(RadioGroup) dialog.findViewById(R.id.modes);
        final Button confirm=(Button) dialog.findViewById(R.id.confirm);
        Button cancel=(Button) dialog.findViewById(R.id.cancel);
        modes.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int id=radioGroup.getCheckedRadioButtonId();
                switch (id){
                    case R.id.read:
                        mode=0;
                        break;

                    case R.id.edit:
                        mode=1;
                        break;
                }

            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=null;
                if(mode==0){
                    intent=new Intent(NotepadListActivity.this,ReadActivity.class);
                    startActivity(intent);
                }else{
                    intent=new Intent(NotepadListActivity.this,MyEditText.class);
                    startActivity(intent);
                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }
}
