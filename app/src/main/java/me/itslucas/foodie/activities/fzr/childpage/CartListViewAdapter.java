package me.itslucas.foodie.activities.fzr.childpage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import me.itslucas.foodie.R;
import me.itslucas.foodie.activities.fzr.CartActivity;
import me.itslucas.foodie.activities.fzr.SearchActivity;

public class CartListViewAdapter extends BaseAdapter implements ListAdapter {
    private ArrayList<String> nameList = new ArrayList<String>();
    private ArrayList<String> numList = new ArrayList<String>();
    private Context context;
    private double totalPrice;

    public CartListViewAdapter(ArrayList<String> nameList, ArrayList<String> numList, Context context) {
        this.nameList = nameList;
        this.numList = numList;
        this.context = context;
        totalPrice = 0;
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

    public double getTotalPrice(){
        return totalPrice;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.cart_listview_item, null);
        }

        TextView name = (TextView) view.findViewById(R.id.cart_listview_name);
        name.setText(nameList.get(position));

        ImageView pic = view.findViewById(R.id.cart_listview_pic);
        pic.setImageResource(SearchActivity.getPicByName(name.getText().toString()));



        TextView type = (TextView) view.findViewById(R.id.cart_listview_type);
        type.setText("水果");

        TextView sign = (TextView) view.findViewById(R.id.cart_listview_sign);
        sign.setText("￥");

        TextView price = (TextView) view.findViewById(R.id.cart_listview_price);
        price.setText(getPriceByName(name.getText().toString()));

        TextView num = view.findViewById(R.id.cart_listview_num);
        num.setText(numList.get(position));

        //Handle buttons and add onClickListeners
        Button bp = (Button) view.findViewById(R.id.cart_listview_buttonp);
        Button br = (Button) view.findViewById(R.id.cart_listview_buttonr);


        totalPrice+=Integer.parseInt(price.getText().toString())*Integer.parseInt(num.getText().toString());

        bp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                num.setText(Integer.parseInt(num.getText().toString())+1+"");
                totalPrice+=Integer.parseInt((price.getText().toString()));

                CartActivity.setTotalPrice(totalPrice+"");
            }
        });
        br.setOnClickListener((v) -> {

            num.setText(Integer.parseInt(num.getText().toString())-1+"");
            totalPrice-=Integer.parseInt((price.getText().toString()));
            CartActivity.setTotalPrice(totalPrice+"");
        });

        CartActivity.setTotalPrice(totalPrice+"");

        return view;
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