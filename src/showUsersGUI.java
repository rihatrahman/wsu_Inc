import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Creates GUI to show list of users
 * @author rihat rahman
 */
public class showUsersGUI extends JFrame {
    private JPanel mainPanel;
    private JTable tableOfUsers;
    private JPanel topPanel;
    private JPanel bottomPanel;
    private JPanel midPanel;
    private JButton homeScreenButton;
    private JButton signOutButton;

    /**
     * Constructor
     * @throws FileNotFoundException
     */
    public showUsersGUI() throws FileNotFoundException {

        super("Show Users");
        FrameComponents.setFrameSize(this, mainPanel);
        createTable();
        FrameComponents.setFrameLocation(this);
        homeScreenButton.addActionListener(e -> NewFrame.newFrame(new adminHomeGUI()));
        FrameComponents.signOut(signOutButton);
    }

    /**
     * Creates table with all user information
     * @throws FileNotFoundException
     */
    private void createTable() throws FileNotFoundException {

        ArrayList<User> allUserInfo = ListOfUsers.listOfUsers();
        int numberOfUsers = allUserInfo.size();
        Object[][] users = new Object[numberOfUsers][5];

        for (int i = 0; i < allUserInfo.size(); i++) {
            users[i][0] = allUserInfo.get(i).getFirstname();
            users[i][1] = allUserInfo.get(i).getLastname();
            users[i][2] = allUserInfo.get(i).getUsername();
            users[i][3] = allUserInfo.get(i).getPassword();
            users[i][4] = allUserInfo.get(i).getCategory();
        }
        tableOfUsers.setModel(new DefaultTableModel(users, new String[] {"FirstName", "Lastname", "Username", "Password", "Category"}));
    }
}