
import java.util.*;

public class MatList<E extends Number> implements Comparator<Number> {

    private final int startSize = 1;
    private int lastIndex = 0;
    private Number[] arr = new Number[startSize];

    public MatList() {
    }

    @SafeVarargs
    public MatList(E[]... numbers) {
        for (E[] number : numbers) {
            add(number);
        }
    }

    @SafeVarargs
    public MatList(MatList<E>... numbers) {
        for (MatList<E> number : numbers) {
            for (Number numberX : number.toArray()) {
                add((E) numberX);
            }
        }
    }


    /* main*/


    /* 1 додає елемент*/
    void add(E number) {
        if (number != null) {
            if (lastIndex == this.arr.length) {
                increaseSize();
            }
            this.arr[lastIndex] = number;
            lastIndex++;
        }
    }

    /* 2 додає n елементів*/
    @SafeVarargs
    final void add(E... n) {
        for (E e : n) {
            add(e);
        }
    }

    /* 3 об'єднує з іншими MatList*/
    void join(MatList<E>... ml) {
        for (MatList<E> matList : ml) {
            for (Number number : matList.toArray()) {
                add((E) number);
            }
        }
    }

    /* 4 об'єднує з іншими MatList, залишаючи тільки ті елементи, які є в усіх колекціях */
    void intersection(MatList... ml) {
        MatList<E> temp = new MatList<>();
        temp.join(ml);
        MatList<E> result = new MatList<>();
        int mlQuantity = ml.length;
        int counter = 0;

        for (int i = 0; i < temp.toArray().length - 1; i++) {
            for (MatList matList : ml) {
                Number value = temp.get(i);
                if (isExist(value, matList)) {
                    if (counter < mlQuantity - 1) {
                        counter++;
                    } else if (counter == mlQuantity - 1 && !isExist(value, result)) {
                        result.add((E) value);
                        counter = 0;
                    }
                }
            }
            counter = 0;
        }
        this.arr = result.arr;
    }

    /* 5 сортує колекцію від найбільшого */
    void sortDesc() {
        Number temp;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1; j++) {
                if (compare(arr[j], arr[j + 1]) < 0) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    /* 6 сортує колекцію від найбільшого тільки ті елементи, які лежать між firstIndex та lastIndex*/
    void sortDesc(int firstIndex, int lastIndex) {
        Number temp;
        for (int i = firstIndex; i < lastIndex; i++) {
            for (int j = firstIndex; j < lastIndex; j++) {
                if (compare(arr[j], arr[j + 1]) < 0) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    /* 7 сортує колекцію від найбільшого починаючи з value */
    void sortDesc(E value) {
        int start = getIndex(value);
        if (start >= 0) {
            Number temp;
            for (int i = start; i < arr.length - 1; i++) {
                for (int j = start; j < arr.length - 1; j++) {
                    if (compare(arr[j], arr[j + 1]) < 0) {
                        temp = arr[j];
                        arr[j] = arr[j + 1];
                        arr[j + 1] = temp;
                    }
                }
            }
        } else {
            System.out.println("Value not found in MatList");
        }

    }


    /* 8 сортує колекцію від найменшого */
    void sortAsc() {
        Number temp;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1; j++) {
                if (compare(arr[j], arr[j + 1]) > 0) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    /* 9  сортує колекцію від найменшого тільки ті елементи, які лежать між firstIndex та lastIndex */
    void sortAsc(int firstIndex, int lastIndex) {
        Number temp;
        for (int i = firstIndex; i < lastIndex; i++) {
            for (int j = firstIndex; j < lastIndex; j++) {
                if (compare(arr[j], arr[j + 1]) > 0) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    /* 10 сортує колекцію від найменшого починаючи з value */
    void sortAsc(E value) {
        int start = getIndex(value);
        if (start >= 0) {
            Number temp;
            for (int i = start; i < arr.length - 1; i++) {
                for (int j = start; j < arr.length - 1; j++) {
                    if (compare(arr[j], arr[j + 1]) > 0) {
                        temp = arr[j];
                        arr[j] = arr[j + 1];
                        arr[j + 1] = temp;
                    }
                }
            }
        } else {
            System.out.println("Value not found in MatList");
        }
    }

    /* 11 отримати елемент по індексу*/
    Number get(int index) {
        return this.arr[index];
    }

    /* 12 */
    Number getMax() {
        Number tempResult = getFirstNonEmpty();

        for (Number number : arr) {
            if (number != null && compare(tempResult, number) < 0) {
                tempResult = number;
            }
        }
        return tempResult;
    }

    /* 13 */
    Number getMin() {
        Number tempResult = getFirstNonEmpty();

        for (Number number : arr) {
            if (number != null && compare(tempResult, number) > 0) {
                tempResult = number;
            }
        }
        return tempResult;
    }

    /* 14 віддає середнє значення */
    Number getAverage() {
        int counter = 0;
        double sum = 0;

        for (Number number : arr) {
            if (number != null) {
                counter++;
                sum += number.doubleValue();
            }
        }
        return sum / counter;
    }

    /* 15 віддає медіану */
    Number getMedian() {
        this.sortAsc();
        Number result = null;
        boolean Odd = arr.length % 2 == 0;

        for (int i = 0; i < arr.length; i++) {
            if (Odd) {
                result = (arr[(arr.length - 1) / 2].doubleValue() + arr[(arr.length) / 2].doubleValue()) / 2;
            } else {
                result = arr[arr.length / 2].doubleValue();
            }
        }
        return result;
    }

    /*16*/
    Number[] toArray() {
        return this.arr;
    }

    /*17*/
    Number[] toArray(int firstIndex, int lastIndex) {

        Number[] newArr = new Number[lastIndex - firstIndex + 1];
        System.arraycopy(arr, firstIndex, newArr, 0, lastIndex - firstIndex + 1);
        arr = newArr;

        return this.arr;
    }

    /*18 вирізає з firstIndex по lastIndex */
    MatList<Number> cut(int firstIndex, int lastIndex) {

        Number[] newArr = new Number[this.arr.length];

        try {
            System.arraycopy(this.arr, firstIndex, newArr, 0, lastIndex - firstIndex + 1);
        } catch (Exception e) {
            System.out.println("Incorrect index.Try another one");
        }
        return new MatList<Number>(newArr);
    }


    /*19*/
    void clear() {
        arr = new Number[startSize];
    }

    /*20*/
    void clear(Number[] numbers) {
        for (Number number : numbers) {
            for (int j = 0; j < this.arr.length; j++) {
                Number[] newArr = new Number[this.arr.length - 1];
                if (Objects.equals(number, this.arr[j])) {
                    if (j == 0) {
                        System.arraycopy(this.arr, j + 1, newArr, 0, this.arr.length - 1);
                    } else {
                        System.arraycopy(this.arr, 0, newArr, 0, j);
                        System.arraycopy(this.arr, j + 1, newArr, j, this.arr.length - j - 1);
                    }
                    this.arr = newArr;
                }
            }
        }
    }


    /*helper methods*/
    private void increaseSize() {
        Number[] newArr = new Number[arr.length + 1];
        System.arraycopy(arr, 0, newArr, 0, arr.length);
        arr = newArr;
    }


    boolean isExist(Number number, MatList ml) {
        for (int i = 0; i < ml.toArray().length; i++) {
            if (Objects.equals(number, ml.get(i))) {
                return true;
            }
        }
        return false;
    }

    private Number getFirstNonEmpty() {
        for (Number number : this.arr) {
            if (number != null) {
                return number;
            }
        }
        return 0;
    }

    private int getIndex(Number numberToFind) {
        int index = -1;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(numberToFind)) {
                index = i;
            }
        }
        return index;
    }

    @Override
    public String toString() {
        return Arrays.toString(arr);
    }

    @Override
    public int compare(Number o1, Number o2) {
        if (o1.doubleValue() > o2.doubleValue()) {
            return 1;
        } else if (o1.doubleValue() < o2.doubleValue()) {
            return -1;
        }
        return 0;
    }
}


