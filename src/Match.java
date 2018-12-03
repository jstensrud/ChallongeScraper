import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * 
 * Match
 * 
 * Stores the information for an individual tournament match.
 * 
 * Integer winner_id: The ID of the player that won the match.
 * Integer loser_id: The ID of the player that lost the match.
 * 
 */
@JsonRootName(value = "match")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Match {
	private Integer winner_id;
	private Integer loser_id;
	
	/**
	 * Default constructor, including some instructions for the JSON deserializer
	 * 
	 * @param w_id: Winner's ID
	 * @param l_id: Loser's ID
	 */
	public Match(@JsonProperty("winner_id")Integer w_id, @JsonProperty("loser_id")Integer l_id){
		this.winner_id = w_id;
		this.loser_id = l_id;
	}
	
	/**
	 * Returns the Winner's ID.
	 * @return: Winner's ID (Integer)
	 */
	public Integer getWinnerID(){
		return this.winner_id;
	}

	/**
	 * Returns the Loser's ID.
	 * @return: Loser's ID (Integer)
	 */
	
	public Integer getLoserID(){
		return this.loser_id;
	}
}
