import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.UIManager;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;


public class ClassSearch extends JFrame implements ActionListener, MouseListener, KeyListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	
	private JTextField lcode;
	private JTextField lname;
	private JTextField mcode;
	private JTextField mname;
	private JTextField scode;
	private JTextField sname;
	private JTextField text_in;
	private JTable table_lcode;
	private JTable table_mcode;
	private JTable table_scode;
		
	private DefaultTableModel dtm_lcode;
	private DefaultTableModel dtm_mcode;
	private DefaultTableModel dtm_scode;
	
	private Ms_Connect ms_connect;
	private ArrayList<HashMap<String, String>> l_data;
	private ArrayList<HashMap<String, String>> m_data;
	private ArrayList<HashMap<String, String>> s_data;
	private ArrayList<HashMap<String, String>> al;
		
	/**
	 * Create the frame.
	 */
	public ClassSearch(Object[] param) {
			
		setTitle("\uBD84\uB958\uAC80\uC0C9");
		setBounds(100, 100, 766, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		ImageIcon im = new ImageIcon(getClass().getClassLoader().getResource("Icon/officesearch_icon.png"));			
		setIconImage(im.getImage());
		
		setContentPane(contentPane);
		
		contentPane.setLayout(new BorderLayout(0, 0));
		
		ms_connect = new Ms_Connect();
		ms_connect.setMainSetting();
		
		try {
		    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) { 
		    System.err.println("Cannot set look and feel:" + e.getMessage ()); 
		}
        
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension dm = tk.getScreenSize();
        Dimension fm = this.getSize();
        this.setLocation((int) (dm.getWidth() / 2 - fm.getWidth() / 2),
                (int) (dm.getHeight() / 2 - fm.getHeight() / 2));		
				
		//시작점
		init();
				
		lcode = (JTextField)param[0];
		lname = (JTextField)param[1];
		mcode = (JTextField)param[2];
		mname = (JTextField)param[3];
		scode = (JTextField)param[4];
		sname = (JTextField)param[5];
		
		searchClass();
		
	}
		
	private void init(){
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		JLabel label_sclass = new JLabel("\uC18C\uBD84\uB958\uBA85 \uAC80\uC0C9");
		label_sclass.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_sclass);
		
		text_in = new JTextField();
		panel.add(text_in);
		text_in.setColumns(20);
		text_in.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				text_in.selectAll();				
			}
		});
		text_in.selectAll();
		text_in.addKeyListener(this);
		
		JButton btn_search = new JButton("\uAC80\uC0C9");
		panel.add(btn_search);
		btn_search.addActionListener(this);
		
		JButton btn_renew = new JButton("지우기");
		panel.add(btn_renew);
		btn_search.addActionListener(this);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		JButton btn_close = new JButton("\uC885\uB8CC");
		btn_close.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		panel_1.add(btn_close);
		btn_close.addActionListener(this);
		
		JPanel panel_3 = new JPanel();
		contentPane.add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(null);
		
		JScrollPane scrollPane_lcode = new JScrollPane();
		scrollPane_lcode.setBounds(12, 10, 230, 400);
		panel_3.add(scrollPane_lcode);
				
		//대분류 목록	
		lclass();		
		
		scrollPane_lcode.setViewportView(table_lcode);		
		
		//중분류 목록
		JScrollPane scrollPane_mcode = new JScrollPane();
		scrollPane_mcode.setBounds(254, 10, 230, 400);
		panel_3.add(scrollPane_mcode);
		
		mclass();			
		
		scrollPane_mcode.setViewportView(table_mcode);		
		
		//소준류 목록
		JScrollPane scrollPane_scode = new JScrollPane();
		scrollPane_scode.setBounds(496, 10, 230, 400);
		panel_3.add(scrollPane_scode);
				
		sclass();
		
		scrollPane_scode.setViewportView(table_scode);
				
		setVisible(true);		
	}
	
	/*
	 * [대분류 목록]
	 * 
	 */
	private void lclass(){
		
		/**
		 * 셀 간격 조정
		 */
		DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
		celAlignCenter.setHorizontalAlignment(JLabel.CENTER);
		DefaultTableCellRenderer celAlignRight = new DefaultTableCellRenderer();
		celAlignRight.setHorizontalAlignment(JLabel.RIGHT);
		
		String[] heard = {"코드", "대분류명"};
		
		dtm_lcode = new DefaultTableModel(null, heard){
			/**
			 * [칼럼 수정 못하게 막기]
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int rowIndex, int mColIndex){
				return false;
			}
		};
				
		table_lcode = new JTable(dtm_lcode);
		table_lcode.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//table.setCellSelectionEnabled(true);
		
		table_lcode.setRowHeight(25);		
		
		table_lcode.getTableHeader().setReorderingAllowed(false);  //이동불가
		
		table_lcode.getColumn("코드").setPreferredWidth(40);
		table_lcode.getColumn("코드").setCellRenderer(celAlignCenter);
		
		table_lcode.getColumn("대분류명").setPreferredWidth(150);
				
		table_lcode.setName("l_class");
		table_lcode.addMouseListener(this);
	}
	
	/*
	 * [중분류 목록]
	 * 
	 */
	private void mclass(){
		
		/**
		 * 셀 간격 조정
		 */
		DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
		celAlignCenter.setHorizontalAlignment(JLabel.CENTER);
		DefaultTableCellRenderer celAlignRight = new DefaultTableCellRenderer();
		celAlignRight.setHorizontalAlignment(JLabel.RIGHT);
			
		
		String[] heard = {"대코드", "대분류명", "중코드", "중분류명"};
		
		dtm_mcode = new DefaultTableModel(null, heard){
			/**
			 * [칼럼 수정 못하게 막기]
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int rowIndex, int mColIndex){
				return false;
			}
		};
				
		table_mcode = new JTable(dtm_mcode);
		table_mcode.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//table.setCellSelectionEnabled(true);
		
		table_mcode.setRowHeight(25);
		
		table_mcode.getTableHeader().setReorderingAllowed(false);  //이동불가
		
		table_mcode.getColumn("대코드").setWidth(0);
		table_mcode.getColumn("대코드").setMinWidth(0);
		table_mcode.getColumn("대코드").setMaxWidth(0);		
		table_mcode.getColumnModel().getColumn(0).setResizable(false); //대코드
		
		table_mcode.getColumn("대분류명").setWidth(0);
		table_mcode.getColumn("대분류명").setMinWidth(0);
		table_mcode.getColumn("대분류명").setMaxWidth(0);		
		table_mcode.getColumnModel().getColumn(1).setResizable(false); //대코드
		
		
		table_mcode.getColumn("중코드").setPreferredWidth(40);
		table_mcode.getColumn("중코드").setCellRenderer(celAlignCenter);
		table_mcode.getColumn("중분류명").setPreferredWidth(150);
		table_mcode.setName("m_class");
		table_mcode.addMouseListener(this);
		
	}
	
	
	/*
	 * [소분류 목록]
	 * 
	 */
	private void sclass(){
		/**
		 * 셀 간격 조정
		 */
		DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
		celAlignCenter.setHorizontalAlignment(JLabel.CENTER);
		DefaultTableCellRenderer celAlignRight = new DefaultTableCellRenderer();
		celAlignRight.setHorizontalAlignment(JLabel.RIGHT);
		
		String[] heard = {"대코드", "대분류명", "중코드", "중분류명", "소코드", "소분류명"};
		
		dtm_scode = new DefaultTableModel(null, heard){
			/**
			 * [칼럼 수정 못하게 막기]
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int rowIndex, int mColIndex){
				return false;
			}
		};
				
		table_scode = new JTable(dtm_scode);
		table_scode.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//table.setCellSelectionEnabled(true);
		
		table_scode.setRowHeight(25);		
		
		table_scode.getTableHeader().setReorderingAllowed(false);  //이동불가
		
		table_scode.getColumn("대코드").setWidth(0);
		table_scode.getColumn("대코드").setMinWidth(0);
		table_scode.getColumn("대코드").setMaxWidth(0);		
		table_scode.getColumnModel().getColumn(0).setResizable(false); //대코드
		
		table_scode.getColumn("대분류명").setWidth(0);
		table_scode.getColumn("대분류명").setMinWidth(0);
		table_scode.getColumn("대분류명").setMaxWidth(0);		
		table_scode.getColumnModel().getColumn(1).setResizable(false); //대코드
		
		table_scode.getColumn("중코드").setWidth(0);
		table_scode.getColumn("중코드").setMinWidth(0);
		table_scode.getColumn("중코드").setMaxWidth(0);		
		table_scode.getColumnModel().getColumn(2).setResizable(false); //대코드
		
		table_scode.getColumn("중분류명").setWidth(0);
		table_scode.getColumn("중분류명").setMinWidth(0);
		table_scode.getColumn("중분류명").setMaxWidth(0);		
		table_scode.getColumnModel().getColumn(3).setResizable(false); //대코드
		
		table_scode.getColumn("소코드").setPreferredWidth(40);
		table_scode.getColumn("소코드").setCellRenderer(celAlignCenter);
		table_scode.getColumn("소분류명").setPreferredWidth(150);
		table_scode.addMouseListener(this);
		table_scode.setName("s_class");
	}

	
	/*
	 * [분류검색]
	 * 조건을 확인 해서 분류 검색을 합니다.
	 * 분류 검색 후 Vector 사용 하여 변경 합니다.
	 * dtm에 저장합니다.
	 * 결과 값으로 true를 반환 합니다.
	 * {"코드", "분류명"};
	 */
	private void searchClass(){
				
		String l_query = "Select * From L_Branch Where L_Code <> 'AA'  Order by L_Code";
		String m_query = "Select * From M_Branch Where L_Code <> 'AA' Order by M_CODE";
		String s_query = "Select * From S_Branch Where L_Code <> 'AA'  Order by S_CODE";
		
		l_data = ms_connect.connection(l_query);
		m_data = ms_connect.connection(m_query);
		s_data = ms_connect.connection(s_query);
		
		listClass_Lcode();
			
	}
	
	//대분류 목록
	private void listClass_Lcode(){
		
		try{
			if(l_data.size() > 0){
				
				dtm_lcode.setRowCount(0);
				dtm_mcode.setRowCount(0);
				dtm_scode.setRowCount(0);
				
				table_lcode.setEnabled(true);
				table_mcode.setEnabled(true);
				table_scode.setEnabled(true);
				
				for(int i=0; i < l_data.size(); i++ ){
					HashMap<String, String> map = new HashMap<String, String>();
					Vector<Object> v = new Vector<Object>();
					
					map = l_data.get(i);
					
					v.add(map.get("L_Code")); //1. 코드
					v.add(map.get("L_Name")); //2. 거래처명
										
					dtm_lcode.addRow(v);				
				}
				
				table_lcode.setSelectionMode(0);
				
			}else{
				dtm_lcode.setRowCount(0);
				JOptionPane.showMessageDialog(null, "검색 결과가 없습니다. ");
				return;
			}				
		}catch(NullPointerException e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "DB 접속에 실패 했습니다. \r\n인터넷 회선 및 서버를 점검해 주세요!!");
			return;
		}		
		
		
		listClass_Mcode(table_lcode.getValueAt(0, 0).toString());
		
	}

	//중분류 목록
	private void listClass_Mcode(String l_code){
	
		try{
			if(m_data.size() > 0){
				dtm_mcode.setRowCount(0);
				for(int i=0; i < m_data.size(); i++ ){
					HashMap<String, String> map = new HashMap<String, String>();
					Vector<Object> v = new Vector<Object>();
					
					map = m_data.get(i);
					
					if(l_code.equals(map.get("L_Code"))){
						v.add(map.get("L_Code"));
						v.add(map.get("L_Name")); //2. 거래처명
						v.add(map.get("M_Code")); //1. 코드
						v.add(map.get("M_Name")); //2. 거래처명
						dtm_mcode.addRow(v);		
					}
					
							
				}
			}else{
				dtm_mcode.setRowCount(0);
				JOptionPane.showMessageDialog(null, "검색 결과가 없습니다. ");
				return;
			}		
			
		}catch(NullPointerException e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "DB 접속에 실패 했습니다. \r\n인터넷 회선 및 서버를 점검해 주세요!!");
			return;
		}
	
		listClass_Scode(table_mcode.getValueAt(0, 0).toString(), table_mcode.getValueAt(0, 2).toString());
	}
	
	//소분류 목록
	private void listClass_Scode(String l_code, String m_code){
		
		try{
			
			if(s_data.size() > 0){
				dtm_scode.setRowCount(0);
				for(int i=0; i < s_data.size(); i++ ){
					HashMap<String, String> map = new HashMap<String, String>();
					Vector<Object> v = new Vector<Object>();
					
					map = s_data.get(i);
					
					if(l_code.equals(map.get("L_Code")) && m_code.equals(map.get("M_Code"))){
						v.add(map.get("L_Code"));
						v.add(map.get("L_Name")); //2. 거래처명
						v.add(map.get("M_Code")); //1. 코드
						v.add(map.get("M_Name")); //2. 거래처명
						v.add(map.get("S_Code")); //1. 코드
						v.add(map.get("S_Name")); //2. 거래처명
						dtm_scode.addRow(v);		
					}
				}
				
			}else{
				dtm_scode.setRowCount(0);
				JOptionPane.showMessageDialog(null, "검색 결과가 없습니다. ");
				return;
			}				
		}catch(NullPointerException e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "DB 접속에 실패 했습니다. \r\n인터넷 회선 및 서버를 점검해 주세요!!");
			return;
		}
		
		
	}
	
	
	//소분류 선택
	private void scode_search_list(){
		
		HashMap<String, String> map = al.get(table_scode.getSelectedRow());
		
		lcode.setText(map.get("L_Code"));				
		lname.setText(map.get("L_Name"));
		mcode.setText(map.get("M_Code"));
		mname.setText(map.get("M_Name"));
		scode.setText(map.get("S_Code"));
		sname.setText(map.get("S_Name"));
	}
	
	
	private boolean searchClass(String s_name){
		
		String query = "Select L_Code, L_Name, M_Code, M_Name, S_Code, S_Name, Profit_Rate "
				+ "From S_Branch Where S_Name Like '%"+s_name+"%'  Order by L_Code,M_Code,S_Code";
		
		al = ms_connect.connection(query);	
		
		try{
			System.out.println(al.size());
		if(al.size() > 0){
			
			clearDTM();
			
			for(int i=0; i < al.size(); i++ ){
				
				HashMap<String, String> map = new HashMap<String, String>();
				Vector<Object> vl = new Vector<Object>();
				Vector<Object> vm = new Vector<Object>();
				Vector<Object> vs = new Vector<Object>();
				
				map = al.get(i);
				
				vl.add(map.get("L_Code")); //1. 코드
				vl.add(map.get("L_Name")); //2. 대분류명			
									
				dtm_lcode.addRow(vl);			
				table_lcode.setEnabled(false);	
				
				vm.add(map.get("L_Code"));
				vm.add(map.get("L_Name")); //2. 대분류명
				vm.add(map.get("M_Code"));//3. 중코드
				vm.add(map.get("M_Name"));//4. 중분류명
				
				dtm_mcode.addRow(vm);
				table_mcode.setEnabled(false);	
								
				vs.add(map.get("L_Code"));
				vs.add(map.get("L_Name")); //2. 대분류명
				vs.add(map.get("M_Code"));//3. 중코드
				vs.add(map.get("M_Name"));//4. 중분류명
				vs.add(map.get("S_Code"));//5. 소코드
				vs.add(map.get("S_Name"));//6.소분류명
				
				dtm_scode.addRow(vs);
				
			}
		}else{			
			clearDTM();			
			JOptionPane.showMessageDialog(ClassSearch.this, "검색 결과가 없습니다. ");			
		}		
		
		}catch(NullPointerException e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "DB 접속에 실패 했습니다. \r\n인터넷 회선 및 서버를 점검해 주세요!!");
			return false;
		}
		
		return true;
	}
	
	//테이블초기화
	private void clearDTM(){
		
		dtm_lcode.setRowCount(0);
		dtm_mcode.setRowCount(0);
		dtm_scode.setRowCount(0);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String getAction = e.getActionCommand();
		
		if(getAction.equals("검색")){
			
			if(text_in.getText().length() > 0){
				searchClass(text_in.getText());				
			}else{
				listClass_Lcode();
			}
			
		}
		
		if(getAction.equals("지우기")){
			System.out.println("jjjjjj");
			text_in.setText("");
		}
		
		if(getAction.equals("종료")){			
			
			this.dispose();
		}
		
	}

	public boolean leftClick = false;
	public int clickCount = 0;
	public boolean doubleClick = false;
	public Timer timer;
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
				
		String click_name = e.getComponent().getName();
		System.out.println(click_name);
				
		if(e.getButton() == 1){
			switch(click_name){
			
			case "l_class":
				
				if(text_in.getText().length() > 0){
					return;
				}
				//searchClass();
				
				dtm_mcode.setRowCount(0);
				dtm_scode.setRowCount(0);
				
				// 테이블의 선택된 행의 인덱스 번호 취득
				int row = table_lcode.getSelectedRow();
				int col = table_lcode.getColumnCount();
				
				System.out.println(row);
				Vector<Object> temp = new Vector<Object>(); 
				
				for(int i =0; i < col; i++){			
					temp.add(dtm_lcode.getValueAt(row, i));			
				}
				
				//대분류 선택시 중분류및 소분류를 선택합니다.
				listClass_Mcode(temp.get(0).toString());				
				break;
				
			case "m_class":
				
				if(text_in.getText().length() > 0){
					return;
				}
				
				dtm_scode.setRowCount(0);
								
				// 테이블의 선택된 행의 인덱스 번호 취득
				int row1 = table_mcode.getSelectedRow();				
				int col1 = table_mcode.getColumnCount();
				
				System.out.println(row1);
				
				Vector<Object> temp1 = new Vector<Object>(); 
				
				for(int i =0; i < col1; i++){			
					temp1.add(dtm_mcode.getValueAt(row1, i));			
				}
				
				//중분류 선택시 소분류를 표시 합니다.
				listClass_Scode(temp1.get(0).toString(), temp1.get(2).toString());
				break;
				}
			
		}		
		
		if(e.getClickCount() ==2){
			switch(click_name){
			case "l_class":
				lcode.setText(dtm_lcode.getValueAt(table_lcode.getSelectedRow(), 0).toString());				
				lname.setText(dtm_lcode.getValueAt(table_lcode.getSelectedRow(), 1).toString());
				ClassSearch.this.dispose();
				break;
				
			case "m_class":
				lcode.setText(dtm_mcode.getValueAt(table_mcode.getSelectedRow(), 0).toString());				
				lname.setText(dtm_mcode.getValueAt(table_mcode.getSelectedRow(), 1).toString());
				mcode.setText(dtm_mcode.getValueAt(table_mcode.getSelectedRow(), 2).toString());
				mname.setText(dtm_mcode.getValueAt(table_mcode.getSelectedRow(), 3).toString());
				ClassSearch.this.dispose();
				break;
				
			case "s_class":
				
				if(text_in.getText().length() > 0){		            					
					scode_search_list();		            					
				}else{						            				
					lcode.setText(dtm_scode.getValueAt(table_scode.getSelectedRow(), 0).toString());				
					lname.setText(dtm_scode.getValueAt(table_scode.getSelectedRow(), 1).toString());
					mcode.setText(dtm_scode.getValueAt(table_scode.getSelectedRow(), 2).toString());
					mname.setText(dtm_scode.getValueAt(table_scode.getSelectedRow(), 3).toString());
					scode.setText(dtm_scode.getValueAt(table_scode.getSelectedRow(), 4).toString());
					sname.setText(dtm_scode.getValueAt(table_scode.getSelectedRow(), 5).toString());					
				}
				
				ClassSearch.this.dispose();
				break;
			}
			
		}
		
		
		/*if (e.getButton()==MouseEvent.BUTTON1){
		    leftClick = true; clickCount = 0;
		    if(e.getClickCount() == 2) doubleClick=true;
		    Integer timerinterval = (Integer)Toolkit.getDefaultToolkit().getDesktopProperty("awt.multiClickInterval");

		               timer = new Timer(timerinterval, new ActionListener() {
		                public void actionPerformed(ActionEvent evt) {  
		                    if(doubleClick){
		                        //System.out.println("double click.");
		                      
		                        switch(click_name){
		            			case "l_class":
		            				lcode.setText(dtm_lcode.getValueAt(table_lcode.getSelectedRow(), 0).toString());				
		            				lname.setText(dtm_lcode.getValueAt(table_lcode.getSelectedRow(), 1).toString());
		            				ClassSearch.this.dispose();
		            				break;
		            				
		            			case "m_class":
		            				lcode.setText(dtm_lcode.getValueAt(table_lcode.getSelectedRow(), 0).toString());				
		            				lname.setText(dtm_lcode.getValueAt(table_lcode.getSelectedRow(), 1).toString());
		            				mcode.setText(dtm_mcode.getValueAt(table_mcode.getSelectedRow(), 2).toString());
		            				mname.setText(dtm_mcode.getValueAt(table_mcode.getSelectedRow(), 3).toString());
		            				ClassSearch.this.dispose();
		            				break;
		            				
		            			case "s_class":
		            				
		            				if(text_in.getText().length() > 0){		            					
		            					scode_search_list();		            					
		            				}else{
		            						            				
		            				lcode.setText(dtm_lcode.getValueAt(table_lcode.getSelectedRow(), 0).toString());				
		            				lname.setText(dtm_lcode.getValueAt(table_lcode.getSelectedRow(), 1).toString());
		            				mcode.setText(dtm_mcode.getValueAt(table_mcode.getSelectedRow(), 2).toString());
		            				mname.setText(dtm_mcode.getValueAt(table_mcode.getSelectedRow(), 3).toString());
		            				scode.setText(dtm_scode.getValueAt(table_scode.getSelectedRow(), 4).toString());
		            				sname.setText(dtm_scode.getValueAt(table_scode.getSelectedRow(), 5).toString());
		            					
		            				}
		            				
		            				ClassSearch.this.dispose();
		            				break;
		            			}
		                        
		                        clickCount++;
		                        if(clickCount == 2){                               
		                            clickCount=0;
		                            doubleClick = false;
		                        }

		                    } else {
		                    	
		                    	if(text_in.getText().length() > 0){
		            				return;
		            			}
		                    	
		                        //System.out.println("single click."); 
		                        
		                        switch(click_name){
		            			
		            			case "l_class":
		            				
		            				dtm_mcode.setRowCount(0);
		            				dtm_scode.setRowCount(0);
		            				
		            				// 테이블의 선택된 행의 인덱스 번호 취득
		            				int row = table_lcode.getSelectedRow();
		            				int col = table_lcode.getColumnCount();
		            				
		            				Vector<Object> temp = new Vector<Object>(); 
		            				
		            				for(int i =0; i < col; i++){			
		            					temp.add(dtm_lcode.getValueAt(row, i));			
		            				}
		            				
		            				listClass_Mcode(temp.get(0).toString());
		            				break;
		            				
		            			case "m_class":
		            				
		            				dtm_scode.setRowCount(0);
		            				
		            				if(table_lcode.getSelectedRow() == -1) {
		            					JOptionPane.showMessageDialog(ClassSearch.this, "대분류를 먼저 선택해 주세요!!");
		            					return;
		            				}
		            				
		            				// 테이블의 선택된 행의 인덱스 번호 취득
		            				int row1 = table_mcode.getSelectedRow();				
		            				int col1 = table_mcode.getColumnCount();
		            				
		            				Vector<Object> temp1 = new Vector<Object>(); 
		            				
		            				for(int i =0; i < col1; i++){			
		            					temp1.add(dtm_mcode.getValueAt(row1, i));			
		            				}
		            				
		            				listClass_Scode(dtm_lcode.getValueAt(table_lcode.getSelectedRow(), 0).toString(), temp1.get(2).toString());
		            				break;
		            				}
		                        
		                    }
		                }               
		            });
		            timer.setRepeats(false);
		            timer.start();
		            if(e.getID()==MouseEvent.MOUSE_RELEASED) timer.stop();
		}*/
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		switch(arg0.getKeyCode()){
		case KeyEvent.VK_ENTER:
				if(text_in.getText().length() > 0){
					searchClass(text_in.getText());
				}else{
					searchClass();
				}
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
