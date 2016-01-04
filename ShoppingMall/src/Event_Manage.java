import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

import org.json.simple.*;

import com.toedter.calendar.*;
import net.miginfocom.swing.*;

public class Event_Manage extends JPanel implements ActionListener {
		
	private static final long serialVersionUID = 154474854451L;
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
	
	public Event_Manage() {
		
		setLayout(new BorderLayout(10, 10));
		setBorder(new EmptyBorder(5, 5, 5, 5));
		
		//��� ���� ����
		ms_connect = new Ms_Connect();
		trans_shopapi = new Trans_ShopAPI();
		
		//��� �˻� â
		panel_top = new JPanel();
		panel_top.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		add(panel_top, BorderLayout.NORTH);		
		panel_top.setLayout(new MigLayout("", "[80px][grow][10px][80px][grow][10px][80px][grow][80px][grow][20px][][100px][100px,grow]", "[15px,grow][grow][15px,grow]"));		
		top_Search();
		
		
		//�̺�Ʈ ��� â
		panel_center = new JPanel();
		add(panel_center, BorderLayout.CENTER);
		panel_center.setLayout(new BorderLayout(0, 0));
		event_List();
		
	}
	
	/** e_gubun : 1-���±�����ǰ, 0-��������Ʈ���� */ 
	private void top_Search(){
		
		JLabel top_label_searchname = new JLabel("\uAC80\uC0C9\uC5B4");
		top_label_searchname.setHorizontalAlignment(SwingConstants.CENTER);
		panel_top.add(top_label_searchname, "cell 0 1,grow");
		
		top_text_searchname = new JTextField();
		panel_top.add(top_text_searchname, "flowx,cell 1 1,growx");
		top_text_searchname.setColumns(10);
		
		top_label_eventgubun = new JLabel("\uC774\uBCA4\uD2B8\uAD6C\uBD84");
		panel_top.add(top_label_eventgubun, "cell 3 1,alignx center");
		
		top_combo_eventgubun = new JComboBox<String>();
		top_combo_eventgubun.setModel(new DefaultComboBoxModel<String>(new String[] {"\uC804\uCCB4", "\uACE0\uAC1D\uD3EC\uC778\uD2B8", "\uC0C1\uD488\uC9C0\uAE09(\uC7AC\uACE0X) "}));
		panel_top.add(top_combo_eventgubun, "cell 4 1,growx");
		
		top_label_ingyn = new JLabel("\uC9C4\uD589\uC720\uBB34");
		panel_top.add(top_label_ingyn, "cell 6 1,alignx center");
		
		top_combo_ingyn = new JComboBox<String>();
		top_combo_ingyn.setModel(new DefaultComboBoxModel<String>(new String[] {"\uC804\uCCB4", "\uC9C4\uD589\uC911", "\uC9C4\uD589\uC885\uB8CC"}));
		top_combo_ingyn.setSelectedIndex(1);
		panel_top.add(top_combo_ingyn, "cell 7 1,growx");
		
		top_label_useyn = new JLabel("\uC0AC\uC6A9\uC720\uBB34");
		panel_top.add(top_label_useyn, "cell 8 1,alignx center");
		
		top_combo_useyn = new JComboBox<String>();
		top_combo_useyn.setModel(new DefaultComboBoxModel<String>(new String[] {"\uC804\uCCB4", "\uC0AC\uC6A9", "\uBBF8\uC0AC\uC6A9"}));
		panel_top.add(top_combo_useyn, "cell 9 1,growx");
		
		top_btn_searchdel = new JButton("\uC9C0\uC6B0\uAE30");
		top_btn_searchdel.setActionCommand("�����");
		top_btn_searchdel.addActionListener(this);
		panel_top.add(top_btn_searchdel, "cell 11 1");
		
		top_btn_search = new JButton("\uAC80\uC0C9");
		top_btn_search.addActionListener(this);
		top_btn_search.setActionCommand("�˻�");
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
		
		
		//��������
		center_tabbed_coupontran = new JPanel();
		center_tabbed.addTab("\uCFE0\uD3F0 \uC804\uC1A1", null, center_tabbed_coupontran, null);
		center_tabbed_coupontran.setLayout(new BorderLayout(5, 0));
		
		panel_coupontran_1 = new JPanel();
		panel_coupontran_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		center_tabbed_coupontran.add(panel_coupontran_1, BorderLayout.WEST);
		panel_coupontran_1.setLayout(new MigLayout("", "[][grow]", "[][][][][][100px][][][100px][][40px]"));
		
		event_CouponTran();
		
		
		
		//���� ��븮��Ʈ
		center_tabbed_couponuselist = new JPanel();
		center_tabbed.addTab("\uCFE0\uD3F0 \uC0AC\uC6A9 \uC774\uB825", null, center_tabbed_couponuselist, null);
		center_tabbed_couponuselist.setLayout(new BorderLayout(0, 0));
				
		cneter_scroll_uselist = new JScrollPane();
		center_tabbed_couponuselist.add(cneter_scroll_uselist, BorderLayout.CENTER);
		
		event_UseList();
		
	}
	
	//�̺�Ʈ ����Ʈ ��� UI
	private void event_ListView(){
		
		DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
		celAlignCenter.setHorizontalAlignment(JLabel.CENTER);	
		
		//��������Ʈ �κ�
		center_scroll_couponlist = new JScrollPane();
		center_tabbed_couponlist.add(center_scroll_couponlist, BorderLayout.CENTER);
					
		String[] colunm_couponlist = {"����", "<html>���� �ڵ�<br>���� ��</html>", "<html>������<br>������</html>", "<html>ȸ������<br>�ߺ�����</html>", "��������", "����Ʈ"	, "<html>��ǰ��<br>����</html>", "���Ƚ��", "<html>�����<br>������<br>������</html>", "���࿩��"};
				
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
		
		center_table_couponlist = new JTable(dtm_couponlist);
		
		JTableHeader header_couponlist = center_table_couponlist.getTableHeader();
	    Dimension d_couponlist = header_couponlist.getPreferredSize();
	    d_couponlist.height = 60;
	    header_couponlist.setPreferredSize(d_couponlist);
	    
	    //���� ����Ʈ��� �κ� �߾�����
	    ((DefaultTableCellRenderer)center_table_couponlist.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(0);
	    
	    center_table_couponlist.setRowHeight(60);
	    center_table_couponlist.getTableHeader().setReorderingAllowed(false);  //�̵��Ұ�	    
	    //center_table_couponlist.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);  //���� ��ũ��	    
	    
	    TableColumnModel tcm_couponlist = center_table_couponlist.getColumnModel();

        //tcm.getColumn(0).setMaxWidth(Integer.MAX_VALUE);
	    tcm_couponlist.getColumn(0).setWidth(40);
	    tcm_couponlist.getColumn(0).setCellRenderer(celAlignCenter);
	    tcm_couponlist.getColumn(0).setPreferredWidth(40);

	    //tcm_couponlist.getColumn(1).setMaxWidth(Integer.MAX_VALUE);
	    tcm_couponlist.getColumn(1).setWidth(200);	    
	    tcm_couponlist.getColumn(1).setPreferredWidth(200);
	    tcm_couponlist.getColumn(1).setCellRenderer(new CouponListTableCellRenderer());
	    
	    	    
	    tcm_couponlist.getColumn(2).setWidth(100);	    
	    tcm_couponlist.getColumn(2).setPreferredWidth(100);
	    tcm_couponlist.getColumn(2).setCellRenderer(celAlignCenter);
	    
	    tcm_couponlist.getColumn(3).setWidth(100);	    
	    tcm_couponlist.getColumn(3).setPreferredWidth(100);
	    tcm_couponlist.getColumn(3).setCellRenderer(celAlignCenter);
	    
	    tcm_couponlist.getColumn(4).setWidth(80);	    
	    tcm_couponlist.getColumn(4).setPreferredWidth(80);
	    tcm_couponlist.getColumn(4).setCellRenderer(celAlignCenter);
	    
	    tcm_couponlist.getColumn(5).setWidth(150);
	    tcm_couponlist.getColumn(5).setPreferredWidth(150);
	    tcm_couponlist.getColumn(5).setCellRenderer(celAlignCenter);
	    
	    tcm_couponlist.getColumn(6).setWidth(100);
	    tcm_couponlist.getColumn(6).setPreferredWidth(100);
	    tcm_couponlist.getColumn(6).setCellRenderer(celAlignCenter);
	    
	    tcm_couponlist.getColumn(7).setWidth(100);
	    tcm_couponlist.getColumn(7).setPreferredWidth(100);
	    tcm_couponlist.getColumn(7).setCellRenderer(celAlignCenter);
	    
	    tcm_couponlist.getColumn(8).setWidth(100);
	    tcm_couponlist.getColumn(8).setPreferredWidth(100);
	    tcm_couponlist.getColumn(8).setCellRenderer(celAlignCenter);
	    
	    tcm_couponlist.getColumn(9).setWidth(100);
	    tcm_couponlist.getColumn(9).setPreferredWidth(100);
	    tcm_couponlist.getColumn(9).setCellRenderer(celAlignCenter);
	    
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
					case 2: //�ϰ�����
						
						break;
					case 3: //�ǸŸ޼���
						if(btn_jtab_hotkey_listcall.isEnabled()){
							//����� ���ڵ带 ã�Ƽ� ������ ������ ��ǰ�� �����մϴ�.
							//��ǰ ���� �Լ�
							setHotKeyListSave();
						}
						break;
					case 4: //
						break;
					case 5:
						break;
					default:
						JOptionPane.showMessageDialog(Goods_Manage.this, "���� ������ �ּ���!!");
						break;
					}		
				}*/
				
					if (e.getClickCount() == 2) {
						System.out.println("���콺 �ι� Ŭ�� �ƽ��ϴ�.");
						//�������� �ڷḦ ���� �մϴ�.
						setEventDetail();
						
					} // ����Ŭ��
				
					if (e.getButton() == 3) { 
						System.out.println("������ ���콺 Ŭ�� �ƽ��ϴ�.");
						//��� �̷��� �����ݴϴ�. �Ǵ� �ΰ����� ���� �ְڲ� �մϴ�.
						
						
						
					} // ������ Ŭ��	 */
				}
		});
		
		
		//���� �̺�Ʈ ���â
		panel_east = new JPanel();
		panel_east.setBorder(new LineBorder(new Color(0, 0, 0)));
		//add(panel_east, BorderLayout.EAST);
		center_tabbed_couponlist.add(panel_east, BorderLayout.EAST);
		panel_east.setLayout(new MigLayout("", "[grow][10px][grow][][grow]", "[30px][10px][][][10px][][][][][][][][10px][15px][15px][grow][]"));
		event_Reg();
		
	}
	
	
	//�̺�Ʈ ���� �����ϱ�
	public void setEventDetail(){
				
		setRenewCouponReg();
		
		//�ѹ��̶� ����� �̷��� �ִٸ� ������ ���ϰ� �����ϴ�.
		int row = center_table_couponlist.getSelectedRow();
		int col = center_table_couponlist.getColumnCount();
		if(row < 0){
			JOptionPane.showMessageDialog(this, "����� ������ �ּ���");			
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
		
		//��˻��ؼ� ��������
		String query = "Select * From "
				 + "( Select x.*, Isnull( (Select Count(*) use_cnt From e_Coupon_History a Where x.e_Seq=a.e_Seq ), 0) use_cnt From e_Coupon_List x ) "
				 + " X Where 1=1 ";		

		//���ǰ� ��������		
		//�˻���					
		query += "And e_Seq='"+temp_v.get(0).trim()+"' ";
		
		ms_connect.setMainSetting();
		HashMap<String, String> map = ms_connect.selectQueryOne(query);
		
		if(map.size() <= 0){
			JOptionPane.showMessageDialog(this, "��� �������� ��ȸ����� �����ϴ�.");
			return;
		}
		
		int count = Integer.parseInt(map.get("use_cnt"));		
		if(count > 0){
			JOptionPane.showMessageDialog(this, "������ ����� ���� �־ ���� �� ���� �Ͻ� �� �����ϴ�.");
			return;
		}
				
		
		System.out.println(map);
		//�̺�Ʈ �ҷ����� ��� ����	
		//���� �ڵ� ���� ����				
		east_text_code.setText(map.get("e_Seq"));
		east_text_code.setEditable(false);
		east_btn_callevent.setEnabled(false);
		
		east_text_name.setText(map.get("e_CouponName"));
				
		east_combo_gubun.setSelectedIndex(Integer.parseInt(map.get("e_gubun")));
			
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
			east_radio_overlapy.setSelected(true);
		}else{
			east_radio_overlapn.setSelected(true);
		}
		
		if(map.get("Del_YN").equals("0")){
			east_radio_usey.setSelected(true);
		}else{
			east_radio_usen.setSelected(true);
		}
						
		east_text_point.setText(map.get("e_Point"));
		east_text_prizesname.setText(map.get("e_Product"));
		east_text_prizescount.setText(map.get("e_pCnt"));
				
		east_btn_save.setText("���� ����");
		east_btn_save.setActionCommand("��������");				
	}
		
	
	//��������Ʈ ���� ��� ����ȭ��
	private void event_Reg(){
		
		east_label_title = new JLabel("\uCFE0\uD3F0 \uBC0F \uC774\uBCA4\uD2B8 \uB4F1\uB85D/\uC218\uC815");
		east_label_title.setHorizontalAlignment(SwingConstants.CENTER);
		east_label_title.setBackground(Color.PINK);
		east_label_title.setOpaque(true);
		east_label_title.setFont(new Font("���� ���", Font.BOLD, 15));
		panel_east.add(east_label_title, "cell 0 0 5 1,grow");
		
		east_label_code = new JLabel("\uCFE0\uD3F0 \uCF54\uB4DC");
		panel_east.add(east_label_code, "cell 0 2,alignx trailing,aligny center");
		
		east_text_code = new JTextField();
		panel_east.add(east_text_code, "cell 2 2,growx");
		east_text_code.setColumns(10);
		
		east_btn_callevent = new JButton("\uC774\uBCA4\uD2B8 \uBD88\uB7EC\uC624\uAE30");
		east_btn_callevent.setToolTipText("<html>\r\n\uD648\uD398\uC774\uC9C0 \uAD00\uB9AC\uC790 \uD398\uC774\uC9C0\uC5D0\uC11C \uB4F1\uB85D\uD55C \uC774\uBCA4\uD2B8 \uBAA9\uB85D\uC744 \uBD88\uB7EC\uC635\uB2C8\uB2E4.\r\n</html>");
		east_btn_callevent.setActionCommand("�̺�Ʈ�ҷ�����");
		east_btn_callevent.addActionListener(this);
		east_btn_callevent.setFont(new Font("���� ���", Font.PLAIN, 10));
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
		east_combo_gubun.setModel(new DefaultComboBoxModel<String>(new String[] {"\uD3EC\uC778\uD2B8\uC801\uB9BD \uCFE0\uD3F0", "\uC0AC\uC740\uD488\uC9C0\uAE09 \uCFE0\uD3F0"}));
		panel_east.add(east_combo_gubun, "cell 2 6 3 1,growx");
		east_combo_gubun.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(e.getStateChange() == 1){
					
					System.out.println(e.getItem());
					if(e.getItem().equals("����Ʈ���� ����")){						
						east_cardlayout.show(east_panel_data, "point");
						east_radio_membery.isSelected();
						east_radio_memberyn.setEnabled(false);
						east_radio_membery.setEnabled(false);						
					}else{
						east_cardlayout.show(east_panel_data, "prizes");						
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
		east_radio_memberyn.setEnabled(false);
		panel_east.add(east_radio_memberyn, "cell 2 8");
		
		east_radio_membery = new JRadioButton("\uD68C\uC6D0");
		east_radio_membery.setEnabled(false);
		east_radio_membery.setSelected(true);
		panel_east.add(east_radio_membery, "cell 4 8");
		
		east_bg_memberyn = new ButtonGroup();
		east_bg_memberyn.add(east_radio_memberyn);
		east_bg_memberyn.add(east_radio_membery);
		
		east_label_memoverlap = new JLabel("\uC911\uBCF5 \uC124\uC815");
		panel_east.add(east_label_memoverlap, "cell 0 9,alignx trailing,aligny center");
		
		east_radio_overlapn = new JRadioButton("\uD5C8\uC6A9\uC548\uD568");
		east_radio_overlapn.setSelected(true);
		panel_east.add(east_radio_overlapn, "cell 2 9");
		
		east_radio_overlapy = new JRadioButton("\uD5C8\uC6A9\uD568");
		panel_east.add(east_radio_overlapy, "cell 4 9");
		
		east_bg_overlapyn = new ButtonGroup();
		east_bg_overlapyn.add(east_radio_overlapn);
		east_bg_overlapyn.add(east_radio_overlapy);		
		
		east_cardlayout = new CardLayout();
		east_panel_data = new JPanel();
		panel_east.add(east_panel_data, "cell 0 10 5 1,grow");
		east_panel_data.setLayout(east_cardlayout);
		
		east_panel_point = new JPanel();		
		east_panel_point.setBorder(new TitledBorder(null, "\uC801\uB9BD \uD3EC\uC778\uD2B8 \uC810\uC218", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		east_panel_data.add("point", east_panel_point);
		
		east_label_point = new JLabel("\uC801\uB9BD\uC810\uC218");
		east_panel_point.add(east_label_point);
		
		east_text_point = new JTextField();
		east_text_point.setHorizontalAlignment(SwingConstants.RIGHT);		
		east_panel_point.add(east_text_point);
		east_text_point.setColumns(10);
		
		east_label_pointright = new JLabel("(\uC810)");
		east_panel_point.add(east_label_pointright);
		
		east_panel_prizes = new JPanel();
		east_panel_prizes.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\uC0AC\uC740\uD488 \uC815\uBCF4", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		east_panel_data.add("prizes", east_panel_prizes);
		east_panel_prizes.setLayout(new MigLayout("", "[57px][][][][grow]", "[21px][]"));
		
		east_label_prizesname = new JLabel("\uC81C\uD488\uBA85");
		east_panel_prizes.add(east_label_prizesname, "cell 0 0,alignx trailing,aligny center");
		
		east_text_prizesname = new JTextField();
		east_panel_prizes.add(east_text_prizesname, "cell 1 0 4 1,growx,aligny top");
		east_text_prizesname.setColumns(10);
		
		east_label_prizescount = new JLabel("\uC218\uB7C9");
		east_panel_prizes.add(east_label_prizescount, "cell 0 1,alignx trailing");
		
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
		east_panel_prizes.add(east_btn_prizescountdown, "cell 1 1");
		
		east_text_prizescount = new JTextField();
		east_text_prizescount.setEditable(false);
		east_text_prizescount.setText("0");
		east_text_prizescount.setHorizontalAlignment(SwingConstants.RIGHT);
		east_panel_prizes.add(east_text_prizescount, "cell 2 1");
		east_text_prizescount.setColumns(3);
		east_panel_prizes.add(east_btn_prizescountup, "cell 3 1");
		
		east_label_useyn = new JLabel("\uC0AC\uC6A9\uC5EC\uBB34");
		panel_east.add(east_label_useyn, "cell 0 11,alignx trailing");
		
		east_radio_usey = new JRadioButton("\uC0AC\uC6A9\uD568");
		east_radio_usey.setSelected(true);
		panel_east.add(east_radio_usey, "cell 2 11");
		
		east_radio_usen = new JRadioButton("\uC0AC\uC6A9\uC548\uD568");
		panel_east.add(east_radio_usen, "cell 4 11");
		
		east_bg_useyn = new ButtonGroup();
		east_bg_useyn.add(east_radio_usey);
		east_bg_useyn.add(east_radio_usen);
		
		east_btn_save = new JButton("\uCFE0\uD3F0\uC800\uC7A5");
		east_btn_save.setActionCommand("��������");
		east_btn_save.addActionListener(this);
		east_btn_save.setForeground(Color.RED);
		east_btn_save.setFont(new Font("���� ���", Font.BOLD, 15));
		east_btn_save.setBackground(Color.BLUE);
		panel_east.add(east_btn_save, "cell 4 13 1 2,grow");
		
		east_btn_renew = new JButton("\uC0C8\uB85C\uC785\uB825");
		east_btn_renew.setActionCommand("�����Է�");
		east_btn_renew.addActionListener(this);
		panel_east.add(east_btn_renew, "cell 4 16,grow");
		
	}

	
	//���� ���� ȭ��
	private void event_CouponTran(){		

		tran_label_title1 = new JLabel("1. \uC804\uC1A1 \uB300\uC0C1 \uC120\uD0DD");
		panel_coupontran_1.add(tran_label_title1, "cell 0 0 2 1,grow");
		
		tran_label_level = new JLabel("\uD68C\uC6D0 \uB4F1\uAE09");
		panel_coupontran_1.add(tran_label_level, "cell 0 1,alignx trailing");
		
		tran_combo_level = new JComboBox<String>();
		tran_combo_level.setModel(new DefaultComboBoxModel<String>(new String[] {"\uC804\uCCB4", "\uBE44\uD68C\uC6D0", "\uC77C\uBC18\uD68C\uC6D0", "\uAC00\uC785\uB300\uAE30\uD68C\uC6D0", "\uD0C8\uD1F4\uD68C\uC6D0"}));
		panel_coupontran_1.add(tran_combo_level, "cell 1 1,growx");
		
		tran_label_joinyn = new JLabel("\uD68C\uC6D0\uAC00\uC785\uC5EC\uBD80");
		panel_coupontran_1.add(tran_label_joinyn, "cell 0 2,alignx trailing");
		
		tran_combo_joinyn = new JComboBox<String>();
		tran_combo_joinyn.setModel(new DefaultComboBoxModel<String>(new String[] {"\uC804\uCCB4", "\uBE44\uD68C\uC6D0", "\uAC00\uC785\uD68C\uC6D0"}));
		panel_coupontran_1.add(tran_combo_joinyn, "cell 1 2,growx");
		
		tran_label_memid = new JLabel("\uD68C\uC6D0 \uC544\uC774\uB514");
		panel_coupontran_1.add(tran_label_memid, "cell 0 3,alignx trailing");
		
		tran_text_memid = new JTextField();
		panel_coupontran_1.add(tran_text_memid, "cell 1 3,growx");
		tran_text_memid.setColumns(10);
		
		tran_label_hp = new JLabel("\uD734\uB300\uD3F0\uBC88\uD638");
		panel_coupontran_1.add(tran_label_hp, "cell 0 4,alignx trailing");
		
		tran_text_hp = new JTextField();
		panel_coupontran_1.add(tran_text_hp, "cell 1 4,growx");
		tran_text_hp.setColumns(10);
		
		tran_label_title2 = new JLabel("2. \uC804\uC1A1 \uBC29\uC2DD \uC120\uD0DD");
		panel_coupontran_1.add(tran_label_title2, "cell 0 6 2 1,grow");
		
		tran_label_trandata = new JLabel("\uC804\uC1A1\uBC29\uC2DD");
		panel_coupontran_1.add(tran_label_trandata, "cell 0 7,alignx trailing");
		
		tran_combo_trandata = new JComboBox<String>();
		tran_combo_trandata.setModel(new DefaultComboBoxModel<String>(new String[] {"\uBA54\uC138\uC9C0", "\uC774\uBBF8\uC9C0", "\uC774\uBCA4\uD2B8\uCFE0\uD3F0"}));
		panel_coupontran_1.add(tran_combo_trandata, "cell 1 7,growx");
		
		tran_label_title3 = new JLabel("3. \uC804\uC1A1\uD558\uAE30");
		panel_coupontran_1.add(tran_label_title3, "cell 0 9 2 1,grow");
		
		tran_btn_submit = new JButton("\uC804\uC1A1");
		panel_coupontran_1.add(tran_btn_submit, "cell 0 10 2 1,grow");
		
		panel_coupontran_2 = new JPanel();
		center_tabbed_coupontran.add(panel_coupontran_2, BorderLayout.CENTER);
		panel_coupontran_2.setLayout(new CardLayout(0, 0));
		
		tran_panel_msg = new JPanel();
		panel_coupontran_2.add(tran_panel_msg, "name_147218793477676");
		tran_panel_msg.setLayout(new BorderLayout(5, 0));
		
		tranmsg_panel_msg = new JPanel();
		tranmsg_panel_msg.setBorder(new LineBorder(new Color(0, 0, 0)));
		tran_panel_msg.add(tranmsg_panel_msg, BorderLayout.WEST);
		tranmsg_panel_msg.setLayout(new MigLayout("", "[][grow][]", "[][][grow][]"));
		
		tranmsg_label_title = new JLabel("\uC81C\uBAA9");
		tranmsg_panel_msg.add(tranmsg_label_title, "cell 0 0,alignx trailing");
		
		tranmsg_text_title = new JTextField();
		tranmsg_panel_msg.add(tranmsg_text_title, "cell 1 0 2 1,growx");
		tranmsg_text_title.setColumns(10);
		
		tranmsg_label_msg = new JLabel("\uBA54\uC138\uC9C0");
		tranmsg_panel_msg.add(tranmsg_label_msg, "cell 0 1,alignx trailing");
		
		tranmsg_textArea_msg = new JTextArea();
		tranmsg_panel_msg.add(tranmsg_textArea_msg, "cell 1 1 2 2,grow");
		
		tranmsg_label_linkurl = new JLabel("\uB9C1\uD06C URL");
		tranmsg_panel_msg.add(tranmsg_label_linkurl, "cell 0 3,alignx trailing");
		
		tranmsg_text_linkurl = new JTextField();
		tranmsg_panel_msg.add(tranmsg_text_linkurl, "cell 1 3,growx");
		tranmsg_text_linkurl.setColumns(10);
		
		tranmsg_btn_msgsave = new JButton("\uBA54\uC81C\uC9C0 \uC800\uC7A5");
		tranmsg_panel_msg.add(tranmsg_btn_msgsave, "cell 2 3");
		
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
		tranmsg_panel_list.add(tranmsg_btn_listsearch, "cell 4 3,growx");
		
		tranmsg_scroll_list = new JScrollPane();
		tranmsg_panel_list.add(tranmsg_scroll_list, "cell 0 4 5 1,grow");
		
		tranmsg_table_list = new JTable();
		tranmsg_scroll_list.setViewportView(tranmsg_table_list);
		
		tran_panel_img = new JPanel();
		panel_coupontran_2.add(tran_panel_img, "name_147231981059254");
		tran_panel_img.setLayout(new BorderLayout(5, 0));
		
		tranimg_panel_preview = new JPanel();
		tranimg_panel_preview.setBorder(new LineBorder(new Color(0, 0, 0)));
		tran_panel_img.add(tranimg_panel_preview, BorderLayout.WEST);
		tranimg_panel_preview.setLayout(new MigLayout("", "[][grow]", "[][grow][]"));
		
		tranimg_label_title = new JLabel("\uC81C\uBAA9");
		tranimg_panel_preview.add(tranimg_label_title, "cell 0 0,alignx trailing");
		
		tranimg_text_title = new JTextField();
		tranimg_panel_preview.add(tranimg_text_title, "cell 1 0,growx");
		tranimg_text_title.setColumns(10);
		
		tranimg_editorPane_img = new JEditorPane();
		tranimg_panel_preview.add(tranimg_editorPane_img, "cell 0 1 2 1,grow");
		
		tranimg_label_linkurl = new JLabel("\uB9C1\uD06C URL");
		tranimg_panel_preview.add(tranimg_label_linkurl, "cell 0 2,alignx trailing");
		
		tranimg_text_linkurl = new JTextField();
		tranimg_panel_preview.add(tranimg_text_linkurl, "cell 1 2,growx");
		tranimg_text_linkurl.setColumns(10);
		
		tranimg_panel_imglist = new JPanel();
		tranimg_panel_imglist.setBorder(new LineBorder(new Color(0, 0, 0)));
		tran_panel_img.add(tranimg_panel_imglist, BorderLayout.CENTER);
		tranimg_panel_imglist.setLayout(new MigLayout("", "[grow][grow][]", "[][][][grow]"));
		
		tranimg_label_listtitle = new JLabel("\uC774\uBBF8\uC9C0 \uBAA9\uB85D");
		tranimg_panel_imglist.add(tranimg_label_listtitle, "cell 0 0");
		
		tranimg_label_pcpath = new JLabel("PC \uD3F4\uB354");
		tranimg_panel_imglist.add(tranimg_label_pcpath, "cell 0 1,alignx trailing");
		
		tranimg_text_pcpath = new JTextField();
		tranimg_panel_imglist.add(tranimg_text_pcpath, "cell 1 1,growx");
		tranimg_text_pcpath.setColumns(10);
		
		tranimg_btn_pcpath = new JButton("\uD3F4\uB354\uAC80\uC0C9");
		tranimg_panel_imglist.add(tranimg_btn_pcpath, "cell 2 1,growx");
		
		tranimg_label_ftptitle = new JLabel("FTP \uC9C0\uC815\uD3F4\uB354 \uC774\uBBF8\uC9C0 \uAC00\uC838\uC624\uAE30");
		tranimg_panel_imglist.add(tranimg_label_ftptitle, "cell 1 2");
		
		tranimg_btn_ftppath = new JButton("FTP\uAC80\uC0C9");
		tranimg_panel_imglist.add(tranimg_btn_ftppath, "cell 2 2,growx");
		
		tranimg_scroll_imglist = new JScrollPane();
		tranimg_scroll_imglist.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		tranimg_panel_imglist.add(tranimg_scroll_imglist, "cell 0 3 3 1,grow");
		
		tranimg_panel_imgview = new JPanel();
		tranimg_scroll_imglist.setViewportView(tranimg_panel_imgview);
		tranimg_panel_imgview.setLayout(new GridLayout(0, 3, 0, 0));
		
		tran_panel_evt = new JPanel();
		panel_coupontran_2.add(tran_panel_evt, "name_147256226379270");
		tran_panel_evt.setLayout(new MigLayout("", "[][grow]", "[][][grow]"));
		
		tranevt_label_listtitle = new JLabel("\uD478\uC2DC \uC774\uBCA4\uD2B8 \uBAA9\uB85D");
		tran_panel_evt.add(tranevt_label_listtitle, "cell 0 0,aligny top");
		
		tranevt_label_title = new JLabel("\uC81C\uBAA9");
		tran_panel_evt.add(tranevt_label_title, "cell 0 1,alignx trailing");
		
		tranevt_text_title = new JTextField();
		tran_panel_evt.add(tranevt_text_title, "cell 1 1,growx");
		tranevt_text_title.setColumns(10);
		
		tranevt_scroll_evtlist = new JScrollPane();
		tran_panel_evt.add(tranevt_scroll_evtlist, "cell 0 2 2 1,grow");
		
		tranevt_table_evtlist = new JTable();
		tranevt_scroll_evtlist.setViewportView(tranevt_table_evtlist);
		
		
		
	}
	
	
	
	//�̺�Ʈ ��� ����Ʈ
	private void event_UseList(){
		
		String[] colunm_uselist = {"����", "<html>���� �ڵ�<br>���� ��<br>�������ڵ�<br>���� ����</html>", "seq", "<html>��ǥ��ȣ<br>�Ǹ�����<br>�ǸŽð�</html>", "������", "����Ʈ"	, "<html>��ǰ��<br>����</html>", "<html>�Ǹ���<br>�Ǹ���</html>", "<html>��ǰ����<br>����ǥ��ȣ</html>", "<html>��������<br>������</html>"};
		
		dtm_uselist = new DefaultTableModel(null, colunm_uselist){			
			private static final long serialVersionUID = 4568136684L;

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
	    
	    //���� ����Ʈ��� �κ� �߾�����
	    ((DefaultTableCellRenderer)center_table_uselist.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(0);
	    
	    center_table_uselist.setRowHeight(25);
	    center_table_uselist.getTableHeader().setReorderingAllowed(false);  //�̵��Ұ�	    
	    //center_table_couponlist.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);  //���� ��ũ��	    
	    
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
	
	
	
	//��� �˻� ���� �ҷ�����
	public void getTopSearchStart(){
		
		String query = "Select * From "
						 + "( Select x.*, Isnull( (Select Count(*) use_cnt From e_Coupon_History a Where x.e_Seq=a.e_Seq ), 0) use_cnt From e_Coupon_List x ) "
						 + " X Where 1=1 ";		
		
		//���ǰ� ��������		
		//�˻���
		if(top_text_searchname.getText().length() > 0){			
			query += "And (e_Seq='"+top_text_searchname.getText()+"' or e_CouponName Like '%"+top_text_searchname+"%' ) ";			
		}
		
		//��������
		switch(top_combo_eventgubun.getSelectedIndex()){		
		case 1:
			query +=" And e_gubun='0' ";
			break;
		case 2:
			query +=" And e_gubun='1' ";
			break;
		}
		
		//�������		
		switch(top_combo_ingyn.getSelectedIndex()){
		case 1:
			query +=" And Del_YN='0' ";
			break;
		case 2:
			query +=" And Del_YN='1' ";
			break;
		}		
		
		ms_connect.setMainSetting();
		ArrayList<HashMap<String, String>> temp_array = ms_connect.connection(query);
		
		//if(temp_array.size() <= 0) JOptionPane.showMessageDialog(this, "�˻��� ����� �����ϴ�.");
		
		setEventList(temp_array);
		System.out.println(temp_array);
	}
	
	//��� ����
	public void setEventList(ArrayList<HashMap<String, String>> temp_array){
		
		dtm_couponlist.setRowCount(0);
		setRenewCouponReg();
		
		//�����ؼ� ��Ͽ� ǥ���մϴ�.
		Iterator<HashMap<String, String>> itr = temp_array.iterator();
		int count=1;
		while(itr.hasNext()){
			HashMap<String, String> temp = itr.next();
			Vector<Object> v = new Vector<Object>();
			
			v.addElement(count);
			//String[] name = {temp.get("e_Seq"), temp.get("e_CouponName")};
			String name = "<html>"+temp.get("e_Seq")+"<br>"+temp.get("e_CouponName")+"</html>";
			v.addElement(name);
			String date = "<html>"+temp.get("e_Sdate")+"<br>"+temp.get("e_EDate")+"</html>";
			v.addElement(date);
			
			String member = "<html>";
			if(temp.get("e_MEM_YN").equals("0")){
				member += "��ü<br>";
			}else{
				member += "ȸ��<br>";
			}
			
			if(temp.get("e_Over_YN").equals("0")){
				member += "������</html>";
			}else{
				member += "�����</html>";
			}
			v.addElement(member);
			
			String gubun = "";
			String point = "";
			String prizes = "";
			if(temp.get("e_gubun").equals("0")){
				gubun += "����Ʈ����";
				point += temp.get("e_Point");
			}else{
				gubun += "����ǰ����";
				prizes += "<html>"+temp.get("e_Product")+"<br>"+temp.get("e_pCnt")+"</html>";
			}
			v.addElement(gubun);
			v.addElement(point);
			v.addElement(prizes);
			
			v.addElement(temp.get("use_cnt"));
			
			String reg_date = "<html>";
			
			String edit_date = "";
			try{
				if(!edit_date.isEmpty()) edit_date = temp.get("Edit_Date");
			}catch(NullPointerException e){
				
			}
			
			String del_date = "";
			try{
				if(!temp.get("Del_Date").isEmpty()) del_date = temp.get("Del_Date");
			}catch(NullPointerException e){
				
			}
			reg_date += temp.get("Write_Date")+"<br>"+edit_date+"<br>"+del_date+"<html>";			
			v.addElement(reg_date);
						
			String ingyn = "";
			if(temp.get("Del_YN").equals("0")){
				ingyn = "�����";
			}else{
				ingyn = "������";
			}
			v.addElement(ingyn);			
			
			dtm_couponlist.addRow(v);
			count++;
		}
		
	}
	
	//�̺�Ʈ ����Ʈ�� �ҷ��ͼ� ������� ���� �ݴϴ�.
	public void getEventListChoose(){
		
		//����� ȣ���մϴ�.
		JSONArray temp_event = trans_shopapi.getPushEventList();
				
		if(temp_event.size() <= 0){			
			JOptionPane.showMessageDialog(this, "�̺�Ʈ ����� �ҷ����� ���� �߽��ϴ�.");
			return;
		}
				
		String[] list = new String[temp_event.size()];
		for(int i = 0; i < temp_event.size(); i++){
			JSONObject temp_map = (JSONObject)temp_event.get(i);
			
			String item = temp_map.get("idx")+" : "+temp_map.get("subject");
			list[i] = item;			
		}
		
		//ȣ���� ����� ���̾� �α׷� ���ϴ�.		
	    String input = (String) JOptionPane.showInputDialog(east_combo_gubun, "������ �̺�Ʈ�� �����ϼ���!!",
	    		"�̺�Ʈ ���", JOptionPane.QUESTION_MESSAGE, null, // Use
	                                                                        // default
	                                                                        // icon
	        list, // Array of choices
	        list[0]); // Initial choice
	    System.out.println(input);
		
	    try{
		//������ �̺�Ʈ�� �ҷ� �ɴϴ�.
		east_text_code.setText(input.substring(0, input.indexOf(":")).trim());
		east_text_name.setText(input.substring(input.indexOf(":")+1, input.length()).trim());
	    }catch(NullPointerException e){
	    		    	
	    }
		
	}
	
	
	
	//��� �˻� ���� �ʱ�ȭ �ϱ�
	public void setTopRenew(){
		
		top_text_searchname.setText("");
		top_combo_eventgubun.setSelectedIndex(0);
		top_combo_ingyn.setSelectedIndex(1);
		top_combo_useyn.setSelectedIndex(0);
		
	}
	
	//���� ���� ����ȭ�� �ʱ�ȭ �ϱ�
	public void setRenewCouponReg(){
				
		east_btn_callevent.setEnabled(true);
		east_text_code.setText("");
		east_text_code.setEditable(true);
		east_text_name.setText("");
		
		east_combo_gubun.setSelectedIndex(0);
			
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date today = new Date();
		String today_date = sdf.format(today);
		System.out.println(today_date);
		
		east_chooser_start.setDate(today);
		east_chooser_end.setDate(today);
		
		east_radio_membery.setSelected(true);
		east_radio_overlapn.setSelected(true);
		east_radio_usey.setSelected(true);
				
		east_text_point.setText("");
		east_text_prizesname.setText("");
		east_text_prizescount.setText("0");
				
		east_radio_usey.setSelected(true);
		
		east_btn_save.setText("���� ����");
		east_btn_save.setActionCommand("��������");
		
	}
	
	//���������� �����մϴ�.
	private void setCouponUpdate() {
		
		
	}

	//���������� ����մϴ�.
	private void setCouponSave() {
		
		//�ڵ尡 ��� �Ǿ����� Ȯ�� �ؾ� �մϴ�.
		
		//��� �Ǿ� �ִٸ� ����Ҽ� �����ϴ�.
		
		//���� �˻�
		//��� �ڵ尡 ���ڷ� �̷�� ������ Ȯ�� �մϴ�.
		//������ ���� ���̸� �����մϴ�.
		//���� ���п� ���� ����Ʈ �� ����ǰ�� �Ǵ� ����ǰ ������ �־����� Ȯ�� �մϴ�.
		//������ ���ڰ� �������� �����Ϻ��� ������ Ȯ�� �մϴ�.
		
		
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String command = e.getActionCommand();
		
		switch(command){
		case "�˻�":
			getTopSearchStart();			
			break;			
		case "�����":
			setTopRenew();
			break;
		case "�����Է�":
			setRenewCouponReg();
			break;
		case "�̺�Ʈ�ҷ�����":
			getEventListChoose();			
			break;
		case "��������":
			setCouponSave();
			break;
		case "��������":
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
	
	
}
