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

public class TournamentResultsVisualizer extends JFrame{

	private Connection con;
	
	public TournamentResultsVisualizer(Connection con){
		this.con = con;
		
		setTitle("Get Results From Tournament");
		JPanel content = new JPanel();
		content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
		
		Font titleFont = new Font("Trebuchet MS", Font.BOLD, 40);
		Font inputFont = new Font("Trebuchet MS", Font.BOLD, 20);
		
		JLabel title = new JLabel("Get Results For Tournament");
		title.setFont(titleFont);
		title.setAlignmentX(CENTER_ALIGNMENT);
		content.add(title);

		JTextField tournamentName = new JTextField();
		tournamentName.setFont(inputFont);
		tournamentName.setAlignmentX(CENTER_ALIGNMENT);
		content.add(tournamentName);

		JButton getResultsFromTournamentButton = new JButton("Get results from Tournament");
	
		ActionListener getTourneyResults = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0){
				SprocCaller sc = new SprocCaller(con);
				int tourneyID = sc.getTournamentIDFromName(tournamentName.getText());
				String[][] matchResults = sc.getMatchesForTournament(tourneyID);
				MatchVisualizer mv = new MatchVisualizer(con, matchResults);
				mv.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				mv.setVisible(true);
			}
		};
	
		getResultsFromTournamentButton.addActionListener(getTourneyResults);
		getResultsFromTournamentButton.setFont(inputFont);
		getResultsFromTournamentButton.setAlignmentX(CENTER_ALIGNMENT);
		content.add(getResultsFromTournamentButton);
		
		add(content);
		pack();
	}
}
