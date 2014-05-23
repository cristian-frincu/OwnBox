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
     PrefferenceManagement pm = new PrefferenceManagement();
     fileManager fm = new fileManager (pm.getFolderToUpdate());
     LinkedList <FileInformation> filesToBackup =fm.getListOfAllFiles();
     InternetCommunicator ic = new InternetCommunicator(pm);
     ReportManager rm = new ReportManager(pm);
       
       
    public static void main(String[] args) {
       
    }
    
    
    public void uploadToServerWithPath(String filePath){
        String fileName=filePath.replace(pm.getFolderToUpdate(),"");
        ic.sendFileByPath(filePath, fileName);
        ic.terminateConnection();
    }
    public void uploadToSeverWithCustomReport(String pathToReport){
        ReportManager crm = new ReportManager(); 
        LinkedList <FileInformation> customReportToUpload = crm.parseReport(pathToReport);
        for(FileInformation f: customReportToUpload){
            ic.sendFileByFileInformation(f);
        }
        ic.terminateConnection();
    }
}
