package functions;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class SprocCaller {
	private Connection con;
	
	public SprocCaller(Connection con){
		this.con = con;
	}
	
	public Integer getTournamentIDFromName(String name){
		CallableStatement cs = null;
		try{
			cs = con.prepareCall("{call get_tournament_id(?)}");
			cs.setString(1, name);
//			cs.registerOutParameter(1, Types.INTEGER);
			ResultSet rs = cs.executeQuery();
/*			int returnValue = cs.getInt(1);
			if(returnValue == 5){
				JOptionPane.showMessageDialog(null, "Tournament ID does not exist in the database.");
			}*/
			if(rs.next()){
				return rs.getInt(rs.findColumn("TournamentID"));
			}
		}catch(SQLException ex){
			ex.printStackTrace();
		}
		return -1;
	}
	
	public String[][] getMatchesForTournament(int tournamentID){	
		System.out.println(tournamentID);
		CallableStatement cs = null;
		try{
			cs = con.prepareCall("{ ? = call getMatchesForTourney(?)}");
			cs.setInt(2, tournamentID);
			cs.registerOutParameter(1, Types.INTEGER);
			ResultSet rs = cs.executeQuery();
/*			int returnValue = cs.getInt(1);
			if(returnValue == 1){
				JOptionPane.showMessageDialog(null, "Tournament ID does not exist in the database.");
			}	
*/			return parseGetMatchesForTournament(rs);
		}catch(SQLException ex){
			ex.printStackTrace();
		}
		return null;
	}
	
	public String[][] parseGetMatchesForTournament(ResultSet rs){
		List<String> winnerTags = new ArrayList<String>();
		List<String> loserTags = new ArrayList<String>();
		List<String> scores = new ArrayList<String>();
		try{
		while(rs.next()){
			int winnerTagIndex = rs.findColumn("WinnerTag");
			int loserTagIndex = rs.findColumn("LoserTag");
			int scoreIndex = rs.findColumn("Score");
			winnerTags.add(rs.getString(winnerTagIndex));
			loserTags.add(rs.getString(loserTagIndex));
			scores.add(rs.getString(scoreIndex));
		}
		String[][] results = new String[winnerTags.size()][3];
		for(int i = 0; i < winnerTags.size(); i++){
			results[i][0] = winnerTags.get(i);
			results[i][1] = loserTags.get(i);
			results[i][2] = scores.get(i);
		}
		return results;
		}catch(SQLException e){
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean addCharacter(String name, String game, String color){
		CallableStatement cs = null;
		try{
			cs = con.prepareCall("{call insert_Characters(?,?,?)}");
			cs.setString(1, name);
			cs.setString(2, game);
			cs.setString(3, color);
			return cs.execute();
		}catch(SQLException ex){
			ex.printStackTrace();
		}
		return false;
	}
}
