/*
 * TCSS 360 Doc Keeper application
 */



//import io.CredentialingLoader;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

/**
 * LoginFrame provides a user interface for login and registration.
 * 
 * @author 
 * @version
 */
public class LoginFrame extends JFrame {

    /** The Serialization ID. */
    private static final long serialVersionUID = 1728929170437545812L;

    // constants to capture screen dimensions
    /** A ToolKit. */
    private static final Toolkit KIT = Toolkit.getDefaultToolkit();
    
    /** The Dimension of the screen. */
    private static final Dimension SCREEN_SIZE = KIT.getScreenSize();
    
    /** The text field used to enter the user name. */
    private final JTextField myUserNameField;

    /** The text field used to enter the password. */
    private final JPasswordField myPasswdField;

    /** The text field used to enter the password the 2nd time on registration. */
    private final JPasswordField myPasswdField2;

    /** The button used to trigger login sequence. */
    private final JButton myLoginButton;

    /** The button used to trigger login registration. */
    private final JButton myRegisterButton;

    /** The panel that holds the Login/registration text fields. */
    private final JPanel myFieldsPanel;
    
    /**The Icon of the frame*/
    private final ImageIcon myIcon= new ImageIcon("./src/logo.PNG");

    /**
     * Initializes the Login Frame GUI (login window).
     * @param theCampusList the list of university campuses
     */
    public LoginFrame() {
        super();
        myUserNameField = new JTextField(10);
        myPasswdField = new JPasswordField(10);
        myPasswdField2 = new JPasswordField(10);
        myFieldsPanel = new JPanel(new GridLayout(2, 2));
        myLoginButton = new JButton("Login");
        myRegisterButton = new JButton("Register");
        this.setIconImage(myIcon.getImage());  //maybe this is not needed, but I leaved it because it is 100% no error prone
        
        //set up the login gui.
        layoutComponents();
        assignActions();
        finalizeFrame();
        
    }

    /**
     * Layout the Swing components.
     */
    private void layoutComponents() {
        final JPanel pane = new JPanel(new BorderLayout());

        final JPanel header = new JPanel();
        final JLabel headerLabel = new JLabel("Login to access Doc Keeper");
        header.add(headerLabel);
        pane.add(header, BorderLayout.NORTH);

        myFieldsPanel.setBorder(BorderFactory.
                        createEmptyBorder(5, 10, 5, 10));

        final JLabel userNameLabel = new JLabel("Username:");
        myFieldsPanel.add(userNameLabel);
        myFieldsPanel.add(myUserNameField);

        final JLabel pwdLabel = new JLabel("Password:"); 
        myFieldsPanel.add(pwdLabel);
        myFieldsPanel.add(myPasswdField);

        pane.add(myFieldsPanel, BorderLayout.CENTER);

        final JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottom.add(myRegisterButton);
        bottom.add(myLoginButton);

        pane.add(bottom, BorderLayout.SOUTH);

        setContentPane(pane);        
    }

    /**
     * Add Listeners to any GUI components that require them.
     */
    private void assignActions() {
        myLoginButton.addActionListener(this::loginButtonAction);
        myRegisterButton.addActionListener(this::registerButtonAction);
    }
    
    /**
     * Finalize this JFrame before making visible. 
     */
    private void finalizeFrame() {
        // make the GUI so that it cannot be resized by the user dragging a corner
        setResizable(false);
        
        // position the frame in the center of the screen
        setLocation(SCREEN_SIZE.width / 2 - getWidth() / 2,
                    SCREEN_SIZE.height / 2 - getHeight() / 2);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);        
    }

    /**
     * Validate the Login Fields for empty. If the fields are empty, an error
     * message is returned, otherwise the empty String is returned.
     * 
     * @return an error message if the login fields are empty, otherwise the
     *         empty String
     */
    private String validateLoginFieldsEmpty() {
        String result = validateEmpty(myUserNameField);
        final String pwd = validateEmpty(myPasswdField);
        if (result.isEmpty()) {
            result = pwd;
        }
        return result;
    }

    /**
     * Validate TextField for empty. If the field is empty, an error message is
     * returned, otherwise the empty String is returned.
     * 
     * @param theField The Field to validate.
     * @return an error message if the field is empty, otherwise the empty String.
     */
    private String validateEmpty(final JTextField theField) {
        String result = "";
        if (theField.getText().isEmpty()) {
            theField.setBackground(Color.PINK);
            result = "All fields are required.";
        } else {
            theField.setBackground(Color.WHITE);
        }
        return result;
    }
    
    /* Event Handlers follow */

    /**
     * Event handler for the Login Button during login. This method is implicitly called when
     * the user presses the "Login" button.  It is not explicitly called in  this class: you 
     * will not find a method invocation for it.
     * 
     * @param theEvent the ActionEvent that triggered this method.
     * @throws IllegalStateException when the component is not a JButton
     */
    private void loginButtonAction(final ActionEvent theEvent) {
        if (theEvent.getSource().getClass() != JButton.class) {
            throw new IllegalStateException("Only invoke when used as handler for JButton.");
        }
        final String errorMsg = validateLoginFieldsEmpty();
        if (errorMsg.isEmpty()) {
            LoginFrame.this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            myRegisterButton.setEnabled(false);
            myLoginButton.setEnabled(false);
            new AttemptLoginWorker().execute();
        } else {
            JOptionPane.showMessageDialog(this, 
                                          errorMsg, 
                                          "All fields are required!",
                                          JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Event handler for the Login Button during registration. 
     * This method is implicitly called when the user presses the "Register and (or) Login" button. It is not explicitly called in 
     * this class: you will not find a method invocation for it.
     * 
     * @param theEvent the ActionEvent that triggered this method.
     * @throws IllegalStateException when the component is not a JButton
     */
    private void loginRegisterButtonAction(final ActionEvent theEvent) {
        if (theEvent.getSource().getClass() != JButton.class) {
            throw new IllegalStateException("Only invoke when used as handler for JButton.");
        }
        final String errorMsg = validateLoginFieldsEmpty();
        if (Arrays.equals(myPasswdField.getPassword(), 
                                                 myPasswdField2.getPassword())) {
            if (errorMsg.isEmpty()) {
                LoginFrame.this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                myRegisterButton.setEnabled(false);
                myLoginButton.setEnabled(false);
                new AttemptRegistrationWorker().execute();
            }
        } else {
            JOptionPane.showMessageDialog(this, 
                                          "Passwords must be the same.",
                                          "Passwords don't match!",
                                          JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Event handler for the Register Button. This method is implicitly called when
     * the user presses the "Register" button. It is not explicitly called in this class: you 
     * will not find a method invocation for it. 
     * 
     * @param theEvent the ActionEvent that triggered this method.
     * @throws IllegalStateException when the component is not a JButton
     */
    private void registerButtonAction(final ActionEvent theEvent) {
        if (theEvent.getSource().getClass() != JButton.class) {
            throw new IllegalStateException("Only invoke when used as handler for JButton.");
        }
        
        /* 
         * disable Register button. Later the name of the login button will be changed to "register".
         * actually the same button (button listener) i.e login is used for registration, but with different label. 
         * Because the method that listener invokes is the same.
         */
        
        myRegisterButton.setVisible(false);   
        myFieldsPanel.setLayout(new GridLayout(3, 2));
        final JLabel pwdLabel = new JLabel("Confirm password:");
        myFieldsPanel.add(pwdLabel);
        myFieldsPanel.add(myPasswdField2);

        myLoginButton.setText("Register and Login");  //change the name for the login button.
        myLoginButton.removeActionListener(myLoginButton.getActionListeners()[0]);
        myLoginButton.addActionListener(this::loginRegisterButtonAction);

        pack();
    }
    
    /* Inner Classes follow */
    
    /**
     * A worker thread to attempt register user.
     * NOTE: THERE ARE NO ERRORS/BUGS IN THIS INNER CLASS
     * Operations that may take a long time to complete (like file I/O or network connections) 
     * should be performed in a thread separate from the user interface thread. This will keep 
     * your graphical user interface responsive to user interactions. 
     * This inner class starts a background thread to perform the file I/O operations found in
     * io.Credentialing.java
     * 
     * @author 
     *
     */
    private class AttemptRegistrationWorker extends SwingWorker<String, Integer> {
        
        @SuppressWarnings("deprecation")
        @Override
        public String doInBackground() {
            //sends to CredentialingLoader.register method login and password
            return CredentialingLoader.register(myUserNameField.getText(), 
                                             myPasswdField.getText());
        }

        @Override
        public void done() {
            try {
                // Error message cmes from CredentialingLoader class register method.
                final String errMsg = get(); //get() blocks all executions in this worker until it is done. 
                if (errMsg.isEmpty()) {      //When empty (no errMsg) - proceed executions
                    LoginFrame.this.dispose();
//                    new AppGUI(false);  //FIXME isAdmin trigger might be incerted here
                    
                } else {      
                    LoginFrame.this.setCursor(Cursor.getDefaultCursor());
                    myRegisterButton.setEnabled(true);
                    myLoginButton.setEnabled(true);
                    JOptionPane.showMessageDialog(LoginFrame.this, 
                                                  errMsg, 
                                                  "Error!",
                                                  JOptionPane.ERROR_MESSAGE);
                } 
                
            } catch (final InterruptedException ex1) {
                ex1.printStackTrace();
            } catch (final ExecutionException ex2) {
                ex2.printStackTrace();
            }
        }
    } 
    
    /**
     * A worker thread to attempt login.
     * NOTE: THERE ARE NO ERRORS/BUGS IN THIS INNER CLASS
     * Operations that may take a long time to complete (like file I/O or network connections) 
     * should be performed in a thread separate from the user interface thread. This will keep 
     * your graphical user interface responsive to user interactions. 
     * This inner class starts a background thread to perform the file I/O operations found in
     * io.Credentialing.java
     * 
     * @author Uladzimir Hanevich
     *
     */
    private class AttemptLoginWorker extends SwingWorker<String, Integer> {

        @SuppressWarnings("deprecation")
        @Override
        public String doInBackground() {
            return CredentialingLoader.login(myUserNameField.getText(), 
                                             myPasswdField.getText()); // Password in plain text
        }

        @Override
        public void done() {
            try {
                final String message = get();
                System.out.println("*TEST* print from LF_loginWorker: " + message); //TODO delete or comment this test
                if (message.isEmpty()) {
                    LoginFrame.this.dispose();
                    //TODO delete test sop
                    System.out.println("LF_LoginWorker said - you are Logged");
//                    new AppGUI(false);  //FIXME isAdmin trigger might be inserted here
                } else {       
                    LoginFrame.this.setCursor(Cursor.getDefaultCursor());
                    myRegisterButton.setEnabled(true);
                    myLoginButton.setEnabled(true);
                    JOptionPane.showMessageDialog(LoginFrame.this, 
                                                  message, 
                                                  "Error!",
                                                  JOptionPane.ERROR_MESSAGE);
                } 
            } catch (final InterruptedException ex1) {
                ex1.printStackTrace();
            } catch (final ExecutionException ex2) {
                ex2.printStackTrace();
            }
        }
    }  // AttemptLoginWorker END

}
