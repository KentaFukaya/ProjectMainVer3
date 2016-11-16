package com.example.b1014100_2.projectmainver3.quiz;

/**
 * Created by b1014169 on 2016/11/11.
 */
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class QuizX extends View {
    private final Paint paint;

    // 線の幅
    private final int strokeWidth = 90;
    // 初期 position
    private float centerX, centerY;
    private float positionX1 = getWidth()/2 - getWidth()/6, positionX2 = getWidth()/2 + getWidth()/6;
    private float positionY1 = getHeight()/2 - getWidth()/6, positionY2 = getHeight()/2 - getWidth()/6;
    // 半径
    public float radius = getWidth()/6;

    private boolean line = true;

    public QuizX(Context context, AttributeSet attrs) {
        super(context, attrs);

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(strokeWidth);
        // 線の色
        paint.setColor(Color.argb(255, 0, 0, 255));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 背景、半透明
        canvas.drawColor(Color.argb(0, 255, 255, 255));

        // Canvas 中心点
        centerX = canvas.getWidth()/2;
        centerY = canvas.getHeight()/2;
        radius = canvas.getWidth()/6;
        // 初期値の設定
        Log.d("Xanime" , "X1=" + positionX1 + ",Y1=" + positionY1 + ",X2=" + positionX2 + ",Y2=" + positionY2);
        if(line){
            line = false;
            positionX1 = centerX - radius;
            positionY1 = centerY - radius;

            positionX2 = centerX + radius;
            positionY2 = centerY - radius;
        }

        // 線の描画
        canvas.drawLine(centerX - radius, centerY - radius, positionX1, positionY1, paint);
        canvas.drawLine(centerX + radius, centerY - radius, positionX2, positionY2, paint);
    }

    public float getPositionX1() {
        return positionX1;
    }
    public float getPositionY1() {
        return positionY1;
    }
    public float getPositionX2() {
        return positionX2;
    }
    public float getPositionY2() {
        return positionY2;
    }

    public void setPosition1(float positionX, float positionY) {
        this.positionX1 = positionX;
        this.positionY1 = positionY;
    }
    public void setPosition2(float positionX, float positionY) {
        this.positionX2 = positionX;
        this.positionY2 = positionY;
    }

    public boolean getLine() {
        return line;
    }
}