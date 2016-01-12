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
            System.out.println("파일 수신 작업을 시작합니다.");
            dis = new DataInputStream(socket.getInputStream());
            	
            String filepath = "C:\\Handy\\Data";
            
            File file = new File(filepath);
            
            System.out.println(file.getAbsolutePath());
            
            if(!file.exists()){
            	System.out.println("폴더를 생성 합니다.");
            	file.mkdir();            	
            }
            
            // 파일명을 전송 받고 파일명 수정.
            String fName = dis.readUTF();
            System.out.println("파일명 " + fName + "을 전송받았습니다.");
            //fName = fName.replaceAll("a", "b");
            
            // 파일을 생성하고 파일에 대한 출력 스트림 생성
            File f = new File(file.getAbsolutePath(), fName);
            fos = new FileOutputStream(f, true);
            bos = new BufferedOutputStream(fos);
            System.out.println(fName + "파일을 생성하였습니다.");
 
            // 바이트 데이터를 전송받으면서 기록
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
            System.out.println("파일 수신 작업을 완료하였습니다.");
            System.out.println("받은 파일의 사이즈 : " + f.length());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
