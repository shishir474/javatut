public final class Final {
    // Final class cannot be inherited.
    // So if you have a usecase where you dont want your class to be inherited, just declare it as final

}

// not allowed -  compilation error ( cannot inherit final class )
public class TestClass extends Final{

}
