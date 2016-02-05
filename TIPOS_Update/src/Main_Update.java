import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
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
import javax.swing.UIManager;

import it.sauronsoftware.ftp4j.FTPAbortedException;
import it.sauronsoftware.ftp4j.FTPClient;
import it.sauronsoftware.ftp4j.FTPDataTransferException;
import it.sauronsoftware.ftp4j.FTPException;
import it.sauronsoftware.ftp4j.FTPFile;
import it.sauronsoftware.ftp4j.FTPIllegalReplyException;
import it.sauronsoftware.ftp4j.FTPListParseException;

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
		
		try {
		    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) { 
		    System.err.println("Cannot set look and feel:" + e.getMessage()); 
		}
        
		//ȭ�� �߾ӿ� ��ġ 
       Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension dm = tk.getScreenSize();
        Dimension fm = this.getSize();
        this.setLocation((int) (dm.getWidth() / 2 - fm.getWidth() / 2), (int) (dm.getHeight() / 2 - fm.getHeight() / 2));		
		
		JLabel label_title = new JLabel("\uC1FC\uD551\uBAB0 \uD504\uB85C\uADF8\uB7A8 \uC5C5\uB370\uC774\uD2B8 \uCCB4\uD06C");
		label_title.setFont(new Font("���� ���", Font.BOLD, 15));
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
		
		//���� ���α׷��� ���� ������ Ȯ�� �մϴ�.
		String query = "Select * From Version";
		HashMap<String, String> map = mscon.selectQueryOne(query);		
		label_state.setText("�������α׷��� ������ Ȯ���մϴ�....");		
		
		startThread(500);		
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
		label_state.setText("�űԹ��� üũ��....  ������� : "+version_manage );		
		this.repaint();
		startThread(1000);
				
		//����üũ�� �մϴ�.
		if(version_manage.equals(map.get("EManage_Version"))){
			
			label_state.setText("�ֽ� �����Դϴ�.");
			startThread(1000);
			startProgram();
			return;
		}else{
			label_state.setText("�ű� ������ �ֽ��ϴ�.");
			label_state.setText("������Ʈ�� ���� �մϴ�.");
			
			startThread(3000);
		}
		
		String newVer = map.get("EManage_Version");
		
		//������ ���α׷� ������ ��ġ ���� �ʴ� �ٸ� ������Ʈ �մϴ�.
		FTPClient ftp_conn = new FTPClient();		
		
		try {
			
			ftp_conn.connect("14.38.161.45", 8000);
			ftp_conn.login("xodlfvhtm", "tips");				
			
			System.out.println(ftp_conn.currentDirectory());		
			
			//��� ����
			ftp_conn.changeDirectory("/"+newVer);
			
			//��� ���� �ҷ�����
			FTPFile[] fileList = ftp_conn.list();
			
			
			
			
			ftp_conn.disconnect(true);
			
			
		} catch (IllegalStateException | IOException | FTPIllegalReplyException | FTPException | FTPDataTransferException | FTPAbortedException | FTPListParseException e) {
			// TODO Auto-generated catch block					
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "������Ʈ�� ���� �߽��ϴ�. \r\n �������� : "+e.getMessage());	
		}
		
		//���������� Ʋ���ٸ� ���������� �´� ������ �ҷ��ɴϴ�.
		
		
		//����Ǿ��� ���μ����� �����մϴ�.
		
		
		//�ӽ� �����������մϴ�.
		
		
		//������ �ٿ� �޽��ϴ�.
		
		
		//������ �ű�ϴ�.
		
		
		//���������� ��Ĩ�ϴ�.
		
		
		//���α׷� ���� �մϴ�.
		
		
		//������Ʈ ���α׷��� ���� �մϴ�.	
		startProgram();
	}
		
	public void startThread(int time){		
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	public void startProgram(){
			String exeFile = "tips_shoppingmall.exe";
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

