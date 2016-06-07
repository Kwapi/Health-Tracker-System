package Model;

import Controllers.DatabaseAccess;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;

/**
 * Bean that models the ExerciseProgress table in the database (can be sorted by date)
 * Includes:
 * id
 * exercise
 * date
 * duration
 * @author vtv13qau
 */
public class ExerciseProgress implements Comparable<ExerciseProgress> {
    private int ID;
    private Exercise exercise;
    private HKFDate date;
    private int duration;
   
    /**
     * Default constructor that initializes all the values from the 
     * passed parameters. Used when loading ExerciseProgress from db
     * @param ID
     * @param exercise
     * @param date
     * @param duration 
     */
    
    public ExerciseProgress(int ID, Exercise exercise, HKFDate date, 
            int duration) {
        this.ID = ID;
        this.exercise = exercise;
        this.date = date;
        this.duration = duration;
      
    }
    /**
     * Constructor that is used when creating an entirely new ExerciseProgress 
     * before parsing it into the database
     * @param exercise
     * @param date
     * @param duration 
     */
    public ExerciseProgress(Exercise exercise, HKFDate date, int duration) {
        this.ID = -1;
        this.exercise = exercise;
        this.date = date;
        this.duration = duration;
        
    }

    /**
     * Method to find ExerciseProgress using ExerciseProgressID
     * @param epID ExerciseProgressID
     * @return the ExerciseProgress
     * @throws ServletException 
     */
    public static ExerciseProgress find(int epID) throws ServletException{
        try {
            //Connect to Database
            Connection con = DatabaseAccess.getConnection();
            //SQL Statement to run, where ? is email address
            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM exerciseProgress WHERE (id = ?)");
            ps.setInt(1, epID);
            ResultSet result = ps.executeQuery();//Run statement
            ExerciseProgress eProgress = null; //Creating a User object to set returned value to
            //If we find User set create a new User using returned values
            if (result.next()) {
                eProgress = new ExerciseProgress(
                        Exercise.find(result.getInt("exerciseID")),
                        new HKFDate(result.getString("exerciseDate"), result.getString("exerciseStartTime")),
                        result.getInt("duration")
                        );
            }
            
            return eProgress;
        } catch (SQLException ex) {
            throw new ServletException(
                    "Find Problem: searching for exerciseProgess from exerciseProgressID: "
                    + epID, ex);
        }
    }
    
    /**
     * Method to return all Exercise Progress data for a member
     * @param memberID ID of the member we are trying to get information for
     * @return ArrayList of members Exercise Progress information
     * @throws ServletException 
     */
    public static ArrayList<ExerciseProgress> findAll(int memberID) throws ServletException{
        try {
            //Connect to Database
            Connection con = DatabaseAccess.getConnection();
            //SQL Statement to run, where ? is email address
            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM exerciseProgress WHERE (memberID = ?)");
            ps.setInt(1, memberID);
            ResultSet result = ps.executeQuery();//Run statement
            ArrayList<ExerciseProgress> epList = new ArrayList<>(); //Creating a User object to set returned value to
            //If we find User set create a new User using returned values
            while (result.next()) {
                ExerciseProgress eProgress = new ExerciseProgress(
                        Exercise.find(result.getInt("exerciseID")),
                        new HKFDate(result.getString("exerciseDate"), result.getString("exerciseStartTime")),
                        result.getInt("duration"));
                
                epList.add(eProgress);
            }            
            return epList;
        } catch (SQLException ex) {
            throw new ServletException(
                    "Find Problem: searching for exerciseProgess for memberID: "
                    + memberID, ex);
        }
    }
    
    /**
     * Method to add information to the exerciseProgress table
     * @param memberID ID of member that we are adding information for
     * @throws ServletException 
     */
    public void persist(int memberID) throws ServletException {
        try {
            Connection con = DatabaseAccess.getConnection();

            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO exerciseProgress (memberID, exerciseDate, exerciseStartTime, duration, exerciseID) VALUES(?, ?, ?, ?, ?)");
            
            ps.setInt(1, memberID);

            //http://stackoverflow.com/questions/530012/how-to-convert-java-util-date-to-java-sql-date
            ps.setString(2, date.toString());
            ps.setString(3, date.timeToString());
            ps.setInt(4, duration);
            ps.setInt(5, exercise.getID());

            ps.executeUpdate();
            
            con.close();
            
        } catch (SQLException ex) {
            throw new ServletException(
                    "Persist Problem: persisting exerciseProgress details, memberID: " + memberID, ex);
        }
    }
    
    /**
     * Update one of the parameters of this exerciseProgress in the database
     *
     * @param valueName The name of the value to be changed
     * @param newValue The new value for the above to be set to
     * @throws ServletException
     * @throws SQLException
     */
    public void updateValue(String valueName, String newValue) throws ServletException, SQLException {
        try {
            Connection con = DatabaseAccess.getConnection();
            PreparedStatement ps = con.prepareStatement("UPDATE goal SET " + valueName + " = ? WHERE id = ?;");
            ps.setString(1, newValue);
            ps.setInt(2, ID);
            ps.execute();
        } catch (ServletException ex) {
            throw ex;
        } catch (SQLException ex) {
            throw ex;
        }
    }
    
    /**
     * Method to Calculate the Calories burnt depending
     * @return Amount of Calories burnt
     */
    public int calculateCals(){
       
           return exercise.getCalPerUnit() * duration;
        
    }

    /**
     * Method to be used to sort Exercise Progress using date
     * @param t Exercise Progress Object
     * @return 1,0 or -1
     */
    @Override
    public int compareTo(ExerciseProgress t) {
        return (this.date.compareTo(t.date));
    }
    
    /**
     * Method to delete all information related to ID
     * @param epID ID for ExerciseProgress we are deleting
     * @throws ServletException 
     */
    public void delete() throws ServletException {
        try {
            Connection con = DatabaseAccess.getConnection();

            PreparedStatement ps = con.prepareStatement(
                    "DELETE FROM exerciseProgress WHERE (id = ?)");
            
            ps.setInt(1, ID);
            ps.executeUpdate();
            
            con.close();
            
        } catch (SQLException ex) {
            throw new ServletException("Delete Problem: Deleting exerciseProgress details: " + ID, ex);
        }
    }
    
    
    //GETTER AND SETTER METHODS
    public int getID() {
        return ID;
    }
    
    public Exercise getExercise() {
        return exercise;
    }

    public HKFDate getDate() {
        return date;
    }

    public int getDuration() {
        return duration;
    }

   

    public void setID(int ID) {
        this.ID = ID;
    }
    
    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    public void setDate(HKFDate date) {
        this.date = date;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
