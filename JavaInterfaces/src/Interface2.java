public class Interface2 {
    // why we need an interface?
    // 1. Abstraction: Using interface, we can achieve full abstraction means, we can define WHAT class must do, but not HOW it will do
    public interface Bird{
        public void fly();          // just provide signature, no implementation
    }

    public class Eagle implements Bird{
        @Override
        public void fly(){
            // implementaion goes here
            System.out.println("eagle fly implementation");
        }
    }

    public class Hen implements Bird{
        @Override
        public void fly(){
            // implementaion goes here
            System.out.println("eagle fly implementation");
        }
    }

    // 2. Polymorphism: interface can be used as a datatype.
    // We cannot create the object of an interface, but it can hold the reference of all the classes
    // which implements it. And at runtime it decides which method needs to be invoke.
    // This allows us to dynamically call the methods based upon the objects.
    // It decides at the runtime, which method should be called. Hence it also allows us to achieve polymorphism.

    // NOTE: You cannot create an object of interface (bcoz in interface you just provide method signature)
    // Only concrete classes can be instantiated.
    public class Main{
        public static void main(String[] args){
            Bird b1 = new Eagle();
            Bird b2 = new Hen();
            b1.fly();       // calls eagle's fly() method
            b2.fly();       // calls Hen's fly() method
        }
    }

    // 3. Multiple inheritance
    // first lets understand Diamond problem. Java allows at most one class to be extended, but you implement multiple interfaces
    public class WaterAnimal{
        public boolean canBreathe(){
            return true;
        }
    }
    public class LandAnimal{
        public boolean canBreathe(){
            return true;
        }
    }
    // Suppose you can inherit multiple classes -- what's the problem. lets analyse that
    public class Animal extends WaterAnimal, LandAnimal {
    }
    public class Main{
        public static void main(String[] args){
            Animal a = new Animal();
            a.canBreathe(); // now which method should be called here? We have 2 different versions provided by LandAnimal & WaterAnimal class?
            // In order to resolve this ambiguity, Java does not allow inheriting multiple classes,
            // because there can be a scenario like this where you have same methods provided by both the parent & child class is now confused and is not able to determine which one to call.
        }
    }

    // But this problem is not present with interfaces, because in interfaces you just provide the signature & not the actual implementation
    // class implementing the interface is responsible for providing the implementation of the method & thus there will always be one single version & therefore no ambiguity.
    public interface LandAnimal{
        public boolean canBreathe();
    }
    public interface WaterAnimal{
        public boolean canBreathe();
    }
    // Animal class implements both interfaces. Animal class has to provide the implementation of all the methods defined in interfaces LandAnimal & WaterAnimal -- allowed
    public class Animal implements WaterAnimal, LandAnimal {
        @Override
        public boolean canBreathe(){
            return true;
        }
    }
    public class Main{
        public static void main(String[] args){
            Animal a = new Animal();
            a.canBreathe(); // Here's there is no ambiguity. This simply calls the canBreathe() method of Animal class
        }
    }

}
