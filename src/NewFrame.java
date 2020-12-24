import javax.swing.*;
import java.awt.*;

/**
 * Utility Class
 * Loads a new frame
 * @author rihat rahman
 */
public class NewFrame extends JFrame {

    /**
     * @param frame The GUI class intended to load
     */
    public static void newFrame(JFrame frame){
        for (Window window : Window.getWindows())
            window.dispose();
        frame.setVisible(true);
    }
}
