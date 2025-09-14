public class Cart {
    private Item[] stack;
    private int topIndex;

    public Cart(int capacity) {
        stack = new Item [capacity];
        topIndex = -1;
    }

    public boolean addItem(Item item) {
        return push(item);
    }

    private boolean push (Item item) {
        if(topIndex < stack.length-1) {
            stack[++topIndex] = item;
            return true;
        }
        return false;

    }

    public Item deleteLastAddedItem() {
        return pop();
    }

    private Item pop() {

        if(topIndex>0)
            return stack[topIndex--];

        return new Item("null", 0);
    }

    float CartPrice(){
        float allPrice = 0;
        for(Item item : stack){
            allPrice+= item.GetPrice();
        }
        return  allPrice;
    }
    void allPriceIncrease(float percent) {
        for (Item item : stack) {
            item.priceIncrease(percent);
        }
    }
    void allPriceReduction(float percent) {
        for (Item item : stack) {
            item.priceReduction(percent);
        }
    }
}