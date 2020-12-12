package me.itslucas.foodie.beans;

public class BaiTiaoInfoBean {
    /**
     * cid : C233
     * max_limit : 20000
     * spent_limit : 10087
     * repay_date : 2020-12-16
     * minimum_repay : 1210.44
     * month_bill : 200
     * repayed_amount : 100
     */

    private String cid;
    private String max_limit;
    private String spent_limit;
    private String repay_date;
    private String minimum_repay;
    private String month_bill;
    private String repayed_amount;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getMax_limit() {
        return max_limit;
    }

    public void setMax_limit(String max_limit) {
        this.max_limit = max_limit;
    }

    public String getSpent_limit() {
        return spent_limit;
    }

    public void setSpent_limit(String spent_limit) {
        this.spent_limit = spent_limit;
    }

    public String getRepay_date() {
        return repay_date;
    }

    public void setRepay_date(String repay_date) {
        this.repay_date = repay_date;
    }

    public String getMinimum_repay() {
        return minimum_repay;
    }

    public void setMinimum_repay(String minimum_repay) {
        this.minimum_repay = minimum_repay;
    }

    public String getMonth_bill() {
        return month_bill;
    }

    public void setMonth_bill(String month_bill) {
        this.month_bill = month_bill;
    }

    public String getRepayed_amount() {
        return repayed_amount;
    }

    public void setRepayed_amount(String repayed_amount) {
        this.repayed_amount = repayed_amount;
    }
}
