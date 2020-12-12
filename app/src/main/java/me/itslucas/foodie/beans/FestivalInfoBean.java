package me.itslucas.foodie.beans;

public class FestivalInfoBean {
    /**
     * fid : 1
     * fname : 圣诞节
     * time_begin : 2020-12-18
     * discount : 8
     * time_end : 2020-12-25
     */

    private String fid;
    private String fname;
    private String time_begin;
    private String discount;
    private String time_end;

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getTime_begin() {
        return time_begin;
    }

    public void setTime_begin(String time_begin) {
        this.time_begin = time_begin;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getTime_end() {
        return time_end;
    }

    public void setTime_end(String time_end) {
        this.time_end = time_end;
    }
}
