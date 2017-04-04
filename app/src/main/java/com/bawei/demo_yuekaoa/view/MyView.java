package com.bawei.demo_yuekaoa.view;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.bawei.demo_yuekaoa.R;
import com.bawei.demo_yuekaoa.SecondActivity;

/**
 * 作    者：云凯文
 * 时    间：2017/4/4
 * 描    述：
 * 修改时间：
 */

public class MyView extends View {
    private int width;
    private int height;
    private int maxradius;//大圆半径
    private int minradius;//小圆半径
    private Paint mPaint;//画笔
    private int maxcolor;//大圆颜色
    private int mincolor;//小圆颜色
    private int textcolor;
    private String text;

    public MyView(Context context) {
        this(context, null);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        //获取自定义属性
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyView);
        maxradius = (int) typedArray.getDimension(R.styleable.MyView_maxradius, 100);
        minradius = (int) typedArray.getDimension(R.styleable.MyView_minradius, 50);
        maxcolor = typedArray.getColor(R.styleable.MyView_maxcolor, Color.YELLOW);
        mincolor = typedArray.getColor(R.styleable.MyView_mincolor, Color.WHITE);
        textcolor = typedArray.getColor(R.styleable.MyView_textcolor, Color.BLACK);
        text = typedArray.getString(R.styleable.MyView_text);
        //创建画笔
        mPaint = new Paint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getWidth() / 2;
        height = getHeight() / 2;
        setMeasuredDimension(maxradius * 2, maxradius * 2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //设置外圆画笔的颜色
        mPaint.setColor(maxcolor);
        canvas.drawCircle(width, height, maxradius, mPaint);
        //设置内圆画笔的颜色
        mPaint.setColor(mincolor);
        canvas.drawCircle(width, height, minradius, mPaint);
        //设置字体的颜色
        mPaint.setColor(textcolor);
        mPaint.setTextSize(20);
        Rect rect = new Rect();
        mPaint.getTextBounds(text, 0, text.length(), rect);
        int textWidth = rect.width();
        int textHeight = rect.height();
        canvas.drawText(text, width - textWidth / 2, height + textHeight / 2, mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //记录按下时的坐标
                float downX = event.getX();
                float downY = event.getY();
                //判断按下的位置
                isDown(downX, downY);
                break;
        }
        return true;
    }

    private void isDown(float downX, float downY) {
        //根据勾股定理获取点击位置
        float absX = Math.abs(width - downX);
        float absY = Math.abs(height - downY);
        double sqrt = Math.sqrt(absX * absX + absY * absY);
        //吐司
        if (sqrt < minradius) {
            Toast.makeText(getContext(), "在小圆内", Toast.LENGTH_SHORT).show();
        } else if (sqrt > minradius && sqrt < maxradius) {
            Toast.makeText(getContext(), "在圆环内", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getContext(), SecondActivity.class);
            getContext().startActivity(intent);
        } else {
            Toast.makeText(getContext(), "在圆环外", Toast.LENGTH_SHORT).show();
        }

    }
}
