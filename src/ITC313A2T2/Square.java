/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ITC313A2T2;

/**
 * @author SeAN MaTKOVICH
 * 
 * Part 2 (square):
 * Assume a Square class which would inherit the Rectangle defined in Part 1. 
 * An object of Square class will have both sides equal (i.e. base=height) which 
 * are set to zero at the time of the creation of the object. The same functions 
 * (area and setValues) of the Rectangle class should be used in Square class 
 * too.
 * Write a JAVA source code declaration of the Square class inheriting the 
 * Rectangle class to behave according to the specification given above.
 * Write a fragment of client code which creates a Square object, sets its side 
 * to 8 and displays its area.
 * 
 */
public class Square extends Rectangle {
    
	public Square(){
        super();
    }
    
    public Square(int newBase){
        super(newBase, newBase);
    }
    
    @Override
    public void setBase(int newBase){
        super.setBase(newBase);
        super.setHeight(newBase);
    }
    
    @Override
    public void setHeight(int newHeight){
        this.setBase(newHeight);
    }    

}
