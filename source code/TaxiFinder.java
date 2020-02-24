import java.io.*;
import java.util.*;

// The "main" of our program. The output is also produced here.
public class TaxiFinder{
	public static void main (String args[]){
		String color[]= {"green", "red", "black","yellow", "blue", "brown","purple", "orange", "grey","sky","pink"};
		int i = 0;
		Astar myS = new Astar();
		LinkedList<TaxiRoute> liseis;

		myS.readInputs();
		liseis = myS.solve();

		// Producing the kml file.
		try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("result.kml"), "utf-8"))) {
			writer.write("<?xml version=" + '"' + "1.0" +  '"' + " encoding=" + '"' +"UTF-8" +  '"' + "?>\n");
			writer.write("\t<kml xmlns=" + '"' +"http://earth.google.com/kml/2.1" + '"' + ">\n");	
			writer.write("\t\t<Document>\n");																		
			writer.write("\t\t<name>Taxi Routes</name>\n");										
			writer.write("\t\t\t<Style id=" + '"' + "green" + '"' + ">\n");											
			writer.write("\t\t\t\t<LineStyle>\n");																		
			writer.write("\t\t\t\t\t<color>ff009900</color>\n");															
			writer.write("\t\t\t\t\t<width>4</width>\n");																	
			writer.write("\t\t\t\t</LineStyle>\n");																		
			writer.write("\t\t\t</Style>\n");																			
			writer.write("\t\t\t<Style id=" + '"' + "pink" + '"' + ">\n");											
			writer.write("\t\t\t\t<LineStyle>\n");																		
			writer.write("\t\t\t\t\t<color>507800F0</color>\n");															
			writer.write("\t\t\t\t\t<width>4</width>\n");																	
			writer.write("\t\t\t\t</LineStyle>\n");																		
			writer.write("\t\t\t</Style>\n");																			
			writer.write("\t\t\t<Style id=" + '"' + "brown" + '"' + ">\n");											
			writer.write("\t\t\t\t<LineStyle>\n");																		
			writer.write("\t\t\t\t\t<color>50143C6E</color>\n");															
			writer.write("\t\t\t\t\t<width>4</width>\n");																	
			writer.write("\t\t\t\t</LineStyle>\n");																		
			writer.write("\t\t\t</Style>\n");																			
			writer.write("\t\t\t<Style id=" + '"' + "grey" + '"' + ">\n");											
			writer.write("\t\t\t\t<LineStyle>\n");																		
			writer.write("\t\t\t\t\t<color>50828282</color>\n");															
			writer.write("\t\t\t\t\t<width>4</width>\n");																	
			writer.write("\t\t\t\t</LineStyle>\n");																		
			writer.write("\t\t\t</Style>\n");																			
			writer.write("\t\t\t<Style id=" + '"' + "black" + '"' + ">\n");											
			writer.write("\t\t\t\t<LineStyle>\n");																		
			writer.write("\t\t\t\t\t<color>50000000</color>\n");															
			writer.write("\t\t\t\t\t<width>4</width>\n");																	
			writer.write("\t\t\t\t</LineStyle>\n");																		
			writer.write("\t\t\t</Style>\n");																			
			writer.write("\t\t\t<Style id=" + '"' + "yellow" + '"' + ">\n");											
			writer.write("\t\t\t\t<LineStyle>\n");																		
			writer.write("\t\t\t\t\t<color>5014F0FF</color>\n");															
			writer.write("\t\t\t\t\t<width>4</width>\n");																	
			writer.write("\t\t\t\t</LineStyle>\n");																		
			writer.write("\t\t\t</Style>\n");																			
			writer.write("\t\t\t<Style id=" + '"' + "sky" + '"' + ">\n");												
			writer.write("\t\t\t\t<LineStyle>\n");																		
			writer.write("\t\t\t\t\t<color>50F0FF14</color>\n");															
			writer.write("\t\t\t\t\t<width>4</width>\n");																	
			writer.write("\t\t\t\t</LineStyle>\n");																		
			writer.write("\t\t\t</Style>\n");																			
			writer.write("\t\t\t<Style id=" + '"' + "orange" + '"' + ">\n");											
			writer.write("\t\t\t\t<LineStyle>\n");																		
			writer.write("\t\t\t\t\t<color>501478E6</color>\n");															
			writer.write("\t\t\t\t\t<width>4</width>\n");																	
			writer.write("\t\t\t\t</LineStyle>\n");																		
			writer.write("\t\t\t</Style>\n");																			
			writer.write("\t\t\t<Style id=" + '"' + "purple" + '"' + ">\n");											
			writer.write("\t\t\t\t<LineStyle>\n");																		
			writer.write("\t\t\t\t\t<color>50780078</color>\n");															
			writer.write("\t\t\t\t\t<width>4</width>\n");																	
			writer.write("\t\t\t\t</LineStyle>\n");																		
			writer.write("\t\t\t</Style>\n");																			
			writer.write("\t\t\t<Style id=" + '"' + "blue" + '"' + ">\n");											
			writer.write("\t\t\t\t<LineStyle>\n");																		
			writer.write("\t\t\t\t\t<color>50F00014</color>\n");															
			writer.write("\t\t\t\t\t<width>4</width>\n");																	
			writer.write("\t\t\t\t</LineStyle>\n");																		
			writer.write("\t\t\t</Style>\n");																				
			writer.write("\t\t\t<Style id=" + '"' + "red" + '"' + ">\n");												
			writer.write("\t\t\t\t<LineStyle>\n");																		
			writer.write("\t\t\t\t\t<color>ff0000ff</color>\n");															
			writer.write("\t\t\t\t\t<width>4</width>\n");																		
			writer.write("\t\t\t\t</LineStyle>\n");																		
			writer.write("\t\t\t</Style>\n");			
			for (TaxiRoute lisi:liseis){
				ArrayList<Node> temp = lisi.getRoute();
			writer.write("\t\t\t<Placemark>\n");											
			writer.write("\t\t\t\t<name>Taxi "+ lisi.getID() + "</name>\n");									
			writer.write("\t\t\t\t<styleUrl>#" + color[i] + "</styleUrl>\n");	
			i++;					
			writer.write("\t\t\t\t<LineString>\n");													
			writer.write("\t\t\t\t\t<altitudeMode>relative</altitudeMode>\n");					
			writer.write("\t\t\t\t\t<coordinates>\n");
				for (int j = 0; j < temp.size(); j++){
					writer.write("\t\t\t\t\t\t" + temp.get(j).getX()+ "," + temp.get(j).getY() + "\n");
				}
			writer.write("\t\t\t\t\t</coordinates>\n");												
			writer.write("\t\t\t\t</LineString>\n");												
		    writer.write("\t\t\t</Placemark>\n");		
			}
			writer.write("\t\t</Document>\n");		
			writer.write("\t</kml>\n");
		}
		catch (IOException e){
			e.printStackTrace();
		}

		// Producing a txt file with the closer taxis.
		try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("result.txt"), "utf-8"))) {
			int l = 1;
			for (TaxiRoute lisi:liseis){
				writer.write("The number " + l + " solution is Taxi: " + lisi.getID()+ "\n");
				l++;
			}	
		}
		catch (IOException e){
			e.printStackTrace();
		}
	}
}