package me.itslucas.foodie.activities;


import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import me.itslucas.foodie.Fragment.BuyAdapter;
import me.itslucas.foodie.R;

public class BuyHistoryActivity extends AppCompatActivity {
    private ImageView btnBack;
    private ListView mBoughtHistoryList;
    private ProgressBar mProgressBar;
    private BuyAdapter adapter;
    private List<Goods> list = new ArrayList<Goods>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);

        //绑定控件
        btnBack = findViewById(R.id.buy_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        init();
        adapter = new BuyAdapter(BuyHistoryActivity.this, R.layout.item_buy, list);
        mBoughtHistoryList = findViewById(R.id.buy_list);
        mBoughtHistoryList.setAdapter(adapter);

        //取消动画显示
        mProgressBar = findViewById(R.id.progress);
        mBoughtHistoryList.setVisibility(View.VISIBLE);
        mProgressBar.setVisibility(View.GONE);


    }
    private void init() {
        for(int i=0;i<20;i++){
            Goods good = new Goods(null, "物品名字", "属性","￥390","x1");
            list.add(good);
        }
    }
}
