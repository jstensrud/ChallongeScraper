import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * APICaller:
 * Calls the Challonge API to get tournament, participant, and match data.
 * Variables:
 * 				String apiKey: The user's API Key to Challonge.
 * 				HashMap<Integer, String> participantList: The list of participants in the tournament.
 * 				ArrayList<String> matchList: The list of each match taken place in the tournament, in the form of "X defeats Y"
 *
 */
public class APICaller {

	private String apiKey;
	private HashMap<Integer, String> participantList;
	private ArrayList<String> matchList;
	
	/**
	 * Default constructor of APICaller
	 * 
	 * @param apiKey: The Challonge API key, provided by the user's input in the Visualizer class.
	 */
	public APICaller(String apiKey){
		this.apiKey = apiKey;
		this.participantList = new HashMap<Integer, String>();
		this.matchList = new ArrayList<String>();
	}
	
	/**
	 * Scrapes the Challonge API for results for a given tournament.
	 * 
	 * @param tournamentID: The ID for the tournament, provided by the user's input in the Visualizer class.
	 * @return An ArrayList of each match in the tournament.
	 */
	public ArrayList<String> scrape(String tournamentID) {
		
		//Get the tournament data
		Tournament t = getTournament(tournamentID);
		
		//From the tournament data,
		//Update the participant list and match list.
		getParticipantList(t);
		getMatchList(t);
		
		//Return the new Match List.
		return this.matchList;
	}
	
	/**
	 * Gets the data for the tournament from the Challonge API. Currently, the only value used from the tournament is the ID,
	 * which is already given, so there isn't much use for this method. However, it will make future features easier to create.
	 * 
	 * @param tournamentID : The ID for the tournament, provided by the scrape method.
	 * @return the data for the Tournament in question.
	 */
	public Tournament getTournament(String tournamentID){
		try {
			//Set up the URL to call with the Tournament's ID.
			URL url = new URL("https://api.challonge.com/v1/tournaments/" + tournamentID + ".json?api_key=" + apiKey);
			try {
				//Establish the connection
				URLConnection con = url.openConnection();
				//Set up the JSON Deserializer
				ObjectMapper o = new ObjectMapper();
				//Challonge's API encases all the data in "tournament", so we remove the root value to get to the data.
				o.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
				//Get the tournament data and return it.
				Tournament t = o.readValue(con.getInputStream(), Tournament.class);
				return t;
				 
			} catch (FileNotFoundException e){
				//FileNotFoundExceptions occur when the Tournament ID entered doesn't exist.
				
				//Explain the situation in a pop-up
				PopUpVisualizer ev = new PopUpVisualizer("Error", 
											"You have either entered an invalid Tournament ID. Please enter a valid Tournament ID.");
				ev.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				ev.setVisible(true);
				
			} catch (IOException e) {
				//IOExceptions occurr when the API Key does not match the user that created the tournament.
				
				//Explain the situation in a pop-up
				PopUpVisualizer ev = new PopUpVisualizer("Error", 
											"The API key saved does not have proper credentials to view this tournament.");
				ev.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				ev.setVisible(true);
				
			}
		} catch (MalformedURLException e) {
			//This should never happen, as it should get caught by the other errors.
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * Updates the participant list.
	 * 
	 * 
	 * @param t: The tournament in question.
	 */
	public void getParticipantList(Tournament t){
		//Clear the participant list from a potential previous search.
		participantList.clear();
		try {
			//Set up the URL to call with the Tournament's ID.
			URL url = new URL("https://api.challonge.com/v1/tournaments/" + t.getID() + "/participants.json?api_key=" + apiKey);
			try {
				//Establish the connection
				URLConnection con = url.openConnection();
				//Set up the JSON Deserializer
				ObjectMapper o = new ObjectMapper();
				/*
				 * Challonge's API encases all the data in an array of "participant" objects, 
				 * so we use a wrapper class to get to the actual data.
				*/
				ParticipantWrapper[] participants = o.readValue(con.getInputStream(), ParticipantWrapper[].class);
				//For each Participant:
				for(int i = 0; i < participants.length; i++){
					//Add the participant data to the participant list, with ID and Name as the key and value for the HashMap.
					participantList.put(participants[i].getParticipant().getID(), participants[i].getParticipant().getName());
				}
			} catch (IOException e) {
				//This exception is caught by the getTournament script.
				e.printStackTrace();
			}
		} catch (MalformedURLException e) {
			//This exception is caught by the getTournament method.
			e.printStackTrace();
		}
	}
	
	/**
	 * Updates the MatchList to include the results of each match, in the form of "X Defeats Y".
	 * 
	 * @param t: The tournament in question.
	 */
	public void getMatchList(Tournament t){
		//Clear the match list from a potential previous search.
		matchList.clear();
		try {
			//Set up the URL to call with the Tournament's ID.
			URL url = new URL("https://api.challonge.com/v1/tournaments/" + t.getID() + "/matches.json?api_key=" + apiKey);
			try {
				//Establish the connection
				URLConnection con = url.openConnection();
				//Set up the JSON Deserializer
				ObjectMapper o = new ObjectMapper();
				/*
				 * Challonge's API encases all the data in an array of "match" objects, 
				 * so we use a wrapper class to get to the actual data.
				*/
				MatchWrapper[] matches = o.readValue(con.getInputStream(), MatchWrapper[].class);
				for(int i = 0; i < matches.length; i++){
					//Builds the String that shows the total result of the match and adds it to the match list.
					Integer winnerID = matches[i].getMatch().getWinnerID();
					Integer loserID = matches[i].getMatch().getLoserID();
					String winnerName = getParticipantNameFromID(winnerID);
					String loserName = getParticipantNameFromID(loserID);
					matchList.add(winnerName + " defeats " + loserName);
				}
			} catch (IOException e) {
				//This exception is caught by the getTournament method.
				e.printStackTrace();
			}
		} catch (MalformedURLException e) {
			//This exception is caught by the getTournament method.
			e.printStackTrace();
		}
	}
	
	/**
	 * Searches the participant list for a player by their ID and returns their name.
	 * 
	 * @param id: The player's ID.
	 * @return: The player's Name
	 */
	public String getParticipantNameFromID(Integer id){
		return participantList.get(id);
	}
}
