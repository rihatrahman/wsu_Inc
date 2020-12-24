import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * To test manageUserGUI
 * @author rihat rahman
 */
class manageUserGUITest {

    @Test
    void updateUser() throws IOException {


        User oldUserData = new User("David","Smith",
                "david49","smith3492","Admin");
        //changed first name
        User newUserData = new User("John","Smith",
                "david49","smith3492","Admin");

        manageUserGUI test = new manageUserGUI(oldUserData);
        test.updateUser(oldUserData, newUserData);
        ArrayList<User> userList = ListOfUsers.listOfUsers();

        for (User user : userList) {
            if (user.getUsername().trim().equals("david49")) {
                if (!(user.getFirstname().trim().equals("John"))) {
                    fail();
                }
            }
        }




    }

    @Test
    void deleteUser() throws IOException {

        User user = new User("David","Smith",
                "david49","smith3492","Admin");
        manageUserGUI test = new manageUserGUI(user);
        test.deleteUser(user);
        ArrayList<User> userList = ListOfUsers.listOfUsers();

        for (User users : userList) {
            if (users.getUsername().trim().equals("david49")) {
                fail();
            }
        }


    }

    @Test
    void getUserPrintwriter() throws IOException {

        manageUserGUI test = new manageUserGUI(new User());
        File file = new File("src\\credentials.txt");
        FileWriter fileWriter = new FileWriter(file);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        assertEquals(printWriter, test.getUserPrintwriter("src\\credentials.txt"));

    }
}
