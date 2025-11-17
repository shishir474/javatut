import java.lang.reflect.Method;

public class Reflections2 {
//    Reflection of Methods

//    created a simple class to demonstrate reflection of methods
//    1 private field, 1 public field, 1 public method, 1 private method
    public class Bird{
        public String breed;
        private boolean canSwim;

        public void fly(int intParam, boolean boolParam, String stringParam){
            System.out.println("fly intParam " + intParam + "boolParam " + boolParam + "strParam " + stringParam);
        }

        private void eat(){
            System.out.println("eat");
        }

    }

    public class Main{
        public static void main(String[] args){
//          step1: create Class object
            Class birdClass =  Bird.class;

//           Object birdObj = new Bird();
//            or
            Object birdObj = birdClass.newInstance();     // creating instance of Bird class using Class object

            // NOTE: .getMethods() returns only the public methods (from the class & its parent class)
            Method[] methods =  birdClass.getMethods();     // [fly, public methods of Object class[

            // .getDeclaredMethods() returns all public & private methods only for Bird class
            Method[] methods2 =  birdClass.getDeclaredMethods();        // [fly, eat]

            // for each method in methods list
            for(Method method: methods){
                System.out.println("Method name: " + method.getName());
                System.out.println("Method Type: " + method.getReturnType());
                System.out.println("Class name: " + method.getDeclaringClass());
                System.out.println("*****************");
            }

            // invoking method using reflections
//            step1: retrieve the Method object
            Method flyMethod = birdClass.getMethod("fly", int.class, boolean.class, String.class);
//            step2: invoke the method
            flyMethod.invoke(birdObj, 1, true, "Shishir");
//            fly() method is invoked on the birdObj with the specified parameters

        }
    }
//    NOTE: you can retrieve all possible metadata associated with the methods like
//    method name, return type, default value, modifier, parameterCount, parameterTypes, declaringClass, exceptions it throws etc.
//    you can even invoke the methods using invoke()


}
