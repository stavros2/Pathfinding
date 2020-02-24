import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


// The class which executes the A* algorithm.
public class Astar{
	private LinkedList<Taxi> taxis;												// A linkedlist to keep all taxis.
	private Node goal;															// Denotes the goal node.
	private HashMap<Integer, Odos> streets;										// A hashmap for fast access to every street.
	private HashMap<Node, ArrayList<Integer>> nodeStreets;						// A hashmap for fast access to the list of streets each node belongs.
	private HashMap<Node, Node> simia;											// A hashmap for fast access to nodes.
	private LinkedList<Node> nodes;												// A list containing all the given nodes. 
	private LinkedList<TaxiRoute> liseis;										// A list containing all the solutions 


// A simple constructor. Every data structure we intend on using is initialized.
	public Astar(){
		taxis = new LinkedList<Taxi>();
		goal = new Node();
		streets = new HashMap<Integer,Odos>();
		nodeStreets = new HashMap<Node, ArrayList<Integer>>();
		simia = new HashMap<Node, Node>();
		nodes = new LinkedList<Node>();
		liseis = new LinkedList<TaxiRoute>();
	}


	// A method for parsing the input. After this method is executed every class variable, except liseis is ready for use.
	public void readInputs(){
		try {
			// Reading the client location and creating the goal node with it.
			File arxeio = new File("client.csv");
			Scanner myScan = new Scanner(arxeio).useDelimiter(",|\\r\n");
			myScan.nextLine();
			double goalX = myScan.nextDouble();
			double goalY = myScan.nextDouble();
			goal = new Node(goalX, goalY);
			myScan.close();


			// Reading the location of all the nodes. 
			arxeio = new File("nodes.csv");
			myScan = new Scanner(arxeio).useDelimiter(",|\r");
			myScan.nextLine();
			while (myScan.hasNext()){
				double x = myScan.nextDouble();
				double y = myScan.nextDouble();
				int roadID = myScan.nextInt();
				myScan.nextLine();
				Node temp = new Node(x, y);
				nodes.add(temp);											// Adding each node to the linked list.
				simia.put(temp,temp);										// Adding each node to the hashmap.

				Odos a = streets.get(roadID);								// Adding the node to the streets it belongs to.
				if (a == null) 
					a = new Odos();
				ArrayList<Node> t = a.getStreetNodes();
				t.add(temp);
				a.setStreetNodes(t);
				streets.put(roadID, a);

				ArrayList<Integer> b = nodeStreets.get(temp);				// Adding the street to the node it belongs to.
				if (b == null)
					b = new ArrayList<Integer>();
				b.add(roadID);
				nodeStreets.put(temp, b);
			}
			myScan.close();


			// Reading the location of all taxis and filling a list with them.
			arxeio = new File("taxis.csv");
			myScan = new Scanner(arxeio).useDelimiter(",|\\r\n");
			myScan.nextLine();
			while (myScan.hasNext()){
				double x = myScan.nextDouble();
				double y = myScan.nextDouble();
				int id = myScan.nextInt();
				Taxi temp = new Taxi();
				temp.setX(x);
				temp.setY(y);
				temp.setID(id);
				taxis.add(temp);
			}
			myScan.close();
		}
		catch (FileNotFoundException e){
			e.printStackTrace();
		}
	}


	// Executing the A* algorithm.
	public LinkedList<TaxiRoute> solve(){
		goal.setCloser(nodes);

		// For each taxi we create a new linked list for the search front of our algorithm and a new hashmap for the closed set.
		for (Taxi taxi:taxis){
			HashMap<Node, Node> tempSimia = new HashMap<Node,Node>(simia);
			LinkedList<Taxidi> metopo = new LinkedList<Taxidi>();
			Taxidi current;
			taxi.setCloser(nodes);
			current = new Taxidi(taxi, goal);

			while (!current.sameSpot(goal)){
				double x, y, estimation, distance, heuristic;
				ArrayList<Node> stathmoi;
				Node stoxos;
				x = current.getX();
				y = current.getY();
				estimation = current.getEstimation();
				distance = current.getDistance();
				heuristic = current.getHeuristic();
				stathmoi = current.getStathmoi();
				stoxos = current.getGoal();

				// Getting all possible next nodes and ading the to the search front.
				Node temp = new Node(current.getX(), current.getY());
				ArrayList<Integer> tempRoads = nodeStreets.get(temp);
				for (int j = 0; j < tempRoads.size(); j++ ){
					int tempID = tempRoads.get(j);
					Odos tempR = streets.get(tempID);
					ArrayList<Node> tempList = tempR.getStreetNodes();
					for (int i = 0; i < tempList.size(); i++){
						if (!current.sameSpot(tempList.get(i))) continue;
						if (i == 0){
							Taxidi toAdd = new Taxidi(x, y, estimation, distance, heuristic, stathmoi, stoxos);
							Node candidate = tempList.get(1);
							Node maybe = tempSimia.get(candidate);
							if (maybe == null)
								continue;
							toAdd.addNode(maybe);
							metopo.add(toAdd);
						}
						else if (i == tempList.size() - 1){
							Taxidi toAdd = new Taxidi(x, y, estimation, distance, heuristic, stathmoi, stoxos);
							Node candidate = tempList.get(i - 1);
							Node maybe = tempSimia.get(candidate);
							if (maybe == null)
								continue;
							toAdd.addNode(maybe);
							metopo.add(toAdd);
						}
						else {  
							Node candidate = tempList.get(i - 1);
							Node maybe = tempSimia.get(candidate);
							if (maybe != null){
								Taxidi toAdd = new Taxidi(x, y, estimation, distance, heuristic, stathmoi, stoxos);
								toAdd.addNode(maybe);
								metopo.add(toAdd);
							}
							candidate = tempList.get(i + 1);
							maybe = tempSimia.get(candidate);
							if (maybe != null){
								Taxidi toAdd = new Taxidi(x, y, estimation, distance, heuristic, stathmoi, stoxos);
								toAdd.addNode(maybe);
								metopo.add(toAdd);
							}
						}
					}
				}
				tempSimia.remove(new Node(current.getX(), current.getY()));
				Collections.sort(metopo);
				current = metopo.removeFirst();
			}
			int id = taxi.getID();
			double dist = current.getDistance();
			ArrayList<Node> diadromi = current.getStathmoi();
			liseis.add(new TaxiRoute(id, dist, diadromi));	
		}
		// sorting our list of solutions returning them to the caller.
		Collections.sort(liseis);
		return liseis;
	}
}