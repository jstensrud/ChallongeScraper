package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import character_data.CharacterWrapper;
import functions.SprocCaller;
import ranking_data.Ranking;
import ranking_data.RankingWrapper;

public class AddRankingVisualizer extends JFrame{

	private Connection con;
	private SprocCaller sproccaller;
	
	public AddRankingVisualizer(Connection con){
		this.con = con;
		this.sproccaller = new SprocCaller(this.con);
		
		
		setTitle("Add Ranking");
		JPanel content = new JPanel();
		content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

		Font inputFont = new Font("Trebuchet MS", Font.BOLD, 20);

		JComboBox gameList = new JComboBox(sproccaller.getGames());
		gameList.setFont(inputFont);
		gameList.setAlignmentX(CENTER_ALIGNMENT);
		content.add(gameList);
		
		JTextField organizationName = new JTextField("Organization Name");
		organizationName.setFont(inputFont);
		organizationName.setAlignmentX(CENTER_ALIGNMENT);
		content.add(organizationName);

		JTextField rankingPeriod = new JTextField("rankingPeriod");
		rankingPeriod.setFont(inputFont);
		rankingPeriod.setAlignmentX(CENTER_ALIGNMENT);
		content.add(rankingPeriod);
		
		JFileChooser fileSearch = new JFileChooser();
		FileNameExtensionFilter jsonFilter = new FileNameExtensionFilter("Json Files only", "json");
		fileSearch.setFileFilter(jsonFilter);
		int returnVal = fileSearch.showOpenDialog(null);
		final File rankFile;
		if(returnVal == JFileChooser.APPROVE_OPTION){
			rankFile = fileSearch.getSelectedFile();
		}else{
			rankFile = null;
		}
		
		JButton addRankingButton = new JButton("Add Ranking");
		
		ActionListener addRanking = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent a){
				boolean result = false;
				if(rankFile != null){
					String period = rankingPeriod.getText();
					String game = (String) gameList.getItemAt(gameList.getSelectedIndex());
					String orgName = organizationName.getText();
					sproccaller.addRank(period, game, orgName);
					ObjectMapper om = new ObjectMapper();
					RankingWrapper[] ranks;
					try {
						ranks = om.readValue(rankFile, RankingWrapper[].class);
						for(int i = 0; i < ranks.length; i++){
							Ranking r = ranks[i].getRanking();
							result = sproccaller.addRanking(r.tag, period, game, orgName, r.rank);
						}
					} catch (IOException e) {
						e.printStackTrace();
					}		
				}
			}
		};
		addRankingButton.addActionListener(addRanking);
		addRankingButton.setFont(inputFont);
		addRankingButton.setAlignmentX(CENTER_ALIGNMENT);
		content.add(addRankingButton);
		
		add(content);
		pack();
	}
}
