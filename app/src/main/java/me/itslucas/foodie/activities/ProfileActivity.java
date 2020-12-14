package me.itslucas.foodie.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import me.itslucas.foodie.R;
import me.itslucas.foodie.activities.fzr.SelectProductActivity;
import me.itslucas.foodie.activities.fzr.childpage.ProductDetail;

public class ProfileActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Button b = findViewById(R.id.button);
        b.setText("开商品选购页（测试用");
        b.setOnClickListener((v)->{
            Intent i = new Intent(this, SelectProductActivity.class);
            startActivity(i);
        });
    }
}
