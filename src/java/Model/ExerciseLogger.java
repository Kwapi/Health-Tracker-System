/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;


import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeMap;
import javax.servlet.ServletException;

/**
 * A class containing a Collection of ExerciseProgresses tied to a member
 * It represents member's Activity History
 * 
 * Includes:
 * memberId
 * exerciseLog
 */
public class ExerciseLogger {

    int memberID;
    ArrayList<ExerciseProgress> exerciseLog;
    
    /**
     * Default constructor. Initializes member id to -1 and creates a new empty
     * exerciseLog
     */
    public ExerciseLogger() {
        this.memberID = -1;
        this.exerciseLog = new ArrayList<>();
    }
    
    /**
     * Constructor that ties that object to a given memberId and creates a new 
     * empty exerciseLog
     * @param memberID (member's ID- int)
     */
    public ExerciseLogger(int memberID) {
        this.memberID = memberID;
        this.exerciseLog = new ArrayList<>();
    }
    
    /**
     * Constructor that initializes the object using the given memberId and 
     * exerciseLog
     * @param memberID (member's ID - int)
     * @param exerciseLog (activity history - ArrayList<ExerciseProgress>)
     */
    public ExerciseLogger(int memberID, ArrayList<ExerciseProgress> 
            exerciseLog) {
        this.memberID = memberID;
        this.exerciseLog = exerciseLog;
    }

       
    /**
     * Method to add ExerciseProgress to exerciseLog
     * @param ep (ExerciseProgress)
     */
    public void addExerciseProgress(ExerciseProgress ep) {
        exerciseLog.add(ep);
    }

   /**
    * A method to find average daily activity time
    * @return avg daily activity time in minutes (int)
    */
    public int findAverageDailyActivityTime() {
        double result = 0;
        
        for (ExerciseProgress ep : exerciseLog)
            result += ep.getDuration();
        
        return (int) (result/exerciseLog.size());
    }
    
   
    /**
     * A method to find total activity time per day
     * @return a map of String representation of Dates and the activity 
     * time (TreeMap<String, Integer>)
     * @throws ServletException 
     */
    public TreeMap<String, Integer> findActivityTimePerDay() 
            throws ServletException {
        TreeMap<String, Integer> result = new TreeMap<String, Integer>();

        //build keys/intialise values
        for (ExerciseProgress ep : exerciseLog) {
            result.put(ep.getDate().toString(), 0);
        }

        //build values
        for (ExerciseProgress ep : exerciseLog) {
            result.put(ep.getDate().toString(), 
                    result.get(ep.getDate().toString()) + ep.getDuration());
        }

        return result;
    }
    
    /**
     * A method to find total amount of calories burned per day
     * @return a map of String representation of dates and the amount of 
     * calories burned (TreeMap<String, Integer>)
     * @throws ServletException 
     */
    public TreeMap<String, Integer> findCalsBurnedPerDay() throws ServletException {
        TreeMap<String, Integer> result = new TreeMap<>();

        //build keys/intialise values
        for (ExerciseProgress ep : exerciseLog) {
            result.put(ep.getDate().toString(), 0);
        }

        //build values
        for (ExerciseProgress ep : exerciseLog) {
            result.put(ep.getDate().toString(), 
                    result.get(ep.getDate().toString()) + ep.calculateCals());
        }

        return result;
    }
    
    
    /**
     * A method to find the ExerciseProgresses between the two given dates
     * @param start (start date - HKFDate)
     * @param end   (end date - HKFDate)
     * @return collection of ExerciseProgresses between those dates (ArrayList)
     */
    public ArrayList<ExerciseProgress> findProgressesBetweenDates(HKFDate start,
            HKFDate end) {
        ArrayList<ExerciseProgress> eps = new ArrayList<>();

        for (ExerciseProgress ep : exerciseLog) {
            if (ep.getDate().compareToWithoutTime(start) >= 0 && 
                    ep.getDate().compareToWithoutTime(end) <= 0) {
                eps.add(ep);
            }
        }

        return eps;
    }
    
    /**
     * A method to find the total amount of exercise time between the two
     * given dates
     * @param start (start date - HKFDate)
     * @param end   (end date - HKFDate)
     * @return amount of exercise time between those dates (int)
     */
    public int findExerciseTimeBetweenDates(HKFDate start, HKFDate end) {
        int totalTime = 0;

        for (ExerciseProgress ep : exerciseLog) {
            if (ep.getDate().compareToWithoutTime(start) >= 0 
                    && ep.getDate().compareToWithoutTime(end) <= 0) {
                totalTime += ep.getDuration();
            }
        }

        return totalTime;
    }
    
    /**
     * A method to find the amount of exercise time on a particular date
     * @param date
     * @return amount of exercise time (int - in minutes)
     */
    public int findExerciseTimeOnDate(HKFDate date) {
        int totalTime = 0;

        for (ExerciseProgress ep : exerciseLog) {
            if (ep.getDate().compareToWithoutTime(date) == 0) {
                totalTime += ep.getDuration();
            }
        }

        return totalTime;
    }
    
    /**
     * A method to find the total amount of calories burned between 
     * the given dates
     * @param start (start date)
     * @param end   (end date)
     * @return total amount of calories burned between those dates
     */
    public int findCalsBurnedBetweenDates(HKFDate start, HKFDate end) {
        int totalCals = 0;

        for (ExerciseProgress ep : exerciseLog) {
            if (ep.getDate().compareToWithoutTime(start) >= 0 
                    && ep.getDate().compareToWithoutTime(end) <= 0) {
                totalCals += ep.calculateCals();
            }
        }

        return totalCals;
    }
    
    /**
     * A method to find the total amount of calories burned on a given date
     * @param date
     * @return total amount of calories burned on that date
     */
    public int findCalsBurnedOnDate(HKFDate date) {
        int totalCals = 0;

        for (ExerciseProgress ep : exerciseLog) {
            if (ep.getDate().compareToWithoutTime(date) == 0) {
                totalCals += ep.calculateCals();
            }
        }

        return totalCals;
    }


    /**
     * Method to Sort information so that Date is in ascending order
     *
     * @return Sorted ArrayList of ExerciseProgress
     */
    public ArrayList<ExerciseProgress> sortDate() {
        Collections.sort(exerciseLog);
        return exerciseLog;
    }

    /**
     * Method to find User using email address
     *
     * @param memberID ID of Member to find the physicalHealth data for
     * @return PhysicalHealth object, if found
     * @throws ServletException Exception, PhysicalHealth was not found for
     * member
     */
    public static ExerciseLogger find(int memberID) throws ServletException {
        ExerciseLogger exlog = new ExerciseLogger(memberID);
        exlog.setExerciseLog(ExerciseProgress.findAll(memberID));
        return exlog;
    }

    /**
     * Enter for the details for this ExerciseLogger in the database.
     *
     * @throws ServletException
     */
    public void persist() throws ServletException {
        for (ExerciseProgress exProg : exerciseLog) {
            exProg.persist(memberID); //passing this psyhicalHealthId
        }
    }

    /**
     * Log a new exercise progress in the database.
     *
     * @throws ServletException
     */
    public void persist(ExerciseProgress exProg) throws ServletException {
        exProg.persist(memberID); //passing this psyhicalHealthId
    }

    /**
     * Method to find specific ExerciseProgress Object and delete it
     *
     * @param epID ID of ExerciseProgress we are going to Delete
     * @throws ServletException
     */
    public void delete(int epID) throws ServletException {

        for (int i = 0; i < exerciseLog.size(); i++) {
            if (exerciseLog.get(i).getID() == epID) {
                exerciseLog.get(i).delete();
                exerciseLog.remove(i);
            }
        }
    }

    /**
     * Method to Delete all ExerciseProgress
     *
     * @throws ServletException
     */
    public void deleteAll() throws ServletException {
        for (ExerciseProgress exProg : exerciseLog) {
            exProg.delete();
        }
        exerciseLog.clear();
    }
    
    
    
    
    //METHODS THAT MIGHT BE USED IN THE FUTURE
    
     public int findTotalCalsBurned() {
        //TODO
        return 1;
    }

    public double findAverageWeeklyCalsBurned() {
        //TODO
        return 1;
    }

    public int findTotalCalsBurnedThisWeek() {
        //todo
        return 1;
    }

    public double findAverageDailyCalsBurned() {
        //TODO
        return 1;
    }
    
     public int findTotalCalsBurnedToday() {
        //TODO
        return 1;
    }
     
    //GETTER AND SETTER METHODS
    public ArrayList<ExerciseProgress> getExerciseLog() {
        return exerciseLog;
    }

    public void setExerciseLog(ArrayList<ExerciseProgress> exerciseLog) {
        this.exerciseLog = exerciseLog;
    }


}
