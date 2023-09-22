package qq.hw5;

import java.util.*;

public class MatList<E extends Number> {

    private LinkedList<E> baseList;
    private LinkedList<E> indexList;


    public MatList() {
        baseList = new LinkedList<>();
        indexList = new LinkedList<>();
    }

    ;

    @Override
    public String toString() {
        return "MatList{" +
                "baseList=" + baseList +
                ", indexList=" + indexList +
                '}';
    }

    public MatList(E[]... numbers) {

    }

    ;

    public MatList(MatList... numbers) {

    }

    //1-2. void add(E ... n) - додає n едементів

    void add(E... n) {
        for (int i = 0; i < n.length; i++) {
            E addedValue = n[i];
            baseList.add(addedValue);
        }
        System.out.println("Elements added: " + Arrays.toString(n));
        System.out.println("Current List: " + baseList);
        System.out.println("Razmer novogo lista = " + baseList.size());
    }

    //5. void sortDesc() - сортує колекцію від найбільшого
    void sortDesc() {

            System.out.println(baseList.get(0));

        }

    }




