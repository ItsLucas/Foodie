package me.itslucas.foodie.beans;

import java.util.ArrayList;
import me.itslucas.foodie.beans.ProductsBean;
public class Products {
    private ArrayList<ProductsBean> products;

    public ArrayList<ProductsBean> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<ProductsBean> products) {
        this.products = products;
    }
}
