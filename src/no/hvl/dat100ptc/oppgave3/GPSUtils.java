package no.hvl.dat100ptc.oppgave3;

import static java.lang.Math.*;

import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.TODO;

public class GPSUtils {

	public static double findMax(double[] da) {

		double max; 
		
		max = da[0];
		
		for (double d : da) {
			if (d > max) {
				max = d;
			}
		}
		
		return max;
	}

	public static double findMin(double[] da) {

		double min;
		
		min = da[0];
		
		for (double d : da) {
			if (d < min) {
				min = d;
			}
		}
		
		return min;
	}


	public static double[] getLatitudes(GPSPoint[] gpspoints) {

		double[] latitude = new double[gpspoints.length];
		
		for (int i = 0; i < gpspoints.length; i++) {
            latitude[i] = gpspoints[i].getLatitude();
        }
		return latitude;
	}

	public static double[] getLongitudes(GPSPoint[] gpspoints) {

		
		double[] longitude = new double[gpspoints.length];
		
		for (int i = 0; i < gpspoints.length; i++) {
			longitude[i] = gpspoints[i].getLongitude();
        }
		return longitude;
	} 


	private static final int R = 6371000; // jordens radius

	public static double distance(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		float R = 6371000;

		double latitude1, longitude1, latitude2, longitude2;

		latitude1 = Math.toRadians(gpspoint1.getLatitude());
		longitude1 = Math.toRadians(gpspoint1.getLongitude());
		
		latitude2 = Math.toRadians(gpspoint2.getLatitude());
		longitude2 = Math.toRadians(gpspoint2.getLongitude());
		
		double Delatitude = latitude2 - latitude1;
		double Delongitude = longitude2 - longitude1;
		
		double a = Math.pow((Math.sin(Delatitude/2)), 2) + Math.cos(latitude1) * Math.cos(latitude2) * Math.pow((Math.sin(Delongitude/2)), 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		double d = R * c;
		return d;
	}
	
	private static double compute_a(double phi1, double phi2, double deltaphi, double deltadelta) {
	
		throw new UnsupportedOperationException(TODO.method());
		
		

	}

	private static double compute_c(double a) {

		
		throw new UnsupportedOperationException(TODO.method());
		
		
		// TODO 

	}

	
	public static double speed(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		int secs = 10;
		double speed;
		double distance=distance(gpspoint1, gpspoint2);
		
		speed=distance/secs;
		return speed;
		
		
		/* som beregninger gjennomsnittshastighet i m/s om man beveger seg fra punktet gitt ved gpspoint1 til punktet gpspoint2.
		Hint: Bruk metoden distance fra d) samt get-metode(r) på GPSPoint-objekt.*/ 

	}

	public static String formatTime(int secs) {

		String timestr;
		String TIMESEP = ":";

		   int timer = secs / 3600;
		    int minuter = (secs % 3600) / 60;
		    int sekunder = secs % 60;
		    
		     timestr = String.format("%02d%s%02d%s%02d", timer, TIMESEP, minuter, TIMESEP, sekunder);
		    
		    return String.format("%10s", timestr);
		}
	
	
	private static int TEXTWIDTH = 10;

	public static String formatDouble(double d) {

		String str;

		
		throw new UnsupportedOperationException(TODO.method());
		
		// TODO
		
	}
}
