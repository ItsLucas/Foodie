package me.itslucas.foodie.beans;

import java.io.Serializable;

public class Category implements Serializable {
    private String pid;
    private String pname ;
    private String net_weight;
    private String price;
    private String sid;

    public Category() {
    }

    public Category(String name) {

        this.pname = name;
    }

    public Category(long id ,String pname) {
        this.pid = pid;
        this.pname = pname;
    }

    public String getName() {
        return pname;
    }

    public void setName(String pname) {
        this.pname = pname;
    }


}
