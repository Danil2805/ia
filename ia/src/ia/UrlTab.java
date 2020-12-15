package ia;

import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class UrlTab extends JPanel{
	UrlTab() {
		setLayout(null);
		setBackground(new Color(76, 146, 212));
		

		String origin = "Syta+120";
		String destination = "Syta+99";
		
		JButton routeBtn = new JButton("Find route");
		routeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Desktop desktop = java.awt.Desktop.getDesktop();
				try {
					//specify the protocol along with the URL
					URI oURL = new URI(
							"https://www.google.com/maps/dir/?api=1&origin=" + origin +"&destination=" + destination);
					desktop.browse(oURL);
				} catch (URISyntaxException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		routeBtn.setBounds(10, 10, 100, 25);
		add(routeBtn);
	}
}


