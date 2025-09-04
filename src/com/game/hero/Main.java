package com.game.hero;

import com.game.hero.strategy.FlyStrategy;
import com.game.hero.strategy.RideHorseStrategy;
import com.game.hero.strategy.WalkStrategy;

public class Main {
    
    public static void main(String[] args) {
        Hero hero = new Hero();
        
        // Перемещение пешком
        hero.move();
        
        // Меняем стратегию на перемещение на лошади
        hero.setMoveStrategy(new RideHorseStrategy());
        hero.move();
        hero.move();
        
        // Меняем стратегию на полет
        hero.setMoveStrategy(new FlyStrategy());
        hero.move();
        
        // Возвращаемся к ходьбе
        hero.setMoveStrategy(new WalkStrategy());
        hero.move();
        
        System.out.println("Финальная позиция героя: " + hero.getPosition());
    }
}