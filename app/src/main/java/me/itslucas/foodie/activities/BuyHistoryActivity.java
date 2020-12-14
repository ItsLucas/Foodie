package me.itslucas.foodie.activities;


import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import me.itslucas.foodie.R;

public class BuyHistoryActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);
        View back = findViewById(R.id.buy_title_layout);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
