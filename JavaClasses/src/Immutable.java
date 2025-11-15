import java.util.ArrayList;
import java.util.List;

// final -- means we dont want any subclass to override its behaviour and break its immutability.
// Declaring class as final -- means the class cannot be extended (so no subclass is allowed).
public final class Immutable {
    // Immutable -- means we cannot change the value of an object once it is created.

    // imp points:
    // Declare the class as 'final', so that it cannot be extended.
    // Declare all class members as private, So that direct access is avoided.
    // And class members are initialized only once using constructor.
    // There shouldn't be setter methods, which is generally used to change values.
    // Just public getters methods, and always return Copy of the member variable
    // Example: String, Wrapper Classes etc

    // private -- means the variables cannot be accessed outside this class. Would need public getters/setters
    // final -- means these variables are immutable, once values are assigned, it cannot be changed
    private final String name;
    private final List<Object> petNameList;

    // value set only once - inside the constructor
    Immutable(String name, List<Object> petNameList){
        this.name = name;
        this.petNameList = petNameList;
    }

    public String getName() {
        return name;
    }

    public List<Object> getPetNameList() {
        // this is required because making list final
        // means you cannot point it to new list, but still can add, delete values from it.
        // So that's why we return a copy of it.

        return new ArrayList<>(petNameList);
    }
}


public class Main{
    public static void main(String[] args){
        List<Object> petNameList = new ArrayList<>();
        petNameList.add("sj");
        petNameList.add("pj");

        Immutable obj = new Immutable("shishir", petNameList);
//       petNameList ref ------------> "original memory location"  has 2 items - "sj" "pj"
//                                 "copy memory - 1" has 2 items initially, then "hello" is added, so 3 items "sj" "pj" "hello"    X (memory frees up after some time)
//                           since "copy memory" ref is not stored anywhere, Garbage collector frees up this memory
//                                                          "copy memory - 2" has 2 items "sj" "pj"  -- THis memory is also freed up after the print operation

        obj.getPetNameList().add("Hello");  // creates "copy memory - 1"
        System.out.println(obj.getPetNameList());  // creates "copy memory - 2"

    }
}

// why returning a copy of the list is important?
// if you return the original memory ref, performing the add operation will change the original list, & thus next time you call getPetNameList() you will get the modified list.
// immutable means once the values are assigned, its fixed. You cannot modify it. But here in lists, modification is possible
// private final List<Object> petNameList
// declaring list as final, only means that its reference is final(but list can be modified). When a list is created,
// some memory is allotted to it & its reference is stored in petNameList Variable.
// marking petNameList variable as final means that this variable will always point this list no matter what & it can never point to other memory reference.
// if you return the original ref, your original list might get corrupted & thus in order to prevent that
// & to maintain immutable behaviour(of the values of list, just to ensure that values dont change and we always get the same original version) we return the copy of the list

// That's why it is recommended to always return the copy of the collection in the getter method.
// Always remember, making list final just means -- its reference cannot be broken/changed. List modification is still possible.


//Why declaring class final is mandatory, for immutability ?
//    To make class immutabitle, we declare class as final, so that it cannot be extended.
//
//What is the harm if a class is allowed to be extended? How will it break immutability?

        public class Money {
            private final int amount;

            public Money(int amount) {
                this.amount = amount;
            }

            public int getAmount() {
                return amount;
            }
        }
        // If it's not final, someone can subclass it:

        public class MutableMoney extends Money {

            private int amount;

            public MutableMoney(int amount) {
                super(amount);
                this.amount = amount;
            }

            @Override
            public int getAmount() {
                return amount;
            }

            public void setAmount(int amount) {
                this.amount = amount;  // Mutability added
            }
        }

//        You're right that the setter only changes MutableMoney.amount, not Money.amount.
//
//        But immutability is broken because a subclass instance can still be treated as a Money object but behave mutably.

        Money money = new MutableMoney(100);
        System.out.println(money.getAmount());  // 100

        ((MutableMoney) money).setAmount(500);

        System.out.println(money.getAmount());  // 500
//        From the outside world, your supposedly immutable Money object is now mutable because a child class changed its behavior.