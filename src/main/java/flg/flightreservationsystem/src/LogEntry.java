package flg.flightreservationsystem.src;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
        return  "Log Type: " + type +
                "\nTransaction Date: " + timestamp.substring(0, 10) +
                "\nTransaction Time: " + timestamp.substring(11, 16) + getDateMarker() + "\n";
    }

    private String getDateMarker() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = null;
        try {
            date = dateFormat.parse(timestamp);
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        return " (" + new SimpleDateFormat("aa").format(date) + ")";
    }
}
