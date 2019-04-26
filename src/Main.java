import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
 * Version 0.3.1
 * 
 * Uses Jackson for JSON Deserialization. Jackson is created by FasterXML (github.com/FasterXML).
 * ASCII Art Title created with a Text to ASCII art generator from partojk.com
 * 
 */
public class Main {
	public static void main(String[] args) {
		DatabaseConnectionService connector = new DatabaseConnectionService("golem.csse.rose-hulman.edu", "_SmashDB");
		boolean connected = connector.connect("scraper", "secretKey");

		Connection con = null;
		if (!connected)
			JOptionPane.showMessageDialog(null, "Failed to connect to database. All actions will be local only.");
		else
			con = connector.getConnection();

		Visualizer v = new Visualizer(con);

		v.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		v.setVisible(true);
	}

}
