import javax.swing.JFrame;

/**
 * 
   _____ _           _ _                             _____                                
  / ____| |         | | |                           / ____|                               
 | |    | |__   __ _| | | ___  _ __   __ _  ___    | (___   ___ _ __ __ _ _ __   ___ _ __ 
 | |    | '_ \ / _` | | |/ _ \| '_ \ / _` |/ _ \    \___ \ / __| '__/ _` | '_ \ / _ \ '__|
 | |____| | | | (_| | | | (_) | | | | (_| |  __/    ____) | (__| | | (_| | |_) |  __/ |   
  \_____|_| |_|\__,_|_|_|\___/|_| |_|\__, |\___|   |_____/ \___|_|  \__,_| .__/ \___|_|   
                                      __/ |                              | |              
                                     |___/                               |_|             
 *
 * Version 0.2. Created by Jack Stensrud (github.com/jstensrud).
 * 
 * Allows Tournament Organizers to enter the Tournament ID of one of their Challonge-hosted tournaments and 
 * display it in a more readable format.
 * 
 * 
 * Uses Jackson for JSON Deserialization. Jackson is created by FasterXML (github.com/FasterXML).
 * ASCII Art Title created with a Text to ASCII art generator from partojk.com
 * 
 */
public class Main {

	public static void main(String[] args) {
		Visualizer v = new Visualizer();
		v.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		v.setVisible(true);
	}

}
