package ua.syrotenko.hw4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HW4Task1Test {

    private static HW4Task1 hw4Task1 = new HW4Task1();


    @Test
    void testAvgArifmOk() {

        int[] array = {3, 5, 9, 5, 8, 5, 10, 13};
        Assertions.assertEquals(7.25, HW4Task1.avgArifm(array));
    }

    @Test
    void testAvgArifmMixed() {

        int[] array = {5, -3, 8, 4, 0};
        Assertions.assertEquals(2.8, HW4Task1.avgArifm(array));
    }

    @Test
    void testAvgArifmAndGeomEmptyArray() {

        int[] array = {};
        Assertions.assertEquals(Double.valueOf("NaN"), HW4Task1.avgArifm(array));
        Assertions.assertEquals(Double.valueOf("NaN"), HW4Task1.avgGeom(array));
    }

    @Test
    void testAvgArifmAndGeomZeroValue() {

        int[] array = {0};
        Assertions.assertEquals(0, HW4Task1.avgArifm(array));
        Assertions.assertEquals(0, HW4Task1.avgGeom(array));
    }


    @Test
    void testAvgGeomAllNegative() {

        int[] array = {-5, -5, -9};
        Assertions.assertEquals(Double.valueOf("NaN"), HW4Task1.avgGeom(array));
    }

    @Test
    void testAvgGeomOk() {

        int[] array = {6, 8, 2, 10, 8};
        Assertions.assertEquals(5.985111478955379, HW4Task1.avgGeom(array));
    }

}