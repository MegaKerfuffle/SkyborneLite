package game;
/** Data about a single Sky Zone, or a single area of the map. */
public class SkyZone {
    /** Time, in days, to clear this zone. */
    public int timeToTraverse = 1;
    /** % chance. Should be within 0 to 100 inclusive. */
    public int chanceOfEncounter = 25;

    public SkyZone(int encounterChance) {
        chanceOfEncounter = encounterChance;
        timeToTraverse = 4 - Math.round(encounterChance / 25);
    }
}