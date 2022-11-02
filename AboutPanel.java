import javax.swing.*;

/**
 * @author James Sherwood
 * Construction class for "about" panel
 */
public class AboutPanel {
    VersionNum myVersionNum = new VersionNum();
    JPanel myPanel;
    JLabel myVersionLabel, myGroupLabel, myDevLabel, devName1;

    public AboutPanel(){
        myPanel = new JPanel();

        myVersionLabel = new JLabel("Version Number: " + myVersionNum.getVersionNum());
        myVersionLabel.setBounds(180, 120, 200,20);

        myGroupLabel = new JLabel(myVersionNum.getGroup());
        myGroupLabel.setBounds(40, 160, 100, 20);

        myDevLabel = new JLabel("Developers: ");
        myDevLabel.setBounds(40, 200, 140, 20);
        devName1 = new JLabel("1. James Sherwood");
        devName1.setBounds(120, 220, 140, 20);
        myPanel.add(myVersionLabel);
        myPanel.add(myGroupLabel);
        myPanel.add(myDevLabel);
        myPanel.add(devName1);

    }

    JPanel getMyPanel(){
        return myPanel;
    }
}
