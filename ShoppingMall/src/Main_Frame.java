
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.LineBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.JToggleButton;

import java.awt.Insets;
import java.beans.PropertyVetoException;
import javax.swing.JTabbedPane;


public class Main_Frame extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	
	private  Goods_Manage goods_manage;
	private Image_Upload image_upload;
	private Main_Config config;
	
	
	JTabbedPane tabbedPane;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main_Frame frame = new Main_Frame();
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
	public Main_Frame() {
		
		//진입점
		initLocation();		
		
		//메뉴바				
		menu_frame();
				
		//하단 버튼
		bottom_button();
		
		//setFocusable(false);
		System.out.println("메인서버");
		System.out.println(Server_Config.getSERVER_IP());
		
		config = new Main_Config();
	}		
	
	// 종료 & 화면중앙
    private void initLocation() {    	
    	
    	setTitle("\uC1FC\uD551\uBAB0 \uC0C1\uD488\uC5F0\uB3D9");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1024, 768);
    	
		//setResizable(false);
		
		ImageIcon im = new ImageIcon(getClass().getClassLoader().getResource("Icon/main_icon.png"));		
				
        setIconImage(im.getImage());
		
		try {
		    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) { 
		    System.err.println("Cannot set look and feel:" + e.getMessage()); 
		}
        
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension dm = tk.getScreenSize();
        Dimension fm = this.getSize();
        this.setLocation((int) (dm.getWidth() / 2 - fm.getWidth() / 2),
                (int) (dm.getHeight() / 2 - fm.getHeight() / 2));
       
        getContentPane().setLayout(new BorderLayout());
		
		JDesktopPane jdp = new JDesktopPane();
		jdp.setLayout(new BorderLayout());
		jdp.setBackground(Color.white);
		
		getContentPane().add(jdp, BorderLayout.CENTER);		
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(Color.GRAY);
		tabbedPane.setBounds(0, 0, 1008, 681);
				
		Main_Contents mc = new Main_Contents();
		tabbedPane.add("쇼핑몰관리", mc);
		
		jdp.add(tabbedPane);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		menuBar.setMargin(new Insets(0, 0, 10, 0));
		setJMenuBar(menuBar);
		
    	JButton goods_ad = new JButton("상품관리");
    	menuBar.add(goods_ad);
    	goods_ad.setFont(new Font("맑은 고딕", Font.BOLD, 15));    	
    	goods_ad.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Icon/btn_goods.png")));
    	goods_ad.setIconTextGap(10);
    	
    	goods_ad.setFocusable(false);
    	
    	goods_ad.addActionListener(this);
		
		JButton image_upload_1 = new JButton("이미지관리");
		menuBar.add(image_upload_1);
		image_upload_1.setFont(new Font("맑은 고딕", Font.BOLD, 15));    	
		image_upload_1.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Icon/btn_image_upload.png")));
		image_upload_1.setIconTextGap(10);
		image_upload_1.setFocusable(false);
		image_upload_1.addActionListener(this);
		
		JButton btn_order = new JButton("\uC8FC\uBB38\uAD00\uB9AC");
		btn_order.setIcon(new ImageIcon(Main_Frame.class.getResource("/Icon/btn_order.png")));
		btn_order.setIconTextGap(10);
		btn_order.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		btn_order.setFocusable(false);
		menuBar.add(btn_order);
		
		JButton btn_member = new JButton("\uD68C\uC6D0\uAD00\uB9AC");
		btn_member.setIcon(new ImageIcon(Main_Frame.class.getResource("/Icon/btn_member.png")));
		btn_member.setIconTextGap(10);
		btn_member.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		btn_member.setFocusable(false);
		menuBar.add(btn_member);
		
		JButton btn_message = new JButton("\uBA54\uC138\uC9C0\uAD00\uB9AC");
		btn_message.setIcon(new ImageIcon(Main_Frame.class.getResource("/Icon/btn_message.png")));
		btn_message.setIconTextGap(10);
		btn_message.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		btn_message.setFocusable(false);
		menuBar.add(btn_message);
		
		JButton btn_cupon = new JButton("\uCFE0\uD3F0\uAD00\uB9AC");
		btn_cupon.setIcon(new ImageIcon(Main_Frame.class.getResource("/Icon/btn_cupon.png")));
		btn_cupon.setIconTextGap(10);
		btn_cupon.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		btn_cupon.setFocusable(false);
		menuBar.add(btn_cupon);
		
		JButton config_setting = new JButton("환경설정");
		menuBar.add(config_setting);
		config_setting.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		config_setting.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Icon/btn_config.png")));
		config_setting.setIconTextGap(10);
		config_setting.setFocusable(false);
		config_setting.addActionListener(this);
		
    	JButton main_close = new JButton("종료");
    	menuBar.add(main_close);
    	main_close.setFont(new Font("맑은 고딕", Font.BOLD, 15));
    	main_close.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Icon/btn_main_cancel.png")));
    	main_close.setIconTextGap(10);
    	main_close.setFocusable(false);
    	main_close.addActionListener(this);
		
    }	
    
    
    //상단메뉴    
    private void menu_frame(){
    	    				
    }
        
    //하단 기능 버튼
    private void bottom_button(){
	
		
    }
  
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		String action = e.getActionCommand();		
		
		System.out.println(action);
		if(tabbedPane.indexOfTab(action) > 0){
			tabbedPane.setSelectedIndex(tabbedPane.indexOfTab(action));
			//이미 실행 되었다면 위치로 이동합니다.
			return;
		}
		
		switch(action){
		case "상품관리":
			//상품관리
			goods_manage = new Goods_Manage();			
			tabbedPane.add("상품관리", goods_manage);
			break;
		case "이미지관리":			
			//이미지 관리
			image_upload = new Image_Upload();
			tabbedPane.add("이미지관리", image_upload);
			break;
		case "주문관리":
			//주문관리
			
			
			break;
			
		case "회원관리":
			//회원관리
			
			break;
		case "메세지관리":
			//메세지관리
			break;
		case "이벤트관리":
			//이벤트관리
			break;
		case "환경설정":
			JPanel panel = new JPanel();
			JLabel label = new JLabel("비밀번호 : ");
			JPasswordField pass = new JPasswordField(10);
			pass.addKeyListener(new KeyListener() {
				
				@Override
				public void keyTyped(KeyEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void keyReleased(KeyEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void keyPressed(KeyEvent e) {
					// TODO Auto-generated method stub
					if(e.getKeyCode() == KeyEvent.VK_ENTER){
						pass.transferFocus();						
					}
				}
			});
			panel.add(label);
			panel.add(pass);
			String[] options = new String[]{"확인", "취소"};
			int option = JOptionPane.showOptionDialog(null, panel, "환경설정",
			                         JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
			                         null, options, pass);
			if(option == 0) // pressing OK button
			{
			    char[] password = pass.getPassword();
			    if(new String(password).equals("tips")){			    	
			    	
			    	config.setLocationRelativeTo(this);
			    	config.setModal(true);					
			    	config.setVisible(true);					
					config.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			    }else{
			    	JOptionPane.showMessageDialog(this, "비밀번호를 잘못 입력 하셨습니다.", "비밀번호 불일치", DO_NOTHING_ON_CLOSE);
			    	return;
			    }
			}	
			break;
		case "종료":
			if(JOptionPane.showConfirmDialog(this, "프로그램을 종료 합니다.", "프로그램 종료", JOptionPane.YES_NO_OPTION) == 0){
				System.exit(0);
			}
			break;
		}	
		
		//위치로 이동합니다.
		if(tabbedPane.indexOfTab(action) > 0){
			tabbedPane.setSelectedIndex(tabbedPane.indexOfTab(action));			
		}	
		
	}
}
