package functions;
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

import gui.PopUpVisualizer;
import tournament_data.Match;
import tournament_data.MatchWrapper;
import tournament_data.OutputWrapper;
import tournament_data.Participant;
import tournament_data.ParticipantWrapper;
import tournament_data.Tournament;

public class APICaller {

	private String apiKey;
	private HashMap<Integer, Participant> participantList;
	private ArrayList<Match> matchList;
	
	public APICaller(String apiKey){
		this.apiKey = apiKey;
		this.participantList = new HashMap<Integer, Participant>();
		this.matchList = new ArrayList<Match>();
	}
	
	public OutputWrapper scrape(String tournamentID) {
		Tournament t = retrieveTournament(tournamentID);
		updateParticipantList(t);
		updateMatchList(t);
		return new OutputWrapper(t, participantList, matchList);
	}
	
	/**
	 * Gets the data for the tournament from the Challonge API. Currently, the only value used from the tournament is the ID,
	 * which is already given, so there isn't much use for this method. However, it will make future features easier to create.
	 * 
	 * @param tournamentID : The ID for the tournament, provided by the scrape method.
	 * @return the data for the Tournament in question.
	 */
	public Tournament retrieveTournament(String tournamentID){
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
											"You have either entered an invalid Tournament ID (" + tournamentID + "). Please enter a valid Tournament ID.");
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
	
	public void updateParticipantList(Tournament t){
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
					Participant p = participants[i].getParticipant();
					//Add the participant data to the participant list, with ID and Name as the key and value for the HashMap.
					participantList.put(p.id, p);
					System.out.println(p.tagWithCrew);
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
	
	public void updateMatchList(Tournament t){
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
					Match m = matches[i].getMatch();
					m.winnerTag = getParticipantTagFromID(m.winner_id);
					m.loserTag = getParticipantTagFromID(m.loser_id);
					matchList.add(m);
					
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
	
	public String getParticipantTagFromID(Integer id){
		return participantList.get(id).tag;
	}
}
