import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;


public class MainProgram implements ActionListener{
	
	//������ſ�
	//public ServerSocket serverSocket;
	public ServerSocket receivSocket;  //�ڵ��� ���ú� ����
	public ServerSocket sendSocket;   //�ڵ��� ���� ����
	private ServerSocket appSocket;  //���θ� ���ú� ����
	
	//public Socket socket;	
	public Thread masterFile_TranThread, dataFile_TranThread, app_thread; 
	
	private Properties config_file;
	
	public JFrame frame;
	
	// ***** ���� �н� ���� *****
	private String[] filePath = new String[2];
		
	DefaultListModel<String> listModel_handy_master = new DefaultListModel<String>();			//����Ʈ����	
	DefaultListModel<String> listModel_handy_data = new DefaultListModel<String>();		//����Ÿ����
	DefaultListModel<String> listModel_handy_log = new DefaultListModel<String>();		//���ۻ���
	
	JLabel lblChandymaster; 			//master ���
	JLabel lblChandydata; 				//data ���	
	JLabel label_state;				//���ۻ���
	
	JList<String> list_handy_master; 							//master ���ϸ��	
	JList<String> list_handy_data; 						//data ���ϸ��
	JList<String> list_handy_log; 						//�޴��� ���ϸ��	
	
	// ***** ���̾� �α� ���� *****
	private JDialog dlg_help_info;	
	
	// SystemTrayŬ������ ���ɴϴ�.
    private SystemTray m_tray = SystemTray.getSystemTray();
    
    //������ �Դϴ�. 
    private TrayIcon m_ti;
    
    //Ʈ���� ������ Ÿ��Ʋ�̱���     
    String m_strTrayTitle;
    private JTextArea textArea_shop_log;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
	    
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {	
					new MainProgram("TIPOS �������α׷� Ver "+TranVersion.version);
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
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) { 
		    System.err.println("Cannot set look and feel:" + e.getMessage()); 
		}		
		
		m_strTrayTitle = strTrayTitle;
		initialize();
		init();			
		
		//�ۼ��� ���� 
	 	rsServer();
	}
	
	/*
	 * ������ �޴��� ������ üũ�մϴ�. 
	 * üũ�� ������ ������ ���ΰ�ħ1, ���ΰ�ħ2, ��ü����üũ, �ؽ�Ʈ�ʵ� 1,2,3 ��ü 
	 * Enable False ���մϴ�. 
	 * 
	 * */
	public void init(){	
		
		//ȯ�漳�� ����
		File file = new File("Config.dat");
		
		if(!file.exists()){
			//ȯ�漳��
			startOption();			
			return;
		}				
		
		listModel_handy_master.clear();
		listModel_handy_data.clear();
		listModel_handy_log.clear();
		
		//����Ÿ ������ ã�Ƽ� �����ϴ�.
		try {			
			
			config_file = new Properties();
			config_file.load(new FileInputStream(file));
			
			lblChandymaster.setText("c:\\handy\\master"); //master path
			filePath[0] = config_file.getProperty("Master_Path", "c:\\handy\\master");
			lblChandydata.setText("c:\\handy\\data"); //data_path
			filePath[1] = config_file.getProperty("Data_Path", "c:\\handy\\data");
						
		} catch (Exception e) {
				e.printStackTrace();
		}
		
		/*
		 *  ��ο� �����ؼ� ������ �ҷ��ͼ� 
		 *  �ؽ�Ʈ �ʵ忡 �ѷ��ݴϴ�.
		 *  
		 * */
		String[] gubun = {"Master", "Data"};
		for(int i = 0 ; i < 2; i++){			
			//�˻��� ������ ������ contentEnable(true) �� �����ؼ� �����ϴ�.
			System.out.println(filePath[i].toString());
			fileDirList(filePath[i].toString(), gubun[i]);
		}
		
		initTray();
		
	}	

	//ȯ�漳��
	private void startOption(){		
		//ConfigFilePath cf = new ConfigFilePath();
		//cf.setVisible(true);
		System.out.println("ȯ�漳���� �����մϴ�.");
		JFrame config = new ConfigFilePath();
		config.setVisible(true);
	}
	
	//���� �˻���
	private void fileDirList(String source, String gubun){
		File dir = new File(source);	
		
		//��ΰ� ������ �׸� �մϴ�.
		if(!dir.exists()){
			show_Dialog("����", "���Ϲ� ��θ� ã������ �����ϴ�.");
			return;
		}
		
		File[] fileList = dir.listFiles();
				
		try{
			for(int i = 0 ; i < fileList.length ; i++){
				File file = fileList[i]; 
				if(file.isFile() && fileNameCheck(file)){
					//������ �ִٸ� ���� �̸� ���
					if(gubun.equals("Master")){									
						listModel_handy_master.addElement(file.getName());						
					}else if(gubun.equals("Data")){
						listModel_handy_data.addElement(file.getName());
					}
				}else if(file.isDirectory()){
					System.out.println("���丮 �̸� = " + file.getName());					
				}				
			}			
		}catch(Exception e){		
			show_Dialog("����", "���Ϲ� ��θ� ã������ �����ϴ�.");			
		}
	}		
	
	//���ϸ� Ȯ���� üũ�ϱ�
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
		
		// ����ȭ���� ������� ����� ����� �ҷ����� ���� ����
		Dimension dim = new Dimension();
		Dimension dim1 = new Dimension();
		int xpos, ypos;
		
		frame = new JFrame();	
		frame.setResizable(false);
		frame.setUndecorated(true); 
		frame.setTitle("��������");
		ImageIcon im = new ImageIcon(getClass().getClassLoader().getResource("tiposH_logo.png"));		
		
        frame.setIconImage(im.getImage());
			
		// ���������� ������ ����
		frame.setSize(430, 290);				
				
		// ���� �����ȭ�� ������ ��������
		dim = Toolkit.getDefaultToolkit().getScreenSize();
				
		// ���� ������ ������ ��������
		dim1 = frame.getSize();
		
		// ȭ���� �߾ӿ� ��ġ ��Ŵ
		xpos = (int)(dim.getWidth()/2-dim1.getWidth()/2);
		ypos = (int)(dim.getHeight()/2-dim1.getHeight()/2);
		frame.setLocation(xpos, ypos);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane);
		
		JPanel panel_shoppingmall = new JPanel();
		tabbedPane.addTab("\uC1FC\uD551\uBAB0", null, panel_shoppingmall, null);
		panel_shoppingmall.setLayout(new BorderLayout(0, 0));
		
		JLabel label_shop_log = new JLabel("\uC1FC\uD551\uBAB0 \uD68C\uC6D0\uAC00\uC785 / \uC571\uC124\uCE58 / \uC8FC\uBB38 \uB85C\uADF8\uAE30\uB85D");
		panel_shoppingmall.add(label_shop_log, BorderLayout.NORTH);
		
		textArea_shop_log = new JTextArea();
		panel_shoppingmall.add(textArea_shop_log, BorderLayout.CENTER);
		
		JPanel panel_handphone = new JPanel();
		tabbedPane.addTab("\uD578\uB4DC\uD3F0", null, panel_handphone, null);
		panel_handphone.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("PC ���ϸ��");
		lblNewLabel_1.setBounds(10, 10, 81, 15);
		panel_handphone.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("���� ���", Font.BOLD, 12));
		
		JLabel lblNewLabel_2 = new JLabel("\uC804\uC1A1 \uC0C1\uD0DC \uD45C\uC2DC");
		lblNewLabel_2.setBounds(223, 10, 97, 15);
		panel_handphone.add(lblNewLabel_2);
		lblNewLabel_2.setFont(new Font("���� ���", Font.BOLD, 12));
		
		lblChandymaster = new JLabel("Master path");
		lblChandymaster.setBounds(10, 36, 188, 15);
		panel_handphone.add(lblChandymaster);
		
		list_handy_master = new JList<String>(listModel_handy_master);
		list_handy_master.setBounds(10, 55, 190, 53);
		panel_handphone.add(list_handy_master);
		list_handy_master.setEnabled(false);
		list_handy_master.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list_handy_master.setFont(new Font("���� ���", Font.PLAIN, 12));
		
		lblChandydata = new JLabel("Data Path");
		lblChandydata.setBounds(10, 129, 190, 15);
		panel_handphone.add(lblChandydata);
		
		list_handy_data = new JList<String>(listModel_handy_data);
		list_handy_data.setBounds(10, 144, 190, 53);
		panel_handphone.add(list_handy_data);
		list_handy_data.setEnabled(false);
		list_handy_data.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list_handy_data.setFont(new Font("���� ���", Font.PLAIN, 12));
		
		list_handy_log = new JList<String>(listModel_handy_log);
		list_handy_log.setBounds(223, 55, 190, 142);
		panel_handphone.add(list_handy_log);
		list_handy_log.setEnabled(false);
		list_handy_log.setFont(new Font("���� ���", Font.PLAIN, 12));
		
		JButton button_clear = new JButton("�����");
		button_clear.addActionListener(this);
		button_clear.setBounds(344, 5, 69, 25);
		panel_handphone.add(button_clear);
		button_clear.setFont(new Font("���� ���", Font.PLAIN, 12));
		
		label_state = new JLabel("���ϼۼ��� ���");
		label_state.setBounds(225, 35, 188, 15);
		panel_handphone.add(label_state);
		label_state.setFont(new Font("���� ���", Font.PLAIN, 12));
		
		JPanel panel_main_bottom = new JPanel();
		frame.getContentPane().add(panel_main_bottom, BorderLayout.SOUTH);
		
		JButton button_rs = new JButton("�ۼ���");
		panel_main_bottom.add(button_rs);
		button_rs.addActionListener(this);
		
		JButton button_rsExit = new JButton("��������");
		panel_main_bottom.add(button_rsExit);
		
		JButton button_exit = new JButton("�ݱ�");
		panel_main_bottom.add(button_exit);
		button_exit.addActionListener(this);
		button_rsExit.addActionListener(this);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("�޴�");
		menuBar.add(mnNewMenu);
		
		JMenuItem menuItem = new JMenuItem("ȯ�漳��");
		mnNewMenu.add(menuItem);
		menuItem.addActionListener(this);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("����");
		mnNewMenu.add(mntmNewMenuItem);		
		mntmNewMenuItem.addActionListener(this);
	}
		
	// Ʈ���� �������� �ʱ⼳���� ���ݴϴ�.
    private void initTray()  {
    	
    ImageIcon im = new ImageIcon(getClass().getClassLoader().getResource("tiposH_logo.png"));	
    Image image = im.getImage();
    	
     // TrayIcon�� �����մϴ�.
     m_ti = new TrayIcon(image, m_strTrayTitle, createPopupMenu());     
     m_ti.setImageAutoSize(true);   
     m_ti.addMouseListener(new java.awt.event.MouseAdapter()
     {
         public void mousePressed(java.awt.event.MouseEvent evt)
         {
        	 if(evt.getButton() == MouseEvent.BUTTON1){
        		 frame.setVisible(true);
        	 }
         }
     });     
        
     // ������ ���� SystemTray�� ��� �� ������ TrayIcon�� �ν��Ͻ��� ���ڷ� �־��ݴϴ�.
     try{
          m_tray.add(m_ti);
	  	} catch(AWTException e1){	
	  		e1.printStackTrace();
	  	}
    }
		
    //������ ����� �մϴ�.
    private void resetSocket(){
    	
    	listModel_handy_log.clear();
		
		if(masterFile_TranThread.isAlive()){
			masterFile_TranThread.interrupt();
			listModel_handy_log.addElement( "�۽� ��������");
		}
				
		if(dataFile_TranThread.isAlive()){
			dataFile_TranThread.interrupt();
			listModel_handy_log.addElement( "���� ��������");
		}
		
		if(app_thread.isAlive()){
			app_thread.interrupt();				
		}
		
		try {
			receivSocket.close();
			sendSocket.close();
			appSocket.close();
			//socket.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}			
    	
    }
    
    
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		System.out.println(e.getActionCommand() + e.getID());
		switch (e.getActionCommand()){
		case "����":
			frame.setVisible(true);
			break;
		case "�ۼ���":
			resetSocket();
			rsServer();
			break;		
		case "��������":
				
			resetSocket();			
			//sendFileTransfer();
			break;
		case "�����":
			listModel_handy_log.clear();
			break;
		case "ȯ�漳��":
			ConfigFilePath cf = new ConfigFilePath();
			cf.setVisible(true);
			break;
		case "�ݱ�":
			frame.dispose();
			break;
		case "����":
			System.exit(0);
			break;
		}
		
	}
		
	
	public void rsServer(){
		
		try {			
			receivSocket = new ServerSocket(8681);
			sendSocket = new ServerSocket(8682);			
			appSocket = new ServerSocket(8683);			
		}catch(BindException e){
			JOptionPane.showConfirmDialog(null, "���α׷��� �̹� �������Դϴ�.", "���θ� ����", JOptionPane.CLOSED_OPTION);
			System.exit(0);
		}catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			show_Dialog("�ۼ���", " ���� �ۼ��� ��� ���Դϴ�. ");
		}
		
		app_thread = new Thread(new Runnable() {
			@Override
			public void run() {
				
				try { 	
					while(!Thread.currentThread().isInterrupted()){
						Socket app_Socket = appSocket.accept();					
						System.out.println("ShoppingMall Data Tran Client Connected!! ");
		            	textArea_shop_log.append("���θ� ���� �Ǿ����ϴ�. \r\n");
						if(app_Socket.isConnected()){
							ShopReceiveData srd = new ShopReceiveData(app_Socket);
							srd.start();			
							textArea_shop_log.append("�ڷ���� �Ϸ� \r\n");							
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
		
		//�������� ������Դϴ�.
		masterFile_TranThread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				// TODO Auto-generated method stub
				//ReceiveServer rs = new ReceiveServer();
				//rs.main(args);
				try {
		            // ������ ���� ���� �� ���
					//ServerSocket serverSocket = new ServerSocket();
					//if(serverSocket != null) serverSocket.close();
					
					listModel_handy_log.clear();
					listModel_handy_log.addElement( "���� �ۼ��� ������Դϴ�.");					
					// ����Ǹ� ��ſ� ���� ����
					
					while(!Thread.currentThread().isInterrupted()){
						Socket socket = receivSocket.accept();
						
						System.out.println("Ŭ���̾�Ʈ�� ����Ǿ����ϴ�.");
		            		            
			            if(socket.isConnected()){
				            // ���� ���� �۾� ����
				            ReceiveFile rf = new ReceiveFile(socket);
				            rf.start();
				            listModel_handy_log.addElement( "���� ���ſϷ�");
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
		masterFile_TranThread.start();		
		
		//�������� �����		
		File[] file = new File[listModel_handy_master.getSize()];
		
		for (int i =0; i < listModel_handy_master.getSize(); i++){
			file[i] = new File( lblChandymaster.getText(), listModel_handy_master.get(i));
		}
		listModel_handy_log.clear();
		listModel_handy_log.addElement( listModel_handy_master.getSize() + " �� �� ������ ���� �մϴ�.");
		
		dataFile_TranThread = new Thread(new Runnable() {
			
			@Override
			public void run() {								
				// TODO Auto-generated method stub
				
				try {
		            // ������ ���� ���� �� ���
					listModel_handy_log.addElement(" ���� �����... ");
					listModel_handy_log.addElement( " �޴����� ���� ��ư�� �����ּ��� " );
					while(!Thread.currentThread().isInterrupted()){
					for(File f : file){
					Socket socket = sendSocket.accept();
				    System.out.println("Ŭ���̾�Ʈ�� ����Ǿ����ϴ�.");
		            		            
		            // ���� ���� �۾� ����
		            SendFile sf = new SendFile(socket, f);
					sf.start();		
					//i++;
					}
					listModel_handy_log.addElement( listModel_handy_master.getSize() + " �� ���� ���ۿϷ�");
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
		        	
		        	//listModel_2.addElement( listModel.getSize() + " �� ���� ���ۿϷ�");
		        }
			}
		});
		dataFile_TranThread.start();
		
	}
	
	
	/**
	 * ���̾� �α� ����	
	 * */	
	public void show_help_info(String title, String content){ 
		
        dlg_help_info= new JDialog(frame, title);                 
        JButton btn_info_exit = new JButton("Ȯ��"); 
         
        JLabel lbl_info_date = new JLabel(content, JLabel.CENTER);
        JPanel pan_info = new JPanel(); 
        JPanel pan_info_btn = new JPanel(); 
                 
        pan_info.add(lbl_info_date);
        pan_info_btn.add(btn_info_exit); 
         
        //dlg_help_info.add(pan_info,"North");
        dlg_help_info.getContentPane().add(pan_info, "Center");
        //dlg_help_info.add(lbl_info_se,"Center"); 
        dlg_help_info.getContentPane().add(pan_info_btn, "South"); 
         
         
        //ȭ�鱸�� 
        int x = frame.getX()+frame.getWidth()/2-150; 
        int y = frame.getY()+frame.getHeight()/2-75; 
                 
        dlg_help_info.setBounds(x,y, 300,150); //�θ��������� ��� �ٿ�� 
        dlg_help_info.setResizable(false);; 
        dlg_help_info.setVisible(true); 
        dlg_help_info.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE); 
         
        btn_info_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//�����մϴ�.
				//System.exit(0);
				ConfigFilePath cf = new ConfigFilePath();
				cf.setVisible(true);
				dlg_help_info.dispose();
			}
		}); 
    } 	
	
	/**
	 * �⺻ ���̾� �α� ����	
	 * */	
	public void show_Dialog(String title, String content){ 
		
        dlg_help_info= new JDialog(frame, title);                 
        JButton btn_info_exit = new JButton("Ȯ��"); 
         
        JLabel lbl_info_date = new JLabel(content, JLabel.CENTER);
        JPanel pan_info = new JPanel(); 
        JPanel pan_info_btn = new JPanel(); 
                 
        pan_info.add(lbl_info_date);
        pan_info_btn.add(btn_info_exit); 
         
        //dlg_help_info.add(pan_info,"North");
        dlg_help_info.getContentPane().add(pan_info, "Center");
        //dlg_help_info.add(lbl_info_se,"Center"); 
        dlg_help_info.getContentPane().add(pan_info_btn, "South"); 
         
         
        //ȭ�鱸�� 
        int x = frame.getX()+frame.getWidth()/2-150; 
        int y = frame.getY()+frame.getHeight()/2-75; 
                 
        dlg_help_info.setBounds(x,y, 300,150); //�θ��������� ��� �ٿ�� 
        dlg_help_info.setResizable(false);; 
        dlg_help_info.setVisible(true); 
        dlg_help_info.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE); 
         
        btn_info_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//�����մϴ�.
				//System.exit(0);
				dlg_help_info.dispose();
			}
		}); 
    } 	
	
	// Ʈ���� �����ܿ��� ����� �˾� �Ŵ��� ����ϴ�.
    private PopupMenu createPopupMenu()
    {
        PopupMenu popupMenu = new PopupMenu();
        
        MenuItem miShow = new MenuItem("����");
        MenuItem miStart = new MenuItem("�ۼ���");
        MenuItem miStop = new MenuItem("��������");
        MenuItem miOption = new MenuItem("ȯ�漳��");
        MenuItem miQuit = new MenuItem("����");
        
        //������ �׸� ���� ������ ����. 
        miShow.addActionListener(this);
        miStart.addActionListener(this);
        miStop.addActionListener(this);
        miOption.addActionListener(this);        
        miQuit.addActionListener(this);
        
        //�˾� �޴��� ��� 
        popupMenu.add(miShow);
        popupMenu.add(miStart);
        popupMenu.add(miStop);
        popupMenu.add(miOption);
        
        // �� ����
        popupMenu.addSeparator();
        popupMenu.add(miQuit);
        
        return popupMenu;
    }
}	//end