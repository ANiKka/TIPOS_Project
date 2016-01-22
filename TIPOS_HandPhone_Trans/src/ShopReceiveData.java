import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ShopReceiveData extends Thread {

	private Socket socket;
	
    private String app = "appinstall";
    private String mem = "member";
    private String ord = "order";
    
    private String gubun = "";       //���ű���  ���,�ֹ�,�ۼ�ġ        
    private Ms_Connect mscon;
    
    //ȸ�� ����� ��
    private String cus_code = "";  //ȸ���ڵ�
	private String connect_add5 = "0";  //��������
	private String phone_number="";  //ȸ�� ��ȭ��ȣ
	private String mem_id = "";  //ȸ�� ���̵�  
	private String result_str = "";  //���۷αױ��	
		
	//ȯ�漳�� ����
	private String memStartNum;
	private Properties config;	
	
	//��Ű
	private String shop_key = "";
	
    public ShopReceiveData(Socket socket) {
        this.socket = socket;
        mscon = new Ms_Connect();        
        config = new Properties();
        try {
			config.load(new FileInputStream(new File("config.dat")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		memStartNum = config.getProperty("MEMNUM");
		
		String query = "Select Online_Key from office_user ";
		HashMap<String, String> map = mscon.selectQueryOne(query); 
		shop_key = map.get("Online_Key");
		System.out.println(shop_key);
		
    }
 
    @Override
    public void run() {
       
    	//�α������� �����մϴ�. shop_log ���� �����մϴ�. 
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String date = sdf.format(today);
				
		File file_dir = new File("Shop_Log");
		if(!file_dir.exists()){
			//���丮 ����
			file_dir.mkdir();
		}
		
    	//����� ���� ������ �����մϴ�.
		File file = new File("Shop_Log/shop_"+date+".log");	
		if(!file.exists()){
			try {				
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		sdf.applyPattern( "yyyy-MM-dd HH:mm:ss");		
		result_str = "���θ� ������ ���� "+sdf.format(today)+"\r\n";
									
		Properties result_data = new Properties();    	
            
    	//����� ���� �մϴ�.
    	//�������� �����մϴ�. ���۳����� �����մϴ�.
		try {
			
			String data ="OK";			
	        System.err.println("client connect");

	        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));
	         
	        out.write("HTTP/1.0 200 OK\r\n");
	        out.write("Content-Type: text/html\r\n");
	        out.write("Content-Length: "+data.length()+"\r\n");
	        out.write("\r\n");

	        out.write(data);
	        out.flush();
	        	        
	        result_data.load(in);
	        System.out.println(result_data.toString());	        
	        result_str += result_data.getProperty("json_data")+"\r\n";
	        
	        out.close();
	        in.close();
	        socket.close();
	        
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//��� ���۵Ǿ����� Ȯ�� �մϴ�.
		gubun = result_data.getProperty("POST");
		String tran_data = result_data.getProperty("json_data"); 
		
		boolean result = true;
		//DB�� ���� �մϴ�.
		if(gubun.contains(app)){    				
			System.out.println("�ۼ�ġ��Ȳ �����մϴ�.");
			result_str += "�ۼ�ġ��Ȳ �����մϴ�.\r\n";
			if(!resultCodeSave(tran_data)){
				result	= false;
			}
		}else if(gubun.contains(mem)){
			System.out.println("��� �����մϴ�.");
			result_str += "��� �����մϴ�.\r\n";
			if(!resultMemberSave(tran_data)){
				result = false;
			}
		}else if(gubun.contains(ord)){
			System.out.println("�ֹ������մϴ�.");
			result_str += "�ֹ������մϴ�.\r\n";
			if(!resultOrderSave(tran_data)){
				result = false;       				        				
			}
		}
		
		result_str += "�������� : ";
		if(result){
			result_str += "����\r\n";
		}else{
			result_str += "����\r\n";
		}
		    	    	           	    	
		System.out.println("===> ���� ���� �۾��� �Ϸ��մϴ�.");
		result_str += "===> ���� ���� �۾��� �Ϸ��մϴ�.";
		//�α������� �ۼ��մϴ�.
		OutputStreamWriter bos;
		try {
			bos = new OutputStreamWriter(new FileOutputStream(file, true), "euc-kr");
			char[] paser = result_str.toCharArray();
			for(char str : paser){
				bos.write(str);
			}
			bos.write("\r\n");
			bos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
    //��� �����մϴ�.
    public boolean resultCodeSave(String data){
    	
    	JSONObject json = new JSONObject();
    	//Properties temp = new Properties();
    	
		try {
			json = (JSONObject)JSONValue.parseWithException(data);			 
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ArrayList<String> query_list = new ArrayList<String>();  //���� ����		
		String query_result =  "";
		
		//���� �ۼ�ġ������ �ִ��� ���� Ȯ�� �մϴ�.
		String query = "Select * From e_AppInstall Where idx='"+json.get("idx")+"' ";		
		HashMap<String, String> map = mscon.selectQueryOne(query);
		
		if(map.size() <= 0){ //�۽űԼ�ġȸ��
						
			ArrayList<HashMap<String, String>> map_cuscode = new ArrayList<HashMap<String, String>>();			
			if(config.getProperty("CONNECT").equals("1")){ //ȸ������ �ɼ� ��� �� 				
				//����ȸ���� �˻� �ؼ� ��Ī�մϴ�.
				query = "Select * From Customer_Info Where replace(cus_mobile, '-', '')='"+json.get("hp_num")+"' or replace(cus_tel, '-', '')='"+json.get("hp_num")+"' Order by Edit_Date DESC";
				map_cuscode = mscon.connection(query);
			}
			
			System.out.println(map_cuscode.size());
			switch(map_cuscode.size()){
			case 0:  //��Ī�ȵ�
				connect_add5 = "0"; //��Ī�ȵ�
				break;
			case 1:
				cus_code = map_cuscode.get(0).get("Cus_Code");
				phone_number = json.get("hp_num").toString();
				connect_add5 = "1"; //��Ī�Ϸ�

				query_result = "Update Customer_Info Set e_AppInstall_YN='1', e_PhoneNumber='"+phone_number+"' Where Cus_Code='"+cus_code+"' ";
				query_list.add(query_result);
				break;	
			default:
				connect_add5 = "2";  //�ߺ��߻�
				break;
			}
			
			query_result = "Insert into e_AppInstall (idx, mem_id, platform, devicetoken, deviceuid, devicename, devicemodel, deviceversion, hp_num, reg_time, cus_code, connect_yn) values("
					+"'"+json.get("idx")+"', '"+json.get("mem_id")+"', '"+json.get("platform")+"', '"+json.get("devicetoken")+"', '"+json.get("deviceuid")+"', '"+json.get("devicename")
					+"', '"+json.get("devicemodel")+"', '"+json.get("deviceversion")+"', '"+json.get("hp_num")+"', '"+json.get("reg_time")+"', '"+cus_code+"', '"+connect_add5+"')";
			query_list.add(query_result);
			
						
			int result = mscon.connect_update(query_result);
			if(result == 0){
				return true;
			}else{
				return false;
			}
		}else{ //������ġȸ��
			
			if(map.get("idx").equals(json.get("idx"))){
				
				//���� ��ġȸ���� ��� ��ٽ� ��Ī Ȯ�� ���մϴ�.
				/*ArrayList<HashMap<String, String>> map_cuscode = new ArrayList<HashMap<String, String>>();
				if(config.getProperty("CONNECT").equals("1")){
					//����ȸ���� �˻� �ؼ� ��Ī�մϴ�.
					query = "Select * From Customer_Info Where replace(cus_mobile, '-', '')='"+json.get("hp_num")+"' or replace(cus_tel, '-', '')='"+json.get("hp_num")+"' Order by Edit_Date DESC";
					map_cuscode = mscon.connection(query);
				}
				
				System.out.println(map_cuscode.size());
				switch(map_cuscode.size()){
				case 0:  //��Ī�ȵ�
					connect_add5 = ", connect_yn='0' "; //��Ī�ȵ�
					break;
				case 1:
					cus_code = ", cus_code='"+map_cuscode.get(0).get("Cus_Code")+"' ";
					connect_add5 = ", connect_yn='1' "; //��Ī�Ϸ�
					break;	
				default:
					connect_add5 = ", connect_yn='2' "; //�ߺ��߻�					
					break;
				}*/
								
				query_result = "Update e_AppInstall Set mem_id='"+json.get("mem_id")+"', platform='"+json.get("platform")+"', devicetoken='"+json.get("devicetoken")
						+"', deviceuid='"+json.get("deviceuid")+"', devicename='"+json.get("devicename")+"', devicemodel='"+json.get("devicemodel")
						+"', deviceversion='"+json.get("deviceversion")+"', hp_num='"+json.get("hp_num")+"', reg_time='"+json.get("reg_time")+"' "
						+"Where idx='"+json.get("idx")+"' ";	
				
				int result = mscon.connect_update(query_result);				
				if(result == 0){				
					//���� ����
					return true;					
				}else{
					//���� ����
					return false;
				}
				
			}else{
				return false;
			}
		}		
    }    
    
    
    //����� �����մϴ�.
    public boolean resultMemberSave(String data){
    	
    	JSONObject json = new JSONObject();
    	HashMap<String, Object> push_list = new HashMap<String, Object>();   //Ǫ�� ���� ����
		
		try {
			json = (JSONObject)JSONValue.parseWithException(data);			 
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		    	
		System.out.println(json.toString());
		
		String query = "Select * From e_Member Where mem_id='"+json.get("mem_id")+"' ";
		String query_result = "";
		mem_id = json.get("mem_id").toString();
		
		HashMap<String, String> map = mscon.selectQueryOne(query);
		//�ӽ÷� ���̺� �����մϴ�.
		if(map.size() <= 0){ // �űԿ¶���ȸ��
			
			if(config.getProperty("CONNECT").equals("1")){  //�ɼ�����
				//����ȸ���� �˻� �ؼ� ��Ī�մϴ�.
				//String cus_code = "";
				//String connect_add5 = "0";
				switch(json.get("add1").toString().trim()){
				case "�űԹ߱޽�û":
					//��ȣ �����մϴ�. ȯ�漳������ ������ ��ȣ�� ȣ�� �� �׹�ȣ�� ������ ��ȣ �ڸ� �̾ �����մϴ�.
					query = "Select ISNull(max(cus_code), '"+memStartNum+"000000')+1 as cus_code From Customer_Info Where LEN(Cus_Code) = 8 and Left(Cus_Code, 2)='"+memStartNum.trim()+"' ";
					HashMap<String, String> map_newmem = mscon.selectQueryOne(query);
					
					if(map_newmem.size() > 0){
						String[] query_won = new String[2];
						query = "Insert Into Customer_Info (Cus_Code,Cus_Name,Cus_Gubun,Cus_Class,Cus_Tel,Cus_Mobile,Cus_BirYN,"
								+ "Cus_BirDay,Cus_RealDay,Pur_Pri,Cus_TPoint,Cus_Point,Cus_UsePoint,Dec_Pri,Vis_Count,Gift_Count,Edit_Check,"
								+ "Zip_Code,Address1,Address2,Bigo,Cus_Date,Vis_Date,Write_Date,Edit_Date,Writer,EDitor,HPSend_YN,"
								+ "Office_Name,Office_Num,Owner_Name,Uptae,JongMok,Address,Credit_YN,Cus_Use,Tax_Use,cPoint_Use,"
								+ "TaxBill_YN,Email,TAX_Print_Use,TAX_AUTO_USE,TAX_Gubun,TAX_Number, e_AppInstall_YN, e_Member_YN, e_PushSMS_YN, e_PhoneNumber)" 
								+ " Values('"+map_newmem.get("cus_code")+"', '"+json.get("name")+"', '��ȸ��','1','"+json.get("tel")+"','"+json.get("hp")+"','1','','','0','0','0','0','0','0','0','1',"
								+ "'"+json.get("zipcode")+"','"+json.get("addr1")+"','"+json.get("addr2")+"','',"
								+ "'2016-01-14','','2016-01-14','2016-01-14','tips','tips','1','','','','','','','1','1','1','1','0','','0','0', '0', '', '', '1', '1', '') ";
						query_won[0] = query;
					
						query_won[1] = "Insert Into Customer_History(Regdate,q_sql,gubun)"
											+"Values(convert(datetime,getdate(),120), '"+query.replace("'", "`")+"', '���θ�����')";					
						
						mscon.connect_update(query_won);						
						cus_code = map_newmem.get("cus_code");
						connect_add5 = "1";						
					}
								
					if(config.getProperty("EVENTCODE").equals("1")){
						//ȯ�漳���� �ɼ� üũ�ÿ��� Ǫ�ð� ���� �˴ϴ�.
						String result_data = "";
						try {
							result_data = "api_key="+shop_key.toString()+"&mem_id="+mem_id+"&add2="+cus_code+"&add4="+URLEncoder.encode("������", "UTF-8");
						} catch (UnsupportedEncodingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}				
						
						//�ű�ȸ�� ��� ������ ���θ��� �ø��ϴ�.
						new Trans_ShopAPI().setMember_Update(result_data, shop_key);							
						
						//������� �ۼ�ġ ȸ�� ��� ����
						push_list.put("Title", "�ű�ȸ���� ���� ��� �Ǿ����ϴ�. ");
						push_list.put("Message", "�ű�ȸ���� ���� ��� �Ǿ����ϴ�. ");
						push_list.put("Link", "");
						push_list.put("Img_Url", "");
						push_list.put("Event", config.get("NEWCODE"));
						
						push_list.put("Mem_Id", "");
						push_list.put("Mem_Only", "ALL");
						push_list.put("Hp", json.get("hp").toString().replace("-", ""));
						
						//�Ϸ� ������ ȸ������ Ǫ�� �����մϴ�.
						new Trans_ShopAPI().setPushSubimt(push_list, shop_key);
					}
					
					break;
				case "����ȸ������":	
					query = "Select * From Customer_Info Where replace(cus_mobile, '-', '')='"+json.get("hp").toString().replace("-", "")+"' or replace(cus_tel, '-', '')='"+json.get("hp").toString().replace("-", "")+"' Order by Edit_Date";
					ArrayList<HashMap<String, String>> map_cuscode = mscon.connection(query);		
					String str = config.getProperty("FAILCODE");
					switch(map_cuscode.size()){
					case 0:  //��Ī�ȵ�						
						break;
					case 1:
						cus_code = map_cuscode.get(0).get("Cus_Code");
						connect_add5 = "1"; //��Ī�Ϸ�
						str = config.getProperty("OKCODE");
						break;	
					default:
						connect_add5 = "2";  //�ߺ��߻�
						
						break;
					}
					
					if(config.getProperty("EVENTCODE").equals("1")){
						
						String result_co = "";
						try {
							result_co = "api_key="+shop_key.toString()+"&mem_id="+mem_id+"&add2="+cus_code+"&add4="+URLEncoder.encode("������", "UTF-8");
						} catch (UnsupportedEncodingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}				
						
						//�ű�ȸ�� ��� ������ ���θ��� �ø��ϴ�.
						new Trans_ShopAPI().setMember_Update(result_co, shop_key);		
						
						//������� �ۼ�ġ ȸ�� ��� ����					
						push_list.put("Title", "����ȸ�� ������ ���� ���� �Ǿ����ϴ�. ");
						push_list.put("Message", "����ȸ�� ������ ���� ���� �Ǿ����ϴ�. ");
						push_list.put("Link", "");
						push_list.put("Img_Url", "");
						
						push_list.put("Event", str);
						
						push_list.put("Mem_Id", "");
						push_list.put("Mem_Only", "ALL");
						push_list.put("Hp",  json.get("hp").toString().replace("-", ""));	
						
						//�Ϸ� ������ ȸ������ Ǫ�� �����մϴ�.
						new Trans_ShopAPI().setPushSubimt(push_list, shop_key);
					}
					break;
				case "��������":	
					break;
				}
				
			}//�����ڵ�������� ��
			
			query_result = "Insert into e_Member values("
					+"'"+json.get("mem_idx")+"', '"+json.get("mem_id")+"', '"+json.get("pwd")+"', '"+json.get("name")+"', '"+json.get("memlv")+"', '"+json.get("hp")+"',"
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
			
		}else{ //�����¶���ȸ��
			
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
		}
    }
    
    
    //����� �����մϴ�.
    public boolean resultOrderSave(String data){
    	
    	ArrayList<String> orderquery_list = new ArrayList<String>();  //���� ����Ʈ �Դϴ�.
    	JSONObject json = new JSONObject();
		try {
			json = (JSONObject)JSONValue.parseWithException(data);	 
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//���� �ֹ����� Ȯ�� �մϴ�.
		String order_idx = (String)json.get("order_idx");		
		String query = "Select * From e_OrderList Where idx='"+order_idx+"' ";		
		HashMap<String, String> map = mscon.selectQueryOne(query);
		if(map.size() > 0){ // �ֹ��� �����Դϴ�.						
			
		}else{  //�űԵ���Դϴ�.
			
			//�ֹ��� ����Ʈ �����ϱ�
			String query_list = "Insert Into e_OrderList (";
			String query_value = "Values(";
			
			for(Object keymap:json.keySet()){			
				if(!keymap.toString().equals("goods_info")){
					query_list += keymap.toString()+", ";
					try{
						query_value += " '"+json.get(keymap).toString()+"', ";
					}catch(NullPointerException e){
						query_value += " '', ";
					}
				}
			}
			query_list = query_list.substring(0, query_list.length()-2)+") ";
			query_value = query_value.substring(0, query_value.length()-2)+") ";
			
			//�ֹ��� ����(orderList) ���
			orderquery_list.add(query_list+query_value);
			
			//JSONArray goods_info = (JSONArray)json.get("goods_info");
			//JSONArray goods_info = new JSONArray(json.get("goods_info").toString());		
			JSONArray goods_info = new JSONArray();
			try {
				goods_info = (JSONArray)JSONValue.parseWithException(json.get("goods_info").toString());	 
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
			System.out.println(query_list+query_value+" Count --> "+ goods_info.size());		
			
			String egoods_key = "Insert Into e_OrderGoods ( ";
			String egoods_value =  " Values( ";
			for(int i=0; i < goods_info.size(); i++){			
				try {
					JSONObject temp = (JSONObject)JSONValue.parseWithException(goods_info.get(i).toString());
					egoods_key += order_idx+", ";
					egoods_value += "'"+json.get("order_idx")+"', ";
					
					for(Object keymap:temp.keySet()){		
						egoods_key += keymap.toString()+", ";
						try{
							egoods_value += " '"+json.get(keymap).toString()+"', ";
						}catch(NullPointerException e){
							egoods_value += " '', ";
						}
					}
					
					egoods_key = egoods_key.substring(0, egoods_key.length()-2)+") ";
					egoods_value = egoods_value.substring(0, egoods_value.length()-2)+") ";
					
					orderquery_list.add(egoods_key+egoods_value);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}			
			}
			
			//��ü ������ �����մϴ�.
			int result = mscon.connect_update(orderquery_list);
			switch(result){
			case 0:
				return true;			
			default:				
				return false;
			}
			
		}		
		return false;
    }
}
