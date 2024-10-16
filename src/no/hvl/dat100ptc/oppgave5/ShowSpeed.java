package no.hvl.dat100ptc.oppgave5;

import javax.swing.JOptionPane;

import easygraphics.EasyGraphics;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSData;
import no.hvl.dat100ptc.oppgave2.GPSDataFileReader;
import no.hvl.dat100ptc.oppgave3.GPSUtils;
import no.hvl.dat100ptc.oppgave4.GPSComputer;
import no.hvl.dat100ptc.TODO;

public class ShowSpeed extends EasyGraphics {
	
			
	private static int MARGIN = 50;
	private static int BARHEIGHT = 100; 

	private GPSComputer gpscomputer;
	
	public ShowSpeed() {

		String filename = JOptionPane.showInputDialog("GPS data filnavn: ");
		gpscomputer = new GPSComputer(filename);
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	public void run() {

		makeWindow("Speed profile", 
				2 * MARGIN + 
				2 * gpscomputer.speeds().length, 2 * MARGIN + BARHEIGHT);
		
		showSpeedProfile(MARGIN + BARHEIGHT);
	}
	
	public void showSpeedProfile(int ybase) {
	    int x = MARGIN;  // Startposisjon på x-aksen
	    double[] speeds = gpscomputer.speeds();  // Henter hastighetene mellom punktene
	    double maxSpeed = gpscomputer.maxSpeed();  // Finn maksimal hastighet

	    // Tegn stolper for hastigheter
	    for (int i = 0; i < speeds.length; i++) {
	        // Skaler hastigheten slik at den passer til BARHEIGHT
	        int barHeight = (int) (speeds[i] * BARHEIGHT / maxSpeed);
	        drawLine(x, ybase, x, ybase - barHeight);  // Tegn linje/stolpe for hastigheten
	        x += 4;  // Flytt x-posisjonen
	    }

	    // Tegn gjennomsnittshastigheten som en grønn linje
	    double avgSpeed = gpscomputer.averageSpeed();
	    setColor(0, 255, 0);  // Sett fargen til grønn
	    int avgLineHeight = ybase - (int) (avgSpeed * BARHEIGHT / maxSpeed);  // Skaler gjennomsnittshastigheten
	    drawLine(MARGIN, avgLineHeight, x, avgLineHeight);  // Tegn linjen langs hele x-aksen
	}



}
