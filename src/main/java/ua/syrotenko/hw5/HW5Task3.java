package ua.syrotenko.hw5;

import java.util.Arrays;

public class HW5Task3 {
    /*
    Дан двумерный массив NxN(количество строк = количество столбцов), написать программу
    которая поменяет местами столбцы и строки.
    *Необходимо создать новый массив, в котором выполняются превращения и вернуть его из метода.
    Изначальный массив не должен измениться
     */

    public static void main(String[] args) {


        int[][] array = {{11, 12, 13}, {14, 15, 16}, {17, 18, 19}};


        System.out.println("Original array :" + Arrays.deepToString(array));
        System.out.println("Transformed array :" + Arrays.deepToString(stringsToRows(array)));
    }

    public static int[][] stringsToRows(int[][] array ) {

        int[][] newArray = new int[array.length][array.length];

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                newArray[i][j] = array[j][i];
            }
        }
        return newArray;
    }
}