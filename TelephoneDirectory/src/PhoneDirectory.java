/*
 * Author: Karthik
 * Title: PhoneDirectory
 * Created: 07/04/14
 */

import java.io.IOException;

public interface PhoneDirectory {

    /** Load file containing directory entries
     @param sourceName is name of the file containing
     the directory entries
     */
    void loadData(String sourceName);

    /** Look up an entry.
     @param name The name of person to look up
     @return The telno or null if name is not in the directory
     */
    String lookUpEntry(String name);

    /** Add new entry or change an existing entry.
     @param name The name of the person being added
     or whose telno is going to change
     @param telno The telno being changed or added
     @return The old telno or if a new entry null
     */
    String addChangeEntry(String name, String telno);

    /** Remove an entry from the directory.
     @param name The name of the person to be removed
     @return The current telno. If not in the directory, null is returned.
     */
    String removeEntry(String name);

    /** If the directory has been modified the contents
     of the directory are written back to the file
     * @throws java.io.IOException
     */
    void save() throws IOException;

    /**
     * Builds a single string representing directory contents
     * Each entry is terminated by a new line
     * @return The formatted list of directory contents
     */
    String format();
}
