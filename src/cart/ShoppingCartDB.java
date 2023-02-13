package cart;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ShoppingCartDB {


    public void saveCart(String activeUser, List<String> activeCart, String fullDirPath) throws IOException{
        
        String filePath = fullDirPath + File.separator + activeUser + ".txt";
        // Path path = Paths.get(filePath);

        // File writer
        FileWriter fw = new FileWriter(filePath);

        // write list to file
        for(String item : activeCart){
            fw.write(item + "\n");
        }
        fw.close();

        System.out.println("Your cart has been saved");
    }

    public List<String> loginAndLoadCart(String activeUser, String fullDirPath) throws IOException{

        List<String> currentCart = new LinkedList<>();
        // Check for user name file in directory
        // If directory contains username
        List<String> userList = getUserList(fullDirPath);
        if (userList.contains(activeUser)){
            // read text file and return list
            String filePath = fullDirPath + File.separator + activeUser + ".txt";
            Path path = Paths.get(filePath);
            File file = path.toFile();
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            String line;

            while((line = br.readLine()) != null){
                currentCart.add(line);
            }
            br.close();
        }

        if(currentCart.size()>0){
            System.out.printf("%s, your cart contains the following items\n", activeUser);
            for (int i=0; i < currentCart.size(); i++){
                System.out.printf("%d. %s\n", i+1, currentCart.get(i));
            }
        } else {
            System.out.printf("%s, your cart is empty\n", activeUser);
        }

        return currentCart;
    }

    public List<String> getUserList(String fullDirPath){
        return Stream.of(new File(fullDirPath).listFiles())
                .filter(file -> !file.isDirectory())
                .map(File::getName)
                .map(file -> file.replace(".txt", ""))
                .collect(Collectors.toList());
    }
}
