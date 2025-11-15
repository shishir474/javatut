public class Singleton {
    // the class objective is to only create 1 and 1 object

    // Different ways of creating singleton class
//    1. Eager Initialization
//    2. Lazy Initilization
//    3. Synchronization block
//    4. Double check lock(has memory issue, resolved thru Volatile instance variable)
//    5. Bill Pugh Solution
//    6. Enum Singleton

// This is helpful for usecases where you need only one object to be created. For ex: DB connection
    // you need only one connection object & that needs to be setup only once.
    // you can use the same connection object multiple times throughout your app

// We want to create a class as a Singleton if No matter how many times or from how many places
// it has been called, only one object should be created of this class. That is the purpose of Singleton class.

// V.V.V imp from interview perspectives

//    1. Eager Initialization
//    As the name suggests, eager meaning something in advance.
//    As soon as the class is loaded in memory, the object is created -- which is also one of the disadvantage of this method.
//    even though getInstance() hasn't been called, the object is created & is stored in the memory.
//    To overcome this problem, we have lazy initilization

    // a. private static connObject -- so that the object is not accessible outside this class. Hence would need a getter i.e getInstance()
    // b. private constructor -- instantiation is not allowed outside the class (although instantiation can be done within the class)
    // c. public static getInstance() method -- public getter, marked as static so that method can be called directly using classname

    public class DBConnection{
        // created an object eagerly (in advance)
        // private means -- connObject cannot be accessed outside this class
        // static means -- the object belongs to the class & is a property of the class
        private static DBConnection connObject = new DBConnection();

        // private constructor -- means you cannot create object of DBConnection outside this class
        // (although object can be created inside this class)
        private DBConnection(){
        }

        // public getInstance() method which returns the one single instance of the DBConnection class
        // static -- because it can be called using className itself. No need to create object of DBConnection class
        public static DBConnection getInstance(){
            return connObject;
        }
    }

    public class Main{
        public static void main(String[] args){
            DBConnection connObject =  DBConnection.getInstance();
        }
    }


//    2. Lazy Initialization
    // Disadvantage: if 2 threads comes in parallel, T1 & T2, they both will find connObject == null
    // and thus both will create the objects. To solve this we came up with synchronization block

    public class DBConnection{
        // just declared the object - its not created yet
        // private means -- connObject cannot be accessed outside this class
        // static means -- the object belongs to the class & is a property of the class
        private static DBConnection connObject;

        // private constructor -- means you cannot create object of DBConnection outside this class
        // (although object can be created inside this class)
        private DBConnection(){
        }

        // public getInstance() method which returns the one single instance of the DBConnection class
        // static -- because it can be called using className itself. No need to create object of DBConnection class
        public static DBConnection getInstance(){
            // if object is not created yet -- then only create object
            if (connObject == null){
                connObject = new DBConnection();
            }
            return connObject;
        }
    }

//    3. Synchronized block
public class DBConnection{
    // just declared the object - its not created yet
    // private means -- connObject cannot be accessed outside this class
    // static means -- the object belongs to the class & is a property of the class
    private static DBConnection connObject;

    // private constructor -- means you cannot create object of DBConnection outside this class
    // (although object can be created inside this class)
    private DBConnection(){
    }

    // public getInstance() method which returns the one single instance of the DBConnection class
    // static -- because it can be called using className itself. No need to create object of DBConnection class
    // synchronized -- means if 2 threads T1 and T2 comes at same time, they wont be allowed to execute the method in parallel.
    // Synchronization involves acquiring locks & releasing locks.
    // T1 will first acquire lock, perform its execution (creates the connObject since its null) & returns the object. Then T1s lock is released
    // T2 comes, it acquires its lock, since the connObject already exists, it simply returns the object. Then T2 lock is also released.
    // This resolves our previous problem of concurrency, but acquiring locks & releasing locks is slow.
    // assume, if this method is called at multiple places, each time the thread will first have to acquire the lock, then only it will perform its operation, & then the lock needs to be released.
    // This makes the whole process pretty slow -- THis is where Double check lock comes in

    public synchronized static DBConnection getInstance(){
        // if object is not created yet -- then only create object
        if (connObject == null){
            connObject = new DBConnection();
        }
        return connObject;
    }
}

// 4. Double check lock

    public class DBConnection{
        // just declared the object - its not created yet
        // private means -- connObject cannot be accessed outside this class
        // static means -- the object belongs to the class & is a property of the class
        // volatile means - any read/write happening on this object will always happen from memory & not from the cache.
        private static volatile DBConnection connObject;

        // private constructor -- means you cannot create object of DBConnection outside this class
        // (although object can be created inside this class)
        private DBConnection(){
        }

        // public getInstance() method which returns the one single instance of the DBConnection class
        // static -- because it can be called using className itself. No need to create object of DBConnection class
        // instead of putting synchronized at method level - and making it dramatically slow, we now added the synchronized block inside the method
        // if the object already exists, it will simply return
        // if object doesn't exist & 2 threads T1,T2 comes at same time. T1 will first acquire the lock, connObject is null, so it will create a new object and then returns it. T1 releases the lock
        // T2 comes in, it acquires the lock, object already exists, so it simply returns the object. So it solves the problem of concurrency.
        // There are 2 levels of check & hence the name Double check lock

        // Disadvantage: This method has memory issues. In the CPU, there are multiple cores, each core has its own cache (known as L1 cache) & then they have access to common memory
//        Core1       Core2
//          |           |
//        Cache1 ------ Cache2
//            \       /
//             Memory
//        Assume Thread T1 comes & it goes to Core1, it acquires the lock, creates the object, stores its reference in the cache1(ref info not yet synced to main memory) & syncup between cache1 & cache2 has also not happened yet.
//        For performance purpose, all the cores temporarily stores the data in their cache and periodically they sync the data to the memory.
//        Thread T2 goes to core2. Since the syncup has not happened yet, it has no knowledge of the connObject created by T1. Because of this it creates another object.

        // Another issue with this method is reordering.

        // Both these issues are resolved using the volatile keyword. If you declare any variable volatile, any read or write operation happening on it, will always take place in memory & not in cache.
        // So when T1 creates an object, its writes/stores its ref in the memory instead of the cache & thus it resolves the syncup issue which happened earlier.
        // T2 will reference the memory & find the connObject.

        // Recommended & standard practise is -- always use volatile, whenever you are using Double locking mechanism.
        // This will always enable all the operations on that variable to be performed from memory ( memory is shared across all cores, its not like cache. Each core has its own independent cache for performance reasons known as L1 cache)

        // Since it's not leveraging cache(bcoz of volatile), also it utilizes synchronized block(requires acquiring & releasing locks) which makes process even more slower. There are better solutions (Bill Pugh & Enums)

        public static DBConnection getInstance(){
            // if object is not created yet -- then only create object
            if (connObject == null){                    // check 1
                synchronized (DBConnection.class){
                    if(connObject == null){             // check 2
                        connObject = new DBConnection();
                    }
                }
            }
            return connObject;
        }
    }



//    5. Bill Pugh Solution  -- used in industries (popular solution)

    // no volatile or synchronization logic is being used
    // This solution is built on top of Eager initilization. The problem with eager approach was even though the method hasn't been called, the object was still created beforehand (as soon as the class loaded)
    // This solution using private static nested class - He just shifted the initilization step inside the nested class.
    // Benefit is the nested class do not get loaded in the memory during the class loading process / when the application starts.
    // Nested class is only loaded when it's being referenced. This property of Nested class helps us to overcome the problem of eager initilization

    public class DBConnection{
        // private constructor -- means you cannot create object of DBConnection outside this class
        // (although object can be created inside this class)
        private DBConnection(){
        }

        // Nested Class - only loaded, when its being referenced/used.
        // private -- Helper here is a nested class, making it private to ensure its not accessible from the outside.
        // static --
        private static class DBConnectionHelper{
            // static final -- constant object
            // static -- means belongs to class DBConnectionHelper
            // final -- means once a value has been assigned, it cannot be changed.
            private static final DBConnection INSTANCE_OBJECT = new DBConnection();
        }

        // public getInstance() method which returns the one single instance of the DBConnection class
        // static -- because it can be called using className itself. No need to create object of DBConnection class
        public static DBConnection getInstance(){
            // return the constant object of DBConnectionHelper nested class
            return DBConnectionHelper.INSTANCE_OBJECT;
        }
    }


//    6. Enum Singleton

// Enums in Java are considered singletons because the JVM guarantees exactly one instance of each enum constant for the entire application lifecycle.
// 1. The JVM ensures that INSTANCE is created only once, no matter how many times you access it.
// 2. JVM guarantees thread-safe instance creation
    //  Unlike manual singleton implementations involving:
    //    private static instance
    //    synchronized methods
    //    double-checked locking
    //  an enum singleton is inherently thread-safe.
//    The JVM handles initialization of enum objects in a thread-safe manner during class loading.
    public enum DatabaseConnection{
        INSTANCE;
    }

}
