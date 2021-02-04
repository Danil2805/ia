package ia;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.google.gson.Gson;

public class Data {
	private static ArrayList<Vehicle> vehicles = new ArrayList<>();
	static Vehicle selectedv;
	Data() {
	}
	public static void saveData(String filename) {
		Gson gson = new Gson();
		String textData = gson.toJson(vehicles.toArray(new Vehicle[0]));
		System.out.println(textData);
		Path path = Paths.get(filename);
		try {
			Files.write(path,textData.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void loadData(String filename) {
		Path path = Paths.get(filename);
		try {
			String textData = new String(Files.readAllBytes(path));
			System.out.println(textData);

			Gson gson = new Gson();
			Vehicle[] newvehicles = gson.fromJson(textData, Vehicle[].class);
			vehicles = new ArrayList<Vehicle>(Arrays.asList(newvehicles));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void addVehicle(Vehicle vehicle) {
		vehicles.add(vehicle);
	}
	public static int getNumVehicles() {
		return vehicles.size();
	}
	public static Vehicle getVehicle(int index) {
		return vehicles.get(index);
	}
	public static void removeVehicle(Vehicle v) {
		vehicles.remove(v);
	}
	public static void setSelectedVehicle(JTable table) {
		selectedv = getVehicle(table.getSelectedRow());
	}
	public static Vehicle getSelectedVehicle() {
		return selectedv;
	}

}