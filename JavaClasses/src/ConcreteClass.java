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





}
