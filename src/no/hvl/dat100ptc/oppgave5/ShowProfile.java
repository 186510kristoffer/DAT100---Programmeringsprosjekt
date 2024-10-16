package no.hvl.dat100ptc.oppgave5;

import no.hvl.dat100ptc.TODO;

import easygraphics.EasyGraphics;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSData;
import no.hvl.dat100ptc.oppgave2.GPSDataConverter;
import no.hvl.dat100ptc.oppgave2.GPSDataFileReader;
import no.hvl.dat100ptc.oppgave4.GPSComputer;

import javax.swing.JOptionPane;

public class ShowProfile extends EasyGraphics {

	private static final int MARGIN = 50;		// margin on the sides 
	
	private static final int MAXBARHEIGHT = 500; // assume no height above 500 meters
	
	private GPSPoint[] gpspoints;

	public ShowProfile() {

		String filename = JOptionPane.showInputDialog("GPS data filnavn (uten .csv): ");
		GPSComputer gpscomputer =  new GPSComputer(filename);

		gpspoints = gpscomputer.getGPSPoints();
		
	}

	public static void main(String[] args) {
		launch(args);
	}

	public void run() {

		int N = gpspoints.length; // number of data points

		makeWindow("Height profile", 2 * MARGIN + 3 * N, 2 * MARGIN + MAXBARHEIGHT);

		// top margin + height of drawing area
		showHeightProfile(MARGIN + MAXBARHEIGHT); 
	}

	public void showHeightProfile(int ybase) {
	    int x = MARGIN;  // Start ved marginen

	    for (int i = 0; i < gpspoints.length; i++) {
	        // Hent høyden for hvert GPS-punkt
	        int elevation = (int) gpspoints[i].getElevation();

	        // Skaler høyden til vinduets maksimale høyde (500 meter som maks)
	        int barHeight = Math.min(elevation, MAXBARHEIGHT);

	        // Tegn en stolpe for hvert punkt
	        drawLine(x, ybase, x, ybase - barHeight);

	        // Flytt x-posisjonen for neste stolpe
	        x += 3;  // Avstand mellom stolpene, kan justeres etter behov
	    }
	}


}
