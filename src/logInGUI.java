import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

/**
 * Class for login screen GUI
 * @author rihat rahman
 */
public class logInGUI extends JFrame {
    private JPanel mainPanel;
    private JPanel leftPanel;
    private JTextField usernameField;
    private JPanel rightPanel;
    private JPasswordField passwordField;
    private JButton signInButton;
    private JLabel invalidErrorMessage;

    /**
     * Constructor
     */
    public logInGUI() {

        super("WSU Inc");
        super.setIconImage(new ImageIcon("src\\logo.png").getImage());
        FrameComponents.setFrameSize(this, mainPanel);
        invalidErrorMessage.setVisible(false);
        FrameComponents.setFrameLocation(this);

        signInButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            try {
                User user = verifyCredentials(username,password);
                if (user != null) {
                    loadFrame(user);
                }
                else {
                    invalidErrorMessage.setText("Invalid Credentials!");
                    invalidErrorMessage.setVisible(true);
                }
            } catch (IOException | ParseException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });
    }

    /**
     * close all frames/windows
     */
    public void closeFrames (){
        for (Window window : Window.getWindows())
            window.dispose();
    }

    /**
     * Verify if a user enters correct ID & Password
     * @param username username entered by the user
     * @param password password entered by the user
     * @return user object if credentials are correct/null object if credentials are incorrect
     * @throws FileNotFoundException credentials file does not exist
     */
    public User verifyCredentials(String username, String password) throws FileNotFoundException {

        ArrayList<User> listOfUsers = ListOfUsers.listOfUsers();
        for (User user : listOfUsers) {
            if (user.getUsername().trim().equals(username.trim()) && user.getPassword().trim().equals(password.trim())) {
                return user;
            }
        }
        return null;
    }

    /**
     * Load home frame of the user who logged in
     * @param user user object of who's logging in
     * @throws IOException if files are missing
     */
    public void loadFrame (User user) throws IOException, ParseException {

        switch (user.getCategory().trim()) {
            case "Admin": {
                NewFrame.newFrame(new adminHomeGUI());
                break;
            }
            case "Owner":
            case "Purchaser":
            case "Accountant":
            case "Inventory Manager":
            case "Sales": {
                break;
            }

            default:
                break;
        }
    }
}