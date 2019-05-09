package tournament_data;
/**
 * MatchWrapper
 * 
 * Since the Challonge API puts match data inside a "match" object that then includes the actual match data,
 * we need to use a wrapper class to get at the correct data.
 * 
 * Match match: The match in question
 *
 */
public class MatchWrapper {
	Match match;
	
	/**
	 * Returns the match wrapped in the class.
	 * 
	 * @return the match wrapped in the class.
	 */
	public Match getMatch(){
		return this.match;
	}
}
