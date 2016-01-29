import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import net.miginfocom.swing.MigLayout;
import javax.swing.JSplitPane;
import java.awt.FlowLayout;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import java.awt.Font;

public class Order_Manage extends JPanel implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1567559994L;
	private JTextField text_top_dayend;
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
	private JTextField text_top_daystart;
	
	public Order_Manage() {
		
		ms_conn = new Ms_Connect();
		ms_conn.setMainSetting();
		
		BorderLayout borderLayout = new BorderLayout();
		borderLayout.setVgap(5);
		borderLayout.setHgap(5);
		setLayout(borderLayout);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		JPanel panel_top_search = new JPanel();
		panel_top_search.setBorder(new LineBorder(new Color(0, 0, 0)));
		this.add(panel_top_search, BorderLayout.NORTH);
		panel_top_search.setLayout(new MigLayout("", "[][][][][][][][][][]", "[][][]"));
		
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
		panel_top_search.add(btn_top_search, "cell 9 0 1 3,growy");
		
		JLabel label_top_daybyday = new JLabel("\uC8FC\uBB38\uAE30\uAC04");
		panel_top_search.add(label_top_daybyday, "cell 0 1,alignx trailing");
		
		text_top_daystart = new JTextField();
		panel_top_search.add(text_top_daystart, "cell 1 1 2 1,growx");
		text_top_daystart.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("~");
		panel_top_search.add(lblNewLabel_1, "cell 3 1,alignx center");
		
		text_top_dayend = new JTextField();
		panel_top_search.add(text_top_dayend, "cell 4 1 2 1,growx");
		text_top_dayend.setColumns(10);
		
		JLabel label_top_info = new JLabel("\uC8FC\uBB38\uC815\uBCF4");
		panel_top_search.add(label_top_info, "cell 0 2,alignx trailing");
		
		text_top_info = new JTextField();
		panel_top_search.add(text_top_info, "cell 1 2 3 1,growx");
		text_top_info.setColumns(10);
		
		JLabel label_top_infotitle = new JLabel("< \uC804\uD654\uBC88\uD638/\uD578\uB4DC\uD3F0\uBC88\uD638/\uC774\uB984/ID \uC785\uB825\uAC80\uC0C9 \uAC00\uB2A5");
		panel_top_search.add(label_top_infotitle, "cell 4 2 4 1");
		
		JPanel panel_bottom_change = new JPanel();
		panel_bottom_change.setBorder(new LineBorder(new Color(0, 0, 0)));
		this.add(panel_bottom_change, BorderLayout.SOUTH);
		panel_bottom_change.setLayout(new MigLayout("", "[100px][][][][]", "[][]"));
		
		JLabel label_bottom_order = new JLabel("\uC8FC\uBB38\uC11C \uCC98\uB9AC");
		label_bottom_order.setBackground(new Color(240, 255, 255));
		label_bottom_order.setOpaque(true);
		panel_bottom_change.add(label_bottom_order, "cell 0 0 1 2,grow");
		
		JLabel label_bottom_info1 = new JLabel("\uC120\uD0DD\uD55C \uC8FC\uBB38\uC11C\uB97C ");
		panel_bottom_change.add(label_bottom_info1, "cell 1 0");
		
		JButton btn_bottom_state1 = new JButton("\uBBF8\uD655\uC778->\uBC1C\uC1A1\uB300\uAE30\uB85C \uBCC0\uACBD");
		panel_bottom_change.add(btn_bottom_state1, "cell 2 0");
		
		JButton btn_bottom_state12 = new JButton("\uC8FC\uBB38\uCDE8\uC18C \uC0C1\uD0DC\uB85C \uBCC0\uACBD");
		panel_bottom_change.add(btn_bottom_state12, "cell 3 0");
		
		JLabel label_bottom_info2 = new JLabel("\uC120\uD0DD\uD55C \uC8FC\uBB38\uC11C\uB97C");
		panel_bottom_change.add(label_bottom_info2, "cell 1 1");
		
		JButton btn_orderList_print = new JButton("\uC778\uC1C4\uD558\uAE30");
		panel_bottom_change.add(btn_orderList_print, "cell 2 1");
		
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
		
		JScrollPane scrollPane_orderList = new JScrollPane();
		splitPane_middle_order.setLeftComponent(scrollPane_orderList);
		scrollPane_orderList.getVerticalScrollBar().setUnitIncrement(20);
		
		String[] colunm = {"순번", "주문번호", "주문상태", "회원ID", "주문자명", "휴대폰번호", "결제방식", "결제금액", "상품대체방식", "결제유형", "주문일"};
		
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
		table_orderList.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);  //가로 스크롤		
				
		table_orderList.getTableHeader().setReorderingAllowed(false);  //이동불가		
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
		
		
		
    	// {0."순번", 1."주문상태", 2."회원ID", 3."주문자명", 4."휴대폰번호", 5."결제방식", 6."결제금액", 7."상품대체방식", 8."결제유형", 9."주문일"};
    	//컬럼넓이 조정
    	table_orderList.getColumn("순번").setPreferredWidth(40);
    	//컬럼 정렬   	
    	table_orderList.getColumn("순번").setCellRenderer(celAlignCenter);
    	table_orderList.getColumn("주문번호").setCellRenderer(celAlignCenter);
    	table_orderList.getColumn("주문상태").setCellRenderer(celAlignCenter);
    	table_orderList.getColumn("회원ID").setCellRenderer(celAlignCenter);
    	table_orderList.getColumn("주문자명").setCellRenderer(celAlignCenter);
    	table_orderList.getColumn("휴대폰번호").setCellRenderer(celAlignCenter);
    	table_orderList.getColumn("결제방식").setCellRenderer(celAlignCenter);
    	table_orderList.getColumn("결제금액").setCellRenderer(celAlignCenter);
    	table_orderList.getColumn("상품대체방식").setCellRenderer(celAlignCenter);
    	table_orderList.getColumn("결제유형").setCellRenderer(celAlignCenter);
    	table_orderList.getColumn("주문일").setCellRenderer(celAlignCenter);
				
    	/*table_orderList.getColumn("주문상태").setPreferredWidth(120);
    	table_orderList.getColumn("회원ID").setPreferredWidth(120);
    	table_orderList.getColumn("주문자명").setPreferredWidth(80);
    	table_orderList.getColumn("휴대폰번호").setPreferredWidth(100);
    	table_orderList.getColumn("결제방식").setPreferredWidth(80);
    	table_orderList.getColumn("결제금액").setPreferredWidth(80);
    	table_orderList.getColumn("주문일").setPreferredWidth(100);*/
    	
        	
    	JPanel panel_orderList_goods = new JPanel();
		splitPane_middle_order.setRightComponent(panel_orderList_goods);
		panel_orderList_goods.setLayout(new BorderLayout(5, 5));
		
		JScrollPane scrollPane_goodsList = new JScrollPane();
		panel_orderList_goods.add(scrollPane_goodsList, BorderLayout.CENTER);
		scrollPane_goodsList.getVerticalScrollBar().setUnitIncrement(20);
		
		String[] colunm_goods = { "순번", "주문번호", "상품이미지", "상품명", "바코드", "수량", "금액", "합계" };
		
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
		scrollPane_goodsList.setViewportView(table_goodsList);
		
		
		((DefaultTableCellRenderer)table_goodsList.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(0);
				
		table_goodsList.setRowHeight(25);
		table_goodsList.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);  //가로 스크롤		
		
		JScrollPane scrollPane_goods_detail = new JScrollPane();
		scrollPane_goods_detail.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panel_orderList_goods.add(scrollPane_goods_detail, BorderLayout.EAST);
		scrollPane_goods_detail.getVerticalScrollBar().setUnitIncrement(20);
		
		
		JPanel panel_goods_detail = new JPanel();
		//panel_orderList_goods.add(panel, BorderLayout.NORTH);
		scrollPane_goods_detail.setViewportView(panel_goods_detail);
		panel_goods_detail.setLayout(new MigLayout("", "[][200px]", "[][][][][][][][][][][][][][][][]"));
		
		JLabel label_detail_title = new JLabel("\uC8FC\uBB38\uC815\uBCF4");
		label_detail_title.setBackground(new Color(240, 255, 255));
		label_detail_title.setOpaque(true);
		label_detail_title.setHorizontalAlignment(SwingConstants.CENTER);
		panel_goods_detail.add(label_detail_title, "cell 0 0 2 1,grow");
		
		JLabel label_detail_orderday = new JLabel("\uC8FC\uBB38\uC77C\uC790");
		label_detail_orderday.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		panel_goods_detail.add(label_detail_orderday, "cell 0 1,alignx trailing");
		
		label_detailtext_jday = new JLabel("");
		panel_goods_detail.add(label_detailtext_jday, "cell 1 1");
		
		JLabel label_detail_orderidx = new JLabel("\uC8FC\uBB38\uBC88\uD638");
		label_detail_orderidx.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		panel_goods_detail.add(label_detail_orderidx, "cell 0 2,alignx trailing");
		
		label_detailtext_jidx = new JLabel("");
		panel_goods_detail.add(label_detailtext_jidx, "cell 1 2");
		
		JLabel label_detail_id = new JLabel("\uD68C\uC6D0ID");
		label_detail_id.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		panel_goods_detail.add(label_detail_id, "cell 0 3,alignx trailing");
		
		label_detailtext_id = new JLabel("");
		panel_goods_detail.add(label_detailtext_id, "cell 1 3");
		
		JLabel label_detail_jname = new JLabel("\uC8FC\uBB38\uC790");
		label_detail_jname.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		panel_goods_detail.add(label_detail_jname, "cell 0 4,alignx trailing");
		
		label_detailtext_jname = new JLabel("");
		panel_goods_detail.add(label_detailtext_jname, "cell 1 4");
		
		JLabel label_detail_jtel = new JLabel("\uC804\uD654\uBC88\uD638");
		label_detail_jtel.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		panel_goods_detail.add(label_detail_jtel, "cell 0 5,alignx trailing");
		
		label_detailtext_jtel = new JLabel("");
		panel_goods_detail.add(label_detailtext_jtel, "cell 1 5");
		
		JLabel label_detail_jhp = new JLabel("\uD734\uB300\uC804\uD654");
		label_detail_jhp.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		panel_goods_detail.add(label_detail_jhp, "cell 0 6,alignx trailing");
		
		label_detailtext_jhp = new JLabel("");
		panel_goods_detail.add(label_detailtext_jhp, "cell 1 6");
		
		JLabel label_detail_comment = new JLabel("\uC694\uAD6C\uC0AC\uD56D");
		label_detail_comment.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		panel_goods_detail.add(label_detail_comment, "cell 0 7,alignx trailing");
		
		label_detailtext_comment = new JLabel("");
		panel_goods_detail.add(label_detailtext_comment, "cell 1 7");
		
		JLabel label_detail_wantday = new JLabel("\uD76C\uB9DD\uBC30\uC1A1\uC77C");
		label_detail_wantday.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		panel_goods_detail.add(label_detail_wantday, "cell 0 8,alignx trailing");
		
		label_detailtext_wantday = new JLabel("");
		panel_goods_detail.add(label_detailtext_wantday, "cell 1 8");
		
		JLabel label_detail_add1 = new JLabel("\uB300\uCCB4\uC0C1\uD488\uC120\uD0DD");
		label_detail_add1.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		panel_goods_detail.add(label_detail_add1, "cell 0 9,alignx trailing");
		
		label_detailtext_add1 = new JLabel("");
		panel_goods_detail.add(label_detailtext_add1, "cell 1 9");
		
		JLabel label_detail_add2 = new JLabel("\uACB0\uC81C\uC720\uD615");
		label_detail_add2.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		panel_goods_detail.add(label_detail_add2, "cell 0 10,alignx trailing");
		
		label_detailtext_add2 = new JLabel("");
		panel_goods_detail.add(label_detailtext_add2, "cell 1 10");
		
		JLabel label_detail_sname = new JLabel("\uC218\uC2E0\uC790");
		label_detail_sname.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		panel_goods_detail.add(label_detail_sname, "cell 0 11,alignx trailing");
		
		label_detailtext_sname = new JLabel("");
		panel_goods_detail.add(label_detailtext_sname, "cell 1 11");
		
		JLabel label_detail_saddress = new JLabel("\uC8FC\uC18C");
		label_detail_saddress.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		panel_goods_detail.add(label_detail_saddress, "cell 0 12,alignx trailing");
		
		label_detailtext_jaddress = new JLabel("");
		panel_goods_detail.add(label_detailtext_jaddress, "cell 1 12");
		
		JLabel label_detail_stel = new JLabel("\uC804\uD654\uBC88\uD638");
		label_detail_stel.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		panel_goods_detail.add(label_detail_stel, "cell 0 13,alignx trailing");
		
		label_detailtext_stel = new JLabel("");
		panel_goods_detail.add(label_detailtext_stel, "cell 1 13");
		
		JLabel label_detail_shp = new JLabel("\uD734\uB300\uC804\uD654");
		label_detail_shp.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		panel_goods_detail.add(label_detail_shp, "cell 0 14,alignx trailing");
		
		label_detailtext_shp = new JLabel("");
		panel_goods_detail.add(label_detailtext_shp, "cell 1 14");
		
		JLabel label_detail_tomsg = new JLabel("\uC804\uB2EC\uAE00");
		label_detail_tomsg.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		panel_goods_detail.add(label_detail_tomsg, "cell 0 15,alignx trailing");
		
		label_detailtext_tomsg = new JLabel("");
		panel_goods_detail.add(label_detailtext_tomsg, "cell 1 15");
		
						
		table_goodsList.getTableHeader().setReorderingAllowed(false);  //이동불가	
			
		//컬럼넓이 조정
		table_goodsList.getColumn("순번").setPreferredWidth(40);
    	
    	//컬럼 정렬   	 { "순번", "주문번호", "상품이미지", "상품명", "바코드", "수량", "금액", "합계" };
		table_goodsList.getColumn("순번").setCellRenderer(celAlignCenter);
		table_goodsList.getColumn("주문번호").setCellRenderer(celAlignCenter);
		table_goodsList.getColumn("상품이미지").setCellRenderer(celAlignCenter);
		table_goodsList.getColumn("상품명").setCellRenderer(celAlignCenter);
		table_goodsList.getColumn("바코드").setCellRenderer(celAlignCenter);
		table_goodsList.getColumn("수량").setCellRenderer(celAlignCenter);
		table_goodsList.getColumn("금액").setCellRenderer(celAlignCenter);
		table_goodsList.getColumn("합계").setCellRenderer(celAlignCenter);
		
	}

	//선택 상품리스트를 표시합니다.
	public void setGoodsList(){
				
		dtm_goodsList.setRowCount(0);
		String order_idx = (String)dtm_orderList.getValueAt(table_orderList.getSelectedRow(), 1);		
		String query = "Select * "
						
				+ " From e_OrderGoods a Join e_OrderList b on a.order_idx=b.order_idx Where a.order_idx='"+order_idx+"' ";		
		
		ArrayList<HashMap<String, String>> orderList = ms_conn.connection(query);
		
		if(orderList.size() <= 0){
			JOptionPane.showMessageDialog(this, "주문된 상품이 없습니다. 삭제된 주문서인지 다시 확인해 주세요!!");
			return;
		}		
		
		HashMap<String, String> detail_map = orderList.get(0);
		
		label_detailtext_jday.setText(detail_map.get("reg_time")); //주문일시
		label_detailtext_jidx.setText(detail_map.get("order_idx"));  //주문번호
		label_detailtext_id.setText(detail_map.get("mem_id")); //주문아이디
		label_detailtext_jname.setText(detail_map.get("j_name")); //주문자
		label_detailtext_jtel.setText(detail_map.get("j_tel")); //주문번화번호
		label_detailtext_jhp.setText(detail_map.get("j_hp")); //주문핸드폰번호
		label_detailtext_comment.setText(detail_map.get("want_comment")); //주문요구사항
		label_detailtext_wantday.setText(detail_map.get("want_day")); //배송희망일
		label_detailtext_add1.setText(detail_map.get("add_column1")); //대체상품선택
		label_detailtext_add2.setText(detail_map.get("add_column2")); //결제유형
		label_detailtext_sname.setText(detail_map.get("s_name")); //수신자이름
		label_detailtext_jaddress.setText(detail_map.get("reg_time")); //수신자주소
		label_detailtext_stel.setText(detail_map.get("s_tel")); //수신자전화번호
		label_detailtext_shp.setText(detail_map.get("s_hp")); //수신자휴대폰
		label_detailtext_tomsg.setText(detail_map.get("to_msg")); //수신자전달메세지
		
		int total_count = 0; //총 주문 수량
		int price_total = 0; //총 단품 합계
		int total_price = 0; //총 결제 금액
		
		//String[] colunm_goods = { "순번", "주문번호", "상품이미지", "상품명", "바코드", "수량", "금액", "합계" };
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
			
			v.addElement(String.valueOf(i+1)); //순번
			v.addElement(map.get("order_idx")); //주문번호
			String image_path = "<Html><img src='"+map.get("goods_img")+"' border='0'></Html>";
			v.addElement(map.get("goods_img")); //상품이미지
			//v.addElement(image_path); //상품이미지
			v.addElement(map.get("user_code")); //상품바코드
			v.addElement(map.get("goods_name")); //상품명
			
			v.addElement(String.valueOf(count)); //수량
			v.addElement(String.format("%,d", sale_price)); //금액
			v.addElement(String.format("%,d", t_price)); //합계
			
			dtm_goodsList.addRow(v);
		}
		
		//합계 계산을 합니다.
		Vector<Object> v = new Vector<Object>();
		
		v.addElement(""); //순번
		v.addElement(""); //주문번호
		v.addElement(""); //상품이미지
		v.addElement(""); //상품바코드
		v.addElement("<Html><p style='color:red; font-weight:bold'>합계</p></Html>"); //상품명		
		
		v.addElement("<Html><p style='color:red; font-weight:bold'>"+String.valueOf(total_count)+"</p></Html>"); //수량
		v.addElement("<Html><p style='color:red; font-weight:bold'>"+String.format("%,d", price_total)+"</p></Html>"); //금액
		v.addElement("<Html><p style='color:red; font-weight:bold; font-face:맑은 고딕'>"+String.format("%,d", total_price)+"</p></Html>"); //합계
		
		dtm_goodsList.addRow(v);
		
	}
	
	//주문서를 검색합니다.
	public void startSearch(){
				
		//목록을 초기화 합니다.
		dtm_orderList.setRowCount(0);
		dtm_goodsList.setRowCount(0);
		setDetailReNew();
		
		String state_use = ""; //진행상태
		if(chkbox_top_state.isSelected()){
			state_use += "or State='0' ";			
		}
		
		if(chkbox_top_state_1.isSelected()){
			state_use += "or State='1' ";			
		}
		
		if(chkbox_top_state_2.isSelected()){
			state_use += "or State='2' ";			
		}
		
		if(chkbox_top_state_3.isSelected()){ //판매종료
			state_use += "or State='3' ";			
		}
		
		if(chkbox_top_state_4.isSelected()){
			state_use += "or State='12' ";			
		}
		
		if(chkbox_top_state_5.isSelected()){
			state_use += "or State='22' ";			
		}
		
		if(chkbox_top_state_6.isSelected()){
			state_use += "or State='40' ";			
		}
		
		/*
		$shopping_order_0 = 0; // 입금대기
		$shopping_order_1 = 0; // 결제완료
		$shopping_order_2 = 0; // 발송완료
		$shopping_order_3 = 0; // 판매종료
		$shopping_order_11 = 0; // 고객취소
		$shopping_order_12 = 0; // 거래취소
		$shopping_order_22 = 0; // 반품신청
		$shopping_order_40 = 0; // 반품승인
		*/
		
		if(text_top_daystart.getText().length() > 0){
			
		}
		
		String query = "Select * From e_OrderList Where 1=1 ";
		
		ArrayList<HashMap<String, String>> map = ms_conn.connection(query);
		
		// {0."순번", 1."주문상태", 2."회원ID", 3."주문자명", 4."휴대폰번호", 5."결제방식", 6."결제금액", 7."상품대체방식", 8."결제유형", 9."주문일"};
		if(map.size() > 0){
			for(int i =0; i < map.size(); i++){
				HashMap<String, String> temp = map.get(i);
				Vector<String> v = new Vector<String>();
				
				v.addElement(String.valueOf(i+1));
				v.addElement(temp.get("order_idx"));
				v.addElement(temp.get("state_subject"));
				v.addElement(temp.get("mem_id"));
				v.addElement(temp.get("j_name"));
				v.addElement(temp.get("j_hp"));
				v.addElement(temp.get("approval_type"));
				v.addElement(temp.get("app_price"));
				v.addElement(temp.get("add_column1"));
				v.addElement(temp.get("add_column2"));
				v.addElement(temp.get("reg_time"));				
				
				dtm_orderList.addRow(v);
			}
		}else{
			JOptionPane.showMessageDialog(this, "검색된 결과가 없습니다.");
		}		
	}
	
	//좌측 상세 보기를 새로고침
	private void setDetailReNew(){
		
		label_detailtext_jday.setText("");
		label_detailtext_jidx.setText("");
		label_detailtext_id.setText("");
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
		
		switch(e.getActionCommand()){
		case "검색":
			startSearch();
			break;
		}
		
	}
	
}
