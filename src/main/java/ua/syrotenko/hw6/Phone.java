package ua.syrotenko.hw6;
/*Класс Phone.
    а) Создайте класс Phone, который содержит переменные(поля) number,
    model и weight.
    б) Добавить конструктор в класс Phone, который принимает на вход
    три параметра для инициализации переменных класса - number, model
    и weight.
    в) Добавить конструктор, который принимает на вход два параметра
    для инициализации переменных класса - number, model.
    г) Добавить конструктор без параметров.
    д) Создайте три экземпляра этого класса.
    ж) Присвоить значения полям класса.
    з) Выведите на консоль значения их полей.
    и) Добавить в класс Phone методы: receiveCall, имеет один параметр
    – имя звонящего. Выводит на консоль сообщение “Звонит {name}”.
    getNumber – возвращает номер телефона. Вызвать эти методы для
    каждого из объектов.
     */

public class Phone {

    String number;
    String model;
    double weight;


    public void receiveCall(String name)
    {
        System.out.println("Звонит " + name);
    }

    public String getNumber()
    {
        return this.number;
    }

    public Phone(String number, String model, double weight) {
        this.number = number;
        this.model = model;
        this.weight = weight;

    }

    public Phone(String number, String model) {
        this.number = number;
        this.model = model;

    }

    public Phone() {

    }



    @Override
    public String toString() {
        return "Phone{" +
                "number=" + number +
                ", model='" + model + '\'' +
                ", weight=" + weight +
                '}';
    }
}

