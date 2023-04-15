package managers;
import game.SkyborneLite;
import game.GameStage;

/** 
 * Manages the Decision phase of the game. This is meant to be a moment to gather
 * yourself after an Encounter, to prepare for the next turn. It could be fleshed
 * out further if I wasn't treating this project like a 2-day game jam.
 */
public class DecisionManager extends GameManager {
    private String decisionState = "standby";

    @Override
    protected void onGameStart() {
        showOptions();
    }

    @Override
    protected void onGameTick() {
        if (SkyborneLite.getStage() != GameStage.Decision) return;
        if (!decisionState.equals("standby")) return;
        showOptions();
    }
    
    @Override
    protected void onInputReceived(String input) {
        if (SkyborneLite.getStage() != GameStage.Decision) return;
        if (decisionState.equals("standby")) return;
        switch (decisionState) {
            case "base":
                baseInputHandler(input);
                break;
            case "navigate":
                navigateInputHandler(input);
                break;
        }
    }

    /** Input handler for the main decision options (navigate, repair, meditate) */
    private void baseInputHandler(String input) {
        switch (input) 
        {
            case "1":
                SkyborneLite.nextTurn();
                showNavigation();
                break;
            case "2":
                ShipManager.instance.repair();
                showOptions();
                break;
            case "3":
                ShipManager.instance.meditate();
                showOptions();
                break;
            default:
                print("Invalid input! Try again, entering only a number (e.g. '1')");
                sleep(1000);
                showOptions();
                break;
        }
    }

    /** Input handler for the navigate decision options (which zone to go to) */
    private void navigateInputHandler(String input) {
        int selected;
        try {
            selected = Integer.parseInt(input) - 1;
        }
        catch (Exception ex) {
            showNavigation();
            return;
        }

        if (!MapManager.instance.setNextZone(selected)) {
            showNavigation();
            return;
        }
            
        SkyborneLite.setStage(GameStage.Encounter);
        decisionState = "standby";
    }

    /** Displays the base decision options and calls for input */
    private void showOptions() {
        print("\nDECISION PHASE");
        print("Turn " + SkyborneLite.turnNumber + ", " + SkyborneLite.daysElapsed + " days since start");
        print("Pirate is " + SkyborneLite.chaseDistance() + " days ahead!");
        print("  1) Navigate to next Sky Zone");
        print("  2) Do repairs [1 DAY]");
        print("  3) Meditate [3 DAYS]");
        decisionState = "base";
        SkyborneLite.input("What would you like to do?");
    }

    /** Displays the navigation decision options and calls for input */
    private void showNavigation() {
        MapManager.instance.printCurrent();
        decisionState = "navigate";
        SkyborneLite.input("Where would you like to go?");
    }
}
