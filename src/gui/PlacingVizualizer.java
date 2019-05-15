package gui;

import java.awt.ScrollPane;
import java.sql.Connection;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class PlacingVizualizer extends JFrame {

	public Connection con;
	public String[][] data;
	public PlacingVizualizer(Connection con, String[][] rankingResults, String playerTag) {
		this.con = con;
		this.data = rankingResults;
		setTitle("Tournament Placings for " + playerTag);
		JPanel content = new JPanel();
		String[] columnNames = {"Tournament", "Seed", "Placing"};
		JTable table = new JTable(this.data, columnNames);
		JScrollPane scrollPane = new JScrollPane(table);
		table.setFillsViewportHeight(true);
		content.add(scrollPane);
		add(content);
		pack();
	}

}
