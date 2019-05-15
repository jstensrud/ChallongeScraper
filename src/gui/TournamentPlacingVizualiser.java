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
import javax.swing.JTextField;

import functions.SprocCaller;

public class TournamentPlacingVizualiser extends JFrame {

	private Connection con;

	public TournamentPlacingVizualiser(Connection con) {
		this.con = con;
		SprocCaller sproccaller = new SprocCaller(con);

		setTitle("Get Results For Tournament");
		JPanel content = new JPanel();
		content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

		Font titleFont = new Font("Trebuchet MS", Font.BOLD, 40);
		Font inputFont = new Font("Trebuchet MS", Font.BOLD, 20);

		JLabel title = new JLabel("Get Results For Tournament");
		title.setFont(titleFont);
		title.setAlignmentX(CENTER_ALIGNMENT);
		content.add(title);

		String[] tournaments = sproccaller.getTournaments();
		JComboBox tournamentList = new JComboBox(tournaments);
		tournamentList.setFont(inputFont);
		tournamentList.setAlignmentX(CENTER_ALIGNMENT);
		content.add(tournamentList);

		JButton getResultsForTournamentButton = new JButton("Get results for Tournament");

		ActionListener getTourneyResults = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				SprocCaller sc = new SprocCaller(con);
				int tourneyID = sc.getTournamentIDFromName((String) tournamentList.getItemAt(tournamentList.getSelectedIndex()));
				String[][] rankingResults = sc.getRankingsForTournament(tourneyID);
				TournamentPlayerResultsVisualizer mv = new TournamentPlayerResultsVisualizer(con, rankingResults);
				mv.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				mv.setVisible(true);
			}
		};

		getResultsForTournamentButton.addActionListener(getTourneyResults);
		getResultsForTournamentButton.setFont(inputFont);
		getResultsForTournamentButton.setAlignmentX(CENTER_ALIGNMENT);
		content.add(getResultsForTournamentButton);

		add(content);
		pack();
	}
}
