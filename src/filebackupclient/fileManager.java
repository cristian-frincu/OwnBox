package filebackupclient;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;



public class fileManager {
    
  
    LinkedList<FileInformation> listOfAllFiles = new LinkedList();
    
    public fileManager(String directoryName) {
       listf(directoryName);
  
    }
    
    private void listf(String directoryName) {
        File directory = new File(directoryName);

        // get all the files from a directory
        File[] fList = directory.listFiles();
        for (File file : fList) {
            if (file.isFile()) {
                listOfAllFiles.add(new FileInformation(file.getName(),file.getAbsolutePath()));
            } else if (file.isDirectory()) {
                listf(file.getAbsolutePath());
            }
        }
    }
   
    

    public LinkedList<FileInformation> getListOfAllFiles() {
        return listOfAllFiles;
    }

    public void setListOfAllFiles(LinkedList<FileInformation> listOfAllFiles) {
        this.listOfAllFiles = listOfAllFiles;
    }
   

}
