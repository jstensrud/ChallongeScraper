package character_data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "character")
public class Character {

	public String name;
	public String[] colors;
	
	public Character(@JsonProperty("name")String name, @JsonProperty("colors")String[] colors){
		this.name = fixName(name);
		this.colors = colors;
		for(int i = 0; i < colors.length; i++){
			this.colors[i] = fixName(this.colors[i]);
		}
	}
	
	public String fixName(String name){
		return name.replace('_', ' ');
	}
	
}
