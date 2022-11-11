/*
 * TCSS 360 "Team Gamma"
 * Assignment - "DocKeeper" group project
 */

package gui;

import java.awt.Image;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
/**
 * The GUI for DocKeeper.
 * 
 * @author Uladzimir Hanevich
 * @version Fall 2022
 */
public class AppGUI extends JPanel {

    /**  A generated serial version UID for object Serialization. */
    private static final long serialVersionUID = -4860201906562574893L;

    /** The window title. */
    private static final String TITLE = "DocKeeper";

    /** The label in which we will display images (text also if needed). */
    private JLabel myImageLabel;
    
    /** The open button. */
    private JButton myOpenButton;

    /** The save button. */
    private JButton mySaveButton;

    /** The close button. */
    private JButton myCloseButton;

    /** One step back button */
    private JButton myBackButton = new JButton("Back");
    
    /** About button, displays about information */
    private JButton myAboutButton = new JButton("About");
    
    /** The list of Admin's buttons. */
    private List<JButton> myAdminButtons;
    
    /** filePath instance variable.*/
    private String myFilePath = ".";
    
    /**
     * FileChoser object (native Java).
     * Program should creates only one JFileChooser object and reuses it repeatedly."
     */
    private JFileChooser myFileChooser = new JFileChooser(myFilePath);
    
    /** myImage instance variable.*/
    private Image myImage;

    /**
     * AppGUI() Constructor.
     * Initializes the GUI.
     */
    public AppGUI() {
        super();
        setLayout(new BorderLayout());
        buildComponents();
        layoutComponents();
        addEvents();
    }
    
    /**
     * Instantiate the graphical components (frame, imageLabel, buttons).
     */
    private void buildComponents() {
        
        // create new ImageLabel
        myImageLabel = new JLabel();
        myImageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        myImageLabel.setVerticalAlignment(SwingConstants.CENTER);
        
        // create buttons (bottom row)
        myOpenButton = new JButton("Open File...");
        mySaveButton = new JButton("Save As...");
        myCloseButton = new JButton("Close file");
        myBackButton = new JButton("Back");
        myAboutButton = new JButton("About");
        /*
         * Disable/enable buttons as needed to assign admin' rights
         */
        mySaveButton.setEnabled(false);
        myCloseButton.setEnabled(false);
        myBackButton.setEnabled(false);
        myAboutButton.setEnabled(true);
        
        // create the "Admin only" buttons. Add any admin functionality to them
        myAdminButtons = new ArrayList<JButton>();
        myAdminButtons.add(new JButton("Adm_func"));
        myAdminButtons.add(new JButton("Adm_func_1"));
        myAdminButtons.add(new JButton("Adm_func_2"));
        
    } //end of buildComponents()

    /**
     * disableButtons() sets all the buttons except Open to disabled.
     * Admin buttons are enabled for admin only functionality is not added yet,
     * so they are enabled by default for everyone now.
     */
    //Use this list to add any Admin buttons to extend functionality.
    private void disableButtons() {
        for (JButton button : myAdminButtons) {
            button.setEnabled(false);
        }
        mySaveButton.setEnabled(false);
        myCloseButton.setEnabled(false);
        myBackButton.setEnabled(false);
    } //end disableButtons()
    
    /**
     * enableButtons() sets all needed buttons to enabled.
     */
    private void enableButtons() {
        for (JButton button : myAdminButtons) {
            button.setEnabled(true);
        }
        mySaveButton.setEnabled(true);
        myCloseButton.setEnabled(true);
        myBackButton.setEnabled(true);
//        myAboutButton.setEnabled(true);
    } //end enableButtons()
    
    /**
     * Sets up the event listeners for all buttons.
     */
    //Add action listener here if needed
    private void addEvents() {

        //Implemented by using inner class MyOpenFileListener.
        myOpenButton.addActionListener(new MyOpenFileListener());
        
        //Implemented by using inner class DisplayAboutInfoListener.
        myAboutButton.addActionListener(new DisplayAboutInfoListener());
              
        //Add an action listener using a method reference.
        mySaveButton.addActionListener(this::saveFileMethodReference);
        
        //implemented using the lambda expression.
        myCloseButton.addActionListener(theCloseImageEvent -> {
            myImageLabel.setIcon(null);
            disableButtons(); //Disable buttons.
        }); //End of lambda
         
    } // End of addEvents().
    
    /**
     * A saveMethodReference(). Save file functionality for V0.1 is not implemented.
     * @param theSaveImageEvent the Action event is not used here
     */
    /*
     * The method header must match the functional interface's single
     * abstract method signature, not including name.
     */
    private void saveFileMethodReference(final ActionEvent theSaveFileEvent) { 
        //Implementation goes here.
    }
    

    /**
     * layoutComponents() Sets up the graphical components: layouts, borders, etc.
     * Aligns the panels.
     */
    private void layoutComponents() {

        // add the Admin's buttons to a north panel (upper row)
        final JPanel northPanel = new JPanel(new FlowLayout());
        for (final JButton button : myAdminButtons) {
            northPanel.add(button);
        }
        add(northPanel, BorderLayout.NORTH);

     // label that displays anything added directly in the Center
        add(myImageLabel, BorderLayout.CENTER);
        
        // south panel to hold the file Open button
        final JPanel south = new JPanel(new FlowLayout());
        south.add(myOpenButton);
        south.add(mySaveButton);
        south.add(myCloseButton);
        south.add(myBackButton);  
        south.add(myAboutButton);
        add(south, BorderLayout.SOUTH);
        
    } //end layoutComponents()

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    public static void createAndShowGui() {
        final AppGUI mainPanel = 
                        new AppGUI();
        
        //set up the color of main window if needed
        mainPanel.setBackground(Color.GRAY);
                
        // set properties of the frame
        final JFrame window = new JFrame(TITLE);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //window.setMinimumSize(new Dimension());  //not needed for V 0.1
        window.setContentPane(mainPanel);
        //window.pack();                           //not needed for V 0.1
        window.setSize(600, 800);
        window.setVisible(true);
        
    } //end of createAndShowGui()
    
    /**
     * The  inner class listener for the "About" button.
     * Inner classes should be declared after all fields and methods.
     * @author Uladzimir Hanevich
     * @version Fall 2022
     */
    class DisplayAboutInfoListener implements ActionListener {
        
        @Override
        public void actionPerformed(final ActionEvent theAboutEvent) {
            //create new version object from Version class
            var version = new Version();

            final var aboutMessage = version.getVersion() + "\n"
                            + "team GAMMA \n "
                            + "\n"
                            + "Developers: \n"
                            + "Uladzimir Hanevich \n"
                            + "...\n" // add info instead of "..." as needed
                            + "...\n"; 
           
            
            JOptionPane.showMessageDialog(null, aboutMessage, 
                                          "About DocKeeper", JOptionPane.INFORMATION_MESSAGE); 
            
        } 
                
    } //End of inner class DisplayAboutInfoListener.

    /**
     * The  inner class listener for the "open file" button.
     * Inner classes should be declared after all fields and methods.
     * @author Uladzimir Hanevich
     * @version Winter 2022
     */
    class MyOpenFileListener implements ActionListener {
        /**
         * actionPerformed method listens to the open button action.
         */
        @Override
        public void actionPerformed(final ActionEvent theOpenEvent) {
            /*
             * A new fileChooser object created before and stored in variable reference,
             * so use it repeatedly for open and save buttons.
             * If statement used to avoid the exceptions when 
             * cancel button pressed very first time.
             */
            final var responce = myFileChooser.showOpenDialog(null);
            if (responce == JOptionPane.OK_OPTION) {
              //Update and save the file path.
                myFilePath = myFileChooser.getSelectedFile().getAbsolutePath();
                
                    myImage = new ImageIcon(myFilePath).getImage();
                    
                    myImageLabel.setIcon(new ImageIcon(myImage));
                    
                    //Enable buttons when file is open.
//FIXME    Make some buttons enabled for an admin, so to keep other users out from modifying admin's stuff
                    enableButtons();
            } 
            
        } 
                
    } //End of inner class MyOpenFileListener.
    
}
