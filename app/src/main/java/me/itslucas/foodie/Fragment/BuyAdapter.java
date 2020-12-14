package me.itslucas.foodie.Fragment;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import me.itslucas.foodie.R;
import me.itslucas.foodie.activities.Goods;

public class BuyAdapter extends ArrayAdapter {
    private final int resourceId;
    public BuyAdapter(Context context, int resourceId, List<Goods> objects) {
        super(context, resourceId, objects);
        this.resourceId = resourceId;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Goods user = (Goods) getItem(position);               // 获取当前user实例
        View view = LayoutInflater.from(getContext()).inflate(resourceId, null);    //实例化一个对象
        //设置图片
        ImageView iv_show_pic = (ImageView) view.findViewById(R.id.iv_show_pic);
        iv_show_pic.setBackgroundResource(R.drawable.act_home_shopcar);
        //设置名称
        TextView tv_commodity_name = (TextView) view.findViewById(R.id.tv_commodity_name);
        tv_commodity_name.setText(user.getGoodsname());
        //设置id
        TextView tv_commodity_attr = (TextView) view.findViewById(R.id.tv_commodity_attr);
        tv_commodity_attr.setText(user.getShopname());
        //设置按钮
        TextView tv_commodity_price = (TextView) view.findViewById(R.id.tv_commodity_price);
        tv_commodity_price.setText(user.getgoodsdes());

        TextView tv_commodity_num = (TextView) view.findViewById(R.id.tv_commodity_num);
        tv_commodity_num.setText(user.getprice());
        return view;
    }
}
