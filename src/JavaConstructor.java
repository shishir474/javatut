import java.lang.reflect.Constructor;

public class JavaConstructor {
//it is used to create an instance and initialise the instance variables
//Its similar to method except:
//    a. Name:  constructor name is same as class name
//    b. Return Type: constructor do not have any return type
//    c. Constructor cannot be static or final or abstract or synchronized.

public class Employee{
    public int empId;

    Employee(){
        // constructor
    }
    // methods can have same name as class name. This is totally valid
    int Employee(){
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


        public class Employee{
            Employee(){
                // constructor
                System.out.println("parent class constructor");
            }
        }

        public class Manager extends Employee{
            // inside Manager class, java will treat Employee() as method and thus it must have some return type
            void Employee(){

            }

            // Manager class's constructor
            Manager() {
                super();    // calls parent class constructor
                System.out.println("child class constructor");
            }
        }


//    Why constructor cannot be static?
//    Can we define constructor in interface?
//    Why constructor name is same as class name? - This is because it is easy to identify which one is the constructor in a class if you have a bunch of methods

}
