/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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

    private Rectangle theModel;
    private TaskView theView;
    
    public Controller(){
        TaskView theView = new TaskView();
        Rectangle theModel = new Rectangle();
        
        // Setup object as Rectangle with dimensions
        theModel.setBase(6);
        theModel.setHeight(4);
        
        // Setup up initial view contents
        theView.setBase(theModel.getBase());
        theView.setHeight(theModel.getHeight());
        theView.setArea(theModel.getArea());
        
        theView.setCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        @SuppressWarnings("unused")
		Controller theController = new Controller(theView, theModel);
                
        theView.setVisible(true);
    }
    
    public Controller(TaskView theView, Rectangle theModel){
        this.theModel = theModel;
        this.theView = theView;
        
        this.theView.addCalculateListener(new CalculateListener());
        this.theView.addRadioListener(new RadioListener());
        this.theView.addFocusListener(new TextListener());
    }
    
    private class RadioListener implements ItemListener{

        @Override
        public void itemStateChanged(ItemEvent e) {
        	try {
				AbstractButton aButton = (AbstractButton) e.getSource();
         
				if (aButton.getText() == "Square" && e.getStateChange() ==  ItemEvent.SELECTED) {
				    theModel =  new Square(theView.getBaseValue());
				    theView.setShape(e);
				    updateView();
				}
				else if (aButton.getText() == "Rectangle" && e.getStateChange() ==  ItemEvent.SELECTED) {
				    theModel = new Rectangle(theView.getBaseValue(), theView.getHeightValue() );
				    theView.setShape(e);
				    updateView();
				}
				updateArea();
			}             
        	catch (ArithmeticException ex){
                System.out.println(ex);
                theView.displayErrorMessage(ex.getMessage());
                requestFocusInWindow(theView.getBaseTxtField());
            }
        }
    }

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
                theView.displayErrorMessage("You have toenter positive numbers silly!");
            }
            catch (ArithmeticException ex){
                System.out.println(ex);
                theView.displayErrorMessage(ex.getMessage());
                requestFocusInWindow((JTextField) e.getSource());
            }
        }
    }
    
    private class TextListener implements FocusListener{
        
        @Override
        public void focusGained(FocusEvent e) {
            if (e.isTemporary()) return;
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
            	System.out.println(ex);
                theView.displayErrorMessage(ex.getMessage() + " FocusLost");
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
        theModel.setBase(6);
        theModel.setHeight(4);
        
        // Setup up initial view contents
        theView.setBase(theModel.getBase());
        theView.setHeight(theModel.getHeight());
        theView.setArea(theModel.getArea());
        
        @SuppressWarnings("unused")
		Controller theController = new Controller(theView, theModel);
                
        theView.setVisible(true);
    }
    
    private void updateView(){
        theView.setBase(theModel.getBase());
        
        if (theView.isSquare())
        	theView.setHeight(0);
        else
        	theView.setHeight(theModel.getHeight());
        
//        theView.setArea(theModel.getArea());
    }
    
    private void updateArea(){
    	theView.setArea(theModel.getArea());
    }
    
    private void updateModel(){
    	if (!theView.isSquare())
    		theModel.setHeight(theView.getHeightValue());
    	
        theModel.setBase(theView.getBaseValue());
    }
    
    private boolean isDouble(String str) {
    	return (str.matches("[0-9]+[.][0-9]+") || str.matches("[.][0-9]+"));
    }

    private boolean isInteger(String str){
        return str.matches("[0-9]+");
    }
   
    private void requestFocusInWindow(JTextField jf){
        System.out.println("Give me focus Man! "+ jf.getText());
        jf.requestFocus();
    }
}
