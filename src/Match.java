import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "match")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Match {
	public Integer id;
	public Integer tournamentID;
	public Integer winner_id;
	public Integer loser_id;
	public String winnerTag;
	public String loserTag;
	public String score;
	

	public Match(@JsonProperty("id")Integer id, @JsonProperty("tournament_id")Integer tournamentID, 
					@JsonProperty("winner_id")Integer w_id, @JsonProperty("loser_id")Integer l_id, 
						@JsonProperty("scores_csv")String score){
		this.id = id;
		this.tournamentID = tournamentID;
		this.winner_id = w_id;
		this.loser_id = l_id;
		this.score = score;
	}
}
