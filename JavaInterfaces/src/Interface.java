public class Interface {
//    What is interface?

//  How to define interface?
//    Interface declaration consists of
//    - Modifiers (only public or default are allowed. Private/Protected is not allowed with interfaces)
//    - "interface" keyword
//    - interface name
//    - comma separted list of parent interfaces
//    - body

//    Only public & default modifiers are allowed
    public inteface Bird{
        public void fly();
    }

    inteface Bird1{
        public void fly();
    }

    // common separated list of parent intefaces (not class -  your interface cannot extend a class)
    public inteface NonFlyingBirds extends Bird, LivingThings{
        public void canRun();
    }

// a java class can only extend atmost one class. This is to avoid Diamond problem
//    If multiple classes were allowed
//    A
//   / \
//  B   C
//   \ /
//    D
//    What happens if B and C both define the same method?

//    class B { void foo() {…} }
//    class C { void foo() {…} }
//    class D extends B, C {}  // Which foo() should run?

//    This ambiguity is called the Diamond Problem. Java avoids it by allowing single inheritance only.

//   Java compensates with interfaces.
//   Java does not allow multiple inheritance of classes, but it does allow multiple inheritance of interfaces
//   because interfaces DO NOT create the diamond problem in the same way classes do.

    class MyClass extends BaseClass implements A, B, C {}
//    Interfaces don't maintain states like classes
//    Classes can have instance variables, constructors, muatable state
//    If java allowed :
      class C extends A,B {};       // X Invalid
//    and both A and B had state, you would inherit 2 sets of fields --> leading to conflicts
//    But interfaces cannot have instance fields (only constants), so state conflict never happens.

//    Since Java 8, interfaces can have default methods.
//     If two interfaces provide the same default method:
    interface A {
        default void show() { System.out.println("A"); }
    }

    interface B {
        default void show() { System.out.println("B"); }
    }

//    And a class implements both:
    class C implements A, B {}

//    Java forces you to resolve the conflict:
//    Since class C implements both A and B, and both interfaces define same default method show()
//    Then Java asks: "Which show() should I use? A's or B's?"
//    Java does not choose automatically because that could lead to unpredicatable behaviour.
//    So the compiler forces you to override the method and specify the source

    class C implements A, B {
        @Override
        public void show() {
            // this syntax explicitly tells compiler to call the default method from interface B, avoiding ambiguity & preventing the diamond problem
            B.super.show();   // or A.super.show()
        }
    }

//    B.super.show() means
//    B -> which interface you are choosing
//    .super -> means call the implementation in the interface, not on class
//    .show() ->  the method that you want to run

//    Why can't we write B.show()?
//    Because default methods belong to instances, not interface themselves.
//    B.show() tries to call the method as if it were a static method of the interface, which is illegal.
//    default methods are not static & are meant to be called on an object, not on the interface type.
//    To call the default method, normally you would write
      new C().show()  // if C implements B
//    you call interface default methods through instances, not through interface names

//    if java allowed B.show(), then which object's context is being used?
//    Default methods operate on an instance, because they can refer to "this"

    interface B{
        default void show(){
            System.out.println(this.toString());
        }
    }
//    What would "this" be if you call B.show()? There's no instance --> so it cannot be allowed


    // Each class in java already extends Object class, then how does this statement(that a class can only extend atmost one class) holds true?
    // Because the compiler automatically adds extends Object only when you don’t explicitly extend another class.
    // You never extend two classes at the same time.

    class A {}
    class B extends A {}   // valid
//    You CANNOT do:
    class C extends A, B {}  // ❌ not allowed

//    If you don’t write any extends, Java automatically inserts:
    class MyClass extends Object

//    So:
    class Person {}
//    Is just syntactic sugar for:
    class Person extends Object {}

//    If you explicitly extend something → that class is your parent.
//    If you don’t extend anything → Object becomes the parent.
    class A {}                     // extends Object
    class B extends A {}           // extends A only
//    Invalid:
    class C extends A, Object {}   // ❌ cannot extend two classes
//    Even though A itself extends Object, Java sees only one parent at each level in the hierarchy.


//    Methods in interface
//    All methods in interface are implicitly public
//    Methods cannot be declared final - bcoz we have just provided the signature. Actual implementation will be provided by the class implementing the interface.
        // if we mark it as final -- then it cannot be modified.

    public interface Bird{
        // all methods inside interface are implicitly public
        void fly(); // although no access modifier is specified, fly() is public
        public void hasBreak();
    }


//   Fields in interface
//    Fields in interface are public, static, and final implicitly (CONSTANTS)
//    You cannot make fields private or protected.

// static final -- is used to declare constant variables
    public interface Bird{
        int MAX_HEIGHT_IN_FEET = 200;
    }
//    Both interfaces are essentially same (all the fields within interfaces are public, static and final implicitly.
    public interface Bird{
        public static final int MAX_HEIGHT_IN_FEET = 200;
    }



}
