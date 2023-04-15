package managers;
import game.SkyZone;
import game.SkyborneLite;

/** Manages the creation and access of the game's map. */
public class MapManager extends GameManager {
    static MapManager instance;
    public static SkyZone nextZone;
    private SkyZone[][] map;

    @Override
    public void onGameStart() {
        instance = this;
        generateMap(SkyborneLite.maxTurns);
    }

    /**
     * Generates a new map for this instance of the game.
     * @param numTurns - number of turns to accomodate for
     */
    private void generateMap(int numTurns) {
        map = new SkyZone[numTurns][];
        for (int i = 0; i < numTurns; i++) {
            map[i] = generateZones();
        }
    }

    /**
     * Generates the Sky Zones for a single turn. A turn must have at least one zone
     * for progression, and may have up to three. Each zone has a random probablity
     * of triggering an encounter - the zones with the higher chance of having an 
     * encounter incur less of a time penalty. Gambling!
     * @return the generated Sky Zones in an array
     */
    private SkyZone[] generateZones() {
        int numZones = SkyborneLite.random.nextInt(1, 4);
        int totalChance = 100;
        SkyZone[] turnZones = new SkyZone[numZones];
        for (int i = 0; i < numZones; i++) {
            int offset = 0;
            if (numZones > 1) {
                if (i == 0)
                    offset = 10;
                else if (i == numZones - 1) {
                    turnZones[i] = new SkyZone(totalChance);
                    break;
                }
            }

            int zoneChance = SkyborneLite.random.nextInt(0, totalChance - offset);
            totalChance -= zoneChance;
            turnZones[i] = new SkyZone(zoneChance);
        }
        return turnZones;
    }

    /** Retrieve a zone, by index, at the current turn */
    public SkyZone getZone(int index) {
        return map[getTurn()][index];
    }

    /** Set the next zone to the given index, at the current turn */
    public boolean setNextZone(int index) {
        if (index >= map[getTurn()].length) return false;
        nextZone = getZone(index);
        SkyborneLite.addDays(nextZone.timeToTraverse);
        return true;
    }

    /** Displays the zones available for this turn */
    public void printCurrent() {
        printZones(SkyborneLite.turnNumber);
    }

    /** Displays the zones available for a given turn  */
    public void printZones(int turnNumber) {
        String zones = "AVAILABLE SKYZONES:\n";
        var turnZones = map[turnNumber];
        for (int i = 0; i < turnZones.length; i++) {
            zones += "  " + (i+1) + ") " + turnZones[i].timeToTraverse + " DAYS\n"; 
        }
        SkyborneLite.print(zones);
    }
}
