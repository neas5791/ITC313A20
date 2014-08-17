package ITC313A2T1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class MapFrame extends JFrame{
	/* GUI Controls */
	private JButton in, out, load, save;
    private JCheckBox petrol, taxi, atm, hospital, shopping;
    private JLabel fileName;
    private JLabel scaleFactor; 
    
    /* ******************************************************** */
    /* I was thinking of adding some additional functionality	*/
    /* ******************************************************** */
    @SuppressWarnings("unused")
	private JButton add;
    @SuppressWarnings("unused")
	private JTextField _X, _Y, _Type;
    /* ******************************************************** */
    /* ******************************************************** */
    
    /* This is the Map control stuff  */
    private JLabel mapView;
    private double scale = 1.0;    
	
    /**
     * this method was used in construction of the GUI
     * it creates an instance of itself on the screen
     */
    public static void main (String[] args){
		java.awt.EventQueue.invokeLater(new Runnable(){
            public void run(){
                new MapFrame(750, new JLabel());
            }});
	}
    
	/**
	 * Constructor for the GUI. sets up the basic layout of the screen
	 * @param frameSize		the dimensions of the frame
	 * @param mapView		the Map image to be displayed
	 */
	public MapFrame(int frameSize, JLabel mapView) {
		
		// Set's up the Frame basics
		this.setTitle("Welcome to ITC313 Assignment Two");
		this.setSize(frameSize+50, frameSize+100);	
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		// Sets the layout style
		this.setLayout(new BorderLayout());
	
		// Adds the UI stuff buttons and checkboxes up the top
		this.add(new UserInterfacePanel(), BorderLayout.NORTH);
		
		this.mapView = mapView;
		// the map is added to the lower 
		this.add(this.mapView);
		
	    /* ******************************************************** */
	    /* I was thinking of adding some additional functionality	*/
	    /* to this part of the GUI									*/
		/* ******************************************************** */
		
		this.add(new FooterInterfacePanel(), BorderLayout.SOUTH);
	    
		/* ******************************************************** */
		/* ******************************************************** */
		
		// shows the frame on the screen
		this.setVisible(true);
	}
    
/*  *****************************************************************  */	
	
    /**
     * This is the guts of the GUI adds all the components into position
     * this class extends JPanel
     */
    private class UserInterfacePanel extends JPanel{
		
		private UserInterfacePanel (){
			in = new JButton("ZOOM IN");
            in.setActionCommand("IN");
            out = new JButton("ZOOM OUT");
            out.setActionCommand("OUT");
            load = new JButton("LOAD MAP");
            load.setActionCommand("LOAD");
            save = new JButton("SAVE MAP");
            save.setActionCommand("SAVE");
            petrol = new JCheckBox("Petrol");
            petrol.setSelected(true);
            taxi = new JCheckBox("Taxi Rank");
            taxi.setSelected(true);
            atm = new JCheckBox("ATM");
            atm.setSelected(true);
            hospital = new JCheckBox("Hospital");
            hospital.setSelected(true);
            shopping = new JCheckBox("Shopping Centre");
            shopping.setSelected(true);
			scaleFactor = new JLabel(String.format("X %d", (int)(10*scale)));
            
            this.setLayout(new GridLayout(2,6));

            this.setBorder(new LineBorder(Color.GREEN));
            this.add(petrol);
            this.add(taxi);
            this.add(atm);
            this.add(hospital);
            this.add(shopping);

            this.add(in);
            this.add(out);
            this.add(scaleFactor);
            this.add(load);
            this.add(save);
		}
	}
    
    /**
     * Adds footer functionality to GUI. Nothing real flash just a panel that shows 
     * the file name and path really... I was going to add the additional 
     * functionality of allowing a user to add a new POI on the fly. This POI would
     * then be added to the collection (theModel) and consequently saved to the 
     * file if needed
     */
    @SuppressWarnings({ })
	private class FooterInterfacePanel extends JPanel{
    	private FooterInterfacePanel(){
    		this.setLayout(new FlowLayout(FlowLayout.LEFT));
/* ====================================================	*/
/* =========the below code is a work in progress=======	*/    		
//    		add = new JButton("ADD NEW POINT");
//    		add.setActionCommand("ADD");
//    		
//    		_Type = new JTextField(10);
//    		_X = new JTextField(10);
//    		_Y = new JTextField(10);
//    		
//    		
//    		this.add(new JLabel("Type:"));
//    		this.add(_Type);
//    		this.add(new JLabel("X:"));
//    		this.add(_X);
//    		this.add(new JLabel("Y:"));
//    		this.add(_Y);
//    		this.add(add);
/* ====================================================	*/    		
    		fileName = new JLabel();
    		fileName.setFont(new Font("Tahoma", Font.ITALIC, 10));
			this.add(fileName);
    	}
    }
    
/*  *****************************************************************  */

    /**
     * Sets the scale factor of the GUI
     * @param newScale	the new scale factor to be applied to the map
     * 
     * NB as I type this document it has occurred to me that the variable scale
     * would be best moved to the controller class (after all the essence of
     * MVC pattern is to decouple the view from the other objects
     * 
     */
    public void setScale (double newScale){
    	
    	int temp = (int)(newScale * 10);
    	// Limit scale factor to values equivalent to 10% incrememnts
        if (temp <= 0 || temp > 10)
            return;
        
        this.scale = newScale;
        this.scaleFactor.setText(String.format("%20s %d", "X", ((int)(10*scale))));
    }

    /**
     * sets the JLable text display the current file and path
     * @param fileName	the string filename to display in GUI
     */
    public void setFileNameLabel(String fileName){
    	this.fileName.setText(fileName);
	}
    /**
     * sets the map image
     * @param theMap	is the JLabel that contains the Map image
     */
    public void setMap(JLabel theMap){
    	mapView = theMap;
    	this.add(mapView);
     }
    /**
     * sets the default close operation
     * @param closeOperation	the JFrame constant ie EXIT_ON_CLOSE
     */
    public void setCloseOperation(int closeOperation){
    	this.setDefaultCloseOperation(closeOperation);
    }
/*  *****************************************************************  */
    
    // Nothing to fancy here just plain old getters :)
    
	public boolean getPetrol() 		{ return petrol.isSelected(); 	}
	public boolean getTaxi() 		{ return taxi.isSelected(); 	}
	public boolean getATM() 		{ return atm.isSelected(); 		}
	public boolean getHospital() 	{ return hospital.isSelected(); }
	public boolean getShopping() 	{ return shopping.isSelected(); }
    public double getScale()		{ return this.scale;			}	
	
/*  *****************************************************************  */
    
    
    /**
     * adds the ActionListener to the buttons Zoom in and Zoom Out
     * @param listenForZoom	the ActionListener class that will listen for button click events
     */
    public void addZoomListener (ActionListener listenForZoom){
        in.addActionListener(listenForZoom);
        out.addActionListener(listenForZoom);
    }
    /**
     * adds the ActionListener to the Load button
     * @param listenForLoad	the ActionListener class that will listen for button click events
     */
    public void addLoadListener(ActionListener listenForLoad){
    	load.addActionListener(listenForLoad);
    }
    /**
     * adds the ActionListener to the save button
     * @param listenForSave the ActionListener class that will listen for button click events
     */
    public void addSaveListener(ActionListener listenForSave){
    	save.addActionListener(listenForSave);
    }
    /**
     * adds the ActionListener to the Checkboxes
     * @param ListenForCheck the ActionListener class that will listen for Checkbox events
     */
    public void addCheckListener(ActionListener ListenForCheck){
    	petrol.addActionListener(ListenForCheck);
    	taxi.addActionListener(ListenForCheck);
    	atm.addActionListener(ListenForCheck);
    	hospital.addActionListener(ListenForCheck);
    	shopping.addActionListener(ListenForCheck);
    }

}
