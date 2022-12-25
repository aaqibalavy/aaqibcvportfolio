package GameUtils;

public class Combat {
    private GameState gameState;

    public Combat(GameState game){
        this.gameState = game;
    }

    // conducts player basic attack, altering enemy health unless enemy dodges
    public void playerBasicAttack(Enemy enemy, Player player){
        int damage = (int)(player.calcDamage());
        boolean enemyDodged = enemy.enemyDodge();
        if(enemyDodged == false){ // enemy fails to dodge
            enemy.setHealth((enemy.getHealth()-damage));
            System.out.println("You dealt " + damage + " damage to the " + gameState.getCurrentEnemyType() + "!");
            // remaining enemy health statements
            if(enemy.getHealth()<=0){
                System.out.println("The " + gameState.getCurrentEnemyType() + " enemy has 0 health left.");
            }
            else{
                System.out.println("The " + gameState.getCurrentEnemyType() + " has " + enemy.getHealth() + " health left.");
            }
        }
        else{ // enemy dodges
            System.out.println("The " + gameState.getCurrentEnemyType() + " dodged your attack!");
        }

    }

    // conducts enemy basic attack, altering player health unless player dodges
    public void enemyBasicAttack(Enemy enemy, Player player){
        int damage = (enemy.calcDamage());
        boolean playerDodged = player.playerDodge();
        if(playerDodged == false){
            player.setHealth((player.getHealth()-damage));
            System.out.println("The " + gameState.getCurrentEnemyType() + " attacks you and you sustain " + damage + " damage!");
            System.out.println("You now have " + player.getHealth() + " health left.");
        }
        else{
            System.out.println("You dodged the " + gameState.getCurrentEnemyType() + "'s attack!");
        }
    }

    // enemy's attack upon player failing to run away (unique print statements)
    public void enemyFirstAttack(Enemy enemy, Player player){
        int damage = (enemy.calcDamage());
        boolean playerDodged = player.playerDodge();
        if(playerDodged == false){
            player.setHealth((player.getHealth()-damage));
            System.out.println("The " + gameState.getCurrentEnemyType() + " attacks you first and you sustain " + damage + " damage!");
            System.out.println("You now have " + player.getHealth() + " health left.");
        }
        else{
            System.out.println("You dodged the " + gameState.getCurrentEnemyType() + "'s attack!");
        }
    }

    // conducts player heal
    public void playerHeal(Enemy enemy, Player player){
        if(player.getPlayerInventory().getHealthPotion().getQuantity() > 0) {
            // checks player lost health is less than the amount healed by health potions
            if (player.getHealth() <= player.getVitality() - player.getPlayerInventory().getHealthPotion().getHealAmount()) {
                player.heal(player.getPlayerInventory().getHealthPotion().getHealAmount());
                player.getPlayerInventory().getHealthPotion().decrementQuantity();
                System.out.println("You healed yourself for 20 HP.");
                System.out.println("You now have " + player.getHealth() + " health.");
            }
            // checks player lost health is within the amount healed by health potions
            else if ((player.getHealth() < player.getVitality()) & (player.getHealth() > player.getVitality() - player.getPlayerInventory().getHealthPotion().getHealAmount())) {
                player.getPlayerInventory().getHealthPotion().decrementQuantity();
                System.out.println("You healed yourself for " + (player.getVitality() - player.getHealth()) + " HP. You now have max health.");
                player.heal(player.getVitality() - player.getHealth());
            }
            // player max health messages
            else {
                System.out.println("You already have max health.");
                System.out.println("You decide to attack instead.");
                playerBasicAttack(enemy, player); // player attacks automatically upon not being able to heal
            }
            //System.out.println("You have " + player.getPlayerInventory().getHealthPotion().getQuantity() + " health potion" + Game.isPlural(player.getPlayerInventory().getHealthPotion().getQuantity()) + " left.");
        }
        else{ // run out of health potions message
            System.out.println("You have run out of health potions, defeat enemies to acquire more.");
            System.out.println("You decide to attack instead.");
            playerBasicAttack(enemy, player); // player attacks automatically upon not being able to heal
        }
    }

}
