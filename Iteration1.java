import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Iteration1 extends JFrame {
    String nameStr, emailStr;
    VersionNum num = new VersionNum();

    JButton  submit;
    JPanel homePanel, profPanel, aboutPanel;
    JLabel name, nameLab, email, emailLab, dev1, dev2, dev3, dev4, dev5, versionNum, versionNumLab,
            name1, name2, name3, name4, name5;

    JLabel group = new JLabel("Team Gamma");
    JTextField emailT;
    JTextField nameT;

    /**
     * @author James Sherwood
     * Structures the GUI frame and panels
     */
    public Iteration1() {

        nameT = new JTextField();
        emailT = new JTextField();

        nameLab = new JLabel("Name: ");
        emailLab = new JLabel("Email: ");
        submit = new JButton("Submit");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initMenu();

        //Home Panel
        homePanel = new JPanel();
        homePanel.setSize(400, 400);
        homePanel.setVisible(true);

        //Profile Panel
        profPanel = new JPanel();
        profPanel.setSize(400,400);
        profPanel.setVisible(true);
        nameT.setBounds(100,40,140,20);
        nameT.setFont(new Font("Arial", Font.BOLD, 20));
        nameT.setHorizontalAlignment(SwingConstants.LEFT);

        emailT.setBounds(100,80,140,20);
        emailT.setFont(new Font("Arial", Font.BOLD, 20));
        emailT.setHorizontalAlignment(SwingConstants.LEFT);

        nameLab.setBounds(60, 40, 60, 20);
        emailLab.setBounds(60,80,60,20);

        submit.setBounds(120, 120, 100, 20);
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nameStr = name.getText();
                emailStr = email.getText();
                UserInfo u = new UserInfo(nameStr, emailStr); //FIXME Do something with new UserInfo object
            }
        });

        profPanel.add(nameLab);
        profPanel.add(emailLab);
        profPanel.add(nameT);
        profPanel.add(emailT);
        profPanel.add(submit);

        //About panel
        aboutPanel = new JPanel();
        aboutPanel.setSize(400,400);
        aboutPanel.setVisible(true);
        dev1 = new JLabel("1. Developer: ");
        name1 = new JLabel("James Sherwood");
        versionNum = new JLabel(num.toString());
        versionNumLab = new JLabel("Version Number: ");

        group.setBounds(40, 100, 100, 20);
        dev1.setBounds(40, 120, 80, 20);
        name1.setBounds(120, 120, 140, 20);
        versionNumLab.setBounds(80, 20, 100,20);
        versionNum.setBounds(190, 20, 100, 20);
        aboutPanel.add(versionNumLab);
        aboutPanel.add(versionNum);
        aboutPanel.add(group);
        aboutPanel.add(dev1);
        aboutPanel.add(name1);
        setLayout(new BorderLayout());
    }

    /**
     * @author James Sherwood
     * initializes the menu bar for moving between pages.
     */

    private void initMenu(){
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        JMenuItem menuHome = new JMenuItem("Home");
        JMenuItem menuProf = new JMenuItem("Profile");
        JMenuItem menuAb = new JMenuItem("About");
        menuBar.add(menu);
        menu.add(menuHome);
        menu.add(menuProf);
        menu.add(menuAb);
        setJMenuBar(menuBar);

        menuHome.addActionListener(new MenuActionHome(homePanel));
        menuProf.addActionListener(new MenuActionProfile(profPanel));
        menuAb.addActionListener(new MenuActionAbout(aboutPanel));
    }

    /**
     * @author James Sherwood
     * change displayed panel
     * @param panel the new panel to be retrieved
     */
    private void changePanel(JPanel panel) {
        getContentPane().removeAll();
        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().doLayout();
        update(getGraphics());
    }

    /**
     * @author James Sherwood
     * Create input based on user menu selection (Switch to profile panel)
     */
    private class MenuActionProfile implements ActionListener {

        JPanel panel;

        private MenuActionProfile(JPanel pnl) {
            this.panel = pnl;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            changePanel(profPanel);

        }
    }

    /**
     * @author James Sherwood
     * Create input based on user menu selection (Switch to about panel)
     */
    private class MenuActionAbout implements ActionListener {

        JPanel panel;

        private MenuActionAbout(JPanel pnl) {
            this.panel = pnl;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            changePanel(aboutPanel);

        }
    }

    /**
     * @author James Sherwood
     * Create input based on user menu selection (Switch to home panel)
     */
    private class MenuActionHome implements ActionListener {

        JPanel panel;

        private MenuActionHome(JPanel pnl) {
            this.panel = pnl;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            changePanel(homePanel);

        }
    }

    /**
     * @author James Sherwood
     * Driver program
     * @param args
     */

    public static void main(String[] args) {
        Iteration1 frame = new Iteration1();
        frame.setBounds(400,400, 400,400);
        frame.setVisible(true);
    }
}
