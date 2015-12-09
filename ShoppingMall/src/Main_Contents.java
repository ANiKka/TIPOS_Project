import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Main_Contents extends JPanel {

	
	/**
	 * Create the frame.
	 */
	public Main_Contents() {
		
		setBorder(new EmptyBorder(5, 5, 5, 5));				
		setLayout(null);
		setLayout(new BorderLayout());		
		
		openUrl("jeilmart.kr");
	}

	
	public static void openUrl(String url){
		
		String os = System.getProperty("os.name");
		Runtime runtime = Runtime.getRuntime();
			try {            
				// Block for Windows Platform        
				System.out.println(os.toString());
				if (os.startsWith("Windows")) {                
					String cmd = "rundll32 url.dll,FileProtocolHandler " + url;                
					Process p = runtime.exec(cmd);					
					System.out.println("실행완료");
				}
			} catch (Exception x) {
					System.err.println("Exception occurd while invoking Browser!");            
					x.printStackTrace();       
			}
	}
	
}
