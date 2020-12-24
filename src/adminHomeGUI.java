import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

/**
 * adminHomeGUI class loads the home screen for admin.
 * @author rihat rahman
 */
public class adminHomeGUI extends JFrame {
    private JButton showUsersButton;
    private JButton createUserButton;
    private JButton manageUserButton;
    private JButton signOutButton;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JPanel midPanel;
    private JPanel mainPanel;

    /**
     * Constructor
     */
    public adminHomeGUI() {
        super("WSU Inc");
        FrameComponents.setFrameSize(this, mainPanel);
        FrameComponents.setFrameLocation(this);

        showUsersButton.addActionListener(e -> {
            try {
                NewFrame.newFrame(new showUsersGUI());
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });
        manageUserButton.addActionListener(e -> {
            try {
                NewFrame.newFrame(new manageUserHomeGUI());
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });
        createUserButton.addActionListener(e -> NewFrame.newFrame(new createProfileGUI()));
        FrameComponents.signOut(signOutButton);
    }
}