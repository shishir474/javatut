public class JavaMethods {
    // package: is a collection of logical/similar classes

    // access specifiers
    // public: this method can be accessed in any class in any package
    // private: this method can only be accessed inside the class in which it is defined
    // protected: this method can be accesed by any class which exists in the same package or subclass in different package
    // default: this is the default access specifier used by java, if you dont mention anything. This method can only be accessed by classes in the same package.

    // Method overloading
    void getInvoice(){

    }

//    string getInvoice(){
//
//    }
//    this is incorrect, because overloaded methods can only be differentiated based on arguments. Function name should be same, and arguments should be different).
//    return type is not even considered

    String getInvoice(string x){
        // works fine
    }
    int getInvoice(int a){
        // works fine
    }
    void getInvoice(int a, int b){
        // works fine
    }

    // For method overloading only condition is same method name, different arguments. Return type is not even considered
}
