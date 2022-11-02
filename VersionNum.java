/**
 * @author James Sherwood
 * Object class to hold the program's version number
 */
public class VersionNum {
    private double versionNum;
    private String group = "Team Gamma";


    public VersionNum(){
        setVersionNum(0.1);

    }
    public void setVersionNum(double versionNum) {
        this.versionNum = versionNum;
    }
    public double getVersionNum() {
        return versionNum;
    }
    public String getGroup(){
        return group;
    }

    public String toString(){
        return Double.toString(getVersionNum());
    }

}
