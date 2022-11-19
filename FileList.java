import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class FileList  {
    String[] myFiles;
    File masterList = new File("C:/Users/james/iteration1/src/FileText.txt");
    FileWriter fw;
    BufferedWriter bw;
    PrintWriter out;

    public FileList() throws FileNotFoundException {
        myFiles = displayFile();
    }

    /**
     * @author James Sherwood
     * @param fileAddress   location of file to add to the list
     * @throws IOException list can't be found.
     */
    void addToFileList(String fileAddress) throws IOException {
        try{
            fw = new FileWriter(masterList, true);
            bw = new BufferedWriter(fw);
            out = new PrintWriter(bw);
            out.println("\n" + fileAddress);
        }
        catch( IOException e ){
            // File writing failed
        }
        finally{
            try{
                if( out != null ){
                    out.close();
                }
                else if( bw != null ){
                    bw.close();
                }
                else if( fw != null ){
                    fw.close();
                }
            }
            catch( IOException e ){
                // Unknown exception
            }
        }
    }

    /**
     * @author James Sherwood
     * @return Array of file names.
     * @throws FileNotFoundException masterList not found.
     */
    public String[] displayFile() throws FileNotFoundException {
        Set<String> myFileKeys = new HashSet<>();
        Scanner scnr = new Scanner(masterList);
        String myFileAddress;
        Path myFileName;

        while(scnr.hasNextLine()){
            myFileAddress = scnr.nextLine();
            myFileName = Paths.get(myFileAddress).getFileName();
            myFileKeys.add(String.valueOf(myFileName));
        }

        return myFileKeys.toArray(new String[0]);
    }
    public void removeFile() {

    }
    String findFile(String str){
        /*
        Search for particular files according a set criteria.
         */
        return null;
    }
}
