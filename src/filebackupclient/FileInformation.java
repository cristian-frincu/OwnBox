package filebackupclient;


public class FileInformation {
    String fileName="";
    String filePath="";
    int timeOfBackup=0;
    boolean itSucessfullyBackedUp=false;
    int fileVersion=0;
    
    public static void FileInformation() {

    }

    public int getFileVersion() {
        return fileVersion;
    }

    public void setFileVersion(int fileVersion) {
        this.fileVersion = fileVersion;
    }

    FileInformation(String name,String path) {
         fileName = name;
         filePath =path;
    }
    FileInformation(String name,String path,int fileVersion) {
         fileName = name;
         filePath =path;
         this.fileVersion = fileVersion;
    }
    FileInformation(String path,int fileVersion,boolean isSynched) {
         filePath =path;
         this.fileVersion = fileVersion;
         itSucessfullyBackedUp=isSynched;
    }

    FileInformation() {
        
    }
    
    public String getFileName() {
        return fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getTimeOfBackup() {
        return timeOfBackup;
    }

    public void setTimeOfBackup(int timeOfBackup) {
        this.timeOfBackup = timeOfBackup;
    }

    public boolean isItSucessfullyBackedUp() {
        return itSucessfullyBackedUp;
    }

    public void setItSucessfullyBackedUp(boolean wasItSucessfullyBackedUp) {
        this.itSucessfullyBackedUp = wasItSucessfullyBackedUp;
    }

}
