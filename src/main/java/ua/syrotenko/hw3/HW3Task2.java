package ua.syrotenko.hw3;

import java.util.Scanner;

public class HW3Task2 {

    /*2. Удалить из строки пробелы и определить, является ли она перевертышем (палиндромом)
    Считать строку из консоли.
   Передать в метод, который удалит из нее все пробелы.
Передать строку без пробелов в метод, который определит, является ли она палиндромом
(перевертышем), т.е. одинаково пишется как с начала, так и с конца.
Вывести результат в main()
    */
    public static void main(String[] args) {

        System.out.println("Enter a word for check");
        String text = new Scanner(System.in).nextLine();

        System.out.println(isPalindrome(replaceSpaces(text)));
    }

    public static String replaceSpaces(String text) {

        String noSpaceText = text.replaceAll("\\s", "");
        return noSpaceText;
    }

    public static boolean isPalindrome(String noSpaceText) {

        String reverse = "";
        for (int i = 0; i < noSpaceText.length(); i++) {
            reverse = noSpaceText.charAt(i) + reverse;
            System.out.println(reverse);
        }

        boolean counter = true;
        for (int i = 0; i < reverse.length(); i++) {
            if ((noSpaceText.charAt(i)) == reverse.charAt(i)) {
            } else {
                counter = false;
                break;
            }
        }
        return counter;
    }
}



