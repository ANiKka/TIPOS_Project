import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;


/**
 * DB���� ���α׷�
 * <pre>
 * TIPOS ���� ���� DB �ۼ�
 * </pre>
 *
 * <pre>
 * <b>History:</b>
 *    �ۼ��� ANikka , 1.0.5, 2016.4.11 �ʱ��ۼ�
 * </pre>
 *
 * @author ���� ������ ANikka
 * @version 1.0.5, 2016.4.11 �ּ� ����
 * @see    None
 */
public class Ms_Connect {
	
	int errCode =0;
    String errMsg;
    
    ResultSet rs = null;
    private ArrayList<HashMap<String, String>> json;
    private HashMap<String, String> json_one;
    private String ip;
    private String port;
	private String dbname;
	private String dbid;
	private String dbpw;	
	private String query;
	//private JSONArray json;
	
	/**
	 * ���� ������ �����մϴ�.
	 *
	 * @param ip ���� ip�ּ�
	 * @return void
 	 * @exception none
	 */
	public void setImageSetting(){
		ip = Server_Config.getIMAGE_IP();		
		port = Server_Config.getIMAGE_PORT();
		dbname = Server_Config.getIMAGE_DBNAME();
		dbid = Server_Config.getIMAGE_DBID();
		dbpw = Server_Config.getIMAGE_DBPW();		
	}
	
		
	public void setMainSetting(){
		
		ip = Server_Config.getSERVER_IP();		
		port = Server_Config.getSERVER_PORT();
		dbname = Server_Config.getSERVER_DBNAME();
		dbid = Server_Config.getSERVER_DBID();
		dbpw = Server_Config.getSERVER_DBPW();
	}
	
	/**
	 * DB���� Select��
	 *
	 * @param query �˻� ������ �Ѱܾ� �մϴ�.
	 * @return ArrayList<HashMap<String, String>>
	 * @exception 0 : ����, 1: SQLException, 2: Exception
	 */
	public ArrayList<HashMap<String, String>> connection(String query) {
		
		this.query = query;
		errCode = 0;
    	Connection conn = null;
    	    	
    	//���üũ
    	if(connect_errorCheck()){
    	try {
    	    Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
    	    System.out.println("MSSQL  MSSQL driver load");

    	    String url = "jdbc:jtds:sqlserver://" +ip+":"+port+"/"+ dbname;
    	    conn = DriverManager.getConnection(url, dbid, dbpw);
    	    System.out.println("MSSQL  MSSQL open: " + url);
    	    
    	    Statement stmt = conn.createStatement();
        	System.out.println("MSSQL  query: " + this.query );
            rs = stmt.executeQuery(this.query);	            
        	json = ResultSetConverter.convert(rs);

			conn.close();
   	 	} catch (SQLException e) {
    	    System.out.println("Error connection : " + e.getMessage());
    	    errCode = 1;
    	    errMsg = e.getMessage();
    	    return null;
    	} catch (Exception e) {
    	    System.out.println("Error connection : " + e.getMessage());	
    	    errCode = 2;
    	    errMsg = e.getMessage();
    	    return null;
    	}
    	return json;
    	}else{
    		return null;
    	}
    }	
	
	/**
	 * DB���� ���ٰ˻� ��
	 *
	 * @param query �˻� ������ �Ѱܾ� �մϴ�.
	 * @return HashMap<String, String>
	 * @exception 0 : ����, 1: SQLException, 2: Exception
	 */
	public HashMap<String, String> selectQueryOne(String query) {			
			this.query = query;
			errCode = 0;
	    	Connection conn = null;
	    	    	
	    	//���üũ
	    	if(connect_errorCheck()){    	
	    	
	    	try {
	    	    Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
	    	    System.out.println("MSSQL  MSSQL driver load");
	
	    	    String url = "jdbc:jtds:sqlserver://" +ip+":"+port+"/"+ dbname;
	    	    conn = DriverManager.getConnection(url, dbid, dbpw);
	    	    System.out.println("MSSQL  MSSQL open: " + url);
	    	    
	    	    Statement stmt = conn.createStatement();
	        	System.out.println("MSSQL  query: " + this.query );
	            rs = stmt.executeQuery(this.query);
	        	json_one = ResultSetConverter.convertOne(rs);	
				conn.close();
	   	 	} catch (SQLException e) {
	    	    System.out.println("Error connection : " + e.getMessage());
	    	    errCode = 1;
	    	    errMsg = e.getMessage();
	    	} catch (Exception e) {
	    	    System.out.println("Error connection : " + e.getMessage());	
	    	    errCode = 2;
	    	    errMsg = e.getMessage();
	    	}
	    	return json_one;
	    	}else{
	    		return null;
	    	}
	    }	
	
	/**
	 * DB���� Update��
	 *
	 * @param query �˻� ������ �Ѱܾ� �մϴ�.
	 * @return int : ��� ����
	 * @exception 0 : ����, 1: SQLException, 2: Exception
	 */
	public int connect_update(String query) {
		
		this.query = query;
		errCode = 0;
    	Connection conn = null;
    	    	
    	//���üũ
    	if(connect_errorCheck()){    	
    	
    	try {
    	    Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
    	    System.out.println("MSSQL  MSSQL driver load");

    	    String url = "jdbc:jtds:sqlserver://" +ip+":"+port+"/"+ dbname;
    	    conn = DriverManager.getConnection(url, dbid, dbpw);
    	    System.out.println("MSSQL  MSSQL open: " + url);
    	    
    	    Statement stmt = conn.createStatement();
        	System.out.println("MSSQL  query: " + this.query );
            //stmt.executeQuery(this.query);            
            stmt.execute(this.query);
            
			conn.close();
   	 	} catch (SQLException e) {
    	    System.out.println("Error connection : " + e.getMessage());
    	    errCode = 1;
    	    errMsg = e.getMessage();
    	} catch (Exception e) {
    	    System.out.println("Error connection : " + e.getMessage());	
    	    errCode = 2;
    	    errMsg = e.getMessage();
    	}    	
    	return errCode;
    	}else{
    		
    		return 3;
    	}
    }	
	
	
	/**
	 * DB���� �迭 ���� Insert,Update��
	 *
	 * @param query_won ���� ������ �Ѱܾ� �մϴ�.
	 * @return int : ��� ����
	 * @exception 0 : ����, 1: SQLException, 2: Exception
	 */
	public int connect_update(String[] query_won){
		
		//String [] query_multi;
		errCode = 0;
    	Connection conn = null;
    	    	
    	//���üũ
    	if(connect_errorCheck()){    	
    	
	    	try {
	    	    Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
	    	    System.out.println("MSSQL  MSSQL driver load");
	
	    	    String url = "jdbc:jtds:sqlserver://" +ip+":"+port+"/"+ dbname;
	    	    conn = DriverManager.getConnection(url, dbid, dbpw);
	    	    System.out.println("MSSQL  MSSQL open: " + url);
	    	    
	    	    Statement stmt = conn.createStatement();
	    	    for(String query: query_won){
	    	    	System.out.println("MSSQL  query: " + query );
	    	    }
	        	for(String query_multi : query_won){
	        		stmt.addBatch(query_multi);
	        		//System.out.println(query_multi);
	        	}
	        	
	        	stmt.executeBatch();     
	        	
				conn.close();
	   	 	} catch (SQLException e) {
	   	 		
	    	    System.out.println("Error connection : " + e.getMessage());
	    	    errCode = 1;
	    	    errMsg = e.getMessage();	    	    
	    	} catch (Exception e) {
	    		
	    	    System.out.println("Error connection : " + e.getMessage());	
	    	    errCode = 2;
	    	    errMsg = e.getMessage();
	    	}    	
	    	
	    	return errCode;	    	
    	}else{
    		
    		return 3; //0 ���� , 1.Sql ���� , 2 Exception ���� , 3 Ŀ�ؼǿ��� 
    	}
	}
	
	
	/**
	 * DB���� List Insert,Update��
	 *
	 * @param query_won ���� ������ �־�� �մϴ�.
	 * @return int : ��� ����
	 * @exception 0 : ����, 1: SQLException, 2: Exception
	 */
	public int connect_update(ArrayList<String> query_won){
	
		//String [] query_multi;
		errCode = 0;
    	Connection conn = null;
    	    	
    	//���üũ
    	if(connect_errorCheck()){    	
    	
	    	try {
	    	    Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
	    	    System.out.println("MSSQL  MSSQL driver load");
	
	    	    String url = "jdbc:jtds:sqlserver://" +ip+":"+port+"/"+ dbname;
	    	    conn = DriverManager.getConnection(url, dbid, dbpw);
	    	    System.out.println("MSSQL  MSSQL open: " + url);
	    	    
	    	    Statement stmt = conn.createStatement();
	        	System.out.println("MSSQL  query: " + query_won.toString() );
	            
	        	for(String query_multi : query_won){
	        		stmt.addBatch(query_multi);
	        		//System.out.println(query_multi);
	        	}
	        	
	        	stmt.executeBatch();
				conn.close();
				
	   	 	} catch (SQLException e) {
	   	 		
	    	    System.out.println("Error connection : " + e.getMessage());
	    	    errCode = 1;
	    	    errMsg = e.getMessage();	    	    
	    	} catch (Exception e) {
	    		
	    	    System.out.println("Error connection : " + e.getMessage());	
	    	    errCode = 2;
	    	    errMsg = e.getMessage();
	    	}    	
	    	
	    	return errCode;	    	
    	}else{
    		
    		return 3; //0 ���� , 1.Sql ���� , 2 Exception ���� , 3 Ŀ�ؼǿ��� 
    	}
	}
	
	
	public Connection getConnection(){
		
		//String [] query_multi;
		errCode = 0;
    	Connection conn = null;
    	    	
    	//���üũ
    	if(connect_errorCheck()){    	
    		
	    	try {
	    	    Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();	    	    
	
	    	    String url = "jdbc:jtds:sqlserver://" +ip+":"+port+"/"+ dbname;
	    	    conn = DriverManager.getConnection(url, dbid, dbpw);
	    	    conn.setAutoCommit(false);
	    	    
	   	 	} catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
	   	 		
	    	    System.out.println("Error connection : " + e.getMessage());
	    	    errCode = 1;
	    	    errMsg = e.getMessage(); 
	    	} 
	    	
    	}else{    		
    		
    		errCode = 3; //0 ���� , 1.Sql ���� , 2 Exception ���� , 3 Ŀ�ؼǿ���
    		return null;
    	}
		
		return conn;
	}
	
	public boolean connect_errorCheck(){
		
		//setMainSetting();
		System.out.println(ip+"/"+port+"/"+dbname+"/"+dbid+"/"+dbpw);
		SocketAddress socketAddress;
		Socket socket;	
		
		try{
			socketAddress = new InetSocketAddress(ip, Integer.parseInt(port));
			socket = new Socket();
		}catch(NumberFormatException e){
			errCode = 1;
    	    errMsg = e.getMessage();
			return false;
		}
		
		try {
			//socket.setSoTimeout(5);           	/* InputStream���� �������������� timeout */	
			socket.connect(socketAddress, 5000); 	/* socket���� ��ü������ timeout */			
			return true;
		} catch (SocketException e){
			errCode = 3;
    	    errMsg = e.getMessage();
			return false;
		} catch (IOException e) {
			errCode = 3;
    	    errMsg = e.getMessage();
			return false;
		} finally {		
			try {
				socket.close();
			} catch (IOException e) {
				errCode = 3;
	    	    errMsg = e.getMessage();		
			}
		}		
	}
}
