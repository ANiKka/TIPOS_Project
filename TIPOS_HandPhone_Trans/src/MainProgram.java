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
	
	//������ſ�
	//public ServerSocket serverSocket;
	public ServerSocket receivSocket;  //�ڵ��� ���ú� ����
	public ServerSocket sendSocket;   //�ڵ��� ���� ����
	private ServerSocket appSocket;  //���θ� ���ú� ����
	
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
	
	// ***** ���� �н� ���� *****
	private String[] filePath = new String[2];
		
	DefaultListModel<String> listModel = new DefaultListModel<String>();			//����Ʈ����	
	DefaultListModel<String> listModel_1 = new DefaultListModel<String>();		//����Ÿ����
	DefaultListModel<String> listModel_2 = new DefaultListModel<String>();		//���ۻ���
	
	JButton btnNewButton; 		//pc��� ���ΰ�ħ	
	
	JLabel label_master; 			//master ���
	JLabel label_data; 				//data ���	
	JLabel label_state;				//���ۻ���
	
	JList list; 							//master ���ϸ��	
	JList list_1; 						//data ���ϸ��
	JList list_2; 						//�޴��� ���ϸ��	
	
	// ***** ���̾� �α� ���� *****
	private JDialog dlg_help_info;	
	
	// SystemTrayŬ������ ���ɴϴ�.
    private SystemTray m_tray = SystemTray.getSystemTray();
    
    //������ �Դϴ�. 
    private TrayIcon m_ti;
    
    //Ʈ���� ������ Ÿ��Ʋ�̱���     
    String m_strTrayTitle;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
	    
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {	
					new MainProgram("TIPOS �������α׷� Ver 1.0.4");
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
	 * ������ �޴��� ������ üũ�մϴ�. 
	 * üũ�� ������ ������ ���ΰ�ħ1, ���ΰ�ħ2, ��ü����üũ, �ؽ�Ʈ�ʵ� 1,2,3 ��ü 
	 * Enable False ���մϴ�. 
	 * 
	 * */	
	public void init(){	
		
		try {
		    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) { 
		    System.err.println("Cannot set look and feel:" + e.getMessage()); 
		}
		
		//ȯ�漳�� ����
		File file = new File("Config.dat");
		
		if(!file.exists()){
			//ȯ�漳��
			startOption();			
			return;
		}				
		
		listModel.clear();
		listModel_1.clear();
		listModel_2.clear();
		
		//����Ÿ ������ ã�Ƽ� �����ϴ�.
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
					//ȯ�漳��
					startOption();
					return;
				}
			}
			
			label_master.setText(filePath[0].toString()); //master path
			label_data.setText(filePath[1].toString());
			
		}catch(Exception e){			
			//show_Dialog("����", "ȯ�漳���� ���ּ���.");		
			//ȯ�漳��
			startOption();			
			return;
		}		*/
		
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
						listModel.addElement(file.getName());						
					}else if(gubun.equals("Data")){
						listModel_1.addElement(file.getName());
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
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("PC ���ϸ��");
		lblNewLabel_1.setFont(new Font("���� ���", Font.BOLD, 12));
		lblNewLabel_1.setBounds(13, 14, 81, 15);
		frame.getContentPane().add(lblNewLabel_1);
		
		btnNewButton = new JButton("���ΰ�ħ");
		btnNewButton.setFont(new Font("���� ���", Font.PLAIN, 12));
		//btnNewButton.setEnabled(false);
		btnNewButton.setBounds(116, 10, 86, 23);
		btnNewButton.addActionListener(this);
		frame.getContentPane().add(btnNewButton);
				
		JLabel lblNewLabel_2 = new JLabel("\uC804\uC1A1 \uC0C1\uD0DC \uD45C\uC2DC");
		lblNewLabel_2.setFont(new Font("���� ���", Font.BOLD, 12));
		lblNewLabel_2.setBounds(227, 14, 97, 15);
		frame.getContentPane().add(lblNewLabel_2);
		
		label_master = new JLabel("Master path");
		label_master.setBounds(13, 38, 188, 15);
		frame.getContentPane().add(label_master);
		
		list = new JList(listModel);
		list.setEnabled(false);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setFont(new Font("���� ���", Font.PLAIN, 12));
		list.setBounds(12, 54, 190, 53);
		frame.getContentPane().add(list);
		
		label_data = new JLabel("Data Path");
		label_data.setBounds(12, 111, 190, 15);
		frame.getContentPane().add(label_data);
		
		list_1 = new JList(listModel_1);
		list_1.setEnabled(false);
		list_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list_1.setFont(new Font("���� ���", Font.PLAIN, 12));
		list_1.setBounds(12, 129, 190, 53);
		frame.getContentPane().add(list_1);
		
		list_2 = new JList(listModel_2);
		list_2.setEnabled(false);
		list_2.setFont(new Font("���� ���", Font.PLAIN, 12));
		list_2.setBounds(227, 54, 190, 128);
		frame.getContentPane().add(list_2);
		
		JButton button_rs = new JButton("�ۼ���");
		button_rs.setBounds(12, 192, 97, 62);
		frame.getContentPane().add(button_rs);
		button_rs.addActionListener(this);
		
		JButton button_rsExit = new JButton("��������");
		button_rsExit.setBounds(167, 192, 97, 62);
		frame.getContentPane().add(button_rsExit);
		button_rsExit.addActionListener(this);
		
		JButton button_exit = new JButton("�ݱ�");
		button_exit.setBounds(320, 192, 97, 62);
		frame.getContentPane().add(button_exit);
		
		label_state = new JLabel("���ϼۼ��� ���");
		label_state.setFont(new Font("���� ���", Font.PLAIN, 12));
		label_state.setBounds(229, 39, 188, 15);
		frame.getContentPane().add(label_state);
		
		JButton button_clear = new JButton("�����");
		button_clear.setFont(new Font("���� ���", Font.PLAIN, 12));
		button_clear.setBounds(331, 10, 86, 23);
		frame.getContentPane().add(button_clear);
		button_exit.addActionListener(this);
		
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
    /* m_ti.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	            	
            	mp.frame.setVisible(true);
                // Ʈ���� ������ ��ü�� Ŭ�������� �Ͼ �̺�Ʈ�� ���� ������ �����մϴ�. 
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
     
        
     // ������ ���� SystemTray�� ��� �� ������ TrayIcon�� �ν��Ͻ��� ���ڷ� �־��ݴϴ�.
     try{
          m_tray.add(m_ti);
	  	} catch(AWTException e1){	
	  		e1.printStackTrace();
	  	}
     
    //�ۼ��� ���� 
 	rsServer();     
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
			rsServer();
			break;		
		case "��������":
				
			listModel_2.clear();
			
			if(th.isAlive()){
				th.interrupt();
				listModel_2.addElement( "�۽� ��������");
			}
					
			if(th1.isAlive()){
				th1.interrupt();
				listModel_2.addElement( "���� ��������");
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
		case "���ΰ�ħ":						
			init();
			break;			
		case "��������":
			listModel_2.clear();
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
		} catch (IOException e1) {
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
		            // ������ ���� ���� �� ���
					//ServerSocket serverSocket = new ServerSocket();
					//if(serverSocket != null) serverSocket.close();
					
					listModel_2.clear();
					listModel_2.addElement( "���� �ۼ��� ������Դϴ�.");					
					// ����Ǹ� ��ſ� ���� ����
					
					while(!Thread.currentThread().isInterrupted()){
						Socket socket = receivSocket.accept();
						
						System.out.println("Ŭ���̾�Ʈ�� ����Ǿ����ϴ�.");
		            		            
			            if(socket.isConnected()){
				            // ���� ���� �۾� ����
				            ReceiveFile rf = new ReceiveFile(socket);
				            rf.start();
				            listModel_2.addElement( "���� ���ſϷ�");
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
		listModel_2.addElement( listModel.getSize() + " �� �� ������ ���� �մϴ�.");
		
		th1 = new Thread(new Runnable() {
			
			@Override
			public void run() {				
				
				// TODO Auto-generated method stub
				//ReceiveServer rs = new ReceiveServer();
				//rs.main(args);
				try {
		            // ������ ���� ���� �� ���
					//ServerSocket serverSocket = new ServerSocket();
					//if(serverSocket != null) serverSocket.close();
					//serverSocket = new ServerSocket(8681);			
					listModel_2.addElement(" ���� �����... ");
					listModel_2.addElement( " �޴����� ���� ��ư�� �����ּ��� " );
					while(!Thread.currentThread().isInterrupted()){
					for(File f : file){
					//int i = 0;
					//while(true){
					// ����Ǹ� ��ſ� ���� ����						
					//Socket socket = serverSocket.accept();
					socket = sendSocket.accept();
		            System.out.println("Ŭ���̾�Ʈ�� ����Ǿ����ϴ�.");
		            		            
		            // ���� ���� �۾� ����
		            SendFile sf = new SendFile(socket, f);
					sf.start();		
					//i++;
					}
					listModel_2.addElement( listModel.getSize() + " �� ���� ���ۿϷ�");
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
		th1.start();	*/
		
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