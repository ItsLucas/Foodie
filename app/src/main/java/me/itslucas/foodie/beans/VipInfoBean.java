package me.itslucas.foodie.beans;

public class VipInfoBean {
    /**
     * cid : C233
     * level : 5
     * saved_amount : 0
     * spent_amount : 13072
     */

    private String cid;
    private String level;
    private String saved_amount;
    private String spent_amount;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getSaved_amount() {
        return saved_amount;
    }

    public void setSaved_amount(String saved_amount) {
        this.saved_amount = saved_amount;
    }

    public String getSpent_amount() {
        return spent_amount;
    }

    public void setSpent_amount(String spent_amount) {
        this.spent_amount = spent_amount;
    }
}
