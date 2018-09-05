import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Application {


    public static void main(String[] args) throws Exception {

        long start = System.currentTimeMillis();
        
        System.out.println("Enter textfile name:");
        Scanner x = new Scanner(System.in);
        String text = x.nextLine();
        System.out.println("Enter output:");
        Scanner z = new Scanner(System.in);
        String output = x.nextLine();
        System.out.println("Enter count:");
        Scanner y = new Scanner(System.in);
        String count = y.nextLine();
        
    

        String textFile = text;
       
        String[] arguments = new String[2];
        if (args.length == 0 || args == null) {
            arguments[0] = textFile;
            arguments[1] = count;
        } else {
            arguments = args;
        }

        Apriori Apriori = new Apriori(output);
        Apriori.analyzer(arguments);
        Apriori.calculate();


        //display the execution time
        long end = System.currentTimeMillis();
        System.out.println("Time: " + ((double) (end - start) / 1000) + " seconds.");


    }
}