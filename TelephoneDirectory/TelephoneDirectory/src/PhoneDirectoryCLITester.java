/*
 * Author: Karthik
 * Title: PhoneDirectoryCLITester
 * Created: 07/04/14
 */

public class PhoneDirectoryCLITester {
    public static void main(String[] args){
        ArrayPhoneDirectory phoneArray = new ArrayPhoneDirectory();
        phoneArray.loadData("data.txt"); 
        PhoneDirectoryCLI cli = new PhoneDirectoryCLI(phoneArray); 
    }
}
