package cart;

import java.util.LinkedList;
import java.util.List;

public class ShoppingCart {
   
    // Methods
    public void addToCart(List<String> activeCart, String input){
        String[] inputArray = input.substring(4).split(",");
        for (String s: inputArray){
            s = s.trim();
            if (activeCart.contains(s)){
                System.out.printf("You have %s in your cart\n", s);
            } else {
                activeCart.add(s);
                System.out.printf("%s added to cart\n", s);
            }
        }
    }

    public void deleteFromCart(List<String> activeCart, String input){
        String[] inputArray = input.substring(7).split(",");
        int[] intArray = new int[inputArray.length];
        for(int i=0; i < inputArray.length; i++){
            intArray[i] = Integer.parseInt(inputArray[i].trim());
        }
        
        List<String> itemsToDelete = new LinkedList<>();
        for (int i : intArray){
            if (i>0 && i <= activeCart.size()){
                System.out.printf("%s removed from cart\n", activeCart.get(i-1));
                itemsToDelete.add(activeCart.get(i-1));
                // cart.remove(cart.get(i-1));
            } else {
                System.out.println("Incorrect item index");
            }
        }

        for (String item : itemsToDelete){
            activeCart.remove(item);
        }
    }

    public void listCart(List<String> activeCart){
        if(activeCart.size() > 0){
            for (int i=0; i < activeCart.size(); i++){
                System.out.printf("%d. %s\n", i+1, activeCart.get(i));
            }
        } else {
            System.out.println("Your cart is empty");
        }
    }

    public void exitCart(List<String> cart){
        if(cart.size() > 0) {
            System.out.println("This is your final cart");
            listCart(cart);
        }
        System.out.println("Have a nice day!");
    }

    public static void login(String name){
        // retrieve user cart
    }

    
    
}
