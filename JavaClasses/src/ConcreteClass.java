public class ConcreteClass {
//    - these are those classes that we can create an instance using NEW keyword
//    - All the methods in this class must have implementation
//    - It can be your child class from interface or extend abstract class.
//    - A class access specifier can be "public" or "package private"(default)

// In Java, an interface defines a contract (blueprint) that a class must follow.
// It contains method declarations (signatures) but NOT the implementation.

// 1️⃣ Interface with a single abstract method
    public interface Shape {
        // By default: methods are public and abstract (even if not explicitly mentioned).
        void computeArea();
    }

// 2️⃣ Rectangle class implements Shape.
// Since Shape declares computeArea(), Rectangle must provide an implementation.
    public class Rectangle implements Shape {
        @Override
        public void computeArea() {
            System.out.println("Computing rectangle area...");
        }
    }

    // 3️⃣ Usage examples
    Shape rect = new Rectangle();      // Reference of interface → object of Rectangle
    Rectangle rect1 = new Rectangle(); // Reference of concrete class → object of Rectangle

    // Both calls are valid:
    rect.computeArea();   // calls Rectangle's implementation
    rect1.computeArea();


//🔑 Key Points about Interfaces
//    1. Cannot be instantiated
//        You cannot do new Shape() because the interface only contains method declarations (no body).
//        Only implementing classes provide actual method definitions.

//    2. Polymorphism in action
//        You can store a reference of the implementing class in the interface type:
//        Shape s = new Rectangle(); // parent reference, child object
//        This is useful when writing flexible code that works with any Shape (e.g., Circle, Square) without knowing the exact class at compile time.

//    3. Multiple inheritance
//        A class can implement multiple interfaces in Java (unlike extending multiple classes).
//    Example:
        interface Drawable { void draw(); }
        interface Colorable { void setColor(String color); }

        class Circle implements Drawable, Colorable {
            public void draw() { System.out.println("Drawing circle"); }
            public void setColor(String color) { System.out.println("Color: " + color); }
        }

//    4. Access specifiers in interfaces
//        Methods in an interface are implicitly public abstract (unless they are static or default).
//        Variables in an interface are implicitly public static final (constants).
//        Interfaces themselves can be public or package-private (default).

//    5. Java 8+ Enhancements
//        Interfaces can also contain:
//            default methods → with a body (provides default behavior, can be overridden).
//            static methods → utility methods inside interface.
//
//            Example:
            interface Shape {
                void computeArea();

                default void display() {
                    System.out.println("This is a shape");
                }

                static void info() {
                    System.out.println("Shape interface - Java 8 feature");
                }
            }

//    6. Nested classes and access specifiers
//        Top-level classes can only be public or default (package-private).
//        But nested classes (inside another class) can have all four access modifiers: public, protected, default, private.

//    ✅ So in short:
//    Interface = blueprint (method signatures).
//    Implementation class = provides the actual logic.
//    Cannot instantiate interface (because there’s no implementation).
//    Polymorphism allows using interface reference → child object.
//    Since Java 8, interfaces are much more powerful with default & static methods.


//    NOTE: Adding @Override annotation is actually the best practise whenever you are overriding a method. Its not mandatory to add this everytime, but it is considered a good practise.
//        Advantage of adding the annotation is compiler checks at the compile time if the method really overrides a parent/interface method. If you accidentally mismatch the method signature(like typo in name or wrong parameters), the compiler will give an error.
//        Without @Override, the compiler will assume you're writing a new method which might lead to subtle bugs
//      With @Override, it allows you to catch the errors at the compile time itseft, instead of catching it at runtime.


// ------------------------------------------------------------------------------------------------

//    static and default methods in Interfaces

//🔹 1. Default Methods in Interfaces
//    Introduced in Java 8 to allow backward compatibility.
//    Before Java 8, if you added a new method to an interface, all implementing classes broke (because they had to implement it).
//    With default, you can add a method with a body inside the interface.
//
//    👉 Key Points
//    Declared with the keyword default.
//    Provides a default implementation that implementing classes can use as-is or override.

    interface Shape {
        void computeArea();  // abstract method

        // Default method (has body)
        default void display() {
            System.out.println("This is a shape");
        }
    }

    class Rectangle implements Shape {
        public void computeArea() {
            System.out.println("Computing area of rectangle");
        }
        // We didn’t override display(), so Rectangle inherits the default implementation
    }

    class Circle implements Shape {
        public void computeArea() {
            System.out.println("Computing area of circle");
        }
        // Overriding the default method
        @Override
        public void display() {
            System.out.println("This is a circle");
        }
    }

    public class Test {
        public static void main(String[] args) {
            Shape rect = new Rectangle();
            Shape circle = new Circle();

            rect.display();   // "This is a shape" (from default)
            circle.display(); // "This is a circle" (overridden)
        }
    }

//✅ Use case: Adding new methods to interfaces without breaking old code.

//    🔹 2. Static Methods in Interfaces
//    Also introduced in Java 8.
//    Can be called on the interface itself (not on the instance).
//    Acts like a utility/helper method for that interface.

//    👉 Key Points
//    Declared with the keyword static.
//    Cannot be inherited by implementing classes.
//    Must be called using the interface name.

    interface Shape {
        void computeArea();

        // Static method
        static void info() {
            System.out.println("This is a Shape interface");
        }
    }

    class Rectangle implements Shape {
        public void computeArea() {
            System.out.println("Computing area of rectangle");
        }
    }

    public class Test {
        public static void main(String[] args) {
            Shape rect = new Rectangle();
            rect.computeArea();

            // Calling static method
            Shape.info();   // ✅ Correct way
            // rect.info(); // ❌ Error: cannot call static method via object
        }
    }

//✅ Use case: Placing common utility functions related to the interface.

//    🔹 3. Difference Between Default and Static Methods
//    Feature	        Default Method	                            Static Method
//    ------------------------------------------------------------------------------------------
//    Declaration	    default void methodName() {}	            static void methodName() {}
//    Belongs to	    Instance of the implementing class	        Interface itself
//    Overriding	    Can be overridden in implementing class	    Cannot be overridden
//    Invocation	    Via object reference (obj.method())	        Via interface name (Interface.method())
//    Use case	         Backward compatibility, common logic	    Utility/helper methods


🔹  Multiple Inheritance Issue (Diamond Problem)
    If two interfaces provide the same default method, the implementing class must override it to resolve ambiguity:

    interface A {
        default void show() {
            System.out.println("A");
        }
    }
    interface B {
        default void show() {
            System.out.println("B");
        }
    }
    class C implements A, B {
        // Must override to resolve conflict
        @Override
        public void show() {
            A.super.show(); // Call A’s version explicitly
            // or B.super.show();
        }
    }

//    C class implements both interfaces A and B. So it will inherit the show method() from both the interfaces. Now since the same method is present in both interfaces, there will be conflict and we will have to manually resolve it by overriding the method in the class C.
//    Inside class C, you provide your own show().
//    But suppose you still want to call one of the parent interface’s default methods (A or B).
//
//    For that, Java gives this syntax:
//
//    InterfaceName.super.methodName();

}
