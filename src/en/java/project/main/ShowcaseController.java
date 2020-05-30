package en.java.project.main;

import java.io.File;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.DirectoryChooser;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class ShowcaseController {
	
	private File selectedDirectory;
	
	private String directory;

	@FXML
	private TableView<MyFiles> tab;
	@FXML
	private TableColumn<MyFiles, String> filenames;
	@FXML
	private TextArea text;
	@FXML
	private TextField filename;
	
	@FXML
	public void initialize(){
		filename.setPromptText("Type your new name here");
		selectedDirectory = ProgramController.selectedDirectory;
		directory = ProgramController.selectedDirectory.getAbsolutePath();
		text.setText("Chosen directory: " + directory + "\nSelect a file and click \"Read\" to read the file");
		
		ObservableList<MyFiles> data;
		data = FXCollections.observableArrayList(MyFiles.listFiles(ProgramController.selectedDirectory.toString()));
		filenames.setCellValueFactory(new PropertyValueFactory<>("name"));
		tab.setItems(new FilteredList<>(data));
	}
	
	public void readFromFile() {
		Tesseract instance = new Tesseract();
		instance.setDatapath("C:\\Java\\Tess4J\\tessdata");
		instance.setLanguage("eng+hrv");
		
		
		try {
			MyFiles file = tab.getSelectionModel().getSelectedItem();
			File f = MyFiles.getPath(file, selectedDirectory.getAbsolutePath());
			String s = instance.doOCR(f);
			text.setText(s);
		} catch (TesseractException e) {
			System.out.println("Something went wrong with reading the image!");
			e.printStackTrace();
		}catch(NullPointerException ex) {
			System.out.println("Select a file first!");
		}
	}
	
	public void newRule() {
		 System.out.println("Rules are not implemented yet!");
	}
	
	public void applyRule() {
		System.out.println("No rule set!");
	}
	
	public void newName() {
		System.out.println("Renaming is not implemented yet");
	}
	
	public void chooseDirectory(){
		DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setInitialDirectory(new File("src"));
        
        try {
        	selectedDirectory = directoryChooser.showDialog(null);
        	
	        if(selectedDirectory.isDirectory()) {
	        	directory = selectedDirectory.getAbsolutePath();
	        	text.setText("Chosen directory: " + selectedDirectory.getAbsolutePath());
	        	ObservableList<MyFiles> data;
	    		data = FXCollections.observableArrayList(MyFiles.listFiles(directory));
	    		
	    		filenames.setCellValueFactory(new PropertyValueFactory<>("name"));
	    		tab.setItems(new FilteredList<>(data));
	        }
        }catch(NullPointerException ex) {
	    	System.out.println("No directory chosen");
	    }
	}
}