package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import functions.SprocCaller;

public class PlayerMatchResultsVisualizer extends JFrame {

	private Connection con;

	public PlayerMatchResultsVisualizer(Connection con) {
		this.con = con;

		setTitle("Get Results For Player");
		JPanel content = new JPanel();
		content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

		Font titleFont = new Font("Trebuchet MS", Font.BOLD, 40);
		Font inputFont = new Font("Trebuchet MS", Font.BOLD, 20);

		JLabel title = new JLabel("Get Matches For Player");
		title.setFont(titleFont);
		title.setAlignmentX(CENTER_ALIGNMENT);
		content.add(title);

		JTextField playerTag = new JTextField();
		playerTag.setFont(inputFont);
		playerTag.setAlignmentX(CENTER_ALIGNMENT);
		content.add(playerTag);

		JButton getResultsForPlayerButton = new JButton("Get matches for Player");

		ActionListener getPlayerResults = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				SprocCaller sc = new SprocCaller(con);
				String[][] rankingResults = sc.getMatchesForPlayer(playerTag.getText());
				PlacingVizualizer mv = new PlacingVizualizer(con, rankingResults);
				mv.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				mv.setVisible(true);
			}
		};

		getResultsForPlayerButton.addActionListener(getPlayerResults);
		getResultsForPlayerButton.setFont(inputFont);
		getResultsForPlayerButton.setAlignmentX(CENTER_ALIGNMENT);
		content.add(getResultsForPlayerButton);

		add(content);
		pack();
	}

}
