package ua.syrotenko.hw6;

import java.awt.*;

public class Task1 {
    /*Класс Phone.
    а) Создайте класс Phone, который содержит переменные(поля) number,
    model и weight.
    б) Добавить конструктор в класс Phone, который принимает на вход
    три параметра для инициализации переменных класса - number, model
    и weight.
    в) Добавить конструктор, который принимает на вход два параметра
    для инициализации переменных класса - number, model.
    г) Добавить конструктор без параметров.
    д) Создайт'е три экземпляра этого класса.
    ж) Присвоить значения полям класса.
    з) Выведите на консоль значения их полей.'
    и) Добавить в класс Phone методы: receiveCall, имеет один параметр
    – имя звонящего. Выводит на консоль сообщение “Звонит {name}”.
    getNumber – возвращает номер телефона. Вызвать эти методы для
    каждого из объектов.
     */
    public static void main(String[] args) {

        Phone nokia3310 = new Phone("0502845612", "Nokia 3310", 665);
        Phone iPhone12 = new Phone("0984561245", "iPhone 12",546.7);
        Phone lenovo = new Phone("0661234565","A11",456);


        System.out.println(nokia3310);
        System.out.println(iPhone12);
        System.out.println(lenovo);


        nokia3310.receiveCall("Alex");
        System.out.println(nokia3310.getNumber());

        iPhone12.receiveCall("Richard");
        System.out.println(iPhone12.getNumber());

        lenovo.receiveCall("James");
        System.out.println(lenovo.getNumber());
    }
}