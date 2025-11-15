// Below is an enum class. Notice we aren't using class keyword. Instaed enum keyword is used to declare an enum class
public enum EnumSample {
    // Enum class
    // Enums are collection of constants ( variables whose values cannot be changed)
    // Its constants are static & final implicitly ( we dont have to write it )
    // it cannot extend any class. as it internally extends java.lang.Enum class
        // we already know, in Java a class can extend atmost one class
    // it can implement interfaces
    // it can have variables, constructors & methods
    // it cannot be initiated (as its constructor is private only, even if you give default, in bytecode it makes it private)
    // no other class can extend Enum class
    // it can have abstract method, and all the constant should implement that abstract method


    // constants/ordinals are assigned to each enum values starting from 0 ( if you haven't assigned any custom value)
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY,
    SUNDAY;
}


public class Main{
    public static void main(String args[]){
        /*
        Common functions which are used
            - values() --> returns a list of all constants defined in your enum
            - ordinal() --> returns the constant value which is assigned internally to each enum constant
            - valueOf() -> matches the string passed with each enum values & returns the corresponding enum
            - name()   --> prints the original enum name/value
         */

        // iterating over enum values using values() method. Notice the method is acccesed directly using classname,
        // which means it's a static method(i.e belongs to the class), but we haven't defined any static method.
        // All Enum classes inherits java.lang.Enum class. values() method is defined in java.lang.Enum class
        // values() method returns an array of the constants
        for(EnumSample sample: EnumSample.values()){
            System.out.println(sample.ordinal()); // ordinal()  -> returns the constant values assigned to each enum sample
        }
        // output:  0 1 2 3 4 5 6 7


        EnumSample enumVariable =  EnumSample.valueOf("FRIDAY");
        System.out.println(enumVariable.name());
        // output: FRIDAY

    }
}
