import java.lang.reflect.Array;
import java.util.*;

public class Collections1 {
//    What is Java Collections Framework
//    - The Java Collections Framework provides ready-made data structures and algorithms to store, retrieve, manipulate,
//    and process groups of objects efficiently.
//    Collections is nothing but a group of Objects
//    JCF is present in java.util package
//    Framework provides us the architecture to manage these "group of objects" i.e add, update, delete, search etc
//    - It includes:
//          Interfaces → List, Set, Map, Queue, Deque
//          Implementations → ArrayList, LinkedList, HashSet, TreeSet, HashMap, PriorityQueue, etc.
//          Algorithms → sorting, searching, shuffling (via Collections utility class)
//          Utility Classes → Collections, Arrays
//    - JCF gives you optimized, reusable, tested data structures so you don’t have to build them yourself.

//    Why do we need Java Collections Framework?
//    1. To avoid reinventing data structures
//        Before JCF (pre-Java 2), developers wrote their own custom: linked lists, dynamic arrays, hash tables, stacks/queues
//        This was time-consuming and error-prone.
//        JCF provides standard implementations that are: efficient , consistent , well-tested.
//    2. To improve code quality & reusability
//        Collections share common interfaces, so code becomes standardized.
//    3. Rich set of built-in algorithms
//        Using Collections utility class, we get: sorting, binary search, min/max, synchronization wrappers, immutable collections
//        reverse/shuffle
//        All without writing logic from scratch.
//    4. Part of core Java — consistent & integrated
//        Collections are integrated with: Java Streams API, Generics,  Lambdas,
//        and Concurrency utilities (ConcurrentHashMap, CopyOnWriteArrayList)
//    4. Better performance & memory optimizations
//        Classes like HashMap and ArrayList are built with:
//        amortized constant-time operations, optimized resizing,  hashing & tree-based balancing (e.g., TreeMap, TreeSet)
//        These are optimized more than custom implementations.
//    5. To handle dynamic data
//        Arrays are fixed-size.
//        Collections like ArrayList, HashMap, and HashSet can grow or shrink as needed.

//    The Java Collections Framework is a unified set of interfaces, classes, and algorithms for working with groups of objects.
//    We need it because it provides efficient, reusable, and standardized data structures, avoids reinventing the wheel,
//    improves performance, supports dynamic data, and offers powerful built-in algorithms and utilities.


//    Java Collections Framework Hierarchy

//
//                                            Iterable (i)
//                                                |   extends
//                                            Collection (i)
//                                                | extends
//    -------------------------------------------------------------------------------------
//    |                                          List(i)  (implements)                     Queue (i)
//    |                                  --------------------------          (implements)/   | extends
//    |                                  |           |            |          PriorityQueue  Deque (i)
//    |                                  LinkedList ArrayList  Vector                       /    \
//    |                                                           | extends          ArrayDeque  LinkedList
//    |                                                          Stack
//    |             implements
//    Set (i) <--------------------------------
//     | extends             |           |
//    SortedSet (i)          HashSet     LinkedHashSet
//     | extends
//    NavigableSet (i)
//     | implements
//    TreeSet

//                implements
//    Map (i) <----------------------------------------------------
//     | extends                      |           |           |
//    SortedMap (i)                   HashMap     Hashtable   LinkedHashMap
//     |  extends
//    NavigableMap (i)
//     | implements
//    TreeMap (concrete class)

//    NOTE:
//      Entities marked with (i) represents interfaces, whereas the others are concrete classes
//      Map has a different hierarchy, it does not extend Iterable interface. In other words it's not part of Iterable or Collection.



//  Iterable : (used to TRAVERSE the collection)
    //    Below are the some of the popular approaches
    //  1. iterator() - it returns the Iterator object which provides below methods to iterate the collection.
    //        hasNext(): Returns true, if there are more elements in the collection
    //        next(): returns the next element in the iteration
    //        remove(): removes the last element returned by the iterator
    //  2. forEach()

//    public interface Iterable{
//        default void forEach(Consumer<T> action){
//            for(T t: this){
//                action.accept(t);
//            }
//        }
//    }



class Main {
    public static void main(String[] args) {

        List<Integer> valueList = new ArrayList<>();
        valueList.add(1);
        valueList.add(2);
        valueList.add(3);
        valueList.add(4);

        System.out.println("accessing through forEach method");
// 		valueList is an iterable, so it has forEach() method
//      forEach method accepts Consumer functional interface
//      we provide the implementation of the Consumer's abstract method using lambda expression
//      the abstract method accepts args, but returns nothing
//      lambda expression (or I can say the abstract method) is called for each element in the collection
//      forEach method iterates thru each element and calls the lambda expression for each element
        valueList.forEach(val -> System.out.println(val));

        System.out.println("accessing through iterator");
        Iterator<Integer> valuesIterator =  valueList.iterator();
        while(valuesIterator.hasNext()){
            int next = valuesIterator.next();
            System.out.println(next);
            if(next == 3){
                valuesIterator.remove();
            }
        }

        System.out.println("accessing through enhanced for loop");
        for(int val: valueList){
            System.out.println(val);
        }


    }
}


//    Collection vs Collections ?
//
//    Collection is part of Java Collection Framework, and its an interface, which expose various methods that are
//    implemented by various collection classes like ArrayList, LinkedList, Stack, PriorityQueue, ArrayDeque, HashSet etc.
//
//    Collections is a utility class( which means all the methods in it are static). These static methods
//    are used to operate on collections
//    Methods:
//        sort(), binarySearch(), get(), reverse(), swap(), copy(), min(), max(), shuffle(), rotate(), unmodifiableCollection()



    import java.util.*;

    class Main {
        public static void main(String[] args) {
            System.out.println("Try programiz.pro");
            List<Integer> valueList = new ArrayList<>();
            valueList.add(3);
            valueList.add(4);
            valueList.add(1);
            valueList.add(2);

            // collections is a utlity class which provides several utility methods like min, max, sort, swap, reverse, binarySearch, copy
            System.out.println("Min value: " + Collections.min(valueList));
            System.out.println("Min value: " + Collections.max(valueList));

            System.out.println("Sorting List");
            Collections.sort(valueList);
            valueList.forEach(val -> System.out.println(val));

            int key = 3;
            System.out.println("Using Binary Search to find key: " + Collections.binarySearch(valueList, key));

            System.out.println("Reversing List");
            Collections.reverse(valueList);
            valueList.forEach(val -> System.out.println(val));


        }
    }

// 		NOTE: src for below is javadoc - refer to individual interface's java doc for more details
//     Iterator
//         - boolean hasNext();
//         - E next();
//             - returns the next element
//         - remove()
//             - removes the last element returned by the iterator

// 		Iterable
// 		    - Iterator<T> iterator()
// 		    - void forEach(Consumer<? super T>  action)
//                - Consumer is a built-in functional interface (interface that has one abstract method)
//                - we provide the implementation of the abstract method using lambda expression


// 	    Collection
// 	        - root interface in the collection hierarchy.
// 	        - collection represents a group of objects, also known as its elements
// 	        - some collections allow duplicates, others do noted Some are ordered, and others are unordered
// 	        - empty collection (no arguments constructor)
//             - constructor with single argument of type Collection
//             // Query Operations
//             - int size();
//             - boolean isEmpty();
//             - boolean contains(Object o);
//                 - returns true if this collection contains the specified element.
//                 - throws:ClassCastException – if the type of the specified element is incompatible with this collection
//             - Iterator<E> iterator();
//                    - as Iterable interface added in java 1.5, so before this, this method was used to iterate the collection and still can be used.
//             - boolean add(E e);
//                 Returns: true if this collection changed as a result of the call
//                 Throws:
//                     UnsupportedOperationException – if the add operation is not supported by this collection
//                     ClassCastException – if the class of the specified element prevents it from being added to this collection
//                     NullPointerException – if the specified element is null and this collection does not permit null elements
//                     IllegalArgumentException – if some property of the element prevents it from being added to this collection
//                     IllegalStateException – if the element cannot be added at this time due to insertion restrictions
//             - boolean remove(object o);
//                  - Removes a single instance of the specified element from this collection, if it is present (optional operation).
//                  - Returns: true if this collection changed as a result of the call
//              - <T> T[] toArray();
//                 - Returns an array containing all of the elements in this collection
//             // Bulk operations
//             - void clear();
//                 - removes all the elements from the collection
//             - boolean containsAll(Collection c);
//                 - returns true if this collection contains all of the elements in the specified collection.
//             - boolean addAll(Collection c);
//                 - Adds all of the elements in the specified collection to this collection
//             - boolean removeAll(Collection c);
//                 - Removes all of this collection's elements that are also contained in the specified collection
//             // Comparison & Hashing
//             - boolean equals(Object o);
//             - int hashCode();
//                 - returns the hash code value for this collection
//                 - In particular, c1.equals(c2) implies that c1.hashCode()==c2.hashCode().
//                 -  any class that overrides the Object. equals method must also override the Object.hashCode method
//             - SplitIterator<E> splitIterator()
//             - Stream<E> stream() & Stream<E> parallelStream()
//                    - provides effective way to work with collection like filtering, processing data etc.


//         List
//             - allows duplicate elements
//             - ListIterator
//                 - List interface provides a special iterator called ListIterator that allows element insertion and replacement, and bidirectional access in addition to the normal operations that the Iterator interface provides
//             - Positional(indexed) access
//             - Search
//                 Use with caution (costly linear searches)
//             - Insert & Remove multiple elements
//             - Attempting to add an ineligible element throws an unchecked exception, typically NullPointerException or ClassCastException
//             // Query Operations
//             - int size();
//             - boolean isEmpty();
//             - boolean contains(Object o);
//             - Iterator<E> iterator();
//             - boolean add(E e);
//                 - appends the specified element to the end of this llist
//             - boolean remove(Object o);
//                 - removes the first occurence of the specified element from this list, if it is present(optional operation)
//                 - if element is not present, list remains unchanged
//             - void clear();
//             - Object[] toArray();
//                 - allocates a new array

//             - boolean containsAll(Collection c);
//             - boolean addAll(Collection c);
//                 - Appends all of the elements in the specified collection to the end of this list
//             - boolean addAll(int index, Collection c);
//                     - Inserts all of the elements in the specified collection into this list at the specified position
//             - boolean removeAll(Collection c);
//             - boolean retainAll(Collection c);


//             - default void sort(Comparator c)
//                 - Sorts this list according to the order induced by the specified Comparato
//                 - sorts the list in-place
//             - boolean equals(Object o);
//                 - two lists are defined to be equal if they contain the same elements in the same order.
//                 - The specified object should also be a list and both lists must have same size, and all corresponding pairs of elements should be equal then it returns true
//             - int hashCode();
//                 - returns the hash code value for this list.
//                  - This ensures that list1.equals(list2) implies that list1.hashCode()==list2.hashCode()

//              // Positional Access Operations
//             - E get(int index);
//                 - returns the element at the specified position in this list.
//                 - throws IndexOutOfBoundsException if (index < 0 || index >= list.size())
//             - E set(int index, E element)
//                 - replaces the element at the specified position in the list with the specified element
//                 - returns the element previously at the specified position
//             - void add(int index, E element)
//                 - inserts the specified element at the specified position in this list. (Shift the element currently at that position(if any) and any subsequent elements to the right)
//             - E remove(int index)
//                 - Removes the element at the specified position in this list. Shifts any subsequent elements to the left (subtracts one from their indice)
//                 - Returns the element that was removed from the list.

//             // Search Operation
//             int indexOf(Object o);
//                 - Returns the index of the first occurrence of the specified element in this list, or -1 if this list does not contain the element.
//             int lastIndexOf(Object o);
//                 - returns the last occurence index or -1 if the element is not present

//             // List Iterators
//             ListIterator<E> listIterator();
//             ListIterator<E> listIterator(int index);
//                 - returns a list iterator starting at the specified position in the list

//             // View
//             List<E> subList(int fromIndex, int toIndex);
//                 - Returns a view of the portion of this list between the specified fromIndex, inclusive, and toIndex, exclusive.
//                 - returned list is backed by this list, so non-structural changes in the returned list are reflected in this list, and vice-versa.

//             List<E> reversed()
//                 -- reverses the list
//                 -- inplace ?

//             // static methods
//             List<E> of()
//                 - returns unmodifiable list (empty list)






// 		Collections class (all these are static methods)
// 		    - void sort() - implement the Comparable Interface
// 		    - int binarySearch(list, key)
// 		        - uses binary search algo to find the key. list must be sorted
// 		        - return index of the search key, if it is contained in the list; otherwise  -(insertion point - 1)
// 		        - Note that this guarantees return value will be >= 0 if and only if the key is found
// 		        - TC: O(logn)
// 		    - void reverse(list)
// 		        - TC: O(n)
// 		        - modifies the list in-place
// 	        - void swap(list, i, j)
// 	            - swaps elements at specified positions in the list
// 	            - throws IndexOutOfBoundsException  if either i or j is out of range (i < 0 || i >= list. size() || j < 0 || j >= list. size()).
// 	        - void fill(list, val)
// 	            - TC: O(n)
// 	        - void copy(destList, srcList)
// 	            - copies all the elements from one list into another
// 	            - TC: O(n)
// 	        - T min(coll)
// 	        - T max(coll)       // coll stands for collection
// 	        - boolean replaceAll(list, oldVal, newVal)
// 	            - replaces all occurrences of one specified value in a list with another


}
