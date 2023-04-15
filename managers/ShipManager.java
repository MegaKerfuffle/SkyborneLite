package managers;
import game.GameStage;
import game.SkyborneLite;

/** 
 * Manages aspects of the player's airship. Functionality is limited but could
 * be expanded upon in the future.
 */
public class ShipManager extends GameManager {
    public static ShipManager instance;
    int health = 100;
    int attack = 30;

    // Temporary buffs. Reapplying them increases the duration.
    private int healthBuffDuration = 0;
    int healthBuff = 0;
    private int attackBuffDuration = 0;
    int attackBuff = 0;

    @Override
    protected void onGameStart() {
        instance = this;
    }

    @Override
    protected void onCustomEvent(ManagerEvent event) {
        if (!(event.payload instanceof String)) return;
        var custom = (String)event.payload;
        switch (custom) {
            case "next_turn":
                checkBuffs();        
                break;
        }
    }

    @Override
    protected void onGameTick() {
        if (SkyborneLite.getStage() == GameStage.Combat) return; 
        if (health <= 0)
            SkyborneLite.endGame("Due to your negligence as captain, your airship has taken severe damage and can no longer continue the pursuit.");
    }

    /** Checks the player's health and attack buffs, and decrements their duration. */
    private void checkBuffs() {
        if (healthBuffDuration > 0)
            healthBuffDuration--;
        else
            healthBuff = 0;

        if (attackBuffDuration > 0)
            attackBuffDuration--;
        else
            attackBuff = 0;
    }
    
    /** Getter for health */
    public int getHealth() {
        return health + healthBuff;
    }

    /** Apply some amount of damage to the player's airship. */
    public void doDamage(int damage) {
        int realDamage = damage - healthBuff;
        if ((healthBuff -= damage) <= 0)
            healthBuff = 0;
        health -= realDamage;
    }

    /** Getter for the player's attack stat. */
    public int getAttack() {
        return attack + attackBuff;
    }

    /** Repair action, to be used during Decision Phase */
    public void repair() {
        SkyborneLite.addDays(1);
        System.out.println();
        if (health == 100) {
            if (healthBuff == 10)
                healthBuffDuration++;
            else
                healthBuff = 10;
            print("Already fully repaired! (+HP)\n");
            sleep(1500);
            return;
        }

        print("*sawing noises*");
        sleep(1000);
        health = 100;
        print("Fully repaired!");
        sleep(2500);
    }

    /** Meditate action, to be used during Decision Phase */
    public void meditate() {
        System.out.println();
        print("Breathe in...");
        sleep(750);
        print("Aight that's enough (+ATK)\n");
        sleep(1500);

        SkyborneLite.addDays(3);
        if (attackBuff == 0)
            attackBuff = 30;
        else
            attackBuffDuration++;
    }
}
