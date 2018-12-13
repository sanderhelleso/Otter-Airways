package flg.flightreservationsystem.helpers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {

    public String getDateMarker(final String timestamp) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = null;
        try {
            date = dateFormat.parse(timestamp);
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        return new SimpleDateFormat("hh:mm (aa)").format(date);
    }

    public String getDateMarkerFromHourMin(final int hm) {

        // Displaying given time in 12 hour format with AM/PM
        String dateString = String.valueOf(hm).substring(0, 2) + ":" + String.valueOf(hm).substring(3, 4);

        //old format
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

        try{
            Date date = sdf.parse(dateString);

            //new format
            SimpleDateFormat newSdf = new SimpleDateFormat("hh:mm (aa)");

            //formatting the given time to new format with AM/PM
            dateString = newSdf.format(date);

        }catch(ParseException e){
            e.printStackTrace();
        }

        return dateString;
    }
}
