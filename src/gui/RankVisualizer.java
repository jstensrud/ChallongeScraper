package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class RankVisualizer extends JFrame{

	public RankVisualizer(String[][] data){
		setTitle("Power Rankings");
		JPanel content = new JPanel();
		String[] columnNames = {"Player Tag", "Rank"};
		JTable table = new JTable(data, columnNames);
		JScrollPane scrollPane = new JScrollPane(table);
		table.setFillsViewportHeight(true);
		content.add(scrollPane);
		add(content);
		pack();

	}
}
