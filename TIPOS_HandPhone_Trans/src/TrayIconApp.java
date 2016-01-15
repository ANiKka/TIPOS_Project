import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.UIManager;


public class TrayIconApp //implements ActionListener
{
   /*  // SystemTrayŬ������ ���ɴϴ�.
     private SystemTray m_tray = SystemTray.getSystemTray();
     
    //������ �Դϴ�. 
    private TrayIcon m_ti;
    
    //�������α׷� ��ü �ޱ�
    private MainProgram mp;
    
    //Ʈ���� ������ Ÿ��Ʋ�̱���     
    String m_strTrayTitle;
    
    public TrayIconApp(String strTrayTitle, MainProgram mp)
    {
     m_strTrayTitle = strTrayTitle;     
     this.mp = mp;
     initTray();
    }
    
    // Ʈ���� �������� �ʱ⼳���� ���ݴϴ�.
    private void initTray()  {
     
    	try {
		    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) { 
		    System.err.println("Cannot set look and feel:" + e.getMessage()); 
		}
    	
    	// Ʈ���� �������� ������ ������ �� �̹��� �Դϴ�. 
    //getClass().getClassLoader().getResource("tiposH_logo.png")
     Image image = Toolkit.getDefaultToolkit().getImage("tiposH_logo.png");
     
     // TrayIcon�� �����մϴ�.
     m_ti = new TrayIcon(image, m_strTrayTitle, createPopupMenu());
     
     m_ti.setImageAutoSize(true);
    // m_ti.addActionListener(new ActionListener() {
     //       public void actionPerformed(ActionEvent e) {
            	            	
      //      	mp.frame.setVisible(true);
                // Ʈ���� ������ ��ü�� Ŭ�������� �Ͼ �̺�Ʈ�� ���� ������ �����մϴ�. 
      //      }
     //});
     m_ti.addMouseListener(new java.awt.event.MouseAdapter()
     {
         public void mousePressed(java.awt.event.MouseEvent evt)
         {
        	 if(evt.getButton() == MouseEvent.BUTTON1){
        		 mp.frame.setVisible(true);
        	 }
         }
     });
     
        
     // ������ ���� SystemTray�� ��� �� ������ TrayIcon�� �ν��Ͻ��� ���ڷ� �־��ݴϴ�.
     try{
          m_tray.add(m_ti);
	  	} catch(AWTException e1){	
	  		e1.printStackTrace();
	  	}
     
    //�ۼ��� ���� 
 	mp.rsServer();
     
    }
    
    // Ʈ���� �����ܿ��� ����� �˾� �Ŵ��� ����ϴ�.
    private PopupMenu createPopupMenu()
    {
        PopupMenu popupMenu = new PopupMenu();
        
        MenuItem miShow = new MenuItem("����");
        MenuItem miStart = new MenuItem("����");
        MenuItem miStop = new MenuItem("����");
        MenuItem miOption = new MenuItem("ȯ�漳��");
        MenuItem miQuit = new MenuItem("����");
        
        //������ �׸� ���� ������ ����. 
        miShow.addActionListener(this);
        miStart.addActionListener(this);
        miStop.addActionListener(this);
        miOption.addActionListener(this);        
        miQuit.addActionListener(this);
        
        //�˾� �޴��� ��� 
        popupMenu.add(miShow);
        popupMenu.add(miStart);
        popupMenu.add(miStop);
        popupMenu.add(miOption);
        
        // �� ����
        popupMenu.addSeparator();
        popupMenu.add(miQuit);
        
        return popupMenu;
    }
    
    public void actionPerformed(ActionEvent e)
    {

     if(e.getActionCommand() == "����")
     {
        //������ �׸� ���ؼ� �Ͼ �ൿ�� ���� ����
    	 mp.frame.setVisible(true);
     }
     else if(e.getActionCommand() == "����"){
         mp.rsServer();       
     }else if(e.getActionCommand() == "����"){    	 
			
			if(mp.th.isAlive()){
				mp.th.interrupt();				
			}
					
			if(mp.th1.isAlive()){
				mp.th1.interrupt();				
			}
			
			try {
				mp.receivSocket.close();
				mp.sendSocket.close();
				mp.socket.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}			
     }else if(e.getActionCommand() == "ȯ�漳��"){
    	 ConfigFilePath cf = new ConfigFilePath();
		 cf.setVisible(true);
     }else if(e.getActionCommand() == "����"){
    	 System.exit(0);
     }     
    }*/
}