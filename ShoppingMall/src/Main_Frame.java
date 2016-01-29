
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JMenuBar;
import javax.swing.JLabel;
import javax.swing.UIManager;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.im.InputContext;

import javax.swing.JButton;

import javax.swing.JTabbedPane;
import net.miginfocom.swing.MigLayout;
import javax.swing.JMenu;
import javax.swing.JMenuItem;


public class Main_Frame extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 22513545765121L;
	
	private final String Shop_Manager = "���θ�����"; 
	private final String Pro_Manager = "��ǰ����";
	private final String Img_Manager = "�̹�������";
	private final String Ord_Manager = "�ֹ�����";
	private final String Mem_Manager = "ȸ������";
	private final String Msg_Manager = "�޼�������";
	private final String Evt_Manager = "�����̺�Ʈ����";
	private final String Con_Manager = "ȯ�漳��";
	private final String Close_Manager = "����";
	
	private  Goods_Manage goods_manage;
	//private Image_Upload image_upload;
	private Main_Config config;
	private Member_Manage member_manage;
	private Event_Manage event_manage;
	private Order_Manage order_manage;
	
	JTabbedPane tabbedPane;
	private final JPanel panel_topmenu = new JPanel();
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
		
		InputContext ctx = getInputContext();
		Character.Subset[] subset = {Character.UnicodeBlock.HANGUL_SYLLABLES};
		ctx.setCharacterSubsets(subset);
		config = new Main_Config();
		
		//������
		initLocation();		
		
		//�޴���				
		//menu_frame();
				
		//�ϴ� ��ư
		//bottom_button();
		
		//setFocusable(false);
		System.out.println("���μ���");
		System.out.println(Server_Config.getSERVER_IP());		
				
	}		
	
	// ���� & ȭ���߾�
    private void initLocation() {    	
    	
    	try{    		
	    	if(Server_Config.getOFFICENAME().equals("")){    	
	    		setTitle("\uC1FC\uD551\uBAB0 \uC0C1\uD488\uC5F0\uB3D9 "+ManageVersion.version);
	    	}else{
	    		setTitle("\uC1FC\uD551\uBAB0 \uC0C1\uD488\uC5F0\uB3D9 "+ManageVersion.version+" [ "+Server_Config.getOFFICECODE() +" "+Server_Config.getOFFICENAME()+" ]" );
	    	}
    	}catch(NullPointerException e){
    		//���α׷� ���� ���� �� �������ϴ�.
    		setTitle("\uC1FC\uD551\uBAB0 \uC0C1\uD488\uC5F0\uB3D9"+" *********************** ȯ�漳���� ������ �ּ���!!! *****************************");
    		JOptionPane.showMessageDialog(this, "ȯ�漳���� ������ �ּ���~!! ����� ���� �۵� �ȵ� �� �ֽ��ϴ�.");
    		startConfigSet();
    	}    	
    	
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
        
		//ȭ�� �߾ӿ� ��ġ 
       Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension dm = tk.getScreenSize();
        Dimension fm = this.getSize();
        this.setLocation((int) (dm.getWidth() / 2 - fm.getWidth() / 2), (int) (dm.getHeight() / 2 - fm.getHeight() / 2));
               
        //ȭ�� ��ü ������� �����ϱ�
        setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        
        getContentPane().setLayout(new BorderLayout());
		
		JDesktopPane jdp = new JDesktopPane();
		jdp.setLayout(new BorderLayout());
		jdp.setBackground(Color.white);
		
		getContentPane().add(jdp, BorderLayout.CENTER);		
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(Color.GRAY);
		
		tabbedPane.setBounds(0, 0, 1008, 681);
				
		Main_Contents mc = new Main_Contents();
		tabbedPane.add(Shop_Manager, mc);
		//close_button(Shop_Manager);
		
		jdp.add(tabbedPane);
		panel_topmenu.setBackground(Color.WHITE);
		getContentPane().add(panel_topmenu, BorderLayout.NORTH);
    	panel_topmenu.setLayout(new MigLayout("", "[123px][123px][123px][137px][167px][123px][93px]", "[29px]"));
		
    	JButton goods_ad = new JButton(Pro_Manager);
    	panel_topmenu.add(goods_ad, "cell 0 0,alignx left,aligny top");
    	goods_ad.setFont(new Font("���� ���", Font.BOLD, 15));    	
    	goods_ad.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Icon/btn_goods.png")));
    	goods_ad.setIconTextGap(10);
    	
    	goods_ad.setFocusable(false);
    	
    	JButton btn_order = new JButton(Ord_Manager);
    	panel_topmenu.add(btn_order, "cell 1 0,alignx left,aligny top");
    	btn_order.setIcon(new ImageIcon(Main_Frame.class.getResource("/Icon/btn_order.png")));
    	btn_order.setIconTextGap(10);
    	btn_order.setFont(new Font("���� ���", Font.BOLD, 15));
    	btn_order.setFocusable(false);
    	
    	JButton btn_member = new JButton(Mem_Manager);
    	panel_topmenu.add(btn_member, "cell 2 0,alignx left,aligny top");
    	btn_member.setIcon(new ImageIcon(Main_Frame.class.getResource("/Icon/btn_member.png")));
    	btn_member.setIconTextGap(10);
    	btn_member.setFont(new Font("���� ���", Font.BOLD, 15));
    	btn_member.setFocusable(false);
    	
    	JButton btn_message = new JButton(Msg_Manager);
    	panel_topmenu.add(btn_message, "cell 3 0,alignx left,aligny top");
    	btn_message.setIcon(new ImageIcon(Main_Frame.class.getResource("/Icon/btn_message.png")));
    	btn_message.setIconTextGap(10);
    	btn_message.setFont(new Font("���� ���", Font.BOLD, 15));
    	btn_message.setFocusable(false);
    	
    	JButton btn_cupon = new JButton(Evt_Manager);
    	panel_topmenu.add(btn_cupon, "cell 4 0,alignx left,aligny top");
    	btn_cupon.setIcon(new ImageIcon(Main_Frame.class.getResource("/Icon/btn_cupon.png")));
    	btn_cupon.setIconTextGap(10);
    	btn_cupon.setFont(new Font("���� ���", Font.BOLD, 15));
    	btn_cupon.setFocusable(false);
    	
    	JButton config_setting = new JButton("ȯ�漳��");
    	panel_topmenu.add(config_setting, "cell 5 0,alignx left,aligny top");
    	config_setting.setFont(new Font("���� ���", Font.BOLD, 15));
    	config_setting.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Icon/btn_config.png")));
    	config_setting.setIconTextGap(10);
    	config_setting.setFocusable(false);
    	
    	JButton main_close = new JButton("����");
    	panel_topmenu.add(main_close, "cell 6 0,alignx left,aligny top");
    	main_close.setFont(new Font("���� ���", Font.BOLD, 15));
    	main_close.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Icon/btn_main_cancel.png")));
    	main_close.setIconTextGap(10);
    	main_close.setFocusable(false);
    	main_close.addActionListener(this);
    	config_setting.addActionListener(this);
    	btn_cupon.addActionListener(this);
    	btn_message.addActionListener(this);
    	//btn_message.setVisible(false);
    	btn_member.addActionListener(this);
    	//btn_member.setVisible(false);
    	btn_order.addActionListener(this);
    	
    	goods_ad.addActionListener(this);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setFont(new Font("���� ���", Font.BOLD, 15));
		menuBar.setMargin(new Insets(0, 0, 10, 0));
		setJMenuBar(menuBar);
		
		JMenu mn_main_file = new JMenu("\uD30C\uC77C");
		mn_main_file.setFont(new Font("���� ���", Font.PLAIN, 12));
		menuBar.add(mn_main_file);
		
		JMenuItem mn_file_close = new JMenuItem("\uC885\uB8CC");
		mn_file_close.addActionListener(this);
		mn_main_file.add(mn_file_close);
		
		JMenu mn_main_goods = new JMenu("\uC0C1\uD488\uAD00\uB9AC");
		mn_main_goods.setFont(new Font("���� ���", Font.PLAIN, 12));
		menuBar.add(mn_main_goods);
		
		JMenuItem mn_goods_goods = new JMenuItem("\uC0C1\uD488\uAD00\uB9AC");
		mn_main_goods.add(mn_goods_goods);
		mn_goods_goods.addActionListener(this);
		
		JMenu mn_main_order = new JMenu("\uC8FC\uBB38\uAD00\uB9AC");
		mn_main_order.setFont(new Font("���� ���", Font.PLAIN, 12));
		menuBar.add(mn_main_order);
				
		JMenuItem mn_order_order = new JMenuItem("\uC8FC\uBB38\uAD00\uB9AC");
		mn_main_order.add(mn_order_order);
		mn_order_order.addActionListener(this);
		
		JMenu mn_main_member = new JMenu("\uD68C\uC6D0\uAD00\uB9AC");
		mn_main_member.setFont(new Font("���� ���", Font.PLAIN, 12));
		menuBar.add(mn_main_member);
		
		JMenuItem mn_member_member = new JMenuItem("\uD68C\uC6D0\uAD00\uB9AC");
		mn_main_member.add(mn_member_member);
		mn_member_member.addActionListener(this);
		
		JMenu mn_main_event = new JMenu("\uC774\uBCA4\uD2B8\uAD00\uB9AC");
		mn_main_event.setFont(new Font("���� ���", Font.PLAIN, 12));
		menuBar.add(mn_main_event);
		
		JMenuItem mn_event_event = new JMenuItem("\uCFE0\uD3F0\uC774\uBCA4\uD2B8\uAD00\uB9AC");
		mn_main_event.add(mn_event_event);
		mn_event_event.addActionListener(this);
		
		JMenu mn_main_config = new JMenu("\uC124\uC815\uAD00\uB9AC");
		mn_main_config.setFont(new Font("���� ���", Font.PLAIN, 12));
		menuBar.add(mn_main_config);
		
		JMenuItem mn_config_config = new JMenuItem("\uD658\uACBD\uC124\uC815");
		mn_main_config.add(mn_config_config);
		mn_config_config.addActionListener(this);
		
		/**JButton image_upload_1 = new JButton(Img_Manager);
		menuBar.add(image_upload_1);
		image_upload_1.setFont(new Font("���� ���", Font.BOLD, 15));    	
		image_upload_1.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Icon/btn_image_upload.png")));
		image_upload_1.setIconTextGap(10);
		image_upload_1.setFocusable(false);
		image_upload_1.addActionListener(this);*/
		
    }	
    
    
    //������ ��ư
    private void close_button(String title){
    	
    	int index = tabbedPane.indexOfTab(title);
    	
    	JPanel pnlTab = new JPanel(new GridBagLayout());
    	pnlTab.setOpaque(false);
    	
    	JLabel lblTitle = new JLabel(title);
    	JButton btnClose = new JButton();    	
    	btnClose.setActionCommand(title);
    	btnClose.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Icon/btn_tab_close.png")));
    	btnClose.setMargin(new Insets(2, 2, 2, 2));
    	
    	GridBagConstraints gbc = new GridBagConstraints();
    	gbc.gridx = 0;
    	gbc.gridy = 0;
    	
    	gbc.weightx = 1;
    	pnlTab.add(lblTitle, gbc);

    	gbc.gridx++;
    	//Top, Left, Bottom, Right
    	gbc.insets = new Insets(0, 10, 0, 5);
    	pnlTab.add(btnClose, gbc);
   	
    	tabbedPane.setTabComponentAt(index, pnlTab);
    	
    	btnClose.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String title = e.getActionCommand();
				// TODO Auto-generated method stub
				int index = tabbedPane.indexOfTab(title);
		        if (index >= 0) {
		        	tabbedPane.removeTabAt(index);          
		        }
		        
			}
		});				
    }
        
    //�ϴ� ��� ��ư
    private void startConfigSet(){
	    	JPanel panel = new JPanel();
			
			JLabel label = new JLabel("��й�ȣ : ");
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
			
			String[] options = new String[]{"Ȯ��", "���"};			
			int option = JOptionPane.showOptionDialog(this, panel, Con_Manager,
			                         JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
			                         null, options, pass);
			if(option == 0) // pressing OK button
			{
			    char[] password = pass.getPassword();
			    if(new String(password).equals("tips")){			    	
			    	
			    	config.setLocationRelativeTo(this);
			    	config.setTitle("ȯ�� ����");
			    	config.setModal(true);					
			    	config.setVisible(true);					
					config.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			    }else{
			    	JOptionPane.showMessageDialog(this, "��й�ȣ�� �߸� �Է� �ϼ̽��ϴ�.", "��й�ȣ ����ġ", DO_NOTHING_ON_CLOSE);
			    	return;
			    }
			}
    }
  
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		String action = e.getActionCommand();		
		
		System.out.println(action);
		if(tabbedPane.indexOfTab(action) >= 0){
			tabbedPane.setSelectedIndex(tabbedPane.indexOfTab(action));
			//�̹� ���� �Ǿ��ٸ� ��ġ�� �̵��մϴ�.
			return;
		}
		
		switch(action){
		case Pro_Manager:
			//��ǰ����
			goods_manage = new Goods_Manage();			
			tabbedPane.addTab(Pro_Manager, goods_manage);
			close_button(Pro_Manager);
			break;
		case Img_Manager:		
			//�̹��� ����
			/*image_upload = new Image_Upload();
			tabbedPane.addTab(Img_Manager, image_upload);
			close_button(Img_Manager);*/
			break;
		case Ord_Manager:
			//�ֹ�����
			order_manage = new Order_Manage();
			tabbedPane.addTab(Ord_Manager, order_manage);
			close_button(Ord_Manager);
			break;
			
		case Mem_Manager:
			//ȸ������
			member_manage = new Member_Manage();
			tabbedPane.addTab(Mem_Manager, member_manage);
			close_button(Mem_Manager);
			break;
		case Msg_Manager:
			//�޼�������
			break;
		case Evt_Manager:
			//�̺�Ʈ����			
			event_manage = new Event_Manage();			
			tabbedPane.addTab(Evt_Manager, event_manage);
			close_button(Evt_Manager);
			break;
		case Con_Manager:
			startConfigSet();
			break;
		case Close_Manager:
			if(JOptionPane.showConfirmDialog(this, "���α׷��� ���� �մϴ�.", "���α׷� ����", JOptionPane.YES_NO_OPTION) == 0){
				System.exit(0);
			}
			break;
		}	
		
		//��ġ�� �̵��մϴ�.
		if(tabbedPane.indexOfTab(action) >= 0){
			tabbedPane.setSelectedIndex(tabbedPane.indexOfTab(action));			
		}	
		
	}
}
