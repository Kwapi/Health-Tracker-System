/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controllers;

import Model.*;
import View.Validator;
import java.io.IOException;
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
@WebServlet(name = "LogExerciseController", urlPatterns = {"/LogExerciseController"})
public class LogExerciseController extends HttpServlet {

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
            throw new ServletException("Attempting to log an exerciseProgress while no session is active (no user logged in)");
        
              
        //Validate all the info and make type conversions where needed
        Validator validator = new Validator();
        
        
        
        String durationString = request.getParameter("duration");
        
        String exerciseIDString = request.getParameter("exercise");
        
       
        int duration = validator.validatePositiveInt("Invalid duration time entered: " + durationString, durationString);
        int exerciseID = validator.validatePositiveInt("Invalid exercise entered: " + exerciseIDString, exerciseIDString);
        
        //should be  YYYY-MM-DD, i.e is shit and doesnt enforce any formatting 
        //on user in input type=date, deal with it later
        String dateString = request.getParameter("date");
        String timeString = request.getParameter("time");
        HKFDate date = new HKFDate();
        if (validator.validateDate("Invalid date entered, must be in YYYY-MM-DD format: " + dateString, dateString)) {
            date = new HKFDate(dateString,timeString);
            
            if (date.compareTo(new HKFDate()) > 0) //in the future
                validator.appendErrMsg("Cannot log things which haven't happened yet");
        }
        
        if (validator.isValid()) {
            Member member = (Member) session.getAttribute("member");
            ExerciseProgress ep = new ExerciseProgress(Exercise.find(exerciseID), 
                                                date, duration);
            ep.persist(member.getUserID());

            //reload weight log page, refresh data essentially
            request.getRequestDispatcher("ExerciseLogController").forward(request, response);
        }
        //Else send user back with the error messages produced by the validator
        else {
            request.setAttribute("errorMessage", validator.getErrMsg());
            request.getRequestDispatcher("exerciseProgress.jsp").forward(request, response);
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
