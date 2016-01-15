import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.Channel;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;


public class MainProgram implements ActionListener{
	
	//소켓통신용
	//public ServerSocket serverSocket;
	public ServerSocket receivSocket;  //핸드폰 리시브 소켓
	public ServerSocket sendSocket;   //핸드폰 센드 소켓
	private ServerSocket appSocket;  //쇼핑몰 리시브 소켓
	
	public Socket socket;	
	private BufferedOutputStream bos = null;
	private BufferedInputStream bis = null;
	private DataInputStream dis = null;
	private DataOutputStream dos = null;	
	private int fileTransferCount = 0;
	private long fileTransferSize = 0;	
	private File copyFile = null;
	public Thread th, th1, app_thread; 
	
	private Properties config_file;
	
	public JFrame frame;
	
	// ***** 파일 패스 설정 *****
	private String[] filePath = new String[2];
		
	DefaultListModel<String> listModel = new DefaultListModel<String>();			//마스트파일	
	DefaultListModel<String> listModel_1 = new DefaultListModel<String>();		//데이타파일
	DefaultListModel<String> listModel_2 = new DefaultListModel<String>();		//전송상태
	
	JButton btnNewButton; 		//pc목록 새로고침	
	
	JLabel label_master; 			//master 경로
	JLabel label_data; 				//data 경로	
	JLabel label_state;				//전송상태
	
	JList list; 							//master 파일목록	
	JList list_1; 						//data 파일목록
	JList list_2; 						//휴대폰 파일목록	
	
	// ***** 다이얼 로그 띄우기 *****
	private JDialog dlg_help_info;	
	
	// SystemTray클래스를 얻어옵니다.
    private SystemTray m_tray = SystemTray.getSystemTray();
    
    //아이콘 입니다. 
    private TrayIcon m_ti;
    
    //트레이 아이콘 타이틀이구요     
    String m_strTrayTitle;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
	    
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {	
					new MainProgram("TIPOS 소켓프로그램 Ver 1.0.4");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the application.
	 */
	public MainProgram(String strTrayTitle) {
		m_strTrayTitle = strTrayTitle;
		initialize();
		init();				
	}
	
	/*
	 * 진입점 휴대폰 폴더를 체크합니다. 
	 * 체크시 폴더가 없으면 새로고침1, 새로고침2, 전체보기체크, 텍스트필드 1,2,3 전체 
	 * Enable False 로합니다. 
	 * 
	 * */	
	public void init(){	
		
		try {
		    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) { 
		    System.err.println("Cannot set look and feel:" + e.getMessage()); 
		}
		
		//환경설정 파일
		File file = new File("Config.dat");
		
		if(!file.exists()){
			//환경설정
			startOption();			
			return;
		}				
		
		listModel.clear();
		listModel_1.clear();
		listModel_2.clear();
		
		//데이타 파일을 찾아서 나눕니다.
		try {			
			
			config_file = new Properties();
			config_file.load(new FileInputStream(file));
			
			label_master.setText(config_file.getProperty("Master_Path")); //master path
			filePath[0] = config_file.getProperty("Master_Path");
			label_data.setText(config_file.getProperty("Data_Path")); //data_path
			filePath[1] = config_file.getProperty("Data_Path");
			
			/*FileInputStream fis = new FileInputStream(file.getPath());
			BufferedReader bufferReader = new BufferedReader(new InputStreamReader(fis, "euc-kr"));
			String temp="";						
				int i = 0;
				while( (temp = bufferReader.readLine()) != null ) {
					filePath[i] = temp.toString();
					i++;
				}					
			bufferReader.close();
			fis.close();*/
		} catch (Exception e) {
				e.printStackTrace();
		}		
		
		/*try{
			for(String a : filePath ){
				if(a.equals("")){				
					//환경설정
					startOption();
					return;
				}
			}
			
			label_master.setText(filePath[0].toString()); //master path
			label_data.setText(filePath[1].toString());
			
		}catch(Exception e){			
			//show_Dialog("오류", "환경설정을 해주세요.");		
			//환경설정
			startOption();			
			return;
		}		*/
		
		/*
		 *  경로에 접속해서 파일을 불러와서 
		 *  텍스트 필드에 뿌려줍니다.
		 *  
		 * */
		String[] gubun = {"Master", "Data"};
		for(int i = 0 ; i < 2; i++){			
			//검색시 폴더가 없으면 contentEnable(true) 를 실행해서 막습니다.
			System.out.println(filePath[i].toString());
			fileDirList(filePath[i].toString(), gubun[i]);
		}		
		initTray();		
		
	}	

	//환경설정
	private void startOption(){		
		//ConfigFilePath cf = new ConfigFilePath();
		//cf.setVisible(true);
		System.out.println("환경설정을 실행합니다.");
		JFrame config = new ConfigFilePath();
		config.setVisible(true);
	}
	
	//파일 검색기
	private void fileDirList(String source, String gubun){
		File dir = new File(source);	
		
		//경로가 없으면 그만 합니다.
		if(!dir.exists()){
			show_Dialog("오류", "파일및 경로를 찾을수가 없습니다.");
			return;
		}
		
		File[] fileList = dir.listFiles();
				
		try{
			for(int i = 0 ; i < fileList.length ; i++){
				File file = fileList[i]; 
				if(file.isFile() && fileNameCheck(file)){
					//파일이 있다면 파일 이름 출력
					if(gubun.equals("Master")){									
						listModel.addElement(file.getName());						
					}else if(gubun.equals("Data")){
						listModel_1.addElement(file.getName());
					}
				}else if(file.isDirectory()){
					System.out.println("디렉토리 이름 = " + file.getName());					
				}				
			}			
		}catch(Exception e){		
			show_Dialog("오류", "파일및 경로를 찾을수가 없습니다.");			
		}
	}		
	
	//파일명 확장자 체크하기
	private boolean fileNameCheck(File file){
		
		FilenameFilter filefilter_java = new FilenameFilter() {
            public boolean accept(File dir, String name) { //if the file extension is .txt return true, else false
                
            	return name.endsWith(".dat");
            }
        };
		
		return filefilter_java.accept(file, file.getName().toLowerCase());		
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		// 현재화면의 사이즈와 모니터 사이즈를 불러오기 위해 선언
		Dimension dim = new Dimension();
		Dimension dim1 = new Dimension();
		int xpos, ypos;
		
		frame = new JFrame();	
		frame.setResizable(false);
		frame.setUndecorated(true); 
		frame.setTitle("파일전송");
		ImageIcon im = new ImageIcon(getClass().getClassLoader().getResource("tiposH_logo.png"));		
		
        frame.setIconImage(im.getImage());
			
		// 메인프레임 사이즈 선언
		frame.setSize(430, 290);				
				
		// 현재 모니터화면 사이즈 가져오기
		dim = Toolkit.getDefaultToolkit().getScreenSize();
				
		// 현재 프레임 사이즈 가져오기
		dim1 = frame.getSize();
		
		// 화면을 중앙에 위치 시킴
		xpos = (int)(dim.getWidth()/2-dim1.getWidth()/2);
		ypos = (int)(dim.getHeight()/2-dim1.getHeight()/2);
		frame.setLocation(xpos, ypos);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("PC 파일목록");
		lblNewLabel_1.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		lblNewLabel_1.setBounds(13, 14, 81, 15);
		frame.getContentPane().add(lblNewLabel_1);
		
		btnNewButton = new JButton("새로고침");
		btnNewButton.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		//btnNewButton.setEnabled(false);
		btnNewButton.setBounds(116, 10, 86, 23);
		btnNewButton.addActionListener(this);
		frame.getContentPane().add(btnNewButton);
				
		JLabel lblNewLabel_2 = new JLabel("\uC804\uC1A1 \uC0C1\uD0DC \uD45C\uC2DC");
		lblNewLabel_2.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		lblNewLabel_2.setBounds(227, 14, 97, 15);
		frame.getContentPane().add(lblNewLabel_2);
		
		label_master = new JLabel("Master path");
		label_master.setBounds(13, 38, 188, 15);
		frame.getContentPane().add(label_master);
		
		list = new JList(listModel);
		list.setEnabled(false);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		list.setBounds(12, 54, 190, 53);
		frame.getContentPane().add(list);
		
		label_data = new JLabel("Data Path");
		label_data.setBounds(12, 111, 190, 15);
		frame.getContentPane().add(label_data);
		
		list_1 = new JList(listModel_1);
		list_1.setEnabled(false);
		list_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list_1.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		list_1.setBounds(12, 129, 190, 53);
		frame.getContentPane().add(list_1);
		
		list_2 = new JList(listModel_2);
		list_2.setEnabled(false);
		list_2.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		list_2.setBounds(227, 54, 190, 128);
		frame.getContentPane().add(list_2);
		
		JButton button_rs = new JButton("송수신");
		button_rs.setBounds(12, 192, 97, 62);
		frame.getContentPane().add(button_rs);
		button_rs.addActionListener(this);
		
		JButton button_rsExit = new JButton("연결종료");
		button_rsExit.setBounds(167, 192, 97, 62);
		frame.getContentPane().add(button_rsExit);
		button_rsExit.addActionListener(this);
		
		JButton button_exit = new JButton("닫기");
		button_exit.setBounds(320, 192, 97, 62);
		frame.getContentPane().add(button_exit);
		
		label_state = new JLabel("파일송수신 목록");
		label_state.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		label_state.setBounds(229, 39, 188, 15);
		frame.getContentPane().add(label_state);
		
		JButton button_clear = new JButton("지우기");
		button_clear.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		button_clear.setBounds(331, 10, 86, 23);
		frame.getContentPane().add(button_clear);
		button_exit.addActionListener(this);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("메뉴");
		menuBar.add(mnNewMenu);
		
		JMenuItem menuItem = new JMenuItem("환경설정");
		mnNewMenu.add(menuItem);
		menuItem.addActionListener(this);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("종료");
		mnNewMenu.add(mntmNewMenuItem);		
		mntmNewMenuItem.addActionListener(this);
	}
		
	// 트레이 아이콘의 초기설정을 해줍니다.
    private void initTray()  {
    	
    ImageIcon im = new ImageIcon(getClass().getClassLoader().getResource("tiposH_logo.png"));	
    Image image = im.getImage();
    	
    	
     // TrayIcon을 생성합니다.
     m_ti = new TrayIcon(image, m_strTrayTitle, createPopupMenu());
     
     m_ti.setImageAutoSize(true);
    /* m_ti.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	            	
            	mp.frame.setVisible(true);
                // 트레이 아이콘 자체를 클릭했을때 일어날 이벤트에 대한 동작을 구현합니다. 
            }
     });*/
     m_ti.addMouseListener(new java.awt.event.MouseAdapter()
     {
         public void mousePressed(java.awt.event.MouseEvent evt)
         {
        	 if(evt.getButton() == MouseEvent.BUTTON1){
        		 frame.setVisible(true);
        	 }
         }
     });
     
        
     // 위에서 얻어온 SystemTray에 방금 막 생성한 TrayIcon의 인스턴스를 인자로 넣어줍니다.
     try{
          m_tray.add(m_ti);
	  	} catch(AWTException e1){	
	  		e1.printStackTrace();
	  	}
     
    //송수신 시작 
 	rsServer();     
    }
		
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		System.out.println(e.getActionCommand() + e.getID());
		switch (e.getActionCommand()){
		case "열기":
			frame.setVisible(true);
			break;
		case "송수신":
			rsServer();
			break;		
		case "연결종료":
				
			listModel_2.clear();
			
			if(th.isAlive()){
				th.interrupt();
				listModel_2.addElement( "송신 연결종료");
			}
					
			if(th1.isAlive()){
				th1.interrupt();
				listModel_2.addElement( "수신 연결종료");
			}
			
			try {
				receivSocket.close();
				sendSocket.close();
				socket.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}			
			
			//sendFileTransfer();
			break;
		case "새로고침":						
			init();
			break;			
		case "목록지우기":
			listModel_2.clear();
			break;
		case "환경설정":
			ConfigFilePath cf = new ConfigFilePath();
			cf.setVisible(true);
			break;
		case "닫기":
			frame.dispose();
			break;
		case "종료":
			System.exit(0);
			break;
		}		
		
	}
		
	
	public void rsServer(){
		
		try {			
			receivSocket = new ServerSocket(8681);
			sendSocket = new ServerSocket(8682);			
			appSocket = new ServerSocket(8683);			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			show_Dialog("송수신", " 현재 송수신 대기 중입니다. ");
		}
		
		app_thread = new Thread(new Runnable() {
			
			@Override
			public void run() {				
				
				try {		        	
					while(!Thread.currentThread().isInterrupted()){
						Socket app_Socket = appSocket.accept();					
						System.out.println("ShoppingMall Data Tran Client Connected!! ");
		            	
						if(app_Socket.isConnected()){
							ShopReceiveData srd = new ShopReceiveData(app_Socket);
							srd.start();			
						}						
					}
					
				} catch (IOException e) {
		            e.printStackTrace();
		        }finally{
		        	try {						
						appSocket.close();	
						System.out.println("ShoppingMall Data Tran Socket Close");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        }
			}
		});
		app_thread.start();
		
		
		/*th = new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				// TODO Auto-generated method stub
				//ReceiveServer rs = new ReceiveServer();
				//rs.main(args);
				try {
		            // 리스너 소켓 생성 후 대기
					//ServerSocket serverSocket = new ServerSocket();
					//if(serverSocket != null) serverSocket.close();
					
					listModel_2.clear();
					listModel_2.addElement( "파일 송수신 대기중입니다.");					
					// 연결되면 통신용 소켓 생성
					
					while(!Thread.currentThread().isInterrupted()){
						Socket socket = receivSocket.accept();
						
						System.out.println("클라이언트와 연결되었습니다.");
		            		            
			            if(socket.isConnected()){
				            // 파일 수신 작업 시작
				            ReceiveFile rf = new ReceiveFile(socket);
				            rf.start();
				            listModel_2.addElement( "파일 수신완료");
			            }	
					}
					
				} catch (IOException e) {
		            e.printStackTrace();
		        }finally{
		        	try {
						receivSocket.close();						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        }
			}
		});
		th.start();	*/	
		/*
		File[] file = new File[listModel.getSize()];
		
		for (int i =0; i < listModel.getSize(); i++){
			file[i] = new File( label_master.getText(), listModel.get(i));
		}
		listModel_2.clear();
		listModel_2.addElement( listModel.getSize() + " 개 의 파일을 전송 합니다.");
		
		th1 = new Thread(new Runnable() {
			
			@Override
			public void run() {				
				
				// TODO Auto-generated method stub
				//ReceiveServer rs = new ReceiveServer();
				//rs.main(args);
				try {
		            // 리스너 소켓 생성 후 대기
					//ServerSocket serverSocket = new ServerSocket();
					//if(serverSocket != null) serverSocket.close();
					//serverSocket = new ServerSocket(8681);			
					listModel_2.addElement(" 전송 대기중... ");
					listModel_2.addElement( " 휴대폰의 수신 버튼을 눌러주세요 " );
					while(!Thread.currentThread().isInterrupted()){
					for(File f : file){
					//int i = 0;
					//while(true){
					// 연결되면 통신용 소켓 생성						
					//Socket socket = serverSocket.accept();
					socket = sendSocket.accept();
		            System.out.println("클라이언트와 연결되었습니다.");
		            		            
		            // 파일 전송 작업 시작
		            SendFile sf = new SendFile(socket, f);
					sf.start();		
					//i++;
					}
					listModel_2.addElement( listModel.getSize() + " 개 파일 전송완료");
					}
				}catch(IOException e) {
		            e.printStackTrace();
		        }finally{			        	
		        	try{
		        		sendSocket.close();
		        	//if( socket != null) socket.close();
		        	//if(serverSocket != null) serverSocket.close();
		        	}catch(Exception e){
		        		e.printStackTrace();
		        	}
		        	
		        	//listModel_2.addElement( listModel.getSize() + " 개 파일 전송완료");
		        }
			}
		});
		th1.start();	*/
		
	}
	
	
	/**
	 * 다이얼 로그 띄우기	
	 * */	
	public void show_help_info(String title, String content){ 
		
        dlg_help_info= new JDialog(frame, title);                 
        JButton btn_info_exit = new JButton("확인"); 
         
        JLabel lbl_info_date = new JLabel(content, JLabel.CENTER);
        JPanel pan_info = new JPanel(); 
        JPanel pan_info_btn = new JPanel(); 
                 
        pan_info.add(lbl_info_date);
        pan_info_btn.add(btn_info_exit); 
         
        //dlg_help_info.add(pan_info,"North");
        dlg_help_info.getContentPane().add(pan_info, "Center");
        //dlg_help_info.add(lbl_info_se,"Center"); 
        dlg_help_info.getContentPane().add(pan_info_btn, "South"); 
         
         
        //화면구성 
        int x = frame.getX()+frame.getWidth()/2-150; 
        int y = frame.getY()+frame.getHeight()/2-75; 
                 
        dlg_help_info.setBounds(x,y, 300,150); //부모프레임의 가운데 뛰우기 
        dlg_help_info.setResizable(false);; 
        dlg_help_info.setVisible(true); 
        dlg_help_info.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE); 
         
        btn_info_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//종료합니다.
				//System.exit(0);
				ConfigFilePath cf = new ConfigFilePath();
				cf.setVisible(true);
				dlg_help_info.dispose();
			}
		}); 
    } 	
	
	/**
	 * 기본 다이얼 로그 띄우기	
	 * */	
	public void show_Dialog(String title, String content){ 
		
        dlg_help_info= new JDialog(frame, title);                 
        JButton btn_info_exit = new JButton("확인"); 
         
        JLabel lbl_info_date = new JLabel(content, JLabel.CENTER);
        JPanel pan_info = new JPanel(); 
        JPanel pan_info_btn = new JPanel(); 
                 
        pan_info.add(lbl_info_date);
        pan_info_btn.add(btn_info_exit); 
         
        //dlg_help_info.add(pan_info,"North");
        dlg_help_info.getContentPane().add(pan_info, "Center");
        //dlg_help_info.add(lbl_info_se,"Center"); 
        dlg_help_info.getContentPane().add(pan_info_btn, "South"); 
         
         
        //화면구성 
        int x = frame.getX()+frame.getWidth()/2-150; 
        int y = frame.getY()+frame.getHeight()/2-75; 
                 
        dlg_help_info.setBounds(x,y, 300,150); //부모프레임의 가운데 뛰우기 
        dlg_help_info.setResizable(false);; 
        dlg_help_info.setVisible(true); 
        dlg_help_info.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE); 
         
        btn_info_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//종료합니다.
				//System.exit(0);				
				dlg_help_info.dispose();
			}
		}); 
    } 	
	
	// 트레이 아이콘에서 사용할 팝업 매뉴를 만듭니다.
    private PopupMenu createPopupMenu()
    {
        PopupMenu popupMenu = new PopupMenu();
        
        MenuItem miShow = new MenuItem("열기");
        MenuItem miStart = new MenuItem("송수신");
        MenuItem miStop = new MenuItem("연결종료");
        MenuItem miOption = new MenuItem("환경설정");
        MenuItem miQuit = new MenuItem("종료");
        
        //각각에 항목에 대해 리스너 장착. 
        miShow.addActionListener(this);
        miStart.addActionListener(this);
        miStop.addActionListener(this);
        miOption.addActionListener(this);        
        miQuit.addActionListener(this);
        
        //팝업 메뉴에 등록 
        popupMenu.add(miShow);
        popupMenu.add(miStart);
        popupMenu.add(miStop);
        popupMenu.add(miOption);
        
        // 줄 생성
        popupMenu.addSeparator();
        popupMenu.add(miQuit);
        
        return popupMenu;
    }
}	//end