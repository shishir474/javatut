public enum EnumCustomSample {
    // Enum with custom values
    // all these constants are of type EnumCustomSample & they have 2 members(val, comment)
    MONDAY(101, "1st day of the week"), // this will call the constructor
    TUESDAY(102, "2nd day of the week"),
    WEDNESDAY(103, "3rd day of the week"),
    THURSDAY(104, "4th day of the week"),
    FRIDAY(105, "5th day of the week"),
    SATURDAY(106, "6th day of the week"),
    SUNDAY(106, "7th day of the week");

    // notice above we are passing 2 values
    // so first we need to have appropriate variables to catch these values
    // second we need parametrized constructor - which will initialize the values
    // each enum constant above has its own copy of val & comment variable as well as the methods(getters/setters)
    // whatever methods that you want to define at class level - you need to declare them static (so that it wont be there for each constant separately)
    private int val;
    public String comment;

    // notice we haven't given any access specifier to the constructor, by default its default
    // but when the byte code is generated it converts the default to private
    // enums constructors are always private & hence you cannot instantiate enum classes
    EnumSample(int val, String comment){
        this.val = val;
        this.comment = comment;
    }

    // there can be static(class level) & non static(enum constants level) methods

    // getVal() is a non static method. Each enum will have its own version
    public int getVal() {
        return val;
    }

    // getComment() is a non static method. Each enum will have its own version
    public String getComment() {
        return comment;
    }

    // getEnumFromValue() is a static method - meaning it's shared across all the enum constants
    public static EnumCustomSample getEnumFromValue(int value){
        for(EnumCustomSample sample: EnumCustomSample.values()){
            if(sample.val == value){
                return sample; // return value of type EnumCustomSample
            }
        }
        return null;
    }

}

public class Main{
    public static void main(String[] args){
        // since getEnumFromValue() is a static method, I dont need an object, and can call the method using classname itself.
       EnumCustomSample sampleVar =  EnumCustomSample.getEnumFromValue(102);
       System.out.println(sampleVar.getComment());
    }
}


// Enum with Method Override
public enum EnumSample3{
    MONDAY{
        @Override
        public void dummyMethod(){
            System.out.println("Monday dummy method");
        }
    },
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY,
    SUNDAY;

    // dummyMethod() is non static meaning each enum constant will have its separate version
    // assume MONDAY wants to have some different behaviour -- we can override the dummyMethod for MONDAY
    // This way you can provide for each enum their own separate versions
    public void dummyMethod(){
        System.out.println("default dummy method");
    }
}


// Enum with Abstract methods
public enum EnumSample3{
    MONDAY{
        public void dummyMethod(){
            System.out.println("Monday dummy method");
        }
    },
    TUESDAY{
        public void dummyMethod(){
            System.out.println("Tuesday dummy method");
        }
    },
    WEDNESDAY{
        public void dummyMethod(){
            System.out.println("Wednesday dummy method");
        }
    };

    // defined an abstract method -- now each enum constant has to provide its implementation
    public abstract void dummyMethod();
}


public class Main{
    public static void main(String[] args){
        // since getEnumFromValue() is a static method, I dont need an object, and can call the method using classname itself.
        EnumSample3 mondayEnumSample =  EnumSample3.MONDAY;
        mondayEnumSample.dummyMethod();
    }
}



// Enums with interfaces
public interface MyInterface{
    public void toLowerCase();
}

// interfaces are generally used with enums when you want the same method to be used by all the enum constatns

// below enum EnumSample4 implements one interface MyInterface
public enum EnumSample4 implements MyInterface{
    MONDAY,
    TUESDAY,
    WEDNESDAY;

    // this method is generic and common for all. Instead of declaring it as abstract & then providing the implementation for each enum
    // we used interface
    // providing the implmentation of the interface's method - ofcourse, you can override it as well for each enum constant
    @Override
    public String toLowerCase() {
        // here this refers to enum constant MONDAY, TUESDAY etc
        return this.name().toLowerCase();
    }
}

public class Main{
    public static void main(String[] args){
       EnumSample4 mondayEnumSample =  EnumSample4.MONDAY;
       mondayEnumSample.toLowerCase();
       // output: monday
    }
}