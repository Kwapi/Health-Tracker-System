package Model.PhysicalHealth;
/**
     * Custom class to model Height
     * Includes methods to convert different height units
     * Stores in centimeters
     */
public class Height {
    
    private int centimetres;
    /**
     * Default constructor
     */
    public Height() {}
    
    /**
     * Sets this Height to the given cm
     * @param centimetres 
     */
    public Height(int centimetres) {
        this.centimetres = centimetres;
    }
    /**
     * Returns height in cm
     * @return 
     */
    public int getCentimetres() {
        return centimetres;
    }
    
    /**
     * Return height in metres
     * @return 
     */
    public double getMetres(){
        return (double)centimetres/100;
    }
    
    /**
     * Set the height in cm
     * @param centimetres 
     */
    public void setCentimetres(int centimetres) {
        this.centimetres = centimetres;
    }
    
    /**
     * Method to convert feet to metres
     * @param feet value in feet
     * @return value in metres
     */
    static public int toMetres(int feet){
        return (int) (feet*0.3048);
    }
    
    /**
     * Method to convert metres to feet
     * @param metres value in metres
     * @return value in feet
     */
    public int toFeet(int metres){
        return (int) (metres*3.2808399);
    }
    
    @Override
    public String toString() { 
        return ""+centimetres;
    }
    
    /**
     * Returns the result of Feet,Inches to cm conversion
     * @param feet
     * @param inches
     * @return 
     */
    public static int toCentimetersFromImperial(int feet, int inches){
        int result = 0;
        
        result += (int)((inches + feet*12) *2.54);
        
        return result;
        
    }
    
    
}
