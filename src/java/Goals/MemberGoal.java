package Goals;

import Controllers.DatabaseAccess;
import Model.HKFDate;
import Model.Member;
import Model.PhysicalHealth.PhysicalHealth;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import javax.servlet.ServletException;

/**
 *
 * Bean that models MemberGoal linked table in the db
 * includes:
 * ID
 * memberId
 * goal
 */
public class MemberGoal implements Comparable<MemberGoal> {
    int ID;
    int memberID;
    Goal goal;
    
    /**
     * All data available
     * @param ID
     * @param memberID
     * @param goal 
     */
    public MemberGoal(int ID, int memberID, Goal goal) {
        this.ID = ID;
        this.memberID = memberID;
        this.goal = goal;
    }
    
    /**
     * Creating new MemberGoal (dummy id) to be persisted later
     * @param memberID
     * @param goal 
     */
    public MemberGoal(int memberID, Goal goal) {
        this.ID = -1;
        this.memberID = memberID;
        this.goal = goal;
    }
    
    //GETTER AND SETTER METHODS START HERE
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getMemberID() {
        return memberID;
    }

    public void setMemberID(int memberID) {
        this.memberID = memberID;
    }

    public Goal getGoal() {
        return goal;
    }

    public void setGoal(Goal goal) {
        this.goal = goal;
    }
    
    //GETTER AND SETTER METHODS END HERE
    @Override
    public String toString() {
        return "MemberGoal{" + "ID=" + ID + ", memberID=" + memberID + ", goal=" + goal + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MemberGoal other = (MemberGoal) obj;
        if (this.ID != other.ID) {
            return false;
        }
        if (this.memberID != other.memberID) {
            return false;
        }
        if (!Objects.equals(this.goal, other.goal)) {
            return false;
        }
        return true;
    }
    
    
    
    /**
     * Method to find Goal from database using
     *
     * @param goalID
     * @return
     * @throws ServletException
     */
    public static MemberGoal find(int memberGoalID) throws ServletException {
        try {
            //Connect to Database
            Connection con = DatabaseAccess.getConnection();
            //Search through member_goals Table inside Database
            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM member_goals WHERE (id = ?)");
            ps.setInt(1, memberGoalID);
            ResultSet result = ps.executeQuery();//Run statement
            MemberGoal memberGoal = null; //Creating a MemberGoal object to set returned value to
            //If we find Goal set create a new MemberGoal using returned values
            if (result.next()) {
                memberGoal = new MemberGoal(memberGoalID,
                                    result.getInt("memberID"),
                                    Goal.find(result.getInt("goalID")));
            }

            con.close(); //Close Connection
            return memberGoal;
        } catch (SQLException ex) {
            throw new ServletException("Find Problem: Searching for MemberGoal, memberGoalID: " + memberGoalID, ex);
        }
    }

    /**
     * Method to find all Goals for a particular user from database
     *
     * @param memberID 
     * @return ArrayList of Goals
     * @throws ServletException
     */
    public static ArrayList<Goal> findAllGoalsForMember(int memberID) throws ServletException {
        try {
            //Connect to Database
            Connection con = DatabaseAccess.getConnection();
            //Get all relevant data from member_goals Table                
            PreparedStatement ps = con.prepareStatement(
                    "SELECT goalID FROM member_goals WHERE (memberID = ?)");

            ps.setInt(1, memberID);
            
            ResultSet result = ps.executeQuery();//Run statement
            ArrayList<Goal> goalList = new ArrayList<>();
            //If we find Goal set create a new Goal using returned values
            while (result.next()) {
                Goal goal = Goal.find(result.getInt("goalID"));
                goalList.add(goal);
            }

            con.close(); //Close Connection
            return goalList;
        } catch (SQLException ex) {
            throw new ServletException("Find Problem: Searching for all Goals for memberID: " + memberID, ex);
        }
    }
    
    /**
     * Method to find all Users for a particular Goal from database
     *
     * @param goalID 
     * @return ArrayList of Goals
     * @throws ServletException
     */
    public static ArrayList<Member> findAllMembersForGoal(int goalID) throws ServletException {
        try {
            //Connect to Database
            Connection con = DatabaseAccess.getConnection();
            //Get all relevant data from member_goals Table                
            PreparedStatement ps = con.prepareStatement(
                    "SELECT memberID FROM member_goals WHERE (goalID = ?)");

            ps.setInt(1, goalID);
            
            ResultSet result = ps.executeQuery();//Run statement
            ArrayList<Member> memberList = new ArrayList<>();
            //If we find Goal set create a new Goal using returned values
            while (result.next()) {
                Member member = Member.find(result.getInt("memberID"));
                memberList.add(member);
            }

            con.close(); //Close Connection
            return memberList;
        } catch (SQLException ex) {
            throw new ServletException("Find Problem: Searching for all members for goalID: " + goalID, ex);
        }
    }

    /**
     * Method to persist information to the member_goals table
     * @throws ServletException 
     */
    public void persist() throws ServletException {
        goal.persist();
        int goalID = Goal.findID(goal);
        
        try {
            Connection con = DatabaseAccess.getConnection();

            PreparedStatement ps = con.prepareStatement(
                        "INSERT INTO member_goals (memberID, goalID) VALUES(?, ?)");
            
            ps.setInt(1, memberID);
            ps.setInt(2, goalID);

            ps.executeUpdate();
            
            con.close();
            
        } catch (SQLException ex) {
            throw new ServletException(
                    "Persist Problem: persisting member_goals details, memberID: " + memberID + " goalID: " + goalID, ex);
        }
    }

    /**
     * Compares based on endDate
     * @param mg
     * @return 
     * @throws ServletException 
     */
    @Override
    public int compareTo(MemberGoal mg) {
        return goal.endDate.compareTo(mg.goal.endDate);
    }
    
}
