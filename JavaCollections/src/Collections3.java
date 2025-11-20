import com.sun.jdi.IntegerValue;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.function.UnaryOperator;

public class Collections3 {
//    Deque & List

//    Deque stands for Double Ended Queue. Means addition and removal can be done from both sides of the queue
//            extends             extends         extends         implements
//    Iterable <------ Collection <--------- Queue <------- Deque <-------ArrayDeque
//                                             |implements
//                                           PriorityQueue
//    Methods available in Deque Interface
//    Methods available in Collection, Queue Interface + Methods available in Deque Interface

//    Queue:
//    add(), offer(), poll(), remove(), peek(), element()

//    Deque:
//                Throws Exception            Do not Throw Exception       Throws Exception            Do not Throw Exception
//    Insert      addFirst(e)                 offerFirst(e)                   addLast(e)                 offerLast(e)
//                                             return true/false                                          return true/false
//    Remove      removeFirst()               pollFirst()                     removeLast()                pollLast()
//                                            return null if deque empty                                  return null if deque empty
//    Examine     getFirst()                  peekFirst()                     getLast()                   peekLast()
//                                            return null if deque empty                                  return null if deque empty

//    Since Deque extends Queue interface, so all the methods available in Queue are also available in Deque
//    Below is the behaviour for each method of Queue interface
//    add() -> internally calls addLast() method
//    offer() --> internally calls offerLast() method
//    remove() --> internally calls removeFirst() method
//    poll() --> internally calls pollFirst() method
//    peek() --> internally calls peekFirst() method
//    element() --> internally calls getFirst() method

// Using these methods, we can also implement Stack (LIFO) and Queue(FIFO) both
//    TO use it as Stack, push() and pop() methods are available
//  push() --> internally calls addFirst()
//  pop() --> internally calls removeFirst()


//  ArrayDeque
//    - Concrete class which implements  the methods available in Queue and Deque Interface

    class Main {
        public static void main(String[] args) {
            ArrayDeque<Integer> dq = new ArrayDeque<>();

            // lets implement stack using dq
            // 4 3 2 1  -- stack uses addFirst() and removeFirst()
            dq.addFirst(1);
            dq.addFirst(2);
            dq.addFirst(3);
            dq.addFirst(4);

            System.out.println("Implementing stack using Deque");
            while(!dq.isEmpty()){
                System.out.println(dq.removeFirst());
            }

            dq.addLast(1);
            dq.addLast(2);
            dq.addLast(3);
            dq.addLast(4);

            // lets implement queue using dq
            // 1 2 3 4 -- addLast() and removeFirst()
            System.out.println("Implementing Queue using Deque");
            while(!dq.isEmpty()){
                System.out.println(dq.removeFirst());
            }


        }
    }


//    Time Complexity
// - insertion:  addFirst(), addLast(), offerFirst(), offerLast()
//        Amortized(Most of the time or Average) complexity is O(1) except few cases when queue is full.
//        When Queue is full, resizing happens internally and a new Queue is created of double the initial size, elements are copied into the new queue
//    and the reference is now changed to point to the new queue. In this case, complexity becomes O(n)
//        But since resizing happens infrequently, the amortized insertion cost remains O(1)
//
//    - Deletion: removeFirst(), removeLast(), pollFirst(), pollLast()
//        always O(1)
//
//    - Search : getFirst(), getLast(), peekFirst(), peekLast()
//        always O(1)
//
//    Space Complexity
//        O(n)


//    Collection      isThreadSafe        Maintains Insertion Order       Null elements allowed       Duplicate element allowed
//    PriorityQueue     No                  No (heapifies)                  No                              Yes
//    ArrayDeque        No                  Yes                             No                              Yes

//   What "not thread safe" actualy means?
//    If two or more threads try to:
//     - insert elements (offer, add)
//     - remove elements (poll, remove)
//     - peek at elements (peek)
//     - iterate over the queue
//    at the same time, the internal data structure (a binary heap) can become corrupted.
//    This can cause
//        - race conditions
//        - Inconsistent reads  - one thread sees a partially updated structure
//        - Data corruption - Internal array may be in an invalid state

//    Why PriorityQueue is not thread safe?
//     - No synchronized keyword
//     - No locking mechanism
//     -  No concurrent update protection
//    So operations like offer() and poll() manipulates indices and swap nodes in the heap.
//    If 2 thread do this simaltaneously --> could lead to broken structure

//   PriorityBlockingQueue -  Thread Safe version of Priority Queue
    PriorityBlockingQueue<Integer> pq = new PriorityBlockingQueue<>();
    pq.add(5);
    pq.add(2);

    System.out.println(pq.peek()); // 2

//    NOTE: since we have not passed comparator in the constructor while declaring PriorityBlockingQueue - it behaves like a min heap (thread safe version of min heap)

//    This is
//        - thread safe
//        - concurrent
//        - lock free on reads
//        - Uses internal locks for writes
//        - Designed for Producer-consumer scenarios

//    Summary
//    “PriorityQueue is not thread-safe” means that if multiple threads access and modify the same PriorityQueue instance
//    without synchronization, the internal heap structure can become corrupted.
//    Java does not provide built-in locking for PriorityQueue, so thread safety must be handled externally
//    or you should use PriorityBlockingQueue instead.”


//    ConcurrentLinkedQueue - Thread safe version of ArrayDeque
    ConcurrentLinkedQueue<Integer> dq = ConcurrentLinkedQueue<>();

    dq.addFirst(1);
    dq.addLast(5)
    System.out.println(dq.removeLast()); // 5



//    List
//    List is a ordered collection of objects, in which duplicate values can be stored

//    How does it differ from Queue?
//    - Queue is also a collectino of objects, but in queue insertion/removal/access can only happen at start or end of the queue.
//    - While in list, data can be inserted, removed, or access from anywhere.

//Methods available in List Interface
//    - all the methods from Collection interface + new Methods defined in the List interface
//
//    Collection methods as explained previously
//    1. int size()       -- reutrns the total no of elements in the collection
//    2. boolean isEmpty()
//    3. boolean contains(E e)
//    4. toArray()   -- to convert collection into an Array
//    5. add(E element) -- appends element at the end of the collection
//    6. remove(E element) -- removes the first occurrence of the element from the list
//    6. addAll(Collection c): appends all the elements of the specified collection in the end of the list.
//    7. removeAll(Collection c): Removes all the elements from the collections, which are present in the collection passed in the parameter.
//    8. clear()
//    9. equals()
//    10. stream() and parallelStream()
//    11. iterator()  - As iterable interface was added in java 1.5, so before this we used to use this method to iterate over the collection and still can be used

//New methods defined in the List interface:

//    1. add(int index, E element) - inserts the element at the specified position in the list by shifting the element and subsequent elements to right.
//    2. addAll(int index, Collection c)
//    3. sort(Comparator c) - sorts the list based on the Comparator
//    4. get(int index)   -- returns the element at the specifed position in the list
//    5. set(int index, Element e)  -- replaces the element at the specified position
//    6. remove(int index) -- removes the element at the specified position and shifts subsequent elements to the left.
//    7. indexOf(Object o) -- returns the index of first occurence of the specified element in the list.
//                        -1 if the list does not contain the element.
//    8. lastIndexOf(Object o) -- returns the index of last occurence
//    9. ListIterator<E> listIterator() -- listIterator returns the object of ListIterator
//                                        -- ListIterator(interface) extends from the Iterator(interface)
//                                    -- it has all the methods from the Iterator interface
//                                        -- hasNext()
//                                        -- next()       - returns the next element in the iteration
//                                        -- remove()    - returns the last element returned by the iterator
//
//
//                                    -- New methods which are introduced in ListIterator, which helps in backward iteration
//                                        -- hasPrevious()
//                                        -- E previous() -- returns the prev element and move the pointer position backward
//                                        -- int nextIndex() -- returns the index of next element (return size of the list, if at the end of the list)
//                                        -- int previousIndex() -- returns the index of prev element (return -1 if at the beginning of the list)
//                                        -- set(E e)
//
//
//    10. List<E> subList(int fromIndex, int toIndex)
//                -- fromIndex - inclusive
//                -- toIndex - exclusive
//        if fromIndex == toIndex, return sublist is empty
//        Any changes in sublist, will change in main list also and vice-versa


    class Main {
        public static void main(String[] args) {
            List<Integer> list1 = new ArrayList<>();
            list1.add(100);
            list1.add(200);
            list1.add(300);
            list1.add(0, 50);

            System.out.println("list1");
            list1.forEach(val -> System.out.println(val));

            List<Integer> list2 = new ArrayList<>();
            list2.add(400);
            list2.add(500);
            list2.add(600);

            list1.addAll(list2);

            System.out.println("list1 after addAll()");
            list1.forEach(val -> System.out.println(val));

            // replaceAll(UnaryOperator op)
            // UnaryOperator is a functional interface (which means it has one abstract method and we're gonna provide its implementation via lambda expressions)
            list1.replaceAll(val -> val * -1);

            // Why this does not work list1.forEach(val -> val * -1) ?
            // replaceAll() expects a UnaryOperator<T> whereas forEach() expects a Consumer<T>
            // A UnaryOperator<T> takes a value and returns a value of the same type
            // T apply(T t); // MUST return something

            // A Consumer does not return anything
            // void accept(T t); // MUST return void

            // You can use forEach to print values. But if you want to mutate the list using forEach --> You cannot
            // forEach() is not meant for modifying list elements

            System.out.println("list1 after replaceAll()");
            list1.forEach(val -> System.out.println(val));

            System.out.println("list1 after sort() in asc order");
            list1.sort((val1, val2) -> val1 - val2);
            list1.forEach(val -> System.out.println(val));

            // NOTE: passing compartor in list.sort() is manadatory
            System.out.println("get() element at 2nd index: " + list1.get(2));
            // throws IndexOutOfBoundsException if idx < 0 || idx >= list1.size()

            // You cannot access elements using [] in list
            // System.out.println("get() element at 2nd index: " + list1[2]);

            System.out.println("list1 after set()");
            list1.set(2, -10000);
            list1.forEach(val -> System.out.println(val));

            // remove() has 2 flavours / overloaded methods
            // remove(int index)
            // remove(Object o)
            System.out.println("list1 after remove(int index)");
            list1.remove(2);
            list1.forEach(val -> System.out.println(val));

            // remove -300
            System.out.println("list1 after remove(Object o) ");
            // list1.remove(-300);
            // the above line does not work bcoz literal -300 is a primitive int, so the compiler chooses remove(int index) and thus tries to remove the element at index -300, which is invalid -> IndexOutOfBoundsException
            list1.remove(Integer.valueOf(-300));
            // this removes the first Occurence of the object

            list1.forEach(val -> System.out.println(val));

            System.out.println("indexOf(Object o): " + list1.indexOf(-100));
            System.out.println("indexOf(Object o): " + list1.indexOf(-1000));
            // returns -1 if the element is not present, else returns the first occurence index

            System.out.println("indexOf(Object o): " + list1.lastIndexOf(-100));

            ListIterator<Integer> listIterator =  list1.listIterator();

            while(listIterator.hasNext()){
                System.out.println("Traversing forward: " + " Next index: " + listIterator.nextIndex() + " Previous index: " + listIterator.previousIndex() + " value " + listIterator.next());
            }

            while(listIterator.hasPrevious()){
                System.out.println("Traversing backward: " + " Next index: " + listIterator.nextIndex() + " Previous index: " + listIterator.previousIndex() + " value " + listIterator.previous());
            }


            System.out.println("sublist [1,4) :");
            List<Integer> subList = list1.subList(1,4);
            subList.forEach(val -> System.out.println(val));
            subList.set(0, 100);
            System.out.println("sublist after set() :");
            subList.forEach(val -> System.out.println(val));
            System.out.println("orig list after set() :");
            list1.forEach(val -> System.out.println(val));
            // Hence subList() just provides a view on top of orig list. Any changes made to origList will be reflected in subList and vice-versa.

            ArrayList
                    Integer
        }
    }

//    Time Complexity:
//    Amortized O(1): when inserting element at the end of the list. If the list is full and we try to insert an element at end, then a new ArrayList is created in the background, elements from the orig list are copied into the new list and then the new element is appended.
//        This is an O(N) operation, but this happens very infrequently.
//
//        When inserting an element at the specified index, it requires shifting the elements -- this is also O(N)
//
//    Deletion:  -- has 2 flavours remove(int index)
//                remove(Object o) --> removes first Occurence of Object o,
//        O(n), we have to shift all the values
//
//    Search
//        O(n)
//
//    Space Complexity:
//        O(n)

                    isThreadSafe        Maintains Order         Duplicates allowed          Nulls allowed
    ArrayList           No                  Yes                     Yes                         Yes

//    Thread safe version of ArrayList - CopyOnWriteArrayList

 import java.util.*;
 import java.util.concurrent.*;


    class Main {
        public static void main(String[] args) {
            List<Integer> list1 = new CopyOnWriteArrayList<>();
            list1.add(100);
            System.out.println(list1.get(0));
            System.out.println(list1.indexOf(100));
            System.out.println(list1.lastIndexOf(100));
            System.out.println(list1.indexOf(1000));
            System.out.println(list1.contains(100));
            list1.remove(Integer.valueOf(100));
            System.out.println(list1.size());

            System.out.println(Integer.valueOf(100));   // allowed
            System.out.println(Integer.valueOf("123")); // allowed -- this string can be parsed
            // System.out.println(Integer.valueOf("hello"));    // invalid throws NumberFormatException bcoz of failure to parse the string


        }
    }



//    LinkedList
//    - it implements both Deque and List interface
//    - Means it supports Deque methods like - getFirst(), getLast(), removeFirst(), removeLast()
//                                    +
//    - it also supports index based operations like list - getIndex(int index), remvove(int index), add(int index, Object o);

    class Main {
        public static void main(String[] args) {
            LinkedList<Integer> list = new LinkedList<>();
            // using Deque functionality
            list.addLast(100);
            list.addLast(200);
            list.addLast(300);
            list.addFirst(400);
            System.out.println(list.size());

            System.out.println("printing list using lambda expression");
            list.forEach(val -> System.out.println(val));

            System.out.println("printing list using for loop");
            for(int i = 0; i<list.size(); i++){
                System.out.println(list.get(i));
            }

            System.out.println("printing list using enhanced for loop");
            for(Integer val: list){
                System.out.println(val);
            }

            // Using list functionality
            LinkedList<Integer> list2 = new LinkedList<>();
            list2.add(100);
            list2.add(200);
            list2.add(300);
            list2.add(400);

            System.out.println("printing list2 using lambda expression");
            list2.forEach(val -> System.out.println(val));

            list2.add(1, 500);

            System.out.println("printing list2 using lambda expression");
            list2.forEach(val -> System.out.println(val));

            list2.remove(3);
            System.out.println("printing list2 using lambda expression");
            list2.forEach(val -> System.out.println(val));


        }
    }

//    Time Complexity:
//    insertion at start or end -  O(1): using Deque functionality addFirst(), addLast()
//    insertion at particular index - O(n) for lookup of the index + O(1) for adding
//
//    Deletion at start or end : O(1)
//    Deletion at specific index : O(n) for the lookup + O(1) for removal
//
//    Search: O(N)
//    Space Complexity:
//        O(n)

//                    isThreadSafe        Maintains Order         Duplicates allowed          Nulls allowed
//    LinkedList           No                  Yes                     Yes                         Yes

//    Like ArrayList, LinkedList is also not thread safe.



//  Vector
//        - Exactly same as ArrayList, elements can be accessed via index.
//        - But is thread safe. (uses synchronized blocks for all implementations -- this makes it thread safe)
//        - Puts lock when operation is perform on Vector
//        - Less efficient than ArrayList as for each operation it performing lock/unlock internally

    class Main {
        public static void main(String[] args) {
            Vector<Integer> v = new Vector<>();
            v.add(100);
            v.add(200);
            v.add(300);

            System.out.println(v.size());
            for(int val: v){
                System.out.println(val);
            }

        }
    }

//    Stacks
//    - Represents LIFO operation
//    - Since it extends Vector, its method is also Synchronized.

//    Stack can be implemented via Deque as well (we previously implemented this)
//        But Deque is not thread safe, Stack is.

    class Main {
        public static void main(String[] args) {
            Stack<Integer> s = new Stack<>();
            s.push(10);
            s.push(20);
            s.push(30);

            //   E push(E item)
            //   synchronized E pop()
            //   E peek()
            //   boolean empty()
            //.  int search(Object o)
            while(!s.empty()){
                System.out.println(s.pop());
            }
        }
    }
//    TC:
//    insertion: O(1) - always inserts at the top
//    Deletion: O(1)
//    Search: O(n)

//    SC: O(n)

//                  isThreadSafe                   Maintains Inertion Order         Duplicates allowed          Nulls allowed
//    Stack           Yes (extends Vector)               Yes                             Yes                         Yes


//    Why Vector<int> v = new Vector<>(); is invalid?
//    Because generics does not support primitive data types.
//    With Java generics you can only use Reference/Non primitive datatypes like Integer, Double,  Boolean etc.
//
//    Why can't generics use primitives?
//        Because Java generics rely on type erasure, and primitive types don't fit into that mechanism.
//        Only reference types (Integer, String, Double, etc.) can be used. Java provides wrapper classes so that primitives can be boxed automatically.
//
//    Why Java relies on type erasure for generics?
//        To ensure backward compatibility with pre java5 code. Before java5, generics did not exist. Collections looked like this:
//            List list = new ArrayList();
//            list.add("Hello");
//            list.add(10);       // totally allowed
//        When generics were introduced in Java 5:
//            List<String> list = new ArrayList<>();
//        Java needed this to still work with old JVMs and old .class files
//        Type erasure means:
//            - Generic types exist only at compile-time
//            - The compiler checks type safety
//            - At runtime, generics are removed (“erased”) → JVM sees raw types
//            - This allows code compiled with generics to still run on older JVMs.

//        Generics were designed so old bytecode doesn't break.

//    🎯 Why Were Generics Introduced?
//       Before Java 5, Java collections stored Object, and developers had to manually cast everything:
//      This caused:
//        ❌ Runtime errors
//        ❌ Lack of compile-time checking
//        ❌ Messy code with manual casting
//        ❌ No clarity about what the collection contains
//     Generics were introduced to solve these exact problems.

//    Advantage of Generics
//        - Compile time safety: Generics prevent type errors at compile time, not at runtime.
//        - No need for manual casting
//        - better code readability & intent declaration
//        - Eliminates many ClassCastException problems
//                Before generics: you could insert anything (String, Integer, Object) and the failure happens LATE (runtime)
//                With generics, errors are detected EARLY (compile time) improving reliability
//        - Allows creation of generic algorithms - one method works with any datatype. Generic algorithms reduces redundent code

//    Summary:
//      Generics were introduced to bring compile-time type safety, remove unnecessary casts, improve code clarity,
//      and allow the creation of reusable, type-independent algorithms.
//      They make Java collections safer and programs more robust by preventing ClassCastException at runtime.

//      NOTE: Generics in Java are implemented using type erasure.
//      The compiler enforces type safety and removes generic type parameters when generating bytecode, replacing them with raw types or bounds.
//      While the actual generic types are erased at runtime, some generic metadata is preserved in .class files for reflection.

//      Generic type parameters (like <T>, <Integer>, <String>) are removed during compilation and replaced with their bounds (Object or an upper bound like Number)
//      The resulting .class file contains no info about generic types at runtime (except for some metadata in reflection).
//      JVM does not see generics. it only sees raw types like List instead of List<String> or Map instead of Map<String,Integer>
//      But generics are not fully erased from .class file. Some of that info is preserved in the class metadata for reflection.
//            For ex: .class file still contains
//                - method signature with generics
//                - field signrature with generics
//                - annotation metadata
//                - generic bounds


//    How autoboxing converts int → Integer?
//      Autoboxing is the automatic conversion of a primitive (int, double, boolean …) into its corresponding
//      wrapper class (Integer, Double, Boolean, …) done by the compiler, not the JVM.
//      int x = 10;
//      Integer y = x; // autoboxing

//    This line -->  Integer y = x;
//    is converted by the compiler into --> Integer y = Integer.valueOf(x);
//    SO autoboxing is just syntactic sugar for calling valueOf()

//    Why does Java use Integer.valueOf() and NOT new Integer()?
//      Because valueOf() uses an internal cache for values between -128 and 127, improving memory efficiency and speed.
//      ex:
//      List<Integer> list = new ArrayList<>();
//      list.add(400);
//      Java rewrites it as : list.add(Integer.valueOf(400));

//    What about large values > 127 or < -127?
//      When you autobox a large int such as
//      Integer a = 1000;
//      Integer b = 1000;
//      Java still calls Integer.valueOf(1000). But the key point is 1000 is NOT in the Integer cache.
//      The default integer cache range is : -128 to +127
//      So for 1000: JVM checks the cache. -> not found. It creates a new Integer object. Each autobox of 1000 creates another separate object.
//      So a and b points to different objects

//            System.out.println(a == b); // false
//            System.out.println(a.equals(b)); // true

//      == -> compares references, different objects -> false
//      .equals() -> compares values -> true

//      We can also change the integer cache range using a JVM flag : AutoBoxCacheMax = 1000
//      This would cache integers from -128 to +1000


//    Autoboxing happens ONLY for these pairs:
//      Primitive	Wrapper
//      int	        Integer
//      long	    Long
//      double	    Double
//      float	    Float
//      char	    Character
//      byte	    Byte
//      short	    Short
//      boolean	    Boolean

//    Integer x = 10;
//    int y = x; // unboxing

}


