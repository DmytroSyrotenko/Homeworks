package ua.syrotenko.hw5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.lang.model.type.ErrorType;

public class HW5Practice1Test {


    private static HW5Practice1 hw5Practice1 = new HW5Practice1();


    @Test
    void testSumByCriterionAllZero() {

        int[] array = {0, 0, 0, 0, 0, 0};
        Assertions.assertEquals(0, HW5Practice1.sumByCriterion(array));
    }

    @Test
    void testSumByCriterionEmpty() {

        int[] array = {};
        Assertions.assertEquals(0, HW5Practice1.sumByCriterion(array));
    }

    @Test
    void testSumByCriterionNegativeValues() {

        int[] array = {-5000, -1001, -55, -1};
        Assertions.assertEquals(0, HW5Practice1.sumByCriterion(array));
    }

    @Test
    void testSumByCriterionMixedValues() {

        int[] array = {5005, -2000, 10, 4500, 1000, 999, 4500, -3};
        Assertions.assertEquals(14005, HW5Practice1.sumByCriterion(array));
    }

//    @Test
//    void testSumByCriterionUnusualData() {
//
//        int[] array = {"34",-3};
//        Assertions.assertEquals("C:\\Users\\Lapstore\\Desktop\\a-level-hw1\\src\\test\\java\\ua\\syrotenko\\hw5\\HW5Practice1Test.java:45:24\n" +
//                "java: incompatible types: java.lang.String cannot be converted to int", HW5Practice1.sumByCriterion(array));
//    }
}