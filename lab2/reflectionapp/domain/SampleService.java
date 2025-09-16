package reflectionapp.domain;

import reflectionapp.annotation.InvokeMultiple;

public class SampleService {
    
    @InvokeMultiple(times = 2)
    public void publicMethod() {
        System.out.println("Public method without parameters invoked");
    }

    @InvokeMultiple(times = 2)
    public void publicMethodWithParams(String name, int age) {
        System.out.println("Public method with parameters - Name: " + name + ", Age: " + age);
    }

    @InvokeMultiple(times = 3)
    private void privateMethod() {
        System.out.println("Private method without parameters invoked");
    }

    @InvokeMultiple(times = 3)
    private void privateMethodWithParams(String name, int age) {
        System.out.println("Private method with parameters - Name: " + name + ", Age: " + age);
    }

    @InvokeMultiple(times = 4)
    protected void protectedMethod() {
        System.out.println("Protected method without parameters invoked");
    }

    @InvokeMultiple(times = 4)
    protected void protectedMethodWithParams(String name, int age) {
        System.out.println("Protected method with parameters - Name: " + name + ", Age: " + age);
    }
}