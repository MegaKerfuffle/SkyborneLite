package managers;

/** Used to send data to - or trigger actions on - Game Managers */
public class ManagerEvent {
    public EventType type;
    public Object payload;

    public ManagerEvent(EventType type) {
        this.type = type;
    }

    public ManagerEvent(EventType type, Object payload) {
        this.type = type;
        this.payload = payload;
    }
}