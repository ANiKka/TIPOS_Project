import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Member_Manage extends JPanel {

	/**
	 * @param ȸ������ ���α׷� 
	 */
	private static final long serialVersionUID = 584467216L;
	Ms_Connect ms_connect;
	Trans_ShopAPI t_api;
	
	//ȸ���������α׷�
	public Member_Manage(){
		setLayout(null);
		ms_connect = new Ms_Connect();
		t_api = new Trans_ShopAPI();
		
		
		//����
		init();
		
		//����
		start();
		
	}
	
	
	//������
	private void init(){
		
		setBorder(new EmptyBorder(5, 5, 5, 5));
				
		
	
	}
	
	
	//����
	private void start(){
		
		
		
		
	}
}
