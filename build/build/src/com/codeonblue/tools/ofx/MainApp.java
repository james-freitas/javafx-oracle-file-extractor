package com.codeonblue.tools.ofx;

import java.io.IOException;

import com.codeonblue.tools.ofx.controller.ExtractorController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * 
 * Class responsible for loading the fxml file and launch the application
 * 
 * @author James Daniel Correia de Freitas
 * @since 26/12/2014
 * 
 */
public class MainApp extends Application {

	private Stage primaryStage;
	private AnchorPane rootLayout;

	@Override
	public void start(Stage primaryStage) throws Exception {

		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("DB File Extractor");
		// Set the application Icon
		this.primaryStage.getIcons().add(new Image("file:resources/images/ofx.png"));

		try {
			// Load the root layout from the fxml file
			FXMLLoader loader = new FXMLLoader(
					MainApp.class.getResource("view/fx-oracle.fxml"));
			rootLayout = (AnchorPane) loader.load();
			Scene scene = new Scene(rootLayout);
			scene.getStylesheets().add(this.getClass().getResource("view/style.css").toString());
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();

			// Give the controller access to the main app
			ExtractorController controller = loader.getController();
			controller.setMainApp(this);

		} catch (IOException e) {
			// Exception gets thrown if the fxml file could not be loaded
			throw new RuntimeException("Error ocurred: " + e.getMessage());
		}
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public static void main(String[] args) {
		launch(args);
	}

}
