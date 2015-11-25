import it.sauronsoftware.ftp4j.FTPAbortedException;
import it.sauronsoftware.ftp4j.FTPClient;
import it.sauronsoftware.ftp4j.FTPDataTransferException;
import it.sauronsoftware.ftp4j.FTPException;
import it.sauronsoftware.ftp4j.FTPFile;
import it.sauronsoftware.ftp4j.FTPIllegalReplyException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FtpConnect{

	static String serverName = "�̹��� ����";
	static String ftpIP = "211.233.63.24";
	static String ftpID = "tipos";
	static String ftpPW = "k5749948";
	static String ftpLocalPath = ".";
	static String ftpServerPath = "main_goods";
	FTPClient ftpConn = null;
	
	//private JFrame jframe;
	
	// �ʱ�ȭ
	public FtpConnect() {
		
		//jframe = frame;
		System.out.println(serverName + " ���� FTP ���� ���� ");
		ftpConn = new FTPClient();
		try {
			ftpConn.connect(ftpIP);
			ftpConn.login(ftpID, ftpPW);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FTPIllegalReplyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FTPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	// ������ ����
	public void connectStart() {
		
		try {
			
			// ���� �õ���, �����ߴ��� ���� �ڵ� Ȯ��
			if (!ftpConn.isConnected()) {
				ftpConn.disconnect(true);
				//JOptionPane.showMessageDialog(jframe, "���ӽ���!!");
				System.exit(1);
			}
		} catch (IOException ioe) {
			if (ftpConn.isConnected()) {
				try {
					try {
						ftpConn.disconnect(true);
					} catch (IllegalStateException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (FTPIllegalReplyException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (FTPException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} catch (IOException f) {
					//
				}
			}
			System.err.println("������ ������ �� �����ϴ�");
			System.exit(1);
		} catch (FTPException e) {

		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FTPIllegalReplyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// FTP�� ls ���, ��� ���� ����Ʈ�� �����´�
	public FTPFile[] list() {
		
		FTPFile[] files = null;		
		try{
		files = ftpConn.list();
		
		return files;
		
		}catch(Exception ex){
			return null;
		}
	}

	//������ ���ε� �մϴ�.
	public void setFileUpLoad(File uploadFile){
		
		File file = uploadFile;

		try {
			
			ftpConn.upload(file);
			
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FTPIllegalReplyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FTPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FTPDataTransferException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FTPAbortedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	// ������ ���� �޴´�
	public void getFileDownLoad(String source, String target) {

		File file = new File(target);

		try {
			
			ftpConn.download(source, file);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FTPIllegalReplyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FTPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FTPDataTransferException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FTPAbortedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// ���� ���丮 �̵�
	public void cd(String path) {
		try {
			ftpConn.changeDirectory(path);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FTPIllegalReplyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FTPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// �����κ��� ������ �ݴ´�
	private void disconnect() {
		try {
			ftpConn.disconnect(true);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FTPIllegalReplyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FTPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//main ���丮�����մϴ�.
	public void changeCD(String cd){
		
		switch(cd){
		case "main":
			this.cd(ftpServerPath);
			break;
		case "home":
			this.cd(ftpLocalPath);
			break;
		}	
	}
	
	public void testftp() {

		//FtpConnect tf = new FtpConnect();
		//tf.cd(ftpServerPath);

		//FTPFile[] file = tf.list();
		//System.out.println(file.length);
		/*for (FTPFile ftpFile : file) {
			System.out.println(ftpFile.getName());
		}*/
		//String ip, String dbname, String dbid, String dbpw
		/*Ms_Connect mc = new Ms_Connect();
		String query = "select * from goods where barcode='88006611' ";
		ArrayList<HashMap<String, String>> al = mc.connection(query);
		
		System.out.println(String.valueOf(al.size()));
		for(int i=0; i < al.size(); i++){
			
			HashMap<String, String> map = new HashMap<String, String>();
			map = al.get(i);
			System.out.println(map.get("BarCode"));			
		}		*/

		//tf.disconnect();
	}

}
