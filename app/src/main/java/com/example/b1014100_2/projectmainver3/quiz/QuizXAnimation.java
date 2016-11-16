package com.example.b1014100_2.projectmainver3.quiz;

/**
 * Created by b1014169 on 2016/11/12.
 */
import android.graphics.Point;
import android.view.animation.Animation;
import android.view.animation.Transformation;

public class QuizXAnimation extends Animation {

    private QuizX quiz_X;

    private float oldX1;
    private float oldY1;

    private float newX1;
    private float newY1;

    private float oldX2;
    private float oldY2;

    private float newX2;
    private float newY2;

    public QuizXAnimation(QuizX quiz_X, float positionX1, float positionY1, float positionX2, float positionY2){
        oldX1 = quiz_X.getPositionX1();
        oldY1 = quiz_X.getPositionY1();
        newX1 = positionX1;
        newY1 = positionY1;

        oldX2 = quiz_X.getPositionX2();
        oldY2 = quiz_X.getPositionY2();
        newX2 = positionX2;
        newY2 = positionY2;
        this.quiz_X = quiz_X;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation transformation) {
        if(interpolatedTime < 0.5) {
            float positionX1 = oldX1 + ((newX1 - oldX1) * interpolatedTime) * 2;
            float positionY1 = oldY1 + ((newY1 - oldY1) * interpolatedTime) * 2;
            quiz_X.setPosition1(positionX1, positionY1);
        }
        if(0.5 <= interpolatedTime){
            float positionX2 = oldX2 + ((newX2 - oldX2) * (interpolatedTime - (float)0.5) * 2);
            float positionY2 = oldY2 + ((newY2 - oldY2) * (interpolatedTime - (float)0.5) * 2);
            quiz_X.setPosition2(positionX2, positionY2);
        }
        quiz_X.requestLayout();
    }
}
