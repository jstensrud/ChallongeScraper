package ranking_data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "ranking")
public class Ranking {
	public String tag;
	public String rank;
	
	public Ranking(@JsonProperty("tag")String tag, @JsonProperty("rank")String rank){
		this.tag = tag;
		this.rank = rank;
	}
}
