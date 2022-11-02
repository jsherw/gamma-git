import javax.swing.*;

/**
 * @author James Sherwood
 * Welcome screen
 * FIXME Currently displays a blank page.
 */
public class HomePanel {
    JPanel myPanel;

    public HomePanel(){
        myPanel = new JPanel();
    }

    JPanel getMyPanel(){
        return myPanel;
    }
}
