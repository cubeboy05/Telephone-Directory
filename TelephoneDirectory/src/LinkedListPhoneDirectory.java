/*
 * Author: Karthik
 * Title: LinkedListPhoneDirectory
 * Created: 07/04/14
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

public class LinkedListPhoneDirectory implements PhoneDirectory{
    
    private LinkedList<DirectoryEntry> theDirectory = new LinkedList();
    private int index;
    
    private String sourceName = null;
    private String num;
    
    /**
     * load information from text file
     * @param sourceName used to store name of txt file
     */
    @Override
    public void loadData(String sourceName){
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
    public String lookUpEntry(String name){
        index = find(name);
        if (index >= 0){
            return theDirectory.get(index).getNumber(); 
        }
        else{
            return null;
        }
    } 
    
    /**
     * @param name used to add name of person being added
     * @param telno used to add tel number of person being added
     * @return tel number of person being edited or null if person doesn't exist
     */
    @Override
    public String addChangeEntry(String name, String telno){
        index = find(name);
        if (index >= 0){
            theDirectory.get(index).setNumber(telno); 
            return theDirectory.get(index).getNumber();
        }
        else{
            add(name, telno);
            return  null;
        }
    }
    
    /**
     * used to remove person name and telno if person exists
     * @param name name of person to be removed
     * @return tel number of person being removed or null if person doesn't exist
     */
    @Override
    public String removeEntry(String name){
        
        index = find(name);
        if(index < 0){
            return null;
        }
        num = theDirectory.get(index).getNumber();
        theDirectory.remove(index); 
        return num;
    }
    
    /**
     * save any change/addition/deletion made to the txt file
     * @throws IOException 
     */
    @Override
    public void save() throws IOException{
        ListIterator<DirectoryEntry> it = theDirectory.listIterator();
        try (PrintWriter out = new PrintWriter("link.txt")) {
            while(it.hasNext()){
                DirectoryEntry d = it.next();
                if(d != null){
                    out.println(d.getName());
                    out.println(d.getNumber());
                }
            }
        }
    }
    
    /**
     * return the information in the list in a formatted manner
     * @return String of info in list
     */
    @Override
    public String format(){
        ListIterator<DirectoryEntry> it = theDirectory.listIterator();
        String list = "";
        System.out.printf("%s\t\t\t\t%s\n", "Name", "Telno");

        while(it.hasNext()){
            DirectoryEntry d = it.next();
            if (d != null){
                list += String.format("%s\t\t\t\t%s\n", d.getName(), d.getNumber());
            }
        }
        return list;
    }
    
    /**
     * Adds a new entry with the given name and telno to the list of directory entries
     * @param name name to be added
     * @param telno tel number to be added
     */
    private void add(String name, String telno){   
        theDirectory.add(new DirectoryEntry(name,telno)); 
    }
    
    /**
     * Searches the array of directory entries for a specific name
     * @param name name of person to find
     * @return index of name in list
     */
    private int find(String name){          
        ListIterator<DirectoryEntry> it = theDirectory.listIterator();
        int i = 0;
        while(it.hasNext()){
            DirectoryEntry d = it.next();
            if(name.equals(d.getName())){
                return i;
            }
            i++;
        }
        return -1;
    }
}