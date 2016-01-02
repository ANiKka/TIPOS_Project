import java.awt.Component;
import java.awt.Cursor;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;

import javax.swing.JOptionPane;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;
	

public class TransferDataGoodsSet {
	
	private Ms_Connect ms_connect;
	private Component cpn;
	private String barcode;
	
	public TransferDataGoodsSet(Ms_Connect ms_connect, Component cpn, String barcode){
		
		this.ms_connect = ms_connect;
		this.cpn = cpn;
		this.barcode = barcode;
		
		start_shyc();
	}
	
	
	//강제 동기화 하기 
	@SuppressWarnings("unchecked")
	public void start_shyc(){
				
		if(Server_Config.getSHOPKEY().equals("")){			
			JOptionPane.showMessageDialog(this.cpn, "환경설정에서 API KEY를 입력해주세요~!");
			return;
		}
				
		String query = "Select X.Barcode, X.ShoppingMall_use, X.UpLoad, X.Shop_View, X.Sto_Use, X.Pro_Sto, X.img_path, "
						+"X.Img_path_use, X.Edit_Tran, X.Img_Name, X.Shop_MainCode, X.G_Name, X.Pur_Pri, "
						+"'Sell_Pri' = Case "
						+"When X.Length4 = '1' Then Z.H_SellPri "
						+"When X.Scale_Use = '1' Then Z.H_SellPri "
						+"Else X.Sell_Pri "
						+"End, "
						+"X.Real_Sto, X.Sale_Pur, X.Sale_Sell, X.Sale_Use, X.Scale_Use, X.Length4, X.Write_Date, X.Goods_Cate "
						+"From "
						+"( "
						+"Select C.Barcode, C.ShoppingMall_use, C.UpLoad, C.Shop_View, C.Sto_Use, C.Pro_Sto, C.img_path, C.Img_path_use, C.Edit_Tran, C.Img_Name, C.Shop_MainCode, D.G_Name, D.Pur_Pri, D.Sell_Pri, D.Real_Sto, D.Sale_Pur, D.Sale_Sell, D.Sale_Use, D.Scale_Use, D.Length4, D.Write_Date, D.Goods_Cate "
						+"From ( "  
						+		"Select * From goods_info Where edit_tran='1' and img_path <> '' and shoppingmall_use='1' and barcode='"+barcode.trim()+"' "  
						+		") C "
						+		"inner join ( "  
						+				"Select A.Barcode, A.G_Name, A.Pur_Pri, A.Sell_Pri, A.Real_Sto, A.Sale_Pur, A.Sale_Sell, A.Sale_Use, A.Scale_Use, A.Length4, A.Write_Date, B.Goods_NewCate as Goods_Cate "  
						+				"From ( "  
						+					"Select BarCode, G_Name, Pur_Pri, Sell_Pri, (L_Code+M_Code+S_Code) + Replicate('0', 8 - Len(L_Code+M_Code+S_Code) ) as Goods_Cate, Real_Sto, Sale_Pur, Sale_Sell, Sale_Use, Write_Date, Scale_use, 'Length4' =  CASE  WHEN Len(Barcode) = 4 THEN '1'  ELSE '0' END "  
						+					"From Goods " 
						+				") A "
						+				"inner join ( "  
						+					"Select  (L_Code+M_Code+SM_SCode) + Replicate('0', 8 - Len(L_Code+M_Code+SM_SCode)) as Goods_NewCate,(L_Code+M_Code+S_Code) + Replicate('0', 8 - Len(L_Code+M_Code+S_Code) ) as Goods_OldCate " 
						+					"From s_branch "
						+				") B "
						+				"on A.Goods_Cate=B.Goods_OldCate "  
						+		") D "
						+		"on C.Barcode=D.Barcode "
						+	") X left Join ( "
						+	"Select * From Hot_Key "
						+	") Z on X.Barcode=Z.H_Barcode ";
		
		//전송결과를 서버에 저장합니다. (성공시 Edit_Tran을 1->0 으로 변경)
		String resultQuery = "Update goods_info set UpLoad='1', Edit_Tran='0' where barcode='"+barcode+"' ";
		String queryIn = "";
				
		//전송할 상품을 검색 합니다.
		ms_connect.setMainSetting();
		ArrayList<HashMap<String, String>> temp_map = ms_connect.connection(query);
		
		//전송할 상품을 담아 둡니다.
		JSONArray json_map = new JSONArray();
				
		if(temp_map.size() <= 0 ){					
			JOptionPane.showMessageDialog(this.cpn, "업로드 상품 조회 실패");			
			return;
		}
				
		//검색 총 수량
		System.out.println("총 "+ temp_map.size() + "개의 상품이 검색 되었습니다.!!");
		
		//있는 만큼 동기화 시작하기		
		Iterator<HashMap<String, String>> iter = temp_map.iterator();
		
		//int i = 1;
		while(iter.hasNext()){
			HashMap<String, String> map =  iter.next();
			
			//임시저장소
			JSONObject obj = new JSONObject();
			
			obj.put("goods_cate", map.get("Goods_Cate"));
			obj.put("goods_img", map.get("img_path"));			
			obj.put("goods_name", map.get("G_Name"));
			obj.put("user_code", map.get("Barcode"));						
			obj.put("view_yn", map.get("Shop_View"));			
			obj.put("goods_regdate", map.get("Write_Date"));
			
			//상품이 행사 중이라면 행사가격으로 변경합니다.
			if(map.get("Sale_Use").trim().equals("1")){
				obj.put("sijung_price", map.get("Sell_Pri"));
				obj.put("goods_price", map.get("Sale_Sell"));
				//세일매입가
				obj.put("in_price", map.get("Sale_Pur"));
				
			}else{
				obj.put("goods_price", map.get("Sell_Pri"));	
				obj.put("sijung_price", "0");
				//일반매입가
				obj.put("in_price", map.get("Pur_Pri"));
			}
			
			//메인출력 코드를 입력합니다.
			String shop_maincode = map.get("Shop_MainCode");
			obj.put("main_code", shop_maincode);
						
			//재고 사용 유무를 체크합니다.
			if(map.get("Sto_Use").equals("1")){
				//재고가 0이하일때는 0개로 표시해줍니다.
				if(Integer.parseInt(map.get("Real_Sto")) < 0 ){
					obj.put("goods_stock", "0");
				}else{
					obj.put("goods_stock", map.get("Real_Sto"));
				}
			}else{
				//재고 사용안함				
				obj.put("goods_stock", "-1");
			}
			System.out.println(obj);
			json_map.add(obj);
			tranStart(json_map, resultQuery);				
		}
	}
	
	//전송함수를 분리 합니다.
	private void tranStart(JSONArray jsonData, String query){
		
		//환경설정
		String shop_key = Server_Config.getSHOPKEY();
		
		//접속 쇼핑몰정보 정의하기
		String shop_address = "https://ssl.anybuild.co.kr/API/goods/goods_insert.php";		
		System.out.println(" 동기화를 시작합니다. 접속 주소 --> " + shop_address);
		
		String shop_data = "";	
		
		try {
			shop_data = "api_key="+shop_key.toString()+"&json_data="+URLEncoder.encode(jsonData.toString(), "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//System.out.println(shop_data);
				
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
						
			SimpleDateFormat formatter = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss", Locale.KOREA );
			Date currentTime = new Date ( );
			String dTime = formatter.format ( currentTime );
			
			String sb = "전송 시간 : " + dTime + "\r\n" 
						+ "전송 위치 : 상품상세보기 수정 \r\n 전송 결과 \r\n" 
						+ object.toJSONString();
			
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
	
	
}
