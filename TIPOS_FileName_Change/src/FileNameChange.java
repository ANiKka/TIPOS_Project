
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JProgressBar;
import javax.swing.UIManager;

public class FileNameChange extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1864354348543483L;
	private JPanel contentPane;
	private JTextField text_folder;
	private JDialog dlg_help_info;
	
	private JFileChooser jfiledialog;	
	private File[] fileList;
		
	private JLabel label_complete;
	private JLabel label_start;
		
	private JProgressBar progressBar;
		
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FileNameChange frame = new FileNameChange();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FileNameChange() {		
		
		// ����ȭ���� ������� ����� ����� �ҷ����� ���� ����
		Dimension dim = new Dimension();
		Dimension dim1 = new Dimension();
		int xpos, ypos;
			
		try {
		    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) { 
		    System.err.println("Cannot set look and feel:" + e.getMessage ()); 
		}			
		
		
		//���� ���̾�α� ��ü ����		
		jfiledialog = new JFileChooser();
		jfiledialog.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		
		setTitle("\uD30C\uC77C\uBA85 \uBCC0\uACBD");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 410, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		
		// ���� �����ȭ�� ������ ��������
		dim = Toolkit.getDefaultToolkit().getScreenSize();
						
		// ���� ������ ������ ��������
		dim1 = getSize();
		
		// ȭ���� �߾ӿ� ��ġ ��Ŵ
		xpos = (int)(dim.getWidth()/2-dim1.getWidth()/2);
		ypos = (int)(dim.getHeight()/2-dim1.getHeight()/2);
		setLocation(xpos, ypos);
								
		setContentPane(contentPane);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane.setLayout(null);
		
		JLabel label1 = new JLabel("\uD3F4\uB354 \uC120\uD0DD :");
		label1.setBounds(12, 21, 67, 26);
		contentPane.add(label1);
		
		text_folder = new JTextField();
		text_folder.setBounds(76, 24, 236, 21);
		contentPane.add(text_folder);
		text_folder.setColumns(10);
		text_folder.setEditable(false);
		
		JButton btn_choose = new JButton("\uC120\uD0DD");
		btn_choose.setBounds(324, 23, 63, 23);
		contentPane.add(btn_choose);
		
		btn_choose.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int ret = jfiledialog.showOpenDialog(null);
				if(ret == jfiledialog.APPROVE_OPTION){
					text_folder.setText(jfiledialog.getSelectedFile().toString());
					filegetCount(jfiledialog.getSelectedFile());
				}
			}
		});
		
		progressBar = new JProgressBar();
		progressBar.setBounds(12, 93, 375, 31);
		progressBar.setStringPainted(true);
		contentPane.add(progressBar);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 134, 375, 26);
		contentPane.add(panel);
		
		label_complete = new JLabel("\uC644\uB8CC");
		panel.add(label_complete);
		
		JButton btn_start = new JButton("\uBCC0\uACBD\uD558\uAE30");
		btn_start.setBounds(62, 170, 139, 31);
		contentPane.add(btn_start);
		btn_start.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				startFileNameChange();				
			}
		});
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(12, 57, 375, 26);
		contentPane.add(panel_1);
		
		label_start = new JLabel("\uD30C\uC77C\uBA85");
		panel_1.add(label_start);
		
		JButton btn_close = new JButton("\uB2EB\uAE30");
		btn_close.setBounds(213, 170, 139, 30);
		contentPane.add(btn_close);
		btn_close.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				FileNameChange.this.dispose();
			}
		});
		
	}
	
	
	/*
	 * [���� ���� �ҷ�����]
	 * ���� ������ �ҷ��ͼ� ���ϸ��̺� ǥ�����ݴϴ�.
	 * 
	 * */
	public void filegetCount(File file){
				
		if(!file.exists()){
			return;
		}
		
		File dirFile = new File(file.getPath());
		fileList = dirFile.listFiles();
		
		label_start.setText("�����ϱ⸦ ���� �ּ���!!");
		label_complete.setText(" ���� ���� : " + fileList.length + "�� " );
		
		progressBar.setMinimum(0);
		progressBar.setMaximum(fileList.length);
		
		//System.out.println(fileList.length);
		/*for(File tempFile : fileList) {
			  if(tempFile.isFile()) {
			    String tempPath=tempFile.getParent();
			    String tempFileName=tempFile.getName();
			    System.out.println("Path="+tempPath);
			    System.out.println("FileName="+tempFileName);
			    *//*** Do something withd tempPath and temp FileName ^^; ***//*
			  }
		}*/
		
	}
	
	
	/**
	 * �⺻ ���̾� �α� ����
	 * 
	 * 
	 * */	
	public void show_Dialog(String title, String content){ 
		
        dlg_help_info= new JDialog(this, title);                 
        JButton btn_info_exit = new JButton("Ȯ��"); 
         
        JLabel lbl_info_date = new JLabel(content, JLabel.CENTER);
        JPanel pan_info = new JPanel(); 
        JPanel pan_info_btn = new JPanel(); 
                 
        pan_info.add(lbl_info_date);
        pan_info_btn.add(btn_info_exit); 
         
        //dlg_help_info.add(pan_info,"North");
        dlg_help_info.getContentPane().add(pan_info, "Center");
        //dlg_help_info.add(lbl_info_se,"Center"); 
        dlg_help_info.getContentPane().add(pan_info_btn, "South"); 
         
         
        //ȭ�鱸�� 
        int x = getX()+getWidth()/2-150; 
        int y = getY()+getHeight()/2-75; 
                 
        dlg_help_info.setBounds(x,y, 300,150); //�θ��������� ��� �ٿ�� 
        dlg_help_info.setResizable(false);; 
        dlg_help_info.setVisible(true); 
        dlg_help_info.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE); 
         
        btn_info_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//�����մϴ�.
				//System.exit(0);				
				dlg_help_info.dispose();				
			}
		}); 
    } 

	
	
	/*
	 * [���ϸ� ���� �ϱ�]
	 * fileList�� �ִ� ���ϵ��� �����ϱ� �����մϴ�.
	 * 
	 */
	public void startFileNameChange(){
				
		try{
			if(fileList.length < 0) return;
		}catch(NullPointerException e){
			show_Dialog("���� ��� ����", "���������� ������ �ּ���!!");
		}
		
		
		
		Runnable task = new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				int i = 1;		
				
				// renameTo ���н� ���� ���縦 �ϱ� ���ؼ� �����Ѵ�.
				byte[] buf = new byte[1024];
				FileInputStream fin = null;
				FileOutputStream fout = null;
				
				//for(File tempFile : fileList) {
				for(File tempFile : fileList){
					
					if(tempFile.isFile()){
						//���� ������ ���� Path�� Name�� �ҷ�����  
					    String tempPath=tempFile.getParent();			    
					    String tempFileName=tempFile.getName();
						
					    label_start.setText(tempFileName + " -> ");
					    
					    //���ϸ��� ���ϴ� �ɼ����� �ڸ���
					    String [] fileName = tempFileName.split("_");					    
					    
					    int pos = tempFileName.lastIndexOf( "." );
					    String ext = tempFileName.substring( pos + 1 );					    
					    
					    //���ο� ���ϸ��� ����� �ش�
					    String tempFileDest = fileName[0].toString()+"."+ext;					   
					    
					    label_start.setText(tempFileName + " -> " + tempFileDest);
					    
					    //���ο� ���ϰ�ü �����Ѵ�.
					    File newFile = new File(tempPath, tempFileDest);
					    
					    //������ �������� �մϴ�. �ߺ� �����϶��� ���� �ʽ��ϴ�.
					    if(!newFile.exists()){
					    	
						    //renameTo ���� �� ������ �����Ѵ�.
						    if(!tempFile.renameTo(newFile)){					    
						    	try{
							    	buf = new byte[1024];
							        fin = new FileInputStream(newFile);
							        fout = new FileOutputStream(newFile);
							     
							        int read = 0;
							        while((read=fin.read(buf,0,buf.length))!=-1){
							            fout.write(buf, 0, read);
							        }
							         
							        fin.close();
							        fout.close();
							        tempFile.delete();
						    	}catch(Exception e){			    		
						    		e.printStackTrace();
						    	}
						    }
					    }else{
					    	tempFile.delete();
					    }
					}		
					
					progressBar.setValue(i); 
					label_complete.setText("�� " + fileList.length + "�� ��" + i +" �� �Ϸ�");
					 
					i++;
				}
			}			
		};
		
		new Thread(task).start();		
	}
	
}

