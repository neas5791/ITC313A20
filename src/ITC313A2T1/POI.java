/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ITC313A2T1;

public class POI implements Comparable<POI>{
	
    private int type;
    private double xVal, yVal;

    public POI (){
        type = 0;
        xVal = 0;
        yVal = 0;
    }
    
    public POI (int t, double x, double y){
        type = t;
        xVal = x;
        yVal = y;
    }
    
    public void setType(int t){
        type = t;
    }
    
    public void setX (double x){
        xVal = x;
    }
    
    public void setY (double y){
        yVal = y;
    }
    
    public double getX(){
        return xVal;
    }
    
    public double getY(){
        return yVal;
    }
    
    public int getType(){
        return type;
    }
    
    @Override
    public String toString(){
        return String.format("Type: %s\t\tX: %6s\tY: %6s", this.type, this.xVal, this.yVal);
    }
    
    @Override
    public int compareTo(POI o) {
        
        if (this.type == o.getType()) {
            if (this.xVal == o.getX() && this.yVal == o.getY())
                return 0;
            else if ( (this.xVal * this.yVal) < (o.getX() * o.getY()))
                return -1;
            else
                return 1;
        }
        else {
            if (this.type < o.getType())
                return -1;
            else
                return 1;
        }
    }

}
