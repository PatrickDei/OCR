package en.java.project.main;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

import static java.nio.file.StandardWatchEventKinds.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class Main implements ActionListener{
	
	public static void main(String args[]) {
		//set directory
		File dir = setDirectory();
		
		watchDirectory(dir.toPath());
		System.out.println("New one\n");
		watchDirectory(dir.toPath());
		
		File fox = new File("C:\\Java\\Tess4J\\test\\resources\\test-data\\eurotext.png");
		
		Tesseract instance = new Tesseract();
		instance.setDatapath("C:\\Java\\Tess4J\\tessdata");
		instance.setLanguage("eng+hrv");
		
		String s = null;
		try {
			s = instance.doOCR(fox);
		}
		catch (TesseractException e) {
			System.out.println("Failed!");
			e.printStackTrace();
		}
		System.out.println(s);
	}

	
	
	private static File setDirectory() {
		return new File("D:\\Fawk ye\\OCRtest");
	}

	
	
	private static void watchDirectory(Path path) {
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
		            if (!Files.probeContentType(child).equals("image/jpeg")) {
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
		
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}