
package Model;

import Model.Meal.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;
import javax.servlet.ServletException;

/**
 * A class containing a Collection of MealProgresses tied to a member
 * It represents member's Meal History
 * 
 * Includes:
 * memberId
 * mealLog
 */
public class DietLogger {
    private int memberID;   //member's ID
    private ArrayList<MealProgress> mealLog;    //collection of MealProgresses
    
    /**
     * Default constructor
     */
    public DietLogger() { 
        this.memberID = -1;
        this.mealLog = new ArrayList<>();
    }
    
    /**
     * Constructor that ties the given memberID to this object and creates
     * an empty MealLog
     * 
     * @param memberID (int - member's ID as stored in the db)
     */
    public DietLogger(int memberID) { 
        this.memberID = memberID;
        this.mealLog = new ArrayList<>();
    }
    
    /**
     * Constructor that ties the given memberID and given Collection of MealProgresses
     * to this object
     * @param memberID (int - member's ID as stored in the db)
     * @param mealLog  (Collection of MealProgresses for that member)
     */
    public DietLogger(int memberID, ArrayList<MealProgress> mealLog) {
        this.memberID = memberID;
        this.mealLog = mealLog;
    }
    
    /**
     * Method to add meal progress to the mealLog
     * @param mp (MealProgress)
     */
    public void addMealProgress(MealProgress mp) {
        mealLog.add(mp);
    }
    
    /**
     * Method to find the average amount of calories consumed per day
     * @return avg amount of calories consumed per day (int)
     * @throws ServletException 
     */
    public int findAverageDailyCalsConsumed() throws ServletException {
        double result = 0;
        
        TreeMap<HKFDate, Integer> calsPerDaySet = findCalsConsumedPerDay();
        
        for (Map.Entry<HKFDate, Integer> entry : calsPerDaySet.entrySet()){
            result += entry.getValue();
            
        }
                
        return (int) (result/calsPerDaySet.size());
    }
    
    
    
    /**
     * Method to find total amount of calories consumed between 
     * the two given dates
     * @param start (HKFDate - start date)
     * @param end (HKFDate - end date)
     * @return total amount of calories (int)
     */
    public int findCalsConsumedBetweenDates(HKFDate start, HKFDate end) {
        int totalCals = 0;
        
        for (MealProgress mp : mealLog) {
            if (mp.getDate().compareToWithoutTime(start) >= 0
                    && mp.getDate().compareToWithoutTime(end) <= 0)
                totalCals += mp.calcCalories();
        }
        
        return totalCals;
    }
    
    /**
     * Method to find the amount of calories consumed on a given date
     * @param date (HKFDate - requested date)
     * @return int (total amount of calories)
     */
    public int findCalsConsumedOnDate(HKFDate date) {
        int totalCals = 0;
        
        for (MealProgress mp : mealLog) {
            if (mp.getDate().compareToWithoutTime(date) == 0)
                totalCals += mp.calcCalories();
        }
        
        return totalCals;
    }
    
    /**
     * Method to find all meal progresses between two given dates
     * @param start (HKFDate - start date)
     * @param end (HKFDate - end date)
     * @return ArrayList<MealProgress> - collection of meal progresses between 
     * the two given dates
     */
    public ArrayList<MealProgress> findProgressesBetweenDates(HKFDate start,
            HKFDate end) {
       ArrayList<MealProgress> mps = new ArrayList<>();

       for (MealProgress mp : mealLog)
            if (mp.getDate().compareToWithoutTime(start) >= 0 
                    && mp.getDate().compareToWithoutTime(end) <= 0)
               mps.add(mp);

       return mps;
    }
    
    
    /**
     * Method to find the amount of calories consumed per day
     * @return TreeMap<HKFDate, Integer> (map of dates and corresponding
     * calorie amounts)
     * @throws ServletException 
     */
    public TreeMap<HKFDate, Integer> findCalsConsumedPerDay() 
            throws ServletException {
        TreeMap<HKFDate, Integer> result = new TreeMap<>();
        
        //build keys/intialise values
        for (MealProgress ep : mealLog)
            result.put(ep.getDate(), 0);
        
        //build values
        for (MealProgress ep : mealLog)
            result.put(ep.getDate(), result.get(ep.getDate()) 
                    + ep.calcCalories());
        
        return result;
    }
    
    /**
     * Method to Sort information so that Date is in ascending order
     * @return Sorted ArrayList of Meal Progress
     */
    public ArrayList<MealProgress> sortDate() {        
        Collections.sort(mealLog);
        return mealLog;
    }
      
    
    /**
     * Method to find a Member's DietLogger using their id
     *
     * @param memberID ID of Member to find the physicalHealth data for
     * @return PhysicalHealth object, if found
     * @throws ServletException Exception
     */
    public static DietLogger find(int memberID) throws ServletException {
        DietLogger dietlog = new DietLogger(memberID);
        dietlog.setMealLog(MealProgress.findAll(memberID));
        return dietlog;
    }
    
    /**
     * Enter the details for this DietLogger in the database.
     *
     * @throws ServletException
     */
    public void persist() throws ServletException {
        for (MealProgress mealProg : mealLog) {
            mealProg.persist(memberID); //passing this memberid
        }
    }
    
    /**
     * Log a new meal progress in the database.
     *
     * @param mealProg
     * @throws ServletException
     */
    public void persist(MealProgress mealProg) throws ServletException {
        mealProg.persist(memberID); //passing this memberid
    }
    
    /**
     * Method to find specific ExerciseProgress Object and delete it
     * @param mpID ID of MealProgress we are going to Delete
     * @throws ServletException 
     */
    public void delete(int mpID) throws ServletException {
             
        for(int i = 0; i < mealLog.size(); i++) {
            if(mealLog.get(i).getID() == mpID) {
                mealLog.get(i).delete();
                mealLog.remove(i);
            }
        }
    }
    
    /**
     * Method to Delete all ExerciseProgress
     * @throws ServletException 
     */
    public void deleteAll() throws ServletException {
        for(MealProgress mProg : mealLog) {
            mProg.delete();
        }
        mealLog.clear();
    }
    
    //METHODS THAT MIGHT BE USEFUL IN THE FUTURE
    
    public void findTotalCalsConsumed() {
        //TODO
    }
    
    public void findAverageWeeklyCalsConsumed() {
        //TODO
    }
    
    public void findTotalCalsConsumedThisWeek() {
        //todo
    }
    public void findTotalCalsConsumedToday() {
        //TODO
    }
    
    //GETTER AND SETTER METHODS
    
    public ArrayList<MealProgress> getMealLog() {
        return mealLog;
    }

    public void setMealLog(ArrayList<MealProgress> mealLog) {
        this.mealLog = mealLog;
    }
    
    
}
