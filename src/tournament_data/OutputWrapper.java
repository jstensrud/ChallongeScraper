package tournament_data;
import java.util.ArrayList;
import java.util.HashMap;

public class OutputWrapper {
	
	public Tournament t;
	public HashMap<Integer, Participant> participants;
	public ArrayList<Match> matches;
	
	public OutputWrapper(Tournament t, HashMap<Integer, Participant> participants, ArrayList<Match> matches){
		this.t = t;
		this.participants = participants;
		this.matches = matches;
	}
}
