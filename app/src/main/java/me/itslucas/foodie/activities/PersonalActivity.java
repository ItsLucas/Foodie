package me.itslucas.foodie.activities;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import me.itslucas.foodie.R;


public class PersonalActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private TextView mNameText;//显示用户名的text
    private TextView mMenu;//Toolbar上的菜单
    private View mMenuLayout, mWarnLayout;
    private ImageView mImageView;

    /**
     * 账号显示（不允许修改）
     */
    private TextView mAccount;

    /**
     * 密码和昵称
     */
    private EditText mUserNameText, mPasswordText;

    /**
     * 账号密码昵称
     */
    private String account, userName, password;
    private Integer userId;


    /**
     * 是否点击编辑按钮的标志
     */
    private boolean isEdit = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_item_personal);

        init();//初始化绑定控件们

        /*Toolbar们*/
        mToolbar.setTitleTextColor(Color.WHITE);
        mToolbar.setTitle("个人中心");//设置ToolBar的标题

        //返回按钮颜色显示不正常时,以下三行是修改回退按钮为白色的逻辑
        Drawable upArrow = ContextCompat.getDrawable(this, R.drawable.abc_ic_ab_back_material);
        upArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        mToolbar.setNavigationIcon(upArrow);//返回按钮监听事件
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        mNameText.setText(userName);

        //修改layout
        mAccount.setText(account);
        mUserNameText.setText(userName);
        mPasswordText.setText(password);

        //设置到默认头像
        mImageView.setImageDrawable(getResources().getDrawable(R.drawable.cutecat));

        /*Toolbar上的"编辑"按钮*/
        mMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    private void init() {
        mToolbar = findViewById(R.id.personal_toolbar);
        mWarnLayout = findViewById(R.id.user_info_show_layout);
        mMenu = findViewById(R.id.tv_edit);

        mMenuLayout = findViewById(R.id.user_Info_edit_layout);
        mImageView = findViewById(R.id.cat_avatar);

        mNameText = findViewById(R.id.tv_username);

        mAccount = findViewById(R.id.account);
        mUserNameText = findViewById(R.id.username);
        mPasswordText = findViewById(R.id.password);

    }
}