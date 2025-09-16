package lab2;

import lab2.domain.SampleService;
import lab2.processor.MethodInvoker;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== Method Invocation Program ===");
        System.out.println("Available methods in SampleService:");
        System.out.println("1. Public methods");
        System.out.println("2. Private methods"); 
        System.out.println("3. Protected methods");
        System.out.println("4. All methods");
        System.out.print("Choose option (1-4): ");
        
        int choice = scanner.nextInt();
        
        SampleService service = new SampleService();
        MethodInvoker invoker = new MethodInvoker();
        
        System.out.println("Starting method invocation...");
        
        switch (choice) {
            case 1:
                invoker.invokePublicMethods(service);
                break;
            case 2:
                invoker.invokePrivateMethods(service);
                break;
            case 3:
                invoker.invokeProtectedMethods(service);
                break;
            case 4:
                invoker.invokeAllMethods(service);
                break;
            default:
                System.out.println("Invalid choice! Invoking all methods...");
                invoker.invokeAllMethods(service);
        }
        
        scanner.close();
    }
}