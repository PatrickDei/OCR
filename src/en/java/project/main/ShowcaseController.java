package en.java.project.main;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;

public class ShowcaseController {
	
	private File selectedDirectory;
	
	private String directory;

	@FXML
	private TableView<String> tab;
	
	@FXML
	private TableColumn<File, String> filenames;
	
	@FXML
	private TextArea text;
	
	@FXML
	private TextField filename;
	
	@FXML
	public void initialize(){
		filename.setPromptText("Type your new name here");
		selectedDirectory = ProgramController.selectedDirectory;
		directory = ProgramController.selectedDirectory.getAbsolutePath();
		text.setText("Click the \"Load\" button to load the files from the directory");

		
		
		/*lista = BazaPodataka
				.dohvatiArtikle()
				.stream()
				.filter(p -> p instanceof Automobil)
				.map(sc -> (Automobil)sc)
				.collect(Collectors.toList());
			ObservableList<Automobil> data;
			data = FXCollections.observableArrayList(lista);
			stupacNaslov.setCellValueFactory(new PropertyValueFactory<>("naslov"));
			stupacOpis.setCellValueFactory(new PropertyValueFactory<>("opis"));
			stupacSnaga.setCellValueFactory(new PropertyValueFactory<>("snagaKs"));
			stupacCijena.setCellValueFactory(new PropertyValueFactory<>("cijena"));
			stupacStanje.setCellValueFactory(new PropertyValueFactory<>("stanje"));
			tab.setItems(new FilteredList<>(data));
			
		tab.getSelectionModel().selectedIndexProperty().addListener(obj ->{
			Automobil auto = tab.getSelectionModel().selectedItemProperty().get();
			BorderPane root = new BorderPane();
			Text t = new Text(auto.tekstOglasa());
			root.getChildren().addAll(t);
			Scene scene = new Scene(root,500,500);
			Stage primaryStage = new Stage();
			primaryStage.setScene(scene);
			primaryStage.show();
		});*/
	}
	
	public void loadFiles() {
		text.setText("Chosen directory: " + directory);
		
		
		
		try (Stream<Path> walk = Files.walk(Paths.get(directory))) {
            // We want to find only regular files
            List<String> result = walk.filter(Files::isRegularFile)
                    .map(x -> x.toString()).collect(Collectors.toList());

            result.forEach(System.out::println);
            
            ObservableList<String> data;
			data = FXCollections.observableArrayList(result);
			//filenames.setCellValueFactory(new PropertyValueFactory<File, String>("file name"));
			tab.setItems(new FilteredList<>(data));
            
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public void newRule() {
		System.out.println("New rule");
	}
	
	public void applyRule() {
		
	}
	
	public void newName() {
		System.out.println("New name");
	}
	
	public void chooseDirectory(){
		
		DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setInitialDirectory(new File("src"));
        
        try {
        	selectedDirectory = directoryChooser.showDialog(null);
        	
	        if(selectedDirectory.isDirectory()) {
	        	directory = selectedDirectory.getAbsolutePath();
	        	text.setText("Chosen directory: " + selectedDirectory.getAbsolutePath());
	        }
        }catch(NullPointerException ex) {
	    	System.out.println("No directory chosen");
	    }
	}
}