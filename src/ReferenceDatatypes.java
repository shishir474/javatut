//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class ReferenceDatatypes {
    public static void main(String[] args) {
        String s1 = "Hello"; // adds this string literal in the string constant pool in the heap memory
        String s2 = "Hello";
        // since this string literal already exists in string constant pool, s1 and s2 will point to the same string literal

        System.out.println(s1 == s2);
        // == checks if s1 and s2 both points to the same memory? Yes, they do

        System.out.println(s1.equals(s2));
        // equals() check if the hold the same content? Yes, they do

        String s3 = new String("Hello");
        // created a string object using new keyword. Java will allocate separate memory in the heap space and s3 will hold the reference of that memory.
        // This object is created outside string constant pool in the heap

        System.out.println(s1 == s3);
        // == checks if s1 and s3 both points to the same memory? No, they don't

        System.out.println(s1.equals(s3));
        // equals() check if the hold the same content? Yes, they do

        // strings are immutable.
        // since "Hello world" string litereal didnt exist in the string constant pool, java created this and now s1 points to "Hello World"
        // Here s1's reference changed and now it points to "Hello World"
        s1 = "Hello World";
        System.out.println(s1);

        // string just hold the reference of the string literals creqted inside the string constant pool or
        // they hold the reference of the objects created inside heap using new keyword

        // WHy strings are considered immutable?
        // This is because, the prev value of s1 "Hello" didnt change. it still exists in the string constant pool.
        // A new literal was created in the constant pool "Hello world" and now s1 holds the reference of that literal.
        // Hence strings are considered immutable.



        // In interface, you just define the methods & not actually implement it
        // You create childs of this interface, which will actually implement that interface.
        // The class which implements interface, needs to provide the implementation of the method's defined in the interface & that implementation willl override the interface's method
        // You can create child(interface's child) class objects and can store its reference in the child class itself or in its parent class.

        // For instance:
//            Assume you have created an interface Person and defined a method profession
//        public interface Person {
//            public String profession();
//        }

//  Then you created 2 child classes Teacher and Engineer which implements this interface and you provided the implementation of interface's methods which overrides the actual methods of the interface
//        public class Teacher implements Person{
//            @Override
//            public String profession(){
//                return "teaching";
//            }
//        };
//
//        public class Engineer implements Person{
//          @Override
//          public String profession(){
//              return "software engineer"
//          }
//        };
//
//        Teacher t1 = new Teacher();
//        Person t2 = new Teacher();
//        above lines creates 2 teacher objects in the heap memory and their reference is stored in t1 and t2 variables.
//        t1 is of type Teacher, whereas t2 is of type Person. Both are valid

//        Engineer e1 = new Engineer();
//        Person e2 = new Engineer();
//        these creates 2 engineer objects in heap memory whose reference is stored in e1 and e2 which is of type Engineer and Person respectively.



    }
}