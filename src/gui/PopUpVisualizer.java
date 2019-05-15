package gui;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * PopUpVisualizer
 * 
 * A visual window (JFrame) that displays a pop-up message. No interaction from the user is required other than closing the window.
 * 
 */
@SuppressWarnings("serial")
public class PopUpVisualizer extends JFrame{
	
	/**
	 * Default constructor
	 * 
	 * @param title: The title of the window
	 * @param messageBody: The body of the message
	 */
	public PopUpVisualizer(String title, String messageBody){
		
		//Set up the window to display the content provided in the constructor
		setTitle(title);
		JPanel content = new JPanel();
		content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

		Font f = new Font("Trebuchet MS", Font.BOLD, 20);
		
		JLabel errorDescription = new JLabel(messageBody);
		errorDescription.setFont(f);
		errorDescription.setAlignmentX(CENTER_ALIGNMENT);
		content.add(errorDescription);
		
		JButton closeButton = new JButton("OK");
		
		ActionListener closeWindow = new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent arg0){
				PopUpVisualizer.super.dispose();
			}
		};
		closeButton.addActionListener(closeWindow);
		closeButton.setFont(f);
		closeButton.setAlignmentX(CENTER_ALIGNMENT);
		content.add(closeButton);
		add(content);
		pack();
	}

}
