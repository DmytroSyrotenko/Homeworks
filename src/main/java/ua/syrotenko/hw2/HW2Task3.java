package ua.syrotenko.hw2;

public class HW2Task3 {
    /*Определить является ли переданное в метод число четным или нет и вывести результат в
     консоль.
     Задания 2, 3, 4 предполагают наличие метода main() и метода, который выполняет задание.*/
    public static void main(String[] args) {

        evenOdd(99);
    }

    public static void evenOdd(int number) {

        if (number % 2 == 0) {
            System.out.println(number + " is Chet");
        } else {
            System.out.println(number + " is Nechet");
        }
    }
}