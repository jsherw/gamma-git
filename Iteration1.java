import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Iteration1 extends JFrame {

    JFrame myFrame;
    JPanel homePanel, profPanel, aboutPanel, buttonPanel, myCurrPanel;
    JButton myHomeBtn,myProfBtn, myAboutBtn;

    /**
     * @author James Sherwood
     * Structures the GUI frame and panels
     */
    public Iteration1() {
        //initialize frame
        myFrame = new JFrame();
        myFrame.setSize(500,500);
        myFrame.setLayout(new BorderLayout());
        myFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        //initialize panels
        homePanel = new HomePanel().getMyPanel();
        homePanel.setLayout(null);
        profPanel = new ProfilePanel().getMyPanel();
        profPanel.setLayout(null);
        aboutPanel = new AboutPanel().getMyPanel();
        aboutPanel.setLayout(null);

        //initialize buttons
        myHomeBtn = new JButton("Home");
        myHomeBtn.setBounds(50, 350, 100, 20);
        myHomeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changePanel(e);
            }
        });
        myProfBtn = new JButton("Profile");
        myProfBtn.setBounds(170, 350, 100, 20);
        myProfBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changePanel(e);
            }
        });
        myAboutBtn = new JButton("About");
        myAboutBtn.setBounds(290, 350, 100, 20);
        myAboutBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changePanel(e);
            }
        });

        //initialize button panel
        buttonPanel = new JPanel();
        buttonPanel.add(myHomeBtn);
        buttonPanel.add(myProfBtn);
        buttonPanel.add(myAboutBtn);

        //Panel currently being displayed.
        myCurrPanel = homePanel;

        myFrame.getContentPane().add(myCurrPanel, BorderLayout.CENTER);
        myFrame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        myFrame.setVisible(true);

    }

    /**
     * @author James Sherwood
     * Change display to user's selected panel.
     * @param e input from button press.
     */
      private void changePanel(ActionEvent e) {
        if(e.getSource() == myHomeBtn){
            myFrame.remove(myCurrPanel);
            myFrame.remove(buttonPanel);
            myCurrPanel = homePanel;
            myFrame.add(myCurrPanel, BorderLayout.CENTER);
            myFrame.add(buttonPanel, BorderLayout.SOUTH);
            myFrame.revalidate();
            myFrame.repaint();

        } else if(e.getSource() == myProfBtn){
            myFrame.remove(myCurrPanel);
            myFrame.remove(buttonPanel);
            myCurrPanel = profPanel;
            myFrame.add(myCurrPanel, BorderLayout.CENTER);
            myFrame.add(buttonPanel, BorderLayout.SOUTH);
            myFrame.revalidate();
            myFrame.repaint();
        } else{
            myFrame.remove(myCurrPanel);
            myFrame.remove(buttonPanel);
            myCurrPanel = aboutPanel;
            myFrame.add(myCurrPanel, BorderLayout.CENTER);
            myFrame.add(buttonPanel, BorderLayout.SOUTH);
            myFrame.revalidate();
            myFrame.repaint();
        }

    }

    /**
     * @author James Sherwood
     * Driver program
     * @param args
     */
    public static void main(String[] args) {
        new Iteration1();

    }
}