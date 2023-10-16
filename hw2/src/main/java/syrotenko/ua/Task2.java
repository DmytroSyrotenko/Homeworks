package syrotenko.ua;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Task2 {

    public static void charactersCount() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String input = bufferedReader.readLine().replaceAll("[^a-zA-Z]", "");

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
            System.out.println(array[i] + "-" + counter);
            counter = 0;
        }
    }
}
