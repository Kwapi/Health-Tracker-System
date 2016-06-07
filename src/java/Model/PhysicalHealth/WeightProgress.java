package Model.PhysicalHealth;

import Controllers.DatabaseAccess;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Model.HKFDate;
import javax.servlet.ServletException;

/**
 * Bean that models WeightProgress table in the db
 * Can be sorted by Date
 * Includes:
 * id
 * physicalHealthId
 * weight
 * date
 * @author vtv13qau
 */
public class WeightProgress implements Comparable<WeightProgress> {

    private int ID;
    private int physicalHealthID;
    private Weight weight;
    private HKFDate date;

    /**
     * Constructor that is used when retrieving data from db
     * @param ID
     * @param physicalHealthID
     * @param weight
     * @param date 
     */
    public WeightProgress(int ID, int physicalHealthID, Weight weight
            , HKFDate date) {
        this.ID = ID;
        this.physicalHealthID = physicalHealthID;
        this.weight = weight;
        this.date = date;
    }
    
    /**
     * Constructor that is used when creating a new WEightProgress and then
     * persisting it in the db
     * @param physicalHealthID
     * @param weight
     * @param date 
     */
    public WeightProgress(int physicalHealthID, Weight weight, HKFDate date) {
        this.ID = -1;
        this.physicalHealthID = physicalHealthID;
        this.weight = weight;
        this.date = date;
    }
    /**
     * Constructor that sets ID and physicalHealthID to dummy values
     * @param weight
     * @param date 
     */
    public WeightProgress(Weight weight, HKFDate date) {
        this.ID = -1;
        this.physicalHealthID = -1;
        this.weight = weight;
        this.date = date;
    }
    
    //GETTER AND SETTER METHODS START HERE
    public int getID() {
        return ID;
    }
    
    public int getPhysicalHealthID() { 
        return physicalHealthID;
    }
    
    public Weight getWeight() {
        return weight;
    }

    public HKFDate getDate() {
        return date;
    }
    
    public void setID(int ID) {
        this.ID = ID;
    }

    public void setPhysicalHealthID(int physicalHealthID) {
        this.physicalHealthID = physicalHealthID;
    }
    
    public void setWeight(Weight weight) {
        this.weight = weight;
    }

    public void setDate(HKFDate date) {
        this.date = date;
    }

    //SETTER AND GETTER METHODS END HERE
    @Override
    public int compareTo(WeightProgress t) {
        return this.date.compareTo(t.date);
    }

    /**
     * Method to find information about Weight progress
     * @param wpID ID of information we are looking for
     * @return Weight Progress
     * @throws ServletException 
     */
    public static WeightProgress find(int wpID) throws ServletException {
        try {
            //Connect to Database
            Connection con = DatabaseAccess.getConnection();
            //SQL Statement to run, where ? is email address
            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM weightProgress WHERE (id = ?)");
            ps.setInt(1, wpID);
            ResultSet result = ps.executeQuery();//Run statement
            WeightProgress wp = null; //Creating a User object to set returned value to
            //If we find User set create a new User using returned values
            if (result.next()) {
                wp = new WeightProgress(
                        result.getInt("id"),
                        result.getInt("physicalHealthID"),
                        new Weight(result.getInt("weight")), 
                        new HKFDate(result.getString("weightDate")));
            }
            
            return wp;
        } catch (SQLException ex) {
            throw new ServletException(
                    "Find Problem: searching for weightProgess from weightprogressid: "
                    + wpID, ex);
        }
    }
    
    /**
     * Method to get information about member using physical health ID
     * @param physHealthID Foreign ID from Physical Health table
     * @return ArrayList of Weight Progress information
     * @throws ServletException 
     */
    public static ArrayList<WeightProgress> findAll(int physHealthID) throws ServletException {
        try {
            //Connect to Database
            Connection con = DatabaseAccess.getConnection();
            //SQL Statement to run, where ? is email address
            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM weightProgress WHERE (physicalHealthID = ?)");
            ps.setInt(1, physHealthID);
            ResultSet result = ps.executeQuery();//Run statement
            
            ArrayList<WeightProgress> resultsLog = new ArrayList<>();
            
            while (result.next()) {
                WeightProgress tempWP = new WeightProgress(
                        result.getInt("id"),
                        result.getInt("physicalHealthID"),
                        new Weight(result.getInt("weight")), 
                        new HKFDate(result.getString("weightDate")));
                
                resultsLog.add(tempWP);
            }
            
            return resultsLog;
        } catch (SQLException ex) {
            throw new ServletException(
                    "Find Problem: searching for weightProgesses from physicalid: "
                    + physHealthID, ex);
        }
    }
    
    /**
     * Method to store Weight Progress information for member
     * @param physHealthId Foreign ID from Physical Health table
     * @throws ServletException 
     */
    public void persist(int physHealthId) throws ServletException {
        try {
            Connection con = DatabaseAccess.getConnection();

            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO weightProgress (physicalHealthID, weightDate, weight) VALUES(?, ?, ?)");
            
            ps.setInt(1, physHealthId);
            
            //http://stackoverflow.com/questions/530012/how-to-convert-java-util-date-to-java-sql-date
            ps.setString(2, date.toString());
            ps.setInt(3, (int) weight.getGrams());
           
            ps.executeUpdate();
            
            con.close();
            
        } catch (SQLException e) {
            throw new ServletException(
                    "Persist Problem: persisting weightProgress details, physicalHealthID: " + physHealthId, e);
        }
    }

    /**
     * Update one of the parameters of this weightProgress in the database
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
     * Method to delete all information related to ID
     * @param epID ID for ExerciseProgress we are deleting
     * @throws ServletException 
     */
    public void delete() throws ServletException {
        try {
            Connection con = DatabaseAccess.getConnection();

            PreparedStatement ps = con.prepareStatement(
                    "DELETE FROM weightProgress WHERE (id = ?)");
            
            ps.setInt(1, ID);
            ps.executeUpdate();
            
            con.close();
            
        } catch (SQLException ex) {
            throw new ServletException("Delete Problem: Deleting weightProgress details: " + ID, ex);
        }
    }
            
}
