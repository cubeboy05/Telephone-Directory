/*
 * Author: Karthik
 * Title: ArrayPhoneDirectoryTester
 * Created: 07/04/14
 */

import java.io.IOException;
import java.util.Random;

public class ArrayPhoneDirectoryTester {
    public static void main(String[] args){
        ArrayPhoneDirectory phoneArray = new ArrayPhoneDirectory();

        //testing loadData()
        phoneArray.loadData("data.txt");

        //testing reallocate()
        //filling up the data.txt with more than 100 elements
        Random rand = new Random();
        for(int i=0; i < 500; i++){
            String tempTelNo = Integer.toString(rand.nextInt(150000)+60000);
            phoneArray.addChangeEntry("Name"+tempTelNo, tempTelNo);
        }
        
        //testing addChangeEntry()
        System.out.println(phoneArray.addChangeEntry("Samuel Lee", "7775345"));
        
        //testing lookUpEntry()
        System.out.println(phoneArray.lookUpEntry("Alan Turing")); 
        
        //testing removeEntry()
        System.out.println(phoneArray.removeEntry("B"));
        
        //testing format()
        System.out.println(phoneArray.format());
        
        //testing save()
        try{
            phoneArray.save();
        }
        catch (IOException e){
            System.out.println("Save Error");
        }
    }
}