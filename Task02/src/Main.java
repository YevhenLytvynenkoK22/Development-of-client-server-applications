import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int N = 5;
        Cart cart = new Cart(N);
        Scanner in = new Scanner(System.in);
        for(int i = 0; i < N;i++) {
            System.out.print("Enter title: ");
            String title = in.nextLine();
            System.out.print("Enter price: ");
            float price = in.nextFloat();
            cart.addItem(new Item(title, price));
            in.nextLine();
        }

        System.out.println("Sum price = "+cart.CartPrice());

        cart.allPriceIncrease(15);
        System.out.println("Sum price + 15% = "+cart.CartPrice());
        cart.allPriceReduction(30);
        System.out.println("Sum price - 30% = "+cart.CartPrice());

    }
}