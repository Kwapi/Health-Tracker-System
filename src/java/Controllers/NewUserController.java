package Controllers;

import Model.*;
import Model.PhysicalHealth.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import View.*;


/**
 * A controller for creating a new user and adding them to the database.
 * @author Mercury Aimnh
 */
@WebServlet(name = "NewUserController", urlPatterns = {"/NewUserController"})
public class NewUserController extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
            
        //Get the details the user has supplied in the form
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String forename = request.getParameter("forename");
        String surname = request.getParameter("surname");
        String weightString = request.getParameter("weight");
        String heightString = request.getParameter("height");
        String weightUnit = request.getParameter("wUnit");
        String heightUnit = request.getParameter("hUnit");
        
        //Validate all the info and make type conversions where needed
        Validator validator = new Validator();
        
        //Just validates, leaves as a string.
        validator.validateEmail("Invalid email entered: " + email, email);
        validator.validateUsername(
                "Invalid username entered, must be between 3 and 12 characters long containing only letters, numbers and underscores, and starting with a letter: " + username, username);
        validator.validatePassword(
                "Invalid password entered, must be between 6 and 12 characters long containing only letters, numbers and underscores: " + password, password);
        validator.validateName("Invalid forename entered: " + forename, forename);
        validator.validateName("Invalid surname entered: " + surname, surname);
        
        Weight weight;
        if (weightUnit.equals("imperial")){
            weight = new Weight(validator.validateWeightImperial("Invalid weight entered: " + weightString+ "." , weightString));
        }else{
            weight = new Weight(validator.validateWeightMetric("Invalid weight entered: " + weightString, weightString));
        }
        //Validates AND CONVERTS TO INT.
        int height;
        if (heightUnit.equals("imperial")){
            height = validator.validateHeightImperial("Invalid height entered: " + heightString +  " Please enter weight in X'Y format" , heightString);
        }else{
            height = validator.validatePositiveInt("Invalid height entered: " + heightString, heightString);
        }
        
            
        if (Member.findByEmail(email) != null)
            validator.appendErrMsg("Account with that email already exists");
        
        if (Member.findByUsername(username) != null)
            validator.appendErrMsg("Account with that username exists");
        
        //If valid, create and persist the new member, send them to their new homepage
        if (validator.isValid()) {
            //Create a user bean, and add their details to the database.
            Member newMember = new Member (username, password, email, forename, surname);
            newMember.persist();

            newMember.setUserID(Member.findID(newMember));

            PhysicalHealth physHealth = new PhysicalHealth(newMember.getUserID(), height, weight);
            physHealth.persist();

            HttpSession session = request.getSession(false);
            session.setAttribute("member", newMember);

            //Log the user in
            request.setAttribute("username", username);
            request.setAttribute("password", password);
            request.getRequestDispatcher("home.jsp").forward(request, response);
        }
        //Else send user back with the error messages produced by the validator
        else {
            request.setAttribute("errorMessage", validator.getErrMsg());
            request.getRequestDispatcher("accountCreation.jsp").forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
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
     * Handles the HTTP
     * <code>POST</code> method.
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
