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
 * DB접속 프로그램
 * <pre>
 * TIPOS 매장 연결 DB 작성
 * </pre>
 *
 * <pre>
 * <b>History:</b>
 *    작성자 ANikka , 1.0.5, 2016.4.11 초기작성
 * </pre>
 *
 * @author 최종 수정자 ANikka
 * @version 1.0.5, 2016.4.11 주석 수정
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
	 * 서버 정보를 저장합니다.
	 *
	 * @param ip 매장 ip주소
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
	 * DB접속 Select문
	 *
	 * @param query 검색 쿼리를 넘겨야 합니다.
	 * @return ArrayList<HashMap<String, String>>
	 * @exception 0 : 정상, 1: SQLException, 2: Exception
	 */
	public ArrayList<HashMap<String, String>> connection(String query) {
		
		this.query = query;
		errCode = 0;
    	Connection conn = null;
    	    	
    	//통신체크
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
	 * DB접속 한줄검색 문
	 *
	 * @param query 검색 쿼리를 넘겨야 합니다.
	 * @return HashMap<String, String>
	 * @exception 0 : 정상, 1: SQLException, 2: Exception
	 */
	public HashMap<String, String> selectQueryOne(String query) {			
			this.query = query;
			errCode = 0;
	    	Connection conn = null;
	    	    	
	    	//통신체크
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
	 * DB접속 Update문
	 *
	 * @param query 검색 쿼리를 넘겨야 합니다.
	 * @return int : 결과 전송
	 * @exception 0 : 정상, 1: SQLException, 2: Exception
	 */
	public int connect_update(String query) {
		
		this.query = query;
		errCode = 0;
    	Connection conn = null;
    	    	
    	//통신체크
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
	 * DB접속 배열 다중 Insert,Update문
	 *
	 * @param query_won 저장 쿼리를 넘겨야 합니다.
	 * @return int : 결과 전송
	 * @exception 0 : 정상, 1: SQLException, 2: Exception
	 */
	public int connect_update(String[] query_won){
		
		//String [] query_multi;
		errCode = 0;
    	Connection conn = null;
    	    	
    	//통신체크
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
    		
    		return 3; //0 정상 , 1.Sql 오류 , 2 Exception 오류 , 3 커넥션오류 
    	}
	}
	
	
	/**
	 * DB접속 List Insert,Update문
	 *
	 * @param query_won 자장 쿼리를 넣어야 합니다.
	 * @return int : 결과 전송
	 * @exception 0 : 정상, 1: SQLException, 2: Exception
	 */
	public int connect_update(ArrayList<String> query_won){
	
		//String [] query_multi;
		errCode = 0;
    	Connection conn = null;
    	    	
    	//통신체크
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
    		
    		return 3; //0 정상 , 1.Sql 오류 , 2 Exception 오류 , 3 커넥션오류 
    	}
	}
	
	
	public Connection getConnection(){
		
		//String [] query_multi;
		errCode = 0;
    	Connection conn = null;
    	    	
    	//통신체크
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
    		
    		errCode = 3; //0 정상 , 1.Sql 오류 , 2 Exception 오류 , 3 커넥션오류
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
			//socket.setSoTimeout(5);           	/* InputStream에서 데이터읽을때의 timeout */	
			socket.connect(socketAddress, 5000); 	/* socket연결 자체에대한 timeout */			
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
