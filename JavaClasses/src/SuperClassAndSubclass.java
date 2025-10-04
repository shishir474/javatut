public class SuperClassAndSubclass {
//    A class that is derived frmo another class is called Subclass.
//    and class through which subclass is derived is called superclass.

//    🔹 The Object Class in Java
//        In Java, every class implicitly extends Object if no other superclass is specified.
//        Object is the root (topmost) class in the Java class hierarchy.
//        This means all classes in Java are direct or indirect subclasses of Object.

//    🔹 Example
        class Person {
            // This class does not explicitly extend anything
            // But internally, it is treated as: class Person extends Object
        }

        class Engineer extends Person {
            // Subclass of Person
        }

//    Here:
//        Person extends Object (implicitly).
//        Engineer extends Person, and therefore also inherits from Object indirectly.
//
//    🔹 Why Object is important
//        The Object class provides common methods that every Java object can use:
//
//        toString() → Returns a string representation of the object.
//                Default: className@hashcode
//        Usually overridden for meaningful output.
//
//        equals(Object obj) → Compares two objects for equality.
//                Default: checks reference equality (==).
//        Often overridden to compare object contents.
//
//        hashCode() → Returns an integer hash value for the object.
//        Used in collections like HashMap, HashSet.
//        Must be consistent with equals().
//
//        clone() → Creates and returns a copy of the object (shallow copy).
//        Requires implementing Cloneable interface.
//
//        getClass() → Returns the runtime class of the object.
//
//        finalize() → Called by the Garbage Collector before object destruction (deprecated in Java 9+).
//
//        notify(), notifyAll(), wait() → Methods for thread communication (used in synchronization).
//
//
//    🔹 Key Takeaways
//    Even if you don’t extend anything, your class automatically extends Object.
//    All classes in Java inherit these methods from Object.
//    You can override methods like toString(), equals(), and hashCode() to make them more meaningful.
//
//    Conclusion: Every class in Java is inheriting Object class directly or indirectly.


    class ObjectTest{
         public static void main(String args[]){
//             child object can be stored in parent class reference
             // Object class is the topmost/ultimate parent of every class and thus we can store the reference of Person and Audi objects inside Object class
            Object ob1 = new Person();
            Object ob2 = new Audi();
            System.out.println(ob1.getClass());     // class Person
             System.out.println(ob2.getClass());    // class Audi
         }
    }


}


