package Generics;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(){
//            Vehicle
//          Bus    Car

        List<Vehicle> vehicleList = new ArrayList<>();
        vehicleList.add(new Bus());
        vehicleList.add(new Vehicle());

        List<Bus> busList = new ArrayList<>();
        busList.add(new Bus());

        vehicleList = busList; // invalid
        busList = vehicleList; // invalid

//        List<Bus> is NOT considered a subtype of List<Vehicle>, even though Bus is a subtype of Vehicle

        Vehicle vehicleObj = new Vehicle();
        Vehicle carObj = new Car();

        vehicleObj = carObj; // valid

//        But with generics this does not work
//Lets understand why this restriction exists - it's to prevent runtime type errors.
//if java allowed the assignment
List<Vehicle> vehicleList = new ArrayList<Car>(); // suppose this was allowed
//then this code would be valid at the compile time but break at runtime
vehicleList.add(new Vehicle());
//Now the internal list(actually ArrayList<Car> contains a Vehicle that is not a Car). This violates type safety
//Java's type system prevents this by disallowing List<Car> to be treated as subtype of List<Vehicle>
//Thats why generics are invariant.

// How to handle this safely?
//        If you want to read from a list of any subtype of Vehicle, you can use wildcards
List<? extends Vehicle> vehicles = new ArrayList<Car>();
//    Now you can read vehicles safely

Vehicle v = vehicles.get(0);
//But you cannot add new elements (because the compiler can’t guarantee the subtype).


//        Think of this diagram:
//
//        Object
//        ↑
//        Vehicle
//        ↑
//        Car
//        ↑
//        SportsCar
//

//    <? extends Vehicle> → any of {Vehicle, Car, SportsCar}
//
//   <? super Car> → any of {Car, Vehicle, Object}

//        class hierarcy
        class Object {}
        class Vehicle {}
        class Car extends Vehicle {}
        class SportsCar extends Car {}

//        example 1: reading from a producer
        public void printVehicles(List<? extends Vehicle> list){
            for(Vehicle v: list){
                System.out.println(v);;
            }
        }

        List<Vehicle> vehicles = new ArrayList<>();
        List<Car> cars = new ArrayList<>();
        List<SportsCar> sportsCar = new ArrayList<>();

//        this is totally valid and will work fine
        printVehicles(vehicles);
        printVehicles(cars);
        printVehicles(sportsCar);

//        but you cannot add anuyting inside this method
        list.add(new Vehicle());   // ❌ Compile error
        list.add(new Car());       // ❌ Compile error

//        Why? Because list could be List<SportsCar> internally, and adding a Car would break type safety.

//        👉 So:
//        <? extends Vehicle> is used when you only need to read from the list — i.e., it’s a producer.

//🧱 EXAMPLE 2 — Writing to a Consumer (? super Car)

//Now suppose you have a method that adds cars to some list — could be a list of cars, vehicles, or generic objects.

        public static void addCars(List<? super Car> list) {
            list.add(new Car());
            list.add(new SportsCar()); // ✅ subclass also allowed
        }

        List<Car> cars = new ArrayList<>();
        List<Vehicle> vehicles = new ArrayList<>();
        List<Object> objects = new ArrayList<>();

        addCars(cars);
        addCars(vehicles);
        addCars(objects);

//        ⚠️ But when you read from it:
        Object obj = list.get(0);  // ✅ works
        Car car = list.get(0);     // ❌ not allowed

//        Why? Because we only know it’s a supertype of Car — it might be a List<Object>, so we can’t safely assume it holds a Car.

//        👉 So:
//        <? super Car> is used when you only need to write to the list — i.e., it’s a consumer.
    }


}
