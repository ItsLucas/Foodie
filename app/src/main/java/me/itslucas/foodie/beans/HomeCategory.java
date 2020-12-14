package me.itslucas.foodie.beans;



public class HomeCategory extends Category {

    private int imgBig;
    private int imgSmallTop;
    private int imgSmallBottom;
    private Category  ctBig;
    private Category ctStop;
    private  Category ctSbom;
    public HomeCategory(String name, int imgBig, int imgSmallTop, int imgSmallBottom, Category ctBig, Category ctStop, Category ctSbom) {
        super(name);
        this.imgBig = imgBig;
        this.imgSmallTop = imgSmallTop;
        this.imgSmallBottom = imgSmallBottom;
        this.ctBig = ctBig;
        this.ctStop = ctStop;
        this.ctSbom = ctSbom;
    }



    public Category getCtBig() {
        return ctBig;
    }

    public void setCtBig(Category ctBig) {
        this.ctBig = ctBig;
    }

    public Category getCtStop() {
        return ctStop;
    }

    public void setCtStop(Category ctStop) {
        this.ctStop = ctStop;
    }

    public Category getCtSbom() {
        return ctSbom;
    }

    public void setCtSbom(Category ctSbom) {
        this.ctSbom = ctSbom;
    }


    public  HomeCategory(String name,int imgBig,int imgSmallTop,int imgSmallBottom){
        super(name);
        this.imgBig = imgBig;
        this.imgSmallTop = imgSmallTop;
        this.imgSmallBottom = imgSmallBottom;
    }

    public void HomeCategoryAdd(  String Bname, String s1name, String s2name) {

        this.ctBig.setName(Bname);

        this.ctStop.setName(s1name);

        this.ctSbom.setName(s2name);
    }

    public Category getFromImgBig(int imgBig){
        return  ctBig;
    }

    public Category getFromImgSmallTop(int imgSmallTop){
        return  ctStop;
    }

    public Category getFromImgSmallBottom(int imgSmallBottom){
        return  ctSbom;
    }

    public int getImgBig() {
        return imgBig;
    }

    public void setImgBig(int imgBig) {
        this.imgBig = imgBig;
    }

    public int getImgSmallTop() {
        return imgSmallTop;
    }

    public void setImgSmallTop(int imgSmallTop) {
        this.imgSmallTop = imgSmallTop;
    }

    public int getImgSmallBottom() {
        return imgSmallBottom;
    }

    public void setImgSmallBottom(int imgSmallBottom) {
        this.imgSmallBottom = imgSmallBottom;
    }
}
