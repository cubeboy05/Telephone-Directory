/*
 * Author: Karthik
 * Title: DirectoryEntryTester
 * Created: 07/04/14
 */

public class DirectoryEntryTester {
    public static void main(String[] args){
        DirectoryEntry entry = new DirectoryEntry("Karthik", "123456");

        System.out.println("\nTesting toString method. Also testing if constructor initialises the values correctly.");
        System.out.println("Expected output: DirectoryEntry[name = Karthik telno = 123456]");
        System.out.println("Actual output: " + entry.toString());

        System.out.println("\nTesting format method. Also testing if constructor initialises the values correctly.");
        System.out.println("Expected output : Karthik 123456");
        System.out.println("Actual output: " + entry.format());

        System.out.println("\nTesting getName method");
        System.out.println("Expected output: Karthik");
        System.out.println("Actual output: " + entry.getName());

        System.out.println("\nTesting getNumber method");
        System.out.println("Expected output: 123456");
        System.out.println("Actual output: " + entry.getNumber());

        System.out.println("\nTesting setNumber method");
        System.out.println("Expected output: 987654");
        entry.setNumber("987654");
        System.out.println("Actual output: " + entry.getNumber());
    }
}
