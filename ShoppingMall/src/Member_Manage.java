import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import net.miginfocom.swing.MigLayout;
import javax.swing.ScrollPaneConstants;
import java.awt.Dimension;
import javax.swing.JCheckBox;
import java.awt.Font;

public class Member_Manage extends JPanel implements ActionListener {

	/**
	 * @param 회원관리 프로그램 
	 */
	private static final long serialVersionUID = 584467216L;
	Ms_Connect ms_connect;
	Trans_ShopAPI t_api;
	
	private JPanel panel_top_search;
	private JTextField text_top_search;
	private JTabbedPane tab_member_list;
		
	private JTable table_offmem_list;
	private DefaultTableModel dtm_offmem;
	private JComboBox combo_top_onmem;
	private JComboBox combo_top_appin;
	
	private DefaultTableModel dtm_detailmem_list;
	private JTextField text_detail_search;
	private JTable table_dtailmem_list;
	
	//회원관리프로그램
	public Member_Manage(){
				
		setLayout(new BorderLayout(0, 5));
		setBorder(new EmptyBorder(5, 5, 5, 5));
				
		//시작
		init();
		
		//본문
		start();
		
	}
	
	//시작점
	private void init(){
		
		panel_top_search = new JPanel();
		panel_top_search.setBorder(new LineBorder(new Color(0, 0, 0)));
		add(panel_top_search, BorderLayout.NORTH);
		panel_top_search.setLayout(new MigLayout("", "[57px][][][][grow][][grow][100px]", "[15px][]"));
		
		//상단 검색 확면
		top_search();
		
		tab_member_list = new JTabbedPane(JTabbedPane.TOP);
		add(tab_member_list, BorderLayout.CENTER);
		
		//중앙 리스트 화면		
		tab_memberList();
		
		//우측 디테일 화면
		tabbed_detail();
	}
	
	//상단 검색 화면
	private void top_search() {
		
		
		// TODO Auto-generated method stub
		JLabel label_top_search = new JLabel("\uAC80\uC0C9\uC5B4");
		panel_top_search.add(label_top_search, "cell 0 0,alignx trailing");
		
		text_top_search = new JTextField();
		panel_top_search.add(text_top_search, "cell 1 0 5 1,growx");
		text_top_search.setColumns(10);
		
		JLabel lblid = new JLabel("\uD68C\uC6D0ID/\uD68C\uC6D0\uBC88\uD638/\uD68C\uC6D0\uBA85/\uC804\uD654\uBC88\uD638/\uD734\uB300\uD3F0\uBC88\uD638/\uC54C\uB9BC\uC218\uC2E0\uBC88\uD638");
		panel_top_search.add(lblid, "cell 6 0");
		
		JButton btn_top_search = new JButton("\uAC80\uC0C9");
		btn_top_search.addActionListener(this);
		panel_top_search.add(btn_top_search, "cell 7 0 1 2,grow");
		
		JLabel label_top_onmem = new JLabel("\uC628\uB77C\uC778\uD68C\uC6D0");
		panel_top_search.add(label_top_onmem, "cell 0 1,alignx trailing");
		
		combo_top_onmem = new JComboBox();
		combo_top_onmem.setModel(new DefaultComboBoxModel(new String[] {"\uC804\uCCB4", "\uC628\uB77C\uC778\uD68C\uC6D0", "\uAC00\uC785\uC548\uB428"}));
		panel_top_search.add(combo_top_onmem, "cell 1 1,growx");
		
		JLabel label_top_appin = new JLabel("\uC571\uC124\uCE58\uD68C\uC6D0");
		panel_top_search.add(label_top_appin, "cell 2 1,alignx trailing");
		
		combo_top_appin = new JComboBox();
		combo_top_appin.setModel(new DefaultComboBoxModel(new String[] {"\uC804\uCCB4", "\uC571\uC124\uCE58\uD68C\uC6D0", "\uC124\uCE58\uC548\uB428"}));
		panel_top_search.add(combo_top_appin, "cell 3 1,growx");
		
		
	}

	
	//중간 멤버 검색 리스트
	private void tab_memberList() {
		// TODO Auto-generated method stub
		
		JPanel panel_offline_mem = new JPanel();
		panel_offline_mem.setBorder(new EmptyBorder(5, 5, 5, 5));
		tab_member_list.addTab("\uB9E4\uC7A5 \uD68C\uC6D0", null, panel_offline_mem, null);
		panel_offline_mem.setLayout(new BorderLayout(5, 0));
		
		JPanel panel_offmem_list = new JPanel();
		panel_offline_mem.add(panel_offmem_list, BorderLayout.CENTER);
		panel_offmem_list.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scroll_offmem_list = new JScrollPane();
		panel_offmem_list.add(scroll_offmem_list);
		
		String[] columnNames = {"순번", "회원명", "회원번호", "포인트", "전화번호", "휴대폰번호", "온라인 회원", "앱설치 회원", "회원 ID", "온라인주문", "알림수신여부", "알림수신번호"};
		dtm_offmem = new DefaultTableModel(null, columnNames){
			@Override
			public boolean isCellEditable(int roe, int column){
				/**if(column == 4){
					return true;
				}else{
					return false;
				}*/
				return false;
			}
		};	
		
		DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
		celAlignCenter.setHorizontalAlignment(JLabel.CENTER);
		
		DefaultTableCellRenderer celAlignRight = new DefaultTableCellRenderer();
		celAlignRight.setHorizontalAlignment(JLabel.RIGHT);
		
		table_offmem_list = new JTable(dtm_offmem);
		scroll_offmem_list.setViewportView(table_offmem_list);
    	
		((DefaultTableCellRenderer)table_offmem_list.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(0);
		
		table_offmem_list.setRowHeight(25);
		//table_offmem_list.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);  //가로 스크롤		
				
		table_offmem_list.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);  //가로 스크롤
		table_offmem_list.getTableHeader().setReorderingAllowed(false);  //이동불가
		
		table_offmem_list.setAutoCreateRowSorter(true);
		TableRowSorter<TableModel> tsorter = new TableRowSorter<TableModel>(table_offmem_list.getModel());
		table_offmem_list.setRowSorter(tsorter);	
		
		table_offmem_list.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if(e.getClickCount() == 2){
				//	setGoodsList();		
				}
				
				/*if (e.getClickCount() == 2) {
					System.out.println("마우스 두번 클릭 됐습니다.");
					//수정된 사항이 있다면 다시 돌아 갑니다.
					if(change_Flags()) return;
					//상품을 우측으로 보냅니다.
					setGoodsDetail();
				} // 더블클릭
				if (e.getButton() == 3) { 
					System.out.println("오른쪽 마우스 클릭 됐습니다.");
					
				} // 오른쪽 클릭	 */
				}
		});
		
    	// {"순번", "회원명", "회원번호", "전화번호", "휴대폰번호", "온라인 회원", "앱설치 회원", "회원 ID", "온라인주문", "알림수신여부", "알림수신번호"};		
    	
		//컬럼넓이 조정
		table_offmem_list.getColumn("순번").setPreferredWidth(40);
    	
		//컬럼 정렬   	
		table_offmem_list.getColumn("순번").setCellRenderer(celAlignCenter);
		table_offmem_list.getColumn("회원명").setCellRenderer(celAlignCenter);
		table_offmem_list.getColumn("회원번호").setCellRenderer(celAlignCenter);
		table_offmem_list.getColumn("포인트").setCellRenderer(celAlignCenter);
		table_offmem_list.getColumn("전화번호").setCellRenderer(celAlignCenter);
		table_offmem_list.getColumn("휴대폰번호").setCellRenderer(celAlignCenter);
		table_offmem_list.getColumn("온라인 회원").setCellRenderer(celAlignCenter);
		table_offmem_list.getColumn("앱설치 회원").setCellRenderer(celAlignCenter);
		table_offmem_list.getColumn("회원 ID").setCellRenderer(celAlignCenter);
		table_offmem_list.getColumn("온라인주문").setCellRenderer(celAlignCenter);
		table_offmem_list.getColumn("알림수신여부").setCellRenderer(celAlignCenter);
		table_offmem_list.getColumn("알림수신번호").setCellRenderer(celAlignCenter);
		
		/*table_offmem_list.getColumn("주문상태번호").setWidth(0);
		table_offmem_list.getColumn("주문상태번호").setMinWidth(0);
		table_offmem_list.getColumn("주문상태번호").setMaxWidth(0);*/
    							
    	/*table_orderList.getColumn("주문상태").setPreferredWidth(120);
    	table_orderList.getColumn("회원ID").setPreferredWidth(120);
    	table_orderList.getColumn("주문자명").setPreferredWidth(80);
    	table_orderList.getColumn("휴대폰번호").setPreferredWidth(100);
    	table_orderList.getColumn("결제방식").setPreferredWidth(80);
    	table_orderList.getColumn("결제금액").setPreferredWidth(80);
    	table_orderList.getColumn("주문일").setPreferredWidth(100);*/
				
		
		JPanel panel_online_mem = new JPanel();
		panel_online_mem.setBorder(new EmptyBorder(5, 5, 5, 5));
		tab_member_list.addTab("\uC628\uB77C\uC778\uD68C\uC6D0", null, panel_online_mem, null);
		panel_online_mem.setLayout(new BorderLayout(5, 0));
		
		JPanel panel_onmem_right = new JPanel();
		panel_online_mem.add(panel_onmem_right, BorderLayout.EAST);
		
		JPanel panel_onmem_list = new JPanel();
		panel_online_mem.add(panel_onmem_list, BorderLayout.CENTER);
		
		JPanel panel_app_install = new JPanel();
		panel_app_install.setBorder(new EmptyBorder(5, 5, 5, 5));
		tab_member_list.addTab("\uC571\uC124\uCE58\uD68C\uC6D0", null, panel_app_install, null);
		panel_app_install.setLayout(new BorderLayout(5, 0));
		
		JPanel panel_appin_right = new JPanel();
		panel_app_install.add(panel_appin_right, BorderLayout.EAST);
		
		JPanel panel_appin_list = new JPanel();
		panel_app_install.add(panel_appin_list, BorderLayout.CENTER);
		
	}
	
	//본문
	private void start(){
		
		ms_connect = new Ms_Connect();
		t_api = new Trans_ShopAPI();		
		
	}

	//상단 검색 하기
	private void topSearchStart(){
		
		dtm_offmem.setRowCount(0);
		//검색 정보 불러오기
		String text_search = text_top_search.getText();
		int shopmem = combo_top_onmem.getSelectedIndex();
		int appin = combo_top_appin.getSelectedIndex(); 
		String query_etc = "";
		
		switch(shopmem){
		case 0:			
			break;
		case 1:
			query_etc += " And isnull(e_Member_yn, 0) = '1' ";
			break;
		case 2:
			query_etc += " And isnull(e_Member_yn, 0) = '0' ";
			break;			
		}
		
		switch(appin){
		case 0:			
			break;
		case 1:
			query_etc += " And isnull(e_Appinstall_yn, 0) = '1' ";
			break;
		case 2:
			query_etc += " And isnull(e_Appinstall_yn, 0) = '0' ";
			break;
		}
		
		String query = "Select Cus_Code, Cus_Name, Cus_Tel, Cus_Point, Cus_Mobile, isnull(e_AppInstall_YN, '0') e_AppInstall_YN, isnull(e_Member_YN, '0') e_Member_YN, e_Member_ID, isnull(e_PushSMS_YN, '0') e_PushSMS_YN, e_PhoneNumber, isnull(order_count, '0') as order_count "
						 + "From Customer_Info a Left Join "
						 + "(Select Count(*) As order_count, mem_id From e_orderList group by mem_id) b "
						 + "On a.e_member_id=b.mem_id "
						 +	"Where ( Cus_Code Like '%"+text_search+"%' or Cus_Name Like '%"+text_search+"%' or replace(Cus_Mobile, '-', '') Like '%"+text_search+"%' "						 
						 + "or Cus_Tel Like '%"+text_search+"%' or e_PhoneNumber Like '%"+text_search+"%' or e_Member_ID Like '%"+text_search+"%' ) " 
						 + query_etc;
		
		ms_connect.setMainSetting();
		ArrayList<HashMap<String, String>> result = ms_connect.connection(query);
		
		if(result == null){			
			JOptionPane.showMessageDialog(this, ms_connect.errMsg);
			return;
		}
		
		//{"순번", "회원명", "회원번호", "전화번호", "휴대폰번호", "온라인 회원", "앱설치 회원", "회원 ID", "온라인주문", "알림수신여부", "알림수신번호"};
		for(int i =0 ; i < result.size(); i++){
			
			HashMap<String, String> map = result.get(i);
			Vector<Object> v = new Vector<Object>();
			
			v.addElement(i+1);
			v.addElement(map.get("Cus_Name"));
			v.addElement(map.get("Cus_Code"));
			v.addElement(Double.parseDouble(map.get("Cus_Point")));
			v.addElement(map.get("Cus_Tel"));
			
			v.addElement(map.get("Cus_Mobile"));
			
			if(map.get("e_Member_YN").equals("1")){
				v.addElement("온라인회원");
			}else{
				v.addElement("가입안됨");
			}
			
			if(map.get("e_AppInstall_YN").equals("1")){
				v.addElement("앱설치회원");
			}else{
				v.addElement("설치안됨");
			}
			
			v.addElement(map.get("e_Member_ID"));
			
			v.addElement(map.get("order_count"));
			
			if(map.get("e_PushSMS_YN").equals("1")){
				v.addElement("수신함");
			}else{
				v.addElement("수신안함");
			}			
			
			v.addElement(map.get("e_PhoneNumber"));
						
			dtm_offmem.addRow(v);
		}
		
		
	}
		
	//우측 디테일 화면
	private void tabbed_detail(){
		
		
		
		JTabbedPane tab_east_detail = new JTabbedPane(JTabbedPane.TOP);
		tab_east_detail.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		add(tab_east_detail, BorderLayout.EAST);
		
		JPanel panel_detail_search = new JPanel();
		tab_east_detail.addTab("\uD68C\uC6D0\uAC80\uC0C9", null, panel_detail_search, null);
		panel_detail_search.setLayout(new MigLayout("", "[][][][][]", "[][][][grow]"));
		
		JLabel label_detail_search = new JLabel("\uD68C\uC6D0 \uC815\uBCF4 \uAC80\uC0C9(\uC628\uB77C\uC778 \uD68C\uC6D0/\uC571\uC124\uCE58\uD68C\uC6D0)");
		label_detail_search.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		panel_detail_search.add(label_detail_search, "cell 0 0 5 1,alignx center");
		
		JCheckBox chkbox_detail_offlinemem = new JCheckBox("\uB9E4\uC7A5\uD68C\uC6D0");
		panel_detail_search.add(chkbox_detail_offlinemem, "cell 0 1 2 1");
		
		JCheckBox chkbox_detail_onlinemem = new JCheckBox("\uC628\uB77C\uC778\uD68C\uC6D0");
		panel_detail_search.add(chkbox_detail_onlinemem, "cell 2 1");
		
		JCheckBox chkbox_detail_appin = new JCheckBox("\uC571\uC124\uCE58\uD68C\uC6D0");
		panel_detail_search.add(chkbox_detail_appin, "cell 3 1");
		
		JCheckBox chkbox_detail_shopping = new JCheckBox("\uC1FC\uD551\uBAB0\uAC80\uC0C9");
		panel_detail_search.add(chkbox_detail_shopping, "cell 4 1");
		
		JLabel label_detail_searchinfo = new JLabel("\uAC80\uC0C9\uC5B4");
		panel_detail_search.add(label_detail_searchinfo, "cell 0 2,alignx trailing");
		
		text_detail_search = new JTextField();
		panel_detail_search.add(text_detail_search, "cell 1 2 3 1,growx");
		text_detail_search.setColumns(10);
		
		JButton btn_detail_search = new JButton("\uD68C\uC6D0\uAC80\uC0C9");
		panel_detail_search.add(btn_detail_search, "cell 4 2");
		
		JScrollPane scrollPane_detailmem_list = new JScrollPane();
		scrollPane_detailmem_list.setPreferredSize(new Dimension(0, 0));
		scrollPane_detailmem_list.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_detailmem_list.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panel_detail_search.add(scrollPane_detailmem_list, "cell 0 3 5 1,grow");
		
		String[] columnNames = {"순번", "구분", "회원명", "회원ID", "휴대폰번호"};
		dtm_detailmem_list = new DefaultTableModel(null, columnNames){
			@Override
			public boolean isCellEditable(int roe, int column){
				/**if(column == 4){
					return true;
				}else{
					return false;
				}*/
				return false;
			}
		};	
		
		DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
		celAlignCenter.setHorizontalAlignment(JLabel.CENTER);
		
		DefaultTableCellRenderer celAlignRight = new DefaultTableCellRenderer();
		celAlignRight.setHorizontalAlignment(JLabel.RIGHT);
		
		table_dtailmem_list = new JTable(dtm_detailmem_list);
		scrollPane_detailmem_list.setViewportView(table_dtailmem_list);
		
		((DefaultTableCellRenderer)table_dtailmem_list.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(0);
		
		table_dtailmem_list.setRowHeight(25);
		table_dtailmem_list.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);  //가로 스크롤		
				
		table_dtailmem_list.getTableHeader().setReorderingAllowed(false);  //이동불가
		
		table_dtailmem_list.setAutoCreateRowSorter(true);
		TableRowSorter<TableModel> tsorter = new TableRowSorter<TableModel>(table_dtailmem_list.getModel());
		table_dtailmem_list.setRowSorter(tsorter);	
		
		table_dtailmem_list.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if(e.getClickCount() == 2){
				//	setGoodsList();		
				}
				
				/*if (e.getClickCount() == 2) {
					System.out.println("마우스 두번 클릭 됐습니다.");
					//수정된 사항이 있다면 다시 돌아 갑니다.
					if(change_Flags()) return;
					//상품을 우측으로 보냅니다.
					setGoodsDetail();
				} // 더블클릭
				if (e.getButton() == 3) { 
					System.out.println("오른쪽 마우스 클릭 됐습니다.");
					
				} // 오른쪽 클릭	 */
				}
		});
		
    	//  {"순번", "구분", "회원명", "회원ID", "휴대폰번호"};
    	
		//컬럼 정렬   	
		table_dtailmem_list.getColumn("순번").setCellRenderer(celAlignCenter);
		table_dtailmem_list.getColumn("구분").setCellRenderer(celAlignCenter);
		table_dtailmem_list.getColumn("회원명").setCellRenderer(celAlignCenter);
		table_dtailmem_list.getColumn("회원ID").setCellRenderer(celAlignCenter);
		table_dtailmem_list.getColumn("휴대폰번호").setCellRenderer(celAlignCenter);
				
		JPanel panel_detail_push = new JPanel();
		tab_east_detail.addTab("\uD478\uC26C\uC804\uC1A1", null, panel_detail_push, null);
		
		JPanel panel_detail_orderlist = new JPanel();
		tab_east_detail.addTab("\uAD6C\uB9E4\uB0B4\uC5ED", null, panel_detail_orderlist, null);
		
		
		
				
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		String commend = e.getActionCommand();
		switch(commend){
		case "검색":
			topSearchStart();
			break;
		}
		
	}
}
