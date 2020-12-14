package me.itslucas.foodie.activities;


import android.content.Context;
import android.os.Bundle;
import android.telephony.gsm.GsmCellLocation;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

import me.itslucas.foodie.Fragment.BuyAdapter;
import me.itslucas.foodie.R;
import me.itslucas.foodie.UserData;
import me.itslucas.foodie.beans.OrderInfoBean;

public class BuyHistoryActivity extends AppCompatActivity {
    private ImageView btnBack;
    private ListView mBoughtHistoryList;
    private ProgressBar mProgressBar;
    private BuyAdapter adapter;
    private List<Goods> list = new ArrayList<Goods>();
    RequestQueue rq;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);
        rq = Volley.newRequestQueue(this);
        String uri = "https://foodie.itslucas.me/vip.php?id="+ UserData.cid + "&type=orderinfo";
        StringRequest sq = new StringRequest(uri, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("Gson","Got response:"+response);
                JsonArray arr = JsonParser.parseString(response).getAsJsonArray();
                  for(JsonElement j : arr) {
                       OrderInfoBean bean1 = new Gson().fromJson(j,OrderInfoBean.class);
                       Goods good = new Goods(null, bean1.getPname(), bean1.getPname(),bean1.getDeal_price(),bean1.getFood_quantity());
                       list.add(good);
                   }adapter = new BuyAdapter(BuyHistoryActivity.this, R.layout.item_buy, list);
                mBoughtHistoryList = findViewById(R.id.buy_list);
                mBoughtHistoryList.setAdapter(adapter);btnBack = findViewById(R.id.buy_back);
                btnBack.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
                mProgressBar = findViewById(R.id.progress);
                mBoughtHistoryList.setVisibility(View.VISIBLE);
                mProgressBar.setVisibility(View.GONE);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        rq.add(sq);
        //绑定控件


        //取消动画显示



    }
}
