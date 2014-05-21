package filebackupclient;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class PrefferenceManagement {
    String PATH_TO_SETTING_FILE="Preferences.txt";
    String folderToUpdate="";
    String IPAddress="";
    String username = "";
    String port ="";
    String knownHosts="";
    String identityKey="";
    String PATH_TO_CLIENT_FILE_REPORT="clientReport.txt";

    public String getPATH_TO_CLIENT_FILE_REPORT() {
        return PATH_TO_CLIENT_FILE_REPORT;
    }

    public void setPATH_TO_CLIENT_FILE_REPORT(String PATH_TO_CLIENT_FILE_REPORT) {
        this.PATH_TO_CLIENT_FILE_REPORT = PATH_TO_CLIENT_FILE_REPORT;
    }
    
    
        public PrefferenceManagement() {
        String wholeFile="";
        String [] fileByLines;
        //PrintStream ps = new PrintStream(System.out);
        
        try{
            FileInputStream fstream = new FileInputStream(PATH_TO_SETTING_FILE);
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            while ((strLine = br.readLine()) != null)   {
                wholeFile= wholeFile.concat(strLine+"\n");
            }
            in.close();
                }catch (Exception e){
            System.err.println("Error: " + e.getMessage());
            }
           
        fileByLines = wholeFile.split("\n");
        
        
        for(String line : fileByLines){
            if (line.contains("FolderToBackup")){
                folderToUpdate = line.split(" = ")[1];
            }
            if (line.contains("User")){
                username = line.split(" = ")[1];
            }
            if (line.contains("Pass")){
                pass = line.split(" = ")[1];
            }
            if (line.contains("IPAddress")){
                IPAddress = line.split(" = ")[1];
            }   
            if (line.contains("Port")){
                port= line.split(" = ")[1];
            }   
            if (line.contains("KnownHostFile")){
                knownHosts= line.split(" = ")[1];
            }   
            if (line.contains("IdentityKeyFile")){
                identityKey= line.split(" = ")[1];
            }   
        }
       
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }


    public String getIPAddress() {
        return IPAddress;
    }

    public void setIPAddress(String IPAddress) {
        this.IPAddress = IPAddress;
    }

    public String getPATH_TO_SETTING_FILE() {
        return PATH_TO_SETTING_FILE;
        
    }

    public String getIdentityKey() {
        return identityKey;
    }

    public void setIdentityKey(String identityKey) {
        this.identityKey = identityKey;
    }

    public String getKnownHosts() {
        return knownHosts;
    }

    public void setKnownHosts(String knownHosts) {
        this.knownHosts = knownHosts;
    }

    public void setPATH_TO_SETTING_FILE(String PATH_TO_SETTING_FILE) {
        this.PATH_TO_SETTING_FILE = PATH_TO_SETTING_FILE;
    }

    public String getFolderToUpdate() {
        return folderToUpdate;
    }

    public void setFolderToUpdate(String folderToUpdate) {
        this.folderToUpdate = folderToUpdate;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getUsername() {
        return username;    
    }

    public void setUsername(String username) {
        this.username = username;
    }
    String pass = "";
    
    
    

    

}
