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




}
