public class Interface3 {
    // covers Java8 features
    //  interface default method & conflict resolution (when a class implements multiple interfaces having same name of default methods)
    // interface static methods

//    java9 features
//    interface private methods
//    interface private static methods

//  default methods(java8)  -- improvement for the long gap Java had
//    - Before java8, interface can have only abstract method, and all child classes has to provide abstract methods implementation.

    public interface Bird{
        // abstract method
        public void canFly();
        // 1st default method
        default public int getMinFlyHeight(){
            return 100;
        }
    }
//    2 child classes - both have to implement all the abstract methods in the interface.
//    problem with this is-- lets say in future there is a requirement to add a new method in the interface, which means
//    we will have to provide the implementation of the new method in all the child classes that are implementing this interface.
//    i.e adding a new method in the interface - becomes challenging --> This is where default method comes in.
//    with default methods - you can provide its implementation right there within the interface. and all the child classes that implements this interface would be able to access it.
//    child classes can also override the default method (if they want to provide their own custom implementation).
//    Note: Java does not impose any restrictino on the no of default methods that you can define within the interface. You can have 0,1,2,10, 500 default methods

    public class Eagle implements Bird{
        @Override
        public void canFly() {
//            implementation goes here
        }
    }

    public class Sparrow implements Bird{
        @Override
        public void canFly() {
//            implementation goes here
        }

//        overriding the default method
        @Override
        public int getMinFlyHeight() {
//            implementation goes here
        }
    }

    public class Main{
        public static void main(String[] args){
            // created Eagle object - notice its ref can be stored in its parent interface
            Bird obj = new Eagle();
            obj.getMinFlyHeight()

        }
    }

//    Why was this feature of default methods introduced in Java8?
//    My understanding - In java8 streams was introduced in the collections interface. if they define it as an abstract method, then they would have to provide the implementation of the stream method inside all the subclasses that are implementing the Collections interface.
//    and there are way too many classes implementing this Collections interface.
//    Thus to simplify things, they decided to introduce default method & provided the implementation within streams itself.
//    Now this method is accessible to all the child classes implementing this interface. Also if they want, they can override it as well (to provide their custom implementation)


//    Default methods & multiple inheritance
//    We know that a class can implement multiple interfaces
    public interface Bird {
        default boolean canBreathe() {
            return true;
        }
    }
    public interface LivingThings {
        default boolean canBreathe() {
            return true;
        }
    }
//    Eagle class implements both the interfaces (Bird & LivingThings) and both the interfaces have the same name for their default methods.
//    WHich default method will be invoked when canBreathe() method will be called using Eagle object
//    Eagle obj = new Eagle();
//    obj.canBreathe();             --> X which method to call
//    In these scenarios, you mandatorily have to provide the implementation in the Eagle class
    public class Eagle implements Bird, LivingThings{
        @Override
        public boolean canBreathe() {
//            implementation goes here
        }
    }


//    How to extend interface, that contains default method:
//    we already know that in java, an interfaqce can extend multiple other interfaces

    //    case 1: child interface didn't change/modify anything in the parent's interface default method
    //  parent interface
        public interface LivingThings {
            default boolean canBreathe() {
                return true;
            }
        }
    //  child interface
        public interface Bird extends LivingThings{

        }
        public class Eagle implements Bird{
            // canBreathe() method would be available here
        }
        public class Main{
            public static void main(String[] args){
                // If I create Eagle object - I would be able to access the canBreathe() default method
                Bird obj = new Eagle();
                obj.canBreathe();
            }
        }

    //    case 2: child interface has an abstract method with the same name as parent's interface default method
    //    In this case, the default method would be implicitly converted to abstract, because of the child interface

    //  parent interface
        public interface LivingThings {
            default boolean canBreathe() {
                return true;
            }
        }
    //  child interface
        public interface Bird extends LivingThings{
            public boolean canBreathe();
        }
        public class Eagle implements Bird{
            // canBreathe() is abstracted because of the Bird interface. Eagle has to provide its implementation
            public boolean canBreathe() {
                return true;
            }
        }

    //    case 3: child interface & parent interface, both provides the same name default method.
    //  parent interface
    public interface LivingThings {
        default boolean canBreathe() {
            return true;
        }
    }
    //  child interface
    public interface Bird extends LivingThings{
//        child interface is overriding this method. Providing its own implementation
        default boolean canBreathe() {
            // you can acccess the parent's interface canBreathe() method here plus also provide some additional logic if you wanna
            boolean canBreatherOrNot =  LivingThings.super.canBreathe();
            return canBreatherOrNot;
        }
    }
    public class Eagle implements Bird{
    }


//    Static methods in interfaces(java8)
//    - We can provide implementatoin of static methods inside the interface.
//            NOTE: Static methods can only access static members(static variable & static methods)
//    - But it cannot be overriden by classes which implements the interface.
//        - NOTE: default methods can be overrident by the child classes implementing the interface. But static methods cant be overriden.
//    - We can access it using the interface name itself.
//    - Its by default public.
//    static methods were present in java8, but by default everything is public, so it's public static.
//    private static methods in interfaces were introduced in java9

    public interface Bird {
        static boolean canBreathe() {
            return true;
        }
    }
    public class Eagle implements Bird {
        public void test() {
            if(Bird.canBreathe()) { // accessing interface's static method
                // do somethign
            }
        }
    }


//    private method & private static method (java9)
//    we can provide the implementation of the method but as a private access modifier in interface.
//    Why do we need this? It brings more readabillity to your codebase. since these are private methods, there scope is only limited to the interface. They wont be accessible outside the interface.
//    which means they were introduced with a sole purpose of using it within the interface.
//    For ex: if multiple default methods share same code, you can move that repeatable code inside the private methods & thus it promotes code reusability.
//    also since they are private, they cannot be abstract. declaring them abstract means, some class has to provide its implementation & its not possible coz they are private. Hence private methods cannot be abstract.
//    private methods can be static or non static.
//    One general rule of thumb is from static methods you can only access only static members (static variables & static methods)
//    whereas in non static methods you can access both static + non static members
//    Follwing the above logic: From static method, we can only call private static interface method
//    private static method can be called from both static and non static method.

//    Ex: demonstrating usage of default method, static method, private method, private static method within interface
    public interface Bird {
        void canFly();      // this is equivalent to public abstract void canFly();

    //        java8 feature
        public default void minFlyingHeight() {
            // default interface methods can be overriden by child classes implementing this interface
            myPublicStaticMethod();         // calling static method
            myPrivateStaticMethod();        // calling private static method
            myPrivateMethod();              // calling private method
        }

    //          java8 feature
        static void myPublicStaticMethod() {
            // static interface methods cannot be overriden by child classes implementing this interface
            myPrivateStaticMethod();        // from static method, we can only call/access other static methods/variables
        }

    //        java9 feature
        private static void myPrivateStaticMethod() {
            // private static interface method implementation
        }

    //        java9 feature
        private void myPrivateMethod(){
            // private interface method implementation
        }
    }




}





}
