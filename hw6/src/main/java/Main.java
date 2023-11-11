import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class Main {

    public static <V> void main(String[] args) {

        MyDictionary<Integer, String> myDictionary = new MyDictionary<>();
        System.out.println("myDictionary values= " + myDictionary);
        System.out.println("size when empty= " + myDictionary.size());
        System.out.println("isEmpty when empty= " + myDictionary.isEmpty());

        System.out.println("-----------");
        myDictionary.put(4, "aaa");
        myDictionary.put(5, "bbb");
        myDictionary.put(6, "ccc");
        myDictionary.put(7, "ddd");
        System.out.println("myDictionary values after put= " + myDictionary);
        System.out.println("isEmpty when NOT empty= " + myDictionary.isEmpty());

        System.out.println("-----------");
        System.out.println("contains existing key= " + myDictionary.containsKey(5));
        System.out.println("contains NOT existing key= " + myDictionary.containsKey(55));

        System.out.println("-----------");
        System.out.println("contains existing value= " + myDictionary.containsValue("ccc"));
        System.out.println("contains NOT existing value= " + myDictionary.containsValue("asdf"));

        System.out.println("-----------");
        System.out.println("get value by key if key exists= " + myDictionary.get(6));
        System.out.println("get value by key if key NOT exists= " + myDictionary.get(66));

        System.out.println("-----------");
        System.out.println("remove existing key= " + myDictionary.remove(6));
        System.out.println("remove NOT existing key= " + myDictionary.remove(66));
        System.out.println("myDictionary values after remove= " + myDictionary);

        System.out.println("-----------");
        Object[] keySet = myDictionary.keySet();
        Object[] valueSet = myDictionary.values();
        System.out.println("keySet" + Arrays.toString(keySet));
        System.out.println("valueSet" + Arrays.toString(valueSet));

        System.out.println("-----------");
        MyDictionary<Integer, String> myDictionary2 = new MyDictionary<>();
        myDictionary2.put(44, "fff");
        myDictionary2.put(55, "ggg");
        myDictionary2.put(66, "sss");

        myDictionary.putAll(myDictionary2);
        System.out.println("myDictionary after putAll myDictionary2= " + myDictionary);

        System.out.println("-----------");
        myDictionary.clear();
        myDictionary2.clear();
        System.out.println("myDictionary after clear= " + myDictionary);
        System.out.println("myDictionary2 after clear= " + myDictionary2);

    }
}
