package me.itslucas.foodie.Fragment.dashboard;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import me.itslucas.foodie.R;
import me.itslucas.foodie.activities.fzr.CartActivity;
import me.itslucas.foodie.activities.fzr.childpage.BuyResultActivity;
import me.itslucas.foodie.activities.fzr.childpage.CartListViewAdapter;
import me.itslucas.foodie.activities.fzr.childpage.fzr_constant;
import me.itslucas.foodie.beans.CartBean;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DashboardFragment extends Fragment {

    private static int priceAll;
    private DashboardViewModel dashboardViewModel;
    static TextView totalPrice;
    Button placeOrder;
    Toolbar tb;
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

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        tb = root.findViewById(R.id.fd_db);
        tb.setTitle("购物车");
        priceAll=0;


//=========================================
        // 获取控件
        totalPrice = root.findViewById(R.id.fd_price);
        placeOrder = root.findViewById(R.id.fd_place_order);
        pb = root.findViewById(R.id.fd_pb);
        pb.setVisibility(View.VISIBLE);

        ListView listview = (ListView) root.findViewById(R.id.fd_listview);
        clva = new CartListViewAdapter(nameList, numList, getContext());
        listview.setAdapter(clva);

        placeOrder.setOnClickListener((v) -> {
            Intent i = new Intent(getActivity(), BuyResultActivity.class);
            Bundle bundle = new Bundle();

            bundle.putFloat("price", Float.parseFloat(totalPrice.getText().toString()));
            i.putExtras(bundle);
            startActivity(i);
        });

        OkHttpClient httpClient = new OkHttpClient();

        String url = "https://foodie.itslucas.me/cart.php?id=" + fzr_constant.userID;
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

                    fzr_constant.cartList = JSON.parseObject(res, new TypeReference<List<CartBean>>() {
                    });

                    for (int i = 0; i < fzr_constant.cartList.size(); i++) {
                        nameList.add(fzr_constant.cartList.get(i).getPname());
                        numList.add(fzr_constant.cartList.get(i).getQuantity());
                        priceAll+=Integer.parseInt(getPriceByName(nameList.get(i)))*Integer.parseInt(numList.get(i));
                    }
                    totalPrice.setText(priceAll+"");
                    mHandler.sendEmptyMessage(1);


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();


        return root;
    }

    public static void changeTotalPrice(int a) {
        priceAll+=a;
        totalPrice.setText(priceAll+"");
    }
    private String getPriceByName(String s) {
        if(s.equals("猕猴桃")){
            return "32";
        }else if (s.equals("桂花梅子酒")){
            return "100";
        }else if(s.equals("鸡肉丸")){
            return "50";
        }else if(s.equals("榴莲腰果")){
            return "20";
        }else if(s.equals("咸蛋黄小饼干")){
            return "8";
        }else if(s.equals("蔓越莓干")){
            return "22";
        }else if(s.equals("梅尼耶干蛋糕")){
            return "15";
        }else if(s.equals("岩烧乳酪面包")){
            return "35";
        }else if(s.equals("蛋黄酥")){
            return "25";
        }else if(s.equals("植物牛肉")){
            return "35";
        }else if(s.equals("梅林午餐肉")){
            return "75";
        }
        return "";
    }
}