public class StudentPOJOSample {
    // POJO stands for "Plain Old Java Object"
    // contains variables and its getters & setter methods
    // class should be public
    // Contains private fields with public getters/setters (not mandatory, but recommended & standard practise)
    // It should not extend any class or implement any interface
    // No annotations should be used like @Table, @Entity, @Id etc

    String Name;
    private int rollNumber;
    protected String address;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    @Override
    public String toString(){
        return "Student{" +
                "id = " + rollNumber +
                ", name = " + name +
                ", address = " + address +
                '}';

    }
}
