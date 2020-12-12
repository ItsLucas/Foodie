package me.itslucas.foodie.beans;

public class CouponInfoBean {
    /**
     * cid : C233
     * couid : 1
     * expired_Time : 2020-12-12
     * deduct : 23
     */

    private String cid;
    private String couid;
    private String expired_Time;
    private String deduct;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCouid() {
        return couid;
    }

    public void setCouid(String couid) {
        this.couid = couid;
    }

    public String getExpired_Time() {
        return expired_Time;
    }

    public void setExpired_Time(String expired_Time) {
        this.expired_Time = expired_Time;
    }

    public String getDeduct() {
        return deduct;
    }

    public void setDeduct(String deduct) {
        this.deduct = deduct;
    }
}
