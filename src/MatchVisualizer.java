import java.awt.Dimension;
import java.sql.Connection;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class MatchVisualizer extends JFrame{
	private Connection con;
	private String[][] data;
	
	public MatchVisualizer(Connection con, String[][] data){
		this.con = con;
		this.data = data;
		JPanel content = new JPanel();
		String[] columnNames = {"Winner", "Loser", "Score"};
		JTable table = new JTable(data, columnNames);
		JScrollPane scrollPane = new JScrollPane(table);
		table.setFillsViewportHeight(true);
//		content.add(table);
		content.add(scrollPane);
		add(content);
		pack();
	}
}
