/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controllers;

import Model.Member;
import Model.PhysicalHealth.Height;
import Model.PhysicalHealth.PhysicalHealth;
import Model.PhysicalHealth.Weight;
import View.Validator;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "UpdateUserController", urlPatterns = {"/UpdateUserController"})
public class UpdateUserController extends HttpServlet {

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
            throw new ServletException("Attempting to update account while no session is active (no user logged in)");
              
        Member member = (Member) session.getAttribute("member");
        
        String action = request.getParameter("submit");
        
        if (action.equals("Delete Account")) {
            member.delete();  
            request.setAttribute("deleteMessage", "Account successfully deleted.");
            request.getRequestDispatcher("LogoutController").forward(request, response);
        }
        else { //updating fields
            //Validate all the info and make type conversions where needed
            Validator validator = new Validator();
            
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String forename = request.getParameter("forename");
            String surname = request.getParameter("surname");
            String heightString = request.getParameter("height");
            String heightUnit = request.getParameter("hUnit");
            
            //modify EMAIL
            if (!validator.isEmpty(email)) {
                if (validator.validateEmail("Invalid email entered: " + email, email)) {
                    member.updateValue("email", email);
                }
            }
            
            //modify PASSWORD
            if (!validator.isEmpty(password)) {
                String errMsg = "Invalid password entered, must be between 6 and 12 characters long "
                            + "containing only letters, numbers and underscores: " + password;
                if (validator.validatePassword(errMsg, password)) {
                    member.updateValue("password", password);
                }
            }
            
            //MODIFY FORENAME
            if(!validator.isEmpty(forename)){
                validator.validateName("Invalid forename entered: " + forename, forename);
                member.updateValue("forename", forename);
            }
            
            //MODIFY SURNAME
            if(!validator.isEmpty(surname)){
                validator.validateName("Invalid surname entered: " + surname, surname);
                member.updateValue("surname", surname);
            } 
            
            //MODIFY HEIGHT
            if(!validator.isEmpty(heightString)){
            int heightCm;
            if (heightUnit.equals("imperial")){
                heightCm = validator.validateHeightImperial("Invalid height entered: " + heightString +  " Please enter weight in X'Y format" , heightString);
            }else{
            heightCm = validator.validatePositiveInt("Invalid height entered: " + heightString, heightString);
            }

                PhysicalHealth ph = PhysicalHealth.find(member.getUserID());
                Height height = new Height(heightCm);
                ph.setHeight(height);
                ph.update();
            }
            
            
            member = Member.find(member.getUserID());
            session.setAttribute("member", member);
            
            if (validator.isValid()) {
                request.setAttribute("updateMessage", "Account successfully updated.");
                request.getRequestDispatcher("home.jsp").forward(request, response);
            }
            else {
                request.setAttribute("errorMessage", validator.getErrMsg());
                request.getRequestDispatcher("accountManagement.jsp").forward(request, response);
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
