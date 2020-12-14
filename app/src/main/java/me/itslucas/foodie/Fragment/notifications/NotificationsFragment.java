package me.itslucas.foodie.Fragment.notifications;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import me.itslucas.foodie.R;
import me.itslucas.foodie.activities.AboutUsActivity;
import me.itslucas.foodie.activities.SettingActivity;

public class NotificationsFragment extends Fragment {

    private Toolbar mToolbar;
    private LinearLayout mBtnCart, mBtnBought, mBtnUserInfo, mBtnAbout, mBtnSetting;
    private ImageView mImageView;
    private TextView mName;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notifications, null);

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();

//        //初始化控件并设置点击事件
//        mImageView = view.findViewById(R.id.cat_avatar);
//        mName = view.findViewById(R.id.cat_title);
//
//        //获得context
//        Context context = getActivity().getApplicationContext();
//        //设置到默认头像
//        mImageView.setImageDrawable(getResources().getDrawable(R.drawable.cutecat));




        //ToolBar
        mToolbar = view.findViewById(R.id.toolbar);

        /*菜单们*/


        //历史购买按钮
        mBtnBought = view.findViewById(R.id.menu_bought);
        mBtnBought.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //个人中心按钮
        mBtnUserInfo = view.findViewById(R.id.menu_user);
        mBtnUserInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        //关于我们按钮
        mBtnAbout = view.findViewById(R.id.menu_about);
        mBtnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AboutUsActivity.class);
                startActivity(intent);
            }
        });

        //设置按钮
        mBtnSetting = view.findViewById(R.id.menu_setting);
        mBtnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //页面跳转
                Intent intent = new Intent(getActivity(), SettingActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); //注意本行的FLAG设置
                startActivity(intent);
            }
        });


        return view;
    }
}