package ua.syrotenko.hw5;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class HW5Practice1 {

    /*
    Известны данные о стоимости каждого из 12 товаров. Найти общую стоимость тех товаров,
    которые стоят дороже 1000 uah (количество таких товаров неизвестно).
     */


    public static void main(String[] args) {


        System.out.println("Total cost of goods with price >1000uah = " + sumByCriterion(newMassiveAndRandom()));
    }

    public static int[] newMassiveAndRandom() {

        int[] array = new int[12];

        for (int i = 0; i < array.length; i++) {
            array[i] = ThreadLocalRandom.current().nextInt(0,2000 );
        }
        System.out.println(Arrays.toString(array));
        return array;
    }


    public static int sumByCriterion(int[] array) {

        int sum = 0;

        for (int i = 0; i < array.length; i++) {
            if (array[i] > 1000) {
                sum = sum + array[i];
            }
        }
        return sum;
    }
}


