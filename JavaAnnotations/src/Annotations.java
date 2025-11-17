import java.lang.annotation.*;
import java.util.ArrayList;
import java.util.List;

public class Annotations {
//    Annotations is a kind of metadata that is added to java code. its usage is optional.
//    We can use this metadata info at runtime and can add certain logic in our code if wanted.
//    How to access this metadata?  Using reflections
//    Annotations can be applied anywhere like in classes, methods, fields, interface etc

//    Types of annotations
//    1. Predefined annotations
//            Used on Java code (like clases, methods etc)
//                - @Deprecated
//                - @Override
//                - @SuppressWarnings
//                - @FunctionalInterface
//                - @SafeVarargs

//            Used on annotations (called Meta annotations)
//                - @Target
//                - @Retention
//                - @Documented
//                - @Inherited
//                - @Repeatable   (java8 feature)

//    2. Custom annotations / user defined
//            @{our custom name}



//Annotations on Java code
//    1. @Deprecated
//        - usage of Depracated Class or Method or fields, shows you compile time WARNING
//        - Deprecation means, no further improvement is happening on this and use new alternative method or field instead.
//        - Can be used over: Constructor, Field, Local Variable, Method, Package, Parameter, Type(class, interface, enum)

    public class Mobile{
        @Deprecated
        public void dummyMethod(){
            // do something
        }
    }
    public class Main{
        public static void main(String[] args){
            Mobile mob = new Mobile();
            mob.dummyMethod();          // gives compile time warning, since this method is depracated.
        }
    }

// 2. @Override
//    - At the compile time, it will check that the method should be overriden
//    - and throws compile time error, if it does not match with the parent method.
//    - Can be used over methods


//  3. @SuppressWarnings()
//    - it will tell compiler to IGNORE any compile time WARNING
//    - Use it safely, could lead to Runtime Exception, if any valid warning is ignored.
//    - Can be used over: Fields, Methods, Parameters, Constructors, Local Variables, Type (class or interface or enum)

    public class Mobile{
        @Deprecated
        public void dummyMethod(){
            // do something
        }
    }
    public class Main{

        @SuppressWarnings("deprecation")                    // suppress deprecation warning
        public static void main(String[] args){
            Mobile mob = new Mobile();
            mob.dummyMethod();          // gives compile time warning, since this method is depracated.
        }
    }
//    "deprecation" -> SuppressWarnings strings - there are bunch of such strings. Some others are "unused", "all" etc


//   4. @FunctionalInterface
//    - Restricts interface to have only 1 abstract method
//    - Throws compilation error, if more than 1 abstract method found.
//    - Can be used over: Type(class or interface or enum)

    @FunctionalInterface
    public interface Bird{
        public boolean canBreathe();
//        public void fly();            // since we marked Bird interface as Functional interface, it restricts the interface to have only 1 abstract method.
    }


//    5. @SafeVarargs -- safe variable arguments
//    - Used over methods and constructors which has Variable Arguments as parameter.
//    - Used to suppress "Heap pollution warning"
//    - Method should be either static or final (i.e methods which cannot be overriden)
//    - In java9, you can also use it on private methods.

//    What is Heap pollution?
//    Object of one type (ex: String), storing the reference of another type of object (ex: Integer)
//    NOTE: void testMethod(Integer... val){
//
//        }
//    Integer... val means the method accepts a variable number of Integer arguments, also called varargs.
//    testMethod can accept 0 arg, 1 arg, multiple arguments, or an array of integers.
//    Internally Java compiles it to
//    void testMethod(Integer[] val) is equivalent to void testMethod(Integer... val)
//    Remember
//        - Only one varargs parameter is allowed in a method
//        void m(Integer... a, String... b);      // ❌ Not allowed

//        - Varargs must be the last parameter
//        void m(String x, Integer... nums);  // ✔ Allowed
//        void m(Integer... nums, String x);  // ❌ Not allowed


    public class Sample{
//        accepts an array of List<Integer>
        @SafeVarargs       // suppresses the Heap pollution warning
        public static void sampleMethod(List<Integer>...logList){
            Object objRef = logList;
            List<String> stringList = new ArrayList<>();
            stringList.add("Hello");

            objRef[0] = stringList;
//            logList is an array of List of Integers.
//            using objRef reference, we added a string list at the 0th index
        }
    }



//    Meta annotations
//    1. @Target
//    - This annotation is used to specify where this annotation can be applied
//    - This meta annotation will restrict, where to use this annotation.
//    - Either at method or constructor or fields etc.

    @Target(ElementType.METHOD)
    public @interface Override{
    }

    @Target({ElementType.METHOD, ElementType.CONSTRUCTOR})
    public @interface SafeVarargs{}

//    This is how you create custom annotation -->  public @interface AnnoName
//    and then you define the target on which this annotation can be applied using @Target
//    @Override can be used only over methods
//    @SafeVarargs can only used over constructors or methods (that are accepting variable arguments)

//    ElementType
//    - METHOD
//    - CONSTRUCTOR
//    - CLASS
//    - FIELD
//    - TYPE (class, interface, enum) -- @Target(ElementType:TYPE) means this annotation can be used over class, interface or enums
//    - PARAMETER
//    - LOCAL_VARIABLE
//    - PACKAGE
//    - TYPE_PARAMETER (allows you to apply on Generic types <T>)
//    - ANNOTATION_TYPE (specifying @Target(ElementType.ANNOTATION_TYPE)) means you are declaring your custom annotation as meta annotation and it can be used over other annotations.
//        ex:
        @Target(ElementType.ANNOTATION_TYPE)
        public @interface Target{}
//      @Target is a meta annotation, it can be used over other annotations


//    @Retention
//    - This meta annotation tells, how Annotation will be stored in Java.

//        - RetentionPolicy.SOURCE: source means annotations will be discarded by the compiler during the compilation stage and it will not be captured in the .class file
//        - RetentionPolicy.CLASS: class means annotations will be recorded in .class file but will be ignored by JVM during runtime.
//        - RetentionPolicy.RUNTIME: annotations will be recorded in .class file + available during runtime. Usage of reflections can be done.

//    ex:
    // this is how src cd Override annotation looks like. RetentionPolicy is set to SOURCE, which means the annotations will not reach .class file & are discarded by the compilers
    // ElementType is set as METHOD, which means the target usage of this annotation is for METHODs
    @Retention(RetentionPolicy.SOURCE)
    @Target(ElementType.METHOD)
    public @interface Override{}

//   Eagle.java
    public class Eagle{
        @Override
        public void fly(){
            // do something
        }
    }

//    Eagle.class (after compilation, .class file is generated. Notice - annotations didn't make it in .class file. This behaviour is controlled by @Retention(RetentionPolicy.SOURCE))
    public class Eagle{
        public void fly(){
            // do something
        }
    }




















//    3. @Documented
//    - By default, annotations are ignored when java documentation is generated.
//    - With this meta annotation, even annotations can be captured in java doc

//    Assume you created a custom annotation
    @Documented                 // this meta annotation allows your annotations to be captured in java doc
    @Retention(RetentionPolicy.SOURCE)
    @Target(ElementType.METHOD)
    public @interface Override{}

    //   applied the custom annotation on the method (just an ex: @Override is not a custom annotation)
    public class Eagle{
        @Override
        public void fly(){
            // do something
        }
    }

//    Now when you generate java doc -- this annotations will now show up in the doc. If you want to capture annotations as well,
//    just mention @Documented meta annotation

//    NOTE: This meta annotations can only be placed on annotations declarations (i.e while creating custom annotations) - and not on annotations usage
    @Documented     // @Documented is a meta annotation, it can only be used while declaring new custom annotation, not on top of pre defined annotations
    @Override
    public void test() {}



//    4. @Inherited
//    By default, annotations applied on the Parent class are not available to child classes.
//    But if you use @Inherited meta annotations, annotations will be available to child classes as well.
//    This meta annotation has no effect, if annotation is used other than a class

    @Inherited                  // assume this custom annotation is applied on parent class -- it will also be available to child class
    @Retention(RetentionPolicy.RUNTIME)     // this annotation will be available at runtime
    @Target(ElementType.TYPE)           // this means, your custom annotation can be applied on Class, Interface, Enums
    public @interface MyCustomAnnotationWithInherited{
    }

    @MyCustomAnnotationWithInherited
    public class Parent{

    }

    public class Child extends Parent{

    }

    public class Main{
        public static void main(String[] args){
            System.out.println(new Child().getClass().getAnnotation(MyCustomAnnotationWithInherited.class));

//            new Child() --> returns a new instance of Child class
//            new Child().getClass() --> returns Class object of Child class (reflections)
//            you can use this class object to retrive metadata of your class like annotations
//            new Child().getClass().getAnnotation()
//            In order to access the custom annotation at runtime, we use @Runtime meta annotation & specified RetentionPolicy as RUNTIME (this ensures that the custom annotation is captured in .class file and is available to be used by jvm during runtime)
//            By default annotations supplied on the Parent class are not passed to Child class. But since we used @Inherited meta annotation, annotations supplied on the parent class are passed to child classes as well
//            which can be verified by fetching the metadata(annotation) of the child class.
//            To fetch metadata of a class, we first need to create its Class object and then use the appropriate get method to fetch relevant metadata of your class
//            If we don't supply the @Inherited meta-annotation on our custom annotation, then that annotation only applies to the Parent class. It will not be passed/inherited to the child class.

//            Why MyCustomAnnotationWithInherited.class inside getAnnotation()?
    //            ✔ MyCustomAnnotationWithInherited is just the name of the annotation
    //            It’s not an object, not a type, and cannot be passed to a method.
    //            ✔ MyCustomAnnotationWithInherited.class is a Class object
    //            It represents the annotation’s type at runtime.
    //            getAnnotation() method expects a Class object

        }
    }


//      5. Repetable
//    - Allows us to use the same annotation more than 1 at same place
//    - available from java8

//    ex: assume we have a use case where we want to repeat annotations like

    // custom annotation @Category

    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    @interface Category{
        String name();                  // annotation member (this is just like a field in class)
    }

//    You cannot assign same annotations like this multiple times on the same class
//    here we need to use @Repetable meta annotation
    @Category(name = "Carnivorous")         // X This is incorrect
    @Category(name = "Eagle")
    public class Eagle{
        public void fly(){

        }
    }

//    Correct way of doing this is
//      create a container annotation that will contain all the annotations
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Categories{
        Category[] value();             // list of @Category annotations
    }

    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    @Repeatable(Categories.class)
    @interface Category{
        String name();                  // annotation member (this is just like a field in class)
    }

    @Category(name = "LivingThing")
    @Category(name = "Carnivorous")
    @Category(name = "Eagle")
    public class Eagle{
        public void fly(){

        }
    }

    public class Main{
        public static void main(){
            // fetch all the Category annotations. Remeber, getAnnotationsByType() method accepts class object
            // create object of eagle class, then retrieve Eagle class Class object and then retrieve the Category related annotations
            Category[] catgAnnotationsArray =  new Eagle().getClass().getAnnotationsByType(Category.class);
            for(Category catgAnnotation: catgAnnotationsArray){
                System.out.println(catgAnnotation.name);        // accessing the name key
            }
        }
    }

//    NOTE:
// Whatever annotation you want to repeat, on top of it use @Repeatable annotation and provide one container class (in our case Categories)
// Container class should have an array of custom annotation (in our case Category


//    User Defined or Custom Annotations
//    We can create our own annotation using keyword @interface

//    In below ex:
//    currently we have not specified any explicit targets where this annotation should be used. Use @Target meta annotation to restrict its usage to specific components
//    Also we haven't supplied any Retention policy, so this annotation will not be availble at the runtime. In fact, the default retention policy is SOURCE (so it's not visible in the .class file as well)
//    Also we haven't used @Documented meta annotation, so this annotation will not show up in java docs
//    Also we haven't used @Inherited meta annotation, so this annotation will only be limited to the parent class. No child class will have access to this annotation
//    Also We haven't use @Repetable, so we cannot repeat this annotation as well. In order to repeat it, we first need to create a container class (create a separate annotation that will have an array which will contain all the repetable annotations)

    //    created a custom annotation
    public @interface MyCustomAnnotation{
    }

//    supplied the custom annotation on top of a class
    @MyCustomAnnotation
    public class Eagle {
        void fly() {
        }
    }

//    creating an annotation with method (its more like a field)
//    No parameter, no body
//    return type is restricted to Primitive, class, String, enums, annotations, and array of these types.
//    can also set the default value of the field
    public @interface MyCustomAnnotation{
        String name() default "Hello";
    }

    @MyCustomAnnotation(name = "testing")
    public class Eagle {
        void fly() {
        }
    }



}





