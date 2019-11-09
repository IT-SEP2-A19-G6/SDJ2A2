package shared;

import java.text.SimpleDateFormat;

/*
    Used as a proxy for system out print line. Ensures classname is also printed out whenever used.
 */
public class sout {

    public enum OutputLevel {
        LOW, NORMAL
    }

    private static OutputLevel outputLevel = OutputLevel.NORMAL;

    public static void write(Object c, String msg) {
        if (outputLevel == OutputLevel.NORMAL) {
            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss:SSSS");
            String dateString = formatter.format(new java.util.Date());
            System.out.println(dateString + " - " + c.getClass().getSimpleName() + ": " + msg);
        }

    }




}
