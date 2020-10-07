package ia;
import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.BrowserFactory;
import javax.swing.*;
import java.awt.*;

public class GoogleMapsSample {

	public static void main(String[] args) {

		Browser browser = BrowserFactory.create();
		JFrame frame = new JFrame("JxBrowser Google Maps");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.add(browser.getView().getComponent(), BorderLayout.CENTER);
		frame.setSize(700, 500);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		browser.loadURL("http://maps.google.com");
	}
}
​


