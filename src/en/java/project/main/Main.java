package en.java.project.main;

import java.io.IOException;

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
		//launch window
		launch(args);
		
		//set directory
		/*File dir = setDirectory();
		
		
		//watchDirectory(dir.toPath());
		System.out.println("New one\n");
		//watchDirectory(dir.toPath());
		
		File fox = new File("C:\\Java\\Tess4J\\test\\resources\\test-data\\eurotext.png");
		File test = new File("D:\\Fawk ye\\OCRtest\\test.png");
		
		Tesseract instance = new Tesseract();
		instance.setDatapath("C:\\Java\\Tess4J\\tessdata");
		instance.setLanguage("eng+hrv");
		
		String s = null;
		try {
			s = instance.doOCR(test);
		}
		catch (TesseractException e) {
			System.out.println("Failed!");
			e.printStackTrace();
		}
		System.out.println(s);*/
	}

	
	
	/*private static File setDirectory() {
		return new File("D:\\Fawk ye\\OCRtest");
	}*/

	
	
	/*private static void watchDirectory(Path path) {
		try {
			WatchService watcher = FileSystems.getDefault().newWatchService();
			WatchKey key = path.register(watcher,
                    ENTRY_CREATE);
			key = watcher.take();
		
			for (WatchEvent<?> event: key.pollEvents()) {
		        WatchEvent.Kind<?> kind = event.kind();

		        if (kind == OVERFLOW) {
		        	System.out.println("Overflow");
		            continue;
		        }
	
		        WatchEvent<Path> ev = (WatchEvent<Path>)event;
		        Path filename = ev.context();
	
		        try {
		            Path child = path.resolve(filename);
		            if (!Files.probeContentType(child).equals("text/plain")) {
		                System.err.format("New file '%s'" +
		                    " is not an image file.%n", filename);
		                continue;
		            }
		        } catch (IOException e) {
		            System.err.println(e);
		            continue;
		        }
		        
		        System.out.format("File created: %s%n", filename);
		    }
			
		    boolean valid = key.reset();
		    if (!valid)
		        return;
	    } catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			return;
		}
		
	}*/

	@Override
	public void start(Stage primaryStage) {
		try {
			primaryStage.setTitle("Optical Character Recognition");
			root = (BorderPane)FXMLLoader.load(getClass().getResource("Program.fxml"));
			scene = new Scene(root,500,500);
			scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
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