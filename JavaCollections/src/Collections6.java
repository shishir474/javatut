import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Collections6 {

//        Collection (i)
        //    |             implements
        //    Set (i) <--------------------------------
        //     | extends             |           |
        //    SortedSet (i)          HashSet     LinkedHashSet
        //     | extends
        //    NavigableSet (i)
        //     | implements
        //    TreeSet

//    HashSet, LinkedHashSet & TreeSet -- they internally use  HashMap, LinkedHashMap, and TreeMap respectively.
//    if you want to maintain insertion order --> use LinkedHashSet
//    If you want to store they elements in inc/desc order -> use TreeSet


//    Why HashMap allows only unique keys?
//    - A map is meant to model a mathematical dictionary or lookup table, where each key uniquely identifies a value.
//     if duplicate keys were allowed, the entire idea of key based lookup would break - get(key) wouldn''t know which value to return
//    - Internally HashMap uses: hashCode() -> to locate the bucket & equals() -> to check key equality inside that bucket.
//        When you do
//            mp.put(key1, value1);
//            mp.put(key1, value2);
//        HashMap will : compute the hash for key1, find bucket, Compare new key with existing key using equals().
//        Since they are equal -> it replaces value1 with value2
//
//        if (existingKey.hash == newKey.hash && existingKey.equals(newKey)){
//            // same key
//            replace the value
//        }
//        So duplicate keys are automatically overwritten, not allowed.

//  Summary
//    HashMap does not allow duplicate keys because a Map is defined as a collection of unique keys,
//    where each key maps to exactly one value. Internally, HashMap uses hashCode() and equals()
//    to detect key equality, so inserting the same key again simply replaces the existing value.
//    Allowing duplicates would break constant-time lookup, violate Map semantics, and introduce ambiguity in retrieval operations.

//    HashSet does not allow duplicates for the same fundamental reason HashMap does not allow duplicate keys - because
//    HashSet is internally backed by a HashMap

//    When you create a HashSet, internally Java creates a HashMap. So each element in your HashSet, becomes a key in the
//    internal HashMap. The value is a dummy Object (PRESENT)

        HashSet<String> set = new HashSet<>();
//        internally java does this
        private transient HashMap<String, Object> map;
        private static final Object PRESENT = new Object();

        // and whenever you add an element
        set.add("A");
//        Internally, it actually calls
        map.put("A", PRESENT);

//    Since HashMap does not allow duplicate keys, HashSet cannot allow duplicate element

//    HashSet does not allow duplicates because it internally uses a HashMap to store its elements.
//    Each element is stored as a key in the map, and since HashMap keys must be unique, HashSet automatically enforces uniqueness.
//    It relies on hashCode() and equals() to identify duplicates.


//    Few properties of set:
//    1. Collection of objcts, but it does not allow duplicates (only one null can be inserted)
//    2. Unlike List, Set is not an ordered collection. meaning they dont retain the insertion order.
//        LinkedHashSet does maintain the order - because internally it is backed by a LinkedHashMap which uses a DLL to retain the insertion order.
//    3. Set cannot be accessed via index.

    public static void main(String[] args){
        Set<Integer> s = new HashSet<>();
        s.add(12);
        s.add(11);
        s.add(33);
        s.add(4);

        s.forEach(val -> System.out.println(val));

//      HashSet is not thread safe  -- use the below version to
        Set<Integer> s2 =  Collections.synchronizedSet(new HashSet<>());

        // or use newKeySet method in ConcurrentHashMap class

        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        Set<Integer> threadSafeSet =  concurrentHashMap.newKeySet();

        threadSafeSet.add(1);

        Iterator<Integer> it =  threadSafeSet.iterator();
        while(it.hasNext()){

            int val = it.next();

            if(val == 1){
                /*
                 * We are modifying the Set while iterating over it.
                 *
                 * This works here because the Set returned by ConcurrentHashMap.newKeySet()
                 * is a *thread-safe, concurrent* set. Its iterator is **weakly consistent**,
                 * which means:
                 *
                 *   - It does NOT throw ConcurrentModificationException.
                 *
                 * In other words, concurrent additions/removals are allowed and do NOT break
                 * the iterator. This is exactly why this code safely adds a new element (9)
                 * while iterating.
                 *
                 * Compare this with a normal HashSet:
                 *
                 *   - HashSet's iterator is fail-fast.
                 *   - Any structural modification during iteration (add/remove)
                 *     causes a ConcurrentModificationException.
                 *   - It is not thread-safe, and modifying it during iteration is unsafe.
                 *
                 * Sometimes HashSet might appear to "accidentally work" if the modification
                 * happens after the iterator has exhausted its elements, but this is unreliable.
                 */
                threadSafeSet.add(9);
            }
        }

        /*
         * Since this is a thread-safe set, it is designed for concurrent access.
         * Multiple threads can add/remove elements without corrupting the structure.
         *
         * Unlike synchronized collections (like Collections.synchronizedSet),
         * ConcurrentHashMap-based sets do NOT acquire a single global lock.
         * Instead:
         *
         *   - They use fine-grained locking and lock striping internally.
         *   - Reads are almost lock-free.
         *   - Iterators are weakly consistent and do not block writers.
         *
         * This allows iteration and modification to proceed safely at the same time.
         */
        // ✔ Removes misconceptions about “thread safety = locking per thread” ?  -- DOUBT
        // although they wont operate together. Each thread will acquire a lock, perform operation & release the lock
        threadSafeSet.forEach(val -> System.out.println(val));

//        Thread Safe is best for the usecase where you are concurrently trying to operate/modify the set together.




    }


}
