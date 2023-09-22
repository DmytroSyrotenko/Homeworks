package ua.syrotenko.hw5;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class HW5Practice2 {
    /*
    Известен рост каждого из 25 учеников класса. Рост мальчиков условно задан
    отрицательными числами. Определить средний рост мальчиков и средний рост девочек.
    Пример ввода [160, 150, 148, -160, -170]
     */

    public static void main(String[] args) {

        int[] array = newMassiveAndRandom();
        System.out.println(Arrays.toString(array));

        System.out.println("avgHeightOfBoys: " + avgHeightOfBoys(array));
        System.out.println("avgHeightOfGirls: " + avgHeightOfGirls(array));
    }

    public static int[] newMassiveAndRandom() {

        int[] array = new int[5];

        for (int i = 0; i < array.length; i++) {
            array[i] = ThreadLocalRandom.current().nextInt(-150, 230);
        }
        return array;
    }

    public static double avgHeightOfBoys(int[] array) {

        double qtyBoys = 0;
        double totalHeightOfBoys = 0;


        for (int i = 0; i < array.length; i++) {
            if (array[i] < 0) {
                qtyBoys++;
                totalHeightOfBoys = totalHeightOfBoys + array[i];
            }
        }
        return totalHeightOfBoys / qtyBoys;
    }

    public static double avgHeightOfGirls(int[] array) {


        double qtyGirls = 0;
        double totalHeightOfGirls = 0;

        for (int i = 0; i < array.length; i++) {
            if (array[i] > 0) {
                qtyGirls++;
                totalHeightOfGirls = totalHeightOfGirls + array[i];
            }
        }
        return totalHeightOfGirls / qtyGirls;
    }

}
/////можно пойти от противного и считать общий рост и общее количество человек+ чьи то параметрі, а рост другого пола вічислять как
//общее-(минус)рост мальчиков/девочек  в штуках и общем росте.Либо сделать все в одном методе вічисления и результат вернуть масивом где
//0й индекс будет ср рост девочек а 1й индекс-мальчиков