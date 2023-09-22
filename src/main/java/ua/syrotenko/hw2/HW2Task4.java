package ua.syrotenko.hw2;

public class HW2Task4 {
    /*
    Вывести на экран меньшее по модулю из трёх переданных в метод вещественных чисел.
    Вывести чисто, не модуль.
    Для вычисления модуля используйте тернарную операцию.

    Задания 2, 3, 4 предполагают наличие метода main() и метода, который выполняет задание.
     */

    public static void main(String[] args) {

        System.out.println(smallestNumber(10, -40, 6));

    }

    public static int smallestNumber(int a, int b, int c) {

        int modA = a > 0 ? a : -a;
        int modB = b > 0 ? b : -b;
        int modC = c > 0 ? c : -c;


        if ((modA <= modB) & (modA <= modC)) {
            return a;
        } else if ((modB <= modC) & (modB <= modA)) {
            return b;
        } else {
            return c;
        }
    }
}
