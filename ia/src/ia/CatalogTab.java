package ia;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Vector;

import javax.swing.DefaultButtonModel;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.text.JTextComponent;

import com.google.gson.Gson;

public class CatalogTab extends JPanel {
	private JTable table;
	private Tabledata tabledata;
	private JLabel storedLbl;
	JLabel availableLbl;

	CatalogTab(Tabledata tabledata, JTabbedPane tabbedPane) {
		Data.loadData("C:\\\\Users\\\\danil\\\\Documents\\\\!school\\\\CS IA\\\\saving.json");
		this.tabledata = tabledata;
		tabledata.addTableModelListener(new TableModelListener() {
			@Override
			public void tableChanged(TableModelEvent e) {
				refreshData();
			}
		});
		this.setBorder(new EmptyBorder(0, 0, 0, 0));
		this.setLayout(null);
		this.setBackground(new Color(76, 146, 212));
		
		JButton addBtn = new JButton("New Car");
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(1);
			}
		});
		addBtn.setBounds(10, 10, 100, 25);
		add(addBtn);

		JComboBox status = new JComboBox();
		status.setModel(new DefaultComboBoxModel(new String[] {"Available", "In use"}));

		JButton removeBtn = new JButton("Remove row");
		removeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Vehicle v = Data.getVehicle(table.getSelectedRow());
				Data.removeVehicle(v);
				tabledata.fireTableDataChanged();
			}
		});
		removeBtn.setBounds(1128, 10, 120, 25);
		add(removeBtn);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 45, 1239, 561);
		this.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(tabledata);

		storedLbl = new JLabel("Cars being shared: " + table.getRowCount());
		storedLbl.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 16));
		storedLbl.setBounds(115, 619, 200, 23);
		this.add(storedLbl);

		JButton saveBtn = new JButton("Save");
		saveBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Data.saveData("C:\\\\Users\\\\danil\\\\Documents\\\\!school\\\\CS IA\\\\saving.json");
			}
		});
		saveBtn.setBounds(10, 618, 90, 25);
		this.add(saveBtn);
		
		JButton locBtn = new JButton("Locate Selected Vehicle");
		locBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(2);
				Data.setSelectedVehicle(table);
				UrlTab.location.setText(Data.getSelectedVehicle().getLocation());
			}
		});
		locBtn.setBounds(120, 10, 180, 25);
		this.add(locBtn);
		
		JButton about = new JButton("About");
		about.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Made by Danylo Kirchatyi, Created on 07/01/2021, Version 1.0",
						"About", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		about.setBounds(1181, 618, 69, 23);
		add(about);

		int sumCol = 0;
		int j = 0;
		while(j<table.getRowCount()) {
			if(table.getValueAt(j,8).equals("Available")) {
				sumCol+=1;
			}
			j+=1;
		}
		availableLbl = new JLabel("Cars available: " + sumCol );
		availableLbl.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 16));
		availableLbl.setBounds(715, 619, 160, 23);
		this.add(availableLbl);
		
	}

	void refreshData() {
		storedLbl.setText("Cars being shared: " + table.getRowCount());
		int rows = table.getRowCount();
		int sumCol = 0;
		int j = 0;
		while(j<rows) {
			if(table.getValueAt(j,8).equals("Available")) {
				sumCol+=1;
			}
			j+=1;
		}
		availableLbl.setText("Cars available: " + sumCol);
	}


}