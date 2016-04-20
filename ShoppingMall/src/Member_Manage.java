import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import net.miginfocom.swing.MigLayout;
import javax.swing.JTextArea;

public class Member_Manage extends JPanel implements ActionListener, KeyListener {

	/**
	 * @param 회원관리 프로그램 
	 */
	private static final long serialVersionUID = 584467216L;
	Ms_Connect ms_connect;
	Trans_ShopAPI t_api;
	
	private JPanel panel_top_search;
	private JTextField text_member_search;
	private JTabbedPane tab_member_list;
		
	private DefaultTableModel dtm_offmem;
	private JTable table_offmem_list;	
	
	private JComboBox combo_member_onmem;
	private JComboBox combo_member_appin;
	
	private DefaultTableModel dtm_detailmem_list;
	private JTextField text_detail_search;
	private JTable table_dtailmem_list;
	
	private JCheckBox chkbox_detail_hpnum;
	private JCheckBox chkbox_detail_memid;
	private JCheckBox chkbox_detail_code;
	private JScrollPane scroll_offmem_list;
	private JScrollPane scrollPane_detailmem_list;
	private JTable table_online_memberlist;
	
	
	private DefaultTableModel dtm_online_member;
	private JScrollPane scrollPane_online_member;
	private JTextField text_onlinetop_memid;
	private JTextField text_onlinetop_hp;
	private JTextField text_onlinetop_idx;
	private JTextField text_onlinetop_page;
	
	private DefaultTableModel dtm_appin_list;
	private JTable table_appin_list;
	private JScrollPane scrollPane_appin_list;
	private JTextField text_appin_hpnum;
	private JTextField text_appin_page;
	private JTextField text_detail_hp;
	private JTextField text_detail_memid;
	private JTextField text_detail_idx;
	private JLabel label_top_offmem_total;
	private JLabel label_top_shoptotal;
	private JLabel label_top_apptotal;
	private JTable table_detail_orderlist;
	private JPanel panel_detail_orderlist;
		
	private DefaultTableModel dtm_detail_orderlist;
	private JTabbedPane tab_east_detail;
	private JScrollPane scrollPane_detail_order;
	private JTextField text_detail_ordername;
	private JTextField text_detail_orderid;
	private JTextField text_detail_orderhp;
	private JTextField text_detail_ordercount;
	private JTextField text_detail_orderpri;
	private JTextField text_push_title;
	
	
	//회원관리프로그램
	public Member_Manage(){
				
		setLayout(new BorderLayout(0, 5));
		setBorder(new EmptyBorder(5, 5, 5, 5));
				
		//본문
		start();
		
		//시작
		init();
				
	}
	
	//시작점
	private void init(){
		
		panel_top_search = new JPanel();
		panel_top_search.setBorder(new LineBorder(new Color(0, 0, 0)));
		add(panel_top_search, BorderLayout.NORTH);
		panel_top_search.setLayout(new MigLayout("", "[100px][][][50px][100px][][][50px][][][][grow][]", "[30px]"));
		
		//상단 정보창
		topinfomation();		
		//상단 정보 검색
		topSearchInfomation();
		
		tab_member_list = new JTabbedPane(JTabbedPane.TOP);
		add(tab_member_list, BorderLayout.CENTER);
		
		//중앙 리스트 화면		
		tab_memberList();
				
		//앱설치회원 검색 리스트
		tab_appin_list();
				
		//온라인 회원 검색 리스트
		tab_online_member();
		
		//우측 디테일 화면
		tabbed_detail();
		
		
		
	}
	
	//상단 조회 부분 디자인화면
	private void topinfomation(){
		
		JLabel label_top_info = new JLabel("\uB9E4\uC7A5 \uCD1D\uD68C\uC6D0 :");
		label_top_info.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		panel_top_search.add(label_top_info, "cell 0 0,growx,aligny center");
		
		label_top_offmem_total = new JLabel("0");
		label_top_offmem_total.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		label_top_offmem_total.setBorder(new EmptyBorder(0, 15, 0, 10));
		label_top_offmem_total.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_top_search.add(label_top_offmem_total, "cell 1 0,growx");
		
		JLabel label_top_offend = new JLabel("\uBA85");
		panel_top_search.add(label_top_offend, "cell 2 0");
		
		JLabel label_top_appinfo = new JLabel("\uC571\uC124\uCE58 \uCD1D\uD68C\uC6D0 :");
		label_top_appinfo.setHorizontalAlignment(SwingConstants.TRAILING);
		label_top_appinfo.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		panel_top_search.add(label_top_appinfo, "cell 4 0,growx,aligny center");
		
		label_top_apptotal = new JLabel("0");
		label_top_apptotal.setHorizontalAlignment(SwingConstants.RIGHT);
		label_top_apptotal.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		label_top_apptotal.setBorder(new EmptyBorder(0, 15, 0, 10));
		panel_top_search.add(label_top_apptotal, "cell 5 0,growx");
		
		JLabel label_top_append = new JLabel("\uBA85");
		panel_top_search.add(label_top_append, "cell 6 0");
		
		JButton btn_top_renew = new JButton("\uC0C8\uB85C\uACE0\uCE68");
		btn_top_renew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				topSearchInfomation();
			}
		});
		
		JLabel label_top_shopinfo = new JLabel("\uC628\uB77C\uC778 \uCD1D\uD68C\uC6D0 :");
		label_top_shopinfo.setHorizontalAlignment(SwingConstants.TRAILING);
		label_top_shopinfo.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		panel_top_search.add(label_top_shopinfo, "cell 8 0,growx,aligny center");
		
		label_top_shoptotal = new JLabel("0");
		label_top_shoptotal.setHorizontalAlignment(SwingConstants.RIGHT);
		label_top_shoptotal.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		label_top_shoptotal.setBorder(new EmptyBorder(0, 15, 0, 10));
		panel_top_search.add(label_top_shoptotal, "cell 9 0,growx");
		
		JLabel label_top_onlineend = new JLabel("\uBA85");
		panel_top_search.add(label_top_onlineend, "cell 10 0");
		panel_top_search.add(btn_top_renew, "cell 12 0,growy");
		
	}
	
	//중간 멤버 검색 리스트
	private void tab_memberList() {
		// TODO Auto-generated method stub
		
		JPanel panel_offline_mem = new JPanel();
		panel_offline_mem.setBorder(new EmptyBorder(5, 5, 5, 5));
		tab_member_list.addTab("\uB9E4\uC7A5 \uD68C\uC6D0", null, panel_offline_mem, null);
		panel_offline_mem.setLayout(new BorderLayout(5, 5));
		
		JPanel panel_offmem_list = new JPanel();
		panel_offline_mem.add(panel_offmem_list, BorderLayout.CENTER);
		panel_offmem_list.setLayout(new BorderLayout(0, 0));
		
		scroll_offmem_list = new JScrollPane();
		panel_offmem_list.add(scroll_offmem_list);
		
		String[] columnNames = {"순번", "회원명", "회원번호", "포인트", "전화번호", "휴대폰번호", "온라인 회원", "앱설치 회원", "회원 ID", "온라인주문", "알림수신여부", "알림수신번호"};
		dtm_offmem = new DefaultTableModel(null, columnNames){			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1568435434999L;
			@Override
			public boolean isCellEditable(int roe, int column){
				/**if(column == 4){
					return true;
				}else{
					return false;
				}*/
				return false;
			}
			public Class getColumnClass(int column) {
				 /*Class returnValue;
				 if ((column >= 0) && (column < getColumnCount())) {
				    returnValue = getValueAt(0, column).getClass();
				 } else {
				    returnValue = Object.class;
				 }
				 return (returnValue==null?Object.class:returnValue.getClass());*/
				     
			    Object value=this.getValueAt(0,column);
			    return (value==null?Object.class:value.getClass());			   				
			}			
		};
		
		DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
		celAlignCenter.setHorizontalAlignment(JLabel.CENTER);
		
		DefaultTableCellRenderer celAlignRight = new DefaultTableCellRenderer();
		celAlignRight.setHorizontalAlignment(JLabel.RIGHT);
		
		//table_offmem_list = new JTable(dtm_offmem);
		table_offmem_list = new JTable(dtm_offmem){
            public boolean getScrollableTracksViewportWidth()
            {
                return getPreferredSize().width < getParent().getWidth();
            }
        };
		scroll_offmem_list.setViewportView(table_offmem_list);
    	
		((DefaultTableCellRenderer)table_offmem_list.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(0);
		
		table_offmem_list.setRowHeight(25);
		//table_offmem_list.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);  //가로 스크롤		
				
		table_offmem_list.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);  //가로 스크롤
		table_offmem_list.getTableHeader().setReorderingAllowed(false);  //이동불가
		
		/*table_offmem_list.setAutoCreateRowSorter(true);
		TableRowSorter<TableModel> tsorter_main = new TableRowSorter<TableModel>(table_offmem_list.getModel());
		table_offmem_list.setRowSorter(tsorter_main);*/	
		
		table_offmem_list.setAutoCreateRowSorter(true);
		
		JPanel panel_member_top = new JPanel();
		panel_member_top.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_offline_mem.add(panel_member_top, BorderLayout.NORTH);
		panel_member_top.setLayout(new MigLayout("", "[][][][][][grow][]", "[][]"));
				
		
		// TODO Auto-generated method stub
		JLabel label_member_search = new JLabel("\uAC80\uC0C9\uC5B4");
		panel_member_top.add(label_member_search, "cell 0 0,alignx trailing");
		
		text_member_search = new JTextField();
		panel_member_top.add(text_member_search, "cell 1 0 3 1,growx");
		text_member_search.setColumns(10);
		
		JLabel label_member_searchinfo = new JLabel("\uD68C\uC6D0\uBA85/\uC804\uD654\uBC88\uD638/\uB4F1\uB4F1");
		panel_member_top.add(label_member_searchinfo, "cell 4 0");
		
		JLabel lblid = new JLabel((String) null);
		panel_member_top.add(lblid, "cell 5 0");
		
		JButton btn_member_search = new JButton("\uAC80\uC0C9");
		panel_member_top.add(btn_member_search, "cell 6 0 1 2,growy");
		
		JLabel label_member_onmem = new JLabel("\uC628\uB77C\uC778\uD68C\uC6D0");
		panel_member_top.add(label_member_onmem, "cell 0 1");
		
		combo_member_onmem = new JComboBox();
		panel_member_top.add(combo_member_onmem, "cell 1 1");
		combo_member_onmem.setModel(new DefaultComboBoxModel(new String[] {"\uC804\uCCB4", "\uC628\uB77C\uC778\uD68C\uC6D0", "\uAC00\uC785\uC548\uB428"}));
		
		JLabel label_member_appin = new JLabel("\uC571\uC124\uCE58\uD68C\uC6D0");
		panel_member_top.add(label_member_appin, "cell 2 1");
		
		combo_member_appin = new JComboBox();
		panel_member_top.add(combo_member_appin, "cell 3 1");
		combo_member_appin.setModel(new DefaultComboBoxModel(new String[] {"\uC804\uCCB4", "\uC571\uC124\uCE58\uD68C\uC6D0", "\uC124\uCE58\uC548\uB428"}));
		btn_member_search.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				topSearchStart();
			}
		});
		
		text_member_search.addKeyListener(this);;
		
		
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
					//setGoodsList();
					
					int tab = tab_east_detail.getSelectedIndex();
					if( tab == 0){
						
						detailRenew();						
						int row = table_offmem_list.getSelectedRow();						
						if(chkbox_detail_hpnum.isSelected()){
							text_detail_hp.setText((String)table_offmem_list.getValueAt(row, 5));
						}
						
						if(chkbox_detail_memid.isSelected()){
							text_detail_memid.setText((String)(table_offmem_list.getValueAt(row, 8) == null? "": table_offmem_list.getValueAt(row, 8)));
						}																
						searchMember();		
					}else if(tab == 1){
						
						getOrderListToServer();
						
					}else if (tab==2){
						
					}
					
					
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
		
		//컬럼 조정
		for(String str:columnNames){
			table_offmem_list.getColumn(str).setCellRenderer(celAlignCenter);
		}
				
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
		
	}
		
	//앱설치회원 조회 리스트
	private void tab_appin_list(){
		
		JPanel panel_app_install = new JPanel();
		panel_app_install.setBorder(new EmptyBorder(5, 5, 5, 5));
		tab_member_list.addTab("\uC1FC\uD551\uBAB0 \uC571\uC124\uCE58\uD68C\uC6D0", null, panel_app_install, null);
		panel_app_install.setLayout(new BorderLayout(5, 5));
		
		JPanel panel_appin_right = new JPanel();
		panel_appin_right.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_app_install.add(panel_appin_right, BorderLayout.NORTH);
		panel_appin_right.setLayout(new MigLayout("", "[][][][][][][][][grow][]", "[][]"));
		
		JLabel label_appin_hpnum = new JLabel("\uD734\uB300\uD3F0\uBC88\uD638");
		panel_appin_right.add(label_appin_hpnum, "cell 0 0,alignx trailing");
		
		text_appin_hpnum = new JTextField();
		panel_appin_right.add(text_appin_hpnum, "cell 1 0 4 1,growx");
		text_appin_hpnum.setColumns(10);
		
		JButton btn_appin_search = new JButton("\uAC80\uC0C9");
		btn_appin_search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				text_appin_page.setText("1");
				searchAppInstall();
			}
		});
		panel_appin_right.add(btn_appin_search, "cell 9 0 1 2,growy");
		
		JLabel label_appin_page = new JLabel("\uD398\uC774\uC9C0");
		panel_appin_right.add(label_appin_page, "cell 0 1,alignx trailing");
		
		JButton btn_appin_downpage = new JButton("<<");
		btn_appin_downpage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//입력 숫자를 불러옵니다.
				int page = Integer.parseInt(text_appin_page.getText());
				
				if(page <= 1){
					JOptionPane.showMessageDialog(Member_Manage.this, "첫 페이지 입니다.");
					text_appin_page.setText("1");
					return;
				}
				
				text_appin_page.setText(String.valueOf(page-1));				
				searchAppInstall();
				
			}
		});
		panel_appin_right.add(btn_appin_downpage, "cell 1 1");
		
		text_appin_page = new JTextField();
		text_appin_page.setEditable(false);
		text_appin_page.setHorizontalAlignment(SwingConstants.CENTER);
		text_appin_page.setText("1");
		panel_appin_right.add(text_appin_page, "cell 2 1 2 1,growx");
		text_appin_page.setColumns(5);
		
		JButton btn_appin_uppage = new JButton(">>");
		btn_appin_uppage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int total_page = Integer.parseInt(label_top_apptotal.getText());
				int page = Integer.parseInt(text_appin_page.getText());
				
				int page_end = total_page/50 +1;
				
				if(page_end == page){
					JOptionPane.showMessageDialog(Member_Manage.this, "마지막 페이지 입니다.");
					return;
				}else{
					text_appin_page.setText(String.valueOf(page+1));
				}
				
				searchAppInstall();
			}
		});
		panel_appin_right.add(btn_appin_uppage, "cell 4 1");
		
		JButton btn_appin_allselect = new JButton("\uC804\uCCB4\uC120\uD0DD");		
		btn_appin_allselect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table_appin_list.selectAll();				
			}
		});
		panel_appin_right.add(btn_appin_allselect, "cell 6 1");
		
		JButton btn_appin_shoptran = new JButton("\uB9E4\uC7A5\uC73C\uB85C \uC804\uC1A1");
		btn_appin_shoptran.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				setAppInstallTransferData();
			}
		});
		panel_appin_right.add(btn_appin_shoptran, "cell 7 1");
		
		JPanel panel_appin_list = new JPanel();
		panel_app_install.add(panel_appin_list, BorderLayout.CENTER);
		panel_appin_list.setLayout(new BorderLayout(0, 0));
		
		scrollPane_appin_list = new JScrollPane();
		panel_appin_list.add(scrollPane_appin_list, BorderLayout.CENTER);
		
		
		String[] column = {"순번", "고유번호", "회원ID", "회원등급명", "플랫폼", "토큰", "기기 고유값", "제조사명", "모델명", "플랫폼 버젼", "핸드폰 번호", "APP설치 시간"	};
		dtm_appin_list = new DefaultTableModel(null, column){			
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 5242485347L;
			@Override
			public boolean isCellEditable(int roe, int column){
				/**if(column == 4){
					return true;
				}else{
					return false;
				}*/
				return false;
			}
			
			public Class getColumnClass(int column) {
				 /*Class returnValue;
				 if ((column >= 0) && (column < getColumnCount())) {
				    returnValue = getValueAt(0, column).getClass();
				 } else {
				    returnValue = Object.class;
				 }
				 return (returnValue==null?Object.class:returnValue.getClass());*/
				     
			    Object value=this.getValueAt(0,column);
			    return (value==null?Object.class:value.getClass());			   				
			}
		};
		
		DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
		celAlignCenter.setHorizontalAlignment(JLabel.CENTER);
		
		DefaultTableCellRenderer celAlignRight = new DefaultTableCellRenderer();
		celAlignRight.setHorizontalAlignment(JLabel.RIGHT);
		
		//table_appin_list = new JTable(dtm_appin_list);
		table_appin_list = new JTable(dtm_appin_list){
            public boolean getScrollableTracksViewportWidth()
            {
                return getPreferredSize().width < getParent().getWidth();
            }
        };
		scrollPane_appin_list.setViewportView(table_appin_list);
		
		
		((DefaultTableCellRenderer)table_appin_list.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(0);
		
		table_appin_list.setRowHeight(25);
		//table_offmem_list.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);  //가로 스크롤		
				
		table_appin_list.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);  //가로 스크롤
		table_appin_list.getTableHeader().setReorderingAllowed(false);  //이동불가
		
		/*table_offmem_list.setAutoCreateRowSorter(true);
		TableRowSorter<TableModel> tsorter_main = new TableRowSorter<TableModel>(table_offmem_list.getModel());
		table_offmem_list.setRowSorter(tsorter_main);*/	
		
		table_appin_list.setAutoCreateRowSorter(true);;
		
		
		table_appin_list.addMouseListener(new MouseListener() {
			
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
					//setGoodsList();
					
					detailRenew();
					
					int row = table_appin_list.getSelectedRow();
					
					if(chkbox_detail_hpnum.isSelected()){
						text_detail_hp.setText((String)table_appin_list.getValueAt(row, 10));
					}
					
					if(chkbox_detail_memid.isSelected()){
						text_detail_memid.setText((String)table_appin_list.getValueAt(row, 2));						
					}
					
					if(chkbox_detail_code.isSelected()){
						text_detail_idx.setText((String)table_appin_list.getValueAt(row, 1));
					}
					
					searchMember();
							
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
		    			
		//{"순번", "고유번호", "회원ID", "회원등급명", "플랫폼", "토큰", "기기 고유값", "제조사명", "모델명", "플랫폼 버젼", "핸드폰 번호", "APP설치 시간"	};
		
		//컬럼넓이 조정
		table_appin_list.getColumn("순번").setPreferredWidth(40);
    	
		//컬럼 조정
		for(String str:column){
			table_appin_list.getColumn(str).setCellRenderer(celAlignCenter);
		}
		
	}
	
	//중간 온라인 회원리스트
	private void tab_online_member(){
		
		JPanel panel_online_mem = new JPanel();
		panel_online_mem.setBorder(new EmptyBorder(5, 5, 5, 5));
		tab_member_list.addTab("\uC1FC\uD551\uBAB0 \uC628\uB77C\uC778\uD68C\uC6D0", null, panel_online_mem, null);
		panel_online_mem.setLayout(new BorderLayout(5, 5));
		
		JPanel panel_onmem_right = new JPanel();
		panel_onmem_right.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_online_mem.add(panel_onmem_right, BorderLayout.NORTH);
		panel_onmem_right.setLayout(new MigLayout("", "[][][][][][][][][][grow][]", "[][]"));
		
		JLabel label_onlinetop_memid = new JLabel("\uD68C\uC6D0 \uC544\uC774\uB514");
		panel_onmem_right.add(label_onlinetop_memid, "cell 0 0,alignx trailing");
		
		text_onlinetop_memid = new JTextField();
		panel_onmem_right.add(text_onlinetop_memid, "cell 1 0 3 1,growx");
		text_onlinetop_memid.setColumns(10);
		
		JLabel label_onlinetop_hp = new JLabel("\uD734\uB300\uD3F0\uBC88\uD638");
		panel_onmem_right.add(label_onlinetop_hp, "cell 4 0,alignx trailing");
		
		text_onlinetop_hp = new JTextField();
		panel_onmem_right.add(text_onlinetop_hp, "cell 5 0 2 1,growx");
		text_onlinetop_hp.setColumns(10);
		
		JLabel label_onlinetop_idx = new JLabel("\uD68C\uC6D0\uACE0\uC720\uBC88\uD638");
		panel_onmem_right.add(label_onlinetop_idx, "cell 7 0,alignx trailing");
		
		text_onlinetop_idx = new JTextField();
		panel_onmem_right.add(text_onlinetop_idx, "cell 8 0,growx");
		text_onlinetop_idx.setColumns(10);
		
		JButton btn_onlinetop_search = new JButton("\uAC80\uC0C9");
		btn_onlinetop_search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				text_onlinetop_page.setText("1");
				searchOnlineMember();
			}
		});
		panel_onmem_right.add(btn_onlinetop_search, "cell 10 0 1 2,growy");
		
		JLabel label_onlinetop_page = new JLabel("\uD398\uC774\uC9C0");
		panel_onmem_right.add(label_onlinetop_page, "cell 0 1,alignx trailing");
		
		JButton btn_online_downpage = new JButton("<<");
		btn_online_downpage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int page = Integer.parseInt(text_onlinetop_page.getText());				
				if(page <= 1){
					JOptionPane.showMessageDialog(Member_Manage.this, "첫 페이지 입니다.");
					text_onlinetop_page.setText("1");
					return;
				}
				
				text_onlinetop_page.setText(String.valueOf(page-1));
				searchOnlineMember();
				
			}
		});
		panel_onmem_right.add(btn_online_downpage, "cell 1 1");
		
		text_onlinetop_page = new JTextField();
		text_onlinetop_page.setEditable(false);
		text_onlinetop_page.setHorizontalAlignment(SwingConstants.CENTER);
		text_onlinetop_page.setText("1");
		panel_onmem_right.add(text_onlinetop_page, "cell 2 1,growx");
		text_onlinetop_page.setColumns(5);
		
		JButton btn_online_uppage = new JButton(">>");
		btn_online_uppage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int total_page = Integer.parseInt(label_top_shoptotal.getText());
				int page = Integer.parseInt(text_onlinetop_page.getText());
				
				int page_end = total_page/50 +1;
				
				if(page_end == page){
					JOptionPane.showMessageDialog(Member_Manage.this, "마지막 페이지 입니다.");
					return;
				}else{
					text_onlinetop_page.setText(String.valueOf(page+1));
				}
				searchOnlineMember();
			}
		});
		panel_onmem_right.add(btn_online_uppage, "cell 3 1");
		
		JButton btn_onlinetop_renew = new JButton("\uC0C8\uB85C\uC785\uB825");
		btn_onlinetop_renew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				text_onlinetop_hp.setText("");
				text_onlinetop_memid.setText("");
				text_onlinetop_idx.setText("");
				text_onlinetop_page.setText("1");
				
				text_onlinetop_hp.requestFocus();
			}
		});
		
		JButton btn_onlinetop_allselect = new JButton("\uC804\uCCB4\uC120\uD0DD");
		btn_onlinetop_allselect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table_online_memberlist.selectAll();
			}
		});
		panel_onmem_right.add(btn_onlinetop_allselect, "cell 5 1,growx");
		
		JButton btn_onlinetop_shoptran = new JButton("\uB9E4\uC7A5\uC73C\uB85C \uC804\uC1A1");
		btn_onlinetop_shoptran.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setShopTransferData();
			}
		});
		panel_onmem_right.add(btn_onlinetop_shoptran, "cell 6 1 2 1");
		panel_onmem_right.add(btn_onlinetop_renew, "cell 8 1,growx");
		
		JPanel panel_onmem_list = new JPanel();
		panel_online_mem.add(panel_onmem_list, BorderLayout.CENTER);
		panel_onmem_list.setLayout(new BorderLayout(0, 0));
		
		scrollPane_online_member = new JScrollPane();
		panel_onmem_list.add(scrollPane_online_member, BorderLayout.CENTER);
		
		String[] column = {"순번", "고유번호", "회원ID", "회원명", "등급코드", "회원등급명", "휴대폰번호", "전화번호", "닉네임", "이메일주소", "우편번호", "주소", "나머지주소", "이머니", "포인트", 
				"매장연동요청", "매장회원번호", "고객검색정보", "연동상태"};
		
		dtm_online_member = new DefaultTableModel(null, column){
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 5242485347L;
			@Override
			public boolean isCellEditable(int roe, int column){
				/**if(column == 4){
					return true;
				}else{
					return false;
				}*/
				return false;
			}
			
			public Class getColumnClass(int column) {
				 /*Class returnValue;
				 if ((column >= 0) && (column < getColumnCount())) {
				    returnValue = getValueAt(0, column).getClass();
				 } else {
				    returnValue = Object.class;
				 }
				 return (returnValue==null?Object.class:returnValue.getClass());*/
				     
			    Object value=this.getValueAt(0,column);
			    return (value==null?Object.class:value.getClass());			   				
			}
		};
		
		DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
		celAlignCenter.setHorizontalAlignment(JLabel.CENTER);
		
		DefaultTableCellRenderer celAlignRight = new DefaultTableCellRenderer();
		celAlignRight.setHorizontalAlignment(JLabel.RIGHT);
		
		//table_online_memberlist = new JTable(dtm_online_member);
		table_online_memberlist = new JTable(dtm_online_member){
            public boolean getScrollableTracksViewportWidth()
            {
                return getPreferredSize().width < getParent().getWidth();
            }
        };
		scrollPane_online_member.setViewportView(table_online_memberlist);
		
		((DefaultTableCellRenderer)table_online_memberlist.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(0);
		
		table_online_memberlist.setRowHeight(25);
		//table_offmem_list.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);  //가로 스크롤		
				
		table_online_memberlist.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);  //가로 스크롤
		table_online_memberlist.getTableHeader().setReorderingAllowed(false);  //이동불가
		
		/*table_offmem_list.setAutoCreateRowSorter(true);
		TableRowSorter<TableModel> tsorter_main = new TableRowSorter<TableModel>(table_offmem_list.getModel());
		table_offmem_list.setRowSorter(tsorter_main);*/	
		
		table_online_memberlist.setAutoCreateRowSorter(true);;
		
		
		table_online_memberlist.addMouseListener(new MouseListener() {
			
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
					//setGoodsList();
					detailRenew();
					int row = table_online_memberlist.getSelectedRow();
					
					if(chkbox_detail_hpnum.isSelected()){
						text_detail_hp.setText((String)table_online_memberlist.getValueAt(row, 6));
					}
					
					if(chkbox_detail_memid.isSelected()){
						text_detail_memid.setText((String)table_online_memberlist.getValueAt(row, 2));						
					}
					
					if(chkbox_detail_code.isSelected()){
						text_detail_idx.setText((String)table_online_memberlist.getValueAt(row, 1));						
					}
										
					searchMember();
							
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
		    			
		//{"순번", "고유번호", "회원ID", "회원명", "등급코드", "회원등급명", "휴대폰번호", "전화번호", "닉네임", "이메일주소", "우편번호", 
		//"주소1", "주소2", "이머니", "포인트", "매장연동요청", "매장회원번호", "고객검색정보", "연동상태"};
		
		//컬럼넓이 조정
		table_online_memberlist.getColumn("순번").setPreferredWidth(40);
    	
		//컬럼 조정
		for(String str:column){
			table_online_memberlist.getColumn(str).setCellRenderer(celAlignCenter);
		}
				
		table_online_memberlist.getColumn("닉네임").setWidth(0);
		table_online_memberlist.getColumn("닉네임").setMinWidth(0);
		table_online_memberlist.getColumn("닉네임").setMaxWidth(0);
		
		
	}
	
	//본문
	private void start(){		
		ms_connect = new Ms_Connect();
		t_api = new Trans_ShopAPI();
	}

	//상단 검색 하기
	private void topSearchStart(){
		
		this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
		
		//dtm_offmem.setRowCount(0);		
		refreshTable(dtm_offmem);
		setScrollReSet("Main");
		
		//검색 정보 불러오기
		String text_search = text_member_search.getText();
		int shopmem = combo_member_onmem.getSelectedIndex();
		int appin = combo_member_appin.getSelectedIndex(); 
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
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
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
						
		this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}
		
	//앱설치회원 검색
	private void searchAppInstall(){
		
		this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
		
		//초기화
		refreshTable(dtm_appin_list);
				
		String hp_num = text_appin_hpnum.getText();
		String page = text_appin_page.getText();
		
		JSONArray json = t_api.getDeviceList(hp_num, page);
				
		if( json.size() <= 0){
			JOptionPane.showMessageDialog(this, "검색 결과가 없습니다.");
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			return;
		}
		
		for(int i=0; i < json.size(); i++){
			JSONObject obj = (JSONObject)json.get(i);
			Vector<Object> v = new Vector<Object>();
			v.add(i+1);
			v.addElement(obj.get("idx"));
			v.add(obj.get("mem_id"));
			v.add(obj.get("lv_subject"));
			v.add(obj.get("platform"));
			v.add(obj.get("devicetoken"));
			v.add(obj.get("deviceuid"));
			v.add(obj.get("devicename"));
			v.add(obj.get("devicemodel"));
			v.add(obj.get("deviceversion"));
			v.add(obj.get("hp_num"));
			v.add(obj.get("reg_time"));
			
			dtm_appin_list.addRow(v);
		}
		
		this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		
		
	}
	
	//우측 디테일 화면
	private void tabbed_detail(){
				
		 tab_east_detail = new JTabbedPane(JTabbedPane.TOP);
		tab_east_detail.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		add(tab_east_detail, BorderLayout.EAST);
		
		JPanel panel_detail_search = new JPanel();
		tab_east_detail.addTab("\uD68C\uC6D0\uAC80\uC0C9", null, panel_detail_search, null);
		panel_detail_search.setLayout(new MigLayout("", "[100px][150px][]", "[][][][][][grow]"));
		
		JLabel label_detail_search = new JLabel("\uD68C\uC6D0 \uC815\uBCF4 \uAC80\uC0C9(\uC628\uB77C\uC778 \uD68C\uC6D0/\uC571\uC124\uCE58\uD68C\uC6D0)");
		label_detail_search.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		panel_detail_search.add(label_detail_search, "cell 0 0 3 1,alignx center");
		
		JLabel label_detail_hp = new JLabel("\uD734\uB300\uD3F0\uBC88\uD638");
		panel_detail_search.add(label_detail_hp, "cell 0 1,alignx trailing");
		
		text_detail_hp = new JTextField();
		panel_detail_search.add(text_detail_hp, "cell 1 1,growx");
		text_detail_hp.setColumns(10);
		
		chkbox_detail_hpnum = new JCheckBox("\uAC00\uC838\uC624\uAE30");
		chkbox_detail_hpnum.setSelected(true);
		panel_detail_search.add(chkbox_detail_hpnum, "cell 2 1");
		
		JLabel label_detail_memid = new JLabel("\uD68C\uC6D0ID");
		panel_detail_search.add(label_detail_memid, "cell 0 2,alignx trailing");
		
		text_detail_memid = new JTextField();
		panel_detail_search.add(text_detail_memid, "cell 1 2,growx");
		text_detail_memid.setColumns(10);
		
		chkbox_detail_memid = new JCheckBox("\uAC00\uC838\uC624\uAE30");
		panel_detail_search.add(chkbox_detail_memid, "cell 2 2");
		
		JLabel label_detail_idx = new JLabel("\uACE0\uC720\uBC88\uD638");
		panel_detail_search.add(label_detail_idx, "cell 0 3,alignx trailing");
		
		text_detail_idx = new JTextField();
		panel_detail_search.add(text_detail_idx, "cell 1 3,growx");
		text_detail_idx.setColumns(10);
		
		chkbox_detail_code = new JCheckBox("\uAC00\uC838\uC624\uAE30");
		panel_detail_search.add(chkbox_detail_code, "cell 2 3");
		
		JLabel label_detail_searchinfo = new JLabel("\uAC80\uC0C9\uC5B4");
		panel_detail_search.add(label_detail_searchinfo, "cell 0 4,alignx trailing");
		
		text_detail_search = new JTextField();
		panel_detail_search.add(text_detail_search, "cell 1 4,growx");
		text_detail_search.setColumns(10);
		
		JButton btn_detail_search = new JButton("\uD68C\uC6D0\uAC80\uC0C9");
		btn_detail_search.addActionListener(this);
		panel_detail_search.add(btn_detail_search, "cell 2 4");
		
		scrollPane_detailmem_list = new JScrollPane();
		scrollPane_detailmem_list.setPreferredSize(new Dimension(0, 0));
		scrollPane_detailmem_list.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_detailmem_list.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panel_detail_search.add(scrollPane_detailmem_list, "cell 0 5 3 1,grow");
		
		String[] columnNames = {"순번", "구분", "회원명", "회원ID", "휴대폰번호"};
		dtm_detailmem_list = new DefaultTableModel(null, columnNames){
			/**
			 * 
			 */
			private static final long serialVersionUID = 15648433354488L;

			@Override
			public boolean isCellEditable(int roe, int column){
				/**if(column == 4){
					return true;
				}else{
					return false;
				}*/
				return false;
			}
			
			public Class getColumnClass(int column) {
				 Class returnValue;
				 if ((column >= 0) && (column < getColumnCount())) {
				    returnValue = getValueAt(0, column).getClass();
				 } else {
				    returnValue = Object.class;
				 }
				 return returnValue;
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
					
					//setGoodsList();
					switch(tab_member_list.getSelectedIndex()){
					case 0:
						setLeftListChooseSave();
						break;
					}
					
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
		
		//컬럼넓이 조정
		table_dtailmem_list.getColumn("순번").setPreferredWidth(25);
		table_dtailmem_list.getColumn("구분").setPreferredWidth(35);
		table_dtailmem_list.getColumn("회원명").setPreferredWidth(40);
		table_dtailmem_list.getColumn("회원ID").setPreferredWidth(50);
    	
		//컬럼 정렬   	
		table_dtailmem_list.getColumn("순번").setCellRenderer(celAlignCenter);
		table_dtailmem_list.getColumn("구분").setCellRenderer(celAlignCenter);
		table_dtailmem_list.getColumn("회원명").setCellRenderer(celAlignCenter);
		table_dtailmem_list.getColumn("회원ID").setCellRenderer(celAlignCenter);
		table_dtailmem_list.getColumn("휴대폰번호").setCellRenderer(celAlignCenter);
		
		panel_detail_orderlist = new JPanel();
		tab_east_detail.addTab("\uC1FC\uD551\uBAB0 \uAD6C\uB9E4\uB0B4\uC5ED", null, panel_detail_orderlist, null);
		
		JPanel panel_detail_push = new JPanel();
		tab_east_detail.addTab("\uD478\uC26C\uC804\uC1A1", null, panel_detail_push, null);
		panel_detail_push.setLayout(new MigLayout("", "[][grow][]", "[30px][][150px][grow][30]"));
		
		JLabel label_push_info = new JLabel("\uBA54\uC138\uC9C0 \uC804\uC1A1");
		label_push_info.setFont(new Font("나눔고딕", Font.BOLD, 15));
		label_push_info.setHorizontalAlignment(SwingConstants.CENTER);
		panel_detail_push.add(label_push_info, "cell 0 0 3 1,growx");
		
		JLabel label_push_title = new JLabel("\uC81C\uBAA9");
		panel_detail_push.add(label_push_title, "cell 0 1,alignx trailing");
		
		text_push_title = new JTextField();
		text_push_title.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		panel_detail_push.add(text_push_title, "cell 1 1 2 1,growx");
		text_push_title.setColumns(10);
		
		JLabel label_push_msg = new JLabel("\uBA54\uC138\uC9C0");
		panel_detail_push.add(label_push_msg, "cell 0 2,aligny top");
		
		JPanel panel_push_msg = new JPanel();
		panel_detail_push.add(panel_push_msg, "cell 1 2 2 1,grow");
		panel_push_msg.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_push_msg = new JScrollPane();
		panel_push_msg.add(scrollPane_push_msg);
		
		JTextArea textArea_push_msg = new JTextArea();
		textArea_push_msg.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		textArea_push_msg.setRows(7);
		textArea_push_msg.setColumns(22);
		scrollPane_push_msg.setViewportView(textArea_push_msg);
		
		JLabel label_push_content = new JLabel("\uBCF8\uBB38");
		panel_detail_push.add(label_push_content, "cell 0 3,alignx trailing,aligny top");
		
		JPanel panel_push_content = new JPanel();
		panel_detail_push.add(panel_push_content, "cell 1 3 2 1,grow");
		panel_push_content.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_push_content = new JScrollPane();
		panel_push_content.add(scrollPane_push_content, BorderLayout.CENTER);
		
		JTextArea textArea_push_content = new JTextArea();
		textArea_push_content.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		scrollPane_push_content.setViewportView(textArea_push_content);
		
		JButton btn_psuh_renew = new JButton("\uC0C8\uB85C\uACE0\uCE68");
		btn_psuh_renew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				text_push_title.setText("");
				textArea_push_msg.setText("");
				textArea_push_content.setText("");
			}
		});
		panel_detail_push.add(btn_psuh_renew, "cell 1 4,growy");
		
		JButton btn_push_submit = new JButton("\uC804\uC1A1\uD558\uAE30");
		btn_push_submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String title = text_push_title.getText();
				String msg = textArea_push_msg.getText();
				String content = textArea_push_content.getText();
				
				if("".equals(title) || "".equals(msg)){
					JOptionPane.showMessageDialog(Member_Manage.this, "내용을 입력해 주세요~!");
					return;
				}
				
				transferPushMessage(title, msg, content);
			}
		});
		panel_detail_push.add(btn_push_submit, "cell 2 4,growy");
		
										
		//구매내역 리스트
		setOrderList();		
				
		//sms 전송
		setSMSTransfer();
		
		
	}
		
	//SMS메세지 전송 하기
	private void setSMSTransfer(){
		
		JPanel panel_detail_sms = new JPanel();
		tab_east_detail.addTab("SMS \uC804\uC1A1", null, panel_detail_sms, null);
		panel_detail_sms.setLayout(new MigLayout("", "[]", "[]"));
		
		
		
	}
	
	
	//메세지 전송하기
	private void transferPushMessage(String title, String msg, String con){
		
		HashMap<String, Object> push_list = new HashMap<String, Object>();
		
		push_list.put("Title", title);
		push_list.put("Message", msg);
		push_list.put("Link", "");
		push_list.put("Noti_Img_Url", "http://14.38.161.45:8080/image/"+Server_Config.getFTPMARTPATH()+"/noti_message.jpg");
		push_list.put("Img_Url", "");
		push_list.put("Content", con);
		push_list.put("Mode", "text");
		push_list.put("Event", "");
		
		push_list.put("Mem_Id", "");
		push_list.put("Mem_Only", "");
		push_list.put("Hp", "");
		push_list.put("Platoform", "");
		
		int selectedrowcount = table_offmem_list.getSelectedRowCount();
		int[] selectrows = table_offmem_list.getSelectedRows();
		switch(tab_member_list.getSelectedIndex()){
		case 0:		
			
			if(selectedrowcount<= 0){
				JOptionPane.showMessageDialog(this, "전송할 회원을 선택해 주세요");
				return;
			}
			
			int count_msg = 0;
			for(int i=0; i < selectedrowcount; i++){	
				
				String gubun = (String)table_offmem_list.getValueAt(selectrows[i], 7);
				String hp_num = (String)table_offmem_list.getValueAt(selectrows[i], 11);				
				if("앱설치회원".equals(gubun) && !"".equals(hp_num) ){
					push_list.put("Hp", hp_num);
					JSONObject result = t_api.tranNewPushSubimt(push_list);
					String count = (String)result.get("result_code");
					if("OK".equals(count)){
						count_msg++;
					}
				}
			}			
			JOptionPane.showMessageDialog(this, "발송 완료\r\n 총 "+selectedrowcount+"건 발송중 "+count_msg+" 건 발송 완료");
			
			break;
		case 1:
			if(table_appin_list.getSelectedRowCount()<= 0){
				JOptionPane.showMessageDialog(this, "전송할 회원을 선택해 주세요");
				return;
			}
			break;
		case 2:
			if(table_online_memberlist.getSelectedRowCount()<= 0){
				JOptionPane.showMessageDialog(this, "전송할 회원을 선택해 주세요");
				return;
			}
			break;
		}
		
		
		
		//t_api.tranNewPushSubimt(push_list);
		
		
		
		
	}
	
	//우측 상세 화면에서 회원을 검색 합니다.
	private void searchMember(){
		
		//dtm_detailmem_list.setRowCount(0);
		refreshTable(dtm_detailmem_list);
		setScrollReSet("Detail");
		String str = text_detail_search.getText();		
		String query =  "";
				
		JSONArray json = new JSONArray();
		ArrayList<HashMap<String, String>> map = new ArrayList<HashMap<String, String>>();
		
		switch( tab_member_list.getSelectedIndex()){
		case 0: //오프라인 회원 선택창일때			
			searchDetailOnlineMember(json, str);			
			break;
		case 1: //온라인 회원 선택창일때			
			searchDetailOfflineMember(json, str);
			break;
		case 2: //앱설치 회원 선택창 일때			
			searchDetailOfflineMember(json, str);
			break;
		}
				
		
	}
	
	//온라인 회원에서 검색 합니다.
	private void searchDetailOnlineMember(JSONArray json, String str){
		
		ArrayList<HashMap<String, String>> map = new ArrayList<HashMap<String, String>>();
		//hp, id, idx, page
		String hp = text_detail_hp.getText();
		String mem_id = text_detail_memid.getText();		
		
		json = t_api.getMemberManage(hp.trim(), mem_id.trim(), "", "");
		for(int i=0; i < json.size(); i++){
			
			JSONObject obj = (JSONObject)json.get(i);
			HashMap<String, String> temp = new HashMap<String, String>();
			temp.put("Cus_Gubun", "온라인");
			temp.put("Cus_Name", (String)obj.get("name"));
			temp.put("Cus_Code", (String)obj.get("mem_id"));
			temp.put("Cus_Hp", (String)obj.get("hp"));
			
			map.add(temp);
		}
		
		//앱설치회원검색
		//hp, page
		json = t_api.getDeviceList(hp.replace("-", ""), "");
		for(int i=0; i < json.size(); i++){
			JSONObject obj = (JSONObject)json.get(i);				
			HashMap<String, String> temp = new HashMap<String, String>();
			temp.put("Cus_Gubun", "앱설치");
			temp.put("Cus_Name", (String)obj.get("idx"));
			temp.put("Cus_Code", (String)obj.get("mem_id"));
			temp.put("Cus_Hp", (String)obj.get("hp_num"));
			
			map.add(temp);
		}
		
		System.out.println(map.size());
		if( map == null || map.size() <= 0 ){
			JOptionPane.showMessageDialog(this, "검색된 결과가 없습니다.");
			return;
		}
		
		for(int i =0; i < map.size(); i++){
			HashMap<String, String> temp = map.get(i);
			Vector<Object> v = new Vector<Object>();
			
			v.addElement(i+1);
			v.addElement(temp.get("Cus_Gubun"));
			v.addElement(temp.get("Cus_Name"));
			v.addElement(temp.get("Cus_Code"));
			v.addElement(temp.get("Cus_Hp"));
			
			dtm_detailmem_list.addRow(v);
		}
	}
		
	
	//오프라인 회원 검색 하기
	private void searchDetailOfflineMember(JSONArray json, String str){
		
		String query =  "";
		ArrayList<HashMap<String, String>> map;
		
		String hp = text_detail_hp.getText();		
		String mem_id = text_detail_memid.getText();
		String idx = text_detail_idx.getText();
		
		//오프라인에서 검색합니다.
		//검색어로 회원명 회원ID 전화번호로 검색이 가능합니다.
		query += "Select '매장' as Cus_Gubun, Cus_Name, Cus_Code, Cus_Mobile as Cus_Hp"
				+ " From customer_info Where Cus_Name Like '%"+str+"%' and replace(Cus_Mobile, '-', '') Like '%"+hp.replace("-", "")+"%' and Cus_Code Like '%"+str+"%' "
				+ " Union all "
			
		//검색어로 회원명 회원ID 전화번호로 검색이 가능합니다.
				+ "Select '온라인' as Cus_Gubun, name as Cus_Name, mem_id as Cus_Code, hp as Cus_Hp"
				+ " From e_Member Where name Like '%"+str+"%' and replace(hp, '-', '') Like '%"+hp.replace("-", "")+"%' and mem_id Like '%"+mem_id+"%' "
				+ "and mem_idx like '%"+idx+"%' "
				+ " Union all "
					
		//검색어로 회원명 회원ID 전화번호로 검색이 가능합니다.
				+ "Select '앱설치' as Cus_Gubun, mem_id as Cus_Name, convert(nvarchar, idx) as Cus_Code, hp_num as Cus_Hp "
				+ " From e_AppInstall Where mem_id Like '%"+mem_id+"%' and replace(hp_num, '-', '') Like '%"+hp.replace("-", "")+"%' and idx Like '%"+idx+"%' ";
				
		ms_connect.setMainSetting();
		map = ms_connect.connection(query);
		
		System.out.println(map.size());
		
		System.out.println(map.size());
		if( map == null || map.size() <= 0 ){
			JOptionPane.showMessageDialog(this, "검색된 결과가 없습니다.");
			return;
		}
		
		for(int i =0; i < map.size(); i++){
			HashMap<String, String> temp = map.get(i);
			Vector<Object> v = new Vector<Object>();
			
			v.addElement(i+1);
			v.addElement(temp.get("Cus_Gubun"));
			v.addElement(temp.get("Cus_Name"));
			v.addElement(temp.get("Cus_Code"));
			v.addElement(temp.get("Cus_Hp"));
			
			dtm_detailmem_list.addRow(v);
		}
	}
	
	
	//온라인 회원 검색하기
	private void searchOnlineMember(){
		
		this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
		
		//초기화
		refreshTable(dtm_online_member);
				
		String mem_id = text_onlinetop_memid.getText();
		String hp = text_onlinetop_hp.getText();
		String mem_idx = text_onlinetop_idx.getText();
		String page = text_onlinetop_page.getText();
		
		JSONArray json = t_api.getMemberManage(hp, mem_id, mem_idx, page);
				

		if( json.size() <= 0){
			JOptionPane.showMessageDialog(this, "검색 결과가 없습니다.");
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			return;
		}
		
		for(int i=0; i < json.size(); i++){
			JSONObject obj = (JSONObject)json.get(i);
			Vector<Object> v = new Vector<Object>();
			v.add(i+1);
			v.addElement(obj.get("mem_idx"));
			v.add(obj.get("mem_id"));			
			v.add(obj.get("name"));
			v.add(obj.get("memlv"));
			v.add(obj.get("memlv_subject"));
			v.add(obj.get("hp"));
			v.add(obj.get("tel"));
			v.add(obj.get("nickname"));
			v.add(obj.get("email"));
			v.add(obj.get("zipcode"));
			v.add(obj.get("addr1"));
			v.add(obj.get("addr2"));
			v.add(obj.get("emoney"));
			v.add(obj.get("point"));
			v.add(obj.get("add1"));
			v.add(obj.get("add2"));
			v.add(obj.get("add3"));
			v.add(obj.get("add4"));
			
			dtm_online_member.addRow(v);		
		}
		
		this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}
	
	//좌측 선택회원 회원정보 입력하기
	private void setLeftListChooseSave(){
		
		if(table_offmem_list.getSelectedRowCount() <= 0){
			JOptionPane.showMessageDialog(Member_Manage.this, "회원을 선택해 주세요!!");
			return;
		}
		
		int result = JOptionPane.showConfirmDialog(null, "선택하신 정보를 좌측의 선택하신 회원에 저장 하시겠습니까?", "회원정보 입력", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
		System.out.println(result);
				
		int row = table_dtailmem_list.getSelectedRow();		
		// 1. 선택된 정보를 불러옵니다.
		
		//2. 선택된 정보중 구분을 통해 전체 정보를 불러옵니다.
		
		//3. 불러온 정보를 좌측의 회원정보에 입력합니다.
		
		//4. 구분의 값으로 다시 전송해 줍니다.
		
		
	}
	
	
	//선택정보 매장으로 전송하기 [온라인 회원가입회원]
	private void setAppInstallTransferData(){
		
		this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
		
		int row_count = table_appin_list.getSelectedRowCount();
		int[] row_list = table_appin_list.getSelectedRows();
		int column_count = table_appin_list.getColumnCount();
		
		int same_count = 0;  //일치 수량
		int incon_count = 0; //불일치 수량
		
		if( row_count <= 0){
			JOptionPane.showMessageDialog(this, "전송할 목록을 선택해 주세요!!");
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			return;
		}
		
		// 1. 매장 회원 정보를 불러옵니다. idx기준으로		
		String query = "Select idx From e_Appinstall ";
		
		ms_connect.setMainSetting();
		ArrayList<HashMap<String, String>> map = ms_connect.connection(query);
				
		// 2. 테이블의 정보를 불러옵니다.
		ArrayList<HashMap<String, Object>> temp = new ArrayList<HashMap<String, Object>>();
		for(int i= 0; i < row_count; i++ ){			
			int row = table_appin_list.convertRowIndexToModel(row_list[i]);				
				
			HashMap<String, Object> value = new HashMap<String, Object>();
			value.put("idx", table_appin_list.getValueAt(row, 1)); //고유값
			
			if(map.contains(value)){
				//선택된 수량을 카운트 합니다.
				
				same_count++;
			}else{								
				//불일치 수량을 카운트 합니다.
				value.clear();
				for(int j =0; j < column_count; j++){
					String columnname = dtm_appin_list.getColumnName(j);
					Object data = dtm_appin_list.getValueAt(row, j);
					value.put(columnname, data); //고유값
					//temp.add(value);
				}				
				temp.add(value);
				incon_count++;
			}
		}
				
		//예 선택시 0반환 아니요 2 닫힘 -1
		
		if(row_count == same_count){
			JOptionPane.showMessageDialog(this, "전부 저장되어 있습니다.");
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			return;
		}
		int result = JOptionPane.showConfirmDialog(this, "총 "+row_count+"명 중 "+same_count+"명은 매장서버에 등록이 되어있고 \r\n"+incon_count+"명을 새로 등록 할까요?", "매장등록 선택", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);		
		int complete_count = 0;
		if( result == 0){
			//매장으로 등록 합니다. 매장으로 등록 하면서 매장연동신청 현화을 사용 합니다.
			for(int i = 0; i < temp.size(); i++){
				if(resultAppInstallSave(temp.get(i))){
					complete_count++;
				}
			}			
			
			JOptionPane.showMessageDialog(this, "총 "+incon_count+"명 중 "+complete_count+" 명 저장되었습니다.");
		}
				
		System.out.println(result);		
		this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}
		
	//선택정보 매장으로 전송하기 [온라인 회원가입회원]
	private void setShopTransferData(){
		
		this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
		
		int row_count = table_online_memberlist.getSelectedRowCount();
		int[] row_list = table_online_memberlist.getSelectedRows();
		int column_count = table_online_memberlist.getColumnCount();
		
		int same_count = 0;  //일치 수량
		int incon_count = 0; //불일치 수량
		
		if( row_count <= 0){
			JOptionPane.showMessageDialog(this, "전송할 목록을 선택해 주세요!!");
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			return;
		}
		
		// 1. 매장 회원 정보를 불러옵니다. idx기준으로		
		String query = "Select mem_idx From e_member ";
		
		ms_connect.setMainSetting();
		ArrayList<HashMap<String, String>> map = ms_connect.connection(query);
				
		// 2. 테이블의 정보를 불러옵니다.
		ArrayList<HashMap<String, Object>> temp = new ArrayList<HashMap<String, Object>>();
		for(int i= 0; i < row_count; i++ ){			
			int row = table_online_memberlist.convertRowIndexToModel(row_list[i]);				
				
			HashMap<String, Object> value = new HashMap<String, Object>();
			value.put("mem_idx", dtm_online_member.getValueAt(row, 1)); //고유값
			
			if(map.contains(value)){				
				//선택된 수량을 카운트 합니다.
				
				same_count++;
			}else{								
				//불일치 수량을 카운트 합니다.
				value.clear();
				for(int j =0; j < column_count; j++){
					String columnname = dtm_online_member.getColumnName(j);
					Object data = dtm_online_member.getValueAt(row, j);
					value.put(columnname, data); //고유값
					//temp.add(value);
				}				
				temp.add(value);
				incon_count++;
			}
		}
				
		//예 선택시 0반환 아니요 2 닫힘 -1
		
		if(row_count == same_count){
			JOptionPane.showMessageDialog(this, "전부 저장되어 있습니다.");
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			return;
		}
		int result = JOptionPane.showConfirmDialog(this, "총 "+row_count+"명 중 "+same_count+"명은 매장서버에 등록이 되어있고 \r\n"+incon_count+"명을 새로 등록 할까요?", "매장등록 선택", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);		
		int complete_count = 0;
		if( result == 0){
			//매장으로 등록 합니다. 매장으로 등록 하면서 매장연동신청 현화을 사용 합니다.
			for(int i = 0; i < temp.size(); i++){
				if(resultMemberSave(temp.get(i))){
					complete_count++;
				}
			}			
			
			JOptionPane.showMessageDialog(this, "총 "+incon_count+"명 중 "+complete_count+" 명 저장되었습니다.");
		}
				
		System.out.println(result);		
		this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}
	
	//결과를 저장합니다.
    private boolean resultMemberSave(HashMap<String, Object> data){
    	
    	System.out.println(data.toString());
    	HashMap<String, Object> temp = data;
    	HashMap<String, Object> push_list = new HashMap<String, Object>();   //푸쉬 내용 저장		    	
		
		
		String query = "Select * From e_Member Where mem_id='"+temp.get("회원ID")+"' ";
		String query_result = "";
		String mem_id = temp.get("회원ID").toString();
		String hp = (String)temp.get("휴대폰번호");
		
		String cus_code = "";
		String connect_add5 = "0"; 
		
			//매장회원을 검색 해서 매칭합니다.			
			switch(temp.get("매장연동요청").toString().trim()){
			case "신규발급신청":
				//휴대폰 번호로 발급합니다. 
				query = "Select ISNull(max(cus_code), '"+hp.replace("-", "")+"') as cus_code, count(*) as count From Customer_Info Where Cus_Code='"+hp.replace("-", "")+"' ";			
				HashMap<String, String> map_newmem = ms_connect.selectQueryOne(query);
				System.out.println("검색 결과 : "+map_newmem.get("cus_code")+" 총 카운트 : "+map_newmem.get("count"));
				if(map_newmem.size() > 0 && map_newmem.get("count").equals("0")){
										
					String[] query_won = new String[2];
					query = "Insert Into Customer_Info (Cus_Code,Cus_Name,Cus_Gubun,Cus_Class,Cus_Tel,Cus_Mobile,Cus_BirYN,"
							+ "Cus_BirDay,Cus_RealDay,Pur_Pri,Cus_TPoint,Cus_Point,Cus_UsePoint,Dec_Pri,Vis_Count,Gift_Count,Edit_Check,"
							+ "Zip_Code,Address1,Address2,Bigo,Cus_Date,Vis_Date,Write_Date,Edit_Date,Writer,EDitor,HPSend_YN,"
							+ "Office_Name,Office_Num,Owner_Name,Uptae,JongMok,Address,Credit_YN,Cus_Use,Tax_Use,cPoint_Use,"
							+ "TaxBill_YN,Email,TAX_Print_Use,TAX_AUTO_USE,TAX_Gubun,TAX_Number, e_AppInstall_YN, e_Member_YN, e_Member_ID, e_PushSMS_YN, e_PhoneNumber)" 
							+ " Values('"+map_newmem.get("cus_code")+"', '"+temp.get("회원명")+"', '정회원','1','"+temp.get("전화번호")+"','"+hp+"','1','','','0','0','0','0','0','0','0','1',"
							+ "'"+temp.get("우편번호")+"','"+temp.get("주소1")+"','"+temp.get("주소2")+"','',"
							+ "'"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"','','"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"','shop','shop','1','','','','','','','1','1','1','1','0','','0','0', '0', '', '', '1', '"+temp.get("mem_id")+"','1', '') ";
					query_won[0] = query;
					
					query_won[1] = "Insert Into Customer_History(Regdate,q_sql,gubun)"
										+"Values(convert(datetime,getdate(),120), '"+query.replace("'", "`")+"', '쇼핑몰관리')";	
					
					int result = ms_connect.connect_update(query_won);
					if(result > 0){
						return false;
					}
				}
				
			
				String result_data = "";
				try {
					result_data = "&mem_id="+mem_id+"&add2="+map_newmem.get("cus_code")+"&add4="+URLEncoder.encode("연동중", "UTF-8");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					break;
				}
				
				//신규회원 등록 정보를 쇼핑몰에 올립니다.
				t_api.setMemberUpdate(result_data);				
				
				//결과전송 앱설치 회원 결과 보고
				push_list.put("Title", "신규회원 가입");
				push_list.put("Message", "신규회원 가입이 정상 완료 되었습니다. ");
				push_list.put("Link", "");
				push_list.put("Img_Url", "");
				push_list.put("Event", "");
				
				push_list.put("Mem_Id", mem_id);
				push_list.put("Mem_Only", "");
				push_list.put("Hp", "");
				
				//완료 내역을 회원에게 푸시 전송합니다.
				t_api.setPushSubimt(push_list);
				break;
				
			case "기존회원연동":	
				query = "Select * From Customer_Info Where replace(cus_mobile, '-', '')='"+hp.replace("-", "")+"' or replace(cus_tel, '-', '')='"+hp.replace("-", "")+"' Order by Edit_Date";
				ArrayList<HashMap<String, String>> map_cuscode = ms_connect.connection(query);		
				
				switch(map_cuscode.size()){
				case 0:  //매칭안됨			
					//결과전송 앱설치 회원 결과 보고
					push_list.put("Title", "매장회원 정보와 연동");
					push_list.put("Message", "매장회원 정보와 연동 정보가 부족하여 연동되지 못했습니다. 마이페이지>회원정보수정>고객검색정보에 전화번호 또는 성함을 입력해 주시면 연동처리 하겠습니다.");
					push_list.put("Link", "");
					push_list.put("Img_Url", "");					
					push_list.put("Event", "");
					
					push_list.put("Mem_Id", mem_id);
					push_list.put("Mem_Only", "ALL");
					push_list.put("Hp",  "");	
					
					//완료 내역을 회원에게 푸시 전송합니다.
					 t_api.setPushSubimt(push_list);
					break;
				case 1:
					cus_code = map_cuscode.get(0).get("Cus_Code");
					String query_mem = "Update Customer_Info set e_Member_YN='1', e_Member_ID='"+mem_id+"' Where Cus_Code='"+cus_code+"' ";						
					switch(ms_connect.connect_update(query_mem)){
					case 0:
						result_data = "";
						try {
							result_data = "&mem_id="+mem_id+"&add2="+cus_code+"&add4="+URLEncoder.encode("연동중", "UTF-8");
						} catch (UnsupportedEncodingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}				
						
						//신규회원 등록 정보를 쇼핑몰에 올립니다.
						t_api.setMemberUpdate(result_data);	
						
						//결과전송 앱설치 회원 결과 보고					
						push_list.put("Title", "매장회원 정보와 정상 연동 되었습니다. ");
						push_list.put("Message", "매장회원 정보와 정상 연동 되었습니다. ");
						push_list.put("Link", "");
						push_list.put("Img_Url", "");
						
						push_list.put("Event", "");
						
						push_list.put("Mem_Id", mem_id);
						push_list.put("Mem_Only", "ALL");
						push_list.put("Hp",  "");	
						
						//완료 내역을 회원에게 푸시 전송합니다.
						t_api.setPushSubimt(push_list);
						System.out.println("기존회원 연동 옵션 : 업데이트 성공");
						break;
					default:
						System.out.println("기존회원 연동 옵션 : 실패");
						break;
					}
					break;	 
				default:
					//결과전송 앱설치 회원 결과 보고					
					push_list.put("Title", "매장회원 정보와 연동실패 ");
					push_list.put("Message", "매장회원 정보와 연동 정보가 부족하여 연동되지 못했습니다. 마이페이지>회원정보수정>고객검색정보에 전화번호 또는 성함을 입력해 주시면 연동처리 하겠습니다.");
					push_list.put("Link", "");
					push_list.put("Img_Url", "");
					
					push_list.put("Event", "");
					
					push_list.put("Mem_Id", mem_id);
					push_list.put("Mem_Only", "ALL");
					push_list.put("Hp",  "");
					
					//완료 내역을 회원에게 푸시 전송합니다.
					t_api.setPushSubimt(push_list);					
					break;
				}
				
				break; //기존회원 연동
				
			case "연동안함":	
				break; //연동안함
				
			default:
			break; //그외 것들
		}
				
			query_result = "Insert into e_Member values("
					+"'"+temp.get("고유번호")+"', '"+temp.get("회원ID")+"', '', '"+temp.get("회원명")+"', '"+temp.get("등급코드")+"', '"+temp.get("휴대폰번호")+"',"
					+ " '"+temp.get("전화번호")+"', '', '"+temp.get("닉네임")+"', '', '', '"+temp.get("이메일")+"', '"+temp.get("우편번호")+"',"
					+ " '"+temp.get("주소")+"', '"+temp.get("나머지주소")+"', '', '', '', '', '',"
					+ " '', '"+temp.get("매장연동요청")+"', '"+temp.get("매장회원번호")+"', '"+temp.get("고객검색정보")+"', '"+temp.get("연동상태")+"', '', '',"
					+ " '', '', '', '', '"+cus_code+"', '"+connect_add5+"' )";			
			int result = ms_connect.connect_update(query_result);
			
			if(result == 0){
				return true;
			}else{
				return false;
			}		
			
    }
	
    
    //앱설치회원 서버로 전송 저장하기
    //결과를 저장합니다.
    private boolean resultAppInstallSave(HashMap<String, Object> data){
    	
    	HashMap<String, Object> json = data; 
		ArrayList<String> query_list = new ArrayList<String>();  //쿼리 모음		
		String query_result =  "";
		String connect_add5 = "0";
		String cus_code = "";
		String phone_number = "";
		
		//기존 앱설치내용이 있는지 먼저 확인 합니다.
		String query = "Select * From e_AppInstall Where idx='"+json.get("고유번호")+"' ";		
		HashMap<String, String> map = ms_connect.selectQueryOne(query);
		
		if(map.size() <= 0){ //앱신규설치회원
						
			ArrayList<HashMap<String, String>> map_cuscode = new ArrayList<HashMap<String, String>>();
			query = "Select * From Customer_Info Where replace(cus_mobile, '-', '')='"+json.get("핸드폰 번호")+"' or replace(cus_tel, '-', '')='"+json.get("핸드폰 번호")+"' Order by Edit_Date DESC";
			map_cuscode = ms_connect.connection(query);
			
			System.out.println(map_cuscode.size());
			switch(map_cuscode.size()){
			case 0:  //매칭안됨
				connect_add5 = "0"; //매칭안됨
				break;
			case 1:
				cus_code = map_cuscode.get(0).get("Cus_Code");
				phone_number = json.get("hp_num").toString();
				connect_add5 = "1"; //매칭완료

				query_result = "Update Customer_Info Set e_AppInstall_YN='1', e_PushSMS_YN='1', e_PhoneNumber='"+phone_number+"' Where Cus_Code='"+cus_code+"' ";
				query_list.add(query_result);
				break;	
			default:
				connect_add5 = "2";  //중복발생
				break;
			}
			
			query_result = "Insert into e_AppInstall (idx, mem_id, platform, devicetoken, deviceuid, devicename, devicemodel, deviceversion, hp_num, reg_time, cus_code, connect_yn) values("
					+"'"+json.get("고유번호")+"', '"+json.get("회원ID")+"', '"+json.get("플랫폼")+"', '"+json.get("토큰")+"', '"+json.get("기기 고유값")+"', '"+json.get("제조사명")
					+"', '"+json.get("모델명")+"', '"+json.get("플랫폼 버젼")+"', '"+json.get("핸드폰 번호")+"', '"+json.get("APP설치 시간")+"', '"+cus_code+"', '"+connect_add5+"')";
			query_list.add(query_result);			
						
			int result = ms_connect.connect_update(query_list);
			if(result == 0){
				return true;
			}else{
				return false;
			}
		}else{ //기존설치회원
			
			if(map.get("idx").equals(json.get("idx"))){
								
				query_result = "Update e_AppInstall Set mem_id='"+json.get("회원ID")+"', platform='"+json.get("플랫폼")+"', devicetoken='"+json.get("토큰")
						+"', deviceuid='"+json.get("기기 고유값")+"', devicename='"+json.get("제조사명")+"', devicemodel='"+json.get("모델명")
						+"', deviceversion='"+json.get("플랫폼 버젼")+"', hp_num='"+json.get("핸드폰 번호")+"', reg_time='"+json.get("APP설치 시간")+"' "
						+"Where idx='"+json.get("고유번호")+"' ";	
				
				int result = ms_connect.connect_update(query_result);				
				if(result == 0){				
					//저장 성공
					return true;					
				}else{
					//저장 실패
					return false;
				}
				
			}else{
				return false;
			}
		}		
    }
    
    
    //구매내역 리스트 디자인
    private void setOrderList(){
		
		String[] column = {"순번", "구매금액", "거래형태", "구매일"};
		dtm_detail_orderlist = new DefaultTableModel(null, column){			
			/**
			 * 
			 */
			private static final long serialVersionUID = 18448777775L;
			@Override
			public boolean isCellEditable(int roe, int column){
				/**if(column == 4){
					return true;
				}else{
					return false;
				}*/
				return false;
			}
			public Class getColumnClass(int column) {
				 /*Class returnValue;
				 if ((column >= 0) && (column < getColumnCount())) {
				    returnValue = getValueAt(0, column).getClass();
				 } else {
				    returnValue = Object.class;
				 }
				 return (returnValue==null?Object.class:returnValue.getClass());*/
				     
			    Object value=this.getValueAt(0,column);
			    return (value==null?Object.class:value.getClass());			   				
			}			
		};
		
		DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
		celAlignCenter.setHorizontalAlignment(JLabel.CENTER);
		
		DefaultTableCellRenderer celAlignRight = new DefaultTableCellRenderer();
		celAlignRight.setHorizontalAlignment(JLabel.RIGHT);
		panel_detail_orderlist.setLayout(new MigLayout("", "[340px,grow]", "[grow][678px]"));
		
		JPanel panel_detail_ordertop = new JPanel();
		panel_detail_ordertop.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_detail_orderlist.add(panel_detail_ordertop, "cell 0 0,grow");
		panel_detail_ordertop.setLayout(new MigLayout("", "[][grow][][grow]", "[][][][]"));
		
		JLabel label_detail_orderinfo = new JLabel("\uD68C\uC6D0\uBA85");
		panel_detail_ordertop.add(label_detail_orderinfo, "cell 0 0,alignx trailing");
		
		text_detail_ordername = new JTextField();
		panel_detail_ordertop.add(text_detail_ordername, "cell 1 0,growx");
		text_detail_ordername.setColumns(10);
		
		JLabel label_detail_orderid = new JLabel("\uD68C\uC6D0ID");
		panel_detail_ordertop.add(label_detail_orderid, "cell 2 0,alignx trailing");
		
		text_detail_orderid = new JTextField();
		panel_detail_ordertop.add(text_detail_orderid, "cell 3 0,growx");
		text_detail_orderid.setColumns(10);
		
		JLabel label_detail_orderhp = new JLabel("\uD734\uB300\uD3F0\uBC88\uD638");
		panel_detail_ordertop.add(label_detail_orderhp, "cell 0 1,alignx trailing");
		
		text_detail_orderhp = new JTextField();
		panel_detail_ordertop.add(text_detail_orderhp, "cell 1 1 2 1,growx");
		text_detail_orderhp.setColumns(10);
		
		JLabel label_detail_ordercount = new JLabel("\uC8FC\uBB38\uD69F\uC218");
		panel_detail_ordertop.add(label_detail_ordercount, "cell 0 2,alignx trailing");
		
		text_detail_ordercount = new JTextField();
		text_detail_ordercount.setHorizontalAlignment(SwingConstants.CENTER);
		panel_detail_ordertop.add(text_detail_ordercount, "cell 1 2,growx");
		text_detail_ordercount.setColumns(10);
		
		JLabel label_detail_orderpri = new JLabel("\uC8FC\uBB38\uCD1D\uC561");
		panel_detail_ordertop.add(label_detail_orderpri, "cell 2 2,alignx trailing");
		
		text_detail_orderpri = new JTextField();
		text_detail_orderpri.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_detail_ordertop.add(text_detail_orderpri, "cell 3 2,growx");
		text_detail_orderpri.setColumns(10);
		
		
		scrollPane_detail_order = new JScrollPane();
		scrollPane_detail_order.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panel_detail_orderlist.add(scrollPane_detail_order, "cell 0 1,growy");
		//panel_detail_ordercenter.add(scrollPane_detail_order, "cell 0 0,growx");
		
		table_detail_orderlist = new JTable(dtm_detail_orderlist);
		scrollPane_detail_order.setViewportView(table_detail_orderlist);
		
    	
		((DefaultTableCellRenderer)table_detail_orderlist.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(0);
		
		table_detail_orderlist.setRowHeight(25);
		//table_offmem_list.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);  //가로 스크롤		
				
		table_detail_orderlist.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);  //가로 스크롤
		table_detail_orderlist.getTableHeader().setReorderingAllowed(false);  //이동불가
		
		table_detail_orderlist.setAutoCreateRowSorter(true);
		
		table_detail_orderlist.addMouseListener(new MouseListener() {
			
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
					//setGoodsList();
					
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
		table_detail_orderlist.getColumn("순번").setPreferredWidth(40);
		
		/*table_offmem_list.setAutoCreateRowSorter(true);
		TableRowSorter<TableModel> tsorter_main = new TableRowSorter<TableModel>(table_offmem_list.getModel());
		table_offmem_list.setRowSorter(tsorter_main);*/	
    	
		//컬럼 정렬   	
		
		//컬럼 조정
		for(String str:column){
			table_detail_orderlist.getColumn(str).setCellRenderer(celAlignCenter);
		}
				
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
		
    }
    
    //매장 서버에서 구매 내역을 가져 옵니다.
    private void getOrderListToServer(){
    	
    	refreshTable(dtm_detail_orderlist);
		setScrollReSet("Order");
		
    	//int row = table_offmem_list.getSelectedRow();    
    	int row = table_offmem_list.convertRowIndexToModel(table_offmem_list.getSelectedRow());
    	String mem_id = (String)dtm_offmem.getValueAt(row, 8);   	
    	String mem_hp = (String)dtm_offmem.getValueAt(row, 5);
    	String mem_name = (String)dtm_offmem.getValueAt(row, 1);
    	int total_price = 0;
    	int total_order = 0;
    	
    	if(null == mem_id || "".equals(mem_id)){    		
    		return;
    	}
    	
    	String query = "Select * From e_OrderList Where mem_id='"+mem_id+"' Order By reg_time DESC ";
    	
    	ms_connect.setMainSetting();
    	ArrayList<HashMap<String, String>> map = ms_connect.connection(query);
    	
    	if(map.size() <= 0 ){
    		JOptionPane.showMessageDialog(this, "주문 내역이 없습니다.");
    		return;
    	}
    	    	
    	
    	for(int i =0; i < map.size(); i++){
    	
    		HashMap<String, String> temp= map.get(i);
    		Vector<Object> v = new Vector<Object>();
    		
    		v.add(i+1);
    		v.add(toNumFormat(Integer.parseInt(temp.get("app_price"))));    		
    		v.add(temp.get("state_subject"));
    		v.add(temp.get("reg_time").substring(0, 10));
    		
    		dtm_detail_orderlist.addRow(v);
    		
    		total_order++;
    		total_price += Integer.parseInt(temp.get("app_price"));
    	}
    	
    	text_detail_ordername.setText(mem_name);
    	text_detail_orderid.setText(mem_id);
    	text_detail_orderhp.setText(mem_hp);
    	text_detail_ordercount.setText(String.valueOf(total_order));
    	text_detail_orderpri.setText(toNumFormat(total_price));
    	
    	
    }
    
	
	/* 
	 ************************************** 초기화 및 기능 부분  ************************************************* 
	 *
	 */
	
    //천단위 콤마 찍기
    private String toNumFormat(int num) {
    	  DecimalFormat df = new DecimalFormat("#,###");    	  
    	  return df.format(num);
    }
    
    //상단 회원수 조회 기능
    private void topSearchInfomation(){
    	
	    String query = "Select Count(*) as Count From customer_info";
		
		ms_connect.setMainSetting();
		HashMap<String, String> map = ms_connect.selectQueryOne(query); 
		
		label_top_offmem_total.setText(map.get("Count"));    
		
		label_top_shoptotal.setText(t_api.getMemberTotal());
		label_top_apptotal.setText(t_api.getAppInstallTotal());
		
    }
    	
	//우측 회원검색 필드 초기화
	private void detailRenew(){
		
		text_detail_hp.setText("");
		text_detail_memid.setText("");
		text_detail_idx.setText("");
		text_detail_search.setText("");		
		
	}
    	
	//테이블 초기화
	private void refreshTable(DefaultTableModel model) {

	   int rowCount= model.getRowCount();	  
	   for(int i=0;i<rowCount;i++ ){
	        model.removeRow(0);	    
	   }
	}
	
	//스크롤을 상단으로 조정합니다.
	private void setScrollReSet(String gubun){
		
		
		JViewport jv1 = scroll_offmem_list.getViewport();		
		
		if(gubun.equals("All")){			
		
			jv1.setViewPosition(new Point(0,0));
			
			jv1 = scrollPane_detailmem_list.getViewport();
			jv1.setViewPosition(new Point(0,0));
						
		}else if(gubun.equals("Main")){
						
			jv1.setViewPosition(new Point(0,0));
			
		}else if(gubun.equals("Detail")){
			
			jv1 = scrollPane_detailmem_list.getViewport();
			jv1.setViewPosition(new Point(0,0));
			
		}else if(gubun.equals("Order")){
			jv1 = scrollPane_detail_order.getViewport();
			jv1.setViewPosition(new Point(0,0));
		}
	}
    
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		String commend = e.getActionCommand();
		switch(commend){
		case "검색":
			
			switch(tab_member_list.getSelectedIndex()){
			case 0:
				topSearchStart();
				break;
			case 1:
				searchOnlineMember();
				break;
			case 2:
				break;
			}
			
			break;
			
		case "회원검색":			
			searchMember();
			break;			
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		int keyCode = e.getKeyCode();
        switch (keyCode) {
        case KeyEvent.VK_ENTER:
        	switch(tab_member_list.getSelectedIndex()){
			case 0:
				topSearchStart();
				break;
			case 1:
				searchOnlineMember();
				break;
			case 2:
				break;
			}
            break;	
        }
	}
}
