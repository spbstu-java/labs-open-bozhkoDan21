package lab4.service;

import java.util.List;

public interface MathOperations {
    double getAverage(List<Integer> numbers);
    List<Integer> getUniqueElementsSquared(List<Integer> numbers);
    int sumOfEvenNumbers(int[] numbers);
}