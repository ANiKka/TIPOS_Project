import java.io.*;
import java.net.*;
import java.text.*;
import java.util.*;

import javax.swing.JOptionPane;

import org.json.simple.*;
import org.json.simple.parser.ParseException;

public class Trans_ShopAPI {

	//ȯ�漳��
	private String shop_key = Server_Config.getSHOPKEY();
	Ms_Connect ms_connect = new Ms_Connect();
		
	//��ǰ���
	public void goods_Insert(JSONArray json_data, String query){
		
		//�ּҵ��
		String goods_reg = "https://ssl.anybuild.co.kr/API/goods/goods_insert.php";
		System.out.println(" ����ȭ�� �����մϴ�. ���� �ּ� --> " + goods_reg);
		
		String shop_data = "";
		try {
			shop_data = "api_key="+shop_key.toString()+"&json_data="+URLEncoder.encode(json_data.toString(), "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		System.out.println(shop_data);
		
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
				
		//����� ���� �մϴ�.
		//�������� �����մϴ�.
		try {
			
			URL url = new URL(goods_reg);
			HttpURLConnection shop_url = (HttpURLConnection)url.openConnection();
			
			shop_url.setRequestMethod("POST");					
			shop_url.setRequestProperty("Accept-Language", "ko-kr,ko;q=0.8,en-us;q=0.5,en;q=0.3");
			shop_url.setDoInput(true);
			shop_url.setDoOutput(true);
						
			System.out.println("���ۻ��� ���");			
			System.out.println(" URL : "+shop_url.getURL());	

			OutputStreamWriter output = new OutputStreamWriter(shop_url.getOutputStream());
			
			output.write(shop_data);				
			
			output.flush();			
			output.close();			
			
			//���� ��� ����
			InputStreamReader isr = new InputStreamReader(shop_url.getInputStream(), "UTF-8");	
			JSONObject object = (JSONObject)JSONValue.parseWithException(isr);							
						
			isr.close();
						
			SimpleDateFormat formatter = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss", Locale.KOREA );
			Date currentTime = new Date ( );
			String dTime = formatter.format ( currentTime );
			
			String sb = "���� �ð� : " + dTime + "��� \r\n" ;
					sb += object.toJSONString();
			
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
			
			bos.close();
			System.out.println("������ �Ϸ� �Ǿ����ϴ�.");
			
			//���� ����� Ȯ�� �մϴ�. 
			if(object.get("result_code").equals("OK")){
				ms_connect.setMainSetting();
				int result = ms_connect.connect_update(query);
				switch(result){			
				case 0:
					System.out.println("���� ��� �Ǿ����ϴ�.");
					break;
				default:
					System.out.println("������ �߻� �Ǿ����ϴ�.");
					break;			
				}
			}						
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();		
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		
	}
	
	//��ǰ ������� �ڵ� �ҷ����� API
	//���θ� �����ڵ带 �ҷ� �ɴϴ�.
	public JSONArray getMainCode(String code){
		
		//code <- ��ȸ��ġ �Դϴ�.
		//ȯ�漳��
		String shop_key = Server_Config.getSHOPKEY();
		
		//���� ���θ����� �����ϱ�
		String shop_address = "https://ssl.anybuild.co.kr/API/goods/main_code.php";	
		System.out.println(" ����ȭ�� �����մϴ�. ���� �ּ� --> " + shop_address);
		
		shop_address += "?api_key="+shop_key.toString();
		System.out.println(shop_address);
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
		
		JSONArray data = new JSONArray();
		
		//����� ���� �մϴ�.
		//�������� �����մϴ�.
		try {
			
			URL url = new URL(shop_address);
			HttpURLConnection shop_url = (HttpURLConnection)url.openConnection();
			
			shop_url.setDoInput(true);
			
			System.out.println("���ۻ��� ���");			
			System.out.println(" URL : "+shop_url.getURL());			

			//���� ��� ����
			InputStreamReader isr = new InputStreamReader(shop_url.getInputStream(), "UTF-8");	
			JSONObject object = (JSONObject)JSONValue.parseWithException(isr);							
						
			isr.close();
			
			data = (JSONArray)object.get("result_data");
			System.out.println(data.toJSONString());
			//������
			System.out.println(object.toString());
			
			SimpleDateFormat formatter = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss", Locale.KOREA );
			Date currentTime = new Date ( );
			String dTime = formatter.format ( currentTime );
			
			String sb = "�����ڵ� ��� ��� ȣ����ġ : "+code
						+ "���� �ð� : " + dTime + " \r\n"; 
					sb += "result_msg : "+object.get("result_msg")+","+" result_cnt : "+object.get("result_cnt");					
			
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
			
			bos.close();
			System.out.println("��ȸ�� �Ϸ� �Ǿ����ϴ�.");
						
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();			
		}
		
		return data;
	}
	
	/*	�ֹ��˻� �Ϸ��� �Ʒ� ������ ���ʿ� �߰��Ͻø� �˴ϴ�. �Ʒ� �ֹ������� �Է����� �ʴ� ��� �ֱ� �ֹ��� 20���� �������� �˴ϴ�.
	&order_idx=�ֹ���ȣ&mem_id=ȸ�����̵�&j_name=�ֹ��ڸ�&s_name=�����ڸ�&order_date=2015-05-05 	*/	
	public void order_Info(String data){	
		String order_list = "https://ssl.anybuild.co.kr/API/shopping/order_info.php";
		System.out.println(order_list);		
		
		String shop_data = "";
		if(!data.isEmpty()){	
			shop_data = "api_key="+shop_key.toString()+data;
		}else{
			shop_data = "api_key="+shop_key.toString();
		}
				
		System.out.println(shop_data);
		
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
				
		//����� ���� �մϴ�.
		//�������� �����մϴ�.
		try {
			
			URL url = new URL(order_list);
			HttpURLConnection shop_url = (HttpURLConnection)url.openConnection();
			
			shop_url.setRequestMethod("POST");					
			shop_url.setRequestProperty("Accept-Language", "ko-kr,ko;q=0.8,en-us;q=0.5,en;q=0.3");
			shop_url.setDoInput(true);
			shop_url.setDoOutput(true);
						
			System.out.println("���ۻ��� ���");			
			System.out.println(" URL : "+shop_url.getURL());			

			OutputStreamWriter output = new OutputStreamWriter(shop_url.getOutputStream());
			
			output.write(shop_data);				
			
			output.flush();			
			output.close();			
			
			//���� ��� ����
			InputStreamReader isr = new InputStreamReader(shop_url.getInputStream(), "UTF-8");	
			JSONObject object = (JSONObject)JSONValue.parseWithException(isr);							
						
			isr.close();
						
			SimpleDateFormat formatter = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss", Locale.KOREA );
			Date currentTime = new Date ( );
			String dTime = formatter.format ( currentTime );
			
			String sb = "���� �ð� : " + dTime + "��� \r\n" ;
					sb += object.toJSONString();
			
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
			
			bos.close();
			System.out.println("������ �Ϸ� �Ǿ����ϴ�.");			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();		
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		
	}
	
	
	/*//�Ա�Ȯ�� ó����...
	$post_str = "api_key=333d4794fbf4b1a9d2b4e26b0091df59&order_idx=�ֹ���ȣ&state=1";
	//���ó��
	$post_str = "api_key=333d4794fbf4b1a9d2b4e26b0091df59&order_idx=�ֹ���ȣ&state=2&move_code=��ۻ��ڵ��ȣ&move_num=������ȣ&move_date=�����YYYY-MM-DD";
	//����Ȯ��
	$post_str = "api_key=333d4794fbf4b1a9d2b4e26b0091df59&order_idx=�ֹ���ȣ&state=3";
	//�ֹ����
	$post_str = "api_key=333d4794fbf4b1a9d2b4e26b0091df59&order_idx=�ֹ���ȣ&state=12&cancel_content=�ֹ���һ����� �Է����ּ���.";*/
	public String order_Edit(String order_list){
		
		//�ּҵ��
		String order_state = "https://ssl.anybuild.co.kr/API/shopping/order_state1.php";
		System.out.println(" ����ȭ�� �����մϴ�. ���� �ּ� --> " + order_state);
		
		if( order_list.length() < 0){
			JOptionPane.showMessageDialog(null, "������ �Է��� �ּ���!!");
			return "Fail";
		}
		
		String[] temp_list = order_list.split(";"); 
		
		String shop_data = "";
		String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		switch(temp_list[0]){
		case "1":
			shop_data = "api_key="+shop_key.toString()+"&order_idx="+temp_list[1]+"&state=1";
			break;
		case "2":
			shop_data = "api_key="+shop_key.toString()+"&order_idx="+temp_list[1]+"&state=2&move_code=98&move_num=&move_date="+today;
			break;
		case "3":
			shop_data = "api_key="+shop_key.toString()+"&order_idx="+temp_list[1]+"&state=3";
			break;
		case "12":
			shop_data = "api_key="+shop_key.toString()+"&order_idx="+temp_list[1]+"&state=12&cancel_content=������ �ֹ����";
			/*try {
				//shop_data = "api_key="+shop_key.toString()+"&order_idx="+URLEncoder.encode(json_data.toString(), "UTF-8");
				shop_data = "api_key="+shop_key.toString()+"&order_idx="+temp_list[1]+"&state=12&cancel_content="+URLEncoder.encode("������ �ֹ����", "UTF-8");
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}*/
			break;
		}		
		System.out.println(shop_data);
		
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
				
		//����� ���� �մϴ�.
		//�������� �����մϴ�.
		try {
			
			URL url = new URL(order_state);
			HttpURLConnection shop_url = (HttpURLConnection)url.openConnection();
			
			shop_url.setRequestMethod("POST");					
			shop_url.setRequestProperty("Accept-Language", "ko-kr,ko;q=0.8,en-us;q=0.5,en;q=0.3");
			shop_url.setDoInput(true);
			shop_url.setDoOutput(true);
						
			System.out.println("���ۻ��� ���");			
			System.out.println(" URL : "+shop_url.getURL());	
			OutputStreamWriter output = new OutputStreamWriter(shop_url.getOutputStream());
			output.write(shop_data);
			
			output.flush();			
			output.close();
			
			//���� ��� ����
			InputStreamReader isr = new InputStreamReader(shop_url.getInputStream(), "UTF-8");	
			JSONObject object = (JSONObject)JSONValue.parseWithException(isr);							
						
			isr.close();
						
			SimpleDateFormat formatter = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss", Locale.KOREA );
			Date currentTime = new Date ();
			String dTime = formatter.format ( currentTime );
			
			String sb = "���� �ð� : " + dTime + "��� \r\n" ;
					sb += object.toJSONString();
			
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
			
			bos.close();
			System.out.println("������ �Ϸ� �Ǿ����ϴ�.");
			
			//���� ����� Ȯ�� �մϴ�. 
			if(object.get("result_code").equals("OK")){
				String query = "Update e_OrderList Set state='"+temp_list[0]+"', state_subject='"+temp_list[2]+"' Where order_idx='"+temp_list[1]+"' ";
				
				ms_connect.setMainSetting();
				int result = ms_connect.connect_update(query);
				
				switch(result){	
				case 0:					
					System.out.println("�ֹ� ������ ���� ����  �Ǿ����ϴ�.");
					return "OK";					
				default:
					System.out.println("�ֹ� ���� ������ ������ �߻� �Ǿ����ϴ�.\r\n"+ms_connect.errMsg);
					return "Fail";						
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();		
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "Fail";
	}
		
	
	/*// * ���� �Է��� �ʵ忡 ���ؼ��� ������Ʈ �մϴ�.
	$post_str = "api_key=333d4794fbf4b1a9d2b4e26b0091df59";
	$post_str .= "&mem_id=sky1004";
	$post_str .= "&name=��浿";
	$post_str .= "&memlv=100";
	$post_str .= "&hp=010-1234-1234";
	$post_str .= "&tel=02-1234-1234";
	$post_str .= "&pwd=abcd1!";
	$post_str .= "&nickname=���ָ���";
	$post_str .= "&sex=1";
	$post_str .= "&age=26";
	$post_str .= "&email=sky1004@domain.com";
	$post_str .= "&zipcode=612-456";
	$post_str .= "&addr1=�λ�� �ؿ�뱸 ���ҵ��� 60";
	$post_str .= "&addr2=�۽�Ʈ�μ��� 501ȣ";
	$post_str .= "&birthday=1990-12-25";
	$post_str .= "&birthday_type=0";
	$post_str .= "&marry_yn=1";
	$post_str .= "&fax=02-456-7897";
	$post_str .= "&biz_num=612-45-7897";
	$post_str .= "&sangho=(��)�޳���";
	$post_str .= "&homepage=http://naver.com";
	$post_str .= "&add1=�߰��ʵ尪";
	$post_str .= "&add2=�߰��ʵ尪";
	$post_str .= "&add3=�߰��ʵ尪";
	$post_str .= "&add4=�߰��ʵ尪";
	$post_str .= "&add5=�߰��ʵ尪";
	$post_str .= "&add6=�߰��ʵ尪";
	$post_str .= "&add7=�߰��ʵ尪";
	$post_str .= "&add8=�߰��ʵ尪";
	$post_str .= "&add9=�߰��ʵ尪";
	$post_str .= "&add10=�߰��ʵ尪";*/	
	String mem_edit = "https://ssl.anybuild.co.kr/API/member/mem_edit.php";
	
	//ȸ�� ���� ��ȸ
	String mem_info = "https://ssl.anybuild.co.kr/API/member/mem_info.php";	
	public JSONArray getMemberManage(String hp, String mem_id){
				
		//ȯ�漳��
		String shop_key = Server_Config.getSHOPKEY();
		
		//���� ���θ����� �����ϱ�
		String shop_address = "https://ssl.anybuild.co.kr/API/member/mem_info.php";	
		System.out.println(" ����ȭ�� �����մϴ�. ���� �ּ� --> " + shop_address);
				
		System.out.println(shop_address);
		
		String shop_data = "";
		try {
			shop_data = "api_key="+shop_key.toString()+"&hp="+URLEncoder.encode("010-8619-7484", "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		System.out.println(shop_data);
		
		
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
		
		JSONArray data = new JSONArray();
		
		//����� ���� �մϴ�.
		//�������� �����մϴ�.
		try {
			
			URL url = new URL(shop_address);
			HttpURLConnection shop_url = (HttpURLConnection)url.openConnection();
			
			shop_url.setRequestMethod("POST");					
			shop_url.setRequestProperty("Accept-Language", "ko-kr,ko;q=0.8,en-us;q=0.5,en;q=0.3");
			
			shop_url.setDoInput(true);
			shop_url.setDoOutput(true);
			
			System.out.println("���ۻ��� ���");			
			System.out.println(" URL : "+shop_url.getURL());			
			
			OutputStreamWriter output = new OutputStreamWriter(shop_url.getOutputStream());
			
			output.write(shop_data);				
			
			output.flush();			
			output.close();		

			//���� ��� ����
			InputStreamReader isr = new InputStreamReader(shop_url.getInputStream(), "UTF-8");	
			JSONObject object = (JSONObject)JSONValue.parseWithException(isr);							
						
			isr.close();
			
			if(object.get("result_code").equals("OK")){				
				data = (JSONArray)object.get("data");
				System.out.println(data.toJSONString());
			}
			
			//������
			System.out.println(object.toString());
			
			
			SimpleDateFormat formatter = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss", Locale.KOREA );
			Date currentTime = new Date ( );
			String dTime = formatter.format ( currentTime );
			
			String sb = "�����ڵ� ��� ��� ȣ����ġ : ȸ�� ȣ��"
						+ "���� �ð� : " + dTime + " \r\n"; 
					sb += "resultcode : "+object.get("result_code")+" result_msg : "+object.get("result_msg");					
			
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
			
			bos.close();
			System.out.println("��ȸ�� �Ϸ� �Ǿ����ϴ�.");
						
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();			
		}
		
		return data;
	}	
	
	
	
	/*$post_str = "api_key=333d4794fbf4b1a9d2b4e26b0091df59";
	$post_str .= "&mem_id=sky1004"; // ȸ�� ���̵�
	$post_str .= "&emoney_use=1000"; // ���̳ʽ� �Է½� ����, ��� �Է½� ����
	$post_str .= "&bigo=���� ����"; // ��� �Է����ּ���.*/	
	String emoney_use = "https://ssl.anybuild.co.kr/API/member/emoney_use.php";
		
	/*$post_str = "api_key=333d4794fbf4b1a9d2b4e26b0091df59";
	$post_str .= "&mem_id=sky1004"; // ȸ�� ���̵�
	$post_str .= "&point_use=1000"; // ���̳ʽ� �Է½� ����, ��� �Է½� ����
	$post_str .= "&bigo=���� ����"; // ��� �Է����ּ���.*/
	String point_use = "https://ssl.anybuild.co.kr/API/member/point_use.php";
	
	/*	�˻� �Ϸ��� �Ʒ� ������ ���ʿ� �߰��Ͻø� �˴ϴ�. �Ʒ� �˻������� �Է����� �ʴ� ��� �ֱ� DATA 20���� ��� �մϴ�.
	&mem_only=(ALL:��ü ����,Y:ȸ��������ȸ���� ����,N:��ȸ���� ����)&platform=�÷���&devicename=�������&devicemodel=������ �𵨸�&deviceversion=�÷��� ����&hp_num=�ڵ�����ȣ
	?api_key=333d4794fbf4b1a9d2b4e26b0091df59 */
	String device_list = "https://ssl.anybuild.co.kr/API/app/device_list.php";	
	public JSONArray getDeviceList(){
				
		//ȯ�漳��
		String shop_key = Server_Config.getSHOPKEY();
		
		//���� ���θ����� �����ϱ�
		String shop_address = "https://ssl.anybuild.co.kr/API/app/device_list.php";	
		System.out.println(" ����ȭ�� �����մϴ�. ���� �ּ� --> " + shop_address);
				
		System.out.println(shop_address);
		
		String shop_data = "";
		//try {
			shop_data = "api_key="+shop_key.toString()+"&mem_only=ALL";//+URLEncoder.encode("010-8619-7484", "UTF-8");
		//} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
		//	e1.printStackTrace();
		//}
		
		System.out.println(shop_data);
		
		
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
		
		JSONArray data = new JSONArray();
		
		//����� ���� �մϴ�.
		//�������� �����մϴ�.
		try {
			
			URL url = new URL(shop_address);
			HttpURLConnection shop_url = (HttpURLConnection)url.openConnection();
			
			shop_url.setRequestMethod("POST");					
			shop_url.setRequestProperty("Accept-Language", "ko-kr,ko;q=0.8,en-us;q=0.5,en;q=0.3");
			
			shop_url.setDoInput(true);
			shop_url.setDoOutput(true);
			
			System.out.println("���ۻ��� ���");			
			System.out.println(" URL : "+shop_url.getURL());			
			
			OutputStreamWriter output = new OutputStreamWriter(shop_url.getOutputStream());
			
			output.write(shop_data);				
			
			output.flush();			
			output.close();		

			//���� ��� ����
			InputStreamReader isr = new InputStreamReader(shop_url.getInputStream(), "UTF-8");	
			JSONObject object = (JSONObject)JSONValue.parseWithException(isr);							
						
			isr.close();
			
			if(object.get("result_code").equals("OK")){				
				data = (JSONArray)object.get("result_data");
				System.out.println(data.toJSONString());
			}
			
			//������
			System.out.println(object.toString());
			
			
			SimpleDateFormat formatter = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss", Locale.KOREA );
			Date currentTime = new Date ( );
			String dTime = formatter.format ( currentTime );
			
			String sb = "�ۼ�ġ��Ȳ ��� ��� ȣ����ġ : �ۼ�ġ ��Ȳ"
						+ "���� �ð� : " + dTime + " \r\n"; 
					sb += "resultcode : "+object.get("result_code")+" result_msg : "+object.get("result_msg")+" result_cnt : "+object.get("result_cnt");					
			
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
			
			bos.close();
			System.out.println("��ȸ�� �Ϸ� �Ǿ����ϴ�.");
						
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();			
		}
		
		return data;
	}	
	
	
	/* �˻� �Ϸ��� �Ʒ� ������ ���ʿ� �߰��Ͻø� �˴ϴ�. �Ʒ� �˻������� �Է����� �ʴ� ��� ��� APP ��ġ ������ �޼��� ���۵ǹǷ� ���� �Ͻñ� �ٶ��ϴ�.
	&memlv=ȸ�����(���Է½� ��μ���, 100,1000,1100....)&mem_id=ȸ�����̵�&mem_only=(ALL:��ü ����,Y:ȸ��������ȸ���� ����,N:��ȸ���� ����)&platform=�÷���&devicename=�������&devicemodel=������ �𵨸�&deviceversion=�÷��� ����&hp_num=�ڵ�����ȣ	
	"api_key=333d4794fbf4b1a9d2b4e26b0091df59&push_title=�Ը����̺�Ʈ&push_msg=���� �湮�Ͻø� �Ƹ޸�ī��1�� ���� �����մϴ�."
	+ "&push_link=/main&push_img_url=".urlencode('http://sskshop1.anybuild.com/thum_img/sskshop1/goods_img2/85b6f89b41cae26786ac72365fff771b_water_3afcaf174b6d740dcc3f8f859871184e_c1_w320_h320.jpg').""
	+ "&memlv=&mem_only=ALL&platform=&devicename=&devicemodel=&deviceversion=&hp_num=");*/
	String push_submit = "https://ssl.anybuild.co.kr/API/app/push_submit.php";
	public JSONObject setPushSubimt(HashMap<String, Object> push_list){
		
		//ȯ�漳��
		String shop_key = Server_Config.getSHOPKEY();
		
		//���� ���θ����� �����ϱ�
		String shop_address = "https://ssl.anybuild.co.kr/API/app/push_submit.php";	
		System.out.println(" ����ȭ�� �����մϴ�. ���� �ּ� --> " + shop_address);				
		System.out.println(shop_address);
				
		String push_title = (String)push_list.get("Title");
		String push_msg = (String)push_list.get("Message");
		String push_link = (String)push_list.get("Link");
		String push_url = (String)push_list.get("Img_Url");
		String event_idx = (String)push_list.get("Event");
		
		String mem_id = (String)push_list.get("Mem_Id");
		String memlv = "";
		String mem_only = (String)push_list.get("Mem_Only");
		String hp_num = (String)push_list.get("Hp");
		
		
		String shop_data = "";
		try {
			shop_data = "api_key="+shop_key;
			/*shop_data +="&push_title="+URLEncoder.encode(push_title, "UTF-8")+"&push_msg="+URLEncoder.encode(push_msg, "UTF-8")+"&push_link="+URLEncoder.encode(push_link, "UTF-8")
					+"&push_img_url="+URLEncoder.encode(push_url, "UTF-8")+"&mem_id="+URLEncoder.encode(mem_id, "UTF-8")
					+"&memlv="+memlv+"&mem_only="+mem_only+"&platform=&devicename=&devicemodel=&deviceversion=&hp_num="+hp_num+"&event_idx="+event_idx;*/
			shop_data +="&push_title="+URLEncoder.encode(push_title, "UTF-8")+"&push_msg="+URLEncoder.encode(push_msg, "UTF-8")+"&push_link="+URLEncoder.encode(push_link, "UTF-8")
			+"&push_img_url="+URLEncoder.encode(push_url, "UTF-8")+"&mem_id="+mem_id
			+"&memlv="+memlv+"&mem_only="+mem_only+"&platform=&devicename=&devicemodel=&deviceversion=&hp_num="+hp_num+"&event_idx="+event_idx;
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		/*shop_data = "api_key="+shop_key;//+"&hp=01090077611";//+URLEncoder.encode("010-8619-7484", "UTF-8");
		shop_data +="&push_title=�Ը����̺�Ʈ&push_msg=���� �湮�Ͻø� �Ƹ޸�ī��1�� ���� �����մϴ�.&push_link=/main&push_img_url="
				+"&memlv=&mem_only=ALL&platform=&devicename=&devicemodel=&deviceversion=&hp_num=01090077611";*/
		System.out.println(shop_data);
		
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
		
		JSONObject object = new JSONObject();;
		
		//����� ���� �մϴ�.
		//�������� �����մϴ�.
		try {
			
			URL url = new URL(shop_address);
			HttpURLConnection shop_url = (HttpURLConnection)url.openConnection();
			
			shop_url.setRequestMethod("POST");					
			shop_url.setRequestProperty("Accept-Language", "ko-kr,ko;q=0.8,en-us;q=0.5,en;q=0.3");
			
			shop_url.setDoInput(true);
			shop_url.setDoOutput(true);
			
			System.out.println("���ۻ��� ���");			
			System.out.println(" URL : "+shop_url.getURL());			
			
			OutputStreamWriter output = new OutputStreamWriter(shop_url.getOutputStream());
			
			output.write(shop_data);				
			
			output.flush();			
			output.close();		

			//���� ��� ����
			InputStreamReader isr = new InputStreamReader(shop_url.getInputStream(), "UTF-8");	
			object = (JSONObject)JSONValue.parseWithException(isr);							
						
			isr.close();
			
			//������
			System.out.println(object.toString());
						
			SimpleDateFormat formatter = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss", Locale.KOREA );
			Date currentTime = new Date ( );
			String dTime = formatter.format ( currentTime );
			
			String sb = "�ۼ�ġ��Ȳ ��� ��� ȣ����ġ : �ۼ�ġ ��Ȳ"
						+ "���� �ð� : " + dTime + " \r\n"; 
					sb += "resultcode : "+object.get("result_code")+" result_msg : "+object.get("result_msg")+" result_cnt : "+object.get("result_log");					
			
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
			
			bos.close();
			System.out.println("��ȸ�� �Ϸ� �Ǿ����ϴ�.");
						
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();			
		}
		
		return object;
	}	
	
	/* �˻� �Ϸ��� �Ʒ� ������ ���ʿ� �߰��Ͻø� �˴ϴ�. �Ʒ� �˻������� �Է����� �ʴ� ��� ��� APP ��ġ ������ �޼��� ���۵ǹǷ� ���� �Ͻñ� �ٶ��ϴ�.
	&memlv=ȸ�����(���Է½� ��μ���, 100,1000,1100....)&mem_id=ȸ�����̵�&mem_only=(ALL:��ü ����,Y:ȸ��������ȸ���� ����,N:��ȸ���� ����)&platform=�÷���&devicename=�������&devicemodel=������ �𵨸�&deviceversion=�÷��� ����&hp_num=�ڵ�����ȣ	
	"api_key=333d4794fbf4b1a9d2b4e26b0091df59&push_title=�Ը����̺�Ʈ&push_msg=���� �湮�Ͻø� �Ƹ޸�ī��1�� ���� �����մϴ�."
	+ "&push_link=/main&push_img_url=".urlencode('http://sskshop1.anybuild.com/thum_img/sskshop1/goods_img2/85b6f89b41cae26786ac72365fff771b_water_3afcaf174b6d740dcc3f8f859871184e_c1_w320_h320.jpg').""
	+ "&memlv=&mem_only=ALL&platform=&devicename=&devicemodel=&deviceversion=&hp_num=");*/	
	public JSONObject tranNewPushSubimt(){
		
		//ȯ�漳��
		String shop_key = Server_Config.getSHOPKEY();
		
		//���� ���θ����� �����ϱ�
		String shop_address = "https://ssl.anybuild.co.kr/API/app/push_submit.php";	
		System.out.println(" ����ȭ�� �����մϴ�. ���� �ּ� --> " + shop_address);				
		System.out.println(shop_address);
				
		
		/*
		�� ������ �޼����� ���� �ϼ���.
		&push_title=�������� (�ִ� 20byte)
		&push_msg=���� ���۵Ǵ� Ǫ�ø޼��� �Դϴ�. 50byte �̻��ΰ�� ios�� ���ܵǹǷ� ���� �Ͻñ� �ٶ��ϴ�.
		&push_link=�ش� Ǫ�ø� Ŭ���� �̵��� ������ ��θ� �Է��ϼ���. �ݵ�� / �� �����ϰ� �ش������ �����ϴ� �ּ��̾�� �մϴ�.  http:// �����ϴ� �ּҴ� ���Ȼ� ���ܵ˴ϴ�.
		&push_noti_img_url=".urlencode('http://�� �����ϴ� �̹��� ��ü ��θ� �Է��ϼ���.')." �ȵ���̵� �������̼ǿ��� �̹����� �ٷ� ��� �մϴ�. �뷮�� 300Kbyte �̻��ΰ�� �����߻��Ҽ� �����Ƿ� �뷮�� �ִ��� ���̽ñ� �ٶ��ϴ�. ios�� �Ұ��� �մϴ�.
		&push_content_img_url=".urlencode('http://�� �����ϴ� �̹��� ��ü ��θ� �Է��ϼ���.')." �ȵ���̵��� ��� ����ȭ�鿡�� Ȯ�� �����ϸ�, ios��  �Ұ��� �մϴ�. �̹��� ���ϰ�ο� �ѱ�, Ư�� ����, ���Ⱑ �ִ� ��� ���� �߻��ǹǷ� ���� �� ���ڷθ� �����Ǿ� �־�� �մϴ�.
		&content = ".urlencode('�������� ������ �Է��ϼ���.')." �ȵ���̵� �� ios ��� ����ȭ�鿡�� Ȯ�� �Ұ��� �մϴ�.
		&content_mode = text or html
		*/
		
		//&push_title=�������� (�ִ� 20byte)
		String push_title = "�׽�Ʈ�Դϴ�.";//(String)push_list.get("Title");
		//&push_msg=���� ���۵Ǵ� Ǫ�ø޼��� �Դϴ�. 50byte �̻��ΰ�� ios�� ���ܵǹǷ� ���� �Ͻñ� �ٶ��ϴ�.
		String push_msg = "���� ���� �޼����� �幮�� �� �޼��� �Դϴ�. Ȯ�� �Ҽ� ������ Ȯ�� �ٶ��ϴ�.";//(String)push_list.get("Message");
		//&push_link=�ش� Ǫ�ø� Ŭ���� �̵��� ������ ��θ� �Է��ϼ���. �ݵ�� / �� �����ϰ� �ش������ �����ϴ� �ּ��̾�� �մϴ�.  http:// �����ϴ� �ּҴ� ���Ȼ� ���ܵ˴ϴ�.
		String push_link = "";//(String)push_list.get("Link");
		//&push_noti_img_url=".urlencode('http://�� �����ϴ� �̹��� ��ü ��θ� �Է��ϼ���.')." �ȵ���̵� �������̼ǿ��� �̹����� �ٷ� ��� �մϴ�. �뷮�� 300Kbyte �̻��ΰ�� �����߻��Ҽ� �����Ƿ� �뷮�� �ִ��� ���̽ñ� �ٶ��ϴ�. ios�� �Ұ��� �մϴ�.
		String push_noti_img_url = "http://14.38.161.45:8080/image/0000001/333_sub.jpg";//(String)push_list.get("Img_Url");		
		//&push_content_img_url=".urlencode('http://�� �����ϴ� �̹��� ��ü ��θ� �Է��ϼ���.')." �ȵ���̵��� ��� ����ȭ�鿡�� Ȯ�� �����ϸ�, ios��  �Ұ��� �մϴ�. �̹��� ���ϰ�ο� �ѱ�, Ư�� ����, ���Ⱑ �ִ� ��� ���� �߻��ǹǷ� ���� �� ���ڷθ� �����Ǿ� �־�� �մϴ�.
		String push_content_img = "http://14.38.161.45:8080/image/0000001/333.jpg";//(String)push_list.get("Content_Img");
		//&content = ".urlencode('�������� ������ �Է��ϼ���.')." �ȵ���̵� �� ios ��� ����ȭ�鿡�� Ȯ�� �Ұ��� �մϴ�.
		String push_content = "������� ��� <br>"
									+"1,000�� ���� ��� �մϴ�. <br>"
									+"<br>"									
									+"<p style='text-align:center'>"
									+"<img src='/API/barcodegen_v2.2.0/html/image.php?code=code128&o=1&dpi=72&t=30&r=3&rot=0&text={$bar_code}&f1=Arial.ttf&f2=12&a1=&a2=&a3={$bar_code}'><br>"
									+"<img src='/API/barcodegen_v2.2.0/html/image.php?code=code128&o=1&dpi=72&t=30&r=3&rot=0&text=ABCD123&f1=Arial.ttf&f2=12&a1=&a2=&a3=ABCD123'><br>"
									+"<img src=\"{@ echo qrcode_load($bar_code,3);  @}\" />"
									+"<br>"
									+"<br>"
									+"����̽� ������ȣ : {$device_idx}<br>"
									+"�̺�Ʈ ������ȣ : {$event_idx}<br>"
									+"�ڵ����� ���ڵ� : {$bar_code}"
									+"</p>";//(String)push_list.get("Content");
		//&content_mode = text or html
		String content_mode = "html";//(String)push_list.get("Content_Mode");
				
		String event_idx = "";//"&event_idx=76";//+(String)push_list.get("Event");		
		
		String mem_id = "";//(String)push_list.get("Mem_Id");
		String memlv = "";
		String mem_only = "";//(String)push_list.get("Mem_Only");
		String hp_num = "01090077611";//(String)push_list.get("Hp");
		
		
		String shop_data = "";
		try {
			shop_data = "api_key="+shop_key;			
			shop_data +="&push_title="+URLEncoder.encode(push_title, "UTF-8")+"&push_msg="+URLEncoder.encode(push_msg, "UTF-8")+"&push_link="+URLEncoder.encode(push_link, "UTF-8")
			+"&push_noti_img_url="+URLEncoder.encode(push_noti_img_url, "UTF-8")
			+"&push_content_img="+URLEncoder.encode(push_content_img, "UTF-8")
			+"&push_img_url="+URLEncoder.encode(push_content_img, "UTF-8")
			+"&content="+URLEncoder.encode(push_content, "UTF-8")+"&content_mode="+content_mode
			+"&mem_id="+mem_id
			+"&memlv="+memlv+"&mem_only="+mem_only+"&platform=&devicename=&devicemodel=&deviceversion=&hp_num="+hp_num+event_idx;
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		/*shop_data = "api_key="+shop_key;//+"&hp=01090077611";//+URLEncoder.encode("010-8619-7484", "UTF-8");
		shop_data +="&push_title=�Ը����̺�Ʈ&push_msg=���� �湮�Ͻø� �Ƹ޸�ī��1�� ���� �����մϴ�.&push_link=/main&push_img_url="
				+"&memlv=&mem_only=ALL&platform=&devicename=&devicemodel=&deviceversion=&hp_num=01090077611";*/
		System.out.println(shop_data);
		
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
		
		JSONObject object = new JSONObject();;
		
		//����� ���� �մϴ�.
		//�������� �����մϴ�.
		try {
			
			URL url = new URL(shop_address);
			HttpURLConnection shop_url = (HttpURLConnection)url.openConnection();
			
			shop_url.setRequestMethod("POST");					
			shop_url.setRequestProperty("Accept-Language", "ko-kr,ko;q=0.8,en-us;q=0.5,en;q=0.3");
			
			shop_url.setDoInput(true);
			shop_url.setDoOutput(true);
			
			System.out.println("���ۻ��� ���");			
			System.out.println(" URL : "+shop_url.getURL());			
			
			OutputStreamWriter output = new OutputStreamWriter(shop_url.getOutputStream());
			
			output.write(shop_data);				
			
			output.flush();			
			output.close();		

			//���� ��� ����
			InputStreamReader isr = new InputStreamReader(shop_url.getInputStream(), "UTF-8");	
			object = (JSONObject)JSONValue.parseWithException(isr);							
						
			isr.close();
			
			//������
			System.out.println(object.toString());
						
			SimpleDateFormat formatter = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss", Locale.KOREA );
			Date currentTime = new Date ( );
			String dTime = formatter.format ( currentTime );
			
			String sb = "�ۼ�ġ��Ȳ ��� ��� ȣ����ġ : �ۼ�ġ ��Ȳ"
						+ "���� �ð� : " + dTime + " \r\n"; 
					sb += "resultcode : "+object.get("result_code")+" result_msg : "+object.get("result_msg")+" result_cnt : "+object.get("result_log");					
			
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
			
			bos.close();
			System.out.println("��ȸ�� �Ϸ� �Ǿ����ϴ�.");
						
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();			
		}
		
		return object;
	}	
	
	
	//Ǫ�� �̺�Ʈ ��� �ҷ�����
	public JSONArray getPushEventList(){
		
			//ȯ�漳��
			String shop_key = Server_Config.getSHOPKEY();
			
			//���� ���θ����� �����ϱ�
			String shop_address = "https://ssl.anybuild.co.kr/API/app/push_event_list.php?api_key="+shop_key;	
			System.out.println(" ����ȭ�� �����մϴ�. ���� �ּ� --> " + shop_address);
			
			//�������� ���� �մϴ�.
			JSONArray data = new JSONArray();
			
			//����� ���� �մϴ�.
			//�������� �����մϴ�.
			try {
				
				URL url = new URL(shop_address);
				HttpURLConnection shop_url = (HttpURLConnection)url.openConnection();
			
				shop_url.setDoInput(true);
				
				System.out.println("���ۻ��� ���");
				System.out.println(" URL : "+shop_url.getURL());			
								
				//���� ��� ����
				InputStreamReader isr = new InputStreamReader(shop_url.getInputStream(), "UTF-8");	
				JSONObject object = (JSONObject)JSONValue.parseWithException(isr);							
							
				isr.close();
				
				if(object.get("result_code").equals("OK")){				
					data = (JSONArray)object.get("result_data");
					System.out.println(data.toJSONString());
				}
				
				//������
				System.out.println(object.toString());
								
				SimpleDateFormat formatter = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss", Locale.KOREA );
				Date currentTime = new Date ( );
				String dTime = formatter.format ( currentTime );
				
				String sb = "Ǫ�� �̺�Ʈ ��� -> ȣ����ġ : Ǫ�� �̺�Ʈ ���"
							+ "���� �ð� : " + dTime + " \r\n"; 
						sb += "resultcode : "+object.get("result_code")+" result_msg : "+object.get("result_msg")+" result_cnt : "+object.get("result_cnt");					
				
				char[] paser = sb.toCharArray();				
				System.out.println("��ȸ�� �Ϸ� �Ǿ����ϴ�.");
							
				//�α׸� ����մϴ�.
				setLogFile(paser);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();			
			}
			
			return data;
	}
	
	
	
	//api key ���� �ϱ�	
	public void setShopContents(JSONArray json_data, String model){
		
		
			
	}
	
	
	//api key ���� �ϱ�	
	public void setShopContents(String json_data, String model){
		
	
		
		
	}
	
	
	//�α����� �����ؼ� �����
	private void setLogFile(char[] paser){		
		
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
		
		//�α������� �ۼ��մϴ�.
		OutputStreamWriter bos;
		try {
			bos = new OutputStreamWriter(new FileOutputStream(file, true), "euc-kr");
			
			StringBuffer result_str = new StringBuffer();
			
			for(char str : paser){
				bos.write(str);
				result_str.append(str);
			}	
			System.out.println(result_str);
			
			bos.write('\r');
			bos.write('\n');	
			
			bos.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}				
		
	}
	
	
	public JSONArray getShopContents(){
		
		JSONArray results = new JSONArray();
		
		return results;
	}
	
	//���θ� ���� ��������
	
	
	
	//���θ� ���ε� �ϱ�
	
	
}
