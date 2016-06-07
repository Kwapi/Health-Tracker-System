package Model;

import Model.PhysicalHealth.*;
import Controllers.DatabaseAccess;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;

/**
 * Bean that models the Member table in the db
 * Includes:
 * forename
 * surname
 * id
 * username
 * password
 * email
 * @author vtv13qau
 */
public class Member {

    private String forename;
    private String surname;
    private int userID; //Users ID Number
    private String username; //User Username
    private String password; //User Password 
    private String email; //User Email
    
    /**
     * Default constructor
     */
    public Member() { }
    
    /**
     * Constructor used when creating a new member and then persisting it into
     * the db.
     * Fills all the values except for id (dummy value)
     * @param username
     * @param password
     * @param email
     * @param forename
     * @param surname 
     */
    public Member(String username, String password, String email,
            String forename, String surname) {
        this.userID = -1;
        this.username = username;
        this.password = password;
        this.email = email;
        this.forename = forename;
        this.surname = surname;
    }
    
    /**
     * Constructor used when retrieving a member from the db
     * Fills all the values
     * @param userID
     * @param username
     * @param password
     * @param email
     * @param forename
     * @param surname 
     */
    public Member(int userID, String username, String password, String email,
            String forename, String surname) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.email = email;
        this.forename = forename;
        this.surname = surname;
    }

    //Getter Methods
    public int getUserID() {
        return userID;
    }
    
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
    
    public String getForename() {
        return forename;
    }
    
    public String getSurname() {
        return surname;
    }
    //End of Getter Methods

    //Setter Methods
    public void setUserID(int userID) {
        this.userID = userID;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setForename(String forename) {
        this.forename = forename;
    }
    
    public void setSurname(String surname) {
        this.surname = surname;
    }
    //End of Setter Methods

    /**
     * Method to find Member using memberID
     *
     * @param memberID
     * @return Member object, if found
     * @throws ServletException Exception, Member was not found
     */
    public static Member find(int memberID) throws ServletException {
        try {
            //Connect to Database
            Connection con = DatabaseAccess.getConnection();
            //SQL Statement to run, where ? is email address
            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM member WHERE (id = ?)");
            ps.setInt(1, memberID);
            
            ResultSet result = ps.executeQuery();//Run statement
            Member member = null; //Creating a Member object to set returned value to
            //If we find Member set create a new Member using returned values
            if (result.next()) {
                member = new Member(memberID,
                        result.getString("username"),
                        result.getString("password"),
                        result.getString("email"),
                        result.getString("forename"),
                        result.getString("surname")
                    );
            }
            
            con.close();
            
            return member;
        } catch (SQLException ex) {
            throw new ServletException("Find Problem: searching for member by memberID: " + memberID, ex);
        }
    }
    
    /**
     * Method to find Member using email address, to check if email is already in use
     *
     * @param email Email Address of User
     * @return User object, if found
     * @throws ServletException Exception, User was not found
     */
    public static Member findByEmail(String email) throws ServletException {
        try {
            //Connect to Database
            Connection con = DatabaseAccess.getConnection();
            //SQL Statement to run, where ? is email address
            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM member WHERE (email = ?)");
            ps.setString(1, email);
            
            ResultSet result = ps.executeQuery();//Run statement
            Member member = null; //Creating a User object to set returned value to
            //If we find User set create a new User using returned values
            if (result.next()) {
                member = new Member(result.getInt("id"),
                        result.getString("username"),
                        result.getString("password"),
                        result.getString("email"),
                        result.getString("forename"),
                        result.getString("surname")
                    );
            }
            
            con.close();
            
            return member;
        } catch (SQLException ex) {
            throw new ServletException("Find Problem: searching for user by email ", ex);
        }
    }
    
    /**
     * Method to find Member by username, to check if it's already in use
     *
     * @param username
     * @return User object, if found
     * @throws ServletException Exception, User was not found
     */
    public static Member findByUsername(String username) throws ServletException {
        try {
            //Connect to Database
            Connection con = DatabaseAccess.getConnection();
            //SQL Statement to run, where ? is email address
            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM member WHERE (username = ?)");
            ps.setString(1, username);
            
            ResultSet result = ps.executeQuery();//Run statement
            Member member = null; //Creating a User object to set returned value to
            //If we find User set create a new User using returned values
            if (result.next()) {
                member = new Member(result.getInt("id"),
                        result.getString("username"),
                        result.getString("password"),
                        result.getString("email"),
                        result.getString("forename"),
                        result.getString("surname")
                    );
            }
            
            con.close();
            
            return member;
        } catch (SQLException ex) {
            throw new ServletException("Find Problem: searching for user by username ", ex);
        }
    }
    
    /**
     * Method to find Member using email address and password, used when logging in
     *
     * @param email Email Address of User
     * @param password Password of User
     * @return User object, if found
     * @throws ServletException Exception, User was not found
     */
    public static Member find(String email, String password) throws ServletException {
        try {
            //Connect to Database
            Connection con = DatabaseAccess.getConnection();
            //SQL Statement to run, where ? is email address
            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM member WHERE (email = ? AND password = ?)");
            ps.setString(1, email);
            ps.setString(2, password);
            
            ResultSet result = ps.executeQuery();//Run statement
            Member member = null; //Creating a User object to set returned value to
            //If we find User set create a new User using returned values
            if (result.next()) {
                member = new Member(result.getInt("id"),
                        result.getString("username"),
                        result.getString("password"),
                        result.getString("email"),
                        result.getString("forename"),
                        result.getString("surname")
                    );
            }
            
            con.close();
            
            return member;
        } catch (SQLException ex) {
            throw new ServletException("Find Problem: searching for user while logging in ", ex);
        }
    }
    
    /**
     * Method to find a Member's userID in the case that it hasn't been set in the bean already.
     * Intended for when a new member has just been persisted, and we need to find the id 
     * generated by the database during that persist, so that the new member's physicalHealth
     * can be generated using it
     *
     * @param email Email Address of User
     * @param password Password of User
     * @return User object, if found
     * @throws ServletException Exception, User was not found
     */
    public static int findID(Member member) throws ServletException {
        try {
            //Connect to Database
            Connection con = DatabaseAccess.getConnection();
            //SQL Statement to run
            PreparedStatement ps = con.prepareStatement(
                    "SELECT id FROM member WHERE (email = ? AND password = ?)");
            ps.setString(1, member.getEmail());
            ps.setString(2, member.getPassword());
            
            ResultSet result = ps.executeQuery();//Run statement
            int id = -1;
            
            if (result.next())
                id = result.getInt("id");
            
            con.close();
            
            return id;
        } catch (SQLException ex) {
            throw new ServletException(
                    "Find Problem: searching for userID in Member.findID, "
                            + "email: " + member.getEmail() 
                            + " password: " + member.getPassword(), 
                    ex);
        }
    }

    /**
     * Enter for the details for this user in the database.
     *
     * @throws ServletException
     */
    public void persist() throws ServletException {
        try {
            Connection con = DatabaseAccess.getConnection();
            
            PreparedStatement ps = con.prepareStatement("INSERT INTO member (email, password, username, forename, surname) VALUES(?, ?, ?, ?, ?)");
            ps.setString(1, this.email);
            ps.setString(2, this.password);
            ps.setString(3, this.username);
            ps.setString(4, this.forename);
            ps.setString(5, this.surname);
            ps.executeUpdate();
            
            con.close();
        } catch (SQLException e) {
            throw new ServletException("Persist Problem: registering user details ", e);
        }
    }
    
    /**
     * Update one of the parameters of this Member in the database
     *
     * @param valueName The name of the value to be changed
     * @param newValue The new value for the above to be set to
     * @throws ServletException
     */
    public void updateValue(String valueName, String newValue) throws ServletException {
        try {
            Connection con = DatabaseAccess.getConnection();
            PreparedStatement ps = con.prepareStatement("UPDATE member SET " + valueName + " = ? WHERE id = ?;");
            ps.setString(1, newValue);
            ps.setInt(2, userID);
            ps.execute();
        } catch (SQLException ex) {
            throw new ServletException("Problem updating account, field: " + valueName
                                            + " value: " + newValue, ex);
        }
    }
    
    /**
     * Remove this Member from the database
     * @throws SQLException
     * @throws ServletException
     */
    public void delete() throws ServletException {
        try {
            Connection con = DatabaseAccess.getConnection();
            PreparedStatement ps = con.prepareStatement("DELETE FROM member WHERE (id = ?)");
            ps.setInt(1, this.userID);
            ps.execute();
        } catch (SQLException ex) {
            throw new ServletException("Problem deleting account");
        }
    }
    
    @Override
    public boolean equals(Object o) {
        if (o instanceof Member)   {
            Member t = (Member) o;
        
            return forename.equals(t.forename)
                && surname.equals(t.surname)
                && username.equals(t.username)
                && password.equals(t.password)
                && email.equals(t.email)
                && userID == t.userID;
        }
        return false;
    }
    
    @Override
    public String toString() {
        return "MemberID: " + userID + 
                "\nUsername: " + username +
                "\nForename: " + forename +
                "\nSurname: " + surname +
                "\nEmail: " + email +
                "\nPassword: " + password; 
    }
    
    
    /**
     * Method to calculate member's healthiness
     * Scale from 0 to 10
     * 
     * Member can score up to 5 points for their weight status based on BMI
     * 2.5 points for their avg activity time
     * 2.5 points for their avg calorie intake
     * @return
     * @throws ServletException 
     */
    public int calculateHealthiness() throws ServletException {
        double result = 0;
        double bmiHealth = calcHealthinessBMI();
        double activityHealth = calcHealthinessActivity();
        double dietHealth = calcHealthinessDiet();
        double bmiFactor = 5, exTimeFactor = 2.5, calsConsumedFactor = 2.5;

        result += bmiHealth * bmiFactor; 
        result += activityHealth * exTimeFactor;
        
        result += dietHealth * calsConsumedFactor;
        
        return (int) (result + 0.5); //round to nearest integer
    }
    
    /**
     * Calculate how healthy this user's BMI is
     * @return double between 0(unhealthy) and 1 (healthy)
     * @throws ServletException 
     */
    public double calcHealthinessBMI() throws ServletException {
        double bmi = calculateBMI();
        int healthyBMILow = 19, healthyBMIHigh = 25;
        
        double result = 1; //healthy BMI
        double distFromHealthyBMI = 0, maxDist = 10;
        
        
        if (bmi < healthyBMILow)
            distFromHealthyBMI = healthyBMILow - bmi;
        else if (bmi > healthyBMIHigh)
            distFromHealthyBMI = bmi - healthyBMIHigh;
        
        //BMI IS NOT IN THE HEALTHY REGION (return a number between 0 and 1)
        if(distFromHealthyBMI!=0){
            if(distFromHealthyBMI>=maxDist){
                result = 0;
            }else{
                result = 1 - distFromHealthyBMI /maxDist;
            }
        }
        
        return result;
    }
    
    /**
     * Calculate how healthy this user's avg Activity time is
     * @return double between 0(unhealthy) and 1 (healthy)
     * @throws ServletException 
     */
    public double calcHealthinessActivity() throws ServletException {
        ExerciseLogger el = ExerciseLogger.find(userID);
        
        int dailyActivityTime = el.findAverageDailyActivityTime();
        int goodDailyTime = 30;
        int greatDailyTime = goodDailyTime*2;
        
        if (dailyActivityTime > greatDailyTime)
            return 1.0;
        if (dailyActivityTime > goodDailyTime) {
            return (double)dailyActivityTime / (double)greatDailyTime;
        }
        
        return ((double)dailyActivityTime / (double)goodDailyTime) * 0.5;
    }
    
      /**
     * Calculate how healthy this user's avg daily calorie intake is
     * @return double between 0(unhealthy) and 1 (healthy)
     * @throws ServletException 
     */
    public double calcHealthinessDiet() throws ServletException {
        DietLogger dl = DietLogger.find(userID);
        
        int dailyCalsBurned = dl.findAverageDailyCalsConsumed();
        int healthyDailyCalorieConsumption = 2250; //we arnt differentiating between
        //male and female, so...

        int unhealthyDistance = 1000;
        int distance = Math.abs(healthyDailyCalorieConsumption - dailyCalsBurned);
        double result = 1 - ((double)distance / (double)unhealthyDistance);
        if(distance> unhealthyDistance){
            result = 0;
        }
        
        return result;
    }
    
    /**
     * Returns a calculated BMI score
     * @return BMI (double)
     * @throws ServletException 
     */
    public double calculateBMI() throws ServletException {
        PhysicalHealth ph = PhysicalHealth.find(userID);
        double height = ph.getHeight().getMetres();
        return (ph.getMostRecentWeightProgress().getWeight().getKilos()/ (height*height));
    }
    
    /**
     * Returns weight status based on the BMI score
     * 
     * @return one of these Strings : 
     * "Underweight", "Normal", "Overweight", "Obese", 
     * "Moderately Obese", "Severely Obese", "Morbidly Obese"
     * @throws ServletException 
     */
    public String getWeightStatus() throws ServletException{
        
        double bmi = this.calculateBMI();
        String result = "";
        
        if (bmi < 18.5){
            result = "Underweight";
        }else if (bmi < 24.9){
            result = "Normal";
        }else if (bmi < 26.9){
            result = "Overweight";
        }else if (bmi < 29.9){
            result = "Obese";
        }else if (bmi < 34.9){
            result = "Moderately obese";         
        }else if (bmi < 39.9){
            result = "Severely obese";
        }else{
            result = "Morbidly obese";
        }
        
        return result;
        
        
    }
}
