import java.util.*;

// A class representing Taxis. Extends the Node class with an additional ID field for every Taxi.
public class Taxi extends Node{
	private int id;

	// A simple constructor.
	public Taxi(){
	}

	// Getter and setter for id.
	public int getID(){
		return id;
	}

	public void setID(int myID){
		id = myID;
	}
}