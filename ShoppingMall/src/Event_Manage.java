
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.toedter.calendar.JDateChooser;

import groovy.ui.SystemOutputInterceptor;
import it.sauronsoftware.ftp4j.FTPAbortedException;
import it.sauronsoftware.ftp4j.FTPClient;
import it.sauronsoftware.ftp4j.FTPDataTransferException;
import it.sauronsoftware.ftp4j.FTPException;
import it.sauronsoftware.ftp4j.FTPIllegalReplyException;
import it.sauronsoftware.ftp4j.FTPListParseException;
import net.miginfocom.swing.MigLayout;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JCheckBox;
import java.awt.event.KeyAdapter;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;



public class Event_Manage extends JPanel implements ActionListener, KeyListener {
		
	private static final long serialVersionUID = 154826599991L;
	private JPanel panel_top;
	private JPanel panel_center;
	private JPanel panel_east;
	private JTextField top_text_searchname;
	private JLabel top_label_eventgubun;
	private JComboBox<String> top_combo_eventgubun;
	private JLabel top_label_ingyn;
	private JComboBox<String> top_combo_ingyn;
	private JLabel top_label_useyn;
	private JComboBox<String> top_combo_useyn;
	private JButton top_btn_searchdel;
	private JButton top_btn_search;
	
	private Ms_Connect ms_connect;
	private Trans_ShopAPI trans_shopapi;
	
	private JLabel east_label_title;
	private JLabel east_label_code;
	private JLabel east_label_name;
	private JTextField east_text_code;
	private JTextField east_text_name;
	private JLabel east_label_setdate;
	private JLabel lblNewLabel_4;	
	private JLabel east_label_gubun;
	private JComboBox<String>east_combo_gubun;
	private JDateChooser east_chooser_start;
	private JDateChooser east_chooser_end;
	private JButton east_btn_callevent;
	private JLabel east_label_memberyn;
	private JRadioButton east_radio_memberyn;
	private JRadioButton east_radio_membery;
	private JLabel east_label_memoverlap;
	private JRadioButton east_radio_overlapn;
	private JRadioButton east_radio_overlapy;
	private JPanel east_panel_data;
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
	private ButtonGroup east_bg_memberyn;
	private ButtonGroup east_bg_overlapyn;
	private ButtonGroup east_bg_useyn;
	private CardLayout east_cardlayout;
	
	private JTabbedPane center_tabbed;
	private JPanel center_tabbed_couponlist;
	private JPanel center_tabbed_couponuselist;
	private JScrollPane center_scroll_couponlist;
	private JTable center_table_couponlist;
	private JScrollPane cneter_scroll_uselist;
	private JTable center_table_uselist;
	private DefaultTableModel dtm_couponlist;
	private DefaultTableModel dtm_uselist;
	private JButton east_btn_renew;
	private JPanel esat_panel_goodsprizes;
	private JPanel east_panel_pricediscount_won;
	private JPanel east_panel_pricediscount_percent;
	private JPanel east_panel_goodsdiscount_won;
	private JPanel east_panel_goodsdiscount_percent;
	private JLabel label_goodsprizes_barcode;
	private JTextField text_goodsprizes_barcode;
	private JLabel label_goodsprizes_gname;
	private JTextField text_goodsprizes_gname;
	private JLabel label_goodsprizes_count;
	private JSpinner spinner_goodsprizes_count;
	private JButton btn_goodsprizes_search;
	private JLabel label_pricediscount_won;
	private JLabel label_pricediscount_minwon;
	private JTextField text_pricediscount_won;
	private JTextField text_pricediscount_minwon;
	private JLabel lable_won;
	private JLabel label_won1;
	private JLabel label_pricediscount_percent;
	private JTextField text_pricediscount_percent;
	private JLabel text_pircediscount_won;
	private JLabel label_pricediscount_maxwon;
	private JTextField text_pricediscount_maxwon;
	private JLabel label_pricediscount_won1;
	private JLabel label_pricediscount_minper;
	private JTextField text_pricediscount_minper;
	private JLabel label_pricediscount_won2;
	private JLabel label_goodsdiscountwon_barcode;
	private JTextField text_goodsdiswon_barcode;
	private JButton btn_goodsdiswon_search;
	private JLabel label_goodsdiswon_gname;
	private JTextField text_goodsdiswon_gname;
	private JLabel label_goodsdiswon_purpri;
	private JTextField text_goodsdiswon_purpri;
	private JLabel label_goodsdiswon_sellpri;
	private JTextField text_goodsdiswon_sellpri;
	private JLabel label_goodsdiswon_diswon;
	private JTextField text_goodsdiswon_diswon;
	private JLabel label_goodsdiswon_won1;
	private JLabel label_goodsdiswon_count;
	private JSpinner spinner_goodsdiswon_count;
	private JCheckBox chkbox_goodsdiswon_over;
	private JLabel label_goodsdisper_barcode;
	private JTextField text_goodsdisper_barcode;
	private JButton btn_goodsdisper_search;
	private JLabel label_goodsdisper_gname;
	private JTextField text_goodsdisper_gname;
	private JLabel label_goodsdisper_purpri;
	private JTextField text_goodsdisper_purpri;
	private JLabel label_goodsdisper_sellpri;
	private JTextField text_goodsdisper_sellpri;
	private JLabel label_goodsdisper_disper;
	private JTextField text_goodsdisper_disper;
	private JLabel label_goodsdisper_won1;
	private JLabel label_goodsdisper_count;
	private JSpinner spinner_goodsdisper_count;
	private JCheckBox checkBox_goodsdisper_over;
	private JLabel label_goodsdisper_maxwon;
	private JTextField text_goodsdisper_maxwon;
	private JLabel label_goodsdisper_won2;
	
	private String[] coupon_listitem = { "포인트 적립쿠폰", "사은품 지급쿠폰" , "사은품(매장상품) 지급쿠폰", "전체 금액할인(원) 쿠폰", "전체 금액할인(%) 쿠폰 ",
			"단품 금액할인(원) 쿠폰", "단품 금액할인(%) 쿠폰" };
	
	private String[] coupon_comboxlistitem = { "전체", "포인트 적립쿠폰", "사은품 지급쿠폰" , "사은품(매장상품) 지급쿠폰", "전체 금액할인(원) 쿠폰", "전체 금액할인(%) 쿠폰 ",
			"단품 금액할인(원) 쿠폰", "단품 금액할인(%) 쿠폰" };
		
	private String[] coupon_panellist = {"point", "prizes", "goodsprizes", "pricediscount_won", "pricediscount_percent", "goodsdiscount_won", "goodsdiscount_percent" };
	private JLabel label_goodsdisper_dis;
	private JTextField text_goodsdisper_dis;
	private JLabel label_goodsdiswon_all;
	private JLabel label_goodsdisper_all;
	
	
	//구분합니다.		
	String e_Point = "0";
	String e_Product = "";
	
	int e_pCnt = 0;
	
	//사은품 상품등록
	String e_bBarcode = "";
	int e_bCnt = 0;
	
	//전체할인(원)
	String e_DcPri = "0";
	String e_MinLimitPri = "0";		
	String e_DcPri_Oyn = "0";		
	
	String e_DcPer = "0";
	String e_DcPerLimit = "0";
	String e_DcPer_Oyn = "";	
	
	String e_pBarcode = "";
	String e_pDCPri = "0";
	String e_pDcPer = "0";
	String e_pDcPerLimit = "0";		
	int e_pAppCnt = 0;
	String e_pOnlyOne = "0";
	
	String update_query = "";	
	private JLabel top_label_couponbarcode;
	private JTextField top_text_couponbarcode;
	private JLabel top_label_saleday;
	private JDateChooser top_dateChooser_start;
	private JDateChooser top_dateChooser_end;
	private JLabel top_label_and;
	
	public Event_Manage() {
		
		setLayout(new BorderLayout(10, 10));
		setBorder(new EmptyBorder(5, 5, 5, 5));
		
		//디비 접속 도구
		ms_connect = new Ms_Connect();
		trans_shopapi = new Trans_ShopAPI();
						
		//상단 검색 창
		panel_top = new JPanel();
		panel_top.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		add(panel_top, BorderLayout.NORTH);		
		panel_top.setLayout(new MigLayout("", "[80px][grow][10px][80px][][10px][][][grow][80px][grow][20px][][100px][100px,grow]", "[grow][15px,grow]"));		
		top_Search();
				
		//이벤트 목록 창
		panel_center = new JPanel();
		add(panel_center, BorderLayout.CENTER);
		panel_center.setLayout(new BorderLayout(0, 0));
		event_List();
				
		//목록을 바로 불러 옵니다	
		getTopSearchStart();
				
	}
	
	/** e_gubun : 1-오픈기념사은품, 0-매장포인트적립 */ 
	private void top_Search(){
		
		JLabel top_label_searchname = new JLabel("\uAC80\uC0C9\uC5B4");
		top_label_searchname.setHorizontalAlignment(SwingConstants.CENTER);
		panel_top.add(top_label_searchname, "cell 0 0,grow");
		
		top_text_searchname = new JTextField();
		panel_top.add(top_text_searchname, "flowx,cell 1 0,growx");
		top_text_searchname.setColumns(10);
		
		top_label_eventgubun = new JLabel("\uC774\uBCA4\uD2B8\uAD6C\uBD84");
		panel_top.add(top_label_eventgubun, "cell 3 0,alignx center");
		
		top_combo_eventgubun = new JComboBox<String>();
		top_combo_eventgubun.setModel(new DefaultComboBoxModel<String>(coupon_comboxlistitem));
		panel_top.add(top_combo_eventgubun, "cell 4 0 2 1,growx");
		
		top_label_ingyn = new JLabel("\uC9C4\uD589\uC720\uBB34");
		panel_top.add(top_label_ingyn, "cell 6 0 2 1");
		
		top_combo_ingyn = new JComboBox<String>();
		top_combo_ingyn.setModel(new DefaultComboBoxModel<String>(new String[] {"\uC804\uCCB4", "\uC9C4\uD589\uC911", "\uC9C4\uD589\uC885\uB8CC"}));
		top_combo_ingyn.setSelectedIndex(1);
		panel_top.add(top_combo_ingyn, "cell 8 0,growx");
		
		top_label_useyn = new JLabel("\uC0AC\uC6A9\uC720\uBB34");
		panel_top.add(top_label_useyn, "cell 9 0,alignx center");
		
		top_combo_useyn = new JComboBox<String>();
		top_combo_useyn.setModel(new DefaultComboBoxModel<String>(new String[] {"\uC804\uCCB4", "\uC0AC\uC6A9", "\uBBF8\uC0AC\uC6A9"}));
		panel_top.add(top_combo_useyn, "cell 10 0,growx");
		
		top_btn_searchdel = new JButton("\uC9C0\uC6B0\uAE30");
		top_btn_searchdel.setActionCommand("지우기");
		top_btn_searchdel.addActionListener(this);
		panel_top.add(top_btn_searchdel, "cell 12 0");
		
		top_btn_search = new JButton("\uAC80\uC0C9");
		top_btn_search.addActionListener(this);
		top_btn_search.setActionCommand("검색");
		panel_top.add(top_btn_search, "cell 13 0,growx");
		
		top_label_couponbarcode = new JLabel("\uCFE0\uD3F0 \uBC14\uCF54\uB4DC");
		panel_top.add(top_label_couponbarcode, "cell 0 1,alignx trailing");
		
		top_text_couponbarcode = new JTextField();
		panel_top.add(top_text_couponbarcode, "cell 1 1 3 1,growx");
		top_text_couponbarcode.setColumns(10);
		
		top_label_saleday = new JLabel("\uB9E4\uCD9C\uAE30\uAC04");
		panel_top.add(top_label_saleday, "cell 4 1");
		Calendar c = Calendar.getInstance();
		top_dateChooser_start = new JDateChooser(c.getTime());
		top_dateChooser_start.setDateFormatString("yyyy-MM-dd");
		panel_top.add(top_dateChooser_start, "cell 5 1,grow");
		
		top_label_and = new JLabel("~");
		panel_top.add(top_label_and, "cell 6 1,alignx center");
		
		top_dateChooser_end = new JDateChooser(c.getTime());
		top_dateChooser_end.setDateFormatString("yyyy-MM-dd");
		panel_top.add(top_dateChooser_end, "cell 7 1 2 1,grow");
		
	}
	
	
	private void event_List(){
		
		center_tabbed = new JTabbedPane(JTabbedPane.TOP);
		center_tabbed.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				
				switch(center_tabbed.getSelectedIndex()){
				case 0:					
					top_text_couponbarcode.setEnabled(false);
					top_dateChooser_start.setEnabled(false);
					top_dateChooser_end.setEnabled(false);
					break;
				case 1:
					top_text_couponbarcode.setEnabled(true);
					top_dateChooser_start.setEnabled(true);
					top_dateChooser_end.setEnabled(true);
					break;
				}
				
			}
		});
		panel_center.add(center_tabbed, BorderLayout.CENTER);
		
		center_tabbed_couponlist = new JPanel();
		center_tabbed_couponlist.setOpaque(false);
		center_tabbed.addTab("\uCFE0\uD3F0 \uBC0F \uC774\uBCA4\uD2B8 \uBAA9\uB85D", null, center_tabbed_couponlist, null);
		center_tabbed_couponlist.setLayout(new BorderLayout(5, 0));
		
		event_ListView();
		
		//쿠폰 사용리스트
		center_tabbed_couponuselist = new JPanel();
		center_tabbed.addTab("\uCFE0\uD3F0 \uC0AC\uC6A9 \uC774\uB825", null, center_tabbed_couponuselist, null);
		center_tabbed_couponuselist.setLayout(new BorderLayout(0, 0));
				
		
		
		event_UseList();
		
	}
	
	//이벤트 리스트 목록 UI
	private void event_ListView(){
				
		DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
		celAlignCenter.setHorizontalAlignment(JLabel.CENTER);	
		
		//쿠폰리스트 부분
		center_scroll_couponlist = new JScrollPane();		
		center_tabbed_couponlist.add(center_scroll_couponlist, BorderLayout.CENTER);
				//<html></html><br>
		String[] colunm_couponlist = {"순번", "<html>쿠폰 코드<br>쿠폰 명</html>", "<html>시작일<br>종료일</html>", 
				"<html>회원설정<br>중복설정</html>", "쿠폰구분", "포인트"	, "<html>상품명<br>수량</html>", 
				"<html>바코드<br>상품명<br>수량</html>", "<html>할인(원)<br>구매액(원)<br>중복할인</html>",
				"<html>할인(%)<br>최대할인액<br>구매액(원)<br>중복할인</html>", "<html>바코드<br>상품명<br>할인(원)/개<br>적용수량<br>반복사용</html>", 
				"<html>바코드<br>상품명<br>할인(%)/개<br>최대할인액/개<br>적용수량<br>반복사용</html>", "<html>사용유무<br>사용횟수</html>",
				"<html>등록일<br>수정일<br>삭제일</html>", "진행여부"};
				
		dtm_couponlist = new DefaultTableModel(null, colunm_couponlist){
		
			private static final long serialVersionUID = 45857216L;

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
		
		center_table_couponlist = new JTable(dtm_couponlist){
            public boolean getScrollableTracksViewportWidth()
            {
                return getPreferredSize().width < getParent().getWidth();
            }
        };
		
        //헤더 높이 조절하기        
        center_table_couponlist.setTableHeader(new JTableHeader(center_table_couponlist.getColumnModel()) {
        	  @Override public Dimension getPreferredSize() {
        	    Dimension d = super.getPreferredSize();
        	    d.height = 100;
        	    return d;
        	  }
        });		
	    
	    //쿠폰 리스트헤더 부분 중앙정렬
	    ((DefaultTableCellRenderer)center_table_couponlist.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(0);
	    
	    center_table_couponlist.setRowHeight(100);
	    center_table_couponlist.getTableHeader().setReorderingAllowed(false);  //이동불가	    
	    center_table_couponlist.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);  //가로 스크롤	    
	    
	    for(String str:colunm_couponlist){
	    	center_table_couponlist.getColumn(str).setCellRenderer(celAlignCenter);
	    }
	    
	    TableColumnModel tcm_couponlist = center_table_couponlist.getColumnModel();

        //tcm.getColumn(0).setMaxWidth(Integer.MAX_VALUE);
	    tcm_couponlist.getColumn(0).setWidth(30);
	    //tcm_couponlist.getColumn(0).setCellRenderer(celAlignCenter);
	    tcm_couponlist.getColumn(0).setPreferredWidth(30);

	    //tcm_couponlist.getColumn(1).setMaxWidth(Integer.MAX_VALUE);
	    tcm_couponlist.getColumn(1).setWidth(150);	    
	    tcm_couponlist.getColumn(1).setPreferredWidth(150);
	    tcm_couponlist.getColumn(1).setCellRenderer(new CouponListTableCellRenderer());
	    
	    	    
	    tcm_couponlist.getColumn(2).setWidth(80);	    
	    tcm_couponlist.getColumn(2).setPreferredWidth(80);
	    //tcm_couponlist.getColumn(2).setCellRenderer(celAlignCenter);
	    
	    tcm_couponlist.getColumn(3).setWidth(70);	    
	    tcm_couponlist.getColumn(3).setPreferredWidth(70);
	    //tcm_couponlist.getColumn(3).setCellRenderer(celAlignCenter);
	    
	    tcm_couponlist.getColumn(4).setWidth(150);	    
	    tcm_couponlist.getColumn(4).setPreferredWidth(150);
	    //tcm_couponlist.getColumn(4).setCellRenderer(celAlignCenter);
	    
	    tcm_couponlist.getColumn(5).setWidth(80);
	    tcm_couponlist.getColumn(5).setPreferredWidth(80);
	    //tcm_couponlist.getColumn(5).setCellRenderer(celAlignCenter);
	    
	    tcm_couponlist.getColumn(6).setWidth(100);
	    tcm_couponlist.getColumn(6).setPreferredWidth(100);
	    //tcm_couponlist.getColumn(6).setCellRenderer(celAlignCenter);
	    
	    tcm_couponlist.getColumn(7).setWidth(100);
	    tcm_couponlist.getColumn(7).setPreferredWidth(100);
	    //tcm_couponlist.getColumn(7).setCellRenderer(celAlignCenter);
	    
	    tcm_couponlist.getColumn(8).setWidth(80);
	    tcm_couponlist.getColumn(8).setPreferredWidth(80);
	    //tcm_couponlist.getColumn(8).setCellRenderer(celAlignCenter);
	    
	    tcm_couponlist.getColumn(9).setWidth(80);
	    tcm_couponlist.getColumn(9).setPreferredWidth(80);
	    //tcm_couponlist.getColumn(9).setCellRenderer(celAlignCenter);
	    
	    tcm_couponlist.getColumn(10).setWidth(100);
	    tcm_couponlist.getColumn(10).setPreferredWidth(100);
	    
	    tcm_couponlist.getColumn(11).setWidth(100);
	    tcm_couponlist.getColumn(11).setPreferredWidth(100);
	    
	    tcm_couponlist.getColumn(12).setWidth(60);
	    tcm_couponlist.getColumn(12).setPreferredWidth(60);
	    
	    tcm_couponlist.getColumn(13).setWidth(150);
	    tcm_couponlist.getColumn(13).setPreferredWidth(150);
	    
	    tcm_couponlist.getColumn(14).setWidth(60);
	    tcm_couponlist.getColumn(14).setPreferredWidth(60);
	    
	    
		center_scroll_couponlist.setViewportView(center_table_couponlist);		
		center_table_couponlist.addMouseListener(new MouseListener() {
			
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
				/*if(e.getButton() == 1){
					if(change_Flags()) return;
					//상품을 우측으로 보냅니다.
					switch(tabPane_detail.getSelectedIndex()){
					case 0: //상품상세정보
						setGoodsDetail();
						break;
					case 1: //이미지 관리	
						if(!chkbox_listSearchNot.isSelected()){
							setSearchGoodsName();
						}
						break;
					case 2: //일괄변경
						
						break;
					case 3: //판매메세지
						if(btn_jtab_hotkey_listcall.isEnabled()){
							//목록의 바코드를 찾아서 지정한 곳으로 상품을 저장합니다.
							//상품 저장 함수
							setHotKeyListSave();
						}
						break;
					case 4: //
						break;
					case 5:
						break;
					default:
						JOptionPane.showMessageDialog(Goods_Manage.this, "탭을 선택해 주세요!!");
						break;
					}		
				}*/
				
					if (e.getClickCount() == 2) {
						System.out.println("마우스 두번 클릭 됐습니다.");
						//우측으로 자료를 전송 합니다.
						setEventDetail();
						
					} // 더블클릭
				
					if (e.getButton() == 3) { 
						System.out.println("오른쪽 마우스 클릭 됐습니다.");
						//사용 이력을 보여줍니다. 또는 두가지를 고를수 있겠끔 합니다.
						
						
						
					} // 오른쪽 클릭	 */
				}
		});
		
		
		//우측 이벤트 등록창
		panel_east = new JPanel();
		panel_east.setBorder(new LineBorder(new Color(0, 0, 0)));
		//add(panel_east, BorderLayout.EAST);
		center_tabbed_couponlist.add(panel_east, BorderLayout.WEST);
		panel_east.setLayout(new MigLayout("", "[grow][10px][grow][][grow]", "[30px][10px][][][10px][][][][][][][][10px][15px][15px][grow][]"));
		event_Reg();
		
	}
	
	
	//이벤트 정보 수정하기
	public void setEventDetail(){
		
		setRenewCouponReg();
		
		//한번이라도 사용한 이력이 있다면 수정을 못하게 막습니다.
		int row = center_table_couponlist.getSelectedRow();
		int col = center_table_couponlist.getColumnCount();
		if(row < 0){
			JOptionPane.showMessageDialog(this, "목록을 선택해 주세요");			
			return;
		}
		
		Vector<String> temp_v =  new Vector<String>();
		for(int i=0; i < col; i++){								
			String temp = dtm_couponlist.getValueAt(row, 1).toString();
			temp = temp.replaceAll("<html>", "");
			temp = temp.replaceAll("</html>", "");
			
			String[] temp_a = temp.split("<br>");			
			for(String str:temp_a){
				temp_v.addElement(str);
			}
		}
		
		//재검색해서 가져오기
		/*String query = "Select * From "
				 + "( Select x.*, Isnull( (Select Count(*) use_cnt From e_Coupon_History a Where x.e_Seq=a.e_Seq ), 0) use_cnt From e_Coupon_List x ) "
				 + " X Where 1=1 ";
*/
		String query = "SELECT * FROM ( " 
				+ "Select x.*, isnull((select g_name from goods a where x.e_bBarcode=a.barcode),'') g_name1, " 
				+ "isnull((select g_name from goods a where x.e_pBarcode=a.barcode),'') g_name2, " 
				+ "isnull((select count(*) use_cnt from e_Coupon_History a where x.e_seq=a.e_seq),0)  use_cnt, "  
				+ "isnull((select pur_pri from goods a where x.e_pBarcode=a.barcode), 0) pur_pri, "
				+ "isnull((select sell_pri from goods a where x.e_pBarcode=a.barcode), 0) sell_pri "
				+ "From e_Coupon_List x  ) X " 
				+ "Where 1=1 ";
		//조건값 가져오기
		//검색어
		query += "And x.e_Seq='"+temp_v.get(0).trim()+"' ";
		
		ms_connect.setMainSetting();
		HashMap<String, String> map = ms_connect.selectQueryOne(query);
		
		if(map.size() <= 0){
			JOptionPane.showMessageDialog(this, "목록 데이터의 조회결과가 없습니다.");
			return;
		}
		
		int count = Integer.parseInt(map.get("use_cnt"));		
		if(count > 0){
			JOptionPane.showMessageDialog(this, "쿠폰을 사용한 적이 있어서 수정 및 중지 하실 수 없습니다.");
			return;
		}
		
		System.out.println(map);
		//이벤트 불러오기 기능 막기	
		//쿠폰 코드 수정 막기		
		east_text_code.setText(map.get("e_Seq"));
		east_text_code.setEditable(false);
		east_btn_callevent.setEnabled(false);
		
		east_text_name.setText(map.get("e_CouponName"));
				
		east_combo_gubun.setSelectedIndex(Integer.parseInt(map.get("e_gubun")));
		east_combo_gubun.setEnabled(false);
			
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Date s_date;
		Date e_date;
		try {
			s_date = sdf.parse(map.get("e_Sdate"));
			e_date = sdf.parse(map.get("e_EDate"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(this, e.getMessage());
			return;
		}
				
		east_chooser_start.setDate(s_date);
		east_chooser_end.setDate(e_date);
		
		if(map.get("e_MEM_YN").equals("0")){
			east_radio_memberyn.setSelected(true);
		}else{
			east_radio_membery.setSelected(true);
		}
		
		if(map.get("e_Over_YN").equals("0")){
			east_radio_overlapn.setSelected(true);
		}else{
			east_radio_overlapy.setSelected(true);
		}
		
		if(map.get("Del_YN").equals("0")){
			east_radio_usey.setSelected(true);
		}else{
			east_radio_usen.setSelected(true);
		}
						
		switch(east_combo_gubun.getSelectedIndex()){
		case 0:
			setRadioButtonisEnabled(false);
			east_text_point.setText(map.get("e_Point"));
			break;
		case 1:			
			setRadioButtonisEnabled(true);
			east_text_prizesname.setText(map.get("e_Product"));
			east_text_prizescount.setText(map.get("e_pCnt"));
			break;
		case 2:
			setRadioButtonisEnabled(true);
			text_goodsprizes_barcode.setText(map.get("e_bBarcode"));
			text_goodsprizes_gname.setText(map.get("g_name1"));
			spinner_goodsprizes_count.setValue(Integer.parseInt(map.get("e_bCnt")));
			break;
		case 3:
			setRadioButtonisEnabled(true);
			text_pricediscount_won.setText(map.get("e_DcPri"));
			text_pricediscount_minwon.setText(map.get("e_MinLimitPri"));
			break;
		case 4:
			setRadioButtonisEnabled(true);
			text_pricediscount_percent.setText(map.get("e_DcPer"));
			text_pricediscount_maxwon.setText(map.get("e_DcPerLimit"));
			text_pricediscount_minper.setText(map.get("e_MinLimitPri"));			
			break;
		case 5:
			setRadioButtonisEnabled(true);
			text_goodsdiswon_barcode.setText(map.get("e_pBarcode"));
			text_goodsdiswon_gname.setText(map.get("g_name2"));
			text_goodsdiswon_purpri.setText(map.get("pur_pri"));
			text_goodsdiswon_sellpri.setText(map.get("sell_pri"));
			text_goodsdiswon_diswon.setText(map.get("e_pDCPri"));
			spinner_goodsdiswon_count.setValue(Integer.parseInt(map.get("e_pAppCnt")));
			
			if( map.get("e_pOnlyOne").equals("0")){
				chkbox_goodsdiswon_over.setSelected(false);
			}else{
				chkbox_goodsdiswon_over.setSelected(true);
			}
			
			break;
		case 6:
			setRadioButtonisEnabled(true);		
			text_goodsdisper_barcode.setText(map.get("e_pBarcode"));
			text_goodsdisper_gname.setText(map.get("g_name2"));
			text_goodsdisper_purpri.setText(map.get("pur_pri"));
			text_goodsdisper_sellpri.setText(map.get("sell_pri"));
			text_goodsdisper_disper.setText(map.get("e_pDcPer"));
			text_goodsdisper_maxwon.setText(map.get("e_pDcPerLimit"));
			
			float sell =Float.parseFloat(map.get("sell_pri"));
			float per =Float.parseFloat(map.get("e_pDcPer"));
			
			int result = Math.round(sell*per/100);
			result = (result /10)*10;
			text_goodsdisper_dis.setText(String.valueOf(result));
			spinner_goodsdisper_count.setValue(Integer.parseInt(map.get("e_pAppCnt")));
			
			if( map.get("e_pOnlyOne").equals("0")){
				checkBox_goodsdisper_over.setSelected(false);
			}else{
				checkBox_goodsdisper_over.setSelected(true);
			}			
			break;			
		}
		
				
		east_btn_save.setText("쿠폰 수정");
		east_btn_save.setActionCommand("쿠폰수정");				
				
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
		east_btn_callevent.setToolTipText("<html>\r\n\uD648\uD398\uC774\uC9C0 \uAD00\uB9AC\uC790 \uD398\uC774\uC9C0\uC5D0\uC11C \uB4F1\uB85D\uD55C \uC774\uBCA4\uD2B8 \uBAA9\uB85D\uC744 \uBD88\uB7EC\uC635\uB2C8\uB2E4.\r\n</html>");
		east_btn_callevent.setActionCommand("이벤트불러오기");
		east_btn_callevent.addActionListener(this);
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
		
		east_combo_gubun = new JComboBox<String>();
		east_combo_gubun.setModel(new DefaultComboBoxModel<String>(coupon_listitem));
		panel_east.add(east_combo_gubun, "cell 2 6 3 1,growx");
		east_combo_gubun.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(e.getStateChange() == 1){
					
					System.out.println(e.getItem());			
					String list_item = (String)e.getItem();
					for(int i = 0; i < coupon_listitem.length; i++){
						String item = coupon_listitem[i];
						
						if(list_item.equals(item)){
							System.out.println(coupon_panellist[i]);
							east_cardlayout.show(east_panel_data, coupon_panellist[i]);
						}	
					}
					
					if(e.getItem().equals("포인트 적립쿠폰")){
						east_radio_membery.setSelected(true);						
						east_radio_memberyn.setEnabled(false);
						east_radio_membery.setEnabled(false);						
					}else{												
						east_radio_memberyn.setEnabled(true);
						east_radio_membery.setEnabled(true);
					}
					
				}
			}
		});
		
		
		east_label_setdate = new JLabel("\uC0AC\uC6A9 \uAE30\uAC04");
		panel_east.add(east_label_setdate, "cell 0 7,alignx trailing,aligny center");
		
		lblNewLabel_4 = new JLabel("~");
		panel_east.add(lblNewLabel_4, "cell 3 7");
		
		Calendar c = Calendar.getInstance();
		//c.add(Calendar.YEAR, -10);

		east_chooser_start = new JDateChooser(c.getTime());
		east_chooser_start.setLocale(Locale.KOREA);		
		east_chooser_start.setDateFormatString("yyyy-MM-dd");
		panel_east.add(east_chooser_start, "cell 2 7,growx");
		
		east_chooser_end = new JDateChooser(c.getTime());
		east_chooser_end.setLocale(Locale.KOREA);
		east_chooser_end.setDateFormatString("yyyy-MM-dd");
		panel_east.add(east_chooser_end, "cell 4 7,growx");
		
		east_label_memberyn = new JLabel("\uD68C\uC6D0 \uC124\uC815");
		panel_east.add(east_label_memberyn, "cell 0 8,alignx trailing,aligny center");
		
		east_radio_memberyn = new JRadioButton("\uD68C\uC6D0+\uBE44\uD68C\uC6D0");
		east_radio_memberyn.setName("0");
		east_radio_memberyn.setEnabled(false);
		panel_east.add(east_radio_memberyn, "cell 2 8");
		
		east_radio_membery = new JRadioButton("\uD68C\uC6D0");
		east_radio_membery.setName("1");
		east_radio_membery.setEnabled(false);
		east_radio_membery.setSelected(true);
		panel_east.add(east_radio_membery, "cell 4 8");
		
		east_bg_memberyn = new ButtonGroup();
		east_bg_memberyn.add(east_radio_memberyn);
		east_bg_memberyn.add(east_radio_membery);
		
		east_label_memoverlap = new JLabel("\uC911\uBCF5 \uC124\uC815");
		panel_east.add(east_label_memoverlap, "cell 0 9,alignx trailing,aligny center");
		
		east_radio_overlapn = new JRadioButton("\uD5C8\uC6A9\uC548\uD568");
		east_radio_overlapn.setName("0");
		east_radio_overlapn.setSelected(true);
		panel_east.add(east_radio_overlapn, "cell 2 9");
		
		east_radio_overlapy = new JRadioButton("\uD5C8\uC6A9\uD568");
		east_radio_overlapy.setName("1");
		panel_east.add(east_radio_overlapy, "cell 4 9");
		
		east_bg_overlapyn = new ButtonGroup();
		east_bg_overlapyn.add(east_radio_overlapn);
		east_bg_overlapyn.add(east_radio_overlapy);		
		
		east_cardlayout = new CardLayout();
		east_panel_data = new JPanel();
		panel_east.add(east_panel_data, "cell 0 10 5 1,grow");
		east_panel_data.setLayout(east_cardlayout);
		
		east_panel_point = new JPanel();		
		east_panel_point.setBorder(new LineBorder(new Color(0, 0, 0)));
		east_panel_data.add("point", east_panel_point);
		east_panel_point.setLayout(new MigLayout("", "[grow][48px][116px][22px][grow]", "[grow][21px][grow]"));
		
		east_label_point = new JLabel("\uC801\uB9BD\uC810\uC218");
		east_panel_point.add(east_label_point, "cell 1 1,alignx left,aligny center");
		
		east_text_point = new JTextField();
		east_text_point.setHorizontalAlignment(SwingConstants.RIGHT);		
		east_panel_point.add(east_text_point, "cell 2 1,alignx left,aligny top");
		east_text_point.setColumns(10);
		east_text_point.addKeyListener(this);
		
		east_label_pointright = new JLabel("(\uC810)");
		east_panel_point.add(east_label_pointright, "cell 3 1,alignx left,aligny center");
		
		east_panel_prizes = new JPanel();
		east_panel_prizes.setBorder(new LineBorder(new Color(0, 0, 0)));
		east_panel_data.add("prizes", east_panel_prizes);
		east_panel_prizes.setLayout(new MigLayout("", "[57px][][][][grow]", "[grow][21px][][grow]"));
		
		east_label_prizesname = new JLabel("\uC81C\uD488\uBA85");
		east_panel_prizes.add(east_label_prizesname, "cell 0 1,alignx trailing,aligny center");
		
		east_text_prizesname = new JTextField();
		east_panel_prizes.add(east_text_prizesname, "cell 1 1 4 1,growx,aligny top");
		east_text_prizesname.setColumns(10);
		
		east_label_prizescount = new JLabel("\uC218\uB7C9");
		east_panel_prizes.add(east_label_prizescount, "cell 0 2,alignx trailing");
		
		east_btn_prizescountup = new JButton("\u25B6");
		east_btn_prizescountup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int c = Integer.parseInt(east_text_prizescount.getText());
				if(c < 99){
					c++;
					east_text_prizescount.setText(String.valueOf(c));
				}
			}
		});
		
		east_btn_prizescountdown = new JButton("\u25C0");
		east_btn_prizescountdown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int c = Integer.parseInt(east_text_prizescount.getText());
				if(c > 0){
					c--;
					east_text_prizescount.setText(String.valueOf(c));
				}				
			}
		});
		east_panel_prizes.add(east_btn_prizescountdown, "cell 1 2");
		
		east_text_prizescount = new JTextField();
		east_text_prizescount.setEditable(false);
		east_text_prizescount.setText("0");
		east_text_prizescount.setHorizontalAlignment(SwingConstants.RIGHT);
		east_panel_prizes.add(east_text_prizescount, "cell 2 2");
		east_text_prizescount.setColumns(3);
		east_panel_prizes.add(east_btn_prizescountup, "cell 3 2");
		
		esat_panel_goodsprizes = new JPanel();
		esat_panel_goodsprizes.setBorder(new LineBorder(new Color(0, 0, 0)));
		east_panel_data.add("goodsprizes", esat_panel_goodsprizes);
		esat_panel_goodsprizes.setLayout(new MigLayout("", "[][50px][grow][]", "[grow][][][][grow]"));
		
		label_goodsprizes_barcode = new JLabel("\uBC14\uCF54\uB4DC");
		esat_panel_goodsprizes.add(label_goodsprizes_barcode, "cell 0 1,alignx trailing");
		
		text_goodsprizes_barcode = new JTextField();
		text_goodsprizes_barcode.setEditable(false);
		esat_panel_goodsprizes.add(text_goodsprizes_barcode, "cell 1 1 2 1,growx");
		text_goodsprizes_barcode.setColumns(10);
		
		btn_goodsprizes_search = new JButton("\uAC80\uC0C9");
		btn_goodsprizes_search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object[] textfield = {text_goodsprizes_barcode, text_goodsprizes_gname};
				GoodsSearch goods = new GoodsSearch();
				goods.getGoodsPrizes(textfield);				
				goods.setAlwaysOnTop(true);
				goods.setLocationRelativeTo(Event_Manage.this);
				goods.setVisible(true);				
			}
		});
		esat_panel_goodsprizes.add(btn_goodsprizes_search, "cell 3 1");
		
		label_goodsprizes_gname = new JLabel("\uC81C\uD488\uBA85");
		esat_panel_goodsprizes.add(label_goodsprizes_gname, "cell 0 2,alignx trailing");
		
		text_goodsprizes_gname = new JTextField();
		text_goodsprizes_gname.setEditable(false);
		esat_panel_goodsprizes.add(text_goodsprizes_gname, "cell 1 2 2 1,growx");
		text_goodsprizes_gname.setColumns(10);
		
		label_goodsprizes_count = new JLabel("\uC218   \uB7C9");
		esat_panel_goodsprizes.add(label_goodsprizes_count, "cell 0 3");
		
		spinner_goodsprizes_count = new JSpinner();
		spinner_goodsprizes_count.setModel(new SpinnerNumberModel(1, 1, 10, 1));
		esat_panel_goodsprizes.add(spinner_goodsprizes_count, "cell 1 3,growx");
		
		east_panel_pricediscount_won = new JPanel();
		east_panel_pricediscount_won.setBorder(new LineBorder(new Color(0, 0, 0)));
		east_panel_data.add("pricediscount_won", east_panel_pricediscount_won);
		east_panel_pricediscount_won.setLayout(new MigLayout("", "[][grow][]", "[grow][][][grow]"));
		
		label_pricediscount_won = new JLabel("\uD560\uC778\uAE08\uC561(\uC6D0)");
		east_panel_pricediscount_won.add(label_pricediscount_won, "cell 0 1,alignx trailing");
		
		text_pricediscount_won = new JTextField();
		text_pricediscount_won.setHorizontalAlignment(SwingConstants.RIGHT);
		text_pricediscount_won.setText("0");
		east_panel_pricediscount_won.add(text_pricediscount_won, "cell 1 1,growx");
		text_pricediscount_won.setColumns(10);
		text_pricediscount_won.addKeyListener(this);
		
		lable_won = new JLabel("(\uC6D0)");
		east_panel_pricediscount_won.add(lable_won, "cell 2 1");
		
		label_pricediscount_minwon = new JLabel("\uCD5C\uC18C \uAD6C\uB9E4 \uAE08\uC561");
		east_panel_pricediscount_won.add(label_pricediscount_minwon, "cell 0 2,alignx trailing");
		
		text_pricediscount_minwon = new JTextField();
		text_pricediscount_minwon.setHorizontalAlignment(SwingConstants.RIGHT);
		text_pricediscount_minwon.setText("0");
		east_panel_pricediscount_won.add(text_pricediscount_minwon, "cell 1 2,growx");
		text_pricediscount_minwon.setColumns(10);
		text_pricediscount_minwon.addKeyListener(this);
		
		label_won1 = new JLabel("(\uC6D0)");
		east_panel_pricediscount_won.add(label_won1, "cell 2 2");
		
		east_panel_pricediscount_percent = new JPanel();
		east_panel_pricediscount_percent.setBorder(new LineBorder(new Color(0, 0, 0)));
		east_panel_data.add("pricediscount_percent", east_panel_pricediscount_percent);
		east_panel_pricediscount_percent.setLayout(new MigLayout("", "[][grow][]", "[grow][][][][grow]"));
		
		label_pricediscount_percent = new JLabel("\uD560\uC778\uC728(%)");
		east_panel_pricediscount_percent.add(label_pricediscount_percent, "cell 0 1,alignx trailing");
		
		text_pricediscount_percent = new JTextField();
		text_pricediscount_percent.setHorizontalAlignment(SwingConstants.RIGHT);
		text_pricediscount_percent.setText("0");
		east_panel_pricediscount_percent.add(text_pricediscount_percent, "cell 1 1,growx");
		text_pricediscount_percent.setColumns(5);
		text_pricediscount_percent.addKeyListener(this);
		
		text_pircediscount_won = new JLabel("(%)");
		east_panel_pricediscount_percent.add(text_pircediscount_won, "cell 2 1");
		
		label_pricediscount_maxwon = new JLabel("\uCD5C\uB300 \uD560\uC778 \uAE08\uC561");
		east_panel_pricediscount_percent.add(label_pricediscount_maxwon, "cell 0 2,alignx trailing");
		
		text_pricediscount_maxwon = new JTextField();
		text_pricediscount_maxwon.setText("0");
		text_pricediscount_maxwon.setHorizontalAlignment(SwingConstants.RIGHT);
		east_panel_pricediscount_percent.add(text_pricediscount_maxwon, "cell 1 2,growx");
		text_pricediscount_maxwon.setColumns(10);
		text_pricediscount_maxwon.addKeyListener(this);
		
		label_pricediscount_won1 = new JLabel("(\uC6D0)");
		east_panel_pricediscount_percent.add(label_pricediscount_won1, "cell 2 2");
		
		label_pricediscount_minper = new JLabel("\uCD5C\uC18C \uAD6C\uB9E4 \uAE08\uC561");
		east_panel_pricediscount_percent.add(label_pricediscount_minper, "cell 0 3,alignx trailing");
		
		text_pricediscount_minper = new JTextField();
		text_pricediscount_minper.setText("0");
		text_pricediscount_minper.setHorizontalAlignment(SwingConstants.RIGHT);
		east_panel_pricediscount_percent.add(text_pricediscount_minper, "cell 1 3,growx");
		text_pricediscount_minper.setColumns(10);
		text_pricediscount_minper.addKeyListener(this);
		
		label_pricediscount_won2 = new JLabel("(\uC6D0)");
		east_panel_pricediscount_percent.add(label_pricediscount_won2, "cell 2 3");
		
		east_panel_goodsdiscount_won = new JPanel();
		east_panel_goodsdiscount_won.setBorder(new LineBorder(new Color(0, 0, 0)));
		east_panel_data.add("goodsdiscount_won", east_panel_goodsdiscount_won);
		east_panel_goodsdiscount_won.setLayout(new MigLayout("", "[grow][grow][][][grow]", "[][][][][][]"));
		
		label_goodsdiscountwon_barcode = new JLabel("\uBC14\uCF54\uB4DC");
		east_panel_goodsdiscount_won.add(label_goodsdiscountwon_barcode, "cell 0 0,alignx trailing");
		
		text_goodsdiswon_barcode = new JTextField();
		text_goodsdiswon_barcode.setEditable(false);
		east_panel_goodsdiscount_won.add(text_goodsdiswon_barcode, "cell 1 0 3 1,growx");
		text_goodsdiswon_barcode.setColumns(10);		
		text_goodsdiswon_barcode.addKeyListener(this);
		
		btn_goodsdiswon_search = new JButton("\uAC80\uC0C9");
		btn_goodsdiswon_search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object[] textfield = {text_goodsdiswon_barcode, text_goodsdiswon_gname, text_goodsdiswon_purpri, text_goodsdiswon_sellpri};
				GoodsSearch goods = new GoodsSearch();
				goods.getGoodsInfo(textfield);				
				goods.setAlwaysOnTop(true);
				goods.setLocationRelativeTo(Event_Manage.this);
				goods.setVisible(true);
			}
		});
		east_panel_goodsdiscount_won.add(btn_goodsdiswon_search, "cell 4 0");
		
		label_goodsdiswon_gname = new JLabel("\uC81C\uD488\uBA85");
		east_panel_goodsdiscount_won.add(label_goodsdiswon_gname, "cell 0 1,alignx trailing");
		
		text_goodsdiswon_gname = new JTextField();
		text_goodsdiswon_gname.setEditable(false);
		east_panel_goodsdiscount_won.add(text_goodsdiswon_gname, "cell 1 1 4 1,growx");
		text_goodsdiswon_gname.setColumns(10);
		
		label_goodsdiswon_purpri = new JLabel("\uB9E4\uC785\uAC00");
		east_panel_goodsdiscount_won.add(label_goodsdiswon_purpri, "cell 0 2,alignx trailing");
		
		text_goodsdiswon_purpri = new JTextField();
		text_goodsdiswon_purpri.setEditable(false);
		text_goodsdiswon_purpri.setHorizontalAlignment(SwingConstants.RIGHT);
		text_goodsdiswon_purpri.setText("0");
		east_panel_goodsdiscount_won.add(text_goodsdiswon_purpri, "cell 1 2 2 1,growx");
		text_goodsdiswon_purpri.setColumns(7);
		
		label_goodsdiswon_sellpri = new JLabel("\uD310\uB9E4\uAC00");
		east_panel_goodsdiscount_won.add(label_goodsdiswon_sellpri, "cell 3 2,alignx trailing");
		
		text_goodsdiswon_sellpri = new JTextField();
		text_goodsdiswon_sellpri.setEditable(false);
		text_goodsdiswon_sellpri.setHorizontalAlignment(SwingConstants.RIGHT);
		text_goodsdiswon_sellpri.setText("0");
		east_panel_goodsdiscount_won.add(text_goodsdiswon_sellpri, "cell 4 2,growx");
		text_goodsdiswon_sellpri.setColumns(7);
		
		label_goodsdiswon_diswon = new JLabel("\uD560\uC778\uAE08\uC561(\uC6D0)");
		east_panel_goodsdiscount_won.add(label_goodsdiswon_diswon, "cell 0 3,alignx trailing");
		
		text_goodsdiswon_diswon = new JTextField();
		text_goodsdiswon_diswon.setHorizontalAlignment(SwingConstants.RIGHT);
		text_goodsdiswon_diswon.setText("0");
		east_panel_goodsdiscount_won.add(text_goodsdiswon_diswon, "cell 1 3,growx");
		text_goodsdiswon_diswon.setColumns(7);
		text_goodsdiswon_diswon.addKeyListener(this);
		
		label_goodsdiswon_won1 = new JLabel("(\uC6D0)");
		east_panel_goodsdiscount_won.add(label_goodsdiswon_won1, "cell 2 3");
		
		label_goodsdiswon_count = new JLabel("\uC801\uC6A9 \uC218\uB7C9");
		east_panel_goodsdiscount_won.add(label_goodsdiswon_count, "cell 0 4,alignx trailing");
		
		spinner_goodsdiswon_count = new JSpinner();
		spinner_goodsdiswon_count.setModel(new SpinnerNumberModel(0, 0, 100, 1));
		east_panel_goodsdiscount_won.add(spinner_goodsdiswon_count, "cell 1 4,growx");
		
		label_goodsdiswon_all = new JLabel("(*)0\uC785\uB825\uC2DC \uC804\uCCB4\uC218\uB7C9 \uC801\uC6A9");
		label_goodsdiswon_all.setForeground(Color.RED);
		east_panel_goodsdiscount_won.add(label_goodsdiswon_all, "cell 2 4 3 1");
		
		chkbox_goodsdiswon_over = new JCheckBox("1\uC7781\uD68C \uC0AC\uC6A9\uD6C4 \uC18C\uBA78");
		east_panel_goodsdiscount_won.add(chkbox_goodsdiswon_over, "cell 1 5 4 1");
		
		east_panel_goodsdiscount_percent = new JPanel();
		east_panel_goodsdiscount_percent.setBorder(new LineBorder(new Color(0, 0, 0)));
		east_panel_data.add("goodsdiscount_percent", east_panel_goodsdiscount_percent);
		east_panel_goodsdiscount_percent.setLayout(new MigLayout("", "[grow][70px][][][grow]", "[][][][][][]"));
		
		label_goodsdisper_barcode = new JLabel("\uBC14\uCF54\uB4DC");
		east_panel_goodsdiscount_percent.add(label_goodsdisper_barcode, "cell 0 0,alignx trailing");
		
		text_goodsdisper_barcode = new JTextField();
		text_goodsdisper_barcode.setEditable(false);
		text_goodsdisper_barcode.setColumns(10);
		east_panel_goodsdiscount_percent.add(text_goodsdisper_barcode, "cell 1 0 3 1,growx");
		
		btn_goodsdisper_search = new JButton("\uAC80\uC0C9");
		btn_goodsdisper_search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object[] textfield = {text_goodsdisper_barcode, text_goodsdisper_gname, text_goodsdisper_purpri, text_goodsdisper_sellpri};
				GoodsSearch goods = new GoodsSearch();
				goods.getGoodsInfo(textfield);				
				goods.setAlwaysOnTop(true);
				goods.setLocationRelativeTo(Event_Manage.this);				
				goods.setVisible(true);
			}			
		});
		east_panel_goodsdiscount_percent.add(btn_goodsdisper_search, "cell 4 0");
		
		label_goodsdisper_gname = new JLabel("\uC81C\uD488\uBA85");
		east_panel_goodsdiscount_percent.add(label_goodsdisper_gname, "cell 0 1,alignx trailing");
		
		text_goodsdisper_gname = new JTextField();
		text_goodsdisper_gname.setEditable(false);
		text_goodsdisper_gname.setColumns(10);
		east_panel_goodsdiscount_percent.add(text_goodsdisper_gname, "cell 1 1 4 1,growx");
		
		label_goodsdisper_purpri = new JLabel("\uB9E4\uC785\uAC00");
		east_panel_goodsdiscount_percent.add(label_goodsdisper_purpri, "cell 0 2,alignx trailing");
		
		text_goodsdisper_purpri = new JTextField();
		text_goodsdisper_purpri.setHorizontalAlignment(SwingConstants.RIGHT);
		text_goodsdisper_purpri.setText("0");
		text_goodsdisper_purpri.setEditable(false);
		text_goodsdisper_purpri.setColumns(7);
		east_panel_goodsdiscount_percent.add(text_goodsdisper_purpri, "cell 1 2 2 1,growx");
		
		label_goodsdisper_sellpri = new JLabel("\uD310\uB9E4\uAC00");
		east_panel_goodsdiscount_percent.add(label_goodsdisper_sellpri, "cell 3 2,alignx trailing");
		
		text_goodsdisper_sellpri = new JTextField();
		text_goodsdisper_sellpri.setHorizontalAlignment(SwingConstants.RIGHT);
		text_goodsdisper_sellpri.setText("0");
		text_goodsdisper_sellpri.setEditable(false);
		text_goodsdisper_sellpri.setColumns(7);
		east_panel_goodsdiscount_percent.add(text_goodsdisper_sellpri, "cell 4 2,growx");
		
		label_goodsdisper_disper = new JLabel("\uD560\uC778\uC728(%)");
		east_panel_goodsdiscount_percent.add(label_goodsdisper_disper, "cell 0 3,alignx trailing");
		
		text_goodsdisper_disper = new JTextField();
		text_goodsdisper_disper.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				  
				if (!Character.isDigit(c)) {
				   e.consume();
				   return;
				}
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub		
				if(e.getKeyCode() == KeyEvent.VK_ENTER){					
					
					float sell = Float.parseFloat(text_goodsdisper_sellpri.getText());
					float per = Float.parseFloat(text_goodsdisper_disper.getText());
					
					int result = Math.round(sell*per/100);
					result = (result /10)*10;
					
					text_goodsdisper_dis.setText(String.valueOf(result));
				}		
			}
		});
		text_goodsdisper_disper.setHorizontalAlignment(SwingConstants.RIGHT);
		text_goodsdisper_disper.setText("0");
		text_goodsdisper_disper.setColumns(10);
		east_panel_goodsdiscount_percent.add(text_goodsdisper_disper, "cell 1 3,growx");		
		
		label_goodsdisper_won1 = new JLabel("(%)");
		east_panel_goodsdiscount_percent.add(label_goodsdisper_won1, "cell 2 3");
		
		label_goodsdisper_dis = new JLabel("\uD560   \uC778");
		east_panel_goodsdiscount_percent.add(label_goodsdisper_dis, "cell 3 3,alignx trailing");
		
		text_goodsdisper_dis = new JTextField();
		text_goodsdisper_dis.setHorizontalAlignment(SwingConstants.RIGHT);
		text_goodsdisper_dis.setText("0");
		text_goodsdisper_dis.setEditable(false);
		east_panel_goodsdiscount_percent.add(text_goodsdisper_dis, "cell 4 3,growx");
		text_goodsdisper_dis.setColumns(7);
		text_goodsdisper_dis.addKeyListener(this);
		
		label_goodsdisper_maxwon = new JLabel("\uCD5C\uB300 \uD560\uC778 \uAE08\uC561");
		east_panel_goodsdiscount_percent.add(label_goodsdisper_maxwon, "cell 0 4,alignx trailing");
		
		text_goodsdisper_maxwon = new JTextField();
		text_goodsdisper_maxwon.setHorizontalAlignment(SwingConstants.RIGHT);
		text_goodsdisper_maxwon.setText("0");
		east_panel_goodsdiscount_percent.add(text_goodsdisper_maxwon, "cell 1 4,growx");
		text_goodsdisper_maxwon.setColumns(10);
		
		label_goodsdisper_won2 = new JLabel("(\uC6D0)");
		east_panel_goodsdiscount_percent.add(label_goodsdisper_won2, "cell 2 4");
		
		checkBox_goodsdisper_over = new JCheckBox("1\uC7781\uD68C \uC0AC\uC6A9\uD6C4 \uC18C\uBA78");
		checkBox_goodsdisper_over.setHorizontalAlignment(SwingConstants.RIGHT);
		east_panel_goodsdiscount_percent.add(checkBox_goodsdisper_over, "cell 3 4 2 1,growx");
		
		label_goodsdisper_count = new JLabel("\uC801\uC6A9 \uC218\uB7C9");
		east_panel_goodsdiscount_percent.add(label_goodsdisper_count, "cell 0 5,alignx trailing");
		
		spinner_goodsdisper_count = new JSpinner();
		spinner_goodsdisper_count.setModel(new SpinnerNumberModel(0, 0, 100, 1));
		east_panel_goodsdiscount_percent.add(spinner_goodsdisper_count, "cell 1 5,growx");
		
		label_goodsdisper_all = new JLabel("(*)0\uC785\uB825\uC2DC \uC804\uCCB4\uC218\uB7C9 \uC801\uC6A9");
		label_goodsdisper_all.setForeground(Color.RED);
		east_panel_goodsdiscount_percent.add(label_goodsdisper_all, "cell 2 5 3 1");
		
		east_label_useyn = new JLabel("\uC0AC\uC6A9\uC5EC\uBB34");
		panel_east.add(east_label_useyn, "cell 0 11,alignx trailing");
		
		east_radio_usey = new JRadioButton("\uC0AC\uC6A9\uD568");
		east_radio_usey.setName("0");
		east_radio_usey.setSelected(true);
		panel_east.add(east_radio_usey, "cell 2 11");
		
		east_radio_usen = new JRadioButton("\uC0AC\uC6A9\uC548\uD568");
		east_radio_usen.setName("1");
		panel_east.add(east_radio_usen, "cell 4 11");
		
		east_bg_useyn = new ButtonGroup();
		east_bg_useyn.add(east_radio_usey);
		east_bg_useyn.add(east_radio_usen);
		
		east_btn_save = new JButton("\uCFE0\uD3F0\uB4F1\uB85D");
		east_btn_save.setActionCommand("쿠폰등록");
		east_btn_save.addActionListener(this);
		
		east_btn_renew = new JButton("\uC0C8\uB85C\uC785\uB825");
		east_btn_renew.setActionCommand("새로입력");
		east_btn_renew.addActionListener(this);
		panel_east.add(east_btn_renew, "cell 0 13 2 2,grow");
		east_btn_save.setForeground(Color.RED);
		east_btn_save.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		east_btn_save.setBackground(Color.BLUE);
		panel_east.add(east_btn_save, "cell 4 13 1 2,grow");
		
	}
		
	//이벤트 사용 리스트
	private void event_UseList(){
		//<html></html><br>
		String[] colunm_uselist = {"순번", "<html>쿠폰 코드<br>쿠폰 명<br>쿠폰바코드<br>쿠폰 구분</html>", "seq", 
				"<html>전표번호<br>판매일자<br>판매시간</html>", "고객정보", "포인트"	, "<html>제품명<br>수량</html>", 
				"<html>바코드<br>상품명<br>수량</html>", "할인(원)", "<html>할인(%)<br>실제할인액</html>", 
				"<html>바코드<br>상품명<br>할인(원)/개<br>총할인금액/개</html>", "<html>바코드<br>상품명<br>할인(%)/개<br>실제할인액/개<br>총할인금액/개</html>",
				"<html>판매일<br>판매자</html>", "<html>반품구분<br>원전표번호</html>", 
				"<html>삭제구분<br>삭제일</html>"};
	
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
	  
	  cneter_scroll_uselist = new JScrollPane();
	  center_tabbed_couponuselist.add(cneter_scroll_uselist, BorderLayout.CENTER);
	  
	  center_table_uselist = new JTable(dtm_uselist){
	             public boolean getScrollableTracksViewportWidth()
	             {
	                 return getPreferredSize().width < getParent().getWidth();
	             }
	  };
	  cneter_scroll_uselist.setViewportView(center_table_uselist);
	     
	  center_table_uselist.setTableHeader(new JTableHeader(center_table_uselist.getColumnModel()) {
          @Override public Dimension getPreferredSize() {
            Dimension d = super.getPreferredSize();
            d.height = 110;
            return d;
          }
     });
	  
	  ((DefaultTableCellRenderer)center_table_uselist.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(0);
	  
	  center_table_uselist.setRowHeight(110);
	  //table_offmem_list.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);  //가로 스크롤  
	    
	  center_table_uselist.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);  //가로 스크롤
	  center_table_uselist.getTableHeader().setReorderingAllowed(false);  //이동불가
	  
	  /*table_offmem_list.setAutoCreateRowSorter(true);
	  TableRowSorter<TableModel> tsorter_main = new TableRowSorter<TableModel>(table_offmem_list.getModel());
	  table_offmem_list.setRowSorter(tsorter_main);*/ 
	  
	  //center_table_uselist.setAutoCreateRowSorter(true);
	  
	    TableColumnModel tcm_uselist = center_table_uselist.getColumnModel();
	
	    //tcm_uselist.getColumn(0).setMaxWidth(Integer.MAX_VALUE);
	    tcm_uselist.getColumn(0).setWidth(40);	    
	    tcm_uselist.getColumn(0).setPreferredWidth(40);
	
	    //tcm_uselist.getColumn(1).setMaxWidth(Integer.MAX_VALUE);
	    tcm_uselist.getColumn(1).setWidth(100);	    
	    tcm_uselist.getColumn(1).setPreferredWidth(100);
	    	    
	    tcm_uselist.getColumn(2).setWidth(30);	 	    
	    tcm_uselist.getColumn(2).setPreferredWidth(30);
	    
	    tcm_uselist.getColumn(3).setWidth(100);
	    tcm_uselist.getColumn(3).setPreferredWidth(100);
	    
	    tcm_uselist.getColumn(4).setWidth(80);	    
	    tcm_uselist.getColumn(4).setPreferredWidth(80);
	    
	    tcm_uselist.getColumn(5).setWidth(80);
	    tcm_uselist.getColumn(5).setPreferredWidth(80);
	    
	    tcm_uselist.getColumn(6).setWidth(100);	    
	    tcm_uselist.getColumn(6).setPreferredWidth(100);
	    
	    tcm_uselist.getColumn(7).setWidth(150);
	    tcm_uselist.getColumn(7).setPreferredWidth(150);
	    	    
	    tcm_uselist.getColumn(8).setWidth(80);	    
	    tcm_uselist.getColumn(8).setPreferredWidth(80);
	    
	    tcm_uselist.getColumn(9).setWidth(100);	    
	    tcm_uselist.getColumn(9).setPreferredWidth(100);
	    
	    tcm_uselist.getColumn(10).setWidth(150);
	    tcm_uselist.getColumn(10).setPreferredWidth(150);
	    
	    tcm_uselist.getColumn(11).setWidth(150);
	    tcm_uselist.getColumn(11).setPreferredWidth(150);
	    
	    tcm_uselist.getColumn(12).setWidth(150);
	    tcm_uselist.getColumn(12).setPreferredWidth(150);
	    
	    tcm_uselist.getColumn(13).setWidth(80);
	    tcm_uselist.getColumn(13).setPreferredWidth(80);
	    	    
	    tcm_uselist.getColumn(14).setWidth(80);
	    tcm_uselist.getColumn(14).setPreferredWidth(80);	    
	    
		for(int i=0; i < 15; i++){
			tcm_uselist.getColumn(i).setCellRenderer(celAlignCenter);
		}
	}
		
	//상단 검색 정보 불러오기
	public void getTopSearchStart(){
		
		this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
		
		/*String query = "Select * From "
						 + "( Select x.*, Isnull( (Select Count(*) use_cnt From e_Coupon_History a Where x.e_Seq=a.e_Seq ), 0) use_cnt From e_Coupon_List x ) "
						 + " X Where 1=1 ";		
		*/
		
		
		String query = "SELECT * FROM ( " 
						+ "Select x.*, isnull((select g_name from goods a where x.e_bBarcode=a.barcode),'') g_name1, " 
						+ "isnull((select g_name from goods a where x.e_pBarcode=a.barcode),'') g_name2, " 
						+ "isnull((select count(*) use_cnt from e_Coupon_History a where x.e_seq=a.e_seq),0)  use_cnt "  
						+ "From e_Coupon_List x  ) X " 
						+ "Where 1=1 ";
						
						/*+ "AND x.DEL_YN = '0' AND x.e_Edate >=  '2016-03-23' "
						+ "Order by e_EDate DESC, Write_Date DESC, Edit_DATE DESC ";*/
		
		//조건값 가져오기
		//검색어
		if(top_text_searchname.getText().length() > 0){			
			query += "And (e_Seq='"+top_text_searchname.getText()+"' or e_CouponName Like '%"+top_text_searchname.getText()+"%' ) ";			
		}
		
		//쿠폰종류
		/*switch(top_combo_eventgubun.getSelectedIndex()){		
		case 1:
			query +=" And e_gubun='0' ";
			break;
		case 2:
			query +=" And e_gubun='1' ";
			break;
		}*/
		//쿠폰 구분
		if( 0 != top_combo_eventgubun.getSelectedIndex()){
			query +=" And e_gubun='"+(top_combo_eventgubun.getSelectedIndex()-1)+"' ";
		}
		
		//진행유무
		if( 0 != top_combo_ingyn.getSelectedIndex()){
			query +=" And Del_YN='"+(top_combo_ingyn.getSelectedIndex()-1)+"' ";
		}
				
		switch(top_combo_useyn.getSelectedIndex()){
		case 1:
			query +=" And x.use_cnt > 0";
			break;
		case 2:
			query +=" And x.use_cnt <= 0";
			break;
		}		
		
		query +=" Order by e_EDate DESC, Write_Date DESC, Edit_DATE DESC ";
		ms_connect.setMainSetting();
		ArrayList<HashMap<String, String>> temp_array = ms_connect.connection(query);
		
		//if(temp_array.size() <= 0) JOptionPane.showMessageDialog(this, "검색된 결과가 없습니다.");
		
		setEventList(temp_array);
		System.out.println(temp_array);
		
		this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}
	
	//목록 생성
	public void setEventList(ArrayList<HashMap<String, String>> temp_array){
		
		dtm_couponlist.setRowCount(0);
		setRenewCouponReg();
		
		//정렬해서 목록에 표시합니다.
		Iterator<HashMap<String, String>> itr = temp_array.iterator();
		int count=1;
		while(itr.hasNext()){
			HashMap<String, String> temp = itr.next();
			Vector<Object> v = new Vector<Object>();
			
			v.add(count);
			//String[] name = {temp.get("e_Seq"), temp.get("e_CouponName")};
			String name = "<html>"+temp.get("e_Seq")+"<br>"+temp.get("e_CouponName")+"</html>";
			v.add(name);
			String date = "<html>"+temp.get("e_Sdate")+"<br>"+temp.get("e_EDate")+"</html>";
			v.add(date);
			
			String member = "<html>";
			if(temp.get("e_MEM_YN").equals("0")){
				member += "전체<br>";
			}else{
				member += "회원<br>";
			}
			
			if(temp.get("e_Over_YN").equals("0")){
				member += "허용안함</html>";
			}else{
				member += "허용함</html>";
			}
			v.add(member);
			
			//쿠폰구분
			int gubun = Integer.parseInt(temp.get("e_gubun"));			
			v.add(coupon_listitem[gubun]);
			
			String point = "";		
			String prizes = "";
			String prizesgoods = "";
			String pricediswon = "";
			String pricedisper = "";
			String goodsdiswon = "";
			String goodsdisper = ""; 
			
			switch(gubun){
			case 0:
				point = temp.get("e_Point")+"(점)";
				break;
			case 1:
				prizes += "<html>"+temp.get("e_Product")+"<br>"+temp.get("e_pCnt")+"(개)</html>";
				break;
			case 2:
				prizesgoods = "<html>"+temp.get("e_bBarcode")+"<br>"+temp.get("g_name1")+"<br>"+temp.get("e_bCnt")+"(개)</html>";
				break;
			case 3:
				pricediswon = "<html>"+temp.get("e_DcPri")+"(원)<br>"+temp.get("e_MinLimitPri")+"(원)<br>불가</html>";				
				break;
			case 4:
				pricedisper = "<html>"+temp.get("e_DcPer")+"(%)<br>"+temp.get("e_DcPerLimit")+"(원)<br>"+temp.get("e_MinLimitPri")+"(원)<br>불가</html>";				
				break;
			case 5:
				goodsdiswon = "<html>"+temp.get("e_pBarcode")+"<br>"+temp.get("g_name2")+"<br>"+temp.get("e_pDCPri")+"(원)<br>";
				if(temp.get("e_pAppCnt").equals("0")){
					goodsdiswon += "전체수량<br>";
				}else{
					goodsdiswon += temp.get("e_pAppCnt")+"(개)<br>";
				}
				
				if( temp.get("e_pOnlyOne").equals("0")){
					goodsdiswon += "반복</html>";
				}else{
					goodsdiswon += "한번</html>";
				}
				break;
			case 6:
				goodsdisper = "<html>"+temp.get("e_pBarcode")+"<br>"+temp.get("g_name2")+"<br>"+temp.get("e_pDcPer")+"(%)<br>"+temp.get("e_pDcPerLimit")+"(원)<br>";
				if(temp.get("e_pAppCnt").equals("0")){
					goodsdisper += "전체수량<br>";
				}else{
					goodsdisper += temp.get("e_pAppCnt")+"(개)<br>";
				}
				
				if( temp.get("e_pOnlyOne").equals("0")){
					goodsdisper += "반복</html>";
				}else{
					goodsdisper += "한번</html>";
				}				
				break;				
			}
						
			v.add(point);
			v.add(prizes);
			v.add(prizesgoods);
			v.add(pricediswon);
			v.add(pricedisper);
			v.add(goodsdiswon);
			v.add(goodsdisper);
			
			
			String use = "";
			if(temp.get("use_cnt").equals("0")){
				use = "<html>미사용<br>0(건)</html>";
			}else{
				use = "<html>사용<br>"+temp.get("use_cnt")+"(건)</html>";
			}
			v.add(use);
			
			String reg_date = "<html>";
			
			String edit_date = "";
			try{
				if (temp.get("Edit_Date") == null){
					edit_date = "";
				}else{
					edit_date = temp.get("Edit_Date").substring(0, 19);
					//edit_date = temp.get("Edit_Date");
				}
			}catch(NullPointerException e){
				
			}
			
			String del_date = "";
			try{
				if(temp.get("Del_Date") == null){
					del_date = "";
				}else{
					del_date = temp.get("Del_Date").substring(0, 19);
				}
			}catch(NullPointerException e){
				
			}
			reg_date += "등: "+temp.get("Write_Date").substring(0, 19)+"<br>수: "+edit_date+"<br>삭: "+del_date+"</html>";
			
			v.add(reg_date);
						
			String ingyn = "";
			if(temp.get("Del_YN").equals("0")){
				ingyn = "사용함";
			}else{
				ingyn = "사용안함";
			}
			v.add(ingyn);	
			
			dtm_couponlist.addRow(v);
			count++;
		}
	}
	
	//이벤트 리스트를 불러와서 목록으로 보여 줍니다.
	public void getEventListChoose(){
		
		this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
		//목록을 호출합니다.
		JSONArray temp_event = trans_shopapi.getPushEventList();
				
		if(temp_event.size() <= 0){			
			JOptionPane.showMessageDialog(this, "이벤트 목록 검색에 실패 했습니다.");
			return;
		}
				
		String[] list = new String[temp_event.size()];
		for(int i = 0; i < temp_event.size(); i++){
			JSONObject temp_map = (JSONObject)temp_event.get(i);
			
			String item = temp_map.get("idx")+" : "+temp_map.get("subject");
			list[i] = item;			
		}
		
		//호출한 목록을 다이얼 로그로 띄웁니다.		
	    String input = (String) JOptionPane.showInputDialog(east_combo_gubun, "적용할 이벤트를 선택하세요!!",
	    		"이벤트 목록", JOptionPane.QUESTION_MESSAGE, null, // Use
	                                                                        // default
	                                                                        // icon
	        list, // Array of choices
	        list[0]); // Initial choice
	    System.out.println(input);
		
	    try{
			//선택한 이벤트를 불러 옵니다.
			east_text_code.setText(input.substring(0, input.indexOf(":")).trim());
			east_text_name.setText(input.substring(input.indexOf(":")+1, input.length()).trim());
	    }catch(NullPointerException e){
	    	this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	    }
	    
	    this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));		
	}
	
	//상단 검색 정보 초기화 하기
	public void setTopRenew(){
		
		top_text_searchname.setText("");
		top_combo_eventgubun.setSelectedIndex(0);
		top_combo_ingyn.setSelectedIndex(1);
		top_combo_useyn.setSelectedIndex(0);
		
	}
	
	//우측 쿠폰 수정화면 초기화 하기
	public void setRenewCouponReg(){
				
		east_btn_callevent.setEnabled(true);
		east_text_code.setText("");
		east_text_code.setEditable(true);
		east_text_name.setText("");
		
		east_combo_gubun.setSelectedIndex(0);
		east_combo_gubun.setEnabled(true);
			
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date today = new Date();
		String today_date = sdf.format(today);
		System.out.println(today_date);
		
		east_chooser_start.setDate(today);
		east_chooser_end.setDate(today);
		
		setRadioButtonisEnabled(false);
				
		//포인트초기화
		east_text_point.setText("0");
		
		//사은품초기화
		east_text_prizesname.setText("");
		east_text_prizescount.setText("0");
		
		//사은품(매장상품) 지급쿠폰
		text_goodsprizes_barcode.setText("");
		text_goodsprizes_gname.setText("");
		spinner_goodsprizes_count.setValue(1);
		
		//전체 금액(원) 할인
		text_pricediscount_won.setText("0");
		text_pricediscount_minwon.setText("0");
		
		//전체 금액(%) 할인
		text_pricediscount_percent.setText("0");
		text_pricediscount_maxwon.setText("0");
		text_pricediscount_minper.setText("0");
		
		//단품 금액(원) 할인
		text_goodsdiswon_barcode.setText("");
		text_goodsdiswon_gname.setText("");
		text_goodsdiswon_purpri.setText("");
		text_goodsdiswon_sellpri.setText("");
		text_goodsdiswon_diswon.setText("");
		spinner_goodsdiswon_count.setValue(0);
		chkbox_goodsdiswon_over.setSelected(false);
		
		//단품 금액(%) 할인
		text_goodsdisper_barcode.setText("");
		text_goodsdisper_gname.setText("");
		text_goodsdisper_purpri.setText("");
		text_goodsdisper_sellpri.setText("");
		text_goodsdisper_disper.setText("");
		text_goodsdisper_dis.setText("");
		text_goodsdisper_maxwon.setText("");
		checkBox_goodsdisper_over.setSelected(false);
				
		east_radio_usey.setSelected(true);
		
		east_btn_save.setText("쿠폰 등록");
		east_btn_save.setActionCommand("쿠폰등록");
	
		//초기화
		setCouponInsertItemReSet();
		
	}
	
	//쿠폰정보를 등록합니다.
	private void setCouponSave() {
				
		this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
		
		//모든 등록된 데이타를 불러옵니다.
		//코드가 등록 되었는지 확인 해야 합니다.
		//등록 되어 있다면 사용할수 없습니다.
		//오류 검사
		//등록 코드가 숫자로 이루어 졌는지 확인 합니다.
		//제목의 문자 길이를 측정합니다.
		//쿠폰 구분에 따라서 포인트 및 사은품명 또는 사은품 수량을 넣었는지 확인 합니다.
		//선택한 날자가 시작일이 종료일보다 앞인지 확인 합니다.		
		String code = east_text_code.getText();
		if(code.length() <= 0){
			JOptionPane.showMessageDialog(this, "이벤트/쿠폰 코드를 숫자로 입력해 주세요");
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			return;
		}
				
		String name = east_text_name.getText();
		if(name.length() <= 0){
			JOptionPane.showMessageDialog(this, "이벤트/쿠폰 이름을 입력해 주세요~!");
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			return;						
		}		
		if(name.length() > 25){
			JOptionPane.showMessageDialog(this, "이벤트/쿠폰 이름을 입력해 주세요~!");
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			return;						
		}
						
		String query = "Select * From e_Coupon_List Where e_seq='"+code+"' ";
		ms_connect.setMainSetting();
		ArrayList<HashMap<String, String>> map = ms_connect.connection(query);
		
		if(map.size() > 0){
			JOptionPane.showMessageDialog(this, "이미 등록된 쿠폰번호 입니다.");
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			return;	
		}
				
		int gubun = east_combo_gubun.getSelectedIndex();
		
		Date sdate = east_chooser_start.getDate();
		Date edate = east_chooser_end.getDate();
		
		int tallDate = sdate.compareTo(edate);
		System.out.println(tallDate);
		if(tallDate > 0){
			JOptionPane.showMessageDialog(this, "시작 날자가 종료일 보다 큽니다. 날자를 다시 설정해 주세요");
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			return;
		}
		
		String s_date = new SimpleDateFormat("yyyy-MM-dd").format(sdate);
		String e_date = new SimpleDateFormat("yyyy-MM-dd").format(edate);
		
		int memberyn = getRadioButtonSelect(east_bg_memberyn);
		int overlapyn = getRadioButtonSelect(east_bg_overlapyn);
		//int useyn = getRadioButtonSelect(east_bg_useyn);
				
		switch(gubun){
		case 0:
			if(setSavePoint()) return;
			break;
		case 1:
			if(setSavePrizes()) return;
			break;
		case 2:
			if(setSaveGoodsPrizes()) return;
			break;
		case 3:
			if(setSavePriceDiscountWon()) return;
			break;
		case 4:
			if(setSavePriceDiscountPer()) return;
			break;
		case 5:
			if(setSaveGoodsDiscountWon(s_date, e_date, "Insert", code)) return;
			break;
		case 6:
			if(setSaveGoodsDiscountPer(s_date, e_date, "Insert", code)) return;
			break;	
		}
				
		//유효성 검사하기		
		if(!isNumber(code)){
			JOptionPane.showMessageDialog(this, "이벤트/쿠폰 코드는 숫자만 등록 가능합니다.");
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			return;
		}else{			
			
		};
		
		query = "INSERT Into e_Coupon_List( "
				+ "e_Seq, "
				+ "e_CouponName, "
				+ "e_gubun, "
				+ "e_Sdate, "
				+ "e_Edate, "
				+ "e_MEM_YN, "
				+ "e_Over_YN , "
				+ "e_Point , "
				+ "e_Product , "
				+ "e_pCnt, "
				+ "e_bBarcode , "
				+ "e_bCnt, "
				+ "e_DcPri , "
				+ "e_DcPri_Oyn, "
				+ "e_DcPer , "
				+ "e_DcPerLimit, "
				+ "e_DcPer_Oyn, "
				+ "e_MinLimitPri, "
				+ "e_pBarcode , "
				+ "e_pDCPri, "
				+ "e_pDcPer , "
				+ "e_pDcPerLimit, "
				+ "Writer, "
				+ "e_pAppCnt, "
				+ "e_pOnlyOne ) "
				+ "Values( "
				+ "'"+code+"', "
				+ "'"+name+"', "
				+ "'"+gubun+"', "
				+ "'"+s_date+"', "
				+ "'"+e_date+"', "
				+ "'"+memberyn+"', "
				+ "'"+overlapyn+"', "
				+ "'"+e_Point+"', "
				+ "'"+e_Product+"', "
				+ e_pCnt+", "
				+ "'"+e_bBarcode+"', "
				+ e_bCnt+", "
				+ "'"+e_DcPri+"', "
				+ "'"+e_DcPri_Oyn+"', "
				+ "'"+e_DcPer+"', "
				+ "'"+e_DcPerLimit+"', "
				+ "'"+e_DcPer_Oyn+"', "
				+ "'"+e_MinLimitPri+"', "
				+ "'"+e_pBarcode+"', "
				+ "'"+e_pDCPri+"', "
				+ "'"+e_pDcPer+"', "
				+ "'"+e_pDcPerLimit+"', "
				+ "'shop', "
				+ e_pAppCnt+", "
				+ "'"+e_pOnlyOne+"' ) ";
		
		int result = ms_connect.connect_update(query);
		
		switch(result){
		case 0:			
			JOptionPane.showMessageDialog(this, "정상 등록 되었습니다.");
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			getTopSearchStart();
			
			return;				
		}		
		JOptionPane.showMessageDialog(this, "등록에 실패 했습니다.");
		this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}
	
	//쿠폰정보를 수정합니다.
	private void setCouponUpdate() {
				
		this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
		
		//모든 등록된 데이타를 불러옵니다.
		//코드가 등록 되었는지 확인 해야 합니다.
		//등록 되어 있다면 사용할수 없습니다.
		//오류 검사
		//등록 코드가 숫자로 이루어 졌는지 확인 합니다.
		//제목의 문자 길이를 측정합니다.
		//쿠폰 구분에 따라서 포인트 및 사은품명 또는 사은품 수량을 넣었는지 확인 합니다.
		//선택한 날자가 시작일이 종료일보다 앞인지 확인 합니다.
		
		//수정모드에선 코드는 변경 못합니다.
		String code = east_text_code.getText();
		
		String name = east_text_name.getText();
		if(name.length() <= 0 && name.length() > 25){
			JOptionPane.showMessageDialog(this, "이벤트/쿠폰 이름을 입력해 주세요~!");
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			return;						
		}
						
		int gubun = east_combo_gubun.getSelectedIndex();
		
		Date sdate = east_chooser_start.getDate();
		Date edate = east_chooser_end.getDate();
		
		int tallDate = sdate.compareTo(edate);
		System.out.println(tallDate);
		if(tallDate > 0){
			JOptionPane.showMessageDialog(this, "시작 날자가 종료일 보다 큽니다. 날자를 다시 설정해 주세요");
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			return;
		}
		
		String s_date = new SimpleDateFormat("yyyy-MM-dd").format(sdate);
		String e_date = new SimpleDateFormat("yyyy-MM-dd").format(edate);		
		
		int memberyn = getRadioButtonSelect(east_bg_memberyn);
		int overlapyn = getRadioButtonSelect(east_bg_overlapyn);
		int useyn = getRadioButtonSelect(east_bg_useyn);
		
		switch(gubun){
		case 0:
			if(setSavePoint()) return;
			break;
		case 1:
			if(setSavePrizes()) return;
			break;
		case 2:
			if(setSaveGoodsPrizes()) return;
			break;
		case 3:
			if(setSavePriceDiscountWon()) return;
			break;
		case 4:
			if(setSavePriceDiscountPer()) return;
			break;
		case 5:
			if(setSaveGoodsDiscountWon(s_date, e_date, "Update", code)) return;
			break;
		case 6:
			if(setSaveGoodsDiscountPer(s_date, e_date, "Update", code)) return;
			break;	
		}
		
		String query = "Update e_Coupon_List  Set e_CouponName='"+name+"', e_gubun='"+gubun+"', e_Sdate='"+s_date+"', "
				+ "e_Edate='"+e_date+"', e_MEM_YN='"+memberyn+"', e_Over_YN='"+overlapyn+"', "
				+ update_query
				+ ", del_yn='"+useyn+"', Edit_Date=getdate(), Editor='shop' Where e_Seq='"+code+"' ";
						
		ms_connect.setMainSetting();
		int result = ms_connect.connect_update(query);
		
		switch(result){
		case 0:			
			JOptionPane.showMessageDialog(this, "정상 수정 되었습니다.");
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			getTopSearchStart();
			return;				
		}		
		JOptionPane.showMessageDialog(this, "수정에 실패 했습니다.");
		this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}
	
	
	//포인트등록
	private boolean setSavePoint(){
		
		e_Point = east_text_point.getText();
		
		if(e_Point.length() <= 0){
			JOptionPane.showMessageDialog(this, "포인트 점수를 입력해 주세요!");
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			return true;
		}
		
		if(!isNumber(e_Point)){
			JOptionPane.showMessageDialog(this, "포인트는 숫자만 등록 가능합니다.");
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			return true;
		}
	
		update_query = "e_Point='"+e_Point+"' ";
		return false;
	}
	
	
	//사은품등록
	private boolean setSavePrizes(){
		
		e_Product = east_text_prizesname.getText();
		e_pCnt = Integer.parseInt(east_text_prizescount.getText());
		
		if(e_Product == null || "".equals(e_Product)){
			JOptionPane.showMessageDialog(this, "사은품명을 입력해 주세요.");
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			return true;
		}	
		
		if(e_pCnt <= 0){
			JOptionPane.showMessageDialog(this, "사은품 수량을 변경해 주세요.");
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			return true;				
		}
		
		update_query = "e_Product='"+e_Product+"', e_pCnt="+e_pCnt+" ";
		return false;
	}
	
	//사은품등록(상품)
	private boolean setSaveGoodsPrizes(){
		
		e_bBarcode = text_goodsprizes_barcode.getText();
		e_bCnt = (int)spinner_goodsprizes_count.getValue();
		
		if(e_bBarcode.length() <= 4 ){
			JOptionPane.showMessageDialog(this, "상품을 선택해 주세요!");
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			return true;
		}
		
		String query = "SELECT SCALE_USE,SALE_USE,BULK_USE FROM GOODS WHERE BARCODE='"+e_bBarcode+"'"; 
		ms_connect.setMainSetting();
		HashMap<String, String> map = ms_connect.selectQueryOne(query);
		
		if( map.size() <= 0){
			JOptionPane.showMessageDialog(this, "등록된 상품을 선택해 주세요!");
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			return true;
		}
		
		if( map.get("SCALE_USE").equals("1")){
			JOptionPane.showMessageDialog(this, "저울 상품은 사은품으로 등록 하실수 없습니다!");
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			return true;
		}
		
		if( map.get("SALE_USE").equals("1")){
			JOptionPane.showMessageDialog(this, "세일 상품은 사은품으로 등록 하실수 없습니다!");
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			return true;
		}
		
		if( map.get("BULK_USE").equals("1")){
			JOptionPane.showMessageDialog(this, "벌크 상품은 사은품으로 등록 하실수 없습니다!");
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			return true;
		}
		
		update_query = "e_bBarcode='"+e_bBarcode+"', e_bCnt="+e_bCnt+" ";
		return false;
	}		

	//전체금액할인
	private boolean setSavePriceDiscountWon(){
		
		e_DcPri = text_pricediscount_won.getText();
		e_MinLimitPri = text_pricediscount_minwon.getText();
		
		if(e_DcPri.length() <= 0){
			JOptionPane.showMessageDialog(this, "할인금액을 입력해 주세요!");
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			return true;
		}
		
		update_query = "e_DcPri='"+e_DcPri+"', e_MinLimitPri='"+e_MinLimitPri+"' ";
		return false;
	}
		
	

	//전체 퍼센트할인
	private boolean setSavePriceDiscountPer(){
		
		e_DcPer = text_pricediscount_percent.getText();
		e_DcPerLimit = text_pricediscount_maxwon.getText();
		e_MinLimitPri = text_pricediscount_minper.getText();	
		
		if(e_DcPer.length() <= 0 ){
			JOptionPane.showMessageDialog(this, "할인 퍼센트를 입력해 주세요!");
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			return true;
		}
		
		update_query = "e_DcPer='"+e_DcPer+"', e_DcPerLimit='"+e_DcPerLimit+"', e_MinLimitPri='"+e_MinLimitPri+"' ";
		return false;
	}
		
	

	//단품 금액할인
	private boolean setSaveGoodsDiscountWon(String s_date, String e_date, String gubun, String code){
		
		String update = "";
		
		if(gubun.equals("Update")){
			update = "and e_seq <> '"+code+"' ";
		}	
		
		e_pBarcode = text_goodsdiswon_barcode.getText();
		e_pDCPri = text_goodsdiswon_diswon.getText();
		e_pAppCnt = (int)spinner_goodsdiswon_count.getValue();
		
		if(chkbox_goodsdiswon_over.isSelected()){
			e_pOnlyOne = "1";
		}else{
			e_pOnlyOne = "0";
		}
		 
		if(e_pBarcode.length() <= 4 ){
			JOptionPane.showMessageDialog(this, "상품을 선택해 주세요!");
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			return true;
		}
		
		if(e_pDCPri.length() <= 0 ){
			JOptionPane.showMessageDialog(this, "상품 할인 금액을 입력해 주세요!");
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			return true;
		}
		
		String query = "Select e_gubun, e_Sdate, e_Edate From e_Coupon_List "
				+ "WHERE DEL_YN='0' AND e_Gubun in ('5','6') AND e_pBArcode = '"+e_pBarcode.trim()+"' "
				+ "AND ((e_Sdate Between  '"+s_date+"' AND '"+e_date+"') "
				+ "OR (e_Edate Between '"+s_date+"' AND '"+e_date+"')) "+update
				+ "Union all ";
		query += "Select e_gubun, e_Sdate, e_Edate From e_Coupon_List "
				+ "WHERE DEL_YN='0' AND e_Gubun in ('5','6') AND e_pBArcode = '"+e_pBarcode.trim()+"' "
				+ "And (e_Sdate <= '"+s_date+"'  and  e_Edate>='"+e_date+"') "+update;
		
		ms_connect.setMainSetting();
		ArrayList<HashMap<String, String>> maps = ms_connect.connection(query);
		
		if(maps.size() > 0){
			JOptionPane.showMessageDialog(this, "단품할인에 등록된 상품은 중복 등록 하실 수 없습니다!");
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			return true;
		}
		
		update_query = "e_pBarcode='"+e_pBarcode+"', e_pDCPri='"+e_pDCPri+"', e_pAppCnt="+e_pAppCnt+", e_pOnlyOne='"+e_pOnlyOne+"' ";
		return false;
	}
		
	
	//단품 퍼센트할인
	private boolean setSaveGoodsDiscountPer(String s_date, String e_date, String gubun, String code){
		
		String update = "";		
		if(gubun.equals("Update")){
			update = "and e_seq <> '"+code+"' ";
		}	
		e_pBarcode = text_goodsdisper_barcode.getText();
		e_pDcPer = text_goodsdisper_disper.getText();
		e_pDcPerLimit = text_goodsdisper_maxwon.getText();
		e_pAppCnt = (int)spinner_goodsdisper_count.getValue();
		if(checkBox_goodsdisper_over.isSelected()){
			e_pOnlyOne = "1";
		}else{
			e_pOnlyOne = "0";
		}
				
		if(e_pBarcode.length() <= 4 ){
			JOptionPane.showMessageDialog(this, "상품을 선택해 주세요!");
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			return true;
		}
		
		if(e_pDcPer.length() <= 0 ){
			JOptionPane.showMessageDialog(this, "할인 퍼센트를 입력해 주세요!");
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			return true;
		}
		
		String query = "Select e_gubun, e_Sdate, e_Edate From e_Coupon_List "
				+ "WHERE DEL_YN='0' AND e_Gubun in ('5','6') AND e_pBArcode = '"+e_pBarcode.trim()+"' "
				+ "AND ((e_Sdate Between  '"+s_date+"' AND '"+e_date+"') "
				+ "OR (e_Edate Between '"+s_date+"' AND '"+e_date+"')) "+update
				+ "Union all ";
		query += "Select e_gubun, e_Sdate, e_Edate From e_Coupon_List "
				+ "WHERE DEL_YN='0' AND e_Gubun in ('5','6') AND e_pBArcode = '"+e_pBarcode.trim()+"' "
				+ "And (e_Sdate <= '"+s_date+"'  and  e_Edate>='"+e_date+"') "+update;
		
		ms_connect.setMainSetting();
		ArrayList<HashMap<String, String>> maps = ms_connect.connection(query);
		
		if(maps.size() > 0){
			JOptionPane.showMessageDialog(this, "단품할인에 등록된 상품은 중복 등록 하실 수 없습니다!");
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			return true;
		}
		
		update_query = "e_pBarcode='"+e_pBarcode+"', e_pDcPer='"+e_pDcPer+"', e_pDcPerLimit='"+e_pDcPerLimit+"', e_pAppCnt="+e_pAppCnt+", e_pOnlyOne='"+e_pOnlyOne+"' ";		
		return false;
	}
		
	//쿠폰사용이력
	private void getCouponUseList(){
		
		String query = "SELECT * FROM ( Select x.idx, x.e_seq, x.e_gubun, x.e_Barcode, x.Seq, x.Sale_Num, "
						+ "x.Sale_Date, x.Sale_Time, x.cus_Code, x.e_id, x.regdate, x.Return_chk, x.befor_Jeonpyo, "
						+ "x.Del_YN, x.Del_Date1, x.Deleter1, x.e_DcPri, y.e_DcPer, y.e_DcPerLimit, x.e_DcPerPri, y.e_MinLimitPri, "
						+ "x.e_ToSalePri,  x.e_pBarcode, x.e_pDCPri, y.e_pDcPer, x.e_pDcPerPri, y.e_pDcPerLimit, x.e_TpDCqty, "
						+ "x.e_TpDCPri, x.e_Point, x.e_Product, x.e_pCnt, x.e_bBarcode, x.e_bCnt, y.e_CouponName, y.e_Sdate, y.e_EDate, "
						+ "y.Write_Date, y.Edit_Date, y.Del_Date, y.Writer, y.Editor, y.Deleter, "
						+ "isnull((select cus_name from customer_info a where x.cus_code=a.cus_code),'') cus_name, "
						+ "isnull((select g_name from goods a where x.e_bBarcode=a.barcode),'') g_name1, "
						+ "isnull((select g_name from goods a where x.e_pBarcode=a.barcode),'') g_name2, "
						+ "isnull((select count(*) use_cnt from e_Coupon_History a where x.e_seq=a.e_seq),0)  use_cnt  "
						+ "From e_Coupon_History x inner join e_Coupon_List y ON x.e_seq = y.e_seq ) X "
						+ "Where 1=1 "; 
		
		String searchContext = top_text_searchname.getText();		
		if( searchContext.length() > 0){
			searchContext = "and ( x.e_seq like '%"+searchContext+"%' or y.e_CouponName Like '%"+searchContext+"%' ) ";
		}
		
		String gubun = "";
		int select_gubun = top_combo_eventgubun.getSelectedIndex();		
		if(select_gubun != 0){
			gubun = "and x.e_gubun = '"+(select_gubun-1)+"' ";
		}
		
		String coupon_barcode = top_text_couponbarcode.getText();
		if( coupon_barcode.length() > 0 ){
			coupon_barcode = "and x_eBarcode='"+coupon_barcode+"' ";
		}		
		
		Date sdate = top_dateChooser_start.getDate();
		Date edate = top_dateChooser_end.getDate();		
		int tallDate = sdate.compareTo(edate);
		System.out.println(tallDate);
		if(tallDate > 0){
			JOptionPane.showMessageDialog(this, "시작 날자가 종료일 보다 큽니다. 날자를 다시 설정해 주세요");
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			return;
		}		
		String s_date = new SimpleDateFormat("yyyy-MM-dd").format(sdate);
		String e_date = new SimpleDateFormat("yyyy-MM-dd").format(edate);
		
		String date = "AND x.SALE_DATE between  '"+s_date+"'  AND  '"+e_date+"' ";		
		
		query += searchContext+gubun+coupon_barcode+date+" Order by idx DESC";		
		ms_connect.setMainSetting();
		ArrayList<HashMap<String, String>> maps = ms_connect.connection(query);
		
		if(maps.size() <= 0){
			JOptionPane.showMessageDialog(this, "검색된 결과가 없습니다.");			
			return;
		}
		
		dtm_uselist.setRowCount(0);
		
		for(int i = 0; i < maps.size();i++){
			
			HashMap<String, String> temp = maps.get(i);
			Vector<Object> v = new Vector<Object>();
			
			v.add(i+1);
			int e_gubun = Integer.parseInt(temp.get("e_gubun"));
			
			String e_seq = "<html>"+temp.get("e_seq")+"<br>"+temp.get("e_CouponName")+"<br>"+temp.get("e_Barcode")+"<br>"+coupon_listitem[e_gubun]+"</html>";
			v.add(e_seq);
			v.add(temp.get("Seq"));
			
			String q_date = "<html>"+temp.get("Sale_Num")+"<br>"+temp.get("Sale_Date")+"<br>"+temp.get("Sale_Time")+"</html>";
			v.add(q_date);
			
			String customer = "<html>"+temp.get("cus_Code")+"<br>"+temp.get("cus_name")+"</html>";
			v.add(customer);			
			
			switch(e_gubun){		
			case 0:
				v.add(temp.get("e_Point"));
				v.add("");
				v.add("");
				v.add("");
				v.add("");
				v.add("");
				v.add("");
				break;
			case 1:
				v.add("");
				String product = "<html>";
				if( null != temp.get("e_Product")) product += temp.get("e_Product")+"<br>";
				if( null != temp.get("e_bCnt")) product += temp.get("e_bCnt");
				product += "</html>";
				v.add(product);
				v.add("");
				v.add("");
				v.add("");
				v.add("");
				v.add("");
				break;
			case 2:
				v.add("");
				v.add("");
				String goodsprizes = "<html>";
				if( null != temp.get("e_bBarcode"))	goodsprizes += temp.get("e_bBarcode")+"<br>";
				if( null != temp.get("g_name1")) 	goodsprizes += temp.get("g_name1")+"<br>";
				if( null != temp.get("e_bCnt")) goodsprizes += temp.get("e_bCnt")+"(개)";
				goodsprizes += "</html>";			
				v.add(goodsprizes);
				v.add("");
				v.add("");
				v.add("");
				v.add("");
				break;
			case 3:
				v.add("");
				v.add("");
				v.add("");
				if( null != temp.get("e_DcPri")){ v.add(temp.get("e_DcPri")+"(원)");
				}else{ v.add("");	}
				v.add("");
				v.add("");
				v.add("");				
				break;	
			case 4:
				v.add("");
				v.add("");
				v.add("");
				v.add("");				
				String pricepercent = "<html>"+temp.get("e_DcPer")+"(%)<br>"+temp.get("e_DcPerLimit")+"(원)</html>";
				v.add(pricepercent);
				v.add("");
				v.add("");
				break;
			case 5:
				v.add("");
				v.add("");
				v.add("");
				v.add("");
				v.add("");
				String goodswon = "<html>";
				if( temp.get("e_pBarcode") != null) goodswon += temp.get("e_pBarcode")+"<br>";
				goodswon += temp.get("g_name2")+"<br>";
				if( temp.get("e_pDCPri") != null) goodswon += temp.get("e_pDCPri")+"(원)/개<br>";
				if( temp.get("e_TpDCPri") != null) goodswon += temp.get("e_TpDCPri")+"(원)/"+temp.get("e_TpDCqty")+"(개)";
				goodswon += "</html>";
				v.add(goodswon);
				v.add("");				
				break;
			case 6:
				v.add("");
				v.add("");
				v.add("");
				v.add("");
				v.add("");
				v.add("");
				String goodsper = "<html>";			
				if(temp.get("e_pBarcode") != null) goodsper += temp.get("e_pBarcode")+"<br>";
				goodsper += temp.get("g_name2")+"<br>";
				if( temp.get("e_pDcPer") != null) goodsper += temp.get("e_pDcPer")+"(%)/개<br>";			
				if( temp.get("e_pDcPerPri") != null)	goodsper += temp.get("e_pDcPerPri")+"(원)/개<br>";			
				if( temp.get("e_TpDCPri") != null) goodsper += temp.get("e_TpDCPri")+"(원)/"+temp.get("e_TpDCqty")+"개";
				goodsper += "</html>";
				v.add(goodsper);				
				break;			
			}			
			
			String regdate = "<html>"+temp.get("regdate").substring(0, 19)+"<br>"+temp.get("e_id")+"</html>";
			v.add(regdate);
			
			String returnChkeck = "<html>";
			if(temp.get("Return_chk").equals("0")){
				returnChkeck += "(X)<br>";
			}else{
				returnChkeck += "(O)<br>";
			}
			if(null != temp.get("befor_Jeonpyo")) returnChkeck += temp.get("befor_Jeonpyo");			
			returnChkeck +="</html>";			
			v.add(returnChkeck);
			
			String del_gubun = "<html>";
			if(temp.get("Del_YN").equals("0")){
				del_gubun += "(X)<br>";
			}else{
				del_gubun += "(O)<br>";
			}			
			if( temp.get("Del_Date1") != null) del_gubun += temp.get("Del_Date1");			
			del_gubun += "</html>";
			v.add(del_gubun);
			
			dtm_uselist.addRow(v);
		}
		
	}
			
	
	//저장아이템 초기화
	private void setCouponInsertItemReSet(){
		//구분합니다.		
		e_Point = "0";
		e_Product = "";
		
		e_pCnt = 0;
		
		//사은품 상품등록
		e_bBarcode = "";
		e_bCnt = 0;
		
		//전체할인(원)
		e_DcPri = "0";
		e_MinLimitPri = "0";		
		e_DcPri_Oyn = "0";		
		
		e_DcPer = "0";
		e_DcPerLimit = "0";
		e_DcPer_Oyn = "";	
		
		e_pBarcode = "";
		e_pDCPri = "0";
		e_pDcPer = "0";
		e_pDcPerLimit = "0";		
		e_pAppCnt = 0;
		e_pOnlyOne = "0";
		
		update_query = "";
	}
			
	//그룹버튼 불러가기
	private int getRadioButtonSelect(ButtonGroup btng){
		
		Enumeration<AbstractButton> enums = btng.getElements();
		int gibonCode = 0;
		while(enums.hasMoreElements()) {            // hasMoreElements() Enum에 더 꺼낼 개체가 있는지 체크한다. 없으며 false 반환
		    AbstractButton ab = enums.nextElement();    // 제네릭스가 AbstractButton 이니까 당연히 AbstractButton으로 받아야함
		    JRadioButton jb = (JRadioButton)ab;         // 형변환. 물론 윗줄과 이줄을 합쳐서 바로 형변환 해서 받아도 된다.
		 
		    if(jb.isSelected())                         // 받아낸 라디오버튼의 체크 상태를 확인한다. 체크되었을경우 true 반환.
		        gibonCode = Integer.parseInt(jb.getName().trim()); //getText() 메소드로 문자열 받아낸다.
		}
		
		return gibonCode;
	}
	
	//라디오 버튼 활성화 비활성화	
	private void setRadioButtonisEnabled(boolean gubun){
		
		if(gubun){
			east_radio_memberyn.setEnabled(true);
			east_radio_membery.setEnabled(true);
		}else{
			east_radio_memberyn.setEnabled(false);
			east_radio_membery.setEnabled(false);
		}		
	}
	
	
	//숫자 유효성 체크
	public boolean isNumber(String str){
		/*try {
	        Double.parseDouble(str);
	        return true;
	    } catch (NumberFormatException e) {
	        return false;
	    }	*/
		
		 boolean flag = true;
		    if (str == null || "".equals(str))
		    	return false;
		    int size = str.length();
		    int st_no= 0;
		    if ( str.charAt(0)  ==  45 )//음수인지 아닌지 판별 . 음수면 시작위치를 1부터
		    	st_no = 1;

		    for ( int i = st_no ; i < size ; ++i ){
		    	if( !(48 <= ((int)str.charAt(i)) && 57>= ((int)str.charAt(i)))){
		    		flag = false;
		    		break;
		    	}
		    }
		    return flag;	
		
		//return str.matches("^[0-9]*$");
	}
	   
    
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String command = e.getActionCommand().trim();
		
		switch(command){
		case "검색":			
			switch(center_tabbed.getSelectedIndex()){
			case 0:
				getTopSearchStart();
				break;
			case 1:
				getCouponUseList();
				break;
			}
			break;			
		case "지우기":
			setTopRenew();
			break;
		case "새로입력":
			setRenewCouponReg();
			break;
		case "이벤트불러오기":
			getEventListChoose();			
			break;
		case "쿠폰등록":
			setCouponSave();
			break;
		case "쿠폰수정":
			setCouponUpdate();
			break;
		}
	}
	
	public class CouponListTableCellRenderer extends JLabel implements TableCellRenderer {
		
    	/**
		 * 
		 */
		private static final long serialVersionUID = 842451231545L;

		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, final int row, int column) { 
   		
    		//setHorizontalAlignment(JLabel.RIGHT);    		
    		JLabel cellSpacingLabel= (JLabel) (this); 
    		
    		if (hasFocus) {
    			setBorder(UIManager.getBorder("Table.focusCellHighlightBorder")); 
    			cellSpacingLabel=null;
    		}else{
    			setBackground(table.getBackground());              
    			setBorder(null);
    		}
    		
    		if (isSelected) {
    			//setBackground(table.getSelectionBackground());
    			setBackground(table.getSelectionBackground());
    			setBorder(null);    			
    		}else{      		
    			setBackground(Color.PINK);
    			setBorder(null);
    			//if(row % 2 == 0){
        		//cell.setBackground(Color.pink);
        		//System.out.println("필드값 : "+value);        			 
        		//}else{
        		//cell.setBackground(Color.white);
        		//}
    		}
    		
    		if (cellSpacingLabel != null) {
    			cellSpacingLabel .setBorder(new CompoundBorder(new EmptyBorder(new Insets(0, 10, 0, 10)), cellSpacingLabel.getBorder()));
    		}
    		
    		this.setOpaque(true);
    		setText((String) value);
    		//System.out.println("("+row+","+column+") "+isSelected+" "+hasFocus); 
    		return this;
    	}
    }

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		char c = e.getKeyChar();
		  
		if (!Character.isDigit(c)) {
		   e.consume();
		   return;
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub		
		if(e.getKeyCode() == KeyEvent.VK_ENTER){						
			
		}		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}

