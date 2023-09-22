package ua.syrotenko.hw7;

import java.util.Arrays;

public class Vector {

    double x;
    double y;
    double z;


    public Vector(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;

    }

    public double vectorLength() {

        double vl = Math.sqrt((this.x * this.x) + (this.y * this.y) + (this.z * this.z));
        return vl;

    }

    @Override
    public String toString() {
        return "{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }

    public Vector vectorProduct(Vector other) {

        double i = (this.y * other.z) - (this.z * other.y);
        double j = -((this.x * other.z) - (this.z * other.x));
        double k = (this.x * other.y) - (this.y * other.x);

        return new Vector(i, j, k);


    }

    public double cosOfTheAngleBetweenVectors(Vector other) {
        double power = 2;
        double cosOfAngle = ((this.x * other.x) + (this.y * other.y) + (this.z * other.z)) /
                (Math.sqrt(Math.pow(this.x, power) + Math.pow(this.y, power) + Math.pow(this.z, power)) *
                        Math.sqrt((other.x * other.x) + (other.y * other.y) + (other.z * other.z)));
        ////// просто написал двумя разніми способами-через pow или умножением на себя.
        return cosOfAngle;
    }

    public Vector sumOfVectors(Vector other) {
        double i = this.x + other.x;
        double j = this.y + other.y;
        double k = this.z + other.z;

        return new Vector(i, j, k);
    }

    public Vector differenceOfVectors( Vector other) {
        double i = this.x - other.x;
        double j = this.y - other.y;
        double k = this.z - other.z;

        return new Vector(i, j, k);
    }

    public static Vector[] randomVectorsInArray(int qty) {

        Vector[] array = new Vector[qty];

        for (int i = 0; i < array.length; i++) {

            array[i] = new Vector((int) (Math.random() * 8) - 3, (int) (Math.random() * 8) - 3, (int) (Math.random() * 8) - 3);

        }
        return array;
    }
}

