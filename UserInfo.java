/*
 * Create object with user info
 */
public class UserInfo {
    private String userName;
    private String userEmail;

    /**
     * @author James Sherwood
     * Default Constructor
     */
    public UserInfo(){
        setUserName(null);
        setUserEmail(null);
    }

    /**
     * @author James Sherwood
     * Parameterized Constructor
     *
     * @param name username
     * @param email user e-mail
     */
    public UserInfo(String name, String email){
        setUserName(name);
        setUserEmail(email);
    }

    /**
     * @author James Sherwood
     * Override default toString
     * @return formatted userName and userEmail
     */
    @Override
    public String toString(){
        return "Name: " + this.userName + "\n" + "E-mail Address:" + this.userEmail;
    }

    /**
     * @author James Sherwood
     * Setter for field userName
     * @param name username
     */
    public void setUserName(String name){
        this.userName = name;
    }

    /**
     * @author James Sherwood
     * Getter for field userName
     * @return field userName value
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @author James Sherwood
     * Setter for field userEmail
     * @param email user e-mail
     */
    public void setUserEmail(String email){
        this.userEmail = email;
    }

    /**
     * @author James Sherwood
     * Getter for field userEmail
     * @return userEmail user e-mail
     */
    public String getUserEmail() {
        return userEmail;
    }
}
