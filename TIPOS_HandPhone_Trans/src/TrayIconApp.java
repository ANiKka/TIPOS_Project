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
   /*  // SystemTray클래스를 얻어옵니다.
     private SystemTray m_tray = SystemTray.getSystemTray();
     
    //아이콘 입니다. 
    private TrayIcon m_ti;
    
    //메인프로그램 객체 받기
    private MainProgram mp;
    
    //트레이 아이콘 타이틀이구요     
    String m_strTrayTitle;
    
    public TrayIconApp(String strTrayTitle, MainProgram mp)
    {
     m_strTrayTitle = strTrayTitle;     
     this.mp = mp;
     initTray();
    }
    
    // 트레이 아이콘의 초기설정을 해줍니다.
    private void initTray()  {
     
    	try {
		    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) { 
		    System.err.println("Cannot set look and feel:" + e.getMessage()); 
		}
    	
    	// 트레이 아이콘의 아이콘 역할을 할 이미지 입니다. 
    //getClass().getClassLoader().getResource("tiposH_logo.png")
     Image image = Toolkit.getDefaultToolkit().getImage("tiposH_logo.png");
     
     // TrayIcon을 생성합니다.
     m_ti = new TrayIcon(image, m_strTrayTitle, createPopupMenu());
     
     m_ti.setImageAutoSize(true);
    // m_ti.addActionListener(new ActionListener() {
     //       public void actionPerformed(ActionEvent e) {
            	            	
      //      	mp.frame.setVisible(true);
                // 트레이 아이콘 자체를 클릭했을때 일어날 이벤트에 대한 동작을 구현합니다. 
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
     
        
     // 위에서 얻어온 SystemTray에 방금 막 생성한 TrayIcon의 인스턴스를 인자로 넣어줍니다.
     try{
          m_tray.add(m_ti);
	  	} catch(AWTException e1){	
	  		e1.printStackTrace();
	  	}
     
    //송수신 시작 
 	mp.rsServer();
     
    }
    
    // 트레이 아이콘에서 사용할 팝업 매뉴를 만듭니다.
    private PopupMenu createPopupMenu()
    {
        PopupMenu popupMenu = new PopupMenu();
        
        MenuItem miShow = new MenuItem("열기");
        MenuItem miStart = new MenuItem("시작");
        MenuItem miStop = new MenuItem("중지");
        MenuItem miOption = new MenuItem("환경설정");
        MenuItem miQuit = new MenuItem("종료");
        
        //각각에 항목에 대해 리스너 장착. 
        miShow.addActionListener(this);
        miStart.addActionListener(this);
        miStop.addActionListener(this);
        miOption.addActionListener(this);        
        miQuit.addActionListener(this);
        
        //팝업 메뉴에 등록 
        popupMenu.add(miShow);
        popupMenu.add(miStart);
        popupMenu.add(miStop);
        popupMenu.add(miOption);
        
        // 줄 생성
        popupMenu.addSeparator();
        popupMenu.add(miQuit);
        
        return popupMenu;
    }
    
    public void actionPerformed(ActionEvent e)
    {

     if(e.getActionCommand() == "열기")
     {
        //각각의 항목에 대해서 일어날 행동에 대해 정의
    	 mp.frame.setVisible(true);
     }
     else if(e.getActionCommand() == "시작"){
         mp.rsServer();       
     }else if(e.getActionCommand() == "중지"){    	 
			
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
     }else if(e.getActionCommand() == "환경설정"){
    	 ConfigFilePath cf = new ConfigFilePath();
		 cf.setVisible(true);
     }else if(e.getActionCommand() == "종료"){
    	 System.exit(0);
     }     
    }*/
}