package character_data;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;

import com.fasterxml.jackson.databind.ObjectMapper;

import functions.SprocCaller;

public class CharacterAdder {
	
	public SprocCaller sc;
	public CharacterAdder(Connection con){
		this.sc = new SprocCaller(con);		
	}

	public void addMelee(){
		ObjectMapper om = new ObjectMapper();
		CharacterWrapper[] chars;
		try {
			chars = om.readValue(new File("src/character_data/ssbm.json"), CharacterWrapper[].class);
			for(int i = 0; i < chars.length; i++){
				for(int j = 0; j < chars[i].getCharacter().colors.length; j++){
					sc.addCharacter(chars[i].getCharacter().name, "Super Smash Bros. Melee", chars[i].getCharacter().colors[j]);					
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	public void addUltimate(){
		ObjectMapper om = new ObjectMapper();
		CharacterWrapper[] chars;
		try {
			chars = om.readValue(new File("src/character_data/ssbu.json"), CharacterWrapper[].class);
			for(int i = 0; i < chars.length; i++){
				for(int j = 0; j < chars[i].getCharacter().colors.length; j++){
					sc.addCharacter(chars[i].getCharacter().name, "Super Smash Bros. Ultimate", chars[i].getCharacter().colors[j]);					
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	public void addCharacterIndividual(String name, String game, String color){
		
	}
}
