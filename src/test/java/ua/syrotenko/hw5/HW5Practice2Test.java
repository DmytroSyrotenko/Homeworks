package ua.syrotenko.hw5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HW5Practice2Test {


    HW5Practice2 hw5Practice2 = new HW5Practice2();


    @Test
    void testAvgHeightZero() {

        int[] array = {0, 0, 0, 0, 0};
        Assertions.assertEquals(Double.valueOf("NaN"), HW5Practice2.avgHeightOfBoys(array));
        Assertions.assertEquals(Double.valueOf("NaN"), HW5Practice2.avgHeightOfGirls(array));
    }


    @Test
    void testAvgHeightNegativeNumbers() {

        int[] array = {-15, -15, -15};

        Assertions.assertEquals(-15, HW5Practice2.avgHeightOfBoys(array));
        Assertions.assertEquals(Double.valueOf("NaN"), HW5Practice2.avgHeightOfGirls(array));

    }

    @Test
    void testAvgHeightPositiveNumbers() {

        int[] array = {20, 10, 30};

        Assertions.assertEquals(Double.valueOf("NaN"), HW5Practice2.avgHeightOfBoys(array));
        Assertions.assertEquals(20, HW5Practice2.avgHeightOfGirls(array));
    }

    @Test
    void testAvgHeightMixed() {

        int[] array = {20, 10, -30};

        Assertions.assertEquals(-30, HW5Practice2.avgHeightOfBoys(array));
        Assertions.assertEquals(15, HW5Practice2.avgHeightOfGirls(array));
    }

    @Test
    void testAvgHeightEmptyArray() {

        int[] array = {};

        Assertions.assertEquals(Double.valueOf("NaN"), HW5Practice2.avgHeightOfBoys(array));
        Assertions.assertEquals(Double.valueOf("NaN"), HW5Practice2.avgHeightOfGirls(array));
    }
}
