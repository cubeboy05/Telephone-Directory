/*
 * Author: Karthik
 * Title: GUI
 * Created: 07/04/14
 */

import java.io.IOException;
import javax.swing.JOptionPane;

public class GUI {
    public static void main(String[] args) throws IOException{
        LinkedListPhoneDirectory phoneList = new LinkedListPhoneDirectory();
        phoneList.loadData("link.txt"); 
        String[] choices = {"AddChangeEntry", "LookUpEntry", "RemoveEntry", "SaveDirectory", "Exit"};
        String name, telno;
   
        while (true) {
            int response = JOptionPane.showOptionDialog(
                               null                       // Center in window.
                             , "Select Command"           // Message
                             , "PhoneDirectory"           // Title in titlebar
                             , JOptionPane.YES_NO_OPTION  // Option type
                             , JOptionPane.PLAIN_MESSAGE  // messageType
                             , null                       // Icon (none)
                             , choices                    // Button text as above array
                             , "label"                    // Default button's label
        );
            
        //switch statement to respond to which button was clicked.
            switch (response) {
                case 0: //AddChangeEntry
                    name = JOptionPane.showInputDialog("Enter name");
                    if(name == null){
                        break;
                    } 
                    telno = JOptionPane.showInputDialog("Enter number");
                    if(telno == null){
                        break;
                    }
                    phoneList.addChangeEntry(name, telno);
                    break;
                case 1: //LookUpEntry
                    name = JOptionPane.showInputDialog("Enter name");
                    if(name == null){
                        break;
                    }
                    telno = phoneList.lookUpEntry(name);
                    if(telno != null){
                        JOptionPane.showMessageDialog(null, "The number for " + name + " is " + telno); 
                    }
                    else{
                        JOptionPane.showMessageDialog(null, name + " not in directory"); 
                    }
                    break;
                case 2: //RemoveEntry
                    name = JOptionPane.showInputDialog("Enter name");
                    if(name == null){
                        break;
                    }
                    telno = phoneList.removeEntry(name); 
                    if(telno != null){
                        JOptionPane.showMessageDialog(null, name + " removed from directory"); 
                    }
                    else{
                        JOptionPane.showMessageDialog(null, name + " not in directory"); 
                    }
                    break;
                case 3: //SaveDirectory
                    phoneList.save();
                    JOptionPane.showMessageDialog(null, "Directory Saved");
                    break;
                case 4:
                case -1: //Exit
                    //Both the quit button (4) and the close box(-1) handled here.
                    System.exit(0);     // exit program
                default:
                    //error handling 
                    JOptionPane.showMessageDialog(null, "Unexpected response " + response);
            }
        }
    }
}