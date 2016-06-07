

package Model.Meal;

import Controllers.DatabaseAccess;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;

/**
 *  Bean that models the Meal table from the database
 * Includes:
 * id
 * mealName
 * calsPerUnit
 */
public class Meal {
    
    private int ID;
    private String mealName;
    private int calsPerUnit;
    
    /**
     * Constructor used when retrieving data from db
     * @param ID
     * @param mealName
     * @param calsPerUnit 
     */
    public Meal(int ID, String mealName, int calsPerUnit) {
        this.ID = ID;
        this.mealName = mealName;
        this.calsPerUnit = calsPerUnit;
    }
    
    /**
     * Constructor used when creating a new Meal and persisting it in the db
     * @param mealName
     * @param calsPerUnit 
     */
    public Meal(String mealName, int calsPerUnit) {
        this.ID = -1;
        this.mealName = mealName;
        this.calsPerUnit = calsPerUnit;
    }
    
    //GETTER AND SETTER METHODS START HERE
    public int getID() {
        return ID;
    }
    
    public String getMealName() {
        return mealName;
    }

    public int getCalsPerUnit() {
        return calsPerUnit;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
    
    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public void setCalsPerUnit(int calsPerUnit) {
        this.calsPerUnit = calsPerUnit;
    }
    
    //GETTER AND SETTER METHODS END HERE
    
    /**
     * Method to find Meal from database using 
     * @param mealID
     * @return
     * @throws ServletException 
     */
    public static Meal find(int mealID) throws ServletException {
        try{
        //Connect to Database
        Connection con = DatabaseAccess.getConnection();
        //Search through Exercise Table inside Database
        PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM meal WHERE (id =?)");
        ps.setInt(1, mealID);
        ResultSet result = ps.executeQuery();//Run statement
        Meal meal = null; //Creating a Meal object to set returned value to
        //If we find Meal set create a new Meal using returned values
        if(result.next()){
            meal = new Meal(mealID,
                            result.getString("name"),
                            result.getInt("calperUnit"));
        }
        
        con.close(); //Close Connection
        return meal;
        }catch (SQLException ex) {
            throw new ServletException("Find Problem: Searching for Meal ", ex);
        }
    }
    
    /**
     * Method to find all Meals from database, for creating options for user
     * @return ArrayList of Meals
     * @throws ServletException 
     */
    public static ArrayList<Meal> findAll() throws ServletException {
        try{
        //Connect to Database
        Connection con = DatabaseAccess.getConnection();
        //Get all data from exercise Table                
        PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM meal");
        
        ResultSet result = ps.executeQuery();//Run statement
        ArrayList<Meal> mealList = new ArrayList<>(); 
        //If we find Meal set create a new Meal using returned values
        while(result.next()){
            Meal meal = new Meal(result.getInt("id"),
                                 result.getString("name"),
                                 result.getInt("calperUnit"));
            mealList.add(meal);
        }
        
        con.close(); //Close Connection
        return mealList;
        }catch (SQLException ex) {
            throw new ServletException("Find Problem: Searching for Meal ", ex);
        }
    }
    
}
