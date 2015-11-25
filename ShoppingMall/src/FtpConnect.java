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

	static String serverName = "이미지 서버";
	static String ftpIP = "211.233.63.24";
	static String ftpID = "tipos";
	static String ftpPW = "k5749948";
	static String ftpLocalPath = ".";
	static String ftpServerPath = "main_goods";
	FTPClient ftpConn = null;
	
	//private JFrame jframe;
	
	// 초기화
	public FtpConnect() {
		
		//jframe = frame;
		System.out.println(serverName + " 파일 FTP 전송 시작 ");
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

	// 서버로 연결
	public void connectStart() {
		
		try {
			
			// 연결 시도후, 성공했는지 응답 코드 확인
			if (!ftpConn.isConnected()) {
				ftpConn.disconnect(true);
				//JOptionPane.showMessageDialog(jframe, "접속실패!!");
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
			System.err.println("서버에 연결할 수 없습니다");
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

	// FTP의 ls 명령, 모든 파일 리스트를 가져온다
	public FTPFile[] list() {
		
		FTPFile[] files = null;		
		try{
		files = ftpConn.list();
		
		return files;
		
		}catch(Exception ex){
			return null;
		}
	}

	//파일을 업로드 합니다.
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
	
	// 파일을 전송 받는다
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

	// 서버 디렉토리 이동
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

	// 서버로부터 연결을 닫는다
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

	//main 디렉토리변경합니다.
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
