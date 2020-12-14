package me.itslucas.foodie.activities.fzr.childpage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import me.itslucas.foodie.R;
import me.itslucas.foodie.activities.fzr.SelectProductActivity;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ProductGridViewAdapter extends BaseAdapter implements ListAdapter {
    private ArrayList<String> nameList = new ArrayList<String>();
    private ArrayList<String> priceList = new ArrayList<String>();
    private int picId;
    private Context context;
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    SelectProductActivity.setPb(true);
                    break;
                case 0:
                    SelectProductActivity.setPb(false);
                    Toast.makeText(context,"添加成功!",Toast.LENGTH_SHORT).show();
                    break;
            }

        }
    };

    public ProductGridViewAdapter(ArrayList<String> nameList,ArrayList<String> priceList, Context context) {
        this.nameList = nameList;
        this.priceList = priceList;
        this.context = context;

    }

    @Override
    public int getCount() {
        return nameList.size();
    }

    @Override
    public Object getItem(int pos) {
        return nameList.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return nameList.indexOf(nameList.get(pos));
        //just return 0 if your list items do not have an Id variable.
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.select_gridview_item, null);
        }



        TextView name = (TextView) view.findViewById(R.id.sgi_name);
        name.setText(nameList.get(position));


        ImageButton pic = view.findViewById(R.id.sgi_pic);
        picId= getPicByName(nameList.get(position));
        pic.setImageResource(picId);


        TextView price = (TextView) view.findViewById(R.id.sgi_price);
        price.setText("￥"+priceList.get(position));

        //Handle buttons and add onClickListeners
        ImageButton addCart = view.findViewById(R.id.sgi_addcart);
        if(fzr_constant.isInCart(name.getText().toString())){
            addCart.setImageResource(R.drawable.ic_fullcart);
        }else{
            addCart.setImageResource(R.drawable.ic_emptycart);
        }

        addCart.setTag(R.id.sgi_addcart,1);
        addCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if((int)addCart.getTag(R.id.sgi_addcart)==1){
                    addCart.setImageResource(R.drawable.ic_fullcart);
                    addCart.setTag(R.id.sgi_addcart,0);
                }

                OkHttpClient httpClient = new OkHttpClient();

                String pid = fzr_constant.getProductIdByName(name.getText().toString());

                String url = "https://foodie.itslucas.me/cart.php?id="+fzr_constant.userID+"&product_id="+pid+"&quantity=1";
                Log.i("res",url);
                Request getRequest = new Request.Builder()
                        .url(url)
                        .get()
                        .build();

                Call call = httpClient.newCall(getRequest);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            mHandler.sendEmptyMessage(1);
                            Response response = call.execute();
                            String res = response.body().string();
                            Log.i("res",res);
                            mHandler.sendEmptyMessage(0);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });
        pic.setOnClickListener((v)->{
            Intent i = new Intent(context, ProductDetail.class);

            Bundle bundle = new Bundle();
            bundle.putString("name",name.getText().toString());
            bundle.putString("price",price.getText().toString());
            bundle.putInt("pic",getPicByName(name.getText().toString()));
            i.putExtras(bundle);
            //这一行加了没用，不加闪退
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        });

        return view;
    }

    private int getPicByName(String s) {
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