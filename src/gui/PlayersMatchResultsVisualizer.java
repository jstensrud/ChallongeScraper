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
public class PlayersMatchResultsVisualizer extends JFrame {

	public PlayersMatchResultsVisualizer(Connection con) {
		SprocCaller sprocCaller = new SprocCaller(con);

		setTitle("Get Results For Player");
		JPanel content = new JPanel();
		content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

		Font titleFont = new Font("Trebuchet MS", Font.BOLD, 40);
		Font inputFont = new Font("Trebuchet MS", Font.BOLD, 20);

		JLabel title = new JLabel("Get Matches For Players");
		title.setFont(titleFont);
		title.setAlignmentX(CENTER_ALIGNMENT);
		content.add(title);
		
		String[] tagList = sprocCaller.getPlayers();

		JComboBox<String> tagListA = new JComboBox<String>(tagList);
		tagListA.setFont(inputFont);
		tagListA.setAlignmentX(CENTER_ALIGNMENT);
		content.add(tagListA);

		JComboBox<String> tagListB = new JComboBox<String>(tagList);
		tagListB.setFont(inputFont);
		tagListB.setAlignmentX(CENTER_ALIGNMENT);
		content.add(tagListB);

		JButton getResultsForPlayerButton = new JButton("Get matches for Players");

		ActionListener getPlayerResults = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				SprocCaller sc = new SprocCaller(con);
				String[][] rankingResults = sc.getMatchesForPlayers((String) tagListA.getItemAt(tagListA.getSelectedIndex()), (String) tagListB.getItemAt(tagListB.getSelectedIndex()));
				MatchVisualizer mv = new MatchVisualizer(rankingResults);
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
