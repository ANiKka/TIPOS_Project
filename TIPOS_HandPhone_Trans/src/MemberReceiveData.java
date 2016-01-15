import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

public class MemberReceiveData extends Thread{

	Socket socket;
    DataInputStream dis;
    FileOutputStream fos;
    BufferedOutputStream bos;
 
    public MemberReceiveData(Socket socket) {
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
            ArrayList<String> list = new ArrayList<String>();
            
    		//결과를 전송 합니다.
    		//전송폼을 생성합니다.
    		try {
    			
    			DataInputStream dis = new DataInputStream(socket.getInputStream());
    			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
    			
    			BufferedReader in = new BufferedReader(new InputStreamReader(dis));
    			String inputline;
    			
    			while((inputline = in.readLine()) != null){
    				list.add(inputline);
    				System.out.println(inputline);
    			}		    			
    			
    			String str ="";
    			
    			//DB에 저장 합니다.        	
    			
    			/*BufferedWriter out = new BufferedWriter(new OutputStreamWriter(dos));
        		
    			if(resultCodeSave(list.get(7))){
    				str = "json_result={\"result_code\":OK,\"result_msg\":\"정상처리\"}";
    			}else{
    				str = "json_result={\"result_code\":error,\"result_msg\":\"처리오류\"}";
    			}	
        		out.write(str);
        		
        		out.flush();*/
        		
    			/*PrintWriter pw = new PrintWriter(dos);
    			pw.println(str);
    			pw.flush();*/ 
    			dos.close();
    			dis.close();
    			System.out.println("조회가 완료 되었습니다.");
    			
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}    		
    		
    		try {
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    		
    }
	
    //결과 저장합니다.
    public boolean resultCodeSave(String data){
    	
    	JSONObject json = new JSONObject();		
		String p_data = data.substring(10, data.length());
		try {
			json = (JSONObject)JSONValue.parseWithException(p_data);			 
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		    	
		String query = "Select * From e_AppInstall Where idx='"+json.get("idx")+"' ";
		String query_result = "";
		Ms_Connect mscon = new Ms_Connect();
		HashMap<String, String> map = mscon.selectQueryOne(query);
		
		if(map.size() <= 0){
			query_result = "Insert into e_AppInstall (idx, mem_id, platform, devicetoken, deviceuid, devicename, devicemodel, deviceversion, hp_num, reg_time, cus_code) values("
					+"'"+json.get("idx")+"', '"+json.get("mem_id")+"', '"+json.get("platform")+"', '"+json.get("devicetoken")+"', '"+json.get("deviceuid")+"', '"+json.get("devicename")
					+"', '"+json.get("devicemodel")+"', '"+json.get("deviceversion")+"', '"+json.get("hp_num")+"', '"+json.get("reg_time")+"', '')";			
			int result = mscon.connect_update(query_result);
			if(result == 0){
				return true;
			}else{
				return false;
			}			
		}else{
			if(map.get("idx").equals(json.get("idx"))){
				query_result = "Update e_AppInstall Set mem_id='"+json.get("mem_id")+"', platform='"+json.get("platform")+"', devicetoken='"+json.get("devicetoken")
						+"', deviceuid='"+json.get("deviceuid")+"', devicename='"+json.get("devicename")+"', devicemodel='"+json.get("devicemodel")
						+"', deviceversion='"+json.get("deviceversion")+"', hp_num='"+json.get("hp_num")+"', reg_time='"+json.get("reg_time")+"' "
						+"Where idx='"+json.get("idx")+"' ";			
				int result = mscon.connect_update(query_result);
				if(result == 0){
					return true;
				}else{
					return false;
				}
			}else{
				return false;
			}
		}
    }    
}
