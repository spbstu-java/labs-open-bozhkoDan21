package hero.strategy;

/**
 * Конкретная стратегия - перемещение на лошади
 */
public class RideHorseStrategy implements MoveStrategy {
    
    private static final int MOVE_DISTANCE = 2;
    
    @Override
    public int move() {
        System.out.println("Герой скачет на лошади...");
        return MOVE_DISTANCE;
    }
    
    @Override
    public String getDescription() {
        return "Герой перемещается на лошади";
    }
}