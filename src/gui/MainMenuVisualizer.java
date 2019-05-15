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
		/*
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
		*/
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
		
		JButton getTournamentResultsButton = new JButton("Get Player Results By Tournament");
		ActionListener getTournamentResults = new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e) {
				TournamentPlacingVizualiser getResultsWindow = new TournamentPlacingVizualiser(con);
				getResultsWindow.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				getResultsWindow.setVisible(true);
			}
		};
		
		getTournamentResultsButton.addActionListener(getTournamentResults);
		getTournamentResultsButton.setFont(inputFont);
		getTournamentResultsButton.setAlignmentX(CENTER_ALIGNMENT);
		content.add(getTournamentResultsButton);
		
		JButton getTournamentMatchesButton = new JButton("Get Match Results By Tournament");
		ActionListener getTournamentMatches = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				TournamentMatchResultsVisualizer getTournamentWindow = new TournamentMatchResultsVisualizer(con);
				getTournamentWindow.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				getTournamentWindow.setVisible(true);
			}
		};
		
		getTournamentMatchesButton.addActionListener(getTournamentMatches);
		getTournamentMatchesButton.setFont(inputFont);
		getTournamentMatchesButton.setAlignmentX(CENTER_ALIGNMENT);
		content.add(getTournamentMatchesButton);

		
		JButton getPlayerResultsButton = new JButton("Get Tournament Results By Player");
		ActionListener getPlayerResults = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				PlayerTournamentResultsVisualizer getPlayerWindow = new PlayerTournamentResultsVisualizer(con);
				getPlayerWindow.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				getPlayerWindow.setVisible(true);
			}
		};
		
		getPlayerResultsButton.addActionListener(getPlayerResults);
		getPlayerResultsButton.setFont(inputFont);
		getPlayerResultsButton.setAlignmentX(CENTER_ALIGNMENT);
		content.add(getPlayerResultsButton);	

		
		JButton getPlayerMatchesButton = new JButton("Get Match Results By Player");
		ActionListener getPlayerMatches = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				PlayerMatchResultsVisualizer getPlayerWindow = new PlayerMatchResultsVisualizer(con);
				getPlayerWindow.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				getPlayerWindow.setVisible(true);
			}
		};
		
		getPlayerMatchesButton.addActionListener(getPlayerMatches);
		getPlayerMatchesButton.setFont(inputFont);
		getPlayerMatchesButton.setAlignmentX(CENTER_ALIGNMENT);
		content.add(getPlayerMatchesButton);	
		
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
