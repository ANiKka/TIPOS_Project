import java.io.File;
import java.io.FileInputStream;
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
import java.util.Properties;

public class Ms_Connect {
	
	int errCode =0;
    String errMsg;
    
    ResultSet rs = null;
    private ArrayList<HashMap<String, String>> json;
    private HashMap<String, String> json_one;
    private String ip = "localhost";
    private String port = "16000";
	private String dbname = "tips";
	private String dbid = "sa";
	private String dbpw ="tips";	
	private String query;
	//private JSONArray json;
	
	
	public Ms_Connect(){
		
		
		Properties config = new Properties();
		try {
			config.load(new FileInputStream(new File("config.dat")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ip = config.getProperty("DBIP");
		port = config.getProperty("DBPORT");
		
	}
	
	
	/*
	 * 서버정보를 셋팅 합니다.
	 * 
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
    	} catch (Exception e) {
    	    System.out.println("Error connection : " + e.getMessage());	
    	    errCode = 2;
    	    errMsg = e.getMessage();
    	}
    	return json;
    	}else{
    		return null;
    	}
    }	
	
	
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
