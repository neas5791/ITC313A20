/** 
 * 
 * @subject		ITC313
 * @author 		Sean Matkovich
 * @studentID	11187033
 * 
 *  This class implement task two on the assignment as detailed below
 * 
 *  My implementation use a model-view-controller pattern to decouple the User interface and the model classes
 *  
 *  
 *  Task 2 (40 marks) (a square inherits a rectangle)
 *  Assume a rectangle as depicted in the diagram bellow. Notice its specifications.
 *	
 *	=================  
 *  |	RECTANGLE	|
 *  |===============|
 *  |	base:int	|
 *  |---------------|
 *  |	height:int	|
 *  |+++++++++++++++|
 *  |	area:int	|
 *  +++++++++++++++++
 *  
 *  The diagram shows a class Rectangle. An object of this class represents a shape which has 
 *  two sides (base and height). These two sides should be kept in private to avoid misuse and 
 *  set to zero when a rectangle object is first created. There should be a function to set the value 
 *  of these two sides. Another function should calculate the area (base * height) of the rectangle.
 *  
 *	Part 1 (rectangle):
 *	Write a JAVA source code, to declare the Rectangle class considering above mentioned specifications.
 *	Write, in JAVA, the complete definition of the Rectangle class based on your declaration
 *	Write a fragment of client code which creates a Rectangle object, sets its base and height value to 8 and 6 respectively and displays its area.
 *
 *	Part 2 (square):
 *	Assume a Square class which would inherit the Rectangle defined in Part 1. An object of Square class will have both sides equal (i.e. base=height) 
 *	which are set to zero at the time of the creation of the object. The same functions (area and setValues) of the Rectangle class should be used in 
 *	Square class too. Write a JAVA source code declaration of the Square class inheriting the Rectangle class to behave according to the specification 
 *	given above. Write a fragment of client code which creates a Square object, sets its side to 8 and displays its area.
 *
 */

package ITC313A2T2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.AbstractButton;
import javax.swing.JFrame;
import javax.swing.JTextField;


/**
 *
 * @author Seaboard
 */
public class Controller {
	// MODEL
    private Rectangle theModel;
    // VIEW
    private TaskView theView;
    
    
    /**
     * Default constructor
     */
    public Controller(){
    	// initialize the GUI
        TaskView theView = new TaskView();
        
        // initialize the model
        Rectangle theModel = new Rectangle();
        
        
//        Part One of the task specifies
//        "Write a fragment of client code which creates a Rectangle object, 
//        sets its base and height value to 8 and 6 respectively and displays its area."
        
        // Setup object as Rectangle with dimensions
        theModel.setBase(8);
        theModel.setHeight(4);
        
        
        // Setup up initial view contents
        theView.setBase(theModel.getBase());
        theView.setHeight(theModel.getHeight());
        theView.setArea(theModel.getArea());
        
        theView.setCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        @SuppressWarnings("unused")
		Controller theController = new Controller(theView, theModel);
                
//        theView.setVisible(true);
    }
    
    /**
     * Private constructor adds action listeners and set Class variables
     * @param theView	the GUI
     * @param theModel	the data object, in this case a Rectangle object
     */
    private Controller(TaskView theView, Rectangle theModel){
        this.theModel = theModel;
        this.theView = theView;
        
        this.theView.addCalculateListener(new CalculateListener());
        this.theView.addRadioListener(new RadioListener());
        this.theView.addFocusListener(new TextListener());
    }
    
    /**
     * This private class provides the actions required whenever a the radio buttons are operated
     */
    private class RadioListener implements ItemListener{

        @Override
        public void itemStateChanged(ItemEvent e) {
        	try {
				AbstractButton aButton = (AbstractButton) e.getSource();
         
				if (aButton.getText() == "Square" && e.getStateChange() ==  ItemEvent.SELECTED) {
					theModel =  new Square(8);
//					We can comment out the above line and use the below line to keep the existing object details
//				    theModel =  new Square(theView.getBaseValue());
				    theView.setShape(e);
				    updateView();
				}
				else if (aButton.getText() == "Rectangle" && e.getStateChange() ==  ItemEvent.SELECTED) {
					theModel = new Rectangle(8, 6);
//					We can comment out the above line and use the below line to keep the existing object details
//					theModel = new Rectangle(theView.getBaseValue(), theView.getHeightValue() );
				    theView.setShape(e);
				    updateView();
				}
				updateArea();
			}             
        	catch (ArithmeticException ex){
                System.out.println(ex + "RadioListener");
            }
        }
    }

    /**
     * This private class provides the actions required whenever a the Calculate button is clicked
     */
	private class CalculateListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            
            try {
                theModel.setBase(theView.getBaseValue());
                if (!(theModel instanceof Square))
                    theModel.setHeight(theView.getHeightValue());
                theView.setArea(theModel.getArea());
                updateView();
            }
            catch(NumberFormatException ex){
                System.out.println(ex);
                theView.displayErrorMessage("You need to enter 2 Integers");
            }
            catch (IllegalArgumentException ex) {
                System.out.println(ex);
                theView.displayErrorMessage("You have to enter positive numbers silly!");
            }
            catch (ArithmeticException ex){
                System.out.println(ex + "CalculateListener");
//                theView.displayErrorMessage(ex.getMessage());
//                requestFocusInWindow(theView.getBaseTxtField());
//                theView.setBaseFocus();
            }
        }
    }
    
    /**
     * This private class provides the actions required whenever the JTextFields lose or gain focus
     */
	private class TextListener implements FocusListener{
        
        @Override
        public void focusGained(FocusEvent e) {
            if (e.isTemporary()) return;
            
            if (e.getSource() instanceof javax.swing.JButton)
            	theView.setBaseFocus();
            // Selects all of the text in the current component
            ((JTextField)(e.getSource())).selectAll();
        }

        @Override
        public void focusLost(final FocusEvent e) {
            
            if (e.isTemporary()) return;

            try {
                // Before updating model and view the JTextField needs to be is checked
                // isInteger uses regex pattern to check for conformity
                if (isDouble(((	(JTextField) e.getSource()	)).getText())){
                	double temp = Double.parseDouble(((	(JTextField) e.getSource()	)).getText());
                	((	(JTextField) e.getSource()	)).setText(String.format("%.0f", temp));
                } 
                else if(!isInteger(	((	(JTextField) e.getSource()	)).getText()	)){
                	System.out.println("No Integers here man");
                	requestFocusInWindow((JTextField) e.getSource());
                	return;
                }
                else {
                	System.out.println("The string is an Integers");
                }

                // Updates the model with new characteristics
                updateModel();
                // Updates the view to reflect new model
//                updateView();
                updateArea();
            }
            catch(NumberFormatException ex){
                System.out.println(ex);
                theView.displayErrorMessage("I'm Sorry but you need to enter an Integer (TextListener)");
            }
            catch (IllegalArgumentException ex) {
                System.out.println(ex);
                theView.displayErrorMessage("I only like positive numbers silly!");
            }
            catch (ArithmeticException ex){
//                if (theView.isSquare() || ((JTextField) e.getSource()).getName() == "heightTxt") return;
            	System.out.println(ex + "FocusListener");
                theView.displayErrorMessage(ex.getMessage());
                requestFocusInWindow((JTextField) e.getSource());
            }
        }
    }    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        TaskView theView = new TaskView();
        Rectangle theModel = new Rectangle();
        
        // Setup object as Rectangle with dimensions
        theModel.setBase(8);
        theModel.setHeight(6);
        
        // Setup up initial view contents
        theView.setBase(theModel.getBase());
        theView.setHeight(theModel.getHeight());
        theView.setArea(theModel.getArea());
        
        @SuppressWarnings("unused")
		Controller theController = new Controller(theView, theModel);
                
        theView.setVisible(true);
    }
    
    /**
     * Private method used to fill the GUI with object information 
     */
    private void updateView(){
        theView.setBase(theModel.getBase());
        
        if (theView.isSquare())
        	theView.setHeight(0);
        else
        	theView.setHeight(theModel.getHeight());
        
//        theView.setArea(theModel.getArea());
    }
    
    /**
     * Private method that specifically sets the GUI with object area information
     */
    private void updateArea(){
    	theView.setArea(theModel.getArea());
    }
    
    /**
     * Private method that collects data from the GUI and update the object model
     */
    private void updateModel(){
    	if (!theView.isSquare())
    		theModel.setHeight(theView.getHeightValue());
    	
        theModel.setBase(theView.getBaseValue());
    }
    
    /**
     * Private method to test if string an acceptable double format
     * @param str	string to be tested
     * @return	true if the string matches the double format, all other cases return false
     */
    private boolean isDouble(String str) {
    	return (str.matches("[0-9]+[.][0-9]+") || str.matches("[.][0-9]+"));
    }

    /**
     * Private method to test if string an acceptable integer format
     * @param str	string to be tested
     * @return	true if the string matches the integer format, all other cases return false
     */
    private boolean isInteger(String str){
        return str.matches("[0-9]+");
    }
   
    /**
     * Private method for requesting focus of a JTextfiled in the GUI
     * @param jf	is a JTextfield that focus is requested on
     */
    private void requestFocusInWindow(JTextField jf){
        System.out.println("Give me focus Man! "+ jf.getText());
        jf.requestFocus();
    }
}
