package ia;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.SpinnerNumberModel;

public class Tab2 extends JPanel {
	private Tabledata tabledata;

	DecimalFormat df = new DecimalFormat("###.##");

	Tab2(Tabledata tabledata, JTabbedPane tabbedPane) {
		this.tabledata = tabledata;
		Main main = new Main();
		setLayout(null);
		setBackground(new Color(76, 146, 212));

		JLabel carbrand = new JLabel("Brand:");
		layout(carbrand, 480, 10);

		JTextArea brandInput = new JTextArea();
		inputlayout(brandInput, 640, 10);

		JLabel carmodel = new JLabel("Model:");
		layout(carmodel, 480, 40);

		JTextArea modelInput = new JTextArea();
		inputlayout(modelInput, 640, 40);

		JLabel caryear = new JLabel("Production Year:");
		layout(caryear, 480, 70);

		JComboBox yearInput = new JComboBox();
		yearInput.addItem("");
		for(int i=2021;i>=1900;i=i-1) {
			yearInput.addItem(i);
		}
		yearInput.setSelectedIndex(0);
		inputlayout(yearInput, 640, 70);

		JLabel pricepermin = new JLabel("Price per minute:");
		layout(pricepermin, 480, 100);

		SpinnerNumberModel decimalmodel = new SpinnerNumberModel(0, 0, 1000, 0.01);
		JSpinner priceInput = new JSpinner(decimalmodel);
		inputlayout(priceInput, 640, 100);

		JLabel fuel = new JLabel("Fuel:");
		layout(fuel, 480, 130);

		JSpinner fuelInput = new JSpinner();
		inputlayout(fuelInput, 640, 130);

		JLabel mileage = new JLabel("Mileage:");
		layout(mileage, 480, 160);

		JSpinner mileageInput = new JSpinner();
		inputlayout(mileageInput, 640, 160);

		JLabel plates = new JLabel("License Plate:");
		layout(plates, 480, 190);

		JTextArea platesInput = new JTextArea();
		inputlayout(platesInput, 640, 190);

		JLabel owner = new JLabel("Owner:");
		layout(owner, 480, 220);

		JTextArea ownerInput = new JTextArea();
		inputlayout(ownerInput, 640, 220);

		JLabel status = new JLabel("Status:");
		layout(status, 480, 250);

		JComboBox statusInput = new JComboBox();
		statusInput.setModel(new DefaultComboBoxModel(new String[] {"", "Available", "In use"}));
		inputlayout(statusInput, 640, 250);


		JLabel user = new JLabel("Current user:");
		layout(user, 480, 280);

		JTextArea userInput = new JTextArea();
		inputlayout(userInput, 640, 280);

		user.setVisible(false);
		userInput.setVisible(false);

		statusInput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(statusInput.getSelectedItem() == "In use") {
					user.setVisible(true);
					userInput.setVisible(true);
				} else {
					user.setVisible(false);
					userInput.setVisible(false);

				}
			}
		});

		JButton addButton = new JButton("Add Car");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				if(brandInput.getText().equals("") 
						|| modelInput.getText().equals("") 
						|| priceInput.getValue().toString().equals("") 
						|| fuelInput.getValue().toString().equals("") || mileageInput.getValue().toString().equals("")
						|| hasLetter(priceInput.getValue().toString()) || hasLetter(fuelInput.getValue().toString()) 
						|| hasLetter(mileageInput.getValue().toString())) {
					JOptionPane.showMessageDialog(null,"Invalid Input", "Error", JOptionPane.ERROR_MESSAGE);
				} else {
					String brand = brandInput.getText();
					String model = modelInput.getText();
					String year = yearInput.getSelectedItem().toString();
					double price = Double.parseDouble(priceInput.getValue().toString());
					double fuel = Double.parseDouble(fuelInput.getValue().toString());
					double mileage = Double.parseDouble(mileageInput.getValue().toString());
					String plates = platesInput.getText();
					String owner = ownerInput.getText();
					String status = (String)statusInput.getSelectedItem();
					String user = userInput.getText();
					tabledata.addItem(brand, model, year, price, fuel, mileage, plates, owner, status, user);
					brandInput.setText("");
					modelInput.setText("");
					yearInput.setSelectedIndex(0);
					priceInput.setValue(0);
					fuelInput.setValue(0);
					mileageInput.setValue(0);
					platesInput.setText("");
					ownerInput.setText("");
					statusInput.setSelectedIndex(0);
					userInput.setText("");
				}
			}
		});
		addButton.setBounds(590, 340, 100, 25);
		add(addButton);

		JButton returnButton = new JButton("Return");
		returnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(0);
				brandInput.setText("");
				modelInput.setText("");
				yearInput.setSelectedIndex(50);
				priceInput.setToolTipText("");
				fuelInput.setToolTipText("");
				mileageInput.setToolTipText("");
				platesInput.setText("");
				ownerInput.setText("");
				statusInput.setSelectedIndex(0);
				userInput.setText("");
			}
		});
		returnButton.setBounds(590, 375, 100, 25);
		add(returnButton);
	}

	void layout(Component component, int xLocation, int yLocation) {
		component.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 16));
		component.setForeground(Color.white);
		component.setBounds(xLocation, yLocation, 160, 20);
		add(component);
	}
	void inputlayout(Component component, int xLocation, int yLocation) {
		component.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 16));
		component.setForeground(Color.GRAY);
		component.setBounds(xLocation, yLocation, 160, 20);
		add(component);
	}

	//Melih Altintas from https://stackoverflow.com/questions/18590901/check-if-a-string-contains-numbers-java
	public boolean hasLetter(String s) {
		boolean letter = false;

		//if (s != null && !s.isEmpty()) {
		for (char c : s.toCharArray()) {
			if (letter = Character.isLetter(c)) {
				break;
			}
		}
		//}

		return letter;
	}
}
