package me.itslucas.foodie.activities.fzr;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import me.itslucas.foodie.R;
import me.itslucas.foodie.UserData;
import me.itslucas.foodie.activities.fzr.childpage.BuyResultActivity;
import me.itslucas.foodie.activities.fzr.childpage.CartListViewAdapter;
import me.itslucas.foodie.activities.fzr.childpage.fzr_constant;
import me.itslucas.foodie.beans.CartBean;
import me.itslucas.foodie.beans.MessageBean;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CartActivity extends AppCompatActivity {

    static TextView totalPrice;
    Button placeOrder;
    Toolbar tb;
    RequestQueue rq;
    ArrayList<String> nameList = new ArrayList<String>();
    ArrayList<String> numList = new ArrayList<String>();
    CartListViewAdapter clva;
    ProgressBar pb;
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    clva.notifyDataSetChanged();
                    pb.setVisibility(View.GONE);
                    break;
            }

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        rq = Volley.newRequestQueue(this);



        ActionBar actionBar = getSupportActionBar();
        //设置状态栏和标题栏颜色
        ColorDrawable cd = new ColorDrawable(Color.parseColor("#EE756D"));

        Window window = this.getWindow();
        window.setStatusBarColor(Color.parseColor("#EE756D"));
        //显示返回按键
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        //=========================================
        // 获取控件
        totalPrice=findViewById(R.id.cart_totalprice);
        placeOrder=findViewById(R.id.cart_place_order);
        tb = findViewById(R.id.cart_toobar);
        pb =findViewById(R.id.ac_pb);
        pb.setVisibility(View.VISIBLE);
        tb.setTitle("购物车");
        setSupportActionBar(tb);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        ListView listview = (ListView) findViewById(R.id.cart_listview);
//        //为ListView绑定适配器
//        listview.setAdapter(mSimpleAdapter);


//        nameList = new ArrayList<String>(Arrays.asList("桂花梅子酒,猕猴桃,薯片".split(",")));
//        numList = new ArrayList<String>(Arrays.asList("1,2,3".split(",")));
        clva = new CartListViewAdapter(nameList, numList,getApplicationContext());
        listview.setAdapter(clva);


        placeOrder.setOnClickListener((v)->{
            String url = "https://foodie.itslucas.me/cart.php?placeorder=true&id=" + UserData.cid;
            StringRequest req = new StringRequest(url, response -> {
                MessageBean msg = new Gson().fromJson(response,MessageBean.class);
                if(msg.getMsg().equalsIgnoreCase("Success")) {
                    Intent i = new Intent(CartActivity.this, BuyResultActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putFloat("price", Float.parseFloat(totalPrice.getText().toString()));
                    i.putExtras(bundle);
                    startActivity(i);
                }
                else {
                    runOnUiThread(() -> Toast.makeText(getApplicationContext(),msg.getMsg(),Toast.LENGTH_SHORT).show());
                }
            }, error -> Log.e("VOLLEY",error.toString()));
            rq.add(req);
        });



        OkHttpClient httpClient = new OkHttpClient();

        String url = "https://foodie.itslucas.me/cart.php?id="+fzr_constant.userID;
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
                    fzr_constant.cartList = JSON.parseObject(res, new TypeReference<List<CartBean>>() {});

                    for (int i = 0; i < fzr_constant.cartList.size(); i++) {
                        nameList.add(fzr_constant.cartList.get(i).getPname());
                        numList.add(fzr_constant.cartList.get(i).getQuantity());
                    }
                    mHandler.sendEmptyMessage(1);

//
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    public static void setTotalPrice(String a){
        totalPrice.setText(a);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //返回按钮
            case android.R.id.home:
                this.finish(); // back button
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}