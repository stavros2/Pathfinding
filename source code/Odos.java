import java.util.ArrayList;

// A class to represent each street. The class contains just an ArrayList with the nodes that belong to each street.
public class Odos{
	private ArrayList<Node> streetNodes;

	// A simple constructor.
	public Odos (){
		streetNodes = new ArrayList<Node>();	
	}

	// Setter and getter for streetNodes.
	public ArrayList<Node> getStreetNodes(){
		return streetNodes;
	}

	public void setStreetNodes(ArrayList<Node> mySN){
		streetNodes = new ArrayList<Node>(mySN);
	}
}