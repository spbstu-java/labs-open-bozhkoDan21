package com.example.reflectionapp.processor;

import com.example.reflectionapp.annotation.InvokeMultiple;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

public class MethodInvoker {
    
    // Карта значений по умолчанию для различных типов параметров
    private static final Map<Class<?>, Object> DEFAULT_VALUES = new HashMap<>();
    
    static {
        DEFAULT_VALUES.put(String.class, "DEFAULT_USER");
        DEFAULT_VALUES.put(int.class, 25);
        DEFAULT_VALUES.put(Integer.class, 25);
        DEFAULT_VALUES.put(double.class, 25.0);
        DEFAULT_VALUES.put(Double.class, 25.0);
    }

    /**
     * Основной метод для вызова всех аннотированных методов
     * @param target объект, методы которого нужно вызвать
     */
    public void invokeAnnotatedMethods(Object target) {
        if (target == null) {
            throw new IllegalArgumentException("Target object cannot be null");
        }

        Class<?> clazz = target.getClass();
        
        for (Method method : clazz.getDeclaredMethods()) {
            InvokeMultiple annotation = method.getAnnotation(InvokeMultiple.class);
            
            if (annotation != null) {
                processAnnotatedMethod(target, method, annotation);
            }
        }
    }

    /**
     * Обрабатывает отдельный аннотированный метод
     */
    private void processAnnotatedMethod(Object target, Method method, InvokeMultiple annotation) {
        System.out.println("Processing method: " + method.getName());
        
        boolean accessibilityChanged = false;
        
        try {
            // Если метод недоступен (private/protected), временно делаем доступным
            if (!method.isAccessible()) {
                method.setAccessible(true);
                accessibilityChanged = true;
            }

            Object[] parameters = prepareMethodParameters(method);
            int invocationCount = annotation.times();

            for (int i = 0; i < invocationCount; i++) {
                invokeMethod(target, method, parameters);
            }
            
        } catch (Exception e) {
            System.err.println("Error invoking method " + method.getName() + ": " + e.getMessage());
        } finally {
            if (accessibilityChanged) {
                method.setAccessible(false);
            }
        }
    }

    /**
     * Подготавливает параметры для вызова метода
     * @return массив значений параметров или null если параметров нет
     */
    private Object[] prepareMethodParameters(Method method) {
        Class<?>[] parameterTypes = method.getParameterTypes();
        
        if (parameterTypes.length == 0) {
            return null;
        }

        Object[] parameters = new Object[parameterTypes.length];
        
        for (int i = 0; i < parameterTypes.length; i++) {
            parameters[i] = DEFAULT_VALUES.get(parameterTypes[i]);
            if (parameters[i] == null) {
                throw new UnsupportedOperationException(
                    "Unsupported parameter type: " + parameterTypes[i].getName()
                );
            }
        }
        
        return parameters;
    }

    /**
     * Вызывает метод с подготовленными параметрами
     */
    private void invokeMethod(Object target, Method method, Object[] parameters) {
        try {
            method.invoke(target, parameters);
        } catch (IllegalAccessException | InvocationTargetException e) {
            System.err.println("Failed to invoke method " + method.getName() + ": " + e.getCause().getMessage());
        }
    }
}