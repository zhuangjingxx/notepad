package com.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.WindowManager;
import android.widget.ImageView;

/**
 * Created by 13410 on 2017/4/29.
 */

public class MyImageview extends ImageView {
    private int screenWidth;//屏幕宽度

    public MyImageview(Context context) {
        super(context);
    }

    public MyImageview(Context context, AttributeSet attrs) {
        super(context, attrs);
        //获得屏幕宽度
        WindowManager wm = (WindowManager) getContext()
                .getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width=(screenWidth-10-10*2)/3;
        int height=300;
        setMeasuredDimension(width,height);
    }
}
