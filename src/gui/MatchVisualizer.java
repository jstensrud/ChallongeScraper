package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

@SuppressWarnings("serial")
public class MatchVisualizer extends JFrame{
	private String[][] data;
	
	public MatchVisualizer(String[][] data){
		this.data = data;
		JPanel content = new JPanel();
		String[] columnNames = {"Winner", "Loser", "Score"};
		JTable table = new JTable(this.data, columnNames);
		JScrollPane scrollPane = new JScrollPane(table);
		table.setFillsViewportHeight(true);
		content.add(scrollPane);
		add(content);
		pack();
	}
}
