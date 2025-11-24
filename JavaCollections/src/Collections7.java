import java.util.*;          // for List, Map, Set, Arrays, Collections etc.
import java.util.stream.*;   // for Stream, IntStream, Collectors, etc.
// we need both import statements. java.util.* is not enough .
// It only imports classes under util, but it does not include subpackages, such as java.util.stream
// java does not recursively import subpackages

public class Collections7 {
    // Streams - VVIMP for Interviews

//    Java Streams (introduced in Java 8) provide a functional, declarative, and pipeline-based way
//    to process collections (and other data sources) efficiently.
//
//    We can think of a Stream as a processing pipeline through which the elements
//    of a collection flow. As each element moves through the pipeline, various
//    operations—such as filtering, mapping, or sorting—can be applied.
//
//    Streams are especially useful for bulk data processing. They allow us to
//    express complex operations more cleanly and can take advantage of parallel
//    processing when working with large datasets.
//
//    If you apply streams to a collection with only a few elements, the benefits are minimal
//    because the overhead of the stream pipeline outweighs the gains.
//    The real power of streams becomes evident when handling large volumes of data
//    or performing multi-step transformations.
//
//    Just for clarity: a stream is not a data structure, its not a collection replacement.
//    It is a pipeline of operations applied to a data source. We are not storing elements in a stream.
//    We are simply processing them.

                Step 1                      Step 2                              Step 3
    Collection ----------> Create Stream ------------> Intermediate Operations ---------------> Termainl Operations

// Step 1:
//   - A Stream is created from a data source such as a Collection, array, or other input.
//   - At this stage, no processing happens. The stream is simply an abstraction over the data.
//
//  Step 2:
//   - Intermediate operations are defined (e.g., filter(), map(), sorted()).
//   - Each intermediate operation produces a new Stream, enabling further operations to be chained together.
//     A key advantage of this design is that the original data source remains untouched—no mutation or modification
//     happens to the underlying collection.
//   - These operations are lazy: they do not execute immediately. They are recorded and will run only when a terminal operation is invoked.
//
//  Step 3:
//   - A terminal operation is executed (e.g., collect(), forEach(), reduce()).
//   - This triggers the actual processing of the entire stream pipeline from start to finish.
//   - Terminal operations consume the stream, meaning the same stream cannot be reused afterward.

    ex:

// normal way
    public static void main(String[] args){
        Integer[] arr = {3000, 4100, 9000, 1000, 3500};
        int cnt = 0;
        for(int i = 0; i < arr.length; i++){
            if(arr[i] > 3000) cnt++;
        }
        System.out.println("Total Employees with salary > 3000: " + cnt);
    }

//    using streams
    public static void main(String[] args){
        List<Integer> arr = new ArrayList<>();
        arr.add(3000);
        arr.add(4100);
        arr.add(9000);
        arr.add(1000);
        arr.add(3500);

        // count() terminal operation returns long
        // arr.stream() --> creates a stream
        // then you specify zero or more intermediate operations
        // one final terminal operation
        // stream processing wont happen until a terminal operation is invoked.
        // This Stream cannot be reused, because once a terminal operation is called, it consumes the stream. Thus it wont be available
        long cnt = arr.stream().filter((Integer val) -> val > 3000).count();
        System.out.println("Total Employees with salary > 3000: " + cnt);
    }


//    Different ways to create a Stream

    public static void main(String[] args) {
//    1. From collection
        List<Integer> salaryList = Arrays.asList(3000, 4100, 9000, 1000, 3500);
        Stream<Integer> streamFromIntegerList = salaryList.stream();

//       2. From Array
        Integer[] salaryArray = {3000, 4100, 9000, 1000, 3500};
        Stream<Integer> streamFromIntegerArray = Arrays.stream(salaryArray);

//       3. From Static method
        Stream<Integer> streamFromStaticMethod =  Stream.of(3000, 4100, 9000, 1000, 3500);

//      4. From StreamBuilder
        // Builder is a sub interface of Stream interface
        // step1: create Stream.Builder object
        Stream.Builder<Integer> streamBuilder =  Stream.builder();
        // step2: add whatever values you wanna add to Stream.Builder
        streamBuilder.add(1000).add(9000).add(3500);

        // step3: build the stream
        Stream<Integer> streamFromStreamBuilder = streamBuilder.build();

//        5. From Stream Iterate
        // Stream.iterate() returns an infinite sequential stream starting from seed value
        // used .limit(5) to restrict the stream to 5 elements
        Stream<Integer> streamFromIterate =  Stream.iterate(1000, (Integer val) -> val + 5000).limit(5);

    }


//  Different Intermediate Operations
//     We can chain multiple intermediate operations before applying terminal operation to produce the result

    public static void main(String[] args) {
    // 1. Filter(Predicate<T> predicate) --> filters the element
        // creates a Stream<String> using Stream.of(...) static method
        Stream<String> nameStream = Stream.of("HELLO", "HOW", "ARE", "YOU", "DOING");
        // filter() takes a Predicate, which is a functional interface containing
        // a single abstract method: boolean test(T t).
        //
        // While processing the stream, filter() applies this predicate to each element.
        // Only the elements for which the predicate returns true are allowed to pass
        // downstream in the pipeline.
        //
        // The Predicate implementation is provided here using a lambda expression.
        // In this example, we retain only the strings whose length is <= 3.
        Stream<String> filteredStream =  nameStream.filter((String name) -> name.length() <= 3);

        // Terminal operation:
        // collect() triggers the execution of the stream pipeline and gathers the
        // filtered elements into a List<String>.
        List<String> filteredNameList = filteredStream.collect(Collectors.toList());
        // OUTPUT: HOW, ARE, YOU


    //  2. map(Function<T,R> mapper) --> used to transform each element
        Stream<String> nameStream = Stream.of("HELLO", "HOW", "ARE", "YOU", "DOING");
        Stream<String> filteredNames = nameStream.map((String name) -> name.toLowerCase());
        // OUTPUT: hello, how, are, you, doing


    //  3. flatMap(Function<T, Stream<R>> mapper)  --> used to iterate over each element of nested collection and helps to flatten it
        List<List<String>> sentenceList =  Arrays.asList(
            Arrays.asList("I", "LOVE", "JAVA"),
            Arrays.asList("CONCEPTS", "ARE", "CLEAR"),
            Arrays.asList("ITS", "VERY", "EASY")
        );

        Stream<String> wordStream1 = sentenceList.stream().
                flatMap((List<String> sentence) -> sentence.stream());
        // OUTPUT: I, LOVE, JAVA, CONCEPTS, ARE, CLEAR, ITS, VERY, EASY

        Stream<String> wordStream2 = sentenceList.stream()
                .flatMap((List<String> sentence) -> sentence.stream().map((String s) -> s.toLowerCase()));
        // OUTPUT: i, love, java, concepts, are, clear, its, very, easy


        // 4. distinct --> removes duplicate from the stream
        Integer[] arr = {1, 5, 2, 7, 4, 4, 2, 0, 9};
        Stream<Integer> arrStream =  Arrays.stream(arr).distinct();
        // OUTPUT:  1, 5, 2, 7, 4, 0, 9


        // 5. sorted(). --> sorts the elements
        Integer[] arr = {1, 5, 2, 7, 4, 4, 2, 0, 9};
        Stream<Integer> arrStream =  Arrays.stream(arr).sorted();
        // OUTPUT: 0, 1, 2, 2, 4, 4, 5, 0, 7, 9

        // sorts the stream in desc order (passed the comparator)
        Integer[] arr2 = {1, 5, 2, 7, 4, 4, 2, 0, 9};
        Stream<Integer> arr2Stream =  Arrays.stream(arr2).sorted((Integer val1, Integer val2) -> val2 - val1);

        // 6. peek(Consumer<T> action)  --> Helps you to see the intermediate results of each step
        // useful for debugging
        List<Integer> numbers =  Arrays.asList(2,1,3,3,4);
        Stream<Integer> numberStream = numbers.stream()
                // abstract method takes 1 arg and returns boolean
                .filter((Integer val) -> val > 2)
                // abstract method takes 1 arg and returns nothing
                .peek((Integer val) -> System.out.println(val))
                // abstract method T apply(R r),
                .map((Integer val) -> val * -1);

        List<Integer> res = numbersStream.collect(Collectors.toList());


        // 7. limit(long maxSize) --> truncates teh stream to have no longer than maxSize
        List<Integer> numbers =  Arrays.asList(2,1,3,3,4);
        Stream<Integer> numberStream = numbers.stream().limit(3);

        List<Integer> res = numbersStream.collect(Collectors.toList());
        // OUTPUT: 2,1,3

        // 8. skip(long n) --> skips the first n elements of the stream
        List<Integer> numbers =  Arrays.asList(2,1,3,3,4);
        Stream<Integer> numberStream = numbers.stream().skip(3);

        List<Integer> res = numbersStream.collect(Collectors.toList());
        // OUTPUT: 3, 4

        // 9. mapToInt(ToIntFunction<T> mapper)  ---> helps to work with primitive data type int
        List<String> strNumbers =  Arrays.asList("2", "1", "4", "7");
        // abstract method returns int (primitive datatype) -->  int applyAsInt(T value);
        IntStream numberStream = strNumbers.stream().mapToInt((String val) -> Integer.parseInt(val));

        // mapToInt() produces an IntStream, and IntStream does NOT have a collect(Collectors.toList()) method
        // collect() works only on a Stream<T>, not the primitive streams (IntStream, DoubleStream, LongStream)
        // primitive streams have their own terminal operations such as: toArray(), sum(), average(), boxed()
        // you need to convert IntStream to StreamInt using boxed() and then you can apply collect()

        // CANNOT reuse this stream -- since its already consumed above
        int[] numbersArr = numberStream.toArray();

        // List<Integer> res = numberStream.collect(Collectors.toList());        X invalid

        // first convert IntStream --> Stream<Integer> using boxed
//        List<Integer> res = numberStream.boxed().collect(Collectors.toList());

        for(int val: res) System.out.println(val);


        // 10. mapToDouble(ToIntFunction<T> mapper)  ---> helps to work with primitive data type double
        List<String> strNumbers =  Arrays.asList("2", "1", "4", "7");
        DoubleStream dblNumberStream =  strNumbers.stream()
                                        .mapToDouble((String val) -> Double.parseDouble(val));

        double[] dblNumberArr =  dblNumberStream.toArray();
        // OUTPUT: 2.0, 1.0, 4.0, 7.0

        // 11. mapToLong

    }


//    WHy we call intermediate operations lazy?
    public static void main(String[] args){
        List<Integer> numberList = Arrays.asList(2,1,4,5,3);
        Stream<Integer> numberStream = numberList.stream()
                .filter((Integer val) -> val >= 3)
                .peek((Integer val) -> System.out.println(val));

//        although we are calling peek(), but nothing would be printed, coz no action has been called.
//        stream processing wont happen unless an action is invoked

        // count is one of the terminal stream
        numberStream.count(); // at this point, the operations are actually invoked.
    }


//  Sequence of Stream Operations
    public static void main(String[] args){
        List<Integer> numberList = Arrays.asList(2,1,4,5,3);
        Stream<Integer> numberStream = numberList.stream()
                .filter((Integer val) -> val >= 3)      // stateless op
                .peek((Integer val) -> System.out.println("after filter: " + val))
                .map((Integer val) -> val * -1)         // stateless op
                .peek((Integer val) -> System.out.println("after negating: " + val))
                // the pipeline pauses here and buffers elements until the full input set for this stage is not known
                .sorted()       // stateful operation -- it will wait for all the elements to be available
                .peek((Integer val) -> System.out.println("after sorted: " + val));

        List<Integer> res = numberStream.collect(Collectors.toList());

        // Expected output
        after filter: 4
        after filter: 5
        after filter: 3
        after negating: -4
        after negating: -5
        after negating: -3
        sorted: -5
        sorted: -4
        sorted: -3

//     Actual Output
        after filter: 4
        after negating: -4
        after filter: 5
        after negating: -5
        after filter: 3
        after negating: -3
        sorted: -5
        sorted: -4
        sorted: -3

        // NOTICE: Observe the exact order in which the operations are executed.
        //
        // For each element, the Stream pipeline processes it as far as possible
        // through all *stateless* intermediate operations (e.g., filter(), map(), peek()).
        // These operations are applied element-by-element in a pipelined fashion.
        //
        // However, when the pipeline reaches a *stateful* intermediate operation
        // (such as sorted(), distinct(), limit(), or skip()), execution pauses.
        // A stateful operation must see all incoming elements before it can produce output,
        // so it buffers all the elements that reach it up to that stage.
        //
        // Only after the full input for that stage is available does the stateful
        // operation emit elements downstream, which is why the 'after sorted' output
        // appears only after all filtering and mapping work is completed.
        //
        //  BENEFITS OF EXECUTING IN THIS FASHION
        // 1. **High Efficiency Through Pipelining**
        //    Stateless operations run element-by-element without waiting for the entire dataset.
        //    This minimizes memory usage and avoids building temporary lists between steps.
        //
        // 2. **Lazy Evaluation Reduces Work**
        //    If a terminal operation short-circuits (e.g., findFirst(), anyMatch()),
        //    only the required number of elements are processed.
        //    This can dramatically improve performance on large datasets.
        //
        // 3. **Optimized Use of Stateful Operations**
        //    By buffering only when absolutely necessary (e.g., for sorted()),
        //    the Stream pipeline maximizes performance while still supporting operations
        //    that require global knowledge of the data.

    }


//    Key Charateristics
//    1. Streams are not collections
//        - A stream does not store anything. It simply processes the underlying data source on demand.
//
//    2. Streams are single-use
//        - Once a terminal operation is executed, the stream is consumed.
//
//    3. Streams are lazy
//        - Intermediate operations do NOT run until a terminal operation triggers them.
//
//    4. Streams do not modify the original collection.
//        - They produce a new result instead.
//
//    4. Streams can be parallel
//        - parallelStream() splits work into multiple threads using Fork Join framework.
//
//    5. Streams allow functional programming
//        - Operatinos like map, filter, reduce operate on elements as functions.


//    Why Streams were introduced?
//        - Promotes functional programming
//        - Provides declarative style
//        - Enables lazy & pipeline based processing
//        - Reduces boilerplate (loops, temp lists, counters)
//        - Supports parallel execution


//    Key Internals (Under the Hood)
//        - Streams use Spliterator API
//        - SplitIterator splits the data for parallelism
//        - Intermediate operations build a chain of Sink objects
//        - Terminal operations pulls data through chains
//        - No intermediate collection required


//    Type of Streams

//    Stream<T>       -> Generic Sequential Stream
//    IntStream       -> Primitive int stream
//    DoubleStream    -> Primitive double stream
//    LongStream      -> Primitive long stream

//    Primitive streams avoid boxing/unboxing overhead.


//    Stream Pipeline Structure
//    1. Source

//    2. Intermediate Operations (lazy)
//        - These return another Stream.
//        - ex: map(),
//            filter(),
//            sorted(),
//            distinct(),
//            limit(),
//            skip(),
//            flatmap(),
//            peek() (debugging only)

//    3. Terminal Operations (eager)
//        - These trigger execution.
//        - ex; collect(),
//            forEach(),
//            reduce(),
//            count(),
//            toArray(),
//            min(), max(),
//            anyMatch(), allMatch(), noneMatch()
//        - After this, the stream is consumed.


//    Lazy Evaluation
//        Streams use lazy evaluation, meaning


//    Short Circuiting Operations
//    - Some operations stop early like:
//        - findFirst() --> returns first match
//        - anyMatch() --> checks if any element matches
//        - limit(n). --> only takes n elements


//    Stateful vs Stateless Operations
//        - Stateless operations behaves independently per element
//            ex: map(), filter(), peek()
//        - Stateful ops depend on other elements
//            ex: sorted(), distinct(), limit(), skip()
//        - Stateful ops might require buffering or temporary storage.


//    Collector in Depth
//    - Collectors are prebuilt utilities that accumulate results.
//        ex: Collectors.toList();
//        Collectors.toSet();
//        Collectors.toMap();
//        Collectors.joining(",")
//        Collectors.groupingBy(...)
//        Collectors.partitionBy(...)


//    Parallel Streams
//        - Advantages
//            - CPU intensive tasks
//            - Effective for large datasets
//        Disadvantages
//            - Overhead for small workloads
//            - Requires stateless, non blocking operations
//            - Not good for I/O
//            - Order is lost unless using forEachOrdered()
//        - Fork/Join pool is used underneath


//    Summary
//    Java Streams are a functional, lazy, and pipeline-based approach to processing data.
//    They allow declarative operations like map/filter/reduce, support parallelism, forbid mutation,
//    rely on Spliterators for iteration, use lazy evaluation for efficiency, avoid intermediate containers
//    and provide powerful collectors for grouping and transformations.
//    Streams ensure high readability, composability, and maintainability of data-processing code.







    // what is the difference between List and Arrays? WHy do we have both ?
}
