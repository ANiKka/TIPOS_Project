import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import java.awt.Toolkit;
import javax.swing.JScrollPane;
import javax.swing.JList;


public class Main_Config extends JDialog implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 157448436L;
	private JTextField text_server_ip;
	private JTextField text_server_port;
	private JTextField text_server_dbname;
	private JTextField text_server_dbid;
	private JPasswordField pass_server_dbpw;
	private JTextField text_ftp_dandock;
	private JTextField text_office_code;
	private JTextField text_office_name;
	private JTextField text_office_http;
	private JTextField text_office_id;
	private JPasswordField text_office_pw;
	
	private Properties config_file;
	private File file;
	private JPasswordField pass_shop_key;
	
	private Ms_Connect ms_connect;
	
	HashMap<String, String> temp_config;
	private JTextField text_pcimage_path;
	
	//환경설정 설명서
	private JLabel label_info;
	
	/**
	 * Create the panel.
	 */
	public Main_Config() {
		
		setResizable(false);
		
		try {
		    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) { 
		    System.err.println("Cannot set look and feel:" + e.getMessage()); 
		}
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Main_Config.class.getResource("/Icon/btn_config.png")));
				
		//시작배치 합니다.
		init();
		
		//db접속 도구를 생성한다.
		ms_connect = new Ms_Connect();
				
		//설정파일을 불러옵니다.		
		pathConfig();
		
		//설정파일을 불러옵니다.
		setSetting();		
		
		//설정파일을 로드 합니다.
		setValues();
		
		ms_connect.setMainSetting();		
		temp_config = new HashMap<String, String>();
		
		if(ms_connect.connect_errorCheck()){
			//서버에서 값을 불러 옵니다.
			String query = "Select * From Office_User";
			ArrayList<HashMap<String, String>> temp_al = ms_connect.connection(query);
			if(temp_al.size() > 0){
				temp_config = 	temp_al.get(0);
				setOnlineValues();
			}
		}		
	}
			
	public void init() {
		setBounds(0, 0, 1035, 690);
		getContentPane().setLayout(null);
		
		JPanel panel_server = new JPanel();
		panel_server.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_server.setBounds(12, 10, 322, 310);
		getContentPane().add(panel_server);
		panel_server.setLayout(null);
		
		JLabel label_server_title = new JLabel("\uC11C\uBC84\uD658\uACBD\uC124\uC815");
		label_server_title.setHorizontalAlignment(SwingConstants.CENTER);
		label_server_title.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		label_server_title.setBounds(12, 10, 298, 30);
		panel_server.add(label_server_title);
		
		JLabel label_server_ip = new JLabel("Server IP ");
		label_server_ip.setBackground(SystemColor.activeCaption);
		label_server_ip.setHorizontalAlignment(SwingConstants.CENTER);
		label_server_ip.setBounds(12, 50, 70, 15);
		panel_server.add(label_server_ip);
		
		text_server_ip = new JTextField();
		text_server_ip.setToolTipText("<Html>\r\n\uC1FC\uD551\uBAB0\uACFC \uC5F0\uB3D9\uD560 \uC11C\uBC84\uC758 IP\uC8FC\uC18C\uB97C \uC785\uB825\uD574 \uC8FC\uC138\uC694!\r\n</Html>");
		text_server_ip.setBounds(94, 47, 216, 21);
		panel_server.add(text_server_ip);
		text_server_ip.setColumns(10);
		
		JLabel label_server_port = new JLabel("Port");
		label_server_port.setHorizontalAlignment(SwingConstants.CENTER);
		label_server_port.setBounds(12, 78, 70, 15);
		panel_server.add(label_server_port);
		
		text_server_port = new JTextField();
		text_server_port.setToolTipText("<Html>\r\n\uC1FC\uD551\uBAB0\uACFC \uC5F0\uB3D9\uD560 \uC11C\uBC84\uC758 PORT\uB97C \uC785\uB825\uD574 \uC8FC\uC138\uC694!\r\n</Html>");
		text_server_port.setColumns(10);
		text_server_port.setBounds(94, 75, 216, 21);
		panel_server.add(text_server_port);
		
		JLabel label_server_dbname = new JLabel("DB Name");
		label_server_dbname.setHorizontalAlignment(SwingConstants.CENTER);
		label_server_dbname.setBounds(12, 106, 70, 15);
		panel_server.add(label_server_dbname);
		
		text_server_dbname = new JTextField();
		text_server_dbname.setToolTipText("<Html>\r\n\uC1FC\uD551\uBAB0\uACFC \uC5F0\uB3D9\uD560 \uC11C\uBC84\uC758 DB\uBA85\uC744 \uC785\uB825\uD574 \uC8FC\uC138\uC694!\r\n</Html>");
		text_server_dbname.setColumns(10);
		text_server_dbname.setBounds(94, 103, 216, 21);
		panel_server.add(text_server_dbname);
		
		JLabel label_server_dbid = new JLabel("DB ID");
		label_server_dbid.setHorizontalAlignment(SwingConstants.CENTER);
		label_server_dbid.setBounds(12, 134, 70, 15);
		panel_server.add(label_server_dbid);
		
		text_server_dbid = new JTextField();
		text_server_dbid.setToolTipText("<Html>\r\n\uC1FC\uD551\uBAB0\uACFC \uC5F0\uB3D9\uD560 \uC11C\uBC84\uC758 DB ID\uB97C \uC785\uB825\uD574 \uC8FC\uC138\uC694!\r\n</Html>");
		text_server_dbid.setColumns(10);
		text_server_dbid.setBounds(94, 131, 216, 21);
		panel_server.add(text_server_dbid);
		
		JLabel lblDbPw = new JLabel("DB PW");
		lblDbPw.setHorizontalAlignment(SwingConstants.CENTER);
		lblDbPw.setBounds(12, 162, 70, 15);
		panel_server.add(lblDbPw);
		
		pass_server_dbpw = new JPasswordField();
		pass_server_dbpw.setToolTipText("<Html>\r\n\uC1FC\uD551\uBAB0\uACFC \uC5F0\uB3D9\uD560 \uC11C\uBC84\uC758 DB PW\uB97C \uC785\uB825\uD574 \uC8FC\uC138\uC694!\r\n</Html>");
		pass_server_dbpw.setBounds(94, 159, 216, 21);
		panel_server.add(pass_server_dbpw);
		
		text_ftp_dandock = new JTextField();
		text_ftp_dandock.setToolTipText("<Html>\r\n\uC1FC\uD551\uBAB0\uACFC \uC5F0\uB3D9\uD560 FTP \uAC1C\uC778 \uC774\uBBF8\uC9C0 \uD3F4\uB354\uBA85\uC785\uB2C8\uB2E4. <br>\r\n\uD504\uB85D\uADF8\uB7A8\uC774 \uB9E4\uC7A5\uCF54\uB4DC\uB85C \uC790\uB3D9\uC0DD\uC131 \uD569\uB2C8\uB2E4.\r\n</Html>");
		text_ftp_dandock.setBounds(94, 187, 216, 21);
		panel_server.add(text_ftp_dandock);
		text_ftp_dandock.setEnabled(false);
		
		JLabel label_ftp_dandock = new JLabel("\uC774\uBBF8\uC9C0\uD3F4\uB354");
		label_ftp_dandock.setBounds(12, 190, 70, 15);
		panel_server.add(label_ftp_dandock);
		label_ftp_dandock.setHorizontalAlignment(SwingConstants.CENTER);
		
		JButton btn_serverSave = new JButton("\uC11C\uBC84\uC800\uC7A5");
		btn_serverSave.setToolTipText("<Html>\r\n\uC11C\uBC84\uD658\uACBD\uC124\uC815 \uB0B4\uC6A9\uB9CC \uC800\uC7A5 \uD569\uB2C8\uB2E4.<br>\r\n\uC6B0\uCE21\uC758 \uB9E4\uC7A5\uD658\uACBD \uC124\uC815\uC758 \uBCC0\uACBD \uB0B4\uC6A9\uC740 \uC800\uC7A5\uB418\uC9C0 \uC54A\uC2B5\uB2C8\uB2E4.<br>\r\n\uC800\uC7A5 \uD6C4 \uD504\uB85C\uADF8\uB7A8\uC744 \uC7AC\uC2E4\uD589\uD574 \uC8FC\uC138\uC694!!\r\n</Html>");
		btn_serverSave.addActionListener(this);
		btn_serverSave.setBounds(213, 277, 97, 23);
		panel_server.add(btn_serverSave);
		
		JPanel panel_ftp = new JPanel();
		panel_ftp.setLayout(null);
		panel_ftp.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_ftp.setBounds(349, 10, 322, 310);
		getContentPane().add(panel_ftp);
		
		JLabel label_office_title = new JLabel("\uB9E4\uC7A5\uD658\uACBD\uC124\uC815");
		label_office_title.setBounds(12, 10, 298, 30);
		panel_ftp.add(label_office_title);
		label_office_title.setHorizontalAlignment(SwingConstants.CENTER);
		label_office_title.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		
		JLabel label_office_code = new JLabel("\uC810\uD3EC\uCF54\uB4DC");
		label_office_code.setBounds(12, 50, 76, 15);
		panel_ftp.add(label_office_code);
		label_office_code.setHorizontalAlignment(SwingConstants.CENTER);
		label_office_code.setBackground(SystemColor.activeCaption);
		
		text_office_code = new JTextField();
		text_office_code.setToolTipText("<Html>\r\n\uC1FC\uD551\uBAB0\uACFC \uC5F0\uB3D9\uD560 \uB9E4\uC7A5\uCF54\uB4DC\uC785\uB2C8\uB2E4. <br>\r\n\uC11C\uBC84\uC124\uC815\uC744 \uC644\uB8CC \uD558\uBA74 \uD504\uB85C\uADF8\uB7A8\uC5D0 \uB4F1\uB85D \uB418\uC5B4\uC788\uB294<br>\r\n\uB9E4\uC7A5\uCF54\uB4DC\uB97C \uC790\uB3D9\uC73C\uB85C \uD638\uCD9C \uD569\uB2C8\uB2E4.\r\n</Html>");
		text_office_code.setBounds(100, 47, 210, 21);
		panel_ftp.add(text_office_code);
		text_office_code.setColumns(10);
		text_office_code.setEnabled(false);
		
		JLabel label_office_name = new JLabel("\uC810\uD3EC\uBA85");
		label_office_name.setBounds(12, 78, 76, 15);
		panel_ftp.add(label_office_name);
		label_office_name.setHorizontalAlignment(SwingConstants.CENTER);
		
		text_office_name = new JTextField();
		text_office_name.setToolTipText("<Html>\r\n\uC1FC\uD551\uBAB0\uACFC \uC5F0\uB3D9\uD560 \uB9E4\uC7A5\uBA85\uC785\uB2C8\uB2E4. <br>\r\n\uC11C\uBC84\uC124\uC815\uC744 \uC644\uB8CC \uD558\uBA74 \uD504\uB85C\uADF8\uB7A8\uC5D0 \uB4F1\uB85D \uB418\uC5B4\uC788\uB294<br>\r\n\uB9E4\uC7A5\uBA85\uC744 \uC790\uB3D9\uC73C\uB85C \uD638\uCD9C \uD569\uB2C8\uB2E4.\r\n</Html>");
		text_office_name.setBounds(100, 75, 210, 21);
		panel_ftp.add(text_office_name);
		text_office_name.setColumns(10);
		text_office_name.setEnabled(false);
		
		JLabel label_office_shopname = new JLabel("\uC1FC\uD551\uBAB0\uC8FC\uC18C");
		label_office_shopname.setBounds(12, 106, 76, 15);
		panel_ftp.add(label_office_shopname);
		label_office_shopname.setHorizontalAlignment(SwingConstants.CENTER);
		
		text_office_http = new JTextField();
		text_office_http.setToolTipText("<Html>\r\n\uB9E4\uC7A5\uACFC \uC5F0\uB3D9\uD560 \uC1FC\uD551\uBAB0\uC8FC\uC18C \uC785\uB2C8\uB2E4. <br>\r\n\uC608) http://tipos.or.kr \uD615\uC2DD\uC73C\uB85C \uB4F1\uB85D\uD574 \uC8FC\uC138\uC694!!\r\n</Html>");
		text_office_http.setBounds(100, 103, 210, 21);
		panel_ftp.add(text_office_http);
		text_office_http.setColumns(10);
		
		JLabel label_office_id = new JLabel("\uC1FC\uD551\uBAB0 ID");
		label_office_id.setBounds(12, 134, 76, 15);
		panel_ftp.add(label_office_id);
		label_office_id.setHorizontalAlignment(SwingConstants.CENTER);
		
		text_office_id = new JTextField();
		text_office_id.setToolTipText("<Html>\r\n\uC1FC\uD551\uBAB0 \uAD00\uB9AC\uC790 \uD398\uC774\uC9C0 ID \uC785\uB2C8\uB2E4. <br>\r\n</Html>");
		text_office_id.setBounds(100, 131, 210, 21);
		panel_ftp.add(text_office_id);
		text_office_id.setColumns(10);
		
		JLabel label_office_pw = new JLabel("\uC1FC\uD551\uBAB0 PW");
		label_office_pw.setBounds(12, 162, 76, 15);
		panel_ftp.add(label_office_pw);
		label_office_pw.setHorizontalAlignment(SwingConstants.CENTER);
		
		text_office_pw = new JPasswordField();
		text_office_pw.setToolTipText("<Html>\r\n\uC1FC\uD551\uBAB0 \uAD00\uB9AC\uC790 \uD398\uC774\uC9C0 PW \uC785\uB2C8\uB2E4. <br>\r\n</Html>");
		text_office_pw.setBounds(100, 159, 210, 21);
		panel_ftp.add(text_office_pw);
		
		JLabel lblKey = new JLabel("\uC1FC\uD551\uBAB0 KEY");
		lblKey.setBounds(12, 190, 76, 15);
		panel_ftp.add(lblKey);
		lblKey.setHorizontalAlignment(SwingConstants.CENTER);
		
		pass_shop_key = new JPasswordField();
		pass_shop_key.setToolTipText("<Html>\r\n\uB9E4\uC7A5\uACFC \uC1FC\uD551\uBAB0\uC744 \uC5F0\uB3D9\uD558\uB294 API\uD0A4 \uC0AC\uC6A9\uC744 \uC704\uD574<br>\r\n\uC1FC\uD551\uBAB0\uC5D0\uC11C \uC81C\uACF5\uD558\uB294 API_KEY\uB97C \uC785\uB825\uD569\uB2C8\uB2E4.<br>\r\n\uC704\uCE58)\uC1FC\uD551\uBAB0\uAD00\uB9AC\uC790 \uD398\uC774\uC9C0<br>\r\n\uC88C\uCE21 \uBA54\uB274 \uD504\uB85C\uADF8\uB7A8\uC0F5>\uD504\uB85D\uADF8\uB7A8\uD658\uACBD\uC124\uC815>API \uAC1C\uBC1C\uC13C\uD130 \uC785\uB2C8\uB2E4.<br>\r\n</Html>");
		pass_shop_key.setBounds(100, 187, 210, 21);
		panel_ftp.add(pass_shop_key);
		pass_shop_key.setText((String) null);
		pass_shop_key.setColumns(10);
		
		JLabel label_pciamge_path = new JLabel("PC\uD3F4\uB354");
		label_pciamge_path.setHorizontalAlignment(SwingConstants.CENTER);
		label_pciamge_path.setBounds(12, 260, 76, 15);
		panel_ftp.add(label_pciamge_path);
		
		text_pcimage_path = new JTextField();
		text_pcimage_path.setToolTipText("<Html>\r\n\uC9C1\uC811 \uC791\uC5C5\uD55C \uC774\uBBF8\uC9C0\uB97C \uBD88\uB7EC\uC624\uAE30 \uC704\uD55C \uD3F4\uB354 \uC785\uB2C8\uB2E4.<br>\r\n\uC790\uC8FC \uC0AC\uC6A9\uD558\uC2DC\uB294 \uC774\uBBF8\uC9C0 \uD3F4\uB354\uB97C \uC9C0\uC815\uD558\uC138\uC694!<br>\r\n\uC9C0\uC815 \uD558\uC9C0 \uC54A\uC73C\uC154\uB3C4 \uC774\uBBF8\uC9C0 \uC120\uD0DD \uD654\uBA74\uC5D0\uC11C \uC6D0\uD558\uB294<br>\r\n\uD3F4\uB354\uB97C \uC9C0\uC815 \uD558\uC2E4 \uC218 \uC788\uC2B5\uB2C8\uB2E4.<br>\r\n</Html>");
		text_pcimage_path.setBounds(100, 257, 140, 21);
		panel_ftp.add(text_pcimage_path);
		text_pcimage_path.setColumns(10);
		
		JLabel label_pcimage_title = new JLabel("\uB9E4\uC7A5 \uC0AC\uC9C4 \uD3F4\uB354 \uC124\uC815");
		label_pcimage_title.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		label_pcimage_title.setHorizontalAlignment(SwingConstants.CENTER);
		label_pcimage_title.setBounds(12, 232, 298, 15);
		panel_ftp.add(label_pcimage_title);
		
		JButton btn_config_folder = new JButton("\uD3F4\uB354");
		btn_config_folder.setToolTipText("<Html>\r\n\uC9C1\uC811 \uC791\uC5C5\uD55C \uC774\uBBF8\uC9C0\uB97C \uBD88\uB7EC\uC624\uAE30 \uC704\uD55C \uD3F4\uB354 \uC785\uB2C8\uB2E4.<br>\r\n\uC790\uC8FC \uC0AC\uC6A9\uD558\uC2DC\uB294 \uC774\uBBF8\uC9C0 \uD3F4\uB354\uB97C \uC9C0\uC815\uD558\uC138\uC694!<br>\r\n\uC9C0\uC815 \uD558\uC9C0 \uC54A\uC73C\uC154\uB3C4 \uC774\uBBF8\uC9C0 \uC120\uD0DD \uD654\uBA74\uC5D0\uC11C \uC6D0\uD558\uB294<br>\r\n\uD3F4\uB354\uB97C \uC9C0\uC815 \uD558\uC2E4 \uC218 \uC788\uC2B5\uB2C8\uB2E4.<br>\r\n</Html>");
		btn_config_folder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getFolderChoose();				
			}
		});
		
		
		btn_config_folder.setBounds(253, 256, 57, 23);
		panel_ftp.add(btn_config_folder);
		
		JPanel panel_office = new JPanel();
		panel_office.setLayout(null);
		panel_office.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_office.setBounds(686, 10, 322, 310);
		getContentPane().add(panel_office);
		
		JLabel label_FunctionKey = new JLabel("\uAE30\uB2A5\uBC84\uD2BC \uBAA8\uC74C");
		label_FunctionKey.setHorizontalAlignment(SwingConstants.CENTER);
		label_FunctionKey.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		label_FunctionKey.setBounds(12, 10, 298, 30);
		panel_office.add(label_FunctionKey);
		
		JButton btn_xls_output = new JButton("\uBD84\uB958\uC5D1\uC140\uCD9C\uB825");
		btn_xls_output.setToolTipText("<Html>\r\n\uD604\uC7AC \uB9E4\uC7A5\uC758 \uBD84\uB958\uB97C \uC1FC\uD551\uBAB0 \uCF54\uB4DC \uD615\uC2DD\uC73C\uB85C \uC5D1\uC140 \uCD9C\uB825 \uD569\uB2C8\uB2E4.<br>\r\n\uCD9C\uB825\uB41C \uC5D1\uC140\uD30C\uC77C\uC744 \uC1FC\uD551\uBAB0 \uBD84\uB958\uC77C\uAD04 \uB4F1\uB85D\uC5D0\uC11C \uB4F1\uB85D\uD574 \uC8FC\uC138\uC694!\r\n</Html>");
		btn_xls_output.setBounds(12, 220, 298, 35);
		panel_office.add(btn_xls_output);
		
		JButton btn_Shyc_Start = new JButton("\uB3D9\uAE30\uD654 \uC2DC\uC791");
		btn_Shyc_Start.setToolTipText("<Html>\r\n\uBCC0\uACBD\uB41C \uC0C1\uD488\uC744 \uC1FC\uD551\uBAB0\uACFC \uC989\uC2DC \uB3D9\uAE30\uD654 \uD560\uB54C \uC0AC\uC6A9 \uD569\uB2C8\uB2E4.\r\n</Html>");
		btn_Shyc_Start.setBounds(12, 265, 298, 35);
		panel_office.add(btn_Shyc_Start);
		btn_Shyc_Start.addActionListener(this);
		btn_xls_output.addActionListener(this);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(349, 330, 322, 310);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setBounds(686, 330, 322, 310);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JButton btn_server_save = new JButton("\uC800 \uC7A5");
		btn_server_save.setToolTipText("<Html>\r\n\uC11C\uBC84 \uC124\uC815 \uBCC0\uACBD \uB0B4\uC6A9 \uBC0F \uB9E4\uC7A5\uD658\uACBD \uC124\uC815 \uBCC0\uACBD \uB0B4\uC6A9\uC744 \uC800\uC7A5\uD569\uB2C8\uB2E4.\r\n</Html>");
		btn_server_save.setBounds(12, 220, 298, 35);
		panel_2.add(btn_server_save);
		
		JButton btn_close = new JButton("\uC885 \uB8CC");
		btn_close.setActionCommand("종료");
		btn_close.addActionListener(this);
		btn_close.setBounds(12, 265, 298, 35);
		panel_2.add(btn_close);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(12, 330, 322, 310);
		getContentPane().add(panel);
		
		label_info = new JLabel("New label");
		label_info.setBounds(12, 10, 298, 290);
		panel.add(label_info);
		btn_server_save.addActionListener(this);
				
		
		String info_title = "<Html>"
							+"<table>"
      +"<th>"
        +"<td>쇼핑몰 환경설정</td>"
      +"</th>"      
      +"<th>"
        +"<td>"
        +"매장과 연동 설정을 위해서 쇼핑몰의 관리자 페이지 프로그램샵<br>"
        +"외부 DB연동 MS-SQL DB연동설정 에서 현재 매장의 서버 IP주소를<br>"
        +"입력해 주셔야 합니다."
        +"</td>"
      +"</th>"      
    +"</table>"
+"</Html>";	
		
		info_title = "<HTML><TABLE>"
				+ "<TH><TD>쇼핑몰 환경설정</TD></TH>"
				+ "</TABLE>"
				+ "<TABLE>"
				+ "<TH><TD>매장과 쇼핑몰의 연동 설정을 위해서<br> "
				+ "쇼핑몰의 관리자 페이지 > 프로그램샵 > <br>"
				+ "외부 DB연동 > MS-SQL DB연동설정 에서 <br>"
				+ "현재 매장의 서버 IP주소를"
				+ " 입력해 <br>주셔야 정상 작동 합니다.</TD></TH>"
				+ "</TABLE></HTML>";		
		
		label_info.setText(info_title);
		
	}
		
	//설정을 배치합니다.
	private void setSetting(){
		
		try {
			
			if(config_file.isEmpty()){
				config_file.load(new FileInputStream(file));	
			}
			//서버 환경설정 불러오기
			text_server_ip.setText(config_file.getProperty("ServerIp"));
			text_server_port.setText(config_file.getProperty("ServerPort"));
			text_server_dbname.setText(config_file.getProperty("ServerDBname"));
			text_server_dbid.setText(config_file.getProperty("ServerDBid"));
			pass_server_dbpw.setText(config_file.getProperty("ServerDBpw"));
			
			//pc이미지 폴더 설정
			text_pcimage_path.setText(config_file.getProperty("PCImagePath", "C:\\"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	
			return;
		}		
	}
		
	//파일이 있는지 검사 합니다.
	private void pathConfig(){
		try{
			
		config_file = new Properties();
		
		file = new File("Config.dat");		
		
		if( !file.exists() ){		
			try {
				
				file.createNewFile();
				config_file.load(new FileInputStream(file));
				
				config_file.setProperty("ServerIp", "localhost");
				config_file.setProperty("ServerPort", "1433");
				config_file.setProperty("ServerDBname", "tips");
				config_file.setProperty("ServerDBid", "sa");
				config_file.setProperty("ServerDBpw", "tips");
				
				config_file.setProperty("PCImagePath", "C:\\");
				/*config_file.setProperty("FTPdan", "");
				
				config_file.setProperty("OfficeCode", "");				
				config_file.setProperty("OfficeName", "");
				config_file.setProperty("OfficeShop", "");
				config_file.setProperty("Officeid", "");
				config_file.setProperty("Officepw", "");
				config_file.setProperty("ShopKey", "");*/
				
				config_file.store(new FileOutputStream(file), "환경설정");
				
				System.out.println("파일이 생성되었습니다.");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(this, e.getMessage());
				return;
			}
		}
		
		}catch(Exception e){
			JOptionPane.showMessageDialog(this, e.getMessage());
			e.printStackTrace();	
		}
	}

	private void setValues(){		
		
		System.out.println(config_file.getProperty("ServerIp"));
		Server_Config.setSERVER_IP(config_file.getProperty("ServerIp"));		
		Server_Config.setSERVER_PORT(config_file.getProperty("ServerPort"));		
		Server_Config.setSERVER_DBNAME(config_file.getProperty("ServerDBname"));
		Server_Config.setSERVER_DBID(config_file.getProperty("ServerDBid"));
		Server_Config.setSERVER_DBPW(config_file.getProperty("ServerDBpw"));		
		
		Server_Config.setPCIMAGE_PATH(config_file.getProperty("PCImagePath"));
	}
	
	private void setOnlineValues(){
	
			Server_Config.setFTPMARTPATH(temp_config.get("Sto_CD"));			
			Server_Config.setOFFICECODE(temp_config.get("Sto_CD"));
			Server_Config.setOFFICENAME(temp_config.get("Office_Name"));
			Server_Config.setOFFICESHOP(temp_config.get("Online_Address"));
			Server_Config.setOFFICEID(temp_config.get("Online_ID"));
			Server_Config.setOFFICEPW(temp_config.get("Online_PW"));
			Server_Config.setSHOPKEY(temp_config.get("Online_Key"));
				
			text_ftp_dandock.setText(temp_config.get("Sto_CD"));
			
			//매장 환경설정 불러오기
			text_office_code.setText(temp_config.get("Sto_CD"));				
			text_office_name.setText(temp_config.get("Office_Name"));
			text_office_http.setText(temp_config.get("Online_Address"));			
			text_office_id.setText(temp_config.get("Online_ID"));
			text_office_pw.setText(temp_config.get("Online_PW"));
			pass_shop_key.setText(temp_config.get("Online_Key"));	
			
	}
	
	private void setSave(){
				
		config_file.setProperty("ServerIp", text_server_ip.getText());
		config_file.setProperty("ServerPort", text_server_port.getText());
		config_file.setProperty("ServerDBname", text_server_dbname.getText());
		config_file.setProperty("ServerDBid", text_server_dbid.getText());		
		char[] pass = pass_server_dbpw.getPassword();
		config_file.setProperty("ServerDBpw", new String(pass));
		
		config_file.setProperty("PCImagePath", text_pcimage_path.getText());
		
		try{	
			config_file.store(new FileOutputStream(file), "환경설정 저장");
		}catch(IOException e){			
			JOptionPane.showMessageDialog(this, e.getMessage());
			return;
		}
		
		String office_http = text_office_http.getText();
		String office_id = text_office_id.getText();
		pass = text_office_pw.getPassword();
		String office_pw = new String(pass);
		pass = pass_shop_key.getPassword();
		String shop_key = new String(pass);
		
		String query = "Update Office_User set Online_Address='"+office_http+"', Online_ID='"+office_id+"', Online_PW='"+office_pw+"', Online_Key='"+shop_key+"' ";
		int result = ms_connect.connect_update(query);
						
			switch(result){
			case 0:				
				JOptionPane.showMessageDialog(this, "저장이 완료 되었습니다.");
				System.out.println("저장이 완료 되었습니다.");
				break;
			default:
				JOptionPane.showMessageDialog(this, "서버에 저장하지 못했습니다. 서버를 확인해 주세요!");
				break;
			}
		
	}

	//서버환경설정만 변경합니다.
	private void setServerSave(){
		
		config_file.setProperty("ServerIp", text_server_ip.getText());
		config_file.setProperty("ServerPort", text_server_port.getText());
		config_file.setProperty("ServerDBname", text_server_dbname.getText());
		config_file.setProperty("ServerDBid", text_server_dbid.getText());		
		char[] pass = pass_server_dbpw.getPassword();
		config_file.setProperty("ServerDBpw", new String(pass));
		
		try{	
			config_file.store(new FileOutputStream(file), "환경설정 저장");
			System.out.println("환경설정 저장 완료");
		}catch(IOException e){			
			JOptionPane.showMessageDialog(this, e.getMessage());
			return;
		}	
	}
	
	
	
	//강제 동기화 하기 
	@SuppressWarnings("unchecked")
	private void start_shyc(){
				
		if(Server_Config.getSHOPKEY().equals("")){			
			JOptionPane.showMessageDialog(this, "환경설정에서 API KEY를 입력해주세요~!");
			return;
		}
		
		this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
		
		/*String query = "Select a.Barcode, a.ShoppingMall_Use, a.UpLoad, a.Shop_View, a.Sto_Use, a.Img_path_use, a.img_path, b.G_Name, b.Pur_Pri, b.Sell_Pri,  "
						+ "(b.L_Code+b.M_Code+b.S_Code) + Replicate('0', 8 - Len(b.L_Code+b.M_Code+b.S_Code) ) as Goods_Cate, b.Real_Sto, "
						+ "b.Sale_Pur, b.Sale_Sell, b.Sale_Use, b.Write_Date"
						+" From goods_info as a inner join goods as b on a.barcode=b.barcode" 
						+" Where a.edit_tran='1' and img_path <> '' and a.shoppingmall_use='1' ";*/
		
		/*String query = "Select * From ( "
						+ " Select * From goods_info Where edit_tran='1' and img_path <> '' and shoppingmall_use='1' "
						+ " ) C inner join ( "
						+ " Select A.Barcode, A.G_Name, A.Pur_Pri, A.Sell_Pri, A.Real_Sto, A.Sale_Pur, A.Sale_Sell, A.Sale_Use, A.Write_Date, B.Goods_NewCate as Goods_Cate " 
						+ " From ( "
						+ " Select BarCode, G_Name, Pur_Pri, Sell_Pri, (L_Code+M_Code+S_Code) + Replicate('0', 8 - Len(L_Code+M_Code+S_Code) ) as Goods_Cate, Real_Sto, Sale_Pur, Sale_Sell, Sale_Use, Write_Date " 
						+ " From Goods " 
						+ " ) A inner join ( "
						+ " Select  (L_Code+M_Code+SM_SCode) + Replicate('0', 8 - Len(L_Code+M_Code+SM_SCode)) as Goods_NewCate,(L_Code+M_Code+S_Code) + Replicate('0', 8 - Len(L_Code+M_Code+S_Code) ) as Goods_OldCate From s_branch "
						+ " ) B on A.Goods_Cate=B.Goods_OldCate "
						+ " ) D on C.Barcode=D.Barcode ";*/		
		
		String query = "Select X.Barcode, X.ShoppingMall_use, X.UpLoad, X.Shop_View, X.Sto_Use, X.Pro_Sto, X.img_path, "
						+"X.Img_path_use, X.Edit_Tran, X.Img_Name, X.Shop_MainCode, X.G_Name, X.Pur_Pri, "
						+"'Sell_Pri' = Case "
						+"When X.Length4 = '1' Then Z.H_SellPri "
						+"When X.Scale_Use = '1' Then Z.H_SellPri "
						+"Else X.Sell_Pri "
						+"End, "
						+"X.Real_Sto, X.Sale_Pur, X.Sale_Sell, X.Sale_Use, X.Scale_Use, X.Length4, X.Write_Date, X.Goods_Cate "
						+"From "
						+"( "
						+"Select C.Barcode, C.ShoppingMall_use, C.UpLoad, C.Shop_View, C.Sto_Use, C.Pro_Sto, C.img_path, C.Img_path_use, C.Edit_Tran, C.Img_Name, C.Shop_MainCode, D.G_Name, D.Pur_Pri, D.Sell_Pri, D.Real_Sto, D.Sale_Pur, D.Sale_Sell, D.Sale_Use, D.Scale_Use, D.Length4, D.Write_Date, D.Goods_Cate "
						+"From ( "  
						+		"Select * From goods_info Where edit_tran='1' and img_path <> '' and shoppingmall_use='1' "  
						+		") C "
						+		"inner join ( "  
						+				"Select A.Barcode, A.G_Name, A.Pur_Pri, A.Sell_Pri, A.Real_Sto, A.Sale_Pur, A.Sale_Sell, A.Sale_Use, A.Scale_Use, A.Length4, A.Write_Date, B.Goods_NewCate as Goods_Cate "  
						+				"From ( "  
						+					"Select BarCode, G_Name, Pur_Pri, Sell_Pri, (L_Code+M_Code+S_Code) + Replicate('0', 8 - Len(L_Code+M_Code+S_Code) ) as Goods_Cate, Real_Sto, Sale_Pur, Sale_Sell, Sale_Use, Write_Date, Scale_use, 'Length4' =  CASE  WHEN Len(Barcode) = 4 THEN '1'  ELSE '0' END "  
						+					"From Goods " 
						+				") A "
						+				"inner join ( "  
						+					"Select  (L_Code+M_Code+SM_SCode) + Replicate('0', 8 - Len(L_Code+M_Code+SM_SCode)) as Goods_NewCate,(L_Code+M_Code+S_Code) + Replicate('0', 8 - Len(L_Code+M_Code+S_Code) ) as Goods_OldCate " 
						+					"From s_branch "
						+				") B "
						+				"on A.Goods_Cate=B.Goods_OldCate "  
						+		") D "
						+		"on C.Barcode=D.Barcode "
						+	") X left Join ( "
						+	"Select * From Hot_Key "
						+	") Z on X.Barcode=Z.H_Barcode ";
		
		//전송결과를 서버에 저장합니다. (성공시 Edit_Tran을 1->0 으로 변경)
		String resultQuery = "Update goods_info set UpLoad = '1', Edit_Tran='0' where barcode in (";
		String queryIn = "";
				
		//전송할 상품을 검색 합니다.
		ms_connect.setMainSetting();
		ArrayList<HashMap<String, String>> temp_map = ms_connect.connection(query);
		
		//전송할 상품을 담아 둡니다.
		JSONArray json_map = new JSONArray();
				
		if(temp_map.size() <= 0 ){					
			JOptionPane.showMessageDialog(this, "업로드할 상품이 없습니다.");
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			return;
		}
				
		//검색 총 수량
		System.out.println("총 "+ temp_map.size() + "개의 상품이 검색 되었습니다.!!");
		
		//있는 만큼 동기화 시작하기		
		Iterator<HashMap<String, String>> iter = temp_map.iterator();		
		
		int i = 1;
		while(iter.hasNext()){
			HashMap<String, String> map =  iter.next();
			
			//임시저장소
			JSONObject obj = new JSONObject();			
			
			obj.put("goods_cate", map.get("Goods_Cate"));
			obj.put("goods_img", map.get("img_path"));			
			obj.put("goods_name", map.get("G_Name"));
			obj.put("user_code", map.get("Barcode"));						
			obj.put("view_yn", map.get("Shop_View"));			
			obj.put("goods_regdate", map.get("Write_Date"));
			
			//상품이 행사 중이라면 행사가격으로 변경합니다.
			if(map.get("Sale_Use").trim().equals("1")){
				obj.put("sijung_price", map.get("Sell_Pri"));
				obj.put("goods_price", map.get("Sale_Sell"));
				//세일매입가
				obj.put("in_price", map.get("Sale_Pur"));
				
			}else{
				obj.put("goods_price", map.get("Sell_Pri"));	
				obj.put("sijung_price", "0");
				//일반매입가
				obj.put("in_price", map.get("Pur_Pri"));
			}
			
			//메인출력 코드를 입력합니다.
			obj.put("main_code", map.get("Shop_MainCode"));				
						
			//재고 사용 유무를 체크합니다.
			if(map.get("Sto_Use").equals("1")){
				//재고가 0이하일때는 0개로 표시해줍니다.
				if(Integer.parseInt(map.get("Real_Sto")) < 0 ){
					obj.put("goods_stock", "0");
				}else{
					obj.put("goods_stock", map.get("Real_Sto"));
				}
			}else{
				//재고 사용안함				
				obj.put("goods_stock", "-1");
			}
			
			json_map.add(obj);
			
			queryIn += "'"+map.get("Barcode")+"', ";
			
			if(i%20 == 0){					
				//전송할 쿼리를 넘깁니다.
				queryIn = queryIn.substring(0, queryIn.length()-2);							
				queryIn += ")";
				//전송 함수 호출
				tranStart(json_map, resultQuery+queryIn);
				//전송 후 클리어 합니다.
				json_map.clear();
				queryIn = "";
			}
			i++;
		}	
				
		//전송할 상품이 남아 있다면 다시 보냅니다.
		if(json_map.size() > 0){			
			queryIn = queryIn.substring(0, queryIn.length()-2);							
			queryIn += ")";
			tranStart(json_map, resultQuery+queryIn);
			json_map.clear();
			queryIn = "";
		}			
			
		//접속 끊기
		this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));		
	}
	
	//전송함수를 분리 합니다.
	private void tranStart(JSONArray jsonData, String query){
		
		//환경설정
		String shop_key = Server_Config.getSHOPKEY();
		
		//접속 쇼핑몰정보 정의하기
		String shop_address = "https://ssl.anybuild.co.kr/API/goods/goods_insert.php";		
		System.out.println(" 동기화를 시작합니다. 접속 주소 --> " + shop_address);
		
		String shop_data = "";	
		
		try {
			shop_data = "api_key="+shop_key.toString()+"&json_data="+URLEncoder.encode(jsonData.toString(), "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		System.out.println(shop_data);
				
		//기록을 남길 파일을 생성합니다.
		File file = new File("result.log");
				
		if(!file.isFile()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
				
		//결과를 전송 합니다.
		//전송폼을 생성합니다.
		try {
			
			URL url = new URL(shop_address);
			HttpURLConnection shop_url = (HttpURLConnection)url.openConnection();
			
			shop_url.setRequestMethod("POST");					
			shop_url.setRequestProperty("Accept-Language", "ko-kr,ko;q=0.8,en-us;q=0.5,en;q=0.3");
			shop_url.setDoInput(true);
			shop_url.setDoOutput(true);
						
			System.out.println("전송상태 출력");			
			System.out.println(" URL : "+shop_url.getURL());			

			OutputStreamWriter output = new OutputStreamWriter(shop_url.getOutputStream());
			
			output.write(shop_data);				
			
			output.flush();			
			output.close();			
			
			//전송 결과 수신
			InputStreamReader isr = new InputStreamReader(shop_url.getInputStream(), "UTF-8");	
			JSONObject object = (JSONObject)JSONValue.parseWithException(isr);							
						
			isr.close();
						
			SimpleDateFormat formatter = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss", Locale.KOREA );
			Date currentTime = new Date ( );
			String dTime = formatter.format ( currentTime );
			
			String sb = "전송 시간 : " + dTime + "결과 \r\n" ;
					sb += object.toJSONString();
			
			char[] paser = sb.toCharArray();
			
			//로그파일을 작성합니다.
			OutputStreamWriter bos = new OutputStreamWriter(new FileOutputStream(file, true), "euc-kr");					
			StringBuffer result_str = new StringBuffer();
			for(char str : paser){				
				bos.write(str);
				result_str.append(str);
			}			
			System.out.println(result_str);
			
			bos.write('\r');
			bos.write('\n');	
			
			bos.close();
			System.out.println("전송이 완료 되었습니다.");
			
			//전송 결과를 확인 합니다. 
			if(object.get("result_code").equals("OK")){
				int result = ms_connect.connect_update(query);
				switch(result){			
				case 0:
					System.out.println("정상 등록 되었습니다.");
					break;
				default:
					System.out.println("오류가 발생 되었습니다.");
					break;			
				}
			}			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();		
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		
	}
			
	private void creat_xls(){
		
		this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
		
		// 분류 소코드를 새로 생성합니다. 분류 소코드 컬럼 SM_Scode 입니다. 
		// 소분류 세자리를 두자리로 변경 합니다. 
		
		//분류를 불러 옵니다.		
		String query_count = "Select l_code, m_code, count(*) as count from s_branch where sm_scode is null group by l_code, m_code";
		
		ms_connect.setMainSetting();        
        ArrayList<HashMap<String, String>> branch_count = ms_connect.connection(query_count);
		
        //분류 코드 생성을 안했을 때만 합니다.
        if(branch_count.size() > 0) {
        	
        	//업데이트 해야하는 분류들만 골라서 담습니다.
	        ArrayList<String> result_query = new ArrayList<String>();
	        
			//불러온 분류에 새로운 코드 번호를 생성 합니다.
			Iterator<HashMap<String, String>> itr = branch_count.iterator();
	        while(itr.hasNext()){        	
	        	
	        	HashMap<String, String> map = itr.next();        	
	        	
	        	//분류를 정리 합니다.
	        	String l_code = map.get("l_code");
	        	String m_code = map.get("m_code");        	
	        	        	
	        	//해당하는 분류만 다시 불러 냅니다.
	        	String query_branch = "select * from s_branch where l_code='"+l_code+"' and m_code='"+m_code+"' order by s_code";
	        	ArrayList<HashMap<String, String>> branch_map = ms_connect.connection(query_branch);
	        		
	        		Iterator<HashMap<String, String>> itr_def = branch_map.iterator();
	        		int i =1;
	        		while(itr_def.hasNext()){
	        			HashMap<String, String> map_def = itr_def.next();
	        			
	        			String lcode = map_def.get("L_Code");
	        			String mcode = map_def.get("M_Code");
	        			String scode = map_def.get("S_Code");
	        			
	        			String result = "Update S_Branch Set SM_Scode='"+String.format("%02d", i)+"' Where l_code='"+lcode+"' and m_code='"+mcode+"' and s_code='"+scode+"' ";
	            		result_query.add(result);
	            		i++;
	        		}        		
	        }
	        
	        System.out.println(result_query);
	        
			//엑셀 생성 데이터를 디비에서 불러온다. 
	        switch(ms_connect.connect_update(result_query)){
	        case 0:
	        	System.out.println("분류 정보를 수정 하였습니다.");
	        	break;
	        case 1:
	        	System.out.println("네트워크 오류입니다. 확인 하세요 ");
	        	break;
	        default:
	        	System.out.println("분류 정보를 수정하지 못했습니다. ");
	        	break;	        
	        }	        
        }
		
		 // 엑셀파일 객체 생성
        WritableWorkbook workbook = null;
         
        // 시트 객체 생성
        WritableSheet sheet = null;
         
        // 셀 객체 생성
        Label label = null;
         
        // 저장할 파일 객체 생성
        File file = new File("Class_File.xls");
         
        if(!file.isFile()){
        	try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				JOptionPane.showMessageDialog(this, "엑셀 파일을 생성하지 못했습니다.\r\n" + e.getMessage() );
				return;
			}        	
        }
                
        //쿼리를 작성합니다.
        String query = "Select L_Code, L_Code + Replicate('0', 8 - Len(L_Code) ) as Code, L_Name, 1 as Gubun From L_Branch Where L_code <> 'AA'  UNION "
        				+ "Select L_Code, (L_Code+M_Code) + Replicate('0', 8 - Len(L_Code+M_Code)) as Code, M_Name, 2 as Gubun From M_Branch Where L_Code <> 'AA' UNION "
        				+ "Select L_Code, (L_Code+M_Code+SM_SCode) + Replicate('0', 8 - Len(L_Code+M_Code+SM_SCode)) as Code, S_Name, 3 as Gubun From S_Branch Where L_Code <> 'AA' ";
                
        //엑셀 생성 데이터를 디비에서 불러온다.     
        //ms_connect.setMainSetting();
        ArrayList<HashMap<String, String>> temp_map = ms_connect.connection(query);
         
        try{
             
            // 파일 생성
            workbook = Workbook.createWorkbook(file);
             
            // 시트 생성
            workbook.createSheet("sheet1", 0);
            sheet = workbook.getSheet(0);
             
            // 셀에 쓰기
            label = new Label(0, 0, "분류코드");
            sheet.addCell(label);
             
            label = new Label(1, 0, "1단계 분류");
            sheet.addCell(label);
            
            label = new Label(2, 0, "2단계 분류");
            sheet.addCell(label);
            
            label = new Label(3, 0, "3단계 분류");
            sheet.addCell(label);
            
            label = new Label(4, 0, "4단계 분류");
            sheet.addCell(label);
            
            label = new Label(5, 0, "숨김여부");
            sheet.addCell(label);
            
            label = new Label(6, 0, "접근권한");
            sheet.addCell(label);
            
            label = new Label(7, 0, "정렬 방식");
            sheet.addCell(label);
                        
             
            // 데이터 삽입
            for(int i=0; i < temp_map.size(); i++){
            	
                HashMap<String, String> result = temp_map.get(i) ;
                
                label = new Label(0, (i+1), (String)result.get("Code"));
                sheet.addCell(label);                
                
                switch(result.get("Gubun")){                
                case "1":
                	label = new Label(1, (i+1), (String)result.get("L_Name"));
                    sheet.addCell(label);                	
                	break;
                case "2":
                	label = new Label(2, (i+1), (String)result.get("L_Name"));
                    sheet.addCell(label);
                	break;
                case "3":
                	label = new Label(3, (i+1), (String)result.get("L_Name"));
                    sheet.addCell(label);
                	break;                	
                }
                
                label = new Label(5, (i+1), "1");
                sheet.addCell(label);
                
                label = new Label(6, (i+1), "|G|");
                sheet.addCell(label);
                
                label = new Label(7, (i+1), "1");
                sheet.addCell(label);
                
            }                          
            workbook.write();
            workbook.close();
 
        }catch(Exception e){        	
            e.printStackTrace();          
            this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            JOptionPane.showMessageDialog(this, "엑셀 파일을 생성하지 못했습니다.\r\n" + e.getMessage() );
			return;
        }
        
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}
	
	public void getFolderChoose(){
		
		JFileChooser jfiledialog = new JFileChooser();		
		int ret = -1;
		    	
    	jfiledialog.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    	//파일선택 창을 띄웁니다.		
    	jfiledialog.setCurrentDirectory(new File(text_pcimage_path.getText().toString()));
		
    	ret = jfiledialog.showOpenDialog(this);	
		System.out.println("결과 보기 : "+ret);
    	
		//폴더를 선택 했다면 선택 폴더의 모든 이미지를 불러옵니다.
		if(ret == 0){
			text_pcimage_path.setText(jfiledialog.getSelectedFile().toString());		
		}
	}
	
		
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		System.out.println(e.getID());
		
		switch(e.getActionCommand()){
		case "저장":
			setSave();
			setValues();
			break;
		case "동기화 시작":
			start_shyc();
			break;
		case "분류엑셀출력":
			creat_xls();
			break;
		case "종료":
			this.dispose();
			break;
		case "서버저장":
			setServerSave();
			System.exit(1);
			break;
		}		
		
	}
}
