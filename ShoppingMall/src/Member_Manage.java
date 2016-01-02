import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Member_Manage extends JPanel {

	/**
	 * @param 회원관리 프로그램 
	 */
	private static final long serialVersionUID = 584467216L;
	Ms_Connect ms_connect;
	Trans_ShopAPI t_api;
	
	//회원관리프로그램
	public Member_Manage(){
		setLayout(null);
		ms_connect = new Ms_Connect();
		t_api = new Trans_ShopAPI();
		
		
		JButton btnNewButton = new JButton("\uC8FC\uBB38\uC870\uD68C");
		btnNewButton.setBounds(27, 71, 97, 23);
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("\uD68C\uC6D0\uC870\uD68C");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//휴대폰번호, 회원 id
				t_api.getMemberManage("", "");
			}
		});
		btnNewButton_1.setBounds(136, 71, 97, 23);
		add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("\uC124\uCE58\uD604\uD669");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				t_api.getDeviceList();
			}
		});
		btnNewButton_2.setBounds(245, 71, 97, 23);
		add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("\uD478\uC2DC\uC804\uC1A1");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				t_api.setPushSubimt();
			}
		});
		btnNewButton_3.setBounds(27, 104, 97, 23);
		add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("New button");
		btnNewButton_4.setBounds(136, 104, 97, 23);
		add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("New button");
		btnNewButton_5.setBounds(245, 104, 97, 23);
		add(btnNewButton_5);
		
		
		//시작
		init();
		
		//본문
		start();
		
	}
	
	
	//시작점
	private void init(){
		
		setBorder(new EmptyBorder(5, 5, 5, 5));
				
		
	
	}
	
	
	//본문
	private void start(){
		
		
		
		
	}
}
