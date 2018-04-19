package algorithm.application;

import algorithm.datastruct.LinearProbingHashST;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LookupCSV {
    public static void main(String[] args) throws FileNotFoundException {
        File data = new File(args[0]);
        Scanner in = new Scanner(data);
        Scanner stdIn = new Scanner(System.in);
        int keyField = Integer.parseInt(args[1]);
        int valueField = Integer.parseInt(args[2]);
        LinearProbingHashST<String, String> hashST = new LinearProbingHashST<>(16);
        while (in.hasNextLine()) {
            String line = in.nextLine();
            String[] tokens = line.split(",");
            hashST.put(tokens[keyField], tokens[valueField]);
        }
        System.out.println("What are you querying for?");
        while (stdIn.hasNext()) {
            String query = stdIn.next();
            System.out.println(hashST.get(query).orElse("Not found"));
            System.out.println("Anything else?");
        }
    }
}
