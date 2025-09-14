public class Item {
    private  String title;
    private float price;
    public  Item(String title, float price){
        this.title = title;
        this.price = price < 0 ? 0: price;

    }
    void priceIncrease(float percent){
        this.price += this.price*(1+percent/100);
    }
    void priceReduction(float percent){
        this.price -= this.price*(1+percent/100)<0 ? this.price*(1+percent/100) : 0;
    }
    float GetPrice(){
        return this.price;
    }

}
