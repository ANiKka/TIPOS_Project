import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.font.TextLayout;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import org.codehaus.groovy.transform.sc.transformers.BooleanExpressionTransformer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.sun.nio.sctp.MessageInfo;
import com.sun.org.apache.xalan.internal.xsltc.compiler.Pattern;
import com.toedter.calendar.JTextFieldDateEditor;

import Contents.GoodsContents;
import Contents.GoodsPrizesCoupon;
import Contents.PointCouponSample;
import it.sauronsoftware.ftp4j.FTPAbortedException;
import it.sauronsoftware.ftp4j.FTPClient;
import it.sauronsoftware.ftp4j.FTPDataTransferException;
import it.sauronsoftware.ftp4j.FTPException;
import it.sauronsoftware.ftp4j.FTPIllegalReplyException;
import it.sauronsoftware.ftp4j.FTPListParseException;
import javafx.scene.layout.Border;
import javafx.scene.text.Text;
import net.miginfocom.swing.MigLayout;
import java.awt.event.KeyAdapter;
import javax.swing.JTabbedPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;

public class Message_Manage extends JPanel implements ActionListener{
	
	private static final long serialVersionUID = 159748543965L;
	
	private Ms_Connect ms_connect;
	private Trans_ShopAPI trans_shopapi;	
	
	private CardLayout message_cardlayout;
	private CardLayout image_cardlayout;
	
	private JPanel center_tabbed_coupontran;
	private JPanel panel_coupontran_1;
	private JPanel panel_coupontran_2;
	private JLabel tran_label_title1;
	private JLabel tran_label_level;
	private JLabel tran_label_joinyn;
	private JLabel tran_label_memid;
	private JLabel tran_label_hp;
	private JComboBox<String> tran_combo_level;
	private JComboBox<String> tran_combo_joinyn;
	private JTextField tran_text_memid;
	private JTextField tran_text_hp;
	private JLabel tran_label_title2;
	private JLabel tran_label_trandata;
	private JLabel tran_label_title3;
	private JButton tran_btn_submit;
	private JPanel tran_panel_msg;
	private JPanel tran_panel_img;
	private JPanel tran_panel_evt;
	private JPanel tranmsg_panel_msg;
	private JPanel tranmsg_panel_list;
	private JLabel tranmsg_label_title;
	private JTextField tranmsg_text_title;
	private JLabel tranmsg_label_msg;
	private JTextArea tranmsg_textArea_msg;
	private JLabel tranmsg_label_linkurl;
	private JTextField tranmsg_text_linkurl;
	private JButton tranmsg_btn_msgsave;
	private JLabel tranmsg_label_listnum;
	private JTextField tranmsg_text_listnum;
	private JLabel tranmsg_label_listmsg;
	private JTextField tranmsg_text_listmsg;
	private JButton tranmsg_btn_listsearch;
	private JLabel tranmsg_label_listtitle;
	private JScrollPane tranmsg_scroll_list;
	private JTable tranmsg_table_list;
	private JPanel tranimg_panel_preview;
	private JPanel tranimg_panel_imglist;
	private JLabel tranimg_label_title;
	private JTextField tranimg_text_title;
	private JEditorPane tranimg_editorPane_img;
	private JLabel tranimg_label_linkurl;
	private JTextField tranimg_text_linkurl;
	private JLabel tranimg_label_listtitle;
	private JLabel tranimg_label_pcpath;
	private JTextField tranimg_text_pcpath;
	private JButton tranimg_btn_pcpath;
	private JButton tranimg_btn_ftppath;
	private JLabel tranimg_label_ftptitle;
	private JScrollPane tranimg_scroll_imglist;
	private JPanel tranimg_panel_imgview;
	private JLabel tranevt_label_listtitle;
	private JLabel tranevt_label_title;
	private JTextField tranevt_text_title;
	private JScrollPane tranevt_scroll_evtlist;
	private JTable tranevt_table_evtlist;
	private JPanel tranmsg_panel_message;
	private JLabel lblNewLabel;
	private JScrollPane tranmsg_scrollPane_message;
	private JLabel tranmsg_label_msginfo;
	
	private DefaultTableModel dtm_msglist;
	
	private String state_y = "¾Ë¸²È­¸éÀÇ ³»¿ëÀÌ Àß¸³´Ï´Ù.";
	private String state_n = "Á¤»óÃâ·Â";
	private int cut_cnt = 800;
	private JLabel tranimg_label_imgpath;
	private JTextField tranimg_text_imgpath;
	private JButton tran_btn_datadown;
	private JButton tran_btn_dataup;
		
	private String[] tran_strarr_filelist;
	
	private String GUBUN = "";
	//ÀÌ¹ÌÁö ÃÑ ¼ö·®
	private int image_total_count=0;
	//ÇöÀç ÆäÀÌÁö ¹øÈ£
	private int image_page_num=0;
	//ÃÑÆäÀÌÁö ¼ö·® 
	private int image_page_count=0;
	//ÇÑÆäÀÌÁö´ç ¸ñ·Ï ¼ö·®
	private int image_page_listcount = 10;
		
	private JLabel tranimg_label_nowpage;
	private JLabel tranimg_label_sp;
	private JLabel tranimg_label_totalpage;
	private JButton tranimg_btn_pageup;
	private JButton tranimg_btn_pagedown;
	private JButton tranimg_btn_msgreset;
	//private JButton tranimg_btn_imgreset;
	private JButton tranevt_btn_search;
	
	private DefaultTableModel dtm_tranevt;
	private JLabel label_tran_platform;
	private JComboBox comboBox_tran_platform;
	private JLabel tranmsg_label_contents;
	private JScrollPane tranmsg_scrollPane_contents;
	private JTextArea tranmsg_textArea_contents;
	private JScrollPane tranimg_scrollPane_msg;
	private JTextArea tranimg_textArea_msg;
	private JLabel tranimg_label_msg;
	private JButton tranimg_btn_renew;
	private JPanel tranimg_panel_contents;
	private JPanel tranimg_panel_contentslist;
	private JPanel tranimg_panel_contentsbtn;
	private JPanel tranimg_panel_contents_list;
	private JPanel panel_content_top;
	private JPanel panel_content_middle;
	private JLabel lblA;
	private JLabel label_top_textin;
	private JButton btn_top_textcolor;
	private JLabel label_bottom_textin;
	private JButton btn_bottom_textcolor;
	private JLabel label_middel_gcount;
	private JLabel lable_middle_searchg;
	private JTextField text_middle_searchg;
	private JButton btn_middle_searchg;
	private JComboBox comboBox_middle_gcount;
	private JPanel panel_middle_imglist;
	private JScrollPane scrollPane_middle_imglist;
	private JTable table_middle_imglist;

	private DefaultTableModel dtm_middle_imglist;	
	private JScrollPane tranimg_scrollPane_editorimg;
	private JPanel tranimg_editorimg;
	private JButton tranimg_btntop_fontcolor;
	private JButton tranimg_btnbottom_fontcolor;
	private JPanel tranimg_panel_goodslist;
	private JLabel tranimg_label_goodstitle;
	private JButton tranimg_btn_renew1;
	private JButton btnNewButton;
	private JPanel panel_content_bottom;
	private JLabel tranimg_bottom_info;
	private JLabel tranimg_bottom_msg;
	private JScrollPane tranimg_scrollPane_bottommsg;
	private JTextArea tranimg_textArea_bottommsg;
	
	DefaultTableModel dtm_coupon_imglist;
	String[] couponListName = {"Æ÷ÀÎÆ®Àû¸³ÄíÆù", "»çÀºÇ°Áö±ÞÄíÆù", "ÀüÃ¼ÇÒÀÎÄíÆù", "´ÜÇ°ÇÒÀÎÄíÆù"};	
	String[] goodsListColumn = {"1¿­ 1Çà »óÇ° 1°³", "2¿­ 2Çà »óÇ° 4°³", "3¿­ 2Çà »óÇ° 6°³", "3¿­ 3Çà »óÇ° 9°³", "4¿­ 3Çà »óÇ° 12°³"};
	
	private JPanel panel_coupon_middlebottom;
	private JScrollPane scrollPane_coupon_couponlist;
	private JTable table_coupon_couponlist;
	
	DefaultTableModel dtm_coupon_couponlist;
	private JTabbedPane tranimg_tabbedPane_preview;
	private JButton btn_top_textclear;
	private JButton btn_bootom_textclear;
	private JSpinner tranimg_spinner_topfontsize;
	private JSpinner tranimg_spinner_bottomfontsize;
	private JPanel tranimg_panel_couponlist;
	private JPanel panel_couponlist_top;
	private JLabel lblNewLabel_1;
	private JLabel label_top_one;
	private JLabel label_top_two;
	private JLabel label_top_three;
	private JComboBox tranimg_comboBox_coupong;
	
	private GoodsContents goodscontents;
	private JPanel panel_myflyzer;
	private JButton btn_coupon_flyzer;
	private JLabel label_coupon_flyzer_info;
	
	private String contents_choose_item = "";
	private JButton btn_middel_renew;
	
	private PointCouponSample pointcoupon;
	private GoodsPrizesCoupon goodsprizescoupon;
	private JEditorPane editorPane_preview_contents;
	private JComboBox comboBox_middle_topfont;
	private JComboBox comboBox_middle_bottomfont;
	private JComboBox comboBox_goods_display;
	private JComboBox comboBox_saleprice_font;
	private JSpinner spinner_goods_fontsize;
	private JSpinner spinner_saleprice_fontsize;
	
	private boolean test = true;
	
	public Message_Manage(){
		
		setLayout(new BorderLayout(0, 0));
		setBorder(new EmptyBorder(5, 5, 5, 5));
		
		//µðºñ Á¢¼Ó µµ±¸
		ms_connect = new Ms_Connect();
		trans_shopapi = new Trans_ShopAPI();
		
		//Àü´ÜÁö ÄÁÅÙÃ÷±¸¼º
		goodscontents = new GoodsContents();
		pointcoupon = new PointCouponSample();
		goodsprizescoupon = new GoodsPrizesCoupon();
		
		
		//ÄíÆùÀü¼Û
		center_tabbed_coupontran = new JPanel();
		//center_tabbed.addTab("\uCFE0\uD3F0 \uBC0F \uC774\uBCA4\uD2B8 \uC804\uC1A1", null, center_tabbed_coupontran, null);
		center_tabbed_coupontran.setLayout(new BorderLayout(5, 0));
		
		add(center_tabbed_coupontran);
		
		panel_coupontran_1 = new JPanel();
		panel_coupontran_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		center_tabbed_coupontran.add(panel_coupontran_1, BorderLayout.WEST);
		panel_coupontran_1.setLayout(new MigLayout("", "[][][grow]", "[30px][][][][][][60px][30px][][][][80px][30px][40px][]"));
		
		event_CouponTran();
			
		//±âº» ¼ÂÆÃÇÕ´Ï´Ù.
		tranmsg_text_title.setText(Server_Config.getOFFICENAME());
		
	}
	
	
	//ÄíÆù Àü¼Û È­¸é
	private void event_CouponTran(){		

		tran_label_title1 = new JLabel("1. \uC804\uC1A1 \uB300\uC0C1 \uC120\uD0DD");
		tran_label_title1.setHorizontalAlignment(SwingConstants.CENTER);
		tran_label_title1.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 13));
		tran_label_title1.setBackground(SystemColor.inactiveCaption);
		tran_label_title1.setOpaque(true);
		panel_coupontran_1.add(tran_label_title1, "cell 0 0 3 1,grow");
		
		tran_label_level = new JLabel("\uD68C\uC6D0 \uB4F1\uAE09");
		tran_label_level.setVisible(false);
		panel_coupontran_1.add(tran_label_level, "cell 0 1,alignx trailing");
		
		tran_combo_level = new JComboBox<String>();
		tran_combo_level.setVisible(false);
		tran_combo_level.setModel(new DefaultComboBoxModel<String>(new String[] {"\uC804\uCCB4", "\uBE44\uD68C\uC6D0", "\uC77C\uBC18\uD68C\uC6D0", "\uAC00\uC785\uB300\uAE30\uD68C\uC6D0", "\uD0C8\uD1F4\uD68C\uC6D0"}));
		panel_coupontran_1.add(tran_combo_level, "cell 1 1 2 1,growx");
		
		tran_label_joinyn = new JLabel("\uD68C\uC6D0\uAC00\uC785\uC5EC\uBD80");
		panel_coupontran_1.add(tran_label_joinyn, "cell 0 2,alignx trailing");
		
		tran_combo_joinyn = new JComboBox<String>();
		tran_combo_joinyn.setModel(new DefaultComboBoxModel<String>(new String[] {"\uC804\uCCB4", "\uBE44\uD68C\uC6D0", "\uAC00\uC785\uD68C\uC6D0"}));
		panel_coupontran_1.add(tran_combo_joinyn, "cell 1 2 2 1,growx");
		
		tran_label_memid = new JLabel("\uD68C\uC6D0 \uC544\uC774\uB514");
		panel_coupontran_1.add(tran_label_memid, "cell 0 3,alignx trailing");
		
		tran_text_memid = new JTextField();
		panel_coupontran_1.add(tran_text_memid, "cell 1 3 2 1,growx");
		tran_text_memid.setColumns(10);
		
		tran_label_hp = new JLabel("\uD734\uB300\uD3F0\uBC88\uD638");
		panel_coupontran_1.add(tran_label_hp, "cell 0 4,alignx trailing");
		
		tran_text_hp = new JTextField();
		panel_coupontran_1.add(tran_text_hp, "cell 1 4 2 1,growx");
		tran_text_hp.setColumns(10);
		
		label_tran_platform = new JLabel("\uD50C\uB7AB\uD3FC");
		panel_coupontran_1.add(label_tran_platform, "cell 0 5,alignx trailing");
		
		comboBox_tran_platform = new JComboBox();
		comboBox_tran_platform.setModel(new DefaultComboBoxModel(new String[] {"\uC804\uCCB4", "\uC548\uB4DC\uB85C\uC774\uB4DC", "IOS"}));
		panel_coupontran_1.add(comboBox_tran_platform, "cell 1 5 2 1,growx");
		
		tran_label_title2 = new JLabel("2. \uC804\uC1A1 \uBC29\uC2DD \uC120\uD0DD");
		tran_label_title2.setHorizontalAlignment(SwingConstants.CENTER);
		tran_label_title2.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 13));
		tran_label_title2.setOpaque(true);
		tran_label_title2.setBackground(SystemColor.inactiveCaption);
		panel_coupontran_1.add(tran_label_title2, "cell 0 7 3 1,grow");
		
		tran_btn_datadown = new JButton("\uBA54\uC138\uC9C0");
		tran_btn_datadown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				message_cardlayout.show(panel_coupontran_2, "message");				
			}
		});
		
		tran_label_trandata = new JLabel("\uC804\uC1A1\uBC29\uC2DD");		
		panel_coupontran_1.add(tran_label_trandata, "cell 0 8,alignx trailing");
		panel_coupontran_1.add(tran_btn_datadown, "cell 1 8 2 1,growx");
		
		tran_btn_dataup = new JButton("\uC804\uB2E8\uC9C0/\uCFE0\uD3F0");
		tran_btn_dataup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				message_cardlayout.show(panel_coupontran_2, "image");
				image_cardlayout.show(tranimg_panel_contentslist, "0");
				msgimg_ReSet("Àü´ÜÁö");
			}
		});
		panel_coupontran_1.add(tran_btn_dataup, "cell 1 9 2 1,growx");
		
		btnNewButton = new JButton("\uC774\uBCA4\uD2B8");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				message_cardlayout.show(panel_coupontran_2, "event");
			}
		});
		panel_coupontran_1.add(btnNewButton, "cell 1 10 2 1,growx");
		
		
		tran_label_title3 = new JLabel("3. \uC804\uC1A1\uD558\uAE30");
		tran_label_title3.setHorizontalAlignment(SwingConstants.CENTER);
		tran_label_title3.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 13));
		tran_label_title3.setBackground(SystemColor.inactiveCaption);
		tran_label_title3.setOpaque(true);
		panel_coupontran_1.add(tran_label_title3, "cell 0 12 3 1,grow");
		
		tran_btn_submit = new JButton("\uC804\uC1A1");
		tran_btn_submit.addActionListener(this);
		panel_coupontran_1.add(tran_btn_submit, "cell 0 13 3 1,grow");
		
		panel_coupontran_2 = new JPanel();
		center_tabbed_coupontran.add(panel_coupontran_2, BorderLayout.CENTER);
		message_cardlayout = new CardLayout();
		panel_coupontran_2.setLayout(message_cardlayout);
		
		tran_panel_msg = new JPanel();
		tran_panel_msg.setName("message");
		panel_coupontran_2.add(tran_panel_msg, "message");
		tran_panel_msg.setLayout(new BorderLayout(5, 0));
		
		tranmsg_panel_msg = new JPanel();
		tranmsg_panel_msg.setBorder(new LineBorder(new Color(0, 0, 0)));
		tran_panel_msg.add(tranmsg_panel_msg, BorderLayout.WEST);
		tranmsg_panel_msg.setLayout(new MigLayout("", "[][grow][]", "[][12px][25px][10px][][100px][10px][grow][][][]"));
		
		lblNewLabel = new JLabel("\uD478\uC2DC \uBA54\uC138\uC9C0");
		lblNewLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		tranmsg_panel_msg.add(lblNewLabel, "cell 0 0 3 1,grow");
		
		tranmsg_label_title = new JLabel("\uC81C\uBAA9");
		tranmsg_panel_msg.add(tranmsg_label_title, "cell 0 2,alignx trailing");
		
		tranmsg_text_title = new JTextField();
		tranmsg_text_title.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 12));
		tranmsg_panel_msg.add(tranmsg_text_title, "cell 1 2 2 1,grow");
		tranmsg_text_title.setColumns(10);
		
		tranmsg_label_msg = new JLabel("\uBA54\uC138\uC9C0");
		tranmsg_panel_msg.add(tranmsg_label_msg, "cell 0 4,alignx trailing");
		
		tranmsg_panel_message = new JPanel();
		FlowLayout fl_tranmsg_panel_message = (FlowLayout) tranmsg_panel_message.getLayout();
		fl_tranmsg_panel_message.setVgap(0);
		fl_tranmsg_panel_message.setHgap(0);
		fl_tranmsg_panel_message.setAlignment(FlowLayout.LEFT);
		tranmsg_panel_msg.add(tranmsg_panel_message, "cell 1 4 2 2,grow");
		
		tranmsg_scrollPane_message = new JScrollPane();
		tranmsg_scrollPane_message.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		tranmsg_panel_message.add(tranmsg_scrollPane_message);
		
		tranmsg_textArea_msg = new JTextArea();
		tranmsg_scrollPane_message.setViewportView(tranmsg_textArea_msg);
		tranmsg_textArea_msg.setWrapStyleWord(true);
		tranmsg_textArea_msg.setRows(7);
		tranmsg_textArea_msg.setColumns(22);
		tranmsg_textArea_msg.setLineWrap(true);
		tranmsg_textArea_msg.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 12));
		
		tranmsg_textArea_msg.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
								
				byte[] str =  tranmsg_textArea_msg.getText().getBytes();
				int line_count = tranmsg_textArea_msg.getLineCount();
				if( str.length > cut_cnt || line_count > 20){
					tranmsg_label_msginfo.setText(state_y+" : "+str.length);					
					JOptionPane.showMessageDialog(Message_Manage.this, "¾Ë¸²È­¸éÀÌ Àß¸³´Ï´Ù. \r\n¹®ÀÚÀÇ ±æÀÌ ¶Ç´Â ³»¿ëÀ» ÁÙ¿© ÁÖ¼¼¿ä");					
				}else{
					tranmsg_label_msginfo.setText(state_n+" : "+str.length);				
				}
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		tranmsg_label_contents = new JLabel("\uBCF8\uBB38");
		tranmsg_panel_msg.add(tranmsg_label_contents, "cell 0 7,alignx trailing,aligny top");
		
		tranmsg_scrollPane_contents = new JScrollPane();
		tranmsg_scrollPane_contents.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		tranmsg_panel_msg.add(tranmsg_scrollPane_contents, "cell 1 7 2 1,grow");
		
		tranmsg_textArea_contents = new JTextArea();
		tranmsg_textArea_contents.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 12));
		tranmsg_textArea_contents.setRows(16);
		tranmsg_textArea_contents.setLineWrap(true);
		tranmsg_textArea_contents.setColumns(22);
		tranmsg_scrollPane_contents.setViewportView(tranmsg_textArea_contents);
		
		
		tranmsg_label_linkurl = new JLabel("\uB9C1\uD06C URL");
		tranmsg_panel_msg.add(tranmsg_label_linkurl, "cell 0 8,alignx trailing");
		
		tranmsg_text_linkurl = new JTextField();
		tranmsg_panel_msg.add(tranmsg_text_linkurl, "cell 1 8 2 1,growx");
		tranmsg_text_linkurl.setColumns(20);
		
		tranmsg_btn_msgsave = new JButton("\uBA54\uC138\uC9C0 \uC800\uC7A5");
		tranmsg_btn_msgsave.addActionListener(this);		
		
		tranimg_btn_msgreset = new JButton("\uC0C8\uB85C\uC785\uB825");
		tranimg_btn_msgreset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				msgmsg_ReSet();
			}
		});
		tranmsg_panel_msg.add(tranimg_btn_msgreset, "cell 1 9,aligny top");
		tranmsg_panel_msg.add(tranmsg_btn_msgsave, "cell 2 9");
		
		tranmsg_label_msginfo = new JLabel("\uC815\uC0C1\uCD9C\uB825");
		tranmsg_label_msginfo.setBackground(SystemColor.info);
		tranmsg_label_msginfo.setOpaque(true);
		tranmsg_label_msginfo.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
		tranmsg_label_msginfo.setHorizontalAlignment(SwingConstants.CENTER);
		tranmsg_panel_msg.add(tranmsg_label_msginfo, "cell 0 10 3 1,grow");
		
		tranmsg_panel_list = new JPanel();
		tranmsg_panel_list.setBorder(new LineBorder(new Color(0, 0, 0)));
		tran_panel_msg.add(tranmsg_panel_list, BorderLayout.CENTER);
		tranmsg_panel_list.setLayout(new MigLayout("", "[][][][grow][100px]", "[][10px][][][grow]"));
		
		tranmsg_label_listtitle = new JLabel("\uBA54\uC138\uC9C0 \uC800\uC7A5\uBAA9\uB85D");
		tranmsg_label_listtitle.setHorizontalAlignment(SwingConstants.CENTER);
		tranmsg_label_listtitle.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
		tranmsg_panel_list.add(tranmsg_label_listtitle, "cell 0 0 5 1,alignx center");
		
		tranmsg_label_listnum = new JLabel("\uBA54\uC138\uC9C0 \uBC88\uD638");
		tranmsg_panel_list.add(tranmsg_label_listnum, "cell 0 2,alignx trailing");
		
		tranmsg_text_listnum = new JTextField();
		tranmsg_panel_list.add(tranmsg_text_listnum, "cell 1 2,growx");
		tranmsg_text_listnum.setColumns(5);
		
		tranmsg_label_listmsg = new JLabel("\uBA54\uC138\uC9C0 \uB0B4\uC6A9");
		tranmsg_panel_list.add(tranmsg_label_listmsg, "cell 2 2,alignx trailing");
		
		tranmsg_text_listmsg = new JTextField();
		tranmsg_panel_list.add(tranmsg_text_listmsg, "cell 3 2 2 1,growx");
		tranmsg_text_listmsg.setColumns(20);
		
		tranmsg_btn_listsearch = new JButton("\uAC80\uC0C9");
		tranmsg_btn_listsearch.setActionCommand("¸Þ¼¼Áö°Ë»ö");
		tranmsg_btn_listsearch.addActionListener(this);
		tranmsg_panel_list.add(tranmsg_btn_listsearch, "cell 4 3,growx");
		
		tranmsg_scroll_list = new JScrollPane();
		tranmsg_panel_list.add(tranmsg_scroll_list, "cell 0 4 5 1,grow");
		
		//ÀúÀå¸ñ·Ï ±¸¼º
		messageSaveList();		
		
		
		tran_panel_img = new JPanel();
		tran_panel_img.setName("image");
		panel_coupontran_2.add(tran_panel_img, "image");
		tran_panel_img.setLayout(new BorderLayout(5, 0));
		
		tranimg_panel_contents = new JPanel();
		tran_panel_img.add(tranimg_panel_contents, BorderLayout.WEST);
		tranimg_panel_contents.setLayout(new BorderLayout(0, 5));
				
			
		
		
		tranimg_tabbedPane_preview = new JTabbedPane(JTabbedPane.TOP);
		tran_panel_img.add(tranimg_tabbedPane_preview, BorderLayout.CENTER);
		
		tranimg_panel_preview = new JPanel();
		tranimg_tabbedPane_preview.addTab("\uD31D\uC5C5 \uBBF8\uB9AC\uBCF4\uAE30", null, tranimg_panel_preview, null);
		tranimg_panel_preview.setBorder(new LineBorder(new Color(0, 0, 0)));
		tranimg_panel_preview.setLayout(new MigLayout("", "[][250px][250px]", "[][155.00][][]"));
		
		JLabel tranimg_label_msgtitle = new JLabel("\uBBF8\uB9AC\uBCF4\uAE30");
		tranimg_label_msgtitle.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
		tranimg_label_msgtitle.setHorizontalAlignment(SwingConstants.CENTER);
		tranimg_panel_preview.add(tranimg_label_msgtitle, "cell 0 0 3 1,alignx center,growy");
		
		JLabel tranimg_label_img = new JLabel("\uC804\uB2E8\uC9C0");
		tranimg_panel_preview.add(tranimg_label_img, "cell 0 1 1 2,alignx trailing,aligny top");
		
		tranimg_scrollPane_editorimg = new JScrollPane();
		tranimg_panel_preview.add(tranimg_scrollPane_editorimg, "cell 1 1 2 2,grow");
		tranimg_scrollPane_editorimg.getVerticalScrollBar().setUnitIncrement(35);
		
		tranimg_editorimg = new JPanel();
		FlowLayout flowLayout = (FlowLayout) tranimg_editorimg.getLayout();
		flowLayout.setHgap(0);
		flowLayout.setVgap(0);
		tranimg_editorimg.setPreferredSize(new Dimension(500, 700));
		tranimg_scrollPane_editorimg.setViewportView(tranimg_editorimg);
		
		tranimg_editorPane_img = new JEditorPane();
		tranimg_editorimg.add(tranimg_editorPane_img);
		tranimg_editorPane_img.setSize(new Dimension(250, 350));
		tranimg_editorPane_img.setPreferredSize(new Dimension(500, 700));
		tranimg_editorPane_img.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		tranimg_editorPane_img.setContentType("text/html");
		tranimg_editorPane_img.setEditable(false);
		tranimg_editorPane_img.setLayout(new BorderLayout(0, 0));
		
		
		
		
		//Ä«µå·¹ÀÌ¾Æ¿ô °ü¸®
		setCardImageList();	
		
		
		
		
		
		
		/*
		tranimg_top_intext = new JPanel();
		tranimg_top_intext.setOpaque(false);		
		tranimg_editorPane_img.add(tranimg_top_intext, BorderLayout.NORTH);				
		tranimg_top_intext.setLayout(new BorderLayout(0, 0));
		
		text_imgtop_title = new JTextField();
		text_imgtop_title.setText("\uC0C1\uB2E8\uBA54\uC138\uC9C0");
		text_imgtop_title.setHorizontalAlignment(SwingConstants.CENTER);
		text_imgtop_title.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 30));
		text_imgtop_title.setBorder(null);
		text_imgtop_title.setOpaque(false);
		tranimg_top_intext.add(text_imgtop_title);
		text_imgtop_title.setColumns(10);
		text_imgtop_title.setText(Server_Config.getOFFICENAME());
		
		tranimg_middle_imgin = new JPanel();
		tranimg_middle_imgin.setOpaque(false);
		tranimg_editorPane_img.add(tranimg_middle_imgin, BorderLayout.CENTER);	
		tranimg_middle_imgin.setLayout(new GridLayout(1, 0, 0, 0));
		
		tranimg_bottom_intext = new JPanel();
		tranimg_bottom_intext.setOpaque(false);		
		tranimg_editorPane_img.add(tranimg_bottom_intext, BorderLayout.SOUTH);	
		tranimg_bottom_intext.setLayout(new BorderLayout(0, 0));
		
		text_imgbottom_title = new JTextField();
		text_imgbottom_title.setText("\uD558\uB2E8\uBA54\uC138\uC9C0");
		text_imgbottom_title.setHorizontalAlignment(SwingConstants.CENTER);
		text_imgbottom_title.setBorder(null);
		text_imgbottom_title.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 30));
		text_imgbottom_title.setOpaque(false);
		tranimg_bottom_intext.add(text_imgbottom_title);
		text_imgbottom_title.setColumns(10);*/
		
		tranimg_label_imgpath = new JLabel("Path");
		tranimg_panel_preview.add(tranimg_label_imgpath, "cell 0 3,alignx trailing");
		
		tranimg_text_imgpath = new JTextField();
		tranimg_text_imgpath.setEnabled(false);
		tranimg_text_imgpath.setEditable(false);
		tranimg_panel_preview.add(tranimg_text_imgpath, "cell 1 3 2 1,growx");
		tranimg_text_imgpath.setColumns(10);
		
		JPanel tranimg_panel_couponpreview = new JPanel();
		tranimg_tabbedPane_preview.addTab("\uD558\uB2E8 \uCD94\uAC00\uB0B4\uC6A9", null, tranimg_panel_couponpreview, null);
		tranimg_panel_couponpreview.setLayout(new MigLayout("", "[][][]", "[][][][grow]"));
		
		JLabel label_preview_title = new JLabel("\uD558\uB2E8 \uCD94\uAC00\uB0B4\uC6A9");
		label_preview_title.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
		tranimg_panel_couponpreview.add(label_preview_title, "cell 0 0 3 1,alignx center");
		
		JLabel label_preview_contents = new JLabel("\uD558\uB2E8");
		tranimg_panel_couponpreview.add(label_preview_contents, "cell 1 2,alignx left,aligny top");
		
		JScrollPane scrollPane = new JScrollPane();
		tranimg_panel_couponpreview.add(scrollPane, "cell 2 2,grow");
		
		editorPane_preview_contents = new JEditorPane();
		editorPane_preview_contents.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 12));
		scrollPane.setViewportView(editorPane_preview_contents);
		editorPane_preview_contents.setPreferredSize(new Dimension(500, 700));
		
		tran_panel_evt = new JPanel();		
		tran_panel_evt.setName("event");
		panel_coupontran_2.add(tran_panel_evt, "event");
		tran_panel_evt.setLayout(new MigLayout("", "[][grow][]", "[][10px][][grow]"));
		
		tranevt_label_listtitle = new JLabel("\uC1FC\uD551\uBAB0 \uC5F0\uB3D9 \uD478\uC2DC \uC774\uBCA4\uD2B8 \uBAA9\uB85D(\uC1FC\uD551\uBAB0\uC5D0\uC11C \uB4F1\uB85D \uAC00\uB2A5)");
		tranevt_label_listtitle.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
		tranevt_label_listtitle.setHorizontalAlignment(SwingConstants.CENTER);
		tran_panel_evt.add(tranevt_label_listtitle, "cell 0 0 2 1,growx,aligny top");
		
		tranevt_label_title = new JLabel("\uC81C\uBAA9");
		tran_panel_evt.add(tranevt_label_title, "cell 0 2,alignx trailing");
		
		tranevt_text_title = new JTextField();
		tran_panel_evt.add(tranevt_text_title, "cell 1 2,growx");
		tranevt_text_title.setColumns(10);
		
		tranevt_btn_search = new JButton("\uAC80\uC0C9");
		tranevt_btn_search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tranevt_EvtCall();
			}
		});
		tran_panel_evt.add(tranevt_btn_search, "cell 2 2");
		
		tranevt_scroll_evtlist = new JScrollPane();
		tran_panel_evt.add(tranevt_scroll_evtlist, "cell 0 3 3 1,grow");
		
		String[] title = {"°íÀ¯¹øÈ£", "Á¦¸ñ", "¸Þ¼¼Áö", "ÇüÅÂ"}; 
		dtm_tranevt = new DefaultTableModel(null, title);
		
		tranevt_table_evtlist = new JTable(dtm_tranevt);
		tranevt_table_evtlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tranevt_scroll_evtlist.setViewportView(tranevt_table_evtlist);
		
		JTableHeader header_uselist = tranevt_table_evtlist.getTableHeader();
	    Dimension d_uselist = header_uselist.getPreferredSize();
	    d_uselist.height = 40;
	    header_uselist.setPreferredSize(d_uselist);
	    	    

		DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
		celAlignCenter.setHorizontalAlignment(JLabel.CENTER);
		
		DefaultTableCellRenderer celAlignRight = new DefaultTableCellRenderer();
		celAlignRight.setHorizontalAlignment(JLabel.RIGHT);
	    
	    //ÄíÆù ¸®½ºÆ®Çì´õ ºÎºÐ Áß¾ÓÁ¤·Ä
	    ((DefaultTableCellRenderer)tranevt_table_evtlist.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(0);
	    
	    tranevt_table_evtlist.setRowHeight(25);
	    tranevt_table_evtlist.getTableHeader().setReorderingAllowed(false);  //ÀÌµ¿ºÒ°¡	    
	    //center_table_couponlist.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);  //°¡·Î ½ºÅ©·Ñ	    
	    
	    TableColumnModel tcm_evtlist = tranevt_table_evtlist.getColumnModel();

        //tcm_uselist.getColumn(0).setMaxWidth(Integer.MAX_VALUE);
	    tcm_evtlist.getColumn(0).setWidth(40);
	    tcm_evtlist.getColumn(0).setCellRenderer(celAlignCenter);
	    tcm_evtlist.getColumn(0).setPreferredWidth(40);

	    //tcm_uselist.getColumn(1).setMaxWidth(Integer.MAX_VALUE);
	    tcm_evtlist.getColumn(1).setWidth(100);	    
	    tcm_evtlist.getColumn(1).setCellRenderer(celAlignCenter);
	    tcm_evtlist.getColumn(1).setPreferredWidth(100);
	    	    
	    tcm_evtlist.getColumn(2).setWidth(300);	    
	    tcm_evtlist.getColumn(2).setCellRenderer(celAlignCenter);
	    tcm_evtlist.getColumn(2).setPreferredWidth(300);
	    
	    tcm_evtlist.getColumn(3).setWidth(60);	    
	    tcm_evtlist.getColumn(3).setCellRenderer(celAlignCenter);
	    tcm_evtlist.getColumn(3).setPreferredWidth(60);
		
	    
	    
	}

	//Ä«µå·¹ÀÌ¾î ºÎºÐ
	private void setCardImageList(){
		

		DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
		celAlignCenter.setHorizontalAlignment(JLabel.CENTER);
		
		DefaultTableCellRenderer celAlignRight = new DefaultTableCellRenderer();
		celAlignRight.setHorizontalAlignment(JLabel.RIGHT);
		
		
		tranimg_panel_contentslist = new JPanel();
		tranimg_panel_contents.add(tranimg_panel_contentslist, BorderLayout.CENTER);
		image_cardlayout = new CardLayout();
		tranimg_panel_contentslist.setLayout(image_cardlayout);
		
		
		//ÄÁÅÙÃ÷ ¼±ÅÃ È­¸é 
		JPanel tranimg_panel_contentschoose = new JPanel();		
		tranimg_panel_contentslist.add("0", tranimg_panel_contentschoose);
		tranimg_panel_contentschoose.setBorder(new LineBorder(new Color(0,0,0)));
		tranimg_panel_contentschoose.setLayout(new BorderLayout(0,5));
		
		panel_myflyzer = new JPanel();
		tranimg_panel_contentschoose.add(panel_myflyzer, BorderLayout.CENTER);
		panel_myflyzer.setLayout(new MigLayout("", "[grow]", "[][][50px][100px][20px][50px][100px][20px][50px][100px]"));
		
		JLabel label_contentschoose_info = new JLabel("\uCEE8\uD150\uCE20 \uC120\uD0DD");
		label_contentschoose_info.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		label_contentschoose_info.setHorizontalAlignment(SwingConstants.CENTER);
		panel_myflyzer.add(label_contentschoose_info, "cell 0 0,growx");
		
		JButton btn_myflyzer = new JButton("\uB0B4\uAC00\uB9CC\uB4E0 \uC804\uB2E8\uC9C0 \uC804\uC1A1\uD558\uAE30");
		btn_myflyzer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contents_choose_item = "myflyzer";
				setMyFlyzer();
				image_cardlayout.show(tranimg_panel_contentslist, "1");
			}
		});
		
		btn_myflyzer.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
		panel_myflyzer.add(btn_myflyzer, "cell 0 2,grow");
		
		JLabel label_myflyzer_info = new JLabel("<html>\r\n\uB0B4 \uCEF4\uD4E8\uD130\uC5D0 \uC800\uC7A5\uB418\uC5B4 \uC788\uB294 \uC774\uBBF8\uC9C0\uB97C \uC804\uC1A1 \uD569\uB2C8\uB2E4.<br>\r\nPOP \uD504\uB85C\uADF8\uB7A8\uC744 \uC774\uC6A9\uD574\uC11C \uB9CC\uB4E0 \uC804\uB2E8\uC9C0\uB97C \uC804\uC1A1\uD569\uB2C8\uB2E4.<br>\r\n\uB9E4\uC7A5\uC804\uB2E8\uC9C0 \uBC0F \uC138\uC77C \uB0B4\uC6A9 \uC804\uC1A1 \uC2DC \uC0AC\uC6A9\r\n</html>\r\n");
		label_myflyzer_info.setBorder(new LineBorder(new Color(0, 0, 0)));
		label_myflyzer_info.setHorizontalAlignment(SwingConstants.CENTER);
		panel_myflyzer.add(label_myflyzer_info, "cell 0 3,grow");
		
		JButton btn_goodsmake_tran = new JButton("\uC0C1\uD488\uC804\uB2E8\uC9C0 \uB9CC\uB4E4\uC5B4\uC11C \uC804\uC1A1\uD558\uAE30");
		btn_goodsmake_tran.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contents_choose_item = "goodsmake";
				setGoodsMakeFlyzer();
				image_cardlayout.show(tranimg_panel_contentslist, "2");
				tranimg_editorPane_img.add(goodscontents);
				goodscontents.panel_center.setLayout(new GridLayout(0, 1, 0, 0));	
			}
		});
		
		btn_goodsmake_tran.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
		panel_myflyzer.add(btn_goodsmake_tran, "cell 0 5,grow");
		
		JLabel label_goodsmake_info = new JLabel("<html>\r\n\uC138\uC77C \uC0C1\uD488 \uBC0F \uD64D\uBCF4 \uC0C1\uD488\uC744 \uC120\uD0DD\uB098\uC5F4\uD558\uC5EC \uC804\uC1A1\uD569\uB2C8\uB2E4.<br>\r\n2*6\uBC30\uC5F4 3*3\uBC30\uC5F4 \uB4F1 \uC6D0\uD558\uB294 \uAD6C\uC870\uB85C \uC804\uC1A1 \uD569\uB2C8\uB2E4.<br>\r\n\uC804\uB2E8\uC9C0\uB97C \uC9C1\uC811 \uC81C\uC791 \uD558\uC5EC \uC804\uC1A1 \uAC00\uB2A5 \uD569\uB2C8\uB2E4.<br>\r\n\uD604\uC7AC \uD310\uB9E4 \uB418\uACE0 \uC788\uB294 \uC0C1\uD488\uC744 \uD64D\uBCF4 \uD560 \uC218 \uC788\uC2B5\uB2C8\uB2E4.\r\n</html>");
		label_goodsmake_info.setBorder(new LineBorder(new Color(0, 0, 0)));
		label_goodsmake_info.setHorizontalAlignment(SwingConstants.CENTER);
		panel_myflyzer.add(label_goodsmake_info, "cell 0 6,grow");
		
		btn_coupon_flyzer = new JButton("\uCFE0\uD3F0\uC804\uB2E8\uC9C0 \uB9CC\uB4E4\uC5B4\uC11C \uC804\uC1A1\uD558\uAE30");
		btn_coupon_flyzer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contents_choose_item = "couponflyzer";
				setCouponMakeFlyzer();
				image_cardlayout.show(tranimg_panel_contentslist, "3");
				tranimg_editorPane_img.add(pointcoupon);
				validate();	
			}
		});
		
		btn_coupon_flyzer.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
		panel_myflyzer.add(btn_coupon_flyzer, "cell 0 8,grow");
		
		label_coupon_flyzer_info = new JLabel("<html>\r\n\uCFE0\uD3F0\uC804\uB2E8\uC9C0\uB97C \uB9CC\uB4E4\uC5B4\uC11C \uC804\uC1A1 \uD560 \uC218 \uC788\uC2B5\uB2C8\uB2E4.<br>\r\n\uB2E8\uD488\uCFE0\uD3F0, \uC804\uCCB4\uD560\uC778\uCFE0\uD3F0, \uC0AC\uC740\uD488 \uCFE0\uD3F0, \uD3EC\uC778\uD2B8 \uC801\uB9BD \uCFE0\uD3F0<br>\r\n\uC6D0\uD558\uB294 \uCFE0\uD3F0\uC758 \uAE30\uBCF8 \uC0D8\uC744 \uC81C\uACF5 \uD569\uB2C8\uB2E4.<br>\r\n</html>");
		label_coupon_flyzer_info.setBorder(new LineBorder(new Color(0, 0, 0)));
		label_coupon_flyzer_info.setHorizontalAlignment(SwingConstants.CENTER);
		panel_myflyzer.add(label_coupon_flyzer_info, "cell 0 9,grow");
		
		
		
		
		
		
		
		//1¹ø ³»°¡¸¸µç Àü´ÜÁö ÀÔ·Â È­¸é
		//ÀÌ¹ÌÁö ¸®½ºÆ®
		
		
		
		
		
		//2. Àü´ÜÁö/ÄíÆù ¸¸µé±â
		
	  
	  
	  
	  
	  
        
	  
		//getMakeGoodsFlyzer();
	    getCouponItemList();
	    //tranimg_panel_contentslist.add("2", tranimg_panel_goodslist);	  
	    //tranimg_panel_contentslist.add("3", tranimg_panel_couponlist);
	  	  
			  
	  	//4¹ø ¸Þ¼¼ÁöÀÔ·Â È­¸é
		
	}
	
	
	//Ã¹¹øÂ° Ä«µå ·¹ÀÌ¾î ¼±ÅÃ (³»°¡ ¸¸µçÀü´ÜÁö Àü¼ÛÇÏ±â)
	private void setMyFlyzer(){
		
		JPanel tranimg_panel_imglistmain = new JPanel();		
		tranimg_panel_imglistmain.setLayout(new BorderLayout(0,5));
		tranimg_panel_contentslist.add("1", tranimg_panel_imglistmain);
		
		//Ä«µå·¹ÀÌ¾Æ¿ô
		JPanel tranimg_panel_imglistbottom = new JPanel();
		tranimg_panel_imglistmain.add(tranimg_panel_imglistbottom, BorderLayout.CENTER);
		CardLayout imglistmain = new CardLayout();
		tranimg_panel_imglistbottom.setLayout(imglistmain);
		
		JButton buttom_reselect_back = new JButton("¼±ÅÃ È­¸éÀ¸·Î µ¹¾Æ°¡±â");
		tranimg_panel_imglistmain.add(buttom_reselect_back, BorderLayout.SOUTH);
		buttom_reselect_back.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				image_cardlayout.show(tranimg_panel_contentslist, "0");	
			}
		});
		buttom_reselect_back.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
		
		
		tranimg_panel_contentsbtn = new JPanel();
		tranimg_panel_imglistmain.add(tranimg_panel_contentsbtn, BorderLayout.NORTH);
		tranimg_panel_contentsbtn.setLayout(new BorderLayout(0, 0));
		
		JButton btn_contents_down = new JButton("<< \uC774\uC804");
		btn_contents_down.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				imglistmain.previous(tranimg_panel_imglistbottom);
				JPanel card = null;
				for (Component comp : tranimg_panel_imglistbottom.getComponents()) {
				    if (comp.isVisible() == true) {
				        card = (JPanel) comp;
				    }
				}
				
				String name = card.getName();
				if("1".equals(name)){
					label_top_one.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
					label_top_two.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
				}else{
					label_top_one.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
					label_top_two.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
				}				
				
			}
		});
		btn_contents_down.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
		tranimg_panel_contentsbtn.add(btn_contents_down, BorderLayout.WEST);
		
		JButton btn_contents_up = new JButton("\uB2E4\uC74C >>");
		btn_contents_up.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				
				imglistmain.next(tranimg_panel_imglistbottom);
				JPanel card = null;
				for (Component comp : tranimg_panel_imglistbottom.getComponents()) {
				    if (comp.isVisible() == true) {
				        card = (JPanel) comp;
				    }
				}
				
				String name = card.getName();
				if("1".equals(name)){
					label_top_one.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
					label_top_two.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
				}else{
					label_top_one.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
					label_top_two.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
				}				
			}
		});
		btn_contents_up.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
		tranimg_panel_contentsbtn.add(btn_contents_up, BorderLayout.EAST);
		
		JPanel panel_contents_top = new JPanel();
		tranimg_panel_contentsbtn.add(panel_contents_top, BorderLayout.CENTER);
		panel_contents_top.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		label_top_one = new JLabel("1");
		label_top_one.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		label_top_one.setHorizontalAlignment(SwingConstants.CENTER);
		panel_contents_top.add(label_top_one);
		
		label_top_two = new JLabel("2");
		panel_contents_top.add(label_top_two);		
		
		getPcListFtpList();
		getMessageInputPanel();
		
		tranimg_panel_imglist.setName("1");
		tranimg_panel_contents_list.setName("2");
		tranimg_panel_imglistbottom.add("1", tranimg_panel_imglist);	
		tranimg_panel_imglistbottom.add("2", tranimg_panel_contents_list);
	}
	
	
	//µÎ¹øÂ° Ä«µå ·¹ÀÌ¾î ¼±ÅÃ (»óÇ°Àü´ÜÁö ¸¸µé¾î¼­ Àü¼ÛÇÏ±â)
	private void setGoodsMakeFlyzer(){
		
		JPanel tranimg_panel_goodsmake = new JPanel();		
		tranimg_panel_goodsmake.setLayout(new BorderLayout(0,5));
		tranimg_panel_contentslist.add("2", tranimg_panel_goodsmake);
		
		//Ä«µå·¹ÀÌ¾Æ¿ô
		JPanel tranimg_panel_goodsmakebottom = new JPanel();
		tranimg_panel_goodsmake.add(tranimg_panel_goodsmakebottom, BorderLayout.CENTER);
		CardLayout imglistmain = new CardLayout();
		tranimg_panel_goodsmakebottom.setLayout(imglistmain);
		
		JButton buttom_reselect_back = new JButton("¼±ÅÃ È­¸éÀ¸·Î µ¹¾Æ°¡±â");
		tranimg_panel_goodsmake.add(buttom_reselect_back, BorderLayout.SOUTH);
		buttom_reselect_back.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				image_cardlayout.show(tranimg_panel_contentslist, "0");	
			}
		});
		buttom_reselect_back.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
		
		
		tranimg_panel_contentsbtn = new JPanel();
		tranimg_panel_goodsmake.add(tranimg_panel_contentsbtn, BorderLayout.NORTH);
		tranimg_panel_contentsbtn.setLayout(new BorderLayout(0, 0));
		
		JButton btn_contents_down = new JButton("<< \uC774\uC804");
		btn_contents_down.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				imglistmain.previous(tranimg_panel_goodsmakebottom);
				JPanel card = null;
				for (Component comp : tranimg_panel_goodsmakebottom.getComponents()) {
				    if (comp.isVisible() == true) {
				        card = (JPanel) comp;
				    }
				}
				
				String name = card.getName();
				if("1".equals(name)){
					label_top_one.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
					label_top_two.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 12));
				}else{
					label_top_one.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 12));
					label_top_two.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
				}				
				
			}
		});
		btn_contents_down.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
		tranimg_panel_contentsbtn.add(btn_contents_down, BorderLayout.WEST);
		
		JButton btn_contents_up = new JButton("\uB2E4\uC74C >>");
		btn_contents_up.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				
				imglistmain.next(tranimg_panel_goodsmakebottom);
				JPanel card = null;
				for (Component comp : tranimg_panel_goodsmakebottom.getComponents()) {
				    if (comp.isVisible() == true) {
				        card = (JPanel) comp;
				    }
				}
				
				String name = card.getName();
				if("1".equals(name)){
					label_top_one.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
					label_top_two.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 12));
				}else{
					label_top_one.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 12));
					label_top_two.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
				}
				
			}
		});
		btn_contents_up.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
		tranimg_panel_contentsbtn.add(btn_contents_up, BorderLayout.EAST);
		
		JPanel panel_contents_top = new JPanel();
		tranimg_panel_contentsbtn.add(panel_contents_top, BorderLayout.CENTER);
		panel_contents_top.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		label_top_one = new JLabel("1");
		label_top_one.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		label_top_one.setHorizontalAlignment(SwingConstants.CENTER);
		panel_contents_top.add(label_top_one);
		
		label_top_two = new JLabel("2");
		panel_contents_top.add(label_top_two);		
		
		getMakeGoodsFlyzer();
		getMessageInputPanel();
		
		tranimg_panel_goodslist.setName("1");
		tranimg_panel_contents_list.setName("2");
		tranimg_panel_goodsmakebottom.add("1", tranimg_panel_goodslist);
		tranimg_panel_goodsmakebottom.add("2", tranimg_panel_contents_list);
	}

	//¼¼¹øÂ° Ä«µå ·¹ÀÌ¾î ¼±ÅÃ (»óÇ°Àü´ÜÁö ¸¸µé¾î¼­ Àü¼ÛÇÏ±â)
	private void setCouponMakeFlyzer(){
		
		JPanel tranimg_panel_goodsmake = new JPanel();		
		tranimg_panel_goodsmake.setLayout(new BorderLayout(0,5));
		tranimg_panel_contentslist.add("3", tranimg_panel_goodsmake);
		
		//Ä«µå·¹ÀÌ¾Æ¿ô
		JPanel tranimg_panel_goodsmakebottom = new JPanel();
		tranimg_panel_goodsmake.add(tranimg_panel_goodsmakebottom, BorderLayout.CENTER);
		CardLayout imglistmain = new CardLayout();
		tranimg_panel_goodsmakebottom.setLayout(imglistmain);
		
		JButton buttom_reselect_back = new JButton("¼±ÅÃ È­¸éÀ¸·Î µ¹¾Æ°¡±â");
		tranimg_panel_goodsmake.add(buttom_reselect_back, BorderLayout.SOUTH);
		buttom_reselect_back.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				image_cardlayout.show(tranimg_panel_contentslist, "0");
			}
		});
		buttom_reselect_back.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
		
		
		tranimg_panel_contentsbtn = new JPanel();
		tranimg_panel_goodsmake.add(tranimg_panel_contentsbtn, BorderLayout.NORTH);
		tranimg_panel_contentsbtn.setLayout(new BorderLayout(0, 0));
		
		JButton btn_contents_down = new JButton("<< \uC774\uC804");
		btn_contents_down.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				imglistmain.previous(tranimg_panel_goodsmakebottom);
				JPanel card = null;
				for (Component comp : tranimg_panel_goodsmakebottom.getComponents()) {
				    if (comp.isVisible() == true) {
				        card = (JPanel) comp;
				    }
				}
				
				String name = card.getName();
				if("1".equals(name)){
					label_top_one.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
					label_top_two.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 12));
					label_top_three.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 12));
				}else if("2".equals(name)){
					label_top_one.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 12));
					label_top_two.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
					label_top_three.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 12));
				}else{				
					label_top_one.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 12));
					label_top_two.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 12));
					label_top_three.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
				}
			}
		});
		btn_contents_down.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
		tranimg_panel_contentsbtn.add(btn_contents_down, BorderLayout.WEST);
		
		JButton btn_contents_up = new JButton("\uB2E4\uC74C >>");
		btn_contents_up.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				
				imglistmain.next(tranimg_panel_goodsmakebottom);
				JPanel card = null;
				for (Component comp : tranimg_panel_goodsmakebottom.getComponents()) {
				    if (comp.isVisible() == true) {
				        card = (JPanel) comp;
				    }
				}
				
				String name = card.getName();
				if("1".equals(name)){
					label_top_one.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
					label_top_two.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 12));
					label_top_three.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 12));
				}else if("2".equals(name)){
					label_top_one.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 12));
					label_top_two.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
					label_top_three.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 12));
				}else{
					label_top_one.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 12));
					label_top_two.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 12));
					label_top_three.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
				}
			}
		});
		btn_contents_up.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
		tranimg_panel_contentsbtn.add(btn_contents_up, BorderLayout.EAST);
		
		JPanel panel_contents_top = new JPanel();
		tranimg_panel_contentsbtn.add(panel_contents_top, BorderLayout.CENTER);
		panel_contents_top.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		label_top_one = new JLabel("1");
		label_top_one.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		label_top_one.setHorizontalAlignment(SwingConstants.CENTER);
		panel_contents_top.add(label_top_one);
		
		label_top_two = new JLabel("2");
		panel_contents_top.add(label_top_two);
				
		label_top_three = new JLabel("3");
		panel_contents_top.add(label_top_three);
		
		getCouponItemList();
		getMessageInputPanel();
		
		tranimg_panel_goodslist.setName("1");
		tranimg_panel_couponlist.setName("2");
		tranimg_panel_contents_list.setName("3");
		tranimg_panel_goodsmakebottom.add("1", tranimg_panel_goodslist);
		tranimg_panel_goodsmakebottom.add("2", tranimg_panel_couponlist);
		tranimg_panel_goodsmakebottom.add("3", tranimg_panel_contents_list);
	}

	
	
	//³»°¡¸¸µç Àü´ÜÁö ¼±ÅÃÇÏ±â
	private void getPcListFtpList(){		

		tranimg_panel_imglist = new JPanel();		
		//tranimg_panel_contentslist.add("1", tranimg_panel_imglist);
		tranimg_panel_imglist.setBorder(new LineBorder(new Color(0, 0, 0)));
		tranimg_panel_imglist.setLayout(new MigLayout("", "[][][grow][][][][][][][][grow][]", "[][][][grow]"));
		
		tranimg_label_listtitle = new JLabel("\uB0B4\uAC00 \uB9CC\uB4E0 \uC804\uB2E8\uC9C0 \uC804\uC1A1\uD558\uAE30");
		tranimg_label_listtitle.setHorizontalAlignment(SwingConstants.CENTER);
		tranimg_label_listtitle.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
		tranimg_panel_imglist.add(tranimg_label_listtitle, "cell 0 0 12 1,grow");
		
		tranimg_label_pcpath = new JLabel("PC \uD3F4\uB354");
		tranimg_panel_imglist.add(tranimg_label_pcpath, "cell 0 1,alignx trailing");
		
		tranimg_text_pcpath = new JTextField();
		tranimg_text_pcpath.setEditable(false);
		tranimg_panel_imglist.add(tranimg_text_pcpath, "cell 1 1 10 1,growx");
		tranimg_text_pcpath.setColumns(10);
		
		tranimg_btn_pcpath = new JButton("\uD3F4\uB354\uAC80\uC0C9");
		tranimg_btn_pcpath.addActionListener(this);
		tranimg_panel_imglist.add(tranimg_btn_pcpath, "cell 11 1,growx");
		
		tranimg_label_ftptitle = new JLabel("FTP \uC774\uBBF8\uC9C0");
		tranimg_label_ftptitle.setHorizontalAlignment(SwingConstants.CENTER);
		tranimg_panel_imglist.add(tranimg_label_ftptitle, "cell 0 2 2 1,grow");
		
		tranimg_btn_pagedown = new JButton("\u25C0");
		tranimg_btn_pagedown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
		    	System.out.println("ÆäÀÌÁö´Ù¿î");
		    	if(image_page_num > 1){
		    		image_page_num--;
		    		tranimg_label_nowpage.setText(String.valueOf(image_page_num));
		    		setImageList();
		    	}
		    	System.out.println(image_page_num);
			    
			}
		});
		tranimg_panel_imglist.add(tranimg_btn_pagedown, "cell 3 2");
		
		tranimg_label_nowpage = new JLabel("0");
		tranimg_panel_imglist.add(tranimg_label_nowpage, "cell 5 2");
		
		tranimg_label_sp = new JLabel("/");
		tranimg_panel_imglist.add(tranimg_label_sp, "cell 6 2");
		
		tranimg_label_totalpage = new JLabel("0");
		tranimg_panel_imglist.add(tranimg_label_totalpage, "cell 7 2");
		
		tranimg_btn_pageup = new JButton("\u25B6");
		tranimg_btn_pageup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("ÆäÀÌÁö¾÷" +image_page_num+" "+image_page_count);
		    	if( image_page_num < image_page_count ){    		
		    		image_page_num++;		    		
		    		tranimg_label_nowpage.setText(String.valueOf(image_page_num));
		    		setImageList();
		    	}    	
		    	System.out.println(image_page_num);	
			}
		});
		tranimg_panel_imglist.add(tranimg_btn_pageup, "cell 9 2");
		
		tranimg_btn_ftppath = new JButton("FTP\uAC80\uC0C9");
		tranimg_btn_ftppath.addActionListener(this);
		tranimg_panel_imglist.add(tranimg_btn_ftppath, "cell 11 2,growx");
		
		tranimg_scroll_imglist = new JScrollPane();
		tranimg_scroll_imglist.setPreferredSize(new Dimension(1, 2));
		tranimg_scroll_imglist.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		tranimg_panel_imglist.add(tranimg_scroll_imglist, "cell 0 3 12 1,grow");
		
		tranimg_scroll_imglist.getVerticalScrollBar().setUnitIncrement(30);
		
		tranimg_panel_imgview = new JPanel();
		tranimg_scroll_imglist.setViewportView(tranimg_panel_imgview);
		tranimg_panel_imgview.setLayout(new GridLayout(0, 3, 0, 0));	
		
	}
	
	private void getMakeGoodsFlyzer(){
		
		DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
		celAlignCenter.setHorizontalAlignment(JLabel.CENTER);
		
		DefaultTableCellRenderer celAlignRight = new DefaultTableCellRenderer();
		celAlignRight.setHorizontalAlignment(JLabel.RIGHT);
		
		//2. »óÇ°Àü´ÜÁö ¸¸µé±â
	    tranimg_panel_goodslist = new JPanel();
		//tranimg_panel_contentslist.add("2", tranimg_panel_goodslist);
		tranimg_panel_goodslist.setLayout(new BorderLayout(0, 0));
		
		
		
		panel_content_middle = new JPanel();
		tranimg_panel_goodslist.add(panel_content_middle, BorderLayout.CENTER);
		panel_content_middle.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_content_middle.setLayout(new MigLayout("", "[grow][][][][][]", "[][10px][][][10px][][][10px][][][grow]"));
		
		tranimg_label_goodstitle = new JLabel("\uC804\uB2E8\uC9C0 \uB9CC\uB4E4\uC5B4\uC11C \uC804\uC1A1\uD558\uAE30");
		tranimg_label_goodstitle.setHorizontalAlignment(SwingConstants.CENTER);
		tranimg_label_goodstitle.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
		panel_content_middle.add(tranimg_label_goodstitle, "cell 0 0 6 1,growx");
			
		GraphicsEnvironment e= GraphicsEnvironment.getLocalGraphicsEnvironment();
		Font[] fonts = e.getAllFonts();
		
		
		Vector<String> font_name = new Vector<String>();
		for(int i=0; i <fonts.length; i++){
			Font f = fonts[i];			
			char[] ch = f.getFontName().toCharArray();
			if(ch[0] >= '°¡' && ch[0] <= 'ÆR' ){
				font_name.add(f.getFontName());			
			}
		}
		
		
		label_top_textin = new JLabel("\uC0C1\uB2E8 \uBB38\uAD6C");
		panel_content_middle.add(label_top_textin, "cell 0 2,alignx trailing");
		comboBox_middle_topfont = new JComboBox(new DefaultComboBoxModel<String>(font_name));
		comboBox_middle_topfont.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == 1){			
				
					String font = (String)comboBox_middle_topfont.getSelectedItem();
					Font f = goodscontents.text_top_intext.getFont();
					
					goodscontents.text_top_intext.setFont(new Font(font, Font.BOLD, f.getSize()));
					validate();
				}
			}
		});
		panel_content_middle.add(comboBox_middle_topfont, "cell 1 2,growx");
		
		
		
		btn_top_textcolor = new JButton("");
		btn_top_textcolor.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		btn_top_textcolor.setOpaque(false);
		btn_top_textcolor.setIcon(new ImageIcon(Message_Manage.class.getResource("/Icon/paper_color.png")));
		btn_top_textcolor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				JColorChooser jcolor = new JColorChooser();
				
				Color selectedColor = jcolor.showDialog(Message_Manage.this, "¹è°æ»ö»ó ¼±ÅÃ", Color.YELLOW);
				
				if(selectedColor != null){
					goodscontents.text_top_intext.setOpaque(true);
					goodscontents.text_top_intext.setBackground(selectedColor);
				}
				
				validate();
			}
		});
		
		tranimg_btntop_fontcolor = new JButton("");
		tranimg_btntop_fontcolor.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		tranimg_btntop_fontcolor.setIcon(new ImageIcon(Message_Manage.class.getResource("/Icon/font_color.png")));
		tranimg_btntop_fontcolor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JColorChooser jcolor = new JColorChooser();
				
				Color selectedColor = jcolor.showDialog(Message_Manage.this, "±ÛÀÚ»ö»ó ¼±ÅÃ", Color.YELLOW);
				
				if(selectedColor != null){
					goodscontents.text_top_intext.setForeground(selectedColor);
				}
				validate();
				
			}
		});
		
		tranimg_spinner_topfontsize = new JSpinner();
		tranimg_spinner_topfontsize.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int font_size = (Integer)tranimg_spinner_topfontsize.getValue();
				Font f = goodscontents.text_top_intext.getFont();
				goodscontents.text_top_intext.setFont(new Font(f.getFontName(), Font.BOLD, font_size));
				validate();
			}
		});
		tranimg_spinner_topfontsize.setModel(new SpinnerNumberModel(30, 0, 200, 5));
		panel_content_middle.add(tranimg_spinner_topfontsize, "cell 2 2,growx");
		panel_content_middle.add(tranimg_btntop_fontcolor, "cell 3 2");
		panel_content_middle.add(btn_top_textcolor, "cell 4 2");
		
		btn_top_textclear = new JButton("");
		btn_top_textclear.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		btn_top_textclear.setIcon(new ImageIcon(Message_Manage.class.getResource("/Icon/eraser.png")));
		btn_top_textclear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goodscontents.text_top_intext.setOpaque(false);
				goodscontents.text_top_intext.setText("");				
				goodscontents.text_top_intext.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 30));
				validate();
			}
		});
		panel_content_middle.add(btn_top_textclear, "cell 5 2");
		
		label_bottom_textin = new JLabel("\uD558\uB2E8 \uBB38\uAD6C");
		panel_content_middle.add(label_bottom_textin, "cell 0 3,alignx trailing");
		
		comboBox_middle_bottomfont = new JComboBox(new DefaultComboBoxModel<String>(font_name));
		comboBox_middle_bottomfont.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() ==1){
					
					String font = (String)comboBox_middle_topfont.getSelectedItem();
					Font f = goodscontents.text_bootom_intext.getFont();	
					goodscontents.text_bootom_intext.setFont(new Font(font, Font.BOLD, f.getSize()));
					validate();
					
				}
			}
		});
		panel_content_middle.add(comboBox_middle_bottomfont, "cell 1 3,growx");
		
		btn_bottom_textcolor = new JButton("");
		btn_bottom_textcolor.setIcon(new ImageIcon(Message_Manage.class.getResource("/Icon/paper_color.png")));
		btn_bottom_textcolor.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		btn_bottom_textcolor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JColorChooser jcolor = new JColorChooser();
				
				Color selectedColor = jcolor.showDialog(Message_Manage.this, "¹è°æ»ö»ó ¼±ÅÃ", Color.YELLOW);
				
				if(selectedColor != null){
					goodscontents.text_bootom_intext.setOpaque(true);
					goodscontents.text_bootom_intext.setBackground(selectedColor);
				}
				
				validate();
			}
		});
		
		tranimg_btnbottom_fontcolor = new JButton("");
		tranimg_btnbottom_fontcolor.setIcon(new ImageIcon(Message_Manage.class.getResource("/Icon/font_color.png")));
		tranimg_btnbottom_fontcolor.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		tranimg_btnbottom_fontcolor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JColorChooser jcolor = new JColorChooser();
				
				Color selectedColor = jcolor.showDialog(Message_Manage.this, "±ÛÀÚ»ö»ó ¼±ÅÃ", Color.YELLOW);
				
				if(selectedColor != null){
					goodscontents.text_bootom_intext.setForeground(selectedColor);
				}
				validate();
			}
		});
		
		tranimg_spinner_bottomfontsize = new JSpinner();
		tranimg_spinner_bottomfontsize.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int font_size = (Integer)tranimg_spinner_bottomfontsize.getValue();
				Font f = goodscontents.text_bootom_intext.getFont();
				goodscontents.text_bootom_intext.setFont(new Font(f.getFontName(), Font.BOLD, font_size));
				validate();
			}
		});
		tranimg_spinner_bottomfontsize.setModel(new SpinnerNumberModel(30, 0, 200, 5));
		panel_content_middle.add(tranimg_spinner_bottomfontsize, "cell 2 3,growx");
		panel_content_middle.add(tranimg_btnbottom_fontcolor, "cell 3 3");
		panel_content_middle.add(btn_bottom_textcolor, "cell 4 3");
		
		btn_bootom_textclear = new JButton("");
		btn_bootom_textclear.setIcon(new ImageIcon(Message_Manage.class.getResource("/Icon/eraser.png")));
		btn_bootom_textclear.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		btn_bootom_textclear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goodscontents.text_bootom_intext.setOpaque(false);
				goodscontents.text_bootom_intext.setText("");
				goodscontents.text_bootom_intext.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 30));
				validate();
			}
		});
		panel_content_middle.add(btn_bootom_textclear, "cell 5 3");
		
		JLabel label_goods_diplay = new JLabel("\uC0C1\uD488\uBA85");
		panel_content_middle.add(label_goods_diplay, "cell 0 5,alignx trailing");
		
		comboBox_goods_display = new JComboBox(new DefaultComboBoxModel<String>(font_name));		
		panel_content_middle.add(comboBox_goods_display, "cell 1 5,growx");
		
		spinner_goods_fontsize = new JSpinner();
		spinner_goods_fontsize.setModel(new SpinnerNumberModel(10, 10, 100, 1));
		panel_content_middle.add(spinner_goods_fontsize, "cell 2 5,growx");
		
		JLabel label_tranimg_background = new JLabel("<html>\uBC30\uACBD<br>\uC120\uD0DD</html>");
		label_tranimg_background.setHorizontalAlignment(SwingConstants.TRAILING);
		label_tranimg_background.setOpaque(true);
		label_tranimg_background.setBackground(Color.PINK);
		panel_content_middle.add(label_tranimg_background, "cell 3 5 1 2,grow");
		
		JButton btn_tranimg_backimagepc = new JButton("PC");
		btn_tranimg_backimagepc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getFlyzerPCImage();
			}
		});
		panel_content_middle.add(btn_tranimg_backimagepc, "cell 4 5 2 1,growx");
		
		JLabel label_goods_saleprice = new JLabel("\uD310\uB9E4\uAC00");
		panel_content_middle.add(label_goods_saleprice, "cell 0 6,alignx trailing");
		
		comboBox_saleprice_font = new JComboBox(new DefaultComboBoxModel<String>(font_name));
		panel_content_middle.add(comboBox_saleprice_font, "cell 1 6,growx");
		
		spinner_saleprice_fontsize = new JSpinner();
		spinner_saleprice_fontsize.setModel(new SpinnerNumberModel(10, 10, 100, 1));
		panel_content_middle.add(spinner_saleprice_fontsize, "cell 2 6,growx");
		
		JButton btn_tranimg_backimageftp = new JButton("FTP");
		btn_tranimg_backimageftp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getFlyzerFTPImage();
			}
		});
		panel_content_middle.add(btn_tranimg_backimageftp, "cell 4 6 2 1,growx");
		
		
		//»óÇ° Áø¿­ ¼±ÅÃ		
		label_middel_gcount = new JLabel("\uC5F4\uC124\uC815");
		panel_content_middle.add(label_middel_gcount, "cell 0 8,alignx trailing");
		
		comboBox_middle_gcount = new JComboBox();
		comboBox_middle_gcount.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				
				if(e.getStateChange() == 1){			
					
					//tranimg_middle_imgin.removeAll();
					tranimg_editorPane_img.removeAll();
					String item = (String)e.getItem();
					comboBox_goods_display.setSelectedItem("¸¼Àº °íµñ");
					comboBox_saleprice_font.setSelectedItem("¸¼Àº °íµñ");
					//msgimg_ReSet("»óÇ°");					
					switch(item){
						case "Àû¿ë¾ÈÇÔ":	
							break;						
						case "1¿­ 1Çà »óÇ° 1°³":
							tranimg_editorPane_img.add(goodscontents);
							goodscontents.panel_center.setLayout(new GridLayout(0, 1, 0, 0));
							
							spinner_goods_fontsize.setValue(30);
							spinner_saleprice_fontsize.setValue(35);
							//tranimg_middle_imgin.setLayout(new GridLayout(0, 1, 0, 0));
							break;	
						case "2¿­ 2Çà »óÇ° 4°³":
							//tranimg_middle_imgin.setLayout(new GridLayout(0, 2, 5, 5));
							//break;
						case "3¿­ 2Çà »óÇ° 6°³":
							tranimg_editorPane_img.add(goodscontents);
							goodscontents.panel_center.setLayout(new GridLayout(0, 2, 0, 0));
							spinner_goods_fontsize.setValue(20);
							spinner_saleprice_fontsize.setValue(25);
							//tranimg_middle_imgin.setLayout(new GridLayout(0, 2, 0, 0));
							break;
						case "3¿­ 3Çà »óÇ° 9°³":
							//tranimg_middle_imgin.setLayout(new GridLayout(0, 3, 5, 5));
							//break;
						case "4¿­ 3Çà »óÇ° 12°³":
							tranimg_editorPane_img.add(goodscontents);
							goodscontents.panel_center.setLayout(new GridLayout(0, 3, 0, 0));
							spinner_goods_fontsize.setValue(15);
							spinner_saleprice_fontsize.setValue(20);
							//tranimg_middle_imgin.setLayout(new GridLayout(0, 3, 0, 0));
							break;
					}
					
					validate();
				}
			}
		});
		
		comboBox_middle_gcount.setModel(new DefaultComboBoxModel(new String[] {"1\uC5F4 1\uD589 \uC0C1\uD488 1\uAC1C", "2\uC5F4 2\uD589 \uC0C1\uD488 4\uAC1C", "3\uC5F4 2\uD589 \uC0C1\uD488 6\uAC1C", "3\uC5F4 3\uD589 \uC0C1\uD488 9\uAC1C", "4\uC5F4 3\uD589 \uC0C1\uD488 12\uAC1C"}));
		comboBox_middle_gcount.setSelectedIndex(0);
		panel_content_middle.add(comboBox_middle_gcount, "cell 1 8,growx");
		
		btn_middel_renew = new JButton("\uC0C8\uB85C\uC785\uB825");
		btn_middel_renew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goodscontents.panel_center.removeAll();
				tranimg_editorPane_img.setText("");
				goodscontents.repaint();
				goodscontents.validate();
				repaint();
				validate();
			}
		});
		panel_content_middle.add(btn_middel_renew, "cell 2 8 2 1,growx");
		
		JButton btn_middle_del = new JButton("\uC0AD\uC81C");
		btn_middle_del.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int count = goodscontents.panel_center.getComponentCount();
				System.out.println(count);
				switch(count){
				case 0:					
					break;
				default:
					goodscontents.panel_center.remove(count-1);
					validate();
					repaint();
					break;
				}
				
			}
		});
		panel_content_middle.add(btn_middle_del, "cell 4 8 2 1,growx");
		
		lable_middle_searchg = new JLabel("\uC0C1\uD488\uAC80\uC0C9");
		panel_content_middle.add(lable_middle_searchg, "cell 0 9,alignx trailing");
		
		text_middle_searchg = new JTextField();
		text_middle_searchg.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					getFtpImgaeSearch();				
				}
			}
		});
		panel_content_middle.add(text_middle_searchg, "cell 1 9 2 1,growx");
		text_middle_searchg.setColumns(10);
		
		
		String[] column = {"¼ø¹ø", "±¸ºÐ", "ÀÌ¹ÌÁö°æ·Î", "ÀÌ¹ÌÁö", "»óÇ°¸í"};
		  dtm_middle_imglist = new DefaultTableModel(null, column){   
		   /**
		    * 
		    */
		   private static final long serialVersionUID = 8457594815L;
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
		
		btn_middle_searchg = new JButton("\uB0B4 \uC0C1\uD488\uC0AC\uC9C4");
		btn_middle_searchg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//ÀÌ¹ÌÁö¸¦ ºÒ·¯¿É´Ï´Ù.
				//getFtpImgaeSearch();				
				setListImg_PCCount();
			}
		});
		panel_content_middle.add(btn_middle_searchg, "cell 3 9 3 1,growx");
		  
		panel_middle_imglist = new JPanel();
		panel_content_middle.add(panel_middle_imglist, "cell 0 10 6 1,grow");
		panel_middle_imglist.setLayout(new BorderLayout(0, 0));
		
		scrollPane_middle_imglist = new JScrollPane();
		scrollPane_middle_imglist.setPreferredSize(new Dimension(1, 1));
		panel_middle_imglist.add(scrollPane_middle_imglist, BorderLayout.CENTER);
		
		table_middle_imglist = new JTable(dtm_middle_imglist);
		scrollPane_middle_imglist.setViewportView(table_middle_imglist);
		
	     
	  ((DefaultTableCellRenderer)table_middle_imglist.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(0);
	  
	  table_middle_imglist.setRowHeight(80);
	  //table_offmem_list.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);  //°¡·Î ½ºÅ©·Ñ  
	    
	  table_middle_imglist.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
	  table_middle_imglist.getTableHeader().setReorderingAllowed(false);  //ÀÌµ¿ºÒ°¡
	  
	  //table_middle_imglist.setAutoCreateRowSorter(true);
	  
	  table_middle_imglist.addMouseListener(new MouseListener() {
	   
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
	    	String gubun = (String)table_middle_imglist.getValueAt(table_middle_imglist.getSelectedRow(), 1);	    	
	    	System.out.println(gubun);
	    	switch(gubun){
	    	case "»óÇ°":
	    		setListImg_Count();
	    		break;
	    	case "PC":
	    		setListImg_Background();
	    		break;
	    	case "FTP":
	    		setListImg_Background();
	    		break;
	    	}
	    }
	    
	    /*if (e.getClickCount() == 2) {
	     System.out.println("¸¶¿ì½º µÎ¹ø Å¬¸¯ µÆ½À´Ï´Ù.");
	     //¼öÁ¤µÈ »çÇ×ÀÌ ÀÖ´Ù¸é ´Ù½Ã µ¹¾Æ °©´Ï´Ù.
	     if(change_Flags()) return;
	     //»óÇ°À» ¿ìÃøÀ¸·Î º¸³À´Ï´Ù.
	     setGoodsDetail();
	    } // ´õºíÅ¬¸¯
	    if (e.getButton() == 3) { 
	     System.out.println("¿À¸¥ÂÊ ¸¶¿ì½º Å¬¸¯ µÆ½À´Ï´Ù.");
	     
	    } // ¿À¸¥ÂÊ Å¬¸¯  */
	    }
	  });
	  
	  // {"¼ø¹ø", "È¸¿ø¸í", "È¸¿ø¹øÈ£", "ÀüÈ­¹øÈ£", "ÈÞ´ëÆù¹øÈ£", "¿Â¶óÀÎ È¸¿ø", "¾Û¼³Ä¡ È¸¿ø", "È¸¿ø ID", "¿Â¶óÀÎÁÖ¹®", "¾Ë¸²¼ö½Å¿©ºÎ", "¾Ë¸²¼ö½Å¹øÈ£"};  
	  // {"¼ø¹ø", "±¸ºÐ", "ÀÌ¹ÌÁö°æ·Î", "ÀÌ¹ÌÁö", "»óÇ°¸í"};
	  
	  //ÄÃ·³³ÐÀÌ Á¶Á¤
	  table_middle_imglist.getColumn("¼ø¹ø").setPreferredWidth(30);
	  	  
	  //ÄÃ·³ Á¤·Ä
	  table_middle_imglist.getColumn("±¸ºÐ").setWidth(0);
	  table_middle_imglist.getColumn("±¸ºÐ").setMinWidth(0);
	  table_middle_imglist.getColumn("±¸ºÐ").setMaxWidth(0);
	  	  
	  table_middle_imglist.getColumn("ÀÌ¹ÌÁö°æ·Î").setWidth(0);
	  table_middle_imglist.getColumn("ÀÌ¹ÌÁö°æ·Î").setMinWidth(0);
	  table_middle_imglist.getColumn("ÀÌ¹ÌÁö°æ·Î").setMaxWidth(0);	  
		  
	  /*table_offmem_list.setAutoCreateRowSorter(true);
	  TableRowSorter<TableModel> tsorter_main = new TableRowSorter<TableModel>(table_offmem_list.getModel());
	  table_offmem_list.setRowSorter(tsorter_main);*/ 
	  
	  //ÄÃ·³ Á¶Á¤
	  for(String str:column){
		  table_middle_imglist.getColumn(str).setCellRenderer(celAlignCenter);
	  }
	  
	}
	
	
	//ÄíÆù µî·Ï È­¸é 
	private void getCouponItemList(){
		
		//»óÇ°ÀÌ¹ÌÁö ¸®½ºÆ®
		DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
		celAlignCenter.setHorizontalAlignment(JLabel.CENTER);
		
		DefaultTableCellRenderer celAlignRight = new DefaultTableCellRenderer();
		celAlignRight.setHorizontalAlignment(JLabel.RIGHT);
		
		//2. »óÇ°Àü´ÜÁö ¸¸µé±â
	    tranimg_panel_goodslist = new JPanel();
		//tranimg_panel_contentslist.add("2", tranimg_panel_goodslist);
		tranimg_panel_goodslist.setLayout(new BorderLayout(0, 0));
		
		panel_content_middle = new JPanel();
		tranimg_panel_goodslist.add(panel_content_middle, BorderLayout.CENTER);
		panel_content_middle.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_content_middle.setLayout(new MigLayout("", "[][grow][][][]", "[10px][10px][][][grow]"));		
		
		tranimg_label_goodstitle = new JLabel("\uCFE0\uD3F0 \uC804\uC1A1\uD558\uAE30");
	    tranimg_label_goodstitle.setHorizontalAlignment(SwingConstants.CENTER);
	    tranimg_label_goodstitle.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
	    panel_content_middle.add(tranimg_label_goodstitle, "cell 0 0 5 1,growx");		
		
		
		//ÄíÆù ¸®½ºÆ®
		JLabel tranimg_label_couponmake = new JLabel("\uCFE0\uD3F0\uB9CC\uB4E4\uAE30");
		panel_content_middle.add(tranimg_label_couponmake, "cell 0 2,alignx trailing");
		
		tranimg_comboBox_coupong = new JComboBox();
		tranimg_comboBox_coupong.setModel(new DefaultComboBoxModel(couponListName));		
		tranimg_comboBox_coupong.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				
				String item = (String)e.getItem();
				if(e.getStateChange() == 1){
					for(String str:couponListName){
						if(item.equals(str))
							getCouponImageList(str);
					}
				}
			}
		});
		panel_content_middle.add(tranimg_comboBox_coupong, "cell 1 2 2 1,growx");
		
		tranimg_btn_renew1 = new JButton("\uC0C8\uB85C\uC785\uB825");
		tranimg_btn_renew1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				msgimg_ReSet("Àü´ÜÁö");
			}
		});
		panel_content_middle.add(tranimg_btn_renew1, "cell 3 2 2 1,growx");
		
		lable_middle_searchg = new JLabel("\uC774\uBBF8\uC9C0\uAC80\uC0C9");
		panel_content_middle.add(lable_middle_searchg, "cell 0 3,alignx trailing");
		
		text_middle_searchg = new JTextField();
		text_middle_searchg.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					getFtpImgaeSearch();				
				}
			}
		});
		panel_content_middle.add(text_middle_searchg, "cell 1 3 2 1,growx");
		text_middle_searchg.setColumns(10);
		
		
		String[] column = {"¼ø¹ø", "±¸ºÐ", "ÀÌ¹ÌÁö°æ·Î", "ÀÌ¹ÌÁö", "»óÇ°¸í"};
		  dtm_middle_imglist = new DefaultTableModel(null, column){   
		   /**
		    * 
		    */
		   private static final long serialVersionUID = 8457594815L;
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
		
		btn_middle_searchg = new JButton("\uC774\uBBF8\uC9C0\uAC80\uC0C9");
		btn_middle_searchg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//ÀÌ¹ÌÁö¸¦ ºÒ·¯¿É´Ï´Ù.
				getFtpImgaeSearch();
			}
		});
		panel_content_middle.add(btn_middle_searchg, "cell 3 3 2 1,growx");
		  
		panel_middle_imglist = new JPanel();
		panel_content_middle.add(panel_middle_imglist, "cell 0 4 5 1,grow");
		panel_middle_imglist.setLayout(new BorderLayout(0, 0));
		
		scrollPane_middle_imglist = new JScrollPane();
		scrollPane_middle_imglist.setPreferredSize(new Dimension(1, 1));
		panel_middle_imglist.add(scrollPane_middle_imglist, BorderLayout.CENTER);
		
		table_middle_imglist = new JTable(dtm_middle_imglist);
		scrollPane_middle_imglist.setViewportView(table_middle_imglist);
		
	     
	  ((DefaultTableCellRenderer)table_middle_imglist.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(0);
	  
	  table_middle_imglist.setRowHeight(150);
	  //table_offmem_list.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);  //°¡·Î ½ºÅ©·Ñ  
	    
	  table_middle_imglist.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
	  table_middle_imglist.getTableHeader().setReorderingAllowed(false);  //ÀÌµ¿ºÒ°¡
	  
	  //table_middle_imglist.setAutoCreateRowSorter(true);
	  
	  table_middle_imglist.addMouseListener(new MouseListener() {
	   
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
	    	String gubun = (String)table_middle_imglist.getValueAt(table_middle_imglist.getSelectedRow(), 1);	    	
	    	if("»óÇ°".equals(gubun)){
	    		String path = (String)table_middle_imglist.getValueAt(table_middle_imglist.getSelectedRow(), 2);
	    		String img = "<html><img src=\""+path+"\" width=330 height=330 ></html>";
	    		goodsprizescoupon.editorPane_img.setText(img);
	    	}else{
	    		setSelectedCouponImage();
	    	}	    		    	
	    }
	    
	    /*if (e.getClickCount() == 2) {
	     System.out.println("¸¶¿ì½º µÎ¹ø Å¬¸¯ µÆ½À´Ï´Ù.");
	     //¼öÁ¤µÈ »çÇ×ÀÌ ÀÖ´Ù¸é ´Ù½Ã µ¹¾Æ °©´Ï´Ù.
	     if(change_Flags()) return;
	     //»óÇ°À» ¿ìÃøÀ¸·Î º¸³À´Ï´Ù.
	     setGoodsDetail();
	    } // ´õºíÅ¬¸¯
	    if (e.getButton() == 3) { 
	     System.out.println("¿À¸¥ÂÊ ¸¶¿ì½º Å¬¸¯ µÆ½À´Ï´Ù.");
	     
	    } // ¿À¸¥ÂÊ Å¬¸¯  */
	    }
	  });
	  
	     // {"¼ø¹ø", "È¸¿ø¸í", "È¸¿ø¹øÈ£", "ÀüÈ­¹øÈ£", "ÈÞ´ëÆù¹øÈ£", "¿Â¶óÀÎ È¸¿ø", "¾Û¼³Ä¡ È¸¿ø", "È¸¿ø ID", "¿Â¶óÀÎÁÖ¹®", "¾Ë¸²¼ö½Å¿©ºÎ", "¾Ë¸²¼ö½Å¹øÈ£"};  
	     
	  //ÄÃ·³³ÐÀÌ Á¶Á¤
	  table_middle_imglist.getColumn("¼ø¹ø").setPreferredWidth(40);
	  	  
	  //ÄÃ·³ Á¤·Ä
	  table_middle_imglist.getColumn("±¸ºÐ").setWidth(0);
	  table_middle_imglist.getColumn("±¸ºÐ").setMinWidth(0);
	  table_middle_imglist.getColumn("±¸ºÐ").setMaxWidth(0);
	  
	  table_middle_imglist.getColumn("ÀÌ¹ÌÁö°æ·Î").setWidth(0);
	  table_middle_imglist.getColumn("ÀÌ¹ÌÁö°æ·Î").setMinWidth(0);
	  table_middle_imglist.getColumn("ÀÌ¹ÌÁö°æ·Î").setMaxWidth(0);	  
			  
	  /*table_offmem_list.setAutoCreateRowSorter(true);
	  TableRowSorter<TableModel> tsorter_main = new TableRowSorter<TableModel>(table_offmem_list.getModel());
	  table_offmem_list.setRowSorter(tsorter_main);*/ 
	  
	  //ÄÃ·³ Á¶Á¤
	  for(String str:column){
		  table_middle_imglist.getColumn(str).setCellRenderer(celAlignCenter);
	  }
		
		
		
		
		
		//3. ÄíÆù¸®½ºÆ®
	  tranimg_panel_couponlist = new JPanel();
	  tranimg_panel_couponlist.setBorder(new LineBorder(new Color(0, 0, 0)));
	  //tranimg_panel_contentslist.add("3", tranimg_panel_couponlist);
	  tranimg_panel_couponlist.setLayout(new BorderLayout(0, 5));

	  panel_coupon_middlebottom = new JPanel();
	  tranimg_panel_couponlist.add(panel_coupon_middlebottom, BorderLayout.CENTER);
	  panel_coupon_middlebottom.setLayout(new BorderLayout(0, 0));
  
	  
	  
	  //»ó´Ü ¹®ÀÚ Ã³¸®
	  panel_couponlist_top = new JPanel();
	  panel_couponlist_top.setBorder(new LineBorder(new Color(0, 0, 0)));
      tranimg_panel_couponlist.add(panel_couponlist_top, BorderLayout.NORTH);
      panel_couponlist_top.setLayout(new MigLayout("", "[grow][][][][]", "[][15px][][][][]"));

      lblNewLabel_1 = new JLabel("\uCFE0\uD3F0 \uC120\uD0DD \uD558\uAE30");
      lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
      lblNewLabel_1.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
      panel_couponlist_top.add(lblNewLabel_1, "cell 0 0 5 1,growx");  
      
      
      
      //»ó´Ü ¹®ÀÚ Ã³¸®	
      label_top_textin = new JLabel("\uC0C1\uB2E8 \uBB38\uAD6C");
      panel_couponlist_top.add(label_top_textin, "cell 0 2,alignx trailing");
	
      tranimg_btntop_fontcolor = new JButton("\uAE00\uC790\uC0C9\uC0C1");
      tranimg_btntop_fontcolor.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			JColorChooser jcolor = new JColorChooser();
			Color selectedColor = jcolor.showDialog(Message_Manage.this, "±ÛÀÚ»ö»ó ¼±ÅÃ", Color.YELLOW);				
			if(selectedColor != null){
				int sel_num = tranimg_comboBox_coupong.getSelectedIndex();
				switch(sel_num){
				case 0:
				case 2:						
					pointcoupon.text_date.setForeground(selectedColor);
					break;
				case 1:
				case 3:						
					goodsprizescoupon.textField_title.setForeground(selectedColor);								
					break;
				}					
			}
			validate();	
		}
	});
    panel_couponlist_top.add(tranimg_btntop_fontcolor, "cell 1 2");
	
	btn_top_textcolor = new JButton("\uBC30\uACBD\uC0C9\uC0C1");
	btn_top_textcolor.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {				
			JColorChooser jcolor = new JColorChooser();				
			Color selectedColor = jcolor.showDialog(Message_Manage.this, "¹è°æ»ö»ó ¼±ÅÃ", Color.YELLOW);				
			if(selectedColor != null){
				
				int sel_num = tranimg_comboBox_coupong.getSelectedIndex();
				switch(sel_num){
				case 0:
				case 2:				
					pointcoupon.text_date.setOpaque(true);
					pointcoupon.text_date.setBackground(selectedColor);						
					break;
				case 1:
				case 3:
					goodsprizescoupon.textField_title.setOpaque(true);
					goodsprizescoupon.textField_title.setBackground(selectedColor);														
					break;
				}	
			}				
			validate();
		}
	});
	panel_couponlist_top.add(btn_top_textcolor, "cell 2 2");
	
	tranimg_spinner_topfontsize = new JSpinner();
	tranimg_spinner_topfontsize.addChangeListener(new ChangeListener() {
		public void stateChanged(ChangeEvent e) {
			int font_size = (Integer)tranimg_spinner_topfontsize.getValue();
			
			int sel_num = tranimg_comboBox_coupong.getSelectedIndex();
			switch(sel_num){
			case 0:
			case 2:
				Font f = pointcoupon.text_date.getFont(); 
				pointcoupon.text_date.setFont(new Font(f.getFontName(), Font.BOLD, font_size));
				break;
			case 1:
			case 3:
				f = goodsprizescoupon.textField_title.getFont();
				goodsprizescoupon.textField_title.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, font_size));											
				break;
			}
			validate();
		}
	});
	tranimg_spinner_topfontsize.setModel(new SpinnerNumberModel(30, 10, 100, 5));
	panel_couponlist_top.add(tranimg_spinner_topfontsize, "cell 4 2,growx");
	
	btn_top_textclear = new JButton("\uC0C1\uB2E8 \uC9C0\uC6B0\uAE30");
	btn_top_textclear.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			int sel_num = tranimg_comboBox_coupong.getSelectedIndex();
			switch(sel_num){
			case 0:
			case 2:					
				pointcoupon.text_date.setOpaque(false);
				pointcoupon.text_date.setText("");
				pointcoupon.text_date.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
				break;
			case 1:
			case 3:					
				goodsprizescoupon.textField_title.setOpaque(false);
				goodsprizescoupon.textField_title.setText("");
				goodsprizescoupon.textField_title.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
				break;
			}				
			validate();
		}
	});
	
	JLabel label_coupon_topfont = new JLabel("\uD3F0\uD2B8");
	panel_couponlist_top.add(label_coupon_topfont, "cell 0 3,alignx trailing");
	
	GraphicsEnvironment e= GraphicsEnvironment.getLocalGraphicsEnvironment();
	Font[] fonts = e.getAllFonts();
	
	Vector<String> font_name = new Vector<String>();
	for(int i=0; i <fonts.length; i++){
		Font f = fonts[i];			
		char[] ch = f.getFontName().toCharArray();
		if(ch[0] >= '°¡' && ch[0] <= 'ÆR' ){
			font_name.add(f.getFontName());			
		}
	}		
	JComboBox comboBox_coupon_topfont = new JComboBox(new DefaultComboBoxModel<String>(font_name));
	panel_couponlist_top.add(comboBox_coupon_topfont, "cell 1 3 2 1,growx");
	comboBox_coupon_topfont.addItemListener(new ItemListener() {
		public void itemStateChanged(ItemEvent e) {
			if(e.getStateChange() == 1){
				String font = (String)comboBox_coupon_topfont.getSelectedItem();
				int sel_num = tranimg_comboBox_coupong.getSelectedIndex();
				switch(sel_num){
				case 0:
				case 2:
					Font f = pointcoupon.text_date.getFont();
					pointcoupon.text_date.setFont(new Font(font, Font.BOLD, f.getSize()));						
					break;
				case 1:
				case 3:
					f = goodsprizescoupon.textField_title.getFont();
					goodsprizescoupon.textField_title.setFont(new Font(font, Font.BOLD, f.getSize()));								
					break;
				}
				validate();
			}
		}
	});
		
	panel_couponlist_top.add(btn_top_textclear, "cell 3 3 2 1,growx");
	
	label_bottom_textin = new JLabel("\uD558\uB2E8 \uBB38\uAD6C");
	panel_couponlist_top.add(label_bottom_textin, "cell 0 4,alignx trailing");
	
	tranimg_btnbottom_fontcolor = new JButton("\uAE00\uC790\uC0C9\uC0C1");
	tranimg_btnbottom_fontcolor.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			JColorChooser jcolor = new JColorChooser();				
			Color selectedColor = jcolor.showDialog(Message_Manage.this, "±ÛÀÚ»ö»ó ¼±ÅÃ", Color.YELLOW);				
			if(selectedColor != null){
				if(selectedColor != null){
					int sel_num = tranimg_comboBox_coupong.getSelectedIndex();
					switch(sel_num){
					case 0:
					case 2:						
						pointcoupon.text_point.setForeground(selectedColor);
						break;
					case 1:
					case 3:						
						goodsprizescoupon.textPane_info.setForeground(selectedColor);								
						break;
					}					
				}
			}
			validate();
		}
	});
	panel_couponlist_top.add(tranimg_btnbottom_fontcolor, "cell 1 4");
	
	btn_bottom_textcolor = new JButton("\uBC30\uACBD\uC0C9\uC0C1");
	btn_bottom_textcolor.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {				
			JColorChooser jcolor = new JColorChooser();
			Color selectedColor = jcolor.showDialog(Message_Manage.this, "¹è°æ»ö»ó ¼±ÅÃ", Color.YELLOW);
			if(selectedColor != null){					
				int sel_num = tranimg_comboBox_coupong.getSelectedIndex();
				switch(sel_num){
				case 0:
				case 2:				
					pointcoupon.text_point.setOpaque(true);
					pointcoupon.text_point.setBackground(selectedColor);						
					break;
				case 1:
				case 3:
					goodsprizescoupon.textPane_info.setOpaque(true);
					goodsprizescoupon.textPane_info.setBackground(selectedColor);														
					break;
				}	
			}
			validate();
		}
	});
	panel_couponlist_top.add(btn_bottom_textcolor, "cell 2 4");
	
	
	tranimg_spinner_bottomfontsize = new JSpinner();
	tranimg_spinner_bottomfontsize.addChangeListener(new ChangeListener() {
		public void stateChanged(ChangeEvent e) {
			int font_size = (Integer)tranimg_spinner_bottomfontsize.getValue();				
			int sel_num = tranimg_comboBox_coupong.getSelectedIndex();
			switch(sel_num){
			case 0:
			case 2:
				Font f = pointcoupon.text_point.getFont();
				pointcoupon.text_point.setFont(new Font(f.getFontName(), Font.BOLD, font_size));
				break;
			case 1:
			case 3:
				f = goodsprizescoupon.textPane_info.getFont();
				goodsprizescoupon.textPane_info.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, font_size));											
				break;
			}
			validate();
		}
	});
	tranimg_spinner_bottomfontsize.setModel(new SpinnerNumberModel(30, 10, 100, 5));
	panel_couponlist_top.add(tranimg_spinner_bottomfontsize, "cell 4 4,growx");
	
	btn_bootom_textclear = new JButton("\uD558\uB2E8 \uC9C0\uC6B0\uAE30");
	btn_bootom_textclear.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
			int sel_num = tranimg_comboBox_coupong.getSelectedIndex();
			switch(sel_num){
			case 0:
			case 2:					
				pointcoupon.text_point.setOpaque(false);
				pointcoupon.text_point.setText("");
				pointcoupon.text_point.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 60));
				break;
			case 1:
			case 3:					
				goodsprizescoupon.textPane_info.setOpaque(false);
				goodsprizescoupon.textPane_info.setText("");
				goodsprizescoupon.textPane_info.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
				break;
			}
			validate();
		}
	});
	
	JLabel label_coupon_bottomfont = new JLabel("\uD3F0\uD2B8");
	panel_couponlist_top.add(label_coupon_bottomfont, "cell 0 5,alignx trailing");
	
	JComboBox comboBox_coupon_bottomfont = new JComboBox(new DefaultComboBoxModel<String>(font_name));
	panel_couponlist_top.add(comboBox_coupon_bottomfont, "cell 1 5 2 1,growx");
	comboBox_coupon_bottomfont.addItemListener(new ItemListener() {
		public void itemStateChanged(ItemEvent e) {
			if(e.getStateChange() == 1){			
			
				String font = (String)comboBox_coupon_bottomfont.getSelectedItem();					
				
				int sel_num = tranimg_comboBox_coupong.getSelectedIndex();
				switch(sel_num){
				case 0:						
				case 2:
					Font f = pointcoupon.text_point.getFont();
					pointcoupon.text_point.setFont(new Font(font, Font.BOLD, f.getSize()));						
					break;
				case 1:						
				case 3:
					f = goodsprizescoupon.textPane_info.getFont();
					goodsprizescoupon.textPane_info.setFont(new Font(font, Font.BOLD, f.getSize()));								
					break;
				}
				
				validate();
			}
		}
	});
	panel_couponlist_top.add(btn_bootom_textclear, "cell 3 5 2 1,growx");  
      
      
      
      
      
      
      
      
      
      
      
      
      
      
	  
	  //ÇÏ´Ü ÄíÆù ÀÌ¹ÌÁö Ã³¸®
	  String[] column_couponlist = {"¼ø¹ø", "ÄíÆùÄÚµå", "<html>ÄíÆù¸í<br>ÄíÆù±¸ºÐ<br>½ÃÀÛÀÏ<br>Á¾·áÀÏ</html>"};
	  dtm_coupon_couponlist = new DefaultTableModel(null, column_couponlist){   
	   
		  private static final long serialVersionUID = 88599595555L;
		  @Override
		  public boolean isCellEditable(int roe, int column){		  
			  return false;
		  }
		  public Class getColumnClass(int column) {		         
			  Object value=this.getValueAt(0,column);
			  return (value==null?Object.class:value.getClass());          
		  }   
	  };
	  		  
	  scrollPane_coupon_couponlist = new JScrollPane();
	  scrollPane_coupon_couponlist.setPreferredSize(new Dimension(1, 1));
	  panel_coupon_middlebottom.add(scrollPane_coupon_couponlist, BorderLayout.CENTER);
	  
	  table_coupon_couponlist = new JTable(dtm_coupon_couponlist){
            public boolean getScrollableTracksViewportWidth()
            {
                return getPreferredSize().width < getParent().getWidth();
            }
        };
        
        table_coupon_couponlist.setTableHeader(new JTableHeader(table_coupon_couponlist.getColumnModel()) {
        	@Override public Dimension getPreferredSize() {
        		Dimension d = super.getPreferredSize();
        		d.height = 80;
        		return d;
        	}
        });

        scrollPane_coupon_couponlist.setViewportView(table_coupon_couponlist);
	    
	    
        ((DefaultTableCellRenderer)table_coupon_couponlist.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(0);
	  
        table_coupon_couponlist.setRowHeight(80);		  
	    
        table_coupon_couponlist.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);  //°¡·Î ½ºÅ©·Ñ
  
        
        
        table_coupon_couponlist.getTableHeader().setReorderingAllowed(false);  //ÀÌµ¿ºÒ°¡
  
        //table_coupon_couponlist.setAutoCreateRowSorter(true);
  
        table_coupon_couponlist.addMouseListener(new MouseListener() {
   
        	@Override
        	public void mouseReleased(MouseEvent e) {        	    
        	}
   
        	@Override
        	public void mousePressed(MouseEvent e) {
        		// 	TODO Auto-generated method stub
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
        			setCouponListSelectItem();
        			//ÄíÆùÄÚ¸¦ ºÒ·¯¿Í¼­ ÀÌ¹ÌÁö¿¡ ¾º¿ö ÁÝ´Ï´Ù.
        			
        		}
        	}
        });
  		   
        //ÄÃ·³³ÐÀÌ Á¶Á¤
        table_coupon_couponlist.getColumn("¼ø¹ø").setWidth(30);
        table_coupon_couponlist.getColumn("¼ø¹ø").setPreferredWidth(30);
  
        table_coupon_couponlist.getColumn("ÄíÆùÄÚµå").setPreferredWidth(35);		  
        table_coupon_couponlist.getColumn("ÄíÆùÄÚµå").setPreferredWidth(35);
  
        table_coupon_couponlist.getColumn("<html>ÄíÆù¸í<br>ÄíÆù±¸ºÐ<br>½ÃÀÛÀÏ<br>Á¾·áÀÏ</html>").setPreferredWidth(200);		  
        table_coupon_couponlist.getColumn("<html>ÄíÆù¸í<br>ÄíÆù±¸ºÐ<br>½ÃÀÛÀÏ<br>Á¾·áÀÏ</html>").setPreferredWidth(200);
     
        //ÄÃ·³ Á¶Á¤
        for(String str:column_couponlist){
        	table_coupon_couponlist.getColumn(str).setCellRenderer(celAlignCenter);
        }
    
        /*table_offmem_list.getColumn(""ÁÖ¹®»óÅÂ¹øÈ£"").setWidth(0);
  		table_offmem_list.getColumn(""ÁÖ¹®»óÅÂ¹øÈ£"").setMinWidth(0);
  		table_offmem_list.getColumn(""ÁÖ¹®»óÅÂ¹øÈ£"").setMaxWidth(0);*/
		
        
        getCouponImageList("Æ÷ÀÎÆ®Àû¸³ÄíÆù");
	}
	
	
	
	//¸Þ¼¼Áö ÀÔ·Â È­¸é ºÒ·¯¿À±â
	private void getMessageInputPanel(){
		
		tranimg_panel_contents_list = new JPanel();
		tranimg_panel_contents_list.setName("contentslist");	
		tranimg_panel_contents_list.setLayout(new BorderLayout(0, 5));		
		//tranimg_panel_contentslist.add("4", tranimg_panel_contents_list);
		
		panel_content_top = new JPanel();
		panel_content_top.setBorder(new LineBorder(new Color(0, 0, 0)));
		tranimg_panel_contents_list.add(panel_content_top, BorderLayout.NORTH);
		panel_content_top.setLayout(new MigLayout("", "[][][][grow][]", "[][][80px][][][][][][]"));
		
		lblA = new JLabel("\uC0C1\uB2E8 \uB0B4\uC6A9 \uC785\uB825");
		lblA.setHorizontalAlignment(SwingConstants.CENTER);
		lblA.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
		panel_content_top.add(lblA, "cell 0 0 5 1,alignx center");
		
		tranimg_label_title = new JLabel("\uC81C\uBAA9");
		panel_content_top.add(tranimg_label_title, "cell 0 1,alignx trailing");
		
		tranimg_text_title = new JTextField(Server_Config.getOFFICENAME());
		panel_content_top.add(tranimg_text_title, "cell 1 1 4 1,growx");
		tranimg_text_title.setAutoscrolls(false);
		tranimg_text_title.setColumns(20);
		
				
		tranimg_label_msg = new JLabel("\uBA54\uC138\uC9C0");
		panel_content_top.add(tranimg_label_msg, "cell 0 2,alignx trailing");
		
		tranimg_scrollPane_msg = new JScrollPane();
		panel_content_top.add(tranimg_scrollPane_msg, "cell 1 2 4 1,growx");
		tranimg_scrollPane_msg.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		tranimg_textArea_msg = new JTextArea();
		tranimg_textArea_msg.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 12));
		tranimg_textArea_msg.setLineWrap(true);
		tranimg_textArea_msg.setRows(4);
		tranimg_scrollPane_msg.setViewportView(tranimg_textArea_msg);
		
		
				
		tranimg_label_linkurl = new JLabel("\uB9C1\uD06C URL");
		panel_content_top.add(tranimg_label_linkurl, "cell 0 7");
		
		tranimg_text_linkurl = new JTextField();
		panel_content_top.add(tranimg_text_linkurl, "cell 1 7 4 1,growx");
		tranimg_text_linkurl.setColumns(20);
		
		tranimg_btn_renew = new JButton("\uC0C8\uB85C\uC785\uB825");
		panel_content_top.add(tranimg_btn_renew, "cell 1 8,growx");
		tranimg_btn_renew.addActionListener(new ActionListener() {				
			public void actionPerformed(ActionEvent e) {				
				msgimg_ReSet("³»¿ë");				
			}
		});		
		
		panel_content_bottom = new JPanel();
		panel_content_bottom.setBorder(new LineBorder(new Color(0, 0, 0)));
		tranimg_panel_contents_list.add(panel_content_bottom, BorderLayout.CENTER);
		panel_content_bottom.setLayout(new MigLayout("", "[][grow]", "[][grow]"));
		
		tranimg_bottom_info = new JLabel("\uBCF8\uBB38 \uB0B4\uC6A9 \uC785\uB825(\uC0DD\uB7B5\uAC00\uB2A5)");
		tranimg_bottom_info.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
		tranimg_bottom_info.setHorizontalAlignment(SwingConstants.CENTER);
		panel_content_bottom.add(tranimg_bottom_info, "cell 0 0 2 1,growx");
		
		tranimg_bottom_msg = new JLabel("  \uBCF8    \uBB38  ");
		panel_content_bottom.add(tranimg_bottom_msg, "cell 0 1,alignx trailing,aligny top");
		
		tranimg_scrollPane_bottommsg = new JScrollPane();
		panel_content_bottom.add(tranimg_scrollPane_bottommsg, "cell 1 1,grow");
		
		tranimg_textArea_bottommsg = new JTextArea();
		tranimg_textArea_bottommsg.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 12));
		tranimg_scrollPane_bottommsg.setViewportView(tranimg_textArea_bottommsg);
		
		
	}
	
	//¼±ÅÃ ÄíÆùÀÇ ³»¿ëÀ» ÀÌ¹ÌÁö¿¡ ¾º¿öÁÝ´Ï´Ù.
	private void setCouponListSelectItem(){
		
		int row = table_coupon_couponlist.getSelectedRow();
		
		String coupon_code = (String)table_coupon_couponlist.getValueAt(row, 1);
		String query = "SELECT * FROM ( " 
						+ "Select x.*, isnull((select g_name from goods a where x.e_bBarcode=a.barcode),'') g_name1, " 
						+ "isnull((select g_name from goods a where x.e_pBarcode=a.barcode),'') g_name2 " 
						+ "From e_Coupon_List x  ) X " 
						+ "Where e_Seq='"+coupon_code+"' "; 
		
		ms_connect.setMainSetting();
		HashMap<String, String> map = ms_connect.selectQueryOne(query);
		
		int item = tranimg_comboBox_coupong.getSelectedIndex();
		setBottomPreview(map);
		switch(item){
		case 0:
			pointcoupon.text_title.setText(map.get("e_CouponName"));
			pointcoupon.text_date.setText(map.get("e_Sdate")+" ~ "+map.get("e_EDate"));
			pointcoupon.text_point.setText(map.get("e_Point"));
			break;
		case 1:
			goodsprizescoupon.textField_title.setText(map.get("e_CouponName"));
			String date = "»ç¿ë±â°£ : "+map.get("e_Sdate")+" ~ "+map.get("e_EDate")+"\r\n";
			if("2".equals(map.get("e_gubun")) ){
				String img = "<html><img src=\"http://14.38.161.45:7080/main_goods/"+map.get("e_bBarcode")+".jpg\" width=330 height=330 ></html>";
				System.out.println(img);
				
				goodsprizescoupon.editorPane_img.setText(img);
				date += "»çÀºÇ°¸í : "+map.get("g_name1")+"/ "+map.get("e_bCnt")+"°³";
			}else{
				date += "»çÀºÇ°¸í : "+map.get("e_Product")+"/ "+map.get("e_bCnt")+"°³";
			}
			goodsprizescoupon.textPane_info.setText(date);
			break;
		case 2:
			pointcoupon.text_title.setText(map.get("e_CouponName"));
			pointcoupon.text_date.setText(map.get("e_Sdate")+" ~ "+map.get("e_EDate"));
			if("3".equals(map.get("e_gubun"))){
				pointcoupon.text_point.setText(map.get("e_DcPri"));
			}else{
				pointcoupon.text_point.setText(map.get("e_DcPer"));
			}			
			break;
		case 3:
			
			String img = "<html><img src=\"http://14.38.161.45:7080/main_goods/"+map.get("e_pBarcode")+".jpg\" width=330 height=330 ></html>";
			System.out.println(img);
			goodsprizescoupon.editorPane_img.setText(img);			
			
			goodsprizescoupon.textField_title.setText(map.get("e_CouponName"));
			String content = "»ç¿ë±â°£ : "+map.get("e_Sdate")+" ~ "+map.get("e_EDate")+"\r\n";
			if("5".equals(map.get("e_gubun"))){				
				content += "±Ý¾×ÇÒÀÎ : "+map.get("e_pDCPri")+"(¿ø) ÇÒÀÎ/ ";
				content += "0".equals(map.get("e_pAppCnt"))? "»óÇ°¼ö·®Á¦ÇÑ ¾ø½¿": map.get("e_pAppCnt")+"°³ ¼ö·® Á¦ÇÑ";
			}else{
				content += "ÆÛ¼¾Æ®ÇÒÀÎ : "+map.get("e_pDCPer")+"(%) ÇÒÀÎ/ ";
				content += "0".equals(map.get("e_pAppCnt"))? "»óÇ°¼ö·®Á¦ÇÑ ¾ø½¿": map.get("e_pAppCnt")+"°³ ¼ö·® Á¦ÇÑ";				
			}
			
			goodsprizescoupon.textPane_info.setText(content);
			break;
		}
				
		
	}
	
	
	private void setBottomPreview(HashMap<String, String> temp){		
		
		temp.get("gubun");
		String s_date = temp.get("e_Sdate");
		String e_date = temp.get("e_EDate");
		
		String s_year = s_date.substring(0, 4);
		String s_month = s_date.substring(5, 7);
		String s_day = s_date.substring(8, 10);
		
		String e_year = e_date.substring(0, 4);
		String e_month = e_date.substring(5, 7);
		String e_day = e_date.substring(8, 10);
		
		String contents = " \r\n {@ \r\n" 
							+ "$now_time = mktime();                                   // ÇöÀç½Ã°£ \r\n"			 
							+ "$start_date = mktime(0,0,0,"+s_month+", "+s_day+", "+s_year+"); \r\n"
							+ "$end_date = mktime(0,0,0,"+e_month+", "+e_day+", "+e_year+"); \r\n"
							
							+ "if($end_date >= $now_time){ \r\n"
							+ "		if( $start_date <= $now_time){ \r\n"
							+ "@} \r\n"
							+ "<p style='text-align:center'> \r\n"
							+ "<img src='/API/barcodegen_v2.2.0/html/image.php?code=code128&o=1&dpi=72&t=30&r=2&rot=0&text={$device_idx}-"+temp.get("e_Seq")+"&f1=Arial.ttf&f2=12&a1=&a2=&a3={$device_idx}-"+temp.get("e_Seq")+"'><br> \r\n"		
							+ "</p> \r\n"
							+ "{@ \r\n"
							+ "}else{ \r\n"
							+ "echo \"Çà»ç ½ÃÀÛÀü\"; \r\n"
							+ " } \r\n"
							+ "} else { \r\n"
							+ " echo \"±â°£¸¸·á\"; \r\n"
							+ "}\r\n" 
							+ "@} \r\n"			
							+ "¹Ù ÄÚ µå : {$device_idx}-"+temp.get("e_Seq")+"<br>\r\n"
							+ "Äí Æù ¸í : "+temp.get("e_CouponName")+"<br>\r\n"
							+ "»ç¿ë±â°£ : "+temp.get("e_Sdate")+" ~ "+temp.get("e_EDate")+" ±îÁö<br>\r\n";
		
		if("0".equals(temp.get("e_gubun"))){
			
		}
		switch(temp.get("e_gubun")){
		case "0": //Æ÷ÀÎÆ®
			contents	+= "<br><br>\r\n"
						+ "[ ³»  ¿ë ] <br>\r\n"
						+ " - Æ÷ÀÎÆ® Àû¸³ ÄíÆùÀÔ´Ï´Ù.\r\n"
						+ "<br><br>\r\n"
						+ "[ »ç¿ë¹æ¹ý ] <br>\r\n"
						+ " - °è»ê¿ø¿¡°Ô ¾Ë¸²³»¿ªÀÇ ÄíÆù ¹ÙÄÚµå¸¦ Á¦½ÃÇØ ÁÖ¼¼¿ä!! \r\n"
						+ "<br><br>\r\n"
						+ "[ ÁÖÀÇ»çÇ× ] <br>\r\n"
						+ " - ´Ù¸¥ ÄíÆù°ú Áßº¹ »ç¿ëÀÌ ¾ÈµË´Ï´Ù.<br> ";
						
			break;	
		case "1": //»çÀºÇ°
			contents += "<br><br> \r\n"
						 + "[ ³»     ¿ë ] <br>\r\n"
						 + " - »çÀºÇ° ÁõÁ¤ ÄíÆù ÀÔ´Ï´Ù. <br>\r\n"
						 + " - ¸ÅÀå¿¡¼­ ÄíÆùÀ» Á¦½ÃÇÏ½Ã¸é ("+temp.get("e_Product")+")À» ÁõÁ¤ÇÕ´Ï´Ù.\r\n"
						 + "<br><br> \r\n"
						 + "[ »ç¿ë¹æ¹ý ] <br>\r\n"
						 + " - °è»ê¿ø¿¡°Ô ¾Ë¸²³»¿ªÀÇ ÄíÆù ¹ÙÄÚµå¸¦ Á¦½ÃÇØ ÁÖ¼¼¿ä!!\r\n"
						 + "<br><br>\r\n"
						 + "[ ÁÖÀÇ»çÇ× ] <br> \r\n"
						 + " - ´Ù¸¥ ÄíÆù°ú Áßº¹ »ç¿ëÀÌ ¾ÈµË´Ï´Ù.<br>";
						
			break;
		case "2": //Àç°í¿¬µ¿ »çÀºÇ°
			contents += "<br><br>\r\n"
						+ "[ ³»     ¿ë ] <br>\r\n"
						+ " - ("+temp.get("g_name1")+") »óÇ°ÁõÁ¤ ÄíÆù ÀÔ´Ï´Ù. <br>\r\n"
						+ " - ¸ÅÀå¿¡¼­ ÄíÆùÀ» Á¦½ÃÇÏ½Ã¸é ("+temp.get("g_name1")+")] »óÇ°À» ÁõÁ¤ÇÕ´Ï´Ù. \r\n"
						+ "<br><br>\r\n"						
						+ "[ »ç¿ë¹æ¹ý ] <br> \r\n"
						+ " - °è»ê¿ø¿¡°Ô ¾Ë¸²³»¿ªÀÇ ÄíÆù ¹ÙÄÚµå¸¦ Á¦½ÃÇØ ÁÖ¼¼¿ä!!\r\n"
						+ "<br><br>\r\n"						
						+ "[ ÁÖÀÇ»çÇ× ] <br>\r\n"
						+ " - ´Ù¸¥ ÄíÆù°ú Áßº¹ »ç¿ëÀÌ ¾ÈµË´Ï´Ù.<br>";
						
			break;
		case "3": //ÀüÃ¼ÇÒÀÎ ¿ø
			contents +=  "<br><br>\r\n"
						+ "[ ³»     ¿ë ]  <br>\r\n"
						+ " - "+temp.get("e_DcPri")+"(¿ø) ÇÒÀÎ ÄíÆù ÀÔ´Ï´Ù. <br>\r\n"
						+ " - ¸ÅÀå¿¡¼­ »óÇ° ±¸¸Å ÈÄ "+temp.get("e_MinLimitPri")+"¿ø ÀÌ»ó ±¸¸Å ½Ã <br>\r\n"						
						+ " &nbsp  º» ÄíÆùÀ» Á¦½Ã ÇÏ½Ã¸é "+temp.get("e_DcPri")+"¿ø ÇÒÀÎ µË´Ï´Ù. \r\n"
						+ "<br><br>\r\n"						
						+ "[ »ç¿ë¹æ¹ý ] <br> \r\n"
						+ " - °è»ê¿ø¿¡°Ô ¾Ë¸²³»¿ªÀÇ ÄíÆù ¹ÙÄÚµå¸¦ Á¦½ÃÇØ ÁÖ¼¼¿ä!! \r\n"
						+ "<br><br>\r\n"						
						+ "[ ÁÖÀÇ»çÇ× ] <br> \r\n"
						+ " - ´Ù¸¥ ÄíÆù°ú Áßº¹ »ç¿ëÀÌ ¾ÈµË´Ï´Ù. <br> ";
						
			break;
		case "4": //ÀüÃ¼ÇÒÀÎ ÆÛ¼¾Æ®
			contents +=  "<br><br> \r\n"
					+ "[ ³»     ¿ë ]  <br> \r\n"
					+ " - "+temp.get("e_DcPer")+"(%) ÇÒÀÎ ÄíÆù ÀÔ´Ï´Ù.<br> \r\n"
					+ " - ¸ÅÀå¿¡¼­ »óÇ° ±¸¸Å ÈÄ "+temp.get("e_MinLimitPri")+"¿ø ÀÌ»ó ±¸¸Å ½Ã <br> \r\n"
					+ " &nbsp º» ÄíÆùÀ» Á¦½ÃÇÏ½Ã¸é ±¸¸Å ±Ý¾×ÀÇ "+temp.get("e_DcPer")+"% ÇÒÀÎ µË´Ï´Ù. <br><br>\r\n"
					+ "[ »ç¿ë¹æ¹ý ] <br> \r\n"
					+ " -  °è»ê¿ø¿¡°Ô ¾Ë¸²³»¿ªÀÇ ÄíÆù ¹ÙÄÚµå¸¦ Á¦½ÃÇØ ÁÖ¼¼¿ä!! <br><br>\r\n"
					+ "[ ÁÖÀÇ»çÇ× ]<br> \r\n"
					+ " - ´Ù¸¥ ÄíÆù°ú Áßº¹ »ç¿ëÀÌ ¾ÈµË´Ï´Ù.<br>";
					
			break;
		case "5": //´ÜÇ°ÇÒÀÎ ¿ø
			contents +=  "<br><br> \r\n"
						+ "[ ³»     ¿ë ]  <br>\r\n"
						+ " - "+temp.get("g_name2")+"/ "+temp.get("e_pDCPri")+"¿ø ÇÒÀÎ ÄíÆù ÀÔ´Ï´Ù. <br>\r\n"
						+ " - ¸ÅÀå¿¡¼­ À§ »óÇ° ±¸¸Å ½Ã º» ÄíÆùÀ» Á¦½ÃÇÏ½Ã¸é <br>";
					if("0".equals(temp.get("e_pAppCnt"))){
						contents += " &nbsp ±¸¸Å ¼ö·® Á¦ÇÑ¾øÀÌ °³´ç "+temp.get("e_pDCPri")+"¿ø ÇÒÀÎ µË´Ï´Ù. \r\n";
					}else{
						contents += " &nbsp ÀÏÈ¸ ±¸¸Å ½Ã ÃÖ´ë "+temp.get("e_pAppCnt")+"°³ ±îÁö "+temp.get("e_pDCPri")+"¿ø/°³´ç ÇÒÀÎ µË´Ï´Ù. \r\n";
					}										
					contents += "<br><br>\r\n "
								+ "[ »ç¿ë¹æ¹ý ]<br>\r\n"
								+ " - °è»ê¿ø¿¡°Ô ¾Ë¸²³»¿ªÀÇ ÄíÆù ¹ÙÄÚµå¸¦ Á¦½ÃÇØ ÁÖ¼¼¿ä!! \r\n"
								+ "<br><br>\r\n"
								+ "[ ÁÖÀÇ»çÇ× ] <br> \r\n";
			contents += "0".equals(temp.get("e_pOnlyOne")) ? " - »ç¿ë±â°£³» ¹«Á¦ÇÑ »ç¿ë°¡´É<br>\r\n" : " - »ç¿ë±â°£³» 1È¸¸¸ »ç¿ë°¡´ÉÇÕ´Ï´Ù.<br>\r\n";			
			break;
		case "6": //´ÜÇ°ÇÒÀÎ ÆÛ¼¾Æ®
			contents += "<br><br>\r\n"
						+ "[ ³»     ¿ë ]<br>\r\n"
						+ " - "+temp.get("g_name2")+"/ "+temp.get("e_pDcPer")+"% ÇÒÀÎ ÄíÆù ÀÔ´Ï´Ù.<br> \r\n"
						+ " &nbsp ¸ÅÀå¿¡¼­ »óÇ° ±¸¸Å ½Ã º» ÄíÆùÀ» Á¦½ÃÇÏ½Ã¸é<br> \r\n";	
					
					if("0".equals(temp.get("e_pAppCnt"))){
						contents += " &nbsp ±¸¸Å ¼ö·® Á¦ÇÑ¾øÀÌ °³´ç "+temp.get("e_pDcPer")+"% ÇÒÀÎ µË´Ï´Ù. <br>\r\n"
									 + " &nbsp ´Ü! ÃÑ ÇÒÀÎ ±Ý¾× "+temp.get("e_pDcPerLimit")+"¿ø ±îÁö µË´Ï´Ù.\r\n";
					}else{
						contents += " &nbsp ÀÏÈ¸ ±¸¸Å ½Ã ÃÖ´ë "+temp.get("e_pAppCnt")+"°³ ±îÁö<br> \r\n"
								      + " &nbsp °³´ç " +temp.get("e_pDcPer")+"% ÇÒÀÎ µË´Ï´Ù. <br>\r\n"
									  + " &nbsp ´Ü! ÃÑ ÇÒÀÎ ±Ý¾× "+temp.get("e_pDcPerLimit")+"¿ø ±îÁö µË´Ï´Ù.\r\n";
					}
					contents += "<br><br>\r\n"
								+ "[ »ç¿ë¹æ¹ý ] <br>\r\n"
								+ " - °è»ê¿ø¿¡°Ô ¾Ë¸²³»¿ªÀÇ ÄíÆù ¹ÙÄÚµå¸¦ Á¦½ÃÇØ ÁÖ¼¼¿ä!! <br><br>\r\n"
								+ "[ ÁÖÀÇ»çÇ× ] <br>\r\n";
					contents += "0".equals(temp.get("e_pOnlyOne")) ? "»ç¿ë±â°£³» ¹«Á¦ÇÑ »ç¿ë°¡´É" : "»ç¿ë±â°£³» 1È¸¸¸ »ç¿ë°¡´ÉÇÕ´Ï´Ù.<br>";					
			break;
		}
		
		
			editorPane_preview_contents.setText(contents);
		
	}
	
	
	
	
	   //ÀÌº¥Æ® ÄÚµå ³»¿ë ºÒ·¯¿À±â
    private void tranevt_EvtCall(){   	
    	    		
		this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
		//¸ñ·ÏÀ» È£ÃâÇÕ´Ï´Ù.
		JSONArray temp_event = trans_shopapi.getPushEventList();
				
		dtm_tranevt.setRowCount(0);
		
		if(temp_event.size() <= 0){			
			JOptionPane.showMessageDialog(this, "ÀÌº¥Æ® ¸ñ·Ï °Ë»ö¿¡ ½ÇÆÐ Çß½À´Ï´Ù.");
			return;
		}				
		
		for(int i = 0; i < temp_event.size(); i++){
			JSONObject temp_map = (JSONObject)temp_event.get(i);
			Vector<Object> v = new Vector<Object>();
			v.addElement(temp_map.get("idx"));
			v.addElement(temp_map.get("subject"));
			v.addElement(temp_map.get("push_msg"));
			v.addElement(temp_map.get("push_type"));
			dtm_tranevt.addRow(v);						
		}		
		    
	    this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));		
    	    	
    }
	
	
    //¸Þ¼¼Áö Àü¼Û ¸®¼Â
    private void msgmsg_ReSet(){    	
    	System.out.println(Server_Config.getOFFICENAME());
    
    	tranmsg_text_title.setText(Server_Config.getOFFICENAME());
    	tranmsg_textArea_msg.setText("");
    	tranmsg_textArea_contents.setText("");
    	tranmsg_text_linkurl.setText("");
    	tranmsg_label_msginfo.setText(state_n);
    	
    }
    
	//¸Þ¼¼Áö ÀúÀå ¸®½ºÆ® ºÒ·¯¿À±â
	private void messageSaveList(){
		
		DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
		celAlignCenter.setHorizontalAlignment(JLabel.CENTER);	
		
		String[] colunm_msglist = {"ÄÚµå", "Á¦¸ñ", "¸Þ¼¼Áö", "º»¹®", "¸µÅ©", "µî·ÏÀÏ"};
		
		dtm_msglist = new DefaultTableModel(null, colunm_msglist){		
			private static final long serialVersionUID = 456847231284L;
	
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
		
		tranmsg_table_list = new JTable(dtm_msglist);
		tranmsg_scroll_list.setViewportView(tranmsg_table_list);
		
		JTableHeader header_msglist = tranmsg_table_list.getTableHeader();
	    Dimension d_msglist = header_msglist.getPreferredSize();
	    d_msglist.height = 40;
	    header_msglist.setPreferredSize(d_msglist);
	    
	    //ÄíÆù ¸®½ºÆ®Çì´õ ºÎºÐ Áß¾ÓÁ¤·Ä
	    ((DefaultTableCellRenderer)tranmsg_table_list.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(0);
	    
	    tranmsg_table_list.setRowHeight(25);
	    tranmsg_table_list.getTableHeader().setReorderingAllowed(false);  //ÀÌµ¿ºÒ°¡
	    
	    //center_table_couponlist.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);  //°¡·Î ½ºÅ©·Ñ	    
	    
	    TableColumnModel tcm_msglist = tranmsg_table_list.getColumnModel();
	
	    //tcm_uselist.getColumn(0).setMaxWidth(Integer.MAX_VALUE);
	    tcm_msglist.getColumn(0).setWidth(40);
	    //tcm_uselist.getColumn(0).setCellRenderer(celAlignCenter);
	    tcm_msglist.getColumn(0).setPreferredWidth(40);
	    tcm_msglist.getColumn(0).setCellRenderer(celAlignCenter);
	
	    //Á¦¸ñ
	    tcm_msglist.getColumn(1).setWidth(200);	    
	    tcm_msglist.getColumn(1).setPreferredWidth(200);
	    
	    //¸Þ¼¼Áö
	    tcm_msglist.getColumn(2).setWidth(200);
	    tcm_msglist.getColumn(2).setPreferredWidth(200);
	    tcm_msglist.getColumn(2).setCellRenderer(celAlignCenter);	    
	    
	    //º»¹®
	    tcm_msglist.getColumn(3).setWidth(0);
	    tcm_msglist.getColumn(3).setMinWidth(0);
	    tcm_msglist.getColumn(3).setMaxWidth(0);
	    //tcm_msglist.getColumn(3).setCellRenderer(celAlignCenter);
	    	
	    //¸µÅ©
	    tcm_msglist.getColumn(4).setWidth(0);
	    tcm_msglist.getColumn(4).setMinWidth(0);
	    tcm_msglist.getColumn(4).setMaxWidth(0);
	    //tcm_msglist.getColumn(4).setCellRenderer(celAlignCenter);
	    
	    //µî·ÏÀÏ
	    tcm_msglist.getColumn(5).setWidth(200);
	    tcm_msglist.getColumn(5).setPreferredWidth(200);
	    tcm_msglist.getColumn(5).setCellRenderer(celAlignCenter);
		//tranmsg_scroll_list.setViewportView(tranmsg_table_list);
		
		tranmsg_table_list.addMouseListener(new MouseListener() {
			
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
					System.out.println("¸¶¿ì½º µÎ¹ø Å¬¸¯ µÆ½À´Ï´Ù.");
					//¿ìÃøÀ¸·Î ÀÚ·á¸¦ Àü¼Û ÇÕ´Ï´Ù.
					getMessageToEdit();
					
				} // ´õºíÅ¬¸¯			
			}
		});
		
	}   
    
	//¸Þ¼¼Áö ¼öÁ¤Ã¢À¸·Î º¸³À´Ï´Ù.
	private void getMessageToEdit(){
		
		int row = tranmsg_table_list.getSelectedRow();	
		
		String title = (String)dtm_msglist.getValueAt(row, 1);
		String msg = (String)dtm_msglist.getValueAt(row, 2);
		String content = (String)dtm_msglist.getValueAt(row, 3);
		String link = (String)dtm_msglist.getValueAt(row, 4);
		
		tranmsg_text_title.setText(title);
		tranmsg_textArea_msg.setText(msg);
		tranmsg_textArea_contents.setText(content);
		if(null != link){
			tranmsg_text_linkurl.setText(link);
		}else{
			tranmsg_text_linkurl.setText("");
		}
		String temp = dtm_msglist.getValueAt(row, 2).toString();			
		
		byte[] cnt = temp.getBytes();
		
		if(cnt.length > cut_cnt){
			tranmsg_label_msginfo.setText(state_y+" : "+cnt.length);
		}else{		
			tranmsg_label_msginfo.setText(state_n+" : "+cnt.length);
		}			
	}
		
		
	private void setImageList(){
    	
    	int last_number = 0;
    	
    	//ÆÇ³Ú ÃÊ±âÈ­
    	tranimg_panel_imgview.removeAll();
    	
    	//ÇöÀç ÆäÀÌÁö°¡ 1ÀÏ¶§
    	//ÇöÀç ÆäÀÌÁö°¡ ¸¶Áö¸· ÆäÀÌÁö ÀÏ¶§ ³ª¸ÓÁö°¡ ÀÖÀ¸¸é ³ª¸ÓÁö±îÁö¸¸
    	if(image_page_num == Integer.parseInt(tranimg_label_totalpage.getText())){
    		//ÃÖÁ¾ ¼ö·®À» µî·Ï ÇÕ´Ï´Ù.
    		last_number = image_total_count;
    	}else{
    		//ÃÖÁ¾ ¼ö·® ÀÔ´Ï´Ù.
    		last_number = image_page_num * image_page_listcount;
    	}
    	
    	//System.out.println(last_number);
    	for(int count = (image_page_num * image_page_listcount)-image_page_listcount; count < last_number; count++){
    		
    		String file_name = tran_strarr_filelist[count].toString();
    		
    		String file_path = "";
    		//¼öÁ¤ÇÏ±â ÀüÀÔ´Ï´Ù. Å×½ºÆ®
    		//file_path = "http://14.38.161.45:8080/image/"+Server_Config.getFTPMARTPATH()+"/"+file_name; //ÀÌ¹ÌÁöÀÇ °æ·Î
    		
    		if(GUBUN.equals("FTP")){
    			file_path = "http://14.38.161.45:8080/image/"+Server_Config.getFTPMARTPATH()+"/"+file_name;
    		}else{
    			file_path = tranimg_text_pcpath.getText()+"/"+file_name;
    			//System.out.println("ÆÄÀÏÀÌ¸§ : "+file_path);
    		}
    		
    		//System.out.println(file_path);
    		
    		//¸ñ·Ï¸®½ºÆ®¸¦ »ý¼ºÇÕ´Ï´Ù.
    		tranimg_panel_imgview.add(setImageView(file_name, file_path));    		
    		this.repaint();
    	}
    }
		
    public JPanel setImageView(String file_name, String file_path){
    	
    	//ÀÌ¹ÌÁö Á¶°¢À» ½ÇÇàÇÕ´Ï´Ù.
    	//ÀÌ¹ÌÁö°¡ º¸ÀÏ Á¶°¢À» ¸¸µì´Ï´Ù.
    	JPanel panel = new JPanel();
    	
    	Box box = Box.createVerticalBox();    	
    	
    	JLabel image_show = new JLabel();
    	box.add(image_show);
    	
    	JLabel image_path = new JLabel(GUBUN);
    	image_path.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
    	box.add(image_path);
    	
    	JLabel g_name = new JLabel(setSubString(file_name, 22));
    	g_name.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 11));
    	box.add(g_name);
    	
    	JButton file_choose = new JButton("¼±ÅÃ");    	    	
    	file_choose.addActionListener(new ActionListener() { 
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//ÀÌ¹ÌÁö ¼±ÅÃ ¹öÆ° ¹Ù·Î ½áÁ®¾ß ÇÕ´Ï´Ù.
				setImageChoose(file_name);
			}
		});
    	
    	JButton file_delete = new JButton("»èÁ¦");
    	file_delete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				deleteImageChooseFile(file_name);
			}
		});
    	    	
    	JPanel jp = new JPanel(new MigLayout("", "[][]", "[]"));
    	jp.add(file_choose, "cell 0 0, grow");
    	jp.add(file_delete, "cell 1 0, grow");
    	
    	box.add(jp, Box.CENTER_ALIGNMENT);
      	panel.add(box);
    	
    	Image image = null;
    	try{	    	
    		
    		if(GUBUN.equals("FTP")){
    			URL url = new URL(file_path);
    	        image = ImageIO.read(url);
        	}else{
        		File file = new File(file_path);
        		image = ImageIO.read(file);
        	}
	        
        } catch (IOException e) {
        	JOptionPane.showMessageDialog(this, e.getMessage());
        	this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }                
       	image_show.setIcon(new ImageIcon(image.getScaledInstance(150, 180, Image.SCALE_SMOOTH)));
       	
       	return panel;
    }    
	    	
	//¹ÙÀÌÆ® ´ÜÀ§·Î ÇÑ±ÛÀ» Àß¶ó ³À´Ï´Ù.
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

    //¼±ÅÃµÈ ÆÄÀÏ FTPÆú´õ¿¡¼­ »èÁ¦ ÇÏ±â
    private void deleteImageChooseFile(String file_name){
    	
    	this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
    	boolean result = false;
    	//System.out.println("¼±ÅÃµÈ °÷ : "+GUBUN+" ¼±ÅÃµÈ ÆÄÀÏ¸í : "+file_name);
    	if(GUBUN.equals("FTP")){
        	
        	//Å×½ºÆ®
        	String ftp_ip = "14.38.161.45";
        	int ftp_port = 8000;
        	
        	String ftp_id = Server_Config.getFTPID();
        	String ftp_pw = Server_Config.getFTPPW();
        	String ftp_path = Server_Config.getFTPMARTPATH();
        	
        	FTPClient ftpConn = new FTPClient();
        	    	
        	try {
    			ftpConn.connect(ftp_ip, ftp_port);			
    			ftpConn.login(ftp_id, ftp_pw);    	
    	    	    			
    			ftpConn.changeDirectory("image/"+ftp_path);    	    	
    			System.out.println(ftpConn.currentDirectory());
    			
    			String[] file_str = ftpConn.listNames();
    			for(String name:file_str){
    				if(name.equals(file_name)){
    					ftpConn.deleteFile(file_name);
    					result = true;
    				}
    			}    			
    		} catch (IllegalStateException | IOException | FTPIllegalReplyException | FTPException | FTPDataTransferException | FTPAbortedException | FTPListParseException e) {
    			// TODO Auto-generated catch block
    			JOptionPane.showMessageDialog(this, e.getMessage());
    			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));    	
    			return;
    		}
    	}else{
    		
    		//¸ÅÀå ÀÌ¹ÌÁö »èÁ¦ÇÏ±â
    		String file_path = tranimg_text_pcpath.getText();
    		File file = new File(file_path, file_name);		
    		if(file.exists()){
    			file.delete();
    			result = true;
    		}    		
    	}
    	
    	if(result){    		
    		JOptionPane.showMessageDialog(this, "ÆÄÀÏÀ» »èÁ¦ Çß½À´Ï´Ù."); 
    		final List<String> list = new ArrayList<String>();
    		Collections.addAll(list, tran_strarr_filelist);
    		list.remove(file_name);
    		
    		tran_strarr_filelist = list.toArray(new String[list.size()]);
    		tranimg_SetImage();
    		
    	}
    	
    	this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }	    
	
 	//°Ë»öµÈ µ¥ÀÌÅ¸¸¦ ¸ñ·ÏÀ¸·Î º¯°æÇÕ´Ï´Ù.
    private void tranimg_SetImage(){
    	
    	this.repaint();
    	msgimg_ReSet("ÀÌ¹ÌÁö");
    	
    	Dimension dm = tranimg_scroll_imglist.getSize();    	
    	int width = (int)dm.getWidth();    	  	
    	
    	//System.out.println("W : "+width);
    	//¼ö·®À» ±¸ÇØ ¿É´Ï´Ù.
    	int p = width/150;
    	int c = p*6;    	
    	//System.out.println("W : "+p+" H :"+c);
    	
    	tranimg_panel_imgview.setLayout(new GridLayout(0, p));
    	image_page_listcount = c;    	
    	
    	int totalCount = tran_strarr_filelist.length;
    	//System.out.println("ÃÑ ¼ö·® : "+totalCount);
    	
    	//ÃÑ °Ë»ö »óÇ° ¼ö·®
    	image_total_count = totalCount;
    	    	
    	/*if(totalCount <= image_page_listcount){
    		tranimg_label_totalpage.setText(String.valueOf(1));
    	}else{
	    	//ÃÑ ÆäÀÌÁö ¼ö·® = ÇöÀç¼ö·®/ÇÑ¹ø¿¡ º¸ÀÏ ÀÌ¹ÌÁö¼ö·®
	    	image_page_count = totalCount/image_page_listcount;
	    	tranimg_label_totalpage.setText(String.valueOf(image_page_count));    	    	
    	}*/
    	
    	//ÃÑ ÆäÀÌÁö ¼ö·®
    	image_page_count = totalCount/image_page_listcount;
    	    	
    	//ÇÑ ÆäÀÌÁö 10°³¾¿ ²÷°í ³ª¸ÓÁö ÀÌ¹ÌÁö°¡ ÀÖ´Ù¸é ÇÑÆäÀÌÁö ´õ º¸ÀÌ±â
    	if(totalCount%image_page_listcount > 0){
    		image_page_count++;
    	}
    	
    	//ÇöÀçÆäÀÌÁö ¹øÈ£
    	image_page_num = 1;
    	//tranimg_label_nowpage.setText(String.valueOf(image_page_num));   	
    	
    	//ÇöÀç ÆäÀÌÁö ¹× ÃÑÆäÀÌÁö Ç¥½Ã
    	tranimg_label_nowpage.setText(String.valueOf(1));
    	tranimg_label_totalpage.setText(String.valueOf(image_page_count));
    	//System.out.println("ÃÑ ÆäÀÌÁö ¼ö·® : "+image_page_count);
    	
    	setImageList();
    }
    	
    //ÀÌ¹ÌÁö ¸ÞÁ¦Áö Àü¼ÛÀ» ¸®¼ÂÇÕ´Ï´Ù.    
    private void msgimg_ReSet(String gubun){
    	    	
    	
    	if(gubun.equals("³»¿ë")){
    		tranimg_text_title.setText(Server_Config.getOFFICENAME());
    		tranimg_textArea_msg.setText("");
    		tranimg_text_linkurl.setText("");
    		
    		goodscontents.text_top_intext.setOpaque(false);
    		//text_imgtop_title.setText(Server_Config.getOFFICENAME());
    		
    		goodscontents.text_top_intext.setOpaque(false);
    		//text_imgbottom_title.setText("ÇÏ´Ü¸Þ¼¼Áö");    		
    	}else if(gubun.equals("Àü´ÜÁö")){// if(gubun.equals("»óÇ°")){    		
    		//tranimg_middle_imgin.removeAll();
    		tranimg_editorPane_img.setText("");
    		tranimg_editorPane_img.removeAll();
    	//}else{
    		tranimg_editorPane_img.setText("");
        	tranimg_label_imgpath.setText("Path");
        	tranimg_text_imgpath.setText("");
        	editorPane_preview_contents.setText("");
    	}
    	//tranimg_text_title.setText(Server_Config.getOFFICENAME());    	    		
		//tranimg_textArea_msg.setText("");    	
    	
    	
    	
    	
    	
    	
		
		this.repaint();
		validate();
    }    
    	    
    //ÆÄÀÏ ¼±ÅÃ ½Ã ¹Ì¸®º¸±â È­¸éÀ¸·Î ¿Å±â±â
    private void setImageChoose(String file_name){    
    	System.out.println("¼±ÅÃµÈ °÷ : "+GUBUN+" ¼±ÅÃµÈ ÆÄÀÏ¸í : "+file_name);
    	String path = "";
    	String img_path = "";
    	
    	if(GUBUN.equals("FTP")){
    		path = "<Html><img src='http://14.38.161.45:8080/image/"+Server_Config.getFTPMARTPATH()+"/"+file_name+"' border=0 width='500' height='700' alt=''></html>";
    		img_path = Server_Config.getFTPMARTPATH()+"/"+file_name;
    	}else{	
			path = "<html><img src='file:"+tranimg_text_pcpath.getText()+"/"+file_name+"' width=500 height=700 alt='' border=0 ></html>";
			img_path = file_name;	
    	}
    	
    	tranimg_label_imgpath.setText(GUBUN);
    	tranimg_editorPane_img.setText(path);
    	tranimg_text_imgpath.setText(img_path);
    }
	
  //¸Þ¼¼Áö Àü¼ÛÇÏ±â
	private void pushTranStart(){
		
		//now we want to get the String identifier of the top card:
		JPanel card = null;
		for (Component comp : panel_coupontran_2.getComponents()) {
		    if (comp.isVisible() == true) {
		        card = (JPanel) comp;
		    }
		}
		
		String name = card.getName();
		System.out.println(name);
		switch(name){			
		case "message":
			setPushMessage();
			break;			
		case "image":
			setPushImage();
			break;
		case "event":
			setPushEvent();
			break;
		}
		
	}
	
	//Çª½Ã ¸Þ½ÃÁö
	private void setPushMessage(){
		
		this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
		HashMap<String, Object> push_list = new HashMap<String, Object>();
		
		//Àü¼Û´ë»ó ºÒ·¯¿À±â
		//°¡ÀÔ¿©ºÎ ALL/Y/N 
		String mem_only = "";
		switch(tran_combo_joinyn.getSelectedIndex()){
		case 0:
			mem_only = "ALL";
			break;
		case 1:
			mem_only = "N";			
			break;
		case 2:
			mem_only = "Y";
			break;			
		}
		String mem_id = tran_text_memid.getText(); 
		String mem_hp = tran_text_hp.getText();				
		String platform = ""; 
		switch(comboBox_tran_platform.getSelectedIndex()){
		case 1:
			platform = "android";
			break;
		case 2:
			platform = "ios";
			break;			
		}			
		
		//Àü¼ÛÇÒ ³»¿ë ºÒ·¯¿À±â
		String title = tranmsg_text_title.getText();
		String message = tranmsg_textArea_msg.getText();
		String contents = tranmsg_textArea_contents.getText();	
		String noti_img_url = "http://14.38.161.45:8080/image/"+Server_Config.getOFFICECODE()+"/Noti_Message.jpg";			
		String push_content_img = "";
		String link  = tranmsg_text_linkurl.getText();
		String content_mode = "text";
					
		String event_code = "";
		
		//À¯È¿¼ºÃ¼Å©
		if(title.length() <= 0){
			JOptionPane.showMessageDialog(this, "¸Þ¼¼Áö Á¦¸ñÀ» ÀÔ·ÂÇØ ÁÖ¼¼¿ä!!");
			tranmsg_text_title.requestFocus();
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			return;
		}
		
		if(message.length() <= 0){
			JOptionPane.showMessageDialog(this, "¸Þ¼¼Áö ³»¿ëÀ» ÀÔ·ÂÇØ ÁÖ¼¼¿ä!!");
			tranmsg_textArea_msg.requestFocus();
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			return;
		}
					
		push_list.put("Mem_Only", mem_only);
		push_list.put("Mem_Id", mem_id);
		push_list.put("Hp", mem_hp);
		push_list.put("Platoform", platform);	
		
		push_list.put("Title", title);
		push_list.put("Message", message);
		push_list.put("Content", contents);
		push_list.put("Noti_Img_Url", noti_img_url);			
		push_list.put("Img_Url", push_content_img);
		push_list.put("Link", link);
		push_list.put("Mode", content_mode);
		push_list.put("Event", event_code);
		
		JSONObject result = trans_shopapi.tranNewPushSubimt(push_list);
		
		String res = "<Html>"+result.get("result_msg")+"<br>"+result.get("result_cnt")+" °Ç ¹ß¼ÛµÊ</Html>";
		JOptionPane.showMessageDialog(this, res);
		this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}
	
	//Çª½Ã ÀÌ¹ÌÁö
	private void setPushImage(){
		
		this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
		
		HashMap<String, Object> push_list = new HashMap<String, Object>();
		
		//Àü¼Û´ë»ó ºÒ·¯¿À±â
		//°¡ÀÔ¿©ºÎ ALL/Y/N 
		String mem_only = "";
		switch(tran_combo_joinyn.getSelectedIndex()){
		case 0:
			mem_only = "ALL";
			break;
		case 1:
			mem_only = "N";			
			break;
		case 2:
			mem_only = "Y";
			break;			
		}
		
		String mem_id = tran_text_memid.getText(); 
		String mem_hp = tran_text_hp.getText();				
		String platform = ""; 
		switch(comboBox_tran_platform.getSelectedIndex()){
		case 1:
			platform = "android";
			break;
		case 2:
			platform = "ios";
			break;	
		}
		
		
		//Àü¼ÛÇÒ ³»¿ë ºÒ·¯¿À±â
		String title = tranimg_text_title.getText();
		String message = tranimg_textArea_msg.getText();		
		String noti_img_url = "http://14.38.161.45:8080/image/"+Server_Config.getOFFICECODE()+"/Noti_Image.jpg";
		String img_url = "";
		String contents = tranimg_textArea_bottommsg.getText();
		String contents_mode = "html";
		String link  = tranmsg_text_linkurl.getText();
		String event_code = "";
		
		contents = "<pre>"+contents+"</pre>";
		
		if("couponflyzer".equals(contents_choose_item)){
			String coupon = editorPane_preview_contents.getText();
			contents = contents + coupon;
			noti_img_url = "http://14.38.161.45:8080/image/"+Server_Config.getOFFICECODE()+"/Noti_Coupon.jpg";
		}
		
		System.out.println(contents);
		//À¯È¿¼ºÃ¼Å©
		if(title.length() <= 0){
			JOptionPane.showMessageDialog(this, "Á¦¸ñÀ» ÀÔ·ÂÇØ ÁÖ¼¼¿ä!!");
			tranmsg_text_title.requestFocus();
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			return;
		}
		
		if(message.length() <= 0){
			message = title;
		}
		
		//ÀÌ¹ÌÁö »ý¼º
		String currentDir = System.getProperty("user.dir");

	    System.out.println(currentDir);
	    File file = new File(currentDir+"\\temp_img");//, "temp_img.jpg");	    
	    System.out.println("Æú´õ »ý¼º");
	    
	    if(!file.exists()){
	    	file.mkdir();
	    }
	    // Çü½Ä ÁöÁ¤
	    Date dt = new Date();
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
	    String datefile =sdf.format(dt).toString();
	    
	    file = new File(currentDir+"\\temp_img", datefile+"_img.jpg"); 
	    if(!file.isFile()){
	    	try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    	    
	    BufferedImage bi = new BufferedImage(tranimg_editorPane_img.getWidth(), tranimg_editorPane_img.getHeight(), BufferedImage.TYPE_INT_RGB);
	    Graphics2D g2 = bi.createGraphics();
	    tranimg_editorPane_img.printAll(g2);
	    g2.dispose();
	    
        try {
            ImageIO.write(bi, "png", file);            
        } catch (Exception ex) {
            System.out.println("Error saving");
        }
	    
	   /* try {
			resize(file, 250, 350);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			JOptionPane.showMessageDialog(this, "ÀÌ¹ÌÁö¸¦ »ý¼ºÇÏÁö ¸øÇß½À´Ï´Ù.\r\n"+e1.getMessage());
			return;
		}
		*/
	    /*try {
			resize(file, 500, 700);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			JOptionPane.showMessageDialog(this, "ÀÌ¹ÌÁö¸¦ »ý¼ºÇÏÁö ¸øÇß½À´Ï´Ù.\r\n"+e1.getMessage());
			return;
		}*/
	    
		img_url = file.getName();
		
		System.out.println(img_url);
		//Å×½ºÆ®
    	String ftp_ip = "14.38.161.45";
    	int ftp_port = 8000;
  	
    	String ftp_id = Server_Config.getFTPID();
    	String ftp_pw = Server_Config.getFTPPW();
    	String ftp_path = Server_Config.getFTPMARTPATH();
    	
    	FTPClient ftpConn = new FTPClient();
    	    	
    	try {
			ftpConn.connect(ftp_ip, ftp_port);			
			ftpConn.login(ftp_id, ftp_pw);  
	    	
			//Å×½ºÆ® Æú´õ
			ftpConn.changeDirectory("image/"+ftp_path);		    	
			System.out.println(ftpConn.currentDirectory());
			
			String[] file_name = ftpConn.listNames();
			
			for(String name:file_name){
				if(name.equals(img_url)){
					ftpConn.deleteFile(name);
				}
			}
	
			ftpConn.upload(file);
	    	
		} catch (IllegalStateException | IOException | FTPIllegalReplyException | FTPException | FTPDataTransferException | FTPAbortedException | FTPListParseException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(this, e.getMessage());
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));    	
			return;
		}
    	
		img_url = "http://14.38.161.45:8080/image/"+Server_Config.getFTPMARTPATH()+"/"+img_url;
		
		push_list.put("Mem_Only", mem_only);
		push_list.put("Mem_Id", mem_id);
		push_list.put("Hp", mem_hp);
		push_list.put("Platoform", platform);
		
		push_list.put("Title", title);
		push_list.put("Message", message);
		push_list.put("Content", contents);
		push_list.put("Noti_Img_Url", noti_img_url);			
		push_list.put("Img_Url", img_url);
		push_list.put("Link", link);
		push_list.put("Mode", contents_mode);
		push_list.put("Event", event_code);
		
		JSONObject result = trans_shopapi.tranNewPushSubimt(push_list);
		
		String cnt = ""+result.get("result_cnt");
		String res = "<Html>"+result.get("result_msg")+"<br>"+cnt.trim()+" °Ç ¹ß¼ÛµÊ</Html>";
		String query = "";
		if("couponflyzer".equals(contents_choose_item) && "OK".equals(result.get("result_code"))){
			String coupon_code = (String)table_coupon_couponlist.getValueAt(table_coupon_couponlist.getSelectedRow(), 1);
			query = "Insert into e_PushTrans_History (push_title, push_type, e_seq, push_count) values('"+title+"', '"+contents_mode+"', '"+coupon_code+"', '"+cnt+"') ";			
		}else{
			query = "Insert into e_PushTrans_History (push_title, push_type, push_count) values('"+title+"', '"+contents_mode+"', '"+cnt.trim()+"') ";
		}
		ms_connect.setMainSetting();
		ms_connect.connect_update(query);
		JOptionPane.showMessageDialog(this, res);
		
		this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}
	
	//Çª½Ã ÀÌº¥Æ®
	private void setPushEvent(){
		
		this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		
		int row = tranevt_table_evtlist.getSelectedRow();
		
		if(row < 0){
			JOptionPane.showMessageDialog(this, "Àü¼ÛÇÒ ÀÌº¥Æ®¸¦ ¼±ÅÃÇØ ÁÖ¼¼¿ä!!");
			return;
		}
		
		HashMap<String, Object> push_list = new HashMap<String, Object>();		
		
		//Àü¼Û´ë»ó ºÒ·¯¿À±â
		//°¡ÀÔ¿©ºÎ ALL/Y/N 
		String mem_only = "";
		switch(tran_combo_joinyn.getSelectedIndex()){
		case 0:
			mem_only = "ALL";
			break;
		case 1:
			mem_only = "N";
			break;
		case 2:
			mem_only = "Y";
			break;
		}
		
		String mem_id = tran_text_memid.getText(); 
		String mem_hp = tran_text_hp.getText();				
		String platform = ""; 
		switch(comboBox_tran_platform.getSelectedIndex()){
		case 1:
			platform = "android";
			break;
		case 2:
			platform = "ios";
			break;			
		}			
		
		//À¯È¿¼ºÃ¼Å©¸¦ ÇÕ´Ï´Ù.	
		String title = (String)dtm_tranevt.getValueAt(row, 1);
		String message = (String)dtm_tranevt.getValueAt(row, 2);
		String img_url = "";
		String link = "";
		String event_code = (String)dtm_tranevt.getValueAt(row, 0);	
		
		push_list.put("Mem_Only", mem_only);
		push_list.put("Mem_Id", mem_id);
		push_list.put("Hp", mem_hp);
		push_list.put("Platoform", platform);
		
		push_list.put("Title", title);
		push_list.put("Message", message);
		push_list.put("Img_Url", img_url);
		push_list.put("Link", link);
		push_list.put("Event", event_code);
		
		JSONObject result = trans_shopapi.setPushSubimt(push_list);		
		String res = "<Html>"+result.get("result_msg")+"<br>"+result.get("result_cnt")+" °Ç ¹ß¼ÛµÊ</Html>";
		JOptionPane.showMessageDialog(this, res);
		this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));	
	}
		
		
	//¸Þ¼¼Áö ºÒ·¯¿À±â
	private void getMessageList(){

		String query = "Select * From e_PushList Where content_mode='text' Order By push_code Desc";
		
		dtm_msglist.setRowCount(0);
		
		ms_connect.setMainSetting();
		ArrayList<HashMap<String, String>> temp_msg = ms_connect.connection(query);
				
		if( temp_msg.size() <= 0 ){
			JOptionPane.showMessageDialog(this, "°Ë»öµÈ ÀÚ·á°¡ ¾ø½À´Ï´Ù.");	
			return;
		}
		
		Iterator<HashMap<String, String>> itr_list = temp_msg.iterator();
				
		while(itr_list.hasNext()){			
			HashMap<String, String> map = itr_list.next();
			Vector<Object> v = new Vector<Object>();
			String write_date = map.get("write_date");
			write_date = write_date.substring(0, 10);
			v.add(map.get("push_idx"));
			v.add(map.get("push_title"));
			v.add(map.get("push_msg"));
			v.add(map.get("push_content"));
			v.add(map.get("push_link"));
			v.add(write_date);
			
			dtm_msglist.addRow(v);
		}
		
	}
	
	//º¸³¾¸Þ¼¼Áö¸¦ ÀúÀåÇÕ´Ï´Ù.
	private void setMessageSave(){
		
		//³»¿ëÀÌ ÀúÀåµÇ¾î ÀÖ´ÂÁö È®ÀÎ ÇÕ´Ï´Ù.
		String title = tranmsg_text_title.getText();
		String msg = tranmsg_textArea_msg.getText();
		String content = tranmsg_textArea_contents.getText();
		content = content.replaceAll("'", "`");
				
		System.out.println(content);
		if(msg.length() <= 0){
			JOptionPane.showMessageDialog(this, "¸Þ¼¼Áö ³»¿ëÀ» ÀÔ·ÂÇØ ÁÖ¼¼¿ä!");
			return;
		}
		
		String query = "Insert Into e_PushList ( push_title, push_msg, push_content, writer, editer) Values("
				 +"'"+title+"', '"+msg+"', '"+content+"', 'shop', 'shop' )";
		
		ms_connect.setMainSetting();
		int result = ms_connect.connect_update(query);
		
		switch(result){
		case 0:
			JOptionPane.showMessageDialog(this, "µî·Ï ¿Ï·á Çß½À´Ï´Ù.");
			break;
		default:
			JOptionPane.showMessageDialog(this, "ÀúÀå ½ÇÆÐ\r\n"+ms_connect.errMsg);
			break;						
		}				
	}	
	
    //PCÆú´õ¿¡¼­ ÀÌ¹ÌÁö¸¦ °Ë»öÇÕ´Ï´Ù.
    private void getPCImage(){
    	
    	this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
    	GUBUN = "PC";
    	String pcpath = tranimg_text_pcpath.getText();
    	
    	JFileChooser jfiledialog = new JFileChooser();
		int ret = 0;
		    	
		jfiledialog.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		
		if(!(pcpath == null || "".equals(pcpath))){			
			jfiledialog.setCurrentDirectory(new File(pcpath).getParentFile());
		}
		
		FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & PNG Images", "jpg", "png", "gif");
		jfiledialog.setFileFilter(filter);
		
		//ÆÄÀÏ¼±ÅÃ Ã¢À» ¶ç¿ó´Ï´Ù.	
		ret = jfiledialog.showOpenDialog(this);
		System.out.println("°á°ú º¸±â : "+ret);
		
		if(ret == JFileChooser.APPROVE_OPTION){
			
			File file = jfiledialog.getSelectedFile();
			if(file.isDirectory()){				
				System.out.println("µð·ºÅä¸® ÀÔ´Ï´Ù. : "+file.getAbsolutePath());
				FilenameFilter fil = new FilenameFilter() {
					private final String[] okfile = new String[] {"jpg", "png", "gif"};
					
					@Override
					public boolean accept(File dir, String name) {
						// TODO Auto-generated method stub
						
						for(String ok: okfile){
							if(name.toLowerCase().endsWith(ok)){
								return true;
							}
						}
						
						return false;
					}
				};
				
				tran_strarr_filelist = file.list(fil);
				
				//ÆÇ³Ú »èÁ¦ÇÏ±â
		    	tranimg_panel_imgview.removeAll();    	
		    	
				if(tran_strarr_filelist.length <= 0){
					
					tranimg_label_totalpage.setText("0");
					tranimg_label_nowpage.setText("0");
					
					image_total_count = 1;
					image_page_num = 1;
					image_page_count = 1;
					
					tranimg_panel_imgview.setLayout(new BorderLayout());
					JLabel label = new JLabel("°Ë»öµÈ ÀÌ¹ÌÁö°¡ ¾ø½À´Ï´Ù.", JLabel.CENTER);
					tranimg_panel_imgview.add(label, BorderLayout.CENTER);
					this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					return;
				}
				
				//°Ë»öµÈ ÀÌ¹ÌÁö¸¦ »Ñ·ÁÁÝ´Ï´Ù.
				tranimg_text_pcpath.setText(file.getPath());
				tranimg_SetImage();				
				
			}else{
				System.out.println("ÆÄÀÏ ÀÔ´Ï´Ù. : "+file.getName());
				
				//ÇöÀçÀÇ ÆÄÀÏÀ» ¹Ù·Î ¼³Á¤ÇÕ´Ï´Ù.
				tranimg_label_imgpath.setText("PC");
				tranimg_text_imgpath.setText(file.getName());
				tranimg_text_pcpath.setText(file.getParent());
				String str = "<html><img src='file:"+file.getAbsolutePath()+"' width=500 height=700 alt='' border=0 ></html>";
				
				tranimg_editorPane_img.setText(str);
			}
			
		}else{
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			return;
		}
		
		this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));    	    	
    }
	
	//FTPÆú´õ¿¡¼­ ÀÌ¹ÌÁö¸¦ °Ë»öÇÕ´Ï´Ù.
    private void getFTPImage(){
    	
    	this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
    	
    	GUBUN = "FTP";
    	//Å×½ºÆ®
    	String ftp_ip = "14.38.161.45";
    	int ftp_port = 8000;
    	
    	//String ftp_ip = "14.38.161.45";
    	//int ftp_port = 7000;
    	
    	String ftp_id = Server_Config.getFTPID();
    	String ftp_pw = Server_Config.getFTPPW();
    	String ftp_path = Server_Config.getFTPMARTPATH();
    	
    	FTPClient ftpConn = new FTPClient();
    	    	
    	try {
			ftpConn.connect(ftp_ip, ftp_port);			
			ftpConn.login(ftp_id, ftp_pw);    	
	    	//Å×½ºÆ® Æú´õ
			ftpConn.changeDirectory("image/"+ftp_path);
	    	//ftpConn.changeDirectory(ftp_path);
			System.out.println(ftpConn.currentDirectory());
			tran_strarr_filelist = ftpConn.listNames();
	    	
		} catch (IllegalStateException | IOException | FTPIllegalReplyException | FTPException | FTPDataTransferException | FTPAbortedException | FTPListParseException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(this, e.getMessage());
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));    	
			return;
		}
    	
    	//ÆÇ³Ú »èÁ¦ÇÏ±â
    	tranimg_panel_imgview.removeAll();    	
    	
		if(tran_strarr_filelist.length <= 0){
			
			tranimg_label_totalpage.setText("0");
			tranimg_label_nowpage.setText("0");
			
			image_total_count = 1;
			image_page_num = 1;
			image_page_count = 1;
			
			tranimg_panel_imgview.setLayout(new BorderLayout());
			JLabel label = new JLabel("°Ë»öµÈ ÀÌ¹ÌÁö°¡ ¾ø½À´Ï´Ù.", JLabel.CENTER);
			tranimg_panel_imgview.add(label, BorderLayout.CENTER);
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			return;
		}
		
		//°Ë»öµÈ ÀÌ¹ÌÁö¸¦ »Ñ·ÁÁÝ´Ï´Ù.
		tranimg_SetImage();
		
		this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));   	
		
    }	
	
    //¹Ìµé»óÇ° ºÒ·¯¿À±â ÀÔ´Ï´Ù.
    private void getFtpImgaeSearch(){
    	
    	dtm_middle_imglist.setRowCount(0);
    	String str = text_middle_searchg.getText();
    	if(str.length() <= 0 ){
    		JOptionPane.showMessageDialog(this, "°Ë»ö¾î¸¦ ÀÔ·ÂÇØ ÁÖ¼¼¿ä!");
    		return;
    	}
    		
    	String query = "Select * From FTP_Image Where g_name like '%"+str+"%' or barcode like '%"+str+"%' ";
    	
    	ms_connect.setImageSetting();
    	ArrayList<HashMap<String, String>> map = ms_connect.connection(query);
    	
    	if(map.size() <= 0){    		
    		JOptionPane.showMessageDialog(this, "°Ë»öµÈ ³»¿ëÀÌ ¾ø½À´Ï´Ù.");
    		return;
    	}
    	
    	for(int i = 0; i < map.size(); i++){    		
    		HashMap<String, String> temp = map.get(i);
    		Vector<Object> v = new Vector<Object>();
    		
    		String img_path = "http://14.38.161.45:7080/"+temp.get("Path")+"/"+temp.get("Barcode")+"."+temp.get("Ext");
    		
    		String img = "<html><img src=\""+img_path+"\" width='50' height='50' border=0 ></html>";
    		//System.out.println(img);
    		v.add(i+1);
    		v.add("»óÇ°");
    		v.add(img_path);
    		v.add(img);
    		v.add(temp.get("G_Name"));    		
    		
    		dtm_middle_imglist.addRow(v);
    	}
    }
    
    
    
  //PCÆú´õ¿¡¼­ ÀÌ¹ÌÁö¸¦ °Ë»öÇÕ´Ï´Ù.
    private void getFlyzerPCImage(){
    	
    	this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
    	
    	//¼±¾ðºÎ
    	JFileChooser jfiledialog = new JFileChooser();
		int ret = 0;
		String[] file_list;    	
		
		
		jfiledialog.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);		
		FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & PNG Images", "jpg", "png", "gif");
		jfiledialog.setFileFilter(filter);
		
		//ÆÄÀÏ¼±ÅÃ Ã¢À» ¶ç¿ó´Ï´Ù.	
		ret = jfiledialog.showOpenDialog(this);
		System.out.println("°á°ú º¸±â : "+ret);
		
		if(ret == JFileChooser.APPROVE_OPTION){
			
			File file = jfiledialog.getSelectedFile();
			if(file.isDirectory()){				
				System.out.println("µð·ºÅä¸® ÀÔ´Ï´Ù. : "+file.getAbsolutePath());
				FilenameFilter fil = new FilenameFilter() {
					private final String[] okfile = new String[] {"jpg", "png", "gif"};
					
					@Override
					public boolean accept(File dir, String name) {
						// TODO Auto-generated method stub
						
						for(String ok: okfile){
							if(name.toLowerCase().endsWith(ok)){
								return true;
							}
						}
						
						return false;
					}
				};
				
				file_list = file.list(fil);
				
				
				//¸®½ºÆ® ÃÊ±âÈ­ ÇÕ´Ï´Ù.
				dtm_middle_imglist.setRowCount(0);
		    	
				if(file_list.length <= 0){
					
					JOptionPane.showMessageDialog(this, "°Ë»öµÈ ÀÌ¹ÌÁö°¡ ¾ø½À´Ï´Ù.");
					this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					return;
				}
				
				//°Ë»öµÈ ÀÌ¹ÌÁö¸¦ »Ñ·ÁÁÝ´Ï´Ù.				
				for(int i=0; i<file_list.length;i++){
					Vector<Object> v = new Vector<Object>();
					String filename = file_list[i];
					v.add(i+1);
					v.add("PC");					
					v.add(file.getAbsolutePath()+"/"+filename);					
					String str = "<html><img src='file:"+file.getAbsolutePath()+"/"+filename+"' width=100 height=100 alt='' border=0 ></html>";
					v.add(str);
					v.add(filename);
					
					dtm_middle_imglist.addRow(v);
				}
				
			}else{
				System.out.println("ÆÄÀÏ ÀÔ´Ï´Ù. : "+file.getName());
				
				//ÇöÀçÀÇ ÆÄÀÏÀ» ¹Ù·Î ¼³Á¤ÇÕ´Ï´Ù.
				String str = "<html><img src='file:"+file.getAbsolutePath()+"' width=500 height=700 alt='' border=0 ></html>";
				tranimg_editorPane_img.setText(str);				
			}
			
		}else{
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			return;
		}
		
		this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));    	    	
    }
    
    
    
  //¹Ìµé»óÇ° ºÒ·¯¿À±â ÀÔ´Ï´Ù.
    private void getFlyzerFTPImage(){
    	
    	this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
    	
    	GUBUN = "FTP";
    	//Å×½ºÆ®
    	String ftp_ip = "14.38.161.45";
    	int ftp_port = 8000;
    	
    	//String ftp_ip = "14.38.161.45";
    	//int ftp_port = 7000;
    	
    	String ftp_id = Server_Config.getFTPID();
    	String ftp_pw = Server_Config.getFTPPW();
    	String ftp_path = "Push_Sample";
    	
    	FTPClient ftpConn = new FTPClient();
    	    	
    	String[] file_list;
    	
    	try {
			ftpConn.connect(ftp_ip, ftp_port);
			ftpConn.login(ftp_id, ftp_pw);    	
	    	//Å×½ºÆ® Æú´õ
			ftpConn.changeDirectory("image/"+ftp_path);
	    	//ftpConn.changeDirectory(ftp_path);
			System.out.println(ftpConn.currentDirectory());
			file_list = ftpConn.listNames();
	    	
		} catch (IllegalStateException | IOException | FTPIllegalReplyException | FTPException | FTPDataTransferException | FTPAbortedException | FTPListParseException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(this, e.getMessage());
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));    	
			return;
		}
    	    	
    	//¸®½ºÆ® ÃÊ±âÈ­ ÇÕ´Ï´Ù.
		dtm_middle_imglist.setRowCount(0);
    	
		if(file_list.length <= 0){
			
			JOptionPane.showMessageDialog(this, "°Ë»öµÈ ÀÌ¹ÌÁö°¡ ¾ø½À´Ï´Ù.");
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			return;
		}
    	
    	//°Ë»öµÈ ÀÌ¹ÌÁö¸¦ »Ñ·ÁÁÝ´Ï´Ù.				
		for(int i=0; i<file_list.length;i++){
			Vector<Object> v = new Vector<Object>();
			String filename = file_list[i];
			if(filename.contains("¹è°æ") || filename.contains(Server_Config.getFTPMARTPATH())){
				v.add(i+1);
				v.add("FTP");					
				filename = "http://14.38.161.45:8080/image/Push_Sample/"+filename;
				v.add(filename);
				String str = "<html><img src='"+filename+"' width=100 height=100 alt='' border=0 ></html>";
				v.add(str);
				v.add(filename);
			
				dtm_middle_imglist.addRow(v);
			}
		}
    	
    	
    	
    	this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    	
    	
    }
    
    
    //PCÆú´õ¿¡¼­ ÀÌ¹ÌÁö¸¦ °Ë»öÇÕ´Ï´Ù.
    private void setListImg_PCCount(){
    	
    	this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
    	
    	//¼±¾ðºÎ
    	JFileChooser jfiledialog = new JFileChooser();
		int ret = 0;
		String[] file_list;
		
		
		jfiledialog.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);		
		FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & PNG Images", "jpg", "png", "gif");
		jfiledialog.setFileFilter(filter);
		
		//ÆÄÀÏ¼±ÅÃ Ã¢À» ¶ç¿ó´Ï´Ù.	
		ret = jfiledialog.showOpenDialog(this);
		System.out.println("°á°ú º¸±â : "+ret);
		
		if(ret == JFileChooser.APPROVE_OPTION){
			
			File file = jfiledialog.getSelectedFile();
			if(file.isDirectory()){				
				System.out.println("µð·ºÅä¸® ÀÔ´Ï´Ù. : "+file.getAbsolutePath());
				FilenameFilter fil = new FilenameFilter() {
					private final String[] okfile = new String[] {"jpg", "png", "gif"};
					
					@Override
					public boolean accept(File dir, String name) {
						// TODO Auto-generated method stub
						
						for(String ok: okfile){
							if(name.toLowerCase().endsWith(ok)){
								return true;
							}
						}
						
						return false;
					}
				};
				
				file_list = file.list(fil);
				
				
				//¸®½ºÆ® ÃÊ±âÈ­ ÇÕ´Ï´Ù.
				dtm_middle_imglist.setRowCount(0);
		    	
				if(file_list.length <= 0){
					
					JOptionPane.showMessageDialog(this, "°Ë»öµÈ ÀÌ¹ÌÁö°¡ ¾ø½À´Ï´Ù.");
					this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					return;
				}
				
				//°Ë»öµÈ ÀÌ¹ÌÁö¸¦ »Ñ·ÁÁÝ´Ï´Ù.				
				for(int i=0; i<file_list.length;i++){
					Vector<Object> v = new Vector<Object>();
					String filename = file_list[i];
					v.add(i+1);
					v.add("»óÇ°");					
					v.add("file:"+file.getAbsolutePath()+"/"+filename);					
					String str = "<html><img src='file:"+file.getAbsolutePath()+"/"+filename+"' width=100 height=100 alt='' border=0 ></html>";
					v.add(str);
					v.add(filename);
					
					dtm_middle_imglist.addRow(v);
				}				
			}/*else{
				System.out.println("ÆÄÀÏ ÀÔ´Ï´Ù. : "+file.getName());
				
				//ÇöÀçÀÇ ÆÄÀÏÀ» ¹Ù·Î ¼³Á¤ÇÕ´Ï´Ù.
				String str = "<html><img src='file:"+file.getAbsolutePath()+"' width=500 height=700 alt='' border=0 ></html>";
				tranimg_editorPane_img.setText(str);				
			}*/
			
		}else{
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			return;
		}
		
		this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));    	    	
    }
    
    
    
    
    
    
    
    
    //¹Ìµé ÀÌ¹ÌÁö ³Ö±â
    private void setListImg_Count(){
    	
    	//int img_allcount =  tranimg_middle_imgin.getComponentCount();
    	int img_allcount =  goodscontents.panel_center.getComponentCount();
    	System.out.println(img_allcount);
    	int c_count = comboBox_middle_gcount.getSelectedIndex();
    	String img_width = "";
    	String img_height = "";
    	String g_name_font =(String)comboBox_goods_display.getSelectedItem();
    	String sale_price_font = (String)comboBox_saleprice_font.getSelectedItem();
    	int g_name_size = (int)spinner_goods_fontsize.getValue();
    	int sale_price_size = (int)spinner_saleprice_fontsize.getValue();
    	int limit_count = 0;
    	switch(c_count){
    	case 0: //1x1
    		img_width = "490";
    		img_height = "600";
    		limit_count = 1;
    		break;
    	case 1: //2x2
    		img_width = "245";
    		img_height = "300";
    		limit_count = 4;
    		break;
    	case 2: //2x3
    		img_width = "245";
    		img_height = "200";
    		limit_count = 6;    		
    		break;
    	case 3: //3x3 
    		img_width = "163";
    		img_height = "200";
    		limit_count = 9;    		
    		break;
    	case 4: //3x4
    		img_width = "163";
    		img_height = "150";
    		limit_count = 12;    		
    		break;
    	}
    	
    	if(img_allcount >= limit_count){
    		JOptionPane.showMessageDialog(this, "´õÀÌ»ó »óÇ°À» Ãß°¡ ÇÒ¼ö ¾ø½À´Ï´Ù.");
    		return;
    	}
    	
    	int row = table_middle_imglist.getSelectedRow();
    	String img_path = (String)table_middle_imglist.getValueAt(row, 2);  	
    	String gname = (String)table_middle_imglist.getValueAt(row, 4);
    	//String sell_pri = (String)table_middle_imglist.getValueAt(row, 4);
    	
    	img_path = "<html><img src=\""+img_path+"\" width='"+img_width+"' height='"+img_height+"' ></html>";
    	//img_path = "<html><body background=\""+img_path+"\" width='"+img_width+"' height='"+img_height+"' ></html>";
    	//Image bg = new ImageIcon(img_path).getImage();
    	
    	JEditorPane panel_down = new JEditorPane();
    	panel_down.setLayout(new BorderLayout());
		panel_down.setContentType("text/html");
		panel_down.setEditable(false);
    	panel_down.setText(img_path);
    	
    	OutlineLabel text1 = new OutlineLabel();
    	text1.setText(gname);
    	text1.setOpaque(false);    	
    	text1.setHorizontalAlignment(SwingConstants.CENTER);
    	text1.setFont(new Font(g_name_font, Font.BOLD, g_name_size));    	
    	text1.setBorder(null);   
    	    	    	
    	OutlineLabel text2 = new OutlineLabel();    	    	
    	text2.setOpaque(false);
    	text2.setForeground(Color.RED);
    	text2.setHorizontalAlignment(SwingConstants.CENTER);
    	text2.setFont(new Font(sale_price_font, Font.BOLD, sale_price_size));
    	text2.setBorder(null);
    	text2.setOutlineSize(2);
    	
    	JPanel panel = new JPanel();
    	panel.setLayout(new BorderLayout());
    	panel.setOpaque(false);    	
    	panel.add(text1, BorderLayout.NORTH);    	
    	panel.add(text2, BorderLayout.SOUTH);    	
    	
    	panel_down.add(panel, BorderLayout.SOUTH);    	
    	
    	//tranimg_middle_imgin.add(panel_down);
    	goodscontents.panel_center.add(panel_down);
    	
    	validate();
    }
    
    //¹è°æ ÀÌ¹ÌÁö ³Ö±â
    private void setListImg_Background(){
    	
    	int row = table_middle_imglist.getSelectedRow();
    	String gubun = (String)table_middle_imglist.getValueAt(row, 1);
    	String img_path = (String)table_middle_imglist.getValueAt(row, 2);
    	
    	if("FTP".equals(gubun)){    		
    		System.out.println(img_path);
    		String str = "<html><img src='"+img_path+"' width=500 height=700 alt='' border=0 ></html>";
    		tranimg_editorPane_img.setText(str);
    	}else{
    		String str = "<html><img src='file:"+img_path+"' width=500 height=700 alt='' border=0 ></html>";    		
    		tranimg_editorPane_img.setText(str);
    	}
    }
    
    
    
    //ÀÌ¹ÌÁö »õ·Î »ý¼ºÇÏ±â
    //private void resize(File src, File dest, int width, int height) throws IOException {
    private void resize(File dest, int width, int height) throws IOException {
    	
	    int RATIO = 0;
        int SAME = -1;
        
       /* //ÀÌµðÅÍ ÆÇ³Ú »ðÀÔ ÇÕ´Ï´Ù.
        JEditorPane temp_editorpane = new JEditorPane();
        temp_editorpane.setPreferredSize(new Dimension(500, 700));
        temp_editorpane.setContentType("text/html");		
		temp_editorpane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_top = (JPanel)tranimg_editorPane_img.getComponent(0);
		JPanel panel_middle = (JPanel)tranimg_editorPane_img.getComponent(1);
		JPanel panel_middle_re = new JPanel();
		panel_middle_re.setLayout(new GridLayout(0, 1));
		JPanel panel_bottom = (JPanel)tranimg_editorPane_img.getComponent(2);
		
		temp_editorpane.add(panel_top, BorderLayout.NORTH);		
		temp_editorpane.add(panel_bottom, BorderLayout.SOUTH);
		
		int row = panel_middle.getComponentCount();
		for(int i =0; i < row; i++){
			
			JEditorPane temp = (JEditorPane)panel_middle.getComponent(i);
			JPanel temp_panel = (JPanel)temp.getComponent(0);
			JTextField option = (JTextField)temp_panel.getComponent(0);
			
			String option_str = option.getText();
			System.out.println(option_str);
			String[] str = option_str.split(";");
			int img_width = Integer.parseInt(str[0]);
			img_width = img_width*2;
			int img_height = Integer.parseInt(str[1]);
			img_height = img_height*2;
			int limit_count = Integer.parseInt(str[2]);			
			int font_size = Integer.parseInt(str[3]);
			font_size = font_size*2;
			String img_path = str[4];
			
			JTextField gname = (JTextField)temp_panel.getComponent(1);			
			JTextField sellpri = (JTextField)temp_panel.getComponent(2);						
			
			if(i == 0){
				switch(limit_count){
				case 1:
					panel_middle_re.setLayout(new GridLayout(0, 1));
					break;
				case 4:
				case 6:
					panel_middle_re.setLayout(new GridLayout(0, 2));
					break;
				case 9:
				case 12:
					panel_middle_re.setLayout(new GridLayout(0, 3));
					break;					
				}				
			}
			
			img_path = "<html><img src=\""+img_path+"\" width='"+img_width+"' height='"+img_height+"' ></html>";
	    	//img_path = "<html><body background=\""+img_path+"\" width='"+img_width+"' height='"+img_height+"' ></html>";
	    	//Image bg = new ImageIcon(img_path).getImage();
	    	
	    	JEditorPane panel_down = new JEditorPane();
	    	panel_down.setLayout(new BorderLayout());
			panel_down.setContentType("text/html");
			panel_down.setEditable(false);
	    	panel_down.setText(img_path);
	    	
	    	gname.setOpaque(false);    	
	    	gname.setHorizontalAlignment(SwingConstants.CENTER);
	    	gname.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, font_size));
	    	gname.setBorder(null);
	    
	    	sellpri.setOpaque(false);
	    	sellpri.setForeground(Color.RED);
	    	sellpri.setHorizontalAlignment(SwingConstants.CENTER);
	    	sellpri.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, font_size));
	    	sellpri.setBorder(null);
	    	
	    	JPanel panel = new JPanel();
	    	panel.setLayout(new BorderLayout());
	    	panel.setOpaque(false);
	    	panel.add(gname, BorderLayout.NORTH);
	    	panel.add(sellpri, BorderLayout.SOUTH);
	    	
	    	panel_down.add(panel, BorderLayout.SOUTH);    	
	    	
	    	panel_middle_re.add(panel_down);
			
		}
		
		temp_editorpane.add(panel_middle_re, BorderLayout.CENTER);*/
		//JOptionPane.showConfirmDialog(this, temp_editorpane);
		
        BufferedImage srcImg = new BufferedImage(tranimg_editorPane_img.getWidth(), tranimg_editorPane_img.getHeight(), BufferedImage.TYPE_INT_RGB);  
	    //BufferedImage bi = new BufferedImage(900, 1247, BufferedImage.TYPE_INT_RGB);
	    Graphics2D g2 = srcImg.createGraphics();
	    //Graphics g = srcImg.getGraphics();
	    tranimg_editorPane_img.printAll(g2);  
	    g2.dispose();
	    
		/*BufferedImage srcImg = new BufferedImage(500, 700, BufferedImage.TYPE_INT_RGB);  
	    //BufferedImage bi = new BufferedImage(900, 1247, BufferedImage.TYPE_INT_RGB);
	    Graphics2D g2 = srcImg.createGraphics();
	    temp_editorpane.printAll(g2);
	    g2.dispose();*/
	    	    
       /* try {
            ImageIO.write(bi, "jpg", file);
            g2.dispose();
        } catch (Exception ex) {
            System.out.println("Error saving");
        }*/
                
        
        //BufferedImage srcImg = ImageIO.read(src);
        /*Image srcImg = null;
        String suffix = src.getName().substring(src.getName().lastIndexOf('.')+1).toLowerCase();
        if (suffix.equals("bmp") || suffix.equals("png") || suffix.equals("gif")) {
            srcImg = ImageIO.read(src);
        } else {
            // BMP°¡ ¾Æ´Ñ °æ¿ì ImageIconÀ» È°¿ëÇØ¼­ Image »ý¼º
            // ÀÌ·¸°Ô ÇÏ´Â ÀÌÀ¯´Â getScaledInstance¸¦ ÅëÇØ ±¸ÇÑ ÀÌ¹ÌÁö¸¦
            // PixelGrabber.grabPixels·Î ¸®»çÀÌÁî ÇÒ¶§
            // ºü¸£°Ô Ã³¸®ÇÏ±â À§ÇÔÀÌ´Ù.
            srcImg = new ImageIcon(src.toURL()).getImage();
        }*/
        
        int srcWidth = srcImg.getWidth(null);
        int srcHeight = srcImg.getHeight(null);
        
        int destWidth = -1, destHeight = -1;
        
        if (width == SAME) {
            destWidth = srcWidth;
        } else if (width > 0) {
            destWidth = width;
        }
        
        if (height == SAME) {
            destHeight = srcHeight;
        } else if (height > 0) {
            destHeight = height;
        }
        
        if (width == RATIO && height == RATIO) {
            destWidth = srcWidth;
            destHeight = srcHeight;
        } else if (width == RATIO) {
            double ratio = ((double)destHeight) / ((double)srcHeight);
            destWidth = (int)((double)srcWidth * ratio);
        } else if (height == RATIO) {
            double ratio = ((double)destWidth) / ((double)srcWidth);
            destHeight = (int)((double)srcHeight * ratio);
        }
        
        Image imgTarget = srcImg.getScaledInstance(destWidth, destHeight, Image.SCALE_SMOOTH); 
        int pixels[] = new int[destWidth * destHeight]; 
        PixelGrabber pg = new PixelGrabber(imgTarget, 0, 0, destWidth, destHeight, pixels, 0, destWidth); 
        try {
            pg.grabPixels();
        } catch (InterruptedException e) {
            throw new IOException(e.getMessage());
        }
        BufferedImage destImg = new BufferedImage(destWidth, destHeight, BufferedImage.TYPE_INT_RGB); 
        destImg.setRGB(0, 0, destWidth, destHeight, pixels, 0, destWidth); 
        
        ImageIO.write(destImg, "jpg", dest);
    }
    
    
    //ÄíÆù ÀÌ¹ÌÁö ¸®½ºÆ®¸¦ ºÒ·¯ ¿É´Ï´Ù.
    private void getCouponImageList(String gubun){
    	
    	this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
    	
    	String coupon_gubun="";
    	String coupon_listgubun=" and e_gubun='7' ";
    	switch(gubun){
    	case "Æ÷ÀÎÆ®Àû¸³ÄíÆù":
    		coupon_gubun = "Æ÷ÀÎÆ®";
    		coupon_listgubun=" and e_gubun='0' ";
    		tranimg_editorPane_img.removeAll();
    		tranimg_editorPane_img.add(pointcoupon);
    		break;
    	case "»çÀºÇ°Áö±ÞÄíÆù":
    		coupon_gubun = "»çÀºÇ°";
    		coupon_listgubun=" and ( e_gubun='1' or e_gubun ='2' )";
    		tranimg_editorPane_img.removeAll();
    		tranimg_editorPane_img.add(goodsprizescoupon);
    		break;
    	case "ÀüÃ¼ÇÒÀÎÄíÆù":
    		coupon_gubun = "ÀüÃ¼ÇÒÀÎ";
    		coupon_listgubun=" and ( e_gubun='3' or e_gubun='4' )";
    		tranimg_editorPane_img.removeAll();
    		tranimg_editorPane_img.add(pointcoupon);
    		break;
    	case "´ÜÇ°ÇÒÀÎÄíÆù":
    		coupon_gubun = "´ÜÇ°ÇÒÀÎ";
    		coupon_listgubun=" and ( e_gubun='5' or e_gubun='6' )";
    		tranimg_editorPane_img.removeAll();
    		tranimg_editorPane_img.add(goodsprizescoupon);
    		break;
    	}    	
    	
    	//Å×½ºÆ®
    	String ftp_ip = "14.38.161.45";
    	int ftp_port = 8000;
    	//String ftp_ip = "14.38.161.45";
    	//int ftp_port = 7000;
    	
    	String ftp_id = Server_Config.getFTPID();
    	String ftp_pw = Server_Config.getFTPPW();
    	String ftp_path = "ÄíÆù»ùÇÃ";
    	String[] file_list;
    	FTPClient ftpConn = new FTPClient();
    	    	
    	try {
			ftpConn.connect(ftp_ip, ftp_port);			
			ftpConn.login(ftp_id, ftp_pw); 
	    	//Å×½ºÆ® Æú´õ
			ftpConn.changeDirectory("POP/"+ftp_path);
	    	//ftpConn.changeDirectory(ftp_path);
			System.out.println(ftpConn.currentDirectory());
			file_list = ftpConn.listNames();
	    	
		} catch (IllegalStateException | IOException | FTPIllegalReplyException | FTPException | FTPDataTransferException | FTPAbortedException | FTPListParseException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(this, e.getMessage());
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));    	
			return;
		}
    	
    	//ÆÇ³Ú »èÁ¦ÇÏ±â
    	dtm_middle_imglist.setRowCount(0);    	
    	
		if(file_list.length <= 0){			
			JOptionPane.showMessageDialog(this, "°Ë»öµÈ °á°ú°¡ ¾ø½À´Ï´Ù.");
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			return;
		}
		
		//for(int i=0; i < file_list.length; i++){
		int i =1;
		for(String str:file_list){
			
			Vector<Object> v = new Vector<Object>();
			String file_name = str;
			
			file_name = file_name.substring(file_name.indexOf('_')+1, file_name.indexOf('.'));
			
			System.out.println(coupon_gubun+" "+file_name);
			if(coupon_gubun.equals(file_name)){
				v.add(i);
				v.add("ÄíÆù");
				v.add(str);
				str = "<html><img src=\"http://14.38.161.45:8080/POP/ÄíÆù»ùÇÃ/"+str+"\" width=100 height=100 > </html>";
				v.add(str);
				v.add(coupon_gubun);
				dtm_middle_imglist.addRow(v);
				i++;
			}
			
		}
		
		//°Ë»öµÈ ÀÌ¹ÌÁö¸¦ »Ñ·ÁÁÝ´Ï´Ù.
		String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());		
		String query = "Select * From e_Coupon_List Where e_Edate >= '"+today+"' "+coupon_listgubun;
		
		ms_connect.setMainSetting();
		ArrayList<HashMap<String, String>> map = ms_connect.connection(query);
		
		dtm_coupon_couponlist.setRowCount(0);
		
		if(map.size() <= 0){
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			return;
		}
		
		for(int j =0; j < map.size(); j++){
			Vector<Object> v = new Vector<Object>();
			HashMap<String, String> temp = map.get(j);
			
			String gubun1 = "";
			switch(temp.get("e_gubun")){
			case "0":
				gubun1 = "Æ÷ÀÎÆ®ÄíÆù";
				break;
			case "1":
				gubun1 = "»çÀºÇ°ÄíÆù(º°µµ)";
				break;
			case "2":
				gubun1 = "»çÀºÇ°ÄíÆù(Àç°í)";
				break;
			case "3":
				gubun1 = "ÀüÃ¼ÇÒÀÎÄíÆù(¿ø)";
				break;
			case "4":
				gubun1 = "ÀüÃ¼ÇÒÀÎÄíÆù(%)";
				break;
			case "5":
				gubun1 = "´ÜÇ°ÇÒÀÎÄíÆù(¿ø)";
				break;
			case "6":
				gubun1 = "´ÜÇ°ÇÒÀÎÄíÆù(%)";
				break;
			}
			
			String coupon_name = "<html>"+temp.get("e_CouponName")+"<br>"+gubun1+"<br>"+temp.get("e_Sdate")+"<br>"+temp.get("e_EDate")+" </html>";
			
			v.add(j+1);
			v.add(temp.get("e_Seq"));
			v.add(coupon_name);
			
			dtm_coupon_couponlist.addRow(v);
		}
		
		
		this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));    	
    }
    
    //ÄíÆùÀÌ¹ÌÁö ¹Ì¸®º¸±â·Î º¸³»±â
    private void setSelectedCouponImage(){
    	int row = table_middle_imglist.getSelectedRow();
    	String img_path = (String)table_middle_imglist.getValueAt(row, 2);    	
    	
    	img_path = "<html><img src=\"http://14.38.161.45:8080/POP/ÄíÆù»ùÇÃ/"+img_path+"\" width=490 height=690 > </html>";    	
    	tranimg_editorPane_img.setText(img_path);
    	
    }    
    
    
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String command = e.getActionCommand().trim();
		
		switch(command){
		case "Àü¼Û":
			pushTranStart();
			break;
		case "¸Þ¼¼Áö°Ë»ö":
			getMessageList();			
			break;
		case "¸Þ¼¼Áö ÀúÀå":
			setMessageSave();			
			break;
		case "FTP°Ë»ö":
			getFTPImage();
			break;			
		case "Æú´õ°Ë»ö":	
			getPCImage();
			break;
		}
	}
}

	class OutlineLabel extends JTextField {

		private Color outlineColor = Color.WHITE;
	    private boolean isPaintingOutline = false;
	    private boolean forceTransparent = false;
	    private int size =1;

	    public OutlineLabel() {
	        super();
	    }

	    public OutlineLabel(String text) {
	        super(text);
	    }

	    public OutlineLabel(String text, int horizontalAlignment) {
	        super(text, horizontalAlignment);
	    }

	    public Color getOutlineColor() {
	        return outlineColor;
	    }

	    public void setOutlineColor(Color outlineColor) {
	        this.outlineColor = outlineColor;
	        this.invalidate();
	    }
	    
	    public void setOutlineSize(int size){
	    	this.size = size;
	    }
	    
	    @Override
	    public Color getForeground() {
	        if ( isPaintingOutline ) {
	            return outlineColor;
	        } else {
	            return super.getForeground();
	        }
	    }

	    @Override
	    public boolean isOpaque() {
	        if ( forceTransparent ) {
	            return false;
	        } else {
	            return super.isOpaque();
	        }
	    }

	    @Override
	    public void paint(Graphics g) {

	        String text = getText();
	        if ( text == null || text.length() == 0 ) {
	            super.paint(g);
	            return;
	        }

	        // 1 2 3
	        // 8 9 4
	        // 7 6 5

	        if ( isOpaque() )
	            super.paint(g);
	        //¾Æ¿ô¶óÀÎ Å©±âÁ¶Àý ¼ýÀÚ	        
	        forceTransparent = true;
	        isPaintingOutline = true;
	        g.translate(-size, -size); super.paint(g); // 1 
	        g.translate( size,  0); super.paint(g); // 2 
	        g.translate( size,  0); super.paint(g); // 3 
	        g.translate( 0,  size); super.paint(g); // 4
	        g.translate( 0,  size); super.paint(g); // 5
	        g.translate(-size,  0); super.paint(g); // 6
	        g.translate(-size,  0); super.paint(g); // 7
	        g.translate( 0, -size); super.paint(g); // 8
	        g.translate( size,  0); // 9
	        isPaintingOutline = false;

	        super.paint(g);
	        forceTransparent = false;
	    }
	}

