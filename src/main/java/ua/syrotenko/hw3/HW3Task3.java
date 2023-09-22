package ua.syrotenko.hw3;

import java.util.Scanner;

public class HW3Task3 {

    /*
    Подсчитать количество слов в строке
• Вводится строка, состоящая из слов, разделенных пробелами. Требуется посчитать количество
слов в ней.
Считывание данных проводить с консоли в методе main().
• Все расчеты проводить в отдельных методах и возвращать значение, которое выводится в консоль
в методе main().
    */


    public static void main(String[] args) {
        System.out.println("Enter a sentence for check");
        String text = new Scanner(System.in).nextLine();

        System.out.println(wordCounter(text));

    }

    public static String wordCounter(String text) {

        String firstLastSpaceDel = text.trim();
        String noSpaces = firstLastSpaceDel.replaceAll("\\s{2,}", " ");

        int counter = 1;

        for (int i = 0; i < noSpaces.length(); i++) {
            if (noSpaces.charAt(i) == '\s') {
                counter++;
            }
        }
        return "Number of words in a line = " + counter;
    }
}





