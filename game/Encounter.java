package game;

/**
 * A random encounter presented to the player, where they need to make a
 * choice. Choices can have a range of impacts.
 */
public class Encounter {
    public String title;
    public String summary;
    public EncounterOutcome[] outcomes;

    public Encounter(String title, String summary, EncounterOutcome[] outcomes) {
        this.title = title;
        this.summary = summary;
        this.outcomes = outcomes;
    }
}
