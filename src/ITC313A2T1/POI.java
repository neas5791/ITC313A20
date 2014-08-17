/*
 * This class represents the atomic model object
 * 
 * Each POI has three attributes
 * 	(1)	Type
 * 	(2) Longitude Coordinate
 * 	(3)	Latitude Coordinate 
 * 
 * The original resource posted for use with this task contained a coordinate system represented as double values
 * it is noted that the new instruction/resource only contains coordinates represented by integer value
 *  
 */

package ITC313A2T1;

public class POI implements Comparable<POI>{
	
    private int type;
    private double xVal, yVal;

    /**
     * Default constructor sets all class attributes
     */
    public POI (){
        type = 0;
        xVal = 0;
        yVal = 0;
    }
    
    /**
     * Constructor with complete object detail
     * @param t	represents the type of POI
     * @param x	the x coordinate
     * @param y	the y coordinate
     */
    public POI (int t, double x, double y){
        type = t;
        xVal = x;
        yVal = y;
    }
    
    // Nothing fancy here simple setters
    public void setType(int t){
        type = t;
    }
    public void setX (double x){
        xVal = x;
    }
    public void setY (double y){
        yVal = y;
    }
    
    // Nothing fancy here simple getters
    public int getType(){
    	return type;
    }
    public double getX(){
        return xVal;
    }
    public double getY(){
        return yVal;
    }
    
    /* Override the toString method of Object class
     * Convenient display of object in string format
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString(){
        return String.format("Type: %s\t\tX: %6s\tY: %6s", this.type, this.xVal, this.yVal);
    }
    
    /* Override of the compareTo method, required for the Comparable interface
     * 
     * Compares this object with the specified object for order. Returns a 
     * negative integer, zero, or a positive integer as this object is less 
     * than, equal to, or greater than the specified object.
     *  
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
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
