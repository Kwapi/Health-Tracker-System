/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controllers;

import Goals.*;
import Model.HKFDate;
import Model.Member;
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
@WebServlet(name = "CreateGoalController", urlPatterns = {"/CreateGoalController"})
public class CreateGoalController extends HttpServlet {

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
              
        //Validate all the info and make type conversions where needed
        Validator validator = new Validator();
        String weightUnit = request.getParameter("wUnit");
        String goalTypeString = request.getParameter("goalType");
        int goalType = validator.validatePositiveInt("Invalid goal type entered: " + goalTypeString, goalTypeString);                  
        String targetString = request.getParameter("target");
        
        int target;
        
        //weight based goals
        if(goalType == 0 || goalType ==1){
            Weight weight;
            if (weightUnit.equals("imperial")){
                weight = new Weight(validator.validateWeightImperial("Invalid weight entered: " + targetString+ "." , targetString));
                target = weight.getGrams();
                
            }else{
                weight = new Weight(validator.validateWeightMetric("Invalid weight entered: " + targetString, targetString));
                target = weight.getGrams();
            }
        }else{
              target = validator.validatePositiveInt("Invalid target entered: " + targetString, targetString);
        }
        
        String startDateString = request.getParameter("startDate");
        HKFDate startDate = new HKFDate();
        if (validator.validateDate("Invalid start date entered, must be in YYYY-MM-DD format: " + startDateString, startDateString)) {
            startDate = new HKFDate(startDateString);
            startDate.setHours(0);
            startDate.setMinutes(0);
        }
        
        String endDateString = request.getParameter("endDate");
        HKFDate endDate = new HKFDate();
        if (validator.validateDate("Invalid end date entered, must be in YYYY-MM-DD format: " + endDateString, endDateString)) {
            endDate = new HKFDate(endDateString);
            
            if (endDate.compareTo(new HKFDate()) < 0) //in the past
                validator.appendErrMsg(endDateString + " has already passed!");
        }
        
        if (startDate.compareTo(endDate) > 0) {
            validator.appendErrMsg("Invalid dates entered, goal ends before it starts");
        }
  
        if (validator.isValid()) {
            Member member = (Member) session.getAttribute("member");
            MemberGoal mg = new MemberGoal(member.getUserID(),
                                    new Goal(startDate, endDate, Goal.GoalType.toGoalType(goalType), target));
            mg.persist();

            //Reload goal page, refresh data essentially
            request.getRequestDispatcher("GoalsController").forward(request, response);
        }
        //Else send user back with the error messages produced by the validator
        else {
            request.setAttribute("errorMessage", validator.getErrMsg());
            request.getRequestDispatcher("goalProgress.jsp").forward(request, response);
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
