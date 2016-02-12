import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import groovy.ui.SystemOutputInterceptor;
import net.miginfocom.swing.MigLayout;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.fill.SimpleTextFormat;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.swing.JRViewer;
import net.sf.jasperreports.view.JasperViewer;


public class Order_Manage extends JPanel implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1567559994L;
	
	private JTextField text_top_info;
	private JTable table_orderList;
	private DefaultTableModel dtm_orderList;
	
	private Ms_Connect ms_conn;
	
	private JTable table_goodsList;	
	private DefaultTableModel dtm_goodsList;
	
	private JLabel label_detailtext_jday;
	private JLabel label_detailtext_jidx;
	private JLabel label_detailtext_id;
	private JLabel label_detailtext_jname;
	private JLabel label_detailtext_jtel;
	private JLabel label_detailtext_jhp;
	private JLabel label_detailtext_comment;
	private JLabel label_detailtext_wantday;
	private JLabel label_detailtext_add1;
	private JLabel label_detailtext_add2;
	private JLabel label_detailtext_sname;
	private JLabel label_detailtext_jaddress;
	private JLabel label_detailtext_stel;
	private JLabel label_detailtext_shp;
	private JLabel label_detailtext_tomsg;
	private JCheckBox chkbox_top_state;
	private JCheckBox chkbox_top_state_1;
	private JCheckBox chkbox_top_state_2;
	private JCheckBox chkbox_top_state_3;
	private JCheckBox chkbox_top_state_4;
	private JCheckBox chkbox_top_state_5;
	private JCheckBox chkbox_top_state_6;
	
	private JLabel label_detailtext_memcode;
	private JScrollPane scrollPane_orderList;
	private JScrollPane scrollPane_goodsList;
	private JScrollPane scrollPane_goods_detail;
	private JDateChooser daychooser_top_start;
	private JDateChooser daychooser_top_end;
	private JButton btn_detail_state1;
	private JButton btn_detail_state12;
	private JButton btn_detail_print;
		
	private Trans_ShopAPI trans_shopapi;
	
	public Order_Manage() {
		
		ms_conn = new Ms_Connect();
		ms_conn.setMainSetting();
		trans_shopapi = new Trans_ShopAPI();
		
		BorderLayout borderLayout = new BorderLayout();
		borderLayout.setVgap(5);
		borderLayout.setHgap(5);
		setLayout(borderLayout);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		JPanel panel_top_search = new JPanel();
		panel_top_search.setBorder(new LineBorder(new Color(0, 0, 0)));
		this.add(panel_top_search, BorderLayout.NORTH);
		panel_top_search.setLayout(new MigLayout("", "[][][][][][][][][][100px][]", "[][][]"));
		
		JLabel label_top_state = new JLabel("\uC9C4\uD589\uC0C1\uD0DC");
		panel_top_search.add(label_top_state, "cell 0 0");
		
		chkbox_top_state = new JCheckBox("\uC785\uAE08\uB300\uAE30");
		chkbox_top_state.setSelected(true);
		panel_top_search.add(chkbox_top_state, "cell 1 0");
		
		chkbox_top_state_1 = new JCheckBox("\uBC1C\uC1A1\uB300\uAE30");
		chkbox_top_state_1.setSelected(true);
		panel_top_search.add(chkbox_top_state_1, "cell 2 0");
		
		chkbox_top_state_2 = new JCheckBox("\uBC1C\uC1A1\uC644\uB8CC");
		chkbox_top_state_2.setSelected(true);
		panel_top_search.add(chkbox_top_state_2, "cell 3 0");
		
		chkbox_top_state_3 = new JCheckBox("\uD310\uB9E4\uC644\uB8CC");
		panel_top_search.add(chkbox_top_state_3, "cell 4 0");
		
		chkbox_top_state_4 = new JCheckBox("\uAC70\uB798\uCDE8\uC18C");
		panel_top_search.add(chkbox_top_state_4, "cell 5 0");
		
		chkbox_top_state_5 = new JCheckBox("\uBC18\uD488\uC2E0\uCCAD");
		panel_top_search.add(chkbox_top_state_5, "cell 6 0");
		
		chkbox_top_state_6 = new JCheckBox("\uBC18\uD488\uC2B9\uC778");
		panel_top_search.add(chkbox_top_state_6, "cell 7 0");
		
		JButton btn_top_search = new JButton("\uAC80\uC0C9");
		btn_top_search.addActionListener(this);
		panel_top_search.add(btn_top_search, "cell 9 0 1 3,grow");
		
		JLabel label_top_daybyday = new JLabel("\uC8FC\uBB38\uAE30\uAC04");
		panel_top_search.add(label_top_daybyday, "cell 0 1,alignx trailing");
				
		Calendar c = Calendar.getInstance();
		c.set(c.get(c.YEAR), c.get(c.MONTH), 1);
		
		//c.add(Calendar.YEAR, -10);
		daychooser_top_start = new JDateChooser(c.getTime());
		daychooser_top_start.setLocale(Locale.KOREA);		
		daychooser_top_start.setDateFormatString("yyyy-MM-dd");
		panel_top_search.add(daychooser_top_start, "cell 1 1 2 1,growx");
				
		JLabel lblNewLabel_1 = new JLabel("~");
		panel_top_search.add(lblNewLabel_1, "cell 3 1,alignx center");
		
		Calendar d = Calendar.getInstance();
		daychooser_top_end = new JDateChooser(d.getTime());
		daychooser_top_end.setLocale(Locale.KOREA);		
		daychooser_top_end.setDateFormatString("yyyy-MM-dd");
		panel_top_search.add(daychooser_top_end, "cell 4 1 2 1,growx");
		
		JLabel label_top_info = new JLabel("\uC8FC\uBB38\uC815\uBCF4");
		panel_top_search.add(label_top_info, "cell 0 2,alignx trailing");
		
		text_top_info = new JTextField();
		panel_top_search.add(text_top_info, "cell 1 2 3 1,growx");
		text_top_info.setColumns(10);
		
		JLabel label_top_infotitle = new JLabel("<- \uC804\uD654\uBC88\uD638/\uD578\uB4DC\uD3F0\uBC88\uD638/\uC774\uB984/ID \uC785\uB825\uAC80\uC0C9 \uAC00\uB2A5");
		panel_top_search.add(label_top_infotitle, "cell 4 2 4 1");
		
		JButton btn_top_renew = new JButton("\uCD08\uAE30\uD654");
		btn_top_renew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setSearchReSet();
			}
		});
		panel_top_search.add(btn_top_renew, "cell 10 0 1 3,growy");
		
		JPanel panel_bottom_change = new JPanel();
		panel_bottom_change.setBorder(new LineBorder(new Color(0, 0, 0)));
		this.add(panel_bottom_change, BorderLayout.SOUTH);
		panel_bottom_change.setLayout(new MigLayout("", "[100px][][][][][]", "[][]"));
		
		JLabel label_bottom_order = new JLabel("\uC8FC\uBB38\uC11C \uCC98\uB9AC");
		label_bottom_order.setHorizontalAlignment(SwingConstants.CENTER);
		label_bottom_order.setBackground(new Color(240, 255, 255));
		label_bottom_order.setOpaque(true);
		panel_bottom_change.add(label_bottom_order, "cell 0 0 1 2,grow");
		
		JLabel label_bottom_info1 = new JLabel("\uC120\uD0DD\uD55C \uC8FC\uBB38\uC11C\uB97C ");
		panel_bottom_change.add(label_bottom_info1, "cell 1 0");
		
		JButton btn_bottom_state1 = new JButton("\uC785\uAE08\uB300\uAE30->\uBC30\uC1A1\uB300\uAE30\uB85C \uBCC0\uACBD");
		btn_bottom_state1.addActionListener(this);
		panel_bottom_change.add(btn_bottom_state1, "cell 2 0");
		
		JButton btn_bottom_state2 = new JButton("\uBC30\uC1A1\uB300\uAE30->\uBC30\uC1A1\uC644\uB8CC\uB85C \uBCC0\uACBD");
		btn_bottom_state2.addActionListener(this);
		panel_bottom_change.add(btn_bottom_state2, "cell 3 0");
		
		JButton btn_bottom_state3 = new JButton("\uBC30\uC1A1\uC644\uB8CC->\uD310\uB9E4\uC644\uB8CC\uB85C \uBCC0\uACBD");
		btn_bottom_state3.addActionListener(this);
		panel_bottom_change.add(btn_bottom_state3, "cell 4 0");
		
		JButton btn_bottom_state_jump = new JButton("\uC785\uAE08\uB300\uAE30->\uBC30\uC1A1\uC644\uB8CC\uB85C \uBCC0\uACBD");
		btn_bottom_state_jump.setVisible(false);
		btn_bottom_state_jump.setForeground(Color.BLUE);
		btn_bottom_state_jump.setFont(new Font("���� ���", Font.BOLD, 12));
		btn_bottom_state_jump.addActionListener(this);
		panel_bottom_change.add(btn_bottom_state_jump, "cell 5 0");
		
		JLabel label_bottom_info2 = new JLabel("\uC120\uD0DD\uD55C \uC8FC\uBB38\uC11C\uB97C");		
		panel_bottom_change.add(label_bottom_info2, "cell 1 1");
		
		JButton btn_orderList_print = new JButton("\uC778\uC1C4\uD558\uAE30");
		btn_orderList_print.addActionListener(this);
		panel_bottom_change.add(btn_orderList_print, "cell 2 1");
		
		JButton btn_orderList_Allprint = new JButton("\uC804\uCCB4 \uC8FC\uBB38\uC11C \uC778\uC1C4\uD558\uAE30");
		btn_orderList_Allprint.addActionListener(this);
		panel_bottom_change.add(btn_orderList_Allprint, "cell 3 1,growx");
		
		JPanel panel_middle_list = new JPanel();
		this.add(panel_middle_list, BorderLayout.CENTER);
		panel_middle_list.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_middle_orderList = new JPanel();
		panel_middle_list.add(panel_middle_orderList, BorderLayout.CENTER);
		panel_middle_orderList.setLayout(new BorderLayout(0, 0));
		
		JSplitPane splitPane_middle_order = new JSplitPane();
		splitPane_middle_order.setResizeWeight(0.5);
		panel_middle_orderList.add(splitPane_middle_order, BorderLayout.CENTER);
		splitPane_middle_order.setOrientation(JSplitPane.VERTICAL_SPLIT);
		
		scrollPane_orderList = new JScrollPane();
		splitPane_middle_order.setLeftComponent(scrollPane_orderList);
		scrollPane_orderList.getVerticalScrollBar().setUnitIncrement(20);
		
		String[] colunm = {"����", "�ֹ���ȣ", "�ֹ����¹�ȣ", "�ֹ�����", "ȸ��ID", "�ֹ��ڸ�", "�޴�����ȣ", "�������", "�����ݾ�", "��ǰ��ü���", "��������", "�ֹ���"};
		
		dtm_orderList = new DefaultTableModel(null, colunm){
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
		
		table_orderList = new JTable(dtm_orderList);
		scrollPane_orderList.setViewportView(table_orderList);		
		
		DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
		celAlignCenter.setHorizontalAlignment(JLabel.CENTER);
    	
    	DefaultTableCellRenderer celAlignRight = new DefaultTableCellRenderer();
    	celAlignRight.setHorizontalAlignment(JLabel.RIGHT);
		
		((DefaultTableCellRenderer)table_orderList.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(0);
		
		table_orderList.setRowHeight(25);
		table_orderList.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);  //���� ��ũ��		
				
		table_orderList.getTableHeader().setReorderingAllowed(false);  //�̵��Ұ�		
		table_orderList.addMouseListener(new MouseListener() {
			
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
					setGoodsList();		
				}
				
				/*if (e.getClickCount() == 2) {
					System.out.println("���콺 �ι� Ŭ�� �ƽ��ϴ�.");
					//������ ������ �ִٸ� �ٽ� ���� ���ϴ�.
					if(change_Flags()) return;
					//��ǰ�� �������� �����ϴ�.
					setGoodsDetail();
				} // ����Ŭ��
				if (e.getButton() == 3) { 
					System.out.println("������ ���콺 Ŭ�� �ƽ��ϴ�.");
					
				} // ������ Ŭ��	 */
				}
		});
		
		
		
    	// {0."����", 1."�ֹ�����", 2."ȸ��ID", 3."�ֹ��ڸ�", 4."�޴�����ȣ", 5."�������", 6."�����ݾ�", 7."��ǰ��ü���", 8."��������", 9."�ֹ���"};
    	//�÷����� ����
    	table_orderList.getColumn("����").setPreferredWidth(40);
    	//�÷� ����   	
    	table_orderList.getColumn("����").setCellRenderer(celAlignCenter);
    	table_orderList.getColumn("�ֹ���ȣ").setCellRenderer(celAlignCenter);
    	
    	table_orderList.getColumn("�ֹ����¹�ȣ").setWidth(0);
    	table_orderList.getColumn("�ֹ����¹�ȣ").setMinWidth(0);
    	table_orderList.getColumn("�ֹ����¹�ȣ").setMaxWidth(0);
    	
    	table_orderList.getColumn("�ֹ�����").setCellRenderer(celAlignCenter);
    	table_orderList.getColumn("ȸ��ID").setCellRenderer(celAlignCenter);
    	table_orderList.getColumn("�ֹ��ڸ�").setCellRenderer(celAlignCenter);
    	table_orderList.getColumn("�޴�����ȣ").setCellRenderer(celAlignCenter);
    	table_orderList.getColumn("�������").setCellRenderer(celAlignCenter);
    	table_orderList.getColumn("�����ݾ�").setCellRenderer(celAlignCenter);
    	table_orderList.getColumn("��ǰ��ü���").setCellRenderer(celAlignCenter);
    	table_orderList.getColumn("��������").setCellRenderer(celAlignCenter);
    	table_orderList.getColumn("�ֹ���").setCellRenderer(celAlignCenter);
				
    	/*table_orderList.getColumn("�ֹ�����").setPreferredWidth(120);
    	table_orderList.getColumn("ȸ��ID").setPreferredWidth(120);
    	table_orderList.getColumn("�ֹ��ڸ�").setPreferredWidth(80);
    	table_orderList.getColumn("�޴�����ȣ").setPreferredWidth(100);
    	table_orderList.getColumn("�������").setPreferredWidth(80);
    	table_orderList.getColumn("�����ݾ�").setPreferredWidth(80);
    	table_orderList.getColumn("�ֹ���").setPreferredWidth(100);*/
    	
        	
    	JPanel panel_orderList_goods = new JPanel();
		splitPane_middle_order.setRightComponent(panel_orderList_goods);
		panel_orderList_goods.setLayout(new BorderLayout(5, 5));
		
		scrollPane_goodsList = new JScrollPane();
		panel_orderList_goods.add(scrollPane_goodsList, BorderLayout.CENTER);
		scrollPane_goodsList.getVerticalScrollBar().setUnitIncrement(40);
		
		String[] colunm_goods = { "����", "�ֹ���ȣ", "��ǰ�̹���", "��ǰ��", "���ڵ�", "����", "�ݾ�", "�հ�" };
		
		dtm_goodsList = new DefaultTableModel(null, colunm_goods){
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
		
		table_goodsList = new JTable(dtm_goodsList);
		table_goodsList.setRowSelectionAllowed(false);
		scrollPane_goodsList.setViewportView(table_goodsList);
		
		
		((DefaultTableCellRenderer)table_goodsList.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(0);
				
		table_goodsList.setRowHeight(60);
		table_goodsList.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);  //���� ��ũ��		
		
		scrollPane_goods_detail = new JScrollPane();
		scrollPane_goods_detail.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_goods_detail.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panel_orderList_goods.add(scrollPane_goods_detail, BorderLayout.EAST);
		scrollPane_goods_detail.getVerticalScrollBar().setUnitIncrement(20);
		
		
		JPanel panel_goods_detail = new JPanel();
		//panel_orderList_goods.add(panel, BorderLayout.NORTH);
		scrollPane_goods_detail.setViewportView(panel_goods_detail);
		panel_goods_detail.setLayout(new MigLayout("", "[][80px][80px]", "[][][][][][][][][][][][][][][][][][]"));
		
		btn_detail_print = new JButton("\uC778\uC1C4");
		btn_detail_print.setEnabled(false);
		panel_goods_detail.add(btn_detail_print, "cell 0 0");
		btn_detail_print.addActionListener(this);
		
		btn_detail_state1 = new JButton("\uC785\uAE08\uB300\uAE30->\uBC30\uC1A1\uB300\uAE30");
		btn_detail_state1.addActionListener(this);
		btn_detail_state1.setEnabled(false);
		panel_goods_detail.add(btn_detail_state1, "cell 1 0 2 1,growx");
		
		JLabel label_detail_title = new JLabel("\uC8FC\uBB38\uC815\uBCF4");
		label_detail_title.setBackground(new Color(240, 255, 255));
		label_detail_title.setOpaque(true);
		label_detail_title.setHorizontalAlignment(SwingConstants.CENTER);
		panel_goods_detail.add(label_detail_title, "cell 0 1 2 1,grow");
		
		btn_detail_state12 = new JButton("\uC8FC\uBB38\uCDE8\uC18C");
		btn_detail_state12.addActionListener(this);
		btn_detail_state12.setEnabled(false);
		panel_goods_detail.add(btn_detail_state12, "cell 2 1,growx");
		
		JLabel label_detail_orderday = new JLabel("\uC8FC\uBB38\uC77C\uC790");
		label_detail_orderday.setFont(new Font("���� ���", Font.BOLD, 12));
		panel_goods_detail.add(label_detail_orderday, "cell 0 2,alignx trailing");
		
		label_detailtext_jday = new JLabel("");
		panel_goods_detail.add(label_detailtext_jday, "cell 1 2 2 1");
		
		JLabel label_detail_orderidx = new JLabel("\uC8FC\uBB38\uBC88\uD638");
		label_detail_orderidx.setFont(new Font("���� ���", Font.BOLD, 12));
		panel_goods_detail.add(label_detail_orderidx, "cell 0 3,alignx trailing");
		
		label_detailtext_jidx = new JLabel("");
		panel_goods_detail.add(label_detailtext_jidx, "cell 1 3 2 1");
		
		JLabel label_detail_id = new JLabel("\uD68C\uC6D0ID");
		label_detail_id.setFont(new Font("���� ���", Font.BOLD, 12));
		panel_goods_detail.add(label_detail_id, "cell 0 4,alignx trailing");
		
		label_detailtext_id = new JLabel("");
		panel_goods_detail.add(label_detailtext_id, "cell 1 4 2 1");
		
		JLabel label_detail_memcode = new JLabel("\uB9E4\uC7A5\uD68C\uC6D0\uCF54\uB4DC");
		label_detail_memcode.setFont(new Font("���� ���", Font.BOLD, 12));
		panel_goods_detail.add(label_detail_memcode, "cell 0 5,alignx trailing");
		
		label_detailtext_memcode = new JLabel("");
		panel_goods_detail.add(label_detailtext_memcode, "cell 1 5 2 1");
		
		JLabel label_detail_jname = new JLabel("\uC8FC\uBB38\uC790");
		label_detail_jname.setFont(new Font("���� ���", Font.BOLD, 12));
		panel_goods_detail.add(label_detail_jname, "cell 0 6,alignx trailing");
		
		label_detailtext_jname = new JLabel("");
		panel_goods_detail.add(label_detailtext_jname, "cell 1 6 2 1");
		
		JLabel label_detail_jtel = new JLabel("\uC804\uD654\uBC88\uD638");
		label_detail_jtel.setFont(new Font("���� ���", Font.BOLD, 12));
		panel_goods_detail.add(label_detail_jtel, "cell 0 7,alignx trailing");
		
		label_detailtext_jtel = new JLabel("");
		panel_goods_detail.add(label_detailtext_jtel, "cell 1 7 2 1");
		
		JLabel label_detail_jhp = new JLabel("\uD734\uB300\uC804\uD654");
		label_detail_jhp.setFont(new Font("���� ���", Font.BOLD, 12));
		panel_goods_detail.add(label_detail_jhp, "cell 0 8,alignx trailing");
		
		label_detailtext_jhp = new JLabel("");
		panel_goods_detail.add(label_detailtext_jhp, "cell 1 8 2 1");
		
		JLabel label_detail_comment = new JLabel("\uC694\uAD6C\uC0AC\uD56D");
		label_detail_comment.setFont(new Font("���� ���", Font.BOLD, 12));
		panel_goods_detail.add(label_detail_comment, "cell 0 9,alignx trailing");
		
		label_detailtext_comment = new JLabel("");
		panel_goods_detail.add(label_detailtext_comment, "cell 1 9 2 1");
		
		JLabel label_detail_wantday = new JLabel("\uD76C\uB9DD\uBC30\uC1A1\uC77C");
		label_detail_wantday.setFont(new Font("���� ���", Font.BOLD, 12));
		panel_goods_detail.add(label_detail_wantday, "cell 0 10,alignx trailing");
		
		label_detailtext_wantday = new JLabel("");
		panel_goods_detail.add(label_detailtext_wantday, "cell 1 10 2 1");
		
		JLabel label_detail_add1 = new JLabel("\uB300\uCCB4\uC0C1\uD488\uC120\uD0DD");
		label_detail_add1.setFont(new Font("���� ���", Font.BOLD, 12));
		panel_goods_detail.add(label_detail_add1, "cell 0 11,alignx trailing");
		
		label_detailtext_add1 = new JLabel("");
		panel_goods_detail.add(label_detailtext_add1, "cell 1 11 2 1");
		
		JLabel label_detail_add2 = new JLabel("\uACB0\uC81C\uC720\uD615");
		label_detail_add2.setFont(new Font("���� ���", Font.BOLD, 12));
		panel_goods_detail.add(label_detail_add2, "cell 0 12,alignx trailing");
		
		label_detailtext_add2 = new JLabel("");
		panel_goods_detail.add(label_detailtext_add2, "cell 1 12 2 1");
		
		JLabel label_detail_sname = new JLabel("\uC218\uC2E0\uC790");
		label_detail_sname.setFont(new Font("���� ���", Font.BOLD, 12));
		panel_goods_detail.add(label_detail_sname, "cell 0 13,alignx trailing");
		
		label_detailtext_sname = new JLabel("");
		panel_goods_detail.add(label_detailtext_sname, "cell 1 13 2 1");
		
		JLabel label_detail_saddress = new JLabel("\uC8FC\uC18C");
		label_detail_saddress.setFont(new Font("���� ���", Font.BOLD, 12));
		panel_goods_detail.add(label_detail_saddress, "cell 0 14,alignx trailing");
		
		label_detailtext_jaddress = new JLabel("");
		panel_goods_detail.add(label_detailtext_jaddress, "cell 1 14 2 1");
		
		JLabel label_detail_stel = new JLabel("\uC804\uD654\uBC88\uD638");
		label_detail_stel.setFont(new Font("���� ���", Font.BOLD, 12));
		panel_goods_detail.add(label_detail_stel, "cell 0 15,alignx trailing");
		
		label_detailtext_stel = new JLabel("");
		panel_goods_detail.add(label_detailtext_stel, "cell 1 15 2 1");
		
		JLabel label_detail_shp = new JLabel("\uD734\uB300\uC804\uD654");
		label_detail_shp.setFont(new Font("���� ���", Font.BOLD, 12));
		panel_goods_detail.add(label_detail_shp, "cell 0 16,alignx trailing");
		
		label_detailtext_shp = new JLabel("");
		panel_goods_detail.add(label_detailtext_shp, "cell 1 16 2 1");
		
		JLabel label_detail_tomsg = new JLabel("\uC804\uB2EC\uAE00");
		label_detail_tomsg.setFont(new Font("���� ���", Font.BOLD, 12));
		panel_goods_detail.add(label_detail_tomsg, "cell 0 17,alignx trailing");
		
		label_detailtext_tomsg = new JLabel("");
		panel_goods_detail.add(label_detailtext_tomsg, "cell 1 17 2 1");
						
		table_goodsList.getTableHeader().setReorderingAllowed(false);  //�̵��Ұ�	
			
		//�÷����� ����
		table_goodsList.getColumn("����").setPreferredWidth(40);
		table_goodsList.getColumn("��ǰ�̹���").setPreferredWidth(60);
    	
    	//�÷� ����   	 { "����", "�ֹ���ȣ", "��ǰ�̹���", "��ǰ��", "���ڵ�", "����", "�ݾ�", "�հ�" };
		table_goodsList.getColumn("����").setCellRenderer(celAlignCenter);
		table_goodsList.getColumn("�ֹ���ȣ").setCellRenderer(celAlignCenter);
		table_goodsList.getColumn("��ǰ�̹���").setCellRenderer(celAlignCenter);
		table_goodsList.getColumn("��ǰ��").setCellRenderer(celAlignCenter);
		table_goodsList.getColumn("���ڵ�").setCellRenderer(celAlignCenter);
		table_goodsList.getColumn("����").setCellRenderer(celAlignCenter);
		table_goodsList.getColumn("�ݾ�").setCellRenderer(celAlignCenter);
		table_goodsList.getColumn("�հ�").setCellRenderer(celAlignCenter);
		
		startSearch();
	}

	//���� ��ǰ����Ʈ�� ǥ���մϴ�.
	public void setGoodsList(){
				
		dtm_goodsList.setRowCount(0);
		setDetailReNew();
		setReSet("Top");		
		
		String order_idx = (String)dtm_orderList.getValueAt(table_orderList.getSelectedRow(), 1);		
		String query = "Select *  From e_OrderGoods a Join " 
				+ " ( Select a.*, b.cus_code, b.connect_yn From e_OrderList a join e_Member b on a.mem_id=b.mem_id ) c "
				+ " on a.order_idx=c.order_idx Where a.order_idx='"+order_idx+"'; ";						
		
		ArrayList<HashMap<String, String>> orderList = ms_conn.connection(query);
		
		if(orderList.size() <= 0){
			JOptionPane.showMessageDialog(this, "�ֹ��� ��ǰ�� �����ϴ�. ������ �ֹ������� �ٽ� Ȯ���� �ּ���!!");
			return;
		}		
		
		HashMap<String, String> detail_map = orderList.get(0);
		
		//��ư ���� ǥ���ϱ�
		btn_detail_print.setEnabled(true);
		switch(detail_map.get("state")){
		case "0":
			btn_detail_state1.setText("�Աݴ��->��۴��");
			btn_detail_state1.setEnabled(true);			
			break;
		case "1":
			btn_detail_state1.setText("��۴��->��ۿϷ�");
			btn_detail_state1.setEnabled(true);
			btn_detail_state12.setEnabled(true);
			break;
		case "2":
			btn_detail_state1.setText("��ۿϷ�->�ǸſϷ�");
			btn_detail_state1.setEnabled(true);
			btn_detail_state12.setEnabled(true);		
			break;
		case "3":
			btn_detail_state1.setText("�ǸſϷ�");
			btn_detail_state12.setEnabled(true);			
			break;
		case "11":
			btn_detail_state1.setText("�����");
			
			break;
		case "12":
			btn_detail_state1.setText("�ŷ����");
			
			break;
		case "22":
			btn_detail_state1.setText("��ǰ��û");
			
			break;
		case "40":
			btn_detail_state1.setText("��ǰ�Ϸ�");
			
			break;			
		default:			
			btn_detail_state1.setEnabled(false);
			break;
		}
		
		label_detailtext_jday.setText(detail_map.get("reg_time")); //�ֹ��Ͻ�
		label_detailtext_jidx.setText(detail_map.get("order_idx"));  //�ֹ���ȣ
		label_detailtext_id.setText(detail_map.get("mem_id")); //�ֹ����̵�
		label_detailtext_memcode.setText(detail_map.get("cus_code")); //����ȸ���ڵ�
		label_detailtext_jname.setText(detail_map.get("j_name")); //�ֹ���
		label_detailtext_jtel.setText(detail_map.get("j_tel")); //�ֹ���ȭ��ȣ
		label_detailtext_jhp.setText(detail_map.get("j_hp")); //�ֹ��ڵ�����ȣ
		
		//�ʹ� ��� ���� ����� ���ϴ�. ������� ������ �ؾ� �մϴ�.		
		String want_comment = detail_map.get("want_comment");
		if( 0 < want_comment.length() && want_comment.length() > 21){
			label_detailtext_comment.setText(want_comment.substring(0, 20)); //���������޸޼���
		}else{
			label_detailtext_comment.setText(want_comment); //���������޸޼���
		}
		label_detailtext_comment.setToolTipText(want_comment);
		label_detailtext_wantday.setText(detail_map.get("want_day")); //��������
		label_detailtext_add1.setText(detail_map.get("add_column1")); //��ü��ǰ����
		label_detailtext_add2.setText(detail_map.get("add_column2")); //��������
		label_detailtext_sname.setText(detail_map.get("s_name")); //�������̸�
		label_detailtext_jaddress.setText(detail_map.get("reg_time")); //�������ּ�
		label_detailtext_stel.setText(detail_map.get("s_tel")); //��������ȭ��ȣ
		label_detailtext_shp.setText(detail_map.get("s_hp")); //�������޴���
		
		//�ʹ� ��� ���� ����� ���ϴ�. ������� ������ �ؾ� �մϴ�.
		String to_msg = detail_map.get("to_msg");
		if( 0 < to_msg.length() && to_msg.length() > 21){
			label_detailtext_tomsg.setText(to_msg.substring(0, 20)); //���������޸޼���
		}else{
			label_detailtext_tomsg.setText(to_msg); //���������޸޼���
		}
		label_detailtext_tomsg.setToolTipText(to_msg);
		
		int total_count = 0; //�� �ֹ� ����
		int price_total = 0; //�� ��ǰ �հ�
		int total_price = 0; //�� ���� �ݾ�
		
		//String[] colunm_goods = { "����", "�ֹ���ȣ", "��ǰ�̹���", "��ǰ��", "���ڵ�", "����", "�ݾ�", "�հ�" };
		for(int i = 0; i < orderList.size(); i++){
			HashMap<String, String> map = orderList.get(i);
			Vector<String> v = new Vector<String>();

			int sale_price = Integer.parseInt(map.get("sale_price"));
			int t_price = Integer.parseInt(map.get("t_price"));
			int count = 0;
			
			try{
				price_total += sale_price;
				total_price += t_price;
				count = t_price/sale_price;
				total_count += count;				
			}catch(NumberFormatException e){
				e.printStackTrace();
			}
			
			v.addElement(String.valueOf(i+1)); //����
			v.addElement(map.get("order_idx")); //�ֹ���ȣ
			String image_path = "<Html><img src='"+map.get("goods_img")+"' width='50' height='50' ></Html>";
			//v.addElement(map.get("goods_img")); //��ǰ�̹���
			v.addElement(image_path); //��ǰ�̹���
			v.addElement(map.get("goods_name")); //��ǰ��
			v.addElement(map.get("user_code")); //��ǰ���ڵ�
			v.addElement(String.valueOf(count)); //����
			v.addElement(String.format("%,d", sale_price)); //�ݾ�
			v.addElement(String.format("%,d", t_price)); //�հ�
			
			dtm_goodsList.addRow(v);
		}
		
		//�հ� ����� �մϴ�.
		Vector<Object> v = new Vector<Object>();
		
		v.addElement(""); //����
		v.addElement(""); //�ֹ���ȣ
		v.addElement(""); //��ǰ�̹���
		v.addElement(""); //��ǰ���ڵ�
		v.addElement("<Html><p style='color:red; font-weight:bold'>�հ�</p></Html>"); //��ǰ��		
		
		v.addElement("<Html><p style='color:red; font-weight:bold'>"+String.valueOf(total_count)+"</p></Html>"); //����
		v.addElement("<Html><p style='color:red; font-weight:bold'>"+String.format("%,d", price_total)+"</p></Html>"); //�ݾ�
		v.addElement("<Html><p style='color:red; font-weight:bold; font-face:���� ���'>"+String.format("%,d", total_price)+"</p></Html>"); //�հ�
		
		dtm_goodsList.addRow(v);
		
	}
	
	//�ֹ����� �˻��մϴ�.
	public void startSearch(){
				
		//����� �ʱ�ȭ �մϴ�.
		dtm_orderList.setRowCount(0);
		dtm_goodsList.setRowCount(0);
		setDetailReNew();
		setReSet("All");
		
		String state_use = ""; //�������
		if(chkbox_top_state.isSelected()){
			state_use += "or State='0' ";
		}
		
		if(chkbox_top_state_1.isSelected()){
			state_use += "or State='1' ";
		}
		
		if(chkbox_top_state_2.isSelected()){
			state_use += "or State='2' ";	
		}
		
		if(chkbox_top_state_3.isSelected()){ //�Ǹ�����
			state_use += "or State='3' ";
		}
		
		if(chkbox_top_state_4.isSelected()){
			state_use += "or State='11' ";
			state_use += "or State='12' ";
		}
		
		if(chkbox_top_state_5.isSelected()){
			state_use += "or State='22' ";
		}
		
		if(chkbox_top_state_6.isSelected()){
			state_use += "or State='40' ";
		}
				
		if(state_use.length() > 3 ){
			state_use = " ( " + state_use.substring(2, state_use.length())+" ) ";			
		}else{
			state_use = " 1=1 ";
		}
		
		/*
		$shopping_order_0 = 0; // �Աݴ��
		$shopping_order_1 = 0; // �����Ϸ�
		$shopping_order_2 = 0; // �߼ۿϷ�
		$shopping_order_3 = 0; // �Ǹ�����
		$shopping_order_11 = 0; // �����
		$shopping_order_12 = 0; // �ŷ����
		$shopping_order_22 = 0; // ��ǰ��û
		$shopping_order_40 = 0; // ��ǰ����
		*/		
		
		Date sdate = daychooser_top_start.getDate();
		Date edate = daychooser_top_end.getDate();	
		
		int tallDate = sdate.compareTo(edate);
		System.out.println(tallDate);
		if(tallDate > 0){
			JOptionPane.showMessageDialog(this, "���� ���ڰ� ������ ���� Ů�ϴ�. ���ڸ� �ٽ� ������ �ּ���");			
			return;
		}
		
		state_use += "And reg_time Between '"+new SimpleDateFormat("yyyy-MM-dd 00:00:00").format(sdate)+"' and '"+new SimpleDateFormat("yyyy-MM-dd 23:59:59").format(edate)+"' ";
		
		if(text_top_info.getText().length() > 0){
			String str = text_top_info.getText();
			state_use += "And ( mem_id like '%"+str+"%' or j_tel like '%"+str+"%' or j_hp like '%"+str+"%' or j_name like '%"+str+"%' ) ";
		}
		
		String query = "Select * From e_OrderList Where "+state_use;		
		ArrayList<HashMap<String, String>> map = ms_conn.connection(query);
		
		if(map == null){			
			JOptionPane.showMessageDialog(this, ms_conn.errMsg);
			return;
		}
		
		// {0."����", 1."�ֹ�����", 2."ȸ��ID", 3."�ֹ��ڸ�", 4."�޴�����ȣ", 5."�������", 6."�����ݾ�", 7."��ǰ��ü���", 8."��������", 9."�ֹ���"};
		if(map.size() > 0){
			for(int i =0; i < map.size(); i++){
				HashMap<String, String> temp = map.get(i);
				Vector<String> v = new Vector<String>();
				
				v.addElement(String.valueOf(i+1));
				v.addElement(temp.get("order_idx"));
				v.addElement(temp.get("state"));
				String state_1 = temp.get("state_subject");		
				switch(temp.get("state")){
				case "0": //�Աݴ��
					//���� ���������� ����
					state_1 = "<p style='color:#FF0000; font-weight:bold; font-face:���� ���'>"+temp.get("state_subject")+"</p>";
					break; 
				case "1": //�����Ϸ�(�߼۴��)
					state_1 = "<p style='color:#5882FA; font-weight:bold; font-face:���� ���'>"+temp.get("state_subject")+"</p>";
					break;
				case "2": //��ۿϷ�
					state_1 = "<p style='color:#CEE3F6; font-weight:bold; font-face:���� ���'>"+temp.get("state_subject")+"</p>";
					break;
				case "3": //�Ǹ�����(����Ȯ��)
					//state_1 = "<p style='color:red; font-weight:bold; font-face:���� ���'>"+temp.get("state_subject")+"</p>";
				break;
				case "11": //�����
					state_1 = "<p style='color:#FAAC58; font-weight:bold; font-face:���� ���'>"+temp.get("state_subject")+"</p>";
					break;
				case "12": //�ŷ����
					state_1 = "<p style='color:#FAAC58; font-weight:bold; font-face:���� ���'>"+temp.get("state_subject")+"</p>";
					break;
				case "22": //��ǰ��û
					state_1 = "<p style='color:#9F81F7; font-weight:bold; font-face:���� ���'>"+temp.get("state_subject")+"</p>";
					break;
				case "40": //��ǰ����
					state_1 = "<p style='color:#9F81F7; font-weight:bold; font-face:���� ���'>"+temp.get("state_subject")+"</p>";
					break;				
				}	
				
				String state_temp = "<Html>"+state_1+"</Html>";
				v.addElement(state_temp);
				v.addElement(temp.get("mem_id"));
				v.addElement(temp.get("j_name"));
				v.addElement(temp.get("j_hp"));
				v.addElement(temp.get("approval_type"));
				v.addElement(String.format("%,d", Integer.parseInt(temp.get("app_price"))));
				v.addElement(temp.get("add_column1"));
				v.addElement(temp.get("add_column2"));
				v.addElement(temp.get("reg_time"));				
				
				dtm_orderList.addRow(v);
			}
		}else{
			JOptionPane.showMessageDialog(this, "�˻��� ����� �����ϴ�.");
		}		
	}
		
	//���� �ֹ��� �Աݴ��->��۴��� ����
	private void setStateChange_0to1(){
				
		this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
		int row_count = table_orderList.getSelectedRowCount();
		if(row_count <= 0){
			JOptionPane.showMessageDialog(this, "������ �ֹ����� ������ �ּ���!");
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			return;
		}
		
		int[] row = table_orderList.getSelectedRows();
		
		String code_in = "";
		for(int row_num:row){
			code_in += "'"+dtm_orderList.getValueAt(row_num, 1)+"', ";			
		}
		
		String query = "Select * From e_orderList Where order_idx in ( "+code_in.substring(0, code_in.length()-2)+" ) And state='0' ";		
		ArrayList<HashMap<String, String>> orderList = ms_conn.connection(query);
				
		System.out.println(code_in);
		System.out.println(orderList.size());
		
		if( orderList.size() <= 0 ){
			JOptionPane.showMessageDialog(this, "�Աݴ������ �ֹ����� �����ϴ�. ");
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			return;
		}		
		
		int submit_total = 0;
		for(int i =0; i < orderList.size(); i++){
			HashMap<String, String> map = orderList.get(i);			
			if(setSubmitState0to1(map.get("order_idx"), map.get("mem_id"))){
				submit_total++;
			}
		}
		
		JOptionPane.showMessageDialog(this, "�� "+orderList.size()+"�� �� �ֹ��� �� "+submit_total+"�� ���� �Ϸ�");
		this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		startSearch();
	}
		
	//���õ� �ֹ��� �ѹ��� �����ϱ�
	private boolean setSubmitState0to1(String idx, String id){
		
		String order_list;
		String result;
		HashMap<String, Object> push_list = new HashMap<String, Object>();
		
		//���õ� �ֹ����� �ҷ��ɴϴ�.
		//����ɻ����ڵ�//�ֹ���ȣ//������ֹ����¸�
		order_list = "1;"+idx+";�߼۴��;";
		System.out.println("��۴��� ���� ����");
		result = trans_shopapi.order_Edit(order_list);
		
		if( result.equals("OK")){	
			try{
				push_list.put("Title", "�ֹ����� �Ϸ�");
				push_list.put("Message", "������ �ֹ��� ���������� ���� �Ǿ����ϴ�.");
				
				push_list.put("Link", "");
				push_list.put("Img_Url", "");
				push_list.put("Event", "");
				
				push_list.put("Mem_Id", id);
				push_list.put("Mem_Only", "");
				push_list.put("Hp", "");			
				trans_shopapi.setPushSubimt(push_list);
			}catch(NullPointerException e){
				e.getMessage();
			}		
		}else{			
			//JOptionPane.showMessageDialog(this, "��ۻ��¸� �������� ���߽��ϴ�.");
			return false;
		}
		
		return true;	
	}
	
	//��۴��->��ۿϷ�� ����
	private void setStateChange_1to2(){
		
		this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
		int row_count = table_orderList.getSelectedRowCount();
		if(row_count <= 0){
			JOptionPane.showMessageDialog(this, "������ �ֹ����� ������ �ּ���!");
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			return;
		}
		
		int[] row = table_orderList.getSelectedRows();
		
		String code_in = "";
		for(int row_num:row){
			code_in += "'"+dtm_orderList.getValueAt(row_num, 1)+"', ";			
		}
		
		String query = "Select * From e_orderList Where order_idx in ( "+code_in.substring(0, code_in.length()-2)+" ) And state='1' ";		
		ArrayList<HashMap<String, String>> orderList = ms_conn.connection(query);
				
		System.out.println(code_in);
		System.out.println(orderList.size());
		
		if( orderList.size() <= 0 ){
			JOptionPane.showMessageDialog(this, "��۴������ �ֹ����� �����ϴ�. ");
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			return;
		}		
		
		int submit_total = 0;
		for(int i =0; i < orderList.size(); i++){
			HashMap<String, String> map = orderList.get(i);			
			if(setSubmitState1to2(map.get("order_idx"), map.get("mem_id"))){
				submit_total++;
			}
		}
		
		JOptionPane.showMessageDialog(this, "�� "+orderList.size()+"�� �� �ֹ��� �� "+submit_total+"�� ���� �Ϸ�");
		this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		startSearch();
	}
		
	//���õ� �ֹ��� �ѹ��� �����ϱ�
	private boolean setSubmitState1to2(String idx, String id){
		
		String order_list;
		String result;
		HashMap<String, Object> push_list = new HashMap<String, Object>();
		
		//���õ� �ֹ����� �ҷ��ɴϴ�.
		//����ɻ����ڵ�//�ֹ���ȣ//������ֹ����¸�
		order_list = "2;"+idx+";�߼ۿϷ�;";
		System.out.println("�߼ۿϷ�� ���� ����");
		result = trans_shopapi.order_Edit(order_list);
		
		if( result.equals("OK")){	
			try{
				push_list.put("Title", "��� (�غ�)�Ϸ�");
				push_list.put("Message", "������ �ֹ� ���(�غ�)�Ϸ� �Ǿ����ϴ�..");
				
				push_list.put("Link", "");
				push_list.put("Img_Url", "");
				push_list.put("Event", "");
				
				push_list.put("Mem_Id", id);
				push_list.put("Mem_Only", "");
				push_list.put("Hp", "");			
				trans_shopapi.setPushSubimt(push_list);
			}catch(NullPointerException e){
				e.getMessage();
			}		
		}else{
			//JOptionPane.showMessageDialog(this, "��ۻ��¸� �������� ���߽��ϴ�.");
			return false;
		}
		
		return true;	
	}
		
	private void setStateChange_2to3(){
		
		this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
		int row_count = table_orderList.getSelectedRowCount();
		if(row_count <= 0){
			JOptionPane.showMessageDialog(this, "������ �ֹ����� ������ �ּ���!");
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			return;
		}
		
		int[] row = table_orderList.getSelectedRows();
		
		String code_in = "";
		for(int row_num:row){
			code_in += "'"+dtm_orderList.getValueAt(row_num, 1)+"', ";			
		}
		
		String query = "Select * From e_orderList Where order_idx in ( "+code_in.substring(0, code_in.length()-2)+" ) And state='2' ";		
		ArrayList<HashMap<String, String>> orderList = ms_conn.connection(query);
				
		System.out.println(code_in);
		System.out.println(orderList.size());
		
		if( orderList.size() <= 0 ){
			JOptionPane.showMessageDialog(this, "��ۿϷ� �ֹ����� �����ϴ�. ");
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			return;
		}		
		
		int submit_total = 0;
		for(int i =0; i < orderList.size(); i++){
			HashMap<String, String> map = orderList.get(i);			
			if(setSubmitState2to3(map.get("order_idx"), map.get("mem_id"))){
				submit_total++;
			}
		}
		
		JOptionPane.showMessageDialog(this, "�� "+orderList.size()+"�� �� �ֹ��� �� "+submit_total+"�� ���� �Ϸ�");
		this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		startSearch();
	}
		
	//���õ� �ֹ��� �ѹ��� �����ϱ�
	private boolean setSubmitState2to3(String idx, String id){
		
		String order_list;
		String result;
		HashMap<String, Object> push_list = new HashMap<String, Object>();
		
		//���õ� �ֹ����� �ҷ��ɴϴ�.
		//����ɻ����ڵ�//�ֹ���ȣ//������ֹ����¸�
		order_list = "3;"+idx+";�ǸſϷ�;";
		System.out.println("�ǸſϷ�� ���� ����");
		result = trans_shopapi.order_Edit(order_list);
		
		if( result.equals("OK")){	
			try{
				push_list.put("Title", "���� ����޼���");
				push_list.put("Message", "�׻� ���� ��Ʈ�� �̿��� �ּż� �����մϴ�. \r\n ���ſϷ� ���� ó�� �Ǿ����ϴ�.");
				
				push_list.put("Link", "");
				push_list.put("Img_Url", "");
				push_list.put("Event", "");
				
				push_list.put("Mem_Id", id);
				push_list.put("Mem_Only", "");
				push_list.put("Hp", "");			
				trans_shopapi.setPushSubimt(push_list);
			}catch(NullPointerException e){
				e.getMessage();
			}		
		}else{
			//JOptionPane.showMessageDialog(this, "��ۻ��¸� �������� ���߽��ϴ�.");
			return false;
		}		
		return true;	
	}
	
	
	/*private void setStateChange_0to3(){
		
		this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
		int row_count = table_orderList.getSelectedRowCount();
		if(row_count <= 0){
			JOptionPane.showMessageDialog(this, "������ �ֹ����� ������ �ּ���!");
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			return;
		}
		
		int[] row = table_orderList.getSelectedRows();
		
		String code_in = "";
		for(int row_num:row){
			code_in += "'"+dtm_orderList.getValueAt(row_num, 1)+"', ";			
		}
		
		String query = "Select * From e_orderList Where order_idx in ( "+code_in.substring(0, code_in.length()-2)+" ) And state='0' ";		
		ArrayList<HashMap<String, String>> orderList = ms_conn.connection(query);
				
		System.out.println(code_in);
		System.out.println(orderList.size());
		
		if( orderList.size() <= 0 ){
			JOptionPane.showMessageDialog(this, "������ �ֹ����� �����ϴ�. ");
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			return;
		}		
		
		int submit_total = 0;
		for(int i =0; i < orderList.size(); i++){
			HashMap<String, String> map = orderList.get(i);			
			if(setSubmitState0to3(map.get("order_idx"), map.get("mem_id"))){
				submit_total++;
			}
		}
		
		JOptionPane.showMessageDialog(this, "�� "+orderList.size()+"�� �� �ֹ��� �� "+submit_total+"�� ���� �Ϸ�");
		this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		startSearch();
	}
		
	//���õ� �ֹ��� �ѹ��� �����ϱ�
	private boolean setSubmitState0to3(String idx, String id){
		
		String order_list;
		String result;
		HashMap<String, Object> push_list = new HashMap<String, Object>();
		
		//���õ� �ֹ����� �ҷ��ɴϴ�.
		//����ɻ����ڵ�//�ֹ���ȣ//������ֹ����¸�
		order_list = "2;"+idx+";�߼ۿϷ�;";
		System.out.println("�߼ۿϷ�� ���� ����");
		result = trans_shopapi.order_Edit(order_list);
		
		if( result.equals("OK")){	
			try{
				push_list.put("Title", "���� ����޼���");
				push_list.put("Message", "�׻� ���� ��Ʈ�� �̿��� �ּż� �����մϴ�. \r\n ���ſϷ� ���� ó�� �Ǿ����ϴ�.");
				
				push_list.put("Link", "");
				push_list.put("Img_Url", "");
				push_list.put("Event", "");
				
				push_list.put("Mem_Id", id);
				push_list.put("Mem_Only", "");
				push_list.put("Hp", "");	
				trans_shopapi.setPushSubimt(push_list);
			}catch(NullPointerException e){
				e.getMessage();
			}		
		}else{
			//JOptionPane.showMessageDialog(this, "��ۻ��¸� �������� ���߽��ϴ�.");
			return false;
		}		
		return true;	
	}*/
	
	//�ֹ��� �μ� �ϱ�
	private void setOrderListPrint(String gubun){
		
		this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(gubun.equals("�μ��ϱ�")){
			
			//�������� ���õǾ��ٸ� �������� �˻��մϴ�.
			int row = table_orderList.getSelectedRowCount();
			
			if(row <= 0){
				JOptionPane.showMessageDialog(this, "�ֹ� ��Ͽ��� �μ��� �ֹ����� ������ �ּ���! ");
				this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				return;
			}
			
			String idx = "";
			int[] order_idx = table_orderList.getSelectedRows();
			
			for(int i:order_idx){
				String str = (String)dtm_orderList.getValueAt(i, 1);
				idx += "'"+str+"', ";
			}
			idx = idx.substring(0, idx.length()-2);
			System.out.println(idx);
			map.put("ORDERIDX_IN", idx.toString());
			
		}else if(gubun.equals("�μ�")){
			//�Ѱ��� �ִ´ٸ�		
			String idx = "'"+label_detailtext_jidx.getText()+"'";
			if( idx.length() <= 0){
				JOptionPane.showMessageDialog(this, "�ֹ���ȣ�� �����ϴ�.");
				this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				return;
			}
			System.out.println(idx);
			map.put("ORDERIDX_IN", idx.toString());
		}else{
			
			//�������� ���õǾ��ٸ� �������� �˻��մϴ�.
			int row = table_orderList.getRowCount();
			
			if(row <= 0){
				JOptionPane.showMessageDialog(this, " �ֹ����� �����ϴ�. ");
				this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				return;
			}
			
			String idx = "";
			for(int i=0; i < row; i++){
				String str = (String)dtm_orderList.getValueAt(i, 1);
				idx += "'"+str+"', ";
			}
			idx = idx.substring(0, idx.length()-2);
			System.out.println(idx);
			map.put("ORDERIDX_IN", idx.toString());			
		}	
				
		String reportFile = "./report/OrderList_report.jrxml";
		
		try {	
			//First, compile jrxml file.
	        JasperReport jasperReport = JasperCompileManager.compileReport(reportFile);
	        //JasperReport jasperSubReport =    JasperCompileManager.compileReport(subreportFile);	        							
	        //map.put("SUBREPORT_DIR", jasperSubReport);
			JasperPrint print = JasperFillManager.fillReport(jasperReport, map, ms_conn.getConnection());
			JasperViewer jrv = new JasperViewer(print, false);
			//jrv.viewReport(print, false);			
			jrv.setDefaultCloseOperation(JasperViewer.DISPOSE_ON_CLOSE);
			jrv.setVisible(true);			
			
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}
		
		this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}
		
	//��� �˻���ȸ ���ΰ�ħ
	private void setSearchReSet(){
		
		chkbox_top_state.setSelected(true);
		chkbox_top_state_1.setSelected(true);
		chkbox_top_state_2.setSelected(true);
		chkbox_top_state_3.setSelected(false);
		chkbox_top_state_4.setSelected(false);
		chkbox_top_state_5.setSelected(false);
		chkbox_top_state_6.setSelected(false);
		text_top_info.setText("");
		
	}
	
	//��ũ���� ������� �����մϴ�.
	private void setReSet(String gubun){
		
		
		JViewport jv1 = scrollPane_orderList.getViewport();
		
		if(gubun.equals("All")){			
		
			jv1.setViewPosition(new Point(0,0));
			
			jv1 = scrollPane_goodsList.getViewport();
			jv1.setViewPosition(new Point(0,0));
			
			jv1 = scrollPane_goods_detail.getViewport();
			jv1.setViewPosition(new Point(0,0));
			
		}else if(gubun.equals("Top")){
			
			jv1 = scrollPane_goodsList.getViewport();
			jv1.setViewPosition(new Point(0,0));
			
			jv1 = scrollPane_goods_detail.getViewport();
			jv1.setViewPosition(new Point(0,0));
			
		}else if(gubun.equals("Detail")){
			
			jv1 = scrollPane_goods_detail.getViewport();
			jv1.setViewPosition(new Point(0,0));
			
		}
	}
	
	//���� �� ���⸦ ���ΰ�ħ
	private void setDetailReNew(){
		
		btn_detail_print.setEnabled(false);
		btn_detail_state1.setEnabled(false);
		btn_detail_state12.setEnabled(false);
		
		label_detailtext_jday.setText("");
		label_detailtext_jidx.setText("");
		label_detailtext_id.setText("");
		label_detailtext_memcode.setText("");
		label_detailtext_jname.setText("");
		label_detailtext_jtel.setText("");
		label_detailtext_jhp.setText("");
		label_detailtext_comment.setText("");
		label_detailtext_wantday.setText("");
		label_detailtext_add1.setText("");
		label_detailtext_add2.setText("");
		label_detailtext_sname.setText("");
		label_detailtext_jaddress.setText("");
		label_detailtext_stel.setText("");
		label_detailtext_shp.setText("");
		label_detailtext_tomsg.setText("");
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String order_list;
		String result;
		HashMap<String, Object> push_list = new HashMap<String, Object>();
		
		switch(e.getActionCommand()){
		case "�˻�":
			startSearch();
			break;
		case "�Աݴ��->��۴��":
			//����ɻ����ڵ�//�ֹ���ȣ//������ֹ����¸�
			order_list = "1;"+label_detailtext_jidx.getText()+";�߼۴��;";
			System.out.println("��۴��� ���� ����");
			result = trans_shopapi.order_Edit(order_list);
			if( result.equals("OK")){
				btn_detail_state1.setText("��۴��->��ۿϷ�");
				
				if(label_detailtext_id.getText().length() > 0){
					push_list.put("Title", "�ֹ����� �Ϸ�");
					push_list.put("Message", "������ �ֹ��� ���������� ���� �Ǿ����ϴ�.");
					
					push_list.put("Link", "");
					push_list.put("Img_Url", "");
					push_list.put("Event", "");
					
					push_list.put("Mem_Id", label_detailtext_id.getText().trim());	
					push_list.put("Mem_Only", "");
					push_list.put("Hp", "");
					
					trans_shopapi.setPushSubimt(push_list);
				}
			}else{
				JOptionPane.showMessageDialog(this, "��ۻ��¸� �������� ���߽��ϴ�.");
			}
			break;
		case "��۴��->��ۿϷ�":
			//����ɻ����ڵ�//�ֹ���ȣ//������ֹ����¸�
			order_list = "2;"+label_detailtext_jidx.getText()+";�߼ۿϷ�;";
			System.out.println("��ۿϷ�� ���� ����");
			result = trans_shopapi.order_Edit(order_list);
			if( result.equals("OK")){
				btn_detail_state1.setText("��ۿϷ�->�ǸſϷ�");				

				if(label_detailtext_id.getText().length() > 0){
					push_list.put("Title", "��� (�غ�)�Ϸ�");
					push_list.put("Message", "������ �ֹ� ���(�غ�)�Ϸ� �Ǿ����ϴ�..");
					
					push_list.put("Link", "");
					push_list.put("Img_Url", "");
					push_list.put("Event", "");
					
					push_list.put("Mem_Id", label_detailtext_id.getText().trim());				
					push_list.put("Mem_Only", "");
					push_list.put("Hp", "");
					
					trans_shopapi.setPushSubimt(push_list);
				}
			}else{				
				JOptionPane.showMessageDialog(this, "��ۻ��¸� �������� ���߽��ϴ�.");
			}
			break;
		case "��ۿϷ�->�ǸſϷ�":
			//����ɻ����ڵ�//�ֹ���ȣ//������ֹ����¸�
			order_list = "3;"+label_detailtext_jidx.getText()+";�ǸſϷ�;";
			System.out.println("�ǸſϷ�� ���� ����");
			result = trans_shopapi.order_Edit(order_list);
			if( result.equals("OK")){
				btn_detail_state1.setText("�ǸſϷ�");
				btn_detail_state1.setEnabled(false);
				
				if(label_detailtext_id.getText().length() > 0){
					push_list.put("Title", "���� ����޼���");
					push_list.put("Message", "�׻� ���� ��Ʈ�� �̿��� �ּż� �����մϴ�. \r\n ���ſϷ� ���� ó�� �Ǿ����ϴ�.");
					
					push_list.put("Link", "");
					push_list.put("Img_Url", "");
					push_list.put("Event", "");
					
					push_list.put("Mem_Id", label_detailtext_id.getText().trim());				
					push_list.put("Mem_Only", "");
					push_list.put("Hp", "");
					
					trans_shopapi.setPushSubimt(push_list);
				}
			}else{				
				JOptionPane.showMessageDialog(this, "��ۻ��¸� �������� ���߽��ϴ�.");
			}
			break;
		case "�ֹ����":
			
			//����ɻ����ڵ�//�ֹ���ȣ//������ֹ����¸�			
			int a = JOptionPane.showConfirmDialog(this, "�ֹ����� �ֹ��� ��� �Ͻðڽ��ϱ�? \r\n �ֹ� ��� �� �����Ҽ� �����ϴ�.", "�ֹ����", JOptionPane.OK_OPTION);
			System.out.println(a);
			if(a != 0 ){
				return;
			}
			order_list = "12;"+label_detailtext_jidx.getText()+";�ŷ����;";
			System.out.println("�ŷ���ҷ� ���� ����");
			result = trans_shopapi.order_Edit(order_list);
			if( result.equals("OK")){
				if(label_detailtext_id.getText().length() > 0){
					push_list.put("Title", "��� �Ϸ�");
					push_list.put("Message", "������ �ֹ��� ��ҿϷ� �Ǿ����ϴ�.");
					
					push_list.put("Link", "");
					push_list.put("Img_Url", "");
					push_list.put("Event", "");
					
					push_list.put("Mem_Id", label_detailtext_id.getText().trim());				
					push_list.put("Mem_Only", "");
					push_list.put("Hp", "");
					
					trans_shopapi.setPushSubimt(push_list);
				}
				startSearch();
			}else{				
				JOptionPane.showMessageDialog(this, "��ۻ��¸� �������� ���߽��ϴ�.");
			}
			break;
		case "�Աݴ��->��۴��� ����":
			System.out.println("�Աݴ��->��۴��� ����");
			setStateChange_0to1();
			break;
		case "��۴��->��ۿϷ�� ����":
			setStateChange_1to2();
			System.out.println("��۴��->��ۿϷ�� ����");
			break;
		case "��ۿϷ�->�ǸſϷ�� ����":
			setStateChange_2to3();
			System.out.println("��ۿϷ�->�ǸſϷ�� ����");
			break;
		/*case "�Աݴ��->��ۿϷ�� ����":
			setStateChange_0to3();
			System.out.println("�Աݴ��->��ۿϷ�� ����");
			break;*/
		case "�μ��ϱ�":
			setOrderListPrint("�μ��ϱ�");
			System.out.println("�μ��ϱ�");
			break;
		case "�μ�":
			setOrderListPrint("�μ�");
			System.out.println("�μ�");
			break;
		case "��ü �ֹ��� �μ��ϱ�":
			setOrderListPrint("��ü �ֹ��� �μ��ϱ�");			
			System.out.println("��ü �ֹ��� �μ��ϱ�");
			break;
		}		
	}	
}
