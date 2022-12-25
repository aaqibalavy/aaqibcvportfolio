package GameUtils;

public class Assassin extends Enemy {

    public Assassin(){
        setHealth(calcHealth(20, 30));
        setDodge(15);
        setMinDamage(5);
        setMaxDamage(15);

    }
}
