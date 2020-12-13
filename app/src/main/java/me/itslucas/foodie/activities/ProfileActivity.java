package me.itslucas.foodie.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import me.itslucas.foodie.R;
public class ProfileActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Button b = findViewById(R.id.button);

        b.setOnClickListener((v)->{
            Intent i = new Intent(this,SearchActivity.class);
            startActivity(i);
        });
    }
}
