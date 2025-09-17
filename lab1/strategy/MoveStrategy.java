package lab1.strategy;

/**
 * Интерфейс стратегии перемещения героя
 * Паттерн "Стратегия"
 */
public interface MoveStrategy {
    int move();
    String getDescription();
}