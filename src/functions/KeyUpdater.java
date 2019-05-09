package functions;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.prefs.Preferences;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import gui.PopUpVisualizer;

/**
 * KeyUpdater
 * 
 * A visual window (JFrame) that allows the user to set/update their API Key.
 * 
 * Preferences prefs: Holds the user's API Key.
 */
public class KeyUpdater extends JFrame{
	
	private Preferences prefs;
	
	/**
	 * Default constructor.
	 * 
	 * @param p: Preferences that hold the API Key, stored in Visualizer.
	 */
	public KeyUpdater(Preferences p){
		
		this.prefs = p;
		
		//Set up the window and the content that will appear on it.
		setTitle("Update API Key");
		JPanel content = new JPanel();
		content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

		Font f = new Font("Comic Sans MS", Font.BOLD, 20);

		JLabel title = new JLabel("Enter API Key");
		title.setFont(f);
		title.setAlignmentX(CENTER_ALIGNMENT);
		content.add(title);
		
		JLabel subtitle = new JLabel("Your API Key can be found at https://challonge.com/settings/developer");
		subtitle.setFont(f);
		subtitle.setAlignmentX(CENTER_ALIGNMENT);
		content.add(subtitle);
		
		
		JTextField apiKey = new JTextField();
		apiKey.setFont(f);
		apiKey.setAlignmentX(CENTER_ALIGNMENT);
		content.add(apiKey);
		
		JButton submitButton = new JButton("Submit");
		
		ActionListener updateKey = new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent arg0){
				//Makes sure an entry was put in the text box
				if(apiKey.getText().isEmpty()){
					//If not, create a pop-up prompting the user to input something.
					PopUpVisualizer e = new PopUpVisualizer("Error", "Please enter an API Key.");
					e.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					e.setVisible(true);
					return;						

				}
				//Update the key and close the key updater window.
				updateKey(apiKey.getText());
				KeyUpdater.super.dispose();
			}
		};
		submitButton.addActionListener(updateKey);
		
		submitButton.setFont(f);
		submitButton.setAlignmentX(CENTER_ALIGNMENT);
		content.add(submitButton);
		
		add(content);
		pack();
	}
	
	public void updateKey(String key){
		//Update the preferences
		this.prefs.put("apiKey", key);
		
		//Create a pop-up stating that the key was changed, showing them the new key.
		PopUpVisualizer e = new PopUpVisualizer("Success", 
											"Preferences updated. New api Key: " + this.prefs.get("apiKey", ""));
		e.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		e.setVisible(true);
		return;						

	}
	

}
