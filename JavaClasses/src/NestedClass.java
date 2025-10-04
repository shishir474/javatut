public class NestedClass {

//    Class within another class is called nested class.
//
//    When to use?
//    If you know that a class (A) will be used by only one another class (B), then instead of creating new file (A.java) for it, we can create nested class inside B itself.
//    It allows us to group logically related classes in one file.
//
//    Scope:
//    Its scope is same as of its outer class. So whatever the scope of outer class (public or defualt) that same scope will be applicable for nested class as well
//
//    Nested class is of 2 types
//        static nested class
//        non static nested class / inner class
//            a. member inner class
//            b. local inner class
//            c. anonymous inner class
//
//
//    static nested class
//        1. it does not have access to non static instance variables and methods of the outer class.2
//        2. since its a static nested class, its object can be initiated without initiating the object of outer class.
//        3. nested class be private, protected, public or default(package-private). Outer class can only be public or default

    class OuterClass{
        // created an instance variable - this will be related to an instance
        int instanceVariable = 10;
        // created classVariable - this will be related to class & not to any instance
        static int classVariable = 20;

        // since it's marked static, it becomes a property of OuterClass - so we can instantiate its object without instantiating the outer class
        static class NestedClass{
            public void print(){
                // cannot access instanceVariable here. Since its static class - we can only access static methods and static variables
                System.out.println(classVariable);
            }
        }
    }

    // how to invoke the print() ?
    // since this is non static method, I will need to create an object of NestedClass and call obj.print()
    // since the nested class is static(its associated with the OuterClass and not to any of its object)
    // we dont need to create an object of the OuterClass. We can directly access the NestedClass using OuterClass.NestedClass
    class ObjectTest{
        public static void main(){
            OuterClass.NestedClass nestedClassObj = new OuterClass.NestedClass();
            nestedClassObj.print();
        }
    }

// creating a private nested class
    class OuterClass{
        // created an instance variable - this will be related to an instance
        int instanceVariable = 10;
        // created classVariable - this will be related to class & not to any instance
        static int classVariable = 20;

        // since it's marked static, it becomes a property of OuterClass - so we can instantiate its object without instantiating the outer class
        // NestedClass declared private means - it is only visible in OuterClass scope. We cannot access it outside OuterClass
        private static class NestedClass{
            public void print(){
                // cannot access instanceVariable here. Since its static class - we can only access static methods and static variables
                System.out.println(classVariable);
            }
        }

        public void display(){
            NestedClass nestedClassObj = new NestedClass();
            nestedClassObj.print();
        }
    }

    class ObjectTest{
        public static void main(){
            // Now we cannot access it like this OuterClass.NestedClass because NestedClass is private
            // OuterClass.NestedClass nestedClassObj = new OuterClass.NestedClass();

            // so if we want to invoke print() - we will create an object of OuterClass and invoke display()
            // display() will internally create an object of NestedClass and invoke print()
            OuterClass outerClassObj = new Outerclass();
            outerClassObj.display();
        }
    }

//    Inner class or non static nested class

//    1. since this is non static - it has access to all the instance variables(static & non static) and method of outer class.
//    2. its object can be initiated on after initiating the object of OuterClass

    class OuterClass{
        // created an instance variable - this will be related to an instance
        int instanceVariable = 10;
        // created classVariable - this will be related to class & not to any instance
        static int classVariable = 20;

        // since it's non static, it is a property of an OuterClass object and not the class itself
        class NestedClass{
            public void print(){
                // can access both static and non static variables
                System.out.println(classVariable + instanceVariable);
            }
        }
    }

    // How to invoke print()
    // print() is non static - this means we need an object of NestedClass in order to invoke it.
    // But for creating an object of NestedClass, we'll first need to create an object of OuterClass.
    // We'll use that OuterClass object to create object of NestedClass
    class ObjectTest{
        public static void main(){
            // created object of OuterClass
            OuterClass outerClassObj = new OuterClass();
            // creating object of NestedClass using OuterClass object
            OuterClass.NestedClass nestedClassObj =  outerClassObj.new NestedClass();
            nestedClassObj.print();
        }
    }

    // Conclusion
    // For static nested classes, we dont need an instance of OuterClass unless the NestedClass is declared private
    // But for normal / non static nested class, we first need to instantiate OuterClass and use that instance to create object of NestedClass.
    // NestedClass can be private, protected, public or default


//Local Inner Class
//    If you are creating inner class inside any block like for loop, while loop, if, method block etc
//    It cannot be declared private, protected, public. Only default(not defined explicit) access modifier is used.
//    Since its scope is only limited to that particular block, it cannot be instantiated outside of this block.

    class OuterClass{
        // created an instance variable - this will be related to an instance
        int instanceVariable = 10;
        // created classVariable - this will be related to class & not to any instance
        static int classVariable = 20;

        public void display(){
            int methodLocalVariable = 4;

            // scope of LocalInnerClass is only inside display() method. Hence we need to instantiate it inside display() method only
            class LocalInnerClass{
                int localInnerVariable = 5;
                public void print(){
                    // inside this method instanceVariable, classVariable, methodLocalVariable, localInnerVariable everything is accessible
                    System.out.println(instanceVariable + classVariable + methodLocalVariable + localInnerVariable);
                }
            }

            LocalInnerClass localInnerClassObj  = new LocalInnerClass();
            localInnerClassObj.print();
        }

    }

    class ObjectTest{
        public static void main(){
            // since display() method is non static - I'll need to create an instance of OuterClass and use that instance to invoke display()
            OuterClass outerClassObj = new OuterClass();
            outerClassObj.display();
        }
    }

//    Inheritance in nested class

//    case 1: inheriting the class within 1 single class
class OuterClass{
    // created an instance variable - this will be related to an instance
    int instanceVariable = 10;
    // created classVariable - this will be related to class & not to any instance
    static int classVariable = 20;

    class InnerClass1{
        int innerClass1Variable = 30;
    }

    class InnerClass2 extends InnerClass1{
        int innerClass2Variable = 40;
        public void display(){
            System.out.println(instanceVariable + classVariable + innerClass1Variable + innerClass2Variable);
        }
    }

}

// How will I invoke display()?
// display() is non static - which means we would need an object of InnerClass2
// InnerClass2 is also non static - meaning we would also need object of OuterClass
class ObjectTest{
    public static void main(){
        OuterClass outerClassObj = new OuterClass();
        // using outerClassObj I can create objects of InnerClass1 and InnerClass2.
        // we need object of InnerClass2
        OuterClass.InnerClass2 innerClass2Obj =  outerClassObj.new InnerClass2();
        innerClass2Obj.display();
    }
}


// case 2:
// 2.1 Inheriting the static nested class in SomeOtherClass
    class OuterClass{
        // created an instance variable - this will be related to an instance
        int instanceVariable = 10;
        // created classVariable - this will be related to class & not to any instance
        static int classVariable = 20;

        static class NestedClass{
            public void display(){
                System.out.println("Inside Static Nested Class");
            }
        }
    }

    // we want to extend NestedClass in SomeOtherClass.
    // since NestedClass is static we can directly write OuterClass.NestedClass
    class SomeOtherClass extends OuterClass.NestedClass{
        public void display1(){
            display();
        }
    }

    // 2.2 Inheriting the inner class(non static) in SomeOtherClass
    class OuterClass{
        // created an instance variable - this will be related to an instance
        int instanceVariable = 10;
        // created classVariable - this will be related to class & not to any instance
        static int classVariable = 20;

        class InnerClass {
            public void display(){
                System.out.println("Inside Inner Class");
            }
        }
    }

    // we want to extend InnerClass in SomeOtherClass, and it is not static which means it is related to object of OuterClass
    // first we will have to initiate the object of parent.
    // Thats why we have to create a constructor of SomeOtherClass and create an object of OuterClass first and then call super()
    class SomeOtherClass extends OuterClass.InnerClass{
        SomeOtherClass{
            // in order to invoke the constructor of InnerClass, I'll have to write new OuterClass.super() - so first create object of OuterClass and then call super()
            new OuterClass().super();
            // as we know, when child class constructor is invoked, it first invokes the constructor of parent class.
            // but here the parent class is InnerClass, which can only be accessed using the object of OuterClass
        }
        public void display1(){
            display();
        }
    }


//    A --> B
//    When we call new B() --> it calls B's constructor and inside that, it first calls A's constructor
//    So first A's object will be created and then B's object will be created.

//    Anonymous Inner Class
//    An inner class without a name is called anonymous inner class

//    When its used?
//        When we want to override the behaviour of the method without even creating any subclass

    public abstract class Car{
        public abstract void applyBrakes();
    }

    // In ideal case - we would create a subclass which extends this Car class and implements applyBrakes()
    // But using anonymous class concept, we can do it in one single step - no need to explicitly create another class
    public class Test{
        public static void main(String args[]){
            Car audiCarObj = new Car(){
                @Override
                public void applyBrakes(){
                    // my audi specific implementation here
                    System.out.println("Audi specific brake changes");
                }
            };      // semi-color here represents end of a statement

            audiCarObj.applyBrakes();
        }
    }

    //  Car audiCarObj = new Car()
    // we're trying to create object of Car, but its an abstract class
//    2 things happens here behind the scenes.
//    a. a subclass is created, name is decided by the compiler
//    b. creates an object of the subclass and assign its reference to object "audiCarObj"




}
