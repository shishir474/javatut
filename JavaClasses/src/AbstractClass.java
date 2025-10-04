public class AbstractClass {

    Abstract class (0 to 100% abstraction)
    Abstraction means hiding the implementation details from the client and only exposing the features that are client required.
    For ex: in cars, we have a feature applyBrakes() - but we dont know what actually is going around here from the implementation perspective

    2 ways to acheive abstraction
            1. using interface
            2. using abstract class


    Difference Between Interfaces and Abstract Classes in Java

    1. Method Declarations & Implementations
        Abstract Class:
            Can contain both abstract methods (no body) and concrete methods (with implementation).
            Abstract methods must be declared using the abstract keyword.
            Example:
            abstract class Car {
                abstract void drive();  // abstract method
                void honk() {          // concrete method
                    System.out.println("Car horn");
                }
            }

        Interface:
            Traditionally, all methods are abstract (public & abstract by default), and do not require the abstract keyword.
            From Java 8 onwards, interfaces can also have:
            Default methods (with implementation, mainly for backward compatibility).
            Static methods (utility/helper methods).
            Example:
            interface Vehicle {
                void drive(); // implicitly public abstract
                default void fuelType() {       // subclasses can inherit and override or use as is
                    System.out.println("Petrol/Diesel");
                }
                static void showCompany() {     // belongs to interface and cannot be inherited
                    System.out.println("Generic Company");
                }
            }

        2. Abstraction Level
            Interface:
                Provides 100% abstraction (till Java 7).
                In modern Java (Java 8+), because of default and static methods, abstraction is slightly relaxed.
            Abstract Class:
                Provides 0% to 100% abstraction.
                You can have no abstract methods (fully concrete), or all methods abstract.

        3. Instantiation
            Neither abstract classes nor interfaces can be instantiated.
            Reason: They may contain abstract methods without implementations.
            You must create a concrete subclass (for abstract class) or a class that implements the interface.

        4. Inheritance Rules
             Abstract Class:
                Can extend another abstract class.
                The child abstract class may:
                    a. Provide implementation for parent’s abstract methods.
                    b. Or declare additional abstract methods.
                A concrete class at the end of the chain must implement all abstract methods.

                Example:
                public abstract class Car{
                    int mileage;

                    Car(int mileage){
                        this.mileage = mileage;
                    }
                    // abstract method 1
                    public abstract void pressBreak();
                    // abstract method 2
                    public abstract void pressClutch();
                    // concreate method
                    public int getNumberOfWheels(){
                        return 4;
                    }
                }

                public abstract class LuxuryCar extends Car{
                    LuxuryCar(int mileage){
                        super(mileage); // calling parent class's constructor
                    }
                    // added one more abstract method
                    public abstract void pressDualBreakSystem();

                    // providing implementation of parent's abstract method
                    @Override
                    public void pressBreak(){
                        // implementation goes here
                    }
                }

                public class Audi extends LuxuryCar{
                    Audi(int mileage){
                        super(mileage);
                    }
                    @Override
                    public void pressClutch(){
                        // implementation goes here
                    }
                    @Override
                    public void pressDualBreakSystem(){
                        // implementation goes here
                    }
                }

                // you can store the reference in the parent class or in the same class itself
                LuxuryCar audi = new Audi();
                Car audi = new Audi();
                Audi audi = new Audi();

        Interface:
            A class can implement multiple interfaces (supports multiple inheritance of type).
            An interface can also extend multiple interfaces.

        Example:
            interface A { void show(); }
            interface B { void display(); }
            interface C extends A, B { void print(); }

            class MyClass implements C {
                public void show() { System.out.println("Show"); }
                public void display() { System.out.println("Display"); }
                public void print() { System.out.println("Print"); }
            }

    5. Constructors
        Abstract Class:
            Can have constructors.
            Used to initialize fields when subclass objects are created.
        Interface:
            Cannot have constructors, because they cannot maintain state.

    6. Access Modifiers
        Abstract Class:
            Abstract methods can have any access modifier (public, protected, default).
        Interface:
            All methods are implicitly public abstract (unless they are default or static).
            Fields in an interface are public, static, and final by default (constants).

    7. Usage Scenarios
        Use abstract class when:
            You want to share common code (concrete methods, fields, state) across subclasses.
            You need constructors or non-static fields.
            There’s a strong is-a relationship.

        Use interface when:
            You need to enforce a contract/behavior that multiple classes (possibly unrelated) should follow.
            You want multiple inheritance of type.
            You need to define constants and utility methods.



    Key difference between interfaces and abstract classes is
            In both abstract classes and interfaces, we just give method declarations(signature) and not the implementation.
            In interfaces all the methods are abstract, whereas in abstract classes, you can have concreate methods as well. You specifically need to use "abstract" keyword to define an abstract method.
            Since all the methods are abstracted in interfaces, the class which implements the interface, needs to provide the implementation of all the methods declared inside interface.
            Whereas in abstract class, we can have both concrete and abstract methods. Class which extends the abstract class must provide the implementation of abstract methods
                concreate methods -- methods whose implementation logic is defined.
            Inside abstract classes - we need to use "abstract" keyword to declare abstract method. In interfaces, we dont use that(all the methods are public abstract by defualt - although you can define default & static methods in interface for backward compatibility and utility purposes)
            Both abstract class and interface cannot be instantiated. Why? because they contain abstract methods(whose implementation is not provided)
            Abstract methods can only be  created inside abstract class. So we need to explictily specify "abstract class" keyword to create an abstract class.
            In interfaces, since all the methods are abstracted - there is 100% abstraction. In abstract class - abstraction is between 0 to 100% which means you can have 0 abstract methods or all methods are abstract.
            an abstract class can inherit another abstract class, but eventually you need to define a concrete class which provides the implementation of all parent abstract methods.
                abstract class inheriting another abstract class- in this scenario, the child abstract class can either provide the implementation or add more abstract methods.






    A class can inherit mutliple interfaces, but can we inherit multiple abstract class?

    In Java:
        A class cannot extend multiple abstract classes.
        Because Java does not support multiple inheritance of classes (to avoid the diamond problem).
        A class can extend only one abstract class.
        Example ❌ (Not allowed):

        abstract class A {
            abstract void methodA();
        }

        abstract class B {
            abstract void methodB();
        }

        class C extends A, B {   // ❌ Compilation error
            @Override
            void methodA() {}
            @Override
            void methodB() {}
        }

    ✅ But a class can:
        Extend one abstract class (single inheritance of class).
        Implement multiple interfaces (multiple inheritance of type).
        Example ✔:

        abstract class A {
            abstract void methodA();
        }

        interface B {
            void methodB();
        }

        interface C {
            void methodC();
        }

        class D extends A implements B, C {
            @Override
            void methodA() { System.out.println("A implemented"); }

            @Override
            public void methodB() { System.out.println("B implemented"); }

            @Override
            public void methodC() { System.out.println("C implemented"); }
        }


🔑 Why this restriction exists (multiple abstract classes not allowed):
    If Java allowed a class to inherit multiple abstract classes, and both had the same method signatures (or fields), it would create ambiguity in which implementation to use (classic diamond problem seen in C++).
    To avoid complexity, Java designers allowed:
        Single inheritance for classes (abstract or concrete).
        Multiple inheritance only through interfaces.

}
