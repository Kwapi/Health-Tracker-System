package Model;

import java.util.Calendar;

/**
 * Custom Date class
 * Includes:
 * day
 * month
 * year
 * hours
 * minutes
 * @author vtv13qau
 */
public class HKFDate implements Comparable<HKFDate> {

    private int day;
    private int month;
    private int year;
    private int hours;
    private int minutes;
    
    /**
     * Default constructor - sets the date to be today's date at 23:59
     */
    public HKFDate() {
        Calendar currentDate = Calendar.getInstance();

        this.day = currentDate.get(Calendar.DATE);
        this.month = currentDate.get(Calendar.MONTH) + 1; 
        //conversion from 0-based months to 1-based months
        this.year = currentDate.get(Calendar.YEAR); 
        this.hours = 23;
        this.minutes = 59;
    }
    
    /**
     * Constructor that sets the date to be the passed date at 23:59
     * @param day
     * @param month
     * @param year 
     */
    public HKFDate(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.hours = 23;
        this.minutes = 59;
    }
    
    /**
     * Constructor that sets the date to be the passed date at the passed time
     * @param day
     * @param month
     * @param year
     * @param hours
     * @param minutes 
     */
    public HKFDate(int day, int month, int year, int hours, int minutes) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.hours = hours;
        this.minutes = minutes;
    }
    
    /**
     * Constructor that sets the date to be the one in the String representation
     * of the date at 23:59
     * @param dateStr 
     */
    public HKFDate(String dateStr) {
        splitStringDate(dateStr);
        this.hours = 23;
        this.minutes = 59;
    }

    /**
     * Constructor that sets the date to be the one in the String representation
     * of the date at the time found in the String representation of time
     * @param dateStr
     * @param timeStr 
     */
    public HKFDate(String dateStr, String timeStr) {
        splitStringDate(dateStr);
        splitStringTime(timeStr);
    }

    /**
     * Adds the number of days to this date
     * @param day 
     */
    public void addDay(int day) {

        //assuming user can't skip more than one month
        if (day + this.day > monthSize()) {
            this.day = day + this.day - monthSize();
            setMonth(this.month + 1);
        } else {
            setDay(day + this.day);
        }
    }
   
    /**
     * Adds hours to this date
     * @param hours 
     */
    public void addHours(int hours) {
        int newhours = this.hours + hours;

        this.hours = newhours % 24;
        addDay(newhours / 24);
    }

    /**
     * Adds minutes to this date
     * @param minutes 
     */
    public void addMinutes(int minutes) {

        int newminutes = this.minutes + minutes;

        this.minutes = newminutes % 60;
        addHours(newminutes / 60);

    }
   
    /**
     * Returns this month's size
     * @return 
     */
    public int monthSize() {
        if ((month == 1 || month == 3 || month == 5 || month == 7
                || month == 8 || month == 10 || month == 12)) {
            return 31;
        } else if ((month == 4 || month == 6
                || month == 9 || month == 11)) {
            return 30;
        } else {
            if (((this.year % 4 == 0 && this.year % 100 != 0)
                    || (this.year % 4 == 0 && this.year % 100 == 0
                    && this.year % 400 == 0))) {
                return 29;
            } else {
                return 28;
            }
        }
    }
    /**
     * Splits the date "YYYY-MM-DD" String and sets it in this object
     * @param dateString 
     */
    public void splitStringDate(String dateString) {
        String[] parts = dateString.split("-");

        setYear(Integer.parseInt(parts[0]));
        System.out.println(parts[1]);
        setMonth(Integer.parseInt(parts[1]));
        setDay(Integer.parseInt(parts[2]));
    }
    
    /**
     * Splits the time "HH:MM" String and sets it in this object
     * @param timeString 
     */
    public void splitStringTime(String timeString) {
        String[] parts = timeString.split(":");

        setHours(Integer.parseInt(parts[0]));
        setMinutes(Integer.parseInt(parts[1]));

    }

    @Override
    /**
     * Retursn a String representation of this date without the time
     * "YYYY-MM-DD
     */
    public String toString() {
        //return year + "-" + (month) + "-" + day;
        return String.format("%4d-%02d-%02d", year, month, day);
    }
    /**
     * Returns a String representation of this date's time
     * "HH:MM"
     * @return 
     */
    public String timeToString() {
        return String.format("%02d:%02d", hours, minutes);
    }
      /**
     * Returns a String representation of the date that is used by Google Charts
     * "YYYY, MM, DD, HH, MM"
     * @return String
     */
    public String forGraphWithTime() {
        return this.getYear() + ", " + this.getMonthForGraph() + ", " + this.getDay() + ", " + this.getHours() + ", " + this.getMinutes();
    }
    /**
     * Returns a String representation of the date (without time) that is used by Google Charts
     * "YYYY, MM, DD"
     * @return String
     */
    public String forGraphWithoutTime() {
        return this.getYear() + ", " + this.getMonthForGraph() + ", " + this.getDay();
    }

    @Override
    /**
     * Returns true if the two dates are exactly the same
     * (day, month, year, time)
     */
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final HKFDate other = (HKFDate) obj;

        return this.day == other.day
                && this.month == other.month
                && this.year == other.year
                && this.hours == other.hours
                && this.minutes == other.minutes;
    }

    @Override
     /**
     * Compares this date with the other date including the time
     * @param t
     * @return 0 - the same, 1 - this is newer, -1 - other is newer
     */
    public int compareTo(HKFDate t) {
        if (t.year > year) {
            return -1;
        } else if (t.year < year) {
            return 1;
        } else {
            //same year
            if (t.month > month) {
                return -1;
            } else if (t.month < month) {
                return 1;
            } else {
                //same month
                if (t.day > day) {
                    return -1;
                } else if (t.day < day) {
                    return 1;
                } else {
                    //same day
                    if (t.hours > hours) {
                        return -1;
                    } else if (t.hours < hours) {
                        return 1;
                    } else {
                        //Same hour
                        if (t.minutes > minutes) {
                            return -1;
                        } else if (t.minutes < minutes) {
                            return 1;
                        } else {
                            //Same time
                            return 0;
                        }
                    }
                }
            }
        }
    }
    /**
     * Compares this date with the other date without taking into 
     * account the time
     * @param t
     * @return 0 - the same, 1 - this is newer, -1 - other is newer
     */
    public int compareToWithoutTime(HKFDate t) {
        if (t.year > year) {
            return -1;
        } else if (t.year < year) {
            return 1;
        } else {
            //same year
            if (t.month > month) {
                return -1;
            } else if (t.month < month) {
                return 1;
            } else {
                //same month
                if (t.day > day) {
                    return -1;
                } else if (t.day < day) {
                    return 1;
                } else {
                    //Same time
                    return 0;
                }

            }
        }
    }


/**
 * Method to calculate the difference between two dates
 *
 * @param date Second date
 * @return difference between dates in days
 */
public int diffDay(HKFDate date) {
        int monthSize = date.monthSize();

        int diffYear = (date.year - year) * (12 * monthSize);
        int diffMonth = (date.month - month) * (monthSize);
        int diffDay = date.day - day;

        return diffYear + diffMonth + diffDay;
    }

    /**
     * Method to calculate the difference between two dates
     *
     * @param date Second date
     * @return difference between dates in minutes
     */
    public int diffMin(HKFDate date) {
        int diffHour = date.hours - hours * 60;
        int diffMin = date.minutes - minutes;

        return (diffDay(date) * 24 * 60) + diffHour + diffMin;
    }

    /**
     * Method to get the end date
     *
     * @param minutes minutes to be added to this date
     * @return End Date
     */
    public HKFDate getEndDate(int minutes) {
        HKFDate endDate = new HKFDate(day, month, year, hours, this.minutes);

        endDate.addMinutes(minutes);

        return endDate;
    }
    
    
    //GETTER AND SETTER METHODS
    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getMonthForGraph() {
        return month - 1;
    }

    public int getYear() {
        return year;
    }

    public int getHours() {
        return hours;
    }

    public int getMinutes() {
        return minutes;
    }
    
     
    public void setMinutes(int minutes) {
        this.minutes = minutes;

    }
    
     public void setDay(int day) {
        this.day = day;
    }
 
    public void setMonth(int month) {
        if (month > 12) {
            this.month = 1;
            setYear(this.year + (month / 12));
        } else {
            this.month = month;
        }
    }

    public void setYear(int year) {
        this.year = year;
    }
    
    
    public void setHours(int hours) {
        this.hours = hours;
    }
    

}
