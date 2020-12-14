package me.itslucas.foodie.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;

import me.itslucas.foodie.MainActivity;
import me.itslucas.foodie.R;
import me.itslucas.foodie.activities.fzr.SearchActivity;
import me.itslucas.foodie.activities.fzr.SelectProductActivity;
import me.itslucas.foodie.beans.MessageBean;

public class LoginActivity extends AppCompatActivity{

    private RequestQueue rq;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        rq = Volley.newRequestQueue(this);

        Button login = (Button)findViewById(R.id.btn_login);
        TextView signupLink = findViewById(R.id.link_signup);
        TextView username = findViewById(R.id.input_mobile);
        TextView password = findViewById(R.id.input_password);
        login.setOnClickListener(v -> {
            String u = username.getText().toString();
            String p = password.getText().toString();
            String url = "https://foodie.itslucas.me/authenticate.php?username=" + u + "&password=" + p;
            StringRequest request = new StringRequest(url, response -> {
                MessageBean msg = new Gson().fromJson(response,MessageBean.class);
                if(msg.getMsg().equalsIgnoreCase("Success")) {

                    new Thread(() -> EMClient.getInstance().login(u, p, new EMCallBack() {
                        @Override
                        public void onSuccess() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(),"IM Server Logged In",Toast.LENGTH_SHORT).show();
                                }
                            });

                        }

                        @Override
                        public void onError(int code, String error) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(),"IM Server Login Failed",Toast.LENGTH_SHORT).show();
                                }
                            });
                        }

                        @Override
                        public void onProgress(int progress, String status) {

                        }
                    })).start();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getApplicationContext(),msg.getMsg(),Toast.LENGTH_SHORT).show();
                }
            }, error -> Log.e("VOLLEY", error.toString()));
            rq.add(request);
        });
        signupLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });
    }
}
