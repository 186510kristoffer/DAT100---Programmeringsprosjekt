package no.hvl.dat100ptc.oppgave5;

import javax.swing.JOptionPane;

import easygraphics.EasyGraphics;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave3.GPSUtils;
import no.hvl.dat100ptc.oppgave4.GPSComputer;

import no.hvl.dat100ptc.TODO;

public class ShowRoute extends EasyGraphics {

	private static int MARGIN = 50;
	private static int MAPXSIZE = 800;
	private static int MAPYSIZE = 800;

	private GPSPoint[] gpspoints;
	private GPSComputer gpscomputer;
	
	private double minlon, minlat, maxlon, maxlat;

	private double xstep, ystep;
	
	public ShowRoute() {

		String filename = JOptionPane.showInputDialog("GPS data filnavn: ");
		gpscomputer = new GPSComputer(filename);

		gpspoints = gpscomputer.getGPSPoints();

	}

	public static void main(String[] args) {
		launch(args);
	}

	public void run() {

	    makeWindow("Route", MAPXSIZE + 2 * MARGIN, MAPYSIZE + 2 * MARGIN);

	    // Beregn verdier for skalering
	    minlon = GPSUtils.findMin(GPSUtils.getLongitudes(gpspoints));
	    minlat = GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints));

	    maxlon = GPSUtils.findMax(GPSUtils.getLongitudes(gpspoints));
	    maxlat = GPSUtils.findMax(GPSUtils.getLatitudes(gpspoints));
	    
	    xstep = scale(MAPXSIZE, minlon, maxlon);
	    ystep = scale(MAPYSIZE, minlat, maxlat);

	    // **Vis statistikken før ruten**
	    showStatistics();

	    // Tegn ruten etter statistikken
	    showRouteMap(MARGIN + MAPYSIZE);

	    // Re-animer ruten til slutt
	    replayRoute(MARGIN + MAPYSIZE);
	}


	public double scale(int maxsize, double minval, double maxval) {

		double step = maxsize / (Math.abs(maxval - minval));

		return step;
	}

	public void showRouteMap(int ybase) {

	    // Setter farge for ruten
	    setColor(0, 0, 255);  // Blå farge for ruten

	    for (int i = 0; i < gpspoints.length - 1; i++) {
	        // Konverter bredde- og lengdegrader til pikselverdier på kartet
	        int x1 = MARGIN + (int) ((gpspoints[i].getLongitude() - minlon) * xstep);
	        int y1 = ybase - (int) ((gpspoints[i].getLatitude() - minlat) * ystep);

	        int x2 = MARGIN + (int) ((gpspoints[i + 1].getLongitude() - minlon) * xstep);
	        int y2 = ybase - (int) ((gpspoints[i + 1].getLatitude() - minlat) * ystep);

	        // Tegn linje mellom hvert par av GPS-punkter
	        drawLine(x1, y1, x2, y2);
	    }
	}


	public void showStatistics() {

	    int TEXTDISTANCE = 20;

	    setColor(0, 0, 255); // Sett tekstfargen til blå
	    setFont("Courier", 12); // Bruk Courier-skrifttype

	    // Hent statistikkverdier fra GPSComputer
	    double totalDistance = gpscomputer.totalDistance();
	    double totalElevation = gpscomputer.totalElevation();
	    double maxSpeed = gpscomputer.maxSpeed();
	    double averageSpeed = gpscomputer.averageSpeed();
	    int totalTimeSecs = gpscomputer.totalTime();

	    // Konverter total tid til timer, minutter og sekunder
	    int hours = totalTimeSecs / 3600;
	    int minutes = (totalTimeSecs % 3600) / 60;
	    int seconds = totalTimeSecs % 60;

	    // Tegn statistikken i vinduet
	    drawString("Total Distance: " + String.format("%.2f", totalDistance) + " km", MARGIN, TEXTDISTANCE);
	    drawString("Total Elevation: " + String.format("%.2f", totalElevation) + " m", MARGIN, TEXTDISTANCE + 20);
	    drawString("Max Speed: " + String.format("%.2f", maxSpeed) + " km/h", MARGIN, TEXTDISTANCE + 40);
	    drawString("Average Speed: " + String.format("%.2f", averageSpeed) + " km/h", MARGIN, TEXTDISTANCE + 60);
	    drawString("Total Time: " + hours + " hours, " + minutes + " minutes, " + seconds + " seconds", MARGIN, TEXTDISTANCE + 80);
	}


	public void replayRoute(int ybase) {
	    int radius = 5;  // Radius på sirkelen
	    int delay = 100;  // Forsinkelse mellom hvert punkt (i millisekunder)

	    // Startposisjonen til sirkelen
	    int x = MARGIN + (int) ((gpspoints[0].getLongitude() - minlon) * xstep);
	    int y = ybase - (int) ((gpspoints[0].getLatitude() - minlat) * ystep);

	    // Tegn sirkelen på startposisjonen
	    int circle = fillCircle(x, y, radius);

	    // Flytt sirkelen langs ruten
	    for (int i = 1; i < gpspoints.length; i++) {
	        int xNext = MARGIN + (int) ((gpspoints[i].getLongitude() - minlon) * xstep);
	        int yNext = ybase - (int) ((gpspoints[i].getLatitude() - minlat) * ystep);

	        // Flytt sirkelen
	        moveCircle(circle, xNext, yNext);
	        pause(delay);  // Forsinkelse for å lage animasjonseffekt
	    }
	}


}
