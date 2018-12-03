/**
 * ParticipantWrapper
 * 
 * Since the Challonge API puts participant data inside a "participant" object that then includes the actual player data,
 * we need to use a wrapper class to get at the correct data.
 * 
 * Match match: The participant in question
 *
 */
public class ParticipantWrapper {
	Participant participant;
	
	/**
	 * Returns the participant wrapped in the class.
	 * 
	 * @return The participant wrapped in the class.
	 */
	public Participant getParticipant(){
		return this.participant;
	}
}
