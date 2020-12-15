package me.itslucas.foodie.activities;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import me.itslucas.foodie.R;
import me.itslucas.foodie.UserData;
import me.itslucas.foodie.beans.MessageBean;
import me.itslucas.foodie.beans.UserInfoBean;


public class PersonalActivity extends AppCompatActivity {

    private EditText input_cid,input_cname,input_email,input_birthday;
    private ImageView back;
    private Button btn_submit;
    RequestQueue rq;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_item_personal);

        init();

        rq = Volley.newRequestQueue(this);
        String url = "https://foodie.itslucas.me/vip.php?id=" + UserData.cid + "&type=userinfo";
        StringRequest req = new StringRequest(url, response -> {
            UserInfoBean info = new Gson().fromJson(response,UserInfoBean.class);
            input_cid.setText(info.getCid());
            input_cid.setEnabled(false);
            input_cname.setText(info.getCname());
            input_email.setText(info.getEmail());
            input_birthday.setText(info.getPasswd());

        },error -> Log.e("VOLLEY",error.toString()));
        rq.add(req);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://foodie.itslucas.me/vip.php?submit=true&id=" + UserData.cid + "&cname="+
                        input_cname.getText().toString() + "&email=" + input_email.getText().toString() +
                        "&password="+input_birthday.getText().toString();
                StringRequest req = new StringRequest(url, response -> {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), new Gson().fromJson(response, MessageBean.class).getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    });
                },error -> Log.e("VOLLEY",error.toString()));
                rq.add(req);
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