package com.game.hero;

import com.game.hero.strategy.FlyStrategy;
import com.game.hero.strategy.RideHorseStrategy;
import com.game.hero.strategy.WalkStrategy;
import com.game.hero.strategy.MoveStrategy;
import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Hero hero = new Hero();
        
        System.out.println("=== Система перемещения героя ===");
        
        boolean running = true;
        while (running) {
            System.out.println("\nВыберите действие:");
            System.out.println("1 - Идти пешком");
            System.out.println("2 - Ехать на лошади");
            System.out.println("3 - Лететь");
            System.out.println("4 - Переместиться один раз");
            System.out.println("5 - Показать текущую позицию");
            System.out.println("6 - Показать текущую стратегию");
            System.out.println("0 - Выйти");
            System.out.print("Ваш выбор: ");
            
            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Пожалуйста, введите число!");
                continue;
            }
            
            switch (choice) {
                case 1:
                    hero.setMoveStrategy(new WalkStrategy());
                    System.out.println("Установлена стратегия: Пешком");
                    break;
                    
                case 2:
                    hero.setMoveStrategy(new RideHorseStrategy());
                    System.out.println("Установлена стратегия: На лошади");
                    break;
                    
                case 3:
                    hero.setMoveStrategy(new FlyStrategy());
                    System.out.println("Установлена стратегия: Полёт");
                    break;
                    
                case 4:
                    hero.move();
                    break;
                    
                case 5:
                    System.out.println("Текущая позиция героя: " + hero.getPosition());
                    break;
                    
                case 6:
                    System.out.println("Текущая стратегия: " + hero.getCurrentStrategyDescription());
                    break;
                    
                case 0:
                    running = false;
                    System.out.println("Выход из программы. Финальная позиция: " + hero.getPosition());
                    break;
                    
                default:
                    System.out.println("Неверный выбор! Попробуйте снова.");
            }
        }
        
        scanner.close();
    }
}