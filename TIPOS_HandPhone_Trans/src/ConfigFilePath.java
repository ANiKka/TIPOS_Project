
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FileDialog;
import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
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

import javax.swing.SwingConstants;

import java.awt.SystemColor;

@SuppressWarnings("serial")
public class ConfigFilePath extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	
	// ***** 다이얼 로그 띄우기 *****
	private JDialog dlg_help_info;
	private FileDialog filedialog;
	private JFileChooser jfiledialog;
	private JTextField textField_3;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConfigFilePath frame = new ConfigFilePath();					 
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ConfigFilePath() {
		// 현재환면의 사이즈와 모니터 사이즈를 불러오기 우해 선언
		Dimension dim = new Dimension();
		Dimension dim1 = new Dimension();
		int xpos, ypos;
		
		//파일 다이얼로그 객체 생성
		filedialog = new FileDialog(ConfigFilePath.this, "폴더 선택", FileDialog.LOAD);
		jfiledialog = new JFileChooser();
		jfiledialog.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		
		// 메인프레임 사이즈 선언
		setSize(380, 265);
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
		getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("저장");
		btnNewButton.setBounds(12, 228, 167, 23);
		getContentPane().add(btnNewButton);
		btnNewButton.addActionListener(this);
		
		JButton btnNewButton_1 = new JButton("종료");
		btnNewButton_1.setBounds(205, 228, 167, 23);
		getContentPane().add(btnNewButton_1);
		contentPane.setLayout(null);
		btnNewButton_1.addActionListener(this);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 10, 360, 210);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("PC 경로설정(Master)");
		lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		lblNewLabel.setBounds(0, 0, 299, 15);
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(0, 20, 299, 21);
		textField.setFocusable(false);
		textField.setEditable(false);
		panel.add(textField);
		//textField.setColumns(10);
		
		JButton btnNewButton_2 = new JButton("...");
		btnNewButton_2.setBounds(311, 19, 49, 23);
		panel.add(btnNewButton_2);
		btnNewButton_2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub				
				//filedialog.setVisible(true);
				int ret = jfiledialog.showOpenDialog(null);
				if(ret == jfiledialog.APPROVE_OPTION){
					textField.setText(jfiledialog.getSelectedFile().toString());
				}
			}
		});
		
		Label label = new Label("예) C:\\Handy\\Master");
		label.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		label.setBounds(0, 43, 299, 23);
		panel.add(label);
		
		Label label_1 = new Label("예) C:\\Handy\\Data");
		label_1.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		label_1.setBounds(0, 115, 299, 23);
		panel.add(label_1);
		
		JLabel lblPcdata = new JLabel("PC경로설정(Data)");
		lblPcdata.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		lblPcdata.setBounds(0, 72, 299, 15);
		panel.add(lblPcdata);
		
		textField_1 = new JTextField();
		//textField_1.setColumns(10);
		textField_1.setFocusable(false);
		textField_1.setEditable(false);
		textField_1.setBounds(0, 92, 299, 21);
		panel.add(textField_1);
		
		JButton button = new JButton("...");
		button.setBounds(311, 91, 49, 23);
		panel.add(button);
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int ret = jfiledialog.showOpenDialog(null);
				if(ret == jfiledialog.APPROVE_OPTION){
					textField_1.setText(jfiledialog.getSelectedFile().toString());
				}	
			}
		});
		
		Label label_3 = new Label("\uD734\uB300\uD3F0\uC5D0\uC11C \uD658\uACBD \uC124\uC815\uD574 \uC8FC\uC138\uC694");
		label_3.setBackground(SystemColor.info);
		label_3.setAlignment(Label.CENTER);
		label_3.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		label_3.setBounds(0, 144, 360, 23);
		panel.add(label_3);
		
		JLabel lblHandphonemaster = new JLabel("\uD604\uC7AC IP");
		lblHandphonemaster.setHorizontalAlignment(SwingConstants.CENTER);
		lblHandphonemaster.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		lblHandphonemaster.setBounds(0, 169, 220, 15);
		panel.add(lblHandphonemaster);
		
		textField_2 = new JTextField();
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		//textField_2.setColumns(10);
		textField_2.setFocusable(false);
		textField_2.setEditable(false);
		textField_2.setBounds(0, 189, 220, 21);
		panel.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		textField_3.setHorizontalAlignment(SwingConstants.CENTER);
		textField_3.setText("8681");
		textField_3.setFocusable(false);
		textField_3.setEditable(false);
		textField_3.setBounds(242, 189, 118, 21);
		panel.add(textField_3);
		
		JLabel lblPort = new JLabel("Port");
		lblPort.setHorizontalAlignment(SwingConstants.CENTER);
		lblPort.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		lblPort.setBounds(244, 169, 116, 15);
		panel.add(lblPort);
		
		pathConfig();
	}
	
	public void pathConfig(){
		
		File file = new File("Config.dat");
			
		//파일이 존재 하지 않으면 신상품 등록에 제한을 줍니다.
		
		if( !file.exists() ){			
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();	
				return;
			}			
		}				
		
		System.out.println(file.getPath());
		try {
			FileInputStream fis = new FileInputStream(file.getPath());
			BufferedReader bufferReader = new BufferedReader(new InputStreamReader(fis, "euc-kr"));
			String temp="";						
				int i = 0;
				while( (temp = bufferReader.readLine()) != null ) {				
					if(i==0){					
						textField.setText(temp.toString());
					}else if( i == 1 ){
						textField_1.setText(temp.toString());
					}/*else if( i == 2 ){
						textField_2.setText(temp.toString());
					}*/
					i++;
				}					
			bufferReader.close();
			fis.close();
		} catch (Exception e) {
				e.printStackTrace();
		}		
		
		textField_2.setText(getLocalServerIp());
		
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
		
		String contents;
		
		contents = textField.getText();
		contents += "\r\n" + textField_1.getText();
		//contents += textField_2.getText();		
		
		try {
			//FileWriter fw = new FileWriter(file, true);
			FileOutputStream fos = new FileOutputStream(file);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos, "euc-kr"));
							
			bw.write(contents);		
			
			bw.close();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
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
