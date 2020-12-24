import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Utility class
 * To List all the users
 * @author rihat rahman
 */
public class ListOfUsers {

    public static ArrayList<User> listOfUsers () throws FileNotFoundException {

        ArrayList<User> allUsersInfo = new ArrayList<>();
        Scanner scanner = new Scanner(new File("src\\credentials.txt"));
        scanner.useDelimiter("[,\n]");

        while (scanner.hasNext()) {
            String firstname = scanner.next();
            String lastname = scanner.next();
            String username = scanner.next();
            String password = scanner.next();
            String category = scanner.next();
            allUsersInfo.add(new User(firstname, lastname, username, password, category));
        }
        scanner.close();
        return allUsersInfo;
    }

}
