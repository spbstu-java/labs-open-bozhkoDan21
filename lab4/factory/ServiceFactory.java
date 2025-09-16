package lab4.factory;

import lab4.service.*;

public class ServiceFactory {

    public static MathOperations createMathOperations() {
        return new MathOperationsImpl();
    }

    public static StringOperations createStringOperations() {
        return new StringOperationsImpl();
    }

    public static CollectionOperations createCollectionOperations() {
        return new CollectionOperationsImpl();
    }
}