package managers;
import game.GameStage;
import game.SkyborneLite;

/**
 * Manager responsible for the game's end conditions, and the boss fight.
 * This'll check the state of the player every tick, and end the game if 
 * they reached a fail state.
 */
public class EndManager extends GameManager {
    private boolean isReady = false;
    private boolean isPirateTurn = false;
    private int pirateHealth = 100;
    private int pirateAttack = 30;

    @Override
    protected void onGameStart() { }
    
    @Override
    protected void onGameTick() 
    { 
        if (SkyborneLite.getStage() == GameStage.Stopped) return;

        // Check distance between player and pirate.
        int chaseDistance = SkyborneLite.chaseDistance();
        if (chaseDistance > 0) {
            if (chaseDistance > 7 || SkyborneLite.turnNumber == SkyborneLite.maxTurns - 1)
                SkyborneLite.endGame("Unfortunately, you were too slow in your pursuit. The pirate managed to escape!");
            return;
        }
        // Setup the combat stage if distance is 0 or less.
        if (SkyborneLite.getStage() != GameStage.Combat)
        {
            SkyborneLite.setStage(GameStage.Combat);
            promptIfReady();
        }    
        // Wait until ready (player input)
        if (!isReady) return;

        // And run the boss fight. Should be in it's own method tbh
        print("\nPLAYER - HP: " + ShipManager.instance.health + " (+" + ShipManager.instance.healthBuff + ") | ATK: " + 
            ShipManager.instance.attack + " (+" + ShipManager.instance.attackBuff + ")");
        print("PIRATE - HP: " + pirateHealth + " | ATK: " + pirateAttack);

        if (isPirateTurn)
            pirateTurn();
        else
            playerTurn();
        sleep(1500);

        healthChecks();
    }

    @Override
    protected void onInputReceived(String input) {
        if (SkyborneLite.getStage() != GameStage.Combat) return;
        if (input.equals("go"))
            isReady = true;
        else
            promptIfReady();
        
    }

    private void promptIfReady() {
        print("\nCOMBAT PHASE");
        print("You've successfully caught up to the pirate, and will now be engaging in combat.");
        print("I had no time, so this bit runs itself. There are no more input prompts after this.");
        print("Depending on how lucky you are, this sequence could go pretty quick or it could take a while.");
        SkyborneLite.input("If you want to skip the combat phase and end the game, type 'quit' now. If not, type 'go'.");
    }

    /** Check if the player or pirate have failed yet. */
    private void healthChecks() {
        if (pirateHealth <= 0) {
            SkyborneLite.endGame("You successfully pursued and defeated the pirate! The skies are safe once again.");
        }
        else if (ShipManager.instance.health <= 0) {
            SkyborneLite.endGame("You managed to catch up with the pirate, but they utterly destroyed you in combat.");
        }
    }

    /** Run the pirate's combat turn. */
    private void pirateTurn() {
        print("The pirate shoots at your airship!");
        if (SkyborneLite.random.nextInt(3) <= 1) {
            int damage = SkyborneLite.random.nextInt(pirateAttack / 2, pirateAttack + 1);
            print("The attack hits, doing " + damage + " damage!");
            ShipManager.instance.doDamage(damage);
        }
        else
            print("The attack misses!");
        isPirateTurn = false;
    }

    /** Run the player's combat turn. */
    private void playerTurn() {
        print("You shoot at the pirate's airship!");
        int attack = ShipManager.instance.getAttack();
        if (SkyborneLite.random.nextInt(2) == 0) {
            int damage = SkyborneLite.random.nextInt(attack / 2, attack + 1);
            print("The attack hits, doing " + damage + " damage!");
            pirateHealth -= damage;
        }
        else
            print("The attack misses!");
        isPirateTurn = true;
    }
}
