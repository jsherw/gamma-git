/**
 * @author James Sherwood
 * Object class to hold the program's version number
 */
public class VersionNum {
    private double versionNum = 0.20;

    public VersionNum(){
        setVersionNum(0.20);
    }
    public void setVersionNum(double versionNum) {
        this.versionNum = versionNum;
    }
    public String toString(){
        return Double.toString(getVersionNum());
    }
    public double getVersionNum() {
        return versionNum;
    }
}
