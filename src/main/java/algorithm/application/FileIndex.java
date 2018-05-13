package algorithm.application;

import algorithm.datastruct.LinearProbingHashST;
import algorithm.datastruct.SET;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileIndex {
    public static void main(String[] args) throws FileNotFoundException {
        LinearProbingHashST<String, SET<File>> st = new LinearProbingHashST<>(4);
        for (String filename: args) {
            File file = new File(filename);
            Scanner fileIn = new Scanner(file);
            while (fileIn.hasNext()) {
                String word = fileIn.next();
                if (!st.contains(word)) st.put(word, new SET<>());
                st.get(word).ifPresent(fileSET -> fileSET.add(file));
            }
        }
        Scanner stdIn = new Scanner(System.in);
        System.out.println("What are you querying for?");
        while (stdIn.hasNextLine()) {
            String query = stdIn.nextLine();
            st.get(query).ifPresent(fileSET ->
                    fileSET.keys().forEach(file -> System.out.println(" " + file.getName())));
            System.out.println("Anything else?");
        }
    }
}
