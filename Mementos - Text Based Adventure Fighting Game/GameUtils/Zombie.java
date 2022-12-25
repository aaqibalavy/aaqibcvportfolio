package GameUtils;

public class Zombie extends Enemy {

    public Zombie(){
        setHealth(calcHealth(25, 35));
        setDodge(12);
        setMinDamage(5);
        setMaxDamage(10);
    }
}
