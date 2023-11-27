// UserDataStore.java
package application;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class UserDataStore {
    private static final String USER_DATA_FILE = "userdata.txt";

    public static void storeUser(User user) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(USER_DATA_FILE, true))) {
            // Append user data to the file
            writer.println(user.getUsername() + "," + user.getPassword());
        } catch (IOException e) {
            e.printStackTrace();
            
        }
    }
}
