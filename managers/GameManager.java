package managers;
import java.util.ArrayList;
import java.util.List;
import game.GameStage;
import game.SkyborneLite;

/** Base class for any and all classes that manage some aspect of the game. */
public abstract class GameManager {
    private static List<GameManager> managers = new ArrayList<>();

    public GameManager() {
        managers.add(this);
    }

    protected abstract void onGameStart();
    protected void onGameQuit() { } // <-- dont think this is used at all, so im un-abstracting it
    protected void onGameTick() { }
    protected void onInputReceived(String input) { }
    protected void onCustomEvent(ManagerEvent event) { } // <--- this is only used like once but ¯\_(ツ)_/¯ 
    
    /** Handles incoming manager events by calling the correct method */
    private void eventHandler(ManagerEvent event) {
        if (SkyborneLite.getStage() == GameStage.Stopped && event.type != EventType.Quit) return;

        switch (event.type) {
            case Start:
                onGameStart();
                break;
            case Quit:
                onGameQuit();
                break;
            case Tick:
                onGameTick();
                break;
            case Input:
                try {
                    onInputReceived((String)event.payload);
                }
                finally { } // just in case payload isn't a string
                break;
            case Custom:
                onCustomEvent(event);
                break;
            default:
                print("[DEBUG] no event handler for type " + event.type);
                break;
        }
    }

    /** Fires a manager event on all constructed Game Managers */
    public static void invokeEvent(ManagerEvent event) {
        for (var manager : managers)
            manager.eventHandler(event);
    }

    /** Prints a message. I really don't like typing System.out.println() all the time. */
    public static void print(String text) {
        System.out.println(text);
    }

    /** Sleep for some amount of time, in milliseconds. */
    public static void sleep(int timeMs) {
        try {
            Thread.sleep(timeMs);
        }
        catch (Exception ex) {
            print("[DEBUG] sleep failed: " + ex);
        }
    }

    /** Returns the current turn number */
    public static int getTurn() {
        return SkyborneLite.turnNumber;
    }

    /** Blocks execution until the user presses enter */
    public static void waitForEnter() {
        print("\nPress [ENTER] to continue");
        SkyborneLite.scanner.nextLine();
    }
}
