public class AccessModifiers {

//    🔑 Java Access Modifiers Visibility
//    Access Modifier	        Same Class	Same Package    Same Package	Different package   Other Packages
//                                            (non-subclass)	(subclass)      (subclass)          (non-subclass)
//    private     	            ✅ Yes	    ❌ No	        ❌ No	         ❌ No	                 ❌ No
//    default (package-private)	✅ Yes	    ✅ Yes	        ✅ Yes	         ❌ No	                 ❌ No
//    protected	                ✅ Yes	    ✅ Yes          ✅ Yes	         ✅ Yes (via inheritance ❌ No
//    public	                ✅ Yes	    ✅ Yes          ✅ Yes	         ✅ Yes                  ✅ Yes

//📌 Quick Memory Hack:
//    private → only inside the same class.
//    default → package only.
//    protected → package + subclasses across packages.
//    public → everywhere.

//    -----------------------------------------------------------

//    1. Default (Package-private)
//    If you don’t specify any access modifier, Java assigns default access.
//    Accessible only within the same package.
//    Not accessible from outside the package (even in subclasses).

    class A {
        int x = 10;   // default access
    }
    class B {
        void print() {
            A obj = new A();
            System.out.println(obj.x); // ✅ works (same package)
        }
    }

//    If B were in a different package → ❌ compile error.

//    2. Protected
//    Accessible within the same package (like default).
//    PLUS, accessible from subclasses in other packages (via inheritance).
//    But note: in subclasses, you can only access through inheritance, not by creating an object.

    // File: pkg1/A.java
    package pkg1;

    public class A {
        protected int y = 20;
    }

// File: pkg2/B.java
    package pkg2;
    import pkg1.A;

    class B extends A {
        void show() {
            System.out.println(y);  // ✅ works via inheritance
        }
        void test() {
            A obj = new A();
            // System.out.println(obj.y); // ❌ not accessible via object
        }
    }
}
