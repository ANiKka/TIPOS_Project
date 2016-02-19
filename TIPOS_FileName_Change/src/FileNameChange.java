
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
		
		// 현재화면의 사이즈와 모니터 사이즈를 불러오기 위해 선언
		Dimension dim = new Dimension();
		Dimension dim1 = new Dimension();
		int xpos, ypos;
			
		try {
		    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) { 
		    System.err.println("Cannot set look and feel:" + e.getMessage ()); 
		}			
		
		
		//파일 다이얼로그 객체 생성		
		jfiledialog = new JFileChooser();
		jfiledialog.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		
		setTitle("\uD30C\uC77C\uBA85 \uBCC0\uACBD");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 410, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		
		// 현재 모니터화면 사이즈 가져오기
		dim = Toolkit.getDefaultToolkit().getScreenSize();
						
		// 현재 프레임 사이즈 가져오기
		dim1 = getSize();
		
		// 화면을 중앙에 위치 시킴
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
	 * [파일 갯수 불러오기]
	 * 파일 갯수를 불러와서 파일명레이블에 표시해줍니다.
	 * 
	 * */
	public void filegetCount(File file){
				
		if(!file.exists()){
			return;
		}
		
		File dirFile = new File(file.getPath());
		fileList = dirFile.listFiles();
		
		label_start.setText("변경하기를 눌러 주세요!!");
		label_complete.setText(" 파일 갯수 : " + fileList.length + "개 " );
		
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
	 * 기본 다이얼 로그 띄우기
	 * 
	 * 
	 * */	
	public void show_Dialog(String title, String content){ 
		
        dlg_help_info= new JDialog(this, title);                 
        JButton btn_info_exit = new JButton("확인"); 
         
        JLabel lbl_info_date = new JLabel(content, JLabel.CENTER);
        JPanel pan_info = new JPanel(); 
        JPanel pan_info_btn = new JPanel(); 
                 
        pan_info.add(lbl_info_date);
        pan_info_btn.add(btn_info_exit); 
         
        //dlg_help_info.add(pan_info,"North");
        dlg_help_info.getContentPane().add(pan_info, "Center");
        //dlg_help_info.add(lbl_info_se,"Center"); 
        dlg_help_info.getContentPane().add(pan_info_btn, "South"); 
         
         
        //화면구성 
        int x = getX()+getWidth()/2-150; 
        int y = getY()+getHeight()/2-75; 
                 
        dlg_help_info.setBounds(x,y, 300,150); //부모프레임의 가운데 뛰우기 
        dlg_help_info.setResizable(false);; 
        dlg_help_info.setVisible(true); 
        dlg_help_info.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE); 
         
        btn_info_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//종료합니다.
				//System.exit(0);				
				dlg_help_info.dispose();				
			}
		}); 
    } 

	
	
	/*
	 * [파일명 변경 하기]
	 * fileList에 있는 파일들을 변경하기 시작합니다.
	 * 
	 */
	public void startFileNameChange(){
				
		try{
			if(fileList.length < 0) return;
		}catch(NullPointerException e){
			show_Dialog("파일 목록 없슴", "파일폴더를 선택해 주세요!!");
		}
		
		
		
		Runnable task = new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				int i = 1;		
				
				// renameTo 실패시 파일 복사를 하기 위해서 선언한다.
				byte[] buf = new byte[1024];
				FileInputStream fin = null;
				FileOutputStream fout = null;
				
				//for(File tempFile : fileList) {
				for(File tempFile : fileList){
					
					if(tempFile.isFile()){
						//원본 파일의 파일 Path와 Name를 불러오기  
					    String tempPath=tempFile.getParent();			    
					    String tempFileName=tempFile.getName();
						
					    label_start.setText(tempFileName + " -> ");
					    
					    //파일명을 원하는 옵션으로 자르기
					    String [] fileName = tempFileName.split("_");					    
					    
					    int pos = tempFileName.lastIndexOf( "." );
					    String ext = tempFileName.substring( pos + 1 );					    
					    
					    //새로운 파일명을 만들어 준다
					    String tempFileDest = fileName[0].toString()+"."+ext;					   
					    
					    label_start.setText(tempFileName + " -> " + tempFileDest);
					    
					    //새로운 파일객체 생성한다.
					    File newFile = new File(tempPath, tempFileDest);
					    
					    //파일이 없을때만 합니다. 중복 파일일때는 하지 않습니다.
					    if(!newFile.exists()){
					    	
						    //renameTo 실패 시 파일을 복사한다.
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
					label_complete.setText("총 " + fileList.length + "개 중" + i +" 개 완료");
					 
					i++;
				}
			}			
		};
		
		new Thread(task).start();		
	}
	
}

