package Goals;

import Controllers.DatabaseAccess;
import Model.*;
import Model.PhysicalHealth.PhysicalHealth;
import Model.PhysicalHealth.Weight;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;

/**
 * Bean that models the Goal table in the db
 * Can be sorted by date
 * Includes:
 * goalId
 * dateSet
 * startDate
 * endDate
 * goalType
 * target
 * notified
 * @author xmw13bzu
 */
public class Goal implements Comparable<Goal> {

    int goalID;
    HKFDate dateSet;
    HKFDate startDate;
    HKFDate endDate;
    GoalType goalType;
    int target;
    boolean notified;
    
    
    public enum GoalType {

        WEIGHT_HIGH(0), WEIGHT_LOW(1), CALORIES_BURNED(2), ACTIVITY_TIME(3), CALORIES_CONSUMED_HIGH(4), CALORIES_CONSUMED_LOW(5);
        final int value;

        GoalType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public static GoalType toGoalType(int i) {
            return GoalType.values()[i];
        }

        @Override
        public String toString() {
            switch (value) {
                case 0:
                    return "Weight Gain";
                case 1:
                    return "Weight Loss";
                case 2:
                    return "Calorie Burn";
                case 3:
                    return "Exercise Time";
                case 4:
                    return "Min Calorie Consumption";
                default:
                    return "Max Calorie Consumption";
            }
        }
    }

    
    /**
     * All data available: getting goal from database
     * @param goalID
     * @param dateSet
     * @param startDate
     * @param endDate
     * @param goalType
     * @param target
     * @param notified 
     */
    public Goal(int goalID, HKFDate dateSet, HKFDate startDate, HKFDate endDate, GoalType goalType, int target, boolean notified) {
        this.goalID = goalID;
        this.dateSet = dateSet;
        this.startDate = startDate;
        this.endDate = endDate;
        this.goalType = goalType;
        this.target = target;
        this.notified = notified;
    }

    
    /**
     * User creating a goal
     * @param startDate
     * @param endDate
     * @param goalType
     * @param target 
     */
    public Goal(HKFDate startDate, HKFDate endDate, GoalType goalType, int target) {
        this.goalID = -1;
        this.dateSet = new HKFDate(); //today's date
        this.startDate = startDate;
        this.endDate = endDate;
        this.goalType = goalType;
        this.target = target;
        this.notified = false;
    }
    
    //GETTER AND SETTER METHODS START HERE
    public int getGoalID() {
        return goalID;
    }

    public void setGoalID(int goalID) {
        this.goalID = goalID;
    }

    public HKFDate getDateSet() {
        return dateSet;
    }

    public void setDateSet(HKFDate dateSet) {
        this.dateSet = dateSet;
    }

    public HKFDate getStartDate() {
        return startDate;
    }
    
    public boolean isNotified () {
        return notified;
    }

    public void setStartDate(HKFDate startDate) {
        this.startDate = startDate;
    }

    public HKFDate getEndDate() {
        return endDate;
    }

    public void setEndDate(HKFDate endDate) {
        this.endDate = endDate;
    }

    public GoalType getGoalType() {
        return goalType;
    }

    public void setGoalType(GoalType goalType) {
        this.goalType = goalType;
    }

    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
    }
    
    public void setNotified(boolean notified) {
        this.notified = notified;
    }
    //GETTER AND SETTER METHODS END HERE
    @Override
    public String toString() {
        return "Goal{" + "goalID=" + goalID + ", dateSet=" + dateSet + ", startDate=" + startDate + ", endDate=" + endDate + ", goalType=" + goalType + ", target=" + target + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Goal) {
            Goal g = (Goal) o;

            return goalID == g.goalID
                    && dateSet == g.dateSet
                    && startDate == g.startDate
                    && endDate == g.endDate
                    && goalType == g.goalType
                    && target == g.target
                    && notified == g.notified;
        }

        return false;
    }

    /**
     * Method to find Goal from database using
     *
     * @param goalID
     * @return
     * @throws ServletException
     */
    public static Goal find(int goalID) throws ServletException {
        try {
            //Connect to Database
            Connection con = DatabaseAccess.getConnection();
            //Search through Exercise Table inside Database
            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM goal WHERE (id = ?)");
            ps.setInt(1, goalID);
            ResultSet result = ps.executeQuery();//Run statement
            Goal goal = null; //Creating a Goal object to set returned value to
            //If we find Goal set create a new Goal using returned values
            if (result.next()) {
                goal = new Goal(goalID,
                        new HKFDate(result.getString("goalDate")),
                        new HKFDate(result.getString("goalStart")),
                        new HKFDate(result.getString("goalDeadline")),
                        GoalType.toGoalType(result.getInt("goalType")),
                        result.getInt("target"),
                        result.getBoolean("notified"));
            }

            con.close(); //Close Connection
            return goal;
        } catch (SQLException ex) {
            throw new ServletException("Find Problem: Searching for Goal, id: " + goalID, ex);
        }
    }

    /**
     * Method to find a Goal's ID in the case that it hasn't been set in the bean already.
     * Intended for when a new Goal has just been persisted, and we need to find the id 
     * generated by the database during that persist, so that the new Goal's id can be stored
     * in it's associated member_goals
     *
     * @param goal
     * @return id of goal, if found 
     * @throws ServletException Exception, Goal was not found
     */
    public static int findID(Goal goal) throws ServletException {
        try {
            //Connect to Database
            Connection con = DatabaseAccess.getConnection();
            //SQL Statement to run
            PreparedStatement ps = con.prepareStatement(
                    "SELECT id FROM goal WHERE (goalType = ? AND goalDate = ? AND goalStart = ? AND goalDeadline = ? AND target = ? AND notified = ?)");
            ps.setInt(1, goal.goalType.ordinal());
            ps.setString(2, goal.dateSet.toString());
            ps.setString(3, goal.startDate.toString());
            ps.setString(4, goal.endDate.toString());
            ps.setInt(5, goal.target);
            ps.setBoolean(6, goal.notified);
            
            ResultSet result = ps.executeQuery();//Run statement
            int id = -1;
            
            if (result.next())
                id = result.getInt("id");
            
            con.close();
            
            return id;
        } catch (SQLException ex) {
            throw new ServletException(
                    "Find Problem: searching for goalID in Goal.findID, "
                            + goal, ex);
        }
    }
    
    /**
     * Method to find all Goals from database
     *
     * @param memberID 
     * @return ArrayList of Goals
     * @throws ServletException
     */
    public static ArrayList<Goal> findAll() throws ServletException {
        try {
            //Connect to Database
            Connection con = DatabaseAccess.getConnection();
            //Get all data from exercise Table                
            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM goal");

            ResultSet result = ps.executeQuery();//Run statement
            ArrayList<Goal> goalList = new ArrayList<>();
            //If we find Goal set create a new Meal using returned values
            while (result.next()) {
                Goal goal = new Goal(result.getInt("id"),
                        new HKFDate(result.getString("goalDate")),
                        new HKFDate(result.getString("goalStart")),
                        new HKFDate(result.getString("goalDeadline")),
                        GoalType.toGoalType(result.getInt("goalType")),
                        result.getInt("target"),
                        result.getBoolean("notified"));
                goalList.add(goal);
            }

            con.close(); //Close Connection
            return goalList;
        } catch (SQLException ex) {
            throw new ServletException("Find Problem: Searching for all Goals", ex);
        }
    }

    /**
     * Method to add information to the goal table
     * @param memberID ID of member that we are adding information for
     * @throws ServletException 
     */
    public void persist() throws ServletException {
        try {
            Connection con = DatabaseAccess.getConnection();

            PreparedStatement ps = con.prepareStatement(
                        "INSERT INTO goal (goalType, goalDate, goalStart, goalDeadline, target, notified) VALUES(?, ?, ?, ?, ?, ?)");
            
            ps.setInt(1, goalType.ordinal());
            ps.setString(2, dateSet.toString());
            ps.setString(3, startDate.toString());
            ps.setString(4, endDate.toString());
            ps.setInt(5, target);
            ps.setBoolean(6, notified);

            ps.executeUpdate();
            
            con.close();
            
        } catch (SQLException ex) {
            throw new ServletException(
                    "Persist Problem: persisting goal details", ex);
        }
    }
    
    /**
     * Update one of the string parameters of this Goal in the database
     *
     * @param valueName The name of the value to be changed
     * @param newValue The new value for the above to be set to
     * @throws ServletException
     * @throws SQLException
     */
    public void updateString(String valueName, String newValue) throws ServletException {
        try {
            Connection con = DatabaseAccess.getConnection();
            PreparedStatement ps = con.prepareStatement("UPDATE goal SET " + valueName + " = ? WHERE id = ?;");
            ps.setString(1, newValue);
            ps.setInt(2, goalID);
            ps.execute();
        } catch (SQLException ex) {
            throw new ServletException("Problem updating goal, field: " + valueName + " value: " + newValue, ex);
        }
    }
    
     /**
     * Update one of the int parameters of this Goal in the database
     *
     * @param valueName The name of the value to be changed
     * @param newValue The new value for the above to be set to
     * @throws ServletException
     * @throws SQLException
     */
    public void updateInt(String valueName, int newValue) throws ServletException {
        try {
            Connection con = DatabaseAccess.getConnection();
            PreparedStatement ps = con.prepareStatement("UPDATE goal SET " + valueName + " = ? WHERE id = ?;");
            ps.setInt(1, newValue);
            ps.setInt(2, goalID);
            ps.execute();
        } catch (SQLException ex) {
            throw new ServletException("Problem updating goal, field: " + valueName + " value: " + newValue, ex);
        }
    }
    
    /**
     * Remove goal with given id from database
     * @throws SQLException
     * @throws ServletException
     */
    public static void delete(int goalID) throws ServletException {
        try {
            Connection con = DatabaseAccess.getConnection();
            PreparedStatement ps = con.prepareStatement("DELETE FROM goal WHERE (id = ?)");
            ps.setInt(1, goalID);
            ps.execute();
        } catch (SQLException ex) {
            throw new ServletException("Problem deleting goal, id: " + goalID);
        }
    }
    
    /**
     * Compares based on endDate
     * @param g
     * @return 
     * @throws ServletException 
     */
    @Override
    public int compareTo(Goal g) {
        return endDate.compareTo(g.endDate);
    }
    
    /**
     * Returns the progress of this goal as percentage
     * @param physHealth
     * @param dietLog
     * @param exLog
     * @return 
     */
    public int checkProgressAsPercentage(PhysicalHealth physHealth, DietLogger dietLog, ExerciseLogger exLog) {
            double progressPercent = 0;

            switch (goalType) {
                    case WEIGHT_HIGH: {
                        Weight currentWeight = physHealth.getMostRecentWeightProgress().getWeight();
                        Weight startweight = physHealth.findWeightOnDate(startDate);
                        
                        if (currentWeight.getGrams() >= target)
                            return 100; //100% complete
                        else {
                            int targetDifference = target - startweight.getGrams();
                            int actualDifference = target - currentWeight.getGrams();
                            if(targetDifference==actualDifference){
                                return 0;
                            }else{
                            progressPercent = 1 - ((double)(actualDifference) / (double)targetDifference);
                            }
                        }
                        break;
                    }
                    case WEIGHT_LOW: {
                        Weight currentWeight = physHealth.getMostRecentWeightProgress().getWeight();
                        Weight startweight = physHealth.findWeightOnDate(startDate);
                        
                        if (currentWeight.getGrams() <= target)
                            return 100; //100% complete
                        else {
                            int targetDifference = startweight.getGrams() - target;
                            int actualDifference = currentWeight.getGrams() - target;
                            
                            if(targetDifference==actualDifference){
                                return 0;
                            }else{
                                
                            progressPercent = 1 - ((double)actualDifference / (double)targetDifference);
                            }
                        }
                        break;
                    }
                    case CALORIES_BURNED: {
                        int cals;
                        
                        if (startDate.compareToWithoutTime(endDate) == 0) {
                            cals = exLog.findCalsBurnedOnDate(startDate);
                        }
                        else {
                            cals = exLog.findCalsBurnedBetweenDates(startDate, endDate);
                        }
                        
                        progressPercent = (double)cals / (double)target;
                        break;
                    }
                    case ACTIVITY_TIME: {
                        int time;
                        if (startDate.compareToWithoutTime(endDate) == 0) {
                            time = exLog.findExerciseTimeOnDate(startDate);
                        }
                        else {
                            time = exLog.findExerciseTimeBetweenDates(startDate, endDate);
                        }
                       
                        progressPercent = (double)time / (double)target;
                        break;
                    }
                    case CALORIES_CONSUMED_HIGH:{
                        int calsConsumed;
                        if (startDate.compareToWithoutTime(endDate) == 0) {
                            calsConsumed = dietLog.findCalsConsumedOnDate(startDate);
                        }
                        else {
                            calsConsumed = dietLog.findCalsConsumedBetweenDates(startDate, endDate);
                        }
                        progressPercent = (double)calsConsumed / (double)target;
                        break;
                    }
                    case CALORIES_CONSUMED_LOW:  {
                        int calsConsumed;
                        if (startDate.compareToWithoutTime(endDate) == 0) {
                            calsConsumed = dietLog.findCalsConsumedOnDate(startDate);
                        }
                        else {
                            calsConsumed = dietLog.findCalsConsumedBetweenDates(startDate, endDate);
                        }
                        if(calsConsumed>target){
                            progressPercent = 0;
                        }
                        progressPercent = 1-((double)calsConsumed / (double)target);
                        break;
                    }
                       
            }
            if(progressPercent >=1){
                progressPercent = 1;
            }
            return (int)(progressPercent*100);
    }
}

