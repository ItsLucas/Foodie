package me.itslucas.foodie.activities;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;


import me.itslucas.foodie.MainActivity;
import me.itslucas.foodie.R;


public class AnimationActivity extends AppCompatActivity {
    /**
     * 入场动画布局
     */
    private LinearLayout inLayout;

    /**
     * 最终动画布局
     */
    private LinearLayout hideLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        inLayout = findViewById(R.id.text_lin);//要显示的字体
        hideLayout = findViewById(R.id.text_hide_lin);

        //动画1
        Animation animation = AnimationUtils.loadAnimation(me.itslucas.foodie.activities.AnimationActivity.this, R.anim.text_splash_position);
        //动画2
        final Animation hideLayoutAnimation = AnimationUtils.loadAnimation(me.itslucas.foodie.activities.AnimationActivity.this, R.anim.text_canvas);

        inLayout.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //第一个动画执行完后执行第二个动画就是那个字体显示那部分
                hideLayout.startAnimation(hideLayoutAnimation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        hideLayoutAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //第二个动画执行完之后跳转到登录
                Intent intent = new Intent(me.itslucas.foodie.activities.AnimationActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
