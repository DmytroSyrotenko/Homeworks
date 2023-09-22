package ua.syrotenko.hw4;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class HW4Task3 {

    /*
    Заполнить одномерный массив случайными целочисленными значениями.
    Проверить сколько составных чисел присутствует в массиве. Размер массива 1000 элементов.
     */

    public static void main(String[] args) {

        int arraySize = 1000;

        int[] randomizedMassive = newMassiveAndRandom(arraySize);

        System.out.println("Количество составніх чисел в массиве = " + notSimpleNumbersCheck(randomizedMassive));
    }

    public static int[] newMassiveAndRandom(int arraySize) {

        int[] arr = new int[arraySize];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = ThreadLocalRandom.current().nextInt(-100, 900);
        }
        System.out.println(Arrays.toString(arr));
        return arr;
    }


    public static int notSimpleNumbersCheck(int[] arr) {

        int counter = 0;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 1) {
                counter++;
            }
            for (int j = 2; j < arr[i]; j++)
                if (arr[i] % j == 0) {
                    counter++;
                    break;
                }
        }
        return counter;
    }
}