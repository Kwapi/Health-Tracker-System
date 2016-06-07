package Model;

import Controllers.DatabaseAccess;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;

/**
 * Bean that models the Exercise Table in the database
 * Includes:
 * id
 * exerciseName
 * exerciseType
 * calPerUnit
 * @author vtv13qau
 */
public class Exercise {
    private int ID;               //id in the db
    private String exerciseName; //Name of the Exercise
    private String exerciseType; //Type of the Exercise
    private int calPerUnit;     //How much Calories burnt per unit(e.g.duration)

    
    /**
     * Default Constructor that initializes all the member variables
     * to the passed values (the data is already in the database)
     *
     * @param ID (id)
     * @param eName (exerciseName)
     * @param eType (exerciseType)
     * @param calPerUnit (calPerUnit)
     */
    public Exercise(int ID, String eName, String eType, int calPerUnit) {
        this.ID = ID;
        this.exerciseName = eName;
        this.exerciseType = eType;
        this.calPerUnit = calPerUnit;
    }
    
    /**
     * Constructor that gets called when creating a new object before persisting
     * it in the database
     * @param eName
     * @param eType
     * @param calPerUnit 
     */
    public Exercise(String eName, String eType, int calPerUnit) {
        this.ID = -1;
        this.exerciseName = eName;
        this.exerciseType = eType;
        this.calPerUnit = calPerUnit;
    }
    
    
    /**
     * Method to find Exercise using exerciseID 
     * @param exerciseID ID of the Exercise we are looking for
     * @return Exercise
     * @throws ServletException 
     */
    public static Exercise find(int exerciseID) throws ServletException{
        try{
        //Connect to Database
        Connection con = DatabaseAccess.getConnection();
        //Search through Exercise Table inside Database
        PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM exercise WHERE (id =?)");
        ps.setInt(1, exerciseID);
        ResultSet result = ps.executeQuery();//Run statement
        Exercise exercise = null; //Creating a ExerciseType object to set returned value to
        //If we find ExerciseType set create a new User using returned values
        if(result.next()){
            exercise = new Exercise(exerciseID,
                                    result.getString("name"),
                                     result.getString("exerciseType"),
                                     result.getInt("calperUnit"));
        }
        //Else Do something like allow user to add new exercise
        con.close(); //Close Connection
        return exercise;
        }catch (SQLException ex) {
            throw new ServletException("Find Problem: Searching for Exercise ", ex);
        }        
    }
    
    /**
     * Method to get all data from exercise table
     * @return ArrayList of Exercises
     * @throws ServletException 
     */
    public static ArrayList<Exercise> findAll() throws ServletException{
        
        try{
        //Connect to Database
        Connection con = DatabaseAccess.getConnection();
        //Get all data from exercise Table                
        PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM exercise");
        
        ResultSet result = ps.executeQuery();//Run statement
        ArrayList<Exercise> exerciseList = new ArrayList<>(); 
        //If we find ExerciseType set create a new User using returned values
        while(result.next()){
            Exercise exercise = new Exercise(result.getInt("id"),
                                             result.getString("name"),
                                             result.getString("exerciseType"),
                                             result.getInt("calperUnit"));
            exerciseList.add(exercise);
        }
        //Else Do something like allow user to add new exercise
        con.close(); //Close Connection
        return exerciseList;
        }catch (SQLException ex) {
            throw new ServletException("Find Problem: Searching for Exercise ", ex);
        }  
    }
    
    //GETTER AND SETTER METHODS
    
     public int getID(){
       return ID;
    }
    
    public String getExerciseName(){
       return exerciseName;
    }
    
    public String getExerciseType() {
        return exerciseType;
    }    

    public int getCalPerUnit(){
        return calPerUnit;
    }
    
    public void setID(int ID){
        this.ID = ID;
    }
    
    public void setExcerciseName(String eName){
        this.exerciseName = eName;
    }
    
    public void setExerciseType(String eType) {
        this.exerciseType = eType;
    }   
    
    public void setCalPerUnit(int calPerUnit){
        this.calPerUnit = calPerUnit;
    }
}
