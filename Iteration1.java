import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Iteration1 implements ActionListener {
    JButton backBtn, profileBtn, aboutBtn;
    JPanel cardPanel;
    JLabel titleLabel, descLabel;
    String[] cardNames = new String[3];
    String[] cardDescription = new String[3];
    int cardCounter = 0;
/**
 * @author James Sherwood
 * Panel definitions for our GUI
 */
    public JPanel createHomePane(){

        JPanel homeGUI = new JPanel(); //Parent Panel

        JPanel btnPanel = new JPanel(); //Panel to hold and format buttons
        btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.LINE_AXIS));
        btnPanel.add(Box.createRigidArea(new Dimension(10,0)));

        backBtn = new JButton("Back");
        backBtn.addActionListener(this);
        btnPanel.add(backBtn);
        btnPanel.add(Box.createHorizontalGlue());

        profileBtn = new JButton("Profile");
        profileBtn.addActionListener(this);
        btnPanel.add(profileBtn);
        btnPanel.add(Box.createHorizontalGlue());

        aboutBtn = new JButton("About");
        aboutBtn.addActionListener(this);
        btnPanel.add(aboutBtn);
        btnPanel.add(Box.createRigidArea(new Dimension(10,0)));

        //FIXME IMPLEMENT PANELS FOR ABOUT AND PROFILE
        //Home Page Panel
        JPanel home = new JPanel();
        home.setLayout(new BoxLayout(home, BoxLayout.LINE_AXIS));
        home.add(Box.createRigidArea(new Dimension(5,5)));

        //Profile Page Panel
        JPanel profile = new JPanel();
        profile.setLayout(new BoxLayout(profile, BoxLayout.LINE_AXIS));
        profile.add(Box.createRigidArea(new Dimension(5,5))); //TEMP VALUES
        //FIXME SHOULD HAVE TEXT FIELDS FOR ENTERING USERNAME AND EMAIL
        profile.add(btnPanel);

        JPanel about = new JPanel();
        about.setLayout(new BoxLayout(about, BoxLayout.LINE_AXIS));
        //FIXME SHOULD DISPLAY VERSION NUMBER AND DEV NAMES

        //Array of cards to be displayed
        cardPanel = new JPanel(new CardLayout(200,200));

        cardNames[0] = "Home Screen";
        cardNames[1] = "Profile Page";
        cardNames[2] = "About Page";

        //Descriptor of cards to be displayed
        cardDescription[0] = "Home screen with navigation buttons.";
        cardDescription[1] = "Profile page to input user data.";
        cardDescription[2] = "About page with version number and dev names";

        cardPanel.add(home, cardNames[0]);
        cardPanel.add(profile, cardNames[1]);
        cardPanel.add(about, cardNames[2]);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout((new BorderLayout()));

        bottomPanel.add(btnPanel, BorderLayout.PAGE_START);
        bottomPanel.add(cardPanel, BorderLayout.CENTER);

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.PAGE_AXIS));

        titleLabel = new JLabel(cardNames[0]);
        descLabel = new JLabel((cardDescription[0]));

        titlePanel.add(titleLabel);
        titlePanel.add(descLabel);

        bottomPanel.add(titlePanel, BorderLayout.PAGE_END);

        homeGUI.add(bottomPanel);
        homeGUI.setOpaque(true);
        return homeGUI;
    }

    /**
     * @author James Sherwood
     * Actions performed when GUI buttons are interacted with
     * @param e the event to be processed
     */
    public void actionPerformed(ActionEvent e){
        CardLayout c1 = (CardLayout) (cardPanel.getLayout());

        if(e.getSource() == profileBtn){
            c1.first(cardPanel);
            cardCounter = 1;
        } else if(e.getSource() == aboutBtn){
            c1.last(cardPanel);
            cardCounter = 2;
        } else if(e.getSource() == backBtn){
            if(cardCounter == 2){
                c1.previous(cardPanel);
                c1.previous(cardPanel);
                cardCounter = 0;
            } else{
                c1.previous(cardPanel);
                cardCounter = 0;
            }
        }
        titleLabel.setText((cardNames[cardCounter]));
        descLabel.setText(cardDescription[cardCounter]);
    }

    /**
     * @author James Sherwood
     * GUI constructor
     */
    private static void createAndDispGUI(){
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("~~~GUI Prototype~~");

        Iteration1 demo = new Iteration1();
        frame.setContentPane(demo.createHomePane());

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * @author James Sherwood
     * Driver program
     * @param args default
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndDispGUI();
            }
        });
    }
}
