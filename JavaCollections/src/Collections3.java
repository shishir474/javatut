import java.util.PriorityQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.PriorityBlockingQueue;

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




}
