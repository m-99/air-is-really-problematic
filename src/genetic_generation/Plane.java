package genetic_generation;

import java.util.Random;

public class Plane {
	String chro;
	int score;
	int startChroLength = 10;
	
	//Initialize
	public Plane(){
		Random r = new Random();
		StringBuffer tempChro = new StringBuffer();
		for(int x = 0; x < startChroLength; x++){
			tempChro.append(r.nextInt(10));
		}
		chro = tempChro.toString();
		
	}
	public Plane(String chro){
		this.chro = chro;
	}
	
	
	//Setters & Getters
	public String getChro(){
		return chro;
	}
	public int getScore(){
		return score;
	}
	
	public void setChro(String chro){
		this.chro = chro;
	}
	public void setScore(int score){
		this.score = score;
	}
	
}
