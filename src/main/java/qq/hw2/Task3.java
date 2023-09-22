package qq.hw2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Task3 {


    public static void main(String[] args) throws IOException {

        System.out.println("Enter a number from 1to10 for check");
        timeToGoHome();

    }

    public static void timeToGoHome() throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String input = bufferedReader.readLine();

        int quantityOfLessons = Integer.parseInt(input);
        int startHour = 9;
        int lessonLengthMins = 45;
        int longBreakMins = 15;
        int shortBreakMins = 5;

        int periods = (quantityOfLessons / 2);
        int totalBreaks = (quantityOfLessons % 2) == 0 ? periods * (longBreakMins + shortBreakMins) - longBreakMins : periods * (longBreakMins + shortBreakMins);

        int totalBreaksAndLessons = ((lessonLengthMins * quantityOfLessons) + totalBreaks);

        int endHour = (totalBreaksAndLessons / 60) + startHour;
        int endMinute = totalBreaksAndLessons - (60 * (endHour - startHour));

        System.out.println("Входные данные: " + quantityOfLessons);
        System.out.println("Выходные данные: " + endHour + " " + endMinute);

    }

}
