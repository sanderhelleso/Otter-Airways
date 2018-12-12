package flg.flightreservationsystem.src;
import flg.flightreservationsystem.helpers.Util;

public class LogEntry  {

    // instantiate new util
    private final Util UTIL = new Util();

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
        return  "Log Type: " + type +
                "\nTransaction Date: " + timestamp.substring(0, 10) +
                "\nTransaction Time: " + timestamp.substring(11, 16) + UTIL.getDateMarker(timestamp) + "\n";
    }
}
