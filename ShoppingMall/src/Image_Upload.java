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
		lblFtp.setFont(new Font("����", Font.PLAIN, 13));
		lblFtp.setHorizontalAlignment(SwingConstants.CENTER);
		lblFtp.setBounds(335, 13, 278, 15);
		panel.add(lblFtp);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(335, 41, 387, 254);
		panel.add(scrollPane);
		
		
		/**
		 * �� ���� ����
		 */
		DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
		celAlignCenter.setHorizontalAlignment(JLabel.CENTER);
		
		DefaultTableCellRenderer celAlignRight = new DefaultTableCellRenderer();		
		celAlignRight.setHorizontalAlignment(JLabel.RIGHT);
						
		String[] head = {"����", "���ϸ�(���ڵ�)", "Ȯ����", "���"};
		dtm = new DefaultTableModel();
		dtm.setColumnIdentifiers(head);		
		
		table_ftp_view = new JTable();
		table_ftp_view.setModel(dtm);
		
		// ����� �÷��� ���� ���� ���������� ���ֵ���, �ڵ������ϴ� ���� �����Ѵ�. 
		table_ftp_view.setAutoCreateColumnsFromModel(false);
		
		// ���̺��� ���ø�带 �����Ѵ�
		table_ftp_view.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table_ftp_view);
		
		//��ũ�����ο� ������ ������� �����Ѵ�.		
		scrollPane.getViewport().setBackground(Color.WHITE);
		
		//�ڵ����� ����� ����Ѵ�.
		table_ftp_view.setAutoCreateRowSorter(true);		
		
		//�ڵ����� ����� ÷���Ѵ�.
		table_ftp_view.setRowSorter(new TableRowSorter<TableModel>(dtm));
		
		table_ftp_view.getTableHeader().setReorderingAllowed(false);  //�̵��Ұ�
		table_ftp_view.getTableHeader().getColumnModel().getColumn(0).setCellRenderer(celAlignCenter);
		
		table_ftp_view.getColumn("����").setPreferredWidth(40);
		table_ftp_view.getColumn("����").setCellRenderer(celAlignCenter);
		
		table_ftp_view.getColumn("���ϸ�(���ڵ�)").setPreferredWidth(80);
		
		table_ftp_view.getColumn("Ȯ����").setPreferredWidth(40);
				
		table_ftp_view.getColumn("���").setPreferredWidth(100);
		
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
					System.out.println("���콺 Ŭ�� �ƽ��ϴ�.");					
					//�̹����� �ҷ��ɴϴ�.
					image_Show();
				} 
			}
		});
		
		JButton btn_ftpview = new JButton("\uAC80\uC0C9");
		btn_ftpview.setBounds(625, 9, 97, 23);
		panel.add(btn_ftpview);
		btn_ftpview.setActionCommand("�˻�");
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

	//������ �˻��մϴ�.
	private void pathSearch(){
		
		JFileChooser jfiledialog = new JFileChooser();		
		jfiledialog.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);		
			 
		//���ϼ��� â�� ���ϴ�.		
		jfiledialog.setCurrentDirectory(new File(text_path.getText()));		
		int ret = jfiledialog.showOpenDialog(this);
		
		//������ ���� �ߴٸ� ���� ������ ��� �̹����� �ҷ��ɴϴ�.
		if(ret == jfiledialog.APPROVE_OPTION){
			
			dlm.removeAllElements();
			text_path.setText(jfiledialog.getSelectedFile().toString());
			
			//���� ��� �ҷ��ͼ� ����Ʈ�� �ѷ��ֱ�
			File dirFile=new File(text_path.getText());
			File []fileList=dirFile.listFiles();
			final String[] FILE_EXTENSION = {"jpg","gif","png","bmp"};
			
			for(File tempFile : fileList) {
				if(tempFile.isFile()) {
					//String tempPath=tempFile.getParent();				
					//������ �ҷ��ɴϴ�.				
					String fileName = tempFile.getName().toString();				
				
					//���ϸ��� "." �� �������� ���ڸ��� �߶� ���ϴ�.				
					String ext = fileName.substring(fileName.lastIndexOf(".")+1, fileName.length()).toString();
					
					//Ȯ���ڸ� �˻��ؼ� �׸� ���ϸ� �ҷ� �ɴϴ�.				
					for(int i =0; i < FILE_EXTENSION.length; i++){					
						if(ext.equals(FILE_EXTENSION[i])){
							dlm.addElement(tempFile.getName());							
						}
					}
				}
			}
		}
		
	}
	
	//������ FTP������ ���ε� �մϴ�.
	private void uploadImage(){
		
		this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
		
		//FTP�� �̹����� ���ε� �մϴ�.		
		//FTP ������ ���� �մϴ�.
		FTPClient ftpConn = new FTPClient();
				
		try {	
			ftpConn.connect(Server_Config.getFTPIP(), Server_Config.getFTPPORT());
			ftpConn.login(Server_Config.getFTPID(), Server_Config.getFTPPW());
		} catch (IllegalStateException | IOException | FTPIllegalReplyException
				| FTPException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(this, "���ӿ� ���� �߽��ϴ�.");
			e.printStackTrace();
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			 return;
		}
				
		//�ܵ����� ��θ� �����մϴ�.		
		try {			
			ftpConn.changeDirectory(Server_Config.getFTPMARTPATH());			
		} catch (IllegalStateException | IOException | FTPIllegalReplyException
				| FTPException e) {
			// TODO Auto-generated catch block
				//���丮 ��� �������߻��ߴٸ�
				String test;
				try {
					//���� ���丮�� üũ�ϰ�
					test = ftpConn.currentDirectory();
					System.out.println(test);
					//�װ��� ��Ʈ ���丮��� 
					if(test.equals("/")){
						//���丮�� �����Ѵ�.
						ftpConn.createDirectory(Server_Config.getFTPMARTPATH());
					}			
				} catch (IllegalStateException | IOException
						| FTPIllegalReplyException | FTPException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			JOptionPane.showMessageDialog(this, "���丮 ���濡 ���� �߽��ϴ�. �ٽ� �õ��� �ּ���!");	
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			e.printStackTrace();
			return;
		}catch(NullPointerException e){
			
			JOptionPane.showMessageDialog(this, "�ܵ�������� ������ �ϼž� �մϴ�.");	
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			e.printStackTrace();
			return;
		}
		
		//������ ������ �ҷ��ɴϴ�.
		List<String> temp = list_image.getSelectedValuesList();	
		if(temp.size() <= 0){
			JOptionPane.showMessageDialog(this, "���ε��� ������ ������ �ּ���!");
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			return;
		}
		
		//������ ���ε� �մϴ�.
		ArrayList<String> queryList = new ArrayList<String>();
		
		//���� ������ �����մϴ�.
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
					JOptionPane.showMessageDialog(this, "������ ���ε� ���� ���߽��ϴ�.");
					e.printStackTrace();
					this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					return;
				}
				
				//�̹������ �����ϱ� ���� ������ ��� �Ӵϴ�.
				String query = "Insert into FTP_Image values('"+file.substring(0, file.indexOf("."))+"', '"+Server_Config.getFTPMARTPATH()+"', '0', '', '"+file.substring(file.indexOf(".")+1, file.length())+"')";
				queryList.add(query);
			}else{
				//���ε� ���н� �����մϴ�.
				uploadFail.add(tempFile.getName());
			}
			
		}
		
		//������ ��ǰ�� �������� �մϴ�.		
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
				JOptionPane.showMessageDialog(this, "������ �Ϸ� �Ǿ����ϴ�.");
				//�˻�â�� ������ ǥ���մϴ�.
				viewImgaeSearch();
				break;
			default:
				this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				JOptionPane.showMessageDialog(this, "������ �Ϸ� ���� ���߽��ϴ�.");
				break;
			}				
		}
				
		//�������մϴ�.
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
	
	//���� ������ �� ũ�⸦ üũ�մϴ�.
	private boolean fileSizeCheck(File file){
				
		  try{
			  
			 String name = file.getName();
			 name = name.substring(name.lastIndexOf('.')+1, name.length());
			 System.out.println(name);			 
			 
		   BufferedImage bi = ImageIO.read(file);
		   
		   //�Ϲ����� �̹��� ��ü�� ��� getWidth�޼ҵ�� getHeight�޼ҵ尡 Ʋ���ϴ�. 
		   //�Ķ���Ͱ� �־� ���� ����ϹǷ� BufferedImage �� ���ô°� �´°� �����ϴ�.
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
	
	//�̹��� ����
	public void viewImgaeSearch(){
		
		dtm.setRowCount(0);
		//�˻��� �������� �̹����� �ҷ��ɴϴ�.		
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
	
	//���õ� �̹����� �����ݴϴ�.
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
	
	//���õ� �̹����� ���� �մϴ�.
	private void ftp_image_delete(){
		
		//Ŀ�� ����(�𷡽ð�)
		this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
		
		//���������� �ҷ��ɴϴ�.
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
			JOptionPane.showMessageDialog(this, "���ӿ� ���� �߽��ϴ�.");
			e.printStackTrace();
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			 return;
		}
		
		//�ܵ����� ��θ� �����մϴ�.		
		try {			
			ftpConn.changeDirectory(Server_Config.getFTPMARTPATH());			
		} catch (IllegalStateException | IOException | FTPIllegalReplyException
				| FTPException e) {
			// TODO Auto-generated catch block
			
			JOptionPane.showMessageDialog(this, "���丮 ���濡 ���� �߽��ϴ�.");	
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			e.printStackTrace();
			return;
		}catch(NullPointerException e){
			
			JOptionPane.showMessageDialog(this, "�ܵ�������� ������ �ϼž� �մϴ�. ȯ�漳������ ������ �ּ���");	
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			e.printStackTrace();
			return;
		}
		
		String file_name = temp.get(1)+"."+temp.get(2);
		System.out.println(file_name);
		//������ �����մϴ�.
			
		try {
			ftpConn.deleteFile(file_name.trim());
		} catch (IllegalStateException | IOException
				| FTPIllegalReplyException | FTPException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			JOptionPane.showMessageDialog(this, "������ ���� ���� ���߽��ϴ�.");				
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			return;
		}
	
	
		//�������� ���� �մϴ�.
		
		//�̹������ ������ ���� �ϱ� ���� ������ �ۼ��մϴ�.
		String query = "Delete From Ftp_Image Where Barcode='"+temp.get(1).toString().trim()+"' and Path_Gubun='0' ";
			
		//���ε��� ������ ���� DB������ ���� �մϴ�.		
		/*if(!ms_connect.connect_errorCheck()){
			JOptionPane.showMessageDialog(this, "��񿡼� ������ �������� ���߽��ϴ�.");
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			return;
		}*/
			
		ms_connect.setImageSetting();
		int result = ms_connect.connect_update(query);
		switch(result){
		case 0:
			JOptionPane.showMessageDialog(this, "������ ������ ���� �Ǿ����ϴ�.");
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			viewImgaeSearch();
			break;
		default:			
			JOptionPane.showMessageDialog(this, "���� ���� ���߽��ϴ�.");
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			break;
		}	
	
		//�������մϴ�.
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
	
	//Ftp ������ ������ ������ 
	private void ftpFile_DownLoad(){
		
		JFileChooser jfiledialog = new JFileChooser();		
		jfiledialog.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);		
			 
		//���ϼ��� â�� ���ϴ�.		
		jfiledialog.setCurrentDirectory(new File(text_path.getText()));		
		int ret = jfiledialog.showOpenDialog(this);
		
		//������ ���� �ߴٸ� ���� ������ ��� �̹����� �ҷ��ɴϴ�.
		if(ret == jfiledialog.APPROVE_OPTION){
			
			//Ŀ�� ����(�𷡽ð�)
			this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
			
			//���������� �ҷ��ɴϴ�.
			int row = table_ftp_view.getSelectedRow();
			int col = table_ftp_view.getColumnCount();
			
			Vector<Object> temp = new Vector<Object>(); 
			
			for(int i =0; i < col; i++){			
				temp.add(dtm.getValueAt(row, i));			
			}		
			
			//ftp������ �����մϴ�.
			FTPClient ftpConn = new FTPClient();
			
			try {			
				ftpConn.connect(Server_Config.getFTPIP(), Server_Config.getFTPPORT());
				ftpConn.login(Server_Config.getFTPID(), Server_Config.getFTPPW());
				
			} catch (IllegalStateException | IOException | FTPIllegalReplyException
					| FTPException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(this, "���ӿ� ���� �߽��ϴ�.");
				e.printStackTrace();
				this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				 return;
			}
			
			//�ܵ����� ��θ� �����մϴ�.		
			try {			
				ftpConn.changeDirectory(Server_Config.getFTPMARTPATH());			
			} catch (IllegalStateException | IOException | FTPIllegalReplyException
					| FTPException e) {
				// TODO Auto-generated catch block
				
				JOptionPane.showMessageDialog(this, "���丮 ���濡 ���� �߽��ϴ�.");	
				this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				e.printStackTrace();
				return;
			}catch(NullPointerException e){
				
				JOptionPane.showMessageDialog(this, "�ܵ�������� ������ �ϼž� �մϴ�. ȯ�漳������ ������ �ּ���");	
				this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				e.printStackTrace();
				return;
			}
			
			//������ ã���ϴ�.
			String file_name = temp.get(1)+"."+temp.get(2);
			System.out.println(file_name);
			//������ �����մϴ�.
				
			File dirFile=new File(jfiledialog.getSelectedFile().toString()+"/"+file_name.trim());
			
			try {				
				if(!dirFile.isFile()){
					dirFile.createNewFile();
				}
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();				
				JOptionPane.showMessageDialog(this, "���� �ٿ�ε� ���� ���߽��ϴ�. \r\n" + e2.getMessage());
				this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				return;
			}
			
			try {
				try {
					ftpConn.download(file_name.trim(), dirFile);
				} catch (FTPDataTransferException | FTPAbortedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					JOptionPane.showMessageDialog(this, "���� �ٿ�ε� ���� ���߽��ϴ�. \r\n" + e.getMessage());
					this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					return;
				}
			} catch (IllegalStateException | IOException
					| FTPIllegalReplyException | FTPException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				JOptionPane.showMessageDialog(this, "���� �ٿ�ε带 ���� ���߽��ϴ�. \r\n" + e1.getMessage() );				
				this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				return;
			}		
		
			//�������մϴ�.
			try {
				if(ftpConn.isConnected()){
					ftpConn.disconnect(true);
				}			
			} catch (IllegalStateException | IOException | FTPIllegalReplyException
					| FTPException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			
			JOptionPane.showMessageDialog(this, "���� �ٿ�ε� �Ϸ� \r\n ���������� �ٿ�ε� �Ǿ����ϴ�." );				
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}		
	}
	
	//������ �ڸ�	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("��ư����"+arg0.getActionCommand());
		switch(arg0.getActionCommand()){
		case "< ��������":
			pathSearch();
			break;
		case "< ���û���":
						
			List<String> temp = list_image.getSelectedValuesList();
			int[] number = list_image.getSelectedIndices();
			
			if(temp.size() <= 0){
				JOptionPane.showMessageDialog(this, "������ ������ ������ �ּ���!!");
				break;
			}
			
			if(JOptionPane.showConfirmDialog(this, "������ ������ ���� �ұ��?", "", JOptionPane.YES_NO_OPTION) == 1 ){
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
			
		case "< ��ü����":
			
			if(dlm.size() <= 0){
				JOptionPane.showMessageDialog(this, "��Ͽ� ������ ���� ���� �ʽ��ϴ�.");
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
			
		case "���ε� >>":
			uploadImage();
			break;			
		case "�˻�":
			viewImgaeSearch();
			break;			
		case "����":			
			if(table_ftp_view.getSelectedRow() == -1){
				JOptionPane.showMessageDialog(this, "������ ������ ������ �ּ���!!");
				return;
			}			
			if(JOptionPane.showConfirmDialog(this, "������ ������ ���� �Ͻðڽ��ϱ�?", "���ϻ���", JOptionPane.OK_CANCEL_OPTION) == 0){
				ftp_image_delete();
			}			
			break;			
		case "�ٿ�ε�":			
			if(table_ftp_view.getSelectedRow() == -1){
				JOptionPane.showMessageDialog(this, "�ٿ�ε��� ������ ������ �ּ���!!");
				return;
			}			
			ftpFile_DownLoad();			
			break;
		}		
	}
}



