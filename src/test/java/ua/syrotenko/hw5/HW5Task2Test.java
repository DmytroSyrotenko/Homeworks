package ua.syrotenko.hw5;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class HW5Task2Test {
    private static HW5Task2 hw5Task2;

    @BeforeAll
    public static void setUp() {

        hw5Task2 = new HW5Task2();
    }

    @Test
    void testFromSmallToLarge(){
        int[] array = {1,2,3,4};

        Assertions.assertEquals(false,HW5Task2.isSortedFromLargeToSmall(array));

    }

    @Test
    void testAllNegativeLargeToSmall(){
        int[] array = {-10,-9,-8,-7,-6};

        Assertions.assertEquals(false,HW5Task2.isSortedFromLargeToSmall(array));

    }
    @Test
    void testLargeToSmall(){
        int[] array = {9,8,7,6,5,4,3,1};

        Assertions.assertEquals(true,HW5Task2.isSortedFromLargeToSmall(array));

    }
    @Test
    void testMixedLargeToSmall(){
        int[] array = {9,8,7,6,5,4,3,-1,-10,-15};

        Assertions.assertEquals(true,HW5Task2.isSortedFromLargeToSmall(array));

    }
    @Test
    void testRepeatedNumbersLargeToSmall(){
        int[] array = {9,8,7,7,6,5,4,3,0};

        Assertions.assertEquals(true,HW5Task2.isSortedFromLargeToSmall(array));

    }
    @Test
    void testOneValue(){
        int[] array = {1,1,1,1,1,1};

        Assertions.assertEquals(true,HW5Task2.isSortedFromLargeToSmall(array));

    }
    @Test
    void testEmptyArray(){
        int[] array = {};

        Assertions.assertEquals(true,HW5Task2.isSortedFromLargeToSmall(array));

    }


}
