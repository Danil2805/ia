package ia;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableColumn;

import com.google.gson.Gson;



import javax.swing.JScrollPane;
import java.awt.Button;
import java.awt.Color;

import javax.swing.JTabbedPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class Main {
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(1280, 720);
		frame.setTitle("Car sharing management app");
		Object[] options = { "Exit", "Cancel" };
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.setContentPane(tabbedPane);

		Tabledata data = new Tabledata();
		tabbedPane.addTab("Table", null, new CatalogTab(data, tabbedPane), null);
		tabbedPane.add(new Tab2(data, tabbedPane), "Add item tab");
		tabbedPane.add(new UrlTab(), "Distance tab");
		
	}
}
