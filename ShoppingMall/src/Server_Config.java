
public class Server_Config {	
	
	//server 설정하기
	private static String SERVER_IP;
	private static String SERVER_PORT;
	private static String SERVER_DBNAME = "tips";
	private static String SERVER_DBID = "sa";
	private static String SERVER_DBPW = "tips";
	
	//ftp설정하기
	private static String FTPIP = "14.38.161.45";
	private static int FTPPORT = 7000;
	private static String FTPID = "xodlfvhtm";
	private static String FTPPW = "tips";
	private static String FTPLOCALPATH = ".";
	private static String FTPSERVERPATH = "main_goods";
	private static String FTPMARTPATH = "";
	
	//office 설정하기
	private static String OFFICECODE;				
	private static String OFFICENAME;
	private static String OFFICESHOP;
	private static String OFFICEID;
	private static String OFFICEPW;
	private static String SHOPKEY;
	
	//image 설정하기
	private static String IMAGE_IP="14.38.161.45";
	private static String IMAGE_PORT="18975";
	private static String IMAGE_DBNAME="ShoppingMall";
	private static String IMAGE_DBID="sa";
	private static String IMAGE_DBPW="tips";
		
	//PC image 저장폴더
	private static String PCIMAGE_PATH="C:\\";
	
	//상품전송방식
	private static String GOODS_TRANYN="1";
	
	
	//SERVER
	public static void setSERVER_DBID(String sERVER_DBID) {
		SERVER_DBID = sERVER_DBID;
	}
	
	public static void setSERVER_DBNAME(String sERVER_DBNAME) {
		SERVER_DBNAME = sERVER_DBNAME;
	}
	
	public static void setSERVER_DBPW(String sERVER_DBPW) {
		SERVER_DBPW = sERVER_DBPW;
	}
	
	public static void setSERVER_IP(String sERVER_IP) {
		SERVER_IP = sERVER_IP;
	}
	
	public static void setSERVER_PORT(String sERVER_PORT) {
		SERVER_PORT = sERVER_PORT;
	}
	
	public static String getSERVER_DBID() {
		return SERVER_DBID;
	}
	
	public static String getSERVER_DBNAME() {
		return SERVER_DBNAME;
	}
	
	public static String getSERVER_DBPW() {
		return SERVER_DBPW;
	}
	
	public static String getSERVER_IP() {
		return SERVER_IP;
	}
	
	public static String getSERVER_PORT() {
		return SERVER_PORT;
	}
		
	
	//FTP설정
	public static void setFTPIP(String fTPIP) {
		FTPIP = fTPIP;
	}
	
	public static void setFTPPORT(int fTPPORT) {
		FTPPORT = fTPPORT;
	}
	
	public static void setFTPID(String fTPID) {
		FTPID = fTPID;
	}
	
	public static void setFTPLOCALPATH(String fTPLOCALPATH) {
		FTPLOCALPATH = fTPLOCALPATH;
	}
	
	public static void setFTPPW(String fTPPW) {
		FTPPW = fTPPW;
	}
	
	public static void setFTPSERVERPATH(String fTPSERVERPATH) {
		FTPSERVERPATH = fTPSERVERPATH;
	}
	
	public static void setFTPMARTPATH(String fTPMARTPATH) {
		FTPMARTPATH = fTPMARTPATH;
	}
	
	public static String getFTPID() {
		return FTPID;
	}
	
	public static String getFTPIP() {
		return FTPIP;
	}
	
	public static int getFTPPORT() {
		return FTPPORT;
	}
	
	public static String getFTPLOCALPATH() {
		return FTPLOCALPATH;
	}
	
	public static String getFTPPW() {
		return FTPPW;
	}
	
	public static String getFTPSERVERPATH() {
		return FTPSERVERPATH;
	}
	
	public static String getFTPMARTPATH() {
		return FTPMARTPATH;
	}
	
	
	//오피스설정
	public static void setOFFICECODE(String oFFICECODE) {
		OFFICECODE = oFFICECODE;
	}
	
	public static void setOFFICENAME(String oFFICENAME) {
		OFFICENAME = oFFICENAME;
	}
	
	public static void setOFFICESHOP(String oFFICESHOP) {
		OFFICESHOP = oFFICESHOP;
	}
	
	public static void setOFFICEID(String oFFICEID) {
		OFFICEID = oFFICEID;
	}
	
	public static void setOFFICEPW(String oFFICEPW) {
		OFFICEPW = oFFICEPW;
	}
	
	public static String getOFFICECODE() {
		return OFFICECODE;
	}
	
	public static String getOFFICENAME() {
		return OFFICENAME;
	}
	
	public static String getOFFICESHOP() {
		return OFFICESHOP;
	}
	
	public static String getOFFICEID() {
		return OFFICEID;
	}
	
	public static String getOFFICEPW() {
		return OFFICEPW;
	}	
	
	public static void setSHOPKEY(String sHOPKEY) {
		SHOPKEY = sHOPKEY;
	}
	
	public static String getSHOPKEY() {
		return SHOPKEY;
	}
	
	public static void setIMAGE_IP(String iMAGE_IP) {
		IMAGE_IP = iMAGE_IP;
	}
	
	public static void setIMAGE_PORT(String iMAGE_PORT) {
		IMAGE_PORT = iMAGE_PORT;
	}
	
	public static void setIMAGE_DBNAME(String iMAGE_DBNAME) {
		IMAGE_DBNAME = iMAGE_DBNAME;
	}
	
	public static void setIMAGE_DBID(String iMAGE_DBID) {
		IMAGE_DBID = iMAGE_DBID;
	}
	
	public static void setIMAGE_DBPW(String iMAGE_DBPW) {
		IMAGE_DBPW = iMAGE_DBPW;
	}
	
	public static String getIMAGE_IP() {
		return IMAGE_IP;
	}
	
	public static String getIMAGE_PORT() {
		return IMAGE_PORT;
	}
	
	public static String getIMAGE_DBNAME() {
		return IMAGE_DBNAME;
	}
	
	public static String getIMAGE_DBID() {
		return IMAGE_DBID;
	}
	
	public static String getIMAGE_DBPW() {
		return IMAGE_DBPW;
	}

	public static String getPCIMAGE_PATH() {
		return PCIMAGE_PATH;
	}

	public static void setPCIMAGE_PATH(String iMAGE_PATH) {
		PCIMAGE_PATH = iMAGE_PATH;
	}

	public static String getGOODS_TRANYN() {
		return GOODS_TRANYN;
	}

	public static void setGOODS_TRANYN(String gOODS_TRANYN) {
		GOODS_TRANYN = gOODS_TRANYN;
	}
	
}
