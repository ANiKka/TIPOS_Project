
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

import javax.swing.UIManager;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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
import java.util.List;
import java.util.Locale;
import java.util.Vector;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class AllChangeDialog extends JDialog implements ActionListener, ItemListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField text_anstock;
	
	private JTable table;
	
	private JLabel label_result;
	private JButton btn_Cancel;
	private JButton btn_imagecall;
	private JButton btn_Change;
	
	private Thread thread;
	
	public JProgressBar progressbar;
	
	//디버그관련 
	private boolean DEBUGE = true;
	
	Ms_Connect ms_connect;
	
	//라디오 그룹 버튼
	private ButtonGroup[] bg_shoppingmall = {new ButtonGroup(), new ButtonGroup(), new ButtonGroup(), new ButtonGroup()};
	
	//체크박스 그룹
	private List<JCheckBox> chk_boxs; 
	private JPanel panel_maincode_title;
	
	
	/**
	 * Create the dialog.
	 */
	public AllChangeDialog(JTable table) {
		setResizable(false);
		
		this.table = table;
				
		setTitle("\uC77C\uAD04\uBCC0\uACBD");
		setBounds(0,0,399, 590);
		getContentPane().setLayout(new BorderLayout());	
		
		ImageIcon im = new ImageIcon(getClass().getClassLoader().getResource("Icon/dialog_allchange.png"));		
		
		setIconImage(im.getImage());
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		try {
		    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) { 
		    System.err.println("Cannot set look and feel:" + e.getMessage ()); 
		}
        
        Toolkit tk = Toolkit.getDefaultToolkit();		
        Dimension dm = tk.getScreenSize();
        Dimension fm = this.getSize();
        this.setLocation((int) (dm.getWidth() / 2 - fm.getWidth() / 2),
                (int) (dm.getHeight() / 2 - fm.getHeight() / 2));		
		
		ms_connect = new Ms_Connect();
        ms_connect.setMainSetting();
		
		//쇼핑몰 연동그룹
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\uC1FC\uD551\uBAB0\uC5F0\uB3D9", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(12, 10, 359, 55);
		contentPanel.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JRadioButton radio_shopmall = new JRadioButton("\uC120\uD0DD\uC548\uD568");
		radio_shopmall.setSelected(true);
		panel.add(radio_shopmall);
		radio_shopmall.setActionCommand("선택안함");
		
		JRadioButton radio_shoppingmall_on = new JRadioButton("\uC5F0\uB3D9\uD568");
		panel.add(radio_shoppingmall_on);		
		radio_shoppingmall_on.setActionCommand("연동함");
		
		JRadioButton radio_shoppingmall_off = new JRadioButton("\uC5F0\uB3D9\uC548\uD568");
		panel.add(radio_shoppingmall_off);
		radio_shoppingmall_off.setActionCommand("연동안함");
				
		bg_shoppingmall[0].add(radio_shopmall);
		bg_shoppingmall[0].add(radio_shoppingmall_off);
		bg_shoppingmall[0].add(radio_shoppingmall_on);
		
		//진열여부 그룹
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "\uC1FC\uD551\uBAB0\uD310\uB9E4", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		panel_1.setBounds(12, 75, 359, 55);
		contentPanel.add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JRadioButton radio_view = new JRadioButton("\uC120\uD0DD\uC548\uD568");
		radio_view.setSelected(true);
		panel_1.add(radio_view);
		
		JRadioButton radio_view_on = new JRadioButton("\uC9C4\uC5F4\uD568");
		panel_1.add(radio_view_on);
		
		JRadioButton radio_view_off = new JRadioButton("\uC9C4\uC5F4\uC548\uD568");
		panel_1.add(radio_view_off);
		
		radio_view.setActionCommand("선택안함");
		radio_view_on.setActionCommand("진열함");
		radio_view_off.setActionCommand("진열안함");
		
		bg_shoppingmall[1].add(radio_view);
		bg_shoppingmall[1].add(radio_view_on);
		bg_shoppingmall[1].add(radio_view_off);
		
		//폴더선택 여부 그룹
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\uC774\uBBF8\uC9C0\uD3F4\uB354", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_2.setBounds(12, 365, 359, 55);
		contentPanel.add(panel_2);
		
		JRadioButton radio_image = new JRadioButton("\uC120\uD0DD\uC548\uD568");
		radio_image.setSelected(true);
		panel_2.add(radio_image);
		
		JRadioButton radio_dandock = new JRadioButton("\uB2E8\uB3C5\uD3F4\uB354");
		panel_2.add(radio_dandock);
		
		JRadioButton radio_gong = new JRadioButton("\uACF5\uC6A9\uD3F4\uB354");
		panel_2.add(radio_gong);
		
		radio_image.setActionCommand("선택안함");
		radio_dandock.setActionCommand("단독폴더");
		radio_gong.setActionCommand("공용폴더");
				
		bg_shoppingmall[2].add(radio_image);
		bg_shoppingmall[2].add(radio_dandock);
		bg_shoppingmall[2].add(radio_gong);
		
		//재고사용 여부 그룹
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\uC7AC\uACE0\uC0AC\uC6A9\uC5EC\uBD80", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_3.setBounds(12, 140, 359, 55);
		contentPanel.add(panel_3);
		
		JRadioButton radio_stock = new JRadioButton("\uC120\uD0DD\uC548\uD568");
		radio_stock.setSelected(true);
		panel_3.add(radio_stock);
		
		JRadioButton radio_stock_on = new JRadioButton("\uC0AC\uC6A9\uD568");
		panel_3.add(radio_stock_on);
		
		JRadioButton radio_stock_off = new JRadioButton("\uC0AC\uC6A9\uC548\uD568");
		panel_3.add(radio_stock_off);
		
		radio_stock.setActionCommand("선택안함");
		radio_stock_on.setActionCommand("사용함");
		radio_stock_off.setActionCommand("사용안함");
		
		bg_shoppingmall[3].add(radio_stock);
		bg_shoppingmall[3].add(radio_stock_off);
		bg_shoppingmall[3].add(radio_stock_on);
		
		//안전재고 등록
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\uC548\uC804\uC7AC\uACE0\uB4F1\uB85D", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_4.setBounds(12, 205, 359, 55);
		contentPanel.add(panel_4);
		
		JLabel lblNewLabel = new JLabel("\uC218\uB7C9\uB4F1\uB85D  :  ");
		panel_4.add(lblNewLabel);
		
		text_anstock = new JTextField();
		text_anstock.setHorizontalAlignment(SwingConstants.CENTER);
		panel_4.add(text_anstock);
		text_anstock.setColumns(5);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new TitledBorder(null, "\uC774\uBBF8\uC9C0\uC5F0\uB3D9", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		//panel_5.setLayout(null);
		panel_5.setBounds(12, 432, 359, 60);
		contentPanel.add(panel_5);
		panel_5.setLayout(null);
				
		progressbar = new JProgressBar(0, 1000);
		progressbar.setBounds(18, 36, 329, 16);
		progressbar.setStringPainted(true);		
		panel_5.add(progressbar);
		
		label_result = new JLabel("\uCD1D   \uAC74");
		label_result.setHorizontalAlignment(SwingConstants.RIGHT);
		label_result.setFont(new Font("맑은 고딕", Font.PLAIN, 11));
		label_result.setBounds(18, 17, 329, 15);
		panel_5.add(label_result);
		
		btn_Change = new JButton("\uC120\uD0DD\uC635\uC158\uBCC0\uACBD");
		btn_Change.setActionCommand("OK");
		btn_Change.setBounds(12, 502, 108, 50);
		btn_Change.addActionListener(this);
		contentPanel.add(btn_Change);
		
		btn_Cancel = new JButton("닫기");
		btn_Cancel.setActionCommand("Close");
		btn_Cancel.setBounds(132, 502, 102, 50);
		btn_Cancel.addActionListener(this);
		contentPanel.add(btn_Cancel);
				
		btn_imagecall = new JButton("\uC774\uBBF8\uC9C0\uC5F0\uB3D9\uC2DC\uC791");
		btn_imagecall.setBounds(246, 502, 125, 50);
		contentPanel.add(btn_imagecall);
		btn_imagecall.setActionCommand("Image");
				
		panel_maincode_title = new JPanel();
		panel_maincode_title.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\uBA54\uC778\uC0C1\uD488 \uCD9C\uB825\uCF54\uB4DC", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_maincode_title.setBounds(12, 270, 359, 85);
		panel_maincode_title.setLayout(new BorderLayout());
		contentPanel.add(panel_maincode_title);
					
		chk_boxs = new ArrayList<JCheckBox>();
		
		getMainCode();
		
		btn_imagecall.addActionListener(this);
	}
	
	private void btnChange(boolean change){
		
		if(change){			
			btn_Cancel.setActionCommand("Cancel");
			btn_Cancel.setText("중지");
			
			btn_Change.setEnabled(false);
			btn_imagecall.setEnabled(false);
			
		}else{
			btn_Cancel.setActionCommand("Close");
			btn_Cancel.setText("닫기");
			
			btn_Change.setEnabled(true);
			btn_imagecall.setEnabled(true);
			
		}
		
	}
				
	//이미지 연동 매칭 
	private void setFtp_Image_select(){
				
		//버튼을 바꿉니다.
		btnChange(true);
		
		thread = new Thread(){
						
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
				// 테이블의 선택된 행의 인덱스 번호 취득
				int[] row = table.getSelectedRows();
				
				//int col = table.getColumnCount();
				
				//선택된 상품들의 바코드를 불러온다 
				//선택된 상품들의 공용폴더 사용여부를 확인하고 사용한다면 
				//이미지 서버에서 이미지를 불러온다.		
				String temp_image = "";
				String query = "Select * From Ftp_Image where ";
				String result_text = "총 "+row.length+" 건중 ";
				String result_text_com = "건 매칭 됨";
				String gubun = bg_shoppingmall[2].getSelection().getActionCommand();
								
				if(gubun.equals("공용폴더")){
					query += "path_gubun='1' ";					
				}else{
					query += "path_gubun='0' ";					
				}
				
				progressbar.setValue(0);
				progressbar.setMaximum(row.length);				
				label_result.setText(result_text);
								
				ArrayList<HashMap<String, String>> query_list = new ArrayList<HashMap<String, String>>();
				int count=0;			
				int total_count = 0;
				int result_count = 0;
				//선택된 상품 바코드 불러오기
				for(int j = 0; j < row.length; j++){
					
					Vector<Object> temp_one = new Vector<Object>();		
					
					temp_one.add(table.getModel().getValueAt(row[j], 1));			
					temp_one.add(table.getModel().getValueAt(row[j], 20));
					temp_one.add(table.getModel().getValueAt(row[j], 21));
					
					if(temp_one.get(1).equals(gubun.toString().trim()) && temp_one.get(2).equals(" ") || temp_one.get(2).equals("")){
						temp_image += "'"+temp_one.get(0)+"', ";	
						count++;
						total_count++;	
					}
					
					label_result.setText(result_text+total_count+"건 매칭시도 "+result_count+"건 매칭완료");
					
					//1000개씩 끊어서 매칭 합니다.
					if(count == 1000){
					
						temp_image = temp_image.substring(0, temp_image.length()-2);		
						query += "and barcode in ("+temp_image+")";
						
						ms_connect.setImageSetting();
						query_list = ms_connect.connection(query);
						System.out.println("총 매칭 수량 : "+query_list.size());
						result_text_com = String.valueOf(query_list.size());
					
						temp_image = "";
					
						Iterator<HashMap<String, String>> iter = query_list.iterator();
						ArrayList<String> query_result = new ArrayList<String>();
					
						while(iter.hasNext()){					
							HashMap<String, String> map = iter.next();
							String temp = "Update Goods_Info Set Img_Path='http://14.38.161.45:7080/main_goods/"+map.get("Barcode")+".jpg', Edit_Tran='1' Where Barcode='"+map.get("Barcode")+"' ";
							query_result.add(temp);
							result_count++;
							label_result.setText(result_text+total_count+"건 매칭시도 "+result_count+"건 매칭중");							
						}
												
						ms_connect.setMainSetting();
						int a = ms_connect.connect_update(query_result);
						
						//완료후 초기화
						switch(a){
						case 0:
						
							//초기화
							query = "Select * From Ftp_Image where path_gubun='1' ";				
							label_result.setText(result_text+total_count+"건 매칭시도 "+result_count+"건 매칭완료");
							
							break;
						default:
							JOptionPane.showMessageDialog(AllChangeDialog.this, "등록하지 못했습니다.");
							this.stop();
							btnChange(false);
							break;
						}				
						
						count = 0;
					}
										
					progressbar.setValue(j+1);
					
					try{
						Thread.sleep(5);
					}catch(InterruptedException eff){
						eff.printStackTrace();
					}
					
				}				
				
				if(!temp_image.isEmpty()){
					
					temp_image = temp_image.substring(0, temp_image.length()-2);		
					query += "and barcode in ("+temp_image+")";
					
					ms_connect.setImageSetting();
					query_list = ms_connect.connection(query);
					result_text_com = String.valueOf(query_list.size());
					System.out.println("총 매칭 수량 : "+query_list.size());
					
					Iterator<HashMap<String, String>> iter = query_list.iterator();
					ArrayList<String> query_result = new ArrayList<String>();
							
					while(iter.hasNext()){						
						HashMap<String, String> map = iter.next();
						String temp = "Update Goods_Info Set Img_Path='http://14.38.161.45:7080/"+map.get("Path").trim()+"/"+map.get("Barcode").trim()+"."+map.get("Ext").trim()+"', Edit_Tran='1' Where Barcode='"+map.get("Barcode").trim()+"' ";
						query_result.add(temp);	
						result_count++;
						label_result.setText(result_text+total_count+"건 매칭시도 "+result_count+"건 매칭중");						
					}
										
					ms_connect.setMainSetting();
					int a = ms_connect.connect_update(query_result);					
					
					//완료후 초기화
					switch(a){
					case 0:
						//초기화											
						label_result.setText(result_text+total_count+"건 매칭시도 "+result_count+"건 매칭완료");						
						break;
					default:
						JOptionPane.showMessageDialog(AllChangeDialog.this, "등록하지 못했습니다.");
						this.stop();
						btnChange(false);
						break;
					}			
				}
				
				//버튼을 원상복구 합니다.
				btnChange(false);
								
			}
		};
		
		thread.start();
		
	}
			
	private void setAllSave(){
			
		this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
		
		if(DEBUGE){
			System.out.println("변경을 시작합니다.");
		}
		// 테이블의 선택된 행의 인덱스 번호 취득
		int[] row = table.getSelectedRows();
		//int col = table.getColumnCount();
		
		ArrayList<String> query_list = new ArrayList<String>();
		
		//수정여루를 확인 합니다.
		boolean edit_check_info = false;
		
		//선택 수량만큼 변경된 내용을 저장합니다.
		for(int j = 0; j < row.length; j++){
			
			Vector<Object> temp = new Vector<Object>();
			String goods_info_query = "Update Goods_Info Set Edit_Tran='1' ";						
												
			temp.add(table.getModel().getValueAt(row[j], 1));
			temp.add(table.getModel().getValueAt(row[j], 17));
						
			String barcode = temp.get(0).toString(); //바코드
			String goods_connect = temp.get(1).toString(); //연동여부
			
			//옵션 불러오기
			//0. 쇼핑몰연동여부/1.진열여부/2.재고사용여부/3.이미지폴더/
			switch(bg_shoppingmall[0].getSelection().getActionCommand()){			
			case "연동함":
				goods_info_query += ", ShoppingMall_Use='1' ";
				edit_check_info = true;
				break;				
				
			case "연동안함":
				if(goods_connect.equals("업로드안됨")){
					goods_info_query += ", ShoppingMall_Use='0' ";				
					edit_check_info = true;
				}
				break;
			}
			
			switch(bg_shoppingmall[1].getSelection().getActionCommand()){				
			case "진열함":
				goods_info_query += ", shop_view='1' ";					
				edit_check_info = true;
				break;
				
			case "진열안함":
				goods_info_query += ", shop_view='0' ";					
				edit_check_info = true;
				break;
			}
			
			switch(bg_shoppingmall[3].getSelection().getActionCommand()){
			case "사용함":
				goods_info_query += ", sto_use='1' ";					
				edit_check_info = true;
				break;
				
			case "사용안함":
				goods_info_query += ", sto_use='0' ";					
				edit_check_info = true;
				break;
			}
			
			switch(bg_shoppingmall[2].getSelection().getActionCommand()){			
			case "단독폴더":
				goods_info_query += ", img_path_use='0' ";					
				edit_check_info = true;
				break;
				
			case "공용폴더":
				goods_info_query += ", img_path_use='1' ";					
				edit_check_info = true;
				break;
			}
						
			//안전재고 설정
			if(text_anstock.getText().length() > 0){
				goods_info_query += ", pro_sto='"+text_anstock.getText()+"' ";
				edit_check_info = true;
			}
			
			//메인출력 코드(선택 되어있는 인덱스를 불러와서 결정합니다.)
			/*switch(cb_maincode_list.getSelectedIndex()){
			case 0:
				//선택 된게 없습니다. 변경을 하지 않습니다.
				break;
			case 1:
				//선택되어 있는 내용을 삭제합니다.
				goods_info_query += ", Shop_MainCode='' ";
				edit_check_info = true;
				break;
			default:
				//선택 옵션을 불러와서 저장합니다.
				String select_item = cb_maincode_list.getSelectedItem().toString();				
				select_item = select_item.substring(select_item.indexOf("[")+1, select_item.lastIndexOf("]"));
				goods_info_query += ", Shop_MainCode='"+select_item+"' ";
				edit_check_info = true;
				break;	
			}*/
			
			Iterator<JCheckBox> itr = chk_boxs.iterator();
			int i = 0;
			String select_item = "";
			while(itr.hasNext()){
				JCheckBox jcb = (JCheckBox)itr.next();
				if(jcb.isSelected()){
					select_item += jcb.getName()+"|";
					i++;
				}
			}
			System.out.println(select_item);
			select_item = select_item.substring(0, select_item.length()-1);
			System.out.println(select_item);
			if(i == 1){				
				if(select_item.equals("none")){
					//선택되어 있는 내용을 삭제합니다.
					goods_info_query += ", Shop_MainCode='' ";
					edit_check_info = true;
				}else{
					goods_info_query += ", Shop_MainCode='"+select_item+"' ";
					edit_check_info = true;
				}
			} 
			
			if(i > 1){
				//선택 옵션을 불러와서 저장합니다.				
				goods_info_query += ", Shop_MainCode='"+select_item+"' ";
				edit_check_info = true;				
			}
						
			//마무리 Goods_Info
			if(edit_check_info){
				goods_info_query += " where barcode='"+barcode+"' ";
				query_list.add(goods_info_query);	
				if(DEBUGE){
					System.out.println(goods_info_query);
				}
			}
		}
		
		//쿼리 실행
		if(edit_check_info){
			ms_connect.setMainSetting();
			int result = ms_connect.connect_update(query_list);
			switch(result){		
			case 0:
				JOptionPane.showMessageDialog(this, "변경 완료 했습니다.");
				this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				break;
			default:
				JOptionPane.showMessageDialog(this, "DB 연결에 실패 했습니다.");
				this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				break;
			}		
		}else{
			JOptionPane.showMessageDialog(this, "변경할 옵션 상품이 없습니다.");
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}
	}
	
	public void open(){
		setVisible(true);
	}

	//쇼핑몰 메인코드를 불러 옵니다.
	public void getMainCode(){
		
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
			
			String sb = "전송 시간 : " + dTime + "메인코드조회 결과 \r\n" ;
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
		
		Iterator itr =data.iterator();
	
		JScrollPane scrollpane = new JScrollPane();		
		JPanel panel = new JPanel();
		panel.setAutoscrolls(true);
		JCheckBox chkbox = new JCheckBox("출력안함");
		chkbox.setName("none");
		chkbox.addItemListener(this);
		
		panel.add(chkbox);
		//scrollpane.add(chkbox);
		chk_boxs.add(chkbox);
		
		while(itr.hasNext()){			
			JSONObject temp = (JSONObject)itr.next();
			//정렬합니다.
			
			JCheckBox chk_box = new JCheckBox(temp.get("subject").toString());
			chk_box.setName(temp.get("group_code").toString());
			chk_box.addItemListener(this);
			//scrollpane.add(chk_box);
			panel.add(chk_box);
			chk_boxs.add(chk_box);
		}		
		
		scrollpane.setViewportView(panel);
		
		panel_maincode_title.add(scrollpane, BorderLayout.CENTER);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		switch(e.getActionCommand()){
		case "OK":
			setAllSave();
			break;
		case "Close":
			this.dispose();
			break;
		case "Cancel":
			if(thread.isAlive()){
				thread.stop();
			}
			
			btnChange(false);	
						
			break;
		case "Image":
			
			if(bg_shoppingmall[2].getSelection().getActionCommand().equals("선택안함")){
				JOptionPane.showMessageDialog(AllChangeDialog.this, "이미지 폴더를 선택해 주세요");
				return;
			}
			setFtp_Image_select();
			break;
		}
		
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		JCheckBox jcb = (JCheckBox)e.getItem();
		System.out.println(e.getStateChange());
		if(e.getStateChange() == 1){
			if( jcb.getName().equals("none")){
				for(int index = 0; index < chk_boxs.size(); index++){
					JCheckBox jcb_list = chk_boxs.get(index);
					if(!jcb_list.getName().equals("none")){
						jcb_list.setSelected(false);
					}
				}
			}else{
				for(int index = 0; index < chk_boxs.size(); index++){
					JCheckBox jcb_list = chk_boxs.get(index);
					if(jcb_list.getName().equals("none") && jcb_list.isSelected()){
						jcb_list.setSelected(false);
					}
				}
			}
		}
	}
}


