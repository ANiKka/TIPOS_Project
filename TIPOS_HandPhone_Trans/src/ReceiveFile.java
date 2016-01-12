import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;


class ReceiveFile extends Thread {
    Socket socket;
    DataInputStream dis;
    FileOutputStream fos;
    BufferedOutputStream bos;
 
    public ReceiveFile(Socket socket) {
        this.socket = socket;
    }
 
    @Override
    public void run() {
        try {
            System.out.println("���� ���� �۾��� �����մϴ�.");
            dis = new DataInputStream(socket.getInputStream());
            	
            String filepath = "C:\\Handy\\Data";
            
            File file = new File(filepath);
            
            System.out.println(file.getAbsolutePath());
            
            if(!file.exists()){
            	System.out.println("������ ���� �մϴ�.");
            	file.mkdir();            	
            }
            
            // ���ϸ��� ���� �ް� ���ϸ� ����.
            String fName = dis.readUTF();
            System.out.println("���ϸ� " + fName + "�� ���۹޾ҽ��ϴ�.");
            //fName = fName.replaceAll("a", "b");
            
            // ������ �����ϰ� ���Ͽ� ���� ��� ��Ʈ�� ����
            File f = new File(file.getAbsolutePath(), fName);
            fos = new FileOutputStream(f, true);
            bos = new BufferedOutputStream(fos);
            System.out.println(fName + "������ �����Ͽ����ϴ�.");
 
            // ����Ʈ �����͸� ���۹����鼭 ���
            int len;
            int size = 4096;
            byte[] data = new byte[size];
            while ((len = dis.read(data)) != -1) {
                bos.write(data, 0, len);
            }
 
            bos.flush();
            bos.close();
            fos.close();
            dis.close();
            System.out.println("���� ���� �۾��� �Ϸ��Ͽ����ϴ�.");
            System.out.println("���� ������ ������ : " + f.length());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
