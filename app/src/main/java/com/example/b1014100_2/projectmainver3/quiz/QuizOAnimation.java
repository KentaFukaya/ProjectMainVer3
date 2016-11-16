package com.example.b1014100_2.projectmainver3.quiz;

/**
 * Created by b1014169 on 2016/11/11.
 */
import android.view.animation.Animation;
import android.view.animation.Transformation;

public class QuizOAnimation extends Animation {

    private QuizO quiz_O;

    // アニメーション角度
    private float oldAngle;
    private float newAngle;

    public QuizOAnimation(QuizO quiz_O, int newAngle) {
        this.oldAngle = quiz_O.getAngle();
        this.newAngle = newAngle;
        this.quiz_O = quiz_O;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation transformation) {
        float angle = oldAngle + ((newAngle - oldAngle) * interpolatedTime);

        quiz_O.setAngle(angle);
        quiz_O.requestLayout();
    }
}
