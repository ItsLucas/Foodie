package me.itslucas.foodie.beans;

public class ProductsBean {
    /**
     * pid : F0001
     * pname : 桂花梅子酒
     * net_weight : 600
     * price : 100
     * sid : 1
     */

    private String pid;
    private String pname;
    private String net_weight;
    private String price;
    private String sid;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getNet_weight() {
        return net_weight;
    }

    public void setNet_weight(String net_weight) {
        this.net_weight = net_weight;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }
}
