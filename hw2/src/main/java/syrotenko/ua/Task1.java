package syrotenko.ua;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Task1 {

    public static void numbersCount() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String input = bufferedReader.readLine();

        char[] charArray = input.toCharArray();

        int sum = 0;

        for (int i = 0; i < charArray.length; i++) {
            if (Character.isDigit(charArray[i])) {
                sum += Character.getNumericValue(charArray[i]);
            }
        }

        System.out.println("Входные данные :" + input);
        System.out.println("Выходные данные :" + sum);
    }
}
