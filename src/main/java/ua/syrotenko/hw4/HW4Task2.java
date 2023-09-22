package ua.syrotenko.hw4;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class HW4Task2 {

    /*
    Заполнить одномерный массив случайными целочисленными значениями.
    Проверить сколько простых чисел присутствует в массиве. Размер массива 1000 элементов.
     */

    public static void main(String[] args) {

        int arraySize = 1000;
        int[] randomizedArray = newMassiveAndRandom(arraySize);

        System.out.println("Количество простіх чисел в массиве = " + simpleNumbersCheck(randomizedArray));
    }


    public static int[] newMassiveAndRandom(int arraySize) {

        int[] arr = new int[arraySize];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = ThreadLocalRandom.current().nextInt(-20, 30);
        }
        System.out.println(Arrays.toString(arr));
        return arr;
    }


    public static int simpleNumbersCheck(int[] arr) {

        int counter = 0;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 2 || arr[i] == 3) {
                counter++;
            }
            for (int j = 2; j <= Math.floor(Math.sqrt(arr[i])); j++)
                if ((double) arr[i] % j == 0) {
                    break;
                } else if (j == Math.floor(Math.sqrt(arr[i]))) {
                    counter++;
                    break;

                }
        }return counter;
    }
}