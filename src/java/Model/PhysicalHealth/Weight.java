package Model.PhysicalHealth;

/**
 * Custom class to model Weight
 * Includes methods to covert between Metric and Imperial
 * Stores in grams
 * @author vtv13qau
 */
public class Weight {
    
    private int grams;
    /**
     * Default constructor
     */
    public Weight() {}
    
    /**
     * Creates and sets this weight to the given amount in grams
     * @param grams 
     */
    public Weight(int grams) {
        this.grams = grams;
    }
    
    /**
     * Returns Weight in grams
     * @return 
     */
    public int getGrams() {
        return grams;
    }
    
    /**
     * Returns weight in kg
     * @return 
     */
    public double getKilos(){
        return ((double)grams/1000);
    }
    
    /**
     * Sets this weight to the given amount in grams
     * @param grams 
     */
    public void setGrams(int grams) {
        this.grams = grams;
    }
    
    /**
     * Method to convert kilograms to pounds
     * @param kilos value in kilograms
     * @return value in pounds
     */
    public static double toPounds(double kilos) {
        return kilos*2.20462;
    }
    
    /**
     * Method to convert pounds to kilogram
     * @param pounds value in Pounds
     * @return value in kilograms
     */
    public static double toKilos(double pounds) {
        return pounds*0.453592;
    }
    
    /**
     * Method to convert pounds to grams
     * @param pounds value in pounds
     * @return value in grams
     */
    public static int toGramsFromPounds(int pounds){
        return (int)(toKilos(pounds)*1000);
    }
     /**
     * Method to convert kgs to pounds
     * @param kilos value in kgs
     * @return value in grams
     */
    public static int toGramsFromKilos(double kilos){
        return (int) (kilos*1000);
    }
    
    @Override
    /**
     * String representation of Weight in kg
     */
    public String toString() {
        return Double.toString(toKg());
    }
    /**
     * String representation of Weight in kg with the unit suffix
     * @return 
     */
    public String toNicelyDisplay(){
        double kilos = grams/1000;
        return kilos + " kg";
    }
    /**
     * Returns String representation of Weight in grams to be used in 
     * Google Graph
     * @return 
     */
    public String forGraph(){
        return Integer.toString((int)grams);
    }
    
    /**
     * Returns this weight in kilograms
     * @return 
     */
    public double toKg(){
        return grams/1000.0;
    }
    
    
    
   
}
