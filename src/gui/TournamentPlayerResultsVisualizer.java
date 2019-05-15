package gui;

import java.sql.Connection;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class TournamentPlayerResultsVisualizer extends JFrame{
	private Connection con;
	private String[][] data;
	
	public TournamentPlayerResultsVisualizer(Connection con, String[][] data){
		this.con = con;
		this.data = data;
		JPanel content = new JPanel();
		String[] columnNames = {"Tournament Name", "Seed", "Placing", "Tag"};
		JTable table = new JTable(this.data, columnNames);
		JScrollPane scrollPane = new JScrollPane(table);
		table.setFillsViewportHeight(true);
		content.add(scrollPane);
		add(content);
		pack();
	}
}
