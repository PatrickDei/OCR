package en.java.project.main;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.DirectoryChooser;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class ShowcaseController {
	
	private File selectedDirectory;
	
	private String directory;
	
	public static String t;
	
	private String type;

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
		t = text.getText();
		
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
			type = file.getName().substring(file.getName().length()-4);
			File f = MyFiles.getPath(file, selectedDirectory.getAbsolutePath());
			String s = instance.doOCR(f);
			text.setText(s);
			t = text.getText();
		} catch (TesseractException e) {
			System.out.println("Something went wrong with reading the image!");
			e.printStackTrace();
		}catch(NullPointerException ex) {
			System.out.println("Select a file first!");
		}
	}
	
	public void newRule() {
		try {
			BorderPane center = FXMLLoader.load(getClass().getResource("RuleSetter.fxml"));
			Main.setCenterPane(center);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void applyRule() {
		if(Rule.getRuleIsSet()) {
			MyFiles file = tab.getSelectionModel().getSelectedItem();
			String f = MyFiles.getPathForRename(file, selectedDirectory.getAbsolutePath());
			Path source = Paths.get(f);
			try {
				String newName = text.getText();
				int index = newName.indexOf(Rule.getPreviousWord()) + RuleSetterController.indexOf;
				System.out.println(index + "\n" + RuleSetterController.indexOf + "\n" + Rule.getPreviousWord());
				newName = newName.substring(index, index + Rule.getNumOfChars());
				System.out.println(newName);
				Files.move(source, source.resolveSibling(newName + type));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else
			System.out.println("No rule is set!");
	}
	
	public void newName() {
		MyFiles file = tab.getSelectionModel().getSelectedItem();
		String f = MyFiles.getPathForRename(file, selectedDirectory.getAbsolutePath());
		Path source = Paths.get(f);
		try {
			Files.move(source, source.resolveSibling(filename.getText()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void chooseDirectory(){
		DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setInitialDirectory(new File("src"));
        
        try {
        	selectedDirectory = directoryChooser.showDialog(null);
        	
	        if(selectedDirectory.isDirectory()) {
	        	directory = selectedDirectory.getAbsolutePath();
	        	text.setText("Chosen directory: " + selectedDirectory.getAbsolutePath());
	        	t = text.getText();
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