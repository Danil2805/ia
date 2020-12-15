package ia;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.google.gson.Gson;

public class Tabledata extends DefaultTableModel {	
	private JTable table;
	private static final Object[] tableHeadings = new Object[] { 
			"Brand",
			"Model",
			"Production Year",
			"Price per minute",
			"Fuel",
			"Mileage",
			"License Plate",
			"Owner",
			"Status",
	};
	private boolean isEditable = false;

	Tabledata(){
		super(tableHeadings, 0);
	}

	public void setDataVector(Object[][] data) {
		super.setDataVector(data, tableHeadings);
	}

	public void addItem(Vehicle v) {
		addRow(new Object[] {
				v.getBrand(), v.getModel(), v.getYear(), v.getPrice(), v.getFuel(), v.getMileage(), v.getPlate(),  
				v.getOwner(), v.getStatus(), v.getUser()});
	}

	public boolean isCellEditable(int row, int column) {
		return isEditable;
	}
	public void toggleEditing() {
		isEditable =! isEditable;
	}

	public void deleteSelectedRow(Tabledata model, JTable table) {  
		int getSelectedRowForDeletion = table.getSelectedRow();
		if (getSelectedRowForDeletion >= 0) {
			model.removeRow(getSelectedRowForDeletion);
			JOptionPane.showMessageDialog(null, "Row Deleted");
		} else {
			JOptionPane.showMessageDialog(null,"Please select a row", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
