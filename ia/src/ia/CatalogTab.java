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

import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import com.google.gson.Gson;

public class CatalogTab extends JPanel {
	private JTable table;
	private Tabledata model;
	private JLabel storedLbl;
	JLabel profitLbl;

	CatalogTab(Tabledata data, JTabbedPane tabbedPane) {
		this.model = data;
		data.addTableModelListener(new TableModelListener() {
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
				data.deleteSelectedRow(model, table);
			}
		});
		removeBtn.setBounds(1128, 10, 120, 25);
		add(removeBtn);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 45, 1239, 561);
		this.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(model);

		storedLbl = new JLabel();
		storedLbl.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 16));
		storedLbl.setBounds(115, 619, 200, 23);
		this.add(storedLbl);

		profitLbl = new JLabel("");
		profitLbl.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 16));
		profitLbl.setBounds(715, 619, 160, 23);
		this.add(profitLbl);

		JButton saveBtn = new JButton("Save");
		saveBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Path savePath = Paths.get("\\\\NAEWAWWLIFIL01\\Students\\Danilo_kirchatyy\\Documents\\all school files\\Yr13CompSci\\CS IA\\saving.json");
				String savePath1;
				try {
					savePath1 = new String(Files.readAllBytes(savePath));
					System.out.println(savePath1);
					if(savePath1.equals("")) {
						String savelocation = JOptionPane.showInputDialog(null, "Specify save location", null);
						data.saveLocation("\\\\NAEWAWWLIFIL01\\Students\\Danilo_kirchatyy\\Documents\\all school files\\Yr13CompSci\\CS IA\\saving.json", savelocation);
					} else {
						data.saveData(savePath1, model);
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		saveBtn.setBounds(10, 618, 90, 25);
		this.add(saveBtn);

		JToggleButton editBtn = new JToggleButton("Edit table");
		editBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.toggleEditing();
				table.getColumnModel().getColumn(8).setCellEditor(new DefaultCellEditor(status));
				boolean enbl =! editBtn.isSelected();
				System.out.println(enbl);
				addBtn.setEnabled(enbl);
				saveBtn.setEnabled(enbl);
				removeBtn.setEnabled(enbl);
			}
		});
		editBtn.setBounds(1023, 10, 100, 25);
		add(editBtn);

		Path savePath = Paths.get("\\\\NAEWAWWLIFIL01\\Students\\Danilo_kirchatyy\\Documents\\all school files\\Yr13CompSci\\CS IA\\saving.json");
		String savePath1;
		try {
			savePath1 = new String(Files.readAllBytes(savePath));
			System.out.println(savePath1);
			data.loadData(savePath1, model);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
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
		profitLbl.setText("Cars available: " + sumCol);
	}

	
}