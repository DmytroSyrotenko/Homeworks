package ua.syrotenko.hw4;

import java.lang.reflect.Array;
import java.util.Arrays;


public class HW4Task1 {
    /*
    Заполнить одномерный массив случайными целочисленными значениями.
    • Найти среднее арифметическое и среднее геометрическое элементов массива. Размер массива -
    400 элементов.
    P.S. значения элементов можно ограничить значениями 1-10 включительно.
     */
    public static void main(String[] args) {

        int arraySize = 6;


        int[] randomizedArray = newMassiveAndRandom(arraySize);


        System.out.println("Array: " + Arrays.toString(randomizedArray));
        System.out.println("avgArifm = " + avgArifm(randomizedArray));
        System.out.println("avgGeom = " + avgGeom(randomizedArray));

    }

    public static int[] newMassiveAndRandom(int arraySize) {

        int[] arr = new int[arraySize];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 10) + 1;
        }
        return arr;
    }

    public static double avgArifm(int[] arr) {

        double sum = 0;

        for (int i = 0; i < arr.length; i++) {
            sum = sum + arr[i];
        }
        return sum/arr.length;
    }

    public static double avgGeom(int[] arr) {

        double result = 1;
        double power = (double) 1 / arr.length;

        for (int i = 0; i < arr.length; i++) {
            result = result * arr[i];

        }
        return Math.pow(result, power);
    }
}
