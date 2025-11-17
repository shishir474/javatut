public class Reflections {
// 1.  Reflections are used to examine the Classes, Methods , Fields, & interfaces at runtime.
//    It also allows to change the behaviour of the class too.
//    For ex:
//    - what all fields are present in the class?
//    - what all methods are present in the class?
//    - what is the return type of the  method.
//    - what is the modifier of the class
//    - what all interfaces class has implemented.
//    - change the value of public and private fields of the class etc.


//    NOTE: REFLECTIONS are used very rarely - but good to know stuff. It essentially allows you to retrieve the metadata of the class
//    and also to change the behaviour of the class.


//2. How to do reflection of the classes?
//    To reflect the class, we first need to create an Object of class Class.
//    There's a class with the name class itself.
//    class Class{
//
//    }

//    what is this class Class?
//    - The instance of the class Class represents classes during runtime.
//    - JVM creates one Class object for each and every class which is loaded during runtime. ie for each class there is an associated Class object that contains all the metadata of that class.
//    - This Class object, has metadata information about the particular class like its method, fields, constructor etc.

//    How to get particular class Class object?

//    There are 3 ways:
//    1. Using forName() method

    // assume we have class named Bird
    class Bird{}

    // get the object of Class for getting the metadata information of Bird class
    Class birdClass = Class.forName("Bird");
    //    forName() returns the Class object for the class with the specified name.

//    2. Using .class method

    // assume we have class named Bird
    class Bird{}

    // get the object of Class for getting the metadata information of Bird class
    Class birdClass = Bird.class;


//    3. Using getClass() method

    // assume we have class named Bird
    class Bird{}

    Bird birdObj = new Bird();

    // get the object of Class for getting the metadata information of Bird class
    Class birdClass = birdObj.getClass();

//    NOTE: the first 2 method class is used to get the Class object, wheaeas in the 3rd method class instance is used to get the Class object.


//    3. how to do reflection of classes.

//    created a simple Bird class with 1 public field, 1 private field & 2 public methods
    public class Bird{
        public String breed;
        private boolean canSwim;

        public void fly(){
            System.out.println("fly");
        }

        public void eat(){
            System.out.println("eat");
        }

    }

    public class Main{
        public static void main(String[] args){
//            step1: get an instance of Class for whatever class which you want to reflect
            Class birdClass =  Bird.class;

//            step2: using the Class object you can fetch whatever metadata that you wanna fetch
//            returns name of the class
            System.out.println(birdClass.getName());        // Bird
//            reutrns modifier of the class
            System.out.println(birdClass.getModifiers());   // public

        }
    }

//    NOTE: Methods available in Class object are all get, no set methods
//    There are numerous get methods - using which you can fetch all the metdata info related to your class, class fields, methods, constructors etc
//    metadata info for class - classname, class modifier, methods list, constructors list etc
//    metdata info for method -method name, method modifier, return type, arguments etc
//    metdata info for fields - field name, field modifier, value assigned

//   NOTE:
//   The package "java.lang.reflect" provides classes that can be used to access & manipulate the value like fields, methods, constructor etc


}
