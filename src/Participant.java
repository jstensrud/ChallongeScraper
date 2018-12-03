import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * 
 * Participant
 * 
 * Stores the information for an individual tournament participant.
 * 
 * Integer id: The ID of the participant.
 * Integer name: The name of the participant.
 * 
 */

@JsonRootName(value = "participant")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Participant {
	
	private Integer id;
	private String name;
	
	/**
	 * Default constructor, including some instructions for the JSON deserializer
	 * 
	 * @param id: Participant's ID
	 * @param name: Participant's name
	 */
	public Participant(@JsonProperty("id")Integer id, @JsonProperty("name")String name){
		this.id = id;
		this.name = name;
	}
	
	/**
	 * Returns the player's ID.
	 * @return the plasyer's ID.
	 */
	public Integer getID(){
		return this.id;
	}

	/**
	 * Returns the player's name.
	 * @return the plasyer's name.
	 */
	public String getName(){
		return this.name;
	}
}
