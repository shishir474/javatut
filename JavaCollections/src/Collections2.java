import java.util.*;

public class Collections2 {
//    Queue
//    Queue is an interface, child of collection interface
//    Generally Queue follows FIFO approach, but there are exceptions like PriorityQueue
//    There are different flavours of Queue like PriorityQueue, Deque(ArrayDeque)
//    supports all the methods available in Collection + some other methods mentioned below

    // 1. Boolean add(E e)
    //    - Insert the element into the queue.
    //    - True if insertion is successfull and Exception if insertion fails
    //    - Null element insertion is not allowed will throw (NPE)

    // 2. Boolean offer(E e)
    //    - Insert the element into the queue.
    //    - True if insertion is successfull and false if insertion fails
    //    - Null element insertion is not allowed will throw (NPE)

    // NOTE: offer() is pretty much same as add. offer() returns false if insertion fails, whereas add returns an Exception
    // inserting Null is not allowed via both the methods, throws NPE (NullPointerException)

    // 3. E poll()
    //    - retrieves and removes the head of the queue.
    //    - returns null if the Queue is empty

    // 4. E remove()
    //    - retrieves and removes the head of the queue.
    //    - returns Exception(NoSuchElementException) if the Queue is empty

    // NOTE: poll() and remove() both retrieves and removes the head of the queue. ONly diff is if queue is empty poll will return Null
    //    whereas remove will throw exception.

    // 5. E peek()
    //     - Retrieves the value present at the head of the queue, but do not removes it.
    //     - Returns null if Queue is empty

    // 5. E element()
    //     - Retrieves the value present at the head of the queue, but do not removes it.
    //     - Returns Exception(NoSuchElementException) if Queue is empty

//    safe : offer(), poll(), peek() -> these methods are safe in the sense that they do not throw any exception.

//    Iterable (i) --> Collection (i) --> Queue (i) ----> PriorityQueue


    public static void main(String[] args) {
        // min heap - haven;t passed any comparator in the constructor
        // by default - it behaves like a min heap and orders the elemetns based on their natural order
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.add(3);
        pq.add(2);
        pq.add(1);
        pq.add(8);


        // printing elements of the pq: elements are traversed in level order fashion
        pq.forEach(val -> System.out.println(val));

        // heap will look something like this (insert then heapify to bring min/max element at the top)
        // 	    1
        // 	   3 2
        // 	 8

        // remove top element of the pq and print
        while (!pq.isEmpty()) {
            int top = (int) pq.poll();
            System.out.println("remove from top: " + top);
        }


        // max heap using PriorityQueue - just pass the Comparator in the constructor
        Comparator<Integer> c = (val1, val2) -> val2 - val1;

        PriorityQueue<Integer> pq1 = new PriorityQueue<>((val1, val2) -> val2 - val1);
        pq1.add(3);
        pq1.add(2);
        pq1.add(1);
        pq1.add(8);

        // remove top element of the pq and print
        while (!pq1.isEmpty()) {
            int top = (int) pq1.poll();
            System.out.println("remove from top: " + top);
        }

//        3 2 1 8
//        rule of thumb: if val2 - val > 0 then it will swap


        // Comparable vs Comparator
//        Comparable provides the default ordering inside the class (compareTo), while Comparator provides external/custom ordering using compare().
//        Comparable supports only one sort order; Comparator supports multiple.

        // note: int[] arr = {1,2,3}
        // & Integer[] arr = {1,2,3} they both are different. The first one is a primitive array (using primitive data types like int, double, char). They dont accept comparators
        // In order to use Comparator, we need to pass a non primitive arr like (Integer, String etc)

        System.out.println("sorting primitive int[] ascending order");
        int arr1[] = {1, 3, 2, 45, 4};
        Arrays.sort(arr1);
        for (int val : arr1) {
            System.out.println(val);
        }

        // System.out.println("sorting primitive int[] descending order");
        // To sort primitive arr in descending order, we have 2 options
        // a. sort the primitive array(this will sort in asc order) and then reverse it.
        // b. convert primitive int[] to Non primitive Integer[] and use comparator. We cannot use comparator with primitive arrays

        // Why we can't use Comparator with primitive arrays?
        // primitive types like int, double, char are not objects and they do not implement Comparable interface.

//        public interface Comparable{
//            int compareTo(T o);
//         }

//        @FunctionalInterface
//        public interface Comparator{
//            int compare(T o1, T o2);
//        }


//        I also saw equals() abstract method in Comparator interface. How is that possible?
//            Because every Java interface implicitly includes the public methods of java.lang.Object.
//                This is a rule of the Java Language Specification (JLS).
//                Meaning:
//            All interfaces automatically have equals(), hashCode(), and toString() as public abstract methods, even if they are not written in the code.
//        Why does this NOT break functional interface rules?
//            Functional interfaces can only have one abstract method—the SAM.
//            So why doesn’t equals() count?
//                ➡️ Because the equals() that interfaces inherit from Object does NOT count as an abstract method for SAM determination.
//                This is explicitly stated in the JLS:
//                    Methods that are implicitly declared in interfaces from Object do not count against functional interface abstract-method requirements.
//                So even though the interface looks like it has two abstract methods (compare() and inherited equals()), Java only counts:
//
//            ✔ compare() ← actual abstract method
//            ✖ equals() ← ignored for SAM counting

//        📌 Key Point: Interfaces don't extend Object but still inherit its methods
//        Interfaces do not extend Object (only classes do), but:
//            they implicitly declare Object methods as abstract
//            they can override them

        System.out.println("sorting non primitive Integer[] ascending order");
        Integer[] arr = {1,3,2,45,4};
        Arrays.sort(arr);       // by default, sorts in asc order
        for(int val: arr){
            System.out.println(val);
        }

        System.out.println("sorting non primitive Integer[] ascending order");
        // provided comparator: sorts in ascending order
        Arrays.sort(arr, (Integer val1, Integer val2) -> val1 - val2);
        for(int val: arr){
            System.out.println(val);
        }


        System.out.println("sorting non primitive Integer[] descending order");
        // provided comparator: sorts in descending order
        Arrays.sort(arr, (Integer val1, Integer val2) -> val2 - val1);
        for(int i = 0;i<arr.length; i++){
            System.out.println(arr[i]);
        }

//        Remember: Comparator only works with Non primitive type of arrays. You cannot use Comparator with primitive arrays.
//        Either you need to convert primitive -> Non Primitive and then apply the Comparator
//        or simply reverse the array after sort (works only for simple usecases like int[])

    }

// example demonstrating difference between Comparable and Comparator
//    - both are interfaces but have different usecases. Comparable is used to define the natural/default sorting order whereas
//    Comparator is used to define the custom sorting order
//    - Comparable provides compareTo(Object o) abstract method, whereas Comparator provides compare(Object o1, Object o2) abstract method
//    - No of sort orders supported: Comparable only supports one sort order which is Natural/Default sort order whereas
//    using Comparator you can define as many sort orders as you want
//    - Implementation Location: Comparable is implemented inside the class. You override the compareTo() method and provide your implementation.
//    whereas Comparator is implemented outside the class


    class Student implements Comparable<Student>{
        int marks;
        int age;
        String name;

        Student(int marks, int age, String name){
            this.marks = marks;
            this.age = age;
            this.name = name;
        }

        // Natural order: by marks
        @Override
        public int compareTo(Student s){
            return this.marks - s.marks;
        }

        @Override
        public String toString(){
            return "Student{" +
                    "marks: " + marks +
                    ", age: " + age +
                    ", name: '" + name + '\'' +
                    "}";
        }


    }


    class Main {
        public static void main(String[] args) {
            List<Student> studentList = new ArrayList<>();
            studentList.add(new Student(55, 21, "Shishir"));
            studentList.add(new Student(54, 23, "Rahul"));
            studentList.add(new Student(60, 22, "Ajay"));

            // the default sorting happens on marks
            Collections.sort(studentList);

            System.out.println("sort on default order - determined by Comparable's compareTo method");
            for(Student s: studentList){
                System.out.println(s);
            }


            System.out.println("custom sort (desc order of age) - determined by Comparator's compare method") ;

            Comparator<Student> byAge = (s1, s2) -> s2.age - s1.age;

            Collections.sort(studentList, byAge);
            for(Student s: studentList){
                System.out.println(s);
            }

            System.out.println("custom sort (asc order of name) - determined by Comparator's compare method") ;
            Collections.sort(studentList, (s1, s2) -> s1.name.compareTo(s2.name));
            for(Student s: studentList){
                System.out.println(s);
            }


        }

    }

//    So when I write:
    List<Integer> list = new ArrayList<>();
    Collections.sort(list);
//Since we did not pass a Comparator, Java will sort the elements based on their Natural ordering.
//    A type has "Natural Order" only if it implements
//    public interface Comparable<T> {
//        int compareTo(T o);
//    }
//    All non primitive types - Integer, String, Double etc implement Comparable interface and provides implementation of compareTo method
//based on which the sorting will happen.

//    So here in this scenario, it is using Comparable compareTo method to sort the array
//    whereas if we provide a comparator along with the list, sorting will happen based on the Comparator logic

//     Comparable only provides one natural/default sorting order using compareTo() method, but if you have custom sorting requirements you can leveerage Comparators



//   Understanding each component of -->  PriorityQueue<Integer> pq = new PriorityQueue<>();

//    new allocates memory for the object. It finds space in the heap to store your PriorityQueue object.
//    calls the constructor. The constructor is responsible for initializing the object. () represents the constructor calling.
//    Notice we aren't passing any parameters in (), so essentially it calls the no arg constructor
//    Post object creation and initialization, its reference is returned which is stored in pq
//    <> is part of Java Generics. Known as Diamond operator. It tells the compiler to infer the generic type automatically from the left hand side
//    so when we write:
//      PriorityQueue<Integer> pq = new PriorityQueue<>()
//    or
//    PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
//    both are equivalent - the first one is just shorter and cleaner. Before Java7, we used to follow the 2nd method, but since the introduction of Java Generics, we started using first approach

//    Generics allow you to define what type of Objects a collection can store
//    PriorityQueue<Integer> pq = new PriorityQueue<>();
//    means:
//        - the pq can only store integers. you cannot accidentally string
//        - you avoid ClassCastException
//        - you get compile time safety
//        - no need for explicit casting

//    Without Generics:
//    PriorityQueue pq = new PriorityQueue(); // raw type
//    pq.add("hello");    // allowed
//    pq.add(1); // allowed
//    int x = (int)pq.poll(); // runtime error possible

}
