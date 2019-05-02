import java.sql.Connection;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;

public class MatchVisualizer extends JFrame{
	private Connection con;
	private String[][] data;
	
	public MatchVisualizer(Connection con, String[][] data){
		this.con = con;
		this.data = data;
		JPanel content = new JPanel();
		String[] columnNames = {"Tournament Name", "Winner", "Loser", "Score"};
		JTable table = new JTable(data, columnNames);
		
	}
}
