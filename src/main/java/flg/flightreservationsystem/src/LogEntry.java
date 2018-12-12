package flg.flightreservationsystem.src;

public class LogEntry {

    private int entryID;
    private String type;
    private String timestamp;
    private String username;

    public LogEntry (int entryID, String type, String timestamp, String username) {
        this.entryID = entryID;
        this.type = type;
        this.timestamp = timestamp;
        this.username = username;
    }

    public int getEntryID() {
        return entryID;
    }

    public String getType() {
        return type;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return "LogEntry{" +
                "entryID=" + entryID +
                ", type='" + type + '\'' +
                ", timestamp=" + timestamp +
                ", cusomterID=" + username +
                '}';
    }
}
