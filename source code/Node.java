import java.lang.Math.*;
import java.util.*;

// A class to represent each given node. 
public class Node{
	protected double x;
	protected double y;

	// 2 simple constructors. 
	public Node (double myX, double myY){
		x = myX;
		y = myY;
	}

	public Node(){
	}

	// Overriding the hashCode() and equals(Object obj) functions in order for our HashMaps in Astar class to work the way we want to.
	@Override
	public int hashCode() {																					 
            long hash = 1;																					
            hash = 42 * hash + Double.doubleToLongBits(x) + Double.doubleToLongBits(y);
            return (int) hash;
    }

    @Override
	public boolean equals(Object obj){ 							
	 	if(obj == null) return false;
	 	if(this.getClass() != obj.getClass()) return false;		
	 	Node other = (Node) obj;
	 	if(x != other.getX() || y != other.getY())	
	 		return false;
	 	return true;
	}


	// A method for finding the distance between the current node and node n.
	public double findDistance(Node n){
		double difX, difY, sinX, sinY, a, c;
		int r = 6371000;
		difX = Math.toRadians(x - n.getX());
		difY = Math.toRadians(y - n.getY());
		a = Math.sin(difX/2) * Math.sin(difX/2) + Math.cos(Math.toRadians(x)) * Math.cos(Math.toRadians(n.getX())) * Math.sin(difY/2) * Math.sin(difY/2);
		c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

		return r * c;
	}

	// A method to change the node coordinates to the closer node in a given list.
	public void setCloser(LinkedList<Node> nodes){
		double min = Double.MAX_VALUE;
		Node t = new Node();	
		for (Node node : nodes){
			double check = this.findDistance(node);
			if (check < min){
				t = node;
				min = check;
			}
		}
		x = t.getX();
		y = t.getY();
	}

	// A method for determining if the node is in the same spot with another.
	public boolean sameSpot(Node n){
		return (n.getX() == x && n.getY() == y);
	}


	// Getters and setter for x and y values.
	public double getX(){
		return x;
	}

	public double getY(){
		return y;
	}

	public void setX(double myX){
		x = myX;
	}
	
	public void setY(double myY){
		y = myY;
	}
}