import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


public class test_Frame extends JFrame {

	private JPanel contentPane;

	
	 JDesktopPane xDesktop;        //JDesktopPane을 정의한다.

	 ToolListener xToolListener = new ToolListener();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					test_Frame frame = new test_Frame("예제");
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
	public test_Frame(String title) {
		super(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		init();
	}





	public void init(){

	        /* 내용 패널을 BorderLayot으로 정한다. */

	        Container cp = getContentPane();

	        cp.setLayout(new BorderLayout());



	        /* 툴바를 만든다 */

	        JPanel tool_bar = new JPanel(new BorderLayout());

	        JPanel tool_pnl = new JPanel(new GridLayout(1, 0));

	        JButton btn;

	        btn = new JButton("Maximize");

	        btn.addActionListener(xToolListener);        //액션 이벤트..
	        tool_pnl.add(btn);



	        btn = new JButton("Minimize");

	        btn.addActionListener(xToolListener);

	        tool_pnl.add(btn);



	        btn = new JButton("Iconify");

	        btn.addActionListener(xToolListener);

	        tool_pnl.add(btn);



	        btn = new JButton("Close");

	        btn.addActionListener(xToolListener);

	        tool_pnl.add(btn);


	        tool_bar.add(tool_pnl, BorderLayout.WEST);

	        cp.add(tool_bar, BorderLayout.NORTH);

	        

	        /* 데스크탑 패널을 만든다. */

	        

	        xDesktop = new JDesktopPane();

	        cp.add(xDesktop, BorderLayout.CENTER);
	        

	        /* 기본 설정의 내부 프레임을 만든다. */

	        JInternalFrame fr = new JInternalFrame("Internal Frame 1");

	        fr.getContentPane().add(

	            new JLabel(new ImageIcon("/root/Download/ICON/jeehaa1004_10.gif"))

	            , BorderLayout.CENTER

	        );

	        fr.setBounds(20, 20, 200, 100);

	        fr.setVisible(true);



	        xDesktop.add(fr); 


	        

	        /* 모든 옵션이 켜져있는 내부 프레임을 만든다. */

	        fr = new JInternalFrame("Internal Frame 2", true, true, true, true);

	        fr.getContentPane().add(

	            new JLabel(new ImageIcon("/root/Download/ICON/jeehaa1004_6.gif"))

	            , BorderLayout.CENTER

	        );

	        fr.setBounds(100, 100, 200, 100);

	        fr.setVisible(true);



	        xDesktop.add(fr);

	    }

	    

	    class ToolListener implements ActionListener {

	        public void actionPerformed(ActionEvent ev){

	            String cmd = ev.getActionCommand();

	            JInternalFrame fr = xDesktop.getSelectedFrame();     //선택된 프레임을 가져온다.           

	            /* 버튼에 따라 최대화, 최소화, 닫기 등의 작업을 한다. */

	            try{

	            if (cmd.equals("Maximize")) {

	                fr.setMaximum(true);            //최대화 복원

	            } else if (cmd.equals("Minimize")) {

	                fr.setMaximum(false);            //최대화 취소

	            } else if (cmd.equals("Iconify")) {

	                fr.setIcon(true);                //내부프레임의 아이콘화 유무

	            } else if (cmd.equals("Close")) {

	                fr.setClosed(true);                //내부프레임 닫기

	            }   

	            }catch(Exception e){}         

	        }

	    }

	
	
}
