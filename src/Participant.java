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
	
	public Integer id;
	public Integer tournamentID;
	public String tagWithCrew;
	public String tag;
	public String crew;
	public Integer seed;
	public Integer placing;
	
	/**
	 * Default constructor, including some instructions for the JSON deserializer
	 * 
	 * @param id: Participant's ID
	 * @param name: Participant's name
	 */
	public Participant(@JsonProperty("id")Integer id, @JsonProperty("tournament_id")Integer tournamentID, 
						@JsonProperty("name")String name, @JsonProperty("seed")Integer seed, 
						 @JsonProperty("final_rank")Integer placing){
		this.id = id;
		this.tournamentID = tournamentID;
		this.tagWithCrew = name;
		this.seed = seed;
		this.placing = placing;
		setTagAndCrew();
	}
	
	public void setTagAndCrew(){
		for(int i = 0; i < tagWithCrew.length(); i++){
			if(tagWithCrew.charAt(i) == '|'){
				crew = tagWithCrew.substring(0, i - 1);
				tag = tagWithCrew.substring(i + 2);
				return;
			}
		}
		crew = "";
		tag = tagWithCrew;
		return;
	}
}
