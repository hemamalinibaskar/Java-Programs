package libraryManagement;

import java.util.Map;
import java.util.HashMap;

public class UserManager {
    private Map<String, String> userCredentials; // Map to store username -> password pairs

    // Constructor to initialize an empty map for user credentials
    public UserManager() {
        userCredentials = new HashMap<>();
    }

    // Method to register a new user with provided username and password
    public void registerUser(String username, String password) {
        userCredentials.put(username, password); // Add username and password to the map
    }

    // Method to authenticate the username and password provided by user
    public boolean login(String username, String password) {
        // Check if the username exists in the map and if the corresponding password matches
        if (userCredentials.containsKey(username) && userCredentials.get(username).equals(password)) {
            return true; // Return true if authentication is correct
        }
        return false; // Return false if authentication fails
    }
}
