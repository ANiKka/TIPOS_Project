import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
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
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
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
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.toedter.calendar.JDateChooser;

import it.sauronsoftware.ftp4j.FTPAbortedException;
import it.sauronsoftware.ftp4j.FTPClient;
import it.sauronsoftware.ftp4j.FTPDataTransferException;
import it.sauronsoftware.ftp4j.FTPException;
import it.sauronsoftware.ftp4j.FTPIllegalReplyException;
import it.sauronsoftware.ftp4j.FTPListParseException;
import net.miginfocom.swing.MigLayout;

public class Message_Manage extends JPanel implements ActionListener{
	
	private static final long serialVersionUID = 159748543965L;
	
	private Ms_Connect ms_connect;
	private Trans_ShopAPI trans_shopapi;	
	private CardLayout message_cardlayout;	
	
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
	private JComboBox<String> tran_combo_trandata;
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
	
	private String state_y = "<html>�˸��� ùȭ���� ����-�ݱ� ��ư��<br> �߸��� ������ ��ü ���� ������ ���� <br>�˸��޼��� �޴����� ��ü ������<br> Ȯ�� �Ҽ� �ֽ��ϴ�.<html>";
	private String state_n = "�������";
	private int cut_cnt = 450;
	private JLabel tranimg_label_msgtitle;
	private JLabel tranimg_label_imgpath;
	private JTextField tranimg_text_imgpath;
	private JButton tran_btn_datadown;
	private JButton tran_btn_dataup;
		
	private String[] tran_strarr_filelist;
	
	private String GUBUN = "";
	//�̹��� �� ����
	private int image_total_count=0;
	//���� ������ ��ȣ
	private int image_page_num=0;
	//�������� ���� 
	private int image_page_count=0;
	//���������� ��� ����
	private int image_page_listcount = 10;
		
	private JLabel tranimg_label_nowpage;
	private JLabel tranimg_label_sp;
	private JLabel tranimg_label_totalpage;
	private JButton tranimg_btn_pageup;
	private JButton tranimg_btn_pagedown;
	private JButton tranimg_btn_msgreset;
	private JButton tranimg_btn_imgreset;
	private JButton tranevt_btn_search;
	
	private DefaultTableModel dtm_tranevt;

	public Message_Manage(){
		
		setLayout(new BorderLayout(0, 0));
		setBorder(new EmptyBorder(5, 5, 5, 5));
		
		//��� ���� ����
		ms_connect = new Ms_Connect();
		trans_shopapi = new Trans_ShopAPI();
		
		//��������
		center_tabbed_coupontran = new JPanel();
		//center_tabbed.addTab("\uCFE0\uD3F0 \uBC0F \uC774\uBCA4\uD2B8 \uC804\uC1A1", null, center_tabbed_coupontran, null);
		center_tabbed_coupontran.setLayout(new BorderLayout(5, 0));
		
		add(center_tabbed_coupontran);
		
		panel_coupontran_1 = new JPanel();
		panel_coupontran_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		center_tabbed_coupontran.add(panel_coupontran_1, BorderLayout.WEST);
		panel_coupontran_1.setLayout(new MigLayout("", "[][][grow]", "[30px][][][][][80px][30px][][][80px][30px][40px]"));		
		event_CouponTran();
			
		//�⺻ �����մϴ�.
		tranmsg_text_title.setText(Server_Config.getOFFICENAME());
	}
	
	
	//���� ���� ȭ��
	private void event_CouponTran(){		

		tran_label_title1 = new JLabel("1. \uC804\uC1A1 \uB300\uC0C1 \uC120\uD0DD");
		tran_label_title1.setHorizontalAlignment(SwingConstants.CENTER);
		tran_label_title1.setFont(new Font("���� ���", Font.BOLD, 13));
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
		
		tran_label_title2 = new JLabel("2. \uC804\uC1A1 \uBC29\uC2DD \uC120\uD0DD");
		tran_label_title2.setHorizontalAlignment(SwingConstants.CENTER);
		tran_label_title2.setFont(new Font("���� ���", Font.BOLD, 13));
		tran_label_title2.setOpaque(true);
		tran_label_title2.setBackground(SystemColor.inactiveCaption);
		panel_coupontran_1.add(tran_label_title2, "cell 0 6 3 1,grow");
		
		tran_label_trandata = new JLabel("\uC804\uC1A1\uBC29\uC2DD");		
		panel_coupontran_1.add(tran_label_trandata, "cell 0 7,alignx trailing");
		
		tran_combo_trandata = new JComboBox<String>();
		tran_combo_trandata.setModel(new DefaultComboBoxModel<String>(new String[] {"\uBA54\uC138\uC9C0", "\uC774\uBBF8\uC9C0", "\uC774\uBCA4\uD2B8\uCFE0\uD3F0"}));
		panel_coupontran_1.add(tran_combo_trandata, "cell 1 7 2 1,growx");
		
		
		tran_combo_trandata.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(e.getStateChange() == 1){
					
					String item = (String)e.getItem();
					
					switch(item){
					case "�޼���":
						message_cardlayout.show(panel_coupontran_2, "message");
						break;
					case "�̹���":
						message_cardlayout.show(panel_coupontran_2, "image");
						break;
					case "�̺�Ʈ����":
						message_cardlayout.show(panel_coupontran_2, "event");
						break;
					}					
				}
			}
		});
		
		tran_btn_datadown = new JButton("\u25C0");
		tran_btn_datadown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int cnt = tran_combo_trandata.getSelectedIndex();
				if(cnt > 0){
					tran_combo_trandata.setSelectedIndex(cnt-1);
				}
			}
		});
		tran_btn_datadown.setHorizontalAlignment(SwingConstants.LEFT);
		panel_coupontran_1.add(tran_btn_datadown, "cell 1 8");
		
		tran_btn_dataup = new JButton("\u25B6");
		tran_btn_dataup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int cnt = tran_combo_trandata.getSelectedIndex();
				if(cnt < 2){
					tran_combo_trandata.setSelectedIndex(cnt+1);
				}
			}
		});
		tran_btn_dataup.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_coupontran_1.add(tran_btn_dataup, "cell 2 8,alignx right");
		
		
		tran_label_title3 = new JLabel("3. \uC804\uC1A1\uD558\uAE30");
		tran_label_title3.setHorizontalAlignment(SwingConstants.CENTER);
		tran_label_title3.setFont(new Font("���� ���", Font.BOLD, 13));
		tran_label_title3.setBackground(SystemColor.inactiveCaption);
		tran_label_title3.setOpaque(true);
		panel_coupontran_1.add(tran_label_title3, "cell 0 10 3 1,grow");
		
		tran_btn_submit = new JButton("\uC804\uC1A1");
		tran_btn_submit.addActionListener(this);
		panel_coupontran_1.add(tran_btn_submit, "cell 0 11 3 1,grow");
		
		panel_coupontran_2 = new JPanel();
		center_tabbed_coupontran.add(panel_coupontran_2, BorderLayout.CENTER);
		message_cardlayout = new CardLayout();
		panel_coupontran_2.setLayout(message_cardlayout);
		
		tran_panel_msg = new JPanel();
		tran_panel_msg.setName("");
		panel_coupontran_2.add(tran_panel_msg, "message");
		tran_panel_msg.setLayout(new BorderLayout(5, 0));
		
		tranmsg_panel_msg = new JPanel();
		tranmsg_panel_msg.setBorder(new LineBorder(new Color(0, 0, 0)));
		tran_panel_msg.add(tranmsg_panel_msg, BorderLayout.WEST);
		tranmsg_panel_msg.setLayout(new MigLayout("", "[][grow][]", "[][12px][25px][10px][][][10px][][][]"));
		
		lblNewLabel = new JLabel("\uD478\uC2DC \uBA54\uC138\uC9C0");
		lblNewLabel.setFont(new Font("���� ���", Font.BOLD, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		tranmsg_panel_msg.add(lblNewLabel, "cell 0 0 3 1,grow");
		
		tranmsg_label_title = new JLabel("\uC81C\uBAA9");
		tranmsg_panel_msg.add(tranmsg_label_title, "cell 0 2,alignx trailing");
		
		tranmsg_text_title = new JTextField();
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
		tranmsg_textArea_msg.setRows(16);
		tranmsg_textArea_msg.setColumns(22);
		tranmsg_textArea_msg.setLineWrap(true);
		tranmsg_textArea_msg.setFont(new Font("���� ���", Font.PLAIN, 12));
		
		tranmsg_textArea_msg.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
								
				byte[] str =  tranmsg_textArea_msg.getText().getBytes();
				if( str.length > cut_cnt ){
					tranmsg_label_msginfo.setText(state_y+" : "+str.length);					
				}else{
					tranmsg_label_msginfo.setText(state_n+" : "+str.length);				
				}
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		tranmsg_label_linkurl = new JLabel("\uB9C1\uD06C URL");
		tranmsg_panel_msg.add(tranmsg_label_linkurl, "cell 0 7,alignx trailing");
		
		tranmsg_text_linkurl = new JTextField();
		tranmsg_panel_msg.add(tranmsg_text_linkurl, "cell 1 7 2 1,growx");
		tranmsg_text_linkurl.setColumns(20);
		
		tranmsg_btn_msgsave = new JButton("\uBA54\uC138\uC9C0 \uC800\uC7A5");
		tranmsg_btn_msgsave.addActionListener(this);		
		
		tranimg_btn_msgreset = new JButton("\uC0C8\uB85C\uC785\uB825");
		tranimg_btn_msgreset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				msgmsg_ReSet();
			}
		});
		tranmsg_panel_msg.add(tranimg_btn_msgreset, "cell 1 8,aligny top");
		tranmsg_panel_msg.add(tranmsg_btn_msgsave, "cell 2 8");
		
		tranmsg_label_msginfo = new JLabel("\uC815\uC0C1\uCD9C\uB825");
		tranmsg_label_msginfo.setBackground(SystemColor.info);
		tranmsg_label_msginfo.setOpaque(true);
		tranmsg_label_msginfo.setFont(new Font("���� ���", Font.BOLD, 15));
		tranmsg_label_msginfo.setHorizontalAlignment(SwingConstants.CENTER);
		tranmsg_panel_msg.add(tranmsg_label_msginfo, "cell 0 9 3 1,grow");
		
		tranmsg_panel_list = new JPanel();
		tranmsg_panel_list.setBorder(new LineBorder(new Color(0, 0, 0)));
		tran_panel_msg.add(tranmsg_panel_list, BorderLayout.CENTER);
		tranmsg_panel_list.setLayout(new MigLayout("", "[][][][grow][100px]", "[][10px][][][grow]"));
		
		tranmsg_label_listtitle = new JLabel("\uBA54\uC138\uC9C0 \uC800\uC7A5\uBAA9\uB85D");
		tranmsg_label_listtitle.setHorizontalAlignment(SwingConstants.CENTER);
		tranmsg_label_listtitle.setFont(new Font("���� ���", Font.BOLD, 15));
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
		tranmsg_btn_listsearch.setActionCommand("�޼����˻�");
		tranmsg_btn_listsearch.addActionListener(this);
		tranmsg_panel_list.add(tranmsg_btn_listsearch, "cell 4 3,growx");
		
		tranmsg_scroll_list = new JScrollPane();
		tranmsg_panel_list.add(tranmsg_scroll_list, "cell 0 4 5 1,grow");
		
		
		
		//������ ����
		messageSaveList();		
		
		tran_panel_img = new JPanel();
		tran_panel_img.setName("");
		panel_coupontran_2.add(tran_panel_img, "image");
		tran_panel_img.setLayout(new BorderLayout(5, 0));
		
		tranimg_panel_preview = new JPanel();
		tranimg_panel_preview.setBorder(new LineBorder(new Color(0, 0, 0)));
		tran_panel_img.add(tranimg_panel_preview, BorderLayout.WEST);
		tranimg_panel_preview.setLayout(new MigLayout("", "[][200px][grow]", "[][10px][25px][10px][][][][grow]"));
		
		tranimg_label_msgtitle = new JLabel("\uBA54\uC138\uC9C0 \uB0B4\uC6A9");
		tranimg_label_msgtitle.setFont(new Font("���� ���", Font.BOLD, 15));
		tranimg_label_msgtitle.setHorizontalAlignment(SwingConstants.CENTER);
		tranimg_panel_preview.add(tranimg_label_msgtitle, "cell 0 0 3 1,alignx center,growy");
		
		tranimg_label_title = new JLabel("\uC81C\uBAA9");
		tranimg_panel_preview.add(tranimg_label_title, "cell 0 2,alignx trailing");
		
		tranimg_text_title = new JTextField();
		tranimg_text_title.setAutoscrolls(false);
		tranimg_panel_preview.add(tranimg_text_title, "cell 1 2 2 1,grow");
		tranimg_text_title.setColumns(20);
		
		tranimg_editorPane_img = new JEditorPane();
		tranimg_editorPane_img.setPreferredSize(new Dimension(300, 400));
		tranimg_editorPane_img.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		tranimg_editorPane_img.setContentType("text/html");
		tranimg_editorPane_img.setEditable(false);
		tranimg_panel_preview.add(tranimg_editorPane_img, "cell 0 4 3 1,alignx center,growy");
				
		tranimg_label_linkurl = new JLabel("\uB9C1\uD06C URL");
		tranimg_panel_preview.add(tranimg_label_linkurl, "cell 0 5,alignx trailing");
		
		tranimg_text_linkurl = new JTextField();
		tranimg_panel_preview.add(tranimg_text_linkurl, "cell 1 5 2 1,growx");
		tranimg_text_linkurl.setColumns(20);
		
		tranimg_label_imgpath = new JLabel("Path");
		tranimg_panel_preview.add(tranimg_label_imgpath, "cell 0 6,alignx trailing");
		
		tranimg_text_imgpath = new JTextField();
		tranimg_text_imgpath.setEnabled(false);
		tranimg_text_imgpath.setEditable(false);
		tranimg_panel_preview.add(tranimg_text_imgpath, "cell 1 6,growx");
		tranimg_text_imgpath.setColumns(10);
						
		tranimg_panel_imglist = new JPanel();
		tranimg_panel_imglist.setBorder(new LineBorder(new Color(0, 0, 0)));
		tran_panel_img.add(tranimg_panel_imglist, BorderLayout.CENTER);
		tranimg_panel_imglist.setLayout(new MigLayout("", "[][][grow][][][][][][][][grow][]", "[][][][grow]"));
		
		tranimg_label_listtitle = new JLabel("\uC774\uBBF8\uC9C0 \uBAA9\uB85D( \uD30C\uC77C\uBA85\uC5D0 \uD55C\uAE00\uD3EC\uD568\uC2DC \uC804\uC1A1\uC548\uB428 )");
		tranimg_label_listtitle.setHorizontalAlignment(SwingConstants.CENTER);
		tranimg_label_listtitle.setFont(new Font("���� ���", Font.BOLD, 15));
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
		
		tranimg_label_ftptitle = new JLabel("FTP \uC9C0\uC815\uD3F4\uB354 \uC774\uBBF8\uC9C0 \uAC00\uC838\uC624\uAE30");
		tranimg_label_ftptitle.setHorizontalAlignment(SwingConstants.CENTER);
		tranimg_panel_imglist.add(tranimg_label_ftptitle, "cell 0 2 2 1,grow");
		
		tranimg_btn_pagedown = new JButton("\u25C0");
		tranimg_btn_pagedown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
		    	System.out.println("�������ٿ�");
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
				System.out.println("��������" +image_page_num+" "+image_page_count);
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
		tranimg_scroll_imglist.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		tranimg_panel_imglist.add(tranimg_scroll_imglist, "cell 0 3 12 1,grow");
		
		tranimg_scroll_imglist.getVerticalScrollBar().setUnitIncrement(30);
		
		tranimg_panel_imgview = new JPanel();
		tranimg_scroll_imglist.setViewportView(tranimg_panel_imgview);
		tranimg_panel_imgview.setLayout(new GridLayout(0, 3, 0, 0));
		
		tran_panel_evt = new JPanel();		
		panel_coupontran_2.add(tran_panel_evt, "event");
		tran_panel_evt.setLayout(new MigLayout("", "[][grow][]", "[][10px][][grow]"));
		
		tranevt_label_listtitle = new JLabel("\uD478\uC2DC \uC774\uBCA4\uD2B8 \uBAA9\uB85D");
		tranevt_label_listtitle.setFont(new Font("���� ���", Font.BOLD, 15));
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
		
		String[] title = {"������ȣ", "����", "�޼���", "����"}; 
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
	    
	    //���� ����Ʈ��� �κ� �߾�����
	    ((DefaultTableCellRenderer)tranevt_table_evtlist.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(0);
	    
	    tranevt_table_evtlist.setRowHeight(25);
	    tranevt_table_evtlist.getTableHeader().setReorderingAllowed(false);  //�̵��Ұ�	    
	    //center_table_couponlist.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);  //���� ��ũ��	    
	    
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

	
	   //�̺�Ʈ �ڵ� ���� �ҷ�����
    private void tranevt_EvtCall(){   	
    	    		
		this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
		//����� ȣ���մϴ�.
		JSONArray temp_event = trans_shopapi.getPushEventList();
				
		dtm_tranevt.setRowCount(0);
		
		if(temp_event.size() <= 0){			
			JOptionPane.showMessageDialog(this, "�̺�Ʈ ��� �˻��� ���� �߽��ϴ�.");
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
	
	
    //�޼��� ���� ����
    private void msgmsg_ReSet(){    	
    	tranimg_text_title.setText(Server_Config.getOFFICENAME());
    	tranmsg_textArea_msg.setText("");
    	tranmsg_text_linkurl.setText("");
    	tranmsg_label_msginfo.setText(state_y);
    }
    
	//�޼��� ���� ����Ʈ �ҷ�����
	private void messageSaveList(){
		
		DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
		celAlignCenter.setHorizontalAlignment(JLabel.CENTER);	
		
		String[] colunm_msglist = {"��ȣ", "����", "�����"};
		
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
	    
	    //���� ����Ʈ��� �κ� �߾�����
	    ((DefaultTableCellRenderer)tranmsg_table_list.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(0);
	    
	    tranmsg_table_list.setRowHeight(25);
	    tranmsg_table_list.getTableHeader().setReorderingAllowed(false);  //�̵��Ұ�
	    
	    //center_table_couponlist.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);  //���� ��ũ��	    
	    
	    TableColumnModel tcm_msglist = tranmsg_table_list.getColumnModel();
	
	      //tcm_uselist.getColumn(0).setMaxWidth(Integer.MAX_VALUE);
	    tcm_msglist.getColumn(0).setWidth(40);
	    //tcm_uselist.getColumn(0).setCellRenderer(celAlignCenter);
	    tcm_msglist.getColumn(0).setPreferredWidth(40);
	    tcm_msglist.getColumn(0).setCellRenderer(celAlignCenter);
	
	    tcm_msglist.getColumn(1).setWidth(800);	    
	    tcm_msglist.getColumn(1).setPreferredWidth(800);
	    
	    tcm_msglist.getColumn(2).setWidth(80);	    
	    tcm_msglist.getColumn(2).setPreferredWidth(80);
	    tcm_msglist.getColumn(2).setCellRenderer(celAlignCenter);
	    
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
					System.out.println("���콺 �ι� Ŭ�� �ƽ��ϴ�.");
					//�������� �ڷḦ ���� �մϴ�.
					getMessageToEdit();
					
				} // ����Ŭ��			
			}
		});
		
	}   
    
	//�޼��� ����â���� �����ϴ�.
		private void getMessageToEdit(){
			
			int row = tranmsg_table_list.getSelectedRow();		
			String temp = dtm_msglist.getValueAt(row, 1).toString();
			
			byte[] cnt = temp.getBytes();
			
			if(cnt.length > cut_cnt){			
				tranmsg_label_msginfo.setText(state_y+" : "+cnt.length);
			}else{		
				tranmsg_label_msginfo.setText(state_n+" : "+cnt.length);
			}		
			tranmsg_textArea_msg.setText(temp);
			
		}
		
		
		private void setImageList(){
	    	
	    	int last_number = 0;
	    	
	    	//�ǳ� �ʱ�ȭ
	    	tranimg_panel_imgview.removeAll();
	    	
	    	//���� �������� 1�϶�
	    	//���� �������� ������ ������ �϶� �������� ������ ������������
	    	if(image_page_num == Integer.parseInt(tranimg_label_totalpage.getText())){
	    		//���� ������ ��� �մϴ�.
	    		last_number = image_total_count;
	    	}else{
	    		//���� ���� �Դϴ�.
	    		last_number = image_page_num * image_page_listcount;
	    	}
	    	
	    	//System.out.println(last_number);
	    	for(int count = (image_page_num * image_page_listcount)-image_page_listcount; count < last_number; count++){
	    		
	    		String file_name = tran_strarr_filelist[count].toString();
	    		
	    		String file_path = "";
	    		//�����ϱ� ���Դϴ�. �׽�Ʈ
	    		//file_path = "http://14.38.161.45:8080/image/"+Server_Config.getFTPMARTPATH()+"/"+file_name; //�̹����� ���
	    		
	    		if(GUBUN.equals("FTP")){
	    			file_path = "http://14.38.161.45:8080/image/"+Server_Config.getFTPMARTPATH()+"/"+file_name;
	    		}else{
	    			file_path = tranimg_text_pcpath.getText()+"/"+file_name;
	    			//System.out.println("�����̸� : "+file_path);
	    		}
	    		
	    		//System.out.println(file_path);
	    		
	    		//��ϸ���Ʈ�� �����մϴ�.
	    		tranimg_panel_imgview.add(setImageView(file_name, file_path));    		
	    		this.repaint();
	    	}
	    }
		
	    public JPanel setImageView(String file_name, String file_path){
	    	
	    	//�̹��� ������ �����մϴ�.
	    	//�̹����� ���� ������ ����ϴ�.
	    	JPanel panel = new JPanel();
	    	
	    	Box box = Box.createVerticalBox();    	
	    	
	    	JLabel image_show = new JLabel();
	    	box.add(image_show);
	    	
	    	JLabel image_path = new JLabel(GUBUN);
	    	image_path.setFont(new Font("���� ���", Font.BOLD, 12));
	    	box.add(image_path);
	    	
	    	JLabel g_name = new JLabel(setSubString(file_name, 22));
	    	g_name.setFont(new Font("���� ���", Font.BOLD, 11));
	    	box.add(g_name);
	    	
	    	JButton file_choose = new JButton("����");    	    	
	    	file_choose.addActionListener(new ActionListener() { 
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					//�̹��� ���� ��ư �ٷ� ������ �մϴ�.
					setImageChoose(file_name);
				}
			});
	    	
	    	JButton file_delete = new JButton("����");
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
	       	image_show.setIcon(new ImageIcon(image.getScaledInstance(150, 200, Image.SCALE_SMOOTH)));
	       	
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

	    //���õ� ���� FTP�������� ���� �ϱ�
	    private void deleteImageChooseFile(String file_name){
	    	
	    	this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
	    	boolean result = false;
	    	//System.out.println("���õ� �� : "+GUBUN+" ���õ� ���ϸ� : "+file_name);
	    	if(GUBUN.equals("FTP")){
	        	
	        	//�׽�Ʈ
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
	    		
	    		//���� �̹��� �����ϱ�
	    		String file_path = tranimg_text_pcpath.getText();
	    		File file = new File(file_path, file_name);		
	    		if(file.exists()){
	    			file.delete();
	    			result = true;
	    		}    		
	    	}
	    	
	    	if(result){    		
	    		JOptionPane.showMessageDialog(this, "������ ���� �߽��ϴ�."); 
	    		final List<String> list = new ArrayList<String>();
	    		Collections.addAll(list, tran_strarr_filelist);
	    		list.remove(file_name);
	    		
	    		tran_strarr_filelist = list.toArray(new String[list.size()]);
	    		tranimg_SetImage();
	    		
	    	}
	    	
	    	this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	    }	    
		
	 	//�˻��� ����Ÿ�� ������� �����մϴ�.
	    private void tranimg_SetImage(){
	    	
	    	this.repaint();
	    	msgimg_ReSet();
	    	
	    	Dimension dm = tranimg_scroll_imglist.getSize();    	
	    	int width = (int)dm.getWidth();    	  	
	    	
	    	//System.out.println("W : "+width);
	    	//������ ���� �ɴϴ�.
	    	int p = width/150;
	    	int c = p*6;    	
	    	//System.out.println("W : "+p+" H :"+c);
	    	
	    	tranimg_panel_imgview.setLayout(new GridLayout(0, p));
	    	image_page_listcount = c;    	
	    	
	    	int totalCount = tran_strarr_filelist.length;
	    	//System.out.println("�� ���� : "+totalCount);
	    	
	    	//�� �˻� ��ǰ ����
	    	image_total_count = totalCount;
	    	    	
	    	/*if(totalCount <= image_page_listcount){
	    		tranimg_label_totalpage.setText(String.valueOf(1));
	    	}else{
		    	//�� ������ ���� = �������/�ѹ��� ���� �̹�������
		    	image_page_count = totalCount/image_page_listcount;
		    	tranimg_label_totalpage.setText(String.valueOf(image_page_count));    	    	
	    	}*/
	    	
	    	//�� ������ ����
	    	image_page_count = totalCount/image_page_listcount;
	    	    	
	    	//�� ������ 10���� ���� ������ �̹����� �ִٸ� �������� �� ���̱�
	    	if(totalCount%image_page_listcount > 0){
	    		image_page_count++;
	    	}
	    	
	    	//���������� ��ȣ
	    	image_page_num = 1;
	    	//tranimg_label_nowpage.setText(String.valueOf(image_page_num));   	
	    	
	    	//���� ������ �� �������� ǥ��
	    	tranimg_label_nowpage.setText(String.valueOf(1));
	    	tranimg_label_totalpage.setText(String.valueOf(image_page_count));
	    	//System.out.println("�� ������ ���� : "+image_page_count);
	    	
	    	setImageList();
	    }
	    	
	    //�̹��� ������ ������ �����մϴ�.
	    private void msgimg_ReSet(){
	    	    	
	    	tranimg_text_title.setText(Server_Config.getOFFICENAME());
	    	tranimg_editorPane_img.setText("");
	    	tranimg_text_linkurl.setText("");
	    	tranimg_label_imgpath.setText("Path");
	    	tranimg_text_imgpath.setText("");
	    	
	    }    
	    	    
	    //���� ���� �� �̸����� ȭ������ �ű��
	    private void setImageChoose(String file_name){    
	    	System.out.println("���õ� �� : "+GUBUN+" ���õ� ���ϸ� : "+file_name);
	    	String path = "";
	    	String img_path = "";
	    	
	    	if(GUBUN.equals("FTP")){
	    		path = "<Html><img src='http://14.38.161.45:8080/image/"+Server_Config.getFTPMARTPATH()+"/"+file_name+"' border=0 width='300' height='400' alt=''></html>";
	    		img_path = Server_Config.getFTPMARTPATH()+"/"+file_name;
	    	}else{			
				path = "<html><img src='file:"+tranimg_text_pcpath.getText()+"/"+file_name+"' width=300 height=400 alt='' border=0 ></html>";
				img_path = file_name;			
	    	}
	    	
	    	tranimg_label_imgpath.setText(GUBUN);
	    	tranimg_editorPane_img.setText(path);
	    	tranimg_text_imgpath.setText(img_path);
	    }
		
	  //�޼��� �����ϱ�
		private void pushTranStart(){
			
			switch(tran_combo_trandata.getSelectedIndex()){			
			case 0:
				setPushMessage();
				break;			
			case 1:
				setPushImage();
				break;
			case 2:
				setPushEvent();
				break;
			}
			
		}
		
		//Ǫ�� �޽���
		private void setPushMessage(){
			
			this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
			HashMap<String, Object> push_list = new HashMap<String, Object>();
			
			//���۴�� �ҷ�����
			//���Կ��� ALL/Y/N 
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
			
			//������ ���� �ҷ�����
			String title = tranmsg_text_title.getText();
			String message = tranmsg_textArea_msg.getText();
			String img_url = "";
			String link  = tranmsg_text_linkurl.getText();
			String event_code = "";
			
			//��ȿ��üũ
			if(title.length() <= 0){
				JOptionPane.showMessageDialog(this, "�޼��� ������ �Է��� �ּ���!!");
				tranmsg_text_title.requestFocus();
				this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				return;
			}
			
			if(message.length() <= 0){
				JOptionPane.showMessageDialog(this, "�޼��� ������ �Է��� �ּ���!!");
				tranmsg_textArea_msg.requestFocus();
				this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				return;
			}
			
			push_list.put("Mem_Only", mem_only);
			push_list.put("Mem_Id", mem_id);
			push_list.put("Hp", mem_hp);
			
			push_list.put("Title", title);
			push_list.put("Message", message);		
			push_list.put("Img_Url", img_url);
			push_list.put("Link", link);
			push_list.put("Event", event_code);
			
			JSONObject result = trans_shopapi.setPushSubimt(push_list);
			
			String res = "<Html>"+result.get("result_msg")+"<br>"+result.get("result_cnt")+" �� �߼۵�</Html>";
			JOptionPane.showMessageDialog(this, res);
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}
		
		//Ǫ�� �̹���
		private void setPushImage(){
			
			this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
			HashMap<String, Object> push_list = new HashMap<String, Object>();
			
			//���۴�� �ҷ�����
			//���Կ��� ALL/Y/N 
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
			
			//������ ���� �ҷ�����
			String title = tranimg_text_title.getText();
			String message = tranimg_text_title.getText();		
			String img_url = tranimg_text_imgpath.getText();
			String link  = tranmsg_text_linkurl.getText();
			String event_code = "";
			
			//��ȿ��üũ
			if(title.length() <= 0){
				JOptionPane.showMessageDialog(this, "�޼��� ������ �Է��� �ּ���!!");
				tranmsg_text_title.requestFocus();
				this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				return;
			}
				
			if(img_url.length() <= 0){
				JOptionPane.showMessageDialog(this, "�̹����� ������ �ּ���!!");			
				this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				return;
			}
			
			//�����̹��� ����
			if(tranimg_label_imgpath.getText().equals("FTP")){
				//�ּҸ� �ҷ��ٰ� ����մϴ�.
				img_url = "http://14.38.161.45:8080/image/"+img_url;
				//push_list.put("Img_Url", img_url);
				System.out.println(img_url);
			}else{
				
				//PC������ �ִٸ� FTP�� UPLoad�� �̹��� �ּҸ� �ҷ��� ����մϴ�.
				String img_path = tranimg_text_pcpath.getText();
				
				//�׽�Ʈ
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
			    	//�׽�Ʈ ����
					ftpConn.changeDirectory("image/"+ftp_path);		    	
					System.out.println(ftpConn.currentDirectory());
					
					String[] file_name = ftpConn.listNames();
					
					for(String name:file_name){
						if(name.equals(img_url)){
							ftpConn.deleteFile(name);
						}
					}
					
					File file = new File(img_path, img_url);
					ftpConn.upload(file);
			    	
				} catch (IllegalStateException | IOException | FTPIllegalReplyException | FTPException | FTPDataTransferException | FTPAbortedException | FTPListParseException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(this, e.getMessage());
					this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));    	
					return;
				}
		    	
				img_url = "http://14.38.161.45:8080/image/"+Server_Config.getFTPMARTPATH()+"/"+img_url;
				//push_list.put("Img_Url", "");			
			}		
			
			push_list.put("Mem_Only", mem_only);
			push_list.put("Mem_Id", mem_id);
			push_list.put("Hp", mem_hp);
			
			push_list.put("Title", title);
			push_list.put("Message", message);
			push_list.put("Img_Url", img_url);
			push_list.put("Link", link);
			push_list.put("Event", event_code);
			
			JSONObject result = trans_shopapi.setPushSubimt(push_list);		
			String res = "<Html>"+result.get("result_msg")+"<br>"+result.get("result_cnt")+" �� �߼۵�</Html>";
			JOptionPane.showMessageDialog(this, res);
			
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}
		
		//Ǫ�� �̺�Ʈ
		private void setPushEvent(){
			
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			
			int row = tranevt_table_evtlist.getSelectedRow();
			
			if(row < 0){
				JOptionPane.showMessageDialog(this, "������ �̺�Ʈ�� ������ �ּ���!!");
				return;
			}
			
			HashMap<String, Object> push_list = new HashMap<String, Object>();		
			
			//���۴�� �ҷ�����
			//���Կ��� ALL/Y/N 
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
			
			//��ȿ��üũ�� �մϴ�.	
			String title = (String)dtm_tranevt.getValueAt(row, 1);
			String message = (String)dtm_tranevt.getValueAt(row, 2);
			String img_url = "";
			String link = "";
			String event_code = (String)dtm_tranevt.getValueAt(row, 0);	
			
			push_list.put("Mem_Only", mem_only);
			push_list.put("Mem_Id", mem_id);
			push_list.put("Hp", mem_hp);
			
			push_list.put("Title", title);
			push_list.put("Message", message);
			push_list.put("Img_Url", img_url);
			push_list.put("Link", link);
			push_list.put("Event", event_code);
			
			JSONObject result = trans_shopapi.setPushSubimt(push_list);		
			String res = "<Html>"+result.get("result_msg")+"<br>"+result.get("result_cnt")+" �� �߼۵�</Html>";
			JOptionPane.showMessageDialog(this, res);
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));	
		}
		
		
	//�޼��� �ҷ�����
	private void getMessageList(){

		String query = "Select SMS_Num, SMS_Memo, Write_Date, Edit_Date, Writer, Editor From SMS_Msg Order By SMS_Num Desc";
		
		dtm_msglist.setRowCount(0);
		
		ms_connect.setMainSetting();
		ArrayList<HashMap<String, String>> temp_msg = ms_connect.connection(query);
		
		Iterator<HashMap<String, String>> itr_list = temp_msg.iterator();
				
		while(itr_list.hasNext()){			
			HashMap<String, String> map = itr_list.next();
			Vector<Object> v = new Vector<Object>();			
			v.addElement(map.get("SMS_Num"));
			v.addElement(map.get("SMS_Memo"));
			v.addElement(map.get("Write_Date"));
			
			dtm_msglist.addRow(v);		
		}		
	}
	
	//�����޼����� �����մϴ�.
	private void setMessageSave(){
		
		//������ ����Ǿ� �ִ��� Ȯ�� �մϴ�.
		String msg = tranmsg_textArea_msg.getText();
				
		if(msg.length() <= 0){			
			JOptionPane.showMessageDialog(this, "�޼��� ������ �Է��� �ּ���!");
			return;
		}
		
		//������ �ҷ��ɴϴ�.
		String query = "Select ISNULL(Max(Sms_Num), 0) Num From Sms_Msg";
		
		ms_connect.setMainSetting();
		HashMap<String, String> map = ms_connect.selectQueryOne(query);
		
		int max_num = Integer.parseInt(map.get("Num")) + 1;
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");		
		String today = sdf.format(new Date());
		
		query = "Insert Into SMS_Msg (SMS_Num,SMS_Memo, Write_Date,Edit_Date, Writer,Editor) Values("
				+max_num+", '"+msg+"', '"+today+"', '"+today+"', 'shop', 'shop')";
		
		int result = ms_connect.connect_update(query);
		
		switch(result){
		case 0:
			JOptionPane.showMessageDialog(this, "��� �Ϸ� �߽��ϴ�.");
			break;
		default:
			JOptionPane.showMessageDialog(this, "���� ����");
			break;						
		}				
	}	

	
    //PC�������� �̹����� �˻��մϴ�.
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
		
		//���ϼ��� â�� ���ϴ�.	
		ret = jfiledialog.showOpenDialog(this);
		System.out.println("��� ���� : "+ret);
		
		if(ret == JFileChooser.APPROVE_OPTION){
			
			File file = jfiledialog.getSelectedFile();
			if(file.isDirectory()){				
				System.out.println("���丮 �Դϴ�. : "+file.getAbsolutePath());
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
				
				//�ǳ� �����ϱ�
		    	tranimg_panel_imgview.removeAll();    	
		    	
				if(tran_strarr_filelist.length <= 0){
					
					tranimg_label_totalpage.setText("0");
					tranimg_label_nowpage.setText("0");
					
					image_total_count = 1;
					image_page_num = 1;
					image_page_count = 1;
					
					tranimg_panel_imgview.setLayout(new BorderLayout());
					JLabel label = new JLabel("�˻��� �̹����� �����ϴ�.", JLabel.CENTER);
					tranimg_panel_imgview.add(label, BorderLayout.CENTER);
					this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					return;
				}
				
				//�˻��� �̹����� �ѷ��ݴϴ�.
				tranimg_text_pcpath.setText(file.getPath());
				tranimg_SetImage();				
				
			}else{
				System.out.println("���� �Դϴ�. : "+file.getName());
				
				//������ ������ �ٷ� �����մϴ�.
				tranimg_label_imgpath.setText("PC");
				tranimg_text_imgpath.setText(file.getName());
				tranimg_text_pcpath.setText(file.getParent());
				String str = "<html><img src='file:"+file.getAbsolutePath()+"' width=300 height=400 alt='' border=0 ></html>";
				
				tranimg_editorPane_img.setText(str);
			}
			
		}else{
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			return;
		}
		
		this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));    	    	
    }
	
	//FTP�������� �̹����� �˻��մϴ�.
    private void getFTPImage(){
    	
    	this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
    	
    	GUBUN = "FTP";
    	//�׽�Ʈ
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
	    	//�׽�Ʈ ����
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
    	
    	//�ǳ� �����ϱ�
    	tranimg_panel_imgview.removeAll();    	
    	
		if(tran_strarr_filelist.length <= 0){
			
			tranimg_label_totalpage.setText("0");
			tranimg_label_nowpage.setText("0");
			
			image_total_count = 1;
			image_page_num = 1;
			image_page_count = 1;
			
			tranimg_panel_imgview.setLayout(new BorderLayout());
			JLabel label = new JLabel("�˻��� �̹����� �����ϴ�.", JLabel.CENTER);
			tranimg_panel_imgview.add(label, BorderLayout.CENTER);
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			return;
		}
		
		//�˻��� �̹����� �ѷ��ݴϴ�.
		tranimg_SetImage();
		
		this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));   	
		
    }	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String command = e.getActionCommand().trim();
		
		switch(command){
		case "����":
			pushTranStart();
			break;
		case "�޼����˻�":
			getMessageList();			
			break;
		case "�޼��� ����":
			setMessageSave();			
			break;
		case "FTP�˻�":
			getFTPImage();
			break;			
		case "�����˻�":	
			getPCImage();
			break;
		}
	}
}
