package managers;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import game.Encounter;
import game.EncounterBuilder;
import game.GameStage;
import game.SkyborneLite;

/** 
 * Manages the Encounter phase of the game. This presents the player with a random
 * encounter, where they need to make a choice. The result of this choice can be 
 * positive or negative, or (if I feel like implementing this) can lead to Combat.
 */
public class EncounterManager extends GameManager {
    public static Encounter[] encounters;
    private static List<Encounter> available;

    private boolean waitingForInput = false;
    private String encounterStatus = "standby";
    private Encounter current;

    @Override
    protected void onGameStart() {
        encounters = EncounterBuilder.buildEncounters();
        if (encounters == null) {
            print("[DEBUG] No encounters were given!");
            SkyborneLite.quitGame();
            return;
        }
        available = new ArrayList<Encounter>();
        Collections.addAll(available, encounters);
    }

    @Override
    protected void onGameTick() {
        if (SkyborneLite.getStage() != GameStage.Encounter) return;
        if (encounterStatus != "standby") return;
        startEncounter();
    }

    @Override
    protected void onInputReceived(String input) {
        if (SkyborneLite.getStage() != GameStage.Encounter) return;
        if (!waitingForInput) return;
        finishEncounter(input);
    }

    /** 
     * Starts the encounter phase. This can either lead to a random encounter,
     * or it'll skip directly to the decision phase. 
     **/
    private void startEncounter() {
        print("\nENCOUNTER PHASE");
        int roll = SkyborneLite.random.nextInt(101);
        if (roll > MapManager.nextZone.chanceOfEncounter) {
            // no encounter - go to decision
            print("You navigated without issues!");
            SkyborneLite.setStage(GameStage.Decision);
            sleep(2000);
            return;
        }
        
        // get and do encounter
        int index = SkyborneLite.random.nextInt(available.size());
        current = available.get(index);
        available.remove(index);
        showEncounter();
    }

    /** Show the current encounter and call for input */
    private void showEncounter() {
        System.out.println();
        print(current.title);
        print(current.summary);
        for (int i = 0; i < current.outcomes.length; i++)
            print((i+1) + ") " + current.outcomes[i].preChooseText);
        
        waitingForInput = true;
        SkyborneLite.input("\nWhat would you like to do?");
    }

    /** Finish the encounter and display the outcome */
    private void finishEncounter(String input) {
        int index;
        try {
            index = Integer.parseInt(input) - 1;
        }
        catch (Exception ex) {
            showEncounter();
            return;
        }
        if (index < 0 || index >= current.outcomes.length) {
            showEncounter();
            return;
        }

        var outcome = current.outcomes[index];
        print(outcome.postChooseText);
        ShipManager.instance.health += outcome.healthEffect;
        ShipManager.instance.attack += outcome.attackEffect;
        
        waitForEnter();
        waitingForInput = false;
        SkyborneLite.setStage(GameStage.Decision);
    }
}