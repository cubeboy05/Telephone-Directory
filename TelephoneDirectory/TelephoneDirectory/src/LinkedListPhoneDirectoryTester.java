/*
 * Author: Karthik
 * Title: LinkedListPhoneDirectoryTester
 * Created: 07/04/14
 */

import java.io.IOException;


public class LinkedListPhoneDirectoryTester {
    public static void main(String[] args) throws IOException{
        LinkedListPhoneDirectory phoneList = new LinkedListPhoneDirectory();
        
        //testing loadData();
        phoneList.loadData("link.txt"); 
        
        //testing addChangeEntry();
        phoneList.addChangeEntry("Rubbish2", "93001");
        
        //testing lookUpEntry();
        System.out.println(phoneList.lookUpEntry("Paul P")); 
        
        //testing removeEntry();
        System.out.println(phoneList.removeEntry("Rubbish211")); 
        
        //testing toString();
        System.out.println(phoneList.format()); 
        
        //testing save();
        phoneList.save(); 
    }
}
