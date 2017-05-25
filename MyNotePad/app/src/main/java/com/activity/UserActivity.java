package com.activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SyncStatusObserver;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.model.Category;
import com.service.CateGorySevice;
import com.view.CategoryAdapter;

import java.util.ArrayList;
import java.util.List;

public class UserActivity extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    private String TAG = "ListViewActivity";

    private CateGorySevice cateGorySevice;
    private LayoutInflater inflater;
    private boolean isDeleteMode=false;//是否处于删除模式
    private CategoryAdapter adapter;
    private Dialog alertDialog;
    private List<Category> categoryList;
    private List<String > list=new ArrayList<>();
    private RelativeLayout relativelayout1;
    private ListView listView1;
    private TextView txtTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_user);
        inflater=LayoutInflater.from(UserActivity.this);
        cateGorySevice=new CateGorySevice(UserActivity.this);
        initActionBar();
        initData();

        initUI();

    }


    @Override
    protected void onResume() {
        super.onResume();
        initData();
        adapter=new CategoryAdapter(inflater,list);
        listView1.setAdapter(adapter);
    }

    /*
                     * 初始化数据

                     */
    private void initData() {
       categoryList=cateGorySevice.showAllCategory();
        list.clear();
        if(categoryList!=null) {
            for (int i = 0; i < categoryList.size(); i++) {
                list.add(categoryList.get(i).getCategoryName());
            }
        }
    }

    /*
      * 初始化UI
      */
    private void initUI() {
        txtTest=(TextView)findViewById(R.id.txtTest);
        Button button1 = (Button) findViewById(R.id.button1);
        Button button4 = (Button) findViewById(R.id.button4);
        relativelayout1 = (RelativeLayout) findViewById(R.id.relativelayout1);
        button1.setOnClickListener(this);
        button4.setOnClickListener(this);
        listView1 = (ListView) findViewById(R.id.listView1);
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
                                                cateGorySevice.removeACategory(categoryList.get(i).getId());

                                                adapter.getIsSelectedMap().put(i, false);
                                            }
                                            adapter.getIsvisibleMap().put(i,View.INVISIBLE);
                                        }
                                        adapter.notifyDataSetChanged();
                                        alertDialog.cancel();
                                        relativelayout1.setVisibility(View.GONE);
                                        isDeleteMode=false;
                                        initData();
                                        adapter=new CategoryAdapter(inflater,list);
                                        listView1.setAdapter(adapter);
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
                Intent intent=new Intent(UserActivity.this,NotepadListActivity.class);
                intent.putExtra("categoryId",categoryList.get(arg2).getId());
                startActivity(intent);
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



    //增加category时显示的对话框
    private void showAlertDialog() {

        final AlertDialog dialog = new AlertDialog.Builder(this).create();
        dialog.setView(LayoutInflater.from(this).inflate(R.layout.alert_dialog, null));
        dialog.show();
        dialog.getWindow().setContentView(R.layout.alert_dialog);
        Button btnPositive = (Button) dialog.findViewById(R.id.confirm);
        Button btnNegative = (Button) dialog.findViewById(R.id.cancel);
        final TextView txtHint=(TextView) dialog.findViewById(R.id.hint);
        final EditText etContent = (EditText) dialog.findViewById(R.id.ed_content);

        btnPositive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = etContent.getText().toString();

                if (isNullEmptyBlank(str)) {
                    txtHint.setText("输入不能为空");

                } else if(!cateGorySevice.addACategory(etContent.getText().toString().trim())){
                    txtHint.setText("类别已存在");
                }
                else{
                    initData();
                    adapter=new CategoryAdapter(inflater,list);
                    listView1.setAdapter(adapter);
                    dialog.dismiss();
                }
            }
        } );

        btnNegative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    private static boolean isNullEmptyBlank(String str) {
        if (str == null || "".equals(str) || "".equals(str.trim()))
            return true;
        return false;
    }



    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK) {
            if (isDeleteMode == true) {
                for (int i = 0; i < list.size(); i++) {
                    adapter.getIsvisibleMap().put(i, View.INVISIBLE);
                    adapter.getIsSelectedMap().put(i, false);
                }
                relativelayout1.setVisibility(View.GONE);
                adapter.notifyDataSetChanged();
                isDeleteMode=false;
                return false;
            }
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
                showAlertDialog();
            }
        });
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM|ActionBar.DISPLAY_SHOW_HOME);
    }
}
