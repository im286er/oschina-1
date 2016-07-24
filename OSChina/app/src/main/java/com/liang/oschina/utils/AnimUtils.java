package com.liang.oschina.utils;

import android.view.View;
import android.view.animation.Animation;

import com.nineoldandroids.animation.ObjectAnimator;

/**
 * Created by Administrator on 2016/7/6 0006.
 */
public class AnimUtils {

    public static void setReversalAnim(View view){

        ObjectAnimator oaAnimator= ObjectAnimator.ofFloat(view, "rotation", 0, 360);
        view.setPivotX(100);
        view.setPivotY(100);
        oaAnimator.setDuration(5000);
        oaAnimator.setRepeatMode(Animation.RESTART);
        oaAnimator.setRepeatCount(Animation.INFINITE);
        oaAnimator.start();

/*        RotateAnimation  rotateAnimation = new RotateAnimation(
                0,
                360,
                RotateAnimation.RELATIVE_TO_SELF,
                0.5f,Animation.RELATIVE_TO_SELF,
                0.5f);
        rotateAnimation.setDuration(3000);
        rotateAnimation.setFillAfter(true);
        rotateAnimation.setRepeatMode(Animation.REVERSE);
        rotateAnimation.setRepeatCount(Animation.INFINITE);
       view.startAnimation(rotateAnimation);*/
    }

/*    ObjectAnimator oaAnimator=ObjectAnimator.ofFloat(imageView1, "rotation", 0,360);

    //如果不指定中心点的话就是按照图标自己的中心进行旋转
    imageView1.setPivotX(100);//设置指定旋转中心点X坐标
    imageView1.setPivotY(100);//设置指定旋转中心点X坐标，注意的是这个点（100,100）是想对于view的坐标，不是屏幕的左上角的0，0位置，有了这你就可以实现和补间动画一样的效果
    oaAnimator.setDuration(5000);
    oaAnimator.start();

    //这个是以Y中心轴进行旋转
    ObjectAnimator oaY=ObjectAnimator.ofFloat(imageView1, "rotationY", 0,360);
    oaY.setDuration(5000);
    oaY.start();*/
}
