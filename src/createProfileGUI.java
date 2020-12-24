import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

/**
 * GUI class to load Create Profile frame
 * @author rihat rahman
 */
public class createProfileGUI extends JFrame {
    private JPanel topPanel;
    private JPanel bottomPanel;
    private JPanel midPanel;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField usernameField;
    private JTextField passwordField;
    private JButton createProfileButton;
    private JButton homeScreenButton;
    private JButton signOutButton;
    private JComboBox<String> categoryField;
    private JPanel mainPanel;
    private JLabel emptyUserPassField;

    /**
     * Constructor
     */
    public createProfileGUI() {

        super("WSU Inc");
        super.setIconImage(new ImageIcon("src\\logo.png").getImage());
        FrameComponents.setFrameSize(this, mainPanel);
        emptyUserPassField.setVisible(false);
        categoryField.addItem("Inventory Manager");
        categoryField.addItem("sales rep");
        categoryField.addItem("Accountant");
        categoryField.addItem("Purchaser");
        FrameComponents.setFrameLocation(this);

        createProfileButton.addActionListener(e -> {

            String firstName = firstNameField.getText().trim();
            String lastName = lastNameField.getText().trim();
            String username = usernameField.getText().trim();
            String password = passwordField.getText().trim();
            String category = Objects.requireNonNull(categoryField.getSelectedItem()).toString().trim();

            if (username.equals("") || (password.equals(""))){
                emptyUserPassField.setVisible(true);
                return;
            }

            try {
                Boolean usernameExists = checkDuplicateUsername(username);
                if (usernameExists) {
                    JOptionPane.showMessageDialog(mainPanel, "Username Already Exists!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }

            try {
                insertUser(firstName, lastName, username, password, category);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

            NewFrame.newFrame(new createProfileGUI());
            JOptionPane.showMessageDialog(mainPanel, "User Added Successfully!", "Error", JOptionPane.PLAIN_MESSAGE);
        });

        homeScreenButton.addActionListener(e -> NewFrame.newFrame(new adminHomeGUI()));
        FrameComponents.signOut(signOutButton);
    }

    /**
     * @param username The username to be checked
     * @return true if a duplicate username exists
     * @throws FileNotFoundException
     */
   public Boolean checkDuplicateUsername(String username) throws FileNotFoundException {

        ArrayList<User> listOfUsers = ListOfUsers.listOfUsers();
        for (User listOfUser : listOfUsers) {
            if (listOfUser.getUsername().equals(username))
                return true;
        }
        return false;
    }

    /**
     * To insert a user to the credentials text file
     * @param firstName
     * @param lastName
     * @param username
     * @param password
     * @param category
     * @throws IOException
     */
    private void insertUser(String firstName, String lastName, String username, String password, String category) throws IOException {

        ArrayList<User> allUsersInfo = ListOfUsers.listOfUsers();
        User newUserInfo = new User(firstName, lastName, username, password, category);

        FileWriter fileWriter = new FileWriter(new File("src\\credentials.txt"));
        PrintWriter printWriter = new PrintWriter(fileWriter);

        for (User user : allUsersInfo)
            printWriter.println(user.getFirstname() + " ," + user.getLastname() + "," + user.getUsername() + ","
                    + user.getPassword() + "," + user.getCategory());

        printWriter.print(newUserInfo.getFirstname() + "," + newUserInfo.getLastname() + "," + newUserInfo.getUsername()
                + "," + newUserInfo.getPassword() + "," + newUserInfo.getCategory());

        printWriter.close();
    }
}