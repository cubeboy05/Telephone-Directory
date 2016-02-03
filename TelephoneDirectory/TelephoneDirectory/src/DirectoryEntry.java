/*
 * Author: Karthik
 * Title: DirectoryEntry
 * Created: 07/04/14
 */

public class DirectoryEntry {

    //instances to hold value for name and telephone number
    private String name;
    private String telno;

    /**
     *
     * @param name used to assign value for name
     * @param telno used to assign value for telephone number
     */
    public DirectoryEntry(String name, String telno){
        this.name = name;
        setNumber(telno);
    }
    
    /**
     * Constructor initialising name and telno to null
     */
    public  DirectoryEntry(){
        name = null;
        telno = null;
    }

    /**
     * used to get the value of the private instance variable name
     * @return name
     */
    public String getName(){
        return name;
    }

    /**
     * used to return the value of the private instance variable telno
     * @return telno
     */
    public String getNumber(){
        return telno;
    }

    /**
     * used to modify the value of telno
     * @param telno used to set the value of telno
     */
    public void setNumber(String telno){
        this.telno = telno;
    }

    /**
     * prints out in the format of class name followed by the values of name and telno
     * @return a string print out
     */
    @Override
    public String toString(){
        return getClass().getName() + "[name = " + getName() + " telno = " + getNumber() + "]";
    }

    /**
     * prints out the name and telno information in a formatted manner with a tab space between the 2 values
     * @return a formatted string print out
     */
    public String format(){
        return String.format("%s\t%s", getName(), getNumber());
    }
}
