/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package filebackupclient;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Cristian
 */
public class FileBackupClient {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        // TODO code application logic here
        PrefferenceManagement pm = new PrefferenceManagement();
        fileManager fm = new fileManager (pm.getFolderToUpdate());
        LinkedList <FileInformation> filesToBackup =fm.getListOfAllFiles();
        
        for(FileInformation s : filesToBackup) { 
         //System.out.println(s.getFileName());
        }
       InternetCommunicator ic = new InternetCommunicator(pm);
       ReportManager rm = new ReportManager(ic,pm);
        //LinkedList <FileInformation> clientFiles = rm.parseReport("/Users/Cristian/BackupToPiFolder/clientReport.txt");
        //LinkedList <FileInformation> serverFiles = rm.parseReport("/Users/Cristian/BackupToPiFolder/serverReport.txt");
     
     //  rm.writeReportToFile(filesToBackup, "/Users/Cristian/BackupToPiFolder/serverReport.txt");
      rm.checkFilesMissingFromServer("/Users/Cristian/BackupToPiFolder/clientReport.txt", "/Users/Cristian/BackupToPiFolder/serverReport.txt");
       ic.terminateConnection();
        
    }
    public static void uploadToServer(String fineName){
        
    }
}
