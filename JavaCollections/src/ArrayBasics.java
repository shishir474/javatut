import java.util.*;

public class ArrayBasics {
    public static void main(String[] args) {
        // Integer v = 10;
        // System.out.println(v.intValue());

        Integer[] arr = {3000, 4100, 9000, 1000, 3500};
        int[] arr1 = new int[5]; // creates array of primitive int -- all the values are initialised with 0
        Integer[] arr2 = new Integer[5]; // creates array of Integer type -- all values are of type Integer and the array is initialed with null

        // attempts to unbox Integer --> int (internally calls Integer.intValue() )
        // Throws NPE, since all elements are null
        // for(int val: arr2){
        //     System.out.println(val);
        // }

        for(Integer val: arr2){
            System.out.print(val + " ");
        }
        System.out.println();       // prints new line

        // Initialises the arr2 with 0
        Arrays.fill(arr2, 0);

        for(Integer val: arr2){
            System.out.print(val + " ");
        }
        System.out.println();       // prints new line

        String s = "Shishir";
        System.out.println("prints arr2 object address: " + arr2); // prints arr2 object address
        System.out.println("prints actual string: " + s); // prints actual string

        // quickiest way to print arr
        System.out.println("prints arr2 " + Arrays.toString(arr2)); // prints arr2

        //  When to choose Integer[] vs int[] ?
        // int[] is an array of primitive int values.
        // Use when
        //     - Maximum performance
        //     - less memory usage
        //     - no null values  (primitives default to 0). Hence no risk of NPE

        // Integer[] is an array of Integer objects (wrapper class )
        // Use when you need objects, nullable values or with Collections, Streams.
        // Collections (List<Integer>) leverage Generics and Generics can't use primitives
        // Extra overhead of Autoboxing (int -> Integer) and Unboxing (Integer -> int) which makes it slow
        //     and can cause NPE


        int cnt = 0;
        for(int i = 0; i < arr.length; i++){
            if(arr[i] > 3000) cnt++;
        }

        System.out.println("Total Employees with salary > 3000: " + cnt);




    }

}
