package hero.strategy;

/**
 * Конкретная стратегия - полет
 */
public class FlyStrategy implements MoveStrategy {
    
    private static final int MOVE_DISTANCE = 3;
    
    @Override
    public int move() {
        System.out.println("Герой летит...");
        return MOVE_DISTANCE;
    }
    
    @Override
    public String getDescription() {
        return "Герой летит";
    }
}