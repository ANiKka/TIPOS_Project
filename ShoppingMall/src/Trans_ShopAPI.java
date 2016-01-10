import java.io.*;
import java.net.*;
import java.text.*;
import java.util.*;

import org.json.simple.*;
import org.json.simple.parser.ParseException;

public class Trans_ShopAPI {

	//환경설정
	private String shop_key = Server_Config.getSHOPKEY();
	Ms_Connect ms_connect = new Ms_Connect();
		
	//상품등록
	public void goods_Insert(JSONArray json_data, String query){
		
		//주소등록
		String goods_reg = "https://ssl.anybuild.co.kr/API/goods/goods_insert.php";
		System.out.println(" 동기화를 시작합니다. 접속 주소 --> " + goods_reg);
		
		String shop_data = "";
		try {
			shop_data = "api_key="+shop_key.toString()+"&json_data="+URLEncoder.encode(json_data.toString(), "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		System.out.println(shop_data);
		
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
				
		//결과를 전송 합니다.
		//전송폼을 생성합니다.
		try {
			
			URL url = new URL(goods_reg);
			HttpURLConnection shop_url = (HttpURLConnection)url.openConnection();
			
			shop_url.setRequestMethod("POST");					
			shop_url.setRequestProperty("Accept-Language", "ko-kr,ko;q=0.8,en-us;q=0.5,en;q=0.3");
			shop_url.setDoInput(true);
			shop_url.setDoOutput(true);
						
			System.out.println("전송상태 출력");			
			System.out.println(" URL : "+shop_url.getURL());			

			OutputStreamWriter output = new OutputStreamWriter(shop_url.getOutputStream());
			
			output.write(shop_data);				
			
			output.flush();			
			output.close();			
			
			//전송 결과 수신
			InputStreamReader isr = new InputStreamReader(shop_url.getInputStream(), "UTF-8");	
			JSONObject object = (JSONObject)JSONValue.parseWithException(isr);							
						
			isr.close();
						
			SimpleDateFormat formatter = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss", Locale.KOREA );
			Date currentTime = new Date ( );
			String dTime = formatter.format ( currentTime );
			
			String sb = "전송 시간 : " + dTime + "결과 \r\n" ;
					sb += object.toJSONString();
			
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
			
			bos.close();
			System.out.println("전송이 완료 되었습니다.");
			
			//전송 결과를 확인 합니다. 
			if(object.get("result_code").equals("OK")){
				ms_connect.setMainSetting();
				int result = ms_connect.connect_update(query);
				switch(result){			
				case 0:
					System.out.println("정상 등록 되었습니다.");
					break;
				default:
					System.out.println("오류가 발생 되었습니다.");
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
	
	//상품 메인출력 코드 불러오기 API
	//쇼핑몰 메인코드를 불러 옵니다.
	public JSONArray getMainCode(String code){
		
		//code <- 조회위치 입니다.
		//환경설정
		String shop_key = Server_Config.getSHOPKEY();
		
		//접속 쇼핑몰정보 정의하기
		String shop_address = "https://ssl.anybuild.co.kr/API/goods/main_code.php";	
		System.out.println(" 동기화를 시작합니다. 접속 주소 --> " + shop_address);
		
		shop_address += "?api_key="+shop_key.toString();
		System.out.println(shop_address);
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
		
		JSONArray data = new JSONArray();
		
		//결과를 전송 합니다.
		//전송폼을 생성합니다.
		try {
			
			URL url = new URL(shop_address);
			HttpURLConnection shop_url = (HttpURLConnection)url.openConnection();
			
			shop_url.setDoInput(true);
			
			System.out.println("전송상태 출력");			
			System.out.println(" URL : "+shop_url.getURL());			

			//전송 결과 수신
			InputStreamReader isr = new InputStreamReader(shop_url.getInputStream(), "UTF-8");	
			JSONObject object = (JSONObject)JSONValue.parseWithException(isr);							
						
			isr.close();
			
			data = (JSONArray)object.get("result_data");
			System.out.println(data.toJSONString());
			//결과출력
			System.out.println(object.toString());
			
			SimpleDateFormat formatter = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss", Locale.KOREA );
			Date currentTime = new Date ( );
			String dTime = formatter.format ( currentTime );
			
			String sb = "메인코드 출력 결과 호출위치 : "+code
						+ "전송 시간 : " + dTime + " \r\n"; 
					sb += "result_msg : "+object.get("result_msg")+","+" result_cnt : "+object.get("result_cnt");					
			
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
			
			bos.close();
			System.out.println("조회가 완료 되었습니다.");
						
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();			
		}
		
		return data;
	}
	
	/*	주문검색 하려면 아래 변수를 뒤쪽에 추가하시면 됩니다. 아래 주문조건을 입력하지 않는 경우 최근 주문서 20개를 가져오게 됩니다.
	&order_idx=주문번호&mem_id=회원아이디&j_name=주문자명&s_name=수신자명&order_date=2015-05-05 	*/	
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
				
		//결과를 전송 합니다.
		//전송폼을 생성합니다.
		try {
			
			URL url = new URL(order_list);
			HttpURLConnection shop_url = (HttpURLConnection)url.openConnection();
			
			shop_url.setRequestMethod("POST");					
			shop_url.setRequestProperty("Accept-Language", "ko-kr,ko;q=0.8,en-us;q=0.5,en;q=0.3");
			shop_url.setDoInput(true);
			shop_url.setDoOutput(true);
						
			System.out.println("전송상태 출력");			
			System.out.println(" URL : "+shop_url.getURL());			

			OutputStreamWriter output = new OutputStreamWriter(shop_url.getOutputStream());
			
			output.write(shop_data);				
			
			output.flush();			
			output.close();			
			
			//전송 결과 수신
			InputStreamReader isr = new InputStreamReader(shop_url.getInputStream(), "UTF-8");	
			JSONObject object = (JSONObject)JSONValue.parseWithException(isr);							
						
			isr.close();
						
			SimpleDateFormat formatter = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss", Locale.KOREA );
			Date currentTime = new Date ( );
			String dTime = formatter.format ( currentTime );
			
			String sb = "전송 시간 : " + dTime + "결과 \r\n" ;
					sb += object.toJSONString();
			
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
			
			bos.close();
			System.out.println("전송이 완료 되었습니다.");			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();		
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		
	}
	
	
	
	/*//입금확인 처리시...
	$post_str = "api_key=333d4794fbf4b1a9d2b4e26b0091df59&order_idx=주문번호&state=1";
	//배송처리
	$post_str = "api_key=333d4794fbf4b1a9d2b4e26b0091df59&order_idx=주문번호&state=2&move_code=배송사코드번호&move_num=운송장번호&move_date=배송일YYYY-MM-DD";
	//수취확인
	$post_str = "api_key=333d4794fbf4b1a9d2b4e26b0091df59&order_idx=주문번호&state=3";
	//주문취소
	$post_str = "api_key=333d4794fbf4b1a9d2b4e26b0091df59&order_idx=주문번호&state=12&cancel_content=주문취소사유를 입력해주세요.";*/
	String order_state = "https://ssl.anybuild.co.kr/API/shopping/order_state1.php";
		
	/*// * 값을 입력한 필드에 대해서만 업데이트 합니다.

	$post_str = "api_key=333d4794fbf4b1a9d2b4e26b0091df59";
	$post_str .= "&mem_id=sky1004";
	$post_str .= "&name=김길동";
	$post_str .= "&memlv=100";
	$post_str .= "&hp=010-1234-1234";
	$post_str .= "&tel=02-1234-1234";
	$post_str .= "&pwd=abcd1!";
	$post_str .= "&nickname=공주마마";
	$post_str .= "&sex=1";
	$post_str .= "&age=26";
	$post_str .= "&email=sky1004@domain.com";
	$post_str .= "&zipcode=612-456";
	$post_str .= "&addr1=부산시 해운대구 센텀동로 60";
	$post_str .= "&addr2=퍼스트인센텀 501호";
	$post_str .= "&birthday=1990-12-25";
	$post_str .= "&birthday_type=0";
	$post_str .= "&marry_yn=1";
	$post_str .= "&fax=02-456-7897";
	$post_str .= "&biz_num=612-45-7897";
	$post_str .= "&sangho=(주)꿈나라";
	$post_str .= "&homepage=http://naver.com";
	$post_str .= "&add1=추가필드값";
	$post_str .= "&add2=추가필드값";
	$post_str .= "&add3=추가필드값";
	$post_str .= "&add4=추가필드값";
	$post_str .= "&add5=추가필드값";
	$post_str .= "&add6=추가필드값";
	$post_str .= "&add7=추가필드값";
	$post_str .= "&add8=추가필드값";
	$post_str .= "&add9=추가필드값";
	$post_str .= "&add10=추가필드값";*/	
	String mem_edit = "https://ssl.anybuild.co.kr/API/member/mem_edit.php";
	
	//회원 정보 조회
	String mem_info = "https://ssl.anybuild.co.kr/API/member/mem_info.php";
	
	public JSONArray getMemberManage(String hp, String mem_id){
				
		//환경설정
		String shop_key = Server_Config.getSHOPKEY();
		
		//접속 쇼핑몰정보 정의하기
		String shop_address = "https://ssl.anybuild.co.kr/API/member/mem_info.php";	
		System.out.println(" 동기화를 시작합니다. 접속 주소 --> " + shop_address);
				
		System.out.println(shop_address);
		
		String shop_data = "";
		try {
			shop_data = "api_key="+shop_key.toString()+"&hp="+URLEncoder.encode("010-8619-7484", "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		System.out.println(shop_data);
		
		
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
		
		JSONArray data = new JSONArray();
		
		//결과를 전송 합니다.
		//전송폼을 생성합니다.
		try {
			
			URL url = new URL(shop_address);
			HttpURLConnection shop_url = (HttpURLConnection)url.openConnection();
			
			shop_url.setRequestMethod("POST");					
			shop_url.setRequestProperty("Accept-Language", "ko-kr,ko;q=0.8,en-us;q=0.5,en;q=0.3");
			
			shop_url.setDoInput(true);
			shop_url.setDoOutput(true);
			
			System.out.println("전송상태 출력");			
			System.out.println(" URL : "+shop_url.getURL());			
			
			OutputStreamWriter output = new OutputStreamWriter(shop_url.getOutputStream());
			
			output.write(shop_data);				
			
			output.flush();			
			output.close();		

			//전송 결과 수신
			InputStreamReader isr = new InputStreamReader(shop_url.getInputStream(), "UTF-8");	
			JSONObject object = (JSONObject)JSONValue.parseWithException(isr);							
						
			isr.close();
			
			if(object.get("result_code").equals("OK")){				
				data = (JSONArray)object.get("data");
				System.out.println(data.toJSONString());
			}
			
			//결과출력
			System.out.println(object.toString());
			
			
			SimpleDateFormat formatter = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss", Locale.KOREA );
			Date currentTime = new Date ( );
			String dTime = formatter.format ( currentTime );
			
			String sb = "메인코드 출력 결과 호출위치 : 회원 호출"
						+ "전송 시간 : " + dTime + " \r\n"; 
					sb += "resultcode : "+object.get("result_code")+" result_msg : "+object.get("result_msg");					
			
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
			
			bos.close();
			System.out.println("조회가 완료 되었습니다.");
						
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
	$post_str .= "&mem_id=sky1004"; // 회원 아이디
	$post_str .= "&emoney_use=1000"; // 마이너스 입력시 차감, 양수 입력시 충전
	$post_str .= "&bigo=서비스 충전"; // 비고를 입력해주세요.*/	
	String emoney_use = "https://ssl.anybuild.co.kr/API/member/emoney_use.php";
		
	/*$post_str = "api_key=333d4794fbf4b1a9d2b4e26b0091df59";
	$post_str .= "&mem_id=sky1004"; // 회원 아이디
	$post_str .= "&point_use=1000"; // 마이너스 입력시 차감, 양수 입력시 충전
	$post_str .= "&bigo=서비스 충전"; // 비고를 입력해주세요.*/
	String point_use = "https://ssl.anybuild.co.kr/API/member/point_use.php";
	
	/*	검색 하려면 아래 변수를 뒤쪽에 추가하시면 됩니다. 아래 검색조건을 입력하지 않는 경우 최근 DATA 20개만 출력 합니다.
	&mem_only=(ALL:전체 선택,Y:회원가입한회원만 선택,N:비회원만 선택)&platform=플랫폼&devicename=제조사명&devicemodel=제조사 모델명&deviceversion=플랫폼 버젼&hp_num=핸드폰번호
	?api_key=333d4794fbf4b1a9d2b4e26b0091df59 */
	String device_list = "https://ssl.anybuild.co.kr/API/app/device_list.php";	
	public JSONArray getDeviceList(){
				
		//환경설정
		String shop_key = Server_Config.getSHOPKEY();
		
		//접속 쇼핑몰정보 정의하기
		String shop_address = "https://ssl.anybuild.co.kr/API/app/device_list.php";	
		System.out.println(" 동기화를 시작합니다. 접속 주소 --> " + shop_address);
				
		System.out.println(shop_address);
		
		String shop_data = "";
		//try {
			shop_data = "api_key="+shop_key.toString()+"&mem_only=ALL";//+URLEncoder.encode("010-8619-7484", "UTF-8");
		//} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
		//	e1.printStackTrace();
		//}
		
		System.out.println(shop_data);
		
		
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
		
		JSONArray data = new JSONArray();
		
		//결과를 전송 합니다.
		//전송폼을 생성합니다.
		try {
			
			URL url = new URL(shop_address);
			HttpURLConnection shop_url = (HttpURLConnection)url.openConnection();
			
			shop_url.setRequestMethod("POST");					
			shop_url.setRequestProperty("Accept-Language", "ko-kr,ko;q=0.8,en-us;q=0.5,en;q=0.3");
			
			shop_url.setDoInput(true);
			shop_url.setDoOutput(true);
			
			System.out.println("전송상태 출력");			
			System.out.println(" URL : "+shop_url.getURL());			
			
			OutputStreamWriter output = new OutputStreamWriter(shop_url.getOutputStream());
			
			output.write(shop_data);				
			
			output.flush();			
			output.close();		

			//전송 결과 수신
			InputStreamReader isr = new InputStreamReader(shop_url.getInputStream(), "UTF-8");	
			JSONObject object = (JSONObject)JSONValue.parseWithException(isr);							
						
			isr.close();
			
			if(object.get("result_code").equals("OK")){				
				data = (JSONArray)object.get("result_data");
				System.out.println(data.toJSONString());
			}
			
			//결과출력
			System.out.println(object.toString());
			
			
			SimpleDateFormat formatter = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss", Locale.KOREA );
			Date currentTime = new Date ( );
			String dTime = formatter.format ( currentTime );
			
			String sb = "앱설치현황 출력 결과 호출위치 : 앱설치 현황"
						+ "전송 시간 : " + dTime + " \r\n"; 
					sb += "resultcode : "+object.get("result_code")+" result_msg : "+object.get("result_msg")+" result_cnt : "+object.get("result_cnt");					
			
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
			
			bos.close();
			System.out.println("조회가 완료 되었습니다.");
						
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();			
		}
		
		return data;
	}	
	
	
	/* 검색 하려면 아래 변수를 뒤쪽에 추가하시면 됩니다. 아래 검색조건을 입력하지 않는 경우 모든 APP 설치 고객에게 메세지 전송되므로 주의 하시기 바랍니다.
	&memlv=회원등급(빈값입력시 모두선택, 100,1000,1100....)&mem_id=회원아이디&mem_only=(ALL:전체 선택,Y:회원가입한회원만 선택,N:비회원만 선택)&platform=플랫폼&devicename=제조사명&devicemodel=제조사 모델명&deviceversion=플랫폼 버젼&hp_num=핸드폰번호	
	"api_key=333d4794fbf4b1a9d2b4e26b0091df59&push_title=게릴라이벤트&push_msg=지금 방문하시면 아메리카노1잔 서비스 제공합니다."
	+ "&push_link=/main&push_img_url=".urlencode('http://sskshop1.anybuild.com/thum_img/sskshop1/goods_img2/85b6f89b41cae26786ac72365fff771b_water_3afcaf174b6d740dcc3f8f859871184e_c1_w320_h320.jpg').""
	+ "&memlv=&mem_only=ALL&platform=&devicename=&devicemodel=&deviceversion=&hp_num=");*/
	String push_submit = "https://ssl.anybuild.co.kr/API/app/push_submit.php";
	public JSONArray setPushSubimt(HashMap<String, Object> push_list){
		
		//환경설정
		String shop_key = Server_Config.getSHOPKEY();
		
		//접속 쇼핑몰정보 정의하기
		String shop_address = "https://ssl.anybuild.co.kr/API/app/push_submit.php";	
		System.out.println(" 동기화를 시작합니다. 접속 주소 --> " + shop_address);				
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
			shop_data +="&push_title="+URLEncoder.encode(push_title, "UTF-8")+"&push_msg="+URLEncoder.encode(push_msg, "UTF-8")+"&push_link="+URLEncoder.encode(push_link, "UTF-8")
					+"&push_img_url="+URLEncoder.encode(push_url, "UTF-8")+"&mem_id="+URLEncoder.encode(mem_id, "UTF-8")				
					+"&memlv="+memlv+"&mem_only="+mem_only+"&platform=&devicename=&devicemodel=&deviceversion=&hp_num="+hp_num+"&event_idx="+event_idx;
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		/*shop_data = "api_key="+shop_key;//+"&hp=01090077611";//+URLEncoder.encode("010-8619-7484", "UTF-8");
		shop_data +="&push_title=게릴라이벤트&push_msg=지금 방문하시면 아메리카노1잔 서비스 제공합니다.&push_link=/main&push_img_url="
				+"&memlv=&mem_only=ALL&platform=&devicename=&devicemodel=&deviceversion=&hp_num=01090077611";*/
		System.out.println(shop_data);
		
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
		
		JSONArray data = new JSONArray();
		
		//결과를 전송 합니다.
		//전송폼을 생성합니다.
		try {
			
			URL url = new URL(shop_address);
			HttpURLConnection shop_url = (HttpURLConnection)url.openConnection();
			
			shop_url.setRequestMethod("POST");					
			shop_url.setRequestProperty("Accept-Language", "ko-kr,ko;q=0.8,en-us;q=0.5,en;q=0.3");
			
			shop_url.setDoInput(true);
			shop_url.setDoOutput(true);
			
			System.out.println("전송상태 출력");			
			System.out.println(" URL : "+shop_url.getURL());			
			
			OutputStreamWriter output = new OutputStreamWriter(shop_url.getOutputStream());
			
			output.write(shop_data);				
			
			output.flush();			
			output.close();		

			//전송 결과 수신
			InputStreamReader isr = new InputStreamReader(shop_url.getInputStream(), "UTF-8");	
			JSONObject object = (JSONObject)JSONValue.parseWithException(isr);							
						
			isr.close();
			
			if(object.get("result_code").equals("OK")){		
				//System.out.println(object.toJSONString());
				//data = (JSONArray)object.get("result_data");
				//System.out.println(data.toJSONString());
			}
			
			//결과출력
			System.out.println(object.toString());
			
			
			SimpleDateFormat formatter = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss", Locale.KOREA );
			Date currentTime = new Date ( );
			String dTime = formatter.format ( currentTime );
			
			String sb = "앱설치현황 출력 결과 호출위치 : 앱설치 현황"
						+ "전송 시간 : " + dTime + " \r\n"; 
					sb += "resultcode : "+object.get("result_code")+" result_msg : "+object.get("result_msg")+" result_cnt : "+object.get("result_log");					
			
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
			
			bos.close();
			System.out.println("조회가 완료 되었습니다.");
						
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();			
		}
		
		return data;
	}	
	
	
	//푸시 이벤트 목록 불러오기
	public JSONArray getPushEventList(){
		
			//환경설정
			String shop_key = Server_Config.getSHOPKEY();
			
			//접속 쇼핑몰정보 정의하기
			String shop_address = "https://ssl.anybuild.co.kr/API/app/push_event_list.php?api_key="+shop_key;	
			System.out.println(" 동기화를 시작합니다. 접속 주소 --> " + shop_address);
			
			//담을곳을 정의 합니다.
			JSONArray data = new JSONArray();
			
			//결과를 전송 합니다.
			//전송폼을 생성합니다.
			try {
				
				URL url = new URL(shop_address);
				HttpURLConnection shop_url = (HttpURLConnection)url.openConnection();
			
				shop_url.setDoInput(true);
				
				System.out.println("전송상태 출력");
				System.out.println(" URL : "+shop_url.getURL());			
								
				//전송 결과 수신
				InputStreamReader isr = new InputStreamReader(shop_url.getInputStream(), "UTF-8");	
				JSONObject object = (JSONObject)JSONValue.parseWithException(isr);							
							
				isr.close();
				
				if(object.get("result_code").equals("OK")){				
					data = (JSONArray)object.get("result_data");
					System.out.println(data.toJSONString());
				}
				
				//결과출력
				System.out.println(object.toString());
								
				SimpleDateFormat formatter = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss", Locale.KOREA );
				Date currentTime = new Date ( );
				String dTime = formatter.format ( currentTime );
				
				String sb = "푸시 이벤트 목록 -> 호출위치 : 푸시 이벤트 목록"
							+ "전송 시간 : " + dTime + " \r\n"; 
						sb += "resultcode : "+object.get("result_code")+" result_msg : "+object.get("result_msg")+" result_cnt : "+object.get("result_cnt");					
				
				char[] paser = sb.toCharArray();				
				System.out.println("조회가 완료 되었습니다.");
							
				//로그를 기록합니다.
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
	
	
	
	//api key 정의 하기	
	public void setShopContents(JSONArray json_data, String model){
		
		
			
	}
	
	
	//api key 정의 하기	
	public void setShopContents(String json_data, String model){
		
	
		
		
	}
	
	
	//로그파일 생성해서 남기기
	private void setLogFile(char[] paser){		
		
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
		
		//로그파일을 작성합니다.
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
	
	//쇼핑몰 정보 가져오기
	
	
	
	//쇼핑몰 업로드 하기
	
	
}
