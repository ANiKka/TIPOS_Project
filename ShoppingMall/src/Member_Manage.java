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
	 * @param ȸ������ ���α׷� 
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
	
	
	//ȸ���������α׷�
	public Member_Manage(){
				
		setLayout(new BorderLayout(0, 5));
		setBorder(new EmptyBorder(5, 5, 5, 5));
				
		//����
		start();
		
		//����
		init();
				
	}
	
	//������
	private void init(){
		
		panel_top_search = new JPanel();
		panel_top_search.setBorder(new LineBorder(new Color(0, 0, 0)));
		add(panel_top_search, BorderLayout.NORTH);
		panel_top_search.setLayout(new MigLayout("", "[100px][][][50px][100px][][][50px][][][][grow][]", "[30px]"));
		
		//��� ����â
		topinfomation();		
		//��� ���� �˻�
		topSearchInfomation();
		
		tab_member_list = new JTabbedPane(JTabbedPane.TOP);
		add(tab_member_list, BorderLayout.CENTER);
		
		//�߾� ����Ʈ ȭ��		
		tab_memberList();
				
		//�ۼ�ġȸ�� �˻� ����Ʈ
		tab_appin_list();
				
		//�¶��� ȸ�� �˻� ����Ʈ
		tab_online_member();
		
		//���� ������ ȭ��
		tabbed_detail();
		
		
		
	}
	
	//��� ��ȸ �κ� ������ȭ��
	private void topinfomation(){
		
		JLabel label_top_info = new JLabel("\uB9E4\uC7A5 \uCD1D\uD68C\uC6D0 :");
		label_top_info.setFont(new Font("���� ���", Font.BOLD, 15));
		panel_top_search.add(label_top_info, "cell 0 0,growx,aligny center");
		
		label_top_offmem_total = new JLabel("0");
		label_top_offmem_total.setFont(new Font("���� ���", Font.BOLD, 20));
		label_top_offmem_total.setBorder(new EmptyBorder(0, 15, 0, 10));
		label_top_offmem_total.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_top_search.add(label_top_offmem_total, "cell 1 0,growx");
		
		JLabel label_top_offend = new JLabel("\uBA85");
		panel_top_search.add(label_top_offend, "cell 2 0");
		
		JLabel label_top_appinfo = new JLabel("\uC571\uC124\uCE58 \uCD1D\uD68C\uC6D0 :");
		label_top_appinfo.setHorizontalAlignment(SwingConstants.TRAILING);
		label_top_appinfo.setFont(new Font("���� ���", Font.BOLD, 15));
		panel_top_search.add(label_top_appinfo, "cell 4 0,growx,aligny center");
		
		label_top_apptotal = new JLabel("0");
		label_top_apptotal.setHorizontalAlignment(SwingConstants.RIGHT);
		label_top_apptotal.setFont(new Font("���� ���", Font.BOLD, 20));
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
		label_top_shopinfo.setFont(new Font("���� ���", Font.BOLD, 15));
		panel_top_search.add(label_top_shopinfo, "cell 8 0,growx,aligny center");
		
		label_top_shoptotal = new JLabel("0");
		label_top_shoptotal.setHorizontalAlignment(SwingConstants.RIGHT);
		label_top_shoptotal.setFont(new Font("���� ���", Font.BOLD, 20));
		label_top_shoptotal.setBorder(new EmptyBorder(0, 15, 0, 10));
		panel_top_search.add(label_top_shoptotal, "cell 9 0,growx");
		
		JLabel label_top_onlineend = new JLabel("\uBA85");
		panel_top_search.add(label_top_onlineend, "cell 10 0");
		panel_top_search.add(btn_top_renew, "cell 12 0,growy");
		
	}
	
	//�߰� ��� �˻� ����Ʈ
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
		
		String[] columnNames = {"����", "ȸ����", "ȸ����ȣ", "����Ʈ", "��ȭ��ȣ", "�޴�����ȣ", "�¶��� ȸ��", "�ۼ�ġ ȸ��", "ȸ�� ID", "�¶����ֹ�", "�˸����ſ���", "�˸����Ź�ȣ"};
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
		//table_offmem_list.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);  //���� ��ũ��		
				
		table_offmem_list.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);  //���� ��ũ��
		table_offmem_list.getTableHeader().setReorderingAllowed(false);  //�̵��Ұ�
		
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
		
    	// {"����", "ȸ����", "ȸ����ȣ", "��ȭ��ȣ", "�޴�����ȣ", "�¶��� ȸ��", "�ۼ�ġ ȸ��", "ȸ�� ID", "�¶����ֹ�", "�˸����ſ���", "�˸����Ź�ȣ"};		
    	
		//�÷����� ����
		table_offmem_list.getColumn("����").setPreferredWidth(40);
    	
		//�÷� ����   	
		
		//�÷� ����
		for(String str:columnNames){
			table_offmem_list.getColumn(str).setCellRenderer(celAlignCenter);
		}
				
		/*table_offmem_list.getColumn("�ֹ����¹�ȣ").setWidth(0);
		table_offmem_list.getColumn("�ֹ����¹�ȣ").setMinWidth(0);
		table_offmem_list.getColumn("�ֹ����¹�ȣ").setMaxWidth(0);*/
    							
    	/*table_orderList.getColumn("�ֹ�����").setPreferredWidth(120);
    	table_orderList.getColumn("ȸ��ID").setPreferredWidth(120);
    	table_orderList.getColumn("�ֹ��ڸ�").setPreferredWidth(80);
    	table_orderList.getColumn("�޴�����ȣ").setPreferredWidth(100);
    	table_orderList.getColumn("�������").setPreferredWidth(80);
    	table_orderList.getColumn("�����ݾ�").setPreferredWidth(80);
    	table_orderList.getColumn("�ֹ���").setPreferredWidth(100);*/
		
	}
		
	//�ۼ�ġȸ�� ��ȸ ����Ʈ
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
				//�Է� ���ڸ� �ҷ��ɴϴ�.
				int page = Integer.parseInt(text_appin_page.getText());
				
				if(page <= 1){
					JOptionPane.showMessageDialog(Member_Manage.this, "ù ������ �Դϴ�.");
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
					JOptionPane.showMessageDialog(Member_Manage.this, "������ ������ �Դϴ�.");
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
		
		
		String[] column = {"����", "������ȣ", "ȸ��ID", "ȸ����޸�", "�÷���", "��ū", "��� ������", "�������", "�𵨸�", "�÷��� ����", "�ڵ��� ��ȣ", "APP��ġ �ð�"	};
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
		//table_offmem_list.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);  //���� ��ũ��		
				
		table_appin_list.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);  //���� ��ũ��
		table_appin_list.getTableHeader().setReorderingAllowed(false);  //�̵��Ұ�
		
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
		    			
		//{"����", "������ȣ", "ȸ��ID", "ȸ����޸�", "�÷���", "��ū", "��� ������", "�������", "�𵨸�", "�÷��� ����", "�ڵ��� ��ȣ", "APP��ġ �ð�"	};
		
		//�÷����� ����
		table_appin_list.getColumn("����").setPreferredWidth(40);
    	
		//�÷� ����
		for(String str:column){
			table_appin_list.getColumn(str).setCellRenderer(celAlignCenter);
		}
		
	}
	
	//�߰� �¶��� ȸ������Ʈ
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
					JOptionPane.showMessageDialog(Member_Manage.this, "ù ������ �Դϴ�.");
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
					JOptionPane.showMessageDialog(Member_Manage.this, "������ ������ �Դϴ�.");
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
		
		String[] column = {"����", "������ȣ", "ȸ��ID", "ȸ����", "����ڵ�", "ȸ����޸�", "�޴�����ȣ", "��ȭ��ȣ", "�г���", "�̸����ּ�", "�����ȣ", "�ּ�", "�������ּ�", "�̸Ӵ�", "����Ʈ", 
				"���忬����û", "����ȸ����ȣ", "���˻�����", "��������"};
		
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
		//table_offmem_list.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);  //���� ��ũ��		
				
		table_online_memberlist.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);  //���� ��ũ��
		table_online_memberlist.getTableHeader().setReorderingAllowed(false);  //�̵��Ұ�
		
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
		    			
		//{"����", "������ȣ", "ȸ��ID", "ȸ����", "����ڵ�", "ȸ����޸�", "�޴�����ȣ", "��ȭ��ȣ", "�г���", "�̸����ּ�", "�����ȣ", 
		//"�ּ�1", "�ּ�2", "�̸Ӵ�", "����Ʈ", "���忬����û", "����ȸ����ȣ", "���˻�����", "��������"};
		
		//�÷����� ����
		table_online_memberlist.getColumn("����").setPreferredWidth(40);
    	
		//�÷� ����
		for(String str:column){
			table_online_memberlist.getColumn(str).setCellRenderer(celAlignCenter);
		}
				
		table_online_memberlist.getColumn("�г���").setWidth(0);
		table_online_memberlist.getColumn("�г���").setMinWidth(0);
		table_online_memberlist.getColumn("�г���").setMaxWidth(0);
		
		
	}
	
	//����
	private void start(){		
		ms_connect = new Ms_Connect();
		t_api = new Trans_ShopAPI();
	}

	//��� �˻� �ϱ�
	private void topSearchStart(){
		
		this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
		
		//dtm_offmem.setRowCount(0);		
		refreshTable(dtm_offmem);
		setScrollReSet("Main");
		
		//�˻� ���� �ҷ�����
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
		
		//{"����", "ȸ����", "ȸ����ȣ", "��ȭ��ȣ", "�޴�����ȣ", "�¶��� ȸ��", "�ۼ�ġ ȸ��", "ȸ�� ID", "�¶����ֹ�", "�˸����ſ���", "�˸����Ź�ȣ"};
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
				v.addElement("�¶���ȸ��");
			}else{
				v.addElement("���Ծȵ�");
			}
			
			if(map.get("e_AppInstall_YN").equals("1")){
				v.addElement("�ۼ�ġȸ��");
			}else{
				v.addElement("��ġ�ȵ�");
			}
			
			v.addElement(map.get("e_Member_ID"));
			
			v.addElement(map.get("order_count"));
			
			if(map.get("e_PushSMS_YN").equals("1")){
				v.addElement("������");
			}else{
				v.addElement("���ž���");
			}			
			
			v.addElement(map.get("e_PhoneNumber"));
						
			dtm_offmem.addRow(v);
		}		
						
		this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}
		
	//�ۼ�ġȸ�� �˻�
	private void searchAppInstall(){
		
		this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
		
		//�ʱ�ȭ
		refreshTable(dtm_appin_list);
				
		String hp_num = text_appin_hpnum.getText();
		String page = text_appin_page.getText();
		
		JSONArray json = t_api.getDeviceList(hp_num, page);
				
		if( json.size() <= 0){
			JOptionPane.showMessageDialog(this, "�˻� ����� �����ϴ�.");
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
	
	//���� ������ ȭ��
	private void tabbed_detail(){
				
		 tab_east_detail = new JTabbedPane(JTabbedPane.TOP);
		tab_east_detail.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		add(tab_east_detail, BorderLayout.EAST);
		
		JPanel panel_detail_search = new JPanel();
		tab_east_detail.addTab("\uD68C\uC6D0\uAC80\uC0C9", null, panel_detail_search, null);
		panel_detail_search.setLayout(new MigLayout("", "[100px][150px][]", "[][][][][][grow]"));
		
		JLabel label_detail_search = new JLabel("\uD68C\uC6D0 \uC815\uBCF4 \uAC80\uC0C9(\uC628\uB77C\uC778 \uD68C\uC6D0/\uC571\uC124\uCE58\uD68C\uC6D0)");
		label_detail_search.setFont(new Font("���� ���", Font.BOLD, 13));
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
		
		String[] columnNames = {"����", "����", "ȸ����", "ȸ��ID", "�޴�����ȣ"};
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
		table_dtailmem_list.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);  //���� ��ũ��		
				
		table_dtailmem_list.getTableHeader().setReorderingAllowed(false);  //�̵��Ұ�
		
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
		
    	//  {"����", "����", "ȸ����", "ȸ��ID", "�޴�����ȣ"};
		
		//�÷����� ����
		table_dtailmem_list.getColumn("����").setPreferredWidth(25);
		table_dtailmem_list.getColumn("����").setPreferredWidth(35);
		table_dtailmem_list.getColumn("ȸ����").setPreferredWidth(40);
		table_dtailmem_list.getColumn("ȸ��ID").setPreferredWidth(50);
    	
		//�÷� ����   	
		table_dtailmem_list.getColumn("����").setCellRenderer(celAlignCenter);
		table_dtailmem_list.getColumn("����").setCellRenderer(celAlignCenter);
		table_dtailmem_list.getColumn("ȸ����").setCellRenderer(celAlignCenter);
		table_dtailmem_list.getColumn("ȸ��ID").setCellRenderer(celAlignCenter);
		table_dtailmem_list.getColumn("�޴�����ȣ").setCellRenderer(celAlignCenter);
		
		panel_detail_orderlist = new JPanel();
		tab_east_detail.addTab("\uC1FC\uD551\uBAB0 \uAD6C\uB9E4\uB0B4\uC5ED", null, panel_detail_orderlist, null);
		
		JPanel panel_detail_push = new JPanel();
		tab_east_detail.addTab("\uD478\uC26C\uC804\uC1A1", null, panel_detail_push, null);
		panel_detail_push.setLayout(new MigLayout("", "[][grow][]", "[30px][][150px][grow][30]"));
		
		JLabel label_push_info = new JLabel("\uBA54\uC138\uC9C0 \uC804\uC1A1");
		label_push_info.setFont(new Font("�������", Font.BOLD, 15));
		label_push_info.setHorizontalAlignment(SwingConstants.CENTER);
		panel_detail_push.add(label_push_info, "cell 0 0 3 1,growx");
		
		JLabel label_push_title = new JLabel("\uC81C\uBAA9");
		panel_detail_push.add(label_push_title, "cell 0 1,alignx trailing");
		
		text_push_title = new JTextField();
		text_push_title.setFont(new Font("���� ���", Font.PLAIN, 12));
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
		textArea_push_msg.setFont(new Font("���� ���", Font.PLAIN, 12));
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
		textArea_push_content.setFont(new Font("���� ���", Font.PLAIN, 12));
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
					JOptionPane.showMessageDialog(Member_Manage.this, "������ �Է��� �ּ���~!");
					return;
				}
				
				transferPushMessage(title, msg, content);
			}
		});
		panel_detail_push.add(btn_push_submit, "cell 2 4,growy");
		
										
		//���ų��� ����Ʈ
		setOrderList();		
				
		//sms ����
		setSMSTransfer();
		
		
	}
		
	//SMS�޼��� ���� �ϱ�
	private void setSMSTransfer(){
		
		JPanel panel_detail_sms = new JPanel();
		tab_east_detail.addTab("SMS \uC804\uC1A1", null, panel_detail_sms, null);
		panel_detail_sms.setLayout(new MigLayout("", "[]", "[]"));
		
		
		
	}
	
	
	//�޼��� �����ϱ�
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
				JOptionPane.showMessageDialog(this, "������ ȸ���� ������ �ּ���");
				return;
			}
			
			int count_msg = 0;
			for(int i=0; i < selectedrowcount; i++){	
				
				String gubun = (String)table_offmem_list.getValueAt(selectrows[i], 7);
				String hp_num = (String)table_offmem_list.getValueAt(selectrows[i], 11);				
				if("�ۼ�ġȸ��".equals(gubun) && !"".equals(hp_num) ){
					push_list.put("Hp", hp_num);
					JSONObject result = t_api.tranNewPushSubimt(push_list);
					String count = (String)result.get("result_code");
					if("OK".equals(count)){
						count_msg++;
					}
				}
			}			
			JOptionPane.showMessageDialog(this, "�߼� �Ϸ�\r\n �� "+selectedrowcount+"�� �߼��� "+count_msg+" �� �߼� �Ϸ�");
			
			break;
		case 1:
			if(table_appin_list.getSelectedRowCount()<= 0){
				JOptionPane.showMessageDialog(this, "������ ȸ���� ������ �ּ���");
				return;
			}
			break;
		case 2:
			if(table_online_memberlist.getSelectedRowCount()<= 0){
				JOptionPane.showMessageDialog(this, "������ ȸ���� ������ �ּ���");
				return;
			}
			break;
		}
		
		
		
		//t_api.tranNewPushSubimt(push_list);
		
		
		
		
	}
	
	//���� �� ȭ�鿡�� ȸ���� �˻� �մϴ�.
	private void searchMember(){
		
		//dtm_detailmem_list.setRowCount(0);
		refreshTable(dtm_detailmem_list);
		setScrollReSet("Detail");
		String str = text_detail_search.getText();		
		String query =  "";
				
		JSONArray json = new JSONArray();
		ArrayList<HashMap<String, String>> map = new ArrayList<HashMap<String, String>>();
		
		switch( tab_member_list.getSelectedIndex()){
		case 0: //�������� ȸ�� ����â�϶�			
			searchDetailOnlineMember(json, str);			
			break;
		case 1: //�¶��� ȸ�� ����â�϶�			
			searchDetailOfflineMember(json, str);
			break;
		case 2: //�ۼ�ġ ȸ�� ����â �϶�			
			searchDetailOfflineMember(json, str);
			break;
		}
				
		
	}
	
	//�¶��� ȸ������ �˻� �մϴ�.
	private void searchDetailOnlineMember(JSONArray json, String str){
		
		ArrayList<HashMap<String, String>> map = new ArrayList<HashMap<String, String>>();
		//hp, id, idx, page
		String hp = text_detail_hp.getText();
		String mem_id = text_detail_memid.getText();		
		
		json = t_api.getMemberManage(hp.trim(), mem_id.trim(), "", "");
		for(int i=0; i < json.size(); i++){
			
			JSONObject obj = (JSONObject)json.get(i);
			HashMap<String, String> temp = new HashMap<String, String>();
			temp.put("Cus_Gubun", "�¶���");
			temp.put("Cus_Name", (String)obj.get("name"));
			temp.put("Cus_Code", (String)obj.get("mem_id"));
			temp.put("Cus_Hp", (String)obj.get("hp"));
			
			map.add(temp);
		}
		
		//�ۼ�ġȸ���˻�
		//hp, page
		json = t_api.getDeviceList(hp.replace("-", ""), "");
		for(int i=0; i < json.size(); i++){
			JSONObject obj = (JSONObject)json.get(i);				
			HashMap<String, String> temp = new HashMap<String, String>();
			temp.put("Cus_Gubun", "�ۼ�ġ");
			temp.put("Cus_Name", (String)obj.get("idx"));
			temp.put("Cus_Code", (String)obj.get("mem_id"));
			temp.put("Cus_Hp", (String)obj.get("hp_num"));
			
			map.add(temp);
		}
		
		System.out.println(map.size());
		if( map == null || map.size() <= 0 ){
			JOptionPane.showMessageDialog(this, "�˻��� ����� �����ϴ�.");
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
		
	
	//�������� ȸ�� �˻� �ϱ�
	private void searchDetailOfflineMember(JSONArray json, String str){
		
		String query =  "";
		ArrayList<HashMap<String, String>> map;
		
		String hp = text_detail_hp.getText();		
		String mem_id = text_detail_memid.getText();
		String idx = text_detail_idx.getText();
		
		//�������ο��� �˻��մϴ�.
		//�˻���� ȸ���� ȸ��ID ��ȭ��ȣ�� �˻��� �����մϴ�.
		query += "Select '����' as Cus_Gubun, Cus_Name, Cus_Code, Cus_Mobile as Cus_Hp"
				+ " From customer_info Where Cus_Name Like '%"+str+"%' and replace(Cus_Mobile, '-', '') Like '%"+hp.replace("-", "")+"%' and Cus_Code Like '%"+str+"%' "
				+ " Union all "
			
		//�˻���� ȸ���� ȸ��ID ��ȭ��ȣ�� �˻��� �����մϴ�.
				+ "Select '�¶���' as Cus_Gubun, name as Cus_Name, mem_id as Cus_Code, hp as Cus_Hp"
				+ " From e_Member Where name Like '%"+str+"%' and replace(hp, '-', '') Like '%"+hp.replace("-", "")+"%' and mem_id Like '%"+mem_id+"%' "
				+ "and mem_idx like '%"+idx+"%' "
				+ " Union all "
					
		//�˻���� ȸ���� ȸ��ID ��ȭ��ȣ�� �˻��� �����մϴ�.
				+ "Select '�ۼ�ġ' as Cus_Gubun, mem_id as Cus_Name, convert(nvarchar, idx) as Cus_Code, hp_num as Cus_Hp "
				+ " From e_AppInstall Where mem_id Like '%"+mem_id+"%' and replace(hp_num, '-', '') Like '%"+hp.replace("-", "")+"%' and idx Like '%"+idx+"%' ";
				
		ms_connect.setMainSetting();
		map = ms_connect.connection(query);
		
		System.out.println(map.size());
		
		System.out.println(map.size());
		if( map == null || map.size() <= 0 ){
			JOptionPane.showMessageDialog(this, "�˻��� ����� �����ϴ�.");
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
	
	
	//�¶��� ȸ�� �˻��ϱ�
	private void searchOnlineMember(){
		
		this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
		
		//�ʱ�ȭ
		refreshTable(dtm_online_member);
				
		String mem_id = text_onlinetop_memid.getText();
		String hp = text_onlinetop_hp.getText();
		String mem_idx = text_onlinetop_idx.getText();
		String page = text_onlinetop_page.getText();
		
		JSONArray json = t_api.getMemberManage(hp, mem_id, mem_idx, page);
				

		if( json.size() <= 0){
			JOptionPane.showMessageDialog(this, "�˻� ����� �����ϴ�.");
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
	
	//���� ����ȸ�� ȸ������ �Է��ϱ�
	private void setLeftListChooseSave(){
		
		if(table_offmem_list.getSelectedRowCount() <= 0){
			JOptionPane.showMessageDialog(Member_Manage.this, "ȸ���� ������ �ּ���!!");
			return;
		}
		
		int result = JOptionPane.showConfirmDialog(null, "�����Ͻ� ������ ������ �����Ͻ� ȸ���� ���� �Ͻðڽ��ϱ�?", "ȸ������ �Է�", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
		System.out.println(result);
				
		int row = table_dtailmem_list.getSelectedRow();		
		// 1. ���õ� ������ �ҷ��ɴϴ�.
		
		//2. ���õ� ������ ������ ���� ��ü ������ �ҷ��ɴϴ�.
		
		//3. �ҷ��� ������ ������ ȸ�������� �Է��մϴ�.
		
		//4. ������ ������ �ٽ� ������ �ݴϴ�.
		
		
	}
	
	
	//�������� �������� �����ϱ� [�¶��� ȸ������ȸ��]
	private void setAppInstallTransferData(){
		
		this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
		
		int row_count = table_appin_list.getSelectedRowCount();
		int[] row_list = table_appin_list.getSelectedRows();
		int column_count = table_appin_list.getColumnCount();
		
		int same_count = 0;  //��ġ ����
		int incon_count = 0; //����ġ ����
		
		if( row_count <= 0){
			JOptionPane.showMessageDialog(this, "������ ����� ������ �ּ���!!");
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			return;
		}
		
		// 1. ���� ȸ�� ������ �ҷ��ɴϴ�. idx��������		
		String query = "Select idx From e_Appinstall ";
		
		ms_connect.setMainSetting();
		ArrayList<HashMap<String, String>> map = ms_connect.connection(query);
				
		// 2. ���̺��� ������ �ҷ��ɴϴ�.
		ArrayList<HashMap<String, Object>> temp = new ArrayList<HashMap<String, Object>>();
		for(int i= 0; i < row_count; i++ ){			
			int row = table_appin_list.convertRowIndexToModel(row_list[i]);				
				
			HashMap<String, Object> value = new HashMap<String, Object>();
			value.put("idx", table_appin_list.getValueAt(row, 1)); //������
			
			if(map.contains(value)){
				//���õ� ������ ī��Ʈ �մϴ�.
				
				same_count++;
			}else{								
				//����ġ ������ ī��Ʈ �մϴ�.
				value.clear();
				for(int j =0; j < column_count; j++){
					String columnname = dtm_appin_list.getColumnName(j);
					Object data = dtm_appin_list.getValueAt(row, j);
					value.put(columnname, data); //������
					//temp.add(value);
				}				
				temp.add(value);
				incon_count++;
			}
		}
				
		//�� ���ý� 0��ȯ �ƴϿ� 2 ���� -1
		
		if(row_count == same_count){
			JOptionPane.showMessageDialog(this, "���� ����Ǿ� �ֽ��ϴ�.");
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			return;
		}
		int result = JOptionPane.showConfirmDialog(this, "�� "+row_count+"�� �� "+same_count+"���� ���弭���� ����� �Ǿ��ְ� \r\n"+incon_count+"���� ���� ��� �ұ��?", "������ ����", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);		
		int complete_count = 0;
		if( result == 0){
			//�������� ��� �մϴ�. �������� ��� �ϸ鼭 ���忬����û ��ȭ�� ��� �մϴ�.
			for(int i = 0; i < temp.size(); i++){
				if(resultAppInstallSave(temp.get(i))){
					complete_count++;
				}
			}			
			
			JOptionPane.showMessageDialog(this, "�� "+incon_count+"�� �� "+complete_count+" �� ����Ǿ����ϴ�.");
		}
				
		System.out.println(result);		
		this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}
		
	//�������� �������� �����ϱ� [�¶��� ȸ������ȸ��]
	private void setShopTransferData(){
		
		this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
		
		int row_count = table_online_memberlist.getSelectedRowCount();
		int[] row_list = table_online_memberlist.getSelectedRows();
		int column_count = table_online_memberlist.getColumnCount();
		
		int same_count = 0;  //��ġ ����
		int incon_count = 0; //����ġ ����
		
		if( row_count <= 0){
			JOptionPane.showMessageDialog(this, "������ ����� ������ �ּ���!!");
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			return;
		}
		
		// 1. ���� ȸ�� ������ �ҷ��ɴϴ�. idx��������		
		String query = "Select mem_idx From e_member ";
		
		ms_connect.setMainSetting();
		ArrayList<HashMap<String, String>> map = ms_connect.connection(query);
				
		// 2. ���̺��� ������ �ҷ��ɴϴ�.
		ArrayList<HashMap<String, Object>> temp = new ArrayList<HashMap<String, Object>>();
		for(int i= 0; i < row_count; i++ ){			
			int row = table_online_memberlist.convertRowIndexToModel(row_list[i]);				
				
			HashMap<String, Object> value = new HashMap<String, Object>();
			value.put("mem_idx", dtm_online_member.getValueAt(row, 1)); //������
			
			if(map.contains(value)){				
				//���õ� ������ ī��Ʈ �մϴ�.
				
				same_count++;
			}else{								
				//����ġ ������ ī��Ʈ �մϴ�.
				value.clear();
				for(int j =0; j < column_count; j++){
					String columnname = dtm_online_member.getColumnName(j);
					Object data = dtm_online_member.getValueAt(row, j);
					value.put(columnname, data); //������
					//temp.add(value);
				}				
				temp.add(value);
				incon_count++;
			}
		}
				
		//�� ���ý� 0��ȯ �ƴϿ� 2 ���� -1
		
		if(row_count == same_count){
			JOptionPane.showMessageDialog(this, "���� ����Ǿ� �ֽ��ϴ�.");
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			return;
		}
		int result = JOptionPane.showConfirmDialog(this, "�� "+row_count+"�� �� "+same_count+"���� ���弭���� ����� �Ǿ��ְ� \r\n"+incon_count+"���� ���� ��� �ұ��?", "������ ����", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);		
		int complete_count = 0;
		if( result == 0){
			//�������� ��� �մϴ�. �������� ��� �ϸ鼭 ���忬����û ��ȭ�� ��� �մϴ�.
			for(int i = 0; i < temp.size(); i++){
				if(resultMemberSave(temp.get(i))){
					complete_count++;
				}
			}			
			
			JOptionPane.showMessageDialog(this, "�� "+incon_count+"�� �� "+complete_count+" �� ����Ǿ����ϴ�.");
		}
				
		System.out.println(result);		
		this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}
	
	//����� �����մϴ�.
    private boolean resultMemberSave(HashMap<String, Object> data){
    	
    	System.out.println(data.toString());
    	HashMap<String, Object> temp = data;
    	HashMap<String, Object> push_list = new HashMap<String, Object>();   //Ǫ�� ���� ����		    	
		
		
		String query = "Select * From e_Member Where mem_id='"+temp.get("ȸ��ID")+"' ";
		String query_result = "";
		String mem_id = temp.get("ȸ��ID").toString();
		String hp = (String)temp.get("�޴�����ȣ");
		
		String cus_code = "";
		String connect_add5 = "0"; 
		
			//����ȸ���� �˻� �ؼ� ��Ī�մϴ�.			
			switch(temp.get("���忬����û").toString().trim()){
			case "�űԹ߱޽�û":
				//�޴��� ��ȣ�� �߱��մϴ�. 
				query = "Select ISNull(max(cus_code), '"+hp.replace("-", "")+"') as cus_code, count(*) as count From Customer_Info Where Cus_Code='"+hp.replace("-", "")+"' ";			
				HashMap<String, String> map_newmem = ms_connect.selectQueryOne(query);
				System.out.println("�˻� ��� : "+map_newmem.get("cus_code")+" �� ī��Ʈ : "+map_newmem.get("count"));
				if(map_newmem.size() > 0 && map_newmem.get("count").equals("0")){
										
					String[] query_won = new String[2];
					query = "Insert Into Customer_Info (Cus_Code,Cus_Name,Cus_Gubun,Cus_Class,Cus_Tel,Cus_Mobile,Cus_BirYN,"
							+ "Cus_BirDay,Cus_RealDay,Pur_Pri,Cus_TPoint,Cus_Point,Cus_UsePoint,Dec_Pri,Vis_Count,Gift_Count,Edit_Check,"
							+ "Zip_Code,Address1,Address2,Bigo,Cus_Date,Vis_Date,Write_Date,Edit_Date,Writer,EDitor,HPSend_YN,"
							+ "Office_Name,Office_Num,Owner_Name,Uptae,JongMok,Address,Credit_YN,Cus_Use,Tax_Use,cPoint_Use,"
							+ "TaxBill_YN,Email,TAX_Print_Use,TAX_AUTO_USE,TAX_Gubun,TAX_Number, e_AppInstall_YN, e_Member_YN, e_Member_ID, e_PushSMS_YN, e_PhoneNumber)" 
							+ " Values('"+map_newmem.get("cus_code")+"', '"+temp.get("ȸ����")+"', '��ȸ��','1','"+temp.get("��ȭ��ȣ")+"','"+hp+"','1','','','0','0','0','0','0','0','0','1',"
							+ "'"+temp.get("�����ȣ")+"','"+temp.get("�ּ�1")+"','"+temp.get("�ּ�2")+"','',"
							+ "'"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"','','"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"','shop','shop','1','','','','','','','1','1','1','1','0','','0','0', '0', '', '', '1', '"+temp.get("mem_id")+"','1', '') ";
					query_won[0] = query;
					
					query_won[1] = "Insert Into Customer_History(Regdate,q_sql,gubun)"
										+"Values(convert(datetime,getdate(),120), '"+query.replace("'", "`")+"', '���θ�����')";	
					
					int result = ms_connect.connect_update(query_won);
					if(result > 0){
						return false;
					}
				}
				
			
				String result_data = "";
				try {
					result_data = "&mem_id="+mem_id+"&add2="+map_newmem.get("cus_code")+"&add4="+URLEncoder.encode("������", "UTF-8");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					break;
				}
				
				//�ű�ȸ�� ��� ������ ���θ��� �ø��ϴ�.
				t_api.setMemberUpdate(result_data);				
				
				//������� �ۼ�ġ ȸ�� ��� ����
				push_list.put("Title", "�ű�ȸ�� ����");
				push_list.put("Message", "�ű�ȸ�� ������ ���� �Ϸ� �Ǿ����ϴ�. ");
				push_list.put("Link", "");
				push_list.put("Img_Url", "");
				push_list.put("Event", "");
				
				push_list.put("Mem_Id", mem_id);
				push_list.put("Mem_Only", "");
				push_list.put("Hp", "");
				
				//�Ϸ� ������ ȸ������ Ǫ�� �����մϴ�.
				t_api.setPushSubimt(push_list);
				break;
				
			case "����ȸ������":	
				query = "Select * From Customer_Info Where replace(cus_mobile, '-', '')='"+hp.replace("-", "")+"' or replace(cus_tel, '-', '')='"+hp.replace("-", "")+"' Order by Edit_Date";
				ArrayList<HashMap<String, String>> map_cuscode = ms_connect.connection(query);		
				
				switch(map_cuscode.size()){
				case 0:  //��Ī�ȵ�			
					//������� �ۼ�ġ ȸ�� ��� ����
					push_list.put("Title", "����ȸ�� ������ ����");
					push_list.put("Message", "����ȸ�� ������ ���� ������ �����Ͽ� �������� ���߽��ϴ�. ����������>ȸ����������>���˻������� ��ȭ��ȣ �Ǵ� ������ �Է��� �ֽø� ����ó�� �ϰڽ��ϴ�.");
					push_list.put("Link", "");
					push_list.put("Img_Url", "");					
					push_list.put("Event", "");
					
					push_list.put("Mem_Id", mem_id);
					push_list.put("Mem_Only", "ALL");
					push_list.put("Hp",  "");	
					
					//�Ϸ� ������ ȸ������ Ǫ�� �����մϴ�.
					 t_api.setPushSubimt(push_list);
					break;
				case 1:
					cus_code = map_cuscode.get(0).get("Cus_Code");
					String query_mem = "Update Customer_Info set e_Member_YN='1', e_Member_ID='"+mem_id+"' Where Cus_Code='"+cus_code+"' ";						
					switch(ms_connect.connect_update(query_mem)){
					case 0:
						result_data = "";
						try {
							result_data = "&mem_id="+mem_id+"&add2="+cus_code+"&add4="+URLEncoder.encode("������", "UTF-8");
						} catch (UnsupportedEncodingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}				
						
						//�ű�ȸ�� ��� ������ ���θ��� �ø��ϴ�.
						t_api.setMemberUpdate(result_data);	
						
						//������� �ۼ�ġ ȸ�� ��� ����					
						push_list.put("Title", "����ȸ�� ������ ���� ���� �Ǿ����ϴ�. ");
						push_list.put("Message", "����ȸ�� ������ ���� ���� �Ǿ����ϴ�. ");
						push_list.put("Link", "");
						push_list.put("Img_Url", "");
						
						push_list.put("Event", "");
						
						push_list.put("Mem_Id", mem_id);
						push_list.put("Mem_Only", "ALL");
						push_list.put("Hp",  "");	
						
						//�Ϸ� ������ ȸ������ Ǫ�� �����մϴ�.
						t_api.setPushSubimt(push_list);
						System.out.println("����ȸ�� ���� �ɼ� : ������Ʈ ����");
						break;
					default:
						System.out.println("����ȸ�� ���� �ɼ� : ����");
						break;
					}
					break;	 
				default:
					//������� �ۼ�ġ ȸ�� ��� ����					
					push_list.put("Title", "����ȸ�� ������ �������� ");
					push_list.put("Message", "����ȸ�� ������ ���� ������ �����Ͽ� �������� ���߽��ϴ�. ����������>ȸ����������>���˻������� ��ȭ��ȣ �Ǵ� ������ �Է��� �ֽø� ����ó�� �ϰڽ��ϴ�.");
					push_list.put("Link", "");
					push_list.put("Img_Url", "");
					
					push_list.put("Event", "");
					
					push_list.put("Mem_Id", mem_id);
					push_list.put("Mem_Only", "ALL");
					push_list.put("Hp",  "");
					
					//�Ϸ� ������ ȸ������ Ǫ�� �����մϴ�.
					t_api.setPushSubimt(push_list);					
					break;
				}
				
				break; //����ȸ�� ����
				
			case "��������":	
				break; //��������
				
			default:
			break; //�׿� �͵�
		}
				
			query_result = "Insert into e_Member values("
					+"'"+temp.get("������ȣ")+"', '"+temp.get("ȸ��ID")+"', '', '"+temp.get("ȸ����")+"', '"+temp.get("����ڵ�")+"', '"+temp.get("�޴�����ȣ")+"',"
					+ " '"+temp.get("��ȭ��ȣ")+"', '', '"+temp.get("�г���")+"', '', '', '"+temp.get("�̸���")+"', '"+temp.get("�����ȣ")+"',"
					+ " '"+temp.get("�ּ�")+"', '"+temp.get("�������ּ�")+"', '', '', '', '', '',"
					+ " '', '"+temp.get("���忬����û")+"', '"+temp.get("����ȸ����ȣ")+"', '"+temp.get("���˻�����")+"', '"+temp.get("��������")+"', '', '',"
					+ " '', '', '', '', '"+cus_code+"', '"+connect_add5+"' )";			
			int result = ms_connect.connect_update(query_result);
			
			if(result == 0){
				return true;
			}else{
				return false;
			}		
			
    }
	
    
    //�ۼ�ġȸ�� ������ ���� �����ϱ�
    //����� �����մϴ�.
    private boolean resultAppInstallSave(HashMap<String, Object> data){
    	
    	HashMap<String, Object> json = data; 
		ArrayList<String> query_list = new ArrayList<String>();  //���� ����		
		String query_result =  "";
		String connect_add5 = "0";
		String cus_code = "";
		String phone_number = "";
		
		//���� �ۼ�ġ������ �ִ��� ���� Ȯ�� �մϴ�.
		String query = "Select * From e_AppInstall Where idx='"+json.get("������ȣ")+"' ";		
		HashMap<String, String> map = ms_connect.selectQueryOne(query);
		
		if(map.size() <= 0){ //�۽űԼ�ġȸ��
						
			ArrayList<HashMap<String, String>> map_cuscode = new ArrayList<HashMap<String, String>>();
			query = "Select * From Customer_Info Where replace(cus_mobile, '-', '')='"+json.get("�ڵ��� ��ȣ")+"' or replace(cus_tel, '-', '')='"+json.get("�ڵ��� ��ȣ")+"' Order by Edit_Date DESC";
			map_cuscode = ms_connect.connection(query);
			
			System.out.println(map_cuscode.size());
			switch(map_cuscode.size()){
			case 0:  //��Ī�ȵ�
				connect_add5 = "0"; //��Ī�ȵ�
				break;
			case 1:
				cus_code = map_cuscode.get(0).get("Cus_Code");
				phone_number = json.get("hp_num").toString();
				connect_add5 = "1"; //��Ī�Ϸ�

				query_result = "Update Customer_Info Set e_AppInstall_YN='1', e_PushSMS_YN='1', e_PhoneNumber='"+phone_number+"' Where Cus_Code='"+cus_code+"' ";
				query_list.add(query_result);
				break;	
			default:
				connect_add5 = "2";  //�ߺ��߻�
				break;
			}
			
			query_result = "Insert into e_AppInstall (idx, mem_id, platform, devicetoken, deviceuid, devicename, devicemodel, deviceversion, hp_num, reg_time, cus_code, connect_yn) values("
					+"'"+json.get("������ȣ")+"', '"+json.get("ȸ��ID")+"', '"+json.get("�÷���")+"', '"+json.get("��ū")+"', '"+json.get("��� ������")+"', '"+json.get("�������")
					+"', '"+json.get("�𵨸�")+"', '"+json.get("�÷��� ����")+"', '"+json.get("�ڵ��� ��ȣ")+"', '"+json.get("APP��ġ �ð�")+"', '"+cus_code+"', '"+connect_add5+"')";
			query_list.add(query_result);			
						
			int result = ms_connect.connect_update(query_list);
			if(result == 0){
				return true;
			}else{
				return false;
			}
		}else{ //������ġȸ��
			
			if(map.get("idx").equals(json.get("idx"))){
								
				query_result = "Update e_AppInstall Set mem_id='"+json.get("ȸ��ID")+"', platform='"+json.get("�÷���")+"', devicetoken='"+json.get("��ū")
						+"', deviceuid='"+json.get("��� ������")+"', devicename='"+json.get("�������")+"', devicemodel='"+json.get("�𵨸�")
						+"', deviceversion='"+json.get("�÷��� ����")+"', hp_num='"+json.get("�ڵ��� ��ȣ")+"', reg_time='"+json.get("APP��ġ �ð�")+"' "
						+"Where idx='"+json.get("������ȣ")+"' ";	
				
				int result = ms_connect.connect_update(query_result);				
				if(result == 0){				
					//���� ����
					return true;					
				}else{
					//���� ����
					return false;
				}
				
			}else{
				return false;
			}
		}		
    }
    
    
    //���ų��� ����Ʈ ������
    private void setOrderList(){
		
		String[] column = {"����", "���űݾ�", "�ŷ�����", "������"};
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
		//table_offmem_list.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);  //���� ��ũ��		
				
		table_detail_orderlist.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);  //���� ��ũ��
		table_detail_orderlist.getTableHeader().setReorderingAllowed(false);  //�̵��Ұ�
		
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
		
    	// {"����", "ȸ����", "ȸ����ȣ", "��ȭ��ȣ", "�޴�����ȣ", "�¶��� ȸ��", "�ۼ�ġ ȸ��", "ȸ�� ID", "�¶����ֹ�", "�˸����ſ���", "�˸����Ź�ȣ"};		
    	
		//�÷����� ����
		table_detail_orderlist.getColumn("����").setPreferredWidth(40);
		
		/*table_offmem_list.setAutoCreateRowSorter(true);
		TableRowSorter<TableModel> tsorter_main = new TableRowSorter<TableModel>(table_offmem_list.getModel());
		table_offmem_list.setRowSorter(tsorter_main);*/	
    	
		//�÷� ����   	
		
		//�÷� ����
		for(String str:column){
			table_detail_orderlist.getColumn(str).setCellRenderer(celAlignCenter);
		}
				
		/*table_offmem_list.getColumn("�ֹ����¹�ȣ").setWidth(0);
		table_offmem_list.getColumn("�ֹ����¹�ȣ").setMinWidth(0);
		table_offmem_list.getColumn("�ֹ����¹�ȣ").setMaxWidth(0);*/
    							
    	/*table_orderList.getColumn("�ֹ�����").setPreferredWidth(120);
    	table_orderList.getColumn("ȸ��ID").setPreferredWidth(120);
    	table_orderList.getColumn("�ֹ��ڸ�").setPreferredWidth(80);
    	table_orderList.getColumn("�޴�����ȣ").setPreferredWidth(100);
    	table_orderList.getColumn("�������").setPreferredWidth(80);
    	table_orderList.getColumn("�����ݾ�").setPreferredWidth(80);
    	table_orderList.getColumn("�ֹ���").setPreferredWidth(100);*/
		
    }
    
    //���� �������� ���� ������ ���� �ɴϴ�.
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
    		JOptionPane.showMessageDialog(this, "�ֹ� ������ �����ϴ�.");
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
	 ************************************** �ʱ�ȭ �� ��� �κ�  ************************************************* 
	 *
	 */
	
    //õ���� �޸� ���
    private String toNumFormat(int num) {
    	  DecimalFormat df = new DecimalFormat("#,###");    	  
    	  return df.format(num);
    }
    
    //��� ȸ���� ��ȸ ���
    private void topSearchInfomation(){
    	
	    String query = "Select Count(*) as Count From customer_info";
		
		ms_connect.setMainSetting();
		HashMap<String, String> map = ms_connect.selectQueryOne(query); 
		
		label_top_offmem_total.setText(map.get("Count"));    
		
		label_top_shoptotal.setText(t_api.getMemberTotal());
		label_top_apptotal.setText(t_api.getAppInstallTotal());
		
    }
    	
	//���� ȸ���˻� �ʵ� �ʱ�ȭ
	private void detailRenew(){
		
		text_detail_hp.setText("");
		text_detail_memid.setText("");
		text_detail_idx.setText("");
		text_detail_search.setText("");		
		
	}
    	
	//���̺� �ʱ�ȭ
	private void refreshTable(DefaultTableModel model) {

	   int rowCount= model.getRowCount();	  
	   for(int i=0;i<rowCount;i++ ){
	        model.removeRow(0);	    
	   }
	}
	
	//��ũ���� ������� �����մϴ�.
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
		case "�˻�":
			
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
			
		case "ȸ���˻�":			
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
