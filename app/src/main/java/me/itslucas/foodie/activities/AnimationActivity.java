package me.itslucas.foodie.activities;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;


import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMOptions;

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
        initIM();
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
                Intent intent = new Intent(me.itslucas.foodie.activities.AnimationActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
    protected void initIM() {
        EMOptions options = new EMOptions();
// 默认添加好友时，是不需要验证的，改成需要验证
        options.setAcceptInvitationAlways(false);
// 是否自动将消息附件上传到环信服务器，默认为True是使用环信服务器上传下载，如果设为 false，需要开发者自己处理附件消息的上传和下载
        options.setAutoTransferMessageAttachments(true);
// 是否自动下载附件类消息的缩略图等，默认为 true 这里和上边这个参数相关联
        options.setAutoDownloadThumbnail(true);
        options.setAutoLogin(false);
//初始化
        EMClient.getInstance().init(getApplicationContext(), options);
//在做打包混淆时，关闭debug模式，避免消耗不必要的资源
        EMClient.getInstance().setDebugMode(true);
    }
}
