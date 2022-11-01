import javax.swing.*;

/**
 * @author James Sherwood
 * Create object class to hold user name and email
 */
public class UserInfo {
    private String userName;
    private String userEmail;

    /**
     * @author James Sherwood
     * @param s1 user's name
     * @param s2 user's e-mail
     */
//FIXME Add input validation
    public UserInfo(String s1, String s2){
        setUserName(s1);
        setUserEmail(s2);
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserEmail() {
        return userEmail;
    }

    /**
     * @author James Sherwood
     * @return formatted user name and email.
     */
    @Override
    public String toString(){
        return "Name: " + getUserName() + "\n" + "Email: " + getUserEmail() + "\n";
    }
}
