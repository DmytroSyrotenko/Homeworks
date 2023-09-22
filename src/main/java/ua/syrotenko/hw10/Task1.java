//package ua.syrotenko.hw10;
//
///*
//Создайте пример наследования, реализуйте класс Student и класс Aspirant, аспирант
//отличается от студента наличием некой научной работы.
//а) Класс Student содержит переменные: String firstName, lastName, group. А
//также, double averageMark, содержащую среднюю оценку.
//б) Создать метод getScholarship() для класса Student, который возвращает
//сумму стипендии. Если средняя оценка студента равна 5, то сумма 100 грн, иначе
//80.
//в) Переопределить этот метод в классе Aspirant. Если средняя оценка
//аспиранта равна 5, то сумма 200 грн, иначе 180.
//г) Создать массив типа Student, содержащий объекты класса Student и
//Aspirant. Вызвать метод getScholarship() для каждого элемента массива.
//д) Покрыть методы getScholarship() тестами
// */
//public class Task1 {
//
//    public static void main(String[] args) {
//
//        Student student1 = new Student("Alex", "Ferguson", "4A", 4.5);
//        Student student2 = new Student("James", "Hetfield", "3C", 5);
//        Aspirant aspirant1 = new Aspirant("Lars", "Ulrich", "7A", 5);
//        Aspirant aspirant2 = new Aspirant("Kirk", "Hammett", "8C", 4);
//
//        System.out.println(student1.getScolarship());
//        System.out.println(student2.getScolarship());
//        System.out.println(aspirant1.getScolarship());
//        System.out.println(aspirant2.getScolarship());
//
//        }
//    }
//
//
