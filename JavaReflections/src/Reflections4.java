import java.lang.reflect.Constructor;

public class Reflections4 {
//    Reflection of constructors        (VVV IMP)
//    This is very imp because this allows you to break the Singleton pattern.
//    In singleton, we create a private constructor, to avoid instantiating the class from the outside. But using reflections, we can call the private constructors and create an instance.
//    In sigleton, the objective is only object should be created. hence we create a private constructor

    class Eagle{

        private Eagle(){
            // private constructor
        }

        public void fly(){
            System.out.println("fly");
        }
    }

//    NOTICE: Eagle class has a private constructor, which means you cannot call new Eagle() or
//    you cannot create an instance of Eagle class (possible within the Eagle class)
//    But using constructor reflection, you can create an instance of Eagle inside Main class and call fly() method

    class Main{
        public static void main(String[] args){
//            step1 : create Eagle Class object
            Class eagleClass =  Eagle.class;

//            fetch private constructor
//            .getDeclaredConstructors() returns a list of all (private & public) constructors within Eagle class
            Constructor[] constructors = eagleClass.getDeclaredConstructors();

            for(Constructor constructor: constructors){
                System.out.println(constructor.getModifiers());

//              this allows Main class to access the private constructor
                constructor.setAccessible(true);
                Eagle eagleObj = (Eagle) constructor.newInstance();
                eagleObj.fly();

            }

        }
    }

//    NOTICE; how despite declaring a private constructor, we were able to create an instance(outside the class). This is how singleton can be broken

//    Below are some of the reasons why reflections are not encouraged to use:
//    1. it breaks the OOPS principle of encapsulation (allows you to access and manipulate private fields and methods). You intend to mark a field/method private, but still using reflections you are able to access it.
//    2. reflection is slow, because all of these is happening at the runtime. Invoking a method directly is much faster than invoking it via reflection.
//    3. It breaks type safety.
//    4. it also makes troubleshooting difficult, introduces security vulnerabilities in your code.

//    Hence the recommendation is try to avoid using it as much as possible. If you can access the fields/methods directly - that is a better approach


}
