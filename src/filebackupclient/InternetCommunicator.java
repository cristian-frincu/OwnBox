package filebackupclient;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import java.io.PrintStream;



public class InternetCommunicator {
    private Session session= null;
    private ChannelSftp c= null;
    private String homeFolder="";
    private PrintStream ps = new PrintStream(System.out);
    
    
    public InternetCommunicator(PrefferenceManagement pm){
       String username = pm.getUsername();
       String host = pm.getIPAddress();
       String pass = pm.getPass();
       String khfile = pm.getKnownHosts();
       String identityfile = pm.getIdentityKey();
       homeFolder=pm.getFolderToUpdate();
       int port = Integer.parseInt(pm.getPort());

       JSch jsch;
       Channel channel;

       try {
           jsch = new JSch();
           session = jsch.getSession(username, host, port);
           session.setPassword(pass);
           jsch.setKnownHosts(khfile);
           jsch.addIdentity(identityfile);
           session.connect();

           channel = session.openChannel("sftp");
           channel.connect();
           c = (ChannelSftp) channel;

       } catch (Exception e) { 	e.printStackTrace();	}

  
    }
 

      public  void sendFileByPath(String absolutePath,String fileName){
      try {
           ps.println("Starting File Upload:");
           String fsrc = absolutePath , fdest = "/home/admin/BackupFromLaptop".concat("/"+fileName);
           c.put(fsrc, fdest);
           ps.println("Upload finished");
          
       } catch (Exception e) {	e.printStackTrace();  terminateConnection();	}
          
    }
      public void sendFileByFileInformation(FileInformation f){
           try {
           ps.println("Starting File Upload:");
           String fsrc = f.getFilePath() , fdest = "/home/admin/BackupFromLaptop".concat("/"+f.getFileName());
           c.put(fsrc, fdest);
           ps.println("Upload finished");
          
       } catch (Exception e) {	e.printStackTrace();  terminateConnection();	}
          
      }
    public void getTransferReport(){
         String fdest = "/home/admin/BackupFromLaptop/report.txt";
         try{
         //ps.println("Getting report");
         c.get(fdest, homeFolder.concat("/"+"report.txt"));
         //ps.println("Finished Trasnimission of report");
         terminateConnection();
         } catch (Exception e) {e.printStackTrace(); terminateConnection();}
         
    }
    
    public void terminateConnection(){
        c.disconnect();
        session.disconnect();
        ps.println("Connection Terminated");
    }
    
}
