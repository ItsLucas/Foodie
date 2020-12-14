package me.itslucas.foodie.activities.fzr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import me.itslucas.foodie.R;

public class SearchActivity extends AppCompatActivity {

    SearchView mSearchView;
    ImageButton backButton;
    ImageButton eyeButton;
    TextView tv;
    ChipGroup cg;
    boolean eyeFlag = true;

    ChipGroup historychips;
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
                "桂花梅子酒", "猕猴桃", "薯片", "桂花梅子酒", "猕猴桃"};
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


        Window window = this.getWindow();
        window.setStatusBarColor(Color.parseColor("#FDC20E"));
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        //getSupportActionBar().hide();

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
        if(temp!=null)
        for (String s : temp) {
            Chip c = new Chip(SearchActivity.this);
            c.setText(s);
            historychips.addView(c);
        }

    }

}