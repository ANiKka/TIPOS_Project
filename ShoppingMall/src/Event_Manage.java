import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

import com.toedter.calendar.*;

import net.miginfocom.swing.*;

public class Event_Manage extends JPanel implements ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 154474854451L;
	private JPanel panel_top;
	private JPanel panel_center;
	private JPanel panel_east;
	private JTextField top_text_searchname;
	private JLabel top_label_eventgubun;
	private JComboBox top_combo_eventgubun;
	private JLabel top_label_ingyn;
	private JComboBox top_combo_ingyn;
	private JLabel top_label_useyn;
	private JComboBox top_combo_useyn;
	private JButton top_btn_searchdel;
	private JButton top_btn_search;
	
	private Ms_Connect ms_connect;
	private JLabel east_label_title;
	private JLabel east_label_code;
	private JLabel east_label_name;
	private JTextField east_text_code;
	private JTextField east_text_name;
	private JLabel east_label_setdate;
	private JLabel lblNewLabel_4;
	private JButton btnNewButton;
	private JLabel east_label_gubun;
	private JComboBox east_combo_gubun;
	private JButton east_btn_callevent;
	private JLabel east_label_memberyn;
	private JRadioButton rdbtnNewRadioButton;
	private JRadioButton rdbtnNewRadioButton_1;
	private JLabel lblNewLabel_6;
	private JRadioButton rdbtnNewRadioButton_2;
	private JRadioButton rdbtnNewRadioButton_3;
	private JPanel panel;
	private JButton east_btn_save;
	private JPanel east_panel_point;
	private JPanel east_panel_prizes;
	private JLabel east_label_point;
	private JTextField east_text_point;
	private JLabel east_label_pointright;
	private JLabel east_label_prizesname;
	private JTextField east_text_prizesname;
	private JLabel east_label_prizescount;
	private JTextField east_text_prizescount;
	private JButton east_btn_prizescountdown;
	private JButton east_btn_prizescountup;
	private JLabel east_label_middletitle;
	private JLabel east_label_useyn;
	private JRadioButton east_radio_usey;
	private JRadioButton east_radio_usen;
	private JLabel east_label_usecount;
	private JTextField east_text_usecount;
	private JTabbedPane center_tabbed;
	private JPanel center_tabbed_couponlist;
	private JPanel center_tabbed_couponuselist;
	private JScrollPane center_scroll_couponlist;
	private JTable center_table_couponlist;
	private JScrollPane cneter_scroll_uselist;
	private JTable center_table_uselist;
	private DefaultTableModel dtm_couponlist;
	private DefaultTableModel dtm_uselist;
	private JButton btnNewButton_1;
	private JPanel center_tabbed_coupontran;
	private JPanel panel_coupontran_1;
	private JPanel panel_coupontran_2;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_5;
	private JComboBox comboBox;
	private JComboBox comboBox_1;
	private JTextField textField;
	private JTextField textField_1;
	private JLabel label;
	private JLabel lblNewLabel_7;
	private JTextField textField_2;
	private JLabel lblNewLabel_8;
	private JComboBox comboBox_2;
	
	public Event_Manage() {
		
		setLayout(new BorderLayout(10, 10));
		setBorder(new EmptyBorder(5, 5, 5, 5));
		
		//디비 접속 도구
		ms_connect = new Ms_Connect();
		
		//상단 검색 창
		panel_top = new JPanel();
		panel_top.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		add(panel_top, BorderLayout.NORTH);		
		panel_top.setLayout(new MigLayout("", "[80px][grow][10px][80px][grow][10px][80px][grow][80px][grow][20px][][100px][100px,grow]", "[15px,grow][grow][15px,grow]"));		
		top_Search();
		
		
		//이벤트 목록 창
		panel_center = new JPanel();
		add(panel_center, BorderLayout.CENTER);
		panel_center.setLayout(new BorderLayout(0, 0));
		event_List();
	
	}
	
	/** e_gubun : 1-오픈기념사은품, 0-매장포인트적립 */ 
	private void top_Search(){
		
		JLabel top_label_searchname = new JLabel("\uAC80\uC0C9\uC5B4");
		top_label_searchname.setHorizontalAlignment(SwingConstants.CENTER);
		panel_top.add(top_label_searchname, "cell 0 1,grow");
		
		top_text_searchname = new JTextField();
		panel_top.add(top_text_searchname, "flowx,cell 1 1,growx");
		top_text_searchname.setColumns(10);
		
		top_label_eventgubun = new JLabel("\uC774\uBCA4\uD2B8\uAD6C\uBD84");
		panel_top.add(top_label_eventgubun, "cell 3 1,alignx center");
		
		top_combo_eventgubun = new JComboBox();
		top_combo_eventgubun.setModel(new DefaultComboBoxModel(new String[] {"\uC804\uCCB4", "\uACE0\uAC1D\uD3EC\uC778\uD2B8", "\uC0C1\uD488\uC9C0\uAE09(\uC7AC\uACE0X) "}));
		panel_top.add(top_combo_eventgubun, "cell 4 1,growx");
		
		top_label_ingyn = new JLabel("\uC9C4\uD589\uC720\uBB34");
		panel_top.add(top_label_ingyn, "cell 6 1,alignx center");
		
		top_combo_ingyn = new JComboBox();
		top_combo_ingyn.setModel(new DefaultComboBoxModel(new String[] {"\uC804\uCCB4", "\uC9C4\uD589\uC911", "\uC9C4\uD589\uC885\uB8CC"}));
		panel_top.add(top_combo_ingyn, "cell 7 1,growx");
		
		top_label_useyn = new JLabel("\uC0AC\uC6A9\uC720\uBB34");
		panel_top.add(top_label_useyn, "cell 8 1,alignx center");
		
		top_combo_useyn = new JComboBox();
		top_combo_useyn.setModel(new DefaultComboBoxModel(new String[] {"\uC804\uCCB4", "\uC0AC\uC6A9", "\uBBF8\uC0AC\uC6A9"}));
		panel_top.add(top_combo_useyn, "cell 9 1,growx");
		
		top_btn_searchdel = new JButton("\uC9C0\uC6B0\uAE30");
		top_btn_searchdel.setActionCommand("지우기");
		top_btn_searchdel.addActionListener(this);
		panel_top.add(top_btn_searchdel, "cell 11 1");
		
		top_btn_search = new JButton("\uAC80\uC0C9");
		top_btn_search.addActionListener(this);
		top_btn_search.setActionCommand("검색");
		panel_top.add(top_btn_search, "cell 12 1,growx");
		
	}
	
	
	private void event_List(){
		
		center_tabbed = new JTabbedPane(JTabbedPane.TOP);
		panel_center.add(center_tabbed, BorderLayout.CENTER);
		
		center_tabbed_couponlist = new JPanel();
		center_tabbed_couponlist.setOpaque(false);
		center_tabbed.addTab("\uCFE0\uD3F0 \uBAA9\uB85D", null, center_tabbed_couponlist, null);
		center_tabbed_couponlist.setLayout(new BorderLayout(5, 0));	
		
		event_ListView();
		
		
		//쿠폰전송
		center_tabbed_coupontran = new JPanel();
		center_tabbed.addTab("\uCFE0\uD3F0 \uC804\uC1A1", null, center_tabbed_coupontran, null);
		center_tabbed_coupontran.setLayout(new BorderLayout(5, 0));
		
		panel_coupontran_1 = new JPanel();
		panel_coupontran_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		center_tabbed_coupontran.add(panel_coupontran_1, BorderLayout.WEST);
		panel_coupontran_1.setLayout(new MigLayout("", "[][grow]", "[][][][][][100px][][][]"));
		
		event_CouponTran();
		
		
		
		//쿠폰 사용리스트
		center_tabbed_couponuselist = new JPanel();
		center_tabbed.addTab("\uCFE0\uD3F0 \uC0AC\uC6A9 \uC774\uB825", null, center_tabbed_couponuselist, null);
		center_tabbed_couponuselist.setLayout(new BorderLayout(0, 0));
				
		cneter_scroll_uselist = new JScrollPane();
		center_tabbed_couponuselist.add(cneter_scroll_uselist, BorderLayout.CENTER);
		
		event_UseList();
		
	}
	
	//이벤트 리스트 목록 UI
	private void event_ListView(){
		
		//쿠폰리스트 부분
		center_scroll_couponlist = new JScrollPane();
		center_tabbed_couponlist.add(center_scroll_couponlist, BorderLayout.CENTER);
					
		String[] colunm_couponlist = {"순번", "<html>쿠폰 코드<br>쿠폰 명</html>", "<html>시작일<br>종료일</html>", "<html>회원설정<br>중복설정</html>", "쿠폰구분", "포인트"	, "<html>제품명<br>수량</html>", "사용횟수", "<html>등록일<br>수정일<br>중지일</html>"};
				
		dtm_couponlist = new DefaultTableModel(null, colunm_couponlist){
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
		
		center_table_couponlist = new JTable(dtm_couponlist);
		
		JTableHeader header_couponlist = center_table_couponlist.getTableHeader();
	    Dimension d_couponlist = header_couponlist.getPreferredSize();
	    d_couponlist.height = 60;
	    header_couponlist.setPreferredSize(d_couponlist);
	    
	    //쿠폰 리스트헤더 부분 중앙정렬
	    ((DefaultTableCellRenderer)center_table_couponlist.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(0);
	    
	    center_table_couponlist.setRowHeight(25);
	    center_table_couponlist.getTableHeader().setReorderingAllowed(false);  //이동불가	    
	    //center_table_couponlist.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);  //가로 스크롤	    
	    
	    TableColumnModel tcm_couponlist = center_table_couponlist.getColumnModel();

        //tcm.getColumn(0).setMaxWidth(Integer.MAX_VALUE);
	    tcm_couponlist.getColumn(0).setWidth(40);
	    //tcm_couponlist.getColumn(0).setCellRenderer(celAlignCenter);
	    tcm_couponlist.getColumn(0).setPreferredWidth(40);

	    //tcm_couponlist.getColumn(1).setMaxWidth(Integer.MAX_VALUE);
	    tcm_couponlist.getColumn(1).setWidth(200);	    
	    tcm_couponlist.getColumn(1).setPreferredWidth(200);
	    	    
	    tcm_couponlist.getColumn(2).setWidth(100);	    
	    tcm_couponlist.getColumn(2).setPreferredWidth(100);
	    
	    tcm_couponlist.getColumn(3).setWidth(100);	    
	    tcm_couponlist.getColumn(3).setPreferredWidth(100);
	    
	    tcm_couponlist.getColumn(4).setWidth(80);	    
	    tcm_couponlist.getColumn(4).setPreferredWidth(80);
	    
	    tcm_couponlist.getColumn(5).setWidth(150);
	    tcm_couponlist.getColumn(5).setPreferredWidth(150);
	    
	    tcm_couponlist.getColumn(6).setWidth(100);
	    tcm_couponlist.getColumn(6).setPreferredWidth(100);
	    
	    tcm_couponlist.getColumn(7).setWidth(100);
	    tcm_couponlist.getColumn(7).setPreferredWidth(100);
	    
	    tcm_couponlist.getColumn(8).setWidth(100);
	    tcm_couponlist.getColumn(8).setPreferredWidth(100);
	    
		center_scroll_couponlist.setViewportView(center_table_couponlist);
		
		//우측 이벤트 등록창
		panel_east = new JPanel();
		panel_east.setBorder(new LineBorder(new Color(0, 0, 0)));
		//add(panel_east, BorderLayout.EAST);
		center_tabbed_couponlist.add(panel_east, BorderLayout.EAST);
		panel_east.setLayout(new MigLayout("", "[grow][10px][grow][][grow]", "[30px][10px][][][10px][][][][][][][][][10px][15px][15px][grow][]"));
		event_Reg();
		
	}
	
	
	//쿠폰리스트 우측 등록 수정화면
	private void event_Reg(){
		
		east_label_title = new JLabel("\uCFE0\uD3F0 \uBC0F \uC774\uBCA4\uD2B8 \uB4F1\uB85D/\uC218\uC815");
		east_label_title.setHorizontalAlignment(SwingConstants.CENTER);
		east_label_title.setBackground(Color.PINK);
		east_label_title.setOpaque(true);
		east_label_title.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		panel_east.add(east_label_title, "cell 0 0 5 1,grow");
		
		east_label_code = new JLabel("\uCFE0\uD3F0 \uCF54\uB4DC");
		panel_east.add(east_label_code, "cell 0 2,alignx trailing,aligny center");
		
		east_text_code = new JTextField();
		panel_east.add(east_text_code, "cell 2 2,growx");
		east_text_code.setColumns(10);
		
		east_btn_callevent = new JButton("\uC774\uBCA4\uD2B8 \uBD88\uB7EC\uC624\uAE30");
		east_btn_callevent.setFont(new Font("맑은 고딕", Font.PLAIN, 10));
		panel_east.add(east_btn_callevent, "cell 4 2,aligny center");
		
		east_label_name = new JLabel("\uCFE0\uD3F0 \uBA85");
		panel_east.add(east_label_name, "cell 0 3,alignx trailing,aligny center");
		
		east_text_name = new JTextField();
		panel_east.add(east_text_name, "cell 2 3 3 1,growx");
		east_text_name.setColumns(20);
		
		east_label_middletitle = new JLabel("\uC0C1 \uC138 \uC815 \uBCF4");
		east_label_middletitle.setHorizontalAlignment(SwingConstants.CENTER);
		east_label_middletitle.setBackground(new Color(244, 164, 96));
		panel_east.add(east_label_middletitle, "cell 0 5 5 1,growx");
		
		east_label_gubun = new JLabel("\uCFE0\uD3F0 \uC885\uB958");
		panel_east.add(east_label_gubun, "cell 0 6,alignx trailing,aligny center");
		
		east_combo_gubun = new JComboBox();
		east_combo_gubun.setModel(new DefaultComboBoxModel(new String[] {"\uD3EC\uC778\uD2B8\uC801\uB9BD \uCFE0\uD3F0", "\uC0AC\uC740\uD488\uC9C0\uAE09 \uCFE0\uD3F0"}));
		panel_east.add(east_combo_gubun, "cell 2 6 3 1,growx");
		
		east_label_setdate = new JLabel("\uC0AC\uC6A9 \uAE30\uAC04");
		panel_east.add(east_label_setdate, "cell 0 7,alignx trailing,aligny center");
		
		lblNewLabel_4 = new JLabel("~");
		panel_east.add(lblNewLabel_4, "cell 3 7");
		
		Calendar c = Calendar.getInstance();
		//c.add(Calendar.YEAR, -10);

		JDateChooser east_chooser_start = new JDateChooser(c.getTime());
		east_chooser_start.setLocale(Locale.KOREA);		
		east_chooser_start.setDateFormatString("yyyy-MM-dd");
		panel_east.add(east_chooser_start, "cell 2 7,growx");
		
		JDateChooser east_chooser_end = new JDateChooser(c.getTime());
		east_chooser_end.setLocale(Locale.KOREA);
		east_chooser_end.setDateFormatString("yyyy-MM-dd");
		panel_east.add(east_chooser_end, "cell 4 7,growx");
		
		east_label_memberyn = new JLabel("\uD68C\uC6D0 \uC124\uC815");
		panel_east.add(east_label_memberyn, "cell 0 8,alignx trailing,aligny center");
		
		rdbtnNewRadioButton = new JRadioButton("\uD68C\uC6D0+\uBE44\uD68C\uC6D0");
		rdbtnNewRadioButton.setSelected(true);
		panel_east.add(rdbtnNewRadioButton, "cell 2 8");
		
		rdbtnNewRadioButton_1 = new JRadioButton("\uD68C\uC6D0");
		panel_east.add(rdbtnNewRadioButton_1, "cell 4 8");
		
		lblNewLabel_6 = new JLabel("\uC911\uBCF5 \uC124\uC815");
		panel_east.add(lblNewLabel_6, "cell 0 9,alignx trailing,aligny center");
		
		rdbtnNewRadioButton_2 = new JRadioButton("\uD5C8\uC6A9\uC548\uD568");
		rdbtnNewRadioButton_2.setSelected(true);
		panel_east.add(rdbtnNewRadioButton_2, "cell 2 9");
		
		rdbtnNewRadioButton_3 = new JRadioButton("\uD5C8\uC6A9");
		panel_east.add(rdbtnNewRadioButton_3, "cell 4 9");
		
		panel = new JPanel();
		panel_east.add(panel, "cell 0 10 5 1,grow");
		panel.setLayout(new CardLayout(0, 0));
		
		east_panel_point = new JPanel();
		east_panel_point.setBorder(new TitledBorder(null, "\uC801\uB9BD \uD3EC\uC778\uD2B8 \uC810\uC218", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.add(east_panel_point, "name_100937529009392");
		
		east_label_point = new JLabel("\uC801\uB9BD\uC810\uC218");
		east_panel_point.add(east_label_point);
		
		east_text_point = new JTextField();
		east_text_point.setHorizontalAlignment(SwingConstants.RIGHT);
		east_text_point.setText("0");
		east_panel_point.add(east_text_point);
		east_text_point.setColumns(10);
		
		east_label_pointright = new JLabel("(\uC810)");
		east_panel_point.add(east_label_pointright);
		
		east_panel_prizes = new JPanel();
		east_panel_prizes.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\uC0AC\uC740\uD488 \uC815\uBCF4", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.add(east_panel_prizes, "name_101016384819908");
		east_panel_prizes.setLayout(new MigLayout("", "[57px][][][][grow]", "[21px][]"));
		
		east_label_prizesname = new JLabel("\uC81C\uD488\uBA85");
		east_panel_prizes.add(east_label_prizesname, "cell 0 0,alignx trailing,aligny center");
		
		east_text_prizesname = new JTextField();
		east_panel_prizes.add(east_text_prizesname, "cell 1 0 4 1,growx,aligny top");
		east_text_prizesname.setColumns(10);
		
		east_label_prizescount = new JLabel("\uC218\uB7C9");
		east_panel_prizes.add(east_label_prizescount, "cell 0 1,alignx trailing");
		
		east_text_prizescount = new JTextField();
		east_text_prizescount.setHorizontalAlignment(SwingConstants.RIGHT);
		east_text_prizescount.setText("0");
		east_panel_prizes.add(east_text_prizescount, "cell 1 1");
		east_text_prizescount.setColumns(3);
		
		east_btn_prizescountdown = new JButton("\u25C0");
		east_panel_prizes.add(east_btn_prizescountdown, "cell 2 1");
		
		east_btn_prizescountup = new JButton("\u25B6");
		east_panel_prizes.add(east_btn_prizescountup, "cell 3 1");
		
		east_label_useyn = new JLabel("\uC0AC\uC6A9\uC5EC\uBB34");
		panel_east.add(east_label_useyn, "cell 0 11,alignx trailing");
		
		east_radio_usey = new JRadioButton("\uC0AC\uC6A9\uD568");
		east_radio_usey.setSelected(true);
		panel_east.add(east_radio_usey, "cell 2 11");
		
		east_radio_usen = new JRadioButton("\uC0AC\uC6A9\uC548\uD568");
		panel_east.add(east_radio_usen, "cell 4 11");
		
		east_label_usecount = new JLabel("\uCFE0\uD3F0 \uC9C0\uAE09 \uD69F\uC218");
		east_label_usecount.setHorizontalAlignment(SwingConstants.CENTER);
		panel_east.add(east_label_usecount, "cell 2 12,alignx trailing,growy");
		
		east_text_usecount = new JTextField();
		east_text_usecount.setEditable(false);
		east_text_usecount.setHorizontalAlignment(SwingConstants.RIGHT);
		east_text_usecount.setText("0");
		panel_east.add(east_text_usecount, "cell 4 12,growx");
		east_text_usecount.setColumns(10);
		
		east_btn_save = new JButton("\uC800\uC7A5");
		east_btn_save.setForeground(Color.RED);
		east_btn_save.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		east_btn_save.setBackground(Color.BLUE);
		panel_east.add(east_btn_save, "cell 4 14 1 2,grow");
		
		btnNewButton_1 = new JButton("\uC0C8\uB85C\uC785\uB825");
		panel_east.add(btnNewButton_1, "cell 4 17,grow");
		/**btnNewButton = new JButton("\uB0A0\uC790");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Calendar c = Calendar.getInstance();
				c.add(Calendar.YEAR, -10);

				JDateChooser chooser = new JDateChooser(c.getTime());
				chooser.setVisible(true);
			}
		});*/
		//panel_east.add(btnNewButton, "cell 4 3,alignx trailing");
		
		
	}

	
	//쿠폰 전송 화면
	private void event_CouponTran(){		

		lblNewLabel = new JLabel("1. \uC804\uC1A1 \uB300\uC0C1 \uC120\uD0DD");
		panel_coupontran_1.add(lblNewLabel, "cell 0 0");
		
		lblNewLabel_1 = new JLabel("\uD68C\uC6D0 \uB4F1\uAE09");
		panel_coupontran_1.add(lblNewLabel_1, "cell 0 1,alignx trailing");
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"\uC804\uCCB4", "\uBE44\uD68C\uC6D0", "\uC77C\uBC18\uD68C\uC6D0", "\uAC00\uC785\uB300\uAE30\uD68C\uC6D0", "\uD0C8\uD1F4\uD68C\uC6D0"}));
		panel_coupontran_1.add(comboBox, "cell 1 1,growx");
		
		lblNewLabel_2 = new JLabel("\uD68C\uC6D0\uAC00\uC785\uC5EC\uBD80");
		panel_coupontran_1.add(lblNewLabel_2, "cell 0 2,alignx trailing");
		
		comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"\uC804\uCCB4", "\uBE44\uD68C\uC6D0", "\uAC00\uC785\uD68C\uC6D0"}));
		panel_coupontran_1.add(comboBox_1, "cell 1 2,growx");
		
		lblNewLabel_3 = new JLabel("\uD68C\uC6D0 \uC544\uC774\uB514");
		panel_coupontran_1.add(lblNewLabel_3, "cell 0 3,alignx trailing");
		
		textField = new JTextField();
		panel_coupontran_1.add(textField, "cell 1 3,growx");
		textField.setColumns(10);
		
		lblNewLabel_5 = new JLabel("\uD734\uB300\uD3F0\uBC88\uD638");
		panel_coupontran_1.add(lblNewLabel_5, "cell 0 4,alignx trailing");
		
		textField_1 = new JTextField();
		panel_coupontran_1.add(textField_1, "cell 1 4,growx");
		textField_1.setColumns(10);
		
		label = new JLabel("2. \uC804\uC1A1 \uBC29\uC2DD \uC120\uD0DD");
		panel_coupontran_1.add(label, "cell 0 6");
		
		lblNewLabel_7 = new JLabel("\uC804\uC1A1 \uC81C\uBAA9");
		panel_coupontran_1.add(lblNewLabel_7, "cell 0 7,alignx trailing");
		
		textField_2 = new JTextField();
		panel_coupontran_1.add(textField_2, "cell 1 7,growx");
		textField_2.setColumns(10);
		
		lblNewLabel_8 = new JLabel("\uC804\uC1A1\uBC29\uC2DD");
		panel_coupontran_1.add(lblNewLabel_8, "cell 0 8,alignx trailing");
		
		comboBox_2 = new JComboBox();
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"\uBA54\uC138\uC9C0", "\uC774\uBBF8\uC9C0", "\uC774\uBCA4\uD2B8\uCFE0\uD3F0"}));
		panel_coupontran_1.add(comboBox_2, "cell 1 8,growx");
		
		panel_coupontran_2 = new JPanel();
		center_tabbed_coupontran.add(panel_coupontran_2, BorderLayout.CENTER);
		
		
		
	}
	
	
	
	//이벤트 사용 리스트
	private void event_UseList(){
		
		String[] colunm_uselist = {"순번", "<html>쿠폰 코드<br>쿠폰 명<br>쿠폰바코드<br>쿠폰 구분</html>", "seq", "<html>전표번호<br>판매일자<br>판매시간</html>", "고객정보", "포인트"	, "<html>제품명<br>수량</html>", "<html>판매일<br>판매자</html>", "<html>반품구분<br>원전표번호</html>", "<html>삭제구분<br>삭제일</html>"};
		
		dtm_uselist = new DefaultTableModel(null, colunm_uselist){
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
		
		center_table_uselist = new JTable(dtm_uselist);		
		
		JTableHeader header_uselist = center_table_uselist.getTableHeader();
	    Dimension d_uselist = header_uselist.getPreferredSize();
	    d_uselist.height = 80;
	    header_uselist.setPreferredSize(d_uselist);
	    
	    //쿠폰 리스트헤더 부분 중앙정렬
	    ((DefaultTableCellRenderer)center_table_uselist.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(0);
	    
	    center_table_uselist.setRowHeight(25);
	    center_table_uselist.getTableHeader().setReorderingAllowed(false);  //이동불가	    
	    //center_table_couponlist.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);  //가로 스크롤	    
	    
	    TableColumnModel tcm_uselist = center_table_uselist.getColumnModel();

        //tcm_uselist.getColumn(0).setMaxWidth(Integer.MAX_VALUE);
	    tcm_uselist.getColumn(0).setWidth(40);
	    //tcm_uselist.getColumn(0).setCellRenderer(celAlignCenter);
	    tcm_uselist.getColumn(0).setPreferredWidth(40);

	    //tcm_uselist.getColumn(1).setMaxWidth(Integer.MAX_VALUE);
	    tcm_uselist.getColumn(1).setWidth(200);	    
	    tcm_uselist.getColumn(1).setPreferredWidth(200);
	    	    
	    tcm_uselist.getColumn(2).setWidth(30);	    
	    tcm_uselist.getColumn(2).setPreferredWidth(30);
	    
	    tcm_uselist.getColumn(3).setWidth(100);	    
	    tcm_uselist.getColumn(3).setPreferredWidth(100);
	    
	    tcm_uselist.getColumn(4).setWidth(100);	    
	    tcm_uselist.getColumn(4).setPreferredWidth(100);
	    
	    tcm_uselist.getColumn(5).setWidth(100);
	    tcm_uselist.getColumn(5).setPreferredWidth(100);
	    
	    tcm_uselist.getColumn(6).setWidth(100);
	    tcm_uselist.getColumn(6).setPreferredWidth(100);
	    
	    tcm_uselist.getColumn(7).setWidth(100);
	    tcm_uselist.getColumn(7).setPreferredWidth(100);
	    
	    tcm_uselist.getColumn(8).setWidth(150);
	    tcm_uselist.getColumn(8).setPreferredWidth(150);
	    
	    tcm_uselist.getColumn(9).setWidth(100);
	    tcm_uselist.getColumn(9).setPreferredWidth(100);
		
		cneter_scroll_uselist.setViewportView(center_table_uselist);	
		
	}
	
	
	
	//상단 검색 정보 불러오기
	public void getTopSearchStart(){
		
		String query = "Select * From "
						 + "( Select x.*, Isnull( (Select Count(*) use_cnt From e_Coupon_History a Where x.e_Seq=a.e_Seq ), 0) use_cnt From e_Coupon_List x ) "
						 + " X Where 1=1 ";		
		
		//조건값 가져오기
		
		//검색어
		if(top_text_searchname.getText().length() > 0){			
			query += "And (e_Seq='"+top_text_searchname.getText()+"' or e_CouponName Like '%"+top_text_searchname+"%' ) ";			
		}
		
		//쿠폰종류
		switch(top_combo_eventgubun.getSelectedIndex()){		
		case 1:
			query +=" And e_gubun='0' ";
			break;
		case 2:
			query +=" And e_gubun='1' ";
			break;
		}
		
		//사용유무
		
		ms_connect.setMainSetting();
		ArrayList<HashMap<String, String>> temp_array = ms_connect.connection(query);
		
		System.out.println(temp_array);
	}
	
	
	//상단 검색 정보 초기화 하기
	public void setTopRenew(){
		
		top_text_searchname.setText("");
		top_combo_eventgubun.setSelectedIndex(0);
		top_combo_ingyn.setSelectedIndex(0);
		top_combo_useyn.setSelectedIndex(0);		
		
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String command = e.getActionCommand();
		
		switch(command){
		case "검색":
			getTopSearchStart();			
			break;			
		case "지우기":
			setTopRenew();
			break;
		}
	}
	
	
}
