package org.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;


/**
 * Marking will be based upon producing a readable, well engineered solution rather than factors
 * such as speed of processing or other performance-based optimizations, which are less
 * important.
 * Implement in single class. Don't chane constructor and signature method.
 */

public class DateSorter {

    /**
     * The implementation of this method should sort dates.
     * The output should be in the following order:
     * Dates with an 'r' in the month,
     * sorted ascending (first to last),
     * then dates without an 'r' in the month,
     * sorted descending (last to first).
     * For example, October dates would come before May dates,
     * because October has an 'r' in it.
     * thus: (2004-07-01, 2005-01-02, 2007-01-01, 2032-05-03)
     * would sort to
     * (2005-01-02, 2007-01-01, 2032-05-03, 2004-07-01)
     *
     * @param unsortedDates - an unsorted list of dates
     * @return the collection of dates now sorted as per the spec
     */

    protected String charToFind = "";

    public void setCharToFind(String charToFind) {
        this.charToFind = charToFind.toUpperCase();
    }


    public Collection<LocalDate> sortDates(List<LocalDate> unsortedDates) {
        final List<LocalDate> result = new ArrayList<>();
        final List<LocalDate> listWithoutCharToFindInMonth = new ArrayList<>();
        Comparator<LocalDate> comparatorForDateWithCharAsc = Comparator.naturalOrder();
        Comparator<LocalDate> comparatorWithoutCharByDesc = Comparator.reverseOrder();

        if (checkInput(unsortedDates)) {
            unsortedDates.forEach(localDate -> {
                if (localDate.getMonth().name().contains(charToFind)) {
                    result.add(localDate);
                } else {
                    listWithoutCharToFindInMonth.add(localDate);
                }
            });
            result.sort(comparatorForDateWithCharAsc);
            listWithoutCharToFindInMonth.sort(comparatorWithoutCharByDesc);
            result.addAll(listWithoutCharToFindInMonth);
        }
        return result;
    }

    private boolean checkInput(List<LocalDate> unsortedDates) {
        if (charToFind.isEmpty()) {
            throw new RuntimeException("Set necessary char to find in LocalDates");
        }
        if (unsortedDates.isEmpty()) {
            throw new RuntimeException("Add data to input list");
        }
        return true;
    }
}
