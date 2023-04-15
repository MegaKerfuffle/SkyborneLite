package game;
import java.util.Scanner;
import java.util.Random;
import managers.*;

/** 
 * SkyborneLite is a simple text-based game, set in the age of airships, about one captain's
 * pursuit of a dangerous air pirate. The story is super limited, given the minimal amount of 
 * time I gave myself to complete this, but it uses some elements from a shared story universe
 * I'm writing for the games I make in my own time.
 **/
public class SkyborneLite {
    public static Scanner scanner;
    // NOTE: keeping random here so that seed could be set at start if desired in future
    public static Random random; 
    static GameStage stage = GameStage.Stopped;

    public static int turnNumber = 0;
    public static int daysElapsed = 0;
    public static int pirateDays = 4;

    public static final int maxTurns = 16;

    /** Starts a new game */
    public static void newGame() {
        // Create managers
        scanner = new Scanner(System.in);
        random = new Random();
        new EndManager();
        new MapManager();
        new ShipManager();
        new DecisionManager();
        new EncounterManager();

        // Display intro text
        intro();

        // Set game stage and invoke start event
        stage = GameStage.Decision;
        GameManager.invokeEvent(new ManagerEvent(EventType.Start));

        // Run main loop
        while (stage != GameStage.Stopped)
            GameManager.invokeEvent(new ManagerEvent(EventType.Tick));
    }   
    
    /** Quits the game */
    public static void quitGame() {
        print("\nQuitting...");
        stage = GameStage.Stopped;
        GameManager.invokeEvent(new ManagerEvent(EventType.Quit));
        scanner.close();   
    }

    /** End the game, with a reason */
    public static void endGame(String reason) {
        print("\nGAME OVER");
        print(reason);
        GameManager.waitForEnter();
        print("STATS");
        print("  * " + turnNumber + " TOTAL TURN(S) TAKEN");
        print("  * " + daysElapsed + " TOTAL DAY(S) OF PURSUIT");
        print("  * " + pirateDays + " DAY(S) TRAVELLED BY PIRATE");
        GameManager.waitForEnter();
        quitGame();
    }

    /** Start the next turn */
    public static void nextTurn() {
        turnNumber++;
        pirateDays += random.nextInt(-3, 2);
        GameManager.invokeEvent(new ManagerEvent(EventType.Custom, "next_turn"));
    }

    /** Progress time by adding days. */
    public static void addDays(int days) {
        daysElapsed += days;
        if (random.nextInt(10) > 7) return;
        pirateDays += days;
    }
    
    /** Displays backstory and some instructions */
    private static void intro() {
        print("\nWelcome to Skyborne Lite!\n");
        print("You are a successful airship captain, in the age of lighter-than-air flying machines.");
        print("You've been tasked with chasing down a known air pirate. They're a few days ahead of you.");
        print("You will need to navigate through several Sky Zones. You can choose which zones to navigate through.");
        print("Safer zones take more time to pass through, while more dangerous zones are quicker.");
        print("\nThe following commands are available at anytime: 'quit', 'about', and 'help'.");
        print("At prompts presenting a list of numbered options, enter just the number to make your choice.");
        GameManager.waitForEnter();
    }

    /**
     * Presents a prompt to the user and awaits their input. User input is sent to managers
     * via the integrated event system.
     * @param prompt - String to show to the user
     */
    public static void input(String prompt) {
        print(prompt);
        var input = scanner.nextLine();
        switch (input.toLowerCase()) {
            case "quit":
                SkyborneLite.quitGame();
                break;
            case "about":
                print("\nSkyborne Lite\n");
                print("Developed by Rob (rob@megakerfuffle.games)");
                print("https://megakerfuffle.games/");
                GameManager.waitForEnter();
                break;
            case "help":
                print("I'd love to have some actual help here but I'm out of time :)");
                GameManager.sleep(1500);
                break;
        }
        GameManager.invokeEvent(new ManagerEvent(EventType.Input, input));
    }

    /** Shorthand for System.out.println() */
    public static void print(String output) { System.out.println(output); }
    /** Getter for Game Stage */
    public static GameStage getStage() { return stage; }
    /** Setter for Game Stage */
    public static void setStage(GameStage newStage) { stage = newStage; }
    /** Gets the chase distance (day difference between player and pirate) */
    public static int chaseDistance() { return pirateDays - daysElapsed; }
}
