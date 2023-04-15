package game;

/**
 * A single outcome of an Encounter. This defines the impact of the 
 * choice made by the player during the encounter.
 */
public class EncounterOutcome {
    public String preChooseText;
    public String postChooseText;
    public int healthEffect;
    public int attackEffect;

    public EncounterOutcome(String preChoose, String postChoose, int healthEffect, int attackEffect) {
        preChooseText = preChoose;
        postChooseText = postChoose;
        this.healthEffect = healthEffect;
        this.attackEffect = attackEffect;
    }
}
