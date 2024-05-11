package com.example.gameuidemo;

import java.time.Duration;
import java.time.Instant;

public class GameTimer {
    private static Instant startTime;
    private static Instant endTime;
    private static double timePlayed;

    public static void setStartTime() {
        startTime = Instant.now();
    }

    public static void setEndTime() {
        endTime = Instant.now();
    }

    public static void setTimePlayed() {
        Duration duration = Duration.between(startTime, endTime);
        long seconds = duration.getSeconds(); // võtab täis sekundid
        int millisPart = duration.toMillisPart(); // võtab sekundi murdosa
        int secondsPart = duration.toSecondsPart(); // Extract seconds part

        //millisekundite teisendamine sekunditeks (jagamine 1000ga)
        // teisendatud millisekundite ja sekundi murdosa liitmine ja teisendamine sekundi murdosaks
        // ning liitmine täis sekunditele
        timePlayed = seconds + (secondsPart + (double) millisPart / 1000) / 1000;


    }

    public static double getTimePlayed() {
        return timePlayed;
    }
}
