package me.itslucas.foodie.beans;

public class CartBean {
    /**
     * pid : F0001
     * quantity : 2
     * pname : 桂花梅子酒
     * price : 100
     */

    private String pid;
    private String quantity;
    private String pname;
    private String price;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
