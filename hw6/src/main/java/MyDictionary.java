import java.util.Arrays;

public class MyDictionary<K, V> {

    private final int startSize = 0;
    private MyEntry<K, V>[] array = new MyEntry[startSize];
    private int lastIndex = 0;

    /*1*/
    int size() {
        return this.array.length;
    }

    /*2*/
    boolean isEmpty() {
        return this.lastIndex == 0;
    }

    /*3*/
    boolean containsKey(K key) {
        for (MyEntry<K, V> myEntry : array) {
            if (myEntry != null) {
                if (myEntry.getKey().equals(key)) {
                    return true;
                }
            }
        }
        return false;
    }

    /*4*/
    boolean containsValue(V value) {
        for (MyEntry<K, V> myEntry : array) {
            if (myEntry != null) {
                if (myEntry.getValue().equals(value)) {
                    return true;
                }
            }
        }
        return false;
    }

    /*5*/
    V get(K key) {
        for (MyEntry<K, V> myEntry : array) {
            if (myEntry.getKey().equals(key)) {
                return myEntry.getValue();
            }
        }
        return null;
    }

    /*6*/
    boolean put(K key, V value) {

        if (key == null || value == null || containsKey(key)) {
            System.out.println("Key is already exist OR key/value is null.Try another one");
            System.out.println("Incorrect entered values:key=" + key + ", value=" + value);
            return false;
        }

        if (lastIndex == array.length) {
            increaseSize();
        }
        array[lastIndex] = new MyEntry<>(key, value);
        lastIndex++;
        return true;
    }

    /*7*/
    boolean remove(K key) {

        if (containsKey(key)) {
            MyEntry<K, V>[] newArr = new MyEntry[array.length - 1];
            for (int i = 0; i < array.length; i++) {
                if (array[i].getKey().equals(key)) {
                    if (lastIndex != 1) {
                        System.arraycopy(array, 0, newArr, 0, i);
                        System.arraycopy(array, i + 1, newArr, i, array.length - i - 1);
                    }
                    array = newArr;
                    lastIndex--;
                    return true;
                }
            }
        }
        return false;
    }

    /*8*/
    boolean putAll(MyDictionary<K, V> dictionary) {
        K[] keySet = dictionary.keySet();
        V[] valueSet = dictionary.values();

        for (int i = 0; i < keySet.length; i++) {
            put(keySet[i], valueSet[i]);
        }
        return true;
    }

    /*9*/
    boolean clear() {
        while (this.lastIndex > 0) {
            remove(this.array[0].getKey());
        }
        return false;
    }


    /*10*/
    K[] keySet() {

        Object[] result = new Object[this.array.length];

        for (int i = 0; i < this.array.length; i++) {
            result[i] = this.array[i].getKey();
        }
        return (K[]) result;
    }


    /*11*/
    V[] values() {
        Object[] result = new Object[this.array.length];
        for (int i = 0; i < this.array.length; i++) {
            result[i] = this.array[i].getValue();
        }
        return (V[]) result;
    }

    @Override
    public String toString() {
        return Arrays.toString(array);
    }

    /*helper*/
    private void increaseSize() {
        MyEntry<K, V>[] newArr = new MyEntry[array.length + 1];
        System.arraycopy(array, 0, newArr, 0, array.length);
        array = newArr;
    }

}
