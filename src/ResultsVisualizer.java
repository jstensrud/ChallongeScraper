import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * ResultsVisualizer:
 * 
 * A visual window (JFrame) that displays the results of a provided tournament's match list and saves it to the user's clipboard.
 * 
 * 
 */
public class ResultsVisualizer extends JFrame{
	private Connection con;
	
	/**
	 * Default constructor
	 * @param con 
	 * 
	 * @param matchList: The tournament's list of matches
	 */
	public ResultsVisualizer(OutputWrapper output, Connection con){
		this.con = con;
		
		//Set up the window and the content that will appear on it
		setTitle("Tournament Results");
		JPanel content = new JPanel();
		content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

		Font titleFont = new Font("Trebuchet MS", Font.BOLD, 20);
		Font inputFont = new Font("Trebuchet MS", Font.BOLD, 12);
		JScrollPane jsp = new JScrollPane(content);
		jsp.setViewportView(content);
		
		JLabel title = new JLabel("Tournament Results:");
		title.setFont(titleFont);
		title.setAlignmentX(LEFT_ALIGNMENT);
		content.add(title);
		
		JLabel tournamentIDLabel = new JLabel("Tournament ID: " + output.t.id);
		tournamentIDLabel.setFont(inputFont);
		tournamentIDLabel.setAlignmentX(LEFT_ALIGNMENT);
		content.add(tournamentIDLabel);
		JLabel tournamentNameLabel = new JLabel("Tournament Name: " + output.t.name);
		tournamentNameLabel.setFont(inputFont);
		tournamentNameLabel.setAlignmentX(LEFT_ALIGNMENT);
		content.add(tournamentNameLabel);
		JLabel tournamentGameLabel = new JLabel("Tournament Game: " + output.t.game);
		tournamentGameLabel.setFont(inputFont);
		tournamentGameLabel.setAlignmentX(LEFT_ALIGNMENT);
		content.add(tournamentGameLabel);
		JLabel tournamentOrgLabel = new JLabel("Tournament Organization: " + output.t.organization);
		tournamentOrgLabel.setFont(inputFont);
		tournamentOrgLabel.setAlignmentX(LEFT_ALIGNMENT);
		content.add(tournamentOrgLabel);
		JLabel tournamentDateLabel = new JLabel("Tournament Date: " + output.t.date);
		tournamentDateLabel.setFont(inputFont);
		tournamentDateLabel.setAlignmentX(LEFT_ALIGNMENT);
		content.add(tournamentDateLabel);
		
		this.insertTournament(output.t.id, output.t.game, output.t.organization, output.t.date, output.t.name);
		
		for(Participant p : output.participants.values()){
			//Set up a label for each participant
			JLabel participantTag = new JLabel("Tag: " + p.tag);
			participantTag.setFont(inputFont);
			participantTag.setAlignmentX(LEFT_ALIGNMENT);
			content.add(participantTag);			
			JLabel participantCrew = new JLabel("Crew: " + p.crew);
			participantCrew.setFont(inputFont);
			participantCrew.setAlignmentX(LEFT_ALIGNMENT);
			content.add(participantCrew);			
			JLabel participantSeed = new JLabel("Seed: " + p.seed);
			participantSeed.setFont(inputFont);
			participantSeed.setAlignmentX(LEFT_ALIGNMENT);
			content.add(participantSeed);
			JLabel participantPlacing = new JLabel("Placing: " + p.placing);
			participantPlacing.setFont(inputFont);
			participantPlacing.setAlignmentX(LEFT_ALIGNMENT);
			content.add(participantPlacing);
			
			this.insertParticipantPlacing(p.tag, p.crew, output.t.id, p.seed, p.placing);
		}
		
		for(int i = 0; i < output.matches.size(); i++){
			Match m = output.matches.get(i);
			JLabel matchID = new JLabel("Match ID: " + m.id);
			matchID.setFont(inputFont);
			matchID.setAlignmentX(LEFT_ALIGNMENT);
			content.add(matchID);
			JLabel matchWinnerTag = new JLabel("Winner: " + m.winnerTag);
			matchWinnerTag.setFont(inputFont);
			matchWinnerTag.setAlignmentX(LEFT_ALIGNMENT);
			content.add(matchWinnerTag);
			JLabel matchLoserTag = new JLabel("Loser: " + m.loserTag);
			matchLoserTag.setFont(inputFont);
			matchLoserTag.setAlignmentX(LEFT_ALIGNMENT);
			content.add(matchLoserTag);
			JLabel matchScore = new JLabel("Score: " + m.score);
			matchScore.setFont(inputFont);
			matchScore.setAlignmentX(LEFT_ALIGNMENT);
			content.add(matchScore);
			
			this.insertMatch(m.id, output.t.id, m.winnerTag, m.loserTag, m.score);
		}
		
		JButton closeButton = new JButton("OK");
		
		ActionListener closeWindow = new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent arg0){
				ResultsVisualizer.super.dispose();
			}
		};
		closeButton.addActionListener(closeWindow);
		closeButton.setFont(inputFont);
		closeButton.setAlignmentX(LEFT_ALIGNMENT);
		content.add(closeButton);
		add(jsp);
		pack();
	}

	private void insertTournament(Integer id, String game, String organization, String date, String name) {
		// TODO  use con to CALL [create_tournament]
	}

	private void insertParticipantPlacing(String tag, String crew, Integer id, Integer seed, Integer placing) {
		// TODO use con to CALL [insert_tournament_placing]	
	}

	private void insertMatch(Integer id, Integer id2, String winnerTag, String loserTag, String score) {
		// TODO use con to CALL [insert_match_details]
	}
}
