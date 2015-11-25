import it.sauronsoftware.ftp4j.FTPAbortedException;
import it.sauronsoftware.ftp4j.FTPClient;
import it.sauronsoftware.ftp4j.FTPDataTransferException;
import it.sauronsoftware.ftp4j.FTPException;
import it.sauronsoftware.ftp4j.FTPIllegalReplyException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JTable;

import java.awt.Label;

import javax.swing.border.BevelBorder;
import javax.swing.event.ListSelectionListener;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.SwingConstants;

import java.awt.Font;


public class Image_Upload extends  JPanel implements ActionListener{

	private DefaultListModel<String> dlm;
	private JList<String> list_image;
	private JLabel label_show_image;
	private DefaultTableModel dtm;
	
	private static final long serialVersionUID = 1L;
	private JTextField text_path;
	private JTable table_ftp_view;

	Ms_Connect ms_connect;
	
	public Image_Upload() {
		
		setBounds(0, 0, 1020, 665);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(12, 10, 996, 305);
		add(panel);
		panel.setLayout(null);
		
		text_path = new JTextField();
		text_path.setBounds(12, 10, 200, 21);
		panel.add(text_path);
		text_path.setColumns(10);
		text_path.setText("C:\\");
		
		JButton btn_path = new JButton("< \uD3F4\uB354\uC120\uD0DD");
		btn_path.setBounds(224, 9, 99, 23);
		panel.add(btn_path);
		btn_path.addActionListener(this);
		
		JButton btn_upload = new JButton("\uC5C5\uB85C\uB4DC >>");
		btn_upload.setBounds(224, 250, 99, 45);
		panel.add(btn_upload);
		btn_upload.addActionListener(this);
		
		dlm = new DefaultListModel<String>();
		
		list_image = new JList<String>();
		list_image.setBounds(0, 0, 274, 254);
		//panel.add(list_image);
		list_image.setModel(dlm);
				
		JScrollPane jsp = new JScrollPane();
		jsp.setBounds(12,41,200,254);
		jsp.setViewportView(list_image);
		
		panel.add(jsp);
		
		JLabel lblFtp = new JLabel("\uB2E8\uB3C5\uD3F4\uB354 FTP \uC774\uBBF8\uC9C0");
		lblFtp.setFont(new Font("굴림", Font.PLAIN, 13));
		lblFtp.setHorizontalAlignment(SwingConstants.CENTER);
		lblFtp.setBounds(335, 13, 278, 15);
		panel.add(lblFtp);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(335, 41, 387, 254);
		panel.add(scrollPane);
		
		
		/**
		 * 셀 간격 조정
		 */
		DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
		celAlignCenter.setHorizontalAlignment(JLabel.CENTER);
		
		DefaultTableCellRenderer celAlignRight = new DefaultTableCellRenderer();		
		celAlignRight.setHorizontalAlignment(JLabel.RIGHT);
						
		String[] head = {"순번", "파일명(바코드)", "확장자", "비고"};
		dtm = new DefaultTableModel();
		dtm.setColumnIdentifiers(head);		
		
		table_ftp_view = new JTable();
		table_ftp_view.setModel(dtm);
		
		// 헤더의 컬럼의 라벨을 내가 직접제어할 수있도록, 자동생성하는 것을 금지한다. 
		table_ftp_view.setAutoCreateColumnsFromModel(false);
		
		// 테이블의 선택모드를 설정한다
		table_ftp_view.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table_ftp_view);
		
		//스크롤페인에 배경색을 흰색으로 변경한다.		
		scrollPane.getViewport().setBackground(Color.WHITE);
		
		//자동정렬 기능을 사용한다.
		table_ftp_view.setAutoCreateRowSorter(true);		
		
		//자동정렬 기능을 첨가한다.
		table_ftp_view.setRowSorter(new TableRowSorter<TableModel>(dtm));
		
		table_ftp_view.getTableHeader().setReorderingAllowed(false);  //이동불가
		table_ftp_view.getTableHeader().getColumnModel().getColumn(0).setCellRenderer(celAlignCenter);
		
		table_ftp_view.getColumn("순번").setPreferredWidth(40);
		table_ftp_view.getColumn("순번").setCellRenderer(celAlignCenter);
		
		table_ftp_view.getColumn("파일명(바코드)").setPreferredWidth(80);
		
		table_ftp_view.getColumn("확장자").setPreferredWidth(40);
				
		table_ftp_view.getColumn("비고").setPreferredWidth(100);
		
		table_ftp_view.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub				
				if (e.getButton() == 1) {
					System.out.println("마우스 클릭 됐습니다.");					
					//이미지를 불러옵니다.
					image_Show();
				} 
			}
		});
		
		JButton btn_ftpview = new JButton("\uAC80\uC0C9");
		btn_ftpview.setBounds(625, 9, 97, 23);
		panel.add(btn_ftpview);
		btn_ftpview.setActionCommand("검색");
		btn_ftpview.addActionListener(this);
				
		label_show_image = new JLabel();
		label_show_image.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		label_show_image.setBounds(734, 41, 250, 254);
		//label_show_image.setBounds(0, 0, 250, 250);
		panel.add(label_show_image);
		
		JButton btn_delete = new JButton("\uC0AD\uC81C");
		btn_delete.setBounds(920, 9, 64, 23);
		btn_delete.addActionListener(this);
		panel.add(btn_delete);
		
		
		JButton btn_download = new JButton("\uB2E4\uC6B4\uB85C\uB4DC");
		btn_download.setBounds(734, 9, 97, 23);
		btn_download.addActionListener(this);
		panel.add(btn_download);
		
		
		JButton btn_all_select = new JButton("< \uC804\uCCB4\uC120\uD0DD");
		btn_all_select.setBounds(224, 209, 99, 31);
		btn_all_select.addActionListener(this);
		panel.add(btn_all_select);
		
		
		JButton btn_select_delete = new JButton("< \uC120\uD0DD\uC0AD\uC81C");
		btn_select_delete.setBounds(224, 168, 99, 31);
		btn_select_delete.addActionListener(this);
		panel.add(btn_select_delete);
		
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(12, 325, 996, 326);
		
		add(panel_1);
		
		ms_connect = new Ms_Connect();
		
	}

	//폴더를 검색합니다.
	private void pathSearch(){
		
		JFileChooser jfiledialog = new JFileChooser();		
		jfiledialog.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);		
			 
		//파일선택 창을 띄웁니다.		
		jfiledialog.setCurrentDirectory(new File(text_path.getText()));		
		int ret = jfiledialog.showOpenDialog(this);
		
		//폴더를 선택 했다면 선택 폴더의 모든 이미지를 불러옵니다.
		if(ret == jfiledialog.APPROVE_OPTION){
			
			dlm.removeAllElements();
			text_path.setText(jfiledialog.getSelectedFile().toString());
			
			//파일 목록 불러와서 리스트에 뿌려주기
			File dirFile=new File(text_path.getText());
			File []fileList=dirFile.listFiles();
			final String[] FILE_EXTENSION = {"jpg","gif","png","bmp"};
			
			for(File tempFile : fileList) {
				if(tempFile.isFile()) {
					//String tempPath=tempFile.getParent();				
					//파일을 불러옵니다.				
					String fileName = tempFile.getName().toString();				
				
					//파일명을 "." 을 기점으로 뒷자리를 잘라 냅니다.				
					String ext = fileName.substring(fileName.lastIndexOf(".")+1, fileName.length()).toString();
					
					//확장자를 검사해서 그림 파일만 불러 옵니다.				
					for(int i =0; i < FILE_EXTENSION.length; i++){					
						if(ext.equals(FILE_EXTENSION[i])){
							dlm.addElement(tempFile.getName());							
						}
					}
				}
			}
		}
		
	}
	
	//파일을 FTP서버로 업로드 합니다.
	private void uploadImage(){
		
		this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
		
		//FTP로 이미지를 업로드 합니다.		
		//FTP 서버에 접속 합니다.
		FTPClient ftpConn = new FTPClient();
				
		try {	
			ftpConn.connect(Server_Config.getFTPIP(), Server_Config.getFTPPORT());
			ftpConn.login(Server_Config.getFTPID(), Server_Config.getFTPPW());
		} catch (IllegalStateException | IOException | FTPIllegalReplyException
				| FTPException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(this, "접속에 실패 했습니다.");
			e.printStackTrace();
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			 return;
		}
				
		//단독폴더 경로를 설정합니다.		
		try {			
			ftpConn.changeDirectory(Server_Config.getFTPMARTPATH());			
		} catch (IllegalStateException | IOException | FTPIllegalReplyException
				| FTPException e) {
			// TODO Auto-generated catch block
				//디렉토리 없어서 오류가발생했다면
				String test;
				try {
					//현재 디렉토리를 체크하고
					test = ftpConn.currentDirectory();
					System.out.println(test);
					//그것이 루트 디렉토리라면 
					if(test.equals("/")){
						//디렉토리를 생성한다.
						ftpConn.createDirectory(Server_Config.getFTPMARTPATH());
					}			
				} catch (IllegalStateException | IOException
						| FTPIllegalReplyException | FTPException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			JOptionPane.showMessageDialog(this, "디렉토리 변경에 실패 했습니다. 다시 시도해 주세요!");	
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			e.printStackTrace();
			return;
		}catch(NullPointerException e){
			
			JOptionPane.showMessageDialog(this, "단독폴더경로 설정을 하셔야 합니다.");	
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			e.printStackTrace();
			return;
		}
		
		//전송할 파일을 불러옵니다.
		List<String> temp = list_image.getSelectedValuesList();	
		if(temp.size() <= 0){
			JOptionPane.showMessageDialog(this, "업로드할 파일을 선택해 주세요!");
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			return;
		}
		
		//파일을 업로드 합니다.
		ArrayList<String> queryList = new ArrayList<String>();
		
		//실패 파일을 저장합니다.
		ArrayList<String> uploadFail = new ArrayList<String>();
		
		for(String file: temp){
			
			File tempFile = new File(text_path.getText(), file);
			
			if(fileSizeCheck(tempFile)){			
				try {
					ftpConn.upload(tempFile);
				} catch (IllegalStateException | IOException
						| FTPIllegalReplyException | FTPException
						| FTPDataTransferException | FTPAbortedException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(this, "파일을 업로드 하지 못했습니다.");
					e.printStackTrace();
					this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					return;
				}
				
				//이미지디비에 저장하기 위해 쿼리를 모아 둡니다.
				String query = "Insert into FTP_Image values('"+file.substring(0, file.indexOf("."))+"', '"+Server_Config.getFTPMARTPATH()+"', '0', '', '"+file.substring(file.indexOf(".")+1, file.length())+"')";
				queryList.add(query);
			}else{
				//업로드 실패시 저장합니다.
				uploadFail.add(tempFile.getName());
			}
			
		}
		
		//저장할 상품이 있을때만 합니다.		
		if(queryList.size() > 0){
			ms_connect.setImageSetting();
			int result = ms_connect.connect_update(queryList);
			switch(result){
			case 0:
	
				String queryOver = "Delete From Ftp_Image Where idx in( select a.idx as idx "
								+"From Ftp_Image A INNER JOIN ( Select Min(idx) as idx, barcode, count(*) as overlapCount "
								+ "From Ftp_Image Where path_gubun='0' and path='"+Server_Config.getFTPMARTPATH()+"' Group by barcode Having count(*) > 1 ) B "
								+ "on a.barcode = b.barcode and A.idx <> B.idx and a.path_gubun='0' and path='"+Server_Config.getFTPMARTPATH()+"')";
				
				ms_connect.connect_update(queryOver);
				this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				JOptionPane.showMessageDialog(this, "저장이 완료 되었습니다.");
				//검색창에 파일을 표시합니다.
				viewImgaeSearch();
				break;
			default:
				this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				JOptionPane.showMessageDialog(this, "저장을 완료 하지 못했습니다.");
				break;
			}				
		}
				
		//마무리합니다.
		try {
			if(ftpConn.isConnected()){
				ftpConn.disconnect(true);
			}			
		} catch (IllegalStateException | IOException | FTPIllegalReplyException
				| FTPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//파일 사이즈 및 크기를 체크합니다.
	private boolean fileSizeCheck(File file){
				
		  try{
			  
			 String name = file.getName();
			 name = name.substring(name.lastIndexOf('.')+1, name.length());
			 System.out.println(name);			 
			 
		   BufferedImage bi = ImageIO.read(file);
		   
		   //일반적인 이미지 객체의 경우 getWidth메소드와 getHeight메소드가 틀립니다. 
		   //파라미터가 있어 쓰기 곤란하므로 BufferedImage 로 쓰시는게 맞는것 같습니다.
		   int width=bi.getWidth();
		   int height=bi.getHeight();		   
		   
		   if(width > 500 || height > 500) {			   
			 BufferedImage outputImage = new BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB);
			 Graphics2D g = outputImage.createGraphics();
			 g.drawImage(bi, 0, 0, 500, 500, null);			   
			   			   
			 File out = new File(file.getAbsolutePath());
			 FileOutputStream fos = new FileOutputStream(out);
			 ImageIO.write(outputImage, name, fos);			 
		   }		   
		  }catch(Exception e){			  
			  e.printStackTrace();
			  return false;
		  }		  
		  return true;
	}
	
	//이미지 서버
	public void viewImgaeSearch(){
		
		dtm.setRowCount(0);
		//검색시 서버에서 이미지를 불러옵니다.		
		ms_connect.setImageSetting();
		String query = "Select * From Ftp_Image Where path='"+Server_Config.getFTPMARTPATH()+"' and path_gubun='0' ";
		ArrayList<HashMap<String, String>> temp_map = ms_connect.connection(query);
		
		Iterator<HashMap<String, String>> iter = temp_map.iterator();
		int i =1;
		while(iter.hasNext()){
			HashMap<String, String> map = iter.next(); 
			Vector<String> item = new Vector<String>();	
			item.add(String.valueOf(i++));
			item.add(map.get("Barcode"));
			item.add(map.get("Ext"));
			item.add(map.get("G_Name"));
			
			dtm.addRow(item);
		}
		
		label_show_image.setIcon(null);
		
	}
	
	//선택된 이미지를 보여줍니다.
	private void image_Show(){
		
		int row = table_ftp_view.getSelectedRow();
		int col = table_ftp_view.getColumnCount();
		
		Vector<Object> temp = new Vector<Object>(); 
		
		for(int i =0; i < col; i++){			
			temp.add(dtm.getValueAt(row, i));			
		}		
	
		Image image = null;
	    try {
	       	
	        URL url = new URL("http://14.38.161.45:7080/"+Server_Config.getFTPMARTPATH()+"/"+temp.get(1)+"."+temp.get(2));
	        System.out.println(url.toString());
	        image = ImageIO.read(url);
	        label_show_image.setIcon(new ImageIcon(image.getScaledInstance(250, 250, Image.SCALE_SMOOTH)));    
	    } catch (IOException e) {
	    	e.printStackTrace();
	    }	   
	    
	   // l.setIcon(new ImageIcon(image.getScaledInstance(150, 150, Image.SCALE_SMOOTH)));
	}
	
	//선택된 이미지를 삭제 합니다.
	private void ftp_image_delete(){
		
		//커서 변경(모래시계)
		this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
		
		//선택정보를 불러옵니다.
		int row = table_ftp_view.getSelectedRow();
		int col = table_ftp_view.getColumnCount();
		
		Vector<Object> temp = new Vector<Object>(); 
		
		for(int i =0; i < col; i++){			
			temp.add(dtm.getValueAt(row, i));			
		}		
		
		FTPClient ftpConn = new FTPClient();
		
		try {			
			ftpConn.connect(Server_Config.getFTPIP(), Server_Config.getFTPPORT());
			ftpConn.login(Server_Config.getFTPID(), Server_Config.getFTPPW());
			
		} catch (IllegalStateException | IOException | FTPIllegalReplyException
				| FTPException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(this, "접속에 실패 했습니다.");
			e.printStackTrace();
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			 return;
		}
		
		//단독폴더 경로를 설정합니다.		
		try {			
			ftpConn.changeDirectory(Server_Config.getFTPMARTPATH());			
		} catch (IllegalStateException | IOException | FTPIllegalReplyException
				| FTPException e) {
			// TODO Auto-generated catch block
			
			JOptionPane.showMessageDialog(this, "디렉토리 변경에 실패 했습니다.");	
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			e.printStackTrace();
			return;
		}catch(NullPointerException e){
			
			JOptionPane.showMessageDialog(this, "단독폴더경로 설정을 하셔야 합니다. 환경설정에서 설정해 주세요");	
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			e.printStackTrace();
			return;
		}
		
		String file_name = temp.get(1)+"."+temp.get(2);
		System.out.println(file_name);
		//파일을 삭제합니다.
			
		try {
			ftpConn.deleteFile(file_name.trim());
		} catch (IllegalStateException | IOException
				| FTPIllegalReplyException | FTPException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			JOptionPane.showMessageDialog(this, "파일을 삭제 하지 못했습니다.");				
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			return;
		}
	
	
		//서버에서 삭제 합니다.
		
		//이미지디비에 파일을 삭제 하기 위해 쿼리를 작성합니다.
		String query = "Delete From Ftp_Image Where Barcode='"+temp.get(1).toString().trim()+"' and Path_Gubun='0' ";
			
		//업로드한 정보를 매장 DB서버에 저장 합니다.		
		/*if(!ms_connect.connect_errorCheck()){
			JOptionPane.showMessageDialog(this, "디비에서 파일을 삭제하지 못했습니다.");
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			return;
		}*/
			
		ms_connect.setImageSetting();
		int result = ms_connect.connect_update(query);
		switch(result){
		case 0:
			JOptionPane.showMessageDialog(this, "선택한 파일이 삭제 되었습니다.");
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			viewImgaeSearch();
			break;
		default:			
			JOptionPane.showMessageDialog(this, "삭제 하지 못했습니다.");
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			break;
		}	
	
		//마무리합니다.
		try {
			if(ftpConn.isConnected()){
				ftpConn.disconnect(true);
			}			
		} catch (IllegalStateException | IOException | FTPIllegalReplyException
				| FTPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	//Ftp 서버의 파일을 지정한 
	private void ftpFile_DownLoad(){
		
		JFileChooser jfiledialog = new JFileChooser();		
		jfiledialog.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);		
			 
		//파일선택 창을 띄웁니다.		
		jfiledialog.setCurrentDirectory(new File(text_path.getText()));		
		int ret = jfiledialog.showOpenDialog(this);
		
		//폴더를 선택 했다면 선택 폴더의 모든 이미지를 불러옵니다.
		if(ret == jfiledialog.APPROVE_OPTION){
			
			//커서 변경(모래시계)
			this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
			
			//선택정보를 불러옵니다.
			int row = table_ftp_view.getSelectedRow();
			int col = table_ftp_view.getColumnCount();
			
			Vector<Object> temp = new Vector<Object>(); 
			
			for(int i =0; i < col; i++){			
				temp.add(dtm.getValueAt(row, i));			
			}		
			
			//ftp서버에 접속합니다.
			FTPClient ftpConn = new FTPClient();
			
			try {			
				ftpConn.connect(Server_Config.getFTPIP(), Server_Config.getFTPPORT());
				ftpConn.login(Server_Config.getFTPID(), Server_Config.getFTPPW());
				
			} catch (IllegalStateException | IOException | FTPIllegalReplyException
					| FTPException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(this, "접속에 실패 했습니다.");
				e.printStackTrace();
				this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				 return;
			}
			
			//단독폴더 경로를 설정합니다.		
			try {			
				ftpConn.changeDirectory(Server_Config.getFTPMARTPATH());			
			} catch (IllegalStateException | IOException | FTPIllegalReplyException
					| FTPException e) {
				// TODO Auto-generated catch block
				
				JOptionPane.showMessageDialog(this, "디렉토리 변경에 실패 했습니다.");	
				this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				e.printStackTrace();
				return;
			}catch(NullPointerException e){
				
				JOptionPane.showMessageDialog(this, "단독폴더경로 설정을 하셔야 합니다. 환경설정에서 설정해 주세요");	
				this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				e.printStackTrace();
				return;
			}
			
			//파일을 찾습니다.
			String file_name = temp.get(1)+"."+temp.get(2);
			System.out.println(file_name);
			//파일을 삭제합니다.
				
			File dirFile=new File(jfiledialog.getSelectedFile().toString()+"/"+file_name.trim());
			
			try {				
				if(!dirFile.isFile()){
					dirFile.createNewFile();
				}
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();				
				JOptionPane.showMessageDialog(this, "파일 다운로드 하지 못했습니다. \r\n" + e2.getMessage());
				this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				return;
			}
			
			try {
				try {
					ftpConn.download(file_name.trim(), dirFile);
				} catch (FTPDataTransferException | FTPAbortedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					JOptionPane.showMessageDialog(this, "파일 다운로드 하지 못했습니다. \r\n" + e.getMessage());
					this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					return;
				}
			} catch (IllegalStateException | IOException
					| FTPIllegalReplyException | FTPException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				JOptionPane.showMessageDialog(this, "파일 다운로드를 하지 못했습니다. \r\n" + e1.getMessage() );				
				this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				return;
			}		
		
			//마무리합니다.
			try {
				if(ftpConn.isConnected()){
					ftpConn.disconnect(true);
				}			
			} catch (IllegalStateException | IOException | FTPIllegalReplyException
					| FTPException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			
			JOptionPane.showMessageDialog(this, "파일 다운로드 완료 \r\n 정상적으로 다운로드 되었습니다." );				
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}		
	}
	
	//리스너 자리	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("버튼눌림"+arg0.getActionCommand());
		switch(arg0.getActionCommand()){
		case "< 폴더선택":
			pathSearch();
			break;
		case "< 선택삭제":
						
			List<String> temp = list_image.getSelectedValuesList();
			int[] number = list_image.getSelectedIndices();
			
			if(temp.size() <= 0){
				JOptionPane.showMessageDialog(this, "삭제할 파일을 선택해 주세요!!");
				break;
			}
			
			if(JOptionPane.showConfirmDialog(this, "선택한 파일을 삭제 할까요?", "", JOptionPane.YES_NO_OPTION) == 1 ){
				break;
			}
			
			for(int i = temp.size()-1; i >= 0; i--){
				String file = temp.get(i);
				File tempFile = new File(text_path.getText(), file);
				if(tempFile.isFile()){
					if(tempFile.delete()){
						dlm.remove(number[i]);
					}
				}
			}			
			break;
			
		case "< 전체선택":
			
			if(dlm.size() <= 0){
				JOptionPane.showMessageDialog(this, "목록에 파일이 존재 하지 않습니다.");
				break;
			}
			
			Robot robot;
			try {
				robot = new Robot();
			} catch (AWTException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				break;
			}			
			list_image.requestFocus();
			robot.keyPress(KeyEvent.VK_CONTROL);
		    robot.keyPress(KeyEvent.VK_A);
		    robot.keyRelease(KeyEvent.VK_CONTROL);
		    robot.keyRelease(KeyEvent.VK_A);		
			break;
			
		case "업로드 >>":
			uploadImage();
			break;			
		case "검색":
			viewImgaeSearch();
			break;			
		case "삭제":			
			if(table_ftp_view.getSelectedRow() == -1){
				JOptionPane.showMessageDialog(this, "삭제할 파일을 선택해 주세요!!");
				return;
			}			
			if(JOptionPane.showConfirmDialog(this, "선택한 파일을 삭제 하시겠습니까?", "파일삭제", JOptionPane.OK_CANCEL_OPTION) == 0){
				ftp_image_delete();
			}			
			break;			
		case "다운로드":			
			if(table_ftp_view.getSelectedRow() == -1){
				JOptionPane.showMessageDialog(this, "다운로드할 파일을 선택해 주세요!!");
				return;
			}			
			ftpFile_DownLoad();			
			break;
		}		
	}
}



