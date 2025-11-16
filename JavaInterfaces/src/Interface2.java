public class Interface2 {
    // why we need an interface?
    // 1. Abstraction: Using interface, we can achieve full abstraction means, we can define WHAT class must do, but not HOW it will do
    public interface Bird{
        public void fly();          // just provide signature, no implementation
    }

    public class Eagle implements Bird{
        @Override
        public void fly(){
            // implementaion goes here
            System.out.println("eagle fly implementation");
        }
    }

    public class Hen implements Bird{
        @Override
        public void fly(){
            // implementaion goes here
            System.out.println("eagle fly implementation");
        }
    }

    // 2. Polymorphism: interface can be used as a datatype.
    // We cannot create the object of an interface, but it can hold the reference of all the classes
    // which implements it. And at runtime it decides which method needs to be invoke.
    // This allows us to dynamically call the methods based upon the objects.
    // It decides at the runtime, which method should be called. Hence it also allows us to achieve polymorphism.

    // NOTE: You cannot create an object of interface (bcoz in interface you just provide method signature)
    // Only concrete classes can be instantiated.
    public class Main{
        public static void main(String[] args){
            Bird b1 = new Eagle();
            Bird b2 = new Hen();
            b1.fly();       // calls eagle's fly() method
            b2.fly();       // calls Hen's fly() method
        }
    }

    // 3. Multiple inheritance
    // first lets understand Diamond problem. Java allows at most one class to be extended, but you implement multiple interfaces
    public class WaterAnimal{
        public boolean canBreathe(){
            return true;
        }
    }
    public class LandAnimal{
        public boolean canBreathe(){
            return true;
        }
    }
    // Suppose you can inherit multiple classes -- what's the problem. lets analyse that
    public class Animal extends WaterAnimal, LandAnimal {
    }
    public class Main{
        public static void main(String[] args){
            Animal a = new Animal();
            a.canBreathe(); // now which method should be called here? We have 2 different versions provided by LandAnimal & WaterAnimal class?
            // In order to resolve this ambiguity, Java does not allow inheriting multiple classes,
            // because there can be a scenario like this where you have same methods provided by both the parent & child class is now confused and is not able to determine which one to call.
        }
    }

    // But this problem is not present with interfaces, because in interfaces you just provide the signature & not the actual implementation
    // class implementing the interface is responsible for providing the implementation of the method & thus there will always be one single version & therefore no ambiguity.
    public interface LandAnimal{
        public boolean canBreathe();
    }
    public interface WaterAnimal{
        public boolean canBreathe();
    }
    // Animal class implements both interfaces. Animal class has to provide the implementation of all the methods defined in interfaces LandAnimal & WaterAnimal -- allowed
    public class Animal implements WaterAnimal, LandAnimal {
        @Override
        public boolean canBreathe(){
            return true;
        }
    }
    public class Main{
        public static void main(String[] args){
            Animal a = new Animal();
            a.canBreathe(); // Here's there is no ambiguity. This simply calls the canBreathe() method of Animal class
        }
    }


//    Interface implementation
//    1. Overriding method cannot have more restrict access specifier. By default, the methods are public in interface, it cannot be declared protected in the concrete class.
//    2. Concrete class must override all the methods declared in the interface.
//    3. Abstract class can also implement the interface, but it is not forced to provide the implementation for all the interface methods.
//    Also abstract class can have its own set of abstract methods.
//    Concrete class inheriting the abstract has to provide the implementation of all the methods(from interface & abstract class)
//    4. A class can implement multiple interfaces, but a class can only extend one class. If a class explicitly extends another class, then that class becomes the parent.
//    if no explicit parent class is mentioned, then Object class is the Parent class. Either way, a class always extends exactly one class in java.
    public interface BirdSample{
        public void canFly();
    }
    public class EagleSample implements BirdSample{
        @override
        protected void canFly();     // X not allowed. Overriding method cannot restrict the access specifier
    }


    public interface Bird{
        public void canFly();
        public void noOfLegs();
    }

    public abstract class Eagle implements Bird{
        @override
        public void canFly(){
            // implementation goes here
        }

        public abstract void beakLength();

    }

    public class WhiteEagle extends Eagle{
        public void noOfLegs(){
            // implement interface method
        }

        public void beakLength(){
            // implementating abstract class method
        }

    }


//    Nested Interface (not used much, but good to know stuff)

//    - nested interface declared within another interface
//    - nested interface declared within a class

//    Generally they are used to group logical related interfaces.

//    Rules:
//    - A nested interface declared within an interface must be public.
//    - A nested interface declared within a class can have any access modifier.
//    - When you implement outer interface, inner interface implementation is not required & vice versa.

//    Ex of Nested interface
    public interface Bird{
        public void canFly();

        public interface NonFlyingBird{
            public void canRun();
        }
    }
    // 3 possible scenarios
    // case1: class implements only the outer interface - so you have to provide the implementation of the methods only from the outer interface
    public class Eagle implements Bird{
        @override
        public void canFly(){
            // implementation goes here
        }
    }

    // case2: class implements only the inner interface - so you have to provide the implementation of the methods only from the inner interface
    public class Eagle implements Bird.NonFlyingBird{
        @override
        public void canRun(){
            // implementation goes here
        }
    }
    // How will you invoke canRun() method?
    public class Main{
        public static void main(String[] args){
            // In the main method, create an object of the concrete Eagle class
            // its reference can be stored in Eagle, or in parent (interface)
            Bird.NonFlyingBird obj =  new Eagle();
            obj.canRun();
        }
    }

    // case3: class implements both outer & inner interface - - so you have to provide the implementation of the methods only from both the inner & outer interface
    public class Eagle implements Bird, Bird.NonFlyingBird{
        @override
        public void canFly(){
            // implementation goes here
        }

        @override
        public void canRun(){
            // implementation goes here
        }
    }


//    Ex: Nested Interface within a class

    // Nested interface within a class can have any access modifier. This is because a class can have member variables that can be private, protected, default or public.
    // The nested interface within the class is just like another member. SO it can also have any possible access modifier

    public class Bird{
        protected interface NonFlyingBird{
            public void canRun();
        }
    }

    public class Eagle implements Bird.NonFlyingBird{
        @override
        public void canRun(){

        }
    }


Difference between abstract class & interface
    1. Keyword
        abstract class: Keyword used here is abstract
        interface: Keyword used here is interface

    2. child class keyword
        abstract class:  Child classes need to use keyword "extends"
        interface:  Child classes need to use keyword "implements"

    3. Method types
    abstract class:  It can have both abstract & non abstract methods.
    interface: It can only have abstract methods. (From Java8 onwards, it can have default, private and static methods too where we can provide implementation)

    4.  Inheritance
    abstract class:  It can extend from another class and multiple interfaces. (We know that, in java any class - abstract or concrete - can extend only class. That one superclass can be abstract or concrete. )
    interface: It can only extend from other interfaces. It cannot extend clasess.

        An interface cannot extend a class, whether the class is: abstract or concrete
            class A {}

            interface B extends A {}   // ❌ Not allowed

        An interface can extend one or more interfaces.
            interface A {
                void methodA();
            }

            interface B {
                void methodB();
            }

            interface C extends A, B {   // ✔️ multiple inheritance is allowed
                void methodC();
            }

    5. Field types
    abstract class:  Variables can be static, non static, final, non final etc.
    interface: Variables are by default constants (public static final always).

    6. Access modifiers
        abstract class:  Variables & methods can be private, protected, public, default.
    interface:  Variables & methods are by default public (In Java9, private method is supported).

    7. Providing implementation
    abstract class:  It can provide the implementation of the interface.
    interface: It can not provide implementation of any other interface or abstract class.

    8. Constructor
    abstract class:  It can have constructor.
    interface:  It cannot have constructor.

    9. declaring method abstract
    abstract class:  To declare the method abstract, we have to use "abstract" keyword and it can be protected, public, default.
    interface: No need for any keyword to make method abstract, and its by default public.


}
