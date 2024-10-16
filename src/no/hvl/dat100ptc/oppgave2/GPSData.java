package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSData {

	private GPSPoint[] gpspoints;
	protected int antall = 0;

	public GPSData(int antall) {
	    gpspoints = new GPSPoint[antall];  // Initialiserer arrayet
	    this.antall = 0;  // Setter antall til 0
	}


	public GPSPoint[] getGPSPoints() {
	    return this.gpspoints;  // Returnerer hele arrayet av GPS-punkter
	}

	protected boolean insertGPS(GPSPoint gpspoint) {
	    if (antall < gpspoints.length) {  // Sjekker om det er plass i arrayet
	        gpspoints[antall] = gpspoint;  // Legger til GPS-punktet i arrayet
	        antall++;  // Øker antall punkter som er lagret
	        return true;  // Returnerer true når innsettingen er vellykket
	    }
	    return false;  // Returnerer false hvis arrayet er fullt
	}


	public boolean insert(String time, String latitude, String longitude, String elevation) {
	    // Bruker GPSDataConverter til å konvertere verdiene til riktige datatyper
	    GPSPoint gpspoint = GPSDataConverter.convert(time, latitude, longitude, elevation);
	    
	    // Setter inn det nye GPS-punktet ved hjelp av insertGPS() og returnerer resultatet
	    return insertGPS(gpspoint);
	}


	public void print() {
	    System.out.println("====== Konvertert GPS Data - START ======");
	    for (int i = 0; i < antall; i++) {
	        System.out.println(gpspoints[i].toString());  // Skriver ut hvert GPS-punkt
	    }
	    System.out.println("====== Konvertert GPS Data - SLUTT ======");
	}

}
