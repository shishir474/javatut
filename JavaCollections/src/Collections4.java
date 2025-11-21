import java.util.HashMap;
import java.util.Hashtable;

public class Collections4 {
//    Map
//                        implements
//    Map (i) <-----------------------------------------------------
//     |               |                   |                   |
//    SortedMap(i)       HashMap             Hashtable     LinkedHashMap
//     | implements
//    TreeMap

    //    Hashmap Internal Working in Java (V IMP for interviews)

    HashMap is a concrete class in Java which implements the Map interface.
    Map interface has a subinterface Entry.
    Node<K,V> (a static nested class of HashMap) implements the Map.entry interface

    Each node contains:
    static class Node<K,V> implements Map.Entry<K,V> {
        final int hash;
        final K key;
        V value;
        Node<K,V> next;  // next pointer for linked list
    }

    How HashMap stores the data?
//    NOTE: below array and hashtable are used interchangaebly. They both are representing the same thing
    When we create an instance of HashMap, under the hood, an array of Node<K,V> type is created of size initial capacity.
        k -> key, V -> value
    Capacity represents the size of the array. The default initial capacity is set as 16.
    There is another parameter that impacts the performance of the HashMap which is load factor. Its default value is 0.75
    Load factor is a measure of how full the hash table or array is allowed to get before its capacity is automatically increased.
    When the number of entries in the hash table exceeds the product of the load factor and the current capacity, the hash table is rehashed
    (that is, internal data structures are rebuilt) so that the hash table has approximately twice the number of buckets.

    Size increases in the powers of 2. We start off with 16 (which is the default initial capacity and load factor of 0.75) which means as soon as we try to add 13th key value pair in our map,
    rehashing takes place. In Rehashing, a new array twice the size of current capacity is created and all the keys are rehashed and then taken modulo with the new size of the hashtable to determine their new indexes.

    For hashing, HashMap does NOT use any cryptographic hashing algorithms like MD5, SHA1, SHA256 etc.
    Instead, HashMap uses:
        whatever hashCode() provides + a simple bit mixing (hash spreading) function.
        This is called supplemental hashing - NOT a hashing algo in the traditional cryptographic sense.

    Every java object has : int hashCode()
        That integer is the base hash used by the HashMap.

    HashMap applies a supplemental hash spreading step to improve the hash distribution
            hash = h ^ (h >>> 16)
        where
            h = key.hashCode()
            >>> = unsigned right shift
            ^ = XOR

    This mixes high bits into low bits to reduce collisions(just simple bit manipuation)

    Why does HashMap do this?
    - Fast hash computation
    - Good uniform distribution
    - Low collision rates

    Cryptographic or secure hash functions are too slow.

//    Summary:
//        HashMap does not use a specific hashing algorithm.
//        It relies on the key’s hashCode() implementation and then applies a lightweight bit-mixing function (h ^ (h >>> 16)) to reduce collisions.
//        Finally, it computes the bucket index using hash & (capacity-1).

    Final index calcuation:
    int idx = hash(key)%capacity           // optimised this step below using bitwise &
    Taking modulo capacity, ensures that the idx value is between 0(inclusive) to capacity - 1 (inclusive)

    Lets assume - we are trying to insert a key value pair
    map.put("Shishir", 25);
    Follwing steps will take place:
        - 1. Hash Computation  - compute the hash value of your Key
        - 2. index calculation - hashValue % capacity where capacity represents the current size of your array/hash table
        - 3. if bucket is empty  - simply insert the node
             if table[idx] == null:
                table[idx] = new Node(hash, key, value, null);
             No collision. Done.
        - 4. Collision Detected: If bucket already has nodes (say linked list or tree)
            - Compare current node''s hash with key''s hash
            - if equal, compare keys using .equals()
            - if same key -> overwrite value
            - if not same key -> go to next node.
        - 5. Insert at the end of chain
            - In a linked list: head → node1 → node2 → null
            - New node added at tail ->  head → node1 → node2 → newnode -> null

            The list can grow in size if there are a lot of collisions. Collision happens when the HashMap produces the same index for each key.
    The distribution of keys is not uniform, and they all are forced to be stored in the same bucket / index
        Collision happens when the HashMap tries to insert a node at table[index], but table[index] != null (i.e it already contains some node(s)).
            Now, if the node(s) key is same as the key (which we are tryin to insert), it will overwrite the value. else it will move to the next node and again comapre the hash & key.
            This goes on until we reach the end of the list. Once we are at the end -- this means no node existed in the bucket with this key, and thus a new node is created and inserted at the end.

        ex: illustration of how the HashMap stores the data internally.
        For simplicity, I have taken an array of size 3. Notice how a list is stored at each index. This illustrates the collision part.
        All the nodes at a particular index, basically got the same index -- and thus were arranged in the form of a list.
        If this list grows too large in size, then the list gets converted to a RBT (There is a TreeifyThreshold parameter, which has a default value of 8)

            |node1| -> node2 -> node3 -> node4
         0  |_____|
            |node4| -> node5 -> node6 -> node7
         1  |_____|
            |node8| -> node9 -> node10 -> node11
         2  |_____|

        - 6. Treeify if needed - This is done to optimise the lookups. If the list grows too long, then searching becomes O(n).
                Benefit of converting it to a RBT is it converts the list into a binary tree like structure and reduces the complexity to O(logn)
             - if a bucket size >= 8
            LinkedList is converted to a RedBlackTree (treeify)
        - 7. Check Load Factor -> Resize
            if current size > capacity * load factor
            Resize table
                Double size (eg: 16 -> 32)
                Rehash or redistribute nodes (java8 optimises this so not all nodes need full rehash)
            This is O(N)

    Lets say we are trying to retrieve a key value using get(K key):
        - 1. Hash Computation  - compute the hash value of your Key. The hash function always generates the same hash for a particular key
                - Hash computation relies on key''s hashCode() method plus applies some bit mixing on top of it to produce the hash.
                - This bit mixing is done to ensure uniform hash distribution (so that not all the keys are assigned the same index. This reduces the collision)

                There is a contract b/w hashCode() and .equals() method. hashCode() is used to generate the hash and .equals() is used to compare the nodes.

                a. if obj1 == obj2, then their hash should also be same.
                    - for instance, during put(5, "some_val"), whatever hash it produces for 5, during get, it will also produce the same hash.
                    This will help us to go to a particular index. No matter how many times, we try to generate the hash for 5, it will always produce the same value

                b. if 2 object''s hash is same, it doesn''t mean that 2 objects are equal.
                    meaning - if the hash value is same, it does not mean that the objects are same. This is the reason why we dont rely solely on the hash value.
                            We compare both hash and the value(which is key) during get() operation.
                    for instance, get(5), get(6) -> 5 and 6 both might produce the same hash. Hence we never rely on hash, it is important to compare key''s as well.

        - 2 Compute bucket index - using the hashvalue which we computed in prev step, take its modulo with capacity. This will give you the bucket index
        - 3. Check first node at table[index]
            - if bucket empty --> return null
            - if first node's key equals the given key -> return value
        - 4. Collision -> Traverse list or tree
            - if linkedlist walk node by node(using the next ptr) and compare the hash value and key
                node = node.next
                Compare: if(node.hash == hash && node.key.equals(key))      --- DOUBT ? Why we need to compare both key and hash here?
                Return value if match
            - if RBT: Use tree search
                compare hash -> go left/right
                TC: O(logn)
        - 5. if not found -> return null

    How the HashMap ensures O(1) average tc for get() and put() operations?

     HashMap maintains O(1) average time for get() and put() by controlling collisions.
     The load factor (default 0.75) determines when to resize the table.
     When entries exceed capacity * loadFactor, the table is doubled in size
     and all existing keys are rehashed and redistributed.

     This prevents too many entries from accumulating in the same bucket.
     After resizing, most buckets contain zero or one entry, so lookups and inserts
     remain constant time (O(1) average). Treeification (Java 8+) converts long
     collision chains into balanced red–black trees, preventing worst-case O(n).

//     Assume we have a HashMap with an initial capacity of 3, and we try to insert 10 keys.
//     Naturally, many collisions will occur because multiple keys will map to the same few buckets.
//     The load factor (default 0.75) helps maintain the HashMap’s average time complexity.
//     Once the number of stored entries exceeds 75% of the current capacity, the HashMap automatically doubles its size,
//     then rehashes and redistributes all existing entries into the new table.

        |node1| -> node2 -> node3 -> node4
     0  |_____|
        |node4| -> node5 -> node6 -> node7 -> node14
     1  |_____|
        |node8| -> node9 -> node10 -> node11 -> node12 -> node13
     2  |_____|

     If the HashMap’s capacity is very small—say 3—and you insert 100 key-value pairs,
     many collisions will occur. As more keys map to the same bucket, the linked list (or tree)
     inside that bucket grows longer. This increases the time required for both searching
     and inserting because the HashMap must traverse more nodes.

     The load factor prevents this degradation. Once the number of stored entries crosses the load-factor
     threshold (default 0.75), the HashMap automatically expands its capacity. When the table grows,
     all existing entries are rehased and redistributed across the new, larger number of buckets.
     With more buckets available, collisions drop significantly, chains become shorter,
     and both get() and put() operations continue to run in average O(1) time.

            |node1| -> node2 -> node13
         0  |_____|
            |node4| -> node5 -> node6
         1  |_____|
            |node8| -> node9 -> node10
         2  |_____|
            |nodex|-> node3 -> node4
            |_____|
            |nodey|-> node7 -> node14
            |_____|
            |nodez|-> node11 -> node12
            |_____|

    But is the load factor alone enough to guarantee optimal performance?
    Not necessarily.
    Even if the HashMap has plenty of capacity — thanks to resizing triggered by the load factor
    — you can still end up with collisions. In the worst case, every put() operation might
    hash to the same bucket. This would create a very long linked list, degrading the
    performance of both get() and put() to O(n).

    To prevent this, HashMap uses another mechanism called TREEIFY_THRESHOLD,
    whose default value is 8. When a particular bucket experiences repeated collisions
    and the linked list stored in that bucket grows beyond this threshold, HashMap converts
    that linked list into a balanced binary search tree (specifically, a Red-Black Tree).
    This reduces the lookup and insertion time for that bucket from O(n) to O(log n).

    Note: Although AVL Trees and Red-Black Trees are both balanced BSTs, we prefer RBTs because they give
    HashMap the best tradeoff between lookup speed and modification cost. They are slightly slower than
    AVL for pure lookups but significantly cheaper for inserts/deletes & requires less bookkeeping per noode.
    This makes them a superior practical choice inside a hash table that must handle frequent updates and occasional
    treeification.

    AVL vs RBT
    1.AVL keeps a tighter balance (height difference ≤1) so insertions and deletions
        require more rotations to restore strict balance.
      RBTs allow a looser balance (longest path ≤ 2× shortest), so they typically
        need fewer rotations on inserts/deletes.
      In a HashMap bucket that’s being updated often (many collisions, many puts/polls),
        fewer rotations = lower cost per modification.

    2. AVL gives slightly faster searches because it’s more strictly balanced (smaller height).
       But HashMap lookups are already O(1) on average due to hashing — the extra gain from AVL’s smaller height is marginal.
       The overall cost for mixed workloads (reads + writes) favors RBT:
       slightly slower reads but faster writes and lower costs for maintaining balance.


    The average time complexity for insertion, deletion, and searching in a HashMap is O(1).
    In the worst case—when collisions occur and a bucket is implemented as a linked list—the
    time complexity degrades to O(n) because you may need to traverse the entire list.

    However, modern HashMap implementations rarely suffer from this worst-case scenario.
    Once the number of nodes in a bucket exceeds a certain threshold, the linked list is automatically
    converted into a Red–Black Tree, which is a self-balancing binary search tree. This reduces the
    worst-case time complexity for operations on that bucket from O(n) to O(log n).

    As a result, HashMap maintains efficient performance even under heavy collision scenarios.



    One thing to note is : all the nodes that are part of one bucket(in linkedlist or RBT tree) will have different hash values but their (hashValue % capacity) is same which is equivalent to bucket index.
    That is the reason all those keys were mapped to the same index.
    There is one to one correspondence between a key and hash value. A key will always generate the same hash no matter what.
    Different keys will generate different hashes, but their hashes % capacity can collide (meaning they all are part of the same table[index] and are stored in the form of a LinkedList or a RBT when bucket.size >= 8)
    Initially the nodes will follow a LinkedList setup, but as soon as the list grows and its size crosses the treeify threshold, linkedlist is converted into a Red Black Tree
    Why RBT? Scanning a list is expensive, its O(N). But scanning a RBT , that is O(logn) which is way more efficient than the linked list


    Time Complexities

    Operation	Amortized / Avg Case	Worst Case
    put()	      O(1)	                    O(log n) (tree) or O(n) (list)
    get()	      O(1)	                    O(log n) (tree) or O(n) (list)
    resize()	  O(n)	                    N/A

    In worst case, its generally O(logn) - because of the treeify operation, the linkedlist gets converted into a Red black tree.


    ** Optimization **

    For index computation, we were using hash % n  --> but there is a much faster & more efficient way of doing the same thing & this uses bitwise & operator.
    Understanding why hash % (n-1) works is pretty important.

    So instead of hash % n do this hash & (n - 1)

    But remember this logic works only if n is a power of 2. This is the reason, why the size of hashtable or the underlying array is always a power of 2

    hashmap capacity is always;
            16, 32, 64, 128 ....

    capacity =2^k

    n - 1 = 2^k - 1

    Which in binary looks like:

    n (capacity)	binary	n – 1	binary
        16	        10000	15	    01111
        32  	    100000	31  	011111

    Why hash & (n - 1) works for index calculation?

    hash & (n - 1) means take only the last k bits of the hash,
    because (n - 1) is k 1's

    Example: n = 16 ->  n - 1 = 15 -> binary 01111
    hash = 101101010101 (binary)

            101101010101   (hash)
            &     001111   (n - 1 = 15)
            -----------------
                  000101     (index)

    index range [0 to 15], why?
    The index will be 01111 which is 15 and this will happen only if the last k bits are set in the hash.
    and if all the last k bits are 0 in the hash, then the index will be 0.

    This proves that index value will always lie between [0,15] or [0, n-1] which is exactly what mod n used to guarantee.

    Hence its proved that hash & (n - 1)  == hash % n if n is a power of 2.

    This is the reason, why HashMap forces capacity to be powers of 2.

    Why % is expensive and & is cheap?
        - % does integer division, which is CPU intensive and slow. Involves multiple clock cycles.
        - & is a bitwise operator which are extremely fast because they involves single cycle CPU operations.

    Thus the performance difference is huge.










    The capacity is the number of buckets in the hash table, and the initial capacity is simply the capacity at the time the hash table is created.
    load factor is a measure of how full the hash table is allowed to get before its capacity is automatically increased.
    When the number of entries in the hash table exceeds the product of the load factor and the current capacity, the hash table is rehashed
    (that is, internal data structures are rebuilt) so that the hash table has approximately twice the number of buckets.


    A HashMap instance, has 2 parameters that affects its performance - loadFactor & initialCapactiy.
    When we create a HashMap instance, under the hood a hashtable / array is created of size initial capacity. which is by default 16
    and the load factor by default is 0.75

    This hashtable/array is of Node<K,V> type --> Node<K,V>[] table
    Each node contains 4 things: final int hash, final K key, V value, Node<k,V> next (next pointer)
    ---------------------------------------------
    |   |   |   |
    ---------------------------------------------




//    methods:
//    1. size()
//    2. isEmpty()
//    3. containsKey(Object key)
//    4. get(Object key)
//    5. put(K key, V value) -> if the key is already present, it will overwrite the value
//                        -> if the key is absent: simply adds the key value pair into the map
//    6. remove(Object key)


    Ask GPT :
      why Map is not part of the Collection Hierarchy?


    public static void main(String[] args){
        // have not passed any parameters in the constructor.
        // default size of Node<K,V>[] table is 16, load factor = 0.75
        Map<Integer, String> mp = new HashMap<>();
        // NOTICE: In HashMap, I can have key as null and value as null
        // put() finds the int idx = hash(key) & (n - 1)
        // the key,value pair will go at index idx in the hashtable
        mp.put(null, "TEST");
        mp.put(0, null);
        mp.put(1, "A");
        mp.put(2, "B");

        System.out.println("size: " + mp.size());

        for(Map.Entry<Integer, String> entry : mp.entrySet()){
            Integer key = entry.getKey();
            String val = entry.getValue();
            System.out.println("Key: "+  key + " value: "+  val);
        }

        // putIfAbsent() -- writes data in 2 scenarios
        // if the key is not present in the HashMap or if value of a key is null, it inserts that key,value pair.
        mp.putIfAbsent(null, "test");  // null key is already present, but it's value is not null. So this will not be inserted
        mp.putIfAbsent(0, "zero"); // key exists and its value is null, write this data
        mp.putIfAbsent(3, "c"); // key does not exist, writes this data

        System.out.println("size: " + mp.size());

        // iterate over map  - mp.entrySet() returns a list of entries
        // iterating over those entries one by one
        for(Map.Entry<Integer, String> entry : mp.entrySet()){
            Integer key = entry.getKey();
            String val = entry.getValue();
            System.out.println("Key: "+  key + " value: "+  val);
        }

        // isEmpty()
        System.out.println("isEmpty(): " + mp.isEmpty());

        // containsKey(key)
        System.out.println("containsKey(3): " + mp.containsKey(3));

        // get(key)
        // first computes the hash, the computes index - int index = hash & (n-1)
        // goes to index in the hashtable, iterates over the list or tree and do .equals()
        // compares the hash and key
        System.out.println("get(3): " + mp.get(3));

        // getOrDefault - if the key exists, returns its value, else returns the default value
        System.out.println("getOrDefault(9, 100): " +  mp.getOrDefault(9, "100"));

        // removeKey()
        System.out.println("remove(null): " + mp.remove(null));

        // iterate over map
        for(Map.Entry<Integer, String> entry : mp.entrySet()){
            Integer key = entry.getKey();
            String val = entry.getValue();
            System.out.println("Key: "+  key + " value: "+  val);
        }

        // keySet() - returns a list of all the keys
        for(Integer key: mp.keySet()){
            System.out.println("key: " + key);
        }

        // values() - returns a list of all the values
        for(String val: mp.values()){
            System.out.println("value: " + val);
        }

    }



}
