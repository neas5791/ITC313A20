/** 
 * 
 * @subject		ITC313
 * @author 		Sean Matkovich
 * @studentID	11187033
 * 
 * This class implement task one on the assignment as detailed below
 * 
 * My implementation use a model-view-controller pattern which reduce 
 * dependency between User interface and the model 
 *  
 * Task 1 (60 marks) (plotting some POIs on a graph/map)
 * 
 * Part 1: 
 * Several types of point of interests (POI) such as - 
 * 		(1) Petrol station
 * 		(2) Taxi Stand
 * 		(3) ATM
 * 		(4) Hospital
 * 		(5) Shopping centre
 * are located in a city. Their locations (longitude and latitude) 
 * are provided in a text file (a file will be provided in the 
 * resource (a copy of this file is located in the root directory of this
 * project) section of the subject site, or you can create your own 
 * dummy text file according to the format you prefer). You must 
 * work with at least two type/kind of POI and at least 20 samples for 
 * each type (e.g. your text file should contain the location of 20 petrol 
 * stations in a city). You have to write a JAVA program that would get 
 * the locations of all the POIs from the file and plot them on a map (graph). 
 * Optional: Save the map/graph in a file if the user wants to.
 * 
 * Part 2: 
 * incorporate a mechanism to zoom in/out the map either by having 
 * buttons or with the help of the scroll button.
 *
 */



package ITC313A2T1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.HashSet;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;


public class Controller {
	
	// MODEL
	private HashSet<POI> theModel;
	// VIEW
	private MapFrame theView;
	// the map image
	private BufferedImage theMapImage;
	// the file we are reading from
	private File theFile;
	// used to display the map image
	private JLabel theMap;
	
	public Controller(){
		
		// initialize the GUI
		MapFrame theView = new MapFrame(750, new JLabel());
		
		// initialize the model (in the case of this task a collection of POI's)
		HashSet<POI> theModel = new HashSet<POI>();
		
		// set the default c=lose operation for this instance of the GUI
		theView.setCloseOperation(JFrame.DISPOSE_ON_CLOSE);		
		
		// initialize the controller class for this GUI 
		@SuppressWarnings("unused")
		Controller theController = new Controller(theView, theModel);
		

	}
	
	/**
	 * Private constructor class for the ITC313A2 TASK ONE this constructor 
	 * further build on the Model-View-Controller (MVC) pattern
	 *  
	 * @param theView	an instance of the GUI
	 * @param theModel	an instance of the model that will
	 */
	private Controller(MapFrame theView, HashSet<POI> theModel){
		// Create the BufferedImage where the POI Map details will be displayed
		theMapImage = new BufferedImage(750,750,BufferedImage.TYPE_INT_RGB);
		theMap = new JLabel(new ImageIcon(theMapImage));
		setMap();
		
		this.theView = theView;
		this.theView.setMap(theMap);
		
		
		// Adds the action listeners for the interface items in the  
		// views GUI
		this.theView.addZoomListener(new Zoom());
		this.theView.addLoadListener(new Load());
		this.theView.addSaveListener(new Save());
		this.theView.addCheckListener(new Check());
		
		// This is the default file unedited and as supplied in the resource section
		// of the assignment
        theFile = new File("Ass1_Task1_POIs.txt");
        
        // Sets the initial scale of the POI map
        theView.setScale(1.0);
        theView.setFileNameLabel("Ass1_Task1_POIs.txt");
        
        // Builds the initial map view for the gui
        buildTheModel();
	}

	/**
	 * Default main method used to develop the class
	 * @param args	String args not used in this implementation
	 */
	public static void main(String[] args){
		// Add with BufferedImage
		//MapFrame theView = new MapFrame(750, new JLabel(new ImageIcon(new BufferedImage(750,750,BufferedImage.TYPE_INT_RGB))));
		MapFrame theView = new MapFrame(750, new JLabel());
		HashSet<POI> theModel = new HashSet<POI>();
		
		@SuppressWarnings("unused")
		Controller theController = new Controller(theView, theModel);
		theView.setVisible(true);
	}

	
	/**
	 * Private method used to paint the POI MAP with longitude and latitude point 
	 * information described in the loaded file
	 */
	private void setMap(){
		
		// create a temporary graphics item to paint
		Graphics g = theMapImage.getGraphics();
		
		// this checks if theModel is empty or no File has yet been opened
		if (theModel == null || theModel.size() == 0) {
			System.out.println("Blank Map Image");
			g.setColor(Color.LIGHT_GRAY);
			g.fillRect(0, 0, theMapImage.getWidth(),theMapImage.getHeight());
			g.dispose();
			theMap.repaint();
			return;
		}

		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 0, theMapImage.getWidth(),theMapImage.getHeight());

		// Pick color based on which TYPE attribute of POI object
		// creates an iterator to allow us to move through the POI Collection
		Iterator<POI> it = theModel.iterator();
		
        while(it.hasNext()){
            POI temp = (POI) it.next();
            
            switch (temp.getType()){
            	case 1:
            		g.setColor(Color.BLACK);
            		break;
            	case 2:
            		g.setColor(Color.YELLOW);
            		break;
            	case 3:
            		g.setColor(Color.CYAN);
            		break;
            	case 4:
            		g.setColor(Color.GREEN);
            		break;
            	case 5:
            		g.setColor(Color.RED);
            		break;
        		default:
        			g.setColor(Color.PINK);
            		break;
            }
            
            // the radius/size of the dot used is scaled relative to the overall map scale
            int radius = (int) ( ((theView.getScale()<0.3)?0.3:theView.getScale()) * 15);
            
            // calculates the scaled form of the co ordinates supplied in the POI object
            int x = (int) (theView.getScale() * temp.getX()*(theMapImage.getWidth()/100));
            int y = (int) (theView.getScale() * temp.getY()*(theMapImage.getWidth()/100));
            // creates the dot on the POI Map
            g.fillOval(x, y, radius, radius);
        }
        // Logs to the system has been set with new details
		System.out.println("Set the map with new details");
		
		// clean up graphics object.. Just housekeeping
		g.dispose();
		
		// specific call for the MAP to be repainted
		theMap.repaint();
	} 

	/**
	 * Private method used to save the current Collection of POI objects to file.
	 * @param saveFileName	the destination file
	 */
	private void saveTheModel(File saveFileName){
		BufferedWriter bw;
		String line;
		
		// Check to see if the current POI collection has any objects in it
		// if the collection doesn't contain anything then nothing occurs
		if (theModel.size() == 0){
			System.out.println("Nothing Saved because the model has nothing too save :(");
			return;
		}
		
		try{
			// instantiate BufferedWritter object
			bw = new BufferedWriter(new FileWriter(saveFileName));
			
			// initializes iterator to allow us to move through the collection
			Iterator<POI> it =  theModel.iterator();
			
			// Loop through each object in the collection
			while(it.hasNext()){
				POI temp = (POI) it.next();
				// creates a formatted string of the object contents to output to file
				line = String.format("%s\t%s\t%s", temp.getType(), temp.getX(), temp.getY());
				// writes the string to the file and moves to the next line
				bw.write(line);
				bw.newLine();
			}
			// housekeeping.... cleans up and closes the BufferedWritter object
			bw.close();
		}
		catch (IOException e){
			// outputs message to system out
			System.err.println("not sure but somethings gone wrong");
			e.printStackTrace();
		}
	}
	
	/**
	 * This private method opens a formatted string file and inputs each line as
	 * a new POI object... These objects are added to theModel (data)
	 */
	private void buildTheModel(){
		BufferedReader br;
        String line;
        
        // creates a new collection for theModel
        theModel = new HashSet<POI>();

        try{
            // creates a BufferedReader to access File with Object data as supplied by ITC313
            br = new BufferedReader(new FileReader(theFile));

            // loop through the file reading each line
            while ((line = br.readLine()) != null){

                // breaks the line into parts
                String[] t1 = line.split("\t");
                
                // Selectively builds the POI based on theView conditions
                //	the below line is an excerpt from the Task criteria
                // (1) petrol station, (2) Taxi Stand, (3) ATM, (4) Hospital and (5) Shopping centre
                if (theView.getATM() && Integer.parseInt(t1[0]) == 3)
                	theModel.add(new POI(Integer.parseInt(t1[0]), Double.parseDouble(t1[1]), Double.parseDouble(t1[2])));
                
                if(theView.getHospital() && Integer.parseInt(t1[0]) == 4)
                	theModel.add(new POI(Integer.parseInt(t1[0]), Double.parseDouble(t1[1]), Double.parseDouble(t1[2])));
                
                if(theView.getPetrol() && Integer.parseInt(t1[0]) == 1)
                	theModel.add(new POI(Integer.parseInt(t1[0]), Double.parseDouble(t1[1]), Double.parseDouble(t1[2])));
                
                if(theView.getShopping() && Integer.parseInt(t1[0]) == 5)
                	theModel.add(new POI(Integer.parseInt(t1[0]), Double.parseDouble(t1[1]), Double.parseDouble(t1[2])));
                
                if(theView.getTaxi() && Integer.parseInt(t1[0]) == 2)
                	theModel.add(new POI(Integer.parseInt(t1[0]), Double.parseDouble(t1[1]), Double.parseDouble(t1[2])));
            }
            
            // adds the POI to theModel
            Iterator<POI> it = theModel.iterator();
            
            // System out reads through the collection and outputs the objects to string
            // for reference only purposes 
            int i = 0;
            while (it.hasNext()){
                System.out.println(i+"\t"+it.next());
                i++;
            }
            
            // housekeeping.... clean up and close the BufferedReader object realease resources that have been attached
            br.close();
            
            // paints the POI Map with new collect model information
            setMap();
        }
        catch (IOException e){
            System.err.println( "Error - cant create the file." );
            return;
        }
	}
	
	/**
	 * This private class provides the actions required whenever a zoom function is called
	 */
	private class Zoom implements ActionListener {

	    @Override
	    public void actionPerformed(ActionEvent e) {
	    	// quick escape if no map is loaded  ie buttons do nothing
	        if (theFile == null || theModel.size() == 0)
	        	return;
	        // figure out which button is pressed and alter the scale accordingly
	        if (e.getActionCommand() == "IN"){
	        	theView.setScale(theView.getScale() + 0.1);
	            System.out.println(String.format("%.2f You pressed zoom in", theView.getScale()));
	        }
	        else if (e.getActionCommand() == "OUT") {
	        	theView.setScale(theView.getScale() - 0.1);
	            System.out.println(String.format("%.2f You pressed zoom out", theView.getScale()));
	        }
	        
            // paints the scale changes to the POI Map
	        setMap();
	    }
	}
	
	/**
	 * Opens a JFileChooser frame and opens the map file for display
	 */
	private class Load implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // This opens JFileChooser and System.out's the filename
            JFileChooser fc = new JFileChooser(".");
            fc.setDialogTitle("Open ITC313 Map file");
            // sets up a file extension filter
            FileFilter filter = new FileNameExtensionFilter("ITC313 Map File (*.map, *.txt)", "map", "txt");
            fc.setFileFilter(filter);
            
            // sets up a variable to record the button pressed in JFileChooser window
            int returnValue = fc.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
            	// sets theFile class variable to the selected file details
                theFile = fc.getSelectedFile();
                System.out.println(theFile.getName());
            }
            else {
            	return;
            }
            // initializes the Map with a scale of 1.0
            theView.setScale(1.0);
            // displays the file name and path in the GUI (theView)
            theView.setFileNameLabel(theFile.getAbsoluteFile() + "\\" + theFile.getName());
            
            System.out.println(theFile.getAbsoluteFile() + "\\" + theFile.getName());
            
            // builds the POI collection and maps it to the screen 
            buildTheModel();
        }
	}

	/**
	 * Opens a JFileChooser frame to allow for user to save the displayed collection to file
	 */
	private class Save implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// If the model has no data don't do anything
			if (theModel.size() == 0)
				return;
			
			// file save name
			File theSaveFile;
			
			// Open JFileChooser dialog
			JFileChooser fc = new JFileChooser(".");
			
			fc.setDialogType(JFileChooser.SAVE_DIALOG );
			fc.setDialogTitle("Save ITC313 Map file");
			// Sets extension filter to ".map"
			FileFilter filter = new FileNameExtensionFilter("ITC313 Map File (*.map)", "map");
			fc.setFileFilter(filter);

			int returnValue = fc.showSaveDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
            	            	
            	// Just doin some house keeping making sure only one file extension is added
            	String str = fc.getSelectedFile().getName();
    			String[] _saveName = str.split("\\.");
            	
    			// just does some crazy stuff to make sure the file extension isn't added and added and added!!!
    			if (_saveName.length > 1)
    				theSaveFile = new File( fc.getCurrentDirectory() + "\\" +_saveName[0] + "." + _saveName[_saveName.length-1]);
    			else
    				theSaveFile = new File(fc.getSelectedFile() + "." + ((FileNameExtensionFilter) fc.getFileFilter()).getExtensions()[0]);
    		
    			// checks if the file already exists and gets user input
    			if (theSaveFile.isFile()){
    				
    				int actionDialog = JOptionPane.showConfirmDialog(null, "The file already exists");	
   				
    	            switch(actionDialog){
    	                case JOptionPane.YES_OPTION:
    	                    System.out.println("YES OVERWRITE FILE");
    	                    saveTheModel(theSaveFile);
    	                    return;
    	                case JOptionPane.NO_OPTION:
    	                	JOptionPane.showMessageDialog(null, "File not saved");
    	                	System.out.println("NO DONT OVERWIRTE");
    	                    return;
    	                case JOptionPane.CLOSED_OPTION:
    	                	System.out.println("CLOSED");
    	                	return;
    	                case JOptionPane.CANCEL_OPTION:
    	                	System.out.println("CANCEL");
    	                    return;
    	                default:
    	                	System.out.println("DEFAULT");
    	                	break;
    	            }
    			}
                System.out.println("Your file is being saved to " + theSaveFile);
                saveTheModel(theSaveFile);
            }
		}
	}
	
	/**
	 * Provides the functionality for the check boxes
	 */
	private class Check implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// check to ensure theModel has been instantiate
			if (theModel == null)
				return;
			
			// builds the new model information and paints to GUI
			buildTheModel();
		}
	}
}
