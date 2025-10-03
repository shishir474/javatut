public class JavaMemoryManagement {
    // java memory management & garbage collector

    There are 2 types of memory which Java creates
    - stack
    - heap
    Both these memory are created by JVM in RAM.

    It divides the memory into 2 parts Stack and Heap and stores different types of data inside it.

    Stack Memory
        1.Stores temporary variables and separate memory blocks for methods.
        2. Stores primitive data types.
        3. Stores reference of heap objects
            a. Strong reference
            b. Weak reference
            c. Soft reference
        4. Each thread has its own copy of stack mempoy.
        5. Variables are visible only within their own scope, and as soon as variable goes out of scope, it gets deleted from stack in LIFO order.
        6. When stack memory goes full, it throws java.lang.StackOverflowError

    NOTE: Heap generally has more memory thatn Stack & it has only one copy which is shared by all threads.

        What kind of data is stored in stack memory?
        variables, heap object references, methods (memory block is allocated for each method in stack & all variables that are created inside that method are allocated space within this memory frame)

    public class MemoryManagement{
        public static void main(String args[]){
            // created primitive datatype
            int primitiveVar = 10;
            // created Person object
            Person personObj = new Person();
            // created string literal in string constant pool
            String stringLiteral = "24";
            // created MemoryManagement Object
            MemoryManagement memObj = new MemoryManagement();
            // invoking method
            memObj.memoryManagementTest(personObj);
        }
        private void memoryManagementTest(Person personObj){
            // created another reference for personObj object
            Person person2 = personObj;
            // "24" literal already exists in string constant pool. just creates another reference literal2
            String literal2 = "24";
            // created a string object in heap
            String stringObj = new String("24");
        }
    }

    1. main() method is invoked. For each method, memory frame is allocated in stack. All the variables created inside main() method are stored within main() method memory block.
    2. Created a primitive variable int a = 10 -> doesnt hold any reference just the value.
    3. created a new person class object. Memory is allocated in heap and its reference is stored in stack.
    4. Created a string literal in string constant pool. This is only done if the literal doesnt already exist in the string pool, otherwise it just points to existing literal object.
        In our case, this literal didnt exist before, hence a new literal is created in the string constant pool and its reference is stored in stringLiteral in stack.
    5. created a new MemoryManagement object. Again some space is allocated on the heap and its reference is stored in stack.
    6. Invoked a method memoryManagementTest(). This creates a new memeory frame in stack for this method. We are passing refeenrce of person object.
        So a reference will be added in stack and it will point to personObj object in heap.
    7. Created another reference for personObj object.
    8. Created another reference for string literal. Since this literal already exists in string constant pool, only a reference was created and this reference again is stored on stack.
    9. Created String object stringObj. Space is allocated on heap(outside string pool) and its reference is stored in stringObj variable on stack.
    10. closing bracket of memoryManagementTest() reached. Function execution complete. All the variables/references created as part of this invokation are deleted in LIFO order.
        First reference for stringObj object is deleted. Then literal2 reference is deleted. Then person2 reference is deleted.
    11. closing bracket of main() method reached means its execution is also complete. All the variables/references created within main method on stack will be deleted in LIFO order.


    NOTE: as you see once the method invokation is complete, only the references are deleted from stack memory. What about the actual objects that were created in the heap?
    This will be taken care by GC(garbage collector).



}
