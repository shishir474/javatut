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




}
