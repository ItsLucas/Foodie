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

        ImageView pic = view.findViewById(R.id.cart_listview_pic);
        pic.setImageResource(R.drawable.guihua);

        TextView name = (TextView) view.findViewById(R.id.cart_listview_name);
        name.setText(nameList.get(position));

        TextView type = (TextView) view.findViewById(R.id.cart_listview_type);
        type.setText("水果");

        TextView sign = (TextView) view.findViewById(R.id.cart_listview_sign);
        sign.setText("￥");

        TextView price = (TextView) view.findViewById(R.id.cart_listview_price);
        price.setText("30");

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
}