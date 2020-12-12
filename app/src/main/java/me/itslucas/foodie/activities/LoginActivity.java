package me.itslucas.foodie.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import me.itslucas.foodie.R;
public class LoginActivity extends AppCompatActivity{
    EditText userID =findViewById(R.id.act_home_et_phone);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
}
