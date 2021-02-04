package ia;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class UrlTab extends JPanel{
	static JTextArea location;

	UrlTab(JTabbedPane tabbedPane) {
		setLayout(null);
		setBackground(new Color(76, 146, 212));
		
		JLabel originlbl = new JLabel("Origin:");
		originlbl.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 16));
		originlbl.setForeground(Color.white);
		originlbl.setBounds(500, 40, 160, 20);
		add(originlbl);
		
		JTextArea originInput = new JTextArea();
		originInput.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 16));
		originInput.setForeground(Color.GRAY);
		originInput.setBounds(620, 40, 160, 20);
		add(originInput);
		
		JLabel destinationlbl = new JLabel("Destination:");
		destinationlbl.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 16));
		destinationlbl.setForeground(Color.white);
		destinationlbl.setBounds(500, 70, 160, 20);
		add(destinationlbl);
		
		location = new JTextArea();
		location.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 16));
		location.setForeground(Color.GRAY);
		location.setBounds(620, 70, 160, 20);
		add(location);
		
		//https://developers.google.com/maps/documentation/urls/get-started
		//https://alvinalexander.com/blog/post/java/how-open-read-url-java-url-class-example-code/
		JButton routeBtn = new JButton("Find route");
		routeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Desktop desktop = java.awt.Desktop.getDesktop();
				String origin = originInput.getText().replaceAll("\\s","+");
				String destination = location.getText().replaceAll("\\s","+");
				try {
					URI oURL = new URI(
							"https://www.google.com/maps/dir/?api=1&origin=" + origin +"&destination=" + destination);
					desktop.browse(oURL);
				} catch (URISyntaxException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		routeBtn.setBounds(590, 100, 100, 25);
		add(routeBtn);
		
		JButton returnButton = new JButton("Return");
		returnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(0);
			}
		});
		returnButton.setBounds(590, 130, 100, 25);
		add(returnButton);
	}
}