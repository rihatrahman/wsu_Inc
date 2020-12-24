import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

/**
 * @author rihat rahman
 * GUI class to Update or Delete a user
 */
public class manageUserGUI extends JFrame {
    private JPanel mainPanel;
    private JPanel topPanel;
    private JButton manageUserHomeButton;
    private JButton homeScreenButton;
    private JButton signOutButton;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField usernameField;
    private JTextField passwordField;
    private JComboBox<String> categoryField;
    private JPanel midPanel;
    private JPanel bottomPanel;
    private JLabel emptyUserPassField;
    private JButton updateButton;
    private JButton deleteButton;

    /**
     * Constructor
     * @param user User object of the user whose information is loaded
     */
    public manageUserGUI(User user) {

        super("Manage User");
        FrameComponents.setFrameSize(this, mainPanel);
        emptyUserPassField.setVisible(false);

        firstNameField.setText(user.getFirstname());
        lastNameField.setText(user.getLastname());
        usernameField.setText(user.getUsername());
        passwordField.setText(user.getPassword());

        categoryField.addItem("Inventory Manager");
        categoryField.addItem("Sales Representative");
        categoryField.addItem("Accountant");
        categoryField.addItem("Purchaser");

        switch (user.getCategory().trim()) {
            case "Inventory Manager":
                categoryField.setSelectedIndex(0);

            case "Sales Representative:":
                categoryField.setSelectedIndex(1);

            case "Accountant":
                categoryField.setSelectedIndex(2);

            case "Purchaser":
                categoryField.setSelectedIndex(3);
        }

        FrameComponents.setFrameLocation(this);

        homeScreenButton.addActionListener(e -> NewFrame.newFrame(new adminHomeGUI()));
        FrameComponents.signOut(signOutButton);
        manageUserHomeButton.addActionListener(e -> {
            try {
                NewFrame.newFrame(new manageUserHomeGUI());
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });

        deleteButton.addActionListener(e -> {
            try {
                deleteUser(user);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            JOptionPane.showMessageDialog(mainPanel, "User Deleted Successfully!", "Error", JOptionPane.ERROR_MESSAGE);
            try {
                NewFrame.newFrame(new manageUserHomeGUI());
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });

        updateButton.addActionListener(e -> {
            String firstName = firstNameField.getText().trim();
            String lastName = lastNameField.getText().trim();
            String username = usernameField.getText().trim();
            String password = passwordField.getText().trim();
            String category = Objects.requireNonNull(categoryField.getSelectedItem()).toString().trim();
            User userNew = new User(firstName, lastName, username, password, category);

            try {
                updateUser(user, userNew);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            JOptionPane.showMessageDialog(mainPanel, "User Updated Successfully!", "Success!", JOptionPane.PLAIN_MESSAGE);
            try {
                NewFrame.newFrame(new manageUserHomeGUI());
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });
    }

    /**
     * Updates information of a user
     * @param oldUser User object containing previous information about the user
     * @param updatedUser User object containing updated information about the user
     * @throws IOException
     */
    void updateUser(User oldUser, User updatedUser) throws IOException {

        ArrayList<User> allUsers = ListOfUsers.listOfUsers();
        PrintWriter printWriter = getUserPrintwriter("src\\credentials.txt");

        for (User user : allUsers) {
            if ((user.getUsername().equals(oldUser.getUsername()))) {
                user.setFirstname(updatedUser.getFirstname());
                user.setLastname(updatedUser.getLastname());
                user.setUsername(updatedUser.getUsername());
                user.setPassword(updatedUser.getPassword());
                user.setCategory(updatedUser.getCategory());
            }
            writeUserToFile(printWriter, user);
        }
        printWriter.close();
    }

    /**
     * Deletes a user
     * @param deletedUser User object of the user to be deleted
     * @throws IOException
     */
    void deleteUser(User deletedUser) throws IOException {

        ArrayList<User> allUsers = ListOfUsers.listOfUsers();
        PrintWriter printWriter = getUserPrintwriter("src\\credentials.txt");

        for (User user : allUsers)
            if (!(deletedUser.getUsername().equals(user.getUsername())))
                writeUserToFile(printWriter, user);

        printWriter.close();
    }

    /**
     * Writes user objects to text file
     * @param printWriter
     * @param user User object of the user to be written in a file
     */
    private void writeUserToFile (PrintWriter printWriter, User user) {
        printWriter.println(user.getFirstname() + "," + user.getLastname() + "," + user.getUsername()
                + "," + user.getPassword() + "," + user.getCategory());
    }

    /**
     * @return PrintWriter object to write to credentials file
     * @throws IOException
     */
    PrintWriter getUserPrintwriter(String filename) throws IOException {
        return new PrintWriter(new FileWriter(new File(filename)));
    }
}