package genetic_generation;

import java.util.Random;

public class Cycler { 
	
	public static void main(String[] args){
		
		int genNum = 100;
		int popSize = 100;
		
		Population pop = new Population(popSize);
		loop(genNum, popSize, pop);
		
		
	}
	
	public static void loop(int totalGen, int popSize, Population pop){
		for(int genCount = 0; genCount < totalGen; genCount++){
			
			//score - arbitrary function
			pop = scoring(pop);
			//selection - Truncate, Tournament, Roulette
			pop = selectionTruncate(10, pop);
			//breeding/mutation
			pop = breed(popSize, pop);
		}
		
		//example output
		for(int x = 0; x < pop.getSize(); x++){
			pop = scoring(pop);
			System.out.println(pop.getPlane(x).getChro() + " " + pop.getPlane(x).getScore());
		}
	}
	
	public static Population scoring(Population pop){
		int score;
		for(int x = 0; x < pop.getSize(); x++){
			//arbitrary scoring function here
			int count = 0;
			for(int y = 0; y < pop.getPlane(x).getChro().length(); y++){
				count += Character.getNumericValue(pop.getPlane(x).getChro().charAt(y));
			}
			score = Math.abs(count - 10);
			//System.out.println(pop.getPlane(x).getChro() + " " + count + " " + score);
			pop.getPlane(x).setScore(score);
		}
		return pop;
	}
	
	public static Population selectionTruncate(int percent, Population pop){
		Population newPop = new Population();
		Random r = new Random();
		int selectSize = percent*pop.getSize()/100;
		
		//Order list
		for(int x = 0; x < pop.getSize()-1; x++){
			Plane buffer = new Plane();
			if(pop.getPlane(x+1).getScore() > pop.getPlane(x).getScore()){
				buffer = pop.getPlane(x+1);
				pop.setPlane(x+1, pop.getPlane(x));
				pop.setPlane(x, buffer);
				x = -1;
			}
		}
		
		for(int x = 0; x < selectSize; x++){
			newPop.addPlane(pop.getPlane(x));
			//System.out.println("Landon looks ugly");
		}
		
		return newPop;
	}
	
	public static Population selectionRoulette(int percent, Population pop){
		Population newPop = new Population();
		Random r = new Random();
		int selectSize = percent*pop.getSize()/100;
		int scoreSum = 0;
		int selectedScore;
		
		//sum fitness
		for(int x = 0; x < pop.getSize(); x++){
			scoreSum += pop.getPlane(x).getScore();
		}
		
		//order List
		for(int x = 0; x < pop.getSize()-1; x++){
			Plane buffer = new Plane();
			if(pop.getPlane(x+1).getScore() > pop.getPlane(x).getScore()){
				buffer = pop.getPlane(x+1);
				pop.setPlane(x+1, pop.getPlane(x));
				pop.setPlane(x, buffer);
				x = -1;
			}
		}
		
		for(int x = 0; x < pop.getSize(); x++){
			System.out.println(pop.getPlane(x).getScore());
		}
		
		for(int x = 0; x < selectSize; x++){
			selectedScore = r.nextInt(scoreSum);
			
			int selectedPlane = 0;
			for(int currentScore = 0; currentScore < selectedScore; currentScore+=pop.getPlane(selectedPlane).getScore()){
				selectedPlane++;
				System.out.println("cScore: " + currentScore + "  sScore: " + selectedScore + "  sPlane: " + selectedPlane);
			}
			System.out.println(selectedPlane);
			
		}
		
		
		for(int x = 0; x < percent*pop.getSize()/100; x++){
			newPop.addPlane(pop.getPlane(x));
		}
		
		return newPop;
	}
	
	public static Population selectionTournament(int percent, Population pop){
		Population newPop = new Population();
		Random r = new Random();
		int selectSize = percent*pop.getSize()/100;
		
		int sel1;
		int sel2;
		
		for(int x = 0; x < selectSize; x++){
			sel1 = r.nextInt(pop.getSize());
			sel2 = r.nextInt(pop.getSize());
			if(sel1 == sel2){
				if(sel1 == pop.getSize()-1){
					sel1--;
				}
				else{
					sel2++;
				}
			}else if(sel1 > sel2){
				int buffer = sel1;	//order them to make nice for deleting later (del sel2 first)
				sel1 = sel2;
				sel2  = buffer;
			};
			
			if(pop.getPlane(sel1).getScore() > pop.getPlane(sel2).getScore()){
				newPop.addPlane(pop.getPlane(sel1));
			}else{
				newPop.addPlane(pop.getPlane(sel2));
			}
			
			pop.removePlane(sel2);
			pop.removePlane(sel1);
			//System.out.println("Landon looks ugly");
		}
		
		return newPop;
	}
	
	public static Population breed(int popSize, Population pop){
		Random r = new Random();
		int sel1;
		int sel2; 
		int pointPercent = 10;
		Population newPop = new Population();
		
		for(int x = 0; x < popSize; x++){
			sel1 = r.nextInt(pop.getSize());
			sel2 = r.nextInt(pop.getSize());
			if(sel1 == sel2){
				if(sel1 == pop.getSize()-1){
					sel1--;
				}
				else{
					sel2++;
				}
			}
			
			int crossPoint;
			int sel1Length = pop.getPlane(sel1).getChro().length();
			int sel2Length = pop.getPlane(sel2).getChro().length();
			
			do{
				crossPoint = r.nextInt(sel1Length);
			}while(crossPoint > sel1Length || crossPoint > sel2Length);
			
			//subdivide chromosomes
			String sel1Chro = pop.getPlane(sel1).getChro();
			String sel2Chro = pop.getPlane(sel2).getChro();
			String newChro = sel1Chro.substring(0, crossPoint) + sel2Chro.substring(crossPoint, sel2Length);
			
			//point Mutations
			int pointNum;
			String mutChro = null;
			for(int y = 0; y < newChro.length(); y++){
				pointNum = r.nextInt(100);
				if(pointNum < pointPercent){
					mutChro = newChro.substring(0,y) + "" + r.nextInt(10) + newChro.substring(y, newChro.length()-1);
					//program edge case?
				}else{
					mutChro = newChro;
				}
			}
			newPop.addPlane(new Plane(mutChro));
			
		}
		
		return newPop;
	}
	
	
}
