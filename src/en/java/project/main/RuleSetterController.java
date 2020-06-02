package en.java.project.main;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class RuleSetterController {
	
	public static int indexOf;
	
	@FXML
	private TextField filename;
	
	@FXML
	private TextArea text;
	
	@FXML
	public void initialize() {
		text.setText(ShowcaseController.t);
	}
	
	public void setRule() {
		String name = filename.getText();
		
		String s = text.getText();
		int intIndex = s.indexOf(name);
		if(intIndex != -1){
		      Rule.setRuleIsSet(true);
		      Rule.setNumOfChars(name.length());
		      indexOf = bestIndex(intIndex, text.getText());
		      Rule.setPreviousWord(text.getText().substring(intIndex - indexOf, intIndex));
		      try {
					BorderPane center = FXMLLoader.load(getClass().getResource("Showcase.fxml"));
					Main.setCenterPane(center);
				} catch (IOException e) {
					e.printStackTrace();
				}
		}else {
			System.out.println("Cannot understand the pattern!");
		}
	}
	
	private int bestIndex(int intIndex, String text) {
		int br=0;
		for(int i = intIndex ; i > 0 ; i--) {
			if(text.charAt(i) == '\n')
				return br;
			br++;
		}
		return 3;
	}

	public void goBack() {
		try {
			BorderPane center = FXMLLoader.load(getClass().getResource("Showcase.fxml"));
			Main.setCenterPane(center);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}