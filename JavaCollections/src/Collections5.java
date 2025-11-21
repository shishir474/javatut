import com.sun.source.tree.Tree;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

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
//
         System.out.println("------------Below is LinkedHashMap output------------------");
//      This LinkedHashMap retains the insertion order
        Map<Integer, String> mp = new LinkedHashMap<>();

        mp.put(1,"A");
        mp.put(21,"B");
        mp.put(23,"C");
        mp.put(141,"D");
        mp.put(25,"E");
//
//      it iterates over the DLL starting from head till the end of the list using the after ptr
        mp.forEach((Integer key, String val) -> System.out.println(key + " : " + val));

        System.out.println("------------Below is normal HashMap output------------------");

        Map<Integer, String> mp2 = new HashMap<>();
//
         mp2.put(1,"A");
         mp2.put(21,"B");
         mp2.put(23,"C");
         mp2.put(141,"D");
         mp2.put(25,"E");

         mp2.forEach((Integer key, String val) -> System.out.println(key + " : " + val));

         System.out.println("HashMap doesn't maintain the insertion order");

//        // there are several flavours of LinkedHashMap
//        // for instance, below we are explicitly asking it to retain accessOrder
         Map<Integer, String> mp3 = new LinkedHashMap<>(16, 0.75, true);

         mp3.put(1,"A");
         mp3.put(21,"B");
         mp3.put(23,"C");
         mp3.put(141,"D");
         mp3.put(25,"E");
//
//         // accessing some data - uses the same logic for get() as in HashMap
//        // in addition it checks, if accessOrder == true: it moves the node to the last
         mp3.get(23);
//
//         // most frequently used will be placed at the last
         mp3.forEach((Integer key, String val) -> System.out.println(key + " : " + val));
//
        // This property of LinkedHashMap can be used in caching eviction policies.
        // Most frequently used keys will be at the end of the list and less frequent ones at the front.
        // We can then purge from the front of the list

//         TimeComplexity is same as HashMap : Average O(1) , Worst case: O(logn)

//         NOTE: LinkedHashMap is not thread safe and there is no thread safe version available for this.

//         We have to explicitly make it thread safe like this:
          Map<Integer, String> = mp = Collections.synchronizedMap(new LinkedHashMap<>());


//          TreeMap
//         - TreeMap is sorted according to the natural ordering of the key or by Comparator provided during map creation.
//         - Its based on Red Black tree (self balancing BST)
//         - O(logn) TC for insert, remove and get operations
//         - TreeMap is slower than HashMap, HashTable, LinkedHashMap. Their average is O(1), but TreeMap''s average is O(logn)
//                since it stores the keys in sorted order fashion

//         Node<K,V> structure of TreeMap:  Left, Parent, Key, Value, Right
//         As mentioned in point2, it creates a Binary Search Tree internally, that's why its tc is little bit higher compared to HashMap / HashTable / LinkedHashMap :
//         assume you do
//         tmap.put(4, "sj");
//         tmap.put(1, "pj");
//         tmap.put(5, "kj");

        // creates a bst internally
//                4
//            1       5


        // using a Comparator in the consturctor -- orders the keys in descending order
         Map<Integer, String> tmap = new TreeMap<>((Integer Key1, Integer key2) -> key2 - key1);
         tmap.put(1,"A");
         tmap.put(21,"B");
         tmap.put(23,"C");
         tmap.put(141,"D");
         tmap.put(25,"E");

         tmap.forEach((Integer key, String val) -> System.out.println(key + " : " + val));

         // increasing order
         Map<Integer, String> tmap2 = new TreeMap<>();
         tmap2.put(1,"A");
         tmap2.put(21,"B");
         tmap2.put(23,"C");
         tmap2.put(141,"D");
         tmap2.put(25,"E");

         tmap2.forEach((Integer key, String val) -> System.out.println(key + " : " + val));



//         Methods available in SortedMap interface

//         SortedMap<K,V> headMap(K tokey);
//         SortedMap<K,V> tailMap(K fromKey);
//         K firstKey();
//         K lastKey();

//         stores the keys in increasing order
         SortedMap<Integer, String> smap = new TreeMap<>();
         smap.put(1,"A");
         smap.put(21,"B");
         smap.put(23,"C");
         smap.put(141,"D");
         smap.put(25,"E");

         System.out.println(smap.headMap(23)); // heaoMap() is exclusive

         System.out.println(smap.tailMap(23)); // tailMap() is inclusive

         System.out.println(smap.firstKey());

         System.out.println(smap.lastKey());



//         Methods available in NavigableMap

//         again this TreeMap stores the. keys in increasing fashion
         NavigableMap<Integer, String> nmap = new TreeMap<>();

         // 1, 21, 23, 25, 141
         nmap.put(1,"A");
         nmap.put(21,"B");
         nmap.put(23,"C");
         nmap.put(141,"D");
         nmap.put(25,"E");

         System.out.println("lowerEntry(23)" + nmap.lowerEntry(23));
         // return the key lower than specified key
         System.out.println("lowerKey(23)" + nmap.lowerKey(23));

         // returns the entry(key,value) whose key <= key passed in floorEntry()
         System.out.println("floorEntry(23)" + nmap.floorEntry(23));
         // return the key <= than specified key
         System.out.println("floorKey(24)" + nmap.floorKey(24));

         // returns the entry(key,value) whose key >= key passed in ceilineEntry(key)
         System.out.println("ceilingEntry(23)" + nmap.ceilingEntry(23));
         // return the key >= than specified key
         System.out.println("ceilingKey(24)" + nmap.ceilingKey(24));


         System.out.println("higherEntry(23)" + nmap.higherEntry(23));
         // return the key > than specified key
         System.out.println("higherKey(23)" + nmap.higherKey(23));

         // firstEntry in the nmap
         System.out.println("firstEntry()" + nmap.firstEntry());

         System.out.println("lastEntry()" + nmap.lastEntry());

         // returns and removes the first entry from the map
         System.out.println("pollFirstEntry(23)" + nmap.pollFirstEntry());

         System.out.println("pollLastEntry()" + nmap.pollLastEntry());

         // returns the reversed map
        NavigableMap<Integer, String> nmap_rev =  nmap.descendingMap();

        // if TreeMap is sorted in asc order, keys will be returned in asc fashion, else descending
        NavigableSet<Integer> nmap_keys =  nmap.keySet();

        // if TreeMap is sorted in asc order, keys will be returned in desc fashion, else asc
        NavigableSet<Integer> nmap_desc_key = nmap.descendingKeySet();

        // headMap(k) is exclusive -- returns the map from top till the point key < k supplied
        NavigableMap<Integer, String> headMap =  nmap.headMap(23);

        // tailMap(k) is inclusive of key passed - returns the tail portion of the map
        NavigableMap<Integer, String> tailMap =nmap.tailMap(23);



//      Summary

         //                implements
//    Map (i) <----------------------------------------------------
//     | extends                      |           |           |
//    SortedMap (i)                   HashMap     Hashtable   LinkedHashMap
//     |  extends
//    NavigableMap (i)
//     | implements
//    TreeMap (concrete class)

//         1. HashMap --> normal map (not thread safe), does not maintain insertion/access order
//         2. Concurrent Version of HashMap -> HashTable(does not maintain insertion/access order) or ConcurrentHashMap
//         3. LinkedHashMap -  maintains the insertion order
//            - you can either maintain the insertion order (default version)
//            - you can maintain the access order (pass accessOrder: true while creating LinkedHashMap)
//            - If you want to maintain the insertion order -- use LinkedHashMap (rarely used)
//            - not thread safe, but we can create its thread safe version using Collections.synchronizedMap(new LinkedHashMap<>())
//         4. TreeMap
//             -- all the keys are stored in increasing / decreasing fashion
//             -- useful when you want to operate on keys in inc/desc order
//             -- not thread safe
//
//                 Collections.synchronizedMap(new TreeMap<>())





     }


}
