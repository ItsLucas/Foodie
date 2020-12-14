package me.itslucas.foodie.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

import java.util.Random;

import me.itslucas.foodie.R;
import me.itslucas.foodie.beans.MessageBean;

public class RegisterActivity extends AppCompatActivity{
    private EditText _mobileText;
    private EditText _passwordText;
    private EditText _vertifyText;
    private Button _signupButton;
    private TextView _loginLink;
    private ImageView _back;
    private RequestQueue rq;
    private void init() {
        _mobileText = findViewById(R.id.input_mobile);
        _passwordText = findViewById(R.id.input_password);
        _vertifyText = findViewById(R.id.input_verify);
        _signupButton = findViewById(R.id.btn_submit);
        _loginLink = findViewById(R.id.link_login);
        _back = findViewById(R.id.signup_back);
    }
    public String getRandomString(int length){
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<length;i++){
            int number=random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
        rq = Volley.newRequestQueue(this);
        //登录按钮点击事件
        _signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cid = getRandomString(8);
                String username = _mobileText.getText().toString();
                String password = _passwordText.getText().toString();
                String phone = "12312311123";
                String wxid = "testwx";
                String email = "example@example.com";
                String url = "https://foodie.itslucas.me/register.php?cid=" + cid + "&username=" + username +"&password=" + password + "&email=" +email + "&phone=" + phone;
                StringRequest request = new StringRequest(url, response -> {
                    MessageBean msg = new Gson().fromJson(response,MessageBean.class);
                    if(msg.getMsg().equalsIgnoreCase("Success")) {

                        new Thread(() -> {
                            try {
                                EMClient.getInstance().createAccount(username,password);
                            } catch (HyphenateException e) {
                                e.printStackTrace();
                            }
                        }).start();
                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(),msg.getMsg(),Toast.LENGTH_SHORT).show();
                        finish();
                        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                    }
                    else {
                        Toast.makeText(getApplicationContext(),msg.getMsg(),Toast.LENGTH_SHORT).show();
                    }
                }, error -> Log.e("VOLLEY", error.toString()));
                rq.add(request);

            }
        });
        //返回按钮点击事件
        _back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //登录按钮链接
        _loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Finish the registration screen and return to the Login activity
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });

    }
}

