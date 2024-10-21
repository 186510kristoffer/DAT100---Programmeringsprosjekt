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
	    int numSpeeds = gpscomputer.speeds().length;  // Henter antall hastigheter
	    
	    // Beregn bredde på vinduet: 2 * MARGIN for venstre og høyre margin + 2 * numSpeeds for bredden til stolpene
	    int windowWidth = 2 * MARGIN + 3 * numSpeeds;

	    // Sett opp høyden på vinduet basert på BARHEIGHT og MARGIN
	    int windowHeight = 2 * MARGIN + BARHEIGHT;

	    // Lag vinduet med justert bredde og høyde
	    makeWindow("Speed profile", windowWidth, windowHeight);
	    
	    // Tegn hastighetsprofilen
	    showSpeedProfile(MARGIN + BARHEIGHT);
	}

	
	public void showSpeedProfile(int ybase) {
	    int x = MARGIN;  // Startposisjon på x-aksen
	    double[] speeds = gpscomputer.speeds();  // Henter hastighetene mellom punktene
	    double maxSpeed = gpscomputer.maxSpeed();  // Finn maksimal hastighet
	    double avgSpeed = gpscomputer.averageSpeed();  // Finn gjennomsnittshastigheten

	    System.out.println("Max Speed: " + maxSpeed);
	    System.out.println("Avg Speed: " + avgSpeed);

	    // Tegn stolper for hastigheter
	    for (int i = 0; i < speeds.length; i++) {
	        // Skaler hastigheten slik at den passer til BARHEIGHT
	        int barHeight = (int) (speeds[i] * BARHEIGHT / maxSpeed);
	        System.out.println("Speed[" + i + "] = " + speeds[i] + ", BarHeight = " + barHeight);
	        drawLine(x, ybase, x, ybase - barHeight);  // Tegn linje/stolpe for hastigheten
	        x += 3;  // Flytt x-posisjonen
	    }

	    // Tegn gjennomsnittshastigheten som en grønn linje
	    setColor(0, 255, 0);  // Sett fargen til grønn

	    // Beregn riktig høyde for gjennomsnittshastigheten, skalert på samme måte som hastighetene
	    int avgLineHeight = (int) (avgSpeed * BARHEIGHT / maxSpeed);
	    System.out.println("Avg Line Height: " + avgLineHeight);

	    // Kontroller at gjennomsnittshastigheten ikke er for lav eller for høy
	    if (avgLineHeight > 0 && avgLineHeight <= BARHEIGHT) {
	        // Tegn linjen langs hele x-aksen på riktig høyde
	        drawLine(MARGIN, ybase - avgLineHeight, x, ybase - avgLineHeight);
	    } else {
	        System.out.println("Gjennomsnittslinjen er utenfor visningsområdet.");
	    }

	}

}
