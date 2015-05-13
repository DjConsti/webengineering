package client;

import java.awt.*;
import javax.swing.*;

/**
 * Name: MyFrame
 *
 * Beschreibung: In der Klasse MyFrame werden die Grundlegend Dinge fuer Frame erzeugt:
 * 				 Titel, Visibel, Groeße, Default Close Operation, Layout(BorderLayout)
 *
 * @author Pfeiffer-Vogl, Konyuncu
 * @version 1.0
 */
public class MyFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    // Neues Objekt von MyPanel wird erzeugt, in diesem Befinden sich alle
    // Komponeten fuer die Oberflaeche
    MyPanel calc = new MyPanel(this);

    public MyFrame() {
        // Titel der Applikation wird gesetzt
        this.setTitle("SOA Client - by Koyuncu & Pfeiffer-Vogl");
        // Hintergrundfarbe setzen
        // this.setBackground(Color.BLUE);
        // Schließe Anwendung, Falls der Frame geschlossen wurde
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Panel wird in den Container gesetzt
        this.getContentPane().add(BorderLayout.CENTER, calc);
        // Stellt die Fenstergroeße passend ein
        this.pack();
        // Die Applikation wird auf sichtbar gesetzt
        this.setVisible(true);
        // Die Groeße des Fensters wird gesetzt
        this.setSize(500, 500);
        // Position der Applikation wird gesetzt
        this.setLocation(100, 50);
        // Fenster kann nicht mehr vergroessert bzw. verkleinert werden
        this.setResizable(false);
    }
}
