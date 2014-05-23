package filebackupclient;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ReportManager {
    
    PrefferenceManagement pm;

     public ReportManager () {

    }
    public ReportManager(PrefferenceManagement p){
        pm=p;
     }
    
    public void writeReportToFile(LinkedList<FileInformation> listOfAllFiles,String pathToRecord){
        try (PrintWriter writer = new PrintWriter(pathToRecord)) {
            for(FileInformation f: listOfAllFiles){
                writer.printf("%s<>%d<>%s\n",f.getFilePath(),f.getFileVersion(),f.isItSucessfullyBackedUp() ? "true" : "false");
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(fileManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    public LinkedList<FileInformation> parseReport(String pathToReport){
          LinkedList<FileInformation> listParsedFromReport = new LinkedList();
          try{
              
            FileInputStream fstream = new FileInputStream(pathToReport);
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            while ((strLine = br.readLine()) != null)   {
                String [] lineElements = strLine.split("<>");
                listParsedFromReport.add(new FileInformation(lineElements[0],Integer.parseInt(lineElements[1]),Boolean.parseBoolean(lineElements[2])));
            }
            in.close();
                }catch (Exception e){
            System.err.println("Error: " + e.getMessage());
            }
          return listParsedFromReport;
     }
    
    
//*****This would be better placed in the deamon rather than here****
//    public void checkFilesMissingFromServer(String pathToLocalFiles, String pathToServerFiles){
//        Map<String, String>localFiles = new HashMap<>();
//        Map<String, String>localFilesName = new HashMap<>();
//        Map<String, String>serverFiles = new HashMap<>();
//        Map<String, String>serverFilesName = new HashMap<>();
//        LinkedList<FileInformation> localFileInformation = parseReport(pathToLocalFiles);
//        LinkedList<FileInformation> serverFileInformation = parseReport(pathToServerFiles);
//        for(FileInformation f : localFileInformation){
//            localFiles.put(f.getFilePath(), f.getFileVersion()+"");
//            localFilesName.put(f.getFilePath(), f.getFileName());
//        }
//        for(FileInformation f : serverFileInformation){
//            serverFiles.put(f.getFilePath(), f.getFileVersion()+"");
//            serverFilesName.put(f.getFilePath(),f.getFileName());
//        }
//        
//        for (String key : localFiles.keySet()) {
//            if(localFiles.containsKey(key) && !serverFiles.containsKey(key)){
//                //update 
//                ic.sendFileByPath(key, localFilesName.get(key));
//            }
//            else if(Integer.parseInt(localFiles.get(key))>Integer.parseInt(serverFiles.get(key))){
//                //update
//                ic.sendFileByPath(key, localFilesName.get(key));
//            }
//            else if(Integer.parseInt(localFiles.get(key))<Integer.parseInt(serverFiles.get(key))){
//                //update the version of file on client to match server
//                addOneToFileVersion(key);
//            }
//           else if(Integer.parseInt(localFiles.get(key))==Integer.parseInt(serverFiles.get(key))){
//                //do nothing
//            }
//           else if(!localFiles.containsKey(key) && serverFiles.containsKey(key)){
//                //will never be true since I am only transversing though the local files, so a local key will always exist
//                //prompt to update or update with a special instruction to save the file for a certain amount of times
//            }
//            
//        }   
//    }
    
    public void changeVersionOfFile(String filePath,int newVersionValue){
        LinkedList <FileInformation> allClientFiles=parseReport(pm.getPATH_TO_CLIENT_FILE_REPORT());
        for(FileInformation f: allClientFiles){
            if(f.getFilePath().equals(filePath)){
                f.setFileVersion(newVersionValue);
            }
        }
    }
      public void addOneToFileVersion(String filePath){
        LinkedList <FileInformation> allClientFiles=parseReport(pm.getPATH_TO_CLIENT_FILE_REPORT());
        for(FileInformation f: allClientFiles){
            if(f.getFilePath().equals(filePath)){
                f.setFileVersion(f.getFileVersion()+1);
            }
        }
        writeReportToFile(allClientFiles,pm.getPATH_TO_CLIENT_FILE_REPORT());
    }

}
