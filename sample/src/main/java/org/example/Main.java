package org.example;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        DateSorter dateSorter = new DateSorter();
        dateSorter.setCharToFind("R");

        /* dates*/
        LocalDate date1= LocalDate.of(2004, Month.JULY, 1);
        LocalDate date2 = LocalDate.of(2005, Month.JANUARY, 2);
        LocalDate date3 = LocalDate.of(2007, Month.JANUARY, 1);
        LocalDate date4 = LocalDate.of(2032, Month.MAY, 3);

        List<LocalDate> list = new ArrayList<>();
        list.add(date1);
        list.add(date2);
        list.add(date3);
        list.add(date4);


        System.out.println("input  ="+list);
        System.out.println("output ="+dateSorter.sortDates(list));

    }
}