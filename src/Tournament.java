import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "tournament")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Tournament {
	public Integer id;
	public String name;
	public String organization;
	public String game;
	public String date;
	
	/**
	 * Default constructor, with some instructions for the JSON Deserializer.
	 */
	@JsonCreator
	public Tournament(@JsonProperty("id")Integer id, @JsonProperty("name")String name, 
						@JsonProperty("subdomain")String subdomain, @JsonProperty("created_at")String date,
						  @JsonProperty("game_name")String game){
		this.id = id;
		this.name = name;
		this.organization = subdomain;
		this.date = date.substring(0, 10);
		this.game = game;
	}
	

	public Integer getID(){
		return this.id;
	}

	public String getName(){
		return this.name;
	}
	
	public String getOrganization(){
		return this.organization;
	}
	
	public String getDate(){
		return this.date;
	}
	
	public String getGame(){
		return this.game;
	}
}
