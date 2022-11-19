import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Objects;

public class DocKeeperGUI extends JFrame {
    UserInfo myVersion;
    String myFilePath = ".";
    JFrame myFrame;
    JPanel homePanel, loginPanel, aboutPanel, buttonPanel, filePanel, myCurrPanel;
    JButton myHomeBtn, myLoginBtn, myAboutBtn, myFilesBtn, mySubmit, myUploadBtn;
    JFileChooser myFileChooser = new JFileChooser(myFilePath);

    /**
     * @author James Sherwood
     * Structures the GUI frame
     */
    public DocKeeperGUI() throws IOException {
        //initialize frame
        myFrame = new JFrame("DocKeeper");
        myFrame.setSize(500,500);

        myFrame.setLayout(new GridBagLayout());
        myFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        GridBagConstraints c = new GridBagConstraints();
        //initialize panels
        homePanel = myHomePanel();
        aboutPanel = myAboutPanel();
        loginPanel = myLogInPanel();
        filePanel = myFilePanel();


        //initialize buttons
        myHomeBtn = new JButton("Home");
        myHomeBtn.addActionListener(e -> {
            try {
                changePanel(e);
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });
        myLoginBtn = new JButton("Log In");
        myLoginBtn.addActionListener(e -> {
            try {
                changePanel(e);
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });
        myAboutBtn = new JButton("About");
        myAboutBtn.addActionListener(e -> {
            try {
                changePanel(e);
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });

        myFilesBtn = new JButton("Files");
        myFilesBtn.addActionListener(e -> {
            try {
                changePanel(e);
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });

        //initialize button panel
        buttonPanel = new JPanel();
        buttonPanel.add(myHomeBtn);
        buttonPanel.add(myLoginBtn);
        buttonPanel.add(myAboutBtn);

        buttonPanel.add(myFilesBtn);

        myCurrPanel = homePanel;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.gridheight = 2;
        myFrame.getContentPane().add(myCurrPanel, c);
        c.gridx = 0;
        c.gridy = 3;

        myFrame.getContentPane().add(buttonPanel, c);

        myFrame.pack();
        myFrame.setVisible(true);

    }

    /**
     * @author James Sherwood
     * Change display to user's selected panel.
     * @param e input from button press.
     */
      private void changePanel(ActionEvent e) throws FileNotFoundException {
        if(e.getSource() == myHomeBtn){
            constraints(homePanel);
        }else if(e.getSource() == myLoginBtn || e.getSource() == mySubmit){
            constraints(myLogInPanel());
        } else if(e.getSource() == myFilesBtn){
            constraints(filePanel);
        }
        else{
            constraints(aboutPanel);
        }
    }

    /**
     * @author James Sherwood
     * constraints used when changing panels.
     * @param homePanel default grid constraints.
     */
    private void constraints(JPanel homePanel) {
        myFrame.remove(myCurrPanel);
        myFrame.remove(buttonPanel);
        GridBagConstraints c = new GridBagConstraints();
        myCurrPanel = homePanel;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.gridheight = 2;
        myFrame.getContentPane().add(myCurrPanel, c);
        c.gridx = 0;
        c.gridy = 3;

        myFrame.getContentPane().add(buttonPanel, c);

        myFrame.revalidate();
        myFrame.repaint();
    }

    /**
     * @author James Sherwood
     * @return  home panel
     * @throws IOException if image not found.
     */
    private JPanel myHomePanel() throws IOException {
        JPanel myPanel;
        JLabel picLabel;

        myPanel = new JPanel();
        myPanel.setLayout(new GridBagLayout());

        //home page image
        Image myImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("DocKeeper-logos.jpeg")));
        myImage = myImage.getScaledInstance(475, 400, java.awt.Image.SCALE_SMOOTH);
        ImageIcon myImageIcon = new ImageIcon(myImage);
        picLabel = new JLabel(myImageIcon);
        picLabel.setBounds(1,1,485,400);
        myPanel.add(picLabel);

        return myPanel;
    }

    /**
     * @author James Sherwood
     * @return login panel
     */
    private JPanel myLogInPanel(){
        final int PADDING = 30;
        JPanel myPanel, welcomePanel, formPanel, btnPanel;
        JLabel myUserName, myPassword, welcomeMessage;
        JTextField myNameText;
        JPasswordField passwordField;

        //change welcome message based on log-in
        welcomePanel = new JPanel(new FlowLayout(FlowLayout.CENTER,PADDING,PADDING));
        if(myVersion.getIsUser()){
            welcomeMessage = new JLabel("Welcome, " + myVersion.getUserName());
        } else{
            welcomeMessage = new JLabel("Welcome. Please Login.");
        }
        welcomePanel.add(welcomeMessage);

        formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        myUserName = new JLabel("Name: ");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy =0;
        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(0,20,0,0);
        formPanel.add(myUserName, c);

        myPassword = new JLabel("Password: ");
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy= 1;
        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(0,20,0,0);
        formPanel.add(myPassword,c);

        myNameText = new JTextField(50);
        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 0;
        c.weightx = 0.1;
        c.ipadx = 200;
        formPanel.add(myNameText, c);

        passwordField = new JPasswordField(150);
        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 1;
        c.weightx = 0.001;
        c.ipadx = 200;
        formPanel.add(passwordField,c);

        btnPanel = new JPanel();
        btnPanel.setLayout(new FlowLayout(FlowLayout.CENTER,PADDING,PADDING));

        mySubmit = new JButton("Submit");
        mySubmit.addActionListener(e -> {
            String s1 = myNameText.getText();
            String s2 = String.valueOf(passwordField.getPassword());
            try {
                myVersion = new UserInfo(s1,s2);
                if(myVersion.getIsUser()){
                    //reload page
                    changePanel(e);
                }
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            if (myVersion.getIsAdmin()){
                myUploadBtn.setEnabled(true);
            }
            myNameText.setText("");
            passwordField.setText("");
        });
        btnPanel.add(mySubmit);

        myPanel = new JPanel(new BorderLayout());
        myPanel.setPreferredSize(new Dimension(400,400));

        //hide formPanel after user has signed in
        if(!myVersion.getIsUser()){
            myPanel.add(welcomePanel,BorderLayout.NORTH);
            myPanel.add(formPanel, BorderLayout.CENTER);
            myPanel.add(btnPanel, BorderLayout.SOUTH);
        } else {
            myPanel.add(welcomePanel, BorderLayout.CENTER);
            myPanel.add(btnPanel, BorderLayout.SOUTH);
        }
        return myPanel;
    }

    /**
     * @author James Sherwood
     * @return filePanel
     * @throws FileNotFoundException for file masterList.
     */
    private JPanel myFilePanel() throws FileNotFoundException {

        DefaultListModel<String> dlm = new DefaultListModel<>();
        FileList myFile = new FileList();
        JList<String> myFileList = new JList<>(dlm);
        myUploadBtn = new JButton("New File");
        JPanel myPanel = new JPanel(new GridBagLayout());

        //initialize List Model
        for(int i = 0; i < myFile.displayFile().length; i++){
            dlm.addElement(myFile.myFiles[i]);
        }

        myPanel.setPreferredSize(new Dimension(400,400));
        GridBagConstraints c = new GridBagConstraints();

        //Scrollpane to display file names.
        JScrollPane fileList = new JScrollPane(myFileList);
        fileList.setPreferredSize(new Dimension(200,200));
        c.gridx = 0;
        c.gridy = 1;
        myPanel.add(fileList,c);

        //FIXME upload button should only be enabled for admins.
        myUploadBtn.setEnabled(false);
        c.gridy = 2;
        myPanel.add(myUploadBtn,c);

        myUploadBtn.addActionListener(e -> {
            var response = myFileChooser.showOpenDialog(null);
            if (response == JOptionPane.OK_OPTION){
                myFilePath = myFileChooser.getSelectedFile().getAbsolutePath();
                try {
                    myFile.addToFileList(myFilePath);
                    dlm.addElement(String.valueOf(String.valueOf(Paths.get(myFilePath).getFileName())));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        return myPanel;
    }

    /**
     * @author James Sherwood
     * @return about panel
     */
    private JPanel myAboutPanel(){
        JPanel versionPanel, registrationPanel, devPanel;
        JPanel myPanel = new JPanel();
        JLabel myVersionLabel, myGroupLabel, myDevLabel, devName1, myIntro;

        versionPanel = new JPanel(new BorderLayout());

        //Change display when logged in.
        if(myVersion != null && myVersion.getUserName() != null){
            myIntro = new JLabel("This app is registered to: " + myVersion.getUserName());
        } else{
            myVersion = new UserInfo();
            myIntro = new JLabel("This app is not yet registered.");
        }

        myVersionLabel = new JLabel("Version Number: " + myVersion.getVersionNum());
        versionPanel.add(myIntro, BorderLayout.NORTH);
        versionPanel.add(myVersionLabel, BorderLayout.CENTER);

        registrationPanel = new JPanel(new BorderLayout());
        myGroupLabel = new JLabel("This app provided by " + myVersion.getGroup());
        registrationPanel.add(myGroupLabel, BorderLayout.LINE_START);


        devPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        myDevLabel = new JLabel("Developers: ");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy =0;
        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(0,20,0,0);
        devPanel.add(myDevLabel, c);

        devName1 = new JLabel("1. James Sherwood");
        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 1;
        c.weightx = 0.1;
        c.ipadx = 200;
        devPanel.add(devName1, c);

        myPanel.setLayout(new BorderLayout());
        myPanel.setPreferredSize(new Dimension(400,400));
        myPanel.add(versionPanel, BorderLayout.NORTH);
        myPanel.add(devPanel, BorderLayout.CENTER);
        myPanel.add(registrationPanel, BorderLayout.SOUTH);

        return myPanel;
    }

    /**
     * @author James Sherwood
     * Driver program
     * @param args default
     */
    public static void main(String[] args) throws IOException {
        new DocKeeperGUI();
    }
}