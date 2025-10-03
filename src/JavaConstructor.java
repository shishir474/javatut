import java.lang.reflect.Constructor;

public class JavaConstructor {
//it is used to create an instance and initialise the instance variables
//Its similar to method except:
//    a. Name:  constructor name is same as class name
//    b. Return Type: constructor do not have any return type
//    c. Constructor cannot be static or final or abstract or synchronized.

    public class Employee {
        public int empId;

        Employee() {
            // constructor
        }

        // methods can have same name as class name. This is totally valid
        int Employee() {
            // method
        }
    }

    Employee e = new Employee();
//    This new Employee() essentially tells java to call the constructor at the runtime and not the method(bcoz we can also have method with same name as class name)
//    Constructor doesnt have a return type. Java implicitly adds return type as class, because we need an object of class itself.
//    A method will always have a return type. This differentiates a method with the constructor


//    Why constructor doesn't have a return type?
//    Why constructor cannot be final?
//    Why constructor cannot be abstract?
//    Why constructor cannot be static?
//    Can we define constructor in interface?
//    Why constructor name is same as class name?


//    Why constructor doesn't have a return type?
//    - You can have a method whose name is same as class name. But that method will always have some sort of return type. Even if it returns nothing we'll use void as the return type. This property of not having a return type differentiates a constructor from a method.
//      So using this 2 features, first constructor name is same as class name. Second, constructors dont have any return type, we can easily identify which is the constructor in our class.


//    Why constructor cannot be final?
//    -  method which is declared final cannot be overriden. Why because final method cannot be changed. So there is no point in overriding the method
//    For ex: assume you have created 2 classes Employee and Manager. Manager extends Employee class, so all the methods and instance variables of Employee class are inherited by Manager class
//    Employee(parent class) ---> Manager (child class)

//    A constructor’s job is to initialize the state of the current class’s object, not the parent class’s object. Each class defines its own constructors.
//    However, when you create a child class object, the parent constructor is still called (either explicitly via super(...) or implicitly via super()), but it’s not inherited — it’s just invoked.

//    Constructors are not inherited, but lets say for understanding purpose it does. Constructor rule is its name should be same as class name.
//    So Manager class will treat Employee as a method (not as constructor, because for Manager class Manager should be the constructor) and thus will expect some return type as well, which it doesnt have.
//    hence constructors are never inherited.

//    final in Java means: cannot be overridden.
//    But constructors are not inherited, so overriding doesn’t apply to them.
//    Since you can’t override a constructor, making it final has no meaning. That’s why Java doesn’t allow it.

//    A constructor cannot be final because it cannot be overridden(and this is bcoz we cannot inherit the constructors, hence overriding doesnt apply to them),
//    and final is used to prevent overriding.


    public class Employee {
        Employee() {
            // constructor
            System.out.println("parent class constructor");
        }
    }

    public class Manager extends Employee {
        // inside Manager class, java will treat Employee() as method and thus it must have some return type
        void Employee() {

        }

        // Manager class's constructor
        Manager() {
            super();    // calls parent class constructor
            System.out.println("child class constructor");
        }
    }


//    Why constructor cannot be abstracted?
//    To create an abstract method, we need an abstract class, lets say Employee, and in abstract method, we define only declaration and not defination
//    When I create a subclass which extends this abstract class, responsibility of providing the implementation of abstract method is on subclass.
//    So child class has to provide the implementation. But we know, constructors cannot be inherited. So if you cannot inherit it, how will you provide the implementation.
//    Hence constructors cannot be abstracted.

//    abstract means: must be overridden in a subclass.
//    But constructors are never inherited and thus cannot be overridden.
//    Each class must define its own constructor.

//👉 If Java allowed abstract constructors, subclasses would be forced to implement/override them, but that concept makes no sense.

    public abstract class Employee {
        abstract Employee();       //  Not allowed

        public abstract void print();
    }

    ;

    public class Manager extends Employee {
        @Override
        public void print() {
            // provide implemetation
        }
    }

    ;


//    Why constructor cannot be static?
//    static methods can only access static variables & static methods. It cannot access non static instance variables, because it is not aware of which object it belongs to.
//    So if you define the constructor static, you wont be able to initialize the non static instance variables.
//
//    2nd problem: It is related to chaining. If you declare constructor static, you wont be able to use super()

    class Employee {
        int empId;

        // marking Constructor static - then you wont be able to initialse non static member variables
        // Incorrect
        static Employee(int val) { // X (static) Not allowed
            empId = value;
        }
    }

//    static means: belongs to the class, not to an object.
//    But constructors are meant to initialize objects, so they’re tied to object creation, not the class itself.
//    If constructors were static, they could be called without creating an object — which defeats their whole purpose.


    //    Can we define constructor in interface?
//    We know that in interface, we only provide method declaration, no definition/body. Subclass which implements this interface needs to provide the implementation of this method.
//    You cannot instantiate or create an object of an interface. You can only instantiate Manager class & for that you will need a constructor, because constructor is used to create and initialise the object/instance.
//    For interface, anyway you cannot instantiate it. Hence concept of constructors doesn't apply to interfaces
    Employee e = new Employee(); // X Invalid

    interface Employee {
        void print();
    }

    class Manager implements Employee {
        void print() {
            // provide implementation
        }
    }

//👉 “By default, interfaces only provide method declarations and not definitions.
//    However, starting with Java 8, interfaces can also contain default and static methods with implementations,
//    and from Java 9, even private methods.”


//    Why constructor name is same as class name? - This is because it is easy to identify which one is the constructor in a class if you have a bunch of methods

//Types of constructors.

//        1. Default constructor:
//            If we dont provide any constructor, internally java implicitly provides one for us. This is the default constructor, and it will initialise our instance variables with the default values.

    public class Calculation {
        public String name;
    }
    // Even though I have not explicitly created a constructor here, java will internally create a constructor here and initialise name with ""

// int (primitive) -> default value is 0
// String (reference) -> default value is null
// Local variables (declared inside methods) → no default value (must be initialized explicitly else throws compile error).

//    Type	   Default Value	Notes
//    byte	    0	            8-bit integer
//    short	    0	            16-bit integer
//    long	    0L	            64-bit integer
//    int	    0	            32-bit integer
//    float	    0.0f	        32-bit floating point
//    double	0.0d	        64-bit floating point
//    char	   '\u0000'	        Null character (not '0')
//    boolean	false	        Default is always false
//    String	null	        Because it's a reference type
//    Any Object Ref null	    Default for all non-primitives

//    2. No Arg constructor
//    looks very similar to default constructor,but this is defined by me.

    public class Calculation {
        public String name;

        Calculation() {
            name = null;
        }
    }

//        NOTE: Whenever you define any constructor manually, default constructor is not added.
//        Default constructor is only added if you dont define a constructor of your own.

    //    3. parametrized constructor
    public class Calculation {
        public String name;

        Calculation(String name) {
            this.name = name;
        }
    }

    // 4. Constructor Overloading (constructor with different arguments)

    public class Calculation {
        public String name;
        public int empId;

        Calculation(String name) {
            this.name = name;
        }
        Calculation(String name, int empId){
            // this.name refers to instance variable name
            // In order to differentiate between instance variable and method's argument, we use this keyword
            // this contains the object's reference and when we say this.name we are referring to object's instance variable name
            this.name = name;
            this.empId = empId;
        }
    }

//    NOTE: There is no constructor overriding. Becuase they cannot be inherited.
//    Why they can't be inherited? Because the naming problem would occur. Child class would treat it as a method and since constructors don't have a return type, this will fail


    // 5. private constructors
//    If you declare your constructor private, nobody will be allowed to call your constructor or create object outside of this class.

//    used in singleton pattern
//    If you dont declare your constructor to be private, then anybody can call this and create an object.
//    By making it private, only this class has access to call the constructor.
//    created a public getInstance() method which calls this constructor and returns an instance

//    In singleton pattern --> only one object instance is created and we restrict the creation of objects by making the constructor private
//    We will slightly update the logic of getInstance() method to just create one single instance.
//    But the basic idea is -- you will always have one single instance of this class

    public class Calculation{
        private Calculation(){
        }

        public static Calculation getInstance(){
            return new Calculation();
        }
    }

    public class Main{
        public static void main(){
//            Calculation calculationObj = new Calculation();
//            we cannot call new Calculation() since we declared constructor to be private. hence we cannot create the object
//            That is why we need to make getInstance() method static, so that it can be called from classname

              Calculation calculationObj = Calculation.getInstance();

        }
    }


    // 6. Constructor Chaining
//            using this and super()
//        This can happen within one single class where you call the other constructors using this

        public class Calculation{
            int empId;
            String name;

            Calculation(){
                this(10);
            }
            Calculation(int empId){
                this(empId, "shishir");
            }
            Calculation(int empId, String name){
                this.empId = empId;
                this.name = name;
            }

        }


//        using super()
This applies in parent child class structures
    public class Employee{
        public int empId;
        // super() refer to no arg constructor
        Employee(){

        }
        // super(empId) refers to parametrized constructor
        Employee(int empId){
            System.out.println("Inside Employee no arg constructor");
            this.empId = empId;
        }
    }
    public class Manager extends Employee{
        public int age;
        Manager(int empId, int age){
            // if your parent construct is no arg, then you dont need to write super(), java will automatically call that for you.
            // But if your parent constructor is parametrized, then you need to explicitly call super(args) and pass the argument, becaue here java will call super() and it will refer to Employee() constructor
            super(empId);    // super() is implicitly added by java
            this.age = age;
            System.out.println("Inside Manager no arg constructor");
        }
    }
    public class Main{
        public static void main(String args[]){
            // super() is hidden, even if you dont write it, java internally adds super() at the first line itself.
            // so first parent's constructor is called (parent's object need need to be constructed first)
            // it calls the constructor from top to bottom fashion

            // while creating objects of child class, you need to pass values for all variables (in parent & child class)
            Manager m = new Manager("24", "Shishir");
        }
    }



}



