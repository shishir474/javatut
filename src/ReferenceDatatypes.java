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

    }
}