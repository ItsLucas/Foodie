package me.itslucas.foodie.activities.fzr.childpage;

import java.util.List;

import me.itslucas.foodie.R;
import me.itslucas.foodie.beans.CartBean;
import me.itslucas.foodie.beans.ProductsBean;

public class fzr_constant {
    public static List<ProductsBean> productList;
    public static List<CartBean> cartList;
    public static String userID;
    public static Float userBalance=5000.0f;
    public static int priceAll = 0;


    public static String getProductIdByName(String name) {

        for(ProductsBean pb:productList){
            if(pb.getPname().equals(name)){
                return pb.getPid();
            }
        }
        return "null";

    }
    public static boolean isInCart(String name){
        if(cartList!=null){
            for(CartBean cb:cartList){
                if(name.equals(cb.getPname())){
                    return true;
                }
            }
        }

        return false;
    }
    public static String getPriceByName(String s) {
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
        }else if(s.equals("梅林午餐肉")) {
            return R.drawable.meilin;
        }
        return 0;
    }

}
