package com.codeonblue.tools.ofx.controller;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Dialogs;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;

import com.codeonblue.tools.ofx.MainApp;
import com.codeonblue.tools.ofx.infra.DBConnection;
import com.codeonblue.tools.ofx.logic.Extractable;
import com.codeonblue.tools.ofx.logic.OracleLongRawExtractor;
import com.codeonblue.tools.ofx.model.ConnectionData;
import com.codeonblue.tools.ofx.model.QueryExtractionData;

/**
 * 
 * Class responsible for binding the fxml properties and methods. It contains
 * the logic of the application
 * 
 * @author James Daniel Correia de Freitas
 * @since 29/12/2014
 * 
 */
public class ExtractorController {

	@FXML
	private TextField oracleServerText;
	@FXML
	private TextField portText;
	@FXML
	private TextField instanceText;
	@FXML
	private TextField userNameText;
	@FXML
	private PasswordField passwordText;

	@FXML
	private TextField tableNameText;
	@FXML
	private TextField fileNameColumnText;
	@FXML
	private TextField fileContentColumnText;
	@FXML
	private TextField orderByColumnText;
	@FXML
	private TextField outputFolderText;

	@FXML
	private Button outputButton;
	@FXML
	private Button clearFieldsButton;
	@FXML
	private Button extractFilesButton;

	@FXML
	private TextArea exportLogTextArea;

	private final ConnectionData connectionData = new ConnectionData();

	private final QueryExtractionData queryExtractionData = new QueryExtractionData();

	// Reference to the main application
	private MainApp mainApp;

	public ExtractorController() {

	}

	@FXML
	public void initialize() {
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	public ConnectionData getConnectionData() {
		return connectionData;
	}

	public QueryExtractionData getQueryExtractionData() {
		return queryExtractionData;
	}

	@FXML
	private void handleOutputButton() {
		DirectoryChooser directoryChooser = new DirectoryChooser();
		File selectedDirectory = directoryChooser.showDialog(null);

		if (selectedDirectory != null) {
			outputFolderText.setText(selectedDirectory.getAbsolutePath());
		}
	}

	@FXML
	private void handleClearFieldsButton() {
		oracleServerText.clear();
		portText.clear();
		instanceText.clear();
		userNameText.clear();
		passwordText.clear();
		tableNameText.clear();
		fileNameColumnText.clear();
		fileContentColumnText.clear();
		orderByColumnText.clear();
		outputFolderText.clear();
		exportLogTextArea.clear();
	}

	private boolean isAllFieldsFilled() {
		return (!oracleServerText.getText().equalsIgnoreCase("")
				&& !portText.getText().equalsIgnoreCase("")
				&& !instanceText.getText().equalsIgnoreCase("")
				&& !userNameText.getText().equalsIgnoreCase("")
				&& !passwordText.getText().equalsIgnoreCase("")
				&& !tableNameText.getText().equalsIgnoreCase("")
				&& !fileNameColumnText.getText().equalsIgnoreCase("")
				&& !fileContentColumnText.getText().equalsIgnoreCase("")
				&& !orderByColumnText.getText().equalsIgnoreCase("") && !outputFolderText
				.getText().equalsIgnoreCase(""));
	}

	private boolean isValidPortNumber() {
		return (portText.getText().matches("[0-9]+"));
	}

	@FXML
	private void handleExtractFilesButton() {
		if (!isAllFieldsFilled()) {
			Dialogs.showErrorDialog(mainApp.getPrimaryStage(),
					"Please enter data in all fields!", "No fields filled",
					"Validation Error");
			return;
		}
		if (!isValidPortNumber()) {
			Dialogs.showErrorDialog(mainApp.getPrimaryStage(),
					"Please enter a valid port number!", "Invalid port number",
					"Validation Error");
			return;
		}

		extractFiles();

	}

	private void extractFiles() {
		exportLogTextArea.clear();
		setConnectionData(this.connectionData);
		setQueryExtractionData(this.queryExtractionData);

		exportLogTextArea.appendText("Conecting on "
				+ connectionData.getUsername().toUpperCase()
				+ " at service / instance "
				+ connectionData.getInstance().toUpperCase() + ".\n");
		exportLogTextArea
				.appendText("----------------------------------------------------------\n");

		Connection connection = new DBConnection()
				.getConnection(connectionData);

		if (connection == null) {
			exportLogTextArea
					.appendText("Error stablishing connection using username: "
							+ connectionData.getUsername().toUpperCase()
							+ " at service / instance "
							+ connectionData.getInstance().toUpperCase()
							+ ".\n");
			exportLogTextArea
					.appendText("----------------------------------------------------------\n");
			exportLogTextArea.appendText("Processing canceled.\n");
			exportLogTextArea
					.appendText("----------------------------------------------------------\n");
			Dialogs.showErrorDialog(mainApp.getPrimaryStage(),
					"Error stablishing connection using username: "
							+ connectionData.getUsername().toUpperCase()
							+ " at service / instance "
							+ connectionData.getInstance().toUpperCase() + ".",
					"Connection Error", "Database Error");
			return;
		}
		exportLogTextArea.appendText("Connected on "
				+ connectionData.getUsername().toUpperCase()
				+ " at service / instance "
				+ connectionData.getInstance().toUpperCase() + ".\n");
		exportLogTextArea
				.appendText("----------------------------------------------------------\n");

		exportLogTextArea.appendText("Generating file names list using query: "
				+ "SELECT " + fileNameColumnText.getText().toUpperCase()
				+ " FROM " + tableNameText.getText().toUpperCase()
				+ " ORDER BY " + orderByColumnText.getText().toUpperCase()
				+ ";\n");
		exportLogTextArea
				.appendText("----------------------------------------------------------\n");

		Extractable extractor = new OracleLongRawExtractor();
		List<String> fileNames = extractor.getFileNames(connection,
				queryExtractionData);

		if (fileNames == null) {
			exportLogTextArea
					.appendText("Error ocurred when retrieving file names.\n");
			exportLogTextArea
					.appendText("----------------------------------------------------------\n");
			exportLogTextArea.appendText("Processing canceled.\n");
			exportLogTextArea
					.appendText("----------------------------------------------------------\n");
			Dialogs.showErrorDialog(
					mainApp.getPrimaryStage(),
					"Error ocurred when retrieving file names. Please check query fields.",
					"Bad formed query", "Query Error");
			return;
		}

		exportLogTextArea.appendText("File names list ready.\n");
		exportLogTextArea
				.appendText("----------------------------------------------------------\n");

		exportLogTextArea.appendText("Extracting files using query: "
				+ "SELECT " + fileContentColumnText.getText().toUpperCase()
				+ " FROM " + tableNameText.getText().toUpperCase()
				+ " ORDER BY " + orderByColumnText.getText().toUpperCase()
				+ ";\n");
		exportLogTextArea
				.appendText("----------------------------------------------------------\n");

		if (!extractor.getFilesFromRows(connection, fileNames,
				queryExtractionData, outputFolderText.getText())) {
			exportLogTextArea
					.appendText("Error ocurred when retrieving file data.\n");
			exportLogTextArea
					.appendText("----------------------------------------------------------\n");
			exportLogTextArea.appendText("Processing canceled.\n");
			exportLogTextArea
					.appendText("----------------------------------------------------------\n");
			Dialogs.showErrorDialog(
					mainApp.getPrimaryStage(),
					"Error ocurred when retrieving file data. Please check query fields.",
					"Bad formed query", "Query Error");
			return;
		}

		try {
			connection.close();
		} catch (SQLException e) {
			throw new RuntimeException("An error ocurred: " + e.getMessage());
		}
		Dialogs.showInformationDialog(mainApp.getPrimaryStage(),
				"File Extraction finished.  Please check output folder.",
				"Process finished.", "Information");
		exportLogTextArea.appendText("Processing finished. \n");
		exportLogTextArea
				.appendText("----------------------------------------------------------\n");
	}

	private void setQueryExtractionData(QueryExtractionData queryExtractionData) {
		this.queryExtractionData.setFileContentColumn(fileContentColumnText
				.getText());
		this.queryExtractionData
				.setFileNameColumn(fileNameColumnText.getText());
		this.queryExtractionData.setOrderByColumn(orderByColumnText.getText());
		this.queryExtractionData.setTableName(tableNameText.getText());
	}

	private void setConnectionData(ConnectionData connectionData) {
		this.connectionData.setOracleServer(oracleServerText.getText());
		this.connectionData.setPort(portText.getText());
		this.connectionData.setInstance(instanceText.getText());
		this.connectionData.setUsername(userNameText.getText());
		this.connectionData.setPassword(passwordText.getText());
	}

}
