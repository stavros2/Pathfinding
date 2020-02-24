import java.util.ArrayList;

// A class to represent the route each taxi has to follow in order to reach the goal.
public class TaxiRoute implements Comparable{
	private int id;
	private double distance;
	private ArrayList<Node> route;

	// 2 Simple Constructors.
	public TaxiRoute(int myID, double myDistance, ArrayList<Node> myRoute){
		id = myID;
		distance = myDistance;
		route = new ArrayList<Node>(myRoute);
	}

	public TaxiRoute(){
	};

	// Overriding the compareTo function in order to sort items, depending on distance covered.
	@Override
	public int compareTo (Object s){
		TaxiRoute t = (TaxiRoute) s;
		if (distance > t.getDistance()) return 1;
		if (distance < t.getDistance()) return -1;
		return 0;
	}

	// Getters and setters for every field.
	public int getID(){
		return id;
	}

	public double getDistance(){
		return distance;
	}

	public ArrayList<Node> getRoute(){
		return route;
	}

	public void setID(int myID){
		id = myID;
	}
	
	public void setDistance(double myDistance){
		distance = myDistance;
	}

	public void setRoute(ArrayList<Node> myRoute){
		route = new ArrayList<Node>(myRoute);
	}
}