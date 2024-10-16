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

		minlon = GPSUtils.findMin(GPSUtils.getLongitudes(gpspoints));
		minlat = GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints));

		maxlon = GPSUtils.findMax(GPSUtils.getLongitudes(gpspoints));
		maxlat = GPSUtils.findMax(GPSUtils.getLatitudes(gpspoints));
		
		xstep = scale(MAPXSIZE, minlon, maxlon);
		ystep = scale(MAPYSIZE, minlat, maxlat);
		
		showRouteMap(MARGIN + MAPYSIZE);

		replayRoute(MARGIN + MAPYSIZE);
		
		showStatistics();
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

		setColor(0,0,0);
		setFont("Courier",12);
		
		// TODO
		throw new UnsupportedOperationException(TODO.method());
		
	}

	public void replayRoute(int ybase) {

		// TODO 
		throw new UnsupportedOperationException(TODO.method());
		
	}

}
