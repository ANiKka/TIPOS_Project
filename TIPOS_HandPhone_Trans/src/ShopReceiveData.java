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
       
        	//기록을 남길 파일을 생성합니다.
    		File file = new File("result.log");
    				
    		if(!file.isFile()){
    			try {
    				file.createNewFile();
    			} catch (IOException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    		}
    		
            System.out.println("파일 수신 작업을 시작합니다.");
            JSONObject object = new JSONObject();;
    		
    		//결과를 전송 합니다.
    		//전송폼을 생성합니다.
    		try {
    			
    			DataInputStream dis = new DataInputStream(socket.getInputStream());
    			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
    			 
    			BufferedReader in = new BufferedReader(new InputStreamReader(dis));
    			String inputline;
    			while((inputline = in.readLine()) != null)
    				System.out.println(inputline);
    			
    			/*object.put("result_code", "OK");
    			object.put("result_msg", "정상처리");
    			
    			try (OutputStreamWriter out = new OutputStreamWriter(
    			        socket.getOutputStream(), StandardCharsets.UTF_8)) {
    			    out.write(object.toString());
    			}*/    			
    			
    			String string = "{\"result_code\":OK,\"result_msg\":\"정상처리\"}";
    			PrintWriter pw = new PrintWriter(dos);
    			pw.println(string);    			
    			pw.flush();  
    			
    			dos.close();
    			dis.close();
    			
	            /*
    			//전송 결과 수신
    			InputStreamReader isr = new InputStreamReader(socket.getInputStream(), "UTF-8");
				try {
					System.out.println(isr.toString());
					object = (JSONObject)JSONValue.parseWithException(isr);
				} catch (org.json.simple.parser.ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
    			isr.close();
    			
    			//결과출력
    			System.out.println(object.toString());
    						
    			SimpleDateFormat formatter = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss", Locale.KOREA );
    			Date currentTime = new Date ( );
    			String dTime = formatter.format ( currentTime );
    			
    			String sb = " 수신 완료 내용 "
    						+ "전송 시간 : " + dTime + " \r\n";     										
    			
    			char[] paser = sb.toCharArray();
    			
    			//로그파일을 작성합니다.
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
    			System.out.println("조회가 완료 되었습니다.");    				
    			
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    }
	
}
