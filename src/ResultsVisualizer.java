import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
	public ResultsVisualizer(ArrayList<String> matchList){
		//Set up the window and the content that will appear on it
		setTitle("Tournament Results");
		JPanel content = new JPanel();
		content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

		Font titleFont = new Font("Comic Sans MS", Font.BOLD, 30);
		Font inputFont = new Font("Comic Sans MS", Font.BOLD, 20);
	
		
		JLabel title = new JLabel("Tournament Results successfully saved to Clipboard");
		title.setFont(titleFont);
		title.setAlignmentX(CENTER_ALIGNMENT);
		content.add(title);
		
		//Holds the total results that will be eventually saved to the user's clipboard
		String fullResults = "";
		
		//For each match in the list of the match:
		for(int i = 0; i < matchList.size(); i++){
			//Set up a label for each match
			JLabel match = new JLabel(matchList.get(i));
			match.setFont(inputFont);
			match.setAlignmentX(CENTER_ALIGNMENT);
			content.add(match);
			//Add the match data and a line break to the full results string
			fullResults = fullResults + matchList.get(i) + System.getProperty("line.separator");
		}
		
		//Copy the total results string to the user's clipboard
		StringSelection selection = new StringSelection(fullResults);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(selection, selection);
		
		
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
