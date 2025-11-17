import java.lang.reflect.Field;

public class Reflections3 {
//    reflections of fields
//    created a simple class to demonstrate reflection of fields
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
            Class birdClass =  Reflections2.Bird.class;

//          returns a list of all the public fields from Bird class
            Field[] fields = birdClass.getFields();

//            returns a list of all the fields (public & private) from Bird class
            Field[] fields2 birdClass.getDeclaredFields();

            for(Field field: fields2){
                System.out.println("FieldName: " + field.getName());
                System.out.println("Field Modifier " + field.getModifiers());
                System.out.println("Type " + field.getType());
                System.out.println("*******************");
            }

//            setting value of public field
            Bird birdObj =  birdClass.newInstance();
            Field breedField =  birdClass.getDeclaredField("breed");
//            setting the value of breedField for birdObj
            breedField.set(birdObj, "eagle brown bread");
            System.out.println(birdObj.breed);


//            setting value of private field

//          accessing private field using reflection
            Field canSwimField = birdClass.getDeclaredField("canSwim");
//           since canSwim is a private field, in order to set its value, you need to set setAccessible to true
//            This provides appropriate access to manipulate the private field
            canSwimField.setAccessible(true);
//          NOTE: canSwim is a private field, but still we are able to access it here in the main class using reflections.
            Bird birdObj = new Bird();
//            for which object, do you intend to set this value
            canSwimField.set(birdObj, true);
//            if(birdObj.canSwim){      // This is illegal. You cannot access canSwim this way coz its a private field

//            This is how you can access the private boolean field of an instance
            System.out.println(canSwimField.get(birdObj));  // this is allowed
            System.out.println(birdObj.canSwim);    // this is illegal (not allowed)

            if(canSwimField.getBoolean(birdObj))
                System.out.println("value is set to true");
            }
//          reason for this behaviour is:
//          reflection bypasses access control only at runtime.
//          Java's visibility rules are enforced at compile time, so the field remains private and cannot be referenced directly outside the class.

        }
    }




}
