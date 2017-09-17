package genetic_generation;

import java.util.ArrayList;

public class Population {
	ArrayList<Plane> pop = new ArrayList<Plane>();
	
	public Population(int size){
		for(int x = 0; x < size; x++){
			pop.add(new Plane());
		}
	}
	public Population(){
		
	}
	
	public ArrayList<Plane> getPop(){
		return pop;
	}
	public Plane getPlane(int index){
		return pop.get(index);
	}
	public int getSize(){
		return pop.size();
	}
	
	public void setPlane(int index, Plane plane){
		pop.set(index, plane);
	}
	
	public void addPlane(Plane plane){
		pop.add(plane);
	}
	public void removePlane(int index){
		pop.remove(index);
	}
}
