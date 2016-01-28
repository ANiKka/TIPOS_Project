import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;

public class Main_Update extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 15971534549L;
	private JLabel label_state;
	private JLabel label_trandata_byte;

	private Ms_Connect mscon;
	
	public Main_Update(){
		
		mscon = new Ms_Connect();
		
		getContentPane().setLayout(null);	
				
		setSize(450, 200);
		
		JLabel label_title = new JLabel("\uC1FC\uD551\uBAB0 \uD504\uB85C\uADF8\uB7A8 \uC5C5\uB370\uC774\uD2B8 \uCCB4\uD06C");
		label_title.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		label_title.setHorizontalAlignment(SwingConstants.CENTER);
		label_title.setBackground(SystemColor.info);
		label_title.setOpaque(true);
		label_title.setBounds(12, 10, 410, 32);
		getContentPane().add(label_title);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(12, 120, 410, 32);
		getContentPane().add(progressBar);
		
		label_state = new JLabel("\uC5C5\uB370\uC774\uD2B8 \uD655\uC778\uC911....");
		label_state.setBounds(22, 52, 233, 33);
		getContentPane().add(label_state);
		
		JLabel label_trandata = new JLabel("\uC804\uC1A1\uC18D\uB3C4:");
		label_trandata.setBounds(32, 95, 57, 15);
		getContentPane().add(label_trandata);
		
		label_trandata_byte = new JLabel("0");
		label_trandata_byte.setHorizontalAlignment(SwingConstants.RIGHT);
		label_trandata_byte.setBounds(81, 95, 97, 15);
		getContentPane().add(label_trandata_byte);
		
		JLabel label_trandata_sux = new JLabel("kb/s");
		label_trandata_sux.setBounds(190, 95, 57, 15);
		getContentPane().add(label_trandata_sux);
		setVisible(true);
		
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				startUpdate();
			}
		});
		thread.start();
	}	
		
	private void startUpdate(){

		startThread();
		//현재 프로그램의 버전 정보를 확인 합니다.
		String query = "Select * From Version";
		HashMap<String, String> map = mscon.selectQueryOne(query);		
		label_state.setText("관리프로그램의 버전을 확인합니다....");		
		
		startThread();		
		Properties config_file = new Properties();				
		File file = new File("Config.dat");		
		if( !file.exists() ){
			startProgram();
			System.exit(0);
		}
			
		try {
			config_file.load(new FileInputStream(file));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			startProgram();
			System.exit(0);
		}	
		
		String version_manage = config_file.getProperty("Version");
		label_state.setText("신규버전 체크중....");
		this.repaint();
		startThread();
		
		//버전체크를 합니다.
		if(version_manage.equals(map.get("EManage_Version"))){
			
			label_state.setText("최신 버전입니다.");			
			startThread();
			
			startProgram();
			return;
			
		}else{			
			label_state.setText("신규 버전이 있습니다.");
			label_state.setText("업데이트를 진행 합니다.");		
		}
		
		System.out.println(map.get("EManage_Version"));
		System.out.println(map.get("ETran_Version"));
		
		//서버의 프로그램 정보와 일치 하지 않는 다면 업데이트 합니다.
		
		//버전정보가 틀리다면 버전정보에 맞는 폴더를 불러옵니다.
		
		//샐행되어진 프로세스를 종료합니다.
		
		//임시 폴더를생성합니다.
		
		//파일을 다운 받습니다.
		
		//파일을 옮깁니다.
		
		//버전정보를 고칩니다.
		
		//프로그램 실행 합니다.
		
		//업데이트 프로그램을 종료 합니다.
		
		
	}
		
	public void startThread(){		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	
	public void startProgram(){
			String exeFile = "C:\\Program Files\\Tips\\Tips_ShoppingMall\\tips_shoppingmall.exe";
			System.out.println("exeFile: " + exeFile);		
			try {
				Process process = new ProcessBuilder(exeFile).start();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(this, e.getMessage());
			}         
			
			System.exit(0);	
	}
		
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {	
					new Main_Update();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}

