package ia;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import com.google.gson.Gson;

public class Data {
	/*public static void main(String[] args) {
		
		Data data = new Data();
		Vehicle v = new Vehicle();
		v.setPlate("WY021X");
		v.setBrand("Opel");
		data.addVehicle(v);
		data.saveData("C:\\\\Users\\\\danil\\\\Documents\\\\!school\\\\CS IA\\\\saving1.json");
		
		Data data2 = new Data();
		data2.loadData("C:\\\\Users\\\\danil\\\\Documents\\\\!school\\\\CS IA\\\\saving1.json");
		data2.saveData("C:\\\\Users\\\\danil\\\\Documents\\\\!school\\\\CS IA\\\\saving2.json");
		
	}
	*/
	private static ArrayList<Vehicle> vehicles = new ArrayList<>();
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

}

