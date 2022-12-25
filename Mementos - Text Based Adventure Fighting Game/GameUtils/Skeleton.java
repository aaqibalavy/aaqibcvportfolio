package GameUtils;

public class Skeleton extends Enemy {

    public Skeleton(){
        setHealth(calcHealth(30, 45));
        setDodge(10);
        setMinDamage(10);
        setMaxDamage(15);
    }
}
