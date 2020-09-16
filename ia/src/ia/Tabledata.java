package ia;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Vector;

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
			"Current user" };
	private boolean isEditable = false;
	
	Tabledata(){
		super(tableHeadings, 0);
	}
	
	public void setDataVector(Object[][] data) {
		super.setDataVector(data, tableHeadings);
	}
	
	public void addItem(String brand, String model, 
			String year, double price, double fuel, double mileage, 
			String plate, String owner, String status, String user) {
		addRow(new Object[] {
			brand, model, year, price, fuel, mileage, plate,  
			owner, status, user});
	}
	
	public void saveData(String filename, Tabledata model) {
		Gson gson = new Gson();
		Vector dataVector = model.getDataVector();
		String textData = gson.toJson(dataVector);

		Path path = Paths.get(filename);
		try {
			Files.write(path,textData.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void loadData(String filename, Tabledata model) {
		Path path = Paths.get(filename);
		try {
			String textData = new String(Files.readAllBytes(path));
			System.out.println(textData);

			Gson gson = new Gson();
			Object[][] tableData = gson.fromJson(textData, Object[][].class);
			model.setDataVector(tableData);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void saveLocation(String filename, String savelocation) {
		Gson gson = new Gson();

		Path path = Paths.get(filename);
		try {
			Files.write(path,savelocation.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
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
