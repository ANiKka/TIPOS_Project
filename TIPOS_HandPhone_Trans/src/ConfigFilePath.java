
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FileDialog;
import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.Properties;

import javax.swing.SwingConstants;
import javax.swing.UIManager;

import java.awt.SystemColor;
import java.awt.BorderLayout;
import javax.swing.JTabbedPane;
import net.miginfocom.swing.MigLayout;
import javax.swing.JCheckBox;

@SuppressWarnings("serial")
public class ConfigFilePath extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField text_handy_masterpath;
	private JTextField text_handy_datapath;
	private JTextField text_handy_ip;
	
	// ***** 다이얼 로그 띄우기 *****
	private JDialog dlg_help_info;
	private FileDialog filedialog;
	private JFileChooser jfiledialog;
	private JTextField text_handy_port;
	private JTextField text_shop_dbip;
	private JTextField text_shop_memnum;
	private JTextField text_shop_dbport;
	private JTextField text_shop_retime;
	private JCheckBox chkbox_shop_connect;
	
	private Properties config_file;
	/**
	 * Create the frame.
	 */
	public ConfigFilePath() {
		
		try {
		    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) { 
		    System.err.println("Cannot set look and feel:" + e.getMessage()); 
		}
		
		
		// 현재환면의 사이즈와 모니터 사이즈를 불러오기 우해 선언
		Dimension dim = new Dimension();
		Dimension dim1 = new Dimension();
		int xpos, ypos;
		
		//파일 다이얼로그 객체 생성
		filedialog = new FileDialog(ConfigFilePath.this, "폴더 선택", FileDialog.LOAD);
		jfiledialog = new JFileChooser();
		jfiledialog.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		
		// 메인프레임 사이즈 선언		
		setSize(400, 300);
		setTitle("환경설정");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setUndecorated(true); 
		//setBounds(100, 100, 450, 300);
		
		// 현재 모니터화면 사이즈 가져오기
		dim = Toolkit.getDefaultToolkit().getScreenSize();
				
		// 현재 프레임 사이즈 가져오기
		dim1 = getSize();
		
		// 화면을 중앙에 위치 시킴
		xpos = (int)(dim.getWidth()/2-dim1.getWidth()/2);
		ypos = (int)(dim.getHeight()/2-dim1.getHeight()/2);
		setLocation(xpos, ypos);	
		
		
		contentPane = new JPanel();
						
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));		
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel_center_shop = new JPanel();
		tabbedPane.addTab("\uC1FC\uD551\uBAB0 \uC635\uC158", null, panel_center_shop, null);
		panel_center_shop.setLayout(new MigLayout("", "[][grow][][grow][][]", "[][][][][][][grow]"));
		
		JLabel label_shop_dbip = new JLabel("DB IP");
		label_shop_dbip.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		panel_center_shop.add(label_shop_dbip, "cell 0 0,alignx trailing");
		
		text_shop_dbip = new JTextField();
		text_shop_dbip.setText("localhost");
		panel_center_shop.add(text_shop_dbip, "cell 1 0 3 1,growx");
		text_shop_dbip.setColumns(30);
		
		JLabel label_shop_dbport = new JLabel("DB PORT");
		label_shop_dbport.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		panel_center_shop.add(label_shop_dbport, "cell 4 0,alignx trailing");
		
		text_shop_dbport = new JTextField();
		text_shop_dbport.setText("1433");
		panel_center_shop.add(text_shop_dbport, "cell 5 0");
		text_shop_dbport.setColumns(5);
		
		JLabel label_shop_memnum = new JLabel("\uC2E0\uADDC \uACE0\uAC1D\uBC88\uD638");
		label_shop_memnum.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		panel_center_shop.add(label_shop_memnum, "cell 0 1,alignx trailing");
		
		text_shop_memnum = new JTextField();
		text_shop_memnum.setText("10");
		text_shop_memnum.setHorizontalAlignment(SwingConstants.CENTER);
		panel_center_shop.add(text_shop_memnum, "cell 1 1 2 1,growx");
		text_shop_memnum.setColumns(3);
		
		JLabel label_shop_memnum_info = new JLabel("\uC2E0\uADDC\uACE0\uAC1D \uACE0\uAC1D\uCE74\uB4DC \uC694\uCCAD \uC2DC \uC0DD\uC131 \uC2DC\uC791\uBC88\uD638");
		panel_center_shop.add(label_shop_memnum_info, "cell 3 1 3 1,alignx center");
		
		JLabel label_shop_retime = new JLabel("\uC571/\uD68C\uC6D0 \uAC31\uC2E0");
		label_shop_retime.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		panel_center_shop.add(label_shop_retime, "cell 0 2,alignx trailing");
		
		text_shop_retime = new JTextField();
		text_shop_retime.setText("20");
		text_shop_retime.setHorizontalAlignment(SwingConstants.CENTER);
		panel_center_shop.add(text_shop_retime, "cell 1 2");
		text_shop_retime.setColumns(3);
		
		JLabel label_shop_retimem = new JLabel("\uBD84");
		panel_center_shop.add(label_shop_retimem, "cell 2 2");
		
		JLabel label_shop_retime_info = new JLabel("\uB9E4\uCE6D\uB41C \uD68C\uC6D0/\uC571\uC124\uCE58 \uC815\uBCF4 \uAC31\uC2E0 \uC2DC\uAC04\uC124\uC815");
		panel_center_shop.add(label_shop_retime_info, "cell 3 2 3 1,alignx right");
		
		JLabel label_shop_con = new JLabel("\uD68C\uC6D0\uC790\uB3D9\uC5F0\uB3D9 \uC635\uC158");
		label_shop_con.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		panel_center_shop.add(label_shop_con, "cell 0 4,alignx trailing");
		
		chkbox_shop_connect = new JCheckBox("\uD68C\uC6D0\uAC00\uC785 \uBC0F \uC571 \uC124\uCE58 \uC2DC \uC790\uB3D9\uC5F0\uB3D9");
		chkbox_shop_connect.setSelected(true);
		panel_center_shop.add(chkbox_shop_connect, "cell 1 4 5 1,alignx left");
		
		JLabel label_connect_info = new JLabel("<html>\uC1FC\uD551\uBAB0 \uC5F0\uB3D9\uC5D0 \uD544\uC694\uD55C \uC678\uBD80\uB0B4\uBD80 \uD3EC\uD2B8\uB294 8683\uBC88 \uC785\uB2C8\uB2E4.<br>\r\n\uC1FC\uD551\uBAB0\uAD00\uB9AC\uC790 \uD398\uC774\uC9C0 > API \uAC1C\uBC1C\uC13C\uD130 > API URI \uC124\uC815\uC5D0\uC11C <br>\r\n\uC11C\uBC84\uC678\uBD80 \uC544\uC774\uD53C \uB610\uB294 DDNS \uC8FC\uC18C\uB97C \uC785\uB825\uD574\uC8FC\uC138\uC694<br>\r\n\uC608) tips000.iptime.org:8683/appinstall.php <- \uC774\uB807\uAC8C \uC785\uB825\uD574\uC8FC\uC138\uC694<br>\r\ntips000.iptime.org:8683/member.php<br>\r\ntips000.iptime.org:8683/order.php<br>\r\n</html>");
		label_connect_info.setHorizontalAlignment(SwingConstants.CENTER);
		label_connect_info.setBackground(SystemColor.info);
		label_connect_info.setOpaque(true);
		panel_center_shop.add(label_connect_info, "cell 0 6 6 1,grow");
		
		JPanel panel_center_handy = new JPanel();
		tabbedPane.addTab("\uD578\uB4DC\uD3F0 \uD1B5\uC2E0\uC124\uC815", null, panel_center_handy, null);
		panel_center_handy.setLayout(new MigLayout("", "[220px][22px,grow][57px][12px][49px]", "[15px][23px][23px][15px][23px][23px][23px][15px][21px]"));
		
		JLabel label_handy_master = new JLabel("PC 경로설정(Master)");
		label_handy_master.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		panel_center_handy.add(label_handy_master, "cell 0 0 3 1,grow");
		
		text_handy_masterpath = new JTextField();
		text_handy_masterpath.setFocusable(false);
		text_handy_masterpath.setEditable(false);
		panel_center_handy.add(text_handy_masterpath, "cell 0 1 3 1,growx,aligny center");
		//textField.setColumns(10);
		
		JButton btn_handy_masterpath = new JButton("...");
		panel_center_handy.add(btn_handy_masterpath, "cell 4 1,growx,aligny top");
		btn_handy_masterpath.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub				
				//filedialog.setVisible(true);
				int ret = jfiledialog.showOpenDialog(null);
				if(ret == jfiledialog.APPROVE_OPTION){
					text_handy_masterpath.setText(jfiledialog.getSelectedFile().toString());
				}
			}
		});
		
		Label label_handy_master_info = new Label("예) C:\\Handy\\Master");
		label_handy_master_info.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		panel_center_handy.add(label_handy_master_info, "cell 0 2 3 1,growx,aligny top");
		
		Label label_handy_data_info = new Label("예) C:\\Handy\\Data");
		label_handy_data_info.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		panel_center_handy.add(label_handy_data_info, "cell 0 5 3 1,growx,aligny top");
		
		JLabel label_handy_data = new JLabel("PC경로설정(Data)");
		label_handy_data.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		panel_center_handy.add(label_handy_data, "cell 0 3 3 1,grow");
		
		text_handy_datapath = new JTextField();
		//textField_1.setColumns(10);
		text_handy_datapath.setFocusable(false);
		text_handy_datapath.setEditable(false);
		panel_center_handy.add(text_handy_datapath, "cell 0 4 3 1,growx,aligny center");
		
		JButton btn_handy_datapath = new JButton("...");
		panel_center_handy.add(btn_handy_datapath, "cell 4 4,growx,aligny top");
		btn_handy_datapath.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int ret = jfiledialog.showOpenDialog(null);
				if(ret == jfiledialog.APPROVE_OPTION){
					text_handy_datapath.setText(jfiledialog.getSelectedFile().toString());
				}	
			}
		});
		
		Label label_handy_info = new Label("\uD734\uB300\uD3F0\uC5D0\uC11C \uD658\uACBD \uC124\uC815\uD574 \uC8FC\uC138\uC694");
		label_handy_info.setBackground(SystemColor.info);
		label_handy_info.setAlignment(Label.CENTER);
		label_handy_info.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		panel_center_handy.add(label_handy_info, "cell 0 6 5 1,growx,aligny top");
		
		JLabel label_handy_ip = new JLabel("\uD604\uC7AC IP");
		label_handy_ip.setHorizontalAlignment(SwingConstants.CENTER);
		label_handy_ip.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		panel_center_handy.add(label_handy_ip, "cell 0 7,grow");
		
		text_handy_ip = new JTextField();
		text_handy_ip.setHorizontalAlignment(SwingConstants.CENTER);
		text_handy_ip.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		//textField_2.setColumns(10);
		text_handy_ip.setFocusable(false);
		text_handy_ip.setEditable(false);
		panel_center_handy.add(text_handy_ip, "cell 0 8,grow");
		
		text_handy_port = new JTextField();
		text_handy_port.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		text_handy_port.setHorizontalAlignment(SwingConstants.CENTER);
		text_handy_port.setText("8681");
		text_handy_port.setFocusable(false);
		text_handy_port.setEditable(false);
		panel_center_handy.add(text_handy_port, "cell 2 8 3 1,grow");
		
		JLabel label_handy_port = new JLabel("Port");
		label_handy_port.setHorizontalAlignment(SwingConstants.CENTER);
		label_handy_port.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		panel_center_handy.add(label_handy_port, "cell 2 7 3 1,grow");
		
		JPanel panel_bottom = new JPanel();
		contentPane.add(panel_bottom, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("저장");
		btnNewButton.setPreferredSize(new Dimension(100, 23));
		panel_bottom.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("종료");
		btnNewButton_1.setPreferredSize(new Dimension(100, 23));
		panel_bottom.add(btnNewButton_1);
		btnNewButton_1.addActionListener(this);
		btnNewButton.addActionListener(this);
		
		
		config_file = new Properties();
		pathConfig();
	}
	
	public void pathConfig(){
		
		File file = new File("Config.dat");
			
		//파일이 존재 하지 않으면 신상품 등록에 제한을 줍니다.		
		if( !file.exists() ){			
			try {
				file.createNewFile();				
				config_file.load(new FileInputStream(file));
				
				config_file.setProperty("Master_Path", "C:\\Handy\\Master"); //마스터
				config_file.setProperty("Data_Path", "C:\\Handy\\Data"); //데이터
				config_file.setProperty("DBIP", "Localhost"); //DBIP				
				config_file.setProperty("DBPORT", "1433"); //DDPORT
				config_file.setProperty("MEMNUM", "10"); //MEMNUM
				config_file.setProperty("RETIME", "10"); //RETIME
				config_file.setProperty("CONNECT", "1"); //CONNECT
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();	
				return;
			}			
		}
		
		System.out.println(file.getPath());
		try {
			
			config_file.load(new FileInputStream(file));
			text_handy_masterpath.setText("c:\\handy\\master");
			text_handy_datapath.setText("c:\\handy\\data");
						
			text_shop_dbip.setText(config_file.getProperty("DBIP"));
			text_shop_dbport.setText(config_file.getProperty("DBPORT"));
			text_shop_memnum.setText(config_file.getProperty("MEMNUM"));
			text_shop_retime.setText(config_file.getProperty("RETIME"));
			
			if(config_file.getProperty("CONNECT").equals("0")){
				chkbox_shop_connect.setSelected(false);
			}else{
				chkbox_shop_connect.setSelected(true);
			}
			
			/*FileInputStream fis = new FileInputStream(file.getPath());
			BufferedReader bufferReader = new BufferedReader(new InputStreamReader(fis, "euc-kr"));
			String temp="";						
				int i = 0;
				while( (temp = bufferReader.readLine()) != null ) {				
					if(i==0){					
						text_handy_masterpath.setText(temp.toString());
					}else if( i == 1 ){
						text_handy_datapath.setText(temp.toString());
					}else if( i == 2 ){
						textField_2.setText(temp.toString());
					}
					i++;
				}					
			bufferReader.close();
			fis.close();*/
		} catch (Exception e) {
				e.printStackTrace();
		}		
		
		text_handy_ip.setText(getLocalServerIp());		
	}
	
	 /* 현재 서버의 IP 주소를 가져옵니다.
	 * 
	 * @return IP 주소
	 */
	private String getLocalServerIp()
	{
		try
		{
		    for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();)
		    {
		        NetworkInterface intf = en.nextElement();
		        for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();)
		        {
		            InetAddress inetAddress = enumIpAddr.nextElement();
		            if (!inetAddress.isLoopbackAddress() && !inetAddress.isLinkLocalAddress() && inetAddress.isSiteLocalAddress())
		            {
		            	return inetAddress.getHostAddress().toString();
		            }
		        }
		    }
		}
		catch (SocketException ex) {}
		return null;
	}
	
	
	public void configFileSave(){
		//파일명 지정합니다. 매입 IPGO.DAT 반품 BANPUM.DAT 가격 PRICE.DAT 재고 JEGO.DAT 발주 BALJU.DAT
		File file = new File("Config.dat");
				
		config_file.setProperty("Master_Path", text_handy_masterpath.getText()); //마스터
		config_file.setProperty("Data_Path", text_handy_datapath.getText()); //데이터
		config_file.setProperty("DBIP", text_shop_dbip.getText()); //DBIP				
		config_file.setProperty("DBPORT", text_shop_dbport.getText()); //DDPORT
		config_file.setProperty("MEMNUM", text_shop_memnum.getText()); //MEMNUM
		config_file.setProperty("RETIME", text_shop_retime.getText()); //RETIME
		
		String con = "0";
		if(chkbox_shop_connect.isSelected()){
			con = "1";
		}		
		config_file.setProperty("CONNECT", con); //CONNECT
		
		try {
			config_file.store(new FileOutputStream(file), "환경설정 저장");
		} catch (IOException e1) {
			// TODO Auto-generated catch block			
			show_Dialog("저장 오류", e1.getMessage());
			return;
		}
		show_Dialog("저장 완료", "저장이 완료 되었습니다.");
	}
	
	/**
	 * 기본 다이얼 로그 띄우기
	 * 
	 * 
	 * */	
	public void show_Dialog(String title, String content){ 
		
        dlg_help_info= new JDialog(this, title);                 
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
        int x = getX()+getWidth()/2-150; 
        int y = getY()+getHeight()/2-75; 
                 
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

	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		switch (e.getActionCommand()){
		
		case "저장":
			configFileSave();
			break;
		case "종료":
			this.dispose();
			break;
		}
	}
}
