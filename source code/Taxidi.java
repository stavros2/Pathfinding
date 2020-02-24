import java.util.ArrayList;
import java.lang.Math.*;

// A class for representing a location and HPW we got there. Comparable with value of heuristic field.
public class Taxidi extends Node implements Comparable{
	private double distance;
	private double estimation;
	private double heuristic;
	private ArrayList<Node> stathmoi;
	private Node goal;

	// 3 Constructors depending on what one needs.
	public Taxidi (double myX, double myY, double myEst, double myDist, double myHeuristic, ArrayList<Node> myStathmoi, Node myGoal){
		x = myX;
		y = myY;
		estimation = myEst;
		distance = myDist;
		heuristic = myHeuristic;
		stathmoi = new ArrayList<Node>(myStathmoi);
		goal = myGoal;

	}

	public Taxidi(Node start, Node finish){
		x = start.getX();
		y = start.getY();
		distance = 0;
		estimation = start.findDistance(finish);
		heuristic = 0;
		stathmoi = new ArrayList<Node>();
		stathmoi.add(new Node(start.getX(), start.getY()));
		goal = finish;
	}

	public Taxidi(){
		
	}

	// Overriding the compareTo function to serve our needs.
	@Override 
	public int compareTo (Object s){
		Taxidi t = (Taxidi) s;
		if (heuristic > t.getHeuristic()) return 1;
		if (heuristic < t.getHeuristic()) return -1;
		return 0;
	}

	// Getters and setters for every field of the class.
	public double getDistance(){
		return distance;
	}

	public double getEstimation(){
		return estimation;
	}

	public double getHeuristic(){
		return heuristic;
	}

	public Node getGoal(){
		return goal;
	}

	public ArrayList<Node> getStathmoi(){
		return stathmoi;
	}

	public void setGoal(Node myGoal){
		goal = myGoal;
	}

	public void setStathmoi(ArrayList<Node> myStathmoi){
		stathmoi = myStathmoi;
	}

	public void setDistance(double myDistance){
		distance = myDistance;
	}

	public void setEstimation(double myEstimation){
		estimation = myEstimation;
	}

	public void setHeuristic(double myHeuristic){
		heuristic = myHeuristic;
	}

	// Reaching a new Node. Every field is updated.
	public void addNode(Node n){
		stathmoi.add(n);
		distance += this.findDistance(n);
		x = n.getX();
		y = n.getY();
		estimation = n.findDistance(goal);
		heuristic = estimation + distance;
	}
}