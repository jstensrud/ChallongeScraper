import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * ResultsVisualizer:
 * 
 * A visual window (JFrame) that displays the results of a provided tournament's match list and saves it to the user's clipboard.
 * 
 * 
 */
public class ResultsVisualizer extends JFrame{
	
	/**
	 * Default constructor
	 * 
	 * @param matchList: The tournament's list of matches
	 */
	public ResultsVisualizer(OutputWrapper output){
		//Set up the window and the content that will appear on it
		setTitle("Tournament Results");
		JPanel content = new JPanel();
		content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

		Font titleFont = new Font("Comic Sans MS", Font.BOLD, 20);
		Font inputFont = new Font("Comic Sans MS", Font.BOLD, 12);
	
		
		JLabel title = new JLabel("Tournament Results:");
		title.setFont(titleFont);
		title.setAlignmentX(CENTER_ALIGNMENT);
		content.add(title);
		
		JLabel tournamentIDLabel = new JLabel("Tournament ID: " + output.t.id);
		tournamentIDLabel.setFont(inputFont);
		tournamentIDLabel.setAlignmentX(CENTER_ALIGNMENT);
		content.add(tournamentIDLabel);
		JLabel tournamentNameLabel = new JLabel("Tournament Name: " + output.t.name);
		tournamentNameLabel.setFont(inputFont);
		tournamentNameLabel.setAlignmentX(CENTER_ALIGNMENT);
		content.add(tournamentNameLabel);
		JLabel tournamentGameLabel = new JLabel("Tournament Game: " + output.t.game);
		tournamentGameLabel.setFont(inputFont);
		tournamentGameLabel.setAlignmentX(CENTER_ALIGNMENT);
		content.add(tournamentGameLabel);
		JLabel tournamentOrgLabel = new JLabel("Tournament Organization: " + output.t.organization);
		tournamentOrgLabel.setFont(inputFont);
		tournamentOrgLabel.setAlignmentX(CENTER_ALIGNMENT);
		content.add(tournamentOrgLabel);
		JLabel tournamentDateLabel = new JLabel("Tournament Date: " + output.t.date);
		tournamentDateLabel.setFont(inputFont);
		tournamentDateLabel.setAlignmentX(CENTER_ALIGNMENT);
		content.add(tournamentDateLabel);
		
		for(Participant p : output.participants.values()){
			//Set up a label for each participant
			JLabel participantTag = new JLabel("Tag: " + p.tag);
			participantTag.setFont(inputFont);
			participantTag.setAlignmentX(CENTER_ALIGNMENT);
			content.add(participantTag);			
			JLabel participantCrew = new JLabel("Crew: " + p.crew);
			participantCrew.setFont(inputFont);
			participantCrew.setAlignmentX(CENTER_ALIGNMENT);
			content.add(participantCrew);			
			JLabel participantSeed = new JLabel("Seed: " + p.seed);
			participantSeed.setFont(inputFont);
			participantSeed.setAlignmentX(CENTER_ALIGNMENT);
			content.add(participantSeed);
			JLabel participantPlacing = new JLabel("Placing: " + p.placing);
			participantPlacing.setFont(inputFont);
			participantPlacing.setAlignmentX(CENTER_ALIGNMENT);
			content.add(participantPlacing);
		}
		
		for(int i = 0; i < output.matches.size(); i++){
			Match m = output.matches.get(i);
			JLabel matchID = new JLabel("Match ID: " + m.id);
			matchID.setFont(inputFont);
			matchID.setAlignmentX(CENTER_ALIGNMENT);
			content.add(matchID);
			JLabel matchWinnerTag = new JLabel("Match ID: " + m.winnerTag);
			matchWinnerTag.setFont(inputFont);
			matchWinnerTag.setAlignmentX(CENTER_ALIGNMENT);
			content.add(matchWinnerTag);
			JLabel matchLoserTag = new JLabel("Match ID: " + m.loserTag);
			matchLoserTag.setFont(inputFont);
			matchLoserTag.setAlignmentX(CENTER_ALIGNMENT);
			content.add(matchLoserTag);
			JLabel matchScore = new JLabel("Score: " + m.score);
			matchScore.setFont(inputFont);
			matchScore.setAlignmentX(CENTER_ALIGNMENT);
			content.add(matchScore);
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
		closeButton.setAlignmentX(CENTER_ALIGNMENT);
		content.add(closeButton);
		add(content);
		pack();

	}
}
