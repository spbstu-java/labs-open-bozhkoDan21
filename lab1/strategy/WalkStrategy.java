package hero.strategy;

/**
 * Конкретная стратегия - перемещение пешком
 */
public class WalkStrategy implements MoveStrategy {
    private static final int MOVE_DISTANCE = 1;

    @Override
    public int move() {
        System.out.println("Герой идет пешком...");
        return MOVE_DISTANCE;
    }
    
    @Override
    public String getDescription() {
        return "Герой перемещается пешком";
    }
}