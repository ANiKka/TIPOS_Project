import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;

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

import net.miginfocom.swing.MigLayout;

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
	private JComboBox combo_top_onmem;
	private JComboBox combo_top_appin;
	
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
		
		top_search();
		
		tab_member_list = new JTabbedPane(JTabbedPane.TOP);
		add(tab_member_list, BorderLayout.CENTER);
		
		tab_memberList();
		
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
		panel_top_search.add(btn_top_search, "cell 7 0,growx");
		
		JLabel label_top_onmem = new JLabel("\uC628\uB77C\uC778\uD68C\uC6D0");
		panel_top_search.add(label_top_onmem, "cell 0 1,alignx trailing");
		
		combo_top_onmem = new JComboBox();
		combo_top_onmem.setModel(new DefaultComboBoxModel(new String[] {"\uC804\uCCB4", "\uC5F0\uB3D9\uC911", "\uC5F0\uB3D9\uC548\uB428"}));
		panel_top_search.add(combo_top_onmem, "cell 1 1,growx");
		
		JLabel label_top_appin = new JLabel("\uC571\uC124\uCE58\uD68C\uC6D0");
		panel_top_search.add(label_top_appin, "cell 2 1,alignx trailing");
		
		combo_top_appin = new JComboBox();
		combo_top_appin.setModel(new DefaultComboBoxModel(new String[] {"\uC804\uCCB4", "\uC124\uCE58\uB428", "\uC124\uCE58\uC548\uB428"}));
		panel_top_search.add(combo_top_appin, "cell 3 1,growx");
		
		
	}

	
	//중간 멤버 검색 리스트
	private void tab_memberList() {
		// TODO Auto-generated method stub
		
		JPanel panel_offline_mem = new JPanel();
		panel_offline_mem.setBorder(new EmptyBorder(5, 5, 5, 5));
		tab_member_list.addTab("\uB9E4\uC7A5 \uD68C\uC6D0", null, panel_offline_mem, null);
		panel_offline_mem.setLayout(new BorderLayout(5, 0));
		
		JPanel panel_offmem_right = new JPanel();
		panel_offline_mem.add(panel_offmem_right, BorderLayout.EAST);
		
		JPanel panel_offmem_list = new JPanel();
		panel_offline_mem.add(panel_offmem_list, BorderLayout.CENTER);
		panel_offmem_list.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scroll_offmem_list = new JScrollPane();
		panel_offmem_list.add(scroll_offmem_list);
		
		String[] columnNames = {"순번", "회원명", "회원번호", "전화번호", "휴대폰번호", "온라인 회원", "앱설치 회원", "회원 ID", "알림수신여부", "알림수신번호"};
		DefaultTableModel dtm_offmem = new DefaultTableModel(null, columnNames){
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
		table_offmem_list.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);  //가로 스크롤		
				
		table_offmem_list.getTableHeader().setReorderingAllowed(false);  //이동불가		
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
		
    	// {"순번", "회원명", "회원번호", "전화번호", "휴대폰번호", "온라인 회원", "앱설치 회원", "회원 ID", "알림수신여부", "알림수신번호"};		
    	
		//컬럼넓이 조정
		table_offmem_list.getColumn("순번").setPreferredWidth(40);
    	
		//컬럼 정렬   	
		table_offmem_list.getColumn("순번").setCellRenderer(celAlignCenter);
		table_offmem_list.getColumn("회원명").setCellRenderer(celAlignCenter);
		table_offmem_list.getColumn("회원번호").setCellRenderer(celAlignCenter);
		table_offmem_list.getColumn("전화번호").setCellRenderer(celAlignCenter);
		table_offmem_list.getColumn("휴대폰번호").setCellRenderer(celAlignCenter);
		table_offmem_list.getColumn("온라인 회원").setCellRenderer(celAlignCenter);
		table_offmem_list.getColumn("앱설치 회원").setCellRenderer(celAlignCenter);
		table_offmem_list.getColumn("회원 ID").setCellRenderer(celAlignCenter);
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
		
		String text_search = text_top_search.getText();
		int appin = combo_top_onmem.getSelectedIndex();
		int shopmem = combo_top_appin.getSelectedIndex(); 
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
		
		String query = "Select * "
						 + "From Customer_Info " 
						 +	"Where ( Cus_Code Like '%"+text_search+"%' or Cus_Name Like '%"+text_search+"%' or Cus_Mobile Like '%"+text_search+"%' "
						 + "or Cus_Tel Like '%"+text_search+"%' or e_PhoneNumber Like '%"+text_search+"%') " 
						 + query_etc;
		
		ArrayList<HashMap<String, String>> result = ms_connect.connection(query);
		
		if(result == null){			
			JOptionPane.showMessageDialog(this, ms_connect.errMsg);
			return;
		}
		
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
