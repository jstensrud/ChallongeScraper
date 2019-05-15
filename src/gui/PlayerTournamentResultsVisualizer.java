package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import functions.SprocCaller;

@SuppressWarnings("serial")
public class PlayerTournamentResultsVisualizer extends JFrame {

	public PlayerTournamentResultsVisualizer(Connection con) {
		SprocCaller sprocCaller = new SprocCaller(con);

		setTitle("Get Results For Player");
		JPanel content = new JPanel();
		content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

		Font titleFont = new Font("Trebuchet MS", Font.BOLD, 40);
		Font inputFont = new Font("Trebuchet MS", Font.BOLD, 20);

		JLabel title = new JLabel("Get Results For Player");
		title.setFont(titleFont);
		title.setAlignmentX(CENTER_ALIGNMENT);
		content.add(title);

		JComboBox<String> tagList = new JComboBox<String>(sprocCaller.getPlayers());
		tagList.setFont(inputFont);
		tagList.setAlignmentX(CENTER_ALIGNMENT);
		content.add(tagList);


		JButton getResultsForPlayerButton = new JButton("Get results for Player");

		ActionListener getPlayerResults = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				SprocCaller sc = new SprocCaller(con);
				String[][] rankingResults = sc.getRankingsForPlayer((String) tagList.getItemAt(tagList.getSelectedIndex()));
				PlayerPlacingVizualizer mv = new PlayerPlacingVizualizer(rankingResults, (String) tagList.getItemAt(tagList.getSelectedIndex()));
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
