package me.itslucas.foodie.activities;

public class Goods {
    private String pic;
    private String Shopname;
    private String Goodsname;
    private String goodsdes;
    private String price;
    public Goods(String pic, String Shopname, String Goodsname, String goodsdes, String price) {
        this.pic = pic;
        this.Shopname = Shopname;
        this.Goodsname = Goodsname;
        this.goodsdes = goodsdes;
        this.price = price;
    }
    public String getPic() {
        return pic;
    }
    public String getGoodsname() {
        return Goodsname;
    }
    public String getShopname() {
        return Shopname;
    }
    public String getgoodsdes() {
        return goodsdes;
    }

    public String getprice() {
        return price;
    }

}
