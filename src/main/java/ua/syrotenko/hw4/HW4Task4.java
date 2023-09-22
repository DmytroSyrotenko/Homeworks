package ua.syrotenko.hw4;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class HW4Task4 {
    /*
    Заполнить одномерный массив случайными целочисленными значениями.
    Все четные значения заменить на нули. Размер массива - 2000 элементов.
     */

    public static void main(String[] args) {
        int arraySize = 2000;

        int[] randomizedMassive = newMassiveAndRandom(arraySize);

        System.out.println(Arrays.toString(changeEvenToZero(randomizedMassive)));

    }

    public static int[] newMassiveAndRandom(int arraySize) {

        int[] arr = new int[arraySize];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = ThreadLocalRandom.current().nextInt(-100, 900);
        }
        System.out.println(Arrays.toString(arr));
        return arr;
    }

    public static int[] changeEvenToZero(int[] array) {

        int[] chahgedArray = new int[array.length];

        for (int i = 0; i < array.length; i++) {
            if (array[i] % 2 == 0) {
                chahgedArray[i] = 0;
            } else {
                chahgedArray[i] = array[i];
            }
        }
        return chahgedArray;
    }

}
