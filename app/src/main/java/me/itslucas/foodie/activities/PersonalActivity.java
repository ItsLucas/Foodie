package me.itslucas.foodie.activities;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import me.itslucas.foodie.R;
import me.itslucas.foodie.UserData;


public class PersonalActivity extends AppCompatActivity {

    private EditText input_cid,input_cname,input_email,input_birthday;
    private ImageView back;
    private Button btn_submit;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_item_personal);

        init();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void init() {
        input_cid = findViewById(R.id.input_cid);
        input_cname = findViewById(R.id.input_cname);
        input_email = findViewById(R.id.input_email);
        input_birthday = findViewById(R.id.input_birthday);
        back = findViewById(R.id.signup_back);
        btn_submit = findViewById(R.id.btn_submit);
        input_cid.setText("默认用户cid");
    }
}