/*import it.sauronsoftware.ftp4j.FTPAbortedException;
import it.sauronsoftware.ftp4j.FTPClient;
import it.sauronsoftware.ftp4j.FTPDataTransferException;
import it.sauronsoftware.ftp4j.FTPException;
import it.sauronsoftware.ftp4j.FTPFile;
import it.sauronsoftware.ftp4j.FTPIllegalReplyException;
import it.sauronsoftware.ftp4j.FTPListParseException;
import it.sauronsoftware.ftp4j.FTPListParser;*/

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultRowSorter;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.border.BevelBorder;

import com.oroinc.net.ftp.FTPClient;
import com.oroinc.net.ftp.FTPFile;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.FlowLayout;
import net.miginfocom.swing.MigLayout;

public class Goods_Manage extends JPanel {

	private static final long serialVersionUID = 1L;
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
	
	private DefaultTableModel dtm; 
		
	Ms_Connect ms_connect;
	private JTextField tx_officecode;
		
	private AllChangeDialog changeDialog = null;
	
	
	/**
	 * Create the panel.
	 */
	public Goods_Manage() {
		//super(title, resize, close);
		ms_connect = new Ms_Connect();    	    
        
		//contentPane_Goods = this;		
		setBorder(new EmptyBorder(5, 5, 5, 5));
		
		//this.add(contentPane_Goods, BorderLayout.CENTER);
				
		//setBounds(0, 0, 1024, 690);		
		//javax.swing.plaf.InternalFrameUI ifu = this.getUI(); ((javax.swing.plaf.basic.BasicInternalFrameUI)ifu). setNorthPane(null); 
		
		//상단 검색조건
		top_search();
		
		//검색목록
		goods_list();
		
		//상품상세정보 및 수정 창
		goods_detail();		
				
	}

	/*
	 * 	 * [검색 항목 초기화 ]
	 * 검색 항목 초기화 하기
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
		
		
		chkeck_top_anstock.setSelected(false);
		
		dtm.setRowCount(0);
		
		tx_barcode.requestFocus();
		
		detail_Renew();
	}
	
	
	/*
	 * [상품저장버튼]
	 * 변경된 부분 읽어와서 저장합니다.
	 */
	private void saveGoodsInfo(){
		
		String query1 = "Update Goods Set ";
		
		try{
			int number = Integer.parseInt(label_Detail_Number.getText());
		}catch(NumberFormatException e){
			JOptionPane.showMessageDialog(this, "상품을 선택해 주세요!!");
			detail_Renew();
			return;
		}
		
		String barcode= text_Detail_Barcode.getText();
		String g_name = text_Detail_Name.getText();
		String an_stock = text_Detail_AnStock.getText();
		System.out.println(g_name.length());
		
		query1 += "G_Name='"+g_name+"', Pro_Sto='"+an_stock+"' ";		
		query1 += "Where Barcode='"+barcode+"'; ";
		
		
		String query2 = "Update Goods_Info Set ";
		
		String shop_use="1";		
		if(combo_Detail_ShopConnectUse.getSelectedIndex() == 1){
			shop_use="0";
		}
		
		String shop_view = "1";
		if(combo_Detail_View.getSelectedIndex() == 1){
			shop_view="0";
		}
		
		String sto_use="0";		
		if(checkBox_Detail_Stock.isSelected()){
			sto_use="1";
		}
		
		String img_path_use = String.valueOf(combox_Detail_ImageConnectUse.getSelectedIndex());
		
		String img_path = text_Detail_ImagePath.getText();
		
		//System.out.println(shop_use + shop_view + sto_use + img_path_use);
		
		query2 += "ShoppingMall_use='"+shop_use+"',  Shop_View='"+shop_view+"', Sto_Use='"+sto_use+"', Img_path_use='"+img_path_use+"', img_path='"+img_path+"', ";
		
		query2 += " Edit_Tran='1' where Barcode='"+barcode+"'; ";
		
		String[] query_multi = {query1, query2};
				
		ms_connect.setMainSetting();
		int result = ms_connect.connect_update(query_multi);
				
		switch(result){
		case 0 :
			detail_Renew();
			JOptionPane.showMessageDialog(null, "저장이 완료 되었습니다.");
			//상품검색 시작
			search_goods(query);
			
			break;
		default :
			JOptionPane.showMessageDialog(null, "오류로 저장하지 못했습니다. \r\n 서버를 점검해 주세요!!");
			break;
		}
		
	}
	
	
	//상세정보 초기화
	private void detail_Renew(){		
		
		label_Detail_Number.setText("순번");
		text_Detail_Barcode.setText("");
		text_Detail_AnStock.setText("");
		text_Detail_Name.setText("");
		text_Detail_Category.setText("");
		text_Detail_PurPri.setText("");
		text_Detail_SellPri.setText("");
		text_Detail_Stock.setText("");
		
		checkBox_Detail_Stock.setSelected(false);
		
		btnToggle_Detail_SaleUse.setSelected(false);
		btnToggle_Detail_SaleUse.setText("행사유무");
		btnToggle_Detail_ConnectState.setSelected(false);
		btnToggle_Detail_ConnectState.setText("연동유무");
		
		combo_Detail_ShopConnectUse.setSelectedIndex(0);
		combo_Detail_View.setSelectedIndex(0);
		combox_Detail_ImageConnectUse.setSelectedIndex(0);
		
		label_image_view.setIcon(null);
		text_Detail_ImagePath.setText("");
		text_Detail_ImagePath.setEnabled(true);
		
	}
	    
    
    //상단 검색 조건
    private void top_search(){
    	setLayout(new BorderLayout(0, 0));
    	    	
    	JPanel p_top = new JPanel();
		p_top.setBorder(new LineBorder(new Color(0, 0, 0)));
		this.add(p_top, BorderLayout.NORTH);
		p_top.setLayout(new MigLayout("", "[50px,grow][]", "[grow][fill][24px,grow]"));
		
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
		tx_gname.setColumns(25);
		
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
				
				/*
				 * [거래처 검색]
				 * 
				 */
				new OfficeSearch(tx_officecode, tx_officename);
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
				
			}
		
		});
		
		JButton bt_renew = new JButton("\uC0C8\uB85C\uC785\uB825");
		p_top.add(bt_renew, "cell 1 1,growx,aligny bottom");
		
		bt_renew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//새로고침
				new_search();				
			}
		});
		
		JPanel panel_2 = new JPanel();
		p_top.add(panel_2, "cell 0 2,grow");
		panel_2.setLayout(new MigLayout("", "[][][][][][][][][][][][][]", "[][]"));
		
		JLabel lb_goodsconnect = new JLabel("\uC0C1\uD488\uC5F0\uB3D9");
		panel_2.add(lb_goodsconnect, "flowy,cell 0 0");
		lb_goodsconnect.setToolTipText("\uB9E4\uC7A5\uC0C1\uD488\uC744 \uC1FC\uD551\uBAB0\uACFC \uC5F0\uB3D9\uC5EC\uBD80\uB97C \uC120\uD0DD\uD569\uB2C8\uB2E4.");
		
		cb_shoppingmall_use = new JComboBox<String>();
		panel_2.add(cb_shoppingmall_use, "cell 1 0");
		cb_shoppingmall_use.setToolTipText("\uB9E4\uC7A5\uC0C1\uD488\uC744 \uC1FC\uD551\uBAB0\uACFC \uC5F0\uB3D9\uC5EC\uBD80\uB97C \uC120\uD0DD\uD569\uB2C8\uB2E4.");
		cb_shoppingmall_use.setModel(new DefaultComboBoxModel<String>(new String[] {"\uC804\uCCB4", "\uC5F0\uB3D9\uD568", "\uC5F0\uB3D9\uC548\uD568"}));
		
		JLabel lb_shoppingmall = new JLabel(" \uC1FC\uD551\uBAB0 ");
		panel_2.add(lb_shoppingmall, "cell 3 0,alignx center");
		lb_shoppingmall.setToolTipText("\uB9E4\uC7A5 \uC0C1\uD488\uC774 \uC1FC\uD551\uBAB0\uACFC \uC5F0\uB3D9 \uC911\uC778\uC9C0 \uC5EC\uBD80\uB97C \uD655\uC778 \uD569\uB2C8\uB2E4.");
		lb_shoppingmall.setHorizontalAlignment(SwingConstants.CENTER);
		
		cb_upload = new JComboBox<String>();
		panel_2.add(cb_upload, "cell 4 0");
		cb_upload.setToolTipText("\uB9E4\uC7A5 \uC0C1\uD488\uC774 \uC1FC\uD551\uBAB0\uACFC \uC5F0\uB3D9 \uC911\uC778\uC9C0 \uC5EC\uBD80\uB97C \uD655\uC778 \uD569\uB2C8\uB2E4.");
		cb_upload.setModel(new DefaultComboBoxModel<String>(new String[] {"\uC804\uCCB4", "\uC5C5\uB85C\uB4DC\uB428", "\uC5C5\uB85C\uB4DC\uC548\uB428"}));
		
		JLabel lb_imageconnect = new JLabel("\uC774\uBBF8\uC9C0\uC5F0\uB3D9");
		panel_2.add(lb_imageconnect, "cell 6 0");
		lb_imageconnect.setToolTipText("\uACF5\uC6A9 \uD3F4\uB354\uC758 \uC774\uBBF8\uC9C0\uC640 \uC5F0\uB3D9\uC774 \uB418\uC5B4\uC788\uB294\uC9C0 \uD655\uC778 \uD569\uB2C8\uB2E4.");
		
		cb_image_connect = new JComboBox<String>();
		panel_2.add(cb_image_connect, "cell 7 0");
		cb_image_connect.setToolTipText("\uACF5\uC6A9 \uD3F4\uB354\uC758 \uC774\uBBF8\uC9C0\uC640 \uC5F0\uB3D9\uC774 \uB418\uC5B4\uC788\uB294\uC9C0 \uD655\uC778 \uD569\uB2C8\uB2E4.");
		cb_image_connect.setModel(new DefaultComboBoxModel(new String[] {"\uC804\uCCB4", "\uACF5\uC6A9\uD3F4\uB354", "\uB2E8\uB3C5\uD3F4\uB354"}));
		
		JLabel lblNewLabel = new JLabel("\uC774\uBBF8\uC9C0\uC720\uBB34");
		panel_2.add(lblNewLabel, "cell 9 0");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		combo_image_get = new JComboBox<String>();
		panel_2.add(combo_image_get, "cell 10 0");
		combo_image_get.setModel(new DefaultComboBoxModel<String>(new String[] {"\uC804\uCCB4", "\uC774\uBBF8\uC9C0\uC5C6\uC2B4", "\uC774\uBBF8\uC9C0\uC788\uC2B4"}));
		combo_image_get.setMaximumRowCount(3);
		
		JLabel lb_goodssale = new JLabel("\uD589\uC0AC");
		panel_2.add(lb_goodssale, "cell 0 1,alignx center");
		lb_goodssale.setToolTipText("\uB9E4\uC7A5 \uC0C1\uD488\uC911 \uD589\uC0AC \uC9C4\uD589\uC0C1\uD488\uC744 \uD45C\uC2DC\uD569\uB2C8\uB2E4.");
		lb_goodssale.setHorizontalAlignment(SwingConstants.CENTER);
		
		cb_Sale_Goods = new JComboBox<String>();
		panel_2.add(cb_Sale_Goods, "cell 1 1");
		cb_Sale_Goods.setToolTipText("\uB9E4\uC7A5 \uC0C1\uD488\uC911 \uD589\uC0AC \uC9C4\uD589\uC0C1\uD488\uC744 \uD45C\uC2DC\uD569\uB2C8\uB2E4.");
		cb_Sale_Goods.setModel(new DefaultComboBoxModel<String>(new String[] {"\uC804\uCCB4", "\uC9C4\uD589\uC911", "\uC9C4\uD589\uC548\uD568"}));
		
		JLabel label_Goods_Gubun = new JLabel("\uC0C1\uD488\uAD6C\uBD84");
		panel_2.add(label_Goods_Gubun, "cell 3 1");
		label_Goods_Gubun.setToolTipText("\uACF5\uC0B0\uD488/\uC800\uC6B8\uC0C1\uD488/\uBD80\uBD84\uC0C1\uD488\uC744 \uAD6C\uBD84\uD558\uB294 \uC870\uD68C \uC635\uC158\uC785\uB2C8\uB2E4.");
		label_Goods_Gubun.setHorizontalAlignment(SwingConstants.CENTER);
		
		combo_Goods_Gubun = new JComboBox<String>();
		panel_2.add(combo_Goods_Gubun, "cell 4 1,growx");
		combo_Goods_Gubun.setToolTipText("\uACF5\uC0B0\uD488/\uC800\uC6B8\uC0C1\uD488/\uBD80\uBD84\uC0C1\uD488\uC744 \uAD6C\uBD84\uD569\uB2C8\uB2E4.");
		combo_Goods_Gubun.setModel(new DefaultComboBoxModel<String>(new String[] {"\uC804\uCCB4", "\uACF5\uC0B0\uD488", "\uC800\uC6B8\uC0C1\uD488", "\uBD80\uBD84\uC0C1\uD488"}));
		combo_Goods_Gubun.setMaximumRowCount(4);
		
		JLabel label_ = new JLabel("\uC9C4\uC5F4\uC5EC\uBD80");
		panel_2.add(label_, "cell 6 1,alignx center");
		label_.setToolTipText("\uC1FC\uD551\uBAB0\uC5D0 \uC0C1\uD488\uC744 \uBCF4\uC774\uAC8C \uD560\uC9C0\uB97C \uC120\uD0DD\uD558\uB294 \uC635\uC165\uC785\uB2C8\uB2E4.");
		label_.setHorizontalAlignment(SwingConstants.CENTER);
		
		combo_view_use = new JComboBox<String>();
		panel_2.add(combo_view_use, "cell 7 1");
		combo_view_use.setModel(new DefaultComboBoxModel<String>(new String[] {"\uC804\uCCB4", "\uC9C4\uC5F4\uD568", "\uC9C4\uC5F4\uC548\uD568"}));
		combo_view_use.setMaximumRowCount(3);
		
		JLabel label_top_stock = new JLabel("\uC7AC\uACE0\uC0AC\uC6A9");
		panel_2.add(label_top_stock, "cell 9 1,alignx center");
		
		combo_top_stockuse = new JComboBox<String>();
		panel_2.add(combo_top_stockuse, "cell 10 1,growx");
		combo_top_stockuse.setToolTipText("\uC1FC\uD551\uBAB0\uACFC \uC7AC\uACE0 \uC5F0\uB3D9\uC744 \uD558\uB294\uC9C0 \uC5EC\uBD80 \uC120\uD0DD \uAC80\uC0C9");
		combo_top_stockuse.setModel(new DefaultComboBoxModel<String>(new String[] {"\uC804\uCCB4", "\uC7AC\uACE0\uC0AC\uC6A9", "\uC7AC\uACE0\uC548\uD568"}));
		combo_top_stockuse.setMaximumRowCount(3);
		
		chkeck_top_anstock = new JCheckBox("\uC548\uC804\uC7AC\uACE0 \uC774\uD558\uC0C1\uD488");
		panel_2.add(chkeck_top_anstock, "cell 12 1");
		chkeck_top_anstock.setHorizontalAlignment(SwingConstants.CENTER);
		
		bt_search = new JButton("\uC0C1\uD488\uAC80\uC0C9");
		p_top.add(bt_search, "cell 1 2,grow");
		
		bt_search.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				//검색하기
				search_start();				
			}
		});
		
		/*
		 *  [상품검색 버튼]
		 *  - 거래처명 분류 상품명 바코드 연동유무 검색 조건이 됩니다.
		 *  
		 */
    	
    }
      
    /*
     * [검색을 시작합니다.]
     * - 검색하기
     */
    private void search_start(){
    	
    	dtm.setRowCount(0);
		
		//검색 초기화
		query_goods = "";
		query_info = "";
		
		//바코드 입력 유무
		if(tx_barcode.getText().trim().length() > 0){
			if(chkeck_barcode_front.isSelected()){
				query_goods += "and barcode like '"+tx_barcode.getText()+"%' ";
			}else{
				query_goods += "and barcode like '%"+tx_barcode.getText()+"%' ";
			}
		}
		
		//상품명 입력 유무
		if(tx_gname.getText().trim().length() > 0){
			query_goods += "and g_name like '%"+tx_gname.getText()+"%' ";
		}
		
		//거래처명 입력 유무
		if(tx_officename.getText().trim().length() > 0){
			query_goods += "and bus_name like '%"+tx_officename.getText()+"' ";
		}
		
		//거래처코드 입력 유무
		if(tx_officecode.getText().trim().length() > 0){
			query_goods += "and bus_code like '%"+tx_officecode.getText()+"' ";
		}
		
		//대코드입력유무
		if(tx_Lcode.getText().trim().length() > 0){
			query_goods += "and L_Code='"+tx_Lcode.getText()+"' ";
		}
		
		//중코드입력유무
		if(tx_Mcode.getText().trim().length() > 0){
			query_goods += "and M_Code='"+tx_Mcode.getText()+"' ";
		}
		
		//소코드입력유무
		if(tx_Scode.getText().trim().length() > 0){
			query_goods += "and S_Code='"+tx_Scode.getText()+"' ";
		}
				
		
		//공산품/부분상품/저울상품 combo_Goods_Gubun
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
						
		//연동현황 유무 설정 하기
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
		
		//이미지관리폴더설정
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
		
		//이미지 존재 여부
		switch(combo_image_get.getSelectedIndex()){	
		case 1:
			query_info += "and (img_path is null or img_path = '') ";			
			break;
		case 2:
			query_info += "and img_path <> ' ' ";
			break;
		}		
		
		//연동 유무 설정 하기
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
		
		//세일관리
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
		
		//진열유무
		switch(combo_view_use.getSelectedIndex()){
		case 1:
			query_info += "and shop_view='1' ";
			break;
			
		case 2:
			query_info += "and shop_view='0' ";
			break;
		}
		
		//안전재고검색
		if(chkeck_top_anstock.isSelected()){			
			query_goods += "and real_sto <= pro_sto ";			
		}
		
		switch(combo_top_stockuse.getSelectedIndex()){
		case 1:
			query_info += "and sto_use='1' ";			
			break;
		case 2:
			query_info += "and sto_use='0' ";			
			break;			
		}
			
		
		
		
		//검색쿼리 초기화
		search_query();
		
		//상품검색 시작
		search_goods(query);
		
    }
    
    
    
	/*
	 * [상품검색]
	 * 조건을 확인 해서 상품 검색을 합니다.
	 * 상품 검색 후 Vector 사용 하여 변경 합니다.
	 * dtm에 저장합니다.
	 * 결과 값으로 true를 반환 합니다.
	 *  {"선택", "순번", "바코드", "상품명", "규격", "매입가", "판매가", "상품사용", "업로드", "단독이미지","이미지유무", "행사"};
	 */
	public boolean search_goods(String query){
		
		ms_connect.setMainSetting();
		ArrayList<HashMap<String, String>> al = ms_connect.connection(query);	
		
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
								
				//"0 순번", "1 바코드", "2 상품명", "3 규격", "4 매입가", "5 판매가", "6 현재고", "7 안전재고", 
				// "8 분류코드", "9 대코드", "10 대명", "11 중코드", "12 중명", "13 소코드", "14 소명", "15 행사", "16 상품연동",
				//"`17 쇼핑몰", "`18 진열유무", "`19 재고연동", "20 이미지설정", "21 이미지경로" };
				
				v.add(String.valueOf(i+1)); //0. 순번
				v.add(map.get("barcode")); //1. 바코드
				v.add(map.get("g_name")); //2. 상품명
				v.add(map.get("std_size")); //3. 규격
				v.add(map.get("pur_pri")); //4. 매입가
				v.add(map.get("sell_pri")); //5. 판매가
				v.add(map.get("real_sto"));//6. 현재고
				v.add(map.get("pro_sto"));//7. 안전재고
				
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
				
				v.add(office_code);		//8. 분류코드		
				
				//숨긴분류
				v.add(map.get("l_code"));  //9. 대코드
				v.add(map.get("l_name"));  //10 대명
				v.add(map.get("m_code"));  //11. 중코드
				v.add(map.get("m_name"));  //12. 중명
				v.add(map.get("s_code"));  //13. 소코드
				v.add(map.get("s_name"));				 // 14. 소명
				
				if(sale_use.equals("1")){ //15. 행사진행
					v.add("행사중");
				}else{
					v.add("행사안함");
				}
				
								
				if(shoppingmall_use.equals("1")){ //16. 상품연동
					v.add("연동함"); 
				}else{
					v.add("연동안함"); 
				}
								
				if(upload.equals("1")){ //17. 쇼핑몰 업로드
					v.add("업로드됨"); 
				}else{
					v.add("업로드안됨"); 
				}
				
				v.add(map.get("shop_view")); //18. 진열여부				
				v.add(map.get("sto_use"));  //19. 재고연동유무
				
				if(img_path_use.equals("1")){ //20. 단독이미지
					v.add("공용폴더");
				}else{
					v.add("단독폴더");
				}
				
				v.add(map.get("img_path")); //21. 연동중 이미지 경로
				
				dtm.addRow(v);				
			}
		}else{
			dtm.setRowCount(0);
			JOptionPane.showMessageDialog(null, "검색 결과가 없습니다. ");			
		}		
		
		}catch(NullPointerException e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "DB 접속에 실패 했습니다. \r\n인터넷 회선 및 서버를 점검해 주세요!!");
			return false;
		}
		
		return true;
	}
		
	
	//기본 쿼리 선언
	private void search_query(){
		//query = "Select * from goods where goods_use='1' ";
		
		query = " Select a.barcode, a.g_name, a.std_size, a.pur_pri, a.sell_pri, a.real_sto, a.pro_sto, a.sale_use, a.l_code, a.l_name, a.m_code, a.m_name, "
				+ "a.s_code, a.s_name, a.bus_code, a.bus_name, a.goods_use, b.shoppingmall_use, b.upload, b. shop_view, b.sto_use, b.img_path_use, b.img_path "
				+ "From ( Select * From Goods Where L_code <> 'AA' and goods_use='1' "+query_goods+" ) a join "
				+ " (select * from goods_info where 1=1 "+query_info+" ) b "
				+ " on a.barcode=b.barcode " ;
	}
        
	
    //상품 검색 목록
    private void goods_list(){
    	
    	/**
		 * 셀 간격 조정
		 */
		DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
		celAlignCenter.setHorizontalAlignment(JLabel.CENTER);
		DefaultTableCellRenderer celAlignRight = new DefaultTableCellRenderer();
		celAlignRight.setHorizontalAlignment(JLabel.RIGHT);
		
		String[] colunm = {"순번", "바코드", "상품명", "규격", "매입가", "판매가", "현재고", "안전재고", 
				"분류코드", "대코드", "대명", "중코드", "중명", "소코드", "소명", "행사", "상품연동", "쇼핑몰", "진열유무", "재고연동", "이미지설정", "이미지경로" };
		dtm = new DefaultTableModel(null, colunm){
			/**
			 * [칼럼 수정 못하게 막기]
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int rowIndex, int mColIndex){
				return false;
			}
		};
		
    }
    
    //임시생성 ftp에 연결해서 파일을 불러와 서버DB에 Insert 합니다.
    //한번 사용하기 위해서 만든 함수 입니다.
    private void ftp_Connect(){
    	
    	FTPClient ftpclient = new FTPClient();
    	// TODO Auto-generated method stub
		// 검색 FTP 불러오기
		// 서버에 연결되어서 불러오기 입니다.
		
    /*	String serverName = "이미지 서버";
    	String ftpIP = Server_Config.getFTPIP();
    	int ftpPort = Server_Config.getFTPPORT();
    	String ftpID = Server_Config.getFTPID();
    	String ftpPW = Server_Config.getFTPPW();
    	String ftpLocalPath = Server_Config.getFTPLOCALPATH();
    	String ftpServerPath = Server_Config.getFTPSERVERPATH();*/
    	    			
    	
    	//네가게 이미지서버에서 불러왔습니다.    	
    	String serverName = "이미지 서버";
    	String ftpIP = "211.233.63.24";
    	//int ftpPort = Server_Config.getFTPPORT();
    	String ftpID = "tipos";
    	String ftpPW = "k5749948";
    	String ftpLocalPath = ".";
    	String ftpServerPath = "main_goods";
    	
    	
    	try {
    		System.out.println("접속시작 합니다.");
    		ftpclient.connect(ftpIP);    		
    		ftpclient.login(ftpID, ftpPW);
    		
    		
    		// 연결 시도후, 성공했는지 응답 코드 확인
			if (!ftpclient.isConnected()) {
				ftpclient.disconnect();
				JOptionPane.showMessageDialog(this, "접속실패!!");
				return;
			}
    		
			System.out.println("접속성공");
			
			System.out.println("폴더변경합니다 -> 폴더명 : "+ftpServerPath);
			//공용폴더선택
			ftpclient.changeWorkingDirectory(ftpServerPath);			
			
			System.out.println(ftpclient.printWorkingDirectory());
			System.out.println(ftpclient.getStatus());
									
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		} 
    	    	
    	System.out.println("파일불러오기 시작");
    	
    	String[] fileName=null;
		try {
			fileName = ftpclient.listNames();			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if(fileName.length < 0){
			JOptionPane.showMessageDialog(this, "검색된 파일이 없습니다.");
			return;
		}
    	
    	//파일불러오기 입니다.
    	/*FTPFile[] files = null;		
		try {
			files = ftpclient.listFiles();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "파일을 불러오지 못했습니다. \r\n"+e.getMessage());
			return;
		}
		System.out.println(files.length);
		*/
		System.out.println("파일을 불러왔습니다.");
		ArrayList<String> temp_image = new ArrayList<String>();
		
		System.out.println("쿼리를 작성합니다.");		
		//불러온 파일을 서버에 저장합니다.
		for(String file_temp: fileName){		
			
			String query_ftpfile = "insert into FTP_Image (Barcode, Path, Path_Gubun, G_Name, Ext) Values(";			
			String[] file_name = new String[2];			
			file_name = file_temp.split("\\.");
			// file_name[0] = 바코드 /// file_name[1] = 확장자 			
			query_ftpfile += "'"+file_name[0]+"', 'main_goods', '1', '', '"+file_name[1]+"' )";			
			temp_image.add(query_ftpfile);					
		}
		
		System.out.println("쿼리 작성이 완료 되었습니다.");
		
		//서버에 저장합니다.		
		ms_connect.setImageSetting();
		ms_connect.connect_update(temp_image);
		System.out.println(temp_image.get(0).toString());
		
		System.out.println("저장 완료 되었습니다.");
		
		if(ftpclient.isConnected()){
			try {
				ftpclient.disconnect();
				JOptionPane.showMessageDialog(this, "접속종료합니다.");
			} catch (IllegalStateException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
    	
    	
    }
    
    
    
	/*
	 * [마우스 두번 클릭]
	 * - 마우스 더블클릭 시 선택 된 상품을 우측의 상세 정보로 보냅니다.
	 * 
	 */	
	public void setGoodsDetail(){
		
		detail_Renew();
		
		// 테이블의 선택된 행의 인덱스 번호 취득
		int row = table.getSelectedRow();
		int col = table.getColumnCount();
		
		Vector<Object> temp = new Vector<Object>(); 
		
		for(int i =0; i < col; i++){			
			temp.add(dtm.getValueAt(row, i));			
		}		
		
		System.out.println(temp.toString());
		
		//"0 순번", "1 바코드", "2 상품명", "3 규격", "4 매입가", "5 판매가", "6 현재고", "7 안전재고", 
		// "8 분류코드", "9 대코드", "10 대명", "11 중코드", "12 중명", "13 소코드", "14 소명", "15 행사", "16 상품연동",
		//"`17 쇼핑몰", "`18 진열유무", "`19 재고연동", "20 이미지설정", "21 이미지경로" };
		
		label_Detail_Number.setText(temp.get(0).toString()); //순번
		text_Detail_Barcode.setText(temp.get(1).toString()); //바코드
		text_Detail_Name.setText(temp.get(2).toString());  //상품명
		
		text_Detail_PurPri.setText(temp.get(4).toString());  //매입가
		text_Detail_SellPri.setText(temp.get(5).toString());  //판매가
		text_Detail_Stock.setText(temp.get(6).toString());  //현재고
		text_Detail_AnStock.setText(temp.get(7).toString());  //안전재고
		
		text_Detail_Category.setText(temp.get(8).toString());  //분류
				
		if(temp.get(15).equals("행사중")){ //행사진행여부			
			btnToggle_Detail_SaleUse.setSelected(true); 
			btnToggle_Detail_SaleUse.setText("행사중");
		}else{
			btnToggle_Detail_SaleUse.setSelected(false);
			btnToggle_Detail_SaleUse.setText("일반");
		}		
		
		combo_Detail_ShopConnectUse.setSelectedItem(temp.get(16).toString());
		
		if(temp.get(17).equals("업로드됨")){
			btnToggle_Detail_ConnectState.setSelected(true);
			btnToggle_Detail_ConnectState.setText("연동중");
		}else{
			btnToggle_Detail_ConnectState.setSelected(false);
			btnToggle_Detail_ConnectState.setText("연동안됨");
		}
		
		if(temp.get(18).equals("1")){
			combo_Detail_View.setSelectedItem("진열함"); //진열여부
		}else{
			combo_Detail_View.setSelectedItem("진열안함"); //진열여부
		}		
		
		if(temp.get(19).equals("1")){ //재고연동
			checkBox_Detail_Stock.setSelected(true);
		}else{
			checkBox_Detail_Stock.setSelected(false);
		}
		
		if(temp.get(20).equals("단독폴더")){
			combox_Detail_ImageConnectUse.setSelectedItem("단독폴더"); //이미지 폴더선택
		}else{
			combox_Detail_ImageConnectUse.setSelectedItem("공용폴더"); //이미지 폴더선택
		}
		
		if(!temp.get(21).toString().equals("")){			
			Image image = null;
	        try {
	        	System.out.println(temp.get(21));
	            URL url = new URL(temp.get(21).toString());
	            image = ImageIO.read(url);	            
	            
	        } catch (IOException e) {
	        	e.printStackTrace();
	        }
	        
	        label_image_view.setIcon(new ImageIcon(image.getScaledInstance(150, 150, Image.SCALE_SMOOTH)));			
		}
		
		text_Detail_ImagePath.setText(temp.get(21).toString()); //이미지경로
				
		//comboBox.setSelectedItem(temp.get(index));
		
	}    
    
	
    //상품 상제 정보 및 수정
    private void goods_detail(){
    	
    	
		/*combox_Detail_ImageConnectUse.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				System.out.println(e.getActionCommand());
				if(e.getActionCommand().equals("comboBoxChanged") && e.getID() == 1001){
					
					if(combox_Detail_ImageConnectUse.getSelectedItem().equals("단독폴더")){
						text_Detail_ImagePath.setEnabled(true);						
					}else{
						text_Detail_ImagePath.setEnabled(false);
					}
				}
			}
		});*/
		
		/*
		 * [상품 수정 후 저장 버튼]
		 * 상품 수정 후 수정된 내용을 저장 합니다. 
		 * 
		 */		
		
		JPanel panel_1 = new JPanel();
		add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel_1.add(panel, BorderLayout.NORTH);
		panel.setLayout(new MigLayout("", "[81px][81px][80px,grow][81px][80px][][grow][][][][][][][][grow][][][165.00]", "[baseline]"));
		
		JButton btn_all_select = new JButton("전체선택");
		panel.add(btn_all_select, "cell 0 0,alignx left,aligny top");
		
		btn_all_select.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				table.selectAll();
			}
		});
		
		//상품 불러오기 임시 기능???
		JButton bt_ftp_connect = new JButton("불러오기");
		panel.add(bt_ftp_connect, "cell 1 0,alignx left,aligny top");
		
		bt_ftp_connect.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ftp_Connect();
			}
		});		
		bt_ftp_connect.setVisible(false);
				
				JLabel lblNewLabel_4 = new JLabel("\uC0C1\uD488 \uAC80\uC0C9 \uBAA9\uB85D");
				panel.add(lblNewLabel_4, "cell 3 0,alignx left,aligny center");
				lblNewLabel_4.setForeground(Color.BLACK);
				lblNewLabel_4.setBackground(SystemColor.textHighlight);
				lblNewLabel_4.setFont(new Font("맑은 고딕", Font.BOLD, 12));
				lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
				
				JButton bt_All_Save = new JButton("\uC77C\uAD04\uBCC0\uACBD");
				panel.add(bt_All_Save, "cell 7 0,alignx left,aligny top");
				
						bt_All_Save.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								// TODO Auto-generated method stub
								if(table.getSelectedRowCount() > 0 && table.getSelectedRow() > -1){
									
									// TODO Auto-generated method stub
									changeDialog = new AllChangeDialog(table);
									changeDialog.setModal(true);
									changeDialog.open();
															
								}else{
									JOptionPane.showMessageDialog(Goods_Manage.this, "상품을 선택해 주세요!!");
								}				
							}
						});
				
				JLabel label_Detail_Title = new JLabel("\uC0C1\uD488 \uC0C1\uC138 \uC815\uBCF4");
				panel.add(label_Detail_Title, "cell 15 0,alignx left,aligny center");
				label_Detail_Title.setFont(new Font("맑은 고딕", Font.BOLD, 12));
				label_Detail_Title.setHorizontalAlignment(SwingConstants.CENTER);
				
    	JPanel panel_goods_detail = new JPanel();
    	panel_1.add(panel_goods_detail, BorderLayout.EAST);
    	panel_goods_detail.setBorder(new LineBorder(new Color(0, 0, 0)));
    	panel_goods_detail.setLayout(new MigLayout("", "[1px][52px][5px][25px][5px][52px][5px][25px][5px][25px][5px][22px][5px][25px][5px]", "[][1px][21px][23px][21px][][25.00px][1px][30.00][30.00px][30.00px][30.00px][30.00,center][pref!][-10.00px][20.00][150px]"));
    	
    	JLabel label_Detail_Barcode = new JLabel("\uBC14\uCF54\uB4DC");
    	label_Detail_Barcode.setHorizontalAlignment(SwingConstants.CENTER);
    	panel_goods_detail.add(label_Detail_Barcode, "cell 1 1,growx,aligny center");
    	
    	text_Detail_Barcode = new JTextField();
    	text_Detail_Barcode.setEditable(false);
    	text_Detail_Barcode.setHorizontalAlignment(SwingConstants.CENTER);
    	panel_goods_detail.add(text_Detail_Barcode, "cell 3 1 3 1,growx,aligny top");
    	text_Detail_Barcode.setColumns(10);
    	
    	label_Detail_Number = new JLabel("\uC21C\uBC88");
    	label_Detail_Number.setToolTipText("\uC88C\uCE21 \uBAA9\uB85D\uC758 \uC21C\uBC88\uC785\uB2C8\uB2E4.");
    	label_Detail_Number.setHorizontalAlignment(SwingConstants.CENTER);
    	panel_goods_detail.add(label_Detail_Number, "cell 9 1,growx,aligny center");
    	
    	JLabel label_Detail_Name = new JLabel("\uC0C1\uD488\uBA85");
    	label_Detail_Name.setHorizontalAlignment(SwingConstants.CENTER);
    	panel_goods_detail.add(label_Detail_Name, "cell 1 2,growx,aligny center");
    	
    	text_Detail_Name = new JTextField();
    	panel_goods_detail.add(text_Detail_Name, "cell 3 2 7 1,growx,aligny top");
    	text_Detail_Name.setColumns(10);
    	
    	JLabel label_Detail_Category = new JLabel("\uBD84   \uB958");
    	label_Detail_Category.setHorizontalAlignment(SwingConstants.CENTER);
    	panel_goods_detail.add(label_Detail_Category, "cell 1 3,growx,aligny center");
    	
    	text_Detail_Category = new JTextField();
    	text_Detail_Category.setEditable(false);
    	text_Detail_Category.setHorizontalAlignment(SwingConstants.CENTER);
    	panel_goods_detail.add(text_Detail_Category, "cell 3 3 9 1,alignx left,aligny center");
    	text_Detail_Category.setColumns(10);
    	
    	btnToggle_Detail_SaleUse = new JToggleButton("\uD589\uC0AC\uC5EC\uBD80");
    	btnToggle_Detail_SaleUse.setEnabled(false);
    	panel_goods_detail.add(btnToggle_Detail_SaleUse, "cell 13 3,growx,aligny top");
    	
    	JLabel label_Detail_PurPri = new JLabel("\uB9E4\uC785\uAC00");
    	label_Detail_PurPri.setHorizontalAlignment(SwingConstants.CENTER);
    	panel_goods_detail.add(label_Detail_PurPri, "cell 1 4,growx,aligny center");
    	
    	text_Detail_PurPri = new JTextField();
    	text_Detail_PurPri.setEditable(false);
    	text_Detail_PurPri.setHorizontalAlignment(SwingConstants.RIGHT);
    	panel_goods_detail.add(text_Detail_PurPri, "cell 3 4 3 1,growx,aligny top");
    	text_Detail_PurPri.setColumns(10);
    	
    	JLabel label_Detail_SellPri = new JLabel("\uD310\uB9E4\uAC00");
    	label_Detail_SellPri.setHorizontalAlignment(SwingConstants.CENTER);
    	panel_goods_detail.add(label_Detail_SellPri, "cell 7 4 3 1,growx,aligny center");
    	
    	text_Detail_SellPri = new JTextField();
    	text_Detail_SellPri.setEditable(false);
    	text_Detail_SellPri.setHorizontalAlignment(SwingConstants.RIGHT);
    	panel_goods_detail.add(text_Detail_SellPri, "cell 11 4 3 1,growx,aligny top");
    	text_Detail_SellPri.setColumns(10);
    	
    	JLabel label_Detail_Stock = new JLabel("\uD604\uC7AC\uACE0");
    	label_Detail_Stock.setHorizontalAlignment(SwingConstants.CENTER);
    	panel_goods_detail.add(label_Detail_Stock, "cell 1 5,growx,aligny center");
    	
    	text_Detail_Stock = new JTextField();
    	text_Detail_Stock.setHorizontalAlignment(SwingConstants.CENTER);
    	text_Detail_Stock.setEditable(false);
    	panel_goods_detail.add(text_Detail_Stock, "cell 3 5 3 1,growx,aligny center");
    	text_Detail_Stock.setColumns(10);
    	
    	JLabel label_Detail_AnStock = new JLabel("\uC548\uC804\uC7AC\uACE0");
    	label_Detail_AnStock.setHorizontalAlignment(SwingConstants.CENTER);
    	panel_goods_detail.add(label_Detail_AnStock, "cell 7 5 3 1,alignx center,aligny center");
    	
    	text_Detail_AnStock = new JTextField();
    	text_Detail_AnStock.setHorizontalAlignment(SwingConstants.CENTER);
    	panel_goods_detail.add(text_Detail_AnStock, "cell 11 5,growx,aligny center");
    	text_Detail_AnStock.setColumns(10);
    	
    	checkBox_Detail_Stock = new JCheckBox("\uC7AC\uACE0\uC0AC\uC6A9");
    	panel_goods_detail.add(checkBox_Detail_Stock, "cell 13 5,alignx right,aligny top");
    	
    	JSeparator separator = new JSeparator();
    	panel_goods_detail.add(separator, "cell 1 7 14 1,growx,aligny top");
    	
    	JLabel label_Detail_ShopConnectUse = new JLabel("\uC1FC\uD551\uBAB0\uC5F0\uB3D9");
    	label_Detail_ShopConnectUse.setToolTipText("\uD604\uC7AC \uC0C1\uD488\uC744 \uC1FC\uD551\uBAB0\uACFC \uC5F0\uB3D9\uC5EC\uBD80\uB97C \uC120\uD0DD \uD569\uB2C8\uB2E4.\r\n\uD604\uC7AC \uC0C1\uD488\uACFC \uC5F0\uB3D9\uC911\uC5D0 \uC774 \uC635\uC158\uC744 \uBCC0\uACBD\uD558\uBA74 \uC1FC\uD551\uBAB0 \uC0C1\uD488\uC774 \uC9C4\uC5F4\uC554\uD568\uC73C\uB85C \uBCC0\uACBD \uB429\uB2C8\uB2E4.");
    	label_Detail_ShopConnectUse.setHorizontalAlignment(SwingConstants.CENTER);
    	panel_goods_detail.add(label_Detail_ShopConnectUse, "cell 1 9 3 1,growx,aligny center");
    	
    	combo_Detail_ShopConnectUse = new JComboBox<String>();
    	combo_Detail_ShopConnectUse.setModel(new DefaultComboBoxModel<String>(new String[] {"\uC5F0\uB3D9\uD568", "\uC5F0\uB3D9\uC548\uD568"}));
    	combo_Detail_ShopConnectUse.setMaximumRowCount(2);
    	combo_Detail_ShopConnectUse.setToolTipText("\uD604\uC7AC \uC0C1\uD488\uC744 \uC1FC\uD551\uBAB0\uACFC \uC5F0\uB3D9\uC5EC\uBD80\uB97C \uC120\uD0DD \uD569\uB2C8\uB2E4.\r\n\uD604\uC7AC \uC0C1\uD488\uACFC \uC5F0\uB3D9\uC911\uC5D0 \uC774 \uC635\uC158\uC744 \uBCC0\uACBD\uD558\uBA74 \uC1FC\uD551\uBAB0 \uC0C1\uD488\uC774 \uC9C4\uC5F4\uC554\uD568\uC73C\uB85C \uBCC0\uACBD \uB429\uB2C8\uB2E4.");
    	panel_goods_detail.add(combo_Detail_ShopConnectUse, "cell 5 9 5 1,growx,aligny center");
    	
    	btnToggle_Detail_ConnectState = new JToggleButton("\uC5F0\uB3D9\uC0C1\uD0DC");
    	btnToggle_Detail_ConnectState.setEnabled(false);
    	btnToggle_Detail_ConnectState.setToolTipText("\uD604\uC7AC \uC0C1\uD488\uC774 \uC1FC\uD551\uBAB0\uACFC \uC5F0\uB3D9\uC911\uC778\uC9C0 \uC544\uB2CC\uC9C0 \uC0C1\uD0DC\uB97C \uD45C\uC2DC\uD569\uB2C8\uB2E4.");
    	panel_goods_detail.add(btnToggle_Detail_ConnectState, "cell 13 9,growx,aligny center");
    	
    	JLabel label_Detail_View = new JLabel("\uC9C4\uC5F4\uC5EC\uBD80");
    	label_Detail_View.setToolTipText("\uBA54\uC778\uD398\uC774\uC9C0 \uBC0F \uCE74\uD14C\uACE0\uB9AC\uC5D0 \uC0C1\uD488\uC744 \uD45C\uC2DC \uD560\uC9C0 \uC5EC\uBD80\uB97C \uD655\uC778\uD569\uB2C8\uB2E4.\r\n\uC9C4\uC5F4\uD568 = \uC9C4\uC5F4\uB428  / \uC9C4\uC5F4\uC554\uD568 = \uC1FC\uD551\uBAB0\uC5D0 \uD45C\uC2DC\uB418\uC9C0 \uC54A\uC74C");
    	label_Detail_View.setHorizontalAlignment(SwingConstants.CENTER);
    	panel_goods_detail.add(label_Detail_View, "cell 1 10 3 1,growx,aligny center");
    	
    	combo_Detail_View = new JComboBox<String>();
    	combo_Detail_View.setToolTipText("\uBA54\uC778\uD398\uC774\uC9C0 \uBC0F \uCE74\uD14C\uACE0\uB9AC\uC5D0 \uC0C1\uD488\uC744 \uD45C\uC2DC \uD560\uC9C0 \uC5EC\uBD80\uB97C \uD655\uC778\uD569\uB2C8\uB2E4.\r\n\uC9C4\uC5F4\uD568 = \uC9C4\uC5F4\uB428  / \uC9C4\uC5F4\uC554\uD568 = \uC1FC\uD551\uBAB0\uC5D0 \uD45C\uC2DC\uB418\uC9C0 \uC54A\uC74C");
    	combo_Detail_View.setModel(new DefaultComboBoxModel<String>(new String[] {"\uC9C4\uC5F4\uD568", "\uC9C4\uC5F4\uC548\uD568"}));
    	combo_Detail_View.setMaximumRowCount(2);
    	panel_goods_detail.add(combo_Detail_View, "cell 5 10 5 1,growx,aligny center");
    	
    	JLabel label_Detail_ImageConnectUse = new JLabel("\uC774\uBBF8\uC9C0\uC5F0\uB3D9");
    	label_Detail_ImageConnectUse.setToolTipText("\uC0C1\uD488\uC758 \uC774\uBBF8\uC9C0\uB97C \uACF5\uC6A9\uC774\uBBF8\uC9C0 \uD3F4\uB354\uC5D0\uC11C \uAC00\uC838\uB2E4 \uC0AC\uC6A9\uD569\uB2C8\uB2E4.\r\n\uB2E8\uB3C5\uD3F4\uB354\uB85C \uC120\uD0DD\uC2DC \uD574\uB2F9 \uB9E4\uC7A5\uC758 \uB2E8\uB3C5 \uD3F4\uB354\uC758 \uC9C0\uC815 \uD30C\uC77C\uC744 \uC120\uD0DD\uD558\uC5EC \uC0AC\uC6A9\uD558\uC2E4\uC218 \uC788\uC2B5\uB2C8\uB2E4.");
    	label_Detail_ImageConnectUse.setHorizontalAlignment(SwingConstants.CENTER);
    	panel_goods_detail.add(label_Detail_ImageConnectUse, "cell 1 11 3 1,growx,aligny center");
    	
    	combox_Detail_ImageConnectUse = new JComboBox<String>();
    	combox_Detail_ImageConnectUse.setToolTipText("\uC0C1\uD488\uC758 \uC774\uBBF8\uC9C0\uB97C \uACF5\uC6A9\uC774\uBBF8\uC9C0 \uD3F4\uB354\uC5D0\uC11C \uAC00\uC838\uB2E4 \uC0AC\uC6A9\uD569\uB2C8\uB2E4.\r\n\uB2E8\uB3C5\uD3F4\uB354\uB85C \uC120\uD0DD\uC2DC \uD574\uB2F9 \uB9E4\uC7A5\uC758 \uB2E8\uB3C5 \uD3F4\uB354\uC758 \uC9C0\uC815 \uD30C\uC77C\uC744 \uC120\uD0DD\uD558\uC5EC \uC0AC\uC6A9\uD558\uC2E4\uC218 \uC788\uC2B5\uB2C8\uB2E4.");
    	combox_Detail_ImageConnectUse.setModel(new DefaultComboBoxModel<String>(new String[] {"\uB2E8\uB3C5\uD3F4\uB354", "\uACF5\uC6A9\uD3F4\uB354"}));
    	combox_Detail_ImageConnectUse.setMaximumRowCount(2);
    	panel_goods_detail.add(combox_Detail_ImageConnectUse, "cell 5 11 5 1,growx,aligny center");
    	
    	JLabel label_Detail_ImagePath = new JLabel("\uC774\uBBF8\uC9C0\uACBD\uB85C");
    	label_Detail_ImagePath.setHorizontalAlignment(SwingConstants.CENTER);
    	panel_goods_detail.add(label_Detail_ImagePath, "cell 1 12 3 1,growx,aligny center");
    	
    	text_Detail_ImagePath = new JTextField();
    	panel_goods_detail.add(text_Detail_ImagePath, "cell 5 12 7 1,growx,aligny center");
    	text_Detail_ImagePath.setColumns(10);
    	text_Detail_ImagePath.setEditable(false);
    	
    	JButton btn_Detail_ImageSearch = new JButton("\uAC80\uC0C9");
    	btn_Detail_ImageSearch.setToolTipText("\uC9C0\uC815\uD55C \uD3F4\uB354\uC5D0\uC11C \uC774\uBBF8\uC9C0\uB97C \uAC80\uC0C9 \uD569\uB2C8\uB2E4.\r\n\uB2E8\uB3C5\uD3F4\uB354 : \uD574\uB2F9 \uB9E4\uC7A5\uC758 \uD3F4\uB354\uC5D0\uC11C \uC774\uBBF8\uC9C0\uB97C \uC120\uD0DD \uD560\uC218\uC788\uC2B5\uB2C8\uB2E4.\r\n\uACF5\uC6A9\uD3F4\uB354 : \uAC19\uC740 \uBC14\uCF54\uB4DC\uC758 \uC774\uBBF8\uC9C0\uB97C \uBD88\uB7EC\uC635\uB2C8\uB2E4.");
    	panel_goods_detail.add(btn_Detail_ImageSearch, "cell 13 12,growx,aligny center");
    	btn_Detail_ImageSearch.addActionListener(new ActionListener() {
    		
    		@Override
    		public void actionPerformed(ActionEvent e) {
    			// TODO Auto-generated method stub
    			//이미지 불러오기
    			getImageData();	
    		}
    	});
    	
    	JSeparator separator_1 = new JSeparator();
    	panel_goods_detail.add(separator_1, "cell 1 14 14 1,growx,aligny top");
    	
    	label_image_view = new JLabel();
    	label_image_view.setBackground(Color.WHITE);
    	label_image_view.setHorizontalAlignment(SwingConstants.CENTER);
    	label_image_view.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
    	panel_goods_detail.add(label_image_view, "cell 1 16 5 1,grow");
    	JButton btn_Detail_Save = new JButton("\uC800\uC7A5");
    	panel_goods_detail.add(btn_Detail_Save, "cell 13 16,growx,aligny bottom");
    	
    	
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
		
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);  //가로 스크롤 
		table.getTableHeader().setReorderingAllowed(false);  //이동불가
		
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
				if (e.getClickCount() == 2) {
					System.out.println("마우스 두번 클릭 됐습니다.");
					
					//상품을 우측으로 보냅니다.
					setGoodsDetail();
				} // 더블클릭
				if (e.getButton() == 3) { 
					System.out.println("오른쪽 마우스 클릭 됐습니다.");
					
				} // 오른쪽 클릭
			}
		});
		
		table.setVisible(true);
    			
		table.getColumn("순번").setPreferredWidth(40);
		table.getColumn("순번").setCellRenderer(celAlignCenter);
		
		table.getColumn("바코드").setPreferredWidth(110);
		table.getColumn("상품명").setPreferredWidth(180);
		table.getColumn("규격").setPreferredWidth(40);
		table.getColumn("규격").setCellRenderer(celAlignCenter);
		
		//String[] colunm = {"순번", "바코드", "상품명", "규격", "매입가", "판매가", "현재고", "안전재고", 
		//		"분류코드", "대코드", "대명", "중코드", "중명", "소코드", "소명", "행사", "상품연동", "진열여부", "연동유무", "재고연동", "이미지설정", "이미지경로" };
		
		table.getColumn("매입가").setPreferredWidth(60);
		table.getColumn("매입가").setCellRenderer(celAlignRight);
		
		table.getColumn("판매가").setPreferredWidth(60);
		table.getColumn("판매가").setCellRenderer(celAlignRight);
		
		table.getColumn("현재고").setPreferredWidth(60);
		table.getColumn("현재고").setCellRenderer(celAlignCenter);
		
		table.getColumn("안전재고").setWidth(0);
		table.getColumn("안전재고").setMinWidth(0);
		table.getColumn("안전재고").setMaxWidth(0);		
		table.getColumnModel().getColumn(7).setResizable(false); //안전재고
		
		table.getColumn("분류코드").setPreferredWidth(60);
		table.getColumn("분류코드").setCellRenderer(celAlignCenter);
		
		table.getColumn("대코드").setWidth(0);
		table.getColumn("대코드").setMinWidth(0);
		table.getColumn("대코드").setMaxWidth(0);		
		table.getColumnModel().getColumn(9).setResizable(false); //대코드
		
		table.getColumn("대명").setWidth(0);
		table.getColumn("대명").setMinWidth(0);
		table.getColumn("대명").setMaxWidth(0);		
		table.getColumnModel().getColumn(10).setResizable(false); //대명
		
		table.getColumn("중코드").setWidth(0);
		table.getColumn("중코드").setMinWidth(0);
		table.getColumn("중코드").setMaxWidth(0);		
		table.getColumnModel().getColumn(11).setResizable(false); //중코드
		
		table.getColumn("중명").setWidth(0);
		table.getColumn("중명").setMinWidth(0);
		table.getColumn("중명").setMaxWidth(0);		
		table.getColumnModel().getColumn(12).setResizable(false); //중명
		
		table.getColumn("소코드").setWidth(0);
		table.getColumn("소코드").setMinWidth(0);
		table.getColumn("소코드").setMaxWidth(0);		
		table.getColumnModel().getColumn(13).setResizable(false); //소코드
		
		table.getColumn("소명").setWidth(0);				
		table.getColumn("소명").setMinWidth(0);
		table.getColumn("소명").setMaxWidth(0);		
		table.getColumnModel().getColumn(14).setResizable(false); //소명
		
		table.getColumn("행사").setCellRenderer(celAlignCenter);								
		table.getColumn("상품연동").setCellRenderer(celAlignCenter);
		table.getColumn("쇼핑몰").setCellRenderer(celAlignCenter);
		
		table.getColumn("진열유무").setWidth(0);
		table.getColumn("진열유무").setMinWidth(0);
		table.getColumn("진열유무").setMaxWidth(0);		
		table.getColumnModel().getColumn(17).setResizable(false); //진열유무
		
		table.getColumn("재고연동").setWidth(0);
		table.getColumn("재고연동").setMinWidth(0);
		table.getColumn("재고연동").setMaxWidth(0);		
		table.getColumnModel().getColumn(18).setResizable(false); //재고연동
		
		table.getColumn("이미지설정").setCellRenderer(celAlignCenter);								
		table.getColumn("이미지경로").setPreferredWidth(300);
		
		table.setRowHeight(25);
		
		JScrollPane scrollPane = new JScrollPane(table);
		panel_1.add(scrollPane, BorderLayout.CENTER);
    }
    
    private void getImageData(){
    	
    	//폴더선택 구분에 따라서 쿼리를 각 서버로 보냅니다.
		//폴더 선택값 불러오기
    	String gubun = ""; 
    	if(combox_Detail_ImageConnectUse.getSelectedItem().equals("단독폴더")){
			//ms_connect.setMainSetting();
			gubun = "0";
		}else{
			//ms_connect.setImageSetting();
			gubun = "1";
		}
		
		//상품정보 불러오기
		String barcode = text_Detail_Barcode.getText();		
		
		//선택 서버에 접속해서 상품 불러오기
		String query = "Select * From FTP_Image Where Barcode='"+barcode+"' and Path_Gubun='"+gubun+"' ";
		
		ms_connect.setImageSetting();
		ArrayList<HashMap<String, String>> temp = ms_connect.connection(query);		
		if(temp.size() > 0){
			
			HashMap<String, String> map = temp.get(0);			
			query = "Update Goods_Info Set Img_Path='http://14.38.161.45:7080/"+map.get("Path")+"/"+map.get("Barcode").trim()+"."+map.get("Ext").trim()+"', Edit_Tran='1' Where Barcode='"+map.get("Barcode").trim()+"' ";
			
			ms_connect.setMainSetting();
			int result = ms_connect.connect_update(query);
			
			switch(result){
			case 0:
				JOptionPane.showMessageDialog(this, "저장이 완료 되었습니다.");
				break;				
			default :
				JOptionPane.showMessageDialog(this, "검색된 파일을 저장하지 못했습니다.");
			break;		
			}
			
		}else{			
			JOptionPane.showMessageDialog(this, "검색된 이미지 파일이 없습니다.");			
		}
	   	
    	
    }
}
