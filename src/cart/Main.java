package cart;

import java.io.Console;
import java.util.LinkedList;
import java.util.List;

public class Main{

    public static void main(String[] args) {
        
        Console cons = System.console();
        List<String> cart = new LinkedList<>();
        boolean continueRunning = true;
        String input;

        System.out.println("Welcome to your shopping cart");

        do {
            input = cons.readLine("> ").trim().toLowerCase();
            if (input.contains("add")){
                addToCart(cart, input);
            } else if (input.contains("delete")){
                deleteFromCart(cart, input);
            } else if (input.contains("list")){
                listCart(cart);
            } else if (input.contains("exit")){
                exitCart(cart);
                continueRunning = false;
            } else {
                System.out.println("Invalid input");
                continue;
            }

        } while (continueRunning);
        
    }

    public static void addToCart(List<String> cart, String input){
        String[] inputArray = input.substring(4).split(",");
        for (String s: inputArray){
            s = s.trim();
            if (cart.contains(s)){
                System.out.printf("You have %s in your cart\n", s);
            } else {
                cart.add(s);
                System.out.printf("%s added to cart\n", s);
            }
        }
    }

    public static void deleteFromCart(List<String> cart, String input){
        String[] inputArray = input.substring(7).split(",");
        int[] intArray = new int[inputArray.length];
        for(int i=0; i < inputArray.length; i++){
            intArray[i] = Integer.parseInt(inputArray[i].trim());
        }
        
        List<String> itemsToDelete = new LinkedList<>();
        for (int i : intArray){
            if (i>0 && i <= cart.size()){
                System.out.printf("%s removed from cart\n", cart.get(i-1));
                itemsToDelete.add(cart.get(i-1));
                // cart.remove(cart.get(i-1));
            } else {
                System.out.println("Incorrect item index");
            }
        }

        for (String item : itemsToDelete){
            cart.remove(item);
        }
    }

    public static void listCart(List<String> cart){
        if(cart.size() > 0){
            for (int i=0; i < cart.size(); i++){
                System.out.printf("%d. %s\n", i+1, cart.get(i));
            }
        } else {
            System.out.println("Your cart is empty");
        }
    }

    public static void exitCart(List<String> cart){
        if(cart.size() > 0) {
            System.out.println("This is your final cart");
            listCart(cart);
        }
        System.out.println("Have a nice day!");
    }
    
}