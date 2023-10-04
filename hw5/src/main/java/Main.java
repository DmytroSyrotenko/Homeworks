
public class Main {
    public static void main(String[] args) {

        MatList<Number> list1 = new MatList<>();
        list1.add(67);
        System.out.println("list1 =" + list1);
        //////////////////////
        Number[] ml60 = new Number[]{18, 25, 66};
        Number[] ml65 = new Number[]{17, -15, 30.2};
        MatList<Number> list2 = new MatList<>(ml60, ml65);
        System.out.println("list2=" + list2);

        Number[] ml70 = new Number[]{27, 66, 30.3};
        Number[] ml75 = new Number[]{-55, 18.3, 45};
        MatList<Number> list3 = new MatList<>(ml70, ml75);
        System.out.println("list3" + list3);

        //////////////////////
        MatList<Number> list4 = new MatList<>(list2, list3);

        //////////////////////
        System.out.println("list4 BEFORE=             " + list4);

        list4.sortAsc(17);
        System.out.println("after sortAsc from value= " + list4);
        list4.sortAsc(3, 6);
        System.out.println("after sortAsc in range=   " + list4);

        list4.sortAsc();
        System.out.println("after sortAsc=            " + list4);


        list4.sortDesc();
        System.out.println("after sortDesc=           " + list4);
        list4.sortDesc(-55);
        System.out.println("after sortDesc from value=" + list4);
        list4.sortDesc(5, 8);
        System.out.println("after sortDesc in range=  " + list4);


        list4.add(654);
        System.out.println("after add=                " + list4);
        list4.add(372, -54, 56.7);
        System.out.println("after add more then 1 val=" + list4);
        MatList<Number> test = list4.cut(5, 12);
        System.out.println("after cut in between=     " + test);
        list4.clear(new Number[]{45, 17});
        System.out.println("after clr numbrs from arr=" + list4);
        list4.get(5);
        System.out.println("after get value by index= " + list4);


        System.out.println("after getMax       =      " + list4.getMax());
        System.out.println("after getMin       =      " + list4.getMin());
        System.out.println("after getAverage   =      " + list4.getAverage());
        System.out.println("after getMedian    =      " + list4.getMedian());


        list4.clear();
        System.out.println("after clear all    =      " + list4);

        list4.intersection(list2, list3);
        System.out.println("after intersection =      " + list4);

    }
}