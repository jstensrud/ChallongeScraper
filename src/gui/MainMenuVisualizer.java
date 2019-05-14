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

public class MainMenuVisualizer extends JFrame{
	private Connection con;
	
	public MainMenuVisualizer(Connection con){
		this.con = con;
		
		setTitle("Terre Haute Smash Database");
		JPanel content = new JPanel();
		content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
		
		Font titleFont = new Font("Trebuchet MS", Font.BOLD, 40);
		Font subtitleFont = new Font("Trebuchet MS", Font.BOLD, 30);
		Font inputFont = new Font("Trebuchet MS", Font.BOLD, 20);
		Font creditsFont = new Font("Trebuchet MS", Font.BOLD, 10);

		JLabel title = new JLabel("Terre Haute Smash Database");
		title.setFont(titleFont);
		title.setAlignmentX(CENTER_ALIGNMENT);
		content.add(title);
		
		JButton instructionsButton = new JButton("Instructions");
		ActionListener instructions = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				PopUpVisualizer instructionsWindow = new PopUpVisualizer("Instructions", "Instructions coming soon");
				instructionsWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				instructionsWindow.setVisible(true);
				return;
			}
		};
		
		instructionsButton.addActionListener(instructions);
		instructionsButton.setFont(inputFont);
		instructionsButton.setAlignmentX(CENTER_ALIGNMENT);
		content.add(instructionsButton);
		
		JButton addTournamentButton = new JButton("Add Tournament to Database");
		ActionListener addTournament = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				AddTournamentVisualizer addTournamentWindow = new AddTournamentVisualizer(con);
				addTournamentWindow.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				addTournamentWindow.setVisible(true);
			}			
		};
		
		addTournamentButton.addActionListener(addTournament);
		addTournamentButton.setFont(inputFont);
		addTournamentButton.setAlignmentX(CENTER_ALIGNMENT);
		content.add(addTournamentButton);		
		
		JButton getTournamentButton = new JButton("Get Tournament Results");
		
		ActionListener getTournament = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				TournamentResultsVisualizer getTournamentWindow = new TournamentResultsVisualizer(con);
				getTournamentWindow.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				getTournamentWindow.setVisible(true);
			}			
		};
		
		getTournamentButton.addActionListener(getTournament);
		getTournamentButton.setFont(inputFont);
		getTournamentButton.setAlignmentX(CENTER_ALIGNMENT);
		content.add(getTournamentButton);	
		
		JButton playerInfoButton = new JButton("Get/Update Player Info");
		
		ActionListener playerInfo = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				PlayerVisualizer playerWindow = new PlayerVisualizer(con);
				playerWindow.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				playerWindow.setVisible(true);
			}			
		};
		
		playerInfoButton.addActionListener(playerInfo);
		playerInfoButton.setFont(inputFont);
		playerInfoButton.setAlignmentX(CENTER_ALIGNMENT);
		content.add(playerInfoButton);	
		
		
		//put this last
		JLabel credits = new JLabel("Terre Haute Smash Database: Created by Jaxon Hoffman, Jack Stensrud, and Sarthak Suri");
		credits.setFont(creditsFont);
		credits.setAlignmentX(CENTER_ALIGNMENT);
		content.add(credits);

		add(content);
		pack();
	}
}
