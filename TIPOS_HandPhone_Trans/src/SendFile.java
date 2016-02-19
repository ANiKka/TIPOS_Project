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
        	
            System.out.println("���� ���� �۾��� �����մϴ�.");
            //String filepath = ".\\Handy\\Master";
            //File file = new File(filepath, "master.dat");            
                        
          /*  if(!file.exists()){
            	return;
            }*/
                        
            //for(int i = 0; i < file.length; i++){
            
            try {
                // ������ ���ۿ� ��Ʈ�� ����
                dos = new DataOutputStream(this.socket.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            
	            String fName = file.getName();//file.length + "," + file[i].getName();
	            
	            dos.writeUTF(fName);	            
	 
	            // ���� ������ �����鼭 ����
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
	            System.out.printf("���� (%s)�� �����Ͽ����ϴ�.\n", fName);        
           
            dos.close();
            bis.close();
            fis.close();
            System.out.println("���� ���� �۾��� �Ϸ��Ͽ����ϴ�.");
            //System.out.println("���� ������ ������ : " + file.length());
           
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
