package ua.syrotenko.hw5;

import java.util.Arrays;

public class HW5Task2 {
    /*
    2. Проверить, заданный массив на упорядоченность по невозрастанию, т. е. определить,
     верно ли, что каждый его элемент, начиная со второго, не больше предыдущего.
   *передать массив в метод, получить boolean
     */

    public static void main(String[] args) {
        int[] fromTenToZero = {10, 6, 4, 3, 2, 1, 9};

        System.out.println(Arrays.toString(fromTenToZero));
        System.out.println("Array sorted from largest to smallest numbers: " + isSortedFromLargeToSmall(fromTenToZero));
    }

    public static boolean isSortedFromLargeToSmall(int[] array) {

        boolean isSorted = true;

        for (int i = 0; i < array.length; i++) {
            if (i != array.length - 1) {
                if (array[i + 1] > array[i]) {
                    isSorted = false;
                    break;
                }
            } else {
                if (array[i] > array[i - 1]) {
                    isSorted = false;
                    break;
                }
            }
        }
        return isSorted;
    }
}


