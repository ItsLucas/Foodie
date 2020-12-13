package me.itslucas.foodie.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import me.itslucas.foodie.MainActivity;
import me.itslucas.foodie.R;
public class LoginActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button login = (Button)findViewById(R.id.btn_login);
        TextView signupLink = findViewById(R.id.link_signup);
        login.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        });
        signupLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });
    }
}
