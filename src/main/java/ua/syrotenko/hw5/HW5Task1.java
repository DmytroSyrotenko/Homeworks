package ua.syrotenko.hw5;

import java.util.Arrays;

public class HW5Task1 {
    /*
    Заполнение двумерного массива значениями индекса(от 1 и до кол-ва элементов в
массиве), при этом каждая вторая строка - отрицательными значениями.
Например
1 2 3
-4 -5 -6
7 8 9
-10 -11 -12
*Передать массив в метод, заполнить данный массив

Требования к задачам:
А) выведение массива в консоль до/после операций над массивом (если был пустой – не нужно)
Б) Все расчеты проводить в отдельных методах и возвращать значение, которое выводится в
консоль в методе main().

В) Исходный массив не должен измениться, т.е. создаем копию и работаем с ней, если требуется
изменение массива.
     */


    public static void main(String[] args) {

        int[][] array = new int[4][3];

        System.out.println(Arrays.deepToString(array));
        System.out.println(Arrays.deepToString(arraySpecificFill(array)));

    }

    public static int[][] arraySpecificFill(int[][] array) {

//        i - количество строк по вертикали
//        j - длинна строк

        int index = 1;

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (i % 2 == 0) {
                    array[i][j] = index;
                    index++;
                } else {
                    array[i][j] = -index;
                    index++;
                }
            }
        }
        return array;
    }
}