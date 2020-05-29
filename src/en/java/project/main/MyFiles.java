package en.java.project.main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MyFiles {
	
	private String name;
	
	public MyFiles(String name) {
		super();
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

	public static List<MyFiles> listFiles(String directory){
		try (Stream<Path> walk = Files.walk(Paths.get(directory))) {
            List<String> result = walk.filter(Files::isRegularFile)
                    .map(x -> x.toString()).collect(Collectors.toList());
            
            List<MyFiles> files = new ArrayList<>();

            for(String l : result){
            	l = l.replace(directory + "\\", "");
            	files.add(new MyFiles(l));
            }
            files = deleteDirectories(files);
            return files;
		}catch (IOException e) {
            e.printStackTrace();
        }
		System.out.println("Something went wrong when returning the file list! (MyFiles.java::listFiles)");
		return null;
	}

	private static List<MyFiles> deleteDirectories(List<MyFiles> files) {
		List<MyFiles> onlyFiles = new ArrayList<>();
		for(int i=0;i<files.size();i++) {
			if(!checkIfDirectory(files.get(i)))
				onlyFiles.add(files.get(i));
		}
		return onlyFiles;
	}

	private static boolean checkIfDirectory(MyFiles file) {
		for(int i=0;i<file.getName().length();i++)
			if(file.getName().charAt(i) == '\\')
				return true;	
		return false;
	}
}