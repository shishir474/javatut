import java.lang.ref.WeakReference;

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
    10. closing bracket of memoryManagementTest() reached. Function execution complete. All the variables/references created as part of this invocation are deleted in LIFO order.
        First reference for stringObj object is deleted. Then literal2 reference is deleted. Then person2 reference is deleted.
    11. closing bracket of main() method reached means its execution is also complete. All the variables/references created within main method on stack will be deleted in LIFO order.


    NOTE: as you see once the method invocation is complete, only the references are deleted from stack memory. What about the actual objects that were created in the heap?
    This will be taken care by GC(garbage collector).

    GC runs periodically and deletes any object from heap that is no longer referenced. GC invocation is completely handled by JVM.

    System.gc() --> using this command we can trigger GC. But its not guaranteed, since JVM controls when to trigger GC. If JVM sees the need, it will trigger GC. That is why this process is known as automatic memory management.
    We dont have to worry about freeing up the memory. JVM automatically triggers the GC at periodic intervals which scans the heap and identifies objects that are not referenced and deletes them.

    If heap memory is filling up fast, JVM will run the GC more frequently. If there's sufficient space, the freq of GC invocation will be reduced by JVM.


    Reference Types
            Person personObj = new Person();
            created a Person object -- space will be allocated on heap and its reference is stored in personObj variable on stack.

    Strong Reference
            Assume GC is invoked, it will scap heap and try to determine objects that are no longer referenced and will delete those.
    person object is being referenced here by personObj. This is strong reference.
    GC will not delete this object.

    Weak Reference:
    we can create a weak reference using WeakReference class.
    WeakReference<Person> weakPersonObj = new WeakReference<Person>(new Person());

    If weak reference is created, the object lasts only until GC is not invoked. As soon as the GC is invoked, it will free up the space(deletes the object that has weak reference)
    So its scope is very limited, and once the object is deleted, you will get null if you try to access it.

    Soft Reference:
        Its a type of weak reference in which the object is only deleted only if its very urgent.
        Unless there is an emergency and space issue, GC will spare this object and will not delete it.
        But in weak reference, GC doesnt care. It simply deletes the object if it is weakly referenced.

    Access Safety wise
        Strong Reference  >    Soft Reference   > WeakReference

        Strong reference - (GC only deletes unreferenced object from heap)
        Soft reference - (GC deletes weak reference objects only if its very necessary from heap)
        Weak reference - (GC deletes weak reference objects from heap)


    How references can be changed and its impact?
        Person personObj1 = new Person();
        Person personObj2 = new Person();

        created 2 person objects in heap and their reference is stored in personObj1 and personObj2
        Now if I set personObj1 = null
        person object still exists in memory, just that its reference is deleted. If GC is invoked now, it will find that this object is not referenced by anyone and it will delete that object.

        If I write personObj1 = personObj2;
        I just changed the reference of personObj1 and now it points to personObj2.
        personObj1 object is no longer being referenced by anyone. GC will delete that object from the heap memory.

        Hence, we should be cautious when we change the references or delete the reference (by setting as null). It makes the object unreferenced and GC will delete that object.


    Heap Memory
            is divided into 2 parts Young Generation(YG) & Old Generation(OG) and non heap space (Metaspace)
            YG is futher divided into 3 parts Eden, S0 & S1.
            S0 & S1 are also known as survivors space.
            Whenever we create any new object, space is allocated inside Eden.

            Assume we created 5 objects o1, o2, o3, o4, o5. All of them will be created inside Eden.

            Eden = {o1, o2, o3, o4, o5}
            s0 = {}
            s1 = {}

            1st GC invocation happens:
                GC uses Mark & Sweep Algorithm to delete objects. In phase one(Mark phase) GC marks all the objects that are no longer referenced.
                In the second phase(Sweep phase) -  it will first delete the objects that were marked during the Mark phase and then sweeps the survivor objects alternatively into one of the survivor stacks S0 or S1 and it also increases their age.
                This is known as minor GC, becuause it happens very frequently and is very fast.

            Assume o2 and o5 are no longer referenced by anyone. GC will mark them and will delete them freeing up space from heap memory.
            it will push the survivor objects o1, o3, & o4 into S1 and their age will become 1 (incremented by 1 during each clean up operation)

            Eden = {}
            s0 = {o1(age=1), o3(age=1), o4(age=1)}
            s1 = {}

            New objects created - lets say o6 and o7
            Space for o6 and o7 will be allocated in Eden.

            Eden = {o6, o7}
            s0 = {o1(age=1), o3(age=1), o4(age=1)}
            s1 = {}

            2nd GC invocation happens:
            GC marks the objects that are no longer referenced and deletes them freeing up memory.
            Assume o4 and o7 are unreferenced - they will be deleted and the survivor objects will be placed in the survior space with their age incremeted by 1.

            Eden = {}
            s0 = {}
            s1 = {o6(age=1), o1(age=2), o3(age=2)}

            NOTE: Certain threshold is defined. Once the object's age crosses that threshold, objects are promoted to OG(old generation)
            Lets say that threshold age value is 3 in our case.

            Created new objects o8, o9 -- space will be allocated in Eden
            Eden = {o8, o9}
            s0 = {}
            s1 = {o6(age=1), o1(age=2), o3(age=2)}

            3rd GC invocation happens
                Assume o3 and o8 are unreferenced - so they will be deleted and the rest of them age increments by 1
                Eden = {o9}
                s0 = {o9(age=1), o6(age=1), o1(age=2)}
                s1 = {}

    Difference between YG and OG
        1. In YG, garbage collection process is known as minor GC becaue it happens very frequently and is much faster
            as compared to the garbage collection in OG which is known as major GC.
        2. Freq of minor GC >> Freq of major GC
        3. time required for minor GC(fast) << time required for major GC(slow)

        Objects in OG are kinda heavy since they are alive from too long and they might hold too many references. Deleting those many references is time taking.

        The process of garbage collection in YG and OG is same. Its just that in OG, this takes bit more time since there are too many references that needs to be deleted.

    Metaspace (non heap space)
        Stores class variables (declared using static keyword)
        Stores constants (declared using static final keyword)
        Stores class metadata (info about class)

    Whenever JVM need to refer a class, it will load that class in Metaspace.

    KEY POINTS:
    1. There exists only one single copy of heap memory which is shared by all the threads, unlike stack where each thread gets its own stack space.
    2. Heap memory contains string constant pool inside which your string literal objects are created. Its a separate space in heap memory allocated for storing string literals.
    3. When Heap memory goes full, it throws java.lang.OutOfMemoryError
    4. Heap memory is futher divided into
            a. YG(young generation) -- minor GC happens here
                1. Eden
                2. Survivors (S0 and S1)
            b. OG(old/tenured generation) -- major GC happens here
            c. Permanent Generation (PermGen) -- valid in old java version before Java7
                From Java8 onwards, Metaspace concept was introduced.

    PermGen vs Metaspace
        PermGen was part of heap and it was not expandable. So as soon as it got filled up, it used to throw java.lang.OutOfMemoryError
        Metaspace is non-heap. Its separate from heap and thus is expandable as well.
    They both store similar kind of data (class variables, constants, class metadata)

    GC Algorithms:
        1. Mark & Sweep
        2. Mark & Sweep with Compaction
            This is pretty much the same as traditional mark and sweep algo. It follows an additional compaction step which brings together the survivor objects together in the memory which is bit more efficient.

    Different Versions of GC:
        1. Serial GC
        2. Parallel GC -- default version of GC in Java8
        3. Concurrent Mark & Sweep (CMS)
        4. G1 Garbage collector

    Garbage collection is an expensive process. When GC work starts, all the application threads gets paused (the world stops when the GC runs)
    So we would want to make sure that this pauses are as minimal as possible so that the performance/throughput of our app is not impacted.

    In serial GC - only 1 thread of GC works which makes the entire GC process slow and time-intensive. GC will take lot of time and your apps performance will be impacted alot.

    In Parallel GC - There are multiple threads of GC that are working in parallel, so this is bit more efficient that serial GC. But again, there will be some pauses - so there is a scope of improvement
    Depending on the no of cores (whether its a 2 core cpu or 4 core cpu) - those many threads will be utilised for GC

    CMS - From java17 onwards, concurrent mark and sweep technique was introduced. GC threads are also working in parallel with the application threads and the app threads are not even stopped(but this behaviour is not guranteed)
    JVM tries its best to not stop the application thread and perform the clean up process in parallel. But its not 100% guaranteed.
    Also no memory compaction happens (disadvantage).

    G1 Garbage collector - GC threads work in parallel with the application threads(JVM tries to give us better assurance here as compared to CMS)
        This also supports memory compaction (all the survivor objects are brought together and freed up space is put collectively -  which can be utilised later)

    With these advancements - application thread's pause time is drastically reduced, which essentially means better throughput & performance.
    With minimal pauses, application threads which earlier used to lets say process 1000 req/min, now they can process 1500 req/min
    Latency is reduced, throughtput of the application is increased & consequently the performance is also improved



}

