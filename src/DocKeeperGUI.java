import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Objects;

public class DocKeeperGUI extends JFrame {
    UserInfo u;
    String myFilePath = ".";
    JFrame myFrame;
    JPanel homePanel, aboutPanel, buttonPanel, filePanel, myCurrPanel;
    JButton homeBtn, loginBtn, aboutBtn, filesBtn, uploadBtn, deleteBtn, exportBtn, importBtn, addTagBtn, searchBtn;
    JFileChooser fc = new JFileChooser(myFilePath);

    /**
     * @author James Sherwood
     * Structures the GUI frame
     */
    public DocKeeperGUI() throws IOException {
        //initialize frame
        myFrame = new JFrame("DocKeeper");
        myFrame.setSize(500, 500);
        //change default App logo
        ImageIcon icon = new ImageIcon("./src/logo.PNG");
        myFrame.setIconImage(icon.getImage());

        myFrame.setLayout(new GridBagLayout());
        myFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        GridBagConstraints c = new GridBagConstraints();
        //initialize panels
        homePanel = myHomePanel();
        aboutPanel = myAboutPanel();
        filePanel = myFilePanel();

        //initialize buttons
        homeBtn = new JButton("Home");
        homeBtn.addActionListener(e -> {
            try {
                changePanel(e);
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });

        loginBtn = new JButton("Log In");
        loginBtn.addActionListener(e -> EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginFrame();
            }

        })); //end invokeLater for Login page);
        aboutBtn = new JButton("About");
        aboutBtn.addActionListener(e -> {
            try {
                changePanel(e);
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });

        filesBtn = new JButton("Files");
        filesBtn.addActionListener(e -> {
            try {
                changePanel(e);
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });

        //initialize button panel
        buttonPanel = new JPanel();
        buttonPanel.add(homeBtn);
        buttonPanel.add(loginBtn);
        buttonPanel.add(aboutBtn);

        buttonPanel.add(filesBtn);

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
     * @return home panel
     * @throws IOException if image not found.
     * @author James Sherwood
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
        picLabel.setBounds(1, 1, 485, 400);
        myPanel.add(picLabel);

        return myPanel;
    }

    /**
     * @return about panel
     * @author James Sherwood
     */
    private JPanel myAboutPanel() {
        JPanel versionPanel, registrationPanel, devPanel;
        JPanel mainPanel = new JPanel();
        JLabel versionLabel, groupLabel, devLabel, devName1, introLabel;

        versionPanel = new JPanel(new BorderLayout());

        //Change display when logged in.
        if (u != null && u.getMyUserName() != null) {
            introLabel = new JLabel("This app is registered to: " + u.getMyUserName());
        } else {
            u = new UserInfo();
            introLabel = new JLabel("This app is not yet registered.");
        }

        versionLabel = new JLabel("Version Number: " + u.getVersionNum());
        versionPanel.add(introLabel, BorderLayout.NORTH);
        versionPanel.add(versionLabel, BorderLayout.CENTER);

        registrationPanel = new JPanel(new BorderLayout());
        groupLabel = new JLabel("This app provided by " + u.getGroup());
        registrationPanel.add(groupLabel, BorderLayout.LINE_START);


        devPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        devLabel = new JLabel("Developers: ");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(0, 20, 0, 0);
        devPanel.add(devLabel, c);

        devName1 = new JLabel("1. James Sherwood");
        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 1;
        c.weightx = 0.1;
        c.ipadx = 200;
        devPanel.add(devName1, c);

        mainPanel.setLayout(new BorderLayout());
        mainPanel.setPreferredSize(new Dimension(400, 400));
        mainPanel.add(versionPanel, BorderLayout.NORTH);
        mainPanel.add(devPanel, BorderLayout.CENTER);
        mainPanel.add(registrationPanel, BorderLayout.SOUTH);

        return mainPanel;
    }

    /**
     * @return filePanel
     * @throws FileNotFoundException for file masterList.
     * @author James Sherwood
     */
    private JPanel myFilePanel() throws IOException {

        DefaultListModel<String> dlm = new DefaultListModel<>();
        FileList fl = new FileList();
        JList<String> fileDisplay = new JList<>(dlm);

        JPanel mainPanel = new JPanel(new GridBagLayout());

        //initialize List Model
        for (int i = 0; i < fl.display().length; i++) {
            dlm.addElement(fl.myFiles[i]);
        }

        mainPanel.setPreferredSize(new Dimension(400, 400));
        GridBagConstraints c = new GridBagConstraints();

        //Scrollpane to display file names.
        JScrollPane fileNames = new JScrollPane(fileDisplay);
        fileNames.setPreferredSize(new Dimension(200, 200));
        c.gridx = 0;
        c.gridy = 1;
        mainPanel.add(fileNames, c);

        uploadBtn = new JButton("New File");
        uploadBtn.setEnabled(false);

        uploadBtn.addActionListener(e -> {
            var response = fc.showOpenDialog(null);
            if (response == JOptionPane.OK_OPTION) {
                myFilePath = fc.getSelectedFile().getAbsolutePath();
                try {
                    fl.addToFileList(myFilePath);
                    dlm.addElement(String.valueOf(String.valueOf(Paths.get(myFilePath).getFileName())));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        deleteBtn = new JButton("Delete");
        deleteBtn.setEnabled(false);
        deleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = fileDisplay.getSelectedIndex();
                String str = (String.valueOf(dlm.get(index)));
                try {
                    fl.remove(str);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                dlm.removeElementAt(index);
            }
        });

        exportBtn = new JButton("Export");
        exportBtn.setEnabled(false);
        exportBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    u.export();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        importBtn = new JButton("Import");
        importBtn.setEnabled(false);
        importBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    u.importFile();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        addTagBtn = new JButton("Add Tag");
        addTagBtn.setEnabled(true);
        addTagBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = fileDisplay.getSelectedIndex();
                String str = (String.valueOf(dlm.get(index)));
                try {
                    fl.addTag(str, chooseFromList(fl));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        searchBtn = new JButton("Search");
        searchBtn.setEnabled(true);
        searchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                        String[] matchedFiles = fl.search(chooseFromList(fl));
                        if(matchedFiles.length >0) {
                            dlm.removeAllElements();
                            for (int i = 0; i < fl.display().length; i++) {
                                for (int j = 0; j < matchedFiles.length; j++) {
                                    if (fl.myFiles[i].equals(matchedFiles[j])) {
                                        dlm.addElement(fl.myFiles[i]);
                                    }
                                }
                            }
                        } else{
                            JOptionPane.showMessageDialog(null,"No Files Found With Matching Tags");
                        }
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
            }
        });


        JPanel buttonPanel = new JPanel(new BorderLayout());

        JPanel btn1 = new JPanel();
        JPanel btn2 = new JPanel();
        btn1.add(uploadBtn);
        btn1.add(exportBtn);
        btn1.add(importBtn);
        btn2.add(addTagBtn);
        btn2.add(searchBtn);
        btn2.add(deleteBtn);
        buttonPanel.add(btn1);
        buttonPanel.add(btn2, BorderLayout.SOUTH);
        c.gridx = GridBagConstraints.RELATIVE;
        c.gridy = GridBagConstraints.SOUTH;
        mainPanel.add(buttonPanel, c);

        return mainPanel;
    }

    private String chooseFromList(FileList fl) {
        String choice = null;
        JPanel pnl = new JPanel();
        DefaultComboBoxModel<String> tags = new DefaultComboBoxModel<>();
        for(String s: fl.tags){
            tags.addElement(s);
        }
        JComboBox<String> box = new JComboBox<>(tags);
        pnl.add(box);
        int iResult = JOptionPane.showConfirmDialog(null, pnl, "Tags", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (iResult == JOptionPane.OK_OPTION) {
            choice = (String) box.getSelectedItem();
        }
        return choice;
    }

    /**
     * @param e input from button press.
     * @author James Sherwood
     * Change display to user's selected panel.
     */
    private void changePanel(ActionEvent e) throws FileNotFoundException {
        if (e.getSource() == homeBtn) {
            constraints(homePanel);
        }
        else if (e.getSource() == filesBtn) {
            constraints(filePanel);
        } else {
            constraints(aboutPanel);
        }
    }

    /**
     * @param homePanel default grid constraints.
     * @author James Sherwood
     * constraints used when changing panels.
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
     * @param args default
     * @author James Sherwood
     * Driver program
     */
    public static void main(String[] args) throws IOException {
        new DocKeeperGUI();
    }

}
