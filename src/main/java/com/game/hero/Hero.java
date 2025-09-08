package main.java.com.game.hero;

import main.java.com.game.hero.strategy.MoveStrategy;
import main.java.com.game.hero.strategy.WalkStrategy;

/**
 * Класс героя, использующий стратегию перемещения
 */
public class Hero {
    
    private MoveStrategy moveStrategy;
    private int position;
    
    /**
     * Конструктор героя с стратегией перемещения по умолчанию
     */
    public Hero() {
        this.moveStrategy = new WalkStrategy();
        this.position = 0;
        System.out.println("Создан новый герой. " + moveStrategy.getDescription());
    }
    
    /**
     * Установка новой стратегии перемещения
     * @param strategy новая стратегия
     */
    public void setMoveStrategy(MoveStrategy strategy) {
        this.moveStrategy = strategy;
        System.out.println("Смена способа перемещения: " + strategy.getDescription());
    }
    
    /**
     * Выполнение перемещения героя
     */
    public void move() {
        int distance = moveStrategy.move();
        position += distance;
        System.out.println("Герой переместился на " + distance + ". Текущая позиция: " + position + "\n");
    }
    
    /**
     * Получение текущей позиции героя
     * @return позиция героя
     */
    public int getPosition() {
        return position;
    }
    
    /**
     * Получение текущей стратегии перемещения
     * @return описание стратегии
     */
    public String getCurrentStrategyDescription() {
        return moveStrategy.getDescription();
    }
}