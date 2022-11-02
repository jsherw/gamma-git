import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProfilePanel {
    JPanel myPanel;
    JLabel myUserName, myEmail;
    JButton myButton;
    JTextField myNameText, myEmailText;

    public ProfilePanel(){
        myPanel = new JPanel();

        myUserName = new JLabel("Name: ");
        myUserName.setBounds(150, 150, 60, 20);
        myNameText = new JTextField();
        myNameText.setBounds(190,150,140,20);

        myEmail= new JLabel("Email: ");
        myEmail.setBounds(150,200,60,20);
        myEmailText = new JTextField();
        myEmailText.setBounds(190,200,140,20);

        myButton = new JButton("Submit");
        myButton.setBounds(200, 240, 100, 20);
        myButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserInfo u = new UserInfo(myNameText.getText(), myEmailText.getText());
            }
        });

        myPanel.add(myUserName);
        myPanel.add(myNameText);
        myPanel.add(myEmail);
        myPanel.add(myEmailText);
        myPanel.add(myButton);


    }

    JPanel getMyPanel(){
        return myPanel;
    }
}
