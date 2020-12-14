package me.itslucas.foodie.activities.fzr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import me.itslucas.foodie.R;
import me.itslucas.foodie.activities.fzr.childpage.ProductDetail;

public class SearchActivity extends AppCompatActivity {

    SearchView mSearchView;
    ImageButton backButton;
    ImageButton eyeButton;
    TextView tv;
    ChipGroup cg;
    boolean eyeFlag = true;

    ChipGroup historychips;
    Chip c1;
    Chip c2;
    Chip c3;
    Chip c4;
    Chip c6;
    SharedPreferences sp;
    SharedPreferences.Editor spe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //防止键盘改动布局
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.activity_search);

        //初始化listview
        //定义一个HashMap构成的列表以键值对的方式存放数据
        ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();
        String[] name = new String[]{
                "桂花梅子酒", "梅林午餐肉", "薯片", "植物牛肉", "蛋黄酥"};
        for (int i = 0; i < name.length; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("index", i + 1);
            map.put("name", name[i]);
            if (i % 3 == 0)
                map.put("image", R.drawable.up);
            else
                map.put("image", R.drawable.one);
            listItem.add(map);
        }


        //构造SimpleAdapter对象，设置适配器
        SimpleAdapter mSimpleAdapter = new SimpleAdapter(this,
                listItem,//需要绑定的数据
                R.layout.search_listview_item,//每一行的布局
                new String[]{"index", "name", "image"},
                //数组中的数据源的键对应到定义布局的View中
                new int[]{R.id.index, R.id.name, R.id.image});
        ListView list = (ListView) findViewById(R.id.list_item);
        ListView list2 = (ListView) findViewById(R.id.list_item2);
        ListView list3 = (ListView) findViewById(R.id.list_item3);
        //为ListView绑定适配器
        list.setAdapter(mSimpleAdapter);
        list2.setAdapter(mSimpleAdapter);
        list3.setAdapter(mSimpleAdapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(),i+"",Toast.LENGTH_SHORT).show();
                TextView tv = list.getChildAt(i).findViewById(R.id.name);
                String temp_name = tv.getText().toString();
                Intent intent = new Intent(getApplicationContext(), ProductDetail.class);

                Bundle bundle = new Bundle();
                bundle.putString("name",temp_name);
                bundle.putString("price",getPriceByName(temp_name));
                bundle.putInt("pic",getPicByName(temp_name));
                intent.putExtras(bundle);
                //这一行加了没用，不加闪退
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });


        Window window = this.getWindow();
        window.setStatusBarColor(Color.parseColor("#FDC20E"));
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
//        getSupportActionBar().hide();

        //==========================================
        // 获取控件
        mSearchView = findViewById(R.id.searchView2);
        backButton = findViewById(R.id.imageButton);
        eyeButton = findViewById(R.id.imageButton2);
        cg = findViewById(R.id.chip_group);
        tv = findViewById(R.id.textView3);
        tv.setVisibility(View.GONE);
        historychips = findViewById(R.id.history_chips);
        sp = getSharedPreferences("history", MODE_PRIVATE);
        spe = sp.edit();
        c1 = findViewById(R.id.chip1);
        c2=findViewById(R.id.chip2);
        c3=findViewById(R.id.chip3);
        c4 = findViewById(R.id.chip4);
        c6 = findViewById(R.id.chip6);

        c1.setOnClickListener((v)->{
            Intent i = new Intent(this, ProductDetail.class);

            Bundle bundle = new Bundle();
            bundle.putString("name","桂花梅子酒");
            bundle.putString("price",getPriceByName("桂花梅子酒"));
            bundle.putInt("pic",getPicByName("桂花梅子酒"));
            i.putExtras(bundle);
            //这一行加了没用，不加闪退
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
        });
        c2.setOnClickListener((v)->{
            Intent i = new Intent(this, ProductDetail.class);

            Bundle bundle = new Bundle();
            bundle.putString("name","梅林午餐肉");
            bundle.putString("price",getPriceByName("梅林午餐肉"));
            bundle.putInt("pic",getPicByName("梅林午餐肉"));
            i.putExtras(bundle);
            //这一行加了没用，不加闪退
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
        });
        c3.setOnClickListener((v)->{
            Intent i = new Intent(this, ProductDetail.class);

            Bundle bundle = new Bundle();
            bundle.putString("name","蔓越莓干");
            bundle.putString("price",getPriceByName("蔓越莓干"));
            bundle.putInt("pic",getPicByName("蔓越莓干"));
            i.putExtras(bundle);
            //这一行加了没用，不加闪退
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
        });
        c4.setOnClickListener((v)->{
            Intent i = new Intent(this, ProductDetail.class);

            Bundle bundle = new Bundle();
            bundle.putString("name","乐事原味薯片");
            bundle.putString("price",getPriceByName("乐事原味薯片"));
            bundle.putInt("pic",getPicByName("乐事原味薯片"));
            i.putExtras(bundle);
            //这一行加了没用，不加闪退
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
        });
        c6.setOnClickListener((v)->{
            Intent i = new Intent(this, ProductDetail.class);

            Bundle bundle = new Bundle();
            bundle.putString("name","梅尼耶干蛋糕");
            bundle.putString("price",getPriceByName("梅尼耶干蛋糕"));
            bundle.putInt("pic",getPicByName("梅尼耶干蛋糕"));
            i.putExtras(bundle);
            //这一行加了没用，不加闪退
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
        });

        mSearchView.setSubmitButtonEnabled(true);

        mSearchView.setQueryHint("桂花梅子酒");


        backButton.setOnClickListener((v) -> {
            onBackPressed();
        });
        eyeButton.setOnClickListener((v) -> {
            if (eyeFlag) {
                eyeButton.setImageResource(R.drawable.blind);
                cg.setVisibility(View.GONE);
                tv.setVisibility(View.VISIBLE);
            } else {
                eyeButton.setImageResource(R.drawable.eye);
                cg.setVisibility(View.VISIBLE);
                tv.setVisibility(View.GONE);
            }
            eyeFlag = !eyeFlag;
        });




        mSearchView.setIconified(false);//一开始处于展开状态
        mSearchView.setIconified(false);//设置searchView处于展开状态
        mSearchView.onActionViewExpanded();// 当展开无输入内容的时候，没有关闭的图标

        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                Set<String> temp = sp.getStringSet("history", null);
                if(temp==null){
                    temp = new HashSet<String>();
                }
                Set<String> s =new HashSet<>();
                s.addAll(temp);
                s.add(query);
                spe.putStringSet("history", s);
                spe.commit();

                Chip c = new Chip(SearchActivity.this);
                c.setText(query);
                historychips.addView(c,0);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //当输入框内容改变的时候回调
                Log.i("query", "内容: " + newText);
                return true;
            }
        });


        //====================================================
        //显示历史记录
        Set<String> temp = new HashSet<>();
        temp = sp.getStringSet("history", null);
        if(temp!=null){
            for (String s : temp) {
                Chip c = new Chip(SearchActivity.this);
                c.setText(s);
                historychips.addView(c);
            }
        }


    }

    private String getPriceByName(String s) {
        if(s.equals("猕猴桃")){
            return "￥32";
        }else if (s.equals("桂花梅子酒")){
            return "￥100";
        }else if(s.equals("鸡肉丸")){
            return "￥50";
        }else if(s.equals("榴莲腰果")){
            return "￥20";
        }else if(s.equals("咸蛋黄小饼干")){
            return "￥8";
        }else if(s.equals("蔓越莓干")){
            return "￥22";
        }else if(s.equals("梅尼耶干蛋糕")){
            return "￥15";
        }else if(s.equals("岩烧乳酪面包")){
            return "￥35";
        }else if(s.equals("蛋黄酥")){
            return "￥25";
        }else if(s.equals("植物牛肉")){
            return "￥35";
        }else if(s.equals("梅林午餐肉")){
            return "￥75";
        }
        return "";
    }

    public static int getPicByName(String s) {
        if(s.equals("猕猴桃")){
            return R.drawable.kiwi;
        }else if (s.equals("桂花梅子酒")){
            return R.drawable.guihua;
        }else if(s.equals("鸡肉丸")){
            return R.drawable.rog;
        }else if(s.equals("榴莲腰果")){
            return R.drawable.liulan;
        }else if(s.equals("咸蛋黄小饼干")){
            return R.drawable.bingan;
        }else if(s.equals("蔓越莓干")){
            return R.drawable.manyue;
        }else if(s.equals("梅尼耶干蛋糕")){
            return R.drawable.meini;
        }else if(s.equals("岩烧乳酪面包")){
            return R.drawable.yanshao;
        }else if(s.equals("蛋黄酥")){
            return R.drawable.danhuang;
        }else if(s.equals("植物牛肉")){
            return R.drawable.zhiwu;
        }else if(s.equals("梅林午餐肉")){
            return R.drawable.meilin;
        }

        return 0;
    }

}