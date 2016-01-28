import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import java.awt.GridLayout;
import javax.swing.JLabel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.GridBagLayout;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import java.awt.SystemColor;
import javax.swing.ListSelectionModel;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.Vector;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JScrollBar;
import java.awt.Component;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.JCheckBox;


public class MainFrame extends JFrame {

	private JPanel contentPane;
	
	public static HashMap<String, String> map;
	
	// ***** 1. 미리보기 폴더 사용될 컨테이너 선언 *****
	
	private JTable table;	
	private DefaultTableModel dtm1;
	
	// ***** 2. 포스데이타 열설정 *****
	private JTable table_1;
	private DefaultTableModel dtm; 
	
	// ***** 3. 편집페이지창 설정 *****	
	private JTable table_2;
	private DefaultTableModel dtm2;
	
	// ***** 4. 인쇄페이지창 설정 *****	
	private JTable table_3;
	private DefaultTableModel dtm3;
	
	// ***** 5. 지정파일 폴더경로 *****
	private JButton button_9;
	private JFileChooser chooser;
	private JTextField textField;	
	private JTextField textField_9;
	
	
	// ***** 6. 제단선 중앙정렬 선택 *****
	private JCheckBox chckbxNewCheckBox;
	private JCheckBox chckbxNewCheckBox_1;
	
	// ***** 7. 다이얼 로그 띄우기 *****
	private JDialog dlg_help_info;
	
	public Properties config_file;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {		
				
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();					
					frame.setVisible(true);
					frame.doit();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * [메인프레임 생성자]
	 * 수정할 그룹생성
	 * 메인프레임 생성
	 */
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("TIPS_POP 환경설정 ver 1.0");
		
		// 현재환면의 사이즈와 모니터 사이즈를 불러오기 우해 선언
		Dimension dim = new Dimension();
		Dimension dim1 = new Dimension();
		int xpos, ypos;
		
		// 메인프레임 사이즈 선언
		setSize(1024, 768);
		
		// 미리보기폴더에서 사용할 map선언
		map = new HashMap<String, String>();
		
		// 현재 모니터화면 사이즈 가져오기
		dim = Toolkit.getDefaultToolkit().getScreenSize();
		
		// contentPane 선언 및 전체 틀선언
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		// 현재 프레임 사이즈 가져오기
		dim1 = getSize();
		
		// 화면을 중앙에 위치 시킴
		xpos = (int)(dim.getWidth()/2-dim1.getWidth()/2);
		ypos = (int)(dim.getHeight()/2-dim1.getHeight()/2);
		setLocation(xpos, ypos);
		
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(2, 3, 5, 5));
		
		
		/**  [ 미리보기 폴더 작성]
		 *  레이블 생성
		 *  DefaultTableModel 에 컬럼 추가
		 * 
		 * */
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		//레이블 선언
		JLabel label = new JLabel("미리보기 폴더 설정", JLabel.CENTER);
		
		label.setBackground(new Color(175, 238, 238));
		label.setBounds(12, 10, 305, 23);
		
		//패널에 레이블 붙임
		panel_1.add(label);
				
		// 컬럼명
		String[] column1 = {"폴더명"};
		dtm1 = new DefaultTableModel(column1, 0){
			public boolean isCellEditable(int row, int column){
				return true;
			}
		};
		
		// 스크롤바 생성
		JScrollPane scrollPane_1 = new JScrollPane(table_1);
		scrollPane_1.setBounds(12, 43, 305, 243);
		
		// 패널에 추가
		panel_1.add(scrollPane_1);
		
		
		//dtm1.addRow(new String[] {"바코드"});
		table_1 = new JTable(dtm1);
		scrollPane_1.setViewportView(table_1);
		//table_1.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		//table_1.setAutoCreateRowSorter(true);
		//table_1.setCellSelectionEnabled(rootPaneCheckingEnabled);
		
		table_1.setSelectionMode(0);
		//table.setBounds(12, 70, 305, 243);
		//panel_2.add(table);		
				
		//table_1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		// 컬럼길이 수정
		table_1.getColumnModel().getColumn(0).setPreferredWidth(300);
				
		
		
		// 패널에 추가버튼 붙이기		
		JButton btnNewButton = new JButton("추가");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dtm1.addRow(new Object[] {});
			}
		});
		btnNewButton.setBounds(111, 296, 97, 51);
		panel_1.add(btnNewButton);
		
		// 패널에 삭제버튼 붙이기
		JButton btnNewButton_1 = new JButton("삭제");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dtm1.removeRow(table_1.getSelectedRow());
			}
		});
		btnNewButton_1.setBounds(220, 296, 97, 51);
		panel_1.add(btnNewButton_1);
		
		
		
		/*** [ 포스데이터창 열설정 ]
		 * 포스데이터창 열수정 작성 
		 * 
		 * 
		 * */
		
		//포스데이터창 패널생성
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		//메인패널에 연결
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		//레이블 생성
		JLabel label_1 = new JLabel("포스데이타창 열설정", SwingConstants.CENTER);
		label_1.setBounds(12, 10, 305, 23);
		label_1.setBackground(new Color(175, 238, 238));
		//상위 패널에 연결
		panel_2.add(label_1);
		
		// 컬럼 설정
		String[] column = {"필드명", "길이", "콤마사용"};
		dtm = new DefaultTableModel(column, 0){
			public boolean isCellEditable(int row, int column){
				return true;
			}
		};
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(12, 43, 305, 243);		
		panel_2.add(scrollPane);
		
		//테스트 데이타 필드
		//dtm.addRow(new String[] {"바코드", "90", "false"});
		
		//JTable 생성
		table = new JTable(dtm);
		table.setSelectionMode(0);
		scrollPane.setViewportView(table);
		
		// 포스데이터창 삭제 버튼
		JButton button_1 = new JButton("삭제");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dtm.removeRow(table.getSelectedRow());
			}
		});
		button_1.setBounds(220, 296, 97, 51);
		panel_2.add(button_1);
		
		// 포스데이터창 추가 버튼
		JButton button_2 = new JButton("추가");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dtm.addRow(new Object[] {});
			}
		});
		button_2.setBounds(111, 296, 97, 51);
		panel_2.add(button_2);
		
		// 컬럼 길이 설정
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		
				
		
		/**  [ 편집페이지 설정 ]
		 *  레이블 생성
		 *  DefaultTableModel 에 컬럼 추가
		 * 
		 * */	
		
		//패널생성
		JPanel panel_6 = new JPanel();
		//패널테두리설정
		panel_6.setBorder(new LineBorder(new Color(0, 0, 0)));
		//패널레이아웃설정
		panel_6.setLayout(null);
		//메인페인에 연결
		contentPane.add(panel_6);
		
		//레이블생성
		JLabel label_3 = new JLabel("편집페이지 설정", SwingConstants.CENTER);
		//레이블 색상설정
		label_3.setBackground(new Color(175, 238, 238));
		//레이블 위치설정
		label_3.setBounds(12, 10, 305, 23);
		//상위 패널에 연결
		panel_6.add(label_3);
		
		//스크롤페인 생성
		JScrollPane scrollPane_3 = new JScrollPane((Component) null);
		//스크롤페인 위치 설정
		scrollPane_3.setBounds(12, 43, 305, 243);
		//상위 패널에 연결
		panel_6.add(scrollPane_3);
		//컬럼명 입력
		String[] column2 = {"페이지명", "가로", "세로"};
		dtm2 = new DefaultTableModel(column2, 0){
			public boolean isCellEditable(int row, int column){
				return true;
			}
		};
		//JTable 생성		
		table_2 = new JTable(dtm2);
		table_2.setSelectionMode(0);
		//스크롤페인에 연결
		scrollPane_3.setViewportView(table_2);
		
		//삭제버튼 생성
		JButton button_7 = new JButton("삭제");
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dtm2.removeRow(table_2.getSelectedRow());
			}
		});
		button_7.setBounds(220, 296, 97, 51);
		panel_6.add(button_7);
		
		JButton button_8 = new JButton("추가");
		button_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dtm2.addRow(new Object[] {});
			}
		});
		button_8.setBounds(111, 296, 97, 51);
		panel_6.add(button_8);
		
		//컬럼 넓이 조정
		table_2.getColumnModel().getColumn(0).setPreferredWidth(100);
		table_2.getColumnModel().getColumn(1).setPreferredWidth(100);
		table_2.getColumnModel().getColumn(2).setPreferredWidth(100);		
		
		
		
		/**  [ 인쇄페이지 설정 ]
		 *  레이블 생성
		 *  DefaultTableModel 에 컬럼 추가
		 * 
		 * */
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_3.setLayout(null);
		contentPane.add(panel_3);
		
		JLabel label_2 = new JLabel("인쇄페이지 설정", SwingConstants.CENTER);
		label_2.setBackground(new Color(175, 238, 238));
		label_2.setBounds(12, 10, 305, 23);
		panel_3.add(label_2);
		
		JScrollPane scrollPane_2 = new JScrollPane((Component) null);
		scrollPane_2.setBounds(12, 43, 305, 243);
		panel_3.add(scrollPane_2);
		
		String[] column3 = {"페이지명", "가로", "세로"};
		dtm3 = new DefaultTableModel(column3, 0){
			public boolean isCellEditable(int row, int column){
				return true;
			}
		};
		
		table_3 = new JTable(dtm3);
		table_3.setSelectionMode(0);
		scrollPane_2.setViewportView(table_3);
		
		JButton button_4 = new JButton("삭제");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dtm3.removeRow(table_3.getSelectedRow());
			}
		});
		button_4.setBounds(220, 296, 97, 51);
		panel_3.add(button_4);
		
		JButton button_5 = new JButton("추가");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dtm3.addRow(new Object[] {});				
			}
		});
		button_5.setBounds(111, 296, 97, 51);
		panel_3.add(button_5);
				
		table_3.getColumnModel().getColumn(0).setPreferredWidth(100);
		table_3.getColumnModel().getColumn(1).setPreferredWidth(100);
		table_3.getColumnModel().getColumn(2).setPreferredWidth(100);		
		
		
		
		/**  [ 환경 설정 ]
		 *  레이블 생성
		 *  DefaultTableModel 에 컬럼 추가
		 * 
		 * */	
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPane.add(panel_4);
		panel_4.setLayout(null);
		
		JLabel label_4 = new JLabel("환경설정", SwingConstants.CENTER);
		label_4.setBounds(12, 5, 305, 31);
		label_4.setBackground(new Color(175, 238, 238));
		panel_4.add(label_4);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\uD3EC\uC2A4\uB370\uC774\uD0C0\uD638\uCD9C\uACBD\uB85C", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(128, 128, 128)));
		panel_5.setBounds(12, 46, 305, 89);
		panel_4.add(panel_5);
		panel_5.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(62, 20, 231, 21);
		panel_5.add(textField);
		textField.setColumns(10);
		
		
		/*지정파일 폴더 경로*/
		
		button_9 = new JButton("폴더변경");
		chooser = new JFileChooser();		
		button_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				File file = new File(textField.getText());
				chooser.setCurrentDirectory(file);
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int result = chooser.showOpenDialog(null);
				
				if(result == JFileChooser.APPROVE_OPTION){
					textField.setText(chooser.getSelectedFile().toString());
				}
			}
		});
		button_9.setBounds(212, 51, 81, 23);
		panel_5.add(button_9);
		
		textField_9 = new JTextField();
		textField_9.setBounds(62, 52, 138, 21);
		panel_5.add(textField_9);
		textField_9.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("파일명");
		lblNewLabel_1.setBounds(12, 55, 36, 15);
		panel_5.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("경  로");
		lblNewLabel_2.setBounds(12, 23, 36, 15);
		panel_5.add(lblNewLabel_2);
		
		
		/*인쇄설정 여백 및 제단선	*/
		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new TitledBorder(null, "\uC778\uC1C4\uC124\uC815", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(128, 128, 128)));
		panel_7.setBounds(12, 158, 305, 71);
		panel_4.add(panel_7);
		panel_7.setLayout(null);
		
		chckbxNewCheckBox = new JCheckBox("중앙정렬");
		chckbxNewCheckBox.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxNewCheckBox.setBounds(8, 27, 144, 23);
		panel_7.add(chckbxNewCheckBox);
		
		chckbxNewCheckBox_1 = new JCheckBox("제단선");
		chckbxNewCheckBox_1.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxNewCheckBox_1.setBounds(156, 27, 141, 23);
		panel_7.add(chckbxNewCheckBox_1);
		
		JPanel panel = new JPanel();
		contentPane.add(panel);		
		panel.setLayout(null);
		
		JButton button_10 = new JButton("닫기");
		button_10.setBounds(220, 295, 97, 52);
		panel.add(button_10);
		
		JButton btnNewButton_2 = new JButton("저장");
		btnNewButton_2.setBounds(111, 295, 97, 52);
		panel.add(btnNewButton_2);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//데이타 저장합니다.
				System.out.println("데이타 저장합니다.");
				inputDate();				
			}
		});
		button_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 System.exit(0);
			}
		});
	
	}
	
	
	
	public void dataRead(){
		// 버퍼 생성
        BufferedReader br = null;        
         
        // Input 스트림 생성
        InputStreamReader isr = null;    
         
        // File Input 스트림 생성
        FileInputStream fis = null;        
 
        // File 경로
        File file = new File("easypop.ini");
        
        if(!file.isFile()){        	
        	
        	//다이얼 로그 띄우고 프로그램 종료
        	show_help_info("오류", "easypop.ini 파일을 찾을수가 없습니다.");
        }
        
        // 버퍼로 읽어들일 임시 변수
        String temp = "";
         
        // 최종 내용 출력을 위한 변수
        String content = "";
         
        try {
             
            // 파일을 읽어들여 File Input 스트림 객체 생성
            fis = new FileInputStream(file);
             
            // File Input 스트림 객체를 이용해 Input 스트림 객체를 생서하는데 인코딩을 UTF-8로 지정
            isr = new InputStreamReader(fis, "EUC-kr");  // "EUC-kr"
            //isr = new InputStreamReader(fis, "UTF-8"); UTF-8 한글 깨짐현상 발생
             
            // Input 스트림 객체를 이용하여 버퍼를 생성
            br = new BufferedReader(isr);
         
            ArrayList<String> als = new ArrayList<String>();
           
            // 버퍼를 한줄한줄 읽어들여 내용 추출
           while( (temp = br.readLine()) != null) {
              	als.add(temp.toString());
            }                 
           
           
           int a;
           if( !als.isEmpty() ){
        	   a = als.indexOf("[category]");
        	   map.put("folders", als.get(a+1));
        	   map.put("active", als.get(a+2));
        	   a = als.indexOf("[datalist]");
        	   map.put("columns", als.get(a+1));
       	       a = als.indexOf("[posapp]");
        	   map.put("file_name", als.get(a+1));
        	   map.put("data_file", als.get(a+2));
        	   a = als.indexOf("[page]");
        	   map.put("comp_list", als.get(a+1));
        	   a = als.indexOf("[hari_page]");
        	   map.put("print_list", als.get(a+1));
        	   map.put("last", als.get(a+2));
        	   map.put("center", als.get(a+3));
        	   map.put("cut_line", als.get(a+4));
        	   map.put("gab", als.get(a+5));
        	   map.put("margine", als.get(a+6));
        	   a = als.indexOf("[config]");
        	   map.put("view_tag_name", als.get(a+1));
        	   map.put("already_exist_run", als.get(a+2));
           }
           //테스트 내용 출력
           System.out.println(als.toString());           
             
        } catch (FileNotFoundException e) {
            e.printStackTrace();
             
        } catch (Exception e) {
            e.printStackTrace();
             
        } finally {
 
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
             
            try {
                isr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
             
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
             
        }

	}
	
	
	
	 public void inputDate(){
		 
		 	// 버퍼 생성
	        BufferedReader br = null;	       
	         
	        // Input 스트림 생성
	        InputStreamReader isr = null;	       
	         
	        // File Input 스트림 생성
	        FileInputStream fis = null;     
	 
	        // File 경로
	        File file = new File("easypop.ini");
	 
	        // 버퍼로 읽어들일 임시 변수
	        String temp = "";
	         
	        // 최종 내용 출력을 위한 변수
	        String content = "";
	        
	        
	        //폴더 불러오기
	        String folders_get="";
	        for(int i =0; i < dtm1.getRowCount() ; i++ ){	        	   
	        	   for( int j = 0 ; j < dtm1.getColumnCount(); j++ ){
	        		   folders_get += (String)dtm1.getValueAt(i, j);
	        		   //map.put("folders", (String)dtm1.getValueAt(i, j));
	        	   }	        	   
	        	   if( i+1 < dtm1.getRowCount() ){
        			   folders_get += ",";
        		   }
	           }	        
	        map.put("folders", "folders="+folders_get);
	        	    
	        //포스데이타창 불러오기
	        String columns_get="";
	        for(int i =0; i < dtm.getRowCount() ; i++ ){	        	   
	        	   for( int j = 0 ; j < dtm.getColumnCount(); j++ ){
	        		   columns_get += (String)dtm.getValueAt(i, j);
	        		   //map.put("folders", (String)dtm1.getValueAt(i, j));	
	        		   if( j < dtm.getColumnCount()-1 ){
	        			   columns_get += ",";
	        		   }
	        	   }	        
	        	   if( i+1 < dtm.getRowCount() ){
	        		   columns_get += ";";
        		   }
	        	   
	           }	        
	        map.put("columns", "columns="+columns_get);
	        
	      //편집용지 불러오기
	        String comp_list_get="";
	        for(int i =0; i < dtm2.getRowCount() ; i++ ){	        	   
	        	   for( int j = 0 ; j < dtm2.getColumnCount(); j++ ){
	        		   comp_list_get += (String)dtm2.getValueAt(i, j);
	        		   //map.put("folders", (String)dtm1.getValueAt(i, j));	
	        		   if( j < dtm2.getColumnCount()-1 ){
	        			   comp_list_get += ",";
	        		   }
	        	   }	   
	        	   if( i+1 < dtm2.getRowCount() ){
	        		   comp_list_get += ";";
        		   }
	        	   
	           }	       
	        map.put("comp_list", "list="+comp_list_get);
	        
	      //인쇄용지 불러오기
	        String print_list_get="";
	        for(int i =0; i < dtm3.getRowCount() ; i++ ){	        	   
	        	   for( int j = 0 ; j < dtm3.getColumnCount(); j++ ){
	        		   print_list_get += (String)dtm3.getValueAt(i, j);
	        		   //map.put("folders", (String)dtm1.getValueAt(i, j));	
	        		   if( j < dtm3.getColumnCount()-1 ){
	        			   print_list_get += ",";
	        		   }
	        	   }	  
	        	   if( i+1 < dtm3.getRowCount() ){
	        		   print_list_get += ";";
        		   }	        	   
	           }	       
	        map.put("print_list", "list="+print_list_get);
	        
	        
	        //포스데이타 폴더설정
	        String data_file_get ="";
	        data_file_get += "data_file=";
	        data_file_get += textField.getText()+"\\"+textField_9.getText();
	        map.put("data_file", data_file_get.toString());
	        
	        //중앙선 제단선 
	        String center_get = "center=true";
	        String cut_line_get = "cut_line=true";
	        
	        if(!chckbxNewCheckBox.isSelected()){
	        	center_get ="center=false";
	        }
	        
	        map.put("center", center_get);
	        if(!chckbxNewCheckBox_1.isSelected()){
	        	cut_line_get = "cut_line=false";
	        }
	        map.put("cut_line", cut_line_get);
	        
	        
	        try {
	             
	            // 파일을 읽어들여 File Input 스트림 객체 생성
	            fis = new FileInputStream(file);	            
	            
	            // File Input 스트림 객체를 이용해 Input 스트림 객체를 생서하는데 인코딩을 UTF-8로 지정
	            isr = new InputStreamReader(fis, "EUC-kr");  // "EUC-kr"
	            //isr = new InputStreamReader(fis, "UTF-8"); UTF-8 한글 깨짐현상 발생	            
	            
	            // Input 스트림 객체를 이용하여 버퍼를 생성
	            br = new BufferedReader(isr);	            
	            
	            ArrayList<String> als = new ArrayList<String>();
	           
	            // 버퍼를 한줄한줄 읽어들여 내용 추출
	           while( (temp = br.readLine()) != null) {
	              	als.add(temp.toString());
	            }                
	           
	           int a;
	           if( !als.isEmpty() ){
	        	   a = als.indexOf("[category]");
	        	   als.set(a+1, map.get("folders"));
	        	   als.set(a+2, map.get("active"));	
	        	   
	        	   a = als.indexOf("[datalist]");
	        	   als.set(a+1, map.get("columns"));	   
	        	   
	       	       a = als.indexOf("[posapp]");
	       	       als.set(a+1, map.get("file_name"));
	       	       als.set(a+2, map.get("data_file"));
	       	       	        	   
	        	   a = als.indexOf("[page]");
	        	   als.set(a+1, map.get("comp_list"));	        	   
	        	   
	        	   a = als.indexOf("[hari_page]");
	        	   als.set(a+1, map.get("print_list"));
	        	   als.set(a+2, map.get("last"));
	        	   als.set(a+3, map.get("center"));
	        	   als.set(a+4, map.get("cut_line"));
	        	   als.set(a+5, map.get("gab"));
	        	   als.set(a+6, map.get("margine"));
	        	   	        	   
	        	   a = als.indexOf("[config]");
	        	   als.set(a+1, map.get("view_tag_name"));
	        	   als.set(a+2, map.get("already_exist_run"));	        	   
	           }
	           	           
	           //FileWriter fw = new FileWriter(file, false);
	           FileOutputStream fos = new FileOutputStream(file);
	           OutputStreamWriter ow = new OutputStreamWriter(fos, "EUC-kr");
	           BufferedWriter bw = new BufferedWriter(ow);
	           //bw = new BufferedWriter(osr);
	           //bw.write(als.toString());
	           
	           Iterator<String> ir= als.iterator();
	           while( ir.hasNext() ) {
	        	    //System.out.println(ir.next());
	              	bw.write(ir.next()+"\n");	              	
	        	   //fw.write(ir.next());
	            }
	          	           
	           bw.close();
	           ow.close();
	           fos.close();
	           //테스트 내용 출력
	           System.out.println(als.toString());           
	             
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	             
	        } catch (Exception e) {
	            e.printStackTrace();
	             
	        } finally {
	 
	            try {
	                fis.close();	                
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	             
	            try {
	                isr.close();	                
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	             
	            try {
	                br.close();	                
	            } catch (IOException e) {
	                e.printStackTrace();
	            }	             
	        }
	        
	        show_help_info("저장", "저장 되었습니다.");
	        
	 }
	
	/**  [ 데이타 불러오기 작업 properties 작업 ]
	 *  레이블 생성
	 *  DefaultTableModel 에 컬럼 추가
	 * 
	 * */	
	
	public void doit() {		
		dataRead();
		
		try{
			System.out.println("이곳 시작합니다.");
			/*Properties p = new Properties();
			
			File f = new File("easypop.ini");
			
			p.load(new FileInputStream(f));
			// Key 값 읽기			
			
			   Set<Object> set = p.keySet();
			   Iterator<Object> iters = set.iterator();
			   while(iters.hasNext()){
			    String key = iters.next().toString();
			    System.out.printf("%s=%s\n", key, 
				             fromDB(p.getProperty(key)));
			    map.put(key, fromDB(p.getProperty(key)));			    
			   }*/				
			   
			   
			   /***  미리보기 폴더 데이터 나열하기
			    * 
			    * folders 미리보기 폴더 불러내기
			    * 
			    * */
			   
			   String folder = map.get("folders");
			   folder = folder.substring(8);
			   String[] fs =	folder.split(",");
				
				for(int i =0 ; i < fs.length ; i++){
					System.out.println(fs[i]);
					String[] a = {fs[i]};
				 dtm1.addRow(a);
				}
				//dtm1.addRow(fs);
				
				
				/***  포스데이터창 열수정 데이터 나열하기
				    * 
				    * columns 미리보기 폴더 불러내기
				    * 
				    * */
				
				String columns = map.get("columns");
				columns = columns.substring(8);
				String[] col = columns.split(";");
				
				for(int i =0 ; i < col.length ; i++){
					System.out.println(col[i]);
					String[] b = col[i].split(",");
					//3번째 콤마 선택 여부만 ㅌ콤보
					
					//b[3].toString();
				 dtm.addRow(b);
				}
				//dtm.addRow(vc);
				
				
				/**  [ 편집페이지 설정 ]
				 *  
				 *  list
				 * 
				 * */	
				String comp_list = map.get("comp_list");
				comp_list = comp_list.substring(5);
				String[] cl = comp_list.split(";");				
				for(int i =0 ; i < cl.length ; i++){
					System.out.println(cl[i]);
					String[] b = cl[i].split(",");
					
				 dtm2.addRow(b);
				}
				
				
				/**  [ 인쇄페이지 설정 ]
				 *  
				 *  list
				 * 
				 * */	
				String print_list = map.get("print_list");
				print_list = print_list.substring(5);
				String[] pl = print_list.split(";");				
				for(int i =0 ; i < pl.length ; i++){
					System.out.println(pl[i]);
					String[] b = pl[i].split(",");
					
				 dtm3.addRow(b);
				}
				
				
				/**  [ Goods.txt 폴더경로 설정 ]
				 *  
				 *  list
				 * 
				 * */	
				String data_file = map.get("data_file");
				data_file = data_file.substring(10);
				data_file = data_file.replace("\\goods.txt", "");
				/*System.out.println(data_file.toString());
				String[] df = data_file.split("\\");
				String dft = "";
				String dft_name = "";
				for(int i =0 ; i < df.length ; i++){
					System.out.println(df[i]);
					if( df.length != i ) { dft += df[i].toString(); }else{ dft_name = df[i].toString(); }
				}*/
				
				textField.setText(data_file.toString());				
				textField_9.setText("goods.txt");
				
				/**  [ 제단선, 중앙정렬 ]
				 *  
				 *  선택 하기
				 * 
				 * */	
				
				String center = map.get("center");
				center = center.substring(7);
					if(center.equals("true")){
						chckbxNewCheckBox.setSelected(true);						
					}else{
						chckbxNewCheckBox.setSelected(false);
					}
				String cut_line = map.get("cut_line");
				cut_line = cut_line.substring(9);
					if(cut_line.equals("true")) {
						chckbxNewCheckBox_1.setSelected(true);
					}else {
						chckbxNewCheckBox_1.setSelected(false);
					}				
					
					
		    //System.out.println("folders = " + fromDB(p.getProperty("folders")));
			//System.out.println("password = " + p.getProperty("datalist"));
			//System.out.println("location = " + p.getProperty("posapp"));
			// Key 값 저장
			//p.setProperty("Key", p.getProperty("DBuser" ));
			//p.list(System.out);
			// ini 파일 쓰기
			//p.store( new FileOutputStream("user.ini"), "done.");
			   System.out.println("종료 합니다.");
			} catch (Exception e) {
				System.out.println(e);
			}
	}	
		
	/**
	 * 다이얼 로그 띄우기
	 * 
	 * 
	 * */
	
	public void show_help_info(String title, String content){ 
        dlg_help_info= new JDialog(this, title);                 
        JButton btn_info_exit = new JButton("확인"); 
         
        JLabel lbl_info_date = new JLabel(content, JLabel.CENTER);
        JPanel pan_info = new JPanel(new GridLayout(1,1,10,10)); 
        JPanel pan_info_btn = new JPanel(); 
                 
        pan_info.add(lbl_info_date);
        pan_info_btn.add(btn_info_exit); 
         
        dlg_help_info.add(pan_info,"North"); 
        //dlg_help_info.add(lbl_info_se,"Center"); 
        dlg_help_info.add(pan_info_btn,"South"); 
         
         
        //화면구성 
        int x = this.getX()+this.getWidth()/2-100; 
        int y = this.getY()+this.getHeight()/2-75; 
                 
        dlg_help_info.setBounds(x,y, 200,150); //부모프레임의 가운데 뛰우기 
        dlg_help_info.setResizable(false);; 
        dlg_help_info.setVisible(true); 
        dlg_help_info.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE); 
         
        btn_info_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//종료합니다.
				System.exit(0);				
			}
		}); 
    } 

	
	
	
	/** 출력문자 깨짐현상 변경 함수
	 * 
	 * 
	 * 
	 * */	
	
		public String fromDB(String str)
		 {
		        String temp = "";
		        try
		        {
		            temp = new String(str.getBytes("8859_1"), "EUC-KR");
		        }
		        catch (Exception e)
		        {}
		        return temp;
		}

		public String toDB(String str) 
		 {
		        String temp = "";
		        try
		        {
		            temp = new String(str.getBytes("EUC-KR"), "8859_1");
		        }
		        catch (Exception e)
		        {}
		        return temp;
		}		
}
