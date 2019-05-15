package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

@SuppressWarnings("serial")
public class PlayerPlacingVizualizer extends JFrame {

	public String[][] data;
	public PlayerPlacingVizualizer(String[][] rankingResults, String playerTag) {
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
