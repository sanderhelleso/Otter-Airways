package flg.flightreservationsystem.src;
import flg.flightreservationsystem.helpers.Util;

public class LogEntry  {

    // instantiate new util
    private final Util UTIL = new Util();

    private int entryID;
    private String type;
    private String timestamp;
    private String username;
    private String message;

    public LogEntry(int entryID, String type, String timestamp, String username, String message) {
        this.entryID = entryID;
        this.type = type;
        this.timestamp = timestamp;
        this.username = username;
        this.message = message;
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

    public String getMessage() {
        return message == null ? "" : message;
    }

    @Override
    public String toString() {
        return  "Log Type: " + type +
                "\nTransaction Date: " + timestamp.substring(0, 10) +
                "\nTransaction Time: " + UTIL.getDateMarker(timestamp) + "\n";
    }
}
