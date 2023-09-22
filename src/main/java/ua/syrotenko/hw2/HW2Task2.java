package ua.syrotenko.hw2;

public class HW2Task2 {
    /*Заданы плоские декартовы координаты треугольника. Например A(1, 5), B(3, 5), C(6, 7)
    Найти площадь треугольника и вывести в консоль.
    Задания 2, 3, 4 предполагают наличие метода main() и метода, который выполняет задание.
    */
    public static void main(String[] args) {

        double xA = 1;
        double yA = 5;

        double xB = 3;
        double yB = 5;

        double xC = 6;
        double yC = 7;


        System.out.println("Triangle square =" + triangleSquare(1, 5, 3, 5, 6, 7));


    }

    public static double triangleSquare(double xA, double yA, double xB, double yB, double xC, double yC) {

        double sABC = (xA * (yB - yC) - xB * (yA - yC) + xC * (yA - yB)) / 2;

        return sABC;
    }
}
