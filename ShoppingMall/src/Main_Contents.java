
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class Main_Contents extends JPanel implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 15648534567654L;

	/**
	 * Create the frame.
	 */	
	private Process process;
	private ImageIcon imgcon;
	
	public Main_Contents() {
		
		setBorder(new EmptyBorder(10, 10, 10, 10));
		//setLayout(null);
		
		setLayout(new MigLayout("", "[grow][][][grow][grow][][][grow][][][grow][][][][]", "[grow][grow][grow][grow][][20px]"));		
		
		imgcon = new ImageIcon(getClass().getClassLoader().getResource("Icon/btn_posIcon.png"));				
		JButton btn_tips_start = new JButton();
		add(btn_tips_start, "cell 1 4");
		btn_tips_start.setIcon(imgcon);		
		btn_tips_start.setActionCommand("TIPS");
		btn_tips_start.addActionListener(this);
		
		imgcon = new ImageIcon(getClass().getClassLoader().getResource("Icon/btn_popIcon.png"));
		JButton btn_pop_start = new JButton(imgcon);
		add(btn_pop_start, "cell 2 4,alignx center");
		btn_pop_start.setActionCommand("POP");
		btn_pop_start.addActionListener(this);
		
		imgcon = new ImageIcon(getClass().getClassLoader().getResource("Icon/btn_homepageIcon.png"));
		JButton btn_shop_start = new JButton(imgcon);
		add(btn_shop_start, "cell 3 4");
		btn_shop_start.setActionCommand("SHOP");
		btn_shop_start.addActionListener(this);
	
	}	
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("Icon/intro+icon_empty.jpg"));
		
		Dimension d = getSize();
		g.drawImage(icon.getImage(), 0, 0, d.width, d.height, null);
	}

	public void setStartProgram(String exeFile){	
		
		System.out.println("exeFile: " + exeFile);		
		try {
			process = new ProcessBuilder(exeFile).start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(this, e.getMessage());
		}            		
	}
	
	public void setStartProgram(String[] exeFile){	
		
		System.out.println("exeFile: " + exeFile);		
		try {
			process = new ProcessBuilder(exeFile).start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(this, e.getMessage());
		}            		
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String action = e.getActionCommand();
		
		switch(action){
		
		case "TIPS":
			String exeFile = "C:\\Program Files\\TIPS\\PosManager.exe";			
			setStartProgram(exeFile);
			break;
		case "POP":
			exeFile = "C:\\Program Files\\TIPS\\EasyPop\\easypop.exe";			
			setStartProgram(exeFile);
			break;
		case "SHOP":			
			
			String addr="";
			/*try {
				addr = "http://www.tips1311.tipos.or.kr/admin/sub_login/login_ok.php?id="+URLEncoder.encode("tips1311", "UTF-8")+"&bu_id="+URLEncoder.encode("root", "UTF-8")+"&pwd="+URLEncoder.encode("kis5749948", "UTF-8")
				 +"&referer=&login_type=&mode=&login_lan=&login_admin_mode=&first_url=&smart_design_yn=&smart_design_mode&this_domain=&post_action=";
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}*/
			
			 System.out.println(Server_Config.getOFFICESHOP());
			addr = Server_Config.getOFFICESHOP()+"/admin/sub_login/login_form.htm";
			String[] cmd = new String[] {"rundll32", "url.dll", "FileProtocolHandler", addr};
			setStartProgram(cmd);
			break;
		
		}
	}	
	
}
