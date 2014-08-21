package ITC313A2;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


/**
 *  @author 		Sean Matkovich
 *  @studentID		11187033
 *  
 *  @subject		ITC313
 *  @assignment		Assignment 2
 *  
 *  This class is the main driving program... It offers an interface for the marker/user 
 *  to interact with the two subtasks of the assignment.
 *  
 */
@SuppressWarnings("serial")
public class RunME extends JFrame {
	private JButton task1;
	private JButton task2;
	
	/**
	 * Constructor
	 * Basic constructor builds GUI and framework
	 */
	public RunME(){
		this.setLayout(new GridLayout(2,1,5,5));
		
		this.setTitle("ITC313");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.add(new JLabel("Please choose a task:"));
		this.add(new UserInterface());

		task1.addActionListener(new ButtonListener());
		task2.addActionListener(new ButtonListener());
		
		this.pack();
	}
	
	/**
	 * This nested class implements the action required by button click events
	 */
	private class ButtonListener implements ActionListener{
		int command;
		@Override
		public void actionPerformed(ActionEvent e) {
			command = Integer.parseInt(((JButton) e.getSource()).getActionCommand());
			
			if (command == 1)
				TaskOne();
			else if (command == 2)
				TaskTwo();
		}
	}

	/**
	 * Initializes a new instance of task one GUI
	 */
	@SuppressWarnings("unused")
	private void TaskOne(){
		ITC313A2T1.Controller taskOne = new ITC313A2T1.Controller();
	}

	/**
	 * Initializes a new instance of task two GUI
	 */
	@SuppressWarnings("unused")
	private void TaskTwo(){
		ITC313A2T2.Controller taskTwo = new ITC313A2T2.Controller();
	}
	
	/**
	 * This is the meat of the SwitchBoard GUI. This class builds and manages the UserInterface
	 */
	private class UserInterface extends JPanel{
		public UserInterface(){
			
			this.setLayout(new FlowLayout(FlowLayout.CENTER));
			
			task1 = new JButton("Task One");
			task1.setActionCommand("1");
			
			task2 = new JButton("Task Two");
			task2.setActionCommand("2");
						
			this.add(task1);
			this.add(task2);
		}
	}

	/**
	 * main is the starting point for this class. It creates the working instance of a SwitchBoard object.
	 * 
	 * @param args
	 */
	public static void main (String[] args){
		RunME switchBoard = new RunME();
		switchBoard.setVisible(true);
	}
	
}
