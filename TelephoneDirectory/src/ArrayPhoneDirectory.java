/*
 * Author: Karthik
 * Title: ArrayPhoneDirectory
 * Created: 07/04/14
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ArrayPhoneDirectory implements PhoneDirectory{

    private static final int INIT_CAPACITY = 100;
    private int capacity = INIT_CAPACITY;

    // holds telno of directory entries
    private int size = 0;
    // Array to contain directory entries
    private DirectoryEntry[] theDirectory = new DirectoryEntry[capacity];
    // Holds name of data file
    private String sourceName = null;
    // Flag to indicate whether directory was modified since it was last loaded or saved
    private boolean modified = false;
    //
    private int index;
    private String num;
    
    /**
     * load information from text file
     * @param sourceName used to store name of txt file
     */
    @Override
    public void loadData(String sourceName) {
        try {
            Scanner in = new Scanner(new File(sourceName));
            while (in.hasNextLine()){
                String name = in.nextLine();
                String telno = in.nextLine();
                add(name, telno);
            }
            in.close();
        }catch (FileNotFoundException e){
            System.out.println("File not found.");
        }
    }
    
    /**
     * used to lookup the tel number for a name if the name exists
     * @param name
     * @return tel number if name exists else return null
     */
    @Override
    public String lookUpEntry(String name) {
        index = find(name);
        if (index >= 0){
            return theDirectory[index].getNumber();
        }
        else{
            return null;
        }
    }
    
    /**
     * 
     * @param name used to add name of person being added
     * @param telno used to add tel number of person being added
     * @return tel number of person being edited or null if person doesn't exist
     */
    @Override
    public String addChangeEntry(String name, String telno) {
        index = find(name);
        if (index >= 0){
            theDirectory[index].setNumber(telno);
            return theDirectory[index].getNumber(); 
        }
        else{
            add(name, telno);
            return  null;
        }
    }
    
    /**
     * used to remove person name and telno if person exists
     * @param name name of person to be removed
     * @return tel number of person being removed or nul if person doesn't exist
     */
    @Override
    public String removeEntry(String name) {
        index = find(name);

        if(index < 0)
            return null;
        num = theDirectory[index].getNumber();

        if (index == theDirectory.length-1){
            theDirectory[index] = null;
            return num;
        }
        else{
            for (int i = index; i < theDirectory.length-1; i++){
                theDirectory[i] = theDirectory[i+1];
            }
            return num;
        }
    }
    
    /**
     * save any change/addition/deletion made to the txt file
     * @throws IOException 
     */
    @Override
    public void save() throws IOException{
            PrintWriter out = new PrintWriter("data.txt");
            for (DirectoryEntry e : theDirectory){
                if(e != null){
                    out.println(e.getName());
                    out.println(e.getNumber());
                }
            }
            out.close();        
    }
    
    /**
     * return the information in the array in a formatted manner
     * @return String of info in array
     */
    @Override
    public String format() {
        String list = "";
        System.out.printf("%s\t\t\t\t%s\n", "Name", "Telno");

        for (DirectoryEntry d : theDirectory){
            if (d != null)
                list += String.format("%s\t\t\t\t%s\n", d.getName(), d.getNumber());
        }
        return list;
    }

    // add private methods
    /**
     * Searches the array of directory entries for a specific name
     * @param name name of person to find
     * @return index of name in array
     */
    private int find(String name){
        for (int x=0; x < theDirectory.length; x++){
            if (theDirectory[x] != null)
                if (theDirectory[x].getName().equals(name)){
                    return x;
            }
        }
        return -1;
    }

    /**
     * Adds a new entry with the given name and telno to the array of directory entries
     * @param name name to be added
     * @param telno tel number to be added
     */
    private void add(String name, String telno){
        if (size >= capacity){
            reallocate();
        }
        theDirectory[size] = new DirectoryEntry(name, telno);
        size++;
    }

    /**
     * Creates a new array of directory entries with twice the capacity of the previous one
     */
    private void reallocate(){
        capacity *= 2;
        DirectoryEntry[] newDirectory = new DirectoryEntry[capacity];
        for (int x=0; x < theDirectory.length; x++) {
             newDirectory[x] = theDirectory[x];
        }
        theDirectory = newDirectory;
    }
}