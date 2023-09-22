package ua.syrotenko.hw7;

/*
Создайте класс, который описывает вектор (в трёхмерном пространстве).
У него должны быть:
а) конструктор с параметрами в виде списка координат x, y, z
б) метод, вычисляющий длину вектора. Корень можно посчитать с помощью Math.sqrt():
в) метод, вычисляющий векторное произведение с другим вектором
г) метод, вычисляющий косинус угла между векторами: косинус угла между векторами
равен скалярному произведению векторов, деленному на произведение модулей (длин)
векторов:
д) методы для суммы и разности векторов
е) статический метод, который принимает целое число N, и возвращает массив случайных
векторов размером N.
*Если метод возвращает вектор, то он должен возвращать новый объект, а не менять
базовый
 */

import java.util.Arrays;

public class Task2 {
    public static void main(String[] args) {

        Vector vectorA = new Vector(20, -3, 12);
        Vector vectorB = new Vector(14, 6, 8);

        System.out.println("б) длинна вектора= " + vectorA.vectorLength());

        System.out.println("в) векторное произведение= " + vectorA.vectorProduct(vectorB));

        System.out.println("г) косинус угла между векторами= " + vectorA.cosOfTheAngleBetweenVectors(vectorB));

        System.out.println("д) сумма векторов= " + vectorA.sumOfVectors(vectorB));

        System.out.println("д) разность векторов= " + vectorA.differenceOfVectors(vectorB));

        System.out.println("е) массив случайніх векторов= " + Arrays.toString(Vector.randomVectorsInArray(3)));

    }
}
