package ITC313A2T2;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class TaskView extends JFrame{
	/* GUI Controls */
    private JTextField baseTxt;
    private JTextField heightTxt;
    private JTextField areaTxt;
    private JButton calculateButton;
    private ButtonGroup buttonGroup;
    private JRadioButton rectangleRadio;
    private JRadioButton squareRadio;
    
    
    /**
     * Default constructor sets up and creates JFrame 
     */
    public TaskView(){
        initComponents();
        
        // after frame has been set up the object is made visible
        this.setVisible(true);
    }
    
    /**
     * Initializes swing components and set's frame layout
     */
    private void initComponents(){

    	JLabel baseLbl;
        JLabel heightLbl;
        JLabel areaLbl;
        JLabel label1;
        
        
    	// Set up the view and add the components
        JPanel taskPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
        
        label1 = new JLabel(
                    String.format("%-55s", "Task two - The Area Progaram"), FlowLayout.LEFT);
        taskPanel.add(label1);
        
        buttonGroup = new ButtonGroup();
        rectangleRadio = new JRadioButton("Rectangle",true);
        squareRadio = new JRadioButton("Square");
        JLabel fillLabel = new JLabel(String.format("%-25s", " "));

        // Sets up radio buttons as group
        buttonGroup.add(rectangleRadio);
        buttonGroup.add(squareRadio);
        taskPanel.add(rectangleRadio);
        taskPanel.add(squareRadio);
        taskPanel.add(fillLabel);
        
        
        baseLbl = new JLabel(String.format("%-8s", "Base:"),FlowLayout.LEFT);
        baseTxt = new JTextField(17);
        baseTxt.setActionCommand("BASE");
        taskPanel.add(baseLbl);
        taskPanel.add(baseTxt);
        
        heightLbl = new JLabel(String.format("%-8s", "Height:"),FlowLayout.LEFT);
        heightTxt = new JTextField(17);
        heightTxt.setActionCommand("HEIGHT");
        taskPanel.add(heightLbl);
        taskPanel.add(heightTxt);
        
        areaLbl = new JLabel(String.format("%-8s","Area:"));
        areaTxt = new JTextField(17);
        areaTxt.setEditable(false);
        taskPanel.add(areaLbl);
        taskPanel.add(areaTxt); 
        
        calculateButton = new JButton("Calculate");
        taskPanel.add(calculateButton, BorderLayout.SOUTH);
        
        this.add(taskPanel);
        
        setTitle("ITC313 Assignment Two");
        setSize(275,300);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
/*  *****************************************************************  */
    
    // Nothing to fancy here just plain old getters :)    
    
    public int getBaseValue(){
        return Integer.parseInt(baseTxt.getText());
    }
    
    public int getHeightValue(){
        return Integer.parseInt(heightTxt.getText());
    }
    
/*  *****************************************************************  */
    
    // Nothing to fancy here just plain old setters :)
    
    public void setArea(int solution){
        areaTxt.setText(String.format("%,d", solution));
    }
    
    public void setBase(int base){
        baseTxt.setText(Integer.toString(base));
    }
    
    public void setHeight(int height){
        heightTxt.setText(Integer.toString(height));
//    	heightTxt.setText(height);
    }

    public void setShape(ItemEvent e) {
        
        AbstractButton aButton = (JRadioButton) e.getSource();
        
        if (aButton.getText() == "Square" && e.getStateChange() ==  ItemEvent.SELECTED){ 
            heightTxt.setEditable(false);
            heightTxt.setFocusable(false);
        }
        else{
            heightTxt.setEditable(true);
            heightTxt.setFocusable(true);
        }
    }
/*  *****************************************************************  */
    
    /**
     * adds the ActionListener to the calculate button
     * @param listenForCalcButton	the ActionListener class that will listen for button click events
     */
    public void addCalculateListener(ActionListener listenForCalcButton){
        calculateButton.addActionListener(listenForCalcButton);
    }
    
    /**
     * adds the ActionListener to the radio buttons
     * @param listenForRadio	the ActionListener class that will listen for button click events
     */
    public void addRadioListener(ItemListener listenForRadio) {
        rectangleRadio.addItemListener(listenForRadio);
        squareRadio.addItemListener(listenForRadio);
    }
    
    /**
     * adds the ActionListener to the JTextField's focus events
     * @param listenForText 	the ActionListener class that will listen for button click events
     */
    public void addTextFocusListener(FocusListener listenForText){
        baseTxt.addFocusListener(listenForText);
        heightTxt.addFocusListener(listenForText);
    }
    
    /**
     * Allows for messages to be past to be display message box
     * @param errorMessage	the String message for the message box
     */
    public void displayErrorMessage(String errorMessage){
        JOptionPane.showMessageDialog(this, errorMessage);
    }

    /**
     * This method checks the state of the Square Radio check button
     * @return	true if Square radio is checked
     */
    public boolean isSquare(){
    	return squareRadio.isSelected();
    }
    
    /**
     * Sets focus to the BaseText field
     */
    public void setBaseFocus(){
    	baseTxt.selectAll();
    }
    
    /**
     * sets the default close operation
     * @param closeOperation	the JFrame constant ie EXIT_ON_CLOSE
     */
    public void setCloseOperation(int closeOperation){
    	this.setDefaultCloseOperation(closeOperation);
    }
}
