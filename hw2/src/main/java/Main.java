import syrotenko.ua.Task1;
import syrotenko.ua.Task2;
import syrotenko.ua.Task3;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        System.out.println("Enter a sentence");
        Task1.numbersCount();

        System.out.println("Enter a sentence");
        Task2.charactersCount();

        System.out.println("Enter a number from 1to10 for check");
        Task3.timeToGoHome();
    }
}
