import java.util.function.Consumer;
import java.util.function.Supplier;

public class Interface4 {
//    covers functional interface and lambda expressions (java8 feature)       -- V.V.V.IMP

//    what is functional interface
//    what is lambda expression
//    How use functional interface with lambda expression
//    Advantages of functional interface
//    Types of Functional Interface
//        1. Consumer
//        2. Supplier
//        3. Function
//        4. Predicate
//    How to handle use case when functional interface extends from other interface (or functional interface)


//    What is functional interface?
//    - If an interface contains only 1 abstract method, that is known as Functional interface.
//      - Also known as SAM (single abstract method)
//      - @FunctionalInterface keyword can be used at top of the interface (but its optional)

    // since Bird interface only contains one abstract method -- added FunctionalInterface annotation
    //  This FunctionalInterface annotation restricts the interface to have more than one abstract methods.
    //  Even if you try to provide more than one abstract methods, it will throw compilation error
    @FunctionalInterface
    public interface Bird {
        void canFly();
    }

    // same ex: without annotation (NOTE: providing this annotation is completely optional. But its considered as best practise to use one if its satisfies the criteria)
    // this interface also has one abstract method, but we have not provided the annotation.
    // You can add more abstract methods here - there's no restriction
    public interface Bird {
        void canFly();
    }


    // In functional interface, only 1 abstract method is allowed, but we can have other methods like default, static method or Methods inherited from the Object class
    // ex2 - below interface is still a Functional Interface bcoz it contains only one abstract method. toString() is not considered an abstract method because
    @FunctionalInterface
    public interface Bird {
        void canFly();

        default void getHeight() {
            // default method implementation
        }

        static void canEat() {
            // static method implementation
        }

        // non abstract method
        String toString();  // Object class method (although it looks like abstract, but it isn't. Its implementation is provided by Object class)
        // Each class in java, directly or indirectly inherits the Object class. The class which will implement this interface will inherit the properties from Object class(directly or indirectly)
        // and the Object class provides the implementation
    }

    // NOTE: Imp thing to note here if within an interface you have a method from the Object class, then the child class does not need to implement this method, because its implementation is already provided in Object class
    // That is why these methods are not considered abstract
    public interface TestInterface {
        String toString();      // this is not an abstract method
    }

    public class TestClassImplements implements TestInterface {
        // Notice: we dont have to provide an implementation for toString() method
    }
    // this code works fine coz, TestClassImplements class extends Object class & Object class has the implementation for toString method.

    // So rule of thumb is, if you define an Object class method within your interface, you dont need to implement it in the child class


//  What are lambda expressions?
//       Lambda expresssion is a way to implement Functional interface.
//       Biggest advantage of lambda expressions is it reduces the no of lines of code, it helps us in minimizing the verbose.

//    Different ways to implement Functional Interface

//    method 1: (Basic approach, create class which implements the interface and provide the implementation)
    @FunctionalInterface
    public interface Bird {
        public void canBreathe();
    }

    public class Eagle implements Bird {
        @Override
        public void canBreathe() {
            System.out.println("Eagle implementation");
        }
    }

    public class Main {
        public static void main(String[] args) {
            Bird eagleObj = new Eagle();
            eagleObj.canBreathe();
        }
    }


//    method 2: using anonymous classes
//    Notice: how we dont need to create Eagle class explicitly. This is the speciality of anonymous classes
//    We directly created eagleObj, without even creating Eagle class. While creating the obj --> we provided the implementation of the abstract method
//    Internally it creates a class with some random name which implements the Bird interface (just like in method 1)
//    advantage of anonymous class - less verbose
    public class Main {
        public static void main(String[] args) {

            Bird eagleObj = new Bird() {
                @Override
                public void canBreathe() {
                    System.out.println("Eagle implementation");
                }
            };

            eagleObj.canBreathe();
        }
    }

//    method 3: using lambda expressions (This only works in case of Functional Interface(i.e interfaces with only one abstract method)
//    -> is known as arrow operator also known as lambda operator
//    if you have single line in the implementation, you dont even need {}
//    Lambda expressions works with only Functional interface bcoz, in functional interface its guranteed that you have just one abstract method. hence you dont need to know its name.
//    whatever args it accepts, pass it in here () -> {
        // here goes the implementation
//    }
    public class Main{
        public static void main(String[] args){
            // created eagleObj
            // provided the implementation of the Functional Interface abtract method using lambda expression
            Bird eagleObj = () -> {
                System.out.println("Eagle implementation");
            };

            eagleObj.canBreathe();
        }
    }

//    Types of Functional Interface (Already Built In and present in java.util.function)

//1. Consumer: represents an operation that accepts a single input parameter and returns no result
//   It essentially takes in one argument and does some processing on top of it, but returns nothing.
//   Consumer<T> can only accept one argument because its functional method accept(T t) is defined with a single parameter.
//   If you need to accept more arguments, Java provides BiConsumer<T, U>, and you can create custom functional interfaces (like TriConsumer) for more parameters

//    Consumer Functional Interface  -- only accepts one argument
    @FunctionalInterface
    public interface Consumer<T>{           // Generic interface
        void accept(T t);
    }

//    BiConsumer interface -- another built-in Functional interface which accepts 2 arguments
    @FunctionalInterface
    public interface BiConsumer<T,U>{
        void accept(T t, U u);
    }

//    TriConsumer custom interface
//    3 argument consumer
    @FunctionalInterface
    public interface TriConsumer<A, B, C>{
        void accept(A a, B b, C c);
    }

//   Usage
    TriConsumer<String, Integer, Double> triObj = (a,b,c) -> {
        System.out.println(a + ", " + b + ", " + c);
    };

//    triObj.accept("test", 10, 3.14);

//    ex:
    public class Main{
        public static void main(String[] args){
            Consumer<String> consumerObj = (String value) -> {
                System.out.println(value);
            };

            consumerObj.accept("Shishir");
        }
    }



//    2. Supplier: Represents the supplier of the result. Accepts no input parameter but produces a result.
    public interface Supplier<T>{
        T get();
    }

    public class Main{
        public static void main(String[] args){
//            always pass the datatype here while creating objects
//            Whatever type of value is passed (here String is passed), it's supposed to return datatype
            Supplier<String> supplierObj = () -> {
                return "Hey there!";
            };

//            or

            Supplier<String> supplier2 = () -> "Hey there"; // both are equivalent, but above one is more clear

            supplierObj.get();      // get() does not accept any argument, but returns an output of type T
        }
    }


//    3. Function: accepts one argument parameter, processes it, and produces a result
//    accepts a value of type T, and returns a value of type R
    @FunctionalInterface
    public interface Function<T, R> {
        R apply(T t);
    }

    public class Main{
        public static void main(String[] args){
            Function<Integer, String> integerToString = (Integer value) -> {
              return value.toString();
            };
            System.out.println(integerToString.apply(10));
        }
    }


//    4. Predicate: accepts one argument and returns the boolean
    @FunctionalInterface
    public interface Predicate<T>{
        boolean test(T t);
    }

    public class Main{
        public static void main(String[] args){
            Predicate<Integer> predicateObj = (Integer value) -> {
                return value == 0;
            };

            System.out.println(predicateObj.test(1));
        }
    }



//    Handle use case when Functional interface extends from other interface

//    Use case1: Functional interface extending non functional interface

    public interface LivingThing{
        public void canBreathe();
    }

    @FunctionalInterface
    public interface Bird extends LivingThing{
        void canFly();
    }
//    Here it will throw compilation error. Functional interface already has one abstract method. Extending LivingThing interface will lead to
//    passing of abstract methods of the parent interface to child interface. Since child interface is marked FunctionalInterface, it cannot have more than one abstract methods

//    Usecase 1.1 -- slight modification of above scenario: provided default method in the parent interface
    public interface LivingThing{
       default public boolean canBreathe(){
           return true;
       }
    }

    @FunctionalInterface
    public interface Bird extends LivingThing{
        void canFly();
    }
//    This works because at the end, the functional interface only has one abstract method

//  Usecase 2: Interface extending Functional interfaces
//        This works

//    Usecase 3: Functional interface extending other Functional interfaces
    @FunctionalInterface
    public interface LivingThing{
        public boolean canBreathe();
    }

    @FunctionalInterface
    public interface Bird extends LivingThing{
        void canFly();
    }
//    THis is invalid -- Bird will have 2 abstract method

    @FunctionalInterface
    public interface LivingThing{
        public boolean canBreathe();
    }

    @FunctionalInterface
    public interface Bird extends LivingThing{
        boolean canBreathe();
    }
//    But this is valid -- since both parent and child interfaces have the same abstract method

    public class Main{
        public static void main(String[] args){
//            Bird is a functional interface - i.e only 1 abstract method which does not accept any argument and returns a boolean
            Bird birdObj = () -> true;
            System.out.println(birdObj.canBreathe());
        }
    }
}
