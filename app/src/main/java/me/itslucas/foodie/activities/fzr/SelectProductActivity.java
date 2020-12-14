package me.itslucas.foodie.activities.fzr;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.itslucas.foodie.R;
import me.itslucas.foodie.activities.fzr.childpage.ProductGridViewAdapter;
import me.itslucas.foodie.beans.ProductsBean;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import me.itslucas.foodie.activities.fzr.childpage.fzr_constant;

public class SelectProductActivity extends AppCompatActivity {

    ImageButton ibBack;
    ImageButton ibMenu;
    GridView gv;
    ProgressBar pb;
    ProductGridViewAdapter pgva;
    ArrayList<String> nameList = new ArrayList<String>();
    ArrayList<String> priceList = new ArrayList<String>();
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    pgva.notifyDataSetChanged();
                    pb.setVisibility(View.GONE);
                    break;
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_product);


        ActionBar actionBar = getSupportActionBar();
        //actionBar.hide();


        ibBack = findViewById(R.id.ib_spa_back);
        ibMenu = findViewById(R.id.ib_spa_menu);
        gv = findViewById(R.id.select_gridview);
        pb = findViewById(R.id.asp_pb);
        if (fzr_constant.productList == null) {
            pb.setVisibility(View.VISIBLE);


            OkHttpClient httpClient = new OkHttpClient();

            String url = "https://foodie.itslucas.me/products.php";
            Request getRequest = new Request.Builder()
                    .url(url)
                    .get()
                    .build();

            Call call = httpClient.newCall(getRequest);

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        //同步请求，要放到子线程执行
                        Response response = call.execute();

                        String res = response.body().string();
//
                        fzr_constant.productList = JSON.parseObject(res, new TypeReference<List<ProductsBean>>() {
                        });

                        for (int i = 0; i < 10; i++) {
                            nameList.add(fzr_constant.productList.get(i).getPname());
                            priceList.add(fzr_constant.productList.get(i).getPrice());
                        }
                        mHandler.sendEmptyMessage(1);

//
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        } else {
            pb.setVisibility(View.GONE);
            for (int i = 0; i < 10; i++) {
                nameList.add(fzr_constant.productList.get(i).getPname());
                priceList.add(fzr_constant.productList.get(i).getPrice());
            }
        }

        ibBack.setOnClickListener((v) -> {
            onBackPressed();
        });
        ibMenu.setOnClickListener((v) -> {
            Toast.makeText(this, "menu点击！", Toast.LENGTH_SHORT).show();
        });


        pgva = new ProductGridViewAdapter(nameList, priceList, getApplicationContext());
        gv.setAdapter(pgva);


    }


}