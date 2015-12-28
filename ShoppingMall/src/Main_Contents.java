import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;

public class Main_Contents extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 15648534567654L;

	/**
	 * Create the frame.
	 */
	public Main_Contents() {
		
		setBorder(new EmptyBorder(10, 10, 10, 10));				
		//setLayout(null);
		
		
		
		setLayout(new MigLayout("", "[grow]", "[grow]"));
		
		//JLabel label = new JLabel();
		//label.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Icon/intro.jpg")));		
		//add(label, "cell 0 0,alignx center,aligny center");
		
	}
	
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("Icon/intro.jpg"));
		
		Dimension d = getSize();
		g.drawImage(icon.getImage(), 0, 0, d.width, d.height, null);
	
		
	}

	
	
	
}
