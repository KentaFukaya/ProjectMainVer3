package com.example.b1014100_2.projectmainver3.quiz;

/**
 * Created by b1014169 on 2016/11/11.
 */
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class QuizO extends View{
    private final Paint paint;
    private RectF rect;

    // Arcの幅
    private final int strokeWidth = 90;
    // Animation 開始地点をセット
    private static final int AngleTarget = 90;
    // 初期 Angle
    private float angle = 0;
    // 半径
    private float radius;

    public QuizO(Context context, AttributeSet attrs) {
        super(context, attrs);

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(strokeWidth);
        // Arcの色
        paint.setColor(Color.argb(255, 255, 0, 0));
        // Arcの範囲
        rect = new RectF();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 背景、半透明
        canvas.drawColor(Color.argb(0, 255, 255, 255));

        // Canvas 中心点
        float x = canvas.getWidth()/2;
        float y = canvas.getHeight()/2;
        radius = canvas.getWidth()/6;

        // 円弧の領域設定
        rect.set(x - radius, y - radius, x + radius, y + radius);

        // 円弧の描画
        canvas.drawArc(rect, AngleTarget, angle, false, paint);
    }

    // AnimationAへ現在のangleを返す
    public float getAngle() {
        return angle;
    }

    // AnimationAから新しいangleが設定される
    public void setAngle(float angle) {
        this.angle = angle;
    }

}