import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author James Sherwood
 * Object class to hold the program's version number
 */
public class UserInfo {
    private final double versionNum = 0.15;
    private final String group = "Team Gamma";
    private String userName;
    private String password;
    private boolean isAdmin = false;
    private boolean isUser = false;

    public UserInfo(){
        this.userName = null;
        this.password = null;
    }
    public UserInfo(String name, String password ) throws FileNotFoundException {
        setUserName(name);
        setPassword(password);

        isUser = verifyUser();
    }

    /**
     * @author James Sherwood
     * @return true if username & password match
     * @throws FileNotFoundException
     */
    private boolean verifyUser() throws FileNotFoundException {
        File f = new File("C:/Users/james/iteration1/src/SavedUsers.txt");
        Scanner scnr = new Scanner(f);

        while(scnr.hasNext()){
           String currLine = scnr.nextLine();
            //check for admin, admin names are stored with a prepended '*' character.
            if(currLine.equals("*" + getUserName() + getPassword())  ) {
                setIsAdmin();
                return true;
            //check for user
            } else if(currLine.equals(getUserName() + getPassword())){
                return true;
            }
        }
        return false;
    }

    public double getVersionNum() {return versionNum;}
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getUserName() {
        return userName;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword(){return this.password;}
    public void setIsAdmin(){
        isAdmin = true;
    }
    public boolean getIsAdmin(){
        return this.isAdmin;
    }
    public boolean getIsUser(){
        return this.isUser;
    }
    public String getGroup(){return this.group;}
}
