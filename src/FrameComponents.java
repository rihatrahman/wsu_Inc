import javax.swing.*;
import java.awt.*;

/**
 * Utility class
 * Frame characteristics
 * @author rihat rahman
 */
public class FrameComponents extends JFrame {

    /**
     * sets frame size
     * @param frame
     */
    public static void setFrameSize (JFrame frame, JPanel panel){

        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(550, 420));
    }

    /**
     * sets frame location
     * @param frame
     */
    public static void setFrameLocation (JFrame frame) {

        frame.pack();
        frame.setLocationRelativeTo(null);
    }

    /**
     * Loads the login Screen
     * @param SignOutButton log in frame
     */
    static void signOut (JButton SignOutButton){
        SignOutButton.addActionListener(e -> NewFrame.newFrame(new logInGUI()));
    }
}
