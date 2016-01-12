import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

import org.json.simple.JSONObject;

public class ShopReceiveData extends Thread{

	Socket socket;
    DataInputStream dis;
    FileOutputStream fos;
    BufferedOutputStream bos;
 
    public ShopReceiveData(Socket socket) {
        this.socket = socket;
    }
 
    @Override
    public void run() {
       
        	//����� ���� ������ �����մϴ�.
    		File file = new File("result.log");
    				
    		if(!file.isFile()){
    			try {
    				file.createNewFile();
    			} catch (IOException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    		}
    		
            System.out.println("���� ���� �۾��� �����մϴ�.");
            JSONObject object = new JSONObject();;
    		
    		//����� ���� �մϴ�.
    		//�������� �����մϴ�.
    		try {
    			
    			DataInputStream dis = new DataInputStream(socket.getInputStream());
    			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
    			 
    			BufferedReader in = new BufferedReader(new InputStreamReader(dis));
    			String inputline;
    			while((inputline = in.readLine()) != null)
    				System.out.println(inputline);
    			
    			/*object.put("result_code", "OK");
    			object.put("result_msg", "����ó��");
    			
    			try (OutputStreamWriter out = new OutputStreamWriter(
    			        socket.getOutputStream(), StandardCharsets.UTF_8)) {
    			    out.write(object.toString());
    			}*/    			
    			
    			String string = "{\"result_code\":OK,\"result_msg\":\"����ó��\"}";
    			PrintWriter pw = new PrintWriter(dos);
    			pw.println(string);    			
    			pw.flush();  
    			
    			dos.close();
    			dis.close();
    			
	            /*
    			//���� ��� ����
    			InputStreamReader isr = new InputStreamReader(socket.getInputStream(), "UTF-8");
				try {
					System.out.println(isr.toString());
					object = (JSONObject)JSONValue.parseWithException(isr);
				} catch (org.json.simple.parser.ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
    			isr.close();
    			
    			//������
    			System.out.println(object.toString());
    						
    			SimpleDateFormat formatter = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss", Locale.KOREA );
    			Date currentTime = new Date ( );
    			String dTime = formatter.format ( currentTime );
    			
    			String sb = " ���� �Ϸ� ���� "
    						+ "���� �ð� : " + dTime + " \r\n";     										
    			
    			char[] paser = sb.toCharArray();
    			
    			//�α������� �ۼ��մϴ�.
    			OutputStreamWriter bos = new OutputStreamWriter(new FileOutputStream(file, true), "euc-kr");					
    			StringBuffer result_str = new StringBuffer();
    			
    			for(char str : paser){
    				bos.write(str);
    				result_str.append(str);
    			}	
    			System.out.println(result_str);
    			
    			bos.write('\r');
    			bos.write('\n');
    			
    			bos.close();*/
    			System.out.println("��ȸ�� �Ϸ� �Ǿ����ϴ�.");    				
    			
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    }
	
}
