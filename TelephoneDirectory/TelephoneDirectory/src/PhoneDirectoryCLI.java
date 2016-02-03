/*
 * Author: Karthik
 * Title: PhoneDirectoryCLI
 * Created: 07/04/14
 */

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class PhoneDirectoryCLI implements PhoneDirectoryUI{

    PhoneDirectory theDirectory;
    Scanner in;
    int choice;
    String name;
    String telno;
    boolean loop;

    // Provides a basic console style interface
    public PhoneDirectoryCLI(PhoneDirectory theDirectory){
        this.theDirectory = theDirectory;
        processCommands();
    }
    
    /**
     * process the various commands depending on the user's choice. 
     */
    @Override
    public void processCommands(){
        while (choice != 5){
            optionList();
            
            loop = true;
            while (loop) {
                try{
                    loop = false;
                    System.out.print("\nEnter Choice :> ");
                    in = new Scanner(System.in);
                    choice = in.nextInt();
                    if (in.hasNextLine()){ //consume \n lingering in buffer
                        in.nextLine();
                    }
                }
                catch(InputMismatchException e){
                    System.out.println("Invalid entry. Enter between 1 - 5.");
                    loop = true;
                }
            }

            if(choice < 0 || choice > 5){
                System.out.println("Invalid entry. Enter between 1 - 5.");
            }

            if (choice == 0){
                try {
                    doAddChangeEntry();
                }
                catch (IOException e){
                    System.out.println("IO error when exe addChangeEntry method");
                }
            }

            else if (choice == 1){
                try {
                    doLookUpEntry();
                }
                catch (IOException e){
                    System.out.println("IO error when exe doLookUpEntry method");
                }
            }

            else if (choice == 2){
                try {
                    doRemoveEntry();
                }
                catch (IOException e){
                    System.out.println("IO error when exe doRemoveEntry method");
                }
            }

            else if (choice == 3){
                doListDirectory();
            }

            else if (choice == 4){
                try{
                    doSave();
                }
                catch (IOException e){
                    System.out.println("IO error when exe doSave method");
                }
            }

            else if (choice == 5){
                doExit();
            }
        }
    }
    
    /**
     * let user input name and telno to the directory. 
     * Either add new data or edit existing one.
     * @throws IOException 
     */
    private void doAddChangeEntry()throws IOException{
        System.out.print("\nEnter name :> ");        
        name = in.nextLine(); 
        System.out.print("\nEnter number :> ");
        telno = in.next();
        theDirectory.addChangeEntry(name, telno);

        if (theDirectory.addChangeEntry(name, telno) != null){
            System.out.println(name + "'s new number is " + telno);
        }
        else {
            System.out.println("New entry into data:\n name = " + name + "\tnumber = " + telno);
        }

        System.out.println("Add/Change executed.");
    }
    
    /**
     * look up a name and return the name's tel number or return invalid name response
     * @throws IOException 
     */
    private void doLookUpEntry()throws IOException{
        System.out.print("\nEnter name :> ");
        name = in.nextLine();
        System.out.println(name);
        if (theDirectory.lookUpEntry(name) != null){
            System.out.println("The number for " + name + " is " + theDirectory.lookUpEntry(name));
        }
        else{
            System.out.println("No such name is found in the list.");
        }
    }

    /**
     * remove name from the directory if the name exists
     * @throws IOException 
     */
    private void doRemoveEntry()throws IOException{
        System.out.print("\nEnter name :> ");
        name = in.nextLine();
        System.out.println(name);
        if (theDirectory.lookUpEntry(name) != null){
            System.out.println(theDirectory.removeEntry(name));  
        }
        else{
            System.out.println("No such name is found in the list.");
        }
    }
    
    /**
     * print out the list in the directory in a formatted manner. 
     */
    private void doListDirectory(){
        System.out.println(theDirectory.format());
    }
    
    /**
     * save information to file
     * @throws IOException 
     */
    private void doSave()throws IOException{
        theDirectory.save();
        System.out.println("Entry Saved.");
    }
    
    /**
     * print end of program when use chooses to exit
     */
    private void doExit(){
        System.out.println("End of program");
    }
    
    /**
     * used to print out options list for user to choose from
     */
    private void optionList(){
        System.out.println("\nLoading Data...");
        System.out.println("Select 0: Add or Change Entry");
        System.out.println("Select 1: Look Up Entry");
        System.out.println("Select 2: Remove Entry");
        System.out.println("Select 3: List Directory");
        System.out.println("Select 4: Save Directory");
        System.out.println("Select 5: Exit");
    }
}