import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;


public class SendFile extends Thread {
	private Socket socket;
    private DataOutputStream dos;
    private FileInputStream fis;
    private BufferedInputStream bis;
    private File file;
 
    public SendFile(Socket socket, File file) {
        this.socket = socket;
        this.file = file;
       
    }
        
    @Override
    public void run() {
        try {
        	
            System.out.println("파일 전송 작업을 시작합니다.");
            //String filepath = ".\\Handy\\Master";
            //File file = new File(filepath, "master.dat");            
                        
          /*  if(!file.exists()){
            	return;
            }*/
                        
            //for(int i = 0; i < file.length; i++){
            
            try {
                // 데이터 전송용 스트림 생성
                dos = new DataOutputStream(this.socket.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            
	            String fName = file.getName();//file.length + "," + file[i].getName();
	            
	            dos.writeUTF(fName);	            
	 
	            // 파일 내용을 읽으면서 전송
	            //File f = new File(fName);
	            fis = new FileInputStream(file);
	            bis = new BufferedInputStream(fis);
	 
	            int len;
	            int size = 4096;
	            byte[] data = new byte[size];
	            while ((len = bis.read(data)) != -1) {
	                dos.write(data, 0, len);
	            }
	            
	            dos.flush();
	            System.out.printf("파일 (%s)을 전송하였습니다.\n", fName);        
           
            dos.close();
            bis.close();
            fis.close();
            System.out.println("파일 전송 작업을 완료하였습니다.");
            //System.out.println("보낸 파일의 사이즈 : " + file.length());
           
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
