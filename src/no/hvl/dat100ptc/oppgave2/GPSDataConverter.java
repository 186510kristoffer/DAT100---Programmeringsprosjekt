package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSDataConverter {

    private static int TIME_STARTINDEX = 11;

    // Metode som konverterer tid fra strengen til sekunder siden midnatt
    public static int toSeconds(String timestr) {
        int hr = Integer.parseInt(timestr.substring(TIME_STARTINDEX, 13));
        int min = Integer.parseInt(timestr.substring(14, 16));
        int sec = Integer.parseInt(timestr.substring(17, 19));
        return hr * 3600 + min * 60 + sec;  // Regner ut antall sekunder
    }

    // Metode som konverterer strenger til et GPSPoint objekt
    public static GPSPoint convert(String timeStr, String latitudeStr, String longitudeStr, String elevationStr) {
        int time = toSeconds(timeStr);  // Konverterer tid til sekunder
        double latitude = Double.parseDouble(latitudeStr);  // Konverterer breddegrad til double
        double longitude = Double.parseDouble(longitudeStr);  // Konverterer lengdegrad til double
        double elevation = Double.parseDouble(elevationStr);  // Konverterer høyde til double
        return new GPSPoint(time, latitude, longitude, elevation);  // Lager nytt GPSPoint
    }
}

