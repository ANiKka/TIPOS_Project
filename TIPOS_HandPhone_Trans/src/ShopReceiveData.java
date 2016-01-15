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
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.StringTokenizer;

import javax.net.ssl.HttpsURLConnection;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

public class ShopReceiveData extends Thread{

	Socket socket;
    DataInputStream dis;
    FileOutputStream fos;
    BufferedOutputStream bos;
     
    String app = "appinstall.php";
    String mem = "member.php";
    String ord = "order.php";
    
    String gubun = "";
    String result_data = "";
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
            ArrayList<String> list = new ArrayList<String>();
            
    		//결과를 전송 합니다.
    		//전송폼을 생성합니다.
    		try {
    			DataInputStream dis = new DataInputStream(socket.getInputStream());
    			BufferedReader in = new BufferedReader(new InputStreamReader(dis));
    			
    			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
    			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(dos));
    			
    			String inputline;    			
    			int contentLength = 0;
    			
    			while((inputline = in.readLine()) != null){
    						
        			if( inputline.contains("GET")){		
        				System.out.println("전송합니다.");
        				out.write("{\"result_code\":\"OK\", \"result_msg\":\"정상처리\" }");
                		out.flush();
        				
        				dis.close();
        				dos.close();
        				socket.close();
        				return;
        			}        	
        			
        			if( inputline.startsWith("POST")){        				
        				if(inputline.contains(app)){
        					gubun=app;
        				}else if(inputline.contains(mem)){
        					gubun=mem;
        				}else if(inputline.contains(ord)){
        					gubun=ord;
        				}
        				System.out.println(gubun);
        				continue;
        			}
        			
                    if (inputline.startsWith("Content-Length: ")) {  
                        contentLength = Integer.parseInt(inputline.substring(16));   //Content-Length 값 할당
                        System.out.println(contentLength);
                        continue;  
                    }
                      
                    //웹페이지 정보에서 <body> 정보를  Parsing  
                    if (inputline.startsWith("json_data=")) {                       //body 내용이 시작 될 때  
                        /*for(int i=0; i<contentLength;i++){     //Content-Length 만큼 정보를 가져옴  
                            char ch = (char)in.read();  
                            result_data += String.valueOf(ch);       //data 값에 String 혛 변환 후 할당  
                        }*/
                    	result_data = inputline;//.substring(inputline.indexOf("json_data="), contentLength);                    	
                        System.out.println(result_data);
                        break;
                    }
                }  
    			    			    			
    			boolean result = true; 
    			//DB에 저장 합니다.
    			if(gubun.contains(app)){    				
    				System.out.println("앱설치현황 저장합니다.");
					if(!resultCodeSave(result_data)){
        				result = false;
        			}
    			}else if(gubun.contains(mem)){
    				System.out.println("멤버 저장합니다.");    				
					if(!resultMemberSave(result_data)){
        				result = false;
        			}
    			}else if(gubun.contains(ord)){
    				System.out.println("주문저장합니다.");
					if(!resultOrderSave(result_data)){
						result = false;       				        				
        			}
    			}			
        		    			
        		//결과 전송합니다.
    			if(result){
    				out.write("{\"result_code\":\"OK\", \"result_msg\":\""+URLEncoder.encode("정상처리", "UTF-8")+"\" }");    				
            		out.flush();
            		System.out.println("정상처리 완료");
    			}else{
    				out.write("{\"result_code\":\"FAIL\", \"result_msg\":\""+URLEncoder.encode("처리오류", "UTF-8")+"\" }");
            		out.flush();
            		System.out.println("처리 오류발생");
    			}
        		
        		dis.close();
        		dos.close();
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
						
			//매장회원을 검색 해서 매칭합니다.
			query = "Select * From Customer_Info Where replace(cus_mobile, '-', '')='"+json.get("hp_num")+"' or replace(cus_tel, '-', '')='"+json.get("hp_num")+"' Order by Edit_Date DESC";
			ArrayList<HashMap<String, String>> map_cuscode = mscon.connection(query);
			String cus_code = "";
			String connect_add5 = "0";
			System.out.println(map_cuscode.size());
			switch(map_cuscode.size()){
			case 0:  //매칭안됨
				break;
			case 1:
				cus_code = map_cuscode.get(0).get("Cus_Code");
				connect_add5 = "1"; //매칭완료
				break;	
			default:
				connect_add5 = "2";  //중복발생
				break;
			}
			
			query_result = "Insert into e_AppInstall (idx, mem_id, platform, devicetoken, deviceuid, devicename, devicemodel, deviceversion, hp_num, reg_time, cus_code, connect_yn) values("
					+"'"+json.get("idx")+"', '"+json.get("mem_id")+"', '"+json.get("platform")+"', '"+json.get("devicetoken")+"', '"+json.get("deviceuid")+"', '"+json.get("devicename")
					+"', '"+json.get("devicemodel")+"', '"+json.get("deviceversion")+"', '"+json.get("hp_num")+"', '"+json.get("reg_time")+"', '"+cus_code+"', '"+connect_add5+"')";
			int result = mscon.connect_update(query_result);
			if(result == 0){
				return true;
			}else{
				return false;
			}
		}else{
			if(map.get("idx").equals(json.get("idx"))){
				
				//매장회원을 검색 해서 매칭합니다.
				query = "Select * From Customer_Info Where replace(cus_mobile, '-', '')='"+json.get("hp_num")+"' or replace(cus_tel, '-', '')='"+json.get("hp_num")+"' Order by Edit_Date DESC";
				ArrayList<HashMap<String, String>> map_cuscode = mscon.connection(query);
				String cus_code = "";
				String connect_add5 = "";
				System.out.println(map_cuscode.size());
				switch(map_cuscode.size()){
				case 0:  //매칭안됨
					connect_add5 = ", conect_yn='0' "; //중복발생
					break;
				case 1:
					cus_code = ", cus_code='"+map_cuscode.get(0).get("Cus_Code")+"' ";
					connect_add5 = ", conect_yn='1' "; //매칭완료
					break;	
				default:
					connect_add5 = ", conect_yn='2' "; //중복발생					
					break;
				}
								
				query_result = "Update e_AppInstall Set mem_id='"+json.get("mem_id")+"', platform='"+json.get("platform")+"', devicetoken='"+json.get("devicetoken")
						+"', deviceuid='"+json.get("deviceuid")+"', devicename='"+json.get("devicename")+"', devicemodel='"+json.get("devicemodel")
						+"', deviceversion='"+json.get("deviceversion")+"', hp_num='"+json.get("hp_num")+"', reg_time='"+json.get("reg_time")+"' "+cus_code+connect_add5
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
    
    
    //결과를 저장합니다.
    public boolean resultMemberSave(String data){
    	
    	JSONObject json = new JSONObject();		
		String p_data = data.substring(10, data.length());
		try {
			json = (JSONObject)JSONValue.parseWithException(p_data);			 
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		    	
		System.out.println(json.toString());
		
		String query = "Select * From e_Member Where mem_id='"+json.get("mem_id")+"' ";
		String query_result = "";
		Ms_Connect mscon = new Ms_Connect();
		HashMap<String, String> map = mscon.selectQueryOne(query);
		//임시로 테이블 생성합니다.
		if(map.size() <= 0){
			
			//매장회원을 검색 해서 매칭합니다.
			String cus_code = "";
			String connect_add5 = "0";
			switch(json.get("add1").toString().trim()){
			case "신규회원신청":
				query = "Select ISNull(max(cus_code), '23000000')+1 as cus_code From Customer_Info Where LEN(Cus_Code) = 8 and Left(Cus_Code, 2)='23' ";
				HashMap<String, String> map_newmem = mscon.selectQueryOne(query);
				if(map_newmem.size() > 0){
					String[] query_won = new String[2]; 
					query = "Insert Into Customer_Info (Cus_Code,Cus_Name,Cus_Gubun,Cus_Class,Cus_Tel,Cus_Mobile,Cus_BirYN,"
							+ "Cus_BirDay,Cus_RealDay,Pur_Pri,Cus_TPoint,Cus_Point,Cus_UsePoint,Dec_Pri,Vis_Count,Gift_Count,Edit_Check,"
							+ "Zip_Code,Address1,Address2,Bigo,Cus_Date,Vis_Date,Write_Date,Edit_Date,Writer,EDitor,HPSend_YN,"
							+ "Office_Name,Office_Num,Owner_Name,Uptae,JongMok,Address,Credit_YN,Cus_Use,Tax_Use,cPoint_Use,"
							+ "TaxBill_YN,Email,TAX_Print_Use,TAX_AUTO_USE,TAX_Gubun,TAX_Number)" 
							+ "Values ('"+map_newmem.get("cus_code")+"', '"+json.get("name")+"', '정회원','1','"+json.get("tel")+"','"+json.get("hp")+"','1','','','0','0','0','0','0','0','0','1',"
							+ "'"+json.get("zipcode")+"','"+json.get("addr1")+"','"+json.get("addr2")+"','',"
							+ "'2016-01-14','','2016-01-14','2016-01-14','tips','tips','1','','','','','','','1','1','1','1','0','','0','0', '0','' ) ";
					query_won[0] = query;
					query_won[1] = "Insert Into Customer_History(Regdate,q_sql,gubun)"
										+"Values(convert(datetime,getdate(),120), '"+query+"',' 쇼핑몰관리')";					
					mscon.connect_update(query_won);		
					cus_code = map_newmem.get("cus_code");
					connect_add5 = "1";
				}
				break;
			case "기존회원연동":
				query = "Select * From Customer_Info Where replace(cus_mobile, '-', '')='"+json.get("hp").toString().replace("-", "")+"' or replace(cus_tel, '-', '')='"+json.get("hp").toString().replace("-", "")+"' Order by Edit_Date";
				ArrayList<HashMap<String, String>> map_cuscode = mscon.connection(query);		
				switch(map_cuscode.size()){
				case 0:  //매칭안됨
					break;
				case 1:
					cus_code = map_cuscode.get(0).get("Cus_Code");
					connect_add5 = "1"; //매칭완료
					break;	
				default:
					connect_add5 = "2";  //중복발생
					break;
				}
				break;
			case "연동안함":
				break;
			}
			
			query_result = "Insert into e_Member values("
					+"'', '"+json.get("mem_id")+"', '"+json.get("pwd")+"', '"+json.get("name")+"', '"+json.get("memlv")+"', '"+json.get("hp")+"',"
					+ " '"+json.get("tel")+"', '"+json.get("fax")+"', '"+json.get("nickname")+"', '"+json.get("sex")+"', '"+json.get("age")+"', '"+json.get("email")+"', '"+json.get("zipcode")+"',"
					+ " '"+json.get("addr1")+"', '"+json.get("addr2")+"', '"+json.get("birthday")+"', '"+json.get("birthday_type")+"', '"+json.get("marry_yn")+"', '"+json.get("biz_num")+"', '"+json.get("sangho")+"',"
					+ " '"+json.get("homepage")+"', '"+json.get("add1")+"', '"+json.get("add2")+"', '"+json.get("add3")+"', '"+json.get("add4")+"', '"+json.get("add5")+"', '"+json.get("add6")+"',"
					+ " '"+json.get("add7")+"', '"+json.get("add8")+"', '"+json.get("add9")+"', '"+json.get("add10")+"', '"+cus_code+"', '"+connect_add5+"' )";			
			int result = mscon.connect_update(query_result);
			if(result == 0){
				return true;
			}else{
				return false;
			}			
		}else{
			if(map.get("mem_id").equals(json.get("mem_id"))){
				query_result = "Update e_Member Set pwd='"+json.get("pwd")+"', name='"+json.get("name")+"', memlv='"+json.get("memlv")+"', hp='"+json.get("hp")
						+"', tel='"+json.get("tel")+"', fax='"+json.get("fax")+"', nickname='"+json.get("nickname")+"', sex='"+json.get("sex")+"', age='"+json.get("age")
						+"', email='"+json.get("email")+"', zipcode='"+json.get("zipcode")+"', addr1='"+json.get("addr1")+"', addr2='"+json.get("addr2")+"', birthday='"+json.get("birthday")
						+"', birthday_type='"+json.get("birthday_type")+"', marry_yn='"+json.get("marry_yn")+"', biz_num='"+json.get("biz_num")+"', sangho='"+json.get("sangho")
						+"', homepage='"+json.get("homepage")+"', add1='"+json.get("add1")+"', add2='"+json.get("add2")+"', add3='"+json.get("add3")+"', add4='"+json.get("add4")
						+"', add5='"+json.get("add5")+"', add6='"+json.get("add6")+"', add7='"+json.get("add7")+"', add8='"+json.get("add8")+"', add9='"+json.get("add9")
						+"', add10='"+json.get("add10")+"' Where mem_id='"+json.get("mem_id")+"' ";			
								
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
    
    
  //결과를 저장합니다.
    public boolean resultOrderSave(String data){
    	
    	JSONObject json = new JSONObject();		
		String p_data = data.substring(10, data.length());
		try {
			json = (JSONObject)JSONValue.parseWithException(p_data);			 
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		    	
		System.out.println(json.toString());
		return false;
    }
}
