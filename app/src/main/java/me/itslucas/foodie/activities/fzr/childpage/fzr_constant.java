package me.itslucas.foodie.activities.fzr.childpage;

import java.util.List;

import me.itslucas.foodie.beans.CartBean;
import me.itslucas.foodie.beans.ProductsBean;

public class fzr_constant {
    public static List<ProductsBean> productList;
    public static List<CartBean> cartList;
    public static String userID;
    public static Float userBalance=5000.0f;


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
}
