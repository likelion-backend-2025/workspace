package day10;

import org.w3c.dom.ls.LSOutput;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Set;
import java.util.TimeZone;

public class ZonedDateTimeExample {
    public static void main(String[] args) {
        ZonedDateTime now = ZonedDateTime.now();
        System.out.println(now);

        String[] ids = TimeZone.getAvailableIDs();
        for(String id : ids){
            if(id.contains("Seoul"))
                System.out.println(id);
        }

        // 다른 시간대로 변환
        ZonedDateTime newYork = now.withZoneSameInstant(
                ZoneId.of("America/New_York"));
        System.out.println("뉴욕: " + newYork);

        ZonedDateTime paris = ZonedDateTime.of(
                LocalDateTime.now(),
                ZoneId.of("Europe/Paris"));

        System.out.println("파리: "+paris);


        Set<String> zoneIds = ZoneId.getAvailableZoneIds();
        for(String z : zoneIds){
            if(z.contains("America"))
                System.out.println(z);
        }
    }
}
