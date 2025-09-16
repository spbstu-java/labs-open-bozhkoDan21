package lab4;

import lab4.exception.EmptyCollectionException;
import lab4.factory.ServiceFactory;
import lab4.service.*;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private static final MathOperations mathService = ServiceFactory.createMathOperations();
    private static final StringOperations stringService = ServiceFactory.createStringOperations();
    private static final CollectionOperations collectionService = ServiceFactory.createCollectionOperations();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("=== Программа работы с Stream API ===");

        boolean running = true;
        while (running) {
            printMenu();
            int choice = getIntInput("Выберите операцию: ");

            switch (choice) {
                case 1 -> demonstrateMathOperations();
                case 2 -> demonstrateStringOperations();
                case 3 -> demonstrateCollectionOperations();
                case 0 -> {
                    running = false;
                    System.out.println("До свидания!");
                }
                default -> System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n=== МЕНЮ ===");
        System.out.println("1. Математические операции");
        System.out.println("2. Операции со строками");
        System.out.println("3. Операции с коллекциями");
        System.out.println("0. Выход");
    }

    private static void demonstrateMathOperations() {
        System.out.println("\n=== Математические операции ===");

        // Среднее значение
        List<Integer> numbers = getIntegerList("Введите числа через пробел (для среднего значения): ");
        System.out.println("Среднее значение: " + mathService.getAverage(numbers));

        // Квадраты уникальных элементов
        List<Integer> numbersForUnique = getIntegerList("Введите числа через пробел (для квадратов уникальных): ");
        System.out.println("Квадраты уникальных элементов: " + mathService.getUniqueElementsSquared(numbersForUnique));

        // Сумма четных чисел
        int[] evenNumbersArray = getIntArray("Введите числа через пробел (для суммы четных): ");
        System.out.println("Сумма четных чисел: " + mathService.sumOfEvenNumbers(evenNumbersArray));
    }

    private static void demonstrateStringOperations() {
        System.out.println("\n=== Операции со строками ===");

        // Преобразование с префиксом
        List<String> stringsForPrefix = getStringList("Введите строки через запятую (для префикса): ");
        System.out.println("Строки с префиксом: " + stringService.convertToUpperCaseWithPrefix(stringsForPrefix));

        // Фильтрация по первой букве
        List<String> stringsForFilter = getStringList("Введите строки через запятую (для фильтрации): ");
        char letter = getCharInput("Введите букву для фильтрации: ");
        System.out.println("Строки начинающиеся с '" + letter + "': " +
                stringService.filterAndSortByFirstLetter(stringsForFilter, letter));

        // Преобразование в Map
        List<String> stringsForMap = getStringList("Введите строки через запятую (для преобразования в Map): ");
        System.out.println("Map по первому символу: " + stringService.getMapByFirstCharacter(stringsForMap));
    }

    private static void demonstrateCollectionOperations() {
        System.out.println("\n=== Операции с коллекциями ===");

        // Получение последнего элемента
        List<String> collection = getStringList("Введите элементы коллекции через запятую: ");

        try {
            String lastElement = collectionService.getLastElement(collection);
            System.out.println("Последний элемент: " + lastElement);
        } catch (EmptyCollectionException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    // Вспомогательные методы с проверкой входных данных

    private static int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите целое число!");
            }
        }
    }

    private static char getCharInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (input.length() == 1) {
                return input.charAt(0);
            }
            System.out.println("Ошибка: введите один символ!");
        }
    }

    private static List<Integer> getIntegerList(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String input = scanner.nextLine().trim();
                if (input.isEmpty()) {
                    return Collections.emptyList();
                }

                return Arrays.stream(input.split("\\s+"))
                        .map(String::trim)
                        .filter(s -> !s.isEmpty())
                        .map(Integer::parseInt)
                        .collect(Collectors.toList());
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите числа через пробел! Например: 1 2 3 4");
            }
        }
    }

    private static int[] getIntArray(String prompt) {
        List<Integer> list = getIntegerList(prompt);
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    private static List<String> getStringList(String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine().trim();
        if (input.isEmpty()) {
            return Collections.emptyList();
        }

        return Arrays.stream(input.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());
    }
}