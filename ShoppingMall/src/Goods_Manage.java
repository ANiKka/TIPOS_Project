
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
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
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.sun.java.swing.plaf.windows.resources.windows;

import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;

import it.sauronsoftware.ftp4j.FTPAbortedException;
import it.sauronsoftware.ftp4j.FTPClient;
import it.sauronsoftware.ftp4j.FTPDataTransferException;
import it.sauronsoftware.ftp4j.FTPException;
import it.sauronsoftware.ftp4j.FTPFile;
import it.sauronsoftware.ftp4j.FTPIllegalReplyException;
import it.sauronsoftware.ftp4j.FTPListParseException;
import net.miginfocom.swing.MigLayout;
import java.awt.GridLayout;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.UIManager;
import java.awt.FlowLayout;
import javax.swing.JRadioButton;
import javax.swing.JProgressBar;
import javax.swing.border.SoftBevelBorder;
import javax.swing.ListSelectionModel;

public class Goods_Manage extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1542846856561L;
	
	private JPanel P = this;	
	private String GUBUN = "";
	
	//private JPanel contentPane_Goods;
	public JTextField tx_barcode;
	private JCheckBox chkeck_barcode_front;
	private JTextField tx_gname;
	private JTextField tx_officename;
	private JTextField tx_Lcode;
	private JTextField tx_Lname;
	private JTextField tx_Mcode;
	private JTextField tx_Mname;
	private JTextField tx_Scode;
	private JTextField tx_Sname;	
	private JComboBox<String> combo_Goods_Gubun;
	private JComboBox<String> combo_view_use;
	private JComboBox<String> combo_top_stockuse;
	private JCheckBox chkeck_top_anstock;
	private JComboBox<String> combo_image_get;
	private JLabel label_image_view;
	private JButton btn_top_salenum;
		
	
	private JLabel label_Detail_Number;
	private JTextField text_Detail_Barcode;
	private JTextField text_Detail_Name;
	private JTextField text_Detail_Category;
	private JTextField text_Detail_PurPri;
	private JTextField text_Detail_SellPri;
	private JTextField text_Detail_Stock;
	private JTextField text_Detail_AnStock;
	private JComboBox<String> combo_Detail_View;
	private JComboBox<String> combox_Detail_ImageConnectUse;
	
	private JToggleButton btnToggle_Detail_ConnectState;
	private JToggleButton btnToggle_Detail_SaleUse; 
	
	private JComboBox<String> combo_Detail_ShopConnectUse;
	private JTextField text_Detail_ImagePath;
	private JCheckBox checkBox_Detail_Stock;
	
	private JComboBox<String> cb_upload;
	
	private JComboBox<String> cb_shoppingmall_use;
	private JComboBox<String> cb_image_connect;
	private JComboBox<String> cb_Sale_Goods;
	
	private JButton bt_search; 
	private JTable table;
	private String query;
	private String query_goods = "";
	private String query_info = ""; 
	private String query_pro = "";
	
	private DefaultTableModel dtm;
		
	private Ms_Connect ms_connect;
	private JTextField tx_officecode;
	
	private JTextField text_imagename;
	
	private Trans_ShopAPI shop_api;
	private JSONArray main_code;
		
	//��� �˻� ���� ������� �ڵ� ��� ���
	private String top_maincode_list = "";
	JLabel label_top_maincode_chk;
	JLabel label_default_maincode;
	List<JCheckBox> chkboxs_top;
	List<JCheckBox> chkboxs_detail;
	
	//TODO: �����ڵ带 api�� �θ����� ���ٸ� �����մϴ�.
	private boolean maincode_callUse = false;	
	
	Vector<Object> temp_detail = new Vector<Object>();
	
	//�ϴ� �󼼺��� �̹�������
	private JTabbedPane tabPane_detail;
	private JTextField text_jtab_search;
	private JTextField text_jtab_path;
	private JCheckBox cb_gname_search; 
	private JPanel panel_jtap_image;
	private JCheckBox chkbox_listSearchNot;
	private JLabel label_count_up;
	private JLabel label_count_total;
	private JCheckBox chkbox_pcsearch_dir;	
	private ButtonGroup listsearch_bg;
	private JCheckBox chkbox_listsearch_gname;
	private JCheckBox chkbox_listsearch_code;	
	
	//�̹��� �� ����
	private int image_total_count=0;
	//���� ������ ��ȣ
	private int image_page_num=0;
	//�������� ���� 
	private int image_page_count=0;
	//���������� ��� ����
	private int image_page_listcount = 15;
	
	//�̹��� ���ε� ���� ����
	ArrayList<HashMap<String, String>> jtap_image_list = new ArrayList<HashMap<String, String>>();
	
	//�����Ȱ��� �ִ��� Ȯ���մϴ�.
	boolean edit_goods = false;
	boolean edit_goods_info = false;
	
	//TODO: ��ǰ��� �ϰ����� 
	private JTextField text_anstock;		
	private JLabel label_result;	
	private JButton btn_imagecall;
	private JButton btn_Change;
	
	private Thread thread;	
	public JProgressBar progressbar;	
	
	//���� �׷� ��ư
	private ButtonGroup[] bg_shoppingmall = {new ButtonGroup(), new ButtonGroup(), new ButtonGroup(), new ButtonGroup()};
	
	//üũ�ڽ� �׷�
	private List<JCheckBox> chk_boxs;
	private JPanel panel_maincode_title;
	
	//hotkey �׷�
	private JTextField text_jtab_hotkey;
	private JTable table_hotkey;
	private DefaultTableModel dtm_hotkey; 
	
	private JComboBox<String> cb_jtab_hotkey_map;
	private JComboBox<String> cb_jtab_hotkey_gubun;
	private DefaultComboBoxModel<String> dcbm_hotkey_group;
	private JLabel label_jtab_hotkey_result;
	private JButton btn_jtab_hotkey_listdown;
	private JButton btn_jtab_hotkey_listup;
	private JButton btn_jtab_hotkey_listcall;
	private JButton btn_jtab_hotkey_save;
	private JTextField text_realsto_up;
	
	/**
	 * Create the panel.
	 */
	public Goods_Manage() {
		
		ms_connect = new Ms_Connect();
		shop_api = new Trans_ShopAPI();
        			
    	setLayout(new BorderLayout(0, 0));
		setBorder(new EmptyBorder(5, 5, 5, 5));
				
		//��� �˻�����
		top_search();
		
		//�˻����
		goods_list();
		
		//��ǰ������ �� ���� â
		goods_detail();
		
	}

	/*
	 * [�˻� �׸� �ʱ�ȭ ]
	 * �˻� �׸� �ʱ�ȭ �ϱ�
	 * 
	 */	
	public void new_search(){
		
		tx_barcode.setText("");
		tx_gname.setText("");
		tx_officecode.setText("");
		tx_officename.setText("");
		tx_Lcode.setText("");
		tx_Lname.setText("");
		tx_Mcode.setText("");
		tx_Mname.setText("");
		tx_Scode.setText("");
		tx_Sname.setText("");
		cb_upload.setSelectedIndex(0);		
		cb_shoppingmall_use.setSelectedIndex(0);
		cb_image_connect.setSelectedIndex(0);
		cb_Sale_Goods.setSelectedIndex(0);
		combo_view_use.setSelectedIndex(0);
		combo_Goods_Gubun.setSelectedIndex(0);
		combo_top_stockuse.setSelectedIndex(0);
		combo_image_get.setSelectedIndex(0);
		btn_top_salenum.setName("");		
		btn_top_salenum.setText("�����ǥ��");
		
		//������� �ڵ� ���� ����Ʈ
		top_maincode_list= "";
		label_top_maincode_chk.setText("���� �ȵ�");
		
		chkeck_top_anstock.setSelected(false);		
		//dtm.setRowCount(0);
		tx_barcode.requestFocus();	
		//detail_Renew();
	}
	
	
	/*
	 * [��ǰ�����ư]
	 * ����� �κ� �о�ͼ� �����մϴ�.
	 */
	private void saveGoodsInfo(){
		
		this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
		
		//���� Goods �⺻ ����
		String query1 = "Update Goods Set ";
				
		try{
			int number = Integer.parseInt(label_Detail_Number.getText());
		}catch(NumberFormatException e){
			JOptionPane.showMessageDialog(this, "��ǰ�� ������ �ּ���!!");
			detail_Renew();
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			return;
		}
		
		//"0 ����", "1 ���ڵ�", "2 ��ǰ��", "3 �԰�", "4 ���԰�", "5 �ǸŰ�", "6 �����", "7 �������", 
		// "8 �з��ڵ�", "9 ���ڵ�", "10 ���", "11 ���ڵ�", "12 �߸�", "13 ���ڵ�", "14 �Ҹ�", "15 ���", "16 ��ǰ����",
		//"`17 ���θ�", "`18 ��������", "`19 �����", (20.�߰� ��������ڵ�) "21 �̹�������", "22 �̹������"  (23.�߰� �̹�����)};	
		
		//��ǰ���� �����Ǿ����� Ȯ���մϴ�.
		String barcode= text_Detail_Barcode.getText();
		String g_name = text_Detail_Name.getText();	
		String img_name = text_imagename.getText();
		System.out.println(g_name.length());
				
		//��ǰ���� �ٲ������ ��ǰ���� �����մϴ�.
		if(!g_name.equals(temp_detail.get(2))){
			query1 += "G_Name='"+g_name+"' Where Barcode='"+barcode+"'; ";
			edit_goods = true;
		}
				
		//Goods_Info �⺻����
		String query2 = "Update Goods_Info Set Edit_Tran='1'";
		
		//������� ���� ����
		String an_stock = text_Detail_AnStock.getText();
		System.out.println("������� : "+temp_detail.get(7).toString() + " ���尪 : "+an_stock);
		if(!temp_detail.get(7).equals(an_stock)){			
			query2 += ", pro_sto = '"+an_stock+"'";
			edit_goods_info = true;
		}
		
		//������� ���� ����Ȯ��  (���� Ʋ���ٸ� ����Ȱ�)
		String shop_use= temp_detail.get(16).toString();
		System.out.println("������� : "+temp_detail.get(16).toString() + " ���尪 : "+combo_Detail_ShopConnectUse.getSelectedItem());
		if(!combo_Detail_ShopConnectUse.getSelectedItem().equals(shop_use)){
			if(combo_Detail_ShopConnectUse.getSelectedIndex() == 0){
				query2 += ", ShoppingMall_use='1'";
			}else{
				query2 += ", ShoppingMall_use='0'";
			}			
			edit_goods_info = true;
		}
		
		//�������� ����Ȯ��  (���� ���ٸ� ����Ȱ�)
		String shop_view = temp_detail.get(18).toString();
		System.out.println("�������� : "+temp_detail.get(18).toString() + " ���尪 : "+combo_Detail_View.getSelectedIndex());
		if(combo_Detail_View.getSelectedIndex() == Integer.parseInt(shop_view)){			
			if(combo_Detail_View.getSelectedIndex() == 1){
				query2 += ", Shop_View='0'";
			}else{
				query2 += ", Shop_View='1'";
			}			
			edit_goods_info = true;
		}
		
		//��� ��� ���� Ȯ��
		String sto_use = "0";		
		if(checkBox_Detail_Stock.isSelected()){
			sto_use = "1";
		}		
		
		System.out.println("����뿩�� : "+sto_use + " ���尪 : "+temp_detail.get(19));
		//��� ��� ������ ���� �� �ϰ� Ʋ���� �� �մϴ�.  (���� Ʋ���ٸ� ����Ȱ�)
		if(!temp_detail.get(19).toString().equals(sto_use)){
			query2 += ", Sto_Use='"+sto_use+"' ";
			edit_goods_info = true;
		}
		
		//��������ڵ� ���������� Ȯ�� �մϴ�.
		String maincode = temp_detail.get(20).toString();
		String select_item = label_default_maincode.getText();
		System.out.println("��������ڵ� : "+maincode + " ���尪 : "+select_item);
		if(!select_item.equals(maincode)){
			if(select_item.equals("") || select_item.equals("none")){
				query2 += ", Shop_MainCode = 'none' ";
			}else{
				query2 += ", Shop_MainCode = '"+select_item+"' ";
			}
			edit_goods_info = true;
		}
		
		//�̹��� �������� ���� Ȯ�� (���� Ʋ���ٸ� ����Ȱ�)
		/*String img_path_use = temp_detail.get(21).toString();
		System.out.println("�̹������� : "+temp_detail.get(21).toString() + " ���尪 : "+combox_Detail_ImageConnectUse.getSelectedItem());
		if(!img_path_use.equals(combox_Detail_ImageConnectUse.getSelectedItem())){
			query2 += ", Img_path_use='"+String.valueOf(combox_Detail_ImageConnectUse.getSelectedIndex())+"' ";
			edit_goods_info = true;			
		}*/
		
		//�̹��� ��θ� ���� �ߴ��� Ȯ�� �մϴ�.
		String img_path = text_Detail_ImagePath.getText();
		//System.out.println("�̹������ : "+temp_detail.get(22).toString() + " ���尪 : "+img_path);
		if(!temp_detail.get(22).equals(img_path)){
			query2 += ", img_path='"+img_path+"' ";
			edit_goods_info = true;	
		}
		
		if(!img_name.equals(temp_detail.get(23))){
			query2 += ", Img_Name='"+img_name+"' ";
			edit_goods_info = true;
		}		
		
		//System.out.println(query2);				
		query2 += " where Barcode='"+barcode+"'; ";		
		
		int result = 0;
		
		if(edit_goods){			
			System.out.println(query1);
			ms_connect.setMainSetting();
			result = ms_connect.connect_update(query1);			
		}
		
		if(edit_goods_info){
			System.out.println(query2);
			ms_connect.setMainSetting();
			result = ms_connect.connect_update(query2);			
		}
		
		if(edit_goods || edit_goods_info){
			switch(result){
			case 0:
				if(Server_Config.getGOODS_TRANYN().equals("1")){
					new TransferDataGoodsSet(ms_connect, this, barcode);
				}
				detail_Renew();	
				JOptionPane.showMessageDialog(null, "������ �Ϸ� �Ǿ����ϴ�.");				
				//��ǰ�˻� ����
				search_goods(query);
				break;				
			default :
				JOptionPane.showMessageDialog(null, "������ �������� ���߽��ϴ�. \r\n ������ ������ �ּ���!!");
				break;
			}
			
		}else{
			JOptionPane.showMessageDialog(null, "����� ������ �����ϴ�.");
		}
		
		this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}
	
	
	//������ �ʱ�ȭ
	private void detail_Renew(){
		
		temp_detail.removeAllElements();
		
		label_Detail_Number.setText("����");
		text_Detail_Barcode.setText("");
		text_Detail_AnStock.setText("");
		text_Detail_Name.setText("");
		text_Detail_Category.setText("");
		text_Detail_PurPri.setText("");
		text_Detail_SellPri.setText("");
		text_Detail_Stock.setText("");
		
		checkBox_Detail_Stock.setSelected(false);
		
		btnToggle_Detail_SaleUse.setSelected(false);
		btnToggle_Detail_SaleUse.setText("�������");
		btnToggle_Detail_ConnectState.setSelected(false);
		btnToggle_Detail_ConnectState.setText("��������");
		
		combo_Detail_ShopConnectUse.setSelectedIndex(0);
		combo_Detail_View.setSelectedIndex(0);
		combox_Detail_ImageConnectUse.setSelectedIndex(0);
		
		label_default_maincode.setText("��¾���");
		text_imagename.setText("");
		
		label_image_view.setIcon(null);
		text_Detail_ImagePath.setText("");
		text_Detail_ImagePath.setEnabled(true);
		
		//�����Ȱ��� �ִ��� Ȯ���մϴ�.
		edit_goods = false;
		edit_goods_info = false;
	}
	    
	//�̹������� ����
	public void jtab_image_Renew(){
		
		//�̹������� ������
		//tabPane_detail.setSelectedIndex(0);		
		panel_jtap_image.removeAll();		
		text_jtab_search.setText("");	
		
		//��� ��� ��ư ����
		label_count_up.setText("0");
		label_count_total.setText("0");
		image_total_count = 1;
		image_page_num = 1;
		image_page_count = 1;
		
		panel_jtap_image.setLayout(new BorderLayout());
		JLabel label = new JLabel("�˻��� �̹����� �����ϴ�.", JLabel.CENTER);
		panel_jtap_image.add(label, BorderLayout.CENTER);
		this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		
		text_jtab_search.requestFocus();
		text_jtab_search.selectAll();
		this.repaint();
		
	}
		
	//�ϰ����� ����
	public void jtab_allChange_Renew(){
		
		
	}
	
	//��Ű ����
	public void jtab_hotkey_Renew(){
		
		text_jtab_hotkey.setText("");	
		cb_jtab_hotkey_map.setSelectedIndex(0);
		cb_jtab_hotkey_gubun.setSelectedIndex(0);
		label_jtab_hotkey_result.setText("0");
		
	}
	
    
    //��� �˻� ����
    private void top_search(){
    	    	
    	JPanel p_top = new JPanel();
		p_top.setBorder(new LineBorder(new Color(0, 0, 0)));
		this.add(p_top, BorderLayout.NORTH);
		p_top.setLayout(new MigLayout("", "[50px,grow][grow][grow]", "[grow][fill][24px,grow]"));
		
		JPanel panel = new JPanel();
		p_top.add(panel, "cell 0 0,grow");
		panel.setLayout(new MigLayout("ins 0", "[][][][][][][][][][][][][][][][][][]", "[]"));
		
		JLabel lb_barcode = new JLabel("\uBC14\uCF54\uB4DC");
		panel.add(lb_barcode, "flowx,cell 0 0");
		lb_barcode.setBackground(Color.GREEN);
		lb_barcode.setHorizontalAlignment(SwingConstants.CENTER);
		
		tx_barcode = new JTextField();
		panel.add(tx_barcode, "cell 3 0");
		tx_barcode.setColumns(14);
		tx_barcode.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				int keyCode = e.getKeyCode();
	            switch (keyCode) {
	            case KeyEvent.VK_ENTER:	            	
	            	if(change_Flags()) break;
	                search_start();
	                break;	            
	            }
			}
		});
		
		chkeck_barcode_front = new JCheckBox("");
		panel.add(chkeck_barcode_front, "cell 5 0");
		chkeck_barcode_front.setToolTipText("\uCCB4\uD06C \uC2DC \uC55E\uC790\uB9AC \uBD80\uD130 \uAC80\uC0C9 \uD569\uB2C8\uB2E4. \r\n\uC608) 80 \uC785\uB825 \uC2DC 80\uC73C\uB85C \uC2DC\uC791\uB418\uB294 \uBC14\uCF54\uB4DC \uAC80\uC0C9 \uD569\uB2C8\uB2E4.");
		
		JLabel lb_gname = new JLabel("\uC0C1\uD488\uBA85");
		panel.add(lb_gname, "cell 10 0");
		lb_gname.setHorizontalAlignment(SwingConstants.CENTER);
		
		tx_gname = new JTextField();
		panel.add(tx_gname, "cell 11 0");
		tx_gname.setColumns(15);
		
		JLabel lb_officecode = new JLabel("\uAC70\uB798\uCC98\uCF54\uB4DC");
		panel.add(lb_officecode, "cell 13 0");
		
		tx_officecode = new JTextField();
		panel.add(tx_officecode, "cell 14 0");
		tx_officecode.setColumns(5);
		
		JLabel lb_officename = new JLabel("\uAC70\uB798\uCC98\uBA85");
		panel.add(lb_officename, "cell 15 0");
		lb_officename.setHorizontalAlignment(SwingConstants.CENTER);
		
		tx_officename = new JTextField();
		panel.add(tx_officename, "cell 16 0");
		tx_officename.setColumns(15);
		
		JButton btn_office_search = new JButton("\uAC80\uC0C9");
		panel.add(btn_office_search, "cell 17 0");
		
		btn_office_search.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				/* [�ŷ�ó �˻�]	 */
				OfficeSearch osh = new OfficeSearch(tx_officecode, tx_officename);
				osh.setLocationRelativeTo(P);
				osh.setAlwaysOnTop(true);
			}
		});
		tx_gname.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				int keyCode = e.getKeyCode();
	            switch (keyCode) {
	            case KeyEvent.VK_ENTER:
	                search_start();
	                break;	
	            }
			}
		});
		
		JPanel panel_1 = new JPanel();
		p_top.add(panel_1, "cell 0 1,grow");
		panel_1.setLayout(new MigLayout("ins 0", "[36px][][][][][][][][]", "[21px]"));
		
		JLabel lb_class = new JLabel("\uBD84\uB958\uCF54\uB4DC");
		panel_1.add(lb_class, "cell 0 0,alignx left,aligny center");
		lb_class.setHorizontalAlignment(SwingConstants.CENTER);
		
		tx_Lcode = new JTextField();
		panel_1.add(tx_Lcode, "cell 1 0,alignx left,aligny top");
		tx_Lcode.setColumns(3);
		
		tx_Lname = new JTextField();
		panel_1.add(tx_Lname, "cell 2 0,alignx center,aligny top");
		tx_Lname.setColumns(10);
		
		tx_Mcode = new JTextField();
		panel_1.add(tx_Mcode, "cell 3 0");
		tx_Mcode.setColumns(3);
		
		tx_Mname = new JTextField();
		panel_1.add(tx_Mname, "cell 4 0");
		tx_Mname.setColumns(10);
		
		tx_Scode = new JTextField();
		panel_1.add(tx_Scode, "cell 5 0");
		tx_Scode.setColumns(3);
		
		tx_Sname = new JTextField();
		panel_1.add(tx_Sname, "cell 6 0");
		tx_Sname.setColumns(10);
		
		JButton btn_Class = new JButton("\uAC80\uC0C9");
		panel_1.add(btn_Class, "cell 8 0");
		btn_Class.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub				
				tx_Lcode.setText("");
				tx_Lname.setText("");
				tx_Mcode.setText("");
				tx_Mname.setText("");
				tx_Scode.setText("");
				tx_Sname.setText("");
				
				Object[] ot = { tx_Lcode, tx_Lname, tx_Mcode, tx_Mname, tx_Scode, tx_Sname};
				ClassSearch cs = new ClassSearch(ot);
				cs.setAlwaysOnTop(true);
				cs.setLocationRelativeTo(P);
			}		
		});
		
		JButton bt_renew = new JButton("\uC0C8\uB85C\uC785\uB825");
		p_top.add(bt_renew, "cell 1 0 1 2,alignx center,aligny center");
		
		bt_renew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//������ ������ �ִٸ� �ٽ� ���� ���ϴ�.
				if(change_Flags()) return;
				
				//���ΰ�ħ
				new_search();				
			}
		});
		
		JPanel panel_2 = new JPanel();
		p_top.add(panel_2, "cell 0 2,grow");
		panel_2.setLayout(new MigLayout("", "[][][][][][][][][][][][][][][][][][][grow][][grow]", "[][]"));
		
		JLabel lb_goodsconnect = new JLabel("\uC0C1\uD488\uC5F0\uB3D9");
		panel_2.add(lb_goodsconnect, "flowy,cell 0 0");
		lb_goodsconnect.setToolTipText("\uB9E4\uC7A5\uC0C1\uD488\uC744 \uC1FC\uD551\uBAB0\uACFC \uC5F0\uB3D9\uC5EC\uBD80\uB97C \uC120\uD0DD\uD569\uB2C8\uB2E4.");
		
		cb_shoppingmall_use = new JComboBox<String>();
		panel_2.add(cb_shoppingmall_use, "cell 1 0");
		cb_shoppingmall_use.setToolTipText("\uB9E4\uC7A5\uC0C1\uD488\uC744 \uC1FC\uD551\uBAB0\uACFC \uC5F0\uB3D9\uC5EC\uBD80\uB97C \uC120\uD0DD\uD569\uB2C8\uB2E4.");
		cb_shoppingmall_use.setModel(new DefaultComboBoxModel<String>(new String[] {"\uC804\uCCB4", "\uC5F0\uB3D9\uD568", "\uC5F0\uB3D9\uC548\uD568"}));
		
		JLabel lb_realsto_up = new JLabel("\uC7AC\uACE0");
		panel_2.add(lb_realsto_up, "cell 2 0,alignx trailing");
		
		text_realsto_up = new JTextField();
		panel_2.add(text_realsto_up, "cell 3 0,growx");
		text_realsto_up.setColumns(2);
		
		JLabel lb_shoppingmall = new JLabel(" \uC1FC\uD551\uBAB0 ");
		panel_2.add(lb_shoppingmall, "cell 5 0,alignx center");
		lb_shoppingmall.setToolTipText("\uB9E4\uC7A5 \uC0C1\uD488\uC774 \uC1FC\uD551\uBAB0\uACFC \uC5F0\uB3D9 \uC911\uC778\uC9C0 \uC5EC\uBD80\uB97C \uD655\uC778 \uD569\uB2C8\uB2E4.");
		lb_shoppingmall.setHorizontalAlignment(SwingConstants.CENTER);
		
		cb_upload = new JComboBox<String>();
		panel_2.add(cb_upload, "cell 6 0");
		cb_upload.setToolTipText("\uB9E4\uC7A5 \uC0C1\uD488\uC774 \uC1FC\uD551\uBAB0\uACFC \uC5F0\uB3D9 \uC911\uC778\uC9C0 \uC5EC\uBD80\uB97C \uD655\uC778 \uD569\uB2C8\uB2E4.");
		cb_upload.setModel(new DefaultComboBoxModel<String>(new String[] {"\uC804\uCCB4", "\uC5C5\uB85C\uB4DC\uB428", "\uC5C5\uB85C\uB4DC\uC548\uB428"}));
		
		JLabel lb_imageconnect = new JLabel("\uC774\uBBF8\uC9C0\uC5F0\uB3D9");
		panel_2.add(lb_imageconnect, "cell 8 0");
		lb_imageconnect.setToolTipText("\uACF5\uC6A9 \uD3F4\uB354\uC758 \uC774\uBBF8\uC9C0\uC640 \uC5F0\uB3D9\uC774 \uB418\uC5B4\uC788\uB294\uC9C0 \uD655\uC778 \uD569\uB2C8\uB2E4.");
		
		cb_image_connect = new JComboBox<String>();
		panel_2.add(cb_image_connect, "cell 9 0");
		cb_image_connect.setToolTipText("\uACF5\uC6A9 \uD3F4\uB354\uC758 \uC774\uBBF8\uC9C0\uC640 \uC5F0\uB3D9\uC774 \uB418\uC5B4\uC788\uB294\uC9C0 \uD655\uC778 \uD569\uB2C8\uB2E4.");
		cb_image_connect.setModel(new DefaultComboBoxModel<String>(new String[] {"\uC804\uCCB4", "\uACF5\uC6A9\uD3F4\uB354", "\uB2E8\uB3C5\uD3F4\uB354"}));
		
		JLabel lblNewLabel = new JLabel("\uC774\uBBF8\uC9C0\uC720\uBB34");
		panel_2.add(lblNewLabel, "cell 11 0");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		combo_image_get = new JComboBox<String>();
		panel_2.add(combo_image_get, "cell 12 0");
		combo_image_get.setModel(new DefaultComboBoxModel<String>(new String[] {"\uC804\uCCB4", "\uC774\uBBF8\uC9C0\uC5C6\uC2B4", "\uC774\uBBF8\uC9C0\uC788\uC2B4"}));
		combo_image_get.setMaximumRowCount(3);
		
		JLabel label_top_maincode = new JLabel("\uC0C1\uD488\uC9C4\uC5F4\uC704\uCE58");
		panel_2.add(label_top_maincode, "cell 14 0");
		
		JButton btn_top_maincode = new JButton("\uC120\uD0DD");
		btn_top_maincode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//maincode ��� �ڵ� ���� ȭ�� ����
				maincode_top_setList();
			}
		});
		panel_2.add(btn_top_maincode, "cell 15 0");
		
		label_top_maincode_chk = new JLabel("\uC120\uD0DD\uC548\uB428");
		label_top_maincode_chk.setFont(new Font("���� ���", Font.PLAIN, 10));
		panel_2.add(label_top_maincode_chk, "cell 17 0,alignx center");
		
		JLabel lb_goodssale = new JLabel("\uD589\uC0AC");
		panel_2.add(lb_goodssale, "cell 0 1,alignx center");
		lb_goodssale.setToolTipText("\uB9E4\uC7A5 \uC0C1\uD488\uC911 \uD589\uC0AC \uC9C4\uD589\uC0C1\uD488\uC744 \uD45C\uC2DC\uD569\uB2C8\uB2E4.");
		lb_goodssale.setHorizontalAlignment(SwingConstants.CENTER);
		
		cb_Sale_Goods = new JComboBox<String>();
		panel_2.add(cb_Sale_Goods, "cell 1 1");
		cb_Sale_Goods.setToolTipText("\uB9E4\uC7A5 \uC0C1\uD488\uC911 \uD589\uC0AC \uC9C4\uD589\uC0C1\uD488\uC744 \uD45C\uC2DC\uD569\uB2C8\uB2E4.");
		cb_Sale_Goods.setModel(new DefaultComboBoxModel<String>(new String[] {"\uC804\uCCB4", "\uC9C4\uD589\uC911", "\uC9C4\uD589\uC548\uD568"}));
		
		btn_top_salenum = new JButton("\uD589\uC0AC\uC804\uD45C\uBCC4");
		btn_top_salenum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getEventCodeCall();
			}
		});
		panel_2.add(btn_top_salenum, "cell 2 1 2 1");
		
		JLabel label_Goods_Gubun = new JLabel("\uC0C1\uD488\uAD6C\uBD84");
		panel_2.add(label_Goods_Gubun, "cell 5 1");
		label_Goods_Gubun.setToolTipText("\uACF5\uC0B0\uD488/\uC800\uC6B8\uC0C1\uD488/\uBD80\uBD84\uC0C1\uD488\uC744 \uAD6C\uBD84\uD558\uB294 \uC870\uD68C \uC635\uC158\uC785\uB2C8\uB2E4.");
		label_Goods_Gubun.setHorizontalAlignment(SwingConstants.CENTER);
		
		combo_Goods_Gubun = new JComboBox<String>();
		panel_2.add(combo_Goods_Gubun, "cell 6 1,growx");
		combo_Goods_Gubun.setToolTipText("\uACF5\uC0B0\uD488/\uC800\uC6B8\uC0C1\uD488/\uBD80\uBD84\uC0C1\uD488\uC744 \uAD6C\uBD84\uD569\uB2C8\uB2E4.");
		combo_Goods_Gubun.setModel(new DefaultComboBoxModel<String>(new String[] {"\uC804\uCCB4", "\uACF5\uC0B0\uD488", "\uC800\uC6B8\uC0C1\uD488", "\uBD80\uBD84\uC0C1\uD488"}));
		combo_Goods_Gubun.setMaximumRowCount(4);
		
		JLabel label_ = new JLabel("\uC9C4\uC5F4\uC5EC\uBD80");
		panel_2.add(label_, "cell 8 1,alignx center");
		label_.setToolTipText("\uC1FC\uD551\uBAB0\uC5D0 \uC0C1\uD488\uC744 \uBCF4\uC774\uAC8C \uD560\uC9C0\uB97C \uC120\uD0DD\uD558\uB294 \uC635\uC165\uC785\uB2C8\uB2E4.");
		label_.setHorizontalAlignment(SwingConstants.CENTER);
		
		combo_view_use = new JComboBox<String>();
		panel_2.add(combo_view_use, "cell 9 1");
		combo_view_use.setModel(new DefaultComboBoxModel<String>(new String[] {"\uC804\uCCB4", "\uC9C4\uC5F4\uD568", "\uC9C4\uC5F4\uC548\uD568"}));
		combo_view_use.setMaximumRowCount(3);
		
		JLabel label_top_stock = new JLabel("\uC7AC\uACE0\uC0AC\uC6A9");
		panel_2.add(label_top_stock, "cell 11 1,alignx center");
		
		combo_top_stockuse = new JComboBox<String>();
		panel_2.add(combo_top_stockuse, "cell 12 1,growx");
		combo_top_stockuse.setToolTipText("\uC1FC\uD551\uBAB0\uACFC \uC7AC\uACE0 \uC5F0\uB3D9\uC744 \uD558\uB294\uC9C0 \uC5EC\uBD80 \uC120\uD0DD \uAC80\uC0C9");
		combo_top_stockuse.setModel(new DefaultComboBoxModel<String>(new String[] {"\uC804\uCCB4", "\uC7AC\uACE0\uC0AC\uC6A9", "\uC7AC\uACE0\uC548\uD568"}));
		combo_top_stockuse.setMaximumRowCount(3);
		
		chkeck_top_anstock = new JCheckBox("\uC548\uC804\uC7AC\uACE0 \uC774\uD558\uC0C1\uD488");
		panel_2.add(chkeck_top_anstock, "cell 14 1 2 1");
		chkeck_top_anstock.setHorizontalAlignment(SwingConstants.CENTER);
		
		bt_search = new JButton("\uC0C1\uD488\uAC80\uC0C9");
		p_top.add(bt_search, "cell 1 2,alignx center,growy");
		
		bt_search.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				//������ ������ �ִٸ� �ٽ� ���� ���ϴ�.
				if(change_Flags()) return;
				
				detail_Renew();
				
				//�˻��ϱ�
				search_start();				
			}
		});
		
		/*
		 *  [��ǰ�˻� ��ư]
		 *  - �ŷ�ó�� �з� ��ǰ�� ���ڵ� �������� �˻� ������ �˴ϴ�.
		 *  
		 */
    	
    }
      
    
    //�����ǥ �ҷ����� ��� �߰�
    private void getEventCodeCall() {
    	this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
    	//����� ȣ���մϴ�.
    	
    	String query = "Select Evt_Cd, Evt_Name, Evt_SDate, Evt_EDate, " 
    					+ "Evt_Use=Case When "
    					+ "DateAdd(dd, 1, Convert(char(10), Finish_date, 23)) Between Convert(char(10), Evt_SDate, 23) and Convert(char(10), Evt_EDate, 23) "
    					+ "Then '����' ELSE '������' End, Count(*) Evt_cnt From Evt_Mst, Finish Group by Evt_Cd, Evt_Name, Evt_SDate, Evt_EDate, finish_date "
    					+ "order by Evt_Use desc ";
    	
    	ms_connect.setMainSetting();
    	ArrayList<HashMap<String, String>> temp_map = ms_connect.connection(query);
						
		if(temp_map.size() <= 0){			
			JOptionPane.showMessageDialog(this, "����ڵ带 �ҷ��� �� �����ϴ�.");
			return;
		}
					
		//String[] list = new String[temp_map.size()];
		String[] list = new String[temp_map.size()];
		String[] list_item = new String[temp_map.size()]; 
		for(int i = 0; i < temp_map.size(); i++){
			HashMap<String, String> map = temp_map.get(i);
			System.out.println(map.toString());
			
			//String.format("%d . [%s] [%s] %s - %s ��", (i+1), map.get("Evt_Use"), map.get("Evt_EDate"), map.get("Evt_Name"), map.get("Evt_cnt") );
			//String item = (i+1)+". "+map.get("Evt_Name")+" ["+map.get("Evt_Use")+"] "+map.get("Evt_cnt")+" ��"+" ������ : "+map.get("Evt_EDate");
			String item = String.format("%d. [%s] [%s] %s - %s ��", (i+1), map.get("Evt_Use"), map.get("Evt_EDate"), map.get("Evt_Name"), map.get("Evt_cnt") );
			list_item[i] = map.get("Evt_Cd");
			list[i] = item;
		}
		
		//ȣ���� ����� ���̾� �α׷� ���ϴ�.		
	    String input = (String) JOptionPane.showInputDialog(btn_top_salenum, "�˻��� ��� ��ǥ�� ������ �ּ���!",
	    		"�����ǥ ���", JOptionPane.QUESTION_MESSAGE, null, // Use
	                                                                        // default
	                                                                        // icon
	        list, // Array of choices
	        list[0]); // Initial choice
	    System.out.println(input);
		
	    try{
	    	
	    	//������ �̺�Ʈ�� �ҷ� �ɴϴ�.
	    	btn_top_salenum.setName(list_item[Integer.parseInt(input.substring(0, input.indexOf(".")))-1]);
	    	btn_top_salenum.setText("���õ�");
	    	System.out.println(btn_top_salenum.getName());
	    	
	    	detail_Renew();		
			
	    	//�˻��ϱ�
			search_start();			
	    }catch(NullPointerException e){
	    	this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	    }
	    this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}

	/*
     * [�˻��� �����մϴ�.]
     * - �˻��ϱ�
     */
    private void search_start(){
    	
    	this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
    	
    	dtm.setRowCount(0);
		
		//�˻� �ʱ�ȭ
		query_goods = "";
		query_info = "";
		
		//���ڵ� �Է� ����
		if(tx_barcode.getText().trim().length() > 0){
			if(chkeck_barcode_front.isSelected()){
				query_goods += "and barcode like '"+tx_barcode.getText()+"%' ";
			}else{
				query_goods += "and barcode like '%"+tx_barcode.getText()+"%' ";
			}
		}
		
		//��ǰ�� �Է� ����
		if(tx_gname.getText().trim().length() > 0){
			query_goods += "and g_name like '%"+tx_gname.getText()+"%' ";
		}
		
		//�ŷ�ó�� �Է� ����
		if(tx_officename.getText().trim().length() > 0){
			query_goods += "and bus_name like '%"+tx_officename.getText()+"' ";
		}
		
		//�ŷ�ó�ڵ� �Է� ����
		if(tx_officecode.getText().trim().length() > 0){
			query_goods += "and bus_code like '%"+tx_officecode.getText()+"' ";
		}
		
		//���ڵ��Է�����
		if(tx_Lcode.getText().trim().length() > 0){
			query_goods += "and L_Code='"+tx_Lcode.getText()+"' ";
		}
		
		//���ڵ��Է�����
		if(tx_Mcode.getText().trim().length() > 0){
			query_goods += "and M_Code='"+tx_Mcode.getText()+"' ";
		}
		
		//���ڵ��Է�����
		if(tx_Scode.getText().trim().length() > 0){
			query_goods += "and S_Code='"+tx_Scode.getText()+"' ";
		}
			
		//����ǰ/�κл�ǰ/�����ǰ combo_Goods_Gubun
		switch(combo_Goods_Gubun.getSelectedIndex()){
			case 1:
				query_goods += "and Scale_Use='0' ";
				break;
			case 2:
				query_goods += "and Scale_Use='1' ";
				break;
			case 3:
				query_goods += "and LEN(Barcode)=4 ";
				break;						
			default:
				break;
		}
						
		//������Ȳ ���� ���� �ϱ�
		switch (cb_upload.getSelectedIndex()) {
		case 1:
			query_info += "and upload='1' ";
			break;
		case 2:
			query_info += "and upload='0' ";
			break;
			
		default:					
			break;
		}
		
		//��� �Է¼������� ũ�ٸ�
		if(text_realsto_up.getText().trim().length() > 0){
			query_goods += "and Real_Sto >= "+text_realsto_up.getText();
		}
		
		//�̹���������������
		switch (cb_image_connect.getSelectedIndex()) {
		case 1:
			query_info += "and img_path_use='1' ";
			break;
		case 2:
			query_info += "and img_path_use='0' ";					
			break;
			
		default:
			break;
		}
		
		//�̹��� ���� ����
		switch(combo_image_get.getSelectedIndex()){	
		case 1:
			query_info += "and (img_path is null or img_path = '') ";			
			break;
		case 2:
			query_info += "and img_path <> ' ' ";
			break;
		}		
		
		//���� ���� ���� �ϱ�
		switch (cb_shoppingmall_use.getSelectedIndex()) {
		case 1:
			query_info += "and ShoppingMall_use='1' ";
			break;
		case 2:
			query_info += "and ShoppingMall_use='0' ";
			break;
			
		default:					
			break;
		}
		
		//���ϰ���
		switch (cb_Sale_Goods.getSelectedIndex()) {
		case 1:
			query_goods += "and sale_use='1' ";
			break;
		case 2:
			query_goods += "and sale_use='0' ";
			break;
			
		default:					
			break;
		}
		
		//���� ��ǥ�� �ҷ�����
		String evt_cd = btn_top_salenum.getName();		
		try{
			if(!evt_cd.isEmpty()) query_goods += "and BARCODE IN (  Select BARCODE FROM EVT_MST WHERE EVT_CD='"+evt_cd+"'  )";
		}catch(NullPointerException e){
			
		}
		
		
		//��������
		switch(combo_view_use.getSelectedIndex()){
		case 1:
			query_info += "and shop_view='1' ";
			break;
			
		case 2:
			query_info += "and shop_view='0' ";
			break;
		}
		
		//�������˻�
		query_pro="";
		if(chkeck_top_anstock.isSelected()){			
			query_pro = "where a.real_sto <= b.pro_sto and b.pro_sto <> '0' ";			
		}
		
		switch(combo_top_stockuse.getSelectedIndex()){
		case 1:
			query_info += "and sto_use='1' ";			
			break;
		case 2:
			query_info += "and sto_use='0' ";			
			break;			
		}
		
		//������� �ڵ� �Է�����
		if(!top_maincode_list.equals("")){
			String maincode_item = top_maincode_list.substring(top_maincode_list.length()-1, top_maincode_list.length());
			System.out.println(maincode_item);
			if(Integer.parseInt(maincode_item) == 1){
				maincode_item = top_maincode_list.substring(0, top_maincode_list.length()-2);
				System.out.println(maincode_item);
				if(maincode_item.equals("none")){
					query_info += "and (Shop_MainCode='' or Shop_MainCode Is Null) ";
				}else{
					query_info += "and Shop_MainCode Like '%"+maincode_item+"%' ";				
				}
			}else{				
				//1���̻��� �˻� ������ ��� �ִٸ� �ɰ��� �˻� ������ ����� �ּ���
				int count = Integer.parseInt(maincode_item);
				maincode_item = top_maincode_list.substring(0, top_maincode_list.length()-2);
				System.out.println(maincode_item);
				String [] item_list = maincode_item.split("\\|");
				query_info += "and ( ";
				for(String temp : item_list){
					query_info += "Shop_MainCode like '%"+temp+"%' ";
					if(count != 1){
						query_info += "or ";
					}
					count--;
				}
				query_info +=")";
			}
		}
		
		//�˻����� �ʱ�ȭ
		search_query();
		
		//��ǰ�˻� ����
		search_goods(query);
		
		this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }
    
        
	/*
	 * [��ǰ�˻�]
	 * ������ Ȯ�� �ؼ� ��ǰ �˻��� �մϴ�.
	 * ��ǰ �˻� �� Vector ��� �Ͽ� ���� �մϴ�.
	 * dtm�� �����մϴ�.
	 * ��� ������ true�� ��ȯ �մϴ�.
	 *  {"����", "����", "���ڵ�", "��ǰ��", "�԰�", "���԰�", "�ǸŰ�", "��ǰ���", "���ε�", "�ܵ��̹���","�̹�������", "���"};
	 */
	public boolean search_goods(String query){
		
		ms_connect.setMainSetting();
		ArrayList<HashMap<String, String>> al = ms_connect.connection(query);	
		switch(ms_connect.errCode){
		case 0:
			break;
		default:
			JOptionPane.showMessageDialog(this, ms_connect.errMsg);		
			return false;
		}
		
		try{
		if(al.size() > 0){
			dtm.setRowCount(0);
			for(int i=0; i < al.size(); i++ ){
				HashMap<String, String> map = new HashMap<String, String>();
				Vector<Object> v = new Vector<Object>();
				
				map = al.get(i);
								
				String sale_use = map.get("sale_use");
				String shoppingmall_use = map.get("shoppingmall_use");
				String upload = map.get("upload");
				String img_path_use = map.get("img_path_use");
								
				//"0 ����", "1 ���ڵ�", "2 ��ǰ��", "3 �԰�", "4 ���԰�", "5 �ǸŰ�", "6 �����", "7 �������", 
				// "8 �з��ڵ�", "9 ���ڵ�", "10 ���", "11 ���ڵ�", "12 �߸�", "13 ���ڵ�", "14 �Ҹ�", "15 ���", "16 ��ǰ����",
				//"`17 ���θ�", "`18 ��������", "`19 �����", "20 �̹�������", "21 �̹������" };
				
				//v.add(String.valueOf(i+1)); //0. ����
				v.add(i+1); //0. ����
				v.add(map.get("barcode")); //1. ���ڵ�
				v.add(map.get("g_name")); //2. ��ǰ��
				v.add(map.get("std_size")); //3. �԰�
				v.add(map.get("pur_pri")); //4. ���԰�
				v.add(map.get("sell_pri")); //5. �ǸŰ�
				v.add(map.get("real_sto"));//6. �����
				v.add(map.get("pro_sto"));//7. �������
				
				StringBuilder office_code = new StringBuilder();
				office_code.append((map.get("l_code")));
				office_code.append((map.get("m_code")));
				office_code.append((map.get("s_code")));
				
				if(office_code.length() < 8){				
					int count = 8 - office_code.length();					
					for(int a = 0 ;  a < count; a++){
						office_code.append("0");
					}					
				}
				
				v.add(office_code);		//8. �з��ڵ�		
				
				//����з�
				v.add(map.get("l_code"));  //9. ���ڵ�
				v.add(map.get("l_name"));  //10 ���
				v.add(map.get("m_code"));  //11. ���ڵ�
				v.add(map.get("m_name"));  //12. �߸�
				v.add(map.get("s_code"));  //13. ���ڵ�
				v.add(map.get("s_name"));				 // 14. �Ҹ�
				
				if(sale_use.equals("1")){ //15. �������
					v.add("�����");
				}else{
					v.add("������");
				}
				
								
				if(shoppingmall_use.equals("1")){ //16. ��ǰ����
					v.add("������"); 
				}else{
					v.add("��������"); 
				}
								
				if(upload.equals("1")){ //17. ���θ� ���ε�
					v.add("���ε��"); 
				}else{
					v.add("���ε�ȵ�"); 
				}
				
				v.add(map.get("shop_view")); //18. ��������				
				v.add(map.get("sto_use"));  //19. ���������
				
				v.add(map.get("shop_maincode")); //20.��������ڵ�
				
				if(img_path_use.equals("1")){ //21. �ܵ��̹���
					v.add("��������");
				}else{
					v.add("�ܵ�����");
				}
				
				v.add(map.get("img_path")); //22. ������ �̹��� ���
				v.add(map.get("img_name")); //23. �̹�����
				v.add(map.get("scale_use")); //24. �����ǰ
				dtm.addRow(v);				
			}
		}else{
			dtm.setRowCount(0);
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			JOptionPane.showMessageDialog(null, "�˻� ����� �����ϴ�. ");			
		}		
		
		}catch(NullPointerException e){
			e.printStackTrace();
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			JOptionPane.showMessageDialog(null, "DB ���ӿ� ���� �߽��ϴ�. \r\n���ͳ� ȸ�� �� ������ ������ �ּ���!!");
			return false;
		}
		
		return true;
	}
		
	
	//�⺻ ���� ����
	private void search_query(){
		//query = "Select * from goods where goods_use='1' ";
		
		query = " Select a.barcode, a.g_name, a.std_size, a.pur_pri, a.sell_pri, a.real_sto, b.pro_sto, a.sale_use, a.l_code, a.l_name, a.m_code, a.m_name, "
				+ "a.s_code, a.s_name, a.bus_code, a.bus_name, a.goods_use, a.scale_use, b.shoppingmall_use, b.upload, b. shop_view, b.sto_use, ISNULL(b.shop_maincode, '') as shop_maincode, b.img_path_use, b.img_path, ISNULL(b.img_name, '') as img_name "
				+ "From ( Select * From Goods Where L_code <> 'AA' and goods_use='1' "+query_goods+" ) a join "
				+ " (select * from goods_info where 1=1 "+query_info+" ) b "
				+ " on a.barcode=b.barcode "+query_pro ;
	}
        
	
    //��ǰ �˻� ���
    private void goods_list(){
    	
    	/**
		 * �� ���� ����
		 */
		/*DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
		celAlignCenter.setHorizontalAlignment(JLabel.CENTER);
		DefaultTableCellRenderer celAlignRight = new DefaultTableCellRenderer();
		celAlignRight.setHorizontalAlignment(JLabel.RIGHT);
		*/
    	
		String[] colunm = {"����", "���ڵ�", "��ǰ��", "�԰�", "���԰�", "�ǸŰ�", "�����", "�������", 
				"�з��ڵ�", "���ڵ�", "���", "���ڵ�", "�߸�", "���ڵ�", "�Ҹ�", "���", "��ǰ����", "���θ�", "��������", "�����", "�������", "�̹�������", "�̹������", "�̹�����", "�����ǰ"};
		
		dtm = new DefaultTableModel(null, colunm){
			/**
			 * [Į�� ���� ���ϰ� ����]
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int rowIndex, int mColIndex){
				return false;
			}
		};		
    }
    
    //�ӽû��� ftp�� �����ؼ� ������ �ҷ��� ����DB�� Insert �մϴ�.
    //�ѹ� ����ϱ� ���ؼ� ���� �Լ� �Դϴ�.
    private void ftp_Connect(){
    	
    	this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
    	//������ �̸��� �ø��ϴ�.
    	HashMap<String, String> file_map = new HashMap<String, String>();
    	    	
    	/* 
    	 * �̹��� ������ �����մϴ�.
    	 * ������ ������ �ҷ��鿩�� split�� �մϴ�. 
    	* ���ϸ�� Ȯ���ڷ� ������ ��� ������ �մϴ�.
    	* ftp_image ������ �̹����� �ִ��� Ȯ���� �մϴ�.
    	* �̹����� ����� �Ǿ��ִٸ� ���ε� ���� �ʰ� �ϱ����� ����� ����ϴ�. 
    	* ���� Ʈ�� �������� ��ǰ���� �ҷ� �ɴϴ�.
    	* �̹����� FTP������ ���ε� �մϴ�.
    	* �̹��������� �����մϴ�. 
    	*/    	
    	
    	String path = JOptionPane.showInputDialog("�ҷ��� ��θ� �Է��� �ּ���");
    	System.out.println(path);
    	
    	try{
    		if(path == null || "".equals(path)) {
    			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    			return;
    		}
    	}catch(NullPointerException e){
    		e.printStackTrace();
    		this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    		return;
    	}
    	//�������� ������ ���� �ϴ��� Ȯ�� �մϴ�.
    	String query = "Select Barcode From FTP_Image Where Barcode In( ";    	
    	
    	//������ �ҷ� �ɴϴ�.
    	File file_path = new File(path);
    	String[] file_list = file_path.list();
    	final String[] FILE_EXTENSION = {"jpg","gif","png","bmp"};
    	
    	int file_count = 0;
    	//�и� �۾��� �մϴ�.
    	for(String file:file_list){
    		if(!file.contains(".")) continue; 
			//���ϸ��� "." �� �������� ���ڸ��� �߶� ���ϴ�.
			//System.out.println(fileName);
			String perfix = file.substring(0, file.lastIndexOf("."));
			String ext = file.substring(file.lastIndexOf(".")+1, file.length()).toString();
			
			//String tempPath=tempFile.getParent();
			//Ȯ���ڸ� �˻��ؼ� �׸� ���ϸ� �ҷ� �ɴϴ�.
			for(int i =0; i < FILE_EXTENSION.length; i++){
				if(ext.equals(FILE_EXTENSION[i])){
					//������ �ҷ��ɴϴ�.
					file_map.put(perfix, ext);
					
					query += "'"+perfix+"', ";
					file_count++;
				}
			}  
    	}    	
    	
    	query = query.substring(0, query.length()-2);
    	query += " )";
    	//�������� ������ ���� �ϴ��� Ȯ�� �մϴ�.
    	ms_connect.setImageSetting();
    	ArrayList<HashMap<String, String>> temp_map = ms_connect.connection(query);
    	    	
    	System.out.println(temp_map.size()+" == "+file_list.length );
    	if(temp_map.size() == file_list.length){
    		JOptionPane.showMessageDialog(this, "���ε��� �̹��� ������ �����ϴ�.");
    		this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    		return;
    	}
    	
    	Iterator<HashMap<String, String>> temp = temp_map.iterator();
    	
    	//�˻� ����� ���ڵ尡 �����ϸ� ���� �մϴ�. 
    	while(temp.hasNext()){
    		HashMap<String, String> map = temp.next();    		
    		file_map.remove(map.get("Barcode"));    		
    	}
    	
    	System.out.println(file_count);
    	System.out.println(file_map.toString());
    	System.out.println(file_map.size());
    	    	
    	//������ ���ε�� ������ �����մϴ�.
    	ArrayList<String> query_list = new ArrayList<String>();
    	Iterator<String> iter = file_map.keySet().iterator();
    	int cnt = 0;
    	while(iter.hasNext()){
    		String image_query = "Insert Into FTP_Image (barcode, path, path_gubun, g_name, ext, pop_tran) Values( ";
    		String key = (String)iter.next();    		
    		image_query += "'"+key.trim()+"', 'main_goods', '1', '', '"+file_map.get(key).trim()+"', '' )";   		
    		query_list.add(image_query);    		
    		cnt++;
    		if(cnt%500 == 0){ 
    	    	int result = ms_connect.connect_update(query_list);
    	    	switch(result){
    	    	case 0:	    		
    	    		query_list.clear();    	    		
    	    		break;
    	    	case 1:
    	    		JOptionPane.showMessageDialog(this, "���ε忡 ���� �߽��ϴ�.");
    	    		this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    	    		return;
    	    	}
    		}
    	}
    	
    	//������ ó���մϴ�.
    	if(cnt%500 > 0){
    		
    		int result = ms_connect.connect_update(query_list);
	    	switch(result){
	    	case 0:
	    		JOptionPane.showMessageDialog(this, "���� ��� �Ǿ����ϴ�.");
	    		this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	    		return;
	    	case 1:
	    		JOptionPane.showMessageDialog(this, "���ε忡 ���� �߽��ϴ�.");
	    	}    		
    	}    	
    	
    	this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }
    
    /*
	 * [���콺 �ι� Ŭ��]
	 * - ���콺 ����Ŭ�� �� ���� �� ��ǰ�� ������ �� ������ �����ϴ�.
	 */	
	public void setGoodsDetail(){
		
		this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
		
		detail_Renew();
		
		// ���̺��� ���õ� ���� �ε��� ��ȣ ���
		//int row = table.getSelectedRow();
		int row = table.convertRowIndexToModel(table.getSelectedRow());
		System.out.println(row);
		int col = table.getColumnCount();
		
		//Vector<Object> temp = new Vector<Object>(); 
		
		for(int i =0; i < col; i++){			
			temp_detail.add(dtm.getValueAt(row, i));
		}
		
		System.out.println(temp_detail.toString());
		
		//"0 ����", "1 ���ڵ�", "2 ��ǰ��", "3 �԰�", "4 ���԰�", "5 �ǸŰ�", "6 �����", "7 �������", 
		// "8 �з��ڵ�", "9 ���ڵ�", "10 ���", "11 ���ڵ�", "12 �߸�", "13 ���ڵ�", "14 �Ҹ�", "15 ���", "16 ��ǰ����",
		//"`17 ���θ�", "`18 ��������", "`19 �����", (20.�߰� ��������ڵ�) "21 �̹�������", "22 �̹������"  (23.�߰� �̹�����)};
		label_Detail_Number.setText(temp_detail.get(0).toString()); //����
		text_Detail_Barcode.setText(temp_detail.get(1).toString()); //���ڵ�
		text_Detail_Name.setText(temp_detail.get(2).toString());  //��ǰ��
		
		text_Detail_PurPri.setText(temp_detail.get(4).toString());  //���԰�
		text_Detail_SellPri.setText(temp_detail.get(5).toString());  //�ǸŰ�
		text_Detail_Stock.setText(temp_detail.get(6).toString());  //�����
		text_Detail_AnStock.setText(temp_detail.get(7).toString());  //�������
		
		text_Detail_Category.setText(temp_detail.get(8).toString());  //�з�
				
		if(temp_detail.get(15).equals("�����")){ //������࿩��			
			btnToggle_Detail_SaleUse.setSelected(true); 
			btnToggle_Detail_SaleUse.setText("�����");
		}else{
			btnToggle_Detail_SaleUse.setSelected(false);
			btnToggle_Detail_SaleUse.setText("�Ϲ�");
		}		
		
		combo_Detail_ShopConnectUse.setSelectedItem(temp_detail.get(16).toString());
		
		if(temp_detail.get(17).equals("���ε��")){
			btnToggle_Detail_ConnectState.setSelected(true);
			btnToggle_Detail_ConnectState.setText("������");
			combo_Detail_ShopConnectUse.setEnabled(false);
		}else{
			btnToggle_Detail_ConnectState.setSelected(false);
			btnToggle_Detail_ConnectState.setText("�����ȵ�");
		}
		
		if(temp_detail.get(18).equals("1")){
			combo_Detail_View.setSelectedItem("������"); //��������
		}else{
			combo_Detail_View.setSelectedItem("��������"); //��������
		}		
		
		if(temp_detail.get(19).equals("1")){ //�����
			checkBox_Detail_Stock.setSelected(true);
		}else{
			checkBox_Detail_Stock.setSelected(false);
		}
		
		//���⼭����
		String maincode = temp_detail.get(20).toString();
		label_default_maincode.setText(maincode);
		
		if(temp_detail.get(21).equals("�ܵ�����")){
			combox_Detail_ImageConnectUse.setSelectedItem("�ܵ�����"); //�̹��� ��������
		}else{
			combox_Detail_ImageConnectUse.setSelectedItem("��������"); //�̹��� ��������
		}
		
		try{
		if(!temp_detail.get(22).toString().equals("")){			
			Image image = null;
	        try {
	        	System.out.println(temp_detail.get(22));
	            URL url = new URL(temp_detail.get(22).toString());	            
	            
	            image = ImageIO.read(url);        
	            
	        } catch (IOException e) {
	        	e.printStackTrace();
	        	this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	        }
	        
	        ConnectAsSocket cas = new ConnectAsSocket("shopFTPServer");
	        
	        if(cas.connect_Check()){
	        	System.out.println("�������Ӽ���");
	        	label_image_view.setIcon(new ImageIcon(image.getScaledInstance(150, 150, Image.SCALE_SMOOTH)));
	        }else{	       
	        	JOptionPane.showMessageDialog(this, "�̹���  FTP������ ������ �ȵǰ� �ֽ��ϴ�. ��Ʈ��ũ�� Ȯ���� �ּ���!!");
	        }
		}
		}catch(NullPointerException e){
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}
		
		text_Detail_ImagePath.setText(temp_detail.get(22).toString()); //�̹������				
		//comboBox.setSelectedItem(temp.get(index));		
		
		text_imagename.setText(temp_detail.get(23).toString()); //�̹�����
		
		this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}    
	
	
	/*
	 * [���콺 Ŭ�� �� ]
	 * ��ǰ���� �״�� �˻� ���ǿ� �Ѱ� �ݴϴ�. 
	 * 
	 * */	
	public void setSearchGoodsName(){		
		this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
		
		//int row = table.getSelectedRow();
		int row = table.convertRowIndexToModel(table.getSelectedRow());
		int col = table.getColumnCount();
		
		Vector<Object> temp = new Vector<Object>(); 
		
		for(int i =0; i < col; i++){
			temp.add(dtm.getValueAt(row, i));
		}		
		System.out.println(temp.toString());
		
		//��ǰ�� �ҷ����� ���
		if(chkbox_listsearch_gname.isSelected()){
			text_jtab_search.setText(temp.get(2).toString());
		}
		
		if(chkbox_listsearch_code.isSelected()){
			text_jtab_search.setText(temp.get(1).toString());
		}
		
		jtap_FTPSearch();
		
		this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));		
	}
	
	//�����ڵ� �����ϱ�
	private void maincode_top_setList(){
		
		//������� �ڵ带 �ҷ� �ɴϴ�. ���� �� â�� ����� �ʽ��ϴ�.
		//�����ڵ带 ȣ�������� �ִٸ� ���̻� ȣ������ �ʽ��ϴ�.
		if(!maincode_callUse){		
			try{
				main_code = shop_api.getMainCode("��ǰ����");
				maincode_callUse = true;
			}catch(NullPointerException e){
				JOptionPane.showMessageDialog(this, "Ȩ�������� ���� �� �� �����ϴ�. ȯ�漳�� �� ���ͳ��� Ȯ���� �ּ���!!");
				return;
			}		
		}
		
		if(main_code.size() <= 0){
			JOptionPane.showMessageDialog(this, "������� �ڵ带 �������� �ʾҽ��ϴ�. ���θ� �����ڿ��� ���� �ϼ���!!");
			return;
		}
		
		//��� ���� �����ϱ� ���ؼ� ���� �մϴ�.
		JPanel panel_main = new JPanel();
		panel_main.setLayout(new BorderLayout());
		
		//��� ���� ����<html>Hello World!<br>blahblahblah</html>", SwingConstants.CENTER		
		JLabel main_title = new JLabel("<html><h2 align='center' style='font-family: ���� ���'>��ǰ ��� ��ġ�� ������ �ּ���!!</h2><p align='center'>- \"��¾���\" �ɼǰ� �ߺ����� ���� �� �������� �˻��� �� �ɼ� �ֽ��ϴ�.!</p><hr><br></html>", SwingConstants.CENTER);
		panel_main.add(main_title, BorderLayout.NORTH);
		
		//���� üũ�ڽ��� ����ϴ�.
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.gray));
		chkboxs_top = new ArrayList<JCheckBox>();
		
		//�ϴ� �޴� ����
		panel_main.add(panel, BorderLayout.CENTER);
		
		JCheckBox chk_box = new JCheckBox("��¾���");
		chk_box.setName("none");
		chk_box.addItemListener(new ChkboxItemListioner_Top());
		panel.add(chk_box);
		chkboxs_top.add(chk_box);
		
		Iterator itr = main_code.iterator();
		while(itr.hasNext()){				
			JSONObject temp = (JSONObject)itr.next();
			chk_box = new JCheckBox();
			chk_box.setText(temp.get("subject").toString());
			chk_box.setName(temp.get("group_code").toString());
			chk_box.addItemListener(new ChkboxItemListioner_Top());
			panel.add(chk_box);
			chkboxs_top.add(chk_box);
		}
				
		//����� ����ϴ�.
		//���� ����Ǿ��ִ� ����Ʈ�� �ִٸ� �ҷ��ɴϴ�.		
		String [] list_item;
		System.out.println(top_maincode_list);
		if(!top_maincode_list.equals("")){
			Iterator itr_list = chkboxs_top.iterator();
			list_item = top_maincode_list.split("\\|");
			//���õǾ��� ������ �����ɴϴ�.			
			while(itr_list.hasNext()){
				JCheckBox temp_chk = (JCheckBox)itr_list.next();
				for(String temp : list_item){
					if(temp_chk.getName().equals(temp)){
						System.out.println("�����մϴ�. " +temp);
						temp_chk.setSelected(true);
					}		
				}
			}
		}
		
		
		String[] options = new String[]{"Ȯ��", "���"};
		int option = JOptionPane.showOptionDialog(P, panel_main, " ������� �ڵ� ���� ",
		                         JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
		                         null, options, chkboxs_top);
		if(option == 0) // pressing OK button
		{			
						
			Iterator itr_list = chkboxs_top.iterator();			
			top_maincode_list = "";
			int i = 0;
			while(itr_list.hasNext()){
				JCheckBox temp = (JCheckBox)itr_list.next();
				if(temp.isSelected()){
					top_maincode_list += temp.getName()+"|";
					i++;
				}
			}
			if(!top_maincode_list.equals(""))	top_maincode_list += i;			
			label_top_maincode_chk.setText(i+"�� ���õ�");
			System.out.println(top_maincode_list);			
		}
	}
	
	
	//�����ڵ� �����ϱ�
	private void maincode_default_setList(){
		
		try{
			int number = Integer.parseInt(label_Detail_Number.getText());
		}catch(NumberFormatException e){
			JOptionPane.showMessageDialog(this, "��ǰ�� ������ �ּ���!!");
			detail_Renew();
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			return;
		}		
		
		//������� �ڵ带 �ҷ� �ɴϴ�. ���� �� â�� ����� �ʽ��ϴ�.
		//�����ڵ带 ȣ�������� �ִٸ� ���̻� ȣ������ �ʽ��ϴ�.
		if(!maincode_callUse){		
			try{
				main_code = shop_api.getMainCode("��ǰ����");
				maincode_callUse = true;
			}catch(NullPointerException e){
				JOptionPane.showMessageDialog(this, "Ȩ�������� ���� �� �� �����ϴ�. ȯ�漳�� �� ���ͳ��� Ȯ���� �ּ���!!");
				return;
			}		
		}
		
		if(main_code.size() <= 0){
			JOptionPane.showMessageDialog(this, "��ǰ��� ��ġ�� ������ �ʾҽ��ϴ�. ���θ� �����ڿ��� ���� �ϼ���!!");
			return;
		}
		
		//��� ���� �����ϱ� ���ؼ� ���� �մϴ�.
		JPanel panel_main = new JPanel();
		panel_main.setLayout(new BorderLayout());
		
		//��� ���� ����<html>Hello World!<br>blahblahblah</html>", SwingConstants.CENTER		
		JLabel main_title = new JLabel("<html><h2 align='center' style='font-family: ���� ���'>��ǰ ��� ��ġ�� ������ �ּ���!!</h2><p align='center'>- �ߺ� ���� �����մϴ�. ����÷��� ��¾����� ������ �ּ���!!</p><hr><br></html>", SwingConstants.CENTER);
		panel_main.add(main_title, BorderLayout.NORTH);
		
		//���� üũ�ڽ��� ����ϴ�.
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.gray));
		chkboxs_detail = new ArrayList<JCheckBox>();
		
		//�ϴ� �޴� ����
		panel_main.add(panel, BorderLayout.CENTER);
		
		JCheckBox chk_box = new JCheckBox("��¾���");
		chk_box.setName("none");
		chk_box.addItemListener(new ChkboxItemListioner_Detail());
		panel.add(chk_box);
		chkboxs_detail.add(chk_box);
		
		Iterator itr = main_code.iterator();
		while(itr.hasNext()){				
			JSONObject temp = (JSONObject)itr.next();
			chk_box = new JCheckBox();
			chk_box.setText(temp.get("subject").toString());
			chk_box.setName(temp.get("group_code").toString());
			chk_box.addItemListener(new ChkboxItemListioner_Detail());
			panel.add(chk_box);
			chkboxs_detail.add(chk_box);	
		}
				
		//����� ����ϴ�.
		//���� ����Ǿ��ִ� ����Ʈ�� �ִٸ� �ҷ��ɴϴ�.		
		String [] list_item;
		String item_list = label_default_maincode.getText();
		//String item_list = temp_detail.get(20).toString();
		System.out.println(item_list);		
		if(!item_list.equals("")){
			Iterator itr_list = chkboxs_detail.iterator();
			list_item = item_list.split("\\|");
			//���õǾ��� ������ �����ɴϴ�.			
			while(itr_list.hasNext()){
				JCheckBox temp_chk = (JCheckBox)itr_list.next();
				for(String temp : list_item){
					if(temp_chk.getName().equals(temp)){
						System.out.println("�����մϴ�. " +temp);
						temp_chk.setSelected(true);
					}
				}
			}
		}		
		
		String[] options = new String[]{"Ȯ��", "���"};
			//panel_main
		int option = JOptionPane.showOptionDialog(P, panel_main, " ������� �ڵ� ���� ",
		                         JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
		                         null, options, chkboxs_detail);
		if(option == 0) // pressing OK button
		{				
			Iterator itr_list = chkboxs_detail.iterator();			
			item_list = "";
			int i = 0;
			while(itr_list.hasNext()){
				JCheckBox temp = (JCheckBox)itr_list.next();
				if(temp.isSelected()){
					item_list += temp.getName()+"|";
					i++;
				}
			}
			
			if(i <= 0){
				label_default_maincode.setText("none");
			}else{
				item_list = item_list.substring(0, item_list.length()-1);
				label_default_maincode.setText(item_list);
			}
			
			edit_goods_info = true;
			System.out.println(item_list);
		}
	}
	
    //��ǰ ���� ���� �� ����
    private void goods_detail(){
    	
    	/*
		 * [��ǰ ���� �� ���� ��ư]
		 * ��ǰ ���� �� ������ ������ ���� �մϴ�. 
		 */		
		
		JPanel panel_1 = new JPanel();
		add(panel_1);
		panel_1.setLayout(new BorderLayout(5, 0));
		
		JPanel panel = new JPanel();
		panel_1.add(panel, BorderLayout.NORTH);
		panel.setLayout(new MigLayout("", "[81px][81px][80px,grow][81px][][][][][][][grow][][][165.00]", "[baseline]"));
		
		JButton btn_all_select = new JButton("��ü����");
		panel.add(btn_all_select, "cell 0 0,alignx left,aligny top");
		
		btn_all_select.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub				
				table.selectAll();
			}
		});
				
		JButton btn_all_clear = new JButton("\uC804\uCCB4\uD574\uC81C");
		btn_all_clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				table.clearSelection();
			}
		});
		panel.add(btn_all_clear, "cell 1 0,alignx left,aligny top");
		
		//��ǰ �ҷ����� �ӽ� ���???
		JButton bt_ftp_connect = new JButton("�ҷ�����");
		panel.add(bt_ftp_connect, "cell 2 0,alignx left,aligny top");
		
		bt_ftp_connect.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub				
				setImageServerUploadFTP();			
			}
		});		
		bt_ftp_connect.setVisible(true);
		
		JLabel lblNewLabel_4 = new JLabel("\uC0C1\uD488 \uAC80\uC0C9 \uBAA9\uB85D");
		panel.add(lblNewLabel_4, "cell 3 0,alignx left,aligny center");
		lblNewLabel_4.setForeground(Color.BLACK);
		lblNewLabel_4.setBackground(SystemColor.textHighlight);
		lblNewLabel_4.setFont(new Font("���� ���", Font.BOLD, 12));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		
		/*JButton bt_All_Save = new JButton("\uC77C\uAD04\uBCC0\uACBD");
		panel.add(bt_All_Save, "cell 7 0,alignx left,aligny top");
		
				bt_All_Save.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
																					
						if(table.getSelectedRowCount() > 0 && table.getSelectedRow() > -1){
						
							//������ ������ �ִٸ� �ٽ� ���� ���ϴ�.
							if(change_Flags()) return;
							
							// TODO Auto-generated method stub
							changeDialog = new AllChangeDialog(table);
							changeDialog.setModal(true);	
							changeDialog.setLocationRelativeTo(P);
							changeDialog.open();
							tabPane_detail.add(changeDialog, 2);
													
						}else{
							JOptionPane.showMessageDialog(Goods_Manage.this, "��ǰ�� ������ �ּ���!!");
						}				
					}
				});*/
		
		JLabel lblNewLabel_1 = new JLabel("\uC0AC\uC6A9\uC911\uC9C0 \uC0C1\uD488\uACFC \uB9E4\uC785\uB300\uBD84\uB958(AA) \uC0C1\uD488\uC740 \uAC80\uC0C9 \uB418\uC9C0 \uC54A\uC2B5\uB2C8\uB2E4.");
		lblNewLabel_1.setFont(new Font("���� ���", Font.PLAIN, 11));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBackground(SystemColor.info);
		lblNewLabel_1.setOpaque(true);
		panel.add(lblNewLabel_1, "cell 5 0,grow");
		
		JLabel label_Detail_Title = new JLabel("\uC0C1\uD488 \uC0C1\uC138 \uC815\uBCF4");
		panel.add(label_Detail_Title, "cell 11 0,alignx left,aligny center");
		label_Detail_Title.setFont(new Font("���� ���", Font.BOLD, 12));
		label_Detail_Title.setHorizontalAlignment(SwingConstants.CENTER);
		
		JButton btn_detail_renew = new JButton("\uD558\uB2E8 \uC0C8\uB85C\uC785\uB825");
		btn_detail_renew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//������ ������ �ִٸ� �ٽ� ���� ���ϴ�.
				if(change_Flags()) return;
				
				switch(tabPane_detail.getSelectedIndex()){
				
				case 0:
					detail_Renew();
					break;
				case 1:
					jtab_image_Renew();							
					break;
				case 2:
					jtab_hotkey_Renew();
					break;
				case 3:					
					jtab_allChange_Renew();
					break;
				}						
			}
		});
		panel.add(btn_detail_renew, "cell 13 0,alignx center");
				
    	JPanel panel_goods_detail = new JPanel();
    	//panel_1.add(panel_goods_detail, BorderLayout.EAST);
    	panel_goods_detail.setBorder(new LineBorder(Color.GRAY));
    	panel_goods_detail.setLayout(new MigLayout("", "[1px][52px][5px][52px][][][][5px]", "[15][1px][21px][23px][21px][][15][1px][15][30.00px][30.00px][30.00px][30.00,center][30][30][15][-10.00px][20.00][150px]"));
    	
    	tabPane_detail = new JTabbedPane(JTabbedPane.TOP);
    	panel_1.add(tabPane_detail, BorderLayout.EAST);
    	
    	
    	JPanel panel_jtabgoods_image = new JPanel();
    	
    	panel_jtabgoods_image.setBorder(new LineBorder(Color.GRAY));
    	panel_jtabgoods_image.setLayout(new MigLayout("", "[57px][][][19.00px,grow][][grow]", "[23px][23px][2px][23px][21px][2px][1px,grow]"));
    	
    	JLabel label_jtab_search = new JLabel("\uC11C\uBC84\uAC80\uC0C9");
    	label_jtab_search.setHorizontalAlignment(SwingConstants.CENTER);
    	panel_jtabgoods_image.add(label_jtab_search, "cell 0 0,growx,aligny center");
    	
    	text_jtab_search = new JTextField();
    	panel_jtabgoods_image.add(text_jtab_search, "cell 1 0 3 1,growx,aligny center");
    	text_jtab_search.setColumns(10);
    	text_jtab_search.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				int keyCode = e.getKeyCode();
	            switch (keyCode) {
	            case KeyEvent.VK_ENTER:	            	
	    			jtap_FTPSearch();	    			
	                break;	
	            }
			}
		});
    	
    	JButton btn_jtab_search = new JButton("\uAC80\uC0C9");
    	btn_jtab_search.setToolTipText("<html>\r\n<p> FTP\uC11C\uBC84\uC5D0 \uC800\uC7A5\uB41C \uB0B4\uC6A9\uC744 \uAC80\uC0C9\uD569\uB2C8\uB2E4. </p>\r\n<p> \uBC14\uCF54\uB4DC\uB610\uB294 \uC0C1\uD488\uBA85\uC744 \uB123\uC5B4 \uC8FC\uC138\uC694</p>\r\n</html>");
    	panel_jtabgoods_image.add(btn_jtab_search, "cell 5 0,growx,aligny top");
    	btn_jtab_search.setActionCommand("�̹����˻�");
    	btn_jtab_search.addActionListener(this);
    	
    	
    	JLabel lblPc = new JLabel("PC\uAC80\uC0C9");    	
    	lblPc.setHorizontalAlignment(SwingConstants.CENTER);
    	panel_jtabgoods_image.add(lblPc, "cell 0 1,growx,aligny center");
    	
    	text_jtab_path = new JTextField();
    	text_jtab_path.setEditable(false);
    	panel_jtabgoods_image.add(text_jtab_path, "cell 1 1 3 1,growx,aligny center");
    	text_jtab_path.setColumns(10);
    	
    	text_jtab_path.setText(Server_Config.getPCIMAGE_PATH());
    	
    	JButton btn_jtab_pathsearch = new JButton("\uD3F4\uB354");
    	btn_jtab_pathsearch.setToolTipText("<html>\r\n<p>\uB0B4 \uCEF4\uD4E8\uD130\uC758 \uC774\uBBF8\uC9C0 \uD30C\uC77C\uC744 \uAC80\uC0C9 \uD569\uB2C8\uB2E4.</p>\r\n<p>\uD655\uC7A5\uC790\uAC00 jpg, gif, png \uD30C\uC77C\uB9CC \uAC80\uC0C9 \uB429\uB2C8\uB2E4.</p>\r\n<p>\uC774\uBBF8\uC9C0 \uC0AC\uC774\uC988\uB294 \uBE44\uC728\uC5D0 \uB9DE\uCDB0 500x500\uC73C\uB85C \uC790\uB3D9 \uC870\uC808 \uB429\uB2C8\uB2E4.</p>\r\n</html>");
    	btn_jtab_pathsearch.setActionCommand("�����˻�");
    	btn_jtab_pathsearch.addActionListener(this);
    	
    	chkbox_pcsearch_dir = new JCheckBox("");
    	chkbox_pcsearch_dir.setToolTipText("<Html>\r\n<center>\uCCB4\uD06C \uC2DC \uD3F4\uB354 \uAC80\uC0C9\uCC3D\uC744 \uB744\uC6B0\uC9C0 \uC54A\uACE0<br>\r\n\uC9C0\uC815\uD55C \uACBD\uB85C\uB97C \uBC14\uB85C \uAC80\uC0C9 \uD569\uB2C8\uB2E4.</center>\r\n</Html>");
    	panel_jtabgoods_image.add(chkbox_pcsearch_dir, "cell 4 1");
    	panel_jtabgoods_image.add(btn_jtab_pathsearch, "cell 5 1,growx,aligny top");
    	
    	JSeparator separator_2 = new JSeparator();
    	panel_jtabgoods_image.add(separator_2, "cell 0 2 6 1,growx,aligny top");
    	
    	JLabel label_1 = new JLabel("\uAC80\uC0C9\uC635\uC158");
    	panel_jtabgoods_image.add(label_1, "cell 0 3");
    	
    	chkbox_listsearch_gname = new JCheckBox("\uC0C1\uD488\uBA85");
    	chkbox_listsearch_gname.setSelected(true);
    	panel_jtabgoods_image.add(chkbox_listsearch_gname, "cell 1 3");
    	
    	chkbox_listsearch_code = new JCheckBox("\uBC14\uCF54\uB4DC");
    	panel_jtabgoods_image.add(chkbox_listsearch_code, "cell 2 3");
    	
    	chkbox_listSearchNot = new JCheckBox("\uAC80\uC0C9 \uC548\uD568");
    	panel_jtabgoods_image.add(chkbox_listSearchNot, "cell 3 3,alignx center");
    	
    	listsearch_bg = new ButtonGroup();
    	listsearch_bg.add(chkbox_listsearch_gname);
    	listsearch_bg.add(chkbox_listsearch_code);
    	listsearch_bg.add(chkbox_listSearchNot); 
    	
    	JButton btn_downCount = new JButton("<");
    	btn_downCount.setActionCommand("�ٿ�");
    	panel_jtabgoods_image.add(btn_downCount, "cell 0 4,alignx center");
    	btn_downCount.addActionListener(this);
    	
    	
    	JButton btn_upCount = new JButton(">");
    	btn_upCount.setActionCommand("��");
    	panel_jtabgoods_image.add(btn_upCount, "cell 5 4,alignx center");
    	btn_upCount.addActionListener(this);
    	
    	JSeparator separator_3 = new JSeparator();
    	panel_jtabgoods_image.add(separator_3, "cell 0 5 6 1,growx,aligny top");
    	
    	final JScrollPane scroll_jtab_image = new JScrollPane();
    	scroll_jtab_image.setViewportBorder(new LineBorder(new Color(0, 0, 0)));
    	scroll_jtab_image.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    	scroll_jtab_image.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    	panel_jtabgoods_image.add(scroll_jtab_image, "cell 0 6 6 1,grow");
    	
    	scroll_jtab_image.getVerticalScrollBar().setUnitIncrement(20);
    	
    	panel_jtap_image = new JPanel();
    	scroll_jtab_image.setViewportView(panel_jtap_image);
    	panel_jtap_image.setLayout(new GridLayout(0, 3, 0, 0));
    	    	
    	JLabel label_Detail_Barcode = new JLabel("\uBC14\uCF54\uB4DC");
    	label_Detail_Barcode.setHorizontalAlignment(SwingConstants.CENTER);
    	panel_goods_detail.add(label_Detail_Barcode, "cell 1 1,alignx center,aligny center");
    	
    	text_Detail_Barcode = new JTextField();
    	text_Detail_Barcode.setEditable(false);
    	text_Detail_Barcode.setHorizontalAlignment(SwingConstants.CENTER);
    	panel_goods_detail.add(text_Detail_Barcode, "cell 2 1 2 1,growx,aligny center");
    	text_Detail_Barcode.setColumns(10);
    	
    	label_Detail_Number = new JLabel("\uC21C\uBC88");
    	label_Detail_Number.setToolTipText("\uC88C\uCE21 \uBAA9\uB85D\uC758 \uC21C\uBC88\uC785\uB2C8\uB2E4.");
    	label_Detail_Number.setHorizontalAlignment(SwingConstants.CENTER);
    	panel_goods_detail.add(label_Detail_Number, "cell 6 1,growx,aligny center");
    	
    	JLabel label_Detail_Name = new JLabel("\uC0C1\uD488\uBA85");
    	label_Detail_Name.setHorizontalAlignment(SwingConstants.CENTER);
    	panel_goods_detail.add(label_Detail_Name, "cell 1 2,alignx center,aligny center");
    	
    	text_Detail_Name = new JTextField();
    	panel_goods_detail.add(text_Detail_Name, "cell 2 2 3 1,growx,aligny center");
    	text_Detail_Name.setColumns(10);
    	
    	btnToggle_Detail_SaleUse = new JToggleButton("\uD589\uC0AC\uC5EC\uBD80");
    	btnToggle_Detail_SaleUse.setEnabled(false);
    	panel_goods_detail.add(btnToggle_Detail_SaleUse, "cell 6 2,growx,aligny center");
    	
    	JLabel label_Detail_Category = new JLabel("\uBD84   \uB958");
    	label_Detail_Category.setHorizontalAlignment(SwingConstants.CENTER);
    	panel_goods_detail.add(label_Detail_Category, "cell 1 3,alignx center,aligny center");
    	
    	text_Detail_Category = new JTextField();
    	text_Detail_Category.setEditable(false);
    	text_Detail_Category.setHorizontalAlignment(SwingConstants.CENTER);
    	panel_goods_detail.add(text_Detail_Category, "cell 2 3 2 1,growx,aligny center");
    	text_Detail_Category.setColumns(10);
    	
    	checkBox_Detail_Stock = new JCheckBox("\uC7AC\uACE0\uC0AC\uC6A9");
    	panel_goods_detail.add(checkBox_Detail_Stock, "cell 6 3,alignx center,aligny center");
    	
    	JLabel label_Detail_PurPri = new JLabel("\uB9E4\uC785\uAC00");
    	label_Detail_PurPri.setHorizontalAlignment(SwingConstants.CENTER);
    	panel_goods_detail.add(label_Detail_PurPri, "cell 1 4,alignx center,aligny center");
    	
    	text_Detail_PurPri = new JTextField();
    	text_Detail_PurPri.setEditable(false);
    	text_Detail_PurPri.setHorizontalAlignment(SwingConstants.CENTER);
    	panel_goods_detail.add(text_Detail_PurPri, "cell 2 4 2 1,growx,aligny center");
    	text_Detail_PurPri.setColumns(10);
    	
    	JLabel label_Detail_SellPri = new JLabel("\uD310\uB9E4\uAC00");
    	label_Detail_SellPri.setHorizontalAlignment(SwingConstants.CENTER);
    	panel_goods_detail.add(label_Detail_SellPri, "cell 4 4,alignx center,aligny center");
    	
    	text_Detail_SellPri = new JTextField();
    	text_Detail_SellPri.setHorizontalAlignment(SwingConstants.CENTER);
    	text_Detail_SellPri.setEditable(false);
    	panel_goods_detail.add(text_Detail_SellPri, "cell 5 4 2 1,growx");
    	text_Detail_SellPri.setColumns(10);
    	
    	JLabel label_Detail_Stock = new JLabel("\uD604\uC7AC\uACE0");
    	label_Detail_Stock.setHorizontalAlignment(SwingConstants.CENTER);
    	panel_goods_detail.add(label_Detail_Stock, "cell 1 5,alignx center,aligny center");
    	
    	text_Detail_Stock = new JTextField();
    	text_Detail_Stock.setHorizontalAlignment(SwingConstants.CENTER);
    	text_Detail_Stock.setEditable(false);
    	panel_goods_detail.add(text_Detail_Stock, "cell 2 5 2 1,growx,aligny center");
    	text_Detail_Stock.setColumns(10);
    	
    	JLabel label_Detail_AnStock = new JLabel("\uC548\uC804\uC7AC\uACE0");
    	label_Detail_AnStock.setHorizontalAlignment(SwingConstants.CENTER);
    	panel_goods_detail.add(label_Detail_AnStock, "cell 4 5,alignx center,aligny center");
    	
    	text_Detail_AnStock = new JTextField();
    	text_Detail_AnStock.setHorizontalAlignment(SwingConstants.CENTER);
    	panel_goods_detail.add(text_Detail_AnStock, "cell 5 5 2 1,alignx center,aligny center");
    	text_Detail_AnStock.setColumns(10);
    	
    	JSeparator separator = new JSeparator();
    	panel_goods_detail.add(separator, "cell 1 7 7 1,growx,aligny top");
    	
    	JLabel label_Detail_ShopConnectUse = new JLabel("\uC1FC\uD551\uBAB0\uC5F0\uB3D9");
    	label_Detail_ShopConnectUse.setToolTipText("<html>\r\n\uD604\uC7AC \uC0C1\uD488\uC744 \uC1FC\uD551\uBAB0\uACFC \uC5F0\uB3D9\uC5EC\uBD80\uB97C \uC120\uD0DD \uD569\uB2C8\uB2E4.<br>\r\n\uD604\uC7AC \uC0C1\uD488\uACFC \uC5F0\uB3D9\uC911\uC5D0 \uC774 \uC635\uC158\uC744 \uBCC0\uACBD\uD558\uBA74 \uC1FC\uD551\uBAB0 \uC0C1\uD488\uC774 \uC9C4\uC5F4\uC554\uD568\uC73C\uB85C \uBCC0\uACBD \uB429\uB2C8\uB2E4.\r\n</html>");
    	label_Detail_ShopConnectUse.setHorizontalAlignment(SwingConstants.CENTER);
    	panel_goods_detail.add(label_Detail_ShopConnectUse, "cell 1 9 2 1,alignx center,aligny center");
    	
    	combo_Detail_ShopConnectUse = new JComboBox<String>();
    	combo_Detail_ShopConnectUse.setModel(new DefaultComboBoxModel<String>(new String[] {"\uC5F0\uB3D9\uD568", "\uC5F0\uB3D9\uC548\uD568"}));
    	combo_Detail_ShopConnectUse.setMaximumRowCount(2);
    	combo_Detail_ShopConnectUse.setToolTipText("<html>\r\n\uD604\uC7AC \uC0C1\uD488\uC744 \uC1FC\uD551\uBAB0\uACFC \uC5F0\uB3D9\uC5EC\uBD80\uB97C \uC120\uD0DD \uD569\uB2C8\uB2E4.<br>\r\n\uD604\uC7AC \uC0C1\uD488\uACFC \uC5F0\uB3D9\uC911\uC5D0 \uC774 \uC635\uC158\uC744 \uBCC0\uACBD\uD558\uBA74 \uC1FC\uD551\uBAB0 \uC0C1\uD488\uC774 \uC9C4\uC5F4\uC554\uD568\uC73C\uB85C \uBCC0\uACBD \uB429\uB2C8\uB2E4.\r\n</html>");
    	panel_goods_detail.add(combo_Detail_ShopConnectUse, "cell 3 9,growx,aligny center");
    	
    	btnToggle_Detail_ConnectState = new JToggleButton("\uC5F0\uB3D9\uC0C1\uD0DC");
    	btnToggle_Detail_ConnectState.setEnabled(false);
    	btnToggle_Detail_ConnectState.setToolTipText("\uD604\uC7AC \uC0C1\uD488\uC774 \uC1FC\uD551\uBAB0\uACFC \uC5F0\uB3D9\uC911\uC778\uC9C0 \uC544\uB2CC\uC9C0 \uC0C1\uD0DC\uB97C \uD45C\uC2DC\uD569\uB2C8\uB2E4.");
    	panel_goods_detail.add(btnToggle_Detail_ConnectState, "cell 6 9,growx,aligny center");
    	
    	JLabel label_Detail_View = new JLabel("\uC9C4\uC5F4\uC5EC\uBD80");
    	label_Detail_View.setToolTipText("<html>\r\n\uBA54\uC778\uD398\uC774\uC9C0 \uBC0F \uCE74\uD14C\uACE0\uB9AC\uC5D0 \uC0C1\uD488\uC744 \uD45C\uC2DC \uD560\uC9C0 \uC5EC\uBD80\uB97C \uD655\uC778\uD569\uB2C8\uB2E4.<br>\r\n\uC9C4\uC5F4\uD568 = \uC9C4\uC5F4\uB428  / \uC9C4\uC5F4\uC554\uD568 = \uC1FC\uD551\uBAB0\uC5D0 \uD45C\uC2DC\uB418\uC9C0 \uC54A\uC74C\r\n</html>");
    	label_Detail_View.setHorizontalAlignment(SwingConstants.CENTER);
    	panel_goods_detail.add(label_Detail_View, "cell 1 10 2 1,alignx center,aligny center");
    	
    	combo_Detail_View = new JComboBox<String>();
    	combo_Detail_View.setToolTipText("<html>\r\n\uBA54\uC778\uD398\uC774\uC9C0 \uBC0F \uCE74\uD14C\uACE0\uB9AC\uC5D0 \uC0C1\uD488\uC744 \uD45C\uC2DC \uD560\uC9C0 \uC5EC\uBD80\uB97C \uD655\uC778\uD569\uB2C8\uB2E4.<br>\r\n\uC9C4\uC5F4\uD568 = \uC9C4\uC5F4\uB428  / \uC9C4\uC5F4\uC554\uD568 = \uC1FC\uD551\uBAB0\uC5D0 \uD45C\uC2DC\uB418\uC9C0 \uC54A\uC74C\r\n</html>");
    	combo_Detail_View.setModel(new DefaultComboBoxModel<String>(new String[] {"\uC9C4\uC5F4\uD568", "\uC9C4\uC5F4\uC548\uD568"}));
    	combo_Detail_View.setMaximumRowCount(2);
    	panel_goods_detail.add(combo_Detail_View, "cell 3 10,growx,aligny center");
    	
    	JLabel label_maincode = new JLabel("\uC0C1\uD488\uC9C4\uC5F4\uC704\uCE58");
    	label_maincode.setHorizontalAlignment(SwingConstants.CENTER);
    	panel_goods_detail.add(label_maincode, "cell 1 11 2 1,alignx center,aligny center");
    	
    	label_default_maincode = new JLabel("\uCD9C\uB825\uC548\uD568");    	
    	panel_goods_detail.add(label_default_maincode, "flowx,cell 3 11 2 1,aligny center");
    	
    	Dimension size = new Dimension();
    	size.setSize(170, 30);
    	label_default_maincode.setMaximumSize(size);
    	
    	JButton btn_default_maincode = new JButton("\uC704\uCE58\uC120\uD0DD");
    	btn_default_maincode.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			//�����ڵ� ��� ����Ʈ�� �ۼ��մϴ�.
    			maincode_default_setList(); 	
    		}
    	});
    	panel_goods_detail.add(btn_default_maincode, "cell 6 11,growx,aligny center");
    	
    	JLabel label_Detail_ImageConnectUse = new JLabel("\uC774\uBBF8\uC9C0\uC5F0\uB3D9");
    	label_Detail_ImageConnectUse.setToolTipText("<html>\r\n\uC0C1\uD488\uC758 \uC774\uBBF8\uC9C0\uB97C \uACF5\uC6A9\uC774\uBBF8\uC9C0 \uD3F4\uB354\uC5D0\uC11C \uAC00\uC838\uB2E4 \uC0AC\uC6A9\uD569\uB2C8\uB2E4.<br>\r\n\uB2E8\uB3C5\uD3F4\uB354\uB85C \uC120\uD0DD\uC2DC \uD574\uB2F9 \uB9E4\uC7A5\uC758 \uB2E8\uB3C5 \uD3F4\uB354\uC758 \uC9C0\uC815 \uD30C\uC77C\uC744 \uC120\uD0DD\uD558\uC5EC \uC0AC\uC6A9\uD558\uC2E4\uC218 \uC788\uC2B5\uB2C8\uB2E4.\r\n</html>");
    	label_Detail_ImageConnectUse.setHorizontalAlignment(SwingConstants.CENTER);
    	panel_goods_detail.add(label_Detail_ImageConnectUse, "cell 1 12 2 1,alignx center,aligny center");
    	
    	combox_Detail_ImageConnectUse = new JComboBox<String>();
    	combox_Detail_ImageConnectUse.setEnabled(false);
    	combox_Detail_ImageConnectUse.setToolTipText("<html>\r\n\uC0C1\uD488\uC758 \uC774\uBBF8\uC9C0\uB97C \uACF5\uC6A9\uC774\uBBF8\uC9C0 \uD3F4\uB354\uC5D0\uC11C \uAC00\uC838\uB2E4 \uC0AC\uC6A9\uD569\uB2C8\uB2E4.<br>\r\n\uB2E8\uB3C5\uD3F4\uB354\uB85C \uC120\uD0DD\uC2DC \uD574\uB2F9 \uB9E4\uC7A5\uC758 \uB2E8\uB3C5 \uD3F4\uB354\uC758 \uC9C0\uC815 \uD30C\uC77C\uC744 \uC120\uD0DD\uD558\uC5EC \uC0AC\uC6A9\uD558\uC2E4\uC218 \uC788\uC2B5\uB2C8\uB2E4.\r\n</html>");
    	combox_Detail_ImageConnectUse.setModel(new DefaultComboBoxModel<String>(new String[] {"\uB2E8\uB3C5\uD3F4\uB354", "\uACF5\uC6A9\uD3F4\uB354"}));
    	combox_Detail_ImageConnectUse.setMaximumRowCount(2);
    	panel_goods_detail.add(combox_Detail_ImageConnectUse, "cell 3 12,growx,aligny center");
    	
    	JLabel label_imagename = new JLabel("\uC774\uBBF8\uC9C0\uBA85");
    	label_imagename.setHorizontalAlignment(SwingConstants.CENTER);
    	panel_goods_detail.add(label_imagename, "cell 1 13 2 1,alignx center,aligny center");
    	
    	text_imagename = new JTextField();
    	panel_goods_detail.add(text_imagename, "cell 3 13 2 1,growx,aligny center");
    	text_imagename.setColumns(10);
    	
    	cb_gname_search = new JCheckBox("");
    	cb_gname_search.setToolTipText("<html>\r\n\uCCB4\uD06C \uC2DC \uC0C1\uD488\uBA85\uC73C\uB85C \uC774\uBBF8\uC9C0\uB97C \uAC80\uC0C9 \uD569\uB2C8\uB2E4.\r\n</html>");
    	panel_goods_detail.add(cb_gname_search, "cell 5 13");
    	
    	JButton btn_Detail_ImageSearch = new JButton("\uAC80\uC0C9");
    	btn_Detail_ImageSearch.setToolTipText("<html>\uC9C0\uC815\uD55C \uD3F4\uB354\uC5D0\uC11C \uC774\uBBF8\uC9C0\uB97C \uAC80\uC0C9 \uD569\uB2C8\uB2E4.<br>\r\n\uB2E8\uB3C5\uD3F4\uB354 : \uD574\uB2F9 \uB9E4\uC7A5\uC758 \uD3F4\uB354\uC5D0\uC11C \uC774\uBBF8\uC9C0\uB97C \uC120\uD0DD \uD560\uC218\uC788\uC2B5\uB2C8\uB2E4.<br>\r\n\uACF5\uC6A9\uD3F4\uB354 : \uAC19\uC740 \uBC14\uCF54\uB4DC\uC758 \uC774\uBBF8\uC9C0\uB97C \uBD88\uB7EC\uC635\uB2C8\uB2E4.<br>\r\n</html>");
    	panel_goods_detail.add(btn_Detail_ImageSearch, "cell 6 13,growx,aligny center");
    	btn_Detail_ImageSearch.addActionListener(new ActionListener() {
    		
    		@Override
    		public void actionPerformed(ActionEvent e) {
    			// TODO Auto-generated method stub
    			//�̹��� �ҷ�����
    			getImageData();	
    		}
    	});
    	
    	JLabel label_Detail_ImagePath = new JLabel("\uC774\uBBF8\uC9C0\uACBD\uB85C");
    	label_Detail_ImagePath.setHorizontalAlignment(SwingConstants.CENTER);
    	panel_goods_detail.add(label_Detail_ImagePath, "cell 1 14 2 1,alignx center,aligny center");
    	
    	text_Detail_ImagePath = new JTextField();
    	panel_goods_detail.add(text_Detail_ImagePath, "cell 3 14 3 1,growx,aligny center");
    	text_Detail_ImagePath.setColumns(10);
    	text_Detail_ImagePath.setEditable(false);
    	
    	JButton btn_ftpimage_delete = new JButton("\uC774\uBBF8\uC9C0\uC0AD\uC81C");
    	btn_ftpimage_delete.setActionCommand("FTP_ImageDelete");    	
    	btn_ftpimage_delete.addActionListener(this);
    	panel_goods_detail.add(btn_ftpimage_delete, "cell 6 14,alignx center,aligny center");
    	
    	JSeparator separator_1 = new JSeparator();
    	panel_goods_detail.add(separator_1, "cell 1 16 7 1,growx,aligny top");
    	
    	label_image_view = new JLabel();
    	label_image_view.setBackground(Color.WHITE);
    	label_image_view.setHorizontalAlignment(SwingConstants.CENTER);
    	label_image_view.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
    	panel_goods_detail.add(label_image_view, "cell 1 18 3 1,grow");
    	JButton btn_Detail_Save = new JButton("\uC800\uC7A5");
    	btn_Detail_Save.setForeground(Color.RED);
    	btn_Detail_Save.setBackground(Color.BLUE);
    	btn_Detail_Save.setFont(new Font("���� ���", Font.BOLD, 15));
    	panel_goods_detail.add(btn_Detail_Save, "cell 6 18,growx,aligny center");
    	
    	JPanel panel_jtaballchange = new JPanel();
    	panel_jtaballchange.setBorder(new LineBorder(Color.GRAY));
    	panel_jtaballchange.setLayout(new MigLayout("", "[grow][grow]", "[grow][grow][grow][grow][100px,grow][grow][]"));
    	
    	//TODO: ���θ� �����׷�
    	JPanel panel_5 = new JPanel();
    	FlowLayout flowLayout = (FlowLayout) panel_5.getLayout();
    	flowLayout.setVgap(0);
    	flowLayout.setHgap(0);
    	panel_5.setBorder(new TitledBorder(null, "\uC1FC\uD551\uBAB0\uC5F0\uB3D9", TitledBorder.LEADING, TitledBorder.TOP, null, null));
    	panel_jtaballchange.add(panel_5, "cell 0 0 2 1,grow");
    	
    	JRadioButton radio_shopmall = new JRadioButton("\uC120\uD0DD\uC548\uD568");
    	panel_5.add(radio_shopmall);
    	radio_shopmall.setSelected(true);
    	radio_shopmall.setActionCommand("\uC120\uD0DD\uC548\uD568");
    	    	
    	JRadioButton radio_shoppingmall_on = new JRadioButton("\uC5F0\uB3D9\uD568");
    	panel_5.add(radio_shoppingmall_on);
    	radio_shoppingmall_on.setActionCommand("\uC5F0\uB3D9\uD568");
    	
    	JRadioButton radio_shoppingmall_off = new JRadioButton("\uC5F0\uB3D9\uC548\uD568");
    	panel_5.add(radio_shoppingmall_off);
    	radio_shoppingmall_off.setActionCommand("\uC5F0\uB3D9\uC548\uD568");
    	
    	radio_shopmall.setActionCommand("���þ���");
    	radio_shoppingmall_on.setActionCommand("������");
    	radio_shoppingmall_off.setActionCommand("��������");
    	
    	bg_shoppingmall[0].add(radio_shopmall);
		bg_shoppingmall[0].add(radio_shoppingmall_off);
		bg_shoppingmall[0].add(radio_shoppingmall_on);
    			
		//TODO: �������� �׷�
    	JPanel panel_6 = new JPanel();
    	FlowLayout flowLayout_1 = (FlowLayout) panel_6.getLayout();
    	flowLayout_1.setVgap(0);
    	flowLayout_1.setHgap(0);
    	panel_6.setBorder(new TitledBorder(null, "\uC1FC\uD551\uBAB0\uD310\uB9E4\uC5EC\uBD80", TitledBorder.LEADING, TitledBorder.TOP, null, null));
    	panel_jtaballchange.add(panel_6, "cell 0 1 2 1,grow");
    	
    	JRadioButton radio_view = new JRadioButton("\uC120\uD0DD\uC548\uD568");
    	panel_6.add(radio_view);
    	radio_view.setSelected(true);
    	radio_view.setActionCommand("\uC120\uD0DD\uC548\uD568");
    	
    	JRadioButton radio_view_on = new JRadioButton("\uC9C4\uC5F4\uD568");
    	panel_6.add(radio_view_on);
    	radio_view_on.setActionCommand("\uC9C4\uC5F4\uD568");
    	
    	JRadioButton radio_view_off = new JRadioButton("\uC9C4\uC5F4\uC548\uD568");
    	panel_6.add(radio_view_off);
    	radio_view_off.setActionCommand("\uC9C4\uC5F4\uC548\uD568");
    	
    	radio_view.setActionCommand("���þ���");
		radio_view_on.setActionCommand("������");
		radio_view_off.setActionCommand("��������");
		
		bg_shoppingmall[1].add(radio_view);
		bg_shoppingmall[1].add(radio_view_on);
		bg_shoppingmall[1].add(radio_view_off);
    	
		//TODO: ����� ���� �׷�
    	JPanel panel_7 = new JPanel();
    	FlowLayout flowLayout_2 = (FlowLayout) panel_7.getLayout();
    	flowLayout_2.setVgap(0);
    	flowLayout_2.setHgap(0);
    	panel_7.setBorder(new TitledBorder(null, "\uC7AC\uACE0\uC0AC\uC6A9\uC5EC\uBD80", TitledBorder.LEADING, TitledBorder.TOP, null, null));
    	panel_jtaballchange.add(panel_7, "cell 0 2 2 1,grow");
    	
    	JRadioButton radio_stock = new JRadioButton("\uC120\uD0DD\uC548\uD568");
    	panel_7.add(radio_stock);
    	radio_stock.setSelected(true);
    	radio_stock.setActionCommand("\uC120\uD0DD\uC548\uD568");
    	
    	JRadioButton radio_stock_on = new JRadioButton("\uC0AC\uC6A9\uD568");
    	panel_7.add(radio_stock_on);
    	radio_stock_on.setActionCommand("\uC0AC\uC6A9\uD568");
    	
    	JRadioButton radio_stock_off = new JRadioButton("\uC0AC\uC6A9\uC548\uD568");
    	panel_7.add(radio_stock_off);
    	radio_stock_off.setActionCommand("\uC0AC\uC6A9\uC548\uD568");
    	
    	radio_stock.setActionCommand("���þ���");
		radio_stock_on.setActionCommand("�����");
		radio_stock_off.setActionCommand("������");
		
		bg_shoppingmall[3].add(radio_stock);
		bg_shoppingmall[3].add(radio_stock_off);
		bg_shoppingmall[3].add(radio_stock_on);
    	
    	JPanel panel_8 = new JPanel();
    	FlowLayout flowLayout_3 = (FlowLayout) panel_8.getLayout();
    	flowLayout_3.setVgap(0);
    	flowLayout_3.setHgap(0);
    	panel_8.setBorder(new TitledBorder(null, "\uC548\uC804\uC7AC\uACE0\uB4F1\uB85D", TitledBorder.LEADING, TitledBorder.TOP, null, null));
    	panel_jtaballchange.add(panel_8, "cell 0 3 2 1,grow");
    	
    	JLabel label = new JLabel("\uC218\uB7C9\uB4F1\uB85D  :  ");
    	panel_8.add(label);
    	
    	text_anstock = new JTextField();
    	panel_8.add(text_anstock);
    	text_anstock.setHorizontalAlignment(SwingConstants.CENTER);
    	text_anstock.setColumns(5);
    	
    	panel_maincode_title = new JPanel();
    	panel_maincode_title.setBorder(new TitledBorder(null, "\uBA54\uC778\uC0C1\uD488 \uCD9C\uB825\uCF54\uB4DC", TitledBorder.LEADING, TitledBorder.TOP, null, null));
    	panel_jtaballchange.add(panel_maincode_title, "cell 0 4 2 1,grow");
    	    	
    	chk_boxs = new ArrayList<JCheckBox>();
    	
    	getMainCode();
    	
    	btn_Change = new JButton("\uC120\uD0DD \uC635\uC158 \uBCC0\uACBD");
    	btn_Change.setForeground(Color.RED);
    	btn_Change.setFont(new Font("���� ���", Font.BOLD, 13));
    	btn_Change.setBackground(Color.BLUE);
    	panel_jtaballchange.add(btn_Change, "cell 0 5,grow");
    	btn_Change.setActionCommand("OK");
    	btn_Change.addActionListener(this);
    	
    	JLabel label_right_info = new JLabel("<html>\r\n\uBCC0\uACBD \uB0B4\uC6A9\uC740 \uC1FC\uD551\uBAB0\uC5D0 \uC989\uC2DC \uC801\uC6A9\uB418\uC9C0 \uC54A\uACE0 <br>\r\n\uC0C1\uD488\uC218\uB7C9\uC5D0 \uB530\uB77C 5~10\uBD84\uC774 \uC18C\uC694\uB429\uB2C8\uB2E4.\r\n</html>");
    	label_right_info.setForeground(SystemColor.activeCaptionText);
    	label_right_info.setHorizontalAlignment(SwingConstants.CENTER);
    	label_right_info.setBackground(SystemColor.info);
    	label_right_info.setOpaque(true);
    	label_right_info.setFont(new Font("���� ���", Font.PLAIN, 11));
    	panel_jtaballchange.add(label_right_info, "cell 1 5,grow");
    	
    	//�������� ���� �׷�
    	JPanel panel_3 = new JPanel();
    	panel_3.setBackground(SystemColor.window);
    	panel_3.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\uC774\uBBF8\uC9C0 \uC5F0\uB3D9", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
    	panel_jtaballchange.add(panel_3, "cell 0 6 2 1,grow");
    	panel_3.setLayout(new MigLayout("", "[grow][][100px]", "[][][15px][16px]"));
    	
    	JPanel panel_9 = new JPanel();
    	FlowLayout flowLayout_4 = (FlowLayout) panel_9.getLayout();
    	flowLayout_4.setVgap(0);
    	flowLayout_4.setHgap(0);
    	panel_3.add(panel_9, "cell 0 1 3 1,growx");
    	panel_9.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
    	panel_9.setVisible(false);
    	
    	JLabel lblNewLabel_6 = new JLabel("\uC800\uC7A5 \uD3F4\uB354 \uC635\uC158");
    	panel_9.add(lblNewLabel_6);
    	
    	JRadioButton radio_image = new JRadioButton("\uC120\uD0DD\uC548\uD568");
    	panel_9.add(radio_image);
    	radio_image.setSelected(true);
    	radio_image.setActionCommand("\uC120\uD0DD\uC548\uD568");
    	
    	JRadioButton radio_dandock = new JRadioButton("\uB2E8\uB3C5\uD3F4\uB354");
    	panel_9.add(radio_dandock);
    	radio_dandock.setActionCommand("\uB2E8\uB3C5\uD3F4\uB354");
    	
    	JRadioButton radio_gong = new JRadioButton("\uACF5\uC6A9\uD3F4\uB354");
    	panel_9.add(radio_gong);
    	radio_gong.setActionCommand("\uACF5\uC6A9\uD3F4\uB354");
    	
    	radio_image.setActionCommand("���þ���");
		radio_dandock.setActionCommand("�ܵ�����");
		radio_gong.setActionCommand("��������");
				
		bg_shoppingmall[2].add(radio_image);
		bg_shoppingmall[2].add(radio_dandock);
		bg_shoppingmall[2].add(radio_gong);
    	
    	
    	btn_imagecall = new JButton("\uC5F0\uB3D9\uC2DC\uC791");
    	panel_3.add(btn_imagecall, "cell 2 2 1 2,grow");
    	btn_imagecall.addActionListener(this);
    	btn_imagecall.setActionCommand("Image");
    	
    	progressbar = new JProgressBar(0, 1000);
    	progressbar.setStringPainted(true);
    	panel_3.add(progressbar, "cell 0 3,growx,aligny top");
    	
    	label_result = new JLabel("\uCD1D   \uAC74");
    	label_result.setHorizontalAlignment(SwingConstants.RIGHT);
    	label_result.setFont(new Font("���� ���", Font.PLAIN, 11));
    	panel_3.add(label_result, "cell 0 2,growx,aligny top");
    	
    	btn_Detail_Save.addActionListener(new ActionListener() {
    		
    		@Override
    		public void actionPerformed(ActionEvent e) {
    			// TODO Auto-generated method stub
    			saveGoodsInfo();
    		}
    	});
    	
    	DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
    	celAlignCenter.setHorizontalAlignment(JLabel.CENTER);
    	
    	DefaultTableCellRenderer celAlignRight = new DefaultTableCellRenderer();
    	celAlignRight.setHorizontalAlignment(JLabel.RIGHT);
		
    	//table = new JTable(dtm);
    	table = new JTable(dtm);
    	
		//table.setBounds(12, 138, 693, 304);
		
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);  //���� ��ũ��
		
		table.getTableHeader().setReorderingAllowed(false);  //�̵��Ұ�
		
		//������ ���ý� �̻��Ѱ� �� �ö� ���� �ϱ�
		/*ListSelectionModel rowSM = table.getSelectionModel();
		  rowSM.addListSelectionListener(new ListSelectionListener(){
		    public void valueChanged(ListSelectionEvent e) {
		      ListSelectionModel lsm = (ListSelectionModel)e.getSource();
		        if(e.getValueIsAdjusting()){
		          if(!lsm.isSelectionEmpty()){ // index�� ���� �Ǿ�����
		        	  temp_detail  = (Vector) dtm.getDataVector().get(lsm.getMinSelectionIndex());
		        	  // sort�� ���̺��� �ε��� ã��.		                         
		        	  // selectedRow = lsm.getMinSelectionIndex();		 
		        }
		      }
		    }
		  });   //		*/
	
		table.setAutoCreateRowSorter(true);
		TableRowSorter<TableModel> tsorter = new TableRowSorter<TableModel>(table.getModel());
		table.setRowSorter(tsorter);	
		
		table.addMouseListener(new MouseListener() {
			
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
				if(e.getButton() == 1){
					if(change_Flags()) return;
					//��ǰ�� �������� �����ϴ�.
					switch(tabPane_detail.getSelectedIndex()){
					case 0: //��ǰ������
						setGoodsDetail();
						break;
					case 1: //�̹��� ����	
						if(!chkbox_listSearchNot.isSelected()){
							setSearchGoodsName();
						}
						break;
					case 2: //�ǸŸ޼���
						if(btn_jtab_hotkey_listcall.isEnabled()){
							//����� ���ڵ带 ã�Ƽ� ������ ������ ��ǰ�� �����մϴ�.
							//��ǰ ���� �Լ�
							setHotKeyListSave();
						}
						break;
					case 3: //�ϰ�����						
						break;
					case 4: //
						break;
					case 5:
						break;
					default:
						JOptionPane.showMessageDialog(Goods_Manage.this, "���� ������ �ּ���!!");
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
		
		table.setVisible(true);
		
		table.getColumn("����").setPreferredWidth(40);
		table.getColumn("����").setCellRenderer(celAlignCenter);
		
		table.getColumn("���ڵ�").setPreferredWidth(110);
		table.getColumn("��ǰ��").setPreferredWidth(180);
		table.getColumn("�԰�").setPreferredWidth(40);
		table.getColumn("�԰�").setCellRenderer(celAlignCenter);
		
		//String[] colunm = {"0. ����", "1. ���ڵ�", "2. ��ǰ��", "3. �԰�", "4. ���԰�", "5. �ǸŰ�", "6. �����", "7. �������", 
		//"8. �з��ڵ�", "9. ���ڵ�", "10. ���", "11. ���ڵ�", "12. �߸�", "13. ���ڵ�", "14. �Ҹ�", "15. ���", "16. ��ǰ����", "17. ��������", "18. ��������", "19. �����", 
		//"20. �������", "21. �̹�������", "22. �̹������" , "23. �̹�����", "24. �����ǰ"};
		
		table.getColumn("���԰�").setPreferredWidth(60);
		table.getColumn("���԰�").setCellRenderer(celAlignRight);
		
		table.getColumn("�ǸŰ�").setPreferredWidth(60);
		table.getColumn("�ǸŰ�").setCellRenderer(celAlignRight);
		
		table.getColumn("�����").setPreferredWidth(60);
		table.getColumn("�����").setCellRenderer(celAlignCenter);
		
		table.getColumn("�������").setWidth(0);
		table.getColumn("�������").setMinWidth(0);
		table.getColumn("�������").setMaxWidth(0);		
		table.getColumnModel().getColumn(7).setResizable(false); //�������
		
		table.getColumn("�з��ڵ�").setPreferredWidth(60);
		table.getColumn("�з��ڵ�").setCellRenderer(celAlignCenter);
		
		table.getColumn("���ڵ�").setWidth(0);
		table.getColumn("���ڵ�").setMinWidth(0);
		table.getColumn("���ڵ�").setMaxWidth(0);		
		table.getColumnModel().getColumn(9).setResizable(false); //���ڵ�
		
		table.getColumn("���").setWidth(0);
		table.getColumn("���").setMinWidth(0);
		table.getColumn("���").setMaxWidth(0);		
		table.getColumnModel().getColumn(10).setResizable(false); //���
		
		table.getColumn("���ڵ�").setWidth(0);
		table.getColumn("���ڵ�").setMinWidth(0);
		table.getColumn("���ڵ�").setMaxWidth(0);		
		table.getColumnModel().getColumn(11).setResizable(false); //���ڵ�
		
		table.getColumn("�߸�").setWidth(0);
		table.getColumn("�߸�").setMinWidth(0);
		table.getColumn("�߸�").setMaxWidth(0);		
		table.getColumnModel().getColumn(12).setResizable(false); //�߸�
		
		table.getColumn("���ڵ�").setWidth(0);
		table.getColumn("���ڵ�").setMinWidth(0);
		table.getColumn("���ڵ�").setMaxWidth(0);		
		table.getColumnModel().getColumn(13).setResizable(false); //���ڵ�
		
		table.getColumn("�Ҹ�").setWidth(0);				
		table.getColumn("�Ҹ�").setMinWidth(0);
		table.getColumn("�Ҹ�").setMaxWidth(0);		
		table.getColumnModel().getColumn(14).setResizable(false); //�Ҹ�
		
		table.getColumn("���").setCellRenderer(celAlignCenter);								
		table.getColumn("��ǰ����").setCellRenderer(celAlignCenter);
		table.getColumn("���θ�").setCellRenderer(celAlignCenter);
		
		table.getColumn("��������").setWidth(0);
		table.getColumn("��������").setMinWidth(0);
		table.getColumn("��������").setMaxWidth(0);		
		table.getColumnModel().getColumn(17).setResizable(false); //��������
		
		table.getColumn("�����").setWidth(0);
		table.getColumn("�����").setMinWidth(0);
		table.getColumn("�����").setMaxWidth(0);		
		table.getColumnModel().getColumn(18).setResizable(false); //�����
		
		table.getColumn("�������").setCellRenderer(celAlignCenter);
		
		table.getColumn("�̹�������").setCellRenderer(celAlignCenter);								
		table.getColumn("�̹������").setPreferredWidth(300);
		
		table.setRowHeight(25);
		
		table.getColumn("�̹�����").setCellRenderer(celAlignCenter);
		
		table.getColumn("�����ǰ").setWidth(0);
		table.getColumn("�����ǰ").setMinWidth(0);
		table.getColumn("�����ǰ").setMaxWidth(0);		
		
		
		final JScrollPane scrollPane = new JScrollPane(table);
		panel_1.add(scrollPane, BorderLayout.CENTER);
		
		
		//JPanel jtab_hotkey_sell = new JPanel();
		JPanel jtab_hotkey_sell = new JPanel(){
			public Dimension getPreferredSize() {
		      return new Dimension(100, 80);
		      };
		};
		jtab_hotkey_sell.setBorder(new LineBorder(Color.GRAY));
		
		
		jtab_hotkey_sell.setLayout(new MigLayout("", "[grow][][][][grow][grow]", "[][][][][25px][][][grow][]"));
		
		JLabel label_jtap_hotkey_search = new JLabel("\uAC80\uC0C9\uC5B4");
		jtab_hotkey_sell.add(label_jtap_hotkey_search, "cell 0 1,alignx center,aligny center");
		
		text_jtab_hotkey = new JTextField();
		jtab_hotkey_sell.add(text_jtab_hotkey, "cell 1 1 4 1,grow");
		text_jtab_hotkey.setColumns(10);
		
		JButton btn_jtab_hotkey_search = new JButton("\uAC80\uC0C9");
		jtab_hotkey_sell.add(btn_jtab_hotkey_search, "cell 5 1,growx,aligny center");
		btn_jtab_hotkey_search.setActionCommand("HotKey_Search");
		btn_jtab_hotkey_search.addActionListener(this);
		
		JLabel label_jtab_hotkey_map = new JLabel("\uC800\uC7A5\uC18C");
		jtab_hotkey_sell.add(label_jtab_hotkey_map, "cell 0 3,alignx center");
		
		JButton btn_jtab_hotkey_mapdown = new JButton("<");
		jtab_hotkey_sell.add(btn_jtab_hotkey_mapdown, "cell 1 3");
		btn_jtab_hotkey_mapdown.setActionCommand("HotKey_Down");
		btn_jtab_hotkey_mapdown.addActionListener(this);
		
		cb_jtab_hotkey_map = new JComboBox<String>();
		jtab_hotkey_sell.add(cb_jtab_hotkey_map, "cell 2 3,growx");		
		cb_jtab_hotkey_map.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				if(cb_jtab_hotkey_map.getSelectedIndex() == 0){
					btn_jtab_hotkey_listcall.setEnabled(false);
					btn_jtab_hotkey_listdown.setEnabled(false);
					btn_jtab_hotkey_listup.setEnabled(false);	
					btn_jtab_hotkey_save.setEnabled(false);
				}else{
					btn_jtab_hotkey_listcall.setEnabled(true);
					btn_jtab_hotkey_listdown.setEnabled(true);
					btn_jtab_hotkey_listup.setEnabled(true);
					btn_jtab_hotkey_save.setEnabled(true);
				}
				getHotKeyGoodsList();
			}
		});
		
		JButton btn_jtab_hotkey_mapup = new JButton(">");
		jtab_hotkey_sell.add(btn_jtab_hotkey_mapup, "cell 3 3");
		btn_jtab_hotkey_mapup.setActionCommand("HotKey_Up");
		btn_jtab_hotkey_mapup.addActionListener(this);
		
		JButton btn_jtab_hotkey_group = new JButton("\uAD00\uB9AC");
		jtab_hotkey_sell.add(btn_jtab_hotkey_group, "cell 4 3,alignx center");
		btn_jtab_hotkey_group.setActionCommand("HotKey_Group");
		btn_jtab_hotkey_group.addActionListener(this);
		
		cb_jtab_hotkey_gubun = new JComboBox<String>();
		cb_jtab_hotkey_gubun.setModel(new DefaultComboBoxModel<String>(new String[] {"\uC804\uCCB4", "\uC800\uC6B8\uC0C1\uD488", "\uBD80\uBD84\uC0C1\uD488"}));
		jtab_hotkey_sell.add(cb_jtab_hotkey_gubun, "cell 5 3,growx");
		
		cb_jtab_hotkey_gubun.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				getHotKeyGoodsList();
			}
		});
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setForeground(Color.GRAY);
		jtab_hotkey_sell.add(separator_4, "cell 0 4 6 1,growx,aligny center");
		
		btn_jtab_hotkey_listcall = new JButton("\uCD94\uAC00");
		jtab_hotkey_sell.add(btn_jtab_hotkey_listcall, "cell 0 5,alignx center");
		btn_jtab_hotkey_listcall.setActionCommand("HotKey_ListCall");
		btn_jtab_hotkey_listcall.addActionListener(this);
		
		btn_jtab_hotkey_listdown = new JButton("��");
		jtab_hotkey_sell.add(btn_jtab_hotkey_listdown, "cell 1 5");
		btn_jtab_hotkey_listdown.setActionCommand("HotKey_ListDown");
		btn_jtab_hotkey_listdown.addActionListener(this);
		
		label_jtab_hotkey_result = new JLabel("0");
		jtab_hotkey_sell.add(label_jtab_hotkey_result, "cell 2 5,alignx center,aligny center");
		
		btn_jtab_hotkey_listup = new JButton("��");
		jtab_hotkey_sell.add(btn_jtab_hotkey_listup, "cell 3 5");
		btn_jtab_hotkey_listup.setActionCommand("HotKey_ListUp");
		btn_jtab_hotkey_listup.addActionListener(this);
		
		JButton btn_jtab_hotkey_del = new JButton("\uC0AD\uC81C");
		jtab_hotkey_sell.add(btn_jtab_hotkey_del, "cell 4 5,alignx center");
		btn_jtab_hotkey_del.setActionCommand("HotKey_Del");
		btn_jtab_hotkey_del.addActionListener(this);
		
		btn_jtab_hotkey_save = new JButton("\uC21C\uC11C\uC800\uC7A5");
		jtab_hotkey_sell.add(btn_jtab_hotkey_save, "cell 5 5,growx");
		btn_jtab_hotkey_save.setActionCommand("HotKey_Save");
		btn_jtab_hotkey_save.addActionListener(this);
		
		JPanel panel_hotkey = new JPanel();
		jtab_hotkey_sell.add(panel_hotkey, "cell 0 7 6 1,grow");
		panel_hotkey.setLayout(new BorderLayout(0, 0));
		
		final JScrollPane scrollPane_hotkey = new JScrollPane();
		panel_hotkey.add(scrollPane_hotkey);
		
		//hot_key ��ϰ��� ���
		setHotkeyTable();		
		scrollPane_hotkey.setViewportView(table_hotkey);
				
		tabPane_detail.addTab("������", panel_goods_detail);
		tabPane_detail.addTab("�̹�������", panel_jtabgoods_image);		
		
		label_count_up = new JLabel("0");
		label_count_up.setHorizontalAlignment(SwingConstants.CENTER);
		panel_jtabgoods_image.add(label_count_up, "flowx,cell 3 4,alignx left");
		
		JLabel label_count = new JLabel("/");
		label_count.setHorizontalAlignment(SwingConstants.CENTER);
		panel_jtabgoods_image.add(label_count, "cell 3 4");
		
		label_count_total = new JLabel("0");
		label_count_total.setHorizontalAlignment(SwingConstants.CENTER);
		panel_jtabgoods_image.add(label_count_total, "cell 3 4");
		tabPane_detail.addTab("1\uCC28\uC2DD\uD488 \uD310\uAC00\uBCC0\uACBD", jtab_hotkey_sell);
		tabPane_detail.addTab("\uC0C1\uD488 \uC77C\uAD04\uBCC0\uACBD", panel_jtaballchange);
		
    }
    
    private void setHotkeyTable(){
    	/**
		 * �� ���� ����
		 */
		DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
		celAlignCenter.setHorizontalAlignment(JLabel.CENTER);		
		
		String[] colunm = {"����", "���ڵ�", "��ǰ��", "������ġ", "�ǸŰ�", "�ڵ�"};
		
		dtm_hotkey = new DefaultTableModel(null, colunm){
			@Override
			public boolean isCellEditable(int roe, int column){
				if(column == 4){
					return true;
				}else{
					return false;
				}
			}
		};
		
		table_hotkey = new JTable(dtm_hotkey);
		
		table_hotkey.setRowHeight(25);		
		table_hotkey.getModel().addTableModelListener(new TableModelListener() {
			
			@Override
			public void tableChanged(TableModelEvent e) {
				// TODO Auto-generated method stub
				// -1 �ʱ�ȭ , 0 ������, 1 ����Ʈ
				switch(e.getColumn()){				
				case 4:
					System.out.println("�÷� ������");
					setHotkeySellPriceSave();										
					break;				
				}
			}
		});
		
		table_hotkey.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub				
				System.out.println(KeyEvent.VK_ENTER);
				if(e.getKeyCode() == 10){
					table_hotkey.changeSelection(table_hotkey.getSelectedRow(), 4, true, true);
				}
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
				if (e.getKeyCode() >= 48  &&	e.getKeyCode() <= 57){
					//System.out.println("���� : "+e.getKeyCode());
					table_hotkey.changeSelection(table_hotkey.getSelectedRow(), 4, true, true);
				}
				
				if(e.getKeyCode() >= 96 && e.getKeyCode() <= 105){
					//System.out.println("���� : "+e.getKeyCode());	
					table_hotkey.changeSelection(table_hotkey.getSelectedRow(), 4, true, true);
				}				
			}
		});
		
		table_hotkey.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		//table_hotkey.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);  //���� ��ũ��
		
		table_hotkey.getTableHeader().setReorderingAllowed(false);  //�̵��Ұ�		
		
		//���� ���� ������ ���� ���� ���� ���� ����
		/*table_hotkey.setAutoCreateRowSorter(true);
		TableRowSorter<TableModel> tsorter = new TableRowSorter<TableModel>(table_hotkey.getModel());
		table_hotkey.setRowSorter(tsorter);*/
				
		table_hotkey.getColumn("����").setPreferredWidth(30);
		table_hotkey.getColumn("����").setCellRenderer(celAlignCenter);
		
		table_hotkey.getColumn("���ڵ�").setPreferredWidth(50);
		table_hotkey.getColumn("��ǰ��").setPreferredWidth(120);
		table_hotkey.getColumn("������ġ").setPreferredWidth(40);
		table_hotkey.getColumn("������ġ").setCellRenderer(celAlignCenter);
		
		table_hotkey.getColumn("�ǸŰ�").setPreferredWidth(40);
		//table_hotkey.getColumn("�ǸŰ�").setCellRenderer(new HotkeyTableCellRenderer());
		table_hotkey.getColumn("�ǸŰ�").setCellRenderer(new HotkeyTableCellRenderer());
		
		table_hotkey.getColumn("�ڵ�").setWidth(0);
		table_hotkey.getColumn("�ڵ�").setMinWidth(0);
		table_hotkey.getColumn("�ڵ�").setMaxWidth(0);	
				
		dcbm_hotkey_group = new DefaultComboBoxModel<String>();
		
		//����Ű ��ȣ �Դϴ�. �⺻�� ��ü �Դϴ�.
		cb_jtab_hotkey_map.setModel(dcbm_hotkey_group);
		
		//��Ű�� �����մϴ�.
		setHotKeyMakeMap();
		
		
    }
    
    //��Ű �޺��ڽ� ���
    private void setHotKeyMakeMap(){
    	
    	//char c = 'A';
		//char d = 'Z';
		//char e = 'a';
		//char f = 'z';
		//System.out.println((int)c);
		//System.out.println((int)d);
		//System.out.println((int)e);
		//System.out.println((int)f);
						
		dcbm_hotkey_group.addElement("��ü");  
    	for(int i =65; i <= 90; i++){    		
    		char a = (char)i;
    		dcbm_hotkey_group.addElement(String.valueOf(a));    		
    	}
    	for(int i =97; i <= 122; i++){    		
    		char a = (char)i;
    		dcbm_hotkey_group.addElement(String.valueOf(a));    		
    	}
    	
    	String query = "Select * from hot_key_defname";
		ms_connect.setMainSetting();
		ArrayList<HashMap<String, String>> hotkey = ms_connect.connection(query);
		
		for(HashMap<String, String> map : hotkey){	
			//System.out.println(map.get("H_CODE"));
			//System.out.println(map.get("H_NAME"));
			dcbm_hotkey_group.removeElementAt(Integer.parseInt(map.get("H_CODE"))+1);
			String name = map.get("H_NAME");
			if(name.length() > 6){				
				name = name.substring(0, 4);
			}
			
			dcbm_hotkey_group.insertElementAt(name, Integer.parseInt(map.get("H_CODE"))+1);
		}
    }
    
    //��ư ü���� �̹��� ���� ���� �� ��ư ü����
	private void btnChange(boolean change){			
		if(change){			
			btn_imagecall.setActionCommand("Cancel");
			btn_imagecall.setText("����");
			
			btn_Change.setEnabled(false);
			//btn_imagecall.setEnabled(false);
			
		}else{
			btn_imagecall.setActionCommand("Image");
			btn_imagecall.setText("��������");
			
			btn_Change.setEnabled(true);
			//btn_imagecall.setEnabled(true);			
		}			
	}
    
	//�̹��� ���� ��Ī 
	private void setFtp_Image_select(){
				
		//��ư�� �ٲߴϴ�.
		btnChange(true);
		
		thread = new Thread(){
						
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
				// ���̺��� ���õ� ���� �ε��� ��ȣ ���
				int[] row = table.getSelectedRows();
				for(int i =0; i < row.length; i++){
					row[i] = table.convertRowIndexToModel(row[i]);
				}				
				
				//int col = table.getColumnCount();
				
				//���õ� ��ǰ���� ���ڵ带 �ҷ��´� 
				//���õ� ��ǰ���� �������� ��뿩�θ� Ȯ���ϰ� ����Ѵٸ� 
				//�̹��� �������� �̹����� �ҷ��´�.		
				String temp_image = "";
				String query = "Select * From Ftp_Image where ";
				String result_text = "�� "+row.length+" ���� ";
				String result_text_com = "�� ��Ī ��";
				String gubun = bg_shoppingmall[2].getSelection().getActionCommand();
								
				//if(gubun.equals("��������")){
					query += "path_gubun='1' ";					
				//}else{
				//	query += "path_gubun='0' ";					
				//}
				
				progressbar.setValue(0);
				progressbar.setMaximum(row.length);				
				label_result.setText(result_text);
								
				ArrayList<HashMap<String, String>> query_list = new ArrayList<HashMap<String, String>>();
				int count=0;			
				int total_count = 0;
				int result_count = 0;
				//���õ� ��ǰ ���ڵ� �ҷ�����
				for(int j = 0; j < row.length; j++){
					
					Vector<Object> temp_one = new Vector<Object>();		
					
					temp_one.add(table.getModel().getValueAt(row[j], 1));		//���ڵ�
					temp_one.add(table.getModel().getValueAt(row[j], 21));       //�̹�������
					
					//System.out.println("21 : " +table.getModel().getValueAt(row[j], 21));
					//System.out.println(table.getModel().getValueAt(row[j], 22));
					//System.out.println(table.getModel().getValueAt(row[j], 23));	
					
					temp_one.add(table.getModel().getValueAt(row[j], 22));		//�̹������
					
					//"20. �������", "21. �̹�������", "22. �̹������" , "23. �̹�����", "24. �����ǰ"};					
					try{
						if(temp_one.get(1).equals(gubun.toString().trim()) && temp_one.get(2).equals(" ") || temp_one.get(2).equals("")){
							temp_image += "'"+temp_one.get(0)+"', ";	
							count++;
							total_count++;
						}
					}catch(NullPointerException e){
						temp_image += "'"+temp_one.get(0)+"', ";
						count++;
						total_count++;
						e.getMessage();
					}
					label_result.setText(result_text+total_count+"�� ��Ī�õ� "+result_count+"�� ��Ī�Ϸ�");
					
					//1000���� ��� ��Ī �մϴ�.
					if(count == 1000){
					
						temp_image = temp_image.substring(0, temp_image.length()-2);	
						query += "and barcode in ("+temp_image+")";
						
						ms_connect.setImageSetting();
						query_list = ms_connect.connection(query);
						System.out.println("�� ��Ī ���� : "+query_list.size());
						result_text_com = String.valueOf(query_list.size());
					
						temp_image = "";
					
						Iterator<HashMap<String, String>> iter = query_list.iterator();
						ArrayList<String> query_result = new ArrayList<String>();
					
						while(iter.hasNext()){					
							HashMap<String, String> map = iter.next();							
							String temp = "Update Goods_Info Set Img_Path='http://14.38.161.45:7080/main_goods/"+map.get("Barcode").trim()+"."+map.get("Ext").trim()+"', Img_Path_Use='1', Edit_Tran='1' Where Barcode='"+map.get("Barcode")+"' ";
							query_result.add(temp);
							result_count++;
							label_result.setText(result_text+total_count+"�� ��Ī�õ� "+result_count+"�� ��Ī��");							
						}
												
						ms_connect.setMainSetting();
						int a = ms_connect.connect_update(query_result);
						
						//�Ϸ��� �ʱ�ȭ
						switch(a){
						case 0:
						
							//�ʱ�ȭ
							query = "Select * From Ftp_Image where path_gubun='1' ";				
							label_result.setText(result_text+total_count+"�� ��Ī�õ� "+result_count+"�� ��Ī�Ϸ�");
							
							break;
						default:
							JOptionPane.showMessageDialog(Goods_Manage.this, "������� ���߽��ϴ�.");
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
					System.out.println("�� ��Ī ���� : "+query_list.size());
					
					Iterator<HashMap<String, String>> iter = query_list.iterator();
					ArrayList<String> query_result = new ArrayList<String>();
							
					while(iter.hasNext()){						
						HashMap<String, String> map = iter.next();
						String temp = "Update Goods_Info Set Img_Path='http://14.38.161.45:7080/"+map.get("Path").trim()+"/"+map.get("Barcode").trim()+"."+map.get("Ext").trim()+"', Img_Path_Use='1', Edit_Tran='1' Where Barcode='"+map.get("Barcode").trim()+"' ";						
						query_result.add(temp);
						result_count++;
						label_result.setText(result_text+total_count+"�� ��Ī�õ� "+result_count+"�� ��Ī��");
					}
										
					ms_connect.setMainSetting();
					int a = ms_connect.connect_update(query_result);
					
					//�Ϸ��� �ʱ�ȭ
					switch(a){
					case 0:
						//�ʱ�ȭ											
						label_result.setText(result_text+total_count+"�� ��Ī�õ� "+result_count+"�� ��Ī�Ϸ�");						
						break;
					default:
						JOptionPane.showMessageDialog(Goods_Manage.this, "������� ���߽��ϴ�.");
						this.stop();
						btnChange(false);
						break;
					}			
				}
				
				//��ư�� ���󺹱� �մϴ�.
				btnChange(false);
								
			}
		};
		
		thread.start();
		
	}
			
	private void setAllSave(){
			
		this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
				
		System.out.println("������ �����մϴ�.");
		
		// ���̺��� ���õ� ���� �ε��� ��ȣ ���
		int[] row = table.getSelectedRows();
		//int col = table.getColumnCount();
		
		for(int i=0; i < row.length; i++){			
			row[i] = table.convertRowIndexToModel(row[i]);
		}
		
		ArrayList<String> query_list = new ArrayList<String>();
		
		//�������縦 Ȯ�� �մϴ�.
		boolean edit_check_info = false;
		
		//���� ������ŭ ����� ������ �����մϴ�.
		for(int j = 0; j < row.length; j++){
			
			Vector<Object> temp = new Vector<Object>();
			String goods_info_query = "Update Goods_Info Set Edit_Tran='1' ";						
												
			temp.add(table.getModel().getValueAt(row[j], 1));
			temp.add(table.getModel().getValueAt(row[j], 17));
						
			String barcode = temp.get(0).toString(); //���ڵ�
			String goods_connect = temp.get(1).toString(); //��������
			
			//�ɼ� �ҷ�����
			//0. ���θ���������/1.��������/2.����뿩��/3.�̹�������/
			switch(bg_shoppingmall[0].getSelection().getActionCommand()){			
			case "������":
				goods_info_query += ", ShoppingMall_Use='1' ";
				edit_check_info = true;
				break;				
				
			case "��������":
				if(goods_connect.equals("���ε�ȵ�")){
					goods_info_query += ", ShoppingMall_Use='0' ";				
					edit_check_info = true;
				}
				break;
			}
			
			switch(bg_shoppingmall[1].getSelection().getActionCommand()){				
			case "������":
				goods_info_query += ", shop_view='1' ";					
				edit_check_info = true;
				break;
				
			case "��������":
				goods_info_query += ", shop_view='0' ";					
				edit_check_info = true;
				break;
			}
			
			switch(bg_shoppingmall[3].getSelection().getActionCommand()){
			case "�����":
				goods_info_query += ", sto_use='1' ";					
				edit_check_info = true;
				break;
				
			case "������":
				goods_info_query += ", sto_use='0' ";					
				edit_check_info = true;
				break;
			}
			
			/*switch(bg_shoppingmall[2].getSelection().getActionCommand()){			
			case "�ܵ�����":
				goods_info_query += ", img_path_use='0' ";					
				edit_check_info = true;
				break;
				
			case "��������":
				goods_info_query += ", img_path_use='1' ";					
				edit_check_info = true;
				break;
			}*/
						
			//������� ����
			if(text_anstock.getText().length() > 0){
				goods_info_query += ", pro_sto='"+text_anstock.getText()+"' ";
				edit_check_info = true;
			}
			
			//������� �ڵ�(���� �Ǿ��ִ� �ε����� �ҷ��ͼ� �����մϴ�.)
			/*switch(cb_maincode_list.getSelectedIndex()){
			case 0:
				//���� �Ȱ� �����ϴ�. ������ ���� �ʽ��ϴ�.
				break;
			case 1:
				//���õǾ� �ִ� ������ �����մϴ�.
				goods_info_query += ", Shop_MainCode='' ";
				edit_check_info = true;
				break;
			default:
				//���� �ɼ��� �ҷ��ͼ� �����մϴ�.
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
			
			if(i > 0){
			System.out.println(select_item);
			select_item = select_item.substring(0, select_item.length()-1);
			System.out.println(select_item);
						
				if(i == 1){
					if(select_item.equals("none")){
						//���õǾ� �ִ� ������ �����մϴ�.
						goods_info_query += ", Shop_MainCode='none' ";
						edit_check_info = true;
					}else{
						goods_info_query += ", Shop_MainCode='"+select_item+"' ";
						edit_check_info = true;
					}
				} 
				
				if(i > 1){
					//���� �ɼ��� �ҷ��ͼ� �����մϴ�.				
					goods_info_query += ", Shop_MainCode='"+select_item+"' ";
					edit_check_info = true;				
				}
			}
			
			//������ Goods_Info
			if(edit_check_info){
				goods_info_query += " where barcode='"+barcode+"' ";
				query_list.add(goods_info_query);	
				System.out.println(goods_info_query);
				
			}
		}
		
		//���� ����
		if(edit_check_info){
			ms_connect.setMainSetting();
			int result = ms_connect.connect_update(query_list);
			switch(result){		
			case 0:
				JOptionPane.showMessageDialog(this, "���� �Ϸ� �߽��ϴ�.");						
				this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				
				detail_Renew();				
				//�˻��ϱ�
				search_start();
				
				break;
			default:
				JOptionPane.showMessageDialog(this, "DB ���ῡ ���� �߽��ϴ�.");
				this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				break;
			}		
		}else{
			JOptionPane.showMessageDialog(this, "������ �ɼ� ��ǰ�� �����ϴ�.");
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}
	}
	
	
	//���θ� �����ڵ带 �ҷ� �ɴϴ�.
	public void getMainCode(){
				
		if(!maincode_callUse){		
			try{
				main_code = shop_api.getMainCode("��ǰ����");
				maincode_callUse = true;
			}catch(NullPointerException e){
				JOptionPane.showMessageDialog(this, "Ȩ�������� ���� �� �� �����ϴ�. ȯ�漳�� �� ���ͳ��� Ȯ���� �ּ���!!");
				return;
			}		
		}
				
		/*if(main_code.size() <= 0){
			JOptionPane.showMessageDialog(this, "������� �ڵ带 �������� �ʾҽ��ϴ�. ���θ� �����ڿ��� ���� �ϼ���!!");
			return;
		}*/
		
		/*//ȯ�漳��
		String shop_key = Server_Config.getSHOPKEY();
		
		//���� ���θ����� �����ϱ�
		String shop_address = "https://ssl.anybuild.co.kr/API/goods/main_code.php";	
		System.out.println(" ����ȭ�� �����մϴ�. ���� �ּ� --> " + shop_address);
		
		shop_address += "?api_key="+shop_key.toString();
		System.out.println(shop_address);
		//����� ���� ������ �����մϴ�.
		
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
		
		//����� ���� �մϴ�.
		//�������� �����մϴ�.
		try {
			
			URL url = new URL(shop_address);
			HttpURLConnection shop_url = (HttpURLConnection)url.openConnection();
			
			shop_url.setDoInput(true);
			
			System.out.println("���ۻ��� ���");			
			System.out.println(" URL : "+shop_url.getURL());			

			//���� ��� ����
			InputStreamReader isr = new InputStreamReader(shop_url.getInputStream(), "UTF-8");	
			JSONObject object = (JSONObject)JSONValue.parseWithException(isr);							
						
			isr.close();
			
			data = (JSONArray)object.get("result_data");
			System.out.println(data.toJSONString());
			//������
			System.out.println(object.toString());
			
			SimpleDateFormat formatter = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss", Locale.KOREA );
			Date currentTime = new Date ( );
			String dTime = formatter.format ( currentTime );
			
			String sb = "���� �ð� : " + dTime + "�����ڵ���ȸ ��� \r\n" ;
					sb += "result_msg : "+object.get("result_msg")+","+" result_cnt : "+object.get("result_cnt");					
			
			char[] paser = sb.toCharArray();
			
			//�α������� �ۼ��մϴ�.
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
			System.out.println("��ȸ�� �Ϸ� �Ǿ����ϴ�.");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();		
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		Iterator<JSONObject> itr = main_code.iterator();
	
		final JScrollPane scrollpane_change = new JScrollPane();		
		scrollpane_change.setBounds(6, 17, 357, 45);
		scrollpane_change.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollpane_change.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		JPanel panel = new JPanel();
		panel.setAutoscrolls(true);
		JCheckBox chkbox = new JCheckBox("��¾���");
		chkbox.setName("none");
		chkbox.addItemListener(new ChkboxItemListioner_AllChange());
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		panel.add(chkbox);
		
		chk_boxs.add(chkbox);
		
		while(itr.hasNext()){		
			JSONObject temp = (JSONObject)itr.next();
			//�����մϴ�.
			
			JCheckBox chk_box = new JCheckBox(temp.get("subject").toString());
			chk_box.setName(temp.get("group_code").toString());
			chk_box.addItemListener(new ChkboxItemListioner_AllChange());
			//scrollpane.add(chk_box);
			panel.add(chk_box);
			chk_boxs.add(chk_box);
		}
		//panel_maincode_title.setLayout(null);
		panel_maincode_title.setLayout(new BorderLayout());
		panel_maincode_title.setPreferredSize(new Dimension(100, 80));
		
		scrollpane_change.setViewportView(panel);
		
		panel_maincode_title.add(scrollpane_change, BorderLayout.CENTER);		
	}
	
	
    private void getImageData(){
    	
    	try{
			int number = Integer.parseInt(label_Detail_Number.getText());
		}catch(NumberFormatException e){
			JOptionPane.showMessageDialog(this, "��ǰ�� ������ �ּ���!!");
			detail_Renew();
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			return;
		}
    	
    	//�������� ���п� ���� ������ �� ������ �����ϴ�.
		//���� ���ð� �ҷ�����
    	String gubun = "";    	
    	String gname = "";
    	//��ǰ���� �ҷ�����
    	String barcode = text_Detail_Barcode.getText();	
    	String goodsName = text_Detail_Name.getText();
    	if(!text_imagename.getText().equals("")){
    		goodsName = text_imagename.getText();
    	}
    	
    	//���� ������ �����ؼ� ��ǰ �ҷ�����
    	String query = "Select * From FTP_Image Where 1=1 ";
    	    	
    	//�������ÿ� ����
    	//if(combox_Detail_ImageConnectUse.getSelectedItem().equals("�ܵ�����")){			
			gubun = "and ( path = '"+Server_Config.getFTPMARTPATH()+"' or Path_Gubun='1' ) ";
		//}else{			
			//gubun = "and Path_Gubun='1' ";
		//}
    	    	
    	if(cb_gname_search.isSelected()){
    		gname = "and G_Name='"+goodsName+"' ";
    	}else{
    		gname = "and Barcode='"+barcode+"' ";    		
    	}
		
    	//���� ��ġ��
    	query += gubun + gname;
    	
		ms_connect.setImageSetting();
		ArrayList<HashMap<String, String>> temp = ms_connect.connection(query);
		
		switch(temp.size()){
		case 0: //�˻��� ����� �����ϴ�.
			JOptionPane.showMessageDialog(this, "�˻��� �̹��� ������ �����ϴ�.");		
			break;
		case 1: //�˻��� ����� �����մϴ�.
			HashMap<String, String> map = temp.get(0);			
			//query = "Update Goods_Info Set Img_Path='http://14.38.161.45:7080/"+map.get("Path")+"/"+map.get("Barcode").trim()+"."+map.get("Ext").trim()+"', Edit_Tran='1' Where Barcode='"+map.get("Barcode").trim()+"' ";
			String imagepath = "http://14.38.161.45:7080/"+map.get("Path")+"/"+map.get("Barcode").trim()+"."+map.get("Ext").trim();
			
			edit_goods_info = true;
			text_imagename.setText(map.get("G_Name"));
			combox_Detail_ImageConnectUse.setSelectedIndex(Integer.parseInt(map.get("Path_Gubun")));
			text_Detail_ImagePath.setText(imagepath);
			break;
		default: //�˻��� ����� �������Դϴ�.
			JOptionPane.showMessageDialog(this, "�˻��� ������ 1�� �̻��Դϴ�. �̹������� �ǿ��� �˻��� �ּ���!!");	
			break;	
		}
    }
    
    //�̹��� �˻���� FTP_Server �˻�
    public void jtap_FTPSearch(){
    
    	this.setCursor(new Cursor(Cursor.WAIT_CURSOR));    	
    	
    	if(text_jtab_search.getText().length() < 1){
			JOptionPane.showMessageDialog(this, "�˻�� �Է��� �ּ���!!");
			text_jtab_search.requestFocus();
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));  
			return;
		}
    	
    	GUBUN = "FTP";
    	
    	//�ǳ� �����ϱ�
    	panel_jtap_image.removeAll();
    	
    	//�˻�� �ҷ��ɴϴ�.
    	String search_str = text_jtab_search.getText().toString();
    	
    	ms_connect.setImageSetting();
		String query = "Select * From Ftp_Image Where barcode like '%"+search_str+"%' or g_name like '%"+search_str+"%' ";
		jtap_image_list = ms_connect.connection(query);
		
		if(jtap_image_list.size() <= 0){
			//JOptionPane.showMessageDialog(this, "�˻��� �̹����� �����ϴ�.");
			
			//��� ��� ��ư ����
			label_count_up.setText("0");
			label_count_total.setText("0");
			image_total_count = 1;
			image_page_num = 1;
			image_page_count = 1;
			
			panel_jtap_image.setLayout(new BorderLayout());
			JLabel label = new JLabel("�˻��� �̹����� �����ϴ�.", JLabel.CENTER);
			panel_jtap_image.add(label, BorderLayout.CENTER);
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			text_jtab_search.requestFocus();
			text_jtab_search.selectAll();
			this.repaint();
			return;
		}
		
		//�˻��� �̹����� �Ѹ��ϴ�. ��� �˻� �ƴ��� Ȯ�� �մϴ�.
		jtap_setImage();
		
		this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }
    
    //�Ǿ� ���� �˻� �մϴ�.
    public void jtap_PCSearch(){
    	
    	this.setCursor(new Cursor(Cursor.WAIT_CURSOR));   
    	
    	JFileChooser jfiledialog = new JFileChooser();		
		int ret = 0;
		
    	if(!chkbox_pcsearch_dir.isSelected()){
    		jfiledialog.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    		//���ϼ��� â�� ���ϴ�.		
    		jfiledialog.setCurrentDirectory(new File(text_jtab_path.getText().toString()));
			ret = jfiledialog.showOpenDialog(this);			
			System.out.println("��� ���� : "+ret);
    	}else{
    		if(text_jtab_path.getText().equals("")){
    			ret = -1;
    		}
    	}
    	
    	
    	
		//������ ���� �ߴٸ� ���� ������ ��� �̹����� �ҷ��ɴϴ�.
		if(ret == 0){
			//�˻� ��θ� �����մϴ�.
			GUBUN = "����";
			
			if(!chkbox_pcsearch_dir.isSelected()){			
				text_jtab_path.setText(jfiledialog.getSelectedFile().toString());
			}
			
			//���� ��� �ҷ��ͼ� ����Ʈ�� �ѷ��ֱ�
			File dirFile=new File(text_jtab_path.getText());
			File []fileList=dirFile.listFiles();
			final String[] FILE_EXTENSION = {"jpg","gif","png","bmp"};
			
			//�̹��� ����� Ŭ�����մϴ�.
			jtap_image_list.clear();
			
			for(File tempFile : fileList) {
				if(tempFile.isFile()) {
					HashMap<String, String> map = new HashMap<String, String>();
					
					if(tempFile.isFile()){  //���ϸ� üũ�մϴ�.
						
						//������ �ҷ��ɴϴ�.
						String fileName = tempFile.getName().toString();		
						
						if(!fileName.contains(".")) continue; 
						//���ϸ��� "." �� �������� ���ڸ��� �߶� ���ϴ�.						
						//System.out.println(fileName);
						String perfix = fileName.substring(0, fileName.lastIndexOf("."));
						String ext = fileName.substring(fileName.lastIndexOf(".")+1, fileName.length()).toString();
						
						//String tempPath=tempFile.getParent();
						//Ȯ���ڸ� �˻��ؼ� �׸� ���ϸ� �ҷ� �ɴϴ�.				
						for(int i =0; i < FILE_EXTENSION.length; i++){
							if(ext.toLowerCase().equals(FILE_EXTENSION[i])){
								//������ �ҷ��ɴϴ�.				
								map.put("Barcode", perfix); //�̹�����
								map.put("G_Name", perfix); //���ϸ�
								map.put("Path", tempFile.getPath()); //���� ���
								map.put("Path_Gubun", "0"); //���� ����					
								map.put("Ext", ext); //Ȯ����
								map.put("Pop_Tran", "");
								jtap_image_list.add(map);
							}
						}
						
					}
				}
			}	
			
			if(jtap_image_list.size() <= 0){
				//JOptionPane.showMessageDialog(this, "�˻��� �̹����� �����ϴ�.");
				
				//��� ��� ��ư ����
				label_count_up.setText("0");
				label_count_total.setText("0");
				image_total_count = 1;
				image_page_num = 1;
				image_page_count = 1;
				
				panel_jtap_image.setLayout(new BorderLayout());
				JLabel label = new JLabel("�˻��� �̹����� �����ϴ�.", JLabel.CENTER);
				panel_jtap_image.add(label, BorderLayout.CENTER);
				this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				text_jtab_search.requestFocus();
				text_jtab_search.selectAll();
				this.repaint();
				return;
			}
			
			//�˻��� �̹����� �Ѹ��ϴ�. ��� �˻� �ƴ��� Ȯ�� �մϴ�.
			jtap_setImage();
    	}
		
    	this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }
    
    
    //�˻��� ����Ÿ�� ������� �����մϴ�.
    private void jtap_setImage(){
    	
    	panel_jtap_image.setLayout(new GridLayout(0, 3));
    	
    	int totalCount =  jtap_image_list.size();
    	System.out.println("�� ���� : "+totalCount);
    	
    	//�� �˻� ��ǰ ����
    	image_total_count = totalCount;
    	
    	//�� ������ ����
    	image_page_count = totalCount/image_page_listcount;
    	
    	//�� ������ 30���� ���� ������ �̹����� �ִٸ� �������� �� ���̱�
    	if(totalCount%image_page_listcount > 0){
    		image_page_count++;
    	}
    	
    	//���������� ��ȣ
    	image_page_num = 1;
    	
    	//���� ������ �� �������� ǥ��
    	label_count_up.setText(String.valueOf(1));
    	label_count_total.setText(String.valueOf(image_page_count));
    	System.out.println("�� ������ ���� : "+image_page_count);
    
    	setImageList();
    }
    
    //�̹����� �����մϴ�.
    public void setImageList(){
    	    	
    	int last_number = 0;    	
    	
    	//�ǳ� �ʱ�ȭ
    	panel_jtap_image.removeAll();
    	
    	//���� �������� 1�϶�
    	//���� �������� ������ ������ �϶� �������� ������ ������������
    	if(image_page_num == Integer.parseInt(label_count_total.getText())){
    		//���� ������ ��� �մϴ�.
    		last_number = image_total_count;
    	}else{
    		//���� ���� �Դϴ�.
    		last_number = image_page_num * image_page_listcount;  		
    	}
    	
    	for(int count = (image_page_num * image_page_listcount)-image_page_listcount; count < last_number; count++){
    		HashMap<String, String> map = jtap_image_list.get(count);    		
    		String [] item = new String[7];
    		
    		item[0] = map.get("Barcode"); //�̹��� ���ڵ�
    		item[1] = map.get("Path");  //�̹��� ����
    		item[2] = map.get("G_Name"); //�̹��� ��ǰ��
    		item[3] = map.get("Ext"); //�̹��� Ȯ����
    		System.out.println(map.get("Path"));
    		System.out.println(map.get("Barcode"));
    		System.out.println(map.get("Ext"));
    		
    		String path = "";
    		if(GUBUN.equals("FTP")){
    			path = "http://14.38.161.45:7080/"+map.get("Path").trim()+"/"+map.get("Barcode").trim()+"."+map.get("Ext").trim(); //�̹����� ���
    			
    			System.out.println(path);
    		}else{
    			path = map.get("Path"); //�̹����� ���
    		}
    		
    		//System.out.println(path);
    		item[4] = path; //���� ������ ���
    		item[5] = String.valueOf(count+1); //�̹��� ����
    		item[6] = GUBUN; //��� �˻� �Ǿ����� FTP���� ���� ���� ����
    		
    		//��ϸ���Ʈ�� �����մϴ�.
    		panel_jtap_image.add(setImageView(item));    		
    		this.repaint();
    	}
    }    
        
    public JPanel setImageView(String[] item){
    	
    	//���ϸ� Barcode 0��, Ext 3��
    	String fileName = item[0]+"."+item[3];    	
    	//��ǰ��    	
    	String goodsName = item[2];    	
    	//�н�
    	String filePath = "";
    	if(GUBUN.equals("FTP")){
    		System.out.println(item[1]);
    		filePath = item[1];
    	}else{
    		filePath = "PC����";
    	}    		
    	
    	//�̹��� ������ �����մϴ�.
    	//�̹����� ���� ������ ����ϴ�.
    	JPanel panel = new JPanel();    	
    	//panel.setLayout(new BorderLayout());    	    	    	
    	
    	Box box = Box.createVerticalBox();
    	
    	JLabel image_show = new JLabel();
    	box.add(image_show);
    	
    	JLabel g_name = new JLabel(setSubString(goodsName, 14));
    	g_name.setFont(new Font("���� ���", Font.BOLD, 11));
    	box.add(g_name);
    	
    	JLabel image_path = new JLabel(setSubString(filePath, 16));
    	image_path.setFont(new Font("���� ���", Font.BOLD, 12));
    	box.add(image_path);
    	
    	JLabel file_name = new JLabel(setSubString(fileName, 17));
    	file_name.setFont(new Font("���� ���", Font.BOLD, 10));    	
    	box.add(file_name);
    	JButton file_choose = new JButton(item[5]+"�� ����");    	
    	
    	box.add(file_choose, Box.CENTER_ALIGNMENT);
    	
    	file_choose.addActionListener(new ActionListener() {    		
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//�̹��� ���� ��ư �ٷ� ������ �մϴ�.								
				saveImageChoose(item);
			}
		});
    	
    	panel.add(box);
    	   	
    	Image image = null;
        try {
        	System.out.println(item[4].toString());        	
        	if(GUBUN.equals("FTP")){
        		URL url = new URL(item[4].toString());
                image = ImageIO.read(url);
        	}else{
        		File file = new File(item[4].toString());
        		image = ImageIO.read(file);
        	}         
        } catch (IOException e) {
        	e.printStackTrace();        	
        	this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }                
       	image_show.setIcon(new ImageIcon(image.getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
       	
       	return panel;
    }    
    
    //����Ʈ ������ �ѱ��� �߶� ���ϴ�.
    private String setSubString(String str, int cut){
    	int cutByte = cut;
    	byte [] strByte = str.getBytes();
        if( strByte.length < cutByte )
          return str;
        int cnt = 0;
        for( int i = 0; i < cutByte; i++ )
        {
           if( strByte[i] < 0 )
           cnt++;
        }

       String r_str;
       if(cnt%2==0) r_str = new String(strByte, 0, cutByte );
       else r_str = new String(strByte, 0, cutByte + 1 );

       return r_str;    	
    }
    
    //���õ� �̹����� �����մϴ�.
    public void saveImageChoose(String[] item)	{	
    	
    	if(table.getSelectedRowCount() == 0 || table.getSelectedRowCount() > 1){
    		JOptionPane.showMessageDialog(this, "�Է��� ��ǰ�� �ϳ��� ������ �ּ���~!!");    		
    		return;
    	}
    	
    	this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
    	
    	//����� �����մϴ�.
    	int row = table.getSelectedRow();
    	//item 0:���ڵ�/1:������/2:��ǰ��/3:Ȯ����/4:�н�/5:����
    	
    	//���� ��ǰ�� ���� �̹����� Ȯ���մϴ�.
    	String barcode = ""; //���� ���õ� ��ǰ ���ڵ�    	
    	barcode = (String)dtm.getValueAt(row, 1);    	
    	String gname = ""; //���� ���õ� ��ǰ ��ǰ��    	
    	gname = (String)dtm.getValueAt(row, 2);   	
    	
    	String filepath = item[4].toString(); //���� ���õ� ��ǰ�����н�
    	
    	//������ �������� �� ���� FTP�̹��� ���� ������ �ƴ϶�� ������ �ڱ� ������ �ű�� ������ ������ �մϴ�.
    	if(item[1].equals(Server_Config.getFTPMARTPATH()) || item[1].equals(Server_Config.getFTPSERVERPATH()) ){    
    		System.out.println("�ܵ����� �Ǵ� ���������Դϴ�.");
    	
    		//��Ʈ������ ���� ���������� ������ 
    		//���� ������ ������ �����ϴ�.
    		String gubun = "1";
    		if(item[1].equals(Server_Config.getFTPMARTPATH())){
    			gubun = "0";
    		}
    	
    		String query_goodsInfo = "Update Goods_info set Edit_Tran='1', Img_Name='"+gname+"', Img_path_use='"+gubun+"', img_path='"+filepath+"', Shop_view='1', ShoppingMall_use='1' where Barcode='"+barcode+"' ";
    		ms_connect.setMainSetting();
			int res = ms_connect.connect_update(query_goodsInfo);
		
			switch(res){
			case 0:
				//ȯ�漳������ �ɼ� ���� �� ���� �˴ϴ�. 
				if(Server_Config.getGOODS_TRANYN().equals("1")){
					new TransferDataGoodsSet(ms_connect, this, barcode);
				}
				JOptionPane.showMessageDialog(this, "�̹����� ���� ��� �Ǿ����ϴ�.");
				break;
			default:
				JOptionPane.showMessageDialog(this, "�̹��� ��Ͽ� �����Ͽ� ���ϴ�.");
				break;
			}			
    	}else{
    		//�׷��� �ʴٸ� ������ �ҷ��ɴϴ�.
    		System.out.println("Ÿ�������� �Ǵ� PC���� �Դϴ�.");    		
    		
    		filepath = "http://14.38.161.45:7080/"+Server_Config.getFTPMARTPATH()+"/"+barcode+"."+item[3].toString(); //���� ��ǰ �̹��� ��ȣ    		
    		//filepath = "/"+Server_Config.getFTPMARTPATH()+"/"+barcode+"."+item[3].toString(); //���� ��ǰ �̹��� ��ȣ
    		//��� �˻��� �������� Ȯ���ؼ� �����մϴ�. "FTP" / "����"
    		//�̹����� FTP��ο� ���� �մϴ�. �����˻��� �̹��� �̸� ũ���ؼ� ������ �ϰ� 
        	//FTP�̹��� �ٸ� ������ �ִ� �Ÿ��ڱ� ������ �����ؼ� �����մϴ�.
    		    		
    		if(uploadImage(item, filepath, barcode, gname)) return;
    	}
    	
    	//�̹��� ���
    	dtm.setValueAt(filepath, row, 22);
    	//�̹�����
    	dtm.setValueAt(gname, row, 23);
    	
    	this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }    
    
    //������ FTP������ ���ε� �մϴ�.
	private boolean uploadImage(String[] item, String filepath, String barcode, String gname){
		
		boolean state = false;
		//item 0:���ڵ�/1:������/2:��ǰ��/3:Ȯ����/4:�н�/5:����
		//FTP�� �̹����� ���ε� �մϴ�.
		//FTP ������ ���� �մϴ�.
		String barcode_org = item[0]; //���� �̹���
		String path_org = item[1]; //���� �̹���
		String gname_org = item[2];		 //���� �̹���
		String ext_org = item[3]; //���� �̹���
		String longPath_org = item[4]; //���� �̹���
		String gubun = item[6];
		String barcode_cho = barcode;  //���� ���õȻ�ǰ
		String gname_cho = gname;  //���� ���õ� ��ǰ
		
		//������ ���ε� �մϴ�.
		String query = "";
		String query_goodsInfo = "";
		
		//���� ������ �����մϴ�.
		String uploadFail = "";
				
		FTPClient ftpConn = new FTPClient();
		
		//���� ������ ��ǰ�� ������ �ִٸ� ������ ���� ���� �մϴ�.
		int row = table.getSelectedRow();
		
		//Vector<Object> temp = new Vector<Object>();
		
		//for(int i = 0; i < table_hotkey.getColumnCount(); i++){
		//�̹��� ���� ���θ� Ȯ�� �մϴ�.
		String path_gubun = (String)dtm.getValueAt(row, 21);		
		String path = (String)dtm.getValueAt(row, 22);
		
		try{
			path = path.trim();
		}catch(NullPointerException e){
			e.printStackTrace();
		}		
		System.out.println(path_gubun);		
		try{
			//���� �̹����� �����ϰ� �ܵ� ������� ���� ��ϵ� �̹����� ���� �մϴ�.
			
			if( path.length() > 0 && path_gubun.equals("�ܵ�����") && !path.equals("http://14.38.161.45:7080/main_goods/noImage.jpg")){				
				//������ ���� �Ѵٸ�				
				System.out.println("������ ������ �����մϴ�. ���� ���� �մϴ�.");				
				//������ ���� �մϴ�.								
				//ftp������ �����մϴ�.
				try {
					
					ftpConn.connect(Server_Config.getFTPIP(), Server_Config.getFTPPORT());
					ftpConn.login(Server_Config.getFTPID(), Server_Config.getFTPPW());				
					
					System.out.println(ftpConn.currentDirectory());
					String path_ext = path.substring(path.length()-3, path.length());
					System.out.println(path_ext);
					
					ftpConn.changeDirectory(Server_Config.getFTPMARTPATH());
					
					//������ ����Ǿ� �ִ� ������ ���� �մϴ�.
					ftpConn.deleteFile(barcode.trim()+"."+path_ext);
					ftpConn.disconnect(true);
					
					//�̹������ �����ϱ� ���� ������ ��� �Ӵϴ�.
					/*String query_ftp = "Delete From FTP_Image Where Barcode = '"+barcode+"' and path_gubun='0' and path='"+Server_Config.getFTPMARTPATH()+"' ";
					String query_delete = "Update Goods_info set Edit_Tran='0', Img_Name='', Img_path_use='0', img_path='', where Barcode='"+barcode+"' ";
					
					System.out.println(query_ftp);					
					System.out.println(query_delete);
					
					//�̹��� ������ 
					ms_connect.setImageSetting();
					ms_connect.connect_update(query_ftp);					
					
					ms_connect.setMainSetting();
					ms_connect.connect_update(query_delete);		*/			
					
					System.out.println("�⺻ ���� ���� ����");
				} catch (IllegalStateException | IOException | FTPIllegalReplyException | FTPException e) {
					// TODO Auto-generated catch block					
					e.printStackTrace();
					JOptionPane.showMessageDialog(this, "FTP�� ���� �̹����� �������� ���߽��ϴ�. . \r\n �������� : "+e.getMessage());
					return state=true;
				}
				
			}			
		}catch(NullPointerException e){
			e.printStackTrace();			
		}
		
		//FTP ������ �ִ� �����̸� �켱 Ư�� ������ �ٿ�ε� �մϴ�.
		System.out.println(GUBUN);
		if(gubun.equals("FTP")){
			//FTP ���� ����			
			try {				
				ftpConn.connect(Server_Config.getFTPIP(), Server_Config.getFTPPORT());
				ftpConn.login(Server_Config.getFTPID(), Server_Config.getFTPPW());				
				
				System.out.println(ftpConn.currentDirectory());
				ftpConn.download(path_org+"/"+barcode_org.trim()+"."+ext_org.trim(), new File(barcode.trim()+"."+ext_org.trim()));
				ftpConn.changeDirectory(Server_Config.getFTPMARTPATH());
				ftpConn.upload(new File(barcode.trim()+"."+ext_org.trim())); //���� ���õ� ��ǰ�� ���ڵ庯�� �˴ϴ�.	
				
				new File(barcode.trim()+"."+ext_org.trim()).delete();
				
				//�̹������ �����ϱ� ���� ������ ��� �Ӵϴ�.
				query = "Insert into FTP_Image (barcode, path, path_gubun, g_name, ext, pop_tran)"
						+ "values('"+barcode+"', '"+Server_Config.getFTPMARTPATH()+"', '0', '"+gname+"', '"+ext_org+"', 0); ";
				
				query_goodsInfo = "Update Goods_info set Edit_Tran='1', Img_Name='"+gname+"', Img_path_use='0', img_path='"+filepath+"', Shop_view='1', ShoppingMall_use='1' where Barcode='"+barcode+"' ";
				System.out.println(query);
				System.out.println(query_goodsInfo);
				
				ftpConn.disconnect(true);
				System.out.println("�ٿ�ε� ����");
			} catch (IllegalStateException | IOException | FTPIllegalReplyException | FTPException | FTPDataTransferException | FTPAbortedException e) {
				// TODO Auto-generated catch block
				//if(temp_file.exists()) temp_file.delete();
				e.printStackTrace();
				JOptionPane.showMessageDialog(this, "FTP �̹����� �������� ���߽��ϴ�. . \r\n �������� : "+e.getMessage());
				return state=true;
			}
		}else{
			//FTP ���� ����			
			try {				
				ftpConn.connect(Server_Config.getFTPIP(), Server_Config.getFTPPORT());
				ftpConn.login(Server_Config.getFTPID(), Server_Config.getFTPPW());				
				
				System.out.println(ftpConn.currentDirectory());
				//ftpConn.download(path_org+"/"+barcode_org.trim()+"."+ext_org.trim(), new File(barcode.trim()+"."+ext_org.trim()));
				ftpConn.changeDirectory(Server_Config.getFTPMARTPATH());
				
				if(!fileSizeCheck(new File(longPath_org))){
					return state=true;
				}				
				
				ftpConn.upload(new File(longPath_org)); //���� ���õ� ��ǰ�� ���ڵ庯�� �˴ϴ�
				System.out.println(longPath_org);
				System.out.println(gname_org.toString()+"."+ext_org.toString() +"->"+ barcode.toString()+"."+ext_org.toString());
				ftpConn.rename(gname_org.toString()+"."+ext_org.toString(), barcode.toString()+"."+ext_org.toString());
				System.out.println("���Ͼ��ε� �Ϸ�");
								
				//�̹������ �����ϱ� ���� ������ ��� �Ӵϴ�.
				query = "Insert into FTP_Image (barcode, path, path_gubun, g_name, ext, pop_tran)"
						+ "values('"+barcode+"', '"+Server_Config.getFTPMARTPATH()+"', '0', '"+gname+"', '"+ext_org+"', 0); ";
				
				query_goodsInfo = "Update Goods_info set Edit_Tran='1', Img_Name='"+gname+"', Img_path_use='0', img_path='"+filepath+"', Shop_view='1', ShoppingMall_use='1' where Barcode='"+barcode+"' ";
				System.out.println(query);
				System.out.println(query_goodsInfo);
				
				ftpConn.disconnect(true);
				System.out.println("�ٿ�ε� ����");
			} catch (IllegalStateException | IOException | FTPIllegalReplyException | FTPException | FTPDataTransferException | FTPAbortedException e) {
				// TODO Auto-generated catch block
				//if(temp_file.exists()) temp_file.delete();
				e.printStackTrace();
				JOptionPane.showMessageDialog(this, "FTP �̹����� �������� ���߽��ϴ�. . \r\n �������� : "+e.getMessage());
				return state=true;
			}			
		}
		
		//������ ��ǰ�� �������� �մϴ�.
		if(query.length() > 0){
			ms_connect.setImageSetting();
			int result = ms_connect.connect_update(query);
			switch(result){
			case 0:	
				String queryOver = "Delete From Ftp_Image Where idx in( select a.idx as idx "
								+"From Ftp_Image A INNER JOIN ( Select Min(idx) as idx, barcode, count(*) as overlapCount "
								+ "From Ftp_Image Where path_gubun='0' and path='"+Server_Config.getFTPMARTPATH()+"' Group by barcode Having count(*) > 1 ) B "
								+ "on a.barcode = b.barcode and A.idx <> B.idx and a.path_gubun='0' and path='"+Server_Config.getFTPMARTPATH()+"')";				
				ms_connect.connect_update(queryOver);
				break;
			default:
				break;
			}
			
			ms_connect.setMainSetting();
			ms_connect.connect_update(query_goodsInfo);	
		}
		
		if(Server_Config.getGOODS_TRANYN().equals("1")){
			new TransferDataGoodsSet(ms_connect, this, barcode);
		}
		
		JOptionPane.showMessageDialog(this, "������ �Ϸ� �Ǿ����ϴ�.");
		System.out.println("����Ϸ�");	
		return state;
	}
	
	//���� ������ �� ũ�⸦ üũ�մϴ�.
	private boolean fileSizeCheck(File file){
				
		try{
			  
			 String name = file.getName();
			 name = name.substring(name.lastIndexOf('.')+1, name.length());
			 System.out.println(name);			 
			 
			 BufferedImage bi = ImageIO.read(file);
			   
			 //�Ϲ����� �̹��� ��ü�� ��� getWidth�޼ҵ�� getHeight�޼ҵ尡 Ʋ���ϴ�. 
			 //�Ķ���Ͱ� �־� ���� ����ϹǷ� BufferedImage �� ���ô°� �´°� �����ϴ�.
			 int width=bi.getWidth();
			 int height=bi.getHeight();		   
		   
			 if(width > 500 || height > 500) {	   
				 BufferedImage outputImage = new BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB);
				 Graphics2D g = outputImage.createGraphics();
				 g.drawImage(bi, 0, 0, 500, 500, null);
				   			   
				 File out = new File(file.getAbsolutePath());
				 FileOutputStream fos = new FileOutputStream(out);
				 ImageIO.write(outputImage, name, fos);
				 fos.close();				 
			 }
		  }catch(Exception e){			  
			  e.printStackTrace();
			  return false;
		  }		  
		  return true;
	}   
    
	//���̾� �α� ����� ���� �մϴ�. (�� 0 �ƴϿ� 1 ��� 2)����� ���� ������ ���� �ȵǰ� �����ϴ�.
    public boolean change_Flags(){
    	
    	//edit_goods_info = true;
    	boolean flags = false;
    	if(edit_goods_info || edit_goods){    		
    		int result = JOptionPane.showConfirmDialog(this, "����� ������ �ֽ��ϴ�. �����Ͻðڽ��ϱ�?");
    		System.out.println(result);
    		switch(result){
    		case 0:
    			//�����մϴ�.
    			saveGoodsInfo();
    			flags = true;
    			break;
    		case 1:   
    			//������մϴ�. 
    			edit_goods = false;
    			edit_goods_info = false;
    			break;
    		case 2:
    			//�ٽ� ���ư��ϴ�.
    			flags = true;
    			break;
    		}    		
    	}
    	return flags;
    }
    
    //������ ��
    public void setImageUpCount(){
    	//�̹��� ������ ��
    	System.out.println("��������");
    	if( image_page_num < image_page_count ){    		
    		image_page_num++;
    		label_count_up.setText(String.valueOf(image_page_num));
    		setImageList();
    	}    	
    	System.out.println(image_page_num);
    }
    
    //������ �ٿ�
    public void setImageDownCount(){
    	//�̹��� ������ �ٿ�
    	System.out.println("�������ٿ�");
    	if(image_page_num > 1){
    		image_page_num--;
    		label_count_up.setText(String.valueOf(image_page_num));
    		setImageList();
    	}
    	System.out.println(image_page_num);
    }    
    
    //��Ű�� ��ϵ� ��ǰ�� �ҷ��ɴϴ�.
    public void getHotKeyGoodsList(){
    	
    	System.out.println("��ǰ�˻��� �����մϴ�.");
    	if(!(tabPane_detail.getSelectedIndex() == 2)) return;
    	
    	//String query = "Select * From Hot_Key A join Goods B On A.H_Barcode=B.Barcode ";
    	
    	String query = "Select * From ( "
    					  +"Select a.H_Code, ISNull(H_Name, H_Index) as H_Index, a.H_Number, a.H_Barcode, a.H_Order, a.H_SellPri "
    					  +"From Hot_Key a Left Join Hot_Key_DefName b On a.H_Code=b.H_Code "
    					  +") c Join Goods d On c.H_Barcode=d.Barcode ";
    	
    	//�˻�� ���� �ɴϴ�.
    	String search_text = text_jtab_hotkey.getText();
    	int hotkey_map = cb_jtab_hotkey_map.getSelectedIndex(); 
    	hotkey_map--;
    	int hotkey_gubun = cb_jtab_hotkey_gubun.getSelectedIndex();
    	
    	//��ǰ �˻���
    	if(search_text.length() > 0){    		
    		query += "And ( d.G_Name Like '%"+search_text+"%' Or d.Barcode Like '%"+search_text+"%' ) ";
    	}
    	
    	//��ǰ ��
    	if(hotkey_map >= 0){
    		query += "And c.H_Code = '"+hotkey_map+"' ";
    	}
    	
    	//��ǰ ����
    	switch(hotkey_gubun){
    	case 0:
    		query += "And ( d.Scale_Use='1' Or Len(d.Barcode)=4) ";
    		break;
    	case 1:
    		query += "And d.Scale_Use='1' ";
    		break;
    	case 2:
    		query += "And Len(d.Barcode)=4 ";
    		break;    	
    	}
    	    	
    	query += "Order By c.H_Order ASC";
    	
    	ms_connect.setMainSetting();
    	ArrayList<HashMap<String, String>> hotkey_list = ms_connect.connection(query);    	    	
    	
    	if(hotkey_list.size() <= 0){    		
    		//JOptionPane.showMessageDialog(this, "�˻������ �����ϴ�.");
    		label_jtab_hotkey_result.setText("0");
    		dtm_hotkey.setRowCount(0);
    		return;    		
    	}
    	
    	label_jtab_hotkey_result.setText(String.valueOf(hotkey_list.size()));
    	setHotKeyList(hotkey_list);
    }
    
    //��Ű ����� ����ϴ�.
    private void setHotKeyList(ArrayList<HashMap<String, String>> hotkey_list){
    	 
    	dtm_hotkey.setRowCount(0);
    	for(HashMap<String, String> map : hotkey_list){
    		
    		Vector<String> hotkey = new Vector<String>();
    		    		
    		String code = map.get("H_Code");
    		String index = map.get("H_Index");
    		if(index.length() > 4){
    			index = index.substring(0, 4);
    		}    		
    		String barcode = map.get("H_Barcode");
    		String sell_pri = map.get("H_SellPri");
    		String g_name = map.get("G_Name");
    		String order = map.get("H_Order");
    		
    		hotkey.add(order);
    		hotkey.add(barcode);
    		hotkey.add(g_name);
    		hotkey.add(index);
    		hotkey.add(sell_pri);
    		hotkey.add(code);
    		
    		dtm_hotkey.addRow(hotkey);
    		
    	}    	
    }
    
    public void setHotKeyMapUp(){
    	
    	int select_hotkey = cb_jtab_hotkey_map.getSelectedIndex();
    	
    	if( select_hotkey < 52){
    		select_hotkey++;
    		cb_jtab_hotkey_map.setSelectedIndex(select_hotkey);
    	}    	
    }
    
    public void setHotKeyMapDown(){
    	
    	int select_hotkey = cb_jtab_hotkey_map.getSelectedIndex();
    	
    	if( select_hotkey > 0){
    		select_hotkey--;
    		cb_jtab_hotkey_map.setSelectedIndex(select_hotkey);
    	}    	
    }
    
    //����Ʈ���� �ҷ����ų� �ݾ��� �����Ͽ��ٸ� ���� �մϴ�.
    public void setHotKeyListSave(){
    	
    	this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
    	
    	int row = table.getSelectedRowCount();
    	
    	if(row <= 0){
    		JOptionPane.showMessageDialog(this, "��Ͽ��� �̵��� ��ǰ�� ������ �ּ���~!");
    		this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    		return;
    	}
    	
    	//�÷� ������ �ҷ��ɴϴ�.
    	int[] row_item = table.getSelectedRows();
    	
    	for(int i=0; i < table.getSelectedRowCount(); i++){
    		row_item[i] = table.convertRowIndexToModel(row_item[i]);
    	}
    	
    	int col = table.getColumnCount();
		
		ArrayList<Vector<Object>> temp_list = new ArrayList<Vector<Object>>();
		for(int j = 0; j < row; j++){
			Vector<Object> temp = new Vector<Object>();
			for(int i =0; i < col; i++){				
				temp.add(dtm.getValueAt(row_item[j], i));
			}
			temp_list.add(temp);
		}
				
		//��Ű�� �����ϱ�
		//�ʿ��ʵ�
		//�����ǰ �Ǵ� �κл�ǰ�� �ƴ϶�� �ɷ� �����ϴ�.
		//cb_jtab_hotkey_map
				
		//H_Code 0-51 ����
		int getHCode = cb_jtab_hotkey_map.getSelectedIndex()-1;
		
		//H_Index A-z ����
		char[] atoz = setAtoZ();
		String getHIndex = String.valueOf(atoz[getHCode]);
		
		//���� ���
		SimpleDateFormat formatter = new SimpleDateFormat ( "yyyy-MM-dd", Locale.KOREA );
		Date currentTime = new Date ( );
		String dTime = formatter.format ( currentTime );
		System.out.println ( dTime );
				
		//���ڵ�
		String barcode = "";
		String scale_use = ""; //�����ǰ
		int length_4; //�κл�ǰ
		
		for(int i = 0; i < temp_list.size(); i++){		
			
			Vector<Object> temp = new Vector<Object>();
			temp = temp_list.get(i);			
			barcode = temp.get(1).toString();//���ڵ�	
			scale_use = temp.get(24).toString();//�����ǰ
			length_4 = barcode.length();
			
			System.out.println(scale_use);
			//�κл�ǰ�� �ƴ϶�� �κл�ǰ�� �ƴ����� ���� ��ǰ�̶�� 
			if(length_4 != 4){
				if(!scale_use.equals("1")) continue;	
			}
				
			String[] query_won = new String[2];
			String query = "SELECT COUNT(*) as Count FROM HOT_KEY  WHERE H_BARCODE='"+barcode+"' ";
			
			//�����ǰ���� �Ǵ� �κл�ǰ���� üũ
			ms_connect.setMainSetting();
			HashMap<String, String> temp_count = ms_connect.selectQueryOne(query);
			int setCount = Integer.parseInt(temp_count.get("Count"));	
			if( setCount > 0 ){
				int result = JOptionPane.showConfirmDialog(this, "�̹� ����Ǿ� �ֽ��ϴ�. \r\n �̵� ���� �Ͻð� ���ϱ�?", "��ǰ �ߺ� ����", JOptionPane.OK_CANCEL_OPTION);
				
				//System.out.println(result);
				//0 �̵����� // �׿� �̵�������� ����
				query = "Update Hot_Key Set H_Code='"+getHCode+"', H_Index='"+getHIndex+"' Where H_Barcode='"+barcode+"' ";
								
				if(result != 0) continue;		
				
				//��������
				ms_connect.connect_update(query);
				continue;
			}
			
			query_won[0] = "INSERT INTO HOT_KEY(H_Code,H_INDEX,H_BARCODE,H_WRITEDAY) VALUES('"+getHCode+"','"+getHIndex+"', '"+barcode+"','"+dTime+"')";
			query_won[1] = "Update Goods_Info Set ShoppingMall_Use='1', Shop_View='1', Edit_Tran='1' Where Barcode='"+barcode+"' ";
			ms_connect.connect_update(query_won);
			
		}		
		
		//�´ٸ� ��ϵǾ� �ִ��� üũ
		//SELECT COUNT(*) FROM HOT_KEY  WHERE H_BARCODE='8801046867174'
		
		//�ȵ��ִٸ� ���ڵ�/ �Է��ڵ�/ ��ǰ��
		//INSERT INTO HOT_KEY(H_Code,H_INDEX,H_BARCODE,H_WRITEDAY) VALUES('49','x', '8801046867174','2015-12-17')
		
		getHotKeyGoodsList();
		this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }
    
    
    private char[] setAtoZ(){
    	
    	char[] atoz = new char[52];
    	
    	int j = 65;
		for(int i = 0; i < 26; i++){
			atoz[i] = (char)j;
			j++;
		}
		j=97;
		for(int i = 26; i < 52; i++){
			atoz[i] = (char)j;
			j++;
		}
		for(char at : atoz){
			System.out.println(at);
		}
    	
    	return atoz;    	
    }
    
    
    //��ǰ �Ѱ��� �����ϱ�
    public void deleteHotkyeList(){
    	    	
    	this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
    	
    	int row = table_hotkey.getSelectedRowCount();
    	
    	if(row <= 0){
    		JOptionPane.showMessageDialog(this, "������ ��ǰ�� ������ �ּ���!!");
    		this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    		return;
    	}
    	
    	//�÷� ������ �ҷ��ɴϴ�.
    	row = table_hotkey.getSelectedRow();
		int col = table_hotkey.getColumnCount();
				
		Vector<Object> temp = new Vector<Object>();
		for(int i =0; i < col; i++){				
			temp.add(dtm_hotkey.getValueAt(row, i));
		}
			
		String barcode = temp.get(1).toString();
	
		String[] query_won = new String[2];
		query_won[0] = "DELETE FROM HOT_KEY WHERE H_BARCODE='"+barcode+"' ";
		query_won[1] = "Update Goods_Info Set Shop_View='0', Edit_Tran = '1' Where Barcode='"+barcode+"' ";
		ms_connect.setMainSetting();
		int result = ms_connect.connect_update(query_won);
		    	
		switch(result){
		case 0:
			JOptionPane.showMessageDialog(this, "��ǰ�� ���� �Ǿ����ϴ�.");
			break;
		default:
			JOptionPane.showMessageDialog(this, "���� ���� ���߽��ϴ�. ");			
			break;
		}
		
		getHotKeyGoodsList();
		this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }
    
    //����Ʈ ��
    private void setHotkeyListUpDown(String updown){
    	
    	int start_item = table_hotkey.getSelectedRow();
    	//System.out.println(start_item);
    	    	
    	int end_item = 0;    	
    	switch(updown){
    	case "UP":
    		if(start_item >= table_hotkey.getRowCount() -1) return;
    		end_item = start_item + 1;
    		break;
    	case "DOWN":
    		if(start_item <= 0)	return;
    		end_item = start_item - 1; 
    		break;
    	}
    	
    	//System.out.println(end_item);
    	dtm_hotkey.moveRow(start_item, start_item, end_item);
    	//dtm_hotkey.moveRow(0, 1, 2);
    	table_hotkey.changeSelection(end_item, 4, false, false);
    	
    }
    
    //�Ǹűݾ��� �����մϴ�.
    public void setHotkeySellPriceSave(){
    	
    	this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
    	//���õ� ���� �ҷ� �ɴϴ�.
    	int row = table_hotkey.getSelectedRow();
    	//int col = table_hotkey.getColumnCount();
    	
    	//���� �̿��ؼ� ���� ������ �ҷ� �ɴϴ�. �ʿ��� �÷� 1 ���ڵ� // 4 �ǸŰ�
    	Vector<String> temp = new Vector<String>();
    	//for(int i = 0; i < col; i++){
    	//������ ���� �ʿ��� ���� �ҷ� �ɴϴ�.
    	String barcode = dtm_hotkey.getValueAt(row, 1).toString();
    	String sellpri = dtm_hotkey.getValueAt(row, 4).toString();
    	
    	if(sellpri.equals("")){
    		sellpri = "0";
    	}
    	
    	System.out.println(barcode+" / "+sellpri);
    	//}    	
    	   	
    	//������ �ۼ��մϴ�.
    	String[] query_won = new String[2];
    	query_won[0] = "Update Hot_Key Set H_SellPri='"+sellpri+"' Where H_Barcode='"+barcode+"' ";
    	query_won[1] = "Update Goods_Info Set ShoppingMall_Use='1', Shop_View='1', Edit_Tran='1' Where Barcode='"+barcode+"' ";    	
    	//������ �����մϴ�.
    	ms_connect.setMainSetting();
    	int result = ms_connect.connect_update(query_won);
    	
    	switch(result){
    	case 0:
    		//���� ���� �Ǿ �ٷ� �ø��ϴ�.
    		if(Server_Config.getGOODS_TRANYN().equals("1")){
    			new TransferDataGoodsSet(ms_connect, this, barcode);
    		}    		
    		break;
    	case 1:
    		JOptionPane.showMessageDialog(this, ms_connect.errMsg);
    		break;
    	default:
    		
    		break;
    	}
    	
    	this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }
    
        
    //��Ű ������ �����մϴ�.
    public void setHotKeyOrderSave(){
    
    	this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
    	
    	//��Ű����Ʈ�� ������ �����մϴ�.
    	//����Ʈ�� �Ѱ����� �ҷ��ɴϴ�.
    	int row_total = table_hotkey.getRowCount();
    	
    	System.out.println(row_total);    	
    	//�� ����Ʈ�� ������ �ҷ� ���� ������ ���մϴ�.
    	ArrayList<String> query_list = new ArrayList<String>();    	
    	
    	for(int i = 1; i <= row_total; i++){
    		//������ �ۼ��մϴ�.
    		String query = "Update Hot_Key Set H_Order='"+i+"' Where H_Barcode='"+dtm_hotkey.getValueAt(i-1, 1).toString()+"' ";    		
    		query_list.add(query);
    	}    	
    	//������ �����մϴ�.
    	int result = ms_connect.connect_update(query_list);
    	
    	//����ó���� �մϴ�.
    	switch(result){
    	case 0:
    		JOptionPane.showMessageDialog(this, "����� ������ ���� �Ǿ����ϴ�.");
    		break;
    	default:
    		JOptionPane.showMessageDialog(this, ms_connect.errMsg);
    		break;
    	}
    	
    	this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }
    
    
    public class HotkeyTableCellRenderer extends JLabel implements TableCellRenderer {
	
    	/**
		 * 
		 */
		private static final long serialVersionUID = 842451231545L;

		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, final int row, int column) { 
   		
    		setHorizontalAlignment(JLabel.RIGHT);    		
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
        		//System.out.println("�ʵ尪 : "+value);        			 
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
    
    //ftp �̹����� ���� �մϴ�.
    public void deleteFtpImage(){
    	    	
    	this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
    	int row = table.getSelectedRow();		
    	if(row < 0) JOptionPane.showMessageDialog(this, "��ǰ�� ������ �ּ���");
    	
    	String barcode = (String)dtm.getValueAt(row, 1);
		String path_gubun = (String)dtm.getValueAt(row, 21);
		String path = (String)dtm.getValueAt(row, 22);
		System.out.println(path_gubun);
		
		if(path == null || "".equals(path)){ 
			JOptionPane.showMessageDialog(this, "������ �̹����� �����ϴ�.");
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			return;
		}
		
		if(path.equals("http://14.38.161.45:7080/main_goods/noImage.jpg")){
			JOptionPane.showMessageDialog(this, "�⺻ �̹����� ���� �Ҽ� �����ϴ�.");
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			return;
		}
		
		int result = JOptionPane.showConfirmDialog(this, "������ ���� �Ͻðڽ��ϱ�?", "�����̹��� ���� ",  JOptionPane.OK_CANCEL_OPTION);    	
    	if(result!= 0){
    		this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    		return;
    	}
		    	
		try{			
			if( path.length() > 0){				
				if(path_gubun.equals("�ܵ�����")){
					//������ ���� �մϴ�.
					
					//ftp������ �����մϴ�.
					try {
						FTPClient ftpConn = new FTPClient();
						
						ftpConn.connect(Server_Config.getFTPIP(), Server_Config.getFTPPORT());
						ftpConn.login(Server_Config.getFTPID(), Server_Config.getFTPPW());				
						
						path = path.trim();
						String path_ext = path.trim().substring(path.length()-3, path.length());
						
						ftpConn.changeDirectory(Server_Config.getFTPMARTPATH());
						
						//������ ����Ǿ� �ִ� ������ ���� �մϴ�. 
						ftpConn.deleteFile(barcode.trim()+"."+path_ext);
						ftpConn.disconnect(true);
						System.out.println("�����Ϸ� �Ǿ����ϴ�.");
						//�̹������ �����ϱ� ���� ������ ��� �Ӵϴ�.
						String query_ftp = "Delete From FTP_Image Where Barcode = '"+barcode+"' and path_gubun='0' and path='"+Server_Config.getFTPMARTPATH()+"' ";						
						String query_delete = "Update Goods_info set Edit_Tran='1', Img_Name='noImage', img_path='http://14.38.161.45:7080/main_goods/noImage.jpg', img_path_use='1' where Barcode='"+barcode+"' ";
						
						System.out.println(query_ftp);					
						System.out.println(query_delete);
						
						//�̹��� ������ 
						ms_connect.setImageSetting();
						ms_connect.connect_update(query_ftp);					
						
						ms_connect.setMainSetting();
						ms_connect.connect_update(query_delete);			
						
						System.out.println("�⺻ ���� ���� ����");
					} catch (IllegalStateException | IOException | FTPIllegalReplyException | FTPException e) {
						// TODO Auto-generated catch block					
						e.printStackTrace();
						JOptionPane.showMessageDialog(this, "FTP�� ���� �̹����� �������� ���߽��ϴ�. . \r\n �������� : "+e.getMessage());						
					}
				}else{
					//���������� ���������� ftp�ּҸ� ���� �մϴ�.																				
					String query_delete = "Update Goods_info set Edit_Tran='1', Img_Name='noImage', img_path='http://14.38.161.45:7080/main_goods/noImage.jpg' where Barcode='"+barcode+"' ";
					System.out.println(query_delete);					
					ms_connect.setMainSetting();
					ms_connect.connect_update(query_delete);
				}
				
			}
		}catch(NullPointerException e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "����� �̹����� �����ϴ�.");
			return;
		}
		
		JOptionPane.showMessageDialog(this, "���� �Ϸ� �Ǿ����ϴ�.");
		
		detail_Renew();		
		//�˻��ϱ�
		search_start();	
		
    }
    
    //���뼭���� �̹��� ������ �մϴ�. �н����带 Ȯ���� �մϴ�.
    private void setImageServerUploadFTP(){
    	
    	JPanel panel = new JPanel();		
		JLabel label = new JLabel("��й�ȣ : ");
		JPasswordField pass = new JPasswordField(10);
		pass.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					pass.transferFocus();						
				}
			}
		});
		
		panel.add(label);
		panel.add(pass);
		
		String[] options = new String[]{"Ȯ��", "���"};			
		int option = JOptionPane.showOptionDialog(this, panel, "������ �̹��� ���� ���ε�",
		                         JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
		                         null, options, pass);
		if(option == 0) // pressing OK button
		{
		    char[] password = pass.getPassword();
		    if(new String(password).equals("tips0945")){
		    	ftp_Connect();
		    }else{
		    	JOptionPane.showMessageDialog(this, "��й�ȣ�� �߸� �Է� �ϼ̽��ϴ�.", "��й�ȣ ����ġ", JOptionPane.CLOSED_OPTION);
		    	return;
		    }
		}    	
    }
    
    
    
	class ChkboxItemListioner_Top implements ItemListener{

		@Override
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
			System.out.println("TOP �Դϴ�.");
			JCheckBox jcb = (JCheckBox)e.getItem();
			System.out.println(e.getStateChange());
			if(e.getStateChange() == 1){
				if( jcb.getName().equals("none")){
					for(int index = 0; index < chkboxs_top.size(); index++){
						JCheckBox jcb_list = chkboxs_top.get(index);
						if(!jcb_list.getName().equals("none")){
							jcb_list.setSelected(false);
						}
					}
				}else{
					for(int index = 0; index < chkboxs_top.size(); index++){
						JCheckBox jcb_list = chkboxs_top.get(index);
						if(jcb_list.getName().equals("none") && jcb_list.isSelected()){
							jcb_list.setSelected(false);
						}
					}
				}
			}
		}		
	}
	
	class ChkboxItemListioner_Detail implements ItemListener{

		@Override
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
			System.out.println("DETAIL �Դϴ�.");
			JCheckBox jcb = (JCheckBox)e.getItem();
			System.out.println(e.getStateChange());
			if(e.getStateChange() == 1){
				if( jcb.getName().equals("none")){
					for(int index = 0; index < chkboxs_detail.size(); index++){
						JCheckBox jcb_list = chkboxs_detail.get(index);
						if(!jcb_list.getName().equals("none")){
							jcb_list.setSelected(false);
						}
					}
				}else{
					for(int index = 0; index < chkboxs_detail.size(); index++){
						JCheckBox jcb_list = chkboxs_detail.get(index);
						if(jcb_list.getName().equals("none") && jcb_list.isSelected()){
							jcb_list.setSelected(false);
						}
					}
				}
			}
		}		
	}

	
	class ChkboxItemListioner_AllChange implements ItemListener{

		@Override
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
			System.out.println("AllChange �Դϴ�.");
			
			JCheckBox jcb = (JCheckBox)e.getItem();		
			//System.out.println(e.getStateChange());
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
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		String command = e.getActionCommand();
		//�˻���� �˻� �մϴ�.
		switch(command){		
		case "�̹����˻�":
			//setSearchGoodsName();	
			jtap_FTPSearch();
			break;		
		case "�����˻�":			
			jtap_PCSearch();
			break;
		case "��":
			setImageUpCount();
			break;
		case "�ٿ�":
			setImageDownCount();
			break;
		case "OK":			
			if(table.getRowCount() > 0){
				setAllSave();
			}else{
				JOptionPane.showMessageDialog(this, "��Ͽ��� ��ǰ�� ������ �ּ���!");
			}
			break;		
		case "Cancel":
			if(thread.isAlive()){
				thread.stop();
			}			
			btnChange(false);
			break;
		case "Image":
			if(table.getRowCount() <= 0){				
				JOptionPane.showMessageDialog(this, "��Ͽ��� ��ǰ�� ������ �ּ���!");
			}				
			setFtp_Image_select();
			break;
		case "HotKey_Search":
			getHotKeyGoodsList();
			break;
		case "HotKey_Down":
			setHotKeyMapDown();
			break;
		case "HotKey_Up":
			setHotKeyMapUp();
			break;
		case "HotKey_Group":
			break;
		case "HotKey_ListCall":
			setHotKeyListSave();
			break;
		case "HotKey_Del":
			//����� ��ǰ�� ���� �մϴ�.
			deleteHotkyeList();
			break;
		case "HotKey_Save":
			setHotKeyOrderSave();
			break;
		case "HotKey_ListUp":
			setHotkeyListUpDown("UP");
			break;
		case "HotKey_ListDown":
			setHotkeyListUpDown("DOWN");
			break;
		case "FTP_ImageDelete":			
			deleteFtpImage();			
			break;
		}
	}
	
//��
}


	

