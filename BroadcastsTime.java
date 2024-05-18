import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class BroadcastsTime implements Comparable{

    private final int hour;
    private final int minutes;
    private static final String TIME = LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm"));

    public BroadcastsTime(int hour, int minutes) {
        this.hour = hour;
        this.minutes = minutes;
    }

    public static BroadcastsTime now() {
        return new BroadcastsTime(LocalTime.now().getHour(), LocalTime.now().getMinute());
    }

    public static BroadcastsTime parse(String time) {
        int hour = Byte.parseByte(time.substring(0, 2));
        int minutes = Byte.parseByte(time.substring(3, 5));
        return new BroadcastsTime(hour, minutes);
    }

    public int getHour() {
        return hour;
    }

    public int getMinutes() {
        return minutes;
    }

    public boolean after(BroadcastsTime time) {
        return hour > time.getHour()
                || (hour == time.getHour() && minutes > time.getMinutes());
    }

    boolean before(BroadcastsTime time) {
        return hour < time.getHour()
                || (hour == time.getHour() && minutes < time.getMinutes());
    }

    boolean between(BroadcastsTime time1, BroadcastsTime time2) {
        return after(time1) && before(time2);
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof BroadcastsTime otherTime) {
            if (before(otherTime)) {
                return -1;
            }
            if (after(otherTime)) {
                return 1;
            }
            return 0;
        }
        throw new RuntimeException("Incorrect object type");
    }

    @Override
    public String toString() {
        return hour + ":" + minutes;
    }
}
