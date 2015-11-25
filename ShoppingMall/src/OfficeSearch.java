
import java.awt.Dimension;
import java.awt.TextField;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;


public class OfficeSearch extends JFrame {

	/**
	 * [프레임 인식 시리얼]
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField text_officecode;
	private JTextField text_officename;
	private JTable table;
	
	DefaultTableModel dtm;
	Ms_Connect ms_connect;
	
	private JTextField text_code;
	private JTextField text_call_name;
	
	/**
	 * Create the frame.
	 */
	public OfficeSearch(JTextField text_code, JTextField text_name) {
		setTitle("\uAC70\uB798\uCC98\uAC80\uC0C9");
		
		/*
		 *[넘어온 값]
		 * 
		 */
		this.text_code = text_code;
		this.text_call_name = text_name;
				
		
		//프로그램 시작점
		init();
		
		searchQuery();		
	}

	
	private void init(){
				
		
		ms_connect = new Ms_Connect();
		ms_connect.setMainSetting();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 493, 568);
		setVisible(true);
		
		ImageIcon im = new ImageIcon(getClass().getClassLoader().getResource("Icon/officesearch_icon.png"));			
		setIconImage(im.getImage());
		
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
		
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		FlowLayout fl_contentPane = new FlowLayout(FlowLayout.CENTER, 5, 5);
		contentPane.setLayout(fl_contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel label_officecode = new JLabel("\uAC70\uB798\uCC98\uCF54\uB4DC");
		label_officecode.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_officecode);
		
		text_officecode = new JTextField();
		text_officecode.setHorizontalAlignment(SwingConstants.CENTER);
		
		panel.add(text_officecode);
		text_officecode.setColumns(5);
		text_officecode.setDocument(new JTextFieldLimit(5));
		text_officecode.addKeyListener(new KeyListener() {
			
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
								
				switch(e.getKeyCode()){				
				case KeyEvent.VK_ENTER:
					searchQuery();
					break;					
				}
			}
		});
		
		JLabel label_officename = new JLabel("\uAC70\uB798\uCC98\uBA85");
		label_officename.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_officename);
		
		text_officename = new JTextField();
		text_officename.addKeyListener(new KeyListener() {
			
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
								
				switch(e.getKeyCode()){				
				case KeyEvent.VK_ENTER:
					searchQuery();
					break;					
				}
			}
		});
		
		panel.add(text_officename);
		text_officename.setColumns(10);
		
		JButton btn_renew = new JButton("\uC9C0\uC6B0\uAE30");		
		panel.add(btn_renew);
		btn_renew.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub				
				renew();				
			}
		});
		
		JButton btn_officesearch = new JButton("\uAC80\uC0C9");
		panel.add(btn_officesearch);
		
		btn_officesearch.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub				
				searchQuery();
			}
		});
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		panel_1.add(scrollPane);
		
		/**
		 * 셀 간격 조정
		 */
		DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
		celAlignCenter.setHorizontalAlignment(JLabel.CENTER);
		DefaultTableCellRenderer celAlignRight = new DefaultTableCellRenderer();
		celAlignRight.setHorizontalAlignment(JLabel.RIGHT);
			
		
		String[] heard = {"순번", "거래처코드", "거래처명", "거래처구분"};
		
		dtm = new DefaultTableModel(null, heard){
			/**
			 * [칼럼 수정 못하게 막기]
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int rowIndex, int mColIndex){
				return false;
			}
		};
		
		
		table = new JTable(dtm);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//table.setCellSelectionEnabled(true);
		
		table.setRowHeight(25);
		scrollPane.setViewportView(table);
		
		table.getTableHeader().setReorderingAllowed(false);  //이동불가
		
		table.getColumn("순번").setPreferredWidth(40);
		table.getColumn("순번").setCellRenderer(celAlignCenter);
		
		table.getColumn("거래처명").setPreferredWidth(180);
		//table.getColumn("거래처명").setCellRenderer(celAlignCenter);
		
		table.getColumn("거래처코드").setPreferredWidth(40);
		table.getColumn("거래처코드").setCellRenderer(celAlignCenter);
		
		table.getColumn("거래처구분").setPreferredWidth(40);
		table.getColumn("거래처구분").setCellRenderer(celAlignCenter);
		
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
					setOfficecode();
					OfficeSearch.this.dispose();
				} // 더블클릭
				if (e.getButton() == 3) { 
					System.out.println("오른쪽 마우스 클릭 됐습니다.");
					
				} // 오른쪽 클릭
			}
		});
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2);
		
		JButton btn_close = new JButton("\uC885\uB8CC");
		panel_2.add(btn_close);
		
		btn_close.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				OfficeSearch.this.dispose();
			}
		});
			
		
	}

	private void setOfficecode(){
		
		// 테이블의 선택된 행의 인덱스 번호 취득
		int row = table.getSelectedRow();
		int col = table.getColumnCount();
		
		Vector<Object> temp = new Vector<Object>(); 
		
		for(int i =0; i < col; i++){			
			temp.add(dtm.getValueAt(row, i));			
		}
		
		System.out.println(temp.toString());
		
		text_code.setText(temp.get(1).toString()); //바코드
		text_call_name.setText(temp.get(2).toString());  //상품명
		
	}
	
	
	/*
	 * [새로입력]
	 * 
	 */
	private void renew(){
		text_officecode.setText("");
		text_officename.setText("");
		text_officecode.requestFocus();
	}
	
	
	private void searchQuery(){
		
		String query = "Select * From Office_manage Where Office_Use='1' ";
		
		if(text_officecode.getText().length() > 0 ){
			query += "And Office_Code Like '%"+text_officecode.getText()+"' ";
		}
		
		if(text_officename.getText().length() > 0 ){
			query += "And Office_Name Like '%"+text_officename.getText()+"%' ";
			
		}
		
		searchOffice(query);
	}
	
	/*
	 * [상품검색]
	 * 조건을 확인 해서 거래처 검색을 합니다.
	 * 거래처 검색 후 Vector 사용 하여 변경 합니다.
	 * dtm에 저장합니다.
	 * 결과 값으로 true를 반환 합니다.
	 * {"거래처코드", "거래처명", "거래처구분"};
	 */
	private boolean searchOffice(String query){
		
		ArrayList<HashMap<String, String>> al = ms_connect.connection(query);	
		
		try{
		if(al.size() > 0){
			dtm.setRowCount(0);
			for(int i=0; i < al.size(); i++ ){
				HashMap<String, String> map = new HashMap<String, String>();
				Vector<Object> v = new Vector<Object>();
				
				map = al.get(i);
								
				v.add(String.valueOf(i+1)); //0. 순번
				v.add(map.get("Office_Code")); //1. 코드
				v.add(map.get("Office_Name")); //2. 거래처명
								
				String office_sec = map.get("Office_Sec");
				System.out.println(office_sec);
				switch( Integer.parseInt(office_sec)){
				case 0:
					office_sec = "매출거래처";					
					break;
				case 1:
					office_sec = "매입거래처";
					break;
				case 2:
					office_sec = "수수료거래처";
					break;
				}
				
				v.add(office_sec); //3. 구분
								
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
	
	class JTextFieldLimit extends PlainDocument {
		   /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private int limit;
		   private boolean toUppercase = false;
		    JTextFieldLimit(int limit) {
		      super();
		      this.limit = limit;
		   }
		    JTextFieldLimit(int limit, boolean upper) {
		      super();
		      this.limit = limit;
		      this.toUppercase = upper;
		   }
		 
		   public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
		      if (str == null) {
		         return;
		      }
		      if ( (getLength() + str.length()) <= limit) {
		         if (toUppercase) {
		            str = str.toUpperCase();
		         }
		         super.insertString(offset, str, attr);
		      }
		   }
		}
	
}
