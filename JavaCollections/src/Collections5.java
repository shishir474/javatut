import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

public class Collections5 {
//    LinkedHashMap & TreeMap
//  Maintains Insertion Order. -- This is possible because internally it maintains a Doubly linked list
//        &
//    Maintains Access Order -- elements that are very frequently used are at the end of the list
//    If we dont pass any thing in the constructor, it retains the insertion order
//    If you want to maintain access order, pass access order as true

//    As you can see below, Entry extends HashMap.Node<K,V> class - so it has int hash, K key, V value, HashMap.Node<K,V> next
//    It adds 2 more fields before & after --> for DLL
//    static class Entry<K,V> extends HashMap.Node<K,V> {
//        LinkedHashMap.Entry<K,V> before, after;
//        Entry(int hash, K key, V value, HashMap.Node<K,V> next) {
//            super(hash, key, value, next);
//        }
//    }

     public static void main(String[] args){

         System.out.println("------------Below is LinkedHashMap output------------------");
//            This LinkedHashMap retains the insertion order
         Map<Integer, String> mp = new LinkedHashMap<>();

        mp.put(1,"A");
        mp.put(21,"B");
        mp.put(23,"C");
        mp.put(141,"D");
        mp.put(25,"E");

//            it iterates over the DLL starting from head till the end of the list using the after ptr
        mp.forEach((Integer key, String val) -> System.out.println(key + " : " + val));

        System.out.println("------------Below is normal HashMap output------------------");

        Map<Integer, String> mp2 = new HashMap<>();

         mp2.put(1,"A");
         mp2.put(21,"B");
         mp2.put(23,"C");
         mp2.put(141,"D");
         mp2.put(25,"E");

         mp2.forEach((Integer key, String val) -> System.out.println(key + " : " + val));

         System.out.println("HashMap doesn't maintain the insertion order");

        // there are several flavours of LinkedHashMap
        // for instance, below we are explictily asking it to retain accessOrder
         Map<Integer, String> mp3 = new LinkedHashMap<>(16, 0.75, true);

         mp3.put(1,"A");
         mp3.put(21,"B");
         mp3.put(23,"C");
         mp3.put(141,"D");
         mp3.put(25,"E");

         // accessing some data - uses the same logic for get() as in HashMap
        // in addition it checks, if accessOrder == true: it moves the node to the last
         mp3.get(23);

         // most frequently used will be placed at the last
         mp3.forEach((Integer key, String val) -> System.out.println(key + " : " + val));

        // This property of LinkedHashMap can be used in caching eviction policies.
        // Most frequently used keys will be at the end of the list and less frequent ones at the front.
        // We can then purge from the front of the list

//         TimeComplexity is same as HashMap : Average O(1) , Worst case: O(logn)

//         LinkedHashMap is not thread safe and there is no thread safe version available for this.

//         We have to explicitly make it thread safe like this:
          Map<Integer, String> = mp = Collections.synchronizedMap(new LinkedHashMap<>());


     }


}
