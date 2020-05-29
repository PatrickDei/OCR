package en.java.project.main;

import java.io.File;
import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.stage.DirectoryChooser;

public class ProgramController {
	
	public static File selectedDirectory;
	
	public void chooseDirectory(){
		
		DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setInitialDirectory(new File("src"));
        
        try {
        	selectedDirectory = directoryChooser.showDialog(null);
        	
	        if(selectedDirectory.isDirectory()) {
	        	BorderPane center = FXMLLoader.load(getClass().getResource("Showcase.fxml"));
	        	Main.setCenterPane(center);
	        }
        }catch (IOException e) {
	            e.printStackTrace();
	    }catch(NullPointerException ex) {
	    	System.out.println("No directory chosen");
	    }
	}
}