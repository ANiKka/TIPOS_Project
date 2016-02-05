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
