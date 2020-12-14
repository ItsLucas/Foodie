package me.itslucas.foodie.Fragment.home;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;

import me.itslucas.foodie.R;
import me.itslucas.foodie.Fragment.DividerItemDecortion;
import me.itslucas.foodie.Fragment.HomeCatgoryAdapter;
import me.itslucas.foodie.activities.fzr.SearchActivity;
import me.itslucas.foodie.beans.*;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import okhttp3.*;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private SliderLayout mSliderLayout;
    private HomeViewModel homeViewModel;
    private PagerIndicator indicator;
    private RecyclerView mRecyclerView;
    private HomeCatgoryAdapter mAdapter;
    public JsonArray elements;
    private Toolbar tb;
    private List<String> datas = new ArrayList<>();
    ArrayList<ProductsBean> ps = new ArrayList<>();
    OkHttpClient client;
    private static final int COMPLETED = 0;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_home, container, false);
        setHasOptionsMenu(true);
        ((AppCompatActivity) getActivity()).setSupportActionBar(tb);
//slider layout
        mSliderLayout = (SliderLayout) root.findViewById(R.id.daimajia_slider_image);
        indicator = (PagerIndicator) root.findViewById(R.id.custom_indicator);
        tb = root.findViewById(R.id.toolbar2);
        tb.getMenu().add(1, 110, 1, "搜索");//  requestImages();

        tb.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener(){

            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent i = new Intent(getContext(),SearchActivity.class);
                startActivity(i);
                return true;
            }
        });
        ininStlider();
        initRecyclerView(root);

        return root;
    }

    private void requestImages() {

        //   String url = "http://112.124.22.238:8081/course_api/banner/query?type=1";
        String url = "https://foodie.itslucas.me/products.php";
        client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    String json = response.body().string();
                    JsonParser parser = new JsonParser();

                    ininStlider();
                    //  ArrayList<ProductsBean> ps = new ArrayList<>();
//                   for(JsonElement j : elements) {
//                       ProductsBean bean1 = new Gson().fromJson(j,ProductsBean.class);
//                  //     String picName = decodeUnicode( bean1.getPname());
//
//                       ps.add(bean1);
//                       System.out.println(bean1.getPid()+bean1.getPname());
//                   }
                    //  System.out.println(json);
                    //Log.i("HomeFragment","json"+json);
                    // System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
                }
            }
        });

    }


    private void initRecyclerView(View view) {

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        List<HomeCategory> datas = new ArrayList<>(20);

        Category ct1 = new Category();
        Category ct2 = new Category();
        Category ct3 = new Category();

        HomeCategory category = new HomeCategory("进口食品", R.drawable.img_big_1, R.drawable.img_1_small1, R.drawable.img_1_small2, ct1, ct2, ct3);
        category.HomeCategoryAdd("分类一", "食物二", "食物三");
        datas.add(category);


        Category ct4 = new Category();
        Category ct5 = new Category();
        Category ct6 = new Category();
        HomeCategory category1 = new HomeCategory("饼干零食", R.drawable.img_big_4, R.drawable.img_4_small1, R.drawable.img_4_small2, ct4, ct5, ct6);
        category1.HomeCategoryAdd("分类二", "食物五", "食物六");
        datas.add(category1);

        Category ct7 = new Category();
        Category ct8 = new Category();
        Category ct9 = new Category();
        HomeCategory category2 = new HomeCategory("缤纷水饮", R.drawable.img_big_2, R.drawable.img_2_small1, R.drawable.img_2_small2, ct7, ct8, ct9);
        category2.HomeCategoryAdd("分类三", "食物八", "食物九");
        datas.add(category2);

        Category ct10 = new Category();
        Category ct11 = new Category();
        Category ct12 = new Category();
        HomeCategory category3 = new HomeCategory("米面杂粮", R.drawable.img_big_3, R.drawable.img_3_small1, R.drawable.imag_3_small2, ct10, ct11, ct12);
        category3.HomeCategoryAdd("分类四", "食物十一", "食物十二");
        datas.add(category3);

        Category ct13 = new Category();
        Category ct14 = new Category();
        Category ct15 = new Category();
        HomeCategory category4 = new HomeCategory("油盐酱醋", R.drawable.img_big_0, R.drawable.img_0_small1, R.drawable.img_0_small2, ct13, ct14, ct15);
        //  category4.HomeCategoryAdd("油盐酱醋",R.drawable.img_big_0,"食物十三",R.drawable.img_0_small1,"食物十四",R.drawable.img_0_small2,"食物十五");
        category4.HomeCategoryAdd("分类五", "食物十四", "食物十五");
        datas.add(category4);

        mAdapter = new HomeCatgoryAdapter(datas);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new DividerItemDecortion());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        initData(datas);
    }


    private void initData(List<HomeCategory> datas) {


        mAdapter = new HomeCatgoryAdapter(datas, getActivity());

        mAdapter.setOnCatgoryCLickLIstener(new HomeCatgoryAdapter.OnCatgoryClickListener() {
            @Override
            public void onClick(View view, Category category) {
                // Toast.makeText(getContext(), category.getName(), Toast.LENGTH_LONG).show();
            }
        });

        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addItemDecoration(new DividerItemDecortion());

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
    }

    private void ininStlider() {

//        if(elements!=null){
//            for(JsonElement j : elements){
//                ProductsBean bean1 = new Gson().fromJson(j,ProductsBean.class);
//                ps.add(bean1);
//                TextSliderView textSliderView = new TextSliderView(this.getActivity());
//                           textSliderView.image("https://foodie.itslucas.me/imgs/"+bean1.getPname()+".jpeg");
//                           textSliderView.description(bean1.getPname());
//                           textSliderView.setScaleType(BaseSliderView.ScaleType.Fit);
//                           mSliderLayout.addSlider(textSliderView);
//                System.out.println("************************");
//
//
//            }
//        }
        TextSliderView textSliderView = new TextSliderView(this.getActivity());
        textSliderView.image("https://img13.360buyimg.com/babel/s1900x912_jfs/t1/55060/22/14910/313561/5dba87deE4bcaff8f/bc7c36ca2b8c7a75.jpg!cc_1900x912.webp");
        textSliderView.description("品质粮油");
        textSliderView.setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
            @Override
            public void onSliderClick(BaseSliderView slider) {
                Toast.makeText(HomeFragment.this.getActivity(), "品质粮油", Toast.LENGTH_LONG).show();
            }
        });

        TextSliderView textSliderView1 = new TextSliderView(this.getActivity());
        textSliderView1.image("https://img20.360buyimg.com/babel/s1900x912_jfs/t1/99304/6/1611/307356/5dc2d093E7b3f70f0/0554e3f13aa81ab5.jpg!cc_1900x912.webp");
        textSliderView1.description("饼干蛋糕");
        textSliderView1.setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
            @Override
            public void onSliderClick(BaseSliderView slider) {
                Toast.makeText(HomeFragment.this.getActivity(), "饼干蛋糕", Toast.LENGTH_LONG).show();
            }
        });

        TextSliderView textSliderView2 = new TextSliderView(this.getActivity());
        textSliderView2.image("https://img12.360buyimg.com/babel/s1900x912_jfs/t1/78275/11/12527/98040/5d9c3755E5ec6bfce/82d08cc3e8b233cd.jpg!cc_1900x912.webp");
        textSliderView2.description("缤纷水饮");
        textSliderView2.setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
            @Override
            public void onSliderClick(BaseSliderView slider) {
                Toast.makeText(HomeFragment.this.getActivity(), "缤纷水饮", Toast.LENGTH_LONG).show();
            }
        });


        TextSliderView textSliderView3 = new TextSliderView(this.getActivity());
        textSliderView3.image("https://img14.360buyimg.com/babel/s1900x912_jfs/t1/103667/20/10860/283297/5e21646bE17e79c42/7ef613d8ff82df68.jpg!cc_1900x912.webp");
        textSliderView3.description("新年好茶");
        textSliderView3.setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
            @Override
            public void onSliderClick(BaseSliderView slider) {
                Toast.makeText(HomeFragment.this.getActivity(), "新年好茶", Toast.LENGTH_LONG).show();
            }
        });

        mSliderLayout.addSlider(textSliderView);
        mSliderLayout.addSlider(textSliderView1);
        mSliderLayout.addSlider(textSliderView2);
        mSliderLayout.addSlider(textSliderView3);


        //    mSliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mSliderLayout.setCustomIndicator(indicator);
        mSliderLayout.setCustomAnimation(new DescriptionAnimation());

        mSliderLayout.setPresetTransformer(SliderLayout.Transformer.RotateUp);
        mSliderLayout.setDuration(5000);

        mSliderLayout.addOnPageChangeListener(new ViewPagerEx.OnPageChangeListener() {
            //   private static final String TAG = ;

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //    Log.d("HomeFragment","onPageScrolled");
                //  Toast.makeText(HomeFragment.this.getActivity(),"11111111111111",Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onPageSelected(int position) {
                // Toast.makeText(HomeFragment.this.getActivity(),"11111111111111",Toast.LENGTH_SHORT).show();
                // Log.d("HomeFragment","onPageSelected");
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                //    Log.d("HomeFragment","onPageScrollStateChanged");

            }
        });
    }


}