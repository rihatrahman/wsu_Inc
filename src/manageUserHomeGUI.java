import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * @author rihat rahman
 * GUI class to manage all users. This frame loads the list of all users in buttons. Each button corresponds to one user.
 */
public class manageUserHomeGUI extends JFrame implements ActionListener {
    private JPanel mainPanel;
    private JPanel buttonPanel;
    private JPanel bottomPanel;
    private JButton homeScreenButton;
    private JButton signOutButton;
    private JPanel topPanel;
    private JButton[] buttonArray;
    private ArrayList<User> allUserInfo;


    /**
     * Constructor
     * @throws FileNotFoundException
     */
    public manageUserHomeGUI() throws FileNotFoundException {

        super("WSU Inc");
        FrameComponents.setFrameSize(this, mainPanel);
        buttonPanel.setLayout(new GridLayout(4, 4));
        allUserInfo = ListOfUsers.listOfUsers();
        buttonArray = new JButton[allUserInfo.size()];

        for (int i = 0; i < allUserInfo.size(); i++) {
            buttonArray[i] = new JButton(allUserInfo.get(i).getUsername());
            buttonPanel.add(buttonArray[i]);
            buttonArray[i].addActionListener(this);

            if ((allUserInfo.get(i).getCategory().equals("Owner")) || (allUserInfo.get(i).getCategory().equals("Admin")))
                buttonArray[i].setEnabled(false);
        }

        FrameComponents.setFrameLocation(this);
        homeScreenButton.addActionListener(e -> NewFrame.newFrame(new adminHomeGUI()));
        FrameComponents.signOut(signOutButton);
    }

    /**
     * To load associated frame of the user whose button was being clicked
     * @param e
     */
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < buttonArray.length; i++) {
            if (e.getSource() == buttonArray[i])
                NewFrame.newFrame(new manageUserGUI(allUserInfo.get(i)));
        }
    }
}