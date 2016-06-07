
package View;

import Model.PhysicalHealth.Height;
import Model.PhysicalHealth.Weight;

/**
 *  Validator class that works as a bridge between View and Controller
 * It assures that user input is correct
 * 
 */
public class Validator {
    
    String errorMessage; //error message that will be displayed to the user
    
    /**
     * Default constructor that initalizes the errorMessaage to be empty
     */
    public Validator() {
        errorMessage = "";
    }
    /**
     * Checks if the error message contains anything
     * @return true if error message is empty, else, false
     */
    public boolean isValid() {
        return errorMessage.equals("");
    }
    /**
     * Returns the error message
     * @return errorMessage
     */
    public String getErrMsg() {
        return errorMessage;
    }
    /**
     * Sets the error message
     * @param newMsg new error message
     */
    public void setErrMsg(String newMsg) {
        errorMessage = newMsg;
    }
    /**
     * Clears the error message
     */
    public void clearErrMsg() {
        errorMessage = "";
    }
    /**
     * Adds new message to the existing error message
     * @param newMsg 
     */
    public void appendErrMsg(String newMsg) {
        if (!errorMessage.equals(""))
            errorMessage += "\n";
        
        errorMessage += newMsg;
    }
    
    /**
     * Checks if the passed String contains anything
     * @param value - String to be checked
     * @return true if empty, else false
     */
    public boolean isEmpty(String value) {
        return value == null || value.equals("");
    }
    
    /**
     * Checks if the given int is within the given range
     * @param value - given value
     * @param low - low threshold
     * @param high - high threshold
     * @return true if within range, else false
     */
    public boolean isWithinRange(int value, int low, int high) {
        return value >= low && value <= high;
    }
    
    /**
     * Checks if the given emal is valid
     * Has to follow xxxx@yyy.zzz format
     * @param errMsg - current error message (might be extended)
     * @param email
     * @return true if valid, else false
     */
    public boolean validateEmail(String errMsg, String email){
        if (!isEmpty(email) && email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]"
                + "+\\.[A-Za-z]{2,6}$"))
            return true;
        else {
            appendErrMsg(errMsg);
            return false;
        }
    }
  
    /**
     * Checks if the given date is in yyyy-mm-dd format from between 1900-01-01
     * and 2099-12-31
     * @param errMsg current error message (might be extended)
     * @param dateString
     * @return true if valid, else false
     */
    public boolean validateDate(String errMsg, String dateString){
        if (!isEmpty(dateString) && dateString.matches("^(19|20)\\d\\d[- /.]"
                + "(0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])$"))
            return true;
        else {
            appendErrMsg(errMsg);
            return false;
        }
    }
    
    
    
    /**
     * Checks if the given username is valid (between 3 and 12 characters
     * containing only letters, numbers and underscores, 
     * and starting with a letter)
     * @param errMsg current error message (might be extended)
     * @param username
     * @return true if valid, else false
     */
    public boolean validateUsername(String errMsg, String username){
        if (!isEmpty(username) && username.matches("^[A-Za-z]"
                + "[A-Za-z0-9_]{2,11}$"))
            return true;
        else {
            appendErrMsg(errMsg);
            return false;
        }
    }
    
    //string containing only alphabetic characters and spaces
    public boolean validateName(String errMsg, String name){
        if (!isEmpty(name) && name.matches("[A-Za-z ]+"))
            return true;
        else {
            appendErrMsg(errMsg);
            return false;
        }
    }
    
 
    /**
     * Checks if the password is in the correct format (String containing 
     * only alphanumeric characters and underscores - between 6 and 12 chars)
     * @param errMsg current error message (might be extended)
     * @param password
     * @return true if valid, else false
     */
    public boolean validatePassword(String errMsg, String password){
        if (!isEmpty(password) && password.matches("[A-Za-z0-9_]{6,12}"))
            return true;
        else {
            appendErrMsg(errMsg);
            return false;
        }
    }
    
    /**
     * Checks if the given String can be cast to an int
     * @param errMsg current error message (might be extended)
     * @param number
     * @return value extracted from the String
     */
    public int validateInt(String errMsg, String number) {        
        int result = 0;
        
        try {
            result = Integer.parseInt(number);
        } catch (Exception ex) { 
            appendErrMsg(errMsg);
            result = 0;
        }
        
        return result;
    }
    
    /**
     * Checks if the given String contains weight in Imperial format
     * (lbs ints)
     * @param errMsg current error message (might be extended)
     * @param weight
     * @return weight in grams
     */
    public int validateWeightImperial(String errMsg, String weight){
        int result = 0;
        
        try {
            result = Integer.parseInt(weight);
            if (result < 0) 
                throw new Exception("");
        } catch (Exception ex) { 
            appendErrMsg(errMsg);
            result = 0;
        }     
        return Weight.toGramsFromPounds(result);
        
    }
    /**
     * Checks if the given String contains weight in Metric format
     * (kg ints)
     * @param errMsg current error message (might be extended)
     * @param weight
     * @return weight in grams
     */
    public int validateWeightMetric(String errMsg, String weight){
        double result = 0;
        
        try {
            result = Double.parseDouble(weight);
            if (result < 0) 
                throw new Exception("");
        } catch (Exception ex) { 
            appendErrMsg(errMsg);
            result = 0;
        }     
        return Weight.toGramsFromKilos(result);
        
    }
    
    /**
     * Checks if the given String contains height in Imperial format
     * (FEET'INCHES)
     * @param errMsg
     * @param height
     * @return height in cm
     */
    public int validateHeightImperial(String errMsg, String height){
        int feet = 0, inches = 0;
        
        if (!isEmpty(height)) {
            if (height.matches("[0-9]'[0-9]{1,2}")) {
                String [] parts = height.split("'");
                
                try {
                    feet = Integer.parseInt(parts[0]);
                    inches = Integer.parseInt(parts[1]);
                    if (feet < 0 || inches < 0) 
                        throw new Exception("");
                } catch (Exception ex) { 
                    appendErrMsg(errMsg);
                    feet = 0;
                    inches = 0;
                }  
            } 
            else {
                appendErrMsg(errMsg);
            }
        }
        else {
            appendErrMsg(errMsg);
        }
        
        return Height.toCentimetersFromImperial(feet, inches);
        
    }
    
    /**
     * Checks if the given String can be converted into a positive int
     * @param errMsg
     * @param number
     * @return converted value (0 if failed)
     */
    public int validatePositiveInt(String errMsg, String number) {
        int result = 0;
        
        try {
            result = Integer.parseInt(number);
            if (result < 0) 
                throw new Exception("");
        } catch (Exception ex) { 
            appendErrMsg(errMsg);
            result = 0;
        }
        
        return result;
    }
    
    /**
     * Chekfs if the given String can be converted into an int 
     * and lies between the two given values
     * @param errMsg
     * @param number
     * @param low
     * @param high
     * @return 0 (if failed), value (if lies between the values)
     */
    public int validateIntWithinRange(String errMsg, String number, int low, int high) {
        int result = 0;
        
        try {
            result = Integer.parseInt(number);
            if (!isWithinRange(result, low, high)) 
                throw new Exception("");
        } catch (Exception ex) { 
            appendErrMsg(errMsg);
            result = 0;
        }
        
        return result;
    }
}
