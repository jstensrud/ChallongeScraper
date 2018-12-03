import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;


/**
 * Tournament
 * 
 * Stores the data for a tournament.
 * 
 * Integer id: The tournament's ID.
 * String name: The tournament's name (currently unused, but it seems nice to have for the future).
 * 
 */
@JsonRootName(value = "tournament")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Tournament {
	private Integer id;
	private String name;
	
	/**
	 * Default constructor, with some instructions for the JSON Deserializer.
	 * @param id: The tournament's ID
	 * @param name: The tournament's name
	 */
	@JsonCreator
	public Tournament(@JsonProperty("id")Integer id, @JsonProperty("name")String name){
		this.id = id;
		this.name = name;
	}
	
	/**
	 * Returns the tournament's ID.
	 * @return the tournament's ID.
	 */
	public Integer getID(){
		return this.id;
	}

	/**
	 * Returns the tournament's name.
	 * @return the tournament's name.
	 */
	public String getName(){
		return this.name;
	}
	
	/**
	 * Sets the tournament's ID.
	 * @param id: the tournament's new ID.
	 */
	public void setID(Integer id){
		this.id = id;
	}

	/**
	 * Sets the tournament's name.
	 * @param id: the tournament's new name.
	 */
	public void setName(String name){
		this.name = name;
	}
}
