package me.itslucas.foodie.activities.fzr;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import me.itslucas.foodie.R;
import me.itslucas.foodie.activities.fzr.childpage.BuyResultActivity;
import me.itslucas.foodie.activities.fzr.childpage.CartListViewAdapter;

public class CartActivity extends AppCompatActivity {

    static TextView totalPrice;
    Button placeOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);


        this.setTitle("购物车");

        ActionBar actionBar = getSupportActionBar();
        //设置状态栏和标题栏颜色
        ColorDrawable cd = new ColorDrawable(Color.parseColor("#EE756D"));
        actionBar.setBackgroundDrawable(cd);
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


        //构建listview
        ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();
        String[] name = new String[]{
                "桂花梅子酒", "猕猴桃", "薯片", "桂花梅子酒", "猕猴桃"};
        String[] type = new String[]{"水果", "水果", "零食", "饮品", "水果"};
        for (int i = 0; i < name.length; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("name", name[i]);
            map.put("type", type[i]);
            map.put("image", R.drawable.guihua);
            map.put("sign", "￥");
            map.put("price", "20");
            map.put("num", 3);
            listItem.add(map);
        }

//        //构造SimpleAdapter对象，设置适配器
//        SimpleAdapter mSimpleAdapter = new SimpleAdapter(this,
//                listItem,//需要绑定的数据
//                R.layout.cart_listview_item,//每一行的布局
//                new String[]{"name", "type", "image", "sign", "price", "num", "buttonp", "buttonr"},
//                //数组中的数据源的键对应到定义布局的View中
//                new int[]{R.id.cart_listview_name, R.id.cart_listview_type, R.id.cart_listview_pic, R.id.cart_listview_sign, R.id.cart_listview_price, R.id.cart_listview_num, R.id.cart_listview_buttonp, R.id.cart_listview_buttonr});
        ListView listview = (ListView) findViewById(R.id.cart_listview);
//        //为ListView绑定适配器
//        listview.setAdapter(mSimpleAdapter);

        ArrayList<String> nameList = new ArrayList<String>();
        ArrayList<String> numList = new ArrayList<String>();
        nameList = new ArrayList<String>(Arrays.asList("桂花梅子酒,猕猴桃,薯片".split(",")));
        numList = new ArrayList<String>(Arrays.asList("1,2,3".split(",")));
        CartListViewAdapter clva = new CartListViewAdapter(nameList, numList,getApplicationContext());
        listview.setAdapter(clva);


        placeOrder.setOnClickListener((v)->{
            Intent i = new Intent(CartActivity.this, BuyResultActivity.class);
            Bundle bundle = new Bundle();

            bundle.putFloat("price",Float.parseFloat(totalPrice.getText().toString()));
            i.putExtras(bundle);
            startActivity(i);
        });

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