package no.hvl.dat100ptc.oppgave4;

import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSData;
import no.hvl.dat100ptc.oppgave2.GPSDataConverter;
import no.hvl.dat100ptc.oppgave2.GPSDataFileReader;
import no.hvl.dat100ptc.oppgave3.GPSUtils;

import no.hvl.dat100ptc.TODO;

public class GPSComputer {

	private GPSPoint[] gpspoints;

	public GPSComputer(String filename) {

		GPSData gpsdata = GPSDataFileReader.readGPSFile(filename);
		gpspoints = gpsdata.getGPSPoints();

	}

	public GPSComputer(GPSPoint[] gpspoints) {
		this.gpspoints = gpspoints;
	}

	public GPSPoint[] getGPSPoints() {
		return this.gpspoints;
	}

	public double totalDistance() {

		double totalDistance = 0;

		for (int i = 0; i < gpspoints.length - 1; i++) {
			totalDistance += GPSUtils.distance(gpspoints[i], gpspoints[i + 1]);
		}
		return totalDistance;

	}

	public double totalElevation() {

		double elevation = 0;

		for (int i = 1; i < gpspoints.length; i++) {
			double deltaElevation = gpspoints[i].getElevation() - gpspoints[i - 1].getElevation();
			if (deltaElevation > 0) {
				elevation += deltaElevation;
				
			}
		}
		return elevation;

	}

	public int totalTime() {

		return gpspoints[gpspoints.length - 1].getTime() - gpspoints[0].getTime();

	}

	public double[] speeds() {

		double[] speeds = new double[gpspoints.length - 1];

		for (int i = 0; i < gpspoints.length - 1; i++) {
			speeds[i] = GPSUtils.speed(gpspoints[i], gpspoints[i + 1]);
		}

		return speeds;
	}

	public double maxSpeed() {

		double maxspeed = 0;

		double[] speeds = speeds();
		return GPSUtils.findMax(speeds);
	}

	public double averageSpeed() {

		double average = 0;

		double totalDistance = totalDistance();
		int totalTime = totalTime();

		return (totalDistance / totalTime) * 3.6;
	}

	public static final double MS = 2.23;

	public double kcal(double weight, int secs, double speed) {
		double kcal;
		double met = 0;

		
		double speedmph = speed * MS;
		
		if (speedmph < 10) {
			met = 4.0;
		} else if (speedmph < 12) {
			met = 6.0;
		} else if (speedmph < 14) {
			met = 8.0;
		} else if (speedmph < 16) {
			met = 10.0;
		} else {
			met = 12.0;
		}

		kcal = met * weight * (secs / 3600.0);
		return kcal;
	}

	public double totalKcal(double weight) {

		double totalKcal = 0;
		int totalTime = totalTime(); 
		double averageSpeed = averageSpeed();  

		
		totalKcal = kcal(weight, totalTime, averageSpeed);

		return totalKcal;

		
	}

	private static double WEIGHT = 80.0;

	public void displayStatistics() {
		
		
		

	}

}
