/*
 * Class: CMSC203 20798
 * Instructor:Ahmed Tarek
 * Description: This javafx file for the sales user input which provide user 
 * to input file to read and 
 * load the data and save in the output file 
 * Due: 11/18/2026
 * 
 * Platform/compiler: Java/Eclipse
 * I pledge that I have completed the programming
 * assignment independently. I have not copied the code
 * from a student or any source. I have not given my code
 * to any student.
 * Print your Name here: Siddhant Bikram Thapa
 */
package assignment5;

import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class SalesAppFX extends Application {

	private TextField inputField;
	private TextField outputField;
	private TextArea resultArea;

	private Button loadButton;
	private Button saveButton;

	private double[][] data;

	@Override
	public void start(Stage stage) {

		// Creating text fields
		inputField = new TextField();
		outputField = new TextField();

		inputField.setPromptText("Enter input filename");
		outputField.setPromptText("Enter output filename");

		// Creating buttons
		loadButton = new Button("Load & Process");
		saveButton = new Button("Save Summary");

		// Create result area
		resultArea = new TextArea();
		resultArea.setEditable(false);

		// Load button action
		loadButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				String inputFile = inputField.getText().trim();

				if (inputFile.isEmpty()) {
					resultArea.setText("Please enter an input filename.");
					return;
				}

				// Read sales data
				data = SalesFileIO.readSalesData(inputFile);

				if (data == null || data.length == 0) {
					resultArea.setText("No sales data found.");
					data = null;
					return;
				}

				// Calculate and display sales info
				StringBuilder report = new StringBuilder();

				report.append("Data loaded from " + inputFile + "\n\n");
				report.append(String.format("Total sales: %.2f%n", SalesDataUtility.getTotal(data)));
				report.append(String.format("Average sale: %.2f%n", SalesDataUtility.getAverage(data)));
				report.append(String.format("Highest sale: %.2f%n", SalesDataUtility.getHighestInArray(data)));
				report.append(String.format("Lowest sale: %.2f%n", SalesDataUtility.getLowestInArray(data)));

				// Display row totals
				report.append("\n");

				for (int row = 0; row < data.length; row++) {

					report.append(String.format("Row %d total: %.2f%n", row, SalesDataUtility.getRowTotal(data, row)));
				}

				resultArea.setText(report.toString());
			}
		});

		// Save button action
		saveButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				String outputFile = outputField.getText().trim();

				if (outputFile.isEmpty()) {
					resultArea.setText("Please enter an output filename.");
					return;
				}

				if (data == null || data.length == 0) {
					resultArea.setText("Please load sales data first.");
					return;
				}

				try {

					// Write summary to output file
					SalesFileIO.writeSummary(outputFile, data);

					resultArea.appendText("\nSummary saved to " + outputFile);

				} catch (FileNotFoundException e) {

					resultArea.appendText("\nUnable to save summary.");
				}
			}
		});

		// Arranging input fields
		HBox inputBox = new HBox(10, new Label("Input File:"), inputField);
		HBox outputBox = new HBox(10, new Label("Output File:"), outputField);
		HBox buttonBox = new HBox(10, loadButton, saveButton);

		// Arranging GUI
		VBox root = new VBox(10, inputBox, outputBox, buttonBox, resultArea);

		root.setStyle("-fx-padding: 15;");

		// Create and display window
		Scene scene = new Scene(root, 750, 520);
		stage.setTitle("Store Sales Data Processor");
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
