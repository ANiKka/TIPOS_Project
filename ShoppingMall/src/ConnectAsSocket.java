import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;

public class ConnectAsSocket {
	
	public String shopPart;
	
	private String ip;
	private String port;
	private String dbname;
	private String dbid;
	private String dbpw;
	
	
	public ConnectAsSocket(String shopPart){		
		// ���� �ּҸ� �޽��ϴ�. ���弭�� // FTP���� // ���θ�����
		this.shopPart = shopPart;
		
		switch(shopPart){
		case "shopMain":
			shopMain();
			break;
		case "shopFTPServer":
			shopFTPServer();
			break;
		case "shopPingMall":
			shopPingMall();
			break;			
		case "shopImageServer":
			shopImageServer();
			break;
		}
	}		 
	
	//���� �ּҸ� �����մϴ�.
	private void shopMain(){
				
		ip = Server_Config.getSERVER_IP();		
		port = Server_Config.getSERVER_PORT();
		dbname = Server_Config.getSERVER_DBNAME();
		dbid = Server_Config.getSERVER_DBID();
		dbpw = Server_Config.getSERVER_DBPW();
	}
	
	
	private void shopPingMall(){
		
		
	}
	
	//FTP����
	private void shopFTPServer(){
		
		ip = Server_Config.getFTPIP();		
		port = String.valueOf(Server_Config.getFTPPORT());		
		dbid = Server_Config.getFTPID();
		dbpw = Server_Config.getFTPPW();
		
	}
	
	//�̹��� ����
	private void shopImageServer(){
		
		ip = Server_Config.getIMAGE_IP();		
		port = Server_Config.getIMAGE_PORT();
		dbname = Server_Config.getIMAGE_DBNAME();
		dbid = Server_Config.getIMAGE_DBID();
		dbpw = Server_Config.getIMAGE_DBPW();
		
	}
	
	// ���� ������ �����մϴ�.
	// ������ �õ� �մϴ�.
	//����� �����մϴ�.	
	public boolean connect_Check(){
			
			//setMainSetting();
			System.out.println(ip+"/"+port+"/"+dbname+"/"+dbid+"/"+dbpw);
			SocketAddress socketAddress;
			Socket socket;	
			
			try{
				socketAddress = new InetSocketAddress(ip, Integer.parseInt(port));
				socket = new Socket();
			}catch(NumberFormatException e){
				e.printStackTrace();
				return false;
			}
			
			try {
				//socket.setSoTimeout(5);           	/* InputStream���� �������������� timeout */
				socket.connect(socketAddress, 5000); 	/* socket���� ��ü������ timeout */	
				return true;
			} catch (SocketException e){
				e.printStackTrace();
				return false;
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			} finally {		
				try {
					socket.close();					
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	
}
