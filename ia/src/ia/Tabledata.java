package ia;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import com.google.gson.Gson;
//reference https://docs.oracle.com/javase/7/docs/api/javax/swing/table/AbstractTableModel.html
public class Tabledata extends AbstractTableModel {	
	public int getRowCount() {
		return Data.getNumVehicles();
	}
	public int getColumnCount() {
		return 11;
	}
	public Object getValueAt(int row, int column) {
		Vehicle v = Data.getVehicle(row);
		if(column == 0) {
			return v.getPlate();
		} else if(column == 1) {
			return v.getBrand();
		} else if(column == 2) {
			return v.getModel();
		} else if(column == 3) {
			return v.getYear();
		} else if(column == 4) {
			return v.getPrice();
		} else if(column == 5) {
			return v.getFuel();
		} else if(column == 6) {
			return v.getMileage();
		} else if(column == 7) {
			return v.getOwner();
		} else if(column == 8) {
			return v.getStatus();
		} else if(column == 9) {
			return v.getUser();
		} else if(column == 10) {
			return v.getLocation();
		} else {
			return "No data";
		}
	}
	public String getColumnName(int column) {
		if(column == 0) {
			return "License plate";
		} else if(column == 1) {
			return "Brand";
		} else if(column == 2) {
			return "Model";
		} else if(column == 3) {
			return "Production Year";
		} else if(column == 4) {
			return "Price per minute";
		} else if(column == 5) {
			return "Fuel";
		} else if(column == 6) {
			return "Mileage";
		} else if(column == 7) {
			return "Owner";
		} else if(column == 8) {
			return "Status";
		} else if(column == 9) {
			return "User";
		} else if(column == 10) {
			return "Location";
		} else {
			return "No data";
		}
	}
	public boolean isCellEditable(int row, int column) {
		return column == 10 || column == 9 || column == 8;
	}
	public void setValueAt(Object value, int row, int column) {
		if(column == 10) {
			Vehicle v = Data.getVehicle(row);
			v.setLocation(value.toString());
		} else if(column == 9) {
			Vehicle v = Data.getVehicle(row);
			v.setUser(value.toString());
		} else if(column == 8) {
			Vehicle v = Data.getVehicle(row);
			v.setStatus(value.toString());
		}
	}
}
/*	private JTable table;
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
 */
