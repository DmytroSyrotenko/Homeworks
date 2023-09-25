package ua.syrotenko.hw2;

import java.io.IOException;
import java.util.Arrays;

public class Task2 {

    public static void main(String[] args) throws IOException {

        System.out.println("Enter a sentence");
        charactersCount();
    }

    public static void charactersCount() throws IOException {

        //BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String input = "14wtt!7".replaceAll("[^a-zA-Z]", "");//bufferedReader.readLine().replaceAll("[^a-zA-Z]", "");


        char[] array = input.toCharArray();
        Arrays.sort(array);
        System.out.println(array);


        int counter = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if (array[i] == array[j]) {
                    counter++;
                }

            }
            System.out.println(array[i] +"-" +counter);
            counter = 0;
        }

    }


}
