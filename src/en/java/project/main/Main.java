package en.java.project.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

@SuppressWarnings("unused")
public class Main extends Application {
	
	private static BorderPane root;
	private static Scene scene;
	
	public static void main(String args[]) {
		launch(args);	
		Rule r;
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			primaryStage.setTitle("Optical Character Recognition");
			root = (BorderPane)FXMLLoader.load(getClass().getResource("Program.fxml"));
			scene = new Scene(root,1200,800);
			//scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Scene getScene() {
		return scene;
	}
	
	public static void setCenterPane(BorderPane center) {
		root.setCenter(center);
	}
}