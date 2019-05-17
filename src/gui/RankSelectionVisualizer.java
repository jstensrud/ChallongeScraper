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

public class RankSelectionVisualizer extends JFrame{
	
	
	public RankSelectionVisualizer(Connection con){	
		setTitle("View Power Rankings");
		JPanel content = new JPanel();
		content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
		
		Font titleFont = new Font("Trebuchet MS", Font.BOLD, 40);
		Font inputFont = new Font("Trebuchet MS", Font.BOLD, 20);
		
		JLabel title = new JLabel("View Power Rankings");
		title.setFont(titleFont);
		title.setAlignmentX(CENTER_ALIGNMENT);
		content.add(title);

		SprocCaller sproccaller = new SprocCaller(con);
		
		
		String[][] rankingsList = sproccaller.getRankings();
		JComboBox<String> tournamentList = new JComboBox<String>();
		for(int i = 0; i < rankingsList.length; i++){
			tournamentList.insertItemAt(rankingsList[i][0] + " - " + rankingsList[i][1] + " - " + rankingsList[i][2], i);
		}
		tournamentList.setFont(inputFont);
		tournamentList.setAlignmentX(CENTER_ALIGNMENT);
		content.add(tournamentList);
		
		JButton getResultsFromTournamentButton = new JButton("Get results from Tournament");
	
		ActionListener getTourneyResults = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0){
				SprocCaller sc = new SprocCaller(con);
				String org = rankingsList[tournamentList.getSelectedIndex()][0];
				String game = rankingsList[tournamentList.getSelectedIndex()][1];
				String period = rankingsList[tournamentList.getSelectedIndex()][2];
				String[][] ranks = sc.getRankings(org, game, period);
				RankVisualizer rv = new RankVisualizer(ranks);
				rv.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				rv.setVisible(true);
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
