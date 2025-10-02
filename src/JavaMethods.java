public class JavaMethods {
    // package: is a collection of logical/similar classes

    // access specifiers
    // public: this method can be accessed in any class in any package
    // private: this method can only be accessed inside the class in which it is defined
    // protected: this method can be accesed by any class which exists in the same package or subclass in different package
    // default: this is the default access specifier used by java, if you dont mention anything. This method can only be accessed by classes in the same package.

    // Method overloading
//    void getInvoice(){
//
//    }

//    string getInvoice(){
//
//    }
//    this is incorrect, because overloaded methods can only be differentiated based on arguments. Function name should be same, and arguments should be different).
//    return type is not even considered

//    String getInvoice(string x){
//        // works fine
//    }
//    int getInvoice(int a){
//        // works fine
//    }
//    void getInvoice(int a, int b){
//        // works fine
//    }

    // For method overloading only condition is same method name, different arguments. Return type is not even considered


//    Overidden method
//    subclass has the same method as the parent class. return type is same, method name is same, arguments are also same. Everything is same.

//    Which method to call?
//    In case of overloading, based on the arguments passed the compiler decides to call that respective method, wheareas in overriding, the method called depends on whether the object is of parent or child class.
//    if you are calling method using parent class's object, parent's method will be called.
//    If you are calling method using child class's object, child's method will be called
//    This is known as dynamic binding. At the runtime, it will be decided which method to call(based on the instance)
//    First priority will be given to the class whose object is used to call the method.
//    Ex: Person (parent class) & Doctor(Child class). method getProfession is defined in both the classes.
//    Person p1 = new Doctor();
//    p1.getProfession();
//    since p1 is the object of Doctor class (ref being stored in the parent class), Doctor's class method will be called.
//    Incase, Doctor class does not contain this method, Person class method will be called

//At runtime it will know which getProfession() method to call. Whether I have to call Doctor one or Person one.
//    Person p1 = new Doctor();
//This object p1, even though its reference is stored in parent, the instance is of Doctor.
//    So it will go first inside Doctor and check if getProfession() method is defined. if it's not present, then it will check the parent one


//    class Person{
//        public void getProfession(){
//            return "person";
//        }
//    }
//
//    class Doctor extends Person{
//        public void getProfession(){
//            return "doctor";
//        }
//    }

    public static void main(String args[]){
        Person p1 = new Doctor();
        System.out.println(p1.getProfession());
    }

//    Person p1 = new Doctor();
//    Doctor p2 = new Doctor();
    // Person which is the parent class can store the reference of child class's object.
    // Both statements will work

//    p1.getProfession(); // doctor


//    Static methods:
//        1. created using static keyword
//        2. These methods belong to class and not to any specific objects. hence you dont need to create objects to call these methods. Can call them using className.methodname()
//            Since they belong to class, all the objects of this class share the same single version of the static method.
//        3. Static methods cannot access Non Static Instance members & method directly ( can be accessed if you specify the exact object ).
//            Instance members can be of 2 types static or non static
//
//    Why we cannot access non static members (variables & methods) inside static methods?
//        Non static members(variables & methods) belongs to a particular object. Each object will have its own copy of non static members. Now since we are talking about static methods which belongs to a class, how will it know which object's member we are trying to access.
//        hence access to non static members(variables & methods) inside static methods is not allowed directly, but you can call/access them using objects.
//
//        If we make method or member variable static, only 1 copy will be present no matter how many objects are there. So it knows which copy to call.
//        But in case of non static members, it doesnt know which copy to call. So you will have to tell which object to use in order to call non static members
//
//        So in static methods, you can directly access static variables/methods (since only 1 copy exists).
//        But if you want to access non static members you need to tell which object to use(since each object will have its own copy)


    public class Calculation{
        int stockPrice = 20;
        static int carPrice = 100;

        public static int getPriceOfPen() {
            carPrice
//            stockPrice    -- cannot accesss non static members inside static method

//            print()       --> cannot access non static methods inside static method
        }

        public void print(){

        }

    };


    static methods cannot be overridenn?

    public class Person{
        public static void profession(x){
            System.out.println("Hi I'm inside person class");
        }
    }

    public class Doctor extends Person{
//        @Override  --> cannot override static method.
//        If I comment @override decorator, this works fine, because both these methods are static and they belong to their respective classes Person & Doctor
//        Until you explicitly use @Override decorator, the method is not overriden. Duplicating the function in child class does not override it. We need to use the decorator
        public static void profession(){
            System.out.println("Hi I'm inside Doctor class");
        }
    }
    public class Main{
        public static void main(String args[]){
            // calling static method profession()
            Doctor.profession();
            Person.profession();
        }
    }

//    Overriding is not possible in case of static methods. The static methods are linked to class and there are no objects involved.
//You can call them directly using className

//    WHen to declare a method static?
//1. Methods which do not update the state of the objects can be declared static.
//    2. Utility methods which do not use any instance variable and compute only on arguments can be declared static.
//    ex; factory design pattern

public class Calculation{
        static int carPrice = 100;

        // sum only uses arguments and does not modify any instance variables. Hence method can be declared static
        public static int sum(int a, int b){
            int total = a + b;
            return total;
        }

        // this method is changing the carPrice instance variable. Hence cannot declare this method as static
        public int sum2(int a, int b){
            int total = a + b;
            carPrice += total;
            return carPrice;
        }
        // conclusion: if your method uses instance variable or is updating the state of instance variable. You cannot declare method as static.
}



//Final method
//1. Final methods cannot be overriden
//2. WHy? final method means its implementation cannot be changed. If child class cannot change its implementation, then no use of overriding.Person

    public class Vehicle{
//        I have marked this method as final - because I dont want any child class to change its behaviour/implemetation
        public final void getVehicleType(){
            return "general vehicle"
        }
    }

    public class Car extends Vehicle{
//        @Override you cannot override
        public void getVehicleType(){
            return "Car"
        }
    }

//Abstract method:
//    1. It is defined only in Abstract classes.
//    2. Only method declaration is done.
//    3. Its implemetation is done in child classes.

    public abstract class Person{
        public abstract int print();
    }

    public class Doctor extends Person{
        @Override
        public int print(){
            // child class will implement this
        }
    }



//    Variable arguments
//    1. This is used if you want to pass variable no of arguments to a function.
//    2. Only one variable argument can be present in the method.
//    3. It should be the last argument in the list.

public class Calculation{
        // passing variable no of arguments using ...(3 dots) & now this could be treated like a list
    public int sum(int ...variable){
        int output = 0;
        for(int var: variable){
            output += var;
        }
        return output;
    }
}

public class Main{
    public static void main(String args[]){
        Calculation calculationObj = new Calculation();
        System.out.println(calculationObj.sum(1, 2, 3));
        System.out.println(calculationObj.sum(1, 2, 3, 4, 5));
    }
}




