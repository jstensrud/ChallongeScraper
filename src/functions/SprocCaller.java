package functions;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class SprocCaller {
	private Connection con;

	public SprocCaller(Connection con) {
		this.con = con;
	}

	public Integer getTournamentIDFromName(String name) {
		CallableStatement cs = null;
		try {
			cs = con.prepareCall("{call get_tournament_id(?)}");
			cs.setString(1, name);
			// cs.registerOutParameter(1, Types.INTEGER);
			ResultSet rs = cs.executeQuery();
			/*
			 * int returnValue = cs.getInt(1); if(returnValue == 5){
			 * JOptionPane.showMessageDialog(null,
			 * "Tournament ID does not exist in the database."); }
			 */
			if (rs.next()) {
				return rs.getInt(rs.findColumn("TournamentID"));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return -1;
	}

	public String[][] getMatchesForTournament(int tournamentID) {
		System.out.println(tournamentID);
		CallableStatement cs = null;
		try {
			cs = con.prepareCall("{ ? = call getMatchesForTourney(?)}");
			cs.setInt(2, tournamentID);
			cs.registerOutParameter(1, Types.INTEGER);
			ResultSet rs = cs.executeQuery();
			/*
			 * int returnValue = cs.getInt(1); if(returnValue == 1){
			 * JOptionPane.showMessageDialog(null,
			 * "Tournament ID does not exist in the database."); }
			 */ return parseMatches(rs);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public String[][] getMatchesForPlayer(String playerTag) {
		CallableStatement cs = null;
		try {
			cs = con.prepareCall("{ ? = call getMatchesForPlayer(?)}");
			cs.setString(2, playerTag);
			cs.registerOutParameter(1, Types.INTEGER);
			ResultSet rs = cs.executeQuery();
			/*
			 * int returnValue = cs.getInt(1); if(returnValue == 1){
			 * JOptionPane.showMessageDialog(null,
			 * "Tournament ID does not exist in the database."); }
			 */ return parseMatches(rs);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public String[][] getMatchesForPlayers(String playerTag, String opponentTag) {
		CallableStatement cs = null;
		try {
			cs = con.prepareCall("{ ? = call getMatchesForPlayers(?, ?)}");
			cs.setString(2, playerTag);
			cs.setString(3, opponentTag);
			cs.registerOutParameter(1, Types.INTEGER);
			ResultSet rs = cs.executeQuery();
			/*
			 * int returnValue = cs.getInt(1); if(returnValue == 1){
			 * JOptionPane.showMessageDialog(null,
			 * "Tournament ID does not exist in the database."); }
			 */ return parseMatches(rs);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public String[][] getRankingsForPlayer(String playerTag) {
		CallableStatement cs = null;
		try {
			cs = con.prepareCall("{ ? = call [get_playerTourney_results](?)}");
			cs.setString(2, playerTag);
			cs.registerOutParameter(1, Types.INTEGER);
			ResultSet rs = cs.executeQuery();
			/*
			 * int returnValue = cs.getInt(1); if(returnValue == 1){
			 * JOptionPane.showMessageDialog(null,
			 * "Tournament ID does not exist in the database."); }
			 */ return parseTournamentResults(rs);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public String[][] getRankingsForTournament(int tournamentID) {
		CallableStatement cs = null;
		try {
			cs = con.prepareCall("{ ? = call [get_tourney_results](?)}");
			cs.setInt(2, tournamentID);
			cs.registerOutParameter(1, Types.INTEGER);
			ResultSet rs = cs.executeQuery();
			/*
			 * int returnValue = cs.getInt(1); if(returnValue == 1){
			 * JOptionPane.showMessageDialog(null,
			 * "Tournament ID does not exist in the database."); }
			 */ return parseTournamentResults(rs);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public String[][] parseMatches(ResultSet rs) {
		List<String> winnerTags = new ArrayList<String>();
		List<String> loserTags = new ArrayList<String>();
		List<String> scores = new ArrayList<String>();
		List<String> tournamentNames = new ArrayList<String>();
		try {
			while (rs.next()) {
				int winnerTagIndex = rs.findColumn("WinnerTag");
				int loserTagIndex = rs.findColumn("LoserTag");
				int scoreIndex = rs.findColumn("Score");
				int tourneyNameIndex = rs.findColumn("Name");
				winnerTags.add(rs.getString(winnerTagIndex));
				loserTags.add(rs.getString(loserTagIndex));
				scores.add(rs.getString(scoreIndex));
				tournamentNames.add(rs.getString(tourneyNameIndex));
			}
			String[][] results = new String[winnerTags.size()][4];
			for (int i = 0; i < winnerTags.size(); i++) {
				results[i][0] = winnerTags.get(i);
				results[i][1] = loserTags.get(i);
				results[i][2] = scores.get(i);
				results[i][3] = tournamentNames.get(i);
			}
			return results;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String[][] parseTournamentResults(ResultSet rs) {
		List<String> tournamentNames = new ArrayList<String>();
		List<String> tags = new ArrayList<String>();
		List<Integer> seeds = new ArrayList<Integer>();
		List<Integer> placings = new ArrayList<Integer>();
		try {
			while (rs.next()) {
				int tourneyIndex = rs.findColumn("Name");
				int tagIndex = rs.findColumn("PlayerTag");
				int seedIndex = rs.findColumn("Seed");
				int placingIndex = rs.findColumn("Placing");
				tournamentNames.add(rs.getString(tourneyIndex));
				tags.add(rs.getString(tagIndex));
				seeds.add(rs.getInt(seedIndex));
				placings.add(rs.getInt(placingIndex));
			}
			String[][] results = new String[tags.size()][4];
			for (int i = 0; i < tournamentNames.size(); i++) {
				results[i][0] = tournamentNames.get(i);
				results[i][1] = seeds.get(i).toString();
				results[i][2] = placings.get(i).toString();
				results[i][3] = tags.get(i);
			}
			return results;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String[][] parseMatchesForTournament(ResultSet rs) {
		List<String> winnerTags = new ArrayList<String>();
		List<String> loserTags = new ArrayList<String>();
		List<String> scores = new ArrayList<String>();
		try {
			while (rs.next()) {
				int winnerTagIndex = rs.findColumn("WinnerTag");
				int loserTagIndex = rs.findColumn("LoserTag");
				int scoreIndex = rs.findColumn("Score");
				winnerTags.add(rs.getString(winnerTagIndex));
				loserTags.add(rs.getString(loserTagIndex));
				scores.add(rs.getString(scoreIndex));
			}
			String[][] results = new String[winnerTags.size()][3];
			for (int i = 0; i < winnerTags.size(); i++) {
				results[i][0] = winnerTags.get(i);
				results[i][1] = loserTags.get(i);
				results[i][2] = scores.get(i);
			}
			return results;
		} catch (SQLException e) {
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
	
	public boolean addMains(String tag, String name, String game, String color){
		CallableStatement cs = null;
		try{
			cs = con.prepareCall("{ ? = call insert_Mains(?,?,?,?)}");
			cs.setString(2, tag);
			cs.setString(3, name);
			cs.setString(4, game);
			cs.setString(5, color);
			cs.registerOutParameter(1, Types.INTEGER);
			boolean rs = cs.execute();
			if (rs == false) {
				// TODO: Handle error
			}
		}catch(SQLException ex){
			ex.printStackTrace();
		}
		return false;
	}
	
	public boolean addRank(String period, String game, String group){
		CallableStatement cs = null;
		try{
			cs = con.prepareCall("{ ? = call create_ranking(?,?,?)}");
			cs.setString(2, period);
			cs.setString(3, game);
			cs.setString(4, group);
			cs.registerOutParameter(1, Types.INTEGER);
			boolean rs = cs.execute();
			if (rs == false) {
				// TODO: Handle error
			}
		}catch(SQLException ex){
			ex.printStackTrace();
		}
		return false;

	}
	
	public boolean addRanking(String tag, String period, String game, String group, String rank){
		CallableStatement cs = null;
		try{
			cs = con.prepareCall("{ ? = call insert_RankedIn(?,?,?,?,?)}");
			cs.setString(2, tag);
			cs.setString(3, period);
			cs.setString(4, game);
			cs.setString(5, group);
			cs.setString(6, rank);
			cs.registerOutParameter(1, Types.INTEGER);
			boolean rs = cs.execute();
			if (rs == false) {
				// TODO: Handle error
			}
		}catch(SQLException ex){
			ex.printStackTrace();
		}
		return false;
		
	}
	
	public String[] getPlayers(){
		CallableStatement cs = null;
		try{
			cs = con.prepareCall("{call getAllPlayers()}");
			return parseSingleStringValues(cs.executeQuery());
		}catch(SQLException ex){
			ex.printStackTrace();
		}
		return null;
	}
	
	public String[] getGames(){
		CallableStatement cs = null;
		try{
			cs = con.prepareCall("{call getAllGames()}");
			return parseSingleStringValues(cs.executeQuery());
		}catch(SQLException ex){
			ex.printStackTrace();
		}
		return null;
	}
		
	public String[] getChars(String game){
		CallableStatement cs = null;
		try{
			cs = con.prepareCall("{call getAllCharactersForGame(?)}");
			cs.setString(1, game);
			return parseSingleStringValues(cs.executeQuery());
		}catch(SQLException ex){
			ex.printStackTrace();
		}
		return null;		
	}

	public String[] getSkins(String game, String name){
		CallableStatement cs = null;
		try{
			cs = con.prepareCall("{call getAllSkinsForCharacter(?,?)}");
			cs.setString(1, game);
			cs.setString(2, name);
			return parseSingleStringValues(cs.executeQuery());
		}catch(SQLException ex){
			ex.printStackTrace();
		}
		return null;		
	}
	
	public String[] getTournaments(){
		CallableStatement cs = null;
		try{
			cs = con.prepareCall("{call getAllTournaments()}");
			return parseSingleStringValues(cs.executeQuery());
		}catch(SQLException ex){
			ex.printStackTrace();
		}
		return null;
	}
	
	public String[] parseSingleStringValues(ResultSet rs){
		List<String> resultsList = new ArrayList<String>();
		try{
			while(rs.next()){
				resultsList.add(rs.getString(1));
			}
		}catch(SQLException ex){
			ex.printStackTrace();
		}
		String[] results = new String[resultsList.size()];
		for(int i = 0; i < results.length; i++){
			results[i] = resultsList.get(i);
		}
		return results;
	}

}
