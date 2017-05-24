package com.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);


        linearLayout =(LinearLayout)findViewById(R.id.activity_main);

        initAnimtion();
    }


    private void initAnimtion() {
        AnimationSet set = new AnimationSet(false);//动画集

        AlphaAnimation ap = new AlphaAnimation(0.9f, 1.0f);//透明度
        ap.setDuration(2000);//执行的时间
        set.addAnimation(ap);
        set.setStartOffset(1000);//延迟2秒开始动画
        set.setFillAfter(true);//保持最后的效果
        linearLayout.startAnimation(set);//开启动画

        set.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            //动画结束后的跳转
            @Override
            public void onAnimationEnd(Animation animation) {
                Intent intent = new Intent(MainActivity.this, UserActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }


}
