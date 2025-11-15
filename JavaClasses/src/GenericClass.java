public class GenericClass {
//    Generics in Java allow you to create classes, interfaces, and methods that operate on typed parameters, meaning you can specify the data type they work with at compile time instead of hardcoding it.
//    In simpler words:
//    Generics make classes and methods type-safe and reusable, so you can use them with different data types without rewriting the code.

//    Example without Generics

    class Box{
        private Object item;
        public void setItem(Object item){
            this.item = item;
        }
        public Object getItem(){
            return item;
        }
    }
//    Problem:
//    You can store any type of object, but you’ll need type casting when retrieving it, which can cause runtime errors.

    Box b = new Box();
    b.setItem("hello");

//    major problem with using Object is we dont actually know with surity what class object it is and thus it can throw errors.
//    This is where generic class comes into the picture. Using generic class it avoids this typecasting and makes it type safe
    Integer val = b.getItem(); // ❌ Runtime error: ClassCastException

    System.out.println(b.getItem() instanceof String); // true

//  instanceof is a binary operator used to test whether an object is an instance of a specific class or subclass, or implements a particular interface.


//    Example with Generics

//    This T can be replaced with any non Primitive object
    public class Box<T>{
        T item;
        public void setItem(T item){
            this.item = item;
        }
        public T getItem(){
            return item;
        }
    }
//    Now we can create type-safe objects:

    Box<String> strBox = new Box<>();
    strBox.setItem("Hello");
    String s = strBox.getItem();

    Box<Integer> intBox = new Box<>();
    intBox.setItem(10);
    Integer value = intBox.getItem();
    if(value == 10){    // now we dont need to typecast
        // do something
    }

//    Inheritance with generic class

//1. Non generic subclass
    public class Print<T>{
        T value;
        public void setPrintValue(T value){
            this .value = value;
        }
        public T getPrintValue(){
            return value;
        }
    }

    // subclass is non generic so we have to define the type of print class at the time of extend itself
    public class ColorPrint extends Print<String>{
    }

    public class Main{
        public static void main(String args[]){
            ColorPrint colorPrintObj =  new ColorPrint();
            colorPrintObj.setPrintValue("2");
        }
    }

//    2. Generic subclass
//    Here since subclass is also generic we dont need to specify the type of parent class.
//    We can define it at the time of object creation
    public class BlackPrint<T> extends print<T>{
    }

    public class Main{
        public static void main(String args[]){
            BlackPrint<String> blackPrintObj = new BlackPrint<>();
            blackPrintObj.setPrintValue("2");
        }
    }


//    More than 1 generic types
    public class Pair<K,V>{
        private K key;
        private V value;
        public void put(K key, V value){
            this.key = key;
            this.value = value;
        }
    }
    public class Main{
        public static void main(String args[]){
            // below mentioned both ways are valid for creating objects from generic class
            Pair<String,Integer> pairObj = new Pair<>();
            Pair<String,Integer> pairObj2 = new Pair<String,Integer>();
            pairObj.put("hello", 123);

        }
    }

//Generic methods
//    What if we only want to make method generic, not the entire class. We can write generic methods too.
//    Type parameter should be before the return type of the method declaration.
//    Type parameter scope is limited to method only.

    public class GenericMethod<T>{
        public <K,V>  void printValue(Pair<K,V> pair1, Pair<K,V> pair2){
            if(pair1.getKey().equals(pair2.getKey())){
                // do something..
            }
        }

        public <T> void setValue(T value){
            // do something..
        }

    }

}

//Raw type:
//    Suppose you have created a generic class, so while creating an object of this class you are supposed to pass the type (like String, Integer or any non primitive datatypes).
//    But if you dont pass the type then compiler implicitly passes Object as the type


//Bounded Generics
//        Suppose you are working with a generic class. Client can pass any Non primitive type. But if I want to restrict the types,
//then we use concept of bounded generics

// This is of 2 types
//    upper bound -- for ex: if we set upper bound as Number (we are restricting the client to only pass Number or its descendents. Now they cannot pass any other class like String etc
//    multi bound

//    Object (Parent)
//   Number(child1)        String(child2) and so on..
//  Integer,Double,and so on

//  Multi bound:
<T extends Superclass & interface1 & interfaceN>
//  We are essentially saying that we can pass an object which extends Superclass and implements interface1 and interfacen
//  The first restrictive type should be a concrete class
//  2,3, and so on can be interfaces

// As we know, in java a class can only extend one class, but it can implement multiple interfaces
//reason: lets assume that a class can inherit 2 classes, Parent1 & Parent2. Both Paren1 & Parent2 have a method display()
//Parent1     Parent2
//        Child
//Child class is inheriting Parent1 & Parent2 just for explaination purpose (not possible in actual)
//If I create an object of Child class (new Child()) and call method display(), compiler will not be able to determine which method to call since they both(Parent1 and Parent2) provide method display().
//Hence for this reason, Java doesnt allow inheriting multiple classes.
//
//But you can implement multiple interfaces
//Assumer your Child Class implements 2 interfaces interface1 and interface2
//
//Interface1      Interface2
//        Child
//Since in interfaces we only give method definition and not the actual implementation, here there's not going to be any conflict.
//Child class will override the display() from both the interfaces and will provide the implementation. Since there's just one implementation which is provided by the Child class, there's no conflict and Java can easily resolve which method to call.


ex:

