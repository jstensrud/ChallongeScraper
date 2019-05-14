package gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import functions.SprocCaller;

public class PlayerVisualizer extends JFrame{
	private Connection con;
	
	
	public PlayerVisualizer(Connection con){
		this.con = con;
		SprocCaller sprocCaller = new SprocCaller(con);
		
		setTitle("Update Player Information");
		
		JPanel content = new JPanel();
		content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
		
		Font titleFont = new Font("Trebuchet MS", Font.BOLD, 40);
		Font subtitleFont = new Font("Trebuchet MS", Font.BOLD, 30);
		Font inputFont = new Font("Trebuchet MS", Font.BOLD, 20);
		
		JLabel title = new JLabel("Update Player Information");
		title.setFont(titleFont);
		title.setAlignmentX(CENTER_ALIGNMENT);
		content.add(title);

		JLabel tagDescription = new JLabel("Your tag: ");
		tagDescription.setFont(subtitleFont);
		tagDescription.setAlignmentX(CENTER_ALIGNMENT);
		content.add(tagDescription);
		
		JComboBox tagList = new JComboBox(sprocCaller.getPlayers());
		tagList.setFont(inputFont);
		tagList.setAlignmentX(CENTER_ALIGNMENT);
		content.add(tagList);

		JComboBox gameList = new JComboBox(sprocCaller.getGames());
		gameList.setFont(inputFont);
		gameList.setAlignmentX(CENTER_ALIGNMENT);
		content.add(gameList);

		JComboBox charList = new JComboBox(sprocCaller.getChars((String) gameList.getItemAt(gameList.getSelectedIndex())));
		charList.setFont(inputFont);
		charList.setAlignmentX(CENTER_ALIGNMENT);
		content.add(charList);
		
		String game = (String) gameList.getItemAt(gameList.getSelectedIndex());
		String character =  (String) charList.getItemAt(charList.getSelectedIndex());
		String[] newChars = sprocCaller.getSkins(game,character);
		JComboBox skinList = new JComboBox(newChars);
		skinList.setFont(inputFont);
		skinList.setAlignmentX(CENTER_ALIGNMENT);
		content.add(skinList);

		gameList.addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent e) {
				JComboBox box = (JComboBox) e.getSource();
				charList.removeAllItems();
				String[] newChars = sprocCaller.getChars((String) gameList.getItemAt(gameList.getSelectedIndex()));
				for(int i = 0; i < newChars.length; i++){
					charList.addItem(newChars[i]);
				}
			}
		});

		charList.addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent e) {
				JComboBox box = (JComboBox) e.getSource();
				skinList.removeAllItems();
				String game = (String) gameList.getItemAt(gameList.getSelectedIndex());
				String character =  (String) charList.getItemAt(charList.getSelectedIndex());
				String[] newChars = sprocCaller.getSkins(game,character);
				for(int i = 0; i < newChars.length; i++){
					skinList.addItem(newChars[i]);
				}
				
			}
		});
		
		JLabel infoTitle = new JLabel("In order to see updates to your information, close and reopen this page.");
		infoTitle.setFont(inputFont);
		infoTitle.setAlignmentX(CENTER_ALIGNMENT);
		content.add(infoTitle);
		
		JButton addMainButton = new JButton("Add new main");
		
		ActionListener addMain = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				String tag = (String) tagList.getItemAt(tagList.getSelectedIndex());
				String name = (String) charList.getItemAt(charList.getSelectedIndex());
				String game = (String) gameList.getItemAt(gameList.getSelectedIndex());
				String color = (String) skinList.getItemAt(skinList.getSelectedIndex());
				sprocCaller.addMains(tag, name, game, color);
			}
			
		};
		addMainButton.addActionListener(addMain);
		addMainButton.setFont(inputFont);
		addMainButton.setAlignmentX(CENTER_ALIGNMENT);
		content.add(addMainButton);
		
		add(content);
		pack();
	}
}
