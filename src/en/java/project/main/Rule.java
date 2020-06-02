package en.java.project.main;

public class Rule {
	
	private static Boolean ruleIsSet;

	private static int numOfChars;
	
	private static String previousWord;

	

	public static Boolean getRuleIsSet() {
		return ruleIsSet;
	}

	public static void setRuleIsSet(Boolean ruleIsSet) {
		Rule.ruleIsSet = ruleIsSet;
	}

	

	public static int getNumOfChars() {
		return numOfChars;
	}

	public static void setNumOfChars(int numOfChars) {
		Rule.numOfChars = numOfChars;
	}

	public static String getPreviousWord() {
		return previousWord;
	}

	public static void setPreviousWord(String previousWord) {
		Rule.previousWord = previousWord;
	}

	public Rule(Boolean ruleIsSet, int numOfChars, String previousWord) {
		super();
		Rule.ruleIsSet = ruleIsSet;
		Rule.numOfChars = numOfChars;
		Rule.previousWord = previousWord;
	}
	
}