/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controllers;

import Model.*;
import Goals.*;
import Model.PhysicalHealth.Weight;
import View.Validator;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author xmw13bzu
 */
@WebServlet(name = "UpdateGoalController", urlPatterns = {"/UpdateGoalController"})
public class UpdateGoalController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        HttpSession session = request.getSession(false);        
        if (session == null) 
            throw new ServletException("Attempting to create a goal while no session is active (no user logged in)");
              
        Member member = (Member) session.getAttribute("member");
        
        int goalID = Integer.parseInt(request.getParameter("goalID"));
        String action = request.getParameter("submit");
        
        if (action.equals("Cancel")) {
            Goal.delete(goalID);  
            request.setAttribute("updateMessage", "Goal successfully cancelled.");
            request.getRequestDispatcher("GoalsController").forward(request, response);
        }
        else { //updating fields
            Goal goal = Goal.find(goalID);
            
            //Validate all the info and make type conversions where needed
            Validator validator = new Validator();
            
            String targetString = request.getParameter("target");
            int target = validator.validatePositiveInt("Invalid target entered: " + targetString, targetString);
            
            if (validator.isValid()) {
                
                if (goal.getGoalType() == Goal.GoalType.WEIGHT_HIGH || goal.getGoalType() == Goal.GoalType.WEIGHT_LOW) { 
                    String weightUnit = request.getParameter("wUnit");
                    if (weightUnit.equals("imperial")){
                        target = validator.validateWeightImperial("Invalid target entered: " + targetString+ "." , targetString);
                    }else{
                        target = validator.validateWeightMetric("Invalid target entered: " + targetString, targetString);
                    }
                }

                goal.updateInt("target", target);
                
                    
            }
            
            String endDateString = request.getParameter("goalDeadline");
            HKFDate endDate = new HKFDate();
            if (validator.validateDate("Invalid end date entered, must be in YYYY-MM-DD format: " + endDateString, endDateString)) {
                endDate = new HKFDate(endDateString);

                if (endDate.compareTo(new HKFDate()) < 0) {//in the past
                    validator.appendErrMsg(endDateString + " has already passed!");
                }
                else if (goal.getStartDate().compareTo(endDate) > 0) {
                    validator.appendErrMsg("Invalid date entered, goal would end before it starts.");
                }
                else {
                    goal.updateString("goalDeadline", endDateString);
                }
            }
            
           
            
            
            if (validator.isValid()) {
                request.setAttribute("updateMessage", "Goal successfully updated.");
                request.getRequestDispatcher("GoalsController").forward(request, response);
            }
            else {
                request.setAttribute("errorMessage", validator.getErrMsg());
                request.getRequestDispatcher("updateGoal.jsp").forward(request, response);
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
