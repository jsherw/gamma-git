 

package gui;
/*
 * The version number cannot be hard coded in the UI – 
 * I want to see the version drawn from a constant property of an object.
 */

/**
 * The Version DocKeeper app.
 * @author Uladzimir Hanevich
 * @version Fall 2022
 */
public class Version {
    /** Constant instance variable VERSION of the app. */
    private static String VERSION = "Version: 0.1";
    
     /**
      * Version constructor (default)
      */
    public Version(){
        
    }
    
    /**
     * getVersion() returns version of the app.
     * @return VERSION
     */
    final String getVersion() {
        return VERSION;
    }
}
