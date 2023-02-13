package cart;

import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

public class Main{

    public static void main(String[] args) throws IOException {

        // default directory 
        String directoryName = "db";
        String pwd = System.getProperty("user.dir");
        
        // if dir is specified, use args[0]
        if (args.length > 0){
            directoryName = args[0];
        }
        
        System.out.println("Directory>> " + directoryName);
        String fullDirPath = pwd + File.separator + directoryName;
        
        File directory = new File(fullDirPath);

        if (!directory.exists()){
            try {
                Path path = Paths.get(directoryName);
                Files.createDirectory(path);
            } catch (IOException e) {
    
                e.printStackTrace();
            }
        } else {
            System.out.println("Directory already exists");
        }
        
        Console cons = System.console();
        List<String> activeCart = new LinkedList<>();
        String activeUser = "";
        boolean continueRunning = true;
        String input;
        ShoppingCartDB shoppingCartDB = new ShoppingCartDB();
        ShoppingCart cart = new ShoppingCart();

        System.out.println("Welcome to your shopping cart");

        while(continueRunning){
            input = cons.readLine("> ").trim().toLowerCase();
            if (input.startsWith("login")){
                activeUser = input.split(" ")[1].trim();
                // List cart
                activeCart = shoppingCartDB.loginAndLoadCart(activeUser, fullDirPath);


            } else if (input.startsWith("add")){
                cart.addToCart(activeCart, input);

            } else if (input.startsWith("delete")){
                cart.deleteFromCart(activeCart, input);

            } else if (input.startsWith("list")){
                cart.listCart(activeCart);

            } else if (input.startsWith("save")){
                shoppingCartDB.saveCart(activeUser, activeCart, fullDirPath);

            } else if (input.startsWith("users")){
                List<String> userList = shoppingCartDB.getUserList(fullDirPath);
                for(String user : userList){
                    System.out.println(user);
                }

            } else if (input.startsWith("exit")){
                continueRunning = false;
                break;
            } else {
                System.out.println("Invalid input");
            }
        }

        
        
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