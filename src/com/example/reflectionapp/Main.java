package com.example.reflectionapp;

import com.example.reflectionapp.domain.SampleService;
import com.example.reflectionapp.processor.MethodInvoker;

public class Main {
    public static void main(String[] args) {
        SampleService service = new SampleService();
        MethodInvoker invoker = new MethodInvoker();
        
        invoker.invokeAnnotatedMethods(service);
    }
}